package misc;

import java.util.Arrays;

    public class StringHelper {

	public static final String[] OPERATORS = {"+", "\u2014", "*", "/"};
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
	
	public static String undoSuperscript(String str) {
	    str = str.replaceAll("⁰", "0");
	    str = str.replaceAll("¹", "1");
	    str = str.replaceAll("²", "2");
	    str = str.replaceAll("³", "3");
	    str = str.replaceAll("⁴", "4");
	    str = str.replaceAll("⁵", "5");
	    str = str.replaceAll("⁶", "6");
	    str = str.replaceAll("⁷", "7");
	    str = str.replaceAll("⁸", "8");
	    str = str.replaceAll("⁹", "9");
	    str = str.replaceAll("\u00b7", "."); //unicode for superscript "."     
	    str = str.replaceAll("\u207b", "-"); //unicode for superscript "-"    
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
	
	public static boolean isDecimalSymbol(String str) {
		return str.equals(".");
	}
	
	public static boolean isSuperscriptNumber(String sup) {
		String[] symbols = {"\u207b", "⁰", "¹", "²", "³", "⁴", "⁵", "⁶", "⁷", "⁸", "⁹"};
		for(String s : symbols) {
			if(s.equals(sup)) {
				return true;
			}
		}
		return false;
	}
}