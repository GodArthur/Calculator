package calculator;


public class Main{

public static void main(String[] args) {

	View calculatorView = new View();
	Calculator calculatorModel = new Calculator();
	Controller calcController = new Controller(calculatorModel, calculatorView);
	calculatorView.setVisible(true);
	
}
}
