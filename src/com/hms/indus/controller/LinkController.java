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

import com.hms.indus.service.LinkService;
import com.hms.indus.util.AccessControl;
import com.hms.indus.util.DateConvertUtil;

@Controller
@RequestMapping(value = "/link")
public class LinkController {
	
	@Autowired
	LinkService linkService;

	@RequestMapping(value = "/listOfLinkMaster", method = RequestMethod.POST)
	public @ResponseBody JSONArray listOfLinkMaster() {
		JSONArray jsonArrayLink = linkService.listOfLinkMaster();
		return jsonArrayLink;
	}

	@RequestMapping(value = "/updateLinkMaster", method = RequestMethod.POST)
	public @ResponseBody
	String updateLinkMaster(HttpSession session,HttpServletRequest request,
			@RequestParam("link") String link,@RequestParam("linkDescription") String linkDescription,
			@RequestParam("linkMasterId") Integer linkMasterId) {
		
		String currentPageId="28";
		String response="";
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		if(access==true){
		//for current time and date
				java.util.Date date = new java.util.Date();
				java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String addedOn = simpleDateFormat.format(date);
				String addedBy=(String)session.getAttribute("userName");
				
				JSONObject object = new JSONObject();
				object.put("linkMasterId", linkMasterId);
				object.put("link", link);
				object.put("linkDescription", linkDescription);
				object.put("addedBy", addedBy);
				object.put("addedOn", addedOn);
		linkService.updateLinkMaster(object);
		response="true";
	}
	else{
		response="false";
	}
	return response;
	}

	@RequestMapping(value = "/saveLinkMaster", method = RequestMethod.POST)
	public @ResponseBody
	String saveLinkMaster(@RequestParam("link") String link,@RequestParam("linkDescription") String linkDescription,
			@RequestParam("linkDate") String linkDate,HttpSession session) {
		//for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String addedOn = simpleDateFormat.format(date);
		String addedBy=(String)session.getAttribute("userName");
		
		JSONObject object = new JSONObject();
		object.put("link", link);
		object.put("linkDescription", linkDescription);
		object.put("linkDate", DateConvertUtil.convertDateToDBFormat(linkDate));
		object.put("addedBy", addedBy);
		object.put("addedOn", addedOn);
		
		String response = linkService.saveLinkMaster(object);
		return response;
	}

	@RequestMapping(value = "/deleteLinkMaster", method = RequestMethod.POST)
	public @ResponseBody
	String deleteLinkMaster(HttpSession session,HttpServletRequest request,
			@RequestParam("linkMasterId") Integer linkMasterId) {
		String currentPageId="28";
		String response="";
		boolean access=AccessControl.isDeleteAccess(request, currentPageId);
		if(access==true){
			//for current time and date
					java.util.Date date = new java.util.Date();
					java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String addedOn = simpleDateFormat.format(date);
					String addedBy=(String)session.getAttribute("userName");
			
					JSONObject object = new JSONObject();
					object.put("linkMasterId", linkMasterId);
					object.put("addedBy", addedBy);
					object.put("addedOn", addedOn);
					
			linkService.deleteLinkMaster(object);
			response="true";
		}
		else{
			response="false";
		}
		return response;
	}
	
	@RequestMapping(value = "/getLinkMasterByLinkId", method = RequestMethod.POST)
	public @ResponseBody JSONObject getLinkMasterByLinkId(HttpServletRequest request,@RequestParam("linkMasterId") Integer linkMasterId) {
		String currentPageId="28";
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		JSONObject object = new JSONObject();
		if(access==true){
			object = linkService.getLinkMasterByLinkId(linkMasterId);
		}
		else{
			object.put("addedBy", "false");
		}
		return object;
	}

}
