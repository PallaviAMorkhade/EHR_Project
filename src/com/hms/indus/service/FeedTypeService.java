package com.hms.indus.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface FeedTypeService {

	public JSONArray listOfFeedTypeMaster();

	public String updateFeedTypeMaster(JSONObject object);

	public String saveFeedTypeMaster(JSONObject object);

	public void deleteFeedTypeMaster(JSONObject object);

	public JSONObject getFeedTypeMasterByFeedTypeId(Integer feedTypeMasterId);

}
