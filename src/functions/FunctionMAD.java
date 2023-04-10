package functions;

import java.util.Arrays;
import misc.StringHelper;

public class FunctionMAD extends Functions {

	private double[] dataset;
	
	public FunctionMAD() {
		this.dataset = new double[0];
		this.varsInputed = 0;
	}
	
	@Override
	public double compute() {
		if (!validate()) {
			return Double.NaN;
		}
		
		// Find average of dataset
		double average = 0;
		int varsInDataset = varsInputed + 1;
		for (int i = 0; i < varsInDataset; i++) {
			average += dataset[i];
		}
		average /= varsInDataset;
		
		// Find MAD of dataset
		double result = 0;
		for (int i = 0; i < varsInDataset; i++) {
			double distance = average - dataset[i];
			if (distance < 0) distance = -distance;
			result += distance;
		}
		result /= varsInDataset;
		return result;
	}

	@Override
	public boolean validate() {
		if (dataset.length == 0) { 
			this.setErrorMessage("Empty dataset");
			return false; 
		}
		return true;
	}

	@Override
	public String parse(String input, String expression) {
		// If array too small, resize dataset
		if (varsInputed + 1 >= dataset.length) {
			resizeDataset();
		}
		
		StringBuilder exprBuilder = new StringBuilder(expression);
		String thisNumber = ""; // String of the current number in the expression
		String afterDecimal = ""; // Decimal part of current number
		
		// First number to input
		if (varsInputed == 0) {
			// Process first few inputs
			if (!exprBuilder.toString().equals("MAD")) {
				thisNumber = exprBuilder.substring(exprBuilder.lastIndexOf("{") + 1, exprBuilder.length() - 2);
			}
			// Rewrite MAD
			exprBuilder.delete(exprBuilder.lastIndexOf("D") + 1, exprBuilder.length());
			exprBuilder.append("({");
		} 
		// Check if the current number is 0
		else if (dataset[varsInputed] == 0) {
			// Delete brackets to rewrite them later
			exprBuilder.delete(exprBuilder.lastIndexOf("}"), exprBuilder.length());
			if (!StringHelper.isDecimalSymbol(input)) {
				// Check if new value is being written
				if (countValues(exprBuilder) <= varsInputed) {
					exprBuilder.append(", "); // Separate values with a comma
				} else {
					// Save current number string and delete it from expression to rewrite it later
					thisNumber = exprBuilder.substring(exprBuilder.lastIndexOf(",") + 2, exprBuilder.length());
					exprBuilder.delete(exprBuilder.lastIndexOf(",") + 2, exprBuilder.length());
				}
			}
		}
		// Current number is not first number and not 0
		else {
			// Save current number string and delete it from expression to rewrite it later
			thisNumber = exprBuilder.substring(exprBuilder.lastIndexOf(",") + 2, exprBuilder.length() - 2);
			exprBuilder.delete(exprBuilder.lastIndexOf(",") + 2, exprBuilder.length());
		}
		
		// Check if current number has a decimal point
		boolean isThisADecimal = thisNumber.contains(".");
		if (isThisADecimal) {
			// Obtain the decimal part as a string
			afterDecimal = thisNumber.substring(thisNumber.lastIndexOf(".") + 1);
		}
		
		// Check if current number is negative
		boolean isThisANegative = thisNumber.contains("-");
		
		String toAppend = "";
		
		// Add negation sign if necessary
		if (isThisANegative) {
			toAppend += "-";
		}
		// Process decimal point input
		if (StringHelper.isDecimalSymbol(input)) {
			// Ignore decimal point input if number is already decimal
			if (isThisADecimal) {
				return expression;
			}			
			// Add decimal point to integer in expression
			if (dataset[varsInputed] != 0) {
				toAppend += Integer.toString(currentAbsoluteInt());
			}
			// Special case: user writes "0.x" as first value
			else if (varsInputed == 0) {
					toAppend += "0";
			}
			toAppend += ".";
		}
		// Process negation sign input
		else if (input.equals("(-)")) {
			// Ignore negation sign input if number is not 0
			if (dataset[varsInputed] != 0) {
				return expression;
			}
			// Add negation sign to expression
			toAppend += "-";
		}
		else {
			double digit = Double.parseDouble(input);
			int negationMultiplier = (isThisANegative) ? -1 : 1;
			if (isThisADecimal) {
				// Divide digit by appropriate power of 10 based on current length of decimal part
				for (int i = 0; i < afterDecimal.length() + 1; i++) {
					digit /= 10;
				}
				// Add digit to value in dataset and in expression
				dataset[varsInputed] += (digit * negationMultiplier);
				toAppend += Integer.toString(currentAbsoluteInt()) + "." + afterDecimal + input;
			} else {
				// Shift number one space to the left to add new digit
				dataset[varsInputed] = 10 * dataset[varsInputed] + (digit * negationMultiplier);
				// Add digit to value in expression
				toAppend += Integer.toString(currentAbsoluteInt());
			}
		}
		
		// Fill in expression with 0s if number of written values does not match actual values
		int numberOfPrintedValues = countValues(exprBuilder);
		for (int i = numberOfPrintedValues; i < varsInputed + 1; i++) {
			exprBuilder.append("0, ");
		}
		
		// Close brackets and return expression
		exprBuilder.append(toAppend + "})");
		return (exprBuilder.toString());
	}
	
	// Double size of dataset to reduce complexity
	private void resizeDataset() {
		while (varsInputed + 1 >= dataset.length) {
			dataset = Arrays.copyOf(dataset, 2 * dataset.length + 1);
		}
	}
	
	// Count the number of commas to evaluate how many values are already written in the expression
	private int countValues(StringBuilder expr) {
		int ctr = 0;
		for (int i = 0; i < expr.length(); i++) {
			if (expr.charAt(i) == ',') {
				ctr++;
			}
		}
		return ctr + 1;
	}
	
	private int currentAbsoluteInt() {
		int value = (int)dataset[varsInputed];
		if (value < 0) {
			value = - value;
		}
		return value;
	}

}
