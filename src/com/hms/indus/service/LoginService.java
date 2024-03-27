package com.hms.indus.service;

import javax.servlet.http.HttpServletRequest;

import com.hms.indus.bo.ClientMaster;

public interface LoginService {

	boolean checkAuthentication(String userName, String password, HttpServletRequest request);

	boolean isAvailEmailId(String emailid);
	
	boolean resetPassword(ClientMaster clientMaster);

	boolean isAvailUserName(String username);
	
}
