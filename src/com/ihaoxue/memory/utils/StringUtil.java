package com.ihaoxue.memory.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public static boolean isNotStringEmpty(String temp) {
		if (temp == null || temp.equals("")) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isEmailAddress(String emailAddress) {
		String strPattern = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		Pattern pattern = Pattern.compile(strPattern);
		Matcher m = pattern.matcher(emailAddress);
		if (m.matches()) {
			return true;
		}
		return false ;
	}
	
	public static boolean isStringEquals(String temp,String temp2){
		if (isNotStringEmpty(temp)&&isNotStringEmpty(temp2)) {
			if (temp.equals(temp2)) {
				return true ;
			}
			return false ;
		}
		return false ;
	}
	
	
}
