package functions;

import misc.StringHelper;

public class FunctionAbx extends Functions {

	public FunctionAbx() {
		this.a = 0;
		this.b = 0;
		this.x = 0;
		this.varsInputed = 0;
		this.totalVars = 3;
	}

	public FunctionAbx(double a, double b, double x) {
		this.a = a;
		this.b = b;
		this.x = x;
		this.varsInputed = 0;
		this.totalVars = 3;
	}

	public double compute() {
		validate();
		
		double powerResult = this.b;
		for(double i = 0; i < this.x -1; i++) {
			powerResult *= this.b;
		}
		return a * powerResult;
	}

	@Override
	public boolean validate() {

		return true;
	}

	public String parse(String input, String expression) {

		String toReturn = expression;
		if (varsInputed == 0) {
			toReturn = setAVariable(input, expression);

		} else if (varsInputed == 1) {
			toReturn = setBVariable(input, expression);

		} else if (varsInputed == 2) {
			toReturn = setXVariable(input, expression);
		} else {
			if (StringHelper.checkIfOperandsClicked(input)) { //can't put an operand right after the function
				return expression; 
			} else { 
				this.varsInputed = 0;
				return expression;
			}
		}

		return toReturn.toString();
	}
	
	private String setAVariable(String input, String expression) {
		StringBuilder exprBuilder = new StringBuilder(expression);

		String currentAToInt = doubleToIntString(this.a + "");
		String currentA =  this.a == 0 ? (input.endsWith(".0") ? doubleToIntString(input) : input)
			: currentAToInt + input;
		
		if (this.a == 0) {

			exprBuilder.replace(expression.length() - 3, expression.length() - 2, input + "\u22C5");

		} else {
			exprBuilder.insert(expression.length() - 3, input.toCharArray(), 0, 1);
		}

			this.setA(Double.parseDouble(currentA));

		return exprBuilder.toString();
	}
	private String setBVariable(String input, String expression) {
		StringBuilder exprBuilder = new StringBuilder(expression);

		String currentBToInt = doubleToIntString(this.b + "");
		String currentB =  this.b == 0 ? (input.endsWith(".0") ? doubleToIntString(input) : input)
			: currentBToInt + input;
		
		if (this.b == 0) {

			exprBuilder.replace(expression.length() - 2, expression.length() - 1, input);

		} else {
			exprBuilder.insert(expression.length() - 1, input.toCharArray(), 0, 1);
		}

			this.setB(Double.parseDouble(currentB));

		return exprBuilder.toString();
	}
	
	private String setXVariable(String input, String expression) {
		StringBuilder exprBuilder = new StringBuilder(expression);

		String currentXToInt = doubleToIntString(this.x + "");
		String currentX =  this.x == 0 ? (input.endsWith(".0") ? doubleToIntString(input) : input)
			: currentXToInt + input;
		input = StringHelper.superscript(input);
		if (this.x == 0) {

			exprBuilder.replace(expression.length() - 1, expression.length(), input);

		} else {
			exprBuilder.insert(expression.length(), input.toCharArray(), 0, 1);
		}

			this.setX(Double.parseDouble(currentX));

		return exprBuilder.toString();
	}

	private String doubleToIntString(String value) {
		StringBuilder inputBuilder = new StringBuilder(value);
		return inputBuilder.delete(inputBuilder.length() - 2, inputBuilder.length()).toString();

	}


}
