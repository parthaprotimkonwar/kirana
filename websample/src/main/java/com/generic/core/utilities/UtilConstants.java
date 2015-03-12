package com.generic.core.utilities;

/**
 * Constants for Utility Services are specified
 * @author pkonwar
 */
public interface UtilConstants {

	String DELIMETER_BAR = "\\|";
	String TWO_DOTS = "..";
	
	String LOCATION_SUB_AREA_PREFIX = "LOC_SUB_AREA";
	
	String TRANSACTION_PREFIX = "TXN_";
	String GUEST_USER_PREFIX = "GUEST_";
	String USER_PREFIX = "USER_";
	
	/**
	 * Regex Validation
	 */
	String REGEX_IS_NOT_NUMERIC = "[^0-9]+";
	//abc1@gmail.com
	
	//(?!.*\\.{2}) : Negative lookahead operator for not accepting two consecutive dots
	String REGEX_IS_EMAIL = "(?!.*\\.{2})[a-zA-Z0-9-_~!$&'()*+,;=:.]+[@][a-zA-Z0-9]+[.][.a-zA-Z]*[a-zA-Z]+";
	
	String CITY_PREFIX = "LOC_CITY_";
	String AREA_PREFIX = "LOC_AREA_";
	/*String CITY_PREFIX = "CTY_";
	String AREA_PREFIX = "ARE_";*/
	String LANDMARK_PREFIX = "LMK_";
	
	String CITY_ID_NAME_DELIMETER = "-";
	
	String CITY_NAME = "CITY_NAME";
	String CITY_ID = "CITY_ID";
	
	
}
