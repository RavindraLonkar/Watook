package com.watook.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

	public static String CurrentDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date();
		return formatter.format(date);
	}

	public static int getAge(String dob) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar outDOB = Calendar.getInstance();
		outDOB.setTime(sdf.parse(dob));

		Calendar today = Calendar.getInstance();
		int curYear = today.get(Calendar.YEAR);
		int dobYear = outDOB.get(Calendar.YEAR);
		int age = curYear - dobYear;
		// if dob is month or day is behind today's month or day
		// reduce age by 1
		int curMonth = today.get(Calendar.MONTH);
		int dobMonth = outDOB.get(Calendar.MONTH);
		if (dobMonth > curMonth) { // this year can't be counted!
			age--;
		} else if (dobMonth == curMonth) { // same month? check for day
			int curDay = today.get(Calendar.DAY_OF_MONTH);
			int dobDay = outDOB.get(Calendar.DAY_OF_MONTH);
			if (dobDay > curDay) { // this year can't be counted!
				age--;
			}
		}
		return age;
	}
}
