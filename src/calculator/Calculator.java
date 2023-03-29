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
	private Functions transcendentalFunction;
	private String errorMessage = "";

	// Store in memory variables TODO : DO WE NEED THESE VARIABLES BELOW??
	private double operand_a; 
	private double operand_b;
	private double operand_c;
	private double operand_d;
	
/**
 * Getters TODO : JAVADOC
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
	public String getError() {
		return this.errorMessage;
	}
	
	public String getExpression() {
		return expression.toString();
	}
	
/**
 * Setters TODO : JAVADOC
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
	public void setErrorMessage(String error) {
		this.errorMessage = error;
	}

/** TODO  : Connection with controller and view
 * Method that applies the clear button to the instance variables of the calculator
 */
	public void clear() {
		this.operand_a = 0;
		this.operand_b = 0;
		this.operand_c = 0;
		this.operand_d = 0;
		this.expression = new StringBuilder("");
		this.expressionToParse = new StringBuilder("");
		this.transcendentalFunction = null;
		this.errorMessage = "";
	}
	
/** TODO : Connection with controller and view
* Method that applies the DEL button to the instance variables of the calculator
*/
	public void del() {
		this.expression = expression.deleteCharAt(expression.length()-1);
	}
	
/**
 * 
 * @param newInput
*/
	public void appendToExpression(String newInput) {
		if(!checkIfFunctionEnabled(newInput)) {
		String expressionString = expression.toString();

			if((expression.toString().isEmpty() && !newInput.equals("+") && !newInput.equals("*") && !newInput.equals("/")) || StringHelper.checkIfOperandsClicked(newInput)) {
				expression.append(newInput);
			} 
			else if (expression.length() == 1  && expressionString.equals("-") && StringHelper.checkIfOperatorClicked(newInput)) {
				if(newInput.equals("+")) {
					expression.deleteCharAt(expression.length() - 1);
				} 
				else {
					return;
				}
			} else if (expression.toString().isEmpty() && (newInput.equals("+") || newInput.equals("*") || newInput.equals("/"))) {
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

	
	private boolean isBasicArithmetic() {
		if(!expression.toString().isEmpty()) {
			String expString = expression.toString();
			if(expString.contains("+")) {
				String[] operands = expString.split("\\+");
				this.expression = expression.replace(0, expression.length()-1, compute(operands[0], operands[1], "+"));
				return true;
			}
			if(expString.contains("-")) {
				String[] operands = expString.split("\\-");
				this.expression = expression.replace(0, expression.length()-1, compute(operands[0], operands[1], "-"));
				return true;
			}
			if(expString.contains("*")) {
				String[] operands = expString.split("\\*");
				this.expression = expression.replace(0, expression.length()-1, compute(operands[0], operands[1], "*"));
				return true;
			}
			if(expString.contains("/")) {
				String[] operands = expString.split("\\/");
				this.expression = expression.replace(0, expression.length()-1, compute(operands[0], operands[1], "/"));

				return true;
			}
		}
		return false;
	}
	/**
	 * Method used for computing basic arithmetic
	 * @param operand1
	 * @param operand2
	 * @param operator
	 * @return
	 */
		private String compute(String op1, String op2, String operator) {
			double operand1 = Double.parseDouble(op1);
			double operand2 = Double.parseDouble(op2);
			double result = 0;
			switch(operator) {
				case "+": result = operand1+operand2; break;
				case "-": result = operand1-operand2; break;
				case "*": result = operand1*operand2; break;
				case "/": {if(operand2==0) {this.errorMessage ="Division by 0 error";result = 0;}
							result = operand1/operand2;} break;
				default: result= 0;
			}
			return Double.toString(result);
			}
	
//-------------------------------- For Transcendental Functions Methods -----------------------------------------------//		


	public void appendAbxToExpression(String abx) {
		if(expression.toString().isEmpty() || !StringHelper.checkIfOperandsClicked(expression.substring(expression.length() - 1))) {
			transcendentalFunction = new FunctionAbx();
			expression.append(abx);
		}
	}

/**
 * Method called by the controller when requests a logbx expression.
 * Creates the function instance and appends to the Calculator expression.	
 * @param logbx
 */
	public void appendLogbXToExpression(String logbx) {
		if(expression.toString().isEmpty() || !StringHelper.checkIfOperandsClicked(expression.substring(expression.length() - 1))) {
			transcendentalFunction = new FunctionLogBX();
			expression.append(logbx);
		}
	}
	
	// TODO : ADD YOUR OWN APPEND (FUNCTION)TO Expression Method
	
	public void appendStandardDevExpression(String i)
	{
		
		transcendentalFunction = new FunctionStandardDev(expression.toString());
		expression.append(i);
	}
/**
 * 
 * @param input
 * @return
 */
	private boolean checkIfFunctionEnabled(String input) {
		if(!expression.toString().isEmpty()) {
			String expString = expression.toString();
			
			//AB^X Function
			if(transcendentalFunction instanceof FunctionAbx) {
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
			else if (transcendentalFunction instanceof FunctionStandardDev)
			{

				return true;
			}
		
		// TODO : ADD YOUR FUNCTION
			
			
	}
		return false;

	}

	
	public void calculateExpression(String calculatorExpression) {
		if(!expression.toString().isEmpty()) {
		String lastInput = expression.substring(expression.length() - 1);
		
		if(StringHelper.checkIfOperatorClicked(lastInput)) {
			expression.deleteCharAt(expression.length()-1);
		}
		
		//Disable all expressions, just test the stdev function
		if(true) 
		{
			transcendentalFunction = new FunctionStandardDev(expression.toString());
			//expression.append(expression.toString());
			//this.expression = expression(transcendentalFunction.compute()).toString();
			this.expression = expression.replace(0, expression.length()-1, Double.toString(transcendentalFunction.compute()));
			System.out.println("Calculating Standard Deviation of: " + transcendentalFunction);
			
			// TODO
		}
		
		// BASIC
		else if(this.isBasicArithmetic());
		{
			
		}
		
		//TRANSCENDENTAL FUNCTIONS 

	}
	}
	
	

	
}