package com.hms.indus.util;

public class Utility {
	
	public static String leftRightTrim(String text){
		//right trim
		text = text.replaceAll("\\s+$", "");
		//left trim
		text = text.replaceAll("^\\s+", "");
		return text;
	}

}
