package dg.invoice.app.commons;

import dg.invoice.app.model.Invoice;
import dg.invoice.app.model.Item;

import javax.swing.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class FileOperationUtil {

    public static boolean saveInvoiceFile(Invoice invoice){
        JFrame parentFrame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(parentFrame) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try{
                if(!file.exists()){
                    file.createNewFile();
                }
                if(file.canWrite()){
                    FileWriter fileWriter = new FileWriter(file);
                    fileWriter.write(invoice.toString());
                    fileWriter.close();
                    return true;
                }
            } catch (IOException ioe){
                ioe.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static Invoice loadInvoiceFile() {

        Invoice invoice = new Invoice();
        JFrame parentFrame = new JFrame();
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(parentFrame) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            //check extension
            Optional<String> extension = getExtension(file.getName());
            if(extension.isEmpty() || !AppConstants.EXTENSION.equals(extension.get())){
                return null;
            }
            if(file.exists() && file.canRead()){
                try{
                    ArrayList<String> invoiceData = new ArrayList<>();
                    ArrayList<Item> items = new ArrayList<>();
                    Scanner scanner = new Scanner(file);
                    while(scanner.hasNextLine()){
                        String line = scanner.nextLine();
                        if(line.equals(AppConstants.ITEMS_TAG)){
                           break;
                        }
                        invoiceData.add(line);
                    }
                    if(invoiceData.size() < 16){
                        return null;
                    }
                    while(scanner.hasNextLine()){
                        String line = scanner.nextLine();
                        items.add(new Item(line));
                    }

                    // general
                    invoice.setId(invoiceData.get(0));
                    invoice.setIssueDate(LocalDate.parse(invoiceData.get(1)));
                    invoice.setTransactionDate(LocalDate.parse(invoiceData.get(2)));
                    invoice.setPaymentDueDate(LocalDate.parse(invoiceData.get(3)));
                    invoice.setPaymentMethod(invoiceData.get(4));

                    // seller
                    invoice.setSellerName(invoiceData.get(5));
                    invoice.setSellerAddress(invoiceData.get(6));
                    invoice.setSellerPostalCode(invoiceData.get(7));
                    invoice.setSellerCity(invoiceData.get(8));
                    invoice.setSellerNIP(invoiceData.get(9));
                    invoice.setSellerAccountNumber(invoiceData.get(10));

                    // buyer
                    invoice.setBuyerName(invoiceData.get(11));
                    invoice.setBuyerAddress(invoiceData.get(12));
                    invoice.setBuyerPostalCode(invoiceData.get(13));
                    invoice.setBuyerCity(invoiceData.get(14));
                    invoice.setBuyerNIP(invoiceData.get(15));

                    invoice.setItems(items);
                    return invoice;
                } catch (IOException ioe){
                    ioe.printStackTrace();
                    return null;
                }
            }
        }
        return null;
    }

    private static Optional<String> getExtension(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }
}
