package com.hms.indus.dao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface FeedTypeDao {
	
	public JSONArray listOfFeedTypeMaster();

	public String updateFeedTypeMaster(JSONObject object);

	public String saveFeedTypeMaster(JSONObject object);

	public void deleteFeedTypeMaster(JSONObject object);

	public JSONObject getFeedTypeMasterByFeedTypeId(Integer feedTypeMasterId);

}
