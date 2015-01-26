package com.generic.core.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import com.generic.rest.constants.Constants;
import com.generic.rest.constants.DeliveryDate;
import com.generic.rest.constants.SessionAttributes;
import com.generic.rest.constants.SessionStoreConstants;

/**
 * Utility Class
 * @author pkonwar
 */
public class Util {

	
	public static int max(int firstNo, int secondNo) {
		return firstNo > secondNo ? firstNo : secondNo;
	}
	
	public static int min(int firstNo, int secondNo) {
		return firstNo < secondNo ? firstNo : secondNo;
	}
	
	public static int integerPartOfString(String aString) {
		return Integer.parseInt(aString.split("[a-zA-Z]+")[0]);
	}
	
	public static String stringPartOfIntegerString(String aString) {
		return aString.replaceAll("[0-9]", "");
	}
	
	public static String chooseFirstIfNotNull(String aString, String otherString) {
		return aString == null ? otherString : aString;
	}
	
	
	public static String[] splitStringsToArray(String delimeter , String data) {
		
		if(isNullAndEmpty(data))
			return null;
		
		return data.split(delimeter);
	}
	
	public static Boolean isNullAndEmpty(String...strings) {
		
		for(String aString : strings) {
			if(aString == null || aString.trim().length() == 0)
				return true;
		}
		return false;
	}
	
	public static Boolean allValuesAreNullAndEmpty(String...strings) {
		
		for(String aString : strings) {
			if(aString != null && aString.trim().length() != 0)
				return false;
		}
		return true;
	}
	
	public static Boolean userLoggedin(HttpSession session) {
		if(session.getAttribute(SessionStoreConstants.USER_LOGGED_IN) == null ||
				Boolean.valueOf((boolean) session.getAttribute(SessionStoreConstants.USER_LOGGED_IN)) == false)
			return false;
		return true;
	}
	
	
	public static SessionAttributes getSessionStoreHouse(HttpSession session) {
			return session.getAttribute(Constants.SESSION_STOREHOUSE) == null ?
				new SessionAttributes() : (SessionAttributes)session.getAttribute(Constants.SESSION_STOREHOUSE);
				
	}
	
	public static void saveSessionStoreHouse(HttpSession session, SessionAttributes sessionAttributes) {
		session.setAttribute(Constants.SESSION_STOREHOUSE, sessionAttributes);
	}
	
	
	public static String generateUserId(String userType) {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
		return userType + sdf.format(d);
	} 
	
	public static Date getPreferredDeliveryDate(String date) {
		
		DeliveryDate deliveryDate = DeliveryDate.valueOf(date);

		switch (deliveryDate) {
		case TODAY:
			return new Date();
		case TOMORROW:
			return getNextDate(1);
		default:
			break;
		}
		return null;
	}
	
	private static Date getNextDate(int numOfDays) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, numOfDays);
		return cal.getTime();
	}
	
	/*public static void main(String[] args) {
		String[] arr = splitStringsToArray("\\|", "partha|anurag|rajith");
		
		System.out.println(arr);
	}*/
}
