package functions;

public class FunctionArccos extends Functions{

	public FunctionArccos(){
		this.x = 0;
		this.varsInputed = 0;
		this.totalVars = 1;
	}
	
	@Override
	public double compute() {
		validate();
		
		double result=0;	
		// Regular valid cases
		result = Math.acos(x);
		
		return result;
	}

	@Override
	public boolean validate() {
		// error conditions
				if (x < -1 || x > 1) {
					this.setErrorMessage("Undefined");
					return false;
				}
				return true;
	}

	@Override
	public String parse(String input, String expression) {
StringBuilder exprBuilder = new StringBuilder(expression);
		
		// Enter X
		 if (this.varsInputed ==0) {
			exprBuilder.replace(exprBuilder.length()-1, exprBuilder.length(), input );
			this.x = Double.parseDouble(input);
		}
		
		
		return exprBuilder.toString();
	}
	
}
