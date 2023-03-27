/**
 * Class to calculate log of base b of x.
 * Author: Marie-Josee Castellanos
 * @param b : Base of the logarithmic function
 * @param x 
 */	
package functions;

public class FunctionLogBX extends Functions{
	
	FunctionLogBX(){
		this.b = 0;
		this.x = 0;
	}

	FunctionLogBX(double b, double x){
		this.b = b;
		this.x = x;
	}
	
	@Override
	public double compute() {
		double result=0;
		
		// special case(s)
		if (x == 1) {result = 0;}
		
		// Regular valid cases
		result = Math.log(x)/Math.log(b);
		return result;
	}

	@Override
	public boolean validate() {
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
	
}
