package functions;

import misc.StringHelper;

public class FunctionStandardDev extends Functions
{
	
	public  double squareRoot(double number) {

        double temp;
    
        double sr = number / 2;
    
        do {
            temp = sr;
            sr = (temp + (number / temp)) / 2;
        } while ((temp - sr) != 0);
    
        return sr;
        
    }

	double[] values;
	
	public FunctionStandardDev() 
	{
		this.values = new double[0];
	}
	public FunctionStandardDev(double[] x) 
	{
		this.values = x;
	}
	
	public double compute() 
	{
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
		// TODO Auto-generated method stub
		return null;
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
