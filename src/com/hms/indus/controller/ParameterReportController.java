package com.hms.indus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hms.indus.bo.ParameterMaster;
import com.hms.indus.bo.ParameterReportDetail;
import com.hms.indus.bo.ParameterReportId;
import com.hms.indus.service.ParameterService;

@Controller
@RequestMapping(value = "/parameterReport")
public class ParameterReportController {

	@Autowired ParameterService parameterService;
	 
	@RequestMapping(value = "/saveParameterReport", method = RequestMethod.POST)
	public @ResponseBody String saveCategory(@RequestBody List<ParameterReportDetail> parameterReportDetails, 
			HttpSession session) {
		java.util.Date date = new java.util.Date();
		String addedBy=(String)session.getAttribute("userName");
		for(ParameterReportDetail parameterReportDetail : parameterReportDetails) {
			parameterReportDetail.setAddedBy(addedBy);
			parameterReportDetail.setAddedOn(date);
			parameterReportDetail.setModifyBy(addedBy);
			parameterReportDetail.setModifyOn(date);
		}
		parameterService.saveParameterReportDetails(parameterReportDetails);
		return "Parameter Report Details Saved Successfully";
	}
	
	@RequestMapping(value = "/getParameterReportByParameterId", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getParameterReportByParameterId(@RequestParam("parameterId") Integer parameterId) {
		Map<String,Object> model=new HashMap<String,Object>();
		ParameterMaster parameterMaster = parameterService.getParameterByParameterId(parameterId);
		List<ParameterReportDetail> parameterReportDetails = parameterService.getParameterReportByParameterId(parameterId);
		model.put("parameterMaster", parameterMaster);
		model.put("parameterReportDetails", parameterReportDetails);
		return model;
	}
	
	@RequestMapping(value = "/listParameterReportDetail", method = RequestMethod.GET)
	public @ResponseBody List<ParameterMaster> listParameterReportDetail() {
		return parameterService.listParameterReportDetail();
	}
	
	@RequestMapping(value = "/deleteParameterReportDetail", method = RequestMethod.GET)
	public @ResponseBody String deleteParameterReportDetail(@RequestParam("parameterId") Integer parameterId, 
			HttpSession session) {
		java.util.Date date = new java.util.Date();
		String addedBy=(String)session.getAttribute("userName");
		ParameterReportDetail parameterReportDetail = new ParameterReportDetail();
		ParameterReportId parameterReportId = new ParameterReportId();
		parameterReportId.setParameterId(parameterId);
		parameterReportDetail.setParameterReportId(parameterReportId);
		parameterReportDetail.setModifyBy(addedBy);
		parameterReportDetail.setModifyOn(date);
		parameterReportDetail.setIsActive(false);
		parameterService.deleteParameterReportDetail(parameterReportDetail);
		return "Parameter Report Details Deleted Successfully";
	}
	

}
