package SummerFire3972.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeController {

    @FXML
    private Label label;

    public void initialize() {
        label.setText("Hello, JavaFX");
    }
}
