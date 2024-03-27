package com.hms.indus.service.serviceimpl;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.dao.ActionDao;
import com.hms.indus.dao.FeedTypeDao;
import com.hms.indus.service.FeedTypeService;

@Service
public class FeedTypeServiceImpl implements FeedTypeService{
	
	@Autowired
	FeedTypeDao feedTypeDao;

	@Override
	@Transactional
	public JSONArray listOfFeedTypeMaster() {
		return feedTypeDao.listOfFeedTypeMaster();
	}

	@Override
	@Transactional
	public String updateFeedTypeMaster(JSONObject object) {
		return feedTypeDao.updateFeedTypeMaster(object);
	}

	@Override
	@Transactional
	public String saveFeedTypeMaster(JSONObject object) {
		return feedTypeDao.saveFeedTypeMaster(object);
	}

	@Override
	@Transactional
	public void deleteFeedTypeMaster(JSONObject object) {
		feedTypeDao.deleteFeedTypeMaster(object);
	}

	@Override
	@Transactional
	public JSONObject getFeedTypeMasterByFeedTypeId(Integer feedTypeMasterId) {
		return feedTypeDao.getFeedTypeMasterByFeedTypeId(feedTypeMasterId);
	}

}
