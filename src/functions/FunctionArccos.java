package functions;

public class FunctionArccos extends Functions{
FunctionXY helperXY;
	
	int negative;
	int decimalCount;
	
	public FunctionArccos() {
		this.x =0;
		this.totalVars = 1;
		this.varsInputed = 0;
		this.negative=1;
		this.decimalCount=0;
		this.helperXY = new FunctionXY();
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
		// TODO Auto-generated method stub
		
		StringBuilder exprBuilder = new StringBuilder(expression);
		
		if(input.equals("(-)")) {
			input = "-";
			
		}
		
		if (exprBuilder.toString().equalsIgnoreCase("acos")) {
			exprBuilder.append("("+input+")");
			if(input.equals("-")) {
				negative = negative*-1;
				return exprBuilder.toString();
			}
			this.x = Double.parseDouble(input);
		}
		
		
		else {
			if(input.equals("-")) {
				exprBuilder.replace(4, 5, " "+input);
				negative = negative*-1;
				return exprBuilder.toString();
			}
			
			
			else if(input.equalsIgnoreCase(".")) {
				exprBuilder.replace(exprBuilder.length()-1, exprBuilder.length(), input+" ");
				
			}
			
			else if(expression.contains(".")) {
				++decimalCount;
				this.helperXY.setX(10);
				this.helperXY.setY(decimalCount);
				exprBuilder.replace(exprBuilder.length()-1, exprBuilder.length(), input+" ");
				this.x = x + Double.parseDouble(input)/(this.helperXY.compute());
			}
			
			else {
				exprBuilder.replace(exprBuilder.length()-1, exprBuilder.length(), input+" ");
				this.x = x*10 + Double.parseDouble(input);
			
			}
		}
		x =  x*negative;
		 System.out.println(x);
		 System.out.println(exprBuilder.toString());
		
		return exprBuilder.toString();
	}
	
}
