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
import com.hms.indus.bo.SmsTemplateMaster;
import com.hms.indus.bo.TemplateMaster;
import com.hms.indus.service.TemplateService;
import com.hms.indus.util.AccessControl;

@Controller
@RequestMapping(value = "/template")
public class TemplateController {

	@Autowired
	TemplateService templateService;

	@RequestMapping(value = "/listOfTemplateMaster", method = RequestMethod.POST)
	public @ResponseBody
	List<TemplateMaster> listOfTemplateMaster() {
		List<TemplateMaster> listTemplateMasters = templateService.listOfTemplateMaster();
		return listTemplateMasters;
	}

	@RequestMapping(value = "/updateTemplateMaster", method = RequestMethod.POST)
	public @ResponseBody
	String updateTemplateMaster(HttpSession session,@RequestParam("templateName") String templateName,
			@RequestParam("templateDescription") String templateDescription,
			@RequestParam("templateMasterId") Integer templateMasterId) {
		TemplateMaster templateMaster = new TemplateMaster();
		templateMaster.setTemplateDescription(templateDescription);
		templateMaster.setTemplateName(templateName);
		templateMaster.setTemplateId(templateMasterId);
		//for current time and date
				java.util.Date date = new java.util.Date();
				java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String addedOn = simpleDateFormat.format(date);
				String addedBy=(String)session.getAttribute("userName");
				templateMaster.setModifyBy(addedBy);
				templateMaster.setModifyOn(addedOn);
		String response = templateService.updateTemplateMaster(templateMaster);
		return response;
	}

	@RequestMapping(value = "/saveTemplateMaster", method = RequestMethod.POST)
	public @ResponseBody
	String saveTemplateMaster(HttpSession session,@RequestParam("templateName") String templateName,
			@RequestParam("templateDescription") String templateDescription) {
		TemplateMaster templateMaster = new TemplateMaster();
		templateMaster.setTemplateDescription(templateDescription);
		templateMaster.setTemplateName(templateName);
		templateMaster.setTemplateDeleteFlag(1);
		//for current time and date
				java.util.Date date = new java.util.Date();
				java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String addedOn = simpleDateFormat.format(date);
				String addedBy=(String)session.getAttribute("userName");
				templateMaster.setAddedBy(addedBy);
				templateMaster.setAddedOn(addedOn);
		String response = templateService.saveTemplateMaster(templateMaster);
		return response;
	}

	@RequestMapping(value = "/deleteTemplateMaster", method = RequestMethod.POST)
	public @ResponseBody
	String deleteRejectMaster(HttpSession session,HttpServletRequest request,
			@RequestParam("templateMasterId") Integer templateMasterId) {
		String currentPageId="19";
		String response="";
		boolean access=AccessControl.isDeleteAccess(request, currentPageId);
		if(access==true){
			TemplateMaster templateMaster = new TemplateMaster();
			templateMaster.setTemplateId(templateMasterId);
			//for current time and date
					java.util.Date date = new java.util.Date();
					java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String addedOn = simpleDateFormat.format(date);
					String addedBy=(String)session.getAttribute("userName");
					templateMaster.setModifyBy(addedBy);
					templateMaster.setModifyOn(addedOn);
			templateService.deleteTemplateMaster(templateMaster);
			response="true";
		}
		else{
			response="false";
		}
		return response;
	}
	
	@RequestMapping(value = "/getTemplateMasterByTemplateId", method = RequestMethod.POST)
	public @ResponseBody TemplateMaster getTemplateMasterByTemplateId(HttpServletRequest request,@RequestParam("templateMasterId") Integer templateMasterId) {
		String currentPageId="19";
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		TemplateMaster templateMaster=new TemplateMaster();
		if(access==true){
			templateMaster = templateService.getTemplateMasterByTemplateId(templateMasterId);
		}
		else{
			templateMaster.setAddedBy("false");
		}
		return templateMaster;
	}
	
	@RequestMapping(value = "/listOfSmsTemplateMaster", method = RequestMethod.POST)
	public @ResponseBody
	List<SmsTemplateMaster> listOfSmsTemplateMaster() {
		List<SmsTemplateMaster> listTemplateMasters = templateService.listOfSmsTemplateMaster();
		return listTemplateMasters;
	}

	@RequestMapping(value = "/updateSmsTemplateMaster", method = RequestMethod.POST)
	public @ResponseBody
	String updateSmsTemplateMaster(HttpSession session,@RequestParam("smsTemplateName") String templateName,
			@RequestParam("smsTemplateDescription") String templateDescription,
			@RequestParam("smsTemplateMasterId") Integer templateMasterId) {
		SmsTemplateMaster smsTemplateMaster = new SmsTemplateMaster();
		smsTemplateMaster.setSmsTemplateDescription(templateDescription);
		smsTemplateMaster.setSmsTemplateName(templateName);
		smsTemplateMaster.setSmsTemplateId(templateMasterId);
		//for current time and date
				java.util.Date date = new java.util.Date();
				java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String addedOn = simpleDateFormat.format(date);
				String addedBy=(String)session.getAttribute("userName");
				smsTemplateMaster.setModifyBy(addedBy);
				smsTemplateMaster.setModifyOn(addedOn);
		String response = templateService.updateSmsTemplateMaster(smsTemplateMaster);
		return response;
	}

	@RequestMapping(value = "/saveSmsTemplateMaster", method = RequestMethod.POST)
	public @ResponseBody
	String saveSmsTemplateMaster(HttpSession session,@RequestParam("smsTemplateName") String templateName,
			@RequestParam("smsTemplateDescription") String templateDescription) {
		SmsTemplateMaster smsTemplateMaster = new SmsTemplateMaster();
		smsTemplateMaster.setSmsTemplateDescription(templateDescription);
		smsTemplateMaster.setSmsTemplateName(templateName);
		smsTemplateMaster.setSmsTemplateDeleteFlag(1);
		//for current time and date
				java.util.Date date = new java.util.Date();
				java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String addedOn = simpleDateFormat.format(date);
				String addedBy=(String)session.getAttribute("userName");
				smsTemplateMaster.setAddedBy(addedBy);
				smsTemplateMaster.setAddedOn(addedOn);
		String response = templateService.saveSmsTemplateMaster(smsTemplateMaster);
		return response;
	}

	@RequestMapping(value = "/deleteSmsTemplateMaster", method = RequestMethod.POST)
	public @ResponseBody
	String deleteSmsTemplateMaster(HttpSession session,HttpServletRequest request,
			@RequestParam("smsTemplateMasterId") Integer templateMasterId) {
		String currentPageId="20";
		String response="";
		boolean access=AccessControl.isDeleteAccess(request, currentPageId);
		if(access==true){
			SmsTemplateMaster smsTemplateMaster = new SmsTemplateMaster();
			smsTemplateMaster.setSmsTemplateId(templateMasterId);
			//for current time and date
					java.util.Date date = new java.util.Date();
					java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String addedOn = simpleDateFormat.format(date);
					String addedBy=(String)session.getAttribute("userName");
					smsTemplateMaster.setModifyBy(addedBy);
					smsTemplateMaster.setModifyOn(addedOn);
			templateService.deleteSmsTemplateMaster(smsTemplateMaster);
			response="true";
		}
		else{
			response="false";
		}
		return response;
	}
	
	@RequestMapping(value = "/getSmsTemplateMasterByTemplateId", method = RequestMethod.POST)
	public @ResponseBody SmsTemplateMaster getSmsTemplateMasterByTemplateId(HttpServletRequest request,@RequestParam("smsTemplateMasterId") Integer templateMasterId) {
		String currentPageId="20";
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		SmsTemplateMaster smsTemplateMaster=new SmsTemplateMaster();
		if(access==true){
			smsTemplateMaster = templateService.getSmsTemplateMasterByTemplateId(templateMasterId);
		}
		else{
			smsTemplateMaster.setAddedBy("false");
		}
		return smsTemplateMaster;
	}
	
}
