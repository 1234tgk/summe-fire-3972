package SummerFire3972.Controllers;

import SummerFire3972.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class NavController {

	@FXML
	private Button btnMain, btnView, btnAdd, btnDelete, btnClose;
	
	public void gotoView() throws Exception {
		Stage stage = (Stage) btnMain.getScene().getWindow();
		Parent root = FXMLLoader.load((new MainApp()).getClass().getResource("FXML/view.fxml"));;
    	
    	Scene scene = new Scene(root);
    	stage.setScene(scene);
    	stage.show();
	}
	
	public void gotoAdd() throws Exception {
		Stage stage = (Stage) btnAdd.getScene().getWindow();
		Parent root = FXMLLoader.load((new MainApp()).getClass().getResource("FXML/add.fxml"));
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void gotoDelete() throws Exception {
		Stage stage = (Stage) btnDelete.getScene().getWindow();
		Parent root = FXMLLoader.load((new MainApp()).getClass().getResource("FXML/delete.fxml"));
		
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void close() {
		Stage stage = (Stage) btnClose.getScene().getWindow();
		stage.close();
	}
}
