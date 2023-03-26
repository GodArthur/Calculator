package calculator;

import java.util.ArrayList;

public class Controller {
	private Calculator model;
	
	private ArrayList<String> operands = new ArrayList<String>(); 
	private ArrayList<String> operators = new ArrayList<String>(); 

	StringBuilder currentNumber =  new StringBuilder("");
	private StringBuilder expression = new StringBuilder("");
	
	//CalculatorView view; Not really possible with Java FX.... so maybe the Main/application class will have a Model instead
	
	public Controller(Calculator model) {
		this.model = model;
	
	}
	
	//getters
	
	//setters
	public void setModel(Calculator model) {
		this.model = model;
	}
	// Here we probably will migrate the functions used in java fx main to parse the expression
	
	public void clear() {
		operators.clear();
		expression.setLength(0);
		operands.clear();
		operators.clear();
		model.clear();
	}
	
	public String appendNumber(String value) {
        this.currentNumber.append(value);
		this.expression.append(value+" ");
		return this.expression.toString();
	}
	
//	public String appendOperator(String value) {
//		
//		return
//	}
}
