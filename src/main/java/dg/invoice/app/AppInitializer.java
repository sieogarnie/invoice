package dg.invoice.app;

import dg.invoice.app.invoice.InvoiceView;
import javafx.application.Application;
import javafx.stage.Stage;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        InvoiceView invoiceView = new InvoiceView();
        invoiceView.showStage(stage);

    }
}