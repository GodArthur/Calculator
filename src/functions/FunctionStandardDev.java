package functions;

import misc.StringHelper;

public class FunctionStandardDev extends Functions
{
	
	public  double squareRoot(double number) {

        double temp;
    
        double squareRoot = number / 2;
        
        double squareRootFinal = 0;
    
        do {
            temp = squareRoot;
            squareRoot = (temp + (number / temp)) / 2;
        } while ((temp - squareRoot) != 0);
    
        
        squareRootFinal = squareRoot;
        return squareRoot;
        
    }

	double[] values;
	
	String stringInput;
	
	public FunctionStandardDev() 
	{
		this.stringInput = "";
	}

	public double compute() 
	{
		
		String[] numStrings = stringInput.substring(5).split(",");
		values = new double[numStrings.length];
		
		for(int s = 0; s < numStrings.length;s++)
		{
			numStrings[s] = numStrings[s].replace("(", "").replace(")", "");
		}
		
		//Take the same string array and convert each number into a double
		for(int i = 0; i < numStrings.length;i++)
		{
			values[i] = Double.parseDouble(numStrings[i]);
		}
		
		
		double populationMean = 0;
		double total = 0;
		
		//Find the sum of population 
		for (int i = 0; i < values.length; i++) 
		{
		    populationMean += values[i];
		}
		
		//divide population by number of values
		populationMean = populationMean/values.length;
		
        for(int k = 0; k < values.length; k++)
        {
            total += (values[k] - populationMean)*(values[k] - populationMean);
        }
		
        
	    //Divide total by number of values
        total = total/values.length;
        
        //Finally, do the square root
        total = squareRoot(total);
		
		return total;
	}
	
	public String parse(String input, String expression) {
		StringBuilder exprBuilder = new StringBuilder(expression);
		exprBuilder = exprBuilder.append(input);
		this.stringInput = exprBuilder.toString();
				
		return exprBuilder.toString();
	}
	
	
	@Override
	public boolean validate() {
		if (values.length < 2) 
		{ 
			this.setErrorMessage("Dataset must be more than 2");
			return false; 
		}
		return true;
	}
}
