package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.HraTypeMaster;
import com.hms.indus.bo.OptionMaster;
import com.hms.indus.bo.QuestionMaster;
import com.hms.indus.bo.QuestionReportDetail;
import com.hms.indus.dao.HraDao;
import com.hms.indus.service.HraService;

@Service
public class HraServiceImpl implements HraService{
	
	@Autowired
	HraDao hraDao;

	@Override
	@Transactional
	public List<HraTypeMaster> listOfAllHraType() {
		return hraDao.listOfAllHraType();
	}

	@Override
	@Transactional
	public String saveHRATypeMaster(HraTypeMaster hraTypeMaster) {
		return hraDao.saveHRATypeMaster(hraTypeMaster);
	}

	@Override
	@Transactional
	public String deleteHRATypeMaster(HraTypeMaster hraTypeMaster) {
		return hraDao.deleteHRATypeMaster(hraTypeMaster);
	}

	@Override
	@Transactional
	public String updateHRATypeMaster(HraTypeMaster hraTypeMaster) {
		return hraDao.updateHRATypeMaster(hraTypeMaster);
	}

	@Override
	@Transactional
	public String saveQuestionMaster(QuestionMaster questionMaster) {
		return hraDao.saveQuestionMaster(questionMaster);
	}

	@Override
	@Transactional
	public String deleteQuestionMaster(QuestionMaster questionMaster) {
		return hraDao.deleteQuestionMaster(questionMaster);
	}

	@Override
	@Transactional
	public String updateQuestionMaster(QuestionMaster questionMaster) {
		return hraDao.updateQuestionMaster(questionMaster);
	}

	@Override
	@Transactional
	public List<QuestionMaster> listOfAllQuestions() {
		return hraDao.listOfAllQuestions();
	}

	@Override
	@Transactional
	public QuestionMaster getQuestionMasterByQuestionId(Integer questionId) {
		return hraDao.getQuestionMasterByQuestionId(questionId);
	}

	@Override
	@Transactional
	public List<OptionMaster> deleteOptionMaster(Integer questionId,
			Integer optionId) {
		return hraDao.deleteOptionMaster(questionId, optionId);
	}

	@Override
	@Transactional
	public List<QuestionMaster> listOfAllQuestionsByHraId(
			Integer hraTypeMasterId) {
		return hraDao.listOfAllQuestionsByHraId(hraTypeMasterId);
	}

	@Override
	@Transactional
	public String saveClientHRADetails(JSONArray jsonArray,JSONArray uncheckedArray) {
		return hraDao.saveClientHRADetails(jsonArray,uncheckedArray);
	}

	@Override
	@Transactional
	public JSONArray getListOfQuestionClientHRADetails(Integer clientId,Integer visitId) {
		return hraDao.getListOfQuestionClientHRADetails(clientId,visitId);
	}

	@Override
	@Transactional
	public String copyHRAFromVisit(JSONObject object) {
		return hraDao.copyHRAFromVisit(object);
	}

	@Override
	@Transactional
	public Integer getVisitIdByClientId(Integer clientId) {
		return hraDao.getVisitIdByClientId(clientId);
	}

	//For Question Report Details
	@Override
	@Transactional
	public String saveQuestionReportDetails(List<QuestionReportDetail> questionReportDetails) {
		return hraDao.saveQuestionReportDetails(questionReportDetails);
	}

	@Override
	@Transactional
	public List<QuestionReportDetail> getQuestionReportByQuestionId(Integer questionId) {
		return hraDao.getQuestionReportByQuestionId(questionId);
	}

	@Override
	@Transactional
	public List<QuestionMaster> listQuestionReportDetail() {
		return hraDao.listQuestionReportDetail();
	}

	@Override
	@Transactional
	public String deleteQuestionReportDetail(QuestionReportDetail questionReportDetail) {
		return hraDao.deleteQuestionReportDetail(questionReportDetail);
	}

	@Override
	@Transactional
	public String mergePreCouncelling(JSONObject object) {
		return hraDao.mergePreCouncelling(object);
	}

	@Override
	@Transactional
	public Integer checkPreCouncelling(JSONObject object) {
		return hraDao.checkPreCouncelling(object);
	}

}
