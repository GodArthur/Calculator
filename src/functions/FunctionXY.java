package functions;
import misc.StringHelper;
import misc.StringHelper.*;
public class FunctionXY extends Functions{

	
	public FunctionXY() {
		
		this.x = 0;
		this.y = 0;
		this.varsInputed = 0;
		this.totalVars = 2;
	
	}
	

	
	public double compute() {
		
		double value = 1;
		
		double absY = abs(y);
		for (int i = 0; i < absY; i++) {
			
			value = value * this.x;
		}
		
		if (absY != y) {
			return 1/value;
		}
		return value;
	}
	
	//returns absolute value of a number
	private double abs(double num) {
		
		if (num < 0) {
			
			return -num;
		}
		
		return num;
	}
	
	public boolean validate() {
		
		return false;
	}
	
	public String parse(String input, String expression) {
		
		System.out.println(varsInputed);
		System.out.println(expression);
		StringBuilder expBuilder = new StringBuilder(expression);
		
		if(expBuilder.toString().equals("x^y")) {
			expBuilder.replace(0, expBuilder.length(), input);
			expBuilder.append("^y");
			this.x = Double.parseDouble(input);
		}
		
		else if (this.varsInputed == 0){
			expBuilder.replace(expBuilder.length() - 2, expBuilder.length(), input);
			expBuilder.append("^y");
			this.x = x * 10 + Double.parseDouble(input);
		}
		// Enter First digit for y
		if (this.varsInputed ==1 && expression.contains("y")) {
			expBuilder.replace(expBuilder.length()-1, expBuilder.length(), StringHelper.superscript(input));
			this.y = Double.parseDouble(input);
		}
		
		//Enter remaining digits for y
		else if (this.varsInputed ==1) {
			expBuilder.append(StringHelper.superscript(input));
			this.y = y*10 + Double.parseDouble(input);
		}
		
		

		return expBuilder.toString();
	}
	
	
}
