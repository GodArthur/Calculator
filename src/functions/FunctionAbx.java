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
		return a * (Math.pow(b, x));
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	public String parse(String input, String expression) {

		System.out.println("EXPRESSION GIVEN: " + expression);
		System.out.println("INPUT GIVEN: " + input);
		System.out.println("this.a: " + this.a);
		String toReturn = expression;
		if (varsInputed == 0) {
			toReturn = setValueInExpression(input, expression, 3, 2, 3);

		} else if (varsInputed == 1) {
			toReturn = setValueInExpression(input, expression, 2, 1, 1);

		} else if (varsInputed == 2) {
			toReturn = setValueInExpression(input, expression, 1, 0, 0);
		} else {
			if (StringHelper.checkIfOperandsClicked(input)) {
				return expression; // skip
			} else { // isAbx = false; varsInputed = 0; //expressionToParse =
				// expression.append(compute());
				this.varsInputed = 0;
				double result = compute();
				String expressionToParse = expression.replace("abx", String.valueOf(result));
				return expression;
			}
		}

		return toReturn.toString();
	}

	private String doubleToIntString(String value) {
		StringBuilder inputBuilder = new StringBuilder(value);
		return inputBuilder.delete(inputBuilder.length() - 2, inputBuilder.length()).toString();

	}

	private String setValueInExpression(String input, String expression, int start, int end, int insertAt) {

		StringBuilder exprBuilder = new StringBuilder(expression);
		String currentAToInt = doubleToIntString(this.a + "");
		String currentBToInt = doubleToIntString(this.b + "");
		String currentXToInt = doubleToIntString(this.x + "");

		String currentA = this.a == 0 ? (input.endsWith(".0") ? doubleToIntString(input) : input)
				: currentAToInt + input;
		String currentB = this.b == 0 ? (input.endsWith(".0") ? doubleToIntString(input) : input)
				: currentBToInt + input;
		String currentX = this.x == 0 ? (input.endsWith(".0") ? doubleToIntString(input) : input)
				: currentXToInt + input;
		String isVarA = this.varsInputed == 0 ? "*" : "";
		System.out.println("CURRENT A: " + currentA);

		if ((this.varsInputed == 0 && this.a == 0) || (this.varsInputed == 1 && this.b == 0) || (this.varsInputed == 2 && this.a == 0)) {
			exprBuilder.replace(expression.length() - start, expression.length() - end, input + isVarA);

		} else {
			exprBuilder.insert(expression.length() - insertAt, input.toCharArray(), 0, 1);
		}
		if(this.varsInputed == 0) {
			this.setA(Double.parseDouble(currentA));
		} else if (this.varsInputed == 1) {
			this.setB(Double.parseDouble(currentB));
		} else if(this.varsInputed == 2) {
			this.setX(Double.parseDouble(currentX));
		}
		return exprBuilder.toString();
	}
	
	private String setXValueInExpression(String input, String expression) {
		
		StringBuilder exprBuilder = new StringBuilder(expression);
		String currentBToInt = doubleToIntString(this.b + "");
		String current = this.b == 0 ? (input.endsWith(".0") ? doubleToIntString(input) : input)
				: currentBToInt + input;
		System.out.println("CURRENT B: " + current);

		if (this.b == 0) {
			exprBuilder.replace(expression.length() - 2, expression.length()-1, input);

		} else {
			exprBuilder.insert(expression.length() - 1, input.toCharArray(), 0, 1);
		}
		this.setB(Double.parseDouble(current));
		return exprBuilder.toString();
	}

}
