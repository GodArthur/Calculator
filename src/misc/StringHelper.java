package misc;

import java.util.Arrays;

    public class StringHelper {

	public static final String[] OPERATORS = {"+", "—", "*", "/"};
	public static final String[] OPERANDS = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};
	public static final String[] FUNCTIONS = {"ab\u02e3"};
	
	
	public static boolean checkIfOperatorClicked(String lastInput) {
		return (Arrays.asList(OPERATORS).contains(lastInput)) ? true : false;
	}
	public static boolean checkIfOperandsClicked(String lastInput) {
		return (Arrays.asList(OPERANDS).contains(lastInput)) ? true : false;
	}
	public static boolean checkIfFunctionClicked(String lastInput) {
		return (Arrays.asList(FUNCTIONS).contains(lastInput)) ? true : false;
	}

	public static String superscript(String str) {
	    str = str.replaceAll("0", "⁰");
	    str = str.replaceAll("1", "¹");
	    str = str.replaceAll("2", "²");
	    str = str.replaceAll("3", "³");
	    str = str.replaceAll("4", "⁴");
	    str = str.replaceAll("5", "⁵");
	    str = str.replaceAll("6", "⁶");
	    str = str.replaceAll("7", "⁷");
	    str = str.replaceAll("8", "⁸");
	    str = str.replaceAll("9", "⁹");  
	    str = str.replaceAll("Y", "ʸ");
	    return str;
	}

	public static String subscript(String str) {
	    str = str.replaceAll("0", "₀");
	    str = str.replaceAll("1", "₁");
	    str = str.replaceAll("2", "₂");
	    str = str.replaceAll("3", "₃");
	    str = str.replaceAll("4", "₄");
	    str = str.replaceAll("5", "₅");
	    str = str.replaceAll("6", "₆");
	    str = str.replaceAll("7", "₇");
	    str = str.replaceAll("8", "₈");
	    str = str.replaceAll("9", "₉");           
	    return str;
	}
}
