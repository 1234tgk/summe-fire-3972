package SummerFire3972;

import java.time.LocalDate;
import java.util.List;
import SummerFire3972.Models.DBManager;
import SummerFire3972.Models.StageManager;
import SummerFire3972.Models.DataObject.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXML/main.fxml"));
        
        Scene scene = new Scene(root);
        // scene.getStylesheets().add(getClass().getResource("CSS/styles.css").toExternalForm());
        
        stage.setTitle("Gary's Ledger App");
        stage.setScene(scene);
        StageManager.setStageProperties(stage);
        stage.show();
    }

    public static void main(String[] args) {    	
    	launch(args);
    }

}