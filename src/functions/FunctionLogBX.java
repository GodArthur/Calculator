/**
 * Class to calculate log of base b of x.
 * Author: Marie-Josee Castellanos
 * @param b : Base of the logarithmic function
 * @param x 
 */	
package functions;
import java.util.ArrayList;
import misc.StringHelper;
import functions.FunctionXY;

public class FunctionLogBX extends Functions{
	
	FunctionXY helperXY;
	
	public FunctionLogBX(){
		this.b = 0;
		this.x = 0;
		this.varsInputed = 0;
		this.totalVars = 2;
		this.helperXY = new FunctionXY();
	}
	
	
	@Override
	public boolean validate() {
		
		// Not enough variable entered
		if (varsInputed < totalVars-1) {
			this.setErrorMessage("Insufficient variables entered");
			return false;}
		
		// error conditions
		// The X cannot be 0 or negative
		if (x <= 0) {
			this.setErrorMessage("Undefined");
			return false;
		}
		// The base cannot be 0 or negative
		if (b <= 0) {
			this.setErrorMessage("Undefined");
			return false;
			}
		
		return true;
	}
	@Override
	public double compute() {
		double result=0;
		double resultB = 0;
		double resultX = 0;
		if(validate()) {

		// special case(s)
		if (x == 1) {result = 0;}
		
		// Regular valid cases
		if ((x <= 1) && (x > -1)) {
			resultX = logarithmicDecimal(x);
		}
		else
		resultX = logarithmic(x);
		
		if ((b <= 1) && (b > -1)) {
			resultB = logarithmicDecimal(b);
		}
		else
		resultB = logarithmic(b);
		
		result = resultX/resultB;
		double realResult = Math.log(this.x)/Math.log(this.b);
//		double result2 = logarithmic2(x)/logarithmic2(b);
		
		System.out.println("Method divison and nearest power: " + result);
		System.out.println("Real result: " + realResult);
		}
		
		// Format to 3 decimals
		result = formatDecimal(result);
		
		return result;
	}

	private double formatDecimal(double result) {
		
		String temp = String.format("%.3f",result);
		result = Double.parseDouble(temp);
		System.out.println(result);
		return result;
	}


	private double logarithmicDecimal(double x) {
		double result = x;
		double interimX = x-1;
		
		for(int i = 2; i<=6; i++) {
			double temp = exponent(interimX,i)/i;
			
			if(i%2==0) {
				result-=temp;
			}
			else
				result+=temp;
		}
		
		
		return result;
	}

	private double logarithmic(double x) {
		double result = 0;
		int n;
		double base = 10;
		ArrayList<String> resultArr = new ArrayList<>();
		double interimX = x;
		String resultStr="";
		
		for(int i = 0; i<=4; i++) {
			n = closestPower(interimX,base);	
			resultArr.add(Integer.toString(n));
			
			if(i ==0) {
				resultArr.add(".");
			}
			// Divide by b exponent nearest power n
			interimX = interimX/exponent(base,n);
			
			// Raise to 10
			interimX = exponent(interimX,10);
		}

		
		for(int j=0; j< resultArr.size();j++) {
			resultStr = resultStr.concat(resultArr.get(j));
		}
		
		result = Double.parseDouble(resultStr);
		return result;
	}

	private int closestPower(double x, double b) {
		int n = 0;
		double test = exponent(b,n);
		while(x >= test) {
			n +=1;
			test = exponent(b,n);
			if(x < test){
				n-=1; 
				break;}
		}
		return n;
	}
	
	
	private double exponent(double x,double y) {
		this.helperXY.setX(x);
		this.helperXY.setY(y);
		double result = this.helperXY.compute();
		return result;
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
		
		// Enter remaining digits for B
		else if (this.varsInputed ==0) {
			exprBuilder.replace(exprBuilder.length()-1, exprBuilder.length(), StringHelper.subscript(input));
			exprBuilder.append("x");
			if(!input.equalsIgnoreCase(".")) {
				this.b = b*10 + Double.parseDouble(input);}
		}
		
		// Enter First digit for X
		if (this.varsInputed == 1 && expression.contains("x")) {
			exprBuilder.replace(exprBuilder.length()-1, exprBuilder.length(), input );
			this.x = Double.parseDouble(input);
		}
		
		//Enter remaining digits for X
		else if (this.varsInputed ==1) {
			exprBuilder.append(input);
			if(!input.equalsIgnoreCase(".")) {
				this.x = x*10 + Double.parseDouble(input);}
		}
		
		return exprBuilder.toString();
	}

	
}
