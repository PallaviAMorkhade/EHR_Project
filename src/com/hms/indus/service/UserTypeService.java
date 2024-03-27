package com.hms.indus.service;

import java.util.List;

import org.json.simple.JSONObject;

import com.hms.indus.bo.MenuMaster;
import com.hms.indus.bo.UserMaster;
import com.hms.indus.bo.UserTypeMaster;

public interface UserTypeService {
	
	List<UserTypeMaster> getListOfUserTypeMaster();
	
	//User Master
	List<UserMaster> getListOfUserMaster(Integer userTypeId);
	
	String saveUserMaster(UserMaster userMaster);
	
	String updateUserMaster(UserMaster userMaster);
	
	String deleteUserMaster(UserMaster userMaster);
	
	UserMaster getUserBuUserId(int userId);
	
	String setLoginStatusUserMaster(UserMaster userMaster);

	List<MenuMaster> getListOfMenuMaster();
	
	List<UserMaster> autoSuggestionsUsers(String letter);

	UserMaster getUserByUserId(Integer userId);

	String saveUserAccess(JSONObject object);

	JSONObject getUserAccessByUserId(Integer userId);
	
	Boolean changePasswordUser(UserMaster userMaster);
}
