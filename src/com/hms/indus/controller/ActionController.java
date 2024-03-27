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
import com.hms.indus.service.ActionService;
import com.hms.indus.util.AccessControl;

@Controller
@RequestMapping(value = "/action")
public class ActionController {

	@Autowired
	ActionService actionService;

	@RequestMapping(value = "/listOfActionMaster", method = RequestMethod.POST)
	public @ResponseBody
	List<ActionMaster> listOfActionMaster() {
		List<ActionMaster> listActionMasters = actionService.listOfActionMaster();
		return listActionMasters;
	}

	@RequestMapping(value = "/updateActionMaster", method = RequestMethod.POST)
	public @ResponseBody
	String updateActionMaster(HttpSession session,
			@RequestParam("action") String action,
			@RequestParam("actionMasterId") Integer actionMasterId) {
		ActionMaster actionMaster = new ActionMaster();
		actionMaster.setAction(action);
		actionMaster.setActionId(actionMasterId);
		//for current time and date
				java.util.Date date = new java.util.Date();
				java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String addedOn = simpleDateFormat.format(date);
				String addedBy=(String)session.getAttribute("userName");
				actionMaster.setModifyBy(addedBy);
				actionMaster.setModifyOn(addedOn);
		String response = actionService.updateActionMaster(actionMaster);
		return response;
	}

	@RequestMapping(value = "/saveActionMaster", method = RequestMethod.POST)
	public @ResponseBody
	String saveActionMaster(@RequestParam("action") String action,HttpSession session) {
		ActionMaster actionMaster = new ActionMaster();
		actionMaster.setAction(action);
		actionMaster.setActionDeleteFlag(1);
		//for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String addedOn = simpleDateFormat.format(date);
		String addedBy=(String)session.getAttribute("userName");
		actionMaster.setAddedBy(addedBy);
		actionMaster.setAddedOn(addedOn);
		String response = actionService.saveActionMaster(actionMaster);
		return response;
	}

	@RequestMapping(value = "/deleteActionMaster", method = RequestMethod.POST)
	public @ResponseBody
	String deleteActionMaster(HttpSession session,HttpServletRequest request,
			@RequestParam("actionMasterId") Integer actionMasterId) {
		String currentPageId="21";
		String response="";
		boolean access=AccessControl.isDeleteAccess(request, currentPageId);
		if(access==true){
			ActionMaster actionMaster = new ActionMaster();
			actionMaster.setActionId(actionMasterId);
			//for current time and date
					java.util.Date date = new java.util.Date();
					java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String addedOn = simpleDateFormat.format(date);
					String addedBy=(String)session.getAttribute("userName");
					actionMaster.setModifyBy(addedBy);
					actionMaster.setModifyOn(addedOn);
			actionService.deleteActionMaster(actionMaster);
			response="true";
		}
		else{
			response="false";
		}
		return response;
	}
	
	@RequestMapping(value = "/getActionMasterByActionId", method = RequestMethod.POST)
	public @ResponseBody ActionMaster getActionMasterByActionId(HttpServletRequest request,@RequestParam("actionMasterId") Integer actionMasterId) {
		String currentPageId="21";
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		ActionMaster actionMaster=new ActionMaster();
		if(access==true){
			actionMaster = actionService.getActionMasterByActionId(actionMasterId);
		}
		else{
			actionMaster.setAddedBy("false");
		}
		return actionMaster;
	}
	
}
