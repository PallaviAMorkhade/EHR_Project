package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.MenuMaster;
import com.hms.indus.bo.UserMaster;
import com.hms.indus.bo.UserTypeMaster;
import com.hms.indus.dao.UserTypeMasterDao;
import com.hms.indus.service.UserTypeService;

@Service
public class UserTypeMasterServiceImpl implements UserTypeService{
	
	@Autowired
	UserTypeMasterDao userTypeMasterDao;

	@Override
	@Transactional
	public List<UserTypeMaster> getListOfUserTypeMaster() {
		return userTypeMasterDao.getListOfUserTypeMaster();
	}

	@Override
	@Transactional
	public String saveUserMaster(UserMaster userMaster) {
		return userTypeMasterDao.saveUserMaster(userMaster);
	}

	@Override
	@Transactional
	public List<UserMaster> getListOfUserMaster(Integer userTypeId) {
		return userTypeMasterDao.getListOfUserMaster(userTypeId);
	}

	@Override
	@Transactional
	public String updateUserMaster(UserMaster userMaster) {
		return userTypeMasterDao.updateUserMaster(userMaster);
	}

	@Override
	@Transactional
	public String deleteUserMaster(UserMaster userMaster) {
		return userTypeMasterDao.deleteUserMaster(userMaster);
	}

	@Override
	@Transactional
	public UserMaster getUserBuUserId(int userId) {
		return userTypeMasterDao.getUserBuUserId(userId);
	}

	@Override
	@Transactional
	public String setLoginStatusUserMaster(UserMaster userMaster) {
		return userTypeMasterDao.setLoginStatusUserMaster(userMaster);
	}

	@Override
	@Transactional
	public List<MenuMaster> getListOfMenuMaster() {
		return userTypeMasterDao.getListOfMenuMaster();
	}

	@Override
	@Transactional
	public List<UserMaster> autoSuggestionsUsers(String letter) {
		return userTypeMasterDao.autoSuggestionsUsers(letter);
	}

	@Override
	@Transactional
	public UserMaster getUserByUserId(Integer userId) {
		return userTypeMasterDao.getUserBuUserId(userId);
	}

	@Override
	@Transactional
	public String saveUserAccess(JSONObject object) {
		return userTypeMasterDao.saveUserAccess(object);
	}

	@Override
	@Transactional
	public JSONObject getUserAccessByUserId(Integer userId) {
		return userTypeMasterDao.getUserAccessByUserId(userId);
	}

	@Override
	@Transactional
	public Boolean changePasswordUser(UserMaster userMaster) {
		return userTypeMasterDao.changePasswordUser(userMaster);
	}

}
