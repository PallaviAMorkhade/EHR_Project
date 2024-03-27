package com.hms.indus.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface LinkService {

	JSONArray listOfLinkMaster();

	void updateLinkMaster(JSONObject object);

	String saveLinkMaster(JSONObject object);

	void deleteLinkMaster(JSONObject object);

	JSONObject getLinkMasterByLinkId(Integer linkMasterId);

}
