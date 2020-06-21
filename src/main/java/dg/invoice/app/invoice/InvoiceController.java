package dg.invoice.app.invoice;

import dg.invoice.app.additem.AddItemView;
import dg.invoice.app.commons.AppConstants;
import dg.invoice.app.commons.Controller;
import dg.invoice.app.commons.FileOperationUtil;
import dg.invoice.app.commons.ValidatorUtil;
import dg.invoice.app.model.Invoice;
import dg.invoice.app.model.Item;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class InvoiceController extends Controller {

    private Stage invoiceStage;
    private Stage addItemStage;
    private AddItemView addItemView = new AddItemView();
    private ObservableList<Item> itemsTableList;
    private boolean isDataUnlocked;
    private Invoice invoice;

    @FXML
    private TableView<Item> tbItemView;
    @FXML
    private TableColumn<Item, String> colName;
    @FXML
    private TableColumn<Item, Integer> colNum;
    @FXML
    private TableColumn<Item, Integer> colAmount;
    @FXML
    private TableColumn<Item, Integer> colDiscount;
    @FXML
    private TableColumn<Item, Integer> colTax;
    @FXML
    private TableColumn<Item, Double> colNetPrice;
    @FXML
    private TableColumn<Item, Double> colGrossPrice;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtId;
    @FXML
    private DatePicker dpIssued;
    @FXML
    private DatePicker dpTransactionDate;
    @FXML
    private DatePicker dpPaymentDue;
    @FXML
    private ChoiceBox<String> cbPaymentMethod;
    @FXML
    private TextField txtSellerName;
    @FXML
    private TextField txtSellerAddress;
    @FXML
    private TextField txtSellerPostalCode;
    @FXML
    private TextField txtSellerCity;
    @FXML
    private TextField txtSellerNip;
    @FXML
    private TextField txtSellerBankNum;
    @FXML
    private TextField txtBuyerName;
    @FXML
    private TextField txtBuyerAddress;
    @FXML
    private TextField txtBuyerPostalCode;
    @FXML
    private TextField txtBuyerCity;
    @FXML
    private TextField txtBuyerNip;
    @FXML
    private Button btnLock;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        isDataUnlocked = true;
        txtTotal.setText("0.0");
        initItemTable();
        initPaymentMethods();
        invoice = new Invoice();
    }

    private void initItemTable() {
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNum.setCellValueFactory(new PropertyValueFactory<>("number"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colTax.setCellValueFactory(new PropertyValueFactory<>("tax"));
        colNetPrice.setCellValueFactory(new PropertyValueFactory<>("netPrice"));
        colGrossPrice.setCellValueFactory(new PropertyValueFactory<>("grossPrice"));
        itemsTableList = FXCollections.observableArrayList();
        tbItemView.setItems(itemsTableList);
    }

    private void initPaymentMethods(){
        cbPaymentMethod.setItems(FXCollections.observableArrayList(
                AppConstants.PAYMENT_BANK, AppConstants.PAYMENT_CASH));
        cbPaymentMethod.getSelectionModel().selectedIndexProperty().addListener(event -> paymentMethodChanged());
    }

    private void paymentMethodChanged(){
        String paymentMethod = cbPaymentMethod.getValue();
        if(AppConstants.PAYMENT_BANK.equals(paymentMethod)){
            txtSellerBankNum.setDisable(true);
        } else {
            txtSellerBankNum.setDisable(false);
        }
    }
    @FXML
    private void btnAddItemClicked(){
        if(addItemStage != null && addItemStage.isShowing()){
            Controller.showError(AppConstants.ERROR_TEXT, AppConstants.ERROR_DUPLICATE_WINDOW_HEADER, AppConstants.ERROR_DUPLICATE_WINDOW_MESSAGE);
        } else {
            addItemStage = addItemView.showStage(invoiceStage, this);
        }
    }


    public void addItemToInvoice(Item item){
        loadItemFromFile(item);
        invoice.getItems().add(item);
    }

    public void loadItemFromFile(Item item){
        itemsTableList.add(item);
        tbItemView.refresh();
    }

    public void recalculateTotal(double grossPrice){
        double total = Double.parseDouble(txtTotal.getText()) + grossPrice;
        txtTotal.setText(String.valueOf(total));
    }

    void setStage(Stage stage) {
        this.invoiceStage = stage;
    }

    @FXML
    private void btnLockClicked(){
        if(isDataUnlocked){
            if(!isDataValid()){
                return;
            }
            isDataUnlocked =false;
            enableElements(false);
            btnLock.setText("Unlock");
        } else {
            isDataUnlocked =true;
            enableElements(true);
            btnLock.setText("Validate and Lock");
        }
    }

    private void enableElements(boolean choice){
        txtId.setDisable(!choice);
        dpIssued.setDisable(!choice);
        dpTransactionDate.setDisable(!choice);
        dpPaymentDue.setDisable(!choice);
        cbPaymentMethod.setDisable(!choice);
        txtSellerName.setDisable(!choice);
        txtSellerAddress.setDisable(!choice);
        txtSellerPostalCode.setDisable(!choice);
        txtSellerCity.setDisable(!choice);
        txtSellerNip.setDisable(!choice);
        txtSellerBankNum.setDisable(!choice);
        txtBuyerName.setDisable(!choice);
        txtBuyerAddress.setDisable(!choice);
        txtBuyerPostalCode.setDisable(!choice);
        txtBuyerCity.setDisable(!choice);
        txtBuyerNip.setDisable(!choice);
    }
    private boolean isDataValid(){

        return isGeneralInformationValid() && isSellerInformationValid() && isBuyerInformationValid();
    }

    private boolean isGeneralInformationValid(){

        LocalDate issued = dpIssued.getValue();
        LocalDate transactionDate = dpTransactionDate.getValue();
        LocalDate paymentDue = dpPaymentDue.getValue();

        //identifier not provided
        String errorMsg = null;
        if(txtId.getText() == null || txtId.getText().length() < 1){
            errorMsg = String.format(AppConstants.ERROR_EMPTY_FIELD_MESSAGE, "Identifier");
            showError(AppConstants.ERROR_TEXT,AppConstants.ERROR_EMPTY_FIELD_HEADER, errorMsg);
            return false;
        }

        //payment method not provided
        if(cbPaymentMethod.getValue() == null || cbPaymentMethod.getValue().length() == 0){
            errorMsg = String.format(AppConstants.ERROR_EMPTY_FIELD_MESSAGE, "Identifier");
            showError(AppConstants.ERROR_TEXT,AppConstants.ERROR_EMPTY_FIELD_HEADER, errorMsg);
            return false;
        }

        // incorrect date
        if(!issued.isAfter(LocalDate.now()) && !transactionDate.isBefore(LocalDate.now()) && !paymentDue.isBefore(LocalDate.now())){
            showError(AppConstants.ERROR_TEXT,AppConstants.ERROR_INVALID_DATE_HEADER, AppConstants.ERROR_INVALID_DATE_MESSAGE);
            return false;
        }

        invoice.setId(txtId.getText());
        invoice.setIssueDate(issued);
        invoice.setTransactionDate(transactionDate);
        invoice.setPaymentDueDate(paymentDue);
        invoice.setPaymentMethod(cbPaymentMethod.getValue());

        return true;
    }

    private boolean isSellerInformationValid(){

        // length only validation fields
        String name = txtSellerName.getText();
        String address = txtSellerAddress.getText();
        String city = txtSellerCity.getText();

        if(name == null || name.length() < 1 || address == null || address.length() < 1 || city == null || city.length() < 1){
            String errorMsg = String.format(AppConstants.ERROR_EMPTY_FIELD_MESSAGE, "seller name/address/city");
            showError(AppConstants.ERROR_TEXT,AppConstants.ERROR_EMPTY_FIELD_HEADER, errorMsg);
            return false;
        }

        // format validation fields
        String nipString = txtSellerNip.getText();
        String bankNumberString = txtSellerBankNum.getText();
        String postalCodeString = txtSellerPostalCode.getText();

        String errorMsg = null;
        if(nipString == null || !ValidatorUtil.isNipValid(nipString)){
            errorMsg = String.format(AppConstants.ERROR_INVALID_INPUT_MESSAGE, "seller NIP (XXX-XXX-XX-XX)");
        } else if(!txtSellerBankNum.isDisabled() && (bankNumberString == null || !ValidatorUtil.isNumeral(bankNumberString))){
            errorMsg = String.format(AppConstants.ERROR_INVALID_INPUT_MESSAGE, "seller bank number (digits only accepted)");
        } else if(postalCodeString == null || !ValidatorUtil.isPostalCodeValid(postalCodeString)){
            errorMsg = String.format(AppConstants.ERROR_INVALID_INPUT_MESSAGE, "seller postal code (XX-XXX)");
        }

        if(errorMsg != null){
            Controller.showError(AppConstants.ERROR_TEXT, AppConstants.ERROR_INVALID_INPUT_HEADER, errorMsg);
            return false;
        }

        invoice.setSellerName(name);
        invoice.setSellerAddress(address);
        invoice.setSellerCity(city);
        invoice.setSellerNIP(nipString);
        invoice.setSellerPostalCode(postalCodeString);
        invoice.setSellerAccountNumber(bankNumberString);
        return true;
    }

    private boolean isBuyerInformationValid(){

        // length only validation fields
        String name = txtBuyerName.getText();
        String address = txtBuyerAddress.getText();
        String city = txtBuyerCity.getText();

        if(name == null || name.length() < 1 || address == null || address.length() < 1 || city == null || city.length() < 1){
            String errorMsg = String.format(AppConstants.ERROR_EMPTY_FIELD_MESSAGE, "buyer name/address/city");
            showError(AppConstants.ERROR_TEXT,AppConstants.ERROR_EMPTY_FIELD_HEADER, errorMsg);
            return false;
        }

        // format validation fields
        String nipString = txtBuyerNip.getText();
        String postalCodeString = txtBuyerPostalCode.getText();

        String errorMsg = null;
        if(nipString == null || !ValidatorUtil.isNipValid(nipString)){
            errorMsg = String.format(AppConstants.ERROR_INVALID_INPUT_MESSAGE, "buyer NIP (XXX-XXX-XX-XX)");
        } else if(postalCodeString == null || !ValidatorUtil.isPostalCodeValid(postalCodeString)){
            errorMsg = String.format(AppConstants.ERROR_INVALID_INPUT_MESSAGE, "buyer postal code (XX-XXX)");
        }

        if(errorMsg != null){
            Controller.showError(AppConstants.ERROR_TEXT, AppConstants.ERROR_INVALID_INPUT_HEADER, errorMsg);
            return false;
        }

        invoice.setBuyerName(name);
        invoice.setBuyerAddress(address);
        invoice.setBuyerCity(city);
        invoice.setBuyerNIP(nipString);
        invoice.setBuyerPostalCode(postalCodeString);
        return true;
    }

    @FXML
    private void saveInvoice(){

        if(isDataUnlocked){
            showError(AppConstants.ERROR_TEXT,AppConstants.ERROR_SAVE_HEADER,AppConstants.ERROR_SAVE_UNLOCKED_MESSAGE);
            return;
        }
        if(invoice.getItems().size() == 0){
            showError(AppConstants.ERROR_TEXT,AppConstants.ERROR_SAVE_HEADER,AppConstants.ERROR_SAVE_NO_ITEMS_MESSAGE);
            return;
        }
        boolean status = FileOperationUtil.saveInvoiceFile(invoice);

        if(status){
            showInfo(AppConstants.INFO_TEXT,AppConstants.INFO_SUCCESS);
        } else {
            showInfo(AppConstants.ERROR_TEXT,AppConstants.INFO_FAILURE);
        }
    }

    @FXML
    private void loadInvoice(){
        if(!isDataUnlocked){
            showError(AppConstants.ERROR_TEXT,AppConstants.ERROR_LOAD_HEADER,AppConstants.ERROR_LOAD_MESSAGE);
            return;
        }
        invoice = FileOperationUtil.loadInvoiceFile();
        if(invoice == null){
            showInfo(AppConstants.ERROR_TEXT,AppConstants.INFO_FAILURE);
            return;
        }
        populateFields();
        itemsTableList.clear();
        tbItemView.refresh();
        for(Item item : invoice.getItems()){
            loadItemFromFile(item);
        }
        showInfo(AppConstants.INFO_TEXT,AppConstants.INFO_SUCCESS);
    }

    private void populateFields(){

        txtId.setText(invoice.getId());
        dpIssued.setValue(invoice.getIssueDate());
        dpTransactionDate.setValue(invoice.getTransactionDate());
        dpPaymentDue.setValue(invoice.getPaymentDueDate());
        cbPaymentMethod.setValue(invoice.getPaymentMethod());

        txtSellerName.setText(invoice.getSellerName());
        txtSellerAddress.setText(invoice.getSellerAddress());
        txtSellerCity.setText(invoice.getSellerCity());
        txtSellerPostalCode.setText(invoice.getSellerPostalCode());
        txtSellerNip.setText(invoice.getSellerNIP());
        if(invoice.getPaymentMethod().equals(AppConstants.PAYMENT_CASH)){
            txtSellerBankNum.setDisable(true);
            txtSellerBankNum.setText("");
        } else {
            txtSellerBankNum.setDisable(false);
            txtSellerBankNum.setText(invoice.getSellerAccountNumber());
        }

        txtBuyerName.setText(invoice.getBuyerName());
        txtBuyerAddress.setText(invoice.getBuyerAddress());
        txtBuyerCity.setText(invoice.getBuyerCity());
        txtBuyerPostalCode.setText(invoice.getBuyerPostalCode());
        txtBuyerNip.setText(invoice.getBuyerNIP());
    }

}
