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

import com.hms.indus.bo.QuestionMaster;
import com.hms.indus.bo.QuestionReportDetail;
import com.hms.indus.bo.QuestionReportId;
import com.hms.indus.service.HraService;

@Controller
@RequestMapping(value = "/questionReport")
public class QuestionReportController {

	@Autowired HraService hraService;
	 
	@RequestMapping(value = "/savequestionReport", method = RequestMethod.POST)
	public @ResponseBody String saveCategory(@RequestBody List<QuestionReportDetail> questionReportDetails, 
			HttpSession session) {
		java.util.Date date = new java.util.Date();
		String addedBy=(String)session.getAttribute("userName");
		for(QuestionReportDetail questionReportDetail : questionReportDetails) {
			questionReportDetail.setAddedBy(addedBy);
			questionReportDetail.setAddedOn(date);
			questionReportDetail.setModifyBy(addedBy);
			questionReportDetail.setModifyOn(date);
		}
		hraService.saveQuestionReportDetails(questionReportDetails);
		return "Question Report Details Saved Successfully";
	}
	
	@RequestMapping(value = "/getQuestionReportByQuestionId", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getquestionReportByquestionId(@RequestParam("questionId") Integer questionId) {
		Map<String,Object> model=new HashMap<String,Object>();
		QuestionMaster questionMaster = hraService.getQuestionMasterByQuestionId(questionId);
		List<QuestionReportDetail> questionReportDetails = hraService.getQuestionReportByQuestionId(questionId);
		model.put("questionMaster", questionMaster);
		model.put("questionReportDetails", questionReportDetails);
		return model;
	}
	
	@RequestMapping(value = "/listQuestionReportDetail", method = RequestMethod.GET)
	public @ResponseBody List<QuestionMaster> listQuestionReportDetail() {
		return hraService.listQuestionReportDetail();
	}
	
	@RequestMapping(value = "/deleteQuestionReportDetail", method = RequestMethod.GET)
	public @ResponseBody String deletequestionReportDetail(@RequestParam("questionId") Integer questionId, 
			HttpSession session) {
		java.util.Date date = new java.util.Date();
		String addedBy=(String)session.getAttribute("userName");
		QuestionReportDetail questionReportDetail = new QuestionReportDetail();
		QuestionReportId questionReportId = new QuestionReportId();
		questionReportId.setQuestionId(questionId);
		questionReportDetail.setQuestionReportId(questionReportId);
		questionReportDetail.setModifyBy(addedBy);
		questionReportDetail.setModifyOn(date);
		questionReportDetail.setIsActive(false);
		hraService.deleteQuestionReportDetail(questionReportDetail);
		return "question Report Details Deleted Successfully";
	}
	

}
