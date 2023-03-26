package calculator;

import java.util.ArrayList;

public class Controller {
	private Calculator model;
	private View view;
	
//	private ArrayList<String> operands = new ArrayList<String>(); 
//	private ArrayList<String> operators = new ArrayList<String>(); 
//
//	StringBuilder currentNumber =  new StringBuilder("");
//	private StringBuilder expression = new StringBuilder("");
	
	//CalculatorView view; Not really possible with Java FX.... so maybe the Main/application class will have a Model instead
	public Controller(Calculator model, View view) {
		this.model = model;
		this.view = view;
		
//		this.view.addBtn_1Listener(new Btn_1Listener);
	}
	
	//getters
	
	//setters
	public void setModel(Calculator model) {
		this.model = model;
	}

}
