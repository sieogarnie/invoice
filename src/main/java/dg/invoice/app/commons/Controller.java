package dg.invoice.app.commons;

import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

public abstract class Controller implements Initializable {

    public static void showError(String text, String header, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(text);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void showInfo(String text, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(text);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }


}
