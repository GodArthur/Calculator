package functions;

import misc.StringHelper;

public class FunctionAbx extends Functions {

	public FunctionAbx() {
		this.a = 0;
		this.b = 0;
		this.x = 0;
		this.varsInputed = 0;
		this.totalVars = 3;
	}

	public FunctionAbx(double a, double b, double x) {
		this.a = a;
		this.b = b;
		this.x = x;
		this.varsInputed = 0;
		this.totalVars = 3;
	}

	public double compute() {
		validate();
		
		double powerResult = this.b;
		for(double i = 0; i < this.x -1; i++) {
			powerResult *= this.b;
		}
		return a * powerResult;
	}

	@Override
	public boolean validate() {

		return true;
	}
	
	@Override
	public String parse(String input, String expression) {
		StringBuilder exprBuilder = new StringBuilder(expression);

		
		// Enter First digit for a
		if (exprBuilder.toString().equalsIgnoreCase("ab\u02e3")) {
			exprBuilder.replace(0, exprBuilder.length(), input);
			exprBuilder.append("\u22C5"+"b\u02e3");
			this.a = Double.parseDouble(input);
			System.out.println("A: "+this.a);

		}
		
		// Enter remaining digits for a 
		else if (this.varsInputed ==0) {

			exprBuilder.replace(exprBuilder.length()-3, exprBuilder.length(), input);
			exprBuilder.append("\u22C5"+"b\u02e3");
			this.a = Double.parseDouble(exprBuilder.substring(0 , exprBuilder.indexOf("\u22C5")));
			System.out.println("A: "+this.a);
		}
		
		// Enter First digit for b
		if (this.varsInputed == 1 && expression.contains("b")) {
			exprBuilder.replace(exprBuilder.length()-2, exprBuilder.length(), input);
			exprBuilder.append("\u02e3");
			this.b = Double.parseDouble(input);
			System.out.println("B: "+this.b);

		}
		
		//Enter remaining digits for b
		else if (this.varsInputed ==1) {
			exprBuilder.replace(exprBuilder.length()-1, exprBuilder.length(), input);
			exprBuilder.append("\u02e3");
			this.b = Double.parseDouble(exprBuilder.substring(exprBuilder.indexOf("\u22C5")+1, exprBuilder.toString().indexOf("\u02e3")));
			System.out.println("B: "+this.b);
		}

		// Enter First digit for x
		if (this.varsInputed == 2 && expression.contains("\u02e3")) {
			exprBuilder.replace(exprBuilder.length()-1, exprBuilder.length(), StringHelper.superscript(input));
			this.x = Double.parseDouble(input);
			System.out.println("X: "+this.x);

		}

		// Enter remaining digits for x
		else if (this.varsInputed == 2) {
			exprBuilder.append(StringHelper.superscript(input));
			this.x = x * 10 + Double.parseDouble(input);
			System.out.println("X: "+this.x);

		}
		
		return exprBuilder.toString();
	}

}