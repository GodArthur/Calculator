package functions;

public class FunctionArccos extends Functions{
	public FunctionArccos(){
		this.x = 0;
		this.varsInputed = 0;
		this.totalVars = 1;
	}
	
	@Override
	public double compute() {
		if (!validate()) {
			return Double.NaN;
		}
		  double result;
		  double PI = 3.14159265359;
		  double EPSILON = 1e-9;
		  if (x == 1) {
	            return 0;
	        } else if (x == -1) {
	            return PI;
	        } 
	        double lower = 0;
	        double upper = PI;
	        while (true) {
	            double mid = (lower + upper) / 2;
	            if (Math.abs(Math.cos(mid) - x) <= EPSILON) {
	            	result = mid;
	               return result;
	            } else if (Math.cos(mid) > x) {
	                lower = mid;
	            } else {
	                upper = mid;
	            }
	        }
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
	if(input.equals("(-)")) {
		input = "-";
	}	

		//Enter First digit for X
		if (this.varsInputed == 0 && expression.contains("X")) {
			
			if(input.equals("-")) {
				
				exprBuilder.replace(exprBuilder.length()-1, exprBuilder.length(), input);
				return exprBuilder.toString();
			}
			else {
			exprBuilder.replace(exprBuilder.length()-1, exprBuilder.length(), input);
			this.x = Double.parseDouble(input);
			}
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
	  
