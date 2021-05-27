package SummerFire3972.Controllers;

import java.util.List;

import SummerFire3972.Models.DBManager;
import SummerFire3972.Models.DataObject.MonthlyExpense;
import SummerFire3972.Models.DataObject.SingleExpense;
import SummerFire3972.Models.DataObject.Type;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ViewController {
	
	@FXML
	private MenuItem all, byMonth, byFood, byEntertainment, byUtility, byOther;
	@FXML
	private TableView table;
	
	private final DBManager manager = new DBManager();
	
	public void viewAll() {
		table.getColumns().clear();
		
		TableColumn dateColumn = new TableColumn("Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<SingleExpense, String>("date"));
		
		TableColumn amountColumn = new TableColumn("Amount");
		amountColumn.setCellValueFactory(new PropertyValueFactory<SingleExpense, Double>("amount"));
		
		TableColumn typeColumn = new TableColumn("Type");
		typeColumn.setCellValueFactory(new PropertyValueFactory<SingleExpense, String>("type"));
		
		ObservableList<SingleExpense> data = manager.queryAll();
		
		table.setItems(data);
		table.getColumns().addAll(dateColumn, amountColumn, typeColumn);
	}

	public void viewByMonth() {
		table.getColumns().clear();
		
		TableColumn dateColumn = new TableColumn("Month");
		dateColumn.setCellValueFactory(new PropertyValueFactory<MonthlyExpense, String>("date"));
		
		TableColumn amountColumn = new TableColumn("Amount");
		amountColumn.setCellValueFactory(new PropertyValueFactory<MonthlyExpense, Double>("amount"));
		
		ObservableList<MonthlyExpense> data = manager.queryTotal();
		
		table.setItems(data);
		table.getColumns().addAll(dateColumn, amountColumn);		
	}
	
	public void viewByFood() {
		getByType(Type.FOOD);
	}
	
	public void viewByEntertainment() {
		getByType(Type.ENTERTAINMENT);
	}
	
	public void viewByUtility() {
		getByType(Type.UTILITY);
	}
	
	public void viewByOther() {
		getByType(Type.OTHER);
	}
	
	private void getByType(Type t) {
		table.getColumns().clear();
		
		TableColumn dateColumn = new TableColumn("Month");
		dateColumn.setCellValueFactory(new PropertyValueFactory<MonthlyExpense, String>("date"));
		
		TableColumn amountColumn = new TableColumn("Amount");
		amountColumn.setCellValueFactory(new PropertyValueFactory<MonthlyExpense, Double>("amount"));
		
		ObservableList<MonthlyExpense> data = manager.queryByType(t);
		
		table.setItems(data);
		table.getColumns().addAll(dateColumn, amountColumn);
	}

}
