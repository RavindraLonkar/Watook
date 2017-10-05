package com.watook.utils;

public class CommonUtilities {

	public static Boolean ValidateIntegerData(String DataValue) {
		Boolean checkFlag = true;
		try {
			if (DataValue.equals("")) {
				checkFlag = false;
			} else {
				Integer.parseInt(DataValue);
			}
		} catch (Exception e) {
			checkFlag = false;
		}
		return checkFlag;
	}
}
