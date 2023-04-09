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
	public double compute() {
		double result=0;
		if(validate()) {

		// special case(s)
		if (x == 1) {result = 0;}
		
		// Regular valid cases
		result = logarithmic(x,b);
		double realResult = Math.log(this.x)/Math.log(this.b);
//		double result2 = logarithmic2(x)/logarithmic2(b);
		
		System.out.println("Method divison and nearest power: " + result);
//		System.out.println("Method series: " + result2);
		System.out.println("Real result: " + realResult);
		}
		return result;
	}

	private double logarithmic(double x, double b) {
		double result = 0;
		int n;
		ArrayList<String> resultArr = new ArrayList<>();
		double interimX = x;
		String resultStr="";
		
		for(int i = 0; i<=4; i++) {
			n = closestPower(interimX,b);	
			resultArr.add(Integer.toString(n));
			
			if(i ==0) {
				resultArr.add(".");
			}
			// Divide by b exponent nearest power n
			interimX = interimX/exponent(b,n);
			
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
	
//	private double logarithmic2(double x) {
//		double result = 0;
//		double x1 = (x-1)/(x+1);
//		double series = 0;
//		
//		for(int i=1; i<=101; i+=2) {
//			series+= exponent(x1,i)/i;
//		}
//		
//		return series*2;
//	}

	private double exponent(double x,double y) {
		this.helperXY.setX(x);
		this.helperXY.setY(y);
		double result = this.helperXY.compute();
		return result;
	}
	
	@Override
	public boolean validate() {
		
		// Not enough variable entered
		if (varsInputed < totalVars-1) {
			this.setErrorMessage("Insufficient variables entered");
			return false;}
		
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
				this.b = b*10 + Double.parseDouble(input);}
		}
		
		return exprBuilder.toString();
	}

	
}
