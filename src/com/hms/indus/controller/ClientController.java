package com.hms.indus.controller;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonObject;
import com.hms.indus.bo.AppointmentMaster;
import com.hms.indus.bo.CentreMaster;
import com.hms.indus.bo.CentreMasterLogs;
import com.hms.indus.bo.CheckUpMaster;
import com.hms.indus.bo.CityMaster;
import com.hms.indus.bo.ClientMaster;
import com.hms.indus.bo.ClientReportHead;
import com.hms.indus.bo.ClientSelfUploadReport;
import com.hms.indus.bo.ClientUploadReport;
import com.hms.indus.bo.CountryMaster;
import com.hms.indus.bo.DataEntryVerification;
import com.hms.indus.bo.EmailRecord;
import com.hms.indus.bo.LogSaveClientApi;
import com.hms.indus.bo.PackageMaster;
import com.hms.indus.bo.PackageMasterLogs;
import com.hms.indus.bo.ParameterMaster;
import com.hms.indus.bo.RejectMaster;
import com.hms.indus.bo.ReportVerification;
import com.hms.indus.bo.SMSRecord;
import com.hms.indus.bo.StateMaster;
import com.hms.indus.bo.TestMaster;
import com.hms.indus.bo.TestMasterLog;
import com.hms.indus.bo.UserMaster;
import com.hms.indus.bo.VisitTypeMaster;
import com.hms.indus.service.AddressService;
import com.hms.indus.service.AppointmentService;
import com.hms.indus.service.CentreService;
import com.hms.indus.service.CheckUpService;
import com.hms.indus.service.ClientService;
import com.hms.indus.service.PackageService;
import com.hms.indus.service.ParameterService;
import com.hms.indus.service.TestService;
import com.hms.indus.service.UserTypeService;
import com.hms.indus.util.AccessControl;
import com.hms.indus.util.CallSmscApi;
import com.hms.indus.util.DateConvertUtil;
import com.hms.indus.util.FilePath;
import com.hms.indus.util.SSLEmail;
import com.hms.indus.util.Utility;

@Controller
@RequestMapping(value = "/client")
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	@Autowired
	PackageService packageService;
	
	@Autowired
	CentreService centreService;
	
	@Autowired
	TestService testService;
	
	@Autowired
	ParameterService parameterService;
	
	@Autowired
	AddressService addressService;
	
	@Autowired
	UserTypeService userTypeMasterService;
	
	@Autowired
	CheckUpService checkUpService;
	
	@Autowired
	AppointmentService appointmentService;
	
	@Autowired
	WebServiceController webServiceController;
	
	/*@RequestMapping(value = "/getClientByUserId", method = RequestMethod.GET)
	public @ResponseBody ClientMaster getUserProfile(HttpServletRequest request,HttpServletResponse response) {
		ClientMaster clientMaster=new ClientMaster();
		HttpSession session=request.getSession();
		String userId=(String)session.getAttribute("userName");
		try
		{
			clientMaster=clientService.getClientByUserId(userId,request);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return clientMaster;
		
	}*/
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public @ResponseBody boolean changePassword(HttpServletRequest request,HttpServletResponse response,
		   @RequestParam("currentPassword") String currentPassword,
		   @RequestParam("newPassword") String newPassword,@RequestParam("confirmPassword") String confirmPassword) {
		HttpSession session=request.getSession();
		boolean flag=false;
		if((String) session.getAttribute("userId")==null){
		Integer clientId = Integer.parseInt(session.getAttribute("clientId").toString());
		ClientMaster clientMaster=new ClientMaster();
		clientMaster.setClientId(clientId);
		clientMaster.setFirstName(currentPassword);
		clientMaster.setLastName(newPassword);
		flag=clientService.changePassword(clientMaster);
		}
		else{
		Integer userId = Integer.parseInt(session.getAttribute("userId").toString());
		UserMaster userMaster=new UserMaster();
		userMaster.setUserId(userId);
		userMaster.setFirstName(currentPassword);
		userMaster.setLastName(newPassword);
		flag=userTypeMasterService.changePasswordUser(userMaster);
		}
		return flag;
	}
	
	@RequestMapping(value = "/getClientMasterRecordByDate", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getClientMasterRecordByDate(@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate,@RequestParam("startIndex") int startIndex,HttpServletRequest request) {
		Map<String,Object> model=new HashMap<String,Object>();
		startDate = DateConvertUtil.convertDateToDBFormat(startDate);
		endDate = DateConvertUtil.convertDateToDBFormat(endDate);
		String count=clientService.getCountClientMaster(startDate,endDate,request);
		List<ClientMaster> listClientMasters=clientService.getClientMastersByDate(startDate, endDate,startIndex,request);
		model.put("count", count);
		model.put("listClientMasters",listClientMasters);
		return model;
	}
	
	@RequestMapping(value = "/autoSuggestions", method = RequestMethod.POST)
	public @ResponseBody List<ClientMaster> autoSuggestions(@RequestParam("searchKeyword") String searchKeyword,HttpServletRequest request) {
		List<ClientMaster> listClientMasters=clientService.autoSuggestionClient(searchKeyword,request);
		return listClientMasters;
	}
	
	@RequestMapping(value = "/getClientMasterRecordByName", method = RequestMethod.POST)
	public @ResponseBody ClientMaster getClientMasterRecordByName(HttpServletRequest request) {
			String clientId=request.getParameter("clientId");
			ClientMaster clientMasters=clientService.getClientByclientIdSearch(Integer.parseInt(clientId),request);
			return clientMasters;
	}
	
	@RequestMapping(value = "/getClientMastersByClientIdTestResults", method = RequestMethod.POST)
	public @ResponseBody ClientMaster getClientMastersByClientIdTestResults(HttpServletRequest request) {
			String clientId=request.getParameter("clientId");
			ClientMaster clientMasters=clientService.getClientMastersByClientIdTestResults(Integer.parseInt(clientId));
			return clientMasters;
	}
	
	@RequestMapping(value = "/getRecordByMemberId", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getRecordByMemberId(HttpServletRequest request) {
		String memberId=request.getParameter("memberId");
		Integer startIndex=Integer.parseInt(request.getParameter("startIndex"));
		Map<String,Object> model=new HashMap<String,Object>();
		String count=clientService.getCountByMemberId(memberId);
		List<ClientMaster> listClientMasters=clientService.getRecordByMemberId(memberId,startIndex,request);
		model.put("count", count);
		model.put("listClientMasters",listClientMasters);
		return model;
	}
	
	@RequestMapping(value = "/getRecordByMemberIdTestResults", method = RequestMethod.POST)
	public @ResponseBody List<ClientMaster> getRecordByMemberIdTestResults(HttpServletRequest request) {
		String memberId=request.getParameter("memberId");
		List<ClientMaster> clientMasters=clientService.getRecordByMemberIdTestResults(memberId);
		return clientMasters;
	}
	
	@RequestMapping(value = "/getClientCenterPackageByUserId", method = RequestMethod.POST)
	public @ResponseBody ClientMaster getClientCenterPackageByUserId(HttpServletRequest request) {
			String clientId=request.getParameter("clientId");
			String visitId=request.getParameter("visitId");
			
			ClientMaster clientMasters=clientService.getClientCenterPackageByUserId(Integer.parseInt(clientId),Integer.parseInt(visitId.split("_")[1]));
			JSONObject jsonObject=clientService.getEmailSmsCountByClientId(Integer.parseInt(clientId));
			String emailCount = (String) jsonObject.get("emailCount");
			String smsCount = (String) jsonObject.get("smsCount");
			clientMasters.setModAt(smsCount);
			clientMasters.setAddAt(emailCount);
			return clientMasters;
	}
	
	@RequestMapping(value = "/isClientLockedByUserId", method = RequestMethod.POST)
	public @ResponseBody ClientMaster isClientLockedByUserId(@RequestParam("clientId") Integer clientId,HttpServletRequest request) {
		
		/*HttpSession session=request.getSession();
		String sessionId=session.getId();
		
		System.out.println(session.getAttribute("userId"));
		System.out.println(session.getId());
		
		HttpSession getSessionBySessionId=MySessionListener.find("2F17363F161C83FBAA21255667EEBD25");//"D9D8251284AB1F7FA86271327E10A8FE"
		if(getSessionBySessionId!=null){
			System.out.println(getSessionBySessionId.getAttribute("userId"));
		}*/
		
		/*Enumeration e = getSessionBySessionId.getAttributeNames();
	    while (e.hasMoreElements()) {
	      String name = (String) e.nextElement();
	      System.out.println(name + ": " + session.getAttribute(name));
	    }*/
		
		return clientService.isClientLocked(clientId,request);
	}
	
	@RequestMapping(value = "/removeClientLocked", method = RequestMethod.POST)
	public @ResponseBody Boolean removeClientLocked(@RequestParam("clientId") Integer clientId) {
		return clientService.removeClientLocked(clientId);
	}
	
	@RequestMapping(value = "/displayPatientDemoghraphic", method = RequestMethod.GET)
	public ModelAndView getDemographicPage(HttpServletRequest request,HttpServletResponse response,@RequestParam("clientId") Integer clientId) {
		ModelAndView modelAndView = new ModelAndView();
		ClientMaster clientMaster=new ClientMaster();
		boolean access=AccessControl.getAccessControl(request,"demograph");
		if(access==true){
		try
		{
				clientMaster=clientService.getClientByUserId(clientId,request);
				modelAndView.addObject("client",clientMaster);
				modelAndView.addObject("login", new UserMaster());
				modelAndView.setViewName("indus_patient_demographic");
				
				if(clientMaster.getClientDOB()!=null && !clientMaster.getClientDOB().equals("NULL") && !clientMaster.getClientDOB().equals("-")){
					clientMaster.setRemark(DateConvertUtil.convertDateDMY(clientMaster.getClientDOB()));
				String convertedDate=DateConvertUtil.convertDate(clientMaster.getClientDOB());
				clientMaster.setClientDOB(convertedDate);
				}
				if(clientMaster.getNomineeDOB()!=null && !clientMaster.getNomineeDOB().equals("NULL") && !clientMaster.getNomineeDOB().equals("-")){
				clientMaster.setAddAt(DateConvertUtil.convertDateDMY(clientMaster.getNomineeDOB()));
				String convertedNomineeDOB=DateConvertUtil.convertDate(clientMaster.getNomineeDOB());
				clientMaster.setNomineeDOB(convertedNomineeDOB);
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/reminderPage", method = RequestMethod.POST)
	public ModelAndView getReminderPage(HttpServletRequest request,HttpServletResponse response,@RequestParam("clientId") Integer clientId) {
		ModelAndView modelAndView = new ModelAndView();
		ClientMaster clientMaster=new ClientMaster();
		boolean access=AccessControl.getAccessControl(request,"reminders");
		if(access==true){
				clientMaster=clientService.getClientByUserId(clientId,request);
				modelAndView.addObject("client",clientMaster);
				modelAndView.addObject("login", new UserMaster());
				modelAndView.setViewName("indus_patient_reminders");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/verifyClientUploadReport",method = RequestMethod.POST)
	public @ResponseBody int verifyClientUploadReport(HttpServletRequest request,
			@RequestParam("clientReportLineId") int clientReportLineId,
			@RequestParam("verifyComment") String verifyComment,
			@RequestParam("clientId") int clientId,
			@RequestParam("clientReportName") String clientReportName) {
		HttpSession session=request.getSession();
		ReportVerification reportVerification=new ReportVerification();
		reportVerification.setApprovedBy((String)session.getAttribute("userName"));
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		reportVerification.setApprovedOn(currentTime);
		reportVerification.setVerifyComment(verifyComment);
		ClientUploadReport clientUploadReport=new ClientUploadReport();
		clientUploadReport.setClientReportLineId(clientReportLineId);
		List<ClientUploadReport> clientUploadReportSet=new ArrayList<ClientUploadReport>();
		clientUploadReportSet.add(clientUploadReport);
		reportVerification.setClientUploadReportSet(clientUploadReportSet);
		int verifyId=clientService.verifyClientUploadReport(reportVerification);
		
		//For verified report send email
		/*ClientMaster clientMaster=clientService.getClientByclientId(clientId);
		if(clientMaster.getContactFlag().equals("Y")) {
			SSLEmail sendMail = new SSLEmail();
			try {
				String mailSubject = "Verifed Report";
				String mailData = "PFA";
				EmailRecord emailRecord = new EmailRecord();
				emailRecord.setTopic("Verifed Report");
				emailRecord.setSentBy((String)session.getAttribute("userName"));
				emailRecord.setClientId(clientId);
				emailRecord.setEmailSubject(mailSubject);
				emailRecord.setEmailMedicalAdvice(mailData);
				emailRecord.setSentTo(clientMaster.getEmailId());
				sendMail.sendMailWithAttach(clientMaster.getEmailId(), mailData, mailSubject, FilePath.getBasePath()+"/"+clientReportName, emailRecord);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}*/
		return verifyId;
	}
	
	@RequestMapping(value = "/rejectClientUploadReport",method = RequestMethod.POST)
	public @ResponseBody int rejectClientUploadReport(HttpServletRequest request,@RequestParam("clientReportLineId") int clientReportLineId,@RequestParam("rejectComment") String rejectComment,
				@RequestParam("reasonToReject") Integer reasonToReject) {
		HttpSession session=request.getSession();
		ReportVerification reportVerification=new ReportVerification();
		reportVerification.setApprovedBy((String)session.getAttribute("userName"));
		
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		
		reportVerification.setApprovedOn(currentTime);
		reportVerification.setRejectComment(rejectComment);
		
		RejectMaster rejectMaster=new RejectMaster();
		rejectMaster.setRejectId(reasonToReject);
		reportVerification.setRejectMaster(rejectMaster);
		
		ClientUploadReport clientUploadReport=new ClientUploadReport();
		clientUploadReport.setClientReportLineId(clientReportLineId);
		List<ClientUploadReport> clientUploadReportSet=new ArrayList<ClientUploadReport>();
		clientUploadReportSet.add(clientUploadReport);
		reportVerification.setClientUploadReportSet(clientUploadReportSet);
		int rejectId=clientService.verifyClientUploadReport(reportVerification);
		return rejectId;
	}
	
	@RequestMapping(value = "/deletePatientReport", method = RequestMethod.POST)
	public @ResponseBody String deletePatientReport(HttpServletRequest request,@RequestParam("clientReportLineId") Integer clientReportLineId,
			@RequestParam("clientId") Integer clientId,@RequestParam("visitId") Integer checkUpId,HttpSession session) {
		String currentPageId="4";
		String response="";
		boolean access=AccessControl.isDeleteAccess(request, currentPageId);
		if(access==true){
			response = clientService.deletePatientReport(clientReportLineId);
			if(response.contains("Verified")) {
				response = "verified";
			}else {
				response = "true";
				
				//For Team Management
				JSONArray clients = new JSONArray();
				java.util.Date date = new java.util.Date();
				java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentTime = simpleDateFormat.format(date);
				String addedByUserId=(String)session.getAttribute("userId");
				JSONObject client = new JSONObject();
				client.put("clientId", clientId);
				client.put("checkupId", checkUpId);
				client.put("statusId", 1);
				client.put("changedBy", addedByUserId);
				client.put("changedOn", currentTime);
				clients.add(client);
				clientService.changeClientStatus(clients);
				
			}
		}
		else{
			response="false";
		}
		return response;
	}
	
	@RequestMapping(value = "/getPatientTest", method = RequestMethod.POST)
	public @ResponseBody List<TestMaster> getPatientTest(@RequestParam("visitId") Integer visitId) {
		CheckUpMaster checkUpMaster = checkUpService.getVisitById(visitId);
		Integer packageId = checkUpMaster.getPackageMaster().getPackageId();
		List<TestMaster> testMasterList=packageService.getTestByPackageId(packageId);
		return testMasterList;
	}
	
	@RequestMapping(value = "/getPatientCenter", method = RequestMethod.POST)
	public @ResponseBody CentreMaster getPatientCenter(@RequestParam("clientId") Integer clientId) {
		ClientMaster clientMaster=clientService.getClientByclientId(clientId);
		int centerId=clientMaster.getCentreMaster().getCentreId();
		CentreMaster centerMaster=centreService.getCenterByCenterId(centerId);
		if(centerId!=0){
			centerMaster.setCheckUpMasterSet(clientMaster.getCheckUpMasterSet());
		}
		return centerMaster;
	}
	
	@RequestMapping(value = "/getListOftestMaster", method = RequestMethod.POST)
	public @ResponseBody List<TestMaster> getListOftestMaster() {
		List<TestMaster> testMasterList=testService.listOfTestMaster();
		return testMasterList;
	}
	
	@RequestMapping(value = "/getParameterList", method = RequestMethod.POST)
	public @ResponseBody List<ParameterMaster> getParameterList() {
		List<ParameterMaster> ParameterMasterList=parameterService.listOfParameterMaster();
		return ParameterMasterList;
	}
	
	@RequestMapping(value = "/getParameterByTestId", method = RequestMethod.POST)
	public @ResponseBody List<ParameterMaster> getParameterByTestId(@RequestParam("testId") Integer testId) {
		List<ParameterMaster> ParameterMasterList=parameterService.getParameterByTestId(testId);
		return ParameterMasterList;
	}
	
	@RequestMapping(value = "/getTestByPackageId", method = RequestMethod.POST)
	public @ResponseBody List<TestMaster> getTestByPackageId(@RequestParam("packageId") Integer packageId) {
		List<TestMaster> testMasterList=packageService.getTestByPackageId(packageId);
		return testMasterList;
	}
	
	@RequestMapping(value = "/getTestByPackageIdInSequence", method = RequestMethod.POST)
	public @ResponseBody List<TestMaster> getTestByPackageIdInSequence(@RequestParam("packageId") Integer packageId) {
		List<TestMaster> testMasterList=packageService.getTestByPackageIdInSequence(packageId);
		return testMasterList;
	}
	
	@RequestMapping(value = "/getParameterByParameterId", method = RequestMethod.POST)
	public @ResponseBody ParameterMaster getParameterByParameterId(HttpServletRequest request,@RequestParam("parameterId") Integer parameterId) {
		String currentPageId="15";
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		ParameterMaster parameterMaster=new ParameterMaster();
		if(access==true){
			parameterMaster=parameterService.getParameterByParameterId(parameterId);
		}
		else{
			parameterMaster.setAddBy("false");
		}
		return parameterMaster;
	}
	
	@RequestMapping(value = "/saveParameterMaster", method = RequestMethod.POST)
	public @ResponseBody String saveParameterMaster(HttpSession session,@RequestParam("testId") Integer testId,HttpServletRequest request) {
		String[] parameterName = request.getParameterValues("parameterName[]");
		//for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		String addedBy=(String)session.getAttribute("userName");
		
		TestMaster testMaster=new TestMaster();
		testMaster.setTestId(testId);
		//List<ParameterMaster> parameterMasterList=new ArrayList<ParameterMaster>();
		for (int i = 0; i < parameterName.length; i++) {
			if (parameterName[i] != null && parameterName[i] != "") {
				ParameterMaster parameterMaster=new ParameterMaster();
				String[] parameter = parameterName[i].split("_");
				if(parameter[1]!=null && !parameter[1].equals("undefined")){
					parameterMaster.setParameterId(Integer.parseInt(parameter[1]));
				}
				parameterMaster.setNormalValue(parameter[2]);
				if(parameter[2].equals("1")) {
					if(parameter[3]!=null && !parameter[3].equals("null") && !parameter[3].equals("")) {
						parameterMaster.setCriticalLowValue(Double.parseDouble(parameter[3]));
					}else {
						parameterMaster.setCriticalLowValue(null);
					}
					if(parameter[4]!=null && !parameter[4].equals("null") && !parameter[4].equals("")) {
						parameterMaster.setCriticalHighValue(Double.parseDouble(parameter[4]));
					}else {
						parameterMaster.setCriticalHighValue(null);
					}
				}else {
					parameterMaster.setCriticalLowValue(null);
					parameterMaster.setCriticalHighValue(null);
				}
				parameterMaster.setParameterName(parameter[0]);
				parameterMaster.setAddAt(currentTime);
				parameterMaster.setAddBy(addedBy);
				parameterMaster.setIsActive('Y');
				parameterMaster.setTestMaster(testMaster);
				parameterService.saveParameterMaster(parameterMaster);
				//parameterMasterList.add(parameterMaster);
			}
		}
		return "parameter saved";
	}
	
	@RequestMapping(value = "/updateParameterMaster", method = RequestMethod.POST)
	public @ResponseBody String updateParameterMaster(HttpSession session,@RequestParam("testId") Integer testId,HttpServletRequest request) {
		String[] parameterName = request.getParameterValues("parameterName[]");
		//String[] parameterId = request.getParameterValues("parameterId[]");
		//for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		String addedBy=(String)session.getAttribute("userName");
		
		TestMaster testMaster=new TestMaster();
		testMaster.setTestId(testId);
		//List<ParameterMaster> parameterMasterList=new ArrayList<ParameterMaster>();
		for (int i = 0; i < parameterName.length; i++) {
			if (parameterName[i] != null && parameterName[i] != "") {
				ParameterMaster parameterMaster=new ParameterMaster();
				String[] parameter = parameterName[i].split("_");
				if(parameter[1]!=null && !parameter[1].equals("undefined")){
					parameterMaster.setParameterId(Integer.parseInt(parameter[1]));
				}
				parameterMaster.setNormalValue(parameter[2]);
				if(parameter[2].equals("1")) {
					if(parameter[3]!=null && !parameter[3].equals("null") && !parameter[3].equals("")) {
						parameterMaster.setCriticalLowValue(Double.parseDouble(parameter[3]));
					}else {
						parameterMaster.setCriticalLowValue(null);
					}
					if(parameter[4]!=null && !parameter[4].equals("null") && !parameter[4].equals("")) {
						parameterMaster.setCriticalHighValue(Double.parseDouble(parameter[4]));
					}else {
						parameterMaster.setCriticalHighValue(null);
					}
				}else {
					parameterMaster.setCriticalLowValue(null);
					parameterMaster.setCriticalHighValue(null);
				}
				parameterMaster.setParameterName(parameter[0]);
				parameterMaster.setAddAt(currentTime);
				parameterMaster.setAddBy(addedBy);
				//parameterMaster.setModAt(currentTime);
				//parameterMaster.setModBy(addedBy);
				parameterMaster.setIsActive('Y');
				parameterMaster.setTestMaster(testMaster);
				parameterService.updateParameterMaster(parameterMaster);
				//parameterMasterList.add(parameterMaster);
			}
		}
		return "parameter updated";
	}
	
	@RequestMapping(value = "/deleteParameterMaster", method = RequestMethod.POST)
	public @ResponseBody String deleteParameterMaster(HttpServletRequest request,HttpSession session,@RequestParam("parameterId") Integer parameterId) {
		String currentPageId="15";
		String response="";
		boolean access=AccessControl.isDeleteAccess(request, currentPageId);
		if(access==true){
			java.util.Date date = new java.util.Date();
			java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = simpleDateFormat.format(date);
			String modifyBy=(String)session.getAttribute("userName");
			ParameterMaster parameterMaster=new ParameterMaster();
			parameterMaster.setParameterId(parameterId);
			parameterMaster.setModAt(currentTime);
			parameterMaster.setModBy(modifyBy);
			parameterService.deleteParameterMaster(parameterMaster);
			response="true";
		}
		else{
			response="false";
		}
		return response;
	}
	
	@RequestMapping(value = "/saveTestResult", method = RequestMethod.POST)
	public @ResponseBody String saveTestResult(HttpSession session,HttpServletRequest request) {
		String[] parameterName = request.getParameterValues("parameterName[]");
		//for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		String addedBy=(String)session.getAttribute("userName");
		
		JSONArray jsonArray = new JSONArray();
		JSONObject object = null;
		if (parameterName != null) {
			for (int i = 0; i < parameterName.length; i++) {
				object = new JSONObject();
				String[] parameter = parameterName[i].split("_");
				String parameterValue = parameter[0];
				String parameterId = parameter[1];
				String testId = parameter[2];
				String clientId=parameter[3];
				String packageId=parameter[4];
				String reportId=parameter[5];
				String naFlag=parameter[6];
				Double lowerValue = null;
				Double upperValue = null;
				/*if(!packageId.equals("0") && !packageId.equals("undefined")){
					object.put("packageId", packageId);
				}*/
				if (naFlag.equals("false") && parameter.length > 7) {
					if (parameter[7] != null && !parameter[7].equals("")) {
						lowerValue = Double.parseDouble(parameter[7]);
					}
					if (parameter[8] != null && !parameter[8].equals("")) {
						upperValue = Double.parseDouble(parameter[8]);
					}
					if(Double.parseDouble(parameterValue)<lowerValue){
						object.put("testResultStatus", "abnormal low");
					}
					if(Double.parseDouble(parameterValue)>upperValue){
						object.put("testResultStatus", "abnormal high");
					}
					if(Double.parseDouble(parameterValue)>=lowerValue && Double.parseDouble(parameterValue)<=upperValue)
					{
						object.put("testResultStatus", "normal");
					}
				}
				object.put("naFlag", naFlag);
				object.put("reportId", reportId);
				object.put("parameterId", parameterId);
				object.put("parameterValue", parameterValue);
				object.put("testId", testId);
				object.put("packageId", packageId);
				object.put("clientId", clientId);
				object.put("addedBy", addedBy);
				object.put("addedOn", currentTime);
				object.put("modifyOn", currentTime);
				object.put("modifyBy", addedBy);
				jsonArray.add(object);
			}
		}
		String respone=parameterService.saveTestResult(jsonArray);
		return "test result saved";
	}
	
	@RequestMapping(value = "/getTestResultsByClientId", method = RequestMethod.POST)
	public @ResponseBody JSONArray getTestResultsByClientId(@RequestParam("clientId") Integer clientId,@RequestParam("testId") Integer testId,
			@RequestParam("reportId") Integer reportId,@RequestParam("centreId") Integer centreId) {
		JSONArray jsonArray=parameterService.getTestResultsByClientId(clientId, testId,reportId,centreId);
		return jsonArray;
	}
	
	@RequestMapping(value = "/getListOfCentres", method = RequestMethod.POST)
	public @ResponseBody List<CentreMaster> getListOfCentres() {
		List<CentreMaster> centerMasterList=centreService.getListOfCentres();
		return centerMasterList;
	}
	
	@RequestMapping(value = "/savePatientreport", method = RequestMethod.POST)
	public @ResponseBody String savePatientreport(HttpServletRequest request,HttpSession session) {
		String currentPageId="4";
		String response="";
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		if(access==true){
			String[] fileData=request.getParameterValues("fileData[]");
			String oldReport = request.getParameter("oldReport");
			//for current time and date
			java.util.Date date = new java.util.Date();
			java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = simpleDateFormat.format(date);
			
			java.text.SimpleDateFormat fileDateFormat = new java.text.SimpleDateFormat("MM-dd-yyyy");
			String fileDate = fileDateFormat.format(date);
			String addedBy=(String)session.getAttribute("userName");
			JSONArray clients = new JSONArray();
			if (fileData != null) {
				ClientReportHead clientReportHead=new ClientReportHead();
				clientReportHead.setAddedBy(addedBy);
				clientReportHead.setAddedOn(currentTime);
				clientReportHead.setReportIsActive("Y");
				
				List<ClientUploadReport> clientUploadReportList = new ArrayList<ClientUploadReport>();
				for (int i = 0; i < fileData.length; i++) {
					//System.err.println("fileData[i]:"+fileData[i]);
					String[] fileValue=fileData[i].split("#");
					String testPackage=fileValue[0];
					String centreId=fileValue[1];
					String checkUpId=fileValue[2];
					String fileName=fileValue[4]+"_"+fileDate+"_"+fileValue[3];
					//String visitDate=fileValue[4];
					String clientId=fileValue[4];
					String testName=fileValue[5];
					String id=fileValue[6];
					String clientReportLineId=fileValue[7];
					String reportDate="";
					if(fileValue.length>8){
					  reportDate=fileValue[8];
					}
					//clientReportHead.setClientReportDate(visitDate);
					
					CheckUpMaster checkUpMaster=new CheckUpMaster();
					checkUpMaster.setCheckUpId(Integer.parseInt(checkUpId));
					clientReportHead.setCheckUpMaster(checkUpMaster);
					
					CentreMaster centreMaster=new CentreMaster();
					centreMaster.setCentreId(Integer.parseInt(centreId));
					clientReportHead.setCentreMaster(centreMaster);
					
					ClientMaster clientMaster=new ClientMaster();
					clientMaster.setClientId(Integer.parseInt(clientId));
					clientReportHead.setClientMaster(clientMaster);
					
					ClientUploadReport clientUploadReport=new ClientUploadReport();
					clientUploadReport.setEditReportStatus(0);
					if(clientReportLineId!=null && !clientReportLineId.equals("0")){
						clientUploadReport.setEditReportStatus(1);
						clientUploadReport.setClientReportLineId(Integer.parseInt(clientReportLineId));
						clientReportHead.setModifyBy(addedBy);
						clientReportHead.setModifyOn(currentTime);
						//clientService.deletePatientReport(Integer.parseInt(clientReportLineId));
					}
					
					if(oldReport==null) {
					if(fileValue[3].contains("undefined")){
						clientUploadReport.setFilePath(fileValue[3].split("\\*")[1]);
					}
					else{
						clientUploadReport.setFilePath(fileName);
					}
					}else {
						clientUploadReport.setFilePath(oldReport);
					}
					
					clientUploadReport.setReportIsActive("Y");
					clientUploadReport.setReportDate(DateConvertUtil.convertDateToDBFormat(reportDate));
					
					if(testPackage.equals("firstVisit")){
						String[] id2=id.split("_"); 
						if(id2[1]!=null && !id2[1].equals("null")){
							TestMaster testMaster=new TestMaster();
							testMaster.setTestId(Integer.parseInt(id2[1]));
							clientUploadReport.setTestMaster(testMaster);
						}
						else{
							PackageMaster packageMaster=new PackageMaster();
							packageMaster.setPackageId(Integer.parseInt(id2[2]));
							clientUploadReport.setPackageMaster(packageMaster);
						}
					}
					
					if(testPackage.equals("Package")){
						PackageMaster packageMaster=new PackageMaster();
						packageMaster.setPackageId(Integer.parseInt(id));
						clientUploadReport.setPackageMaster(packageMaster);
					}
					else if(testPackage.equals("Test")){
						TestMaster testMaster=new TestMaster();
						testMaster.setTestId(Integer.parseInt(id));
						clientUploadReport.setTestMaster(testMaster);
					}
					clientUploadReport.setClientReportHead(clientReportHead);
					clientUploadReport.setReportDescription(testName);
					clientUploadReportList.add(clientUploadReport);
					
				}
				clientReportHead.setClientUploadReportSet(clientUploadReportList);
				clientService.saveClientReport(clientReportHead);
				
				//For Team Management
				String addedByUserId=(String)session.getAttribute("userId");
				JSONObject client = new JSONObject();
				client.put("clientId", clientReportHead.getClientMaster().getClientId());
				client.put("checkupId", clientReportHead.getCheckUpMaster().getCheckUpId());
				client.put("statusId", 1);
				client.put("changedBy", addedByUserId);
				client.put("changedOn", currentTime);
				clients.add(client);
				clientService.changeClientStatus(clients);
			}
			response="true";
		}
		else{
			response="false";
		}
		return response;
	}
	
	@RequestMapping(value = "/getCityByCityId", method = RequestMethod.POST)
	public @ResponseBody CityMaster getCityByCityId(@RequestParam("cityId") Integer cityId) {
		//CityMaster cityMaster=addressService.getCityByCityId(cityId);
		return null;//cityMaster;
	}
	
	@RequestMapping(value = "/getListOfCitys", method = RequestMethod.POST)
	public @ResponseBody List<CityMaster> getListOfCitys() {
		List<CityMaster> cityMasterList=addressService.getListOfCitys();
		return cityMasterList;
	}
	
	@RequestMapping(value = "/getCityByStateId", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getCityByStateId(@RequestParam("stateId") Integer stateId) {
		List<CityMaster> cityMasterList=addressService.getCityByStateId(stateId);
		Map<String,Object> model=new HashMap<String,Object>();
		model.put("stateMaster", stateId);
		model.put("cityMasterList",cityMasterList);
		return model;
	}
	
	@RequestMapping(value = "/getListOfStates", method = RequestMethod.POST)
	public @ResponseBody List<StateMaster> getListOfStates() {
		List<StateMaster> stateMasterList=addressService.getListOfStates();
		return stateMasterList;
	}
	
	@RequestMapping(value = "/getStateByCountryId", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getStateByCountryId(@RequestParam("countryId") Integer countryId) {
		List<StateMaster> stateMasterList=addressService.getStateByCountryId(countryId);
		 Map<String,Object> model=new HashMap<String,Object>();
		 model.put("countryMaster", countryId);
		 model.put("stateMasterList",stateMasterList);
		 return model;
	}
	
	@RequestMapping(value = "/getListOfCountrys", method = RequestMethod.POST)
	public @ResponseBody List<CountryMaster> getListOfCountrys() {
		List<CountryMaster> countryMasterList=addressService.getListOfCountrys();
		return countryMasterList;
	}
	
	@RequestMapping(value = "/updateClientProfile", method = RequestMethod.POST)
	public @ResponseBody String updateClientProfile(@RequestParam("clientId") Integer clientId,@RequestParam("firstName") String firstName,@RequestParam("middleName") String middleName,
			@RequestParam("lastName") String lastName,@RequestParam("bloodGroup") String bloodGroup,@RequestParam("addressLine1") String addressLine1,@RequestParam("addressLine2") String addressLine2,
			@RequestParam("addressLine3") String addressLine3,@RequestParam("cityId") Integer cityId,@RequestParam("stateId") Integer stateId,@RequestParam("birthDate") String birthDate,
			@RequestParam("countryId") Integer countryId,@RequestParam("pincode") int pincode,@RequestParam("mobNo") String mobNo,@RequestParam("landlineNo") String landlineNo,
			@RequestParam("nomineeFirstName") String nomineeFirstName,@RequestParam("nomineeMiddleName") String nomineeMiddleName,@RequestParam("nomineeLastName") String nomineeLastName,
			@RequestParam("nomineeBirthDate") String nomineeBirthDate,@RequestParam("nomineeRelation") String nomineeRelation,@RequestParam("clientGender") String clientGender,
			@RequestParam("clientHeight") String clientHeight,@RequestParam("clientWeight") String clientWeight,@RequestParam("emailId") String emailId,HttpServletRequest request) {
		ClientMaster clientMaster=new ClientMaster();
		clientMaster.setClientId(clientId);
		clientMaster.setFirstName(Utility.leftRightTrim(firstName));
		clientMaster.setMiddleName(Utility.leftRightTrim(middleName));
		clientMaster.setLastName(Utility.leftRightTrim(lastName));
		clientMaster.setBloodGroup(bloodGroup);
		clientMaster.setAddressLine1(Utility.leftRightTrim(addressLine1));
		clientMaster.setAddressLine2(Utility.leftRightTrim(addressLine2));
		clientMaster.setAddressLine3(Utility.leftRightTrim(addressLine3));
		clientMaster.setMobNo(mobNo);
		clientMaster.setLandlineNo(landlineNo);
		clientMaster.setClientDOB(DateConvertUtil.convertDateToDBFormat(birthDate));
		clientMaster.setNomineeFirstName(Utility.leftRightTrim(nomineeFirstName));
		clientMaster.setNomineeMiddleName(Utility.leftRightTrim(nomineeMiddleName));
		clientMaster.setNomineeLastName(Utility.leftRightTrim(nomineeLastName));
		clientMaster.setNomineeDOB(DateConvertUtil.convertDateToDBFormat(nomineeBirthDate));
		clientMaster.setNomineeRelation(Utility.leftRightTrim(nomineeRelation));
		String[] height=clientHeight.split("#");
		if(height[0].equals("foot")){
			if(height.length>2){
			Double inches=Double.parseDouble(height[2])*12;
			if(height.length>3){
				inches=inches+Double.parseDouble(height[3]);
				clientMaster.setClientHeight(height[2]+" feet "+height[3]+" inches");
			}
			Double heightInCm=inches* 2.54;
			//clientMaster.setClientHeight(height[2]+" "+height[0]+" "+height[3]+" inches");
			clientMaster.setHeightInCm(heightInCm.toString());
			}
		}
		else{
			if(height.length>1){
			clientMaster.setClientHeight(height[1]+" Cm");
			clientMaster.setHeightInCm(height[1]);
			}
		}
		String[] weight=clientWeight.split("#");
		if(weight[0].equals("pound")){
			if(weight.length>1){
			 Double weightInKg = Double.parseDouble(weight[1]) * 0.45359237;
		     clientMaster.setClientWeight(weight[1]+" "+weight[0]);
		     clientMaster.setWeightInKg(weightInKg.toString());
			}
		}
		else{
			if(weight.length>1){
			 clientMaster.setClientWeight(weight[1]+" Kg");
		     clientMaster.setWeightInKg(weight[1]);
			}
		}
		clientMaster.setGender(clientGender);
		clientMaster.setPinCode(pincode);
		clientMaster.setEmailId(emailId);
		if(cityId!=null){
		CityMaster cityMaster=new CityMaster();
		cityMaster.setCityId(cityId);
		clientMaster.setCityMaster(cityMaster);
		}
		if(stateId!=null){
		StateMaster stateMaster=new StateMaster();
		stateMaster.setStateId(stateId);
		clientMaster.setStateMaster(stateMaster);
		}
		if(countryId!=null){
		CountryMaster countryMaster=new CountryMaster();
		countryMaster.setCountryId(countryId);
		clientMaster.setCountryMaster(countryMaster);
		}
		
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currentTime = simpleDateFormat.format(date);
		java.util.Date clientBirthDate = null;
		java.util.Date currentYear = null;
		java.util.Date nomineeBirthDate2 = null;
		
		try {
			clientBirthDate = simpleDateFormat.parse(birthDate);
			currentYear = simpleDateFormat.parse(currentTime);
			Integer nomineeAge =0;
			if(!nomineeBirthDate.equals("-")){
				nomineeBirthDate2 = simpleDateFormat.parse(nomineeBirthDate);
				nomineeAge = currentYear.getYear() - nomineeBirthDate2.getYear();
			}
			Integer age = currentYear.getYear() - clientBirthDate.getYear();
			clientMaster.setClientAge(age);
			clientMaster.setNomineeAge(nomineeAge);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String modifyAt = dateFormat.format(date);
		HttpSession session=request.getSession();
		String modifyBy=(String)session.getAttribute("userName");
		clientMaster.setModBy(modifyBy);
		clientMaster.setModAt(modifyAt);
		
		clientService.updateClientProfile(clientMaster);
		return "Profile updated successfully";
	}
	
	
	@RequestMapping(value = "/updateSmsIsOnOffFlag", method = RequestMethod.POST)
	public @ResponseBody String updateSmsIsOnOffFlag(@RequestParam("clientId") Integer clientId,@RequestParam("contactFlag") String contactFlag,HttpServletRequest request) {
		ClientMaster clientMaster=new ClientMaster();
		clientMaster.setClientId(clientId);		
		clientMaster.setContactFlag(contactFlag);		
		
		java.util.Date date = new java.util.Date();		
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String modifyAt = dateFormat.format(date);
		HttpSession session=request.getSession();
		String modifyBy=(String)session.getAttribute("userName");
		clientMaster.setModBy(modifyBy);
		clientMaster.setModAt(modifyAt);
		
		clientService.updateSmsIsOnOffFlag(clientMaster);
		return "Profile updated successfully";
	}
	
	@RequestMapping(value = "/changeProfilePicture", method = RequestMethod.POST)
	public @ResponseBody String changeProfilePicture(@RequestParam("clientId") Integer clientId,@RequestParam("fileName") String fileName,HttpServletRequest request) {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);	
		HttpSession session=request.getSession();
		ClientMaster clientMaster=new ClientMaster();
		String modifyBy = "";
		clientMaster.setModBy(modifyBy);
		if(session!=null && session.getAttribute("userName")!=null) {
			modifyBy = (String)session.getAttribute("userName");
		} else {
			modifyBy = "self";
		}
		clientMaster.setModAt(currentTime);
		clientMaster.setClientId(clientId);
		
		java.text.SimpleDateFormat simpleDateFormat2 = new java.text.SimpleDateFormat("MM-dd-yyyy");
		String currentTime2 = simpleDateFormat2.format(date);
		String photoUrl=clientId+"_"+currentTime2+"_"+fileName;
		clientMaster.setPhotoUrl(photoUrl);
		clientService.changeProfilePicture(clientMaster);
		return photoUrl;
	}
	
	@RequestMapping(value = "/deleteProfilePicture", method = RequestMethod.POST)
	public @ResponseBody String deleteProfilePicture(@RequestParam("clientId") Integer clientId,HttpServletRequest request) {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);	
		HttpSession session=request.getSession();
		ClientMaster clientMaster=new ClientMaster();
		String modifyBy=(String)session.getAttribute("userName");
		clientMaster.setModBy(modifyBy);
		clientMaster.setModAt(currentTime);
		clientMaster.setClientId(clientId);
		String url=clientService.deleteProfilePicture(clientMaster);
		return url;
	}
	
	@RequestMapping(value = "/getClientReportByClientReportLineId", method = RequestMethod.POST)
	public @ResponseBody ClientReportHead getClientReportByClientReportLineId(HttpServletRequest request,@RequestParam("clientReportLineId") Integer clientReportLineId) {
		ClientReportHead clientReportHead=new ClientReportHead();
		String currentPageId="4";
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		if(access==true){
			clientReportHead=clientService.getClientReportByClientReportLineId(clientReportLineId);
		}
		else{
			clientReportHead.setAddedBy("false");
		}
		return clientReportHead;
	}
	
	@RequestMapping(value = "/getParameterValuesByCenterIdAndTestId", method = RequestMethod.POST)
	public @ResponseBody JSONArray getParameterValuesByCenterIdAndTestId(@RequestParam("centerId") Integer centerId,@RequestParam("testId") Integer testId,@RequestParam("clientId") Integer clientId) {
		JSONArray jsonArray=parameterService.getParameterValuesByCenterIdAndTestId(centerId, testId,clientId);
		return jsonArray;
	}
	
	@RequestMapping(value = "/sendSmsAndMail", method = RequestMethod.POST)
	public @ResponseBody String sendSmsAndMail(@RequestParam("clientId") Integer clientId,HttpSession session) {
		String userName=(String)session.getAttribute("userName");
		boolean sendFlag=clientService.sendSmsAndMail(clientId);
		ClientMaster clientMaster=clientService.getClientByclientId(clientId);
		System.err.println("sendFlag==="+sendFlag);
		if(sendFlag==false){
			try {
				Random rnd = new Random();
				Integer n = 10000000 + rnd.nextInt(900000);
				String text = "i"+n.toString()+"p";
				
				clientMaster.setPassword(text);
				String cantactFlag="";
				if(clientMaster.getContactFlag().equals(null)) {
					cantactFlag="N";
				}else {
					cantactFlag=clientMaster.getContactFlag();
				}
				if(cantactFlag.equals("Y") && clientService.sendUserNameAndPassword(clientMaster)){
					//for sending mail
					String mailSubject = "Electronic Health Record Login Details";
					String mailData = "Dear <b>"+clientMaster.getFirstName()+"</b>, <br><br> Your INDUS EHR account has been created successfully! <br> LOGIN ID : "+clientMaster.getUserId()+" <br> PASSWORD : "+text+" , <br> Kindly Visit: http://ehr.indushealthplus.com/indus/login <br> <br> Regards, <br> Team INDUS";
					SSLEmail sendMail = new SSLEmail();
					EmailRecord emailRecord = new EmailRecord();
					emailRecord.setTopic("Login Details");
					emailRecord.setSentBy(userName);
					emailRecord.setClientId(clientId);
					emailRecord.setEmailSubject(mailSubject);
					emailRecord.setEmailMedicalAdvice(mailData);
					emailRecord.setSentTo(clientMaster.getEmailId());
					sendMail.sendMail(clientMaster.getEmailId(), mailData , mailSubject, emailRecord);
					//for sending sms
					CallSmscApi callSmscApi=new CallSmscApi();
					String smsData = "Welcome to INDUS EHR! \n Your Login Id: "+clientMaster.getUserId()+" , \n Password: "+text+" , \n Visit http://ehr.indushealthplus.com/indus/login \n Regards, \n Team INDUS";
					SMSRecord smsRecord = new SMSRecord();
					smsRecord.setTopic("Login Details");
					smsRecord.setSentBy(userName);
					smsRecord.setClientId(clientId);
					smsRecord.setSmsMedicalAdvice(smsData);
					smsRecord.setSentTo(clientMaster.getMobNo());
					callSmscApi.sendSms("+"+clientMaster.getMobNo(), smsData, smsRecord);
				}
				return "mail sent";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "mail sent";
	}
	
	@RequestMapping(value = "/autoSuggestionClientTestResults", method = RequestMethod.POST)
	public @ResponseBody List<ClientMaster> autoSuggestionClientTestResults(@RequestParam("searchKeyword") String searchKeyword) {
			List<ClientMaster> listClientMasters=clientService.autoSuggestionClientTestResults(searchKeyword);
			return listClientMasters;
	}
	
	@RequestMapping(value = "/clientTestResultsDropDown", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> clientTestResultsDropDown(@RequestParam("value") String value,@RequestParam("startIndex") int startIndex) {
			Map<String,Object> model=new HashMap<String,Object>();
			List<ClientMaster> listClientMasters=clientService.clientTestResultsDropDown(value,startIndex);
			String count=clientService.getCountClientTestResultsDropDown(value);
			model.put("count", count);
			model.put("listClientMasters",listClientMasters);
			return model;
	}
	
	@RequestMapping(value = "/getClientMastersByDateTestResults", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getClientMastersByDateTestResults(@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate,@RequestParam("startIndex") int startIndex) {
		Map<String,Object> model=new HashMap<String,Object>();
		startDate = DateConvertUtil.convertDateToDBFormat(startDate);
		endDate = DateConvertUtil.convertDateToDBFormat(endDate);
		String count=clientService.getCountClientMasterTestResult(startDate, endDate);
		List<ClientMaster> listClientMasters=clientService.getClientMastersByDateTestResults(startDate, endDate,startIndex);
		model.put("count", count);
		model.put("listClientMasters",listClientMasters);
		return model;
	}
	
	@RequestMapping(value = "/autoSuggestionClientAnalysisResults", method = RequestMethod.POST)
	public @ResponseBody List<ClientMaster> autoSuggestionClientAnalysisResults(@RequestParam("searchKeyword") String searchKeyword) {
		List<ClientMaster> listClientMasters=clientService.autoSuggestionClientAnalysisResults(searchKeyword);
		return listClientMasters;
	}
	
	@RequestMapping(value = "/getClientMastersByAnalysisResults", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getClientMastersByAnalysisResults(@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate,@RequestParam("startIndex") int startIndex) {
		Map<String,Object> model=new HashMap<String,Object>();
		startDate = DateConvertUtil.convertDateToDBFormat(startDate);
		endDate = DateConvertUtil.convertDateToDBFormat(endDate);
		String count=clientService.getCountClientMasterAnalysisResults(startDate, endDate);
		List<ClientMaster> listClientMasters=clientService.getClientMastersByAnalysisResults(startDate, endDate,startIndex);
		model.put("count", count);
		model.put("listClientMasters",listClientMasters);
		return model;
	}
	
	@RequestMapping(value = "/saveEmail", method = RequestMethod.POST)
	public @ResponseBody String saveEmail(@RequestBody EmailRecord emailRecord) {
		/*String addedBy=(String)session.getAttribute("userName");
		emailRecord.setSentBy(addedBy);*/
		return clientService.saveEmail(emailRecord);
	}
	
	@RequestMapping(value = "/saveMsg", method = RequestMethod.POST)
	public @ResponseBody String saveMsg(@RequestBody SMSRecord smsRecord) {
		/*String addedBy=(String)session.getAttribute("userName");
		smsRecord.setSentBy(addedBy);*/
		return clientService.saveMsg(smsRecord);
	}
	
	@RequestMapping(value = "/sendEmailAndSmsMedicalAdvice", method = RequestMethod.POST)
	public @ResponseBody
	String sendEmailAndSmsMedicalAdvice(
			@RequestParam("emailTemplateId") Integer emailTemplateId,
			@RequestParam("smsTemplateId") Integer smsTemplateId,
			@RequestParam("emailMedicalAdvice") String emailMedicalAdvice,
			@RequestParam("smsMedicalAdvice") String smsMedicalAdvice,
			@RequestParam("emailSubject") String emailSubject,
			HttpServletRequest request, HttpSession session) {
		String response = "";
		// for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		String addedBy = (String) session.getAttribute("userName");

		JSONArray jsonArray = new JSONArray();
		//JSONObject object = null;
		
		String[] emailClient = request.getParameterValues("emailClient[]");
		String[] smsClient = request.getParameterValues("smsClient[]");

		if(emailClient !=null){
			for (int i = 0; i < emailClient.length; i++) {
				String[] email = emailClient[i].split("&");
				String emailId=email[0];
				Integer clientId=Integer.parseInt(email[1]);
				if (emailTemplateId != null) {
					// for sending mail
					String mailSubject = "Indus medical advice";
					SSLEmail sendMail = new SSLEmail();
					try {
						EmailRecord emailRecord = new EmailRecord();
						emailRecord.setTopic("Medical Advice");
						emailRecord.setSentBy(addedBy);
						emailRecord.setClientId(clientId);
						emailRecord.setEmailSubject(mailSubject);
						emailRecord.setEmailMedicalAdvice(emailMedicalAdvice);
						emailRecord.setSentTo(emailId);
						sendMail.sendMail(emailId, emailMedicalAdvice, mailSubject, emailRecord);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
					
					/*object = new JSONObject();
					object.put("sentBy", addedBy);
					object.put("sentOn", currentTime);
					object.put("clientId", clientId);
					object.put("sentToEmail", emailId);
					object.put("emailTemplateId", emailTemplateId);
					object.put("emailMedicalAdvice", emailMedicalAdvice);
					object.put("emailSubject", emailSubject);
					jsonArray.add(object);*/
					response = "Email Sent Successfully";
				}
			}
		}
		
		if(smsClient !=null){
			for (int i = 0; i < smsClient.length; i++) {
				String[] mob = smsClient[i].split("&");
				String mobNo=mob[0];
				Integer clientId=Integer.parseInt(mob[1]);
				if (smsTemplateId != null) {
					// for sending sms
					CallSmscApi callSmscApi = new CallSmscApi();
					SMSRecord smsRecord = new SMSRecord();
					smsRecord.setTopic("Medical Advice");
					smsRecord.setSentBy(addedBy);
					smsRecord.setClientId(clientId);
					smsRecord.setSmsMedicalAdvice(smsMedicalAdvice);
					smsRecord.setSentTo(mobNo);
					callSmscApi.sendSms(mobNo,smsMedicalAdvice,smsRecord);
					/*object = new JSONObject();
					object.put("sentBy", addedBy);
					object.put("sentOn", currentTime);
					object.put("clientId", clientId);
					object.put("sentToMobNo", mobNo);
					object.put("smsTemplateId", smsTemplateId);
					object.put("smsMedicalAdvice", smsMedicalAdvice);
					jsonArray.add(object);*/
					response = "SMS Sent Successfully";
				}
			}
		}
		
		if (emailTemplateId != null && smsTemplateId != null) {
			response = "Email and Sms sent successfully";
		}
		
		/*if (jsonArray != null) {
			// System.out.println("******"+jsonArray);
			clientService.saveEmailLog(jsonArray);
		}*/
		return response;
	}
	
	@RequestMapping(value = "/getClientTimeline", method = RequestMethod.POST)
	public @ResponseBody Integer getClientTimeline(@RequestParam("clientId") Integer clientId) {
			Integer value=clientService.getClientTimeline(clientId);
			return value;
	}
	
	@RequestMapping(value = "/saveFollowUpRecord", method = RequestMethod.POST)
	public @ResponseBody String saveFollowUpRecord(
			@RequestParam("actionId") Integer actionId,@RequestParam("clientId") Integer clientId,
			@RequestParam("centerId") Integer centerId,@RequestParam("subActionId") Integer subActionId,
			@RequestParam("followUpDate") String followUpDate,@RequestParam("status") Integer status,
			@RequestParam("time") String time,@RequestParam("activities") String activities,
			@RequestParam("comment") String comment,@RequestParam("callResultStatus") String callResultStatus,
			@RequestParam("visitId") Integer visitId,HttpServletRequest request, HttpSession session) {

		// for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		String addedBy = (String) session.getAttribute("userName");
		JSONObject object = new JSONObject();
				object.put("addedBy", addedBy);
				object.put("addedOn", currentTime);
				object.put("followUpDate", DateConvertUtil.convertDateToDBFormat(followUpDate));
				object.put("actionId", actionId);
				object.put("centerId", centerId);
				object.put("clientId", clientId);
				object.put("time", time);
				object.put("activities", activities);
				object.put("comment", comment);
				object.put("callResultStatus",callResultStatus);
				object.put("subActionId", subActionId);
				object.put("status",status);
				object.put("visitId",visitId);
		clientService.saveFollowUpRecord(object);
		return "follow up record saved";
	}
	
	@RequestMapping(value = "/listOfFollowUpRecord", method = RequestMethod.POST)
	public @ResponseBody JSONArray listOfFollowUpRecord(@RequestParam("clientId") Integer clientId) {
		JSONArray jsonArray=clientService.getFollowUpRecord(clientId);
		return jsonArray;
	}
	
	@RequestMapping(value = "/getFollowUpRecordById", method = RequestMethod.POST)
	public @ResponseBody JSONObject getFollowUpRecordById(@RequestParam("ehrFollowUpRecordId") Integer ehrFollowUpRecordId) {
		JSONObject object=clientService.getFollowUpRecordById(ehrFollowUpRecordId);
		return object;
	}
	
	@RequestMapping(value = "/getEmailRecordByClientId", method = RequestMethod.POST)
	public @ResponseBody JSONArray getEmailRecordByClientId(@RequestParam("clientId") Integer clientId) {
		JSONArray jsonArray=clientService.getEmailRecordByClientId(clientId);
		return jsonArray;
	}
	
	@RequestMapping(value = "/getEmailRecordByEmailId", method = RequestMethod.POST)
	public @ResponseBody JSONObject getEmailRecordByEmailId(@RequestParam("ehrEmailId") Integer ehrEmailId) {
		JSONObject object=clientService.getEmailRecordByEmailId(ehrEmailId);
		return object;
	}
	
	@RequestMapping(value = "/getSmsRecordByClientId", method = RequestMethod.POST)
	public @ResponseBody JSONArray getSmsRecordByClientId(@RequestParam("clientId") Integer clientId) {
		JSONArray jsonArray=clientService.getSmsRecordByClientId(clientId);
		return jsonArray;
	}
	
	@RequestMapping(value = "/getSmsRecordBySmsId", method = RequestMethod.POST)
	public @ResponseBody JSONObject getSmsRecordBySmsId(@RequestParam("ehrSmsId") Integer ehrSmsId) {
		JSONObject object=clientService.getSmsRecordBySmsId(ehrSmsId);
		return object;
	}
	
	@RequestMapping(value = "/getEmailSmsRecordByClientId", method = RequestMethod.POST)
	public @ResponseBody JSONArray getEmailSmsRecordByClientId(@RequestParam("clientId") Integer clientId) {
		
		JSONArray jsonArray=new JSONArray();
		JSONArray emailJsonArray=clientService.getEmailRecordByClientId(clientId);
		JSONArray smsJsonArray=clientService.getSmsRecordByClientId(clientId);
		jsonArray.add(emailJsonArray);
		jsonArray.add(smsJsonArray);
		return jsonArray;
	}
	
	@RequestMapping(value = "/getEmailRecordByDate", method = RequestMethod.POST)
	public @ResponseBody JSONArray getEmailRecordByDate(@RequestParam("clientId") Integer clientId,
			@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) {
		Date date1 = null;
		Date date2 = null;
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				 date1 = new SimpleDateFormat("dd-MM-yyyy").parse(startDate);
				 date2 = new SimpleDateFormat("dd-MM-yyyy").parse(endDate);

				
				//startDate = formatter.format(date1);
				//endDate = formatter.format(date2);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		JSONArray jsonArray=clientService.getEmailRecordByDate(clientId, formatter.format(date1), formatter.format(date2));
		return jsonArray;
	}
	
	@RequestMapping(value = "/getSmsRecordByDate", method = RequestMethod.POST)
	public @ResponseBody JSONArray getSmsRecordByDate(@RequestParam("clientId") Integer clientId,
			@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) {
		Date date1 = null;
		Date date2 = null;
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				 date1 = new SimpleDateFormat("dd-MM-yyyy").parse(startDate);
				 date2 = new SimpleDateFormat("dd-MM-yyyy").parse(endDate);

				
				//startDate = formatter.format(date1);
				//endDate = formatter.format(date2);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		JSONArray jsonArray=clientService.getSmsRecordByDate(clientId, formatter.format(date1), formatter.format(date2));
		return jsonArray;
	}
	
	@RequestMapping(value = "/getEmailSmsRecordByDate", method = RequestMethod.POST)
	public @ResponseBody JSONArray getEmailSmsRecordByDate(@RequestParam("clientId") Integer clientId,
			@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) {  
		
		Date date1 = null;
		Date date2 = null;
		Format formatter = new SimpleDateFormat("yyyy-MM-dd");
			try {
				 date1 = new SimpleDateFormat("dd-MM-yyyy").parse(startDate);
				 date2 = new SimpleDateFormat("dd-MM-yyyy").parse(endDate);

				
				//startDate = formatter.format(date1);
				//endDate = formatter.format(date2);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		JSONArray jsonArray=new JSONArray();
		JSONArray emailJsonArray=clientService.getEmailRecordByDate(clientId, formatter.format(date1), formatter.format(date2));
		JSONArray smsJsonArray=clientService.getSmsRecordByDate(clientId, formatter.format(date1), formatter.format(date2));
		jsonArray.add(emailJsonArray);
		jsonArray.add(smsJsonArray);
		return jsonArray;
	}
	
	@RequestMapping(value = "/submitFeedback", method = RequestMethod.POST)
	public @ResponseBody String submitFeedback(@RequestParam("feedback") String feedback, HttpSession session) {
		Integer clientId=(Integer)session.getAttribute("clientId");
		ClientMaster clientMaster=clientService.getClientByclientId(clientId);
		if (feedback != null) {
			String cantactFlag="";
			if(clientMaster.getContactFlag().equals(null)) {
				cantactFlag="N";
			}else {
				cantactFlag=clientMaster.getContactFlag();
			}
			if (cantactFlag.equals("Y")) {
			// for sending mail
			String mailSubject = "Feedback";
			String mailData = feedback+"<br><br>From:<br>ClientId:"+clientId+"<br>Client Name:"+clientMaster.getFirstName()+" "+clientMaster.getLastName()+"<br>Client Mob No.:"+clientMaster.getMobNo()+"<br>Client Email-Id:"+clientMaster.getEmailId();
			SSLEmail sendMail = new SSLEmail();
			try {
				EmailRecord emailRecord = new EmailRecord();
				emailRecord.setTopic("Feedback");
				emailRecord.setSentBy("Client");
				emailRecord.setClientId(clientId);
				emailRecord.setEmailSubject(mailSubject);
				emailRecord.setEmailMedicalAdvice(mailData);
				emailRecord.setSentTo("it.pro@indushealthplus.com");
				sendMail.sendMail("it.pro@indushealthplus.com",mailData, mailSubject, emailRecord);
				
				emailRecord.setSentTo("shailesh.thakare@indushealthplus.com");
				sendMail.sendMail("shailesh.thakare@indushealthplus.com",mailData,mailSubject,emailRecord);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			}
			java.util.Date date = new java.util.Date();
			java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = simpleDateFormat.format(date);
			JSONObject object = new JSONObject();
			object.put("feedback", feedback);
			object.put("sentOn", currentTime);
			object.put("clientId", clientId);
			object.put("sentToEmail", "it.pro@indushealthplus.com,shailesh.thakare@indushealthplus.com");
			clientService.submitFeedback(object);
		}
		return "Feedback submitted successfully";
	}
	
	@RequestMapping(value = "/saveSelfClientReport", method = RequestMethod.POST)
	public @ResponseBody String saveSelfClientReport(HttpServletRequest request,HttpSession session) {
		String currentPageId="7";
		String response="";
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		if(access==true){
			String[] fileData=request.getParameterValues("fileData[]");
			//for current time and date
			java.util.Date date = new java.util.Date();
			java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = simpleDateFormat.format(date);
			
			java.text.SimpleDateFormat fileDateFormat = new java.text.SimpleDateFormat("MM-dd-yyyy");
			String fileDate = fileDateFormat.format(date);
			
			String addedBy=(String)session.getAttribute("userName");
			
			if (fileData != null) {
				List<ClientSelfUploadReport> clientSelfUploadReportList = new ArrayList<ClientSelfUploadReport>();
				for (int i = 0; i < fileData.length; i++) {
					ClientSelfUploadReport clientSelfUploadReport=new ClientSelfUploadReport();
					
					//clientSelfUploadReport.setModifyBy(addedBy);
					//clientSelfUploadReport.setModifyOn(currentTime);
					clientSelfUploadReport.setAddedBy(addedBy);
					clientSelfUploadReport.setAddedOn(currentTime);
					clientSelfUploadReport.setReportIsActive("Y");
					
					String[] fileValue=fileData[i].split("#");
					//System.out.println("fileData[i]:"+fileData[i]);
					Integer clientId=Integer.parseInt(fileValue[1]);
					String fileName=clientId+"_"+fileDate+"_"+fileValue[0];
					String comment="";
					if(fileValue.length>2){
					  comment=fileValue[2];
					}
					ClientMaster clientMaster=new ClientMaster();
					clientMaster.setClientId(clientId);
					clientSelfUploadReport.setClientMaster(clientMaster);
					clientSelfUploadReport.setFilePath(fileName);
					clientSelfUploadReport.setComment(comment);
					clientSelfUploadReport.setEditReportStatus(0);
					clientSelfUploadReportList.add(clientSelfUploadReport);
				}
				clientService.saveSelfClientReport(clientSelfUploadReportList);
			}
			response="true";
		}
		else{
			response="false";
		}
		return response;
	}
	
	@RequestMapping(value = "/getClientSelfUploadReport", method = RequestMethod.POST)
	public @ResponseBody List<ClientSelfUploadReport> getClientSelfUploadReport(@RequestParam("clientId") Integer clientId) {
		List<ClientSelfUploadReport> clientSelfUploadReportList=clientService.getClientSelfUploadReport(clientId);
		return clientSelfUploadReportList;
	}
	
	@RequestMapping(value = "/deleteSelfClientReport", method = RequestMethod.POST)
	public @ResponseBody String deleteSelfClientReport(HttpSession session,HttpServletRequest request,@RequestParam("clientSelfReportId") Integer clientSelfReportId) {
		String currentPageId="7";
		String response="";
		boolean access=AccessControl.isDeleteAccess(request, currentPageId);
		if(access==true){
			//for current time and date
			java.util.Date date = new java.util.Date();
			java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = simpleDateFormat.format(date);
			
			String addedBy=(String)session.getAttribute("userName");
			
			ClientSelfUploadReport clientSelfUploadReport=new ClientSelfUploadReport();
			clientSelfUploadReport.setClientSelfReportId(clientSelfReportId);
			clientSelfUploadReport.setModifyBy(addedBy);
			clientSelfUploadReport.setModifyOn(currentTime);
			clientService.deleteSelfClientReport(clientSelfUploadReport);
			response="true";
		}
		else{
			response="false";
		}
		return response;
	}
	
	@RequestMapping(value = "/getVisitDateList", method = RequestMethod.POST)
	public @ResponseBody List<CheckUpMaster> getVisitDateList(@RequestParam("clientId") Integer clientId) {
		List<CheckUpMaster> checkUpMasterList=checkUpService.getVisitDateList(clientId);
		return checkUpMasterList;
	}
	
	@RequestMapping(value = "/saveNewVisit", method = RequestMethod.POST)
	public @ResponseBody String saveNewVisit(HttpServletRequest request,HttpSession session,@RequestParam("clientId") Integer clientId,@RequestParam("visitDate") String checkUpDate,@RequestParam("visitTypeId") Integer visitTypeId,
		   @RequestParam("centerId") Integer centerId,@RequestParam("testPackage") String testPackage,@RequestParam("testPackageId") Integer testPackageId) {
		String currentPageId="4";
		String response="";
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		if(access==true){
			CheckUpMaster checkUpMaster=new CheckUpMaster();
			checkUpMaster.setCheckUpDate(DateConvertUtil.convertDateToDBFormat(checkUpDate));
			ClientMaster clientMaster=new ClientMaster();
			clientMaster.setClientId(clientId);
			checkUpMaster.setClientMaster(clientMaster);
			
			VisitTypeMaster visitTypeMaster=new VisitTypeMaster();
			visitTypeMaster.setVisitTypeId(visitTypeId);
			checkUpMaster.setVisitTypeMaster(visitTypeMaster);
			
			CentreMaster centreMaster=new CentreMaster();
			centreMaster.setCentreId(centerId);
			checkUpMaster.setCentreMaster(centreMaster);
			
			if(testPackage.equals("Test")){
				TestMaster testMaster=new TestMaster();
				testMaster.setTestId(testPackageId);
				checkUpMaster.setTestMaster(testMaster);
			}
			else{
				PackageMaster packageMaster=new PackageMaster();
				packageMaster.setPackageId(testPackageId);
				checkUpMaster.setPackageMaster(packageMaster);
			}
			checkUpService.saveCheckUpMaster(checkUpMaster,session);
			response="true";
		}
		else{
			response="false";
		}
		return response;
	}
	
	@RequestMapping(value = "/getListOfPackageMaster", method = RequestMethod.POST)
	public @ResponseBody List<PackageMaster> getListOfPackageMaster() {
		List<PackageMaster> packageMasterList=packageService.getListOfPackageMaster();
		return packageMasterList;
	}
	
	@RequestMapping(value = "/getVisitByVisitId", method = RequestMethod.POST)
	public @ResponseBody List<ClientUploadReport> getVisitByVisitId(@RequestParam("clientId") Integer clientId,@RequestParam("visitId") Integer visitId) {
		List<ClientUploadReport> clientUploadReportList=clientService.getVisitByVisitId(clientId, visitId);
		return clientUploadReportList;
	}
	
	@RequestMapping(value = "/clientRegister", method = RequestMethod.POST)
	public @ResponseBody JSONObject clientRegister(@RequestParam("firstName") String firstName,@RequestParam("middleName") String middleName,
			@RequestParam("lastName") String lastName,@RequestParam("hospitalRegNo") String hospitalRegNo,@RequestParam("addressLine1") String addressLine1,@RequestParam("addressLine2") String addressLine2,
			@RequestParam("addressLine3") String addressLine3,@RequestParam("appNo") String appNo,@RequestParam("apYear") String apYear,@RequestParam("birthDate") String birthDate,
			@RequestParam("packageId") Integer packageId,@RequestParam("centerId") Integer centerId,@RequestParam("mobNo") String mobNo,@RequestParam("userType") String userType,
			@RequestParam("checkUpDate") String checkUpDate,@RequestParam("clientGender") String clientGender,@RequestParam("memberId") String memberId,
			@RequestParam("emailId") String emailId,@RequestParam("linkAppNo") Long linkAppNo,@RequestParam("mbRelation") String mbRelation,HttpServletRequest request) {
		
		ClientMaster clientMaster=new ClientMaster();
		clientMaster.setMbRelation(mbRelation);
		clientMaster.setUserType(userType);
		clientMaster.setFirstName(firstName);
		clientMaster.setMiddleName(middleName);
		clientMaster.setLastName(lastName);
		clientMaster.setAddressLine1(addressLine1);
		clientMaster.setAddressLine2(addressLine2);
		clientMaster.setAddressLine3(addressLine3);
		clientMaster.setMobNo(mobNo);
		if(birthDate!=null) {
			birthDate = DateConvertUtil.convertDateToDBFormat(birthDate);
			clientMaster.setClientDOB(birthDate);
		}
		clientMaster.setGender(clientGender);
		clientMaster.setEmailId(emailId);
		if(memberId!=null){
			clientMaster.setMemberId(memberId);
		}
		CentreMaster centreMaster=new CentreMaster();
		centreMaster.setCentreId(centerId);
		PackageMaster packageMaster=new PackageMaster();
		packageMaster.setPackageId(packageId);
		clientMaster.setCentreMaster(centreMaster);
		clientMaster.setPackageMaster(packageMaster);
		
		clientMaster.setIsActive("Y");
		clientMaster.setCount(0);
		clientMaster.setClientLocked(0);
		clientMaster.setClientFullName(firstName+" "+middleName+" "+lastName);
		
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currentTime = simpleDateFormat.format(date);
		java.util.Date clientBirthDate = null;
		java.util.Date currentYear = null;
		
		try {
			if(birthDate!=null && birthDate!=""){
			//clientBirthDate = simpleDateFormat.parse(birthDate);
			//currentYear = simpleDateFormat.parse(currentTime);
			//Integer age = currentYear.getYear() - clientBirthDate.getYear();
			
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");    
				LocalDate today = LocalDate.now();
				LocalDate birthday = LocalDate.parse(birthDate, formatter);

			Period p = Period.between(birthday, today);
			//System.err.println("age in yeras old - "+age);
			System.err.println("age in yeras - "+p.getYears());
			
			Integer age=p.getYears();
			clientMaster.setClientAge(age);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String addAt = dateFormat.format(date);
		HttpSession session=request.getSession();
		String addBy=(String)session.getAttribute("userName");
		clientMaster.setAddBy(addBy);
		clientMaster.setAddAt(addAt);
		
		String clientIdd = clientService.saveClientMaster(clientMaster);
		
		String a=clientIdd.substring(0, 4);
		int dup=0;
		
		int clientId=0;
		if(a.equalsIgnoreCase("dup_"))
		{
			clientId= Integer.parseInt(clientIdd.substring(4));
			dup=1;
			
		}else {
			clientId= Integer.parseInt(clientIdd.substring(4));
			dup=2;
		}
		System.err.println("clientoiiddd==== "+a);
		clientMaster.setClientId(clientId);
		if(checkUpDate!=null && !checkUpDate.equals("")) {
		CheckUpMaster checkUpMaster=new CheckUpMaster();
		checkUpMaster.setCheckUpDate(DateConvertUtil.convertDateToDBFormat(checkUpDate));
		if (hospitalRegNo != null && hospitalRegNo != "") {
			checkUpMaster.setHospitalRegistrationNo(hospitalRegNo);
		}
		checkUpMaster.setCentreMaster(centreMaster);
		checkUpMaster.setPackageMaster(packageMaster);
		checkUpMaster.setClientMaster(clientMaster);
		checkUpMaster.setApAppNo(appNo);
		checkUpMaster.setApYear(apYear);
		
		AppointmentMaster appointmentMaster = new AppointmentMaster();
		appointmentMaster.setApAppDate(DateConvertUtil.convertDateToDBFormat(checkUpDate));
		appointmentMaster.setApAppNo(appNo);
		appointmentMaster.setApCenter(centerId.toString());
		appointmentMaster.setApCenterRegNo(hospitalRegNo);
		appointmentMaster.setApCheckUpDate(DateConvertUtil.convertDateToDBFormat(checkUpDate));
		appointmentMaster.setApProduct(packageId.toString());
		appointmentMaster.setApYear(apYear);
		appointmentMaster.setClientMaster(clientMaster);
		appointmentMaster.setMbYear(apYear);
		appointmentMaster.setMbRelation(mbRelation);
		if(memberId!=null){
			appointmentMaster.setApMemberId(memberId.toString());
			checkUpMaster.setApMemberId(memberId.toString());
		}
		if(linkAppNo!=null){
			appointmentMaster.setApLinkAppNo(linkAppNo.toString());
		}
		if(memberId==null || appNo==null || apYear==null || memberId.equalsIgnoreCase("") || appNo.equalsIgnoreCase("") || apYear.equalsIgnoreCase("")) {
				
		}else {
			//if(dup != 1) {
			Integer appointmentId = appointmentService
					.saveAppointmentMaster(appointmentMaster);
			Integer checkUpId = checkUpService
					.saveCheckUpMaster(checkUpMaster,session);
			//}	
		}
		
		}
		//JSONArray jsonArrayOfClientId=new JSONArray();
		JSONObject clientDetails=new JSONObject();
		clientDetails.put("MB_EHR_NO",clientIdd);
		//clientDetails.put("visitId",checkUpId);
		//clientDetails.put("MB_APP_NO",appNo);
		//clientDetails.put("MB_YEAR",apYear);
		
		/*jsonArrayOfClientId.add(jsonObjectOfClientId);
		WebServiceController webServiceController = (ApplicationContextUtil
				.getApplicationContext())
				.getBean(WebServiceController.class);
		webServiceController.callWebServiceUpdateEHRID(jsonArrayOfClientId);*/
		return clientDetails;
	}
	
	@RequestMapping(value = "/getPatientAnalysis", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getPatientAnalysis(@RequestParam("userId") String userId,@RequestParam("clientId") Integer clientId,
			HttpServletRequest request,HttpServletResponse response,@RequestParam("visitId") Integer visitId) {
		//clientService.getClientByUserId(userId,request);
		JSONArray getPatientParameter=clientService.getPatientAnalysis(clientId,visitId);
		JSONArray analysisComment=clientService.getPatientAnalysisComment(clientId,visitId);
		Map<String , Object> analysisMap=new HashMap<String,Object>();
		analysisMap.put("patientParameter", getPatientParameter);
		analysisMap.put("analysisComment", analysisComment);
		
		ClientMaster clientMaster=clientService.getClientByUserId(clientId,request);		
		if(clientMaster.getClientDOB()!=null && !clientMaster.getClientDOB().equals("NULL") && !clientMaster.getClientDOB().equals("-")){
				String convertedDate=DateConvertUtil.convertDate(clientMaster.getClientDOB());
				analysisMap.put("clientDOB", convertedDate);
				}
		if(clientMaster.getNomineeDOB()!=null && !clientMaster.getNomineeDOB().equals("NULL") && !clientMaster.getNomineeDOB().equals("-")){
				String convertedNomineeDOB=DateConvertUtil.convertDate(clientMaster.getNomineeDOB());
				analysisMap.put("nomineeDOB", convertedNomineeDOB);
				}
		
				analysisMap.put("firstName", clientMaster.getFirstName());
				analysisMap.put("middleName", clientMaster.getMiddleName());
				analysisMap.put("lastName", clientMaster.getLastName());
				analysisMap.put("age", clientMaster.getClientAge());
				analysisMap.put("gender", clientMaster.getGender());
				analysisMap.put("height", clientMaster.getClientHeight());
				analysisMap.put("weight", clientMaster.getClientWeight());
				analysisMap.put("bloodGroup", clientMaster.getBloodGroup());
				analysisMap.put("relationWithNominee", clientMaster.getNomineeRelation());
				analysisMap.put("nomineeFirstName", clientMaster.getNomineeFirstName());
				analysisMap.put("nomineeMiddleName", clientMaster.getNomineeMiddleName());
				analysisMap.put("nomineeLastName", clientMaster.getNomineeLastName());
				analysisMap.put("nomineeAge", clientMaster.getNomineeAge());
				analysisMap.put("address1", clientMaster.getAddressLine1());
				analysisMap.put("address2", clientMaster.getAddressLine2());
				analysisMap.put("address3", clientMaster.getAddressLine3());
				if(clientMaster.getCityMaster()!=null){
					analysisMap.put("city", clientMaster.getCityMaster().getCityName());
				}
				if(clientMaster.getStateMaster()!=null){
					analysisMap.put("state", clientMaster.getStateMaster().getStateName());
				}
				if(clientMaster.getCountryMaster()!=null){
					analysisMap.put("country", clientMaster.getCountryMaster().getCountryName());
				}
				analysisMap.put("pincode", clientMaster.getPinCode());
				analysisMap.put("mobile", clientMaster.getMobNo());
				analysisMap.put("landline", clientMaster.getLandlineNo());
				analysisMap.put("email", clientMaster.getEmailId());
				analysisMap.put("userName", clientMaster.getUserId());
				analysisMap.put("profilePicture", clientMaster.getPhotoUrl());
		
		return analysisMap;
	}
	
	@RequestMapping(value = "/savePatientAnalysis", method = RequestMethod.POST)
	public @ResponseBody String savePatientAnalysis(HttpSession session,@RequestParam("clientId") Integer clientId,
			@RequestParam("comments[]") String[] comments,@RequestParam("classification") String classification,
			//@RequestParam("clientAnalysisComment") String clientAnalysisComment,
			@RequestParam("analysisComments") String analysisComments,
			@RequestParam("visitId") Integer visitId) {
		//for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String addedOn = simpleDateFormat.format(date);
		String addedBy=(String)session.getAttribute("userName");
		
		JSONParser parser = new JSONParser();
		try {
		JSONArray commentArray=new JSONArray();
		for(int i=0;i<comments.length;i++){
			JSONObject commentObject=new JSONObject();
			String comment=comments[i].split("#")[0];
			String testResultId=(comments[i].split("#")[1]).split("_")[1];
			commentObject.put("comment", comment);
			commentObject.put("testResultId", testResultId);
			commentArray.add(commentObject);
		}
		
		JSONArray analysisCommentArray = (JSONArray) parser.parse(analysisComments);
		
		JSONObject object=new JSONObject();
		object.put("visitId", visitId);
		object.put("clientId", clientId);
		object.put("classification", classification);
		//object.put("clientAnalysisComment", clientAnalysisComment);
		object.put("analysisComments", analysisCommentArray);
		object.put("addedBy", addedBy);
		object.put("addedOn", addedOn);
		clientService.saveClientAnalysisComment(object);
		clientService.savePatientAnalysis(commentArray);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "saved";
	}
	
	@RequestMapping(value = "/acceptTermsAndConditions", method = RequestMethod.POST)
	public @ResponseBody String acceptTermsAndConditions(HttpSession session) {
		//for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String addedOn = simpleDateFormat.format(date);
		Integer clientId=(Integer)session.getAttribute("clientId");
		ClientMaster clientMaster=new ClientMaster();
		clientMaster.setClientId(clientId);
		clientMaster.setCount(1);
		clientMaster.setIsAgree(addedOn);
		session.setAttribute("count", 1);
		clientService.acceptTermsAndConditions(clientMaster);
		return "accepted";
	}
	
	@RequestMapping(value = "/mergeClient", method = RequestMethod.POST)
	public @ResponseBody String mergeClient(HttpSession session,HttpServletRequest request,
			@RequestParam("originalClientId") Integer originalClientId,
			@RequestParam("replaceableClientIdArray[]") String[] replaceableClientIdArray) {
		//for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String addedOn = simpleDateFormat.format(date);
		String addedBy=(String)session.getAttribute("userName");
		String remoteIp = (String)request.getRemoteAddr();
		
		JSONObject object=new JSONObject();
		object.put("originalClientId", originalClientId);
		object.put("replaceableClientIdArray", Arrays.toString(replaceableClientIdArray).replaceAll("[\\[\\](){}]",""));
		object.put("addedBy", addedBy);
		object.put("addedOn", addedOn);
		object.put("remoteIp", remoteIp);
		clientService.mergeClient(object);
		return "Client Merge Successfully";
	}
	
	@RequestMapping(value = "/getClientSelfUploadReport", method = RequestMethod.GET)
	public @ResponseBody JSONObject getAnalysisCommentByClientId(@RequestParam("clientId") Integer clientId) {
		JSONObject analysisObject = clientService.getAnalysisCommentByClientId(clientId);
		return analysisObject;
	}
	
	@RequestMapping(value = "/getPatientAnalysisByVisitId", method = RequestMethod.GET)
	public @ResponseBody JSONArray getPatientAnalysisByVisitId(@RequestParam("clientId") Integer clientId,
			@RequestParam("visitId") Integer visitId) {
		JSONArray patientParameter=clientService.getPatientAnalysis(clientId,visitId);
		return patientParameter;
	}
	
	@RequestMapping(value = "/getUserNameByClientId", method = RequestMethod.GET)
	public @ResponseBody String getUserNameByClientId(@RequestParam("clientId") Integer clientId) {
		return clientService.getUserNameByClientId(clientId);
	}
	
	@RequestMapping(value = "/savePackage", method = RequestMethod.POST)
	public @ResponseBody Integer savePackage(@RequestBody PackageMaster packageMaster) {
		java.util.Date date = new java.util.Date();
		packageMaster.setAddAt(date);
		packageMaster.setIsActive('Y');
		Integer repsonse = packageService.savePackage(packageMaster);
		return repsonse;
	}
	
	@RequestMapping(value = "/updatePackage", method = RequestMethod.POST)
	public @ResponseBody Integer updatePackage(@RequestBody PackageMaster packageMaster) {
		java.util.Date date = new java.util.Date();
		packageMaster.setModAt(date);
		packageMaster.setIsActive('Y');
		Integer repsonse = packageService.updatePackage(packageMaster);
		return repsonse;
	}
	
	@RequestMapping(value = "/deletePackage", method = RequestMethod.POST)
	public @ResponseBody Integer deletePackage(@RequestBody PackageMaster packageMaster) {
		java.util.Date date = new java.util.Date();
		packageMaster.setIsActive('N');
		packageMaster.setModAt(date);
		Integer repsonse = packageService.deletePackage(packageMaster);
		return repsonse;
	}
	
	@RequestMapping(value = "/saveCentre", method = RequestMethod.POST)
	public @ResponseBody Integer saveCentre(@RequestBody CentreMaster centreMaster) {
		java.util.Date date = new java.util.Date();
		centreMaster.setAddAt(date);
		centreMaster.setIsActive('Y');
		Integer repsonse = centreService.saveCentre(centreMaster);
		return repsonse;
	}
	
	@RequestMapping(value = "/updateCentre", method = RequestMethod.POST)
	public @ResponseBody Integer updateCentre(@RequestBody CentreMaster centreMaster) {
		java.util.Date date = new java.util.Date();
		centreMaster.setModAt(date);
		centreMaster.setIsActive('Y');
		Integer repsonse = centreService.updateCentre(centreMaster);
		return repsonse;
	}
	
	@RequestMapping(value = "/deleteCentre", method = RequestMethod.POST)
	public @ResponseBody Integer deleteCentre(@RequestBody CentreMaster centreMaster) {
		java.util.Date date = new java.util.Date();
		centreMaster.setIsActive('N');
		centreMaster.setModAt(date);
		Integer repsonse = centreService.deleteCentre(centreMaster);
		return repsonse;
	}
	
	@RequestMapping(value = "/saveTest", method = RequestMethod.POST)
	public @ResponseBody Integer saveTest(@RequestBody TestMaster testMaster) {
		java.util.Date date = new java.util.Date();
		testMaster.setAddAt(date);
		testMaster.setIsActive('Y');
		Integer repsonse = testService.saveTest(testMaster);
		return repsonse;
	}
	
	@RequestMapping(value = "/updateTest", method = RequestMethod.POST)
	public @ResponseBody Integer updateTest(@RequestBody TestMaster testMaster) {
		java.util.Date date = new java.util.Date();
		testMaster.setModAt(date);
		testMaster.setIsActive('Y');
		Integer repsonse = testService.updateTest(testMaster);
		return repsonse;
	}
	
	@RequestMapping(value = "/deleteTest", method = RequestMethod.POST)
	public @ResponseBody Integer deleteTest(@RequestBody TestMaster testMaster) {
		java.util.Date date = new java.util.Date();
		testMaster.setIsActive('N');
		testMaster.setModAt(date);
		Integer repsonse = testService.deleteTest(testMaster);
		return repsonse;
	}

	public JSONArray getPatientAnalysisComment(Integer clientId) {
		Integer visitId = 0;
		JSONArray analysisComments = clientService.getPatientAnalysisComment(clientId,visitId);
		return analysisComments;
	}
	
	@RequestMapping(value = "/dataEntryVerify", method = RequestMethod.POST)
	public @ResponseBody String dataEntryVerify(@RequestBody DataEntryVerification dataEntryVerification, 
			HttpSession session) {
		java.util.Date date = new java.util.Date();
		String addedBy=(String)session.getAttribute("userName");
		dataEntryVerification.setAddedBy(addedBy);
		dataEntryVerification.setAddedOn(date);
		clientService.dataEntryVerify(dataEntryVerification);
		return "Successfully";
	}
	
	@RequestMapping(value = "/getVisitStatus", method = RequestMethod.GET)
	public @ResponseBody Integer getVisitStatus(@RequestParam("visitId") Integer visitId) {
		return clientService.getVisitStatus(visitId);
	}
	
	@RequestMapping(value = "/generateReport", method = RequestMethod.POST)
	public @ResponseBody String generateReport(HttpSession session, @RequestParam("visitId") Integer visitId) {
		String addedByUserId = (String) session.getAttribute("userId");
		CheckUpMaster checkUpMaster = new CheckUpMaster();
		checkUpMaster.setHrGeneratedBy(Integer.parseInt(addedByUserId));
		checkUpMaster.setCheckUpId(visitId);
		return clientService.generateReport(checkUpMaster);
	}
	
	@RequestMapping(value = "/verifyClientUploadReportIndividual",method = RequestMethod.POST)
	public @ResponseBody int verifyClientUploadReportIndividual(HttpServletRequest request,			
			@RequestParam("clientId") int clientId,
			@RequestParam("clientReportName") String clientReportName,
			@RequestParam("clientIdd") int clientIdd,
			@RequestParam("visitId") int visitId) {
		HttpSession session=request.getSession();
		int returnInt=0;
		/*ReportVerification reportVerification=new ReportVerification();
		reportVerification.setApprovedBy((String)session.getAttribute("userName"));
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		reportVerification.setApprovedOn(currentTime);
		reportVerification.setVerifyComment(verifyComment);
		ClientUploadReport clientUploadReport=new ClientUploadReport();
		clientUploadReport.setClientReportLineId(clientReportLineId);
		List<ClientUploadReport> clientUploadReportSet=new ArrayList<ClientUploadReport>();
		clientUploadReportSet.add(clientUploadReport);
		reportVerification.setClientUploadReportSet(clientUploadReportSet);
		int verifyId=clientService.verifyClientUploadReport(reportVerification);*/
		
		//For verified report send email
		ClientMaster clientMaster=clientService.getClientByclientId(clientIdd);
		String cantactFlag="";
		if(clientMaster.getContactFlag().equals(null) || clientMaster.getContactFlag()==null) {
			cantactFlag="N";
		}else {
			cantactFlag=clientMaster.getContactFlag();
		}
		
		if(cantactFlag.equals("Y")) {
			SSLEmail sendMail = new SSLEmail();
			SSLEmail sendMailNew = new SSLEmail();
			try {
				String mailSubject = "Verifed Report";
				String mailData = "PFA";
				EmailRecord emailRecord = new EmailRecord();
				emailRecord.setTopic("Verifed Report Password");
				emailRecord.setSentBy((String)session.getAttribute("userName"));
				emailRecord.setClientId(clientIdd);
				emailRecord.setEmailSubject(mailSubject);
				emailRecord.setEmailMedicalAdvice(mailData);
				emailRecord.setSentTo(clientMaster.getEmailId());
				//sendMail.sendMailWithAttach(clientMaster.getEmailId(), mailData, mailSubject, FilePath.getBasePath()+"/"+clientReportName, emailRecord);
				int result=sendMail.sendMailWithAttach(clientMaster.getEmailId(), mailData, mailSubject, FilePath.getBasePath()+"/"+clientReportName, emailRecord);
				System.err.println("result====  "+result);
				if(result == 1) {
					String mailSubject1 = "Verifed Report Password";
					String mailData1 = "Your password is "+clientMaster.getLastName();
					EmailRecord emailRecord1 = new EmailRecord();
					emailRecord1.setTopic("Verifed Report Password");
					emailRecord1.setSentBy((String)session.getAttribute("userName"));
					emailRecord1.setClientId(clientIdd);
					emailRecord1.setEmailSubject(mailSubject1);
					emailRecord1.setEmailMedicalAdvice(mailData1);
					emailRecord1.setSentTo(clientMaster.getEmailId());
					
					int result1=sendMailNew.sendMailWithPdfPassword(clientMaster.getEmailId(), mailData1, mailSubject1, emailRecord1);
					System.err.println("result1====  "+result);
					if(result1 == 1) {
						String clientIddd=String.valueOf(clientIdd);

						String BeneficiaryId=clientService.BeneficiaryId(clientIddd,visitId);
						System.err.println("contoller--"+BeneficiaryId.toString());
						
						if(BeneficiaryId.toString().equalsIgnoreCase("No Data Available") || BeneficiaryId.toString().equalsIgnoreCase("error")) {
										
							returnInt=-1;
							
						}else {
							SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
							Date date = new Date();					
							String ReportReceiveDate=formatter.format(date);
							System.err.println("else"+BeneficiaryId);
							JSONArray ClientByclientId = new JSONArray();
							JSONObject jsonObjectOfClientId = new JSONObject();
							jsonObjectOfClientId.put("BeneficiaryId", BeneficiaryId);
							jsonObjectOfClientId.put("ReportReceiveDate", ReportReceiveDate);					
							ClientByclientId.add(jsonObjectOfClientId);				
							
							Integer responseCode=webServiceController.BeneficiaryApi(ClientByclientId);
							
							JSONArray mailResponse = new JSONArray();
							JSONObject jsonObjectOfmailResponse = new JSONObject();
							jsonObjectOfmailResponse.put("BeneficiaryId", BeneficiaryId);
							jsonObjectOfmailResponse.put("ReportReceiveDate", ReportReceiveDate);							
							jsonObjectOfmailResponse.put("clientId", clientIdd);
							jsonObjectOfmailResponse.put("visitId", visitId);
							jsonObjectOfmailResponse.put("responseCode", responseCode);	
							jsonObjectOfmailResponse.put("clientReportLineId", clientId);	
							mailResponse.add(jsonObjectOfmailResponse);
							clientService.updatEmailResponseAPI(mailResponse);
							//added by kishor for update Y flag of isSendEmail after send email with report
							int isSendEmail=clientService.isSendEmail(clientId,clientReportName);							
							
							returnInt=1;
						}		
					}
				}
							
				//added by kishor for update Y flag of isSendEmail after send email with report
				/*int isSendEmail=clientService.isSendEmail(clientId,clientReportName);
				String clientIddd=String.valueOf(clientIdd);
				if(isSendEmail==1) {
					String BeneficiaryId=clientService.BeneficiaryId(clientIddd,visitId);
					System.err.println("contoller--"+BeneficiaryId.toString());
					
					if(BeneficiaryId.toString().equalsIgnoreCase("No Data Available") || BeneficiaryId.toString().equalsIgnoreCase("error")) {
									
						returnInt=0;
						//webServiceController.BeneficiaryApi(ClientByclientId);
					}else {
						SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
						Date date = new Date();					
						String ReportReceiveDate=formatter.format(date);
						System.err.println("else"+BeneficiaryId);
						JSONArray ClientByclientId = new JSONArray();
						JSONObject jsonObjectOfClientId = new JSONObject();
						jsonObjectOfClientId.put("BeneficiaryId", BeneficiaryId);
						jsonObjectOfClientId.put("ReportReceiveDate", ReportReceiveDate);					
						ClientByclientId.add(jsonObjectOfClientId);				
						
						webServiceController.BeneficiaryApi(ClientByclientId);
						returnInt=1;
					}		
					
				}*/				
				
			} catch (MessagingException e) {
				e.printStackTrace();
				returnInt= 0;
			}
		}else {
			returnInt=2;
		}
		return returnInt;
	}
	
	@RequestMapping(value = "/getEmailIdToDisplay",method = RequestMethod.POST)
	public @ResponseBody String getEmailIdToDisplay(HttpServletRequest request,			
			@RequestParam("clientId") int clientId) {
		HttpSession session=request.getSession();
		String returnString="";
		String EmailId="";

		ClientMaster clientMaster=clientService.getClientByclientId(clientId);
		//if(clientMaster.getContactFlag().equals("Y")) {
			
			try {
				
				EmailId=clientMaster.getEmailId();
				returnString=EmailId;
			} catch (Exception e) {
				e.printStackTrace();
				returnString= EmailId;
			}
		//}
		return returnString;
	}
	
	public int savelogSaveClientApi(LogSaveClientApi logSaveClientApi) {
		return clientService.savelogSaveClientApi(logSaveClientApi);	
	}
	
	public int savePackageLogs(PackageMasterLogs packageMasterLogs) {
		java.util.Date date = new java.util.Date();
		packageMasterLogs.setAddAt(date);
		return packageService.savePackageLogs(packageMasterLogs);	
	}

	public int saveCentreLogs(CentreMasterLogs centreMasterLogs) {
		java.util.Date date = new java.util.Date();
		centreMasterLogs.setAddAt(date);
		return centreService.saveCentreLogs(centreMasterLogs);
	}
	public int saveTestLogs(TestMasterLog testMasterLog) {
		java.util.Date date = new java.util.Date();
		testMasterLog.setAddAt(date);
		return testService.saveTestLogs(testMasterLog);
	}
	
	@RequestMapping(value = "/saveTestAgainstPackage", method = RequestMethod.POST)
	@ResponseBody
	public String saveTestAgainstPackage(@RequestParam("testDetails") String testDetails) throws ParseException {

		JSONParser parser = new JSONParser();
	/*	JSONArray arr = (JSONArray) parser.parse(testDetails);
		int count= arr.size();
		for(int i=0;i<=count;i++) {
			JsonObject obj= (JsonObject) arr.get(i);
			System.out.println(obj.get("testDescription"));
			
		}*/
		//JSONArray array  = new JSONArray(testDetails);
System.err.println("jkkhkk"+testDetails);

//JSONObject json = new JSONObject();


/*JSONArray jsonArray = null;
jsonArray = (org.json.simple.JSONArray) parser.parse(testDetails);
*/

/*Object obj = parser.parse(testDetails);
JSONArray json1 = (org.json.simple.JSONArray) obj ;

for (int i = 0; i < json1.size(); i++) {
	JSONObject object = (JSONObject) json1.get(i);
	System.out.println(object.get("testDescription"));
	
}*/

int perValue=0;
try {
	
	
	Object obj  = parser.parse(testDetails);
	JSONArray testDetailsArr = new JSONArray();
	testDetailsArr.add(obj);
	
	//System.err.println("analysisCommentArray-----"+testDetailsArr);
	 perValue = packageService.saveTestAgainstPackage(testDetailsArr);

	} catch (ParseException e) {
		e.printStackTrace();
	}

		return (perValue == 1) ? "Test Inserted Successfully"
				: (perValue == 2) ? "Test Updated Successfully"									
										: "Network Error!!";
	}
	
}
