package com.watook.utils;

import java.lang.reflect.Field;

import com.google.gson.FieldNamingStrategy;

public class FieldNamingPolicies {
	
	public static FieldNamingStrategy lowerCaseFields() {
		return new FieldNamingStrategy() {
			public String translateName(Field f) {
				return f.getName().toLowerCase();
			}
		};
	}
}
