package com.hms.indus.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hms.indus.service.FeedTypeService;
import com.hms.indus.util.AccessControl;

@Controller
@RequestMapping(value = "/feedType")
public class FeedTypeController {
	
	@Autowired
	FeedTypeService feedTypeService;

	@RequestMapping(value = "/listOfFeedTypeMaster", method = RequestMethod.POST)
	public @ResponseBody JSONArray listOfFeedTypeMaster() {
		JSONArray jsonArrayFeedType = feedTypeService.listOfFeedTypeMaster();
		return jsonArrayFeedType;
	}

	@RequestMapping(value = "/updateFeedTypeMaster", method = RequestMethod.POST)
	public @ResponseBody
	String updateFeedTypeMaster(HttpSession session,HttpServletRequest request,
			@RequestParam("feedType") String feedType,
			@RequestParam("feedTypeMasterId") Integer feedTypeMasterId) {
		
		String currentPageId="25";
		String response="";
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		if(access==true){
		//for current time and date
				java.util.Date date = new java.util.Date();
				java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String addedOn = simpleDateFormat.format(date);
				String addedBy=(String)session.getAttribute("userName");
				
				JSONObject object = new JSONObject();
				object.put("feedType", feedType);
				object.put("feedTypeMasterId", feedTypeMasterId);
				object.put("addedBy", addedBy);
				object.put("addedOn", addedOn);
		feedTypeService.updateFeedTypeMaster(object);
		response="true";
	}
	else{
		response="false";
	}
	return response;
	}

	@RequestMapping(value = "/saveFeedTypeMaster", method = RequestMethod.POST)
	public @ResponseBody
	String saveFeedTypeMaster(@RequestParam("feedType") String feedType,HttpSession session) {
		//for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String addedOn = simpleDateFormat.format(date);
		String addedBy=(String)session.getAttribute("userName");
		
		JSONObject object = new JSONObject();
		object.put("feedType", feedType);
		object.put("addedBy", addedBy);
		object.put("addedOn", addedOn);
		
		String response = feedTypeService.saveFeedTypeMaster(object);
		return response;
	}

	@RequestMapping(value = "/deleteFeedTypeMaster", method = RequestMethod.POST)
	public @ResponseBody
	String deleteFeedTypeMaster(HttpSession session,HttpServletRequest request,
			@RequestParam("feedTypeMasterId") Integer feedTypeMasterId) {
		String currentPageId="25";
		String response="";
		boolean access=AccessControl.isDeleteAccess(request, currentPageId);
		if(access==true){
			//for current time and date
					java.util.Date date = new java.util.Date();
					java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String addedOn = simpleDateFormat.format(date);
					String addedBy=(String)session.getAttribute("userName");
			
					JSONObject object = new JSONObject();
					object.put("feedTypeMasterId", feedTypeMasterId);
					object.put("addedBy", addedBy);
					object.put("addedOn", addedOn);
					
			feedTypeService.deleteFeedTypeMaster(object);
			response="true";
		}
		else{
			response="false";
		}
		return response;
	}
	
	@RequestMapping(value = "/getFeedTypeMasterByFeedTypeId", method = RequestMethod.POST)
	public @ResponseBody JSONObject getFeedTypeMasterByFeedTypeId(HttpServletRequest request,@RequestParam("feedTypeMasterId") Integer feedTypeMasterId) {
		String currentPageId="25";
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		JSONObject object = new JSONObject();
		if(access==true){
			object = feedTypeService.getFeedTypeMasterByFeedTypeId(feedTypeMasterId);
		}
		else{
			object.put("addedBy", "false");
		}
		return object;
	}

}
