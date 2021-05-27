package SummerFire3972.Models.DataObject;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class MonthlyExpense {

	private SimpleStringProperty date;
	private SimpleDoubleProperty amount;

	public MonthlyExpense(String date, double amount) {
		this.date = new SimpleStringProperty(date);
		this.amount = new SimpleDoubleProperty(amount);
	}

	public String getDate() {
		return date.get();
	}

	public double getAmount() {
		return amount.get();
	}
}
