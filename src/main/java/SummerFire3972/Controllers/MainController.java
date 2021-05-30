package SummerFire3972.Controllers;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

import SummerFire3972.Models.DBManager;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class MainController {
	
	@FXML
	private LineChart<String, Number> lineChart;
	@FXML
	private CategoryAxis xAxis;
	@FXML
	private NumberAxis yAxis;
	
	DBManager manager = new DBManager();
	
	public void initialize() {
		
		xAxis.setLabel("Month");
		yAxis.setLabel("Amount");
		
		lineChart.setTitle("Expense by Month This Year");
		
		XYChart.Series<String, Number> series = new XYChart.Series<>();
		
		double[] data = manager.queryByMonthThisYear();
		
		for (int i = 0 ; i < 12 ; i++) {
			series.getData().add(new XYChart.Data<String, Number>(Month.of(i + 1).getDisplayName(TextStyle.SHORT, Locale.ENGLISH), data[i]));
		}
		
		lineChart.getData().add(series);
	}

}
