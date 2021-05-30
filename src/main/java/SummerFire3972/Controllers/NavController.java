package SummerFire3972.Controllers;

import java.util.Optional;

import SummerFire3972.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class NavController {

	@FXML
	private Button btnMain, btnView, btnAdd, btnDelete, btnClose;
	
	public void gotoMain() throws Exception {
		Parent root = FXMLLoader.load((new MainApp()).getClass().getResource("FXML/main.fxml"));;
    	btnMain.getScene().setRoot(root);
	}
	
	public void gotoView() throws Exception {
		Parent root = FXMLLoader.load((new MainApp()).getClass().getResource("FXML/view.fxml"));
		btnView.getScene().setRoot(root);
	}
	
	public void gotoAdd() throws Exception {
		Parent root = FXMLLoader.load((new MainApp()).getClass().getResource("FXML/add.fxml"));
		btnAdd.getScene().setRoot(root);
	}
	
	public void gotoDelete() throws Exception {
		Parent root = FXMLLoader.load((new MainApp()).getClass().getResource("FXML/delete.fxml"));
		btnAdd.getScene().setRoot(root);
	}
	
	public void close() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Close Confirmation");
		alert.setHeaderText(null);
		alert.setContentText("Close the application?");
		
		ButtonType buttonTypeYes = new ButtonType("Yes", ButtonData.YES);
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		
		alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeCancel);
		
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeYes) {
			Stage stage = (Stage) btnClose.getScene().getWindow();
			stage.close();
		} else {
			// empty
		}
	}
}
