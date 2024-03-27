package com.hms.indus.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface VideoService {

	JSONArray listOfVideoMaster();

	void updateVideoMaster(JSONObject object);

	String saveVideoMaster(JSONObject object);

	void deleteVideoMaster(JSONObject object);

	JSONObject getVideoMasterByVideoId(Integer videoMasterId);
	
	String getMaxId();

}
