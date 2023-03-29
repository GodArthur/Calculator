package functions;

import misc.StringHelper;

public class FunctionAbx extends Functions {

	public FunctionAbx() {
		this.a = 0;
		this.b = 0;
		this.x = 0;
	}

	public FunctionAbx(int a, int b, int x) {
		this.a = a;
		this.b = b;
		this.x = x;
	}

	public double compute() {
		return a * (Math.pow(b, x));
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}

	public String parse(String input, String expression) {
		
		StringBuilder exprBuilder = new StringBuilder(expression);
		  if(StringHelper.checkIfOperatorClicked(input) &&
		  StringHelper.checkIfFunctionClicked(input) && varsInputed < 3) { return "-1";
		  }
		  
		  if(varsInputed == 0) { 
			  String current = this.a == 0 ? input : this.a+input;
			  System.out.println("CURRENT: "+current);


			  System.out.println("setting A: "+input);
			  if(this.a == 0) {
				  exprBuilder.replace(expression.length()-3, expression.length()-2, input+"*");
				  System.out.println("exprBuilder1: "+exprBuilder);

			  } else {
				  exprBuilder.insert(expression.length()-3, current.toCharArray(), 0, 1);
				  System.out.println("exprBuilder2: "+exprBuilder);
			  }
			  this.setA(Integer.parseInt(current)); 

		  
		  } else if(varsInputed == 1) { 
			  System.out.println("setting B: "+input);
			  String current = this.b == 0 ? input : this.b+input;
			  this.setB(Integer.parseInt(current)); 
			  expression.replace("b", input);
		  
		  } else if(varsInputed == 2) { 
			  System.out.println("setting X: "+input);
			  String current = this.x == 0 ? input : this.x+input;
			  this.setX(Integer.parseInt(current)); 
			  expression.replace("x",StringHelper.superscript(input)); 
		  } else {
			  if(StringHelper.checkIfOperandsClicked(input)) { 
				  return expression; //skip 
			  	}
		  else { //isAbx = false; varsInputed = 0; //expressionToParse =
		  //expression.append(compute()); 
		  this.varsInputed = 0;
		  double result = compute();
		  String expressionToParse = expression.replace("abx", String.valueOf(result));
		  return expressionToParse; } }

	return "-1";
}
}
