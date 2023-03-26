package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;


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

		this.view.addOperandsAndOperatorsBtnListener(new OperandsAndOperatorsBtnListener());
		this.view.addCalculateBtnListener(new CalculateBtnListener());
	}
	
	class OperandsAndOperatorsBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String buttonText = ((JButton) e.getSource()).getText();

			model.appendToExpression(buttonText);
			System.out.println(buttonText);
			view.setExpressionInput(model.getExpression());
		}
		
	}
	
	class CalculateBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String expressionInputText = model.getExpression();
			
			model.calculateExpression(expressionInputText);
			System.out.println("expression: "+model.getExpression());


			//TODO Calculate the math expression input in the TextField
		}
		
	}
	
	//getters
	
	//setters
	public void setModel(Calculator model) {
		this.model = model;
	}

}
