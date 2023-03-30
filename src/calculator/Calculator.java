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
//	private double operand_a; 
//	private double operand_b;
//	private double operand_c;
//	private double operand_d;
	
/**
 * Getters TODO : JAVADOC
 */
//	public double getOperand_A() {
//		return operand_a;
//	}
//	public double getOperand_B() {
//		return operand_b;
//	}
//	public double getOperand_C() {
//		return operand_c;
//	}
//	public double getOperand_D() {
//		return operand_d;
//	}	
	public String getError() {
		return this.errorMessage;
	}
	
	public String getExpression() {
		return expression.toString();
	}
	
/**
 * Setters TODO : JAVADOC
 */	
//	public void setOperand_a(double operand) {
//		this.operand_a = operand;
//	}
//	public void setOperand_b(double operand) {
//		this.operand_b = operand;
//	}	
//	public void setOperand_c(double operand) {
//		this.operand_c = operand;
//	}
//	public void setOperand_d(double operand) {
//		this.operand_d = operand;
//	}
	public void setErrorMessage(String error) {
		this.errorMessage = error;
	}
	
//-------------------------------- Basic Buttons Functions  -----------------------------------------------//	
/** 
 * Method that applies the clear button to the instance variables of the calculator
 */
	public void clear() {
		this.expression = new StringBuilder("");
		this.expressionToParse = new StringBuilder("");
		this.transcendentalFunction = null;
		this.errorMessage = "";
	}
	
/** 
* Method that applies the DEL button to the instance variables of the calculator
*/
	public void del() {
		 if(!expression.isEmpty())
			expression.deleteCharAt(expression.length()-1);
	}

	public void appendToExpression(String newInput) {
		if (newInput =="Clr")
			this.clear();
		
		if(!checkIfFunctionEnabled(newInput)) {
			if(StringHelper.checkIfOperandsClicked(newInput))
				expression.append(newInput);
			
			else if(StringHelper.checkIfOperatorClicked(newInput))
				operatorHandler(newInput);
			
			// TODO : THE BUTTONS BELOW ARE NOT AVAILABLE WHEN USING A T.FUNCTION YET
			else if(newInput=="DEL")
				this.del();
			
			else if (newInput == ".")
				decimalHandler(newInput);
			
			else if(newInput =="(-)")
				negationHandler(newInput);
		} 
	}
	
	
	private void operatorHandler(String newInput) {
		if(!expression.isEmpty()) {
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
	
	private void decimalHandler(String newInput) {
		if(!expression.isEmpty()) {
			String lastInput = expression.substring(expression.length() - 1);
			if(StringHelper.checkIfOperandsClicked(lastInput)) {
				expression.append(newInput);
			}
		}
		//TODO :  IF THERE IS NO DIGIT ENTERED AFTER DECIMAL AND THEY CLICK CALCULATE/...ERROR MESSAGE?
	}
	
	private void negationHandler(String newInput) {
		if(!expression.isEmpty()) {
			String lastInput = expression.substring(expression.length() - 1);
			if(lastInput==newInput) {
				return;
			}
			else if(StringHelper.checkIfOperatorClicked(lastInput)) {
				expression.append("-");
			}
		}
		else
			expression.append("-");
	}

/**
 * Method called when the Controller requests "=". Will calculate basic arithmetic and transcendental functions	
 * @param calculatorExpression
 * */
		public void calculateExpression(String calculatorExpression) {
			if(!expression.isEmpty()) {
			String lastInput = expression.substring(expression.length() - 1);
						
				if(StringHelper.checkIfOperatorClicked(lastInput)) {
					expression.deleteCharAt(expression.length()-1);
				}
						
				// BASIC ARITHMETIC
				if(isBasicArithmetic()) 
					computeBasicArithmetic();
						
				//TRANSCENDENTAL FUNCTIONS 
				else {
					this.expression = new StringBuilder(Double.toString(transcendentalFunction.compute()));
				}
			}
			}
				
//-------------------------------- Basic Arithmetic  -----------------------------------------------//	
	private boolean isBasicArithmetic() {
		if(!expression.isEmpty()) {
			String expString = expression.toString();
			if(expString.contains("+") || expString.contains("—")|| expString.contains("*")|| expString.contains("/")) {
				return true;
			}
		}
		return false;
	}
	
	private void computeBasicArithmetic() {
			String expString = expression.toString();
			if(expString.contains("+")) {
				String[] operands = expString.split("\\+");
				this.expression = expression.replace(0, expression.length(), compute(operands[0], operands[1], "+"));
			}
			if(expString.contains("—")) {
				String[] operands = expString.split("\\—");
				this.expression = expression.replace(0, expression.length(), compute(operands[0], operands[1], "-"));
			}
			if(expString.contains("*")) {
				String[] operands = expString.split("\\*");
				this.expression = expression.replace(0, expression.length(), compute(operands[0], operands[1], "*"));
			}
			if(expString.contains("/")) {
				String[] operands = expString.split("\\/");
				this.expression = expression.replace(0, expression.length(), compute(operands[0], operands[1], "/"));
			}
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

//-------------------------------- Transcendental Functions Methods -----------------------------------------------//		


	public void appendAbxToExpression(String abx) {
		boolean isFunctionInUse = transcendentalFunction != null;

		if(!isFunctionInUse && ( expression.isEmpty() || !StringHelper.checkIfOperandsClicked(expression.substring(expression.length() - 1)))) {
			transcendentalFunction = new FunctionAbx();
			expression.append(abx);
		}
	}

/**
 * Method called by the controller when it requests a logbx expression.
 * Creates the function instance and appends to the Calculator expression.	
 * @param logbx
 */
	public void appendLogbXToExpression(String logbx) {
	boolean isFunctionInUse = (transcendentalFunction == null);
	
		if(isFunctionInUse && expression.isEmpty()){
			transcendentalFunction = new FunctionLogBX();
			expression.append(logbx);
		}
	}
	
	public void appendArccosToExpression(String arccos) {
		boolean isFunctionInUse = (transcendentalFunction == null);
		
			if(isFunctionInUse && expression.isEmpty()){
				transcendentalFunction = new FunctionArccos();
				expression.append(arccos);
			}
		}
	
	// TODO : ADD YOUR OWN APPEND (FUNCTION)TO Expression Method
	
/**
 * 
 * @param input
 * @return
 */
	private boolean checkIfFunctionEnabled(String input) {
		//if(!expression.isEmpty()) {

			String expString = expression.isEmpty()? "" : expression.toString();
			
			//AB^X Function
			if(transcendentalFunction instanceof FunctionAbx) {
				if(!StringHelper.checkIfOperandsClicked(input)) {
					return true;
				}
				String expr = transcendentalFunction.parse(input, expString);

				/*if(transcendentalFunction.getVarsInputed() == transcendentalFunction.getTotalVars()) {
					System.out.println("set to null");

					transcendentalFunction = null;
				}*/
				expression = new StringBuilder(expr);

				System.out.println("EXP: "+expression);
				return true;
			//}
			}
		
		// Log_BX
			if(transcendentalFunction instanceof FunctionLogBX) {
				// Does not let the user enter an operator when the transcendental function is enabled
				if(!StringHelper.checkIfOperandsClicked(input)) {
						return true;
				}
				// Call to Method parse that will receive the input and reformat it as well as store the value
				String updatedExpression = transcendentalFunction.parse(input, expString);
				this.expression = new StringBuilder(updatedExpression);
					
				System.out.println("EXP in CheckifFunctionEnabled: "+expression);
				return true;
				}
			
			// Arccos
			if(transcendentalFunction instanceof FunctionArccos) {
				// Does not let the user enter an operator when the transcendental function is enabled
				if(!StringHelper.checkIfOperandsClicked(input)) {
						return true;
				}
				// Call to Method parse that will receive the input and reformat it as well as store the value
				String updatedExpression = transcendentalFunction.parse(input, expString);
				this.expression = new StringBuilder(updatedExpression);
					
				System.out.println("EXP in CheckifFunctionEnabled: "+expression);
				return true;
				}
		
	// TODO : ADD YOUR FUNCTION
		return false;

	}

	
	public void updateNextFunctionValue() {
		if(transcendentalFunction == null) {
			return;
		}
		// MAYBE NO NEED TO SEPARATE PER FUNCTION?
		if(transcendentalFunction instanceof FunctionAbx) {
			if(transcendentalFunction.getVarsInputed() <= transcendentalFunction.getTotalVars()) {
				transcendentalFunction.setVarsInputed(transcendentalFunction.getVarsInputed()+1);
			} else {
				transcendentalFunction = null;
			}
		}
		if(transcendentalFunction instanceof FunctionLogBX) {
			if(transcendentalFunction.getVarsInputed() < transcendentalFunction.getTotalVars()-1) {
				transcendentalFunction.setVarsInputed(transcendentalFunction.getVarsInputed()+1);
			} else {
				transcendentalFunction = null;
			}
		}
		
	}
	
	

	
}