package functions;

import misc.StringHelper;
import misc.StringHelper.*;

public class FunctionXY extends Functions {

	public FunctionXY() {

		this.x = 0;
		this.y = 0;
		this.varsInputed = 0;
		this.totalVars = 2;

	}

	public double compute() {

		double value = 1;

		double absY = abs(y);

		if (isDecimal(y)) {
			
			return fractionalExponent(x, y);
		}

		for (int i = 0; i < absY; i++) {

			value = value * this.x;
		}

		if (absY != y) {
			return 1 / value;
		}
		return value;
	}

	/**
	 * Function checks the floor of the value and sees if it matches the original
	 * number
	 * 
	 * @param num the value being checked
	 * 
	 * @return if the number is a decimal, it returns
	 */
	public boolean isDecimal(double num) {

		int castedNum = (int) num;
		double floorValue = castedNum;

		if (num != floorValue) {

			return true;
		}

		return false;
	}

	// returns absolute value of a number
	private double abs(double num) {

		if (num < 0) {

			return -num;
		}

		return num;
	}
	
	public boolean validate() {
		
		return false;
	}

	

	/**
	 * function calculates fractional exponents
	 * for the exponential function using the taylor 
	 * polynomial for exponential and natural log
	 * @param base
	 * @param exponent
	 * @return
	 */
	public double fractionalExponent(double base, double exponent) {
		double logBase = taylorSeriesLn(base);
		double power = exponent * logBase;
		double result = taylorSeriesExp(power);
		return result;
	}

	/**
	 * Function calculates the taylor series for the power value
	 * in the exponential function
	 * @param x
	 * @return
	 */
	public double taylorSeriesExp(double x) {
		double sum = 0.0;
		double term = 1.0;
		int n = 0;

		while (sum + term != sum) {
			sum += term;
			n++;
			term *= x / n;
		}

		return sum;
	}

	/**
	 * Function calculates the taylor series
	 * for the natural log function
	 * @param x
	 * @return
	 */
	public double taylorSeriesLn(double x) {
		if (x <= 0) {
			throw new IllegalArgumentException("x must be greater than 0");
		}

		double y = (x - 1) / (x + 1);
		double ySquared = y * y;
		double sum = 0.0;
		double term = y;
		int n = 1;

		while (sum + term != sum) {
			sum += term;
			n += 2;
			term *= ySquared * (n - 2) / n;
		}

		return 2 * sum;
	}

	public String parse(String input, String expression) {

		System.out.println(varsInputed);
		System.out.println(expression);
		StringBuilder expBuilder = new StringBuilder(expression);

		if (expBuilder.toString().equals("x^y")) {
			expBuilder.replace(0, expBuilder.length(), input);
			expBuilder.append("^y");
			this.x = Double.parseDouble(input);
		}

		else if (this.varsInputed == 0) {
			expBuilder.replace(expBuilder.length() - 2, expBuilder.length(), input);
			expBuilder.append("^y");
			this.x = x * 10 + Double.parseDouble(input);
		}
		// Enter First digit for y
		if (this.varsInputed == 1 && expression.contains("y")) {
			expBuilder.replace(expBuilder.length() - 1, expBuilder.length(), StringHelper.superscript(input));
			this.y = Double.parseDouble(input);
		}

		// Enter remaining digits for y
		else if (this.varsInputed == 1) {
			expBuilder.append(StringHelper.superscript(input));
			this.y = y * 10 + Double.parseDouble(input);
		}

		return expBuilder.toString();
	}

}
