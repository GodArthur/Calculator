package functions;

import java.util.Arrays;

import misc.StringHelper;

public class FunctionStandardDev extends Functions
{
	
	public static double squareRoot(double num) {
	    if (num < 0) {
	        return Double.NaN;  // Return NaN for negative input
	    } else if (num == 0) {
	        return 0;  // Return 0 for input 0
	    }

	    double x = num / 2;  // Initial guess
	    double lastX = 0;  // Variable to hold last guess

	    while (x != lastX) {
	        lastX = x;
	        x = (x + num / x) / 2;  // Newton's method iteration
	    }

	    return x;
	}

	double[] values;
	
	String stringInput;
	
	public FunctionStandardDev() 
	{
		this.values = new double[0];
		this.stringInput = "";
		this.varsInputed = 0;
	}

	public double compute(){
		
		if (!validate()) {
			return Double.NaN;
		}

		int varsInDataset = varsInputed + 1;
		for (int i = 0; i < varsInDataset; i++) 
		{
		    System.out.print("testing: " + values[i]);
		}
		
		System.out.println("There is : " + Double.NaN );
		
	    if (varsInDataset < 1) {
	        return 0;
	    }
			
		double populationMean = 0;
		double total = 0;
		
		//Find the sum of population 
		for (int i = 0; i < varsInDataset; i++) 
		{
		    populationMean += values[i];
		}
		
		//divide population by number of values
		populationMean = populationMean/varsInDataset;
		
        for(int k = 0; k < varsInDataset; k++)
        {
            total += (values[k] - populationMean)*(values[k] - populationMean);
        }
		
        
	    //Divide total by number of values
        total = total/varsInDataset;
        
        //Finally, do the square root
        total = squareRoot(total);
		
		return total;
	}
	
	public String parse(String input, String expression) {
//		StringBuilder exprBuilder = new StringBuilder(expression);
//		exprBuilder = exprBuilder.append(input);
//		this.stringInput = exprBuilder.toString();
//				
//		return exprBuilder.toString();
		
		if (varsInputed + 1 >= values.length) {
			resizeDataset();
		}
		
		StringBuilder exprBuilder = new StringBuilder(expression);
		if (varsInputed == 0) {
			exprBuilder.delete(exprBuilder.lastIndexOf("v") + 1, exprBuilder.length());
			exprBuilder.append("(");
		} else if (values[varsInputed] == 0) {
			exprBuilder.delete(exprBuilder.lastIndexOf(")"), exprBuilder.length());
			exprBuilder.append(",");
		} else {
			exprBuilder.delete(exprBuilder.lastIndexOf(",") + 2, exprBuilder.length());
		}
		
		values[varsInputed] = 10 * values[varsInputed] + Double.parseDouble(input);
		int numberOfPrintedValues = countValues(exprBuilder);
		System.out.print("values: " + numberOfPrintedValues);
//		for (int i = numberOfPrintedValues; i < varsInputed + 1; i++) {
//			exprBuilder.append("0, ");
//		}
		exprBuilder.append((int)values[varsInputed] + ")");
		return (exprBuilder.toString());
	}
	
	
	private void resizeDataset() {
		while (varsInputed + 1 >= values.length) {
			values = Arrays.copyOf(values, 2 * values.length + 1);
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
	
	@Override
	public boolean validate() {
		if (values.length < 2) 
		{ 
			this.setErrorMessage("Dataset must have at least 2 values");
			return false; 
		}
		return true;
	}
}
