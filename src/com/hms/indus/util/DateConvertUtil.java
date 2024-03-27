package com.hms.indus.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConvertUtil {
	
	public static String convertDate(String dateConvert) {
		String convertedDate="";
		if(dateConvert!=null && dateConvert!=""){
		//dateConvert = dateConvert.replaceAll("-", "/");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateInString = dateConvert;
		try {
			   Date date = formatter.parse(dateInString);
			   DateFormat apiDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			   convertedDate= apiDateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		}
		return convertedDate;
	}
	
	public static String convertDateToDBFormat(String dateConvert) {
		String convertedDate=null;
		if(dateConvert!=null && dateConvert!=""){
		//dateConvert = dateConvert.replaceAll("-", "/");
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String dateInString = dateConvert;
		try {
			   Date date = formatter.parse(dateInString);
			   DateFormat apiDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			   convertedDate= apiDateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		}
		return convertedDate;
	}
	
	public static String convertDateTime(String dateConvert) {
		String convertedDate="";
		if(dateConvert!=null && dateConvert!=""){
		//dateConvert = dateConvert.replaceAll("-", "/");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateInString = dateConvert;
		try {
			   Date date = formatter.parse(dateInString);
			   DateFormat apiDateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
			   convertedDate= apiDateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		}
		return convertedDate;
	}
	
	public static String convertDateDMY(String dateConvert) {
		String convertedDate="";
		if(dateConvert!=null && dateConvert!=""){
		//dateConvert = dateConvert.replaceAll("-", "/");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateInString = dateConvert;
		try {
			   Date date = formatter.parse(dateInString);
			   DateFormat apiDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			   convertedDate= apiDateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		}
		return convertedDate;
	}

}
