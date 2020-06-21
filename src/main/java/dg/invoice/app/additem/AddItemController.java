package dg.invoice.app.additem;

import dg.invoice.app.invoice.InvoiceController;
import dg.invoice.app.commons.AppConstants;
import dg.invoice.app.commons.Controller;
import dg.invoice.app.commons.ValidatorUtil;
import dg.invoice.app.model.Item;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddItemController extends Controller{

    private Stage stage;
    private Stage ownerStage;

    @FXML
    private TextField tfName;
    @FXML
    private TextField tfAmount;
    @FXML
    private TextField tfUnitPrice;
    @FXML
    private TextField tfDiscountP;
    @FXML
    private TextField tfTaxP;
    @FXML
    private TextField tfGrossPrice;

    private InvoiceController invoiceController;

    @FXML
    private void addToGrid(){

        // get user inputs
        String itemName = tfName.getText();
        String amountString = tfAmount.getText();
        String unitPriceString = tfUnitPrice.getText();
        String discountPercentString = tfDiscountP.getText();
        String taxPercentString = tfTaxP.getText();

        // cast inputs into correct formats
        Integer amount = ValidatorUtil.convertToInteger(amountString);
        Double unitPrice = ValidatorUtil.convertToDouble(unitPriceString);
        Integer discountPercent = ValidatorUtil.convertToInteger(discountPercentString);
        Integer taxPercent = ValidatorUtil.convertToInteger(taxPercentString);

        // check numerical inputs
        if(amount == null || unitPrice == null || discountPercent == null || taxPercent == null){
            String errorMsg = String.format(AppConstants.ERROR_INVALID_INPUT_MESSAGE, "amount/price/discount/tax");
            Controller.showError(AppConstants.ERROR_TEXT, AppConstants.ERROR_INVALID_INPUT_HEADER, errorMsg);
            return;
        }

        // crete model item
        Item item = new Item();
        item.setAmount(amount);
        item.setDiscount(discountPercent);
        item.setName(itemName);
        item.setTax(taxPercent);
        item.setNetPrice(unitPrice*amount);
        item.setGrossPrice(unitPrice*amount*(100-discountPercent)/100 * (100+taxPercent)/100);
        invoiceController.addItemToInvoice(item);
        invoiceController.recalculateTotal(item.getGrossPrice());
        stage.close();
    }

    void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setInvoiceController(InvoiceController invoiceController) {
        this.invoiceController = invoiceController;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
