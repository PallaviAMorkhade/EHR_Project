package com.hms.indus.util;

import org.mindrot.jbcrypt.BCrypt;


public class Security {
	public static String encrypt(String text) {
		String encryptedString = "";
		try {
			encryptedString = BCrypt.hashpw(text.trim(), BCrypt.gensalt());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;
	}

	public static String decrypt(String text) {
		String decryptedString = "";
		return decryptedString;
	}
	
	public static Boolean checkEquality(String plainText,String hash){
		
		if(BCrypt.checkpw(plainText, hash)){
			return true;	
		}
		else{
			return false;
		}		
	}
}
