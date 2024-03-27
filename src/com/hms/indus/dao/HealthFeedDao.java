package com.hms.indus.dao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface HealthFeedDao {
	
	JSONArray listOfHealthFeedMaster();

	void updateHealthFeedMaster(JSONObject object);

	String saveHealthFeedMaster(JSONObject object);

	void deleteHealthFeedMaster(JSONObject object);

	JSONObject getHealthFeedMasterByHealthFeedId(Integer healthFeedMasterId);

}
