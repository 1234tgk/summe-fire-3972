package SummerFire3972.Controllers;

import java.time.LocalDate;

import SummerFire3972.MainApp;
import SummerFire3972.Models.DBManager;
import SummerFire3972.Models.DataObject.Expense;
import SummerFire3972.Models.DataObject.Type;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddController {

	@FXML
	private TableView<Expense> table;
	@FXML
	private TextField amount;
	@FXML
	private ComboBox<Type> type;
	@FXML
	private Button btnAdd;
	
	private final DBManager manager = new DBManager();
	
	public void initialize() {
        type.getItems().addAll(Type.values());
        
        table.getColumns().clear();
        
        TableColumn<Expense, String> dateColumn = new TableColumn<>("Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		TableColumn<Expense, Double> amountColumn = new TableColumn<>("Amount");
		amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
		
		TableColumn<Expense, String> typeColumn = new TableColumn<>("Type");
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		
		TableColumn<Expense, String> idColumn = new TableColumn<>("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		ObservableList<Expense> data = manager.queryAllDetailedThisMonth();
		
		table.setItems(data);
		table.getColumns().addAll(dateColumn, amountColumn, typeColumn, idColumn);
    }
	
	public void add() {
		
		double amount;
		
		try {
			amount = Double.parseDouble(this.amount.getText());
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Unparsable input detected on amount field");

			alert.showAndWait();
			
			return;
		}
		
		if (type.getValue() == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Choose a type");

			alert.showAndWait();
			
			return;
		}
		
		String id = null;
		
		if ((id = manager.insertValue(LocalDate.now(), type.getValue().value, amount)) == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Unforseen error occured");

			alert.showAndWait();
			
			return;
		}
		
		Expense expense = new Expense(LocalDate.now().toString(), amount, type.getValue().value, id);
		
		table.getItems().add(0, expense);
		
//		this.amount.clear();
//		table.getItems().clear();
//		
//		ObservableList<Expense> data = manager.queryAllDetailedThisMonth();
//		
//		table.setItems(data);
	}
}
