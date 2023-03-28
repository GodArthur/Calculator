package functions;

public class FunctionAbx extends Functions{
// Instances all exist in Superclass Function already, 
	//Also the superclass as getters and setters for the variables
	// but they are type double so it gives error here, you can override or just return a double
	
//	private int a;
//	private int b;
//	private int x;
	
	
	public FunctionAbx(){
		a = 0;
		b = 0;
		x = 0;
	}
	
	public FunctionAbx(int a, int b, int x){
		this.a = a;
		this.b = b;
		this.x = x;
	}
	
	public int getA() {
		return this.a;
	}
	public int getB() {
		return this.b;
	}
	public int getX() {
		return this.x;
	}
	
	public void setA(int a) {
		this.a = a;
	}
	public void setB(int b) {
		this.b = b;
	}
	public void setX(int x) {
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
}
