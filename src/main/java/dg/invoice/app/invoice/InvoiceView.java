package dg.invoice.app.invoice;

import dg.invoice.app.commons.AppConstants;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class InvoiceView {

    public Stage showStage(Stage stage) {

        try {
            // create and load scene
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/invoice.fxml"));
            AnchorPane page = loader.load();
            Scene scene = new Scene(page);
            scene.getStylesheets().add(getClass().getResource("/bootstrap3.css").toExternalForm());

            // set scene
            stage.setTitle(AppConstants.APP_TITLE);
            stage.setScene(scene);
            stage.show();

            InvoiceController invoiceController = loader.getController();
            invoiceController.setStage(stage);
            return stage;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }

}
