package functions;

public class FunctionSinh extends Functions{

	public FunctionSinh() {
		this.x =0;
		this.totalVars = 1;
		this.varsInputed = 0;
	}
	
	
	
	
	@Override
	public double compute() {
		return Math.sinh(x);
	}

	@Override
	public boolean validate() {
		return true;
	}

	@Override
	public String parse(String input, String expression) {
		// TODO Auto-generated method stub
		
		StringBuilder exprBuilder = new StringBuilder(expression);
		
		if (exprBuilder.toString().equalsIgnoreCase("sinh")) {
			exprBuilder.append("("+input+")");
			this.x = Double.parseDouble(input);
		}
		
		else {
			exprBuilder.replace(exprBuilder.length()-1, exprBuilder.length(), input+")");
			this.x = x*10 + Double.parseDouble(input);
		}
		
		return exprBuilder.toString();
	}

}
