package com.hms.indus.service.serviceimpl;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.dao.HealthFeedDao;
import com.hms.indus.service.HealthFeedService;

@Service
public class HealthFeedServiceImpl implements HealthFeedService{
	
	@Autowired
	HealthFeedDao healthFeedDao;

	@Override
	@Transactional
	public JSONArray listOfHealthFeedMaster() {
		return healthFeedDao.listOfHealthFeedMaster();
	}

	@Override
	@Transactional
	public void updateHealthFeedMaster(JSONObject object) {
		healthFeedDao.updateHealthFeedMaster(object);
	}

	@Override
	@Transactional
	public String saveHealthFeedMaster(JSONObject object) {
		return healthFeedDao.saveHealthFeedMaster(object);
	}

	@Override
	@Transactional
	public void deleteHealthFeedMaster(JSONObject object) {
		healthFeedDao.deleteHealthFeedMaster(object);
	}

	@Override
	@Transactional
	public JSONObject getHealthFeedMasterByHealthFeedId(
			Integer healthFeedMasterId) {
		return healthFeedDao.getHealthFeedMasterByHealthFeedId(healthFeedMasterId);
	}

}
