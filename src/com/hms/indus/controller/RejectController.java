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

import com.hms.indus.bo.QuestionMaster;
import com.hms.indus.bo.RejectMaster;
import com.hms.indus.service.RejectService;
import com.hms.indus.util.AccessControl;

@Controller
@RequestMapping(value = "/reject")
public class RejectController {

	@Autowired
	RejectService rejectService;

	@RequestMapping(value = "/listOfRejectMaster", method = RequestMethod.POST)
	public @ResponseBody
	List<RejectMaster> listOfRejectMaster() {
		List<RejectMaster> listRejectMasters = rejectService.listOfRejectMaster();
		return listRejectMasters;
	}

	@RequestMapping(value = "/updateRejectMaster", method = RequestMethod.POST)
	public @ResponseBody
	String updateRejectMaster(HttpSession session,
			@RequestParam("rejectReason") String rejectReason,
			@RequestParam("rejectMasterId") Integer rejectMasterId) {
		RejectMaster rejectMaster = new RejectMaster();
		rejectMaster.setRejectReason(rejectReason);
		rejectMaster.setRejectId(rejectMasterId);
		//for current time and date
				java.util.Date date = new java.util.Date();
				java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String addedOn = simpleDateFormat.format(date);
				String addedBy=(String)session.getAttribute("userName");
		rejectMaster.setModifyBy(addedBy);
		rejectMaster.setModifyOn(addedOn);
		String response = rejectService.updateRejectMaster(rejectMaster);
		return response;
	}

	@RequestMapping(value = "/saveRejectMaster", method = RequestMethod.POST)
	public @ResponseBody
	String saveRejectMaster(@RequestParam("rejectReason") String rejectReason,HttpSession session) {
		RejectMaster rejectMaster = new RejectMaster();
		rejectMaster.setRejectReason(rejectReason);
		rejectMaster.setRejectDeleteFlag(1);
		//for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String addedOn = simpleDateFormat.format(date);
		String addedBy=(String)session.getAttribute("userName");
		rejectMaster.setAddedBy(addedBy);
		rejectMaster.setAddedOn(addedOn);
		String response = rejectService.saveRejectMaster(rejectMaster);
		return response;
	}

	@RequestMapping(value = "/deleteRejectMaster", method = RequestMethod.POST)
	public @ResponseBody
	String deleteRejectMaster(HttpSession session,HttpServletRequest request,
			@RequestParam("rejectMasterId") Integer rejectMasterId) {
		String currentPageId="18";
		String response="";
		boolean access=AccessControl.isDeleteAccess(request, currentPageId);
		if(access==true){
			RejectMaster rejectMaster = new RejectMaster();
			rejectMaster.setRejectId(rejectMasterId);
			//for current time and date
					java.util.Date date = new java.util.Date();
					java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String addedOn = simpleDateFormat.format(date);
					String addedBy=(String)session.getAttribute("userName");
			rejectMaster.setModifyBy(addedBy);
			rejectMaster.setModifyOn(addedOn);
			rejectService.deleteRejectMaster(rejectMaster);
			response="true";
		}
		else{
			response="false";
		}
		return response;
	}
	
	@RequestMapping(value = "/getRejectMasterByRejectId", method = RequestMethod.POST)
	public @ResponseBody RejectMaster getRejectMasterByRejectId(HttpServletRequest request,@RequestParam("rejectMasterId") Integer rejectMasterId) {
		String currentPageId="18";
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		RejectMaster rejectMaster=new RejectMaster();
		if(access==true){
			rejectMaster = rejectService.getRejectMasterByRejectId(rejectMasterId);
		}
		else{
			rejectMaster.setAddedBy("false");
		}
		return rejectMaster;
	}
	
}
