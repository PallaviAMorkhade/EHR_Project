package com.hms.indus.dao;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.hms.indus.bo.HraTypeMaster;
import com.hms.indus.bo.OptionMaster;
import com.hms.indus.bo.QuestionMaster;
import com.hms.indus.bo.QuestionReportDetail;

public interface HraDao {

	List<HraTypeMaster> listOfAllHraType();

	String saveHRATypeMaster(HraTypeMaster hraTypeMaster);

	String deleteHRATypeMaster(HraTypeMaster hraTypeMaster);

	String updateHRATypeMaster(HraTypeMaster hraTypeMaster);

	// for question master
	String saveQuestionMaster(QuestionMaster questionMaster);

	String deleteQuestionMaster(QuestionMaster questionMaster);

	String updateQuestionMaster(QuestionMaster questionMaster);

	List<QuestionMaster> listOfAllQuestions();

	QuestionMaster getQuestionMasterByQuestionId(Integer questionId);

	List<OptionMaster> deleteOptionMaster(Integer questionId, Integer optionId);

	List<QuestionMaster> listOfAllQuestionsByHraId(Integer hraTypeMasterId);

	String saveClientHRADetails(JSONArray jsonArray, JSONArray uncheckedArray);

	JSONArray getListOfQuestionClientHRADetails(Integer clientId, Integer visitId);

	String copyHRAFromVisit(JSONObject object);

	Integer getVisitIdByClientId(Integer clientId);

	// For Question Report Details
	String saveQuestionReportDetails(List<QuestionReportDetail> questionReportDetails);

	List<QuestionReportDetail> getQuestionReportByQuestionId(Integer questionId);

	List<QuestionMaster> listQuestionReportDetail();

	String deleteQuestionReportDetail(QuestionReportDetail questionReportDetail);
	
	String mergePreCouncelling(JSONObject object);
	
	Integer checkPreCouncelling(JSONObject object);

}
