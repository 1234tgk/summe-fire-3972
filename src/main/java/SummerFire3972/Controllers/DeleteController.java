package SummerFire3972.Controllers;

import SummerFire3972.Models.DBManager;
import SummerFire3972.Models.DataObject.Expense;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class DeleteController {

	@FXML
	private TableView<Expense> table;
	
	DBManager manager = new DBManager();
	
	public void initialize() {
		table.getColumns().clear();
        
        TableColumn dateColumn = new TableColumn("Date");
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		TableColumn amountColumn = new TableColumn("Amount");
		amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
		
		TableColumn typeColumn = new TableColumn("Type");
		typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
		
		TableColumn idColumn = new TableColumn("Id");
		idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn btnColumn = new TableColumn("Delete");
		
		Callback<TableColumn<Expense, Void>, TableCell<Expense, Void>> cellFactory = (final TableColumn<Expense, Void> pararm) -> {
			final TableCell<Expense, Void> cell = new TableCell<Expense, Void>() {
				
				final Button btn = new Button("Delete");
				
				@Override
				public void updateItem(Void item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setGraphic(null);
//						setText(null);
					} else {
						btn.setOnAction(event -> {
							Expense expense = getTableView().getItems().get(getIndex());
							manager.deleteValue(expense.getId());
							getTableView().getItems().remove(expense);
						});
						setGraphic(btn);
//						setText(null);
					}
				}
			};
			return cell;
		};
		
		btnColumn.setCellFactory(cellFactory);
		
		ObservableList<Expense> data = manager.queryAllDetailedThisMonth();
		
		table.setItems(data);
		table.getColumns().addAll(dateColumn, amountColumn, typeColumn, idColumn, btnColumn);		
		
	}
}
