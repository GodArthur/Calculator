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
		
		double result=Math.acos(x);
		
		
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
		

		//Enter First digit for X
		if (this.varsInputed == 0 && expression.contains("X")) {
			exprBuilder.replace(exprBuilder.length()-1, exprBuilder.length(), input);
			this.x = Double.parseDouble(input);
		}
		
		//Enter remaining digits for X
		else if (this.varsInputed ==0) {
			exprBuilder.append(input);
			if(!input.equalsIgnoreCase(".")) {
				if(expression.contains(".")) {
					this.x = x*10 + Double.parseDouble(input)/10;
				}
				else {
					this.x = x*10 + Double.parseDouble(input);
				}
			}
			
		}
		System.out.println(x);
		 System.out.println(exprBuilder.toString());
		
		
		return exprBuilder.toString();
	}
	
}

