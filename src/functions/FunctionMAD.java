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
		
		double average = 0;
		int varsInDataset = varsInputed + 1;
		for (int i = 0; i < varsInDataset; i++) {
			average += dataset[i];
		}
		average /= varsInDataset;
		
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
		if (varsInputed + 1 >= dataset.length) {
			resizeDataset();
		}
		
		StringBuilder exprBuilder = new StringBuilder(expression);
		String thisNumber = "";
		String afterDecimal = "";
		boolean isThisADecimal = false;
		
		if (varsInputed == 0) {
			if (!exprBuilder.toString().equals("MAD")) {
				thisNumber = exprBuilder.substring(exprBuilder.lastIndexOf("{") + 1, exprBuilder.length() - 2);
			}
			exprBuilder.delete(exprBuilder.lastIndexOf("D") + 1, exprBuilder.length());
			exprBuilder.append("({");
			if (StringHelper.isDecimalSymbol(input) && dataset[varsInputed] == 0) {
				exprBuilder.append("0");
			}
		} else if (dataset[varsInputed] == 0) {
			exprBuilder.delete(exprBuilder.lastIndexOf("}"), exprBuilder.length());
			if (!StringHelper.isDecimalSymbol(input)) {
				if (countValues(exprBuilder) <= varsInputed) {
					exprBuilder.append(", ");
				} else {
					thisNumber = exprBuilder.substring(exprBuilder.lastIndexOf(",") + 2, exprBuilder.length());
					exprBuilder.delete(exprBuilder.lastIndexOf(",") + 2, exprBuilder.length());
				}
			}
		} else {
			thisNumber = exprBuilder.substring(exprBuilder.lastIndexOf(",") + 2, exprBuilder.length() - 2);
			exprBuilder.delete(exprBuilder.lastIndexOf(",") + 2, exprBuilder.length());
		}
		
		isThisADecimal = thisNumber.contains(".");
		if (isThisADecimal) {
			afterDecimal = thisNumber.substring(thisNumber.lastIndexOf(".") + 1);
		}
		
		String toAppend = "";
		if (StringHelper.isDecimalSymbol(input)) {
			if (isThisADecimal) {
				return expression;
			}
			if (dataset[varsInputed] != 0) {
				toAppend += Integer.toString((int)dataset[varsInputed]);
			}
			toAppend += ".";
		} else {
			double digit = Double.parseDouble(input);
			if (isThisADecimal) {
				for (int i = 0; i < afterDecimal.length() + 1; i++) {
					digit /= 10;
				}
				dataset[varsInputed] += digit;
				toAppend = Integer.toString((int)dataset[varsInputed]) + "." + afterDecimal + input;
			} else {
				dataset[varsInputed] = 10 * dataset[varsInputed] + digit;
				toAppend = Integer.toString((int)dataset[varsInputed]);
			}
			int numberOfPrintedValues = countValues(exprBuilder);
			for (int i = numberOfPrintedValues; i < varsInputed + 1; i++) {
				exprBuilder.append("0, ");
			}
		}
		exprBuilder.append(toAppend + "})");
		return (exprBuilder.toString());
	}
	
	private void resizeDataset() {
		while (varsInputed + 1 >= dataset.length) {
			dataset = Arrays.copyOf(dataset, 2 * dataset.length + 1);
		}
	}
	
	private int countValues(StringBuilder expr) {
		int ctr = 0;
		for (int i = 0; i < expr.length(); i++) {
			if (expr.charAt(i) == ',') {
				ctr++;
			}
		}
		return ctr + 1;
	}

}
