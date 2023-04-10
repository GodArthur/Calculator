package functions;

public class FunctionSinh extends Functions{

	FunctionXY helperXY;
	
	double euler = 2.7182818284590452353602874713527;
	int negative;
	int decimalCount;
	
	public FunctionSinh() {
		this.x =0;
		this.totalVars = 1;
		this.varsInputed = 0;
		this.negative=1;
		this.decimalCount=0;
		this.helperXY = new FunctionXY();
	}
	
	
	
	@Override
	public double compute() {
		this.helperXY.setX(euler);
		this.helperXY.setY(this.x);
		double result1 = this.helperXY.compute();
		
		this.helperXY.setX(euler);
		this.helperXY.setY(-this.x);
		double result2 = this.helperXY.compute();
		
		return negative*(result1-result2)/2;
	}

	@Override
	public boolean validate() {
		return true;
	}

	@Override
	public String parse(String input, String expression) {
		// TODO Auto-generated method stub
		
		StringBuilder exprBuilder = new StringBuilder(expression);
		
		if(input.equals("(-)")) {
			input = "-";
		}
		
		if (exprBuilder.toString().equalsIgnoreCase("sinh")) {
			exprBuilder.append("("+input+")");
			if(input.equals("-")) {
				negative=negative*-1;
				return exprBuilder.toString();
			}
			this.x = Double.parseDouble(input);
		}
		
		
		else {
			if(input.equals("-")) {
				exprBuilder.replace(4, 5, "("+input);
				negative=negative*-1;
				return exprBuilder.toString();
			}
			
			
			else if(input.equalsIgnoreCase(".")) {
				exprBuilder.replace(exprBuilder.length()-1, exprBuilder.length(), input+")");
				
			}
			
			else if(expression.contains(".")) {
				++decimalCount;
				this.helperXY.setX(10);
				this.helperXY.setY(decimalCount);
				exprBuilder.replace(exprBuilder.length()-1, exprBuilder.length(), input+")");
				this.x = x + Double.parseDouble(input)/(this.helperXY.compute());
			}
			
			else {
				exprBuilder.replace(exprBuilder.length()-1, exprBuilder.length(), input+")");
				this.x = x*10 + Double.parseDouble(input);
			
			}
		}
		
		return exprBuilder.toString();
	}

}
