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

import com.hms.indus.bo.VisitTypeMaster;
import com.hms.indus.service.VisitTypeService;
import com.hms.indus.util.AccessControl;

@Controller
@RequestMapping(value = "/visitType")
public class VisitTypeController {

	@Autowired
	VisitTypeService visitTypeService;

	@RequestMapping(value = "/listOfVisitTypeMaster", method = RequestMethod.POST)
	public @ResponseBody
	List<VisitTypeMaster> listOfVisitTypeMaster() {
		List<VisitTypeMaster> listVisitTypeMasters = visitTypeService.listOfVisitTypeMaster();
		return listVisitTypeMasters;
	}

	@RequestMapping(value = "/updateVisitTypeMaster", method = RequestMethod.POST)
	public @ResponseBody
	String updateVisitTypeMaster(HttpSession session,
			@RequestParam("visitType") String visitType,
			@RequestParam("visitTypeMasterId") Integer visitTypeMasterId) {
		VisitTypeMaster visitTypeMaster = new VisitTypeMaster();
		visitTypeMaster.setVisitType(visitType);
		visitTypeMaster.setVisitTypeId(visitTypeMasterId);
		//for current time and date
				java.util.Date date = new java.util.Date();
				java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String addedOn = simpleDateFormat.format(date);
				String addedBy=(String)session.getAttribute("userName");
				visitTypeMaster.setModifyBy(addedBy);
				visitTypeMaster.setModifyOn(addedOn);
		String response = visitTypeService.updateVisitTypeMaster(visitTypeMaster);
		return response;
	}

	@RequestMapping(value = "/saveVisitTypeMaster", method = RequestMethod.POST)
	public @ResponseBody
	String saveActionMaster(@RequestParam("visitType") String visitType,HttpSession session) {
		VisitTypeMaster visitTypeMaster = new VisitTypeMaster();
		visitTypeMaster.setVisitType(visitType);
		visitTypeMaster.setVisitTypeDeleteFlag(1);
		//for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String addedOn = simpleDateFormat.format(date);
		String addedBy=(String)session.getAttribute("userName");
		visitTypeMaster.setAddedBy(addedBy);
		visitTypeMaster.setAddedOn(addedOn);
		String response = visitTypeService.saveVisitTypeMaster(visitTypeMaster);
		return response;
	}

	@RequestMapping(value = "/deleteVisitTypeMaster", method = RequestMethod.POST)
	public @ResponseBody
	String deleteActionMaster(HttpSession session,HttpServletRequest request,
			@RequestParam("visitTypeMasterId") Integer visitTypeMasterId) {
		String currentPageId="23";
		String response="";
		boolean access=AccessControl.isDeleteAccess(request, currentPageId);
		if(access==true){
			VisitTypeMaster visitTypeMaster = new VisitTypeMaster();
			visitTypeMaster.setVisitTypeId(visitTypeMasterId);
			//for current time and date
					java.util.Date date = new java.util.Date();
					java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String addedOn = simpleDateFormat.format(date);
					String addedBy=(String)session.getAttribute("userName");
					visitTypeMaster.setModifyBy(addedBy);
					visitTypeMaster.setModifyOn(addedOn);
					visitTypeService.deleteVisitTypeMaster(visitTypeMaster);
			response="true";
		}
		else{
			response="false";
		}
		return response;
	}
	
	@RequestMapping(value = "/getVisitTypeMasterByVisitTypeId", method = RequestMethod.POST)
	public @ResponseBody VisitTypeMaster getVisitTypeMasterByVisitTypeId(HttpServletRequest request,@RequestParam("visitTypeMasterId") Integer visitTypeMasterId) {
		String currentPageId="23";
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		VisitTypeMaster visitTypeMaster=new VisitTypeMaster();
		if(access==true){
			visitTypeMaster = visitTypeService.getVisitTypeMasterByVisitTypeId(visitTypeMasterId);
		}
		else{
			visitTypeMaster.setAddedBy("false");
		}
		return visitTypeMaster;
	}
	
}
