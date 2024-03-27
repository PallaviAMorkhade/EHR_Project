package com.hms.indus.dao;

import java.util.List;

import com.hms.indus.bo.AnalysisCommentMaster;

public interface AnalysisCommentDao {
	
	String saveAnalysisComment(AnalysisCommentMaster analysisCommentMaster);

	String deleteAnalysisComment(AnalysisCommentMaster analysisCommentMaster);
	
	List<AnalysisCommentMaster> listOfAnalysisCommentMaster();

	AnalysisCommentMaster getAnalysisCommentByCommentId(Integer analysisCommentId);

}
