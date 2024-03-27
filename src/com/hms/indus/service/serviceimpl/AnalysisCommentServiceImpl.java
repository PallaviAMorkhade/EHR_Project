package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.AnalysisCommentMaster;
import com.hms.indus.dao.AnalysisCommentDao;
import com.hms.indus.service.AnalysisCommentService;

@Service
@Transactional
public class AnalysisCommentServiceImpl implements AnalysisCommentService{
	
	@Autowired
	AnalysisCommentDao analysisCommentDao;

	@Override
	public String saveAnalysisComment(AnalysisCommentMaster analysisCommentMaster) {
		return analysisCommentDao.saveAnalysisComment(analysisCommentMaster);
	}

	@Override
	public String deleteAnalysisComment(AnalysisCommentMaster analysisCommentMaster) {
		return analysisCommentDao.deleteAnalysisComment(analysisCommentMaster);
	}

	@Override
	public List<AnalysisCommentMaster> listOfAnalysisCommentMaster() {
		return analysisCommentDao.listOfAnalysisCommentMaster();
	}

	@Override
	public AnalysisCommentMaster getAnalysisCommentByCommentId(Integer analysisCommentId) {
		return analysisCommentDao.getAnalysisCommentByCommentId(analysisCommentId);
	}

}
