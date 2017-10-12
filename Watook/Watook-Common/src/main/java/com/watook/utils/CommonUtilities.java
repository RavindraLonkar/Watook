package com.watook.utils;

public class CommonUtilities {

	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static boolean isNumber(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
}
