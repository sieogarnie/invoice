package dg.invoice.app.model;

import dg.invoice.app.commons.AppConstants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Invoice {

    private String id;
    private LocalDate issueDate;
    private LocalDate transactionDate;
    private LocalDate paymentDueDate;
    private String paymentMethod;

    // seller information
    private String sellerName;
    private String sellerAddress;
    private String sellerPostalCode;
    private String sellerCity;
    private String sellerNIP;
    private String sellerAccountNumber;

    // buyer information
    private String buyerName;
    private String buyerAddress;
    private String buyerPostalCode;
    private String buyerCity;
    private String buyerNIP;

    private List<Item> items;

    public Invoice() {
        this.items = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public LocalDate getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(LocalDate paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public void setSellerAccountNumber(String sellerAccountNumber) {
        this.sellerAccountNumber = sellerAccountNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerAddress() {
        return sellerAddress;
    }

    public void setSellerAddress(String sellerAddress) {
        this.sellerAddress = sellerAddress;
    }

    public String getSellerPostalCode() {
        return sellerPostalCode;
    }

    public void setSellerPostalCode(String sellerPostalCode) {
        this.sellerPostalCode = sellerPostalCode;
    }

    public String getSellerCity() {
        return sellerCity;
    }

    public void setSellerCity(String sellerCity) {
        this.sellerCity = sellerCity;
    }

    public String getSellerNIP() {
        return sellerNIP;
    }

    public void setSellerNIP(String sellerNIP) {
        this.sellerNIP = sellerNIP;
    }

    public String getSellerAccountNumber() {
        return sellerAccountNumber;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerAddress() {
        return buyerAddress;
    }

    public void setBuyerAddress(String buyerAddress) {
        this.buyerAddress = buyerAddress;
    }

    public String getBuyerPostalCode() {
        return buyerPostalCode;
    }

    public void setBuyerPostalCode(String buyerPostalCode) {
        this.buyerPostalCode = buyerPostalCode;
    }

    public String getBuyerCity() {
        return buyerCity;
    }

    public void setBuyerCity(String buyerCity) {
        this.buyerCity = buyerCity;
    }

    public String getBuyerNIP() {
        return buyerNIP;
    }

    public void setBuyerNIP(String buyerNIP) {
        this.buyerNIP = buyerNIP;
    }

    public List<Item> getItems() {
        return items;
    }

    public String toString(){
        StringBuilder invoiceString = new StringBuilder();
        invoiceString.append(id).append("\n");
        invoiceString.append(issueDate.toString()).append("\n");
        invoiceString.append(transactionDate.toString()).append("\n");
        invoiceString.append(paymentDueDate.toString()).append("\n");
        invoiceString.append(paymentMethod).append("\n");

        invoiceString.append(sellerName).append("\n");
        invoiceString.append(sellerAddress).append("\n");
        invoiceString.append(sellerPostalCode).append("\n");
        invoiceString.append(sellerCity).append("\n");
        invoiceString.append(sellerNIP).append("\n");
        invoiceString.append(sellerAccountNumber).append("\n");

        invoiceString.append(buyerName).append("\n");
        invoiceString.append(buyerAddress).append("\n");
        invoiceString.append(buyerPostalCode).append("\n");
        invoiceString.append(buyerCity).append("\n");
        invoiceString.append(buyerNIP).append("\n");

        invoiceString.append(AppConstants.ITEMS_TAG).append("\n");
        for(Item item : items){
            invoiceString.append(item.toString()).append("\n");
        }

        return invoiceString.toString();
    }
}
