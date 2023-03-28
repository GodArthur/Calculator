package functions;

public class FunctionMAD extends Functions {

	private double[] dataset;
	
	public FunctionMAD() {
		this.dataset = new double[0];
	}
	public FunctionMAD(double[] dataSet) {
		this.dataset = dataSet;
	}
	
	@Override
	public double compute() {
		double average = 0;
		for (double value : dataset) {
			average += value;
		}
		average /= dataset.length;
		
		double result = 0;
		for (double value : dataset) {
			double distance = average - value;
			if (distance < 0) distance = -distance;
			result += distance;
		}
		result /= dataset.length;
		return result;
	}

	@Override
	public boolean validate() {
		if (dataset.length == 0) { 
			this.setErrorMessage("Dataset empty");
			return false; 
		}
		return true;
	}

	@Override
	public String parse(String input, String expression) {
		// TODO Auto-generated method stub
		return null;
	}

}
