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

import com.hms.indus.bo.AnalysisCommentMaster;
import com.hms.indus.service.AnalysisCommentService;

@Controller
@RequestMapping(value = "/comment")
public class AnalysisCommentController {

	@Autowired 
	AnalysisCommentService analysisCommentService;
	 
	@RequestMapping(value = "/saveAnalysisComment", method = RequestMethod.POST)
	public @ResponseBody String saveanalysisComment(@RequestBody AnalysisCommentMaster analysisCommentMaster, 
			HttpSession session) {
		java.util.Date date = new java.util.Date();
		String addedBy=(String)session.getAttribute("userName");
		if(analysisCommentMaster.getCommentId()==null) {
			analysisCommentMaster.setAddedBy(addedBy);
			analysisCommentMaster.setAddedOn(date);
		}else {
			analysisCommentMaster.setModifyBy(addedBy);
			analysisCommentMaster.setModifyOn(date);
		}
		analysisCommentMaster.setIsActive(true);
		analysisCommentService.saveAnalysisComment(analysisCommentMaster);
		return "Analysis Comment Saved Successfully";
	}
	
	@RequestMapping(value = "/deleteAnalysisComment", method = RequestMethod.GET)
	public @ResponseBody String deleteanalysisComment(@RequestParam("analysisCommentMasterId") Integer analysisCommentId, 
			HttpSession session) {
		java.util.Date date = new java.util.Date();
		String addedBy=(String)session.getAttribute("userName");
		AnalysisCommentMaster analysisCommentMaster = new AnalysisCommentMaster();
		analysisCommentMaster.setCommentId(analysisCommentId);
		analysisCommentMaster.setModifyBy(addedBy);
		analysisCommentMaster.setModifyOn(date);
		analysisCommentMaster.setIsActive(false);
		analysisCommentService.deleteAnalysisComment(analysisCommentMaster);
		return "Analysis Comment Deleted Successfully";
	}
	
	@RequestMapping(value = "/listOfAnalysisComment", method = RequestMethod.GET)
	public @ResponseBody List<AnalysisCommentMaster> listOfanalysisCommentMaster() {
		return analysisCommentService.listOfAnalysisCommentMaster();
	}
	
	@RequestMapping(value = "/getAnalysisCommentByCommentId", method = RequestMethod.GET)
	public @ResponseBody AnalysisCommentMaster getAnalysisCommentByCommentId(HttpServletRequest request,@RequestParam("analysisCommentMasterId") Integer analysisCommentId) {
		AnalysisCommentMaster analysisCommentMaster = analysisCommentService.getAnalysisCommentByCommentId(analysisCommentId);
		return analysisCommentMaster;
	}
	
}
