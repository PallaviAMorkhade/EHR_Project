package com.hms.indus.service;

import java.util.List;

import com.hms.indus.bo.AnalysisCommentMaster;

public interface AnalysisCommentService {
	
	String saveAnalysisComment(AnalysisCommentMaster analysisCommentMaster);

	String deleteAnalysisComment(AnalysisCommentMaster analysisCommentMaster);
	
	List<AnalysisCommentMaster> listOfAnalysisCommentMaster();

	AnalysisCommentMaster getAnalysisCommentByCommentId(Integer analysisCommentId);
	
}
