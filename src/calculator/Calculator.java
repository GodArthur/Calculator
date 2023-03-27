package calculator;
import functions.FunctionAbx;
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
	private boolean isAbx = false;
	private int totalNumInputed = 0;
	FunctionAbx funcAbx = new FunctionAbx();

	
	// Store in memory variables
	private double operand_a; 
	private double operand_b;
	private double operand_c;
	private double operand_d;
	private ArrayList<Double> data = new ArrayList<Double>(); // FOR THE PEOPLE WITH STAT FUNCTIONS
	private String error = "empty";
	
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
		return error;
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

	public void setError(String error) {
		this.error = error;
	}

	public void clear() {
		operand_a = 0;
		operand_b = 0;
		operand_c = 0;
		operand_d = 0;
		data.clear();
		error = "empty";
	}
	
	public static String superscript(String str) {
	    str = str.replaceAll("0", "⁰");
	    str = str.replaceAll("1", "¹");
	    str = str.replaceAll("2", "²");
	    str = str.replaceAll("3", "³");
	    str = str.replaceAll("4", "⁴");
	    str = str.replaceAll("5", "⁵");
	    str = str.replaceAll("6", "⁶");
	    str = str.replaceAll("7", "⁷");
	    str = str.replaceAll("8", "⁸");
	    str = str.replaceAll("9", "⁹");         
	    return str;
	}
	
	private boolean checkIfOperatorClicked(String lastInput) {
		return (Arrays.asList(OPERATORS).contains(lastInput)) ? true : false;
	}
	private boolean checkIfOperandsClicked(String lastInput) {
		return (Arrays.asList(OPERANDS).contains(lastInput)) ? true : false;
	}
	
	private boolean checkIfFunctionEnabled(String input) {
		if(!expression.isEmpty()) {
			


		if(isAbx) {
			if(checkIfOperatorClicked(input) && totalNumInputed < 3) {
				return true;
			}
			
			if(totalNumInputed == 0) {
				System.out.println("setting A: "+input);
				funcAbx.setA(Integer.parseInt(input));
				expression.replace(expression.length()-3, expression.length()-2, input+"*");

			} else if(totalNumInputed == 1) {
				System.out.println("setting B: "+input);

				funcAbx.setB(Integer.parseInt(input));
				expression.replace(expression.length()-2, expression.length()-1, input);

			} else if(totalNumInputed == 2) {
				System.out.println("setting X: "+input);

				funcAbx.setX(Integer.parseInt(input));
				expression.replace(expression.length()-1, expression.length(), superscript(input));
			} else {
				if(checkIfOperandsClicked(input)) {
					return true; //skip
				} else {
					isAbx = false;
					totalNumInputed = 0;
					return false;
				}
			}

			System.out.println("EXP: "+expression);
			totalNumInputed++;
			return true;
		}
	}
		return false;

	}
	
	public void appendToExpression(String newInput) {
		if(!checkIfFunctionEnabled(newInput)) {
		
		String expressionString = expression.toString();
		
		//Append to the expression input if the input is empty or the button clicked is a number
		if((expression.isEmpty() && !newInput.equals("+") && !newInput.equals("*") && !newInput.equals("/")) || checkIfOperandsClicked(newInput)) {
			expression.append(newInput);

		} else if (expression.length() == 1  && expressionString.equals("-") && checkIfOperatorClicked(newInput)) {
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

			if(checkIfOperatorClicked(lastInput)) {
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
		String lastInput = expression.substring(expression.length() - 1);
		if(checkIfOperatorClicked(lastInput)) {
			expression.deleteCharAt(expression.length()-1);
//			expression.deleteCharAt(expression.length() - 1);
		}
		// TODO: Turn expression into postfix expression
		ArrayList<String> expressionPostfix = infixToPostfix(calculatorExpression);
		// probably a for loop here to go through postfixexpression
		
		// TODO: CALCULATIONS BASIC
		// TODO: CALCULATIONS ADVANCED
		
		// Delete the expression and replace with the result only
		
	}
	
/**
 * Method to transform the expression entered from infix to postfix as to respect precedence and associativity rules
 * @param expression
 * @return
 */
	private ArrayList<String> infixToPostfix(String expression){
		Stack<Character> postfixStack = new Stack();
		ArrayList<String> postfixString = new ArrayList<String>();
		String tempOperand="";
		
		for (char i: expression.toCharArray()) {
			//Brackets

			if (i=='(') {postfixStack.push(i);}
			
			if (i==')') {
				while(postfixStack.peek() != '(') {
					postfixString.add(Character.toString(postfixStack.pop()));
				}
				// removes open bracket leftover
				postfixStack.pop();
			}
			// Operand : will keep concatenating digits that are part of the operand
			if (isOperand(i)) {
				tempOperand = tempOperand.concat(Character.toString(i));
				if (postfixString.size()>0 && isOperand(postfixString.get(postfixString.size()-1)) )
				postfixString.set(postfixString.size()-1, tempOperand);
				else
				postfixString.add(Character.toString(i));
			}
			
			//Operator : resets temporary operand, TODO : THIS PART IS NOT WORKING CORRECTLY
			if (isOperator(i)) {
				tempOperand="";
	                // remove operators from the stack with higher or equal precedence
	                // and append them at the end of the postfix expression
	                while (!postfixStack.isEmpty() && precedence(i) >= precedence(postfixStack.peek())) {
	                    postfixString.add(Character.toString(postfixStack.pop()));
	                }
	                // add current operator to string
	                postfixString.add(Character.toString(i));
			}
		}
	     
		while (!postfixStack.isEmpty()) {
	            postfixString.add(Character.toString(postfixStack.pop()));
	        }
		
	      for (String str : postfixString)
	      { System.out.println("COMPONENT "+str); }
		return postfixString;
		
	}
	
	
// BASIC ARITHMETIC
private double calculateBasicArithmetic(double operand1, double operand2, String operator) {
		switch(operator) {
		case "+": return operand1+operand2;
		case "-": return operand1-operand2;
		case "x": return operand1*operand2;
		case "/": 
			if(operand2==0) {
				this.error="Division by 0 error";
				return 0;
				}
			return operand1/operand2;
		default: return 0;
		}
	}
	
public void appendAbxToExpression(String abx) {
	if(expression.isEmpty() || !checkIfOperandsClicked(expression.substring(expression.length() - 1))) {
		isAbx = true;
		expression.append(abx);
	}
}
	
/**
 * Method to calculate log of base b of x.
 * Author: Marie-Josee Castellanos
 * @param b : Base of the logarithmic function
 * @param x 
 * Will not return anything, rather will set the result as an instance of the expression.
 */
	public double logbx(double b, double x) {
		double result=0;
		
		// error conditions
		if (x <= 0) {this.setError("Undefined");}
		if (b <= 1) {this.setError("Undefined");}
		
		// special case(s)
		if (x == 1) {result = 0;}
		
		// Regular valid cases
		//TODO : Estimation (Halley/Newton) instead of loga/logb
		result = Math.log(x)/Math.log(b);
		return result;
	}
	
// Same method but in case the user enters values in memory before selecting the function
	public void logab() {
		this.logbx(this.operand_a,this.operand_b);
	}
	
	
	
// Support Methods for the Infix to Postfix Method	
	private boolean isOperand(char c) {
		if (Arrays.asList(OPERANDS).contains(Character.toString(c)))
			return true;
		else 
			return false;
	}
	
	private boolean isOperand(String str) {
		if (Arrays.asList(OPERANDS).contains(str))
			return true;
		else 
			return false;
	}
	
	private boolean isOperator(char c) {
		if (Arrays.asList(OPERATORS).contains(Character.toString(c)))
			return true;
		else 
			return false;
	}
	
	private int precedence(char c) {
		int precedence = 0;
		switch(c) {
		case '+':
		precedence = 0;
		
		case '-':
		precedence = 0;
		
		case '*':
		precedence = 1;
		
		case '/':
		precedence = 1;
		}
		
		return precedence;
	}
	
}