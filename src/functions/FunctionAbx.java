package functions;

import misc.StringHelper;

public class FunctionAbx extends Functions{
// Instances all exist in Superclass Function already, 
	//Also the superclass as getters and setters for the variables
	// but they are type double so it gives error here, you can override or just return a double
	
//	private int a;
//	private int b;
//	private int x;
	
	
	public FunctionAbx(){
		this.a = 0;
		this.b = 0;
		this.x = 0;
	}
	
	public FunctionAbx(int a, int b, int x){
		this.a = a;
		this.b = b;
		this.x = x;
	}

	public double compute() {
		return a*(Math.pow(b, x));
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String parse(String input, String expression) {
		/*
		 * if(StringHelper.checkIfOperatorClicked(input) &&
		 * StringHelper.checkIfFunctionClicked(input) && varsInputed < 3) { return "-1";
		 * }
		 * 
		 * if(varsInputed == 0) { System.out.println("setting A: "+input);
		 * this.setA(Integer.parseInt(input)); expression.replace("a", input+"*");
		 * 
		 * } else if(varsInputed == 1) { System.out.println("setting B: "+input);
		 * 
		 * this.setB(Integer.parseInt(input)); expression.replace("b", input);
		 * 
		 * } else if(varsInputed == 2) { System.out.println("setting X: "+input);
		 * 
		 * this.setX(Integer.parseInt(input)); expression.replace("x",
		 * StringHelper.superscript(input)); } else {
		 * if(StringHelper.checkIfOperandsClicked(input)) { return expression; //skip }
		 * else { //isAbx = false; varsInputed = 0; //expressionToParse =
		 * expression.append(compute()); return false; } }
		 */
		return "";
	}
}
