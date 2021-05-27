package SummerFire3972.Models;

import javafx.stage.Stage;

public class StageManager {
	
	private static final double MIN_HEIGHT = 400.00;
	private static final double MIN_WIDTH = 600.00;
	
	// block ctor
	private StageManager() {
		
	}
	
	public static void setStageProperties(Stage stage) {
		stage.setMinHeight(MIN_HEIGHT);
		stage.setMinWidth(MIN_WIDTH);
	}
}
