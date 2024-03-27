package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.CentreMasterLogs;
import com.hms.indus.bo.CheckUpMaster;
import com.hms.indus.bo.ClientMaster;
import com.hms.indus.bo.ClientReportHead;
import com.hms.indus.bo.ClientSelfUploadReport;
import com.hms.indus.bo.ClientUploadReport;
import com.hms.indus.bo.DataEntryVerification;
import com.hms.indus.bo.EmailRecord;
import com.hms.indus.bo.GetBeneficiaryApiLog;
import com.hms.indus.bo.LogSaveClientApi;
import com.hms.indus.bo.PackageMaster;
import com.hms.indus.bo.PackageMasterLogs;
import com.hms.indus.bo.ReportVerification;
import com.hms.indus.bo.SMSRecord;
import com.hms.indus.bo.TaskMaster;
import com.hms.indus.dao.ClientDao;
import com.hms.indus.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{

	@Autowired
	ClientDao clientDao;

	@Override
	@Transactional
	public ClientMaster getClientByUserId(Integer clientId,HttpServletRequest request) {
		return clientDao.getClientByUserId(clientId,request);
	}

	@Override
	@Transactional
	public List<ClientUploadReport> getClientUploadReport(Integer clientId) {
		return clientDao.getClientUploadReport(clientId);
	}

	@Override
	@Transactional
	public Boolean changePassword(ClientMaster clientMaster) {
		return clientDao.changePassword(clientMaster);
	}

	@Override
	@Transactional
	public List<ClientMaster> getClientMastersByDate(String startDate,
			String endDate,int startIndex,HttpServletRequest request) {
		return clientDao.getClientMastersByDate(startDate,endDate,startIndex,request);
	}

	@Override
	@Transactional
	public List<ClientMaster> autoSuggestionClient(String letter,HttpServletRequest request) {
		return clientDao.autoSuggestionClient(letter,request);
	}

	@Override
	@Transactional
	public ClientMaster getClientByclientId(int clientId) {
		return clientDao.getClientByClientId(clientId);
	}

	@Override
	@Transactional
	public String getCountClientMaster(String startDate,String endDate,HttpServletRequest request) {
		return clientDao.getCountClientMaster(startDate,endDate,request);
	}

	@Override
	@Transactional
	public int verifyClientUploadReport(ReportVerification reportVerification) {
		return clientDao.verifyClientUploadReport(reportVerification);
	}

	@Override
	@Transactional
	public String deletePatientReport(Integer clientReportLineId) {
		return clientDao.deletePatientReport(clientReportLineId);
	}

	@Override
	@Transactional
	public String saveClientReport(ClientReportHead clientReportHead) {
		return clientDao.saveClientReport(clientReportHead);
	}

	@Override
	@Transactional
	public List<ClientMaster> getAllClientMastersList() {
		return clientDao.getAllClientMastersList();
	}

	@Override
	@Transactional
	public String saveClientMaster(ClientMaster clientMaster) {
		return clientDao.saveClientMaster(clientMaster);
	}
	
	@Override
	@Transactional
	public int savelogSaveClientApi(LogSaveClientApi logSaveClientApi) {
		return clientDao.savelogSaveClientApi(logSaveClientApi);
	}
	
	@Override
	@Transactional
	public int saveBeneficiaryApiLog(GetBeneficiaryApiLog getBeneficiaryApiLog) {
		return clientDao.saveBeneficiaryApiLog(getBeneficiaryApiLog);
	}

	@Override
	@Transactional
	public ClientMaster getClientCenterPackageByUserId(Integer clientId,Integer visitId) {
		return clientDao.getClientCenterPackageByUserId(clientId,visitId);
	}

	@Override
	@Transactional
	public String updateClientProfile(ClientMaster clientMaster) {
		return clientDao.updateClientProfile(clientMaster);
	}

	@Override
	@Transactional
	public String changeProfilePicture(ClientMaster clientMaster) {
		return clientDao.changeProfilePicture(clientMaster);
	}

	@Override
	@Transactional
	public String deleteProfilePicture(ClientMaster clientMaster) {
		return clientDao.deleteProfilePicture(clientMaster);
	}

	@Override
	@Transactional
	public ClientReportHead getClientReportByClientReportLineId(
			Integer clientReportLineId) {
		return clientDao.getClientReportByClientReportLineId(clientReportLineId);
	}

	@Override
	@Transactional
	public boolean sendSmsAndMail(Integer clientId) {
		return clientDao.sendSmsAndMail(clientId);
	}

	@Override
	@Transactional
	public boolean sendUserNameAndPassword(ClientMaster clientMaster) {
		return clientDao.sendUserNameAndPassword(clientMaster);
	}

	@Override
	@Transactional
	public List<ClientMaster> autoSuggestionClientTestResults(String letter) {
		return clientDao.autoSuggestionClientTestResults(letter);
	}

	@Override
	@Transactional
	public List<ClientMaster> getClientMastersByDateTestResults(
			String startDate, String endDate, int startIndex) {
		return clientDao.getClientMastersByDateTestResults(startDate, endDate, startIndex);
	}

	@Override
	@Transactional
	public List<ClientMaster> clientTestResultsDropDown(String value, int startIndex) {
		return clientDao.clientTestResultsDropDown(value,startIndex);
	}

	@Override
	@Transactional
	public String getCountClientMasterTestResult(String startDate,
			String endDate) {
		return clientDao.getCountClientMasterTestResult(startDate, endDate);
	}

	@Override
	@Transactional
	public String saveEmailLog(JSONArray jsonArray) {
		return clientDao.saveEmailLog(jsonArray);
	}

	@Override
	@Transactional
	public List<ClientMaster> getAllClientMastersListGreaterThanClientId(
			Integer clientId) {
		return clientDao.getAllClientMastersListGreaterThanClientId(clientId);
	}

	@Override
	@Transactional
	public Integer getClientTimeline(Integer clientId) {
		return clientDao.getClientTimeline(clientId);
	}

	@Override
	@Transactional
	public String saveFollowUpRecord(JSONObject object) {
		return clientDao.saveFollowUpRecord(object);
	}

	@Override
	@Transactional
	public JSONArray getFollowUpRecord(Integer clientId) {
		return clientDao.getFollowUpRecord(clientId);
	}

	@Override
	@Transactional
	public JSONObject getFollowUpRecordById(Integer ehrFollowUpRecordId) {
		return clientDao.getFollowUpRecordById(ehrFollowUpRecordId);
	}

	@Override
	@Transactional
	public JSONObject getSmsRecordBySmsId(Integer smsId) {
		return clientDao.getSmsRecordBySmsId(smsId);
	}

	@Override
	@Transactional
	public JSONObject getEmailRecordByEmailId(Integer emailId) {
		return clientDao.getEmailRecordByEmailId(emailId);
	}

	@Override
	@Transactional
	public JSONArray getSmsRecordByClientId(Integer clientId) {
		return clientDao.getSmsRecordByClientId(clientId);
	}

	@Override
	@Transactional
	public JSONArray getEmailRecordByClientId(Integer clientId) {
		return clientDao.getEmailRecordByClientId(clientId);
	}

	@Override
	@Transactional
	public JSONArray getEmailRecordByDate(Integer clientId, String startDate,
			String endDate) {
		return clientDao.getEmailRecordByDate(clientId, startDate, endDate);
	}

	@Override
	@Transactional
	public JSONArray getSmsRecordByDate(Integer clientId, String startDate,
			String endDate) {
		return clientDao.getSmsRecordByDate(clientId, startDate, endDate);
	}

	@Override
	@Transactional
	public String getCountClientTestResultsDropDown(String value) {
		return clientDao.getCountClientTestResultsDropDown(value);
	}

	@Override
	@Transactional
	public String saveClientCheckUpDetails(JSONArray jsonArray) {
		return clientDao.saveClientCheckUpDetails(jsonArray);
	}

	@Override
	@Transactional
	public JSONArray getClientCheckUpDetails() {
		return clientDao.getClientCheckUpDetails();
	}

	@Override
	@Transactional
	public JSONObject getEmailSmsCountByClientId(Integer clientId) {
		return clientDao.getEmailSmsCountByClientId(clientId);
	}

	@Override
	@Transactional
	public String submitFeedback(JSONObject object) {
		return clientDao.submitFeedback(object);
	}

	@Override
	@Transactional
	public ClientMaster getResetPasswordMobNoByUsername(String userName) {
		return clientDao.getResetPasswordMobNoByUsername(userName);
	}

	@Override
	@Transactional
	public String saveSelfClientReport(
			List<ClientSelfUploadReport> clientSelfUploadReportList) {
		return clientDao.saveSelfClientReport(clientSelfUploadReportList);
	}

	@Override
	@Transactional
	public List<ClientSelfUploadReport> getClientSelfUploadReport(
			Integer clientId) {
		return clientDao.getClientSelfUploadReport(clientId);
	}

	@Override
	@Transactional
	public String deleteSelfClientReport(
			ClientSelfUploadReport clientSelfUploadReport) {
		return clientDao.deleteSelfClientReport(clientSelfUploadReport);
	}

	@Override
	@Transactional
	public List<ClientUploadReport> getVisitByVisitId(Integer clientId,
			Integer visitId) {
		return clientDao.getVisitByVisitId(clientId, visitId);
	}

	@Override
	@Transactional
	public ClientMaster isClientLocked(Integer clientId,HttpServletRequest request) {
		return clientDao.isClientLocked(clientId,request);
	}

	@Override
	@Transactional
	public Boolean removeClientLocked(Integer clientId) {
		return clientDao.removeClientLocked(clientId);
	}

	@Override
	@Transactional
	public String getCountForRepresentativeDashboard(String centerId,
			String startDate, String endDate,String userName) {
		return clientDao.getCountForRepresentativeDashboard(centerId, startDate, endDate,userName);
	}

	@Override
	@Transactional
	public JSONArray rejectedReportList(String centerId, String startDate,
			String endDate, String userName,String listData) {
		return clientDao.rejectedReportList(centerId, startDate, endDate, userName, listData);
	}
	
	@Override
	@Transactional
	public JSONArray totalPendingVisits(String centerId, String startDate,
			String endDate, String userName,String listData) {
		return clientDao.totalPendingVisits(centerId, startDate, endDate, userName, listData);
	}

	@Override
	@Transactional
	public JSONArray getPatientAnalysis(Integer clientId,Integer visitId) {
		return clientDao.getPatientAnalysis(clientId,visitId);
	}

	@Override
	@Transactional
	public String savePatientAnalysis(JSONArray commentArray) {
		return clientDao.savePatientAnalysis(commentArray);
	}

	@Override
	@Transactional
	public String saveClientAnalysisComment(JSONObject object) {
		return clientDao.saveClientAnalysisComment(object);
	}

	@Override
	@Transactional
	public JSONArray getPatientAnalysisComment(Integer clientId,Integer visitId) {
		return clientDao.getPatientAnalysisComment(clientId,visitId);
	}

	@Override
	@Transactional
	public String getCountClientMasterAnalysisResults(String startDate,
			String endDate) {
		return clientDao.getCountClientMasterAnalysisResults(startDate, endDate);
	}

	@Override
	@Transactional
	public List<ClientMaster> getClientMastersByAnalysisResults(
			String startDate, String endDate, int startIndex) {
		return clientDao.getClientMastersByAnalysisResults(startDate, endDate, startIndex);
	}

	@Override
	@Transactional
	public List<ClientMaster> autoSuggestionClientAnalysisResults(
			String searchKeyword) {
		return clientDao.autoSuggestionClientAnalysisResults(searchKeyword);
	}

	@Override
	@Transactional
	public JSONArray getPatientHealthStatistics(Integer clientId,
			String parameterId) {
		return clientDao.getPatientHealthStatistics(clientId, parameterId);
	}

	@Override
	@Transactional
	public String saveReminder(JSONArray jsonArray) {
		return clientDao.saveReminder(jsonArray);
	}

	@Override
	@Transactional
	public JSONArray getPatientReminder(Integer clientId) {
		return clientDao.getPatientReminder(clientId);
	}

	@Override
	@Transactional
	public String acceptTermsAndConditions(ClientMaster clientMaster2) {
		return clientDao.acceptTermsAndConditions(clientMaster2);
	}

	@Override
	@Transactional
	public JSONArray getAllReminder(String date) {
		return clientDao.getAllReminder(date);
	}

	@Override
	@Transactional
	public String deleteReminder(String reminderId, String groupId) {
		return clientDao.deleteReminder(reminderId, groupId);
	}

	@Override
	@Transactional
	public List<ClientMaster> getRecordByMemberId(String memberId,Integer startIndex, HttpServletRequest request) {
		return clientDao.getRecordByMemberId(memberId,startIndex,request);
	}

	@Override
	@Transactional
	public String getCountByMemberId(String memberId) {
		return clientDao.getCountByMemberId(memberId);
	}

	@Override
	@Transactional
	public ClientMaster getClientMastersByClientIdTestResults(Integer clientId) {
		return clientDao.getClientMastersByClientIdTestResults(clientId);
	}

	@Override
	@Transactional
	public List<ClientMaster> getRecordByMemberIdTestResults(String memberId) {
		return clientDao.getRecordByMemberIdTestResults(memberId);
	}

	@Override
	@Transactional
	public JSONObject getAnalysisCommentByClientId(Integer clientId) {
		return clientDao.getAnalysisCommentByClientId(clientId);
	}

	@Override
	@Transactional
	public void mergeClient(JSONObject object) {
		clientDao.mergeClient(object);		
	}

	@Override
	@Transactional
	public String getUserNameByClientId(Integer clientId) {
		return clientDao.getUserNameByClientId(clientId);
	}

	@Override
	@Transactional
	public JSONArray getWork(JSONObject object) {
		return clientDao.getWork(object);
	}

	@Override
	@Transactional
	public String assignTask(TaskMaster taskMaster) {
		return clientDao.assignTask(taskMaster);
	}

	@Override
	@Transactional
	public String submitTask(JSONArray clients) {
		return clientDao.submitTask(clients);
	}

	@Override
	@Transactional
	public void changeClientStatus(JSONArray clients) {
		clientDao.changeClientStatus(clients);
	}

	@Override
	@Transactional
	public Integer isAllReportVerified(JSONObject client) {
		return clientDao.isAllReportVerified(client);
	}

	@Override
	@Transactional
	public String dataEntryVerify(DataEntryVerification dataEntryVerification) {
		return clientDao.dataEntryVerify(dataEntryVerification);
	}

	@Override
	@Transactional
	public Integer getVisitStatus(Integer visitId) {
		return clientDao.getVisitStatus(visitId);
	}

	@Override
	@Transactional
	public String generateReport(CheckUpMaster checkUpMaster) {
		return clientDao.generateReport(checkUpMaster);
	}

	@Override
	@Transactional
	public Integer countVisitStatus(JSONObject object) {
		return clientDao.countVisitStatus(object);
	}

	@Override
	@Transactional
	public void updateEHRIDAPI(JSONArray jsonArray) {
		clientDao.updateEHRIDAPI(jsonArray);
	}

	@Override
	@Transactional
	public JSONArray getClientStatus(JSONObject object) {
		return clientDao.getClientStatus(object);
	}
	
	@Override
	@Transactional
	public JSONArray getClientStatusAll(JSONObject object) {
		return clientDao.getClientStatusAll(object);
	}

	@Override
	@Transactional
	public String closeVisit(JSONArray clientArray) {
		return clientDao.closeVisit(clientArray);
	}

	@Override
	@Transactional
	public String saveEmail(EmailRecord emailRecord) {
		return clientDao.saveEmail(emailRecord);
	}

	@Override
	@Transactional
	public String saveMsg(SMSRecord smsRecord) {
		return clientDao.saveMsg(smsRecord);
	}

	@Override
	@Transactional
	public Integer duplicateCheck(ClientMaster clientMaster) {
		return clientDao.duplicateCheck(clientMaster);
	}
	
	@Transactional
	@Override
	public int isSendEmail(int clientId, String clientReportName) {		
		return clientDao.isSendEmail(clientId,clientReportName);
	}
	
	@Transactional
	@Override
	public String checkStatusBeforeSubmitTask(JSONArray clients) {
		return clientDao.checkStatusBeforeSubmitTask(clients);
	}
	
	@Transactional
	@Override
	public String BeneficiaryId(String clientIdd,Integer visitId) {
		return clientDao.BeneficiaryId(clientIdd,visitId);
	}
	
	@Override
	@Transactional
	public void updatEmailResponseAPI(JSONArray jsonArray) {
		clientDao.updatEmailResponseAPI(jsonArray);
	}

	@Override
	@Transactional
	public ClientMaster getClientByclientIdSearch(Integer clientId, HttpServletRequest request) {
		return clientDao.getClientByclientIdSearch(clientId,request);
	}
	
	@Override
	@Transactional
	public String updateSmsIsOnOffFlag(ClientMaster clientMaster) {
		return clientDao.updateSmsIsOnOffFlag(clientMaster);
	}
	
}
