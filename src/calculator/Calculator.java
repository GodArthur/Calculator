package calculator;

import java.util.*;
/**
 * Expression is the Model in the MVC Design Pattern
 * @author Team C
 *
 */
public class Calculator {

	private StringBuilder expression = new StringBuilder("");
	public static final String[] OPERATORS = {"+", "-", "*", "/"};
	public static final String[] OPERANDS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};

	private double operand_1; //a
	private double operand_2; //b
	private double operand_3; //x
	private double operand_4; //y
	private String operator;
	private ArrayList<Double> data = new ArrayList<Double>(); // FOR THE PEOPLE WITH STAT FUNCTIONS
	private double result;
	private String error = "empty";
	
/**
 * Getters
 */
	public double getOperand_1() {
		return operand_1;
	}
	public double getOperand_2() {
		return operand_2;
	}
	public double getOperand_3() {
		return operand_3;
	}
	public double getOperand_4() {
		return operand_4;
	}	
	public String getOperator() {
		return operator;
	}
	public ArrayList<Double> getData() {
		return data;
	}
	public double getResult() {
		return result;
	}
	public String getError() {
		return error;
	}
	
/**
 * Setters
 */	
	public void setOperand_1(double operand) {
		this.operand_1 = operand;
	}
	public void setOperand_2(double operand) {
		this.operand_2 = operand;
	}	
	public void setOperand_3(double operand) {
		this.operand_3 = operand;
	}
	public void setOperand_4(double operand) {
		this.operand_4 = operand;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public void setData(ArrayList<Double> data) {
		this.data = data;
	}
	public void setResult(double result) {
		this.result = result;
	}
	public void setError(String error) {
		this.error = error;
	}

	public void clear() {
		operand_1 = 0;
		operand_2 = 0;
		operand_3 = 0;
		operand_4 = 0;
		operator = "";
		data.clear();
		result = 0;
		error = "empty";
	}
	
	private boolean checkIfOperatorClicked(String lastInput) {
		return (Arrays.asList(OPERATORS).contains(lastInput)) ? true : false;
	}
	
	public void appendToExpression(String newInput) {
		
		//Append to the expression input if the input is empty or the button clicked is a number
		if(expression.isEmpty() || expression.length() == 1 || Arrays.asList(OPERANDS).contains(newInput)) {
			if(!(newInput.equals("+") || newInput.equals("*") || newInput.equals("/"))) {
				expression.append(newInput);
			}
		}
		//Check if any operator is clicked twice - if yes, replace the new operator by the new one
		else {
			String lastInput = expression.substring(expression.length() - 1);
			if(checkIfOperatorClicked(lastInput)) {
				System.out.println("Another operator is clicked");
				expression.deleteCharAt(expression.length() - 1);
				expression.append(newInput);
			}
			else {
				expression.append(newInput);
			}
		}

	}
	
	public void calculateExpression(String calculatorExpression) {
		String lastInput = expression.substring(expression.length() - 1);
		if(checkIfOperatorClicked(lastInput)) {
			expression.deleteCharAt(expression.length() - 1);
		}
	}
	
	public String getExpression() {
		return expression.toString();
	}
	
// BASIC ARITHMETIC just copied from Main
//private double calculate(double operand1, double operand2, String operator) {
//		switch(operator) {
//		case "+": return operand1+operand2;
//		case "-": return operand1-operand2;
//		case "x": return operand1*operand2;
//		case "/": 
//			if(operand2==0)
//				return 0;
//			return operand1/operand2;
//		default: return 0;
//		}
//	}
	
	
	
/**
 * Method to calculate log of base b of x.
 * Author: Marie-Josee Castellanos
 * @param b : Base of the logarithmic function
 * @param x 
 * Will not return anything, rather will set the result as an instance of the expression.
 */
	public void logbx(double b, double x) {
		double result=0;
		
		// error conditions
		if (x <= 0) {this.setError("Undefined");}
		if (b <= 1) {this.setError("Undefined");}
		
		// special case(s)
		if (x == 1) {result = 0;}
		
		// Regular valid cases
		
		//TODO : Estimation (Halley/Newton) instead of loga/logb
		result = Math.log(x)/Math.log(b);
		this.setResult(result);
	}
	
// Same method but in case the user enters values in memory before selecting the function
	public void logbx() {
		this.logbx(this.operand_1,this.operand_2);
	}
	
	
	
}
