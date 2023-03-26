module EternityCalculator {
	requires javafx.controls;
	requires javafx.graphics;
	requires java.desktop;
	
	opens calculator to javafx.graphics, javafx.fxml;
}
