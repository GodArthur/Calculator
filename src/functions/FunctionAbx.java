package functions;

import misc.StringHelper;

public class FunctionAbx extends Functions {
	FunctionXY helperXY;

	public FunctionAbx() {
		this.a = 0;
		this.b = 0;
		this.x = 0;
		this.varsInputed = 0;
		this.totalVars = 3;
		this.helperXY = new FunctionXY();
	}

	public FunctionAbx(double a, double b, double x) {
		this.a = a;
		this.b = b;
		this.x = x;
		this.varsInputed = 0;
		this.totalVars = 3;
		this.helperXY = new FunctionXY();
	}

	public double compute() {

		this.helperXY.setX(this.b);
		this.helperXY.setY(this.x);
		double result = this.helperXY.compute();
		return a * result;
	}

	@Override
	public boolean validate() {

		return true;
	}
	
	@Override
	public String parse(String input, String expression) {
		StringBuilder exprBuilder = new StringBuilder(expression);
		if(input.equals("(-)")) {
			input = "-";
		}
		
		// Enter First digit for a
		if (exprBuilder.toString().equalsIgnoreCase("ab\u02e3")) {
			exprBuilder.replace(0, exprBuilder.length(), input);
			exprBuilder.append("\u22C5"+"b\u02e3"); //u22C5 is unicode for middle "." (for multiplication)
			if(input.equals("-")) {
				return exprBuilder.toString();
			}
			this.a = Double.parseDouble(input);
			System.out.println("A: "+this.a);

		}
		
		// Enter remaining digits for a 
		else if (this.varsInputed ==0) {

			exprBuilder.replace(exprBuilder.length()-3, exprBuilder.length(), input);
			exprBuilder.append("\u22C5"+"b\u02e3");
			if(input.equals("-")) {
				return exprBuilder.toString();
			}
			this.a = Double.parseDouble(exprBuilder.substring(0 , exprBuilder.indexOf("\u22C5")));
			System.out.println("A: "+this.a);
		}
		
		// Enter First digit for b
		if (this.varsInputed == 1 && expression.contains("b")) {
			exprBuilder.replace(exprBuilder.length()-2, exprBuilder.length(), input);
			exprBuilder.append("\u02e3"); //unicode for superscript "x"
			if(input.equals("-")) {
				return exprBuilder.toString();
			}
			this.b = Double.parseDouble(input);
			System.out.println("B: "+this.b);

		}
		
		//Enter remaining digits for b
		else if (this.varsInputed ==1) {
			exprBuilder.replace(exprBuilder.length()-1, exprBuilder.length(), input);
			exprBuilder.append("\u02e3");
			if(input.equals("-")) {
				return exprBuilder.toString();
			}
			this.b = Double.parseDouble(exprBuilder.substring(exprBuilder.indexOf("\u22C5")+1, exprBuilder.toString().indexOf("\u02e3")));
			System.out.println("B: "+this.b);
		}

		// Enter First digit for x
		if (this.varsInputed == 2 && expression.contains("\u02e3")) {
			if (input.equals("-")) {
				exprBuilder.replace(exprBuilder.length() - 1, exprBuilder.length(), "\u207b"); // unicode for superscript "-"
				return exprBuilder.toString();
			} else {
				exprBuilder.replace(exprBuilder.length() - 1, exprBuilder.length(), StringHelper.superscript(input));
				this.x = Double.parseDouble(input);
				System.out.println("X: " + this.x);
			}
		}

		// Enter remaining digits for x
		else if (this.varsInputed == 2) {
			if(StringHelper.isDecimalSymbol(input)) {
				exprBuilder.append("\u00b7");
			} else {
				exprBuilder.append(StringHelper.superscript(input));
				String xSuperscriptValue = exprBuilder.substring(getFirstSuperscriptNumber(expression), exprBuilder.length());
				this.x = Double.parseDouble(StringHelper.undoSuperscript(xSuperscriptValue));
				System.out.println("X: "+this.x);
			}
		}
		return exprBuilder.toString();
	}
	
	private int getFirstSuperscriptNumber(String expression) {
		int index = 0;
		for(int i = 0; i < expression.length(); i++) {
			if(StringHelper.isSuperscriptNumber(String.valueOf(expression.charAt(i)))) {
				index = i;
				break;
			}
		}
		return index;
	}

}