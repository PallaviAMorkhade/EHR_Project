package com.hms.indus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hms.indus.bo.FrequencyMaster;
import com.hms.indus.service.FrequencyService;

@Controller
@RequestMapping(value = "/frequency")
public class FrequencyController {

	@Autowired FrequencyService frequencyService;
	 
	@RequestMapping(value = "/saveFrequency", method = RequestMethod.POST)
	public @ResponseBody String saveFrequency(@RequestBody FrequencyMaster frequencyMaster, 
			HttpSession session) {
		java.util.Date date = new java.util.Date();
		String addedBy=(String)session.getAttribute("userName");
		if(frequencyMaster.getFrequencyId()==null) {
			frequencyMaster.setAddedBy(addedBy);
			frequencyMaster.setAddedOn(date);
		}else {
			frequencyMaster.setModifyBy(addedBy);
			frequencyMaster.setModifyOn(date);
		}
		frequencyMaster.setIsActive(true);
		frequencyService.saveFrequency(frequencyMaster);
		return "Frequency Saved Successfully";
	}
	
	@RequestMapping(value = "/deleteFrequency", method = RequestMethod.GET)
	public @ResponseBody String deleteFrequency(@RequestParam("frequencyMasterId") Integer frequencyId, 
			HttpSession session) {
		java.util.Date date = new java.util.Date();
		String addedBy=(String)session.getAttribute("userName");
		FrequencyMaster frequencyMaster = new FrequencyMaster();
		frequencyMaster.setFrequencyId(frequencyId);
		frequencyMaster.setModifyBy(addedBy);
		frequencyMaster.setModifyOn(date);
		frequencyMaster.setIsActive(false);
		frequencyService.deleteFrequency(frequencyMaster);
		return "Frequency Deleted Successfully";
	}
	
	@RequestMapping(value = "/listOfFrequencyMaster", method = RequestMethod.GET)
	public @ResponseBody List<FrequencyMaster> listOfFrequencyMaster() {
		return frequencyService.listOfFrequencyMaster();
	}
	
	@RequestMapping(value = "/getFrequencyByFrequencyId", method = RequestMethod.GET)
	public @ResponseBody FrequencyMaster getFrequencyByFrequencyId(HttpServletRequest request,@RequestParam("frequencyMasterId") Integer frequencyId) {
		FrequencyMaster frequencyMaster = frequencyService.getFrequencyByFrequencyId(frequencyId);
		return frequencyMaster;
	}
	

}
