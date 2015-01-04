package com.generic.core.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

	/**
	 * Regex to check if it is numeric
	 * @param aString
	 * @return
	 */
	public static Boolean isNumeric(String aString) {
		Pattern pattern = Pattern.compile(UtilConstants.REGEX_IS_NOT_NUMERIC);
		Matcher matcher = pattern.matcher(aString);
		while(matcher.find(0)){
			return false;
		}
		return true;
	}
	/**
	 * Check if a string length is less than length.
	 * @param aString
	 * @param length
	 * @return
	 */
	public static Boolean stringLengthLessThen(String aString, int length) {
		return numberLessThan(aString.length(), length);
	}
	/**
	 * Can be used to check the if the first number is less than the max number.
	 * @param first
	 * @param second
	 * @return
	 */
	public static Boolean numberLessThan(int first, int second) {
		return first <= second ? true : false;
	}
	
	public static void main(String[] args) {
		System.out.println(isNumeric("123"));
	}
	
}
