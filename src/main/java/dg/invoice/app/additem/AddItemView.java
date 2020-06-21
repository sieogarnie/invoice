package dg.invoice.app.additem;

import dg.invoice.app.invoice.InvoiceController;
import dg.invoice.app.commons.AppConstants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AddItemView {

    public Stage showStage(Stage ownerStage, InvoiceController invoiceController) {
        try{
            // create and load scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/addItem.fxml"));
            AnchorPane page = loader.load();
            Scene scene = new Scene(page);
            scene.getStylesheets().add(getClass().getResource("/bootstrap3.css").toExternalForm());

            // set scene
            Stage stage = new Stage();
            stage.initOwner(ownerStage);
            stage.setTitle(AppConstants.ADD_ITEM_TITLE);
            stage.setScene(scene);
            stage.show();

            AddItemController addItemController = loader.getController();
            addItemController.setStage(stage);
            addItemController.setInvoiceController(invoiceController);

            return stage;
        }catch(IOException ioe){
            ioe.printStackTrace();
            return null;
        }
    }

}
