package com.hms.indus.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface HealthFeedService {

	JSONArray listOfHealthFeedMaster();

	void updateHealthFeedMaster(JSONObject object);

	String saveHealthFeedMaster(JSONObject object);

	void deleteHealthFeedMaster(JSONObject object);

	JSONObject getHealthFeedMasterByHealthFeedId(Integer healthFeedMasterId);

}
