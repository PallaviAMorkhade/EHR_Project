package com.hms.indus.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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

public interface ClientService{

	ClientMaster getClientByUserId(Integer clientId,HttpServletRequest request);

	List<ClientUploadReport> getClientUploadReport(Integer clientId);
	
	Boolean changePassword(ClientMaster clientMaster);
	
	List<ClientMaster> getClientMastersByDate(String startDate,String endDate,int startIndex,HttpServletRequest request);

	ClientMaster getClientByclientId(int clientId);
	
	List<ClientMaster> autoSuggestionClient(String letter,HttpServletRequest request);
	
	String getCountClientMaster(String startDate,String endDate,HttpServletRequest request);
	
	int verifyClientUploadReport(ReportVerification reportVerification);
	
	String deletePatientReport(Integer clientReportLineId);
	
	String saveClientReport(ClientReportHead clientReportHead);
	
	List<ClientMaster> getAllClientMastersList();

	String saveClientMaster(ClientMaster clientMaster2);
	
	int savelogSaveClientApi(LogSaveClientApi logSaveClientApi);
	
	int saveBeneficiaryApiLog(GetBeneficiaryApiLog getBeneficiaryApiLog);

	ClientMaster getClientCenterPackageByUserId(Integer clientId,Integer visitId);

	String updateClientProfile(ClientMaster clientMaster);

	String changeProfilePicture(ClientMaster clientMaster);

	String deleteProfilePicture(ClientMaster clientMaster);

	ClientReportHead getClientReportByClientReportLineId(Integer clientReportLineId);
	
	boolean sendSmsAndMail(Integer clientId);

	boolean sendUserNameAndPassword(ClientMaster clientMaster);
	
	List<ClientMaster> autoSuggestionClientTestResults(String letter);
	
	List<ClientMaster> getClientMastersByDateTestResults(String startDate,
			String endDate, int startIndex);
	
	List<ClientMaster> clientTestResultsDropDown(String value, int startIndex);
	
	String getCountClientMasterTestResult(String startDate, String endDate);

	String saveEmailLog(JSONArray jsonArray);

	List<ClientMaster> getAllClientMastersListGreaterThanClientId(Integer clientId);
	
	Integer getClientTimeline(Integer clientId);
	
	String saveFollowUpRecord(JSONObject object);

	JSONArray getFollowUpRecord(Integer clientId);

	JSONObject getFollowUpRecordById(Integer ehrFollowUpRecordId);
	
	JSONObject getSmsRecordBySmsId(Integer smsId);

	JSONObject getEmailRecordByEmailId(Integer emailId);

	JSONArray getSmsRecordByClientId(Integer clientId);

	JSONArray getEmailRecordByClientId(Integer clientId);
	
	JSONArray getEmailRecordByDate(Integer clientId, String startDate,
			String endDate);

	JSONArray getSmsRecordByDate(Integer clientId, String startDate,
			String endDate);
	
	String getCountClientTestResultsDropDown(String value);
	
	String saveClientCheckUpDetails(JSONArray jsonArray);
	JSONArray getClientCheckUpDetails();
	
	JSONObject getEmailSmsCountByClientId(Integer clientId);
	
	String submitFeedback(JSONObject object);
	
	ClientMaster getResetPasswordMobNoByUsername(String userName);

	String saveSelfClientReport(
			List<ClientSelfUploadReport> clientSelfUploadReportList);
	
	List<ClientSelfUploadReport> getClientSelfUploadReport(Integer clientId);

	String deleteSelfClientReport(ClientSelfUploadReport clientSelfUploadReport);	

	List<ClientUploadReport> getVisitByVisitId(Integer clientId, Integer visitId);
	
	ClientMaster isClientLocked(Integer clientId,HttpServletRequest request);
	
	Boolean removeClientLocked(Integer clientId);
	
	String getCountForRepresentativeDashboard(String centerId,
			String startDate, String endDate,String userName);
	
	JSONArray rejectedReportList(String centerId,
			String startDate, String endDate,String userName, String listData);
	
	JSONArray getPatientAnalysis(Integer clientId,Integer visitId);

	String savePatientAnalysis(JSONArray commentArray);

	String saveClientAnalysisComment(JSONObject object);

	JSONArray getPatientAnalysisComment(Integer clientId,Integer visitId);

	String getCountClientMasterAnalysisResults(String startDate, String endDate);

	List<ClientMaster> getClientMastersByAnalysisResults(String startDate,
			String endDate, int startIndex);

	List<ClientMaster> autoSuggestionClientAnalysisResults(String searchKeyword);
	
	JSONArray getPatientHealthStatistics(Integer clientId, String parameterId);

	String saveReminder(JSONArray jsonArray);
	
	JSONArray getPatientReminder(Integer clientId);
	
	String acceptTermsAndConditions(ClientMaster clientMaster2);
	
	JSONArray getAllReminder(String date);
	
	String deleteReminder(String reminderId, String groupId);

	List<ClientMaster> getRecordByMemberId(String memberId,Integer startIndex, HttpServletRequest request);

	String getCountByMemberId(String memberId);
	
	ClientMaster getClientMastersByClientIdTestResults(Integer clientId);

	List<ClientMaster> getRecordByMemberIdTestResults(String memberId);

	JSONObject getAnalysisCommentByClientId(Integer clientId);

	void mergeClient(JSONObject object);

	String getUserNameByClientId(Integer clientId);

	JSONArray getWork(JSONObject object);

	String assignTask(TaskMaster taskMaster);

	String submitTask(JSONArray clients);
	
	void changeClientStatus(JSONArray clients);
	
	Integer isAllReportVerified(JSONObject client);

	String dataEntryVerify(DataEntryVerification dataEntryVerification);

	Integer getVisitStatus(Integer visitId);

	String generateReport(CheckUpMaster checkUpMaster);
	
	Integer countVisitStatus(JSONObject object);
	
	void updateEHRIDAPI(JSONArray jsonArray);

	JSONArray getClientStatus(JSONObject object);

	String closeVisit(JSONArray clientArray);
	
	String saveEmail(EmailRecord emailRecord);

	String saveMsg(SMSRecord smsRecord);

	Integer duplicateCheck(ClientMaster clientMaster);

	int isSendEmail(int clientId, String clientReportName);

	String checkStatusBeforeSubmitTask(JSONArray clients);

	String BeneficiaryId(String clientIdd,Integer visitId);

	void updatEmailResponseAPI(JSONArray mailResponse);

	ClientMaster getClientByclientIdSearch(Integer clientId, HttpServletRequest request);

	JSONArray getClientStatusAll(JSONObject object);

	String updateSmsIsOnOffFlag(ClientMaster clientMaster);

	JSONArray totalPendingVisits(String centerId, String startDate, String endDate, String userName, String listData);

	
}
