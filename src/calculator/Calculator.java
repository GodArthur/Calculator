package calculator;
import functions.FunctionAbx;
import misc.StringHelper;
import functions.*;
import java.util.*;
/**
 * Expression is the Model in the MVC Design Pattern
 * @author Team C
 *
 */
public class Calculator {

	private StringBuilder expression = new StringBuilder("");

	private StringBuilder expressionToParse = new StringBuilder("");
	
	// Find another than an instance, probably just compare operator and type of function

	
	Functions transcendentalFunction;
	private String errorMessage = "";

	
	// Store in memory variables TODO : DO WE NEED THESE VARIABLES BELOW??
	private double operand_a; 
	private double operand_b;
	private double operand_c;
	private double operand_d;
	private ArrayList<Double> data = new ArrayList<Double>(); // FOR THE PEOPLE WITH STAT FUNCTIONS
	
/**
 * Getters
 */
	public double getOperand_A() {
		return operand_a;
	}
	public double getOperand_B() {
		return operand_b;
	}
	public double getOperand_C() {
		return operand_c;
	}
	public double getOperand_D() {
		return operand_d;
	}	
	public ArrayList<Double> getData() {
		return data;
	}
	public String getError() {
		return transcendentalFunction.getErrorMessage();
	}
	
	public String getExpression() {
		return expression.toString();
	}
	
/**
 * Setters
 */	
	public void setOperand_a(double operand) {
		this.operand_a = operand;
	}
	public void setOperand_b(double operand) {
		this.operand_b = operand;
	}	
	public void setOperand_c(double operand) {
		this.operand_c = operand;
	}
	public void setOperand_d(double operand) {
		this.operand_d = operand;
	}

	public void setData(ArrayList<Double> data) {
		this.data = data;
	}

	public void setErrorMessage(String error) {
		this.errorMessage = error;
	}

	public void clear() {
		operand_a = 0;
		operand_b = 0;
		operand_c = 0;
		operand_d = 0;
		data.clear();
	}


	private boolean checkIfFunctionEnabled(String input) {
		if(!expression.isEmpty()) {

		if(transcendentalFunction instanceof FunctionAbx) {
			String expString = expression.toString();
			String expr = transcendentalFunction.parse(input, expString);
			if(expr.equals("-1")) {
				return true;
			}
			if(transcendentalFunction.getVarsInputed() == transcendentalFunction.getTotalVars()) {
				transcendentalFunction = null;
			}
			System.out.println("EXP: "+expression);
			return true;
		}
		
	}
		return false;

	}
	
	public void appendToExpression(String newInput) {
		if(!checkIfFunctionEnabled(newInput)) {
		
		String expressionString = expression.toString();
		
		//Append to the expression input if the input is empty or the button clicked is a number
		if((expression.isEmpty() && !newInput.equals("+") && !newInput.equals("*") && !newInput.equals("/")) || StringHelper.checkIfOperandsClicked(newInput)) {
			expression.append(newInput);

		} else if (expression.length() == 1  && expressionString.equals("-") && StringHelper.checkIfOperatorClicked(newInput)) {
			if(newInput.equals("+")) {
				expression.deleteCharAt(expression.length() - 1);
			} 
			else {
				return;
			}
		} else if (expression.isEmpty() && (newInput.equals("+") || newInput.equals("*") || newInput.equals("/"))) {
			return;
		}
		//Check if any operator is clicked twice - if yes, replace the new operator by the new one
		else {
			String lastInput = expression.substring(expression.length() - 1);

			if(StringHelper.checkIfOperatorClicked(lastInput)) {
				expression.deleteCharAt(expression.length() - 1);
				expression.append(newInput);
			}
			else {
				expression.append(newInput);
			}
		}
		}
	}
	
	public void calculateExpression(String calculatorExpression) {
		if(!expression.isEmpty()) {
		String lastInput = expression.substring(expression.length() - 1);
		
		if(StringHelper.checkIfOperatorClicked(lastInput)) {
			expression.deleteCharAt(expression.length()-1);
		}
		
		// BASIC TODO
		
		//TRANSCENDENTAL FUNCTIONS TODO

		
		}
	}
	
	private ArrayList<String> expressionSeparated(String expression){
		ArrayList<String> expressionSeparated = new ArrayList<String>();
		// TODO 
		return expressionSeparated;
	}
	
/**
 * Method used for basic arithmetic
 * @param operand1
 * @param operand2
 * @param operator
 * @return
 */
	private double compute(double operand1, double operand2, String operator) {
			switch(operator) {
			case "+": return operand1+operand2;
			case "-": return operand1-operand2;
			case "x": return operand1*operand2;
			case "/": 
				if(operand2==0) {
					this.errorMessage ="Division by 0 error";
					return 0;
					}
				return operand1/operand2;
			default: return 0;
			}
		}

	// Method below should be moved to FunctionAbx
	public void appendAbxToExpression(String abx) {
		if(expression.isEmpty() || !StringHelper.checkIfOperandsClicked(expression.substring(expression.length() - 1))) {
			transcendentalFunction = new FunctionAbx();
			//isAbx = true;
			expression.append(abx);
		}
	}
	
/**
 * Method to transform the expression entered from infix to postfix as to respect precedence and associativity rules
 * @param expression
 * @return
 */
//	private ArrayList<String> infixToPostfix(String expression){
//		Stack<Character> postfixStack = new Stack();
//		ArrayList<String> postfixString = new ArrayList<String>();
//		String tempOperand="";
//		
//		for (char i: expression.toCharArray()) {
//			//Brackets
//
//			if (i=='(') {postfixStack.push(i);}
//			
//			if (i==')') {
//				while(postfixStack.peek() != '(') {
//					postfixString.add(Character.toString(postfixStack.pop()));
//				}
//				// removes open bracket leftover
//				postfixStack.pop();
//			}
//			// Operand : will keep concatenating digits that are part of the operand
//			if (isOperand(i)) {
//				tempOperand = tempOperand.concat(Character.toString(i));
//				if (postfixString.size()>0 && isOperand(postfixString.get(postfixString.size()-1)) )
//				postfixString.set(postfixString.size()-1, tempOperand);
//				else
//				postfixString.add(Character.toString(i));
//			}
//			
//			//Operator : resets temporary operand, TODO : THIS PART IS NOT WORKING CORRECTLY
//			if (isOperator(i)) {
//				tempOperand="";
//	                // remove operators from the stack with higher or equal precedence
//	                // and append them at the end of the postfix expression
//	                while (!postfixStack.isEmpty() && precedence(i) >= precedence(postfixStack.peek())) {
//	                    postfixString.add(Character.toString(postfixStack.pop()));
//	                }
//	                // add current operator to string
//	                postfixString.add(Character.toString(i));
//			}
//		}
//	     
//		while (!postfixStack.isEmpty()) {
//	            postfixString.add(Character.toString(postfixStack.pop()));
//	        }
//		
//	      for (String str : postfixString)
//	      { System.out.println("COMPONENT "+str); }
//		return postfixString;
//		
//	}
	
	
	
// Support Methods for the Infix to Postfix Method	- > wont be used but maybe support might still be useful
	private boolean isOperand(char c) {
		if (Arrays.asList(StringHelper.OPERANDS).contains(Character.toString(c)))
			return true;
		else 
			return false;
	}
	
	private boolean isOperand(String str) {
		if (Arrays.asList(StringHelper.OPERANDS).contains(str))
			return true;
		else 
			return false;
	}
	
	private boolean isOperator(char c) {
		if (Arrays.asList(StringHelper.OPERATORS).contains(Character.toString(c)))
			return true;
		else 
			return false;
	}
//	
//	private int precedence(char c) {
//		int precedence = 0;
//		switch(c) {
//		case '+':
//		precedence = 0;
//		
//		case '-':
//		precedence = 0;
//		
//		case '*':
//		precedence = 1;
//		
//		case '/':
//		precedence = 1;
//		}
//		
//		return precedence;
//	}
//	
}