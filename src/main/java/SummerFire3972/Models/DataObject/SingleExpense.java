package SummerFire3972.Models.DataObject;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class SingleExpense {

	private SimpleStringProperty date;
	private SimpleDoubleProperty amount;
	private SimpleStringProperty type;
	
	public SingleExpense(String date, double amount, String type) {
		this.date = new SimpleStringProperty(date);
		this.amount = new SimpleDoubleProperty(amount);
		this.type = new SimpleStringProperty(type);
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
}
