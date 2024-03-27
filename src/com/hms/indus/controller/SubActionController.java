package com.hms.indus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hms.indus.bo.ActionMaster;
import com.hms.indus.bo.SubActionMaster;
import com.hms.indus.service.SubActionService;
import com.hms.indus.util.AccessControl;

@Controller
@RequestMapping(value = "/subAction")
public class SubActionController {

	@Autowired
	SubActionService subActionService;

	@RequestMapping(value = "/listOfSubActionMaster", method = RequestMethod.POST)
	public @ResponseBody
	List<SubActionMaster> listOfSubActionMaster() {
		List<SubActionMaster> listSubActionMasters = subActionService.listOfSubActionMaster();
		return listSubActionMasters;
	}

	@RequestMapping(value = "/listOfSubActionMasterByActionId", method = RequestMethod.POST)
	public @ResponseBody
	List<SubActionMaster> listOfSubActionMasterByActionId(@RequestParam("actionId") Integer actionId) {
		List<SubActionMaster> listSubActionMasters = subActionService.listOfSubActionMasterByActionId(actionId);
		return listSubActionMasters;
	}
	
	@RequestMapping(value = "/updateSubActionMaster", method = RequestMethod.POST)
	public @ResponseBody
	String updateSubActionMaster(HttpSession session,@RequestParam("actionId") Integer actionId,
			@RequestParam("subAction") String subAction,
			@RequestParam("subActionMasterId") Integer subActionMasterId) {
		SubActionMaster subActionMaster = new SubActionMaster();
		subActionMaster.setSubAction(subAction);
		subActionMaster.setSubActionId(subActionMasterId);
		ActionMaster actionMaster=new ActionMaster();
		actionMaster.setActionId(actionId);
		subActionMaster.setActionMaster(actionMaster);
		//for current time and date
				java.util.Date date = new java.util.Date();
				java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String addedOn = simpleDateFormat.format(date);
				String addedBy=(String)session.getAttribute("userName");
				subActionMaster.setModifyBy(addedBy);
				subActionMaster.setModifyOn(addedOn);
		String response = subActionService.updateSubActionMaster(subActionMaster);
		return response;
	}

	@RequestMapping(value = "/saveSubActionMaster", method = RequestMethod.POST)
	public @ResponseBody
	String saveSubActionMaster(@RequestParam("subAction") String subAction,@RequestParam("actionId") Integer actionId,HttpSession session) {
		SubActionMaster subActionMaster = new SubActionMaster();
		subActionMaster.setSubAction(subAction);
		subActionMaster.setSubActionDeleteFlag(1);
		ActionMaster actionMaster=new ActionMaster();
		actionMaster.setActionId(actionId);
		subActionMaster.setActionMaster(actionMaster);
		//for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String addedOn = simpleDateFormat.format(date);
		String addedBy=(String)session.getAttribute("userName");
		subActionMaster.setAddedBy(addedBy);
		subActionMaster.setAddedOn(addedOn);
		String response = subActionService.saveSubActionMaster(subActionMaster);
		return response;
	}

	@RequestMapping(value = "/deleteSubActionMaster", method = RequestMethod.POST)
	public @ResponseBody
	String deleteSubActionMaster(HttpSession session,HttpServletRequest request,
			@RequestParam("subActionMasterId") Integer subActionMasterId) {
		String currentPageId="22";
		String response="";
		boolean access=AccessControl.isDeleteAccess(request, currentPageId);
		if(access==true){
			SubActionMaster subActionMaster = new SubActionMaster();
			subActionMaster.setSubActionId(subActionMasterId);
			//for current time and date
					java.util.Date date = new java.util.Date();
					java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String addedOn = simpleDateFormat.format(date);
					String addedBy=(String)session.getAttribute("userName");
					subActionMaster.setModifyBy(addedBy);
					subActionMaster.setModifyOn(addedOn);
			subActionService.deleteSubActionMaster(subActionMaster);
			response="true";
		}
		else{
			response="false";
		}
		return response;
	}
	
	@RequestMapping(value = "/getSubActionMasterBySubActionId", method = RequestMethod.POST)
	public @ResponseBody SubActionMaster getSubActionMasterBySubActionId(HttpServletRequest request,@RequestParam("subActionMasterId") Integer subActionMasterId) {
		String currentPageId="22";
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		SubActionMaster subActionMaster = new SubActionMaster();
		if(access==true){
			subActionMaster = subActionService.getSubActionMasterBySubActionId(subActionMasterId);
		}
		else{
			subActionMaster.setAddedBy("false");
		}
		return subActionMaster;
	}
	
}
