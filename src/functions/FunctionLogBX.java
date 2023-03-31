/**
 * Class to calculate log of base b of x.
 * Author: Marie-Josee Castellanos
 * @param b : Base of the logarithmic function
 * @param x 
 */	
package functions;

import misc.StringHelper;

public class FunctionLogBX extends Functions{
	
	public FunctionLogBX(){
		this.b = 0;
		this.x = 0;
		this.varsInputed = 0;
		this.totalVars = 2;
	}
	
	@Override
	public double compute() {
		validate();
		
		double result=0;
		
		// special case(s)
		if (x == 1) {result = 0;}
		
		// Regular valid cases
		result = Math.log(x)/Math.log(b);
		
		return result;
	}

	@Override
	public boolean validate() {
		
		// Not enough variable entered
		if (varsInputed < totalVars)
			this.setErrorMessage("Insufficient variables entered");
		
		// error conditions
		if (x <= 0) {
			this.setErrorMessage("Undefined");
			return false;
		}
		if (b <= 1) {
			this.setErrorMessage("Undefined");
			return false;
			}
		
		return true;
	}

	@Override
	public String parse(String input, String expression) {
		StringBuilder exprBuilder = new StringBuilder(expression);
		
		// Enter First digit for B
		if (exprBuilder.toString().equalsIgnoreCase("logbx")) {
			exprBuilder.replace(exprBuilder.length()-2, exprBuilder.length(), StringHelper.subscript(input));
			exprBuilder.append("x");
			this.b = Double.parseDouble(input);
		}
		
		// Enter remaining digits for B //Genyes
		else if (this.varsInputed ==0) {
			exprBuilder.replace(exprBuilder.length()-1, exprBuilder.length(), StringHelper.subscript(input));
			exprBuilder.append("x");
			this.b = b*10 + Double.parseDouble(input);
		}
		
		// Enter First digit for X
		if (this.varsInputed == 1 && expression.contains("x")) {
			exprBuilder.replace(exprBuilder.length()-1, exprBuilder.length(), input );
			this.x = Double.parseDouble(input);
		}
		
		//Enter remaining digits for X
		else if (this.varsInputed ==1) {
			exprBuilder.append(input);
			this.x = x*10 + Double.parseDouble(input);
		}
		
		return exprBuilder.toString();
	}

	
}
