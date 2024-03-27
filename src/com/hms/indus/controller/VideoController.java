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

import com.hms.indus.service.VideoService;
import com.hms.indus.util.AccessControl;
import com.hms.indus.util.DateConvertUtil;

@Controller
@RequestMapping(value = "/video")
public class VideoController {
	
	@Autowired
	VideoService videoService;

	@RequestMapping(value = "/listOfVideoMaster", method = RequestMethod.POST)
	public @ResponseBody JSONArray listOfVideoMaster() {
		JSONArray jsonArrayVideo = videoService.listOfVideoMaster();
		return jsonArrayVideo;
	}

	@RequestMapping(value = "/updateVideoMaster", method = RequestMethod.POST)
	public @ResponseBody
	String updateVideoMaster(HttpSession session,HttpServletRequest request,
			@RequestParam("videoDescription") String videoDescription,
			@RequestParam("videoMasterId") Integer videoMasterId) {
		
		String currentPageId="27";
		String response="";
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		if(access==true){
		//for current time and date
				java.util.Date date = new java.util.Date();
				java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String addedOn = simpleDateFormat.format(date);
				String addedBy=(String)session.getAttribute("userName");
				
				JSONObject object = new JSONObject();
				object.put("videoMasterId", videoMasterId);
				object.put("videoDescription", videoDescription);
				object.put("addedBy", addedBy);
				object.put("addedOn", addedOn);
		videoService.updateVideoMaster(object);
		response="true";
	}
	else{
		response="false";
	}
	return response;
	}

	@RequestMapping(value = "/saveVideoMaster", method = RequestMethod.POST)
	public @ResponseBody
	String saveVideoMaster(@RequestParam("fileName") String fileName,@RequestParam("videoDescription") String videoDescription,
			@RequestParam("videoDate") String videoDate,HttpSession session) {
		//for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String addedOn = simpleDateFormat.format(date);
		String addedBy=(String)session.getAttribute("userName");
		
		JSONObject object = new JSONObject();
		object.put("fileName", fileName);
		object.put("videoDate", DateConvertUtil.convertDateToDBFormat(videoDate));
		object.put("videoDescription", videoDescription);
		object.put("addedBy", addedBy);
		object.put("addedOn", addedOn);
		
		String response = videoService.saveVideoMaster(object);
		return response;
	}

	@RequestMapping(value = "/deleteVideoMaster", method = RequestMethod.POST)
	public @ResponseBody
	String deleteVideoMaster(HttpSession session,HttpServletRequest request,
			@RequestParam("videoMasterId") Integer videoMasterId) {
		String currentPageId="27";
		String response="";
		boolean access=AccessControl.isDeleteAccess(request, currentPageId);
		if(access==true){
			//for current time and date
					java.util.Date date = new java.util.Date();
					java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String addedOn = simpleDateFormat.format(date);
					String addedBy=(String)session.getAttribute("userName");
			
					JSONObject object = new JSONObject();
					object.put("videoMasterId", videoMasterId);
					object.put("addedBy", addedBy);
					object.put("addedOn", addedOn);
					
			videoService.deleteVideoMaster(object);
			response="true";
		}
		else{
			response="false";
		}
		return response;
	}
	
	@RequestMapping(value = "/getVideoMasterByVideoId", method = RequestMethod.POST)
	public @ResponseBody JSONObject getVideoMasterByVideoId(HttpServletRequest request,@RequestParam("videoMasterId") Integer videoMasterId) {
		String currentPageId="27";
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		JSONObject object = new JSONObject();
		if(access==true){
			object = videoService.getVideoMasterByVideoId(videoMasterId);
		}
		else{
			object.put("addedBy", "false");
		}
		return object;
	}

}
