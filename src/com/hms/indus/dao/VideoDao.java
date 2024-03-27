package com.hms.indus.dao;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface VideoDao {
	
	JSONArray listOfVideoMaster();

	void updateVideoMaster(JSONObject object);

	String saveVideoMaster(JSONObject object);

	void deleteVideoMaster(JSONObject object);

	JSONObject getVideoMasterByVideoId(Integer videoMasterId);

	String getMaxId();

}
