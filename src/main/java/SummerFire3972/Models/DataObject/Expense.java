package SummerFire3972.Models.DataObject;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class Expense {

	private SimpleStringProperty date;
	private SimpleDoubleProperty amount;
	private SimpleStringProperty type;
	private SimpleStringProperty id;
	
	public Expense(String date, double amount, String type, String id) {
		this.date = new SimpleStringProperty(date);
		this.amount = new SimpleDoubleProperty(amount);
		this.type = new SimpleStringProperty(type);
		this.id = new SimpleStringProperty(id);
	}

	public String getDate() {
		return date.get();
	}

	public double getAmount() {
		return amount.get();
	}

	public String getType() {
		return type.get();
	}

	public String getId() {
		return id.get();
	}
}
