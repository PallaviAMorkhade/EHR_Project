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

import com.hms.indus.service.HealthFeedService;
import com.hms.indus.util.AccessControl;
import com.hms.indus.util.DateConvertUtil;

@Controller
@RequestMapping(value = "/healthFeed")
public class HealthFeedController {
	
	@Autowired
	HealthFeedService healthFeedService;

	@RequestMapping(value = "/listOfHealthFeedMaster", method = RequestMethod.POST)
	public @ResponseBody JSONArray listOfHealthFeedMaster() {
		JSONArray jsonArrayHealthFeed = healthFeedService.listOfHealthFeedMaster();
		return jsonArrayHealthFeed;
	}

	@RequestMapping(value = "/updateHealthFeedMaster", method = RequestMethod.POST)
	public @ResponseBody
	String updateHealthFeedMaster(HttpSession session,HttpServletRequest request,
			@RequestParam("feedTypeId") Integer feedTypeId,@RequestParam("healthFeed") String healthFeed,
			@RequestParam("healthFeedMasterId") Integer healthFeedMasterId,@RequestParam("healthFeedDescription") String healthFeedDescription) {
		
		String currentPageId="26";
		String response="";
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		if(access==true){
		//for current time and date
				java.util.Date date = new java.util.Date();
				java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String addedOn = simpleDateFormat.format(date);
				String addedBy=(String)session.getAttribute("userName");
				
				JSONObject object = new JSONObject();
				object.put("feedTypeId", feedTypeId);
				object.put("healthFeedMasterId", healthFeedMasterId);
				object.put("healthFeed", healthFeed);
				object.put("healthFeedDescription", healthFeedDescription);
				object.put("addedBy", addedBy);
				object.put("addedOn", addedOn);
		healthFeedService.updateHealthFeedMaster(object);
		response="true";
	}
	else{
		response="false";
	}
	return response;
	}

	@RequestMapping(value = "/saveHealthFeedMaster", method = RequestMethod.POST)
	public @ResponseBody
	String saveHealthFeedMaster(@RequestParam("feedTypeId") Integer feedTypeId,@RequestParam("healthFeed") String healthFeed,
			@RequestParam("healthFeedDate") String healthFeedDate,@RequestParam("healthFeedDescription") String healthFeedDescription,HttpSession session) {
		//for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String addedOn = simpleDateFormat.format(date);
		String addedBy=(String)session.getAttribute("userName");
		
		JSONObject object = new JSONObject();
		object.put("feedTypeId", feedTypeId);
		object.put("healthFeed", healthFeed);
		object.put("healthFeedDate", DateConvertUtil.convertDateToDBFormat(healthFeedDate));
		object.put("healthFeedDescription", healthFeedDescription);
		object.put("addedBy", addedBy);
		object.put("addedOn", addedOn);
		
		String response = healthFeedService.saveHealthFeedMaster(object);
		return response;
	}

	@RequestMapping(value = "/deleteHealthFeedMaster", method = RequestMethod.POST)
	public @ResponseBody
	String deleteHealthFeedMaster(HttpSession session,HttpServletRequest request,
			@RequestParam("healthFeedMasterId") Integer healthFeedMasterId) {
		String currentPageId="26";
		String response="";
		boolean access=AccessControl.isDeleteAccess(request, currentPageId);
		if(access==true){
			//for current time and date
					java.util.Date date = new java.util.Date();
					java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String addedOn = simpleDateFormat.format(date);
					String addedBy=(String)session.getAttribute("userName");
			
					JSONObject object = new JSONObject();
					object.put("healthFeedMasterId", healthFeedMasterId);
					object.put("addedBy", addedBy);
					object.put("addedOn", addedOn);
					
			healthFeedService.deleteHealthFeedMaster(object);
			response="true";
		}
		else{
			response="false";
		}
		return response;
	}
	
	@RequestMapping(value = "/getHealthFeedMasterByHealthFeedId", method = RequestMethod.POST)
	public @ResponseBody JSONObject getHealthFeedMasterByHealthFeedId(HttpServletRequest request,@RequestParam("healthFeedMasterId") Integer healthFeedMasterId) {
		String currentPageId="26";
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		JSONObject object = new JSONObject();
		if(access==true){
			object = healthFeedService.getHealthFeedMasterByHealthFeedId(healthFeedMasterId);
		}
		else{
			object.put("addedBy", "false");
		}
		return object;
	}

}
