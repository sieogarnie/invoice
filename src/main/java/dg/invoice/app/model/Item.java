package dg.invoice.app.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Item {

    private static int NUMBER = 0;

    private SimpleIntegerProperty number;
    private SimpleStringProperty name;
    private SimpleIntegerProperty amount;
    private SimpleDoubleProperty netPrice;
    private SimpleIntegerProperty discount;
    private SimpleIntegerProperty tax;
    private SimpleDoubleProperty grossPrice;

    public Item(){
        NUMBER++;
        number = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        amount = new SimpleIntegerProperty();
        netPrice = new SimpleDoubleProperty();
        discount = new SimpleIntegerProperty();
        tax = new SimpleIntegerProperty();
        grossPrice = new SimpleDoubleProperty();
        number.set(NUMBER);
    }


    public void setName(String name) {
        this.name.set(name);
    }


    public static int getNUMBER() {
        return NUMBER;
    }

    public static void setNUMBER(int NUMBER) {
        Item.NUMBER = NUMBER;
    }

    public int getNumber() {
        return number.get();
    }

    public SimpleIntegerProperty numberProperty() {
        return number;
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public int getAmount() {
        return amount.get();
    }

    public SimpleIntegerProperty amountProperty() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount.set(amount);
    }

    public double getNetPrice() {
        return netPrice.get();
    }

    public SimpleDoubleProperty netPriceProperty() {
        return netPrice;
    }

    public void setNetPrice(double netPrice) {
        this.netPrice.set(netPrice);
    }

    public int getDiscount() {
        return discount.get();
    }

    public SimpleIntegerProperty discountProperty() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount.set(discount);
    }

    public int getTax() {
        return tax.get();
    }

    public SimpleIntegerProperty taxProperty() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax.set(tax);
    }

    public double getGrossPrice() {
        return grossPrice.get();
    }

    public SimpleDoubleProperty grossPriceProperty() {
        return grossPrice;
    }

    public void setGrossPrice(double grossPrice) {
        this.grossPrice.set(grossPrice);
    }

    public String toString(){
        String itemString = "";
        itemString += number.get() + ",";
        itemString += name.get() + ",";
        itemString += amount.get() + ",";
        itemString += netPrice.get() + ",";
        itemString += discount.get() + ",";
        itemString += tax.get() + ",";
        itemString += grossPrice.get();

        return itemString;
    }

    public Item(String itemString){
        this();
        String[] itemArray = itemString.split(",");
        this.setName(itemArray[1]);
        this.setAmount(Integer.parseInt(itemArray[2]));
        this.setNetPrice(Double.parseDouble(itemArray[3]));
        this.setDiscount(Integer.parseInt(itemArray[4]));
        this.setTax(Integer.parseInt(itemArray[5]));
        this.setGrossPrice(Double.parseDouble(itemArray[6]));
    }
}
