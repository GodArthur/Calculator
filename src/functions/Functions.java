package functions;

public abstract class Functions {
	
	public String type;
	public String errorMessage;
	protected int varsInputed; //the number of variables you inputed in the calculator (ex: ab^x -> 2*3^x means varsInputed = 2)
	protected int totalVars; //the total number of variables you need for your function
	protected double a;
	protected double b;
	protected double x;
	protected double y;
	
	Functions(){
		this.type = "";
		this.errorMessage = "";
	}
	
/**
 * Getters
 */	
	public String getType() {
		return type;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public double getA() {
		return this.a;
	}
	public double getB() {
		return this.b;
	}
	
	public double getX() {
		return this.x;
	}
	public double getY() {
		return this.y;
	}
	public double getVarsInputed() {
		return this.varsInputed;
	}
	public double getTotalVars() {
		return this.totalVars;
	}
	
/**
 * Setters
 */		
	public void setType(String type) {
		this.type = type;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public void setA(double a) {
		this.a = a;
	}
	public void setB(double b) {
		this.b = b;
	}
	public void setX(double x) {
		this.x = x;
	}
	public void setY(double y) {
		this.y = y;
	}
/**
 * Abstract method to be implemented in subclasses as to calculate the function defined in the subclass.
 * @return double value from the resulting computation.
 */
	public abstract double compute();
/**
 * 	Abstract method to be implemented in subclasses as to validate the values entered by the user.
 * @return boolean
 */
	public abstract boolean validate();
	
	public abstract String parse(String input, String expression);
}
