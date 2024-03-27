package com.hms.indus.dao;

import javax.servlet.http.HttpServletRequest;

import com.hms.indus.bo.ClientMaster;
import com.hms.indus.bo.Users;

public interface LoginDao {

	boolean checkAuthentication(String userName, String password, HttpServletRequest request);

	boolean isAvailEmailId(String emailid);

	boolean resetPassword(ClientMaster clientMaster);
	
	boolean isAvailUserName(String username);
	
	Users findByUserName(String username);

}
