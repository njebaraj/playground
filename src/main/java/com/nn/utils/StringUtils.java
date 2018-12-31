package com.nn.utils;

public class StringUtils {

	private StringUtils() {

	}

	/**
	 * Lower case the first letter of a given string. Eg. Replace CustomerName with
	 * customerName
	 * 
	 * @param name
	 * @return
	 */
	public static String lowerCaseFirstLetter(String name) {
		if (name != null && !name.isEmpty()) {
			return name.substring(0, 1).toLowerCase() + name.substring(1);
		}

		return "";
	}

	/**
	 * Enclose a given string with double quotes
	 * 
	 * Eg. Returns "John" for the name John and "" for a null string
	 * 
	 * @param name
	 * @return
	 */
	public static String encloseStringWithDoubleQuotes(String name) {
		if (name != null) {
			return "\"" + name + "\"";
		}

		return "\"\"";
	}
}
