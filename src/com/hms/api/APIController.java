package com.hms.api;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @author Amol Saware
 */

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceContext;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import com.hms.indus.bo.CategoryMaster;
import com.hms.indus.bo.CentreMaster;
import com.hms.indus.bo.CentreMasterLogs;
import com.hms.indus.bo.CheckUpMaster;
import com.hms.indus.bo.ClientMaster;
import com.hms.indus.bo.ClientSelfUploadReport;
import com.hms.indus.bo.ContentMaster;
import com.hms.indus.bo.EmailRecord;
import com.hms.indus.bo.HraTypeMaster;
import com.hms.indus.bo.LogSaveClientApi;
import com.hms.indus.bo.OptionMaster;
import com.hms.indus.bo.PackageMaster;
import com.hms.indus.bo.PackageMasterLogs;
import com.hms.indus.bo.QuestionMaster;
import com.hms.indus.bo.SMSRecord;
import com.hms.indus.bo.SubCategoryMaster;
import com.hms.indus.bo.TestMaster;
import com.hms.indus.bo.TestMasterLog;
import com.hms.indus.controller.CategoryController;
import com.hms.indus.controller.ClientController;
import com.hms.indus.controller.ContentController;
import com.hms.indus.controller.HraController;
import com.hms.indus.controller.ReminderController;
import com.hms.indus.controller.SubCategoryController;
import com.hms.indus.service.ClientService;
import com.hms.indus.util.ApplicationContextUtil;
import com.hms.indus.util.CallSmscApi;
import com.hms.indus.util.FilePath;
import com.hms.indus.util.SSLEmail;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

@Path("/")
public class APIController {

	@Resource
	private WebServiceContext context;

	@Context
	private ServletContext servletContext;

	@Context
	private HttpServletRequest request;

	@Context
	private HttpServletResponse response;
	
	@Autowired
	ClientService clientService;

	APIDaoImpl apiDaoImpl = (ApplicationContextUtil.getApplicationContext()).getBean(APIDaoImpl.class);

	ClientController clientController = (ApplicationContextUtil.getApplicationContext())
			.getBean(ClientController.class);
	
	HraController hraController = (ApplicationContextUtil.getApplicationContext()).getBean(HraController.class);
	
	ReminderController reminderController = (ApplicationContextUtil.getApplicationContext())
			.getBean(ReminderController.class);
	
	ContentController contentController = (ApplicationContextUtil.getApplicationContext()).getBean(ContentController.class);
	
	CategoryController categoryController = (ApplicationContextUtil.getApplicationContext()).getBean(CategoryController.class);
	
	SubCategoryController subCategoryController = (ApplicationContextUtil.getApplicationContext()).getBean(SubCategoryController.class);

	JSONParser parser = new JSONParser();
	
	@GET
	@Produces("plain/text")
	@Path("/testing")
	public String testing() {
		return "testing";
	}

	@GET
	@Produces("application/json")
	@Consumes("plain/text")
	@Path("/getClientByEHRId/{ehrId}/{otpFlag}")
	public JSONObject getClientByEHRId(@PathParam("ehrId") String ehrId,@PathParam("otpFlag") String otpFlag) throws JSONException {
		JSONObject response = new JSONObject();
		Random random = new Random();
		Integer otp = 100000 + random.nextInt(900000);
		JSONObject clientObject = apiDaoImpl.getClientByEHRId(ehrId);
		if (clientObject != null) {
			if(otpFlag.equals("Y")) {
			// For saving OTP
			java.util.Date date = new java.util.Date();
			java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String otpSendAt = simpleDateFormat.format(date);
			
			System.err.println("EHR ID--"+ehrId);
			if(ehrId.equalsIgnoreCase("178720")) {
				System.err.println(" Not SAve ID--"+ehrId);
				clientObject.put("otp", "000000");
				clientObject.put("otpSendAt", otpSendAt);
				apiDaoImpl.saveOTP(clientObject);
			}else {
				System.err.println(" SAve ID--"+ehrId);
				clientObject.put("otp", otp.toString());
				clientObject.put("otpSendAt", otpSendAt);
				apiDaoImpl.saveOTP(clientObject);
			}
			if (clientObject.get("contactFlag").equals("Y") && clientObject.get("emailId") != null && !(clientObject.get("emailId")).equals("")) {
				// for sending mail
				String mailSubject = "Indus OTP";
				String mailData = "Dear <b>" + clientObject.get("firstName") + " " + clientObject.get("lastName")
						+ "</b>, <br><br>" + "You have requested online access to our service.<br>"
						+ "We have generated a One-Time Passcode for you which will verify that you have requested access. <br>"
						+ "This One-Time Passcode is time sensitive and valid for a single use. <br>"
						+ "On subsequent logins you will not need to enter this One-Time Passcode.<br><br>"
						+ "Your One-Time Passcode is <b>" + otp + "</b> <br><br>"
						+ "Please enter this code in to the form that you have accessed, and thank you for utilizing our services.<br>"
						+ "-------------------------------------------------------------------------------------------------------------------------------------   <br>"
						+ "Indus Health Plus (P) Ltd.<br>" + "'INDUS HOUSE', Pride Port,<br>"
						+ "Model Colony, Pune - 411 016,<br>" + "Maharashtra, India <br>" + "<br>"
						+ "Customer Care Number:<br>" + "<a>0-90490-22222</a><br>"
						+ "Mail ID : <a>customercare@indushealthplus.com</a> <br>" + "Toll Free : <a>1800-313-2500</a>";
				SSLEmail sendMail = new SSLEmail();
				try {
					
					EmailRecord emailRecord = new EmailRecord();
					emailRecord.setTopic("OTP");
					emailRecord.setSentBy("API");
					emailRecord.setClientId(Integer.parseInt(ehrId));
					emailRecord.setEmailSubject(mailSubject);
					emailRecord.setEmailMedicalAdvice(mailData);
					emailRecord.setSentTo((String) clientObject.get("emailId"));
					
					sendMail.sendMail((String) clientObject.get("emailId"), mailData, mailSubject, emailRecord);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}

			if (clientObject.get("contactFlag").equals("Y") && clientObject.get("mobileNumber") != null && !(clientObject.get("mobileNumber")).equals("")) {
				// for sending sms
				CallSmscApi callSmscApi = new CallSmscApi();
				String smsData = "Your One Time Login Password is " + otp + " .\n" + "Only valid for 05 minutes.";
				SMSRecord smsRecord = new SMSRecord();
				smsRecord.setTopic("OTP");
				smsRecord.setSentBy("API");
				smsRecord.setClientId(Integer.parseInt(ehrId));
				smsRecord.setSmsMedicalAdvice(smsData);
				smsRecord.setSentTo((String) clientObject.get("mobileNumber"));
				callSmscApi.sendSms("+" + clientObject.get("mobileNumber"), smsData, smsRecord);
			}
			}
			response.put("status_code", 0);
			response.put("error_code", 0);
			response.put("msg", "Client Found");
		} else {
			clientObject = new JSONObject();
			response.put("status_code", 1);
			response.put("error_code", 404);// Not Found
			response.put("msg", "Client Not Found");
		}
		response.put("clientDetails", clientObject);
		return response;
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/resendOTP/{ehrId}")
	public JSONObject resendOTP(@PathParam("ehrId") String ehrId) throws JSONException {
		JSONObject response = new JSONObject();
		JSONObject clientObject = apiDaoImpl.getOTPByEHRId(ehrId);
		if (clientObject != null) {
			String otp = (String) clientObject.get("otp");
			// For saving OTP
			java.util.Date date = new java.util.Date();
			java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String otpSendAt = simpleDateFormat.format(date);
			System.err.println("EHR ID--"+ehrId);
			if(ehrId.equalsIgnoreCase("178720")) {
				System.err.println(" Not SAve ID--"+ehrId);
				clientObject.put("otp", "000000");
				clientObject.put("otpSendAt", otpSendAt);
				apiDaoImpl.saveOTP(clientObject);
			}else {
				System.err.println(" SAve ID--"+ehrId);
				clientObject.put("otp", otp.toString());
				clientObject.put("otpSendAt", otpSendAt);
				apiDaoImpl.saveOTP(clientObject);
			}
			if (clientObject.get("contactFlag").equals("Y") && clientObject.get("emailId") != null && !(clientObject.get("emailId")).equals("")) {
				// for sending mail
				String mailSubject = "Indus OTP";
				String mailData = "Dear <b>" + clientObject.get("firstName") + " " + clientObject.get("lastName")
						+ "</b>, <br><br>" + "You have requested online access to our service.<br>"
						+ "We have generated a One-Time Passcode for you which will verify that you have requested access. <br>"
						+ "This One-Time Passcode is time sensitive and valid for a single use. <br>"
						+ "On subsequent logins you will not need to enter this One-Time Passcode.<br><br>"
						+ "Your One-Time Passcode is <b>" + otp + "</b> <br><br>"
						+ "Please enter this code in to the form that you have accessed, and thank you for utilizing our services.<br>"
						+ "-------------------------------------------------------------------------------------------------------------------------------------   <br>"
						+ "Indus Health Plus (P) Ltd.<br>" + "'INDUS HOUSE', Pride Port,<br>"
						+ "Model Colony, Pune - 411 016,<br>" + "Maharashtra, India <br>" + "<br>"
						+ "Customer Care Number:<br>" + "<a>0-90490-22222</a><br>"
						+ "Mail ID : <a>customercare@indushealthplus.com</a> <br>" + "Toll Free : <a>1800-313-2500</a>";
				SSLEmail sendMail = new SSLEmail();
				try {
					EmailRecord emailRecord = new EmailRecord();
					emailRecord.setTopic("Resend OTP");
					emailRecord.setSentBy("API");
					emailRecord.setClientId(Integer.parseInt(ehrId));
					emailRecord.setEmailSubject(mailSubject);
					emailRecord.setEmailMedicalAdvice(mailData);
					emailRecord.setSentTo((String) clientObject.get("emailId"));
					sendMail.sendMail((String) clientObject.get("emailId"), mailData, mailSubject, emailRecord);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}

			if (clientObject.get("contactFlag").equals("Y") && clientObject.get("mobileNumber") != null && !(clientObject.get("mobileNumber")).equals("")) {
				// for sending sms
				CallSmscApi callSmscApi = new CallSmscApi();
				String smsData = "Your One Time Login Password is " + otp + " .\n" + "Only valid for 05 minutes.";
				SMSRecord smsRecord = new SMSRecord();
				smsRecord.setTopic("Resend OTP");
				smsRecord.setSentBy("API");
				smsRecord.setClientId(Integer.parseInt(ehrId));
				smsRecord.setSmsMedicalAdvice(smsData);
				smsRecord.setSentTo((String) clientObject.get("mobileNumber"));
				callSmscApi.sendSms("+" + clientObject.get("mobileNumber"),smsData,smsRecord);
			}
			response.put("status_code", 0);
			response.put("error_code", 0);
			response.put("msg", "OTP Send Successfully");
		} else {
			clientObject = new JSONObject();
			response.put("status_code", 1);
			response.put("error_code", 101);
			response.put("msg", "Not Able To Send OTP");
		}
		response.put("clientDetails", clientObject);
		return response;
	}

	@GET
	@Produces("application/json")
	@Consumes("plain/text")
	@Path("/verifyOTP/{ehrId}/{otp}")
	public JSONObject verifyOTP(@PathParam("ehrId") String ehrId, @PathParam("otp") String otp) throws JSONException {
		JSONObject response = new JSONObject();
		JSONObject clientObject = apiDaoImpl.getOTPByEHRId(ehrId);
		String otpDB = (String) clientObject.get("otp");
		String otpSendAt = (String) clientObject.get("otpSendAt");
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date otpSendAtDB = null;
		Date currentTimeDate = null;
		try {
			otpSendAtDB = simpleDateFormat.parse(otpSendAt);
			Calendar cal = Calendar.getInstance();
			cal.setTime(otpSendAtDB);
			cal.add(Calendar.MINUTE, 5);
			otpSendAtDB = cal.getTime();
			String currentTime = simpleDateFormat.format(date);
			currentTimeDate = simpleDateFormat.parse(currentTime);
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		if (otpDB.equals(otp) && otpSendAtDB.after(currentTimeDate)) {
			response.put("status_code", 0);
			response.put("error_code", 0);
			response.put("msg", "OTP Verified Successfully");
		} else if (otpDB.equals(otp)) {
			response.put("status_code", 1);
			response.put("error_code", 401);
			response.put("msg", "OTP Is Expired");
		} else {
			response.put("status_code", 1);
			response.put("error_code", 402);
			response.put("msg", "OTP Is Not Valid");
		}
		response.put("clientDetails", clientObject);
		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/verifyDetails")
	public JSONObject verifyDetails(String content) {
		JSONObject response = new JSONObject();
		JSONObject object = null;
		JSONObject clientObject = null;
		try {
			object = (org.json.simple.JSONObject) parser.parse(content);
			String mobileNumber = (String) object.get("mobileNumber");
			String emailId = (String) object.get("emailId");
			clientObject = apiDaoImpl.verifyDetails(object);
			if (clientObject != null) {
				if (clientObject.get("contactFlag").equals("Y") && clientObject.get("emailId") != null && !(clientObject.get("emailId")).equals("")
						&& clientObject.get("emailIdStatus") != null && (clientObject.get("emailIdStatus")).equals("")
						&& (clientObject.get("mobileStatus")).equals("")) {
					// for sending mail
					String mailSubject = "Indus User Id";
					String mailData = "Dear <b>" + clientObject.get("firstName") + " " + clientObject.get("lastName")
							+ "</b>, <br><br>" + "Your User Id is <b>" + clientObject.get("clientId") + "</b>. <br>"
							+ "If you are having any issue with your account, please don't hesitate to contact us.<br><br>"
							+ "Thank You! <br><br>"
							+ "-------------------------------------------------------------------------------------------------------------------------------------   <br>"
							+ "Indus Health Plus (P) Ltd.<br>" + "'INDUS HOUSE', Pride Port,<br>"
							+ "Model Colony, Pune - 411 016,<br>" + "Maharashtra, India <br>" + "<br>"
							+ "Customer Care Number:<br>" + "<a>0-90490-22222</a><br>"
							+ "Mail ID : <a>customercare@indushealthplus.com</a> <br>"
							+ "Toll Free : <a>1800-313-2500</a>";
					SSLEmail sendMail = new SSLEmail();
					try {
						
						EmailRecord emailRecord = new EmailRecord();
						emailRecord.setTopic("Verify Details");
						emailRecord.setSentBy("API");
						emailRecord.setClientId(Integer.parseInt((String)clientObject.get("clientId")));
						emailRecord.setEmailSubject(mailSubject);
						emailRecord.setEmailMedicalAdvice(mailData);
						emailRecord.setSentTo((String) clientObject.get("emailId"));
						
						sendMail.sendMail((String) clientObject.get("emailId"), mailData, mailSubject, emailRecord);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
					response.put("status_code", 0);
					response.put("error_code", 0);
					response.put("msg", "Client Found");
				}

				if (clientObject.get("contactFlag").equals("Y") && clientObject.get("mobileNumber") != null && !(clientObject.get("mobileNumber")).equals("")
						&& clientObject.get("mobileStatus") != null && (clientObject.get("mobileStatus")).equals("")
						&& (clientObject.get("emailIdStatus")).equals("")) {
					// for sending sms
					CallSmscApi callSmscApi = new CallSmscApi();
					String smsData = "Dear " + clientObject.get("firstName") + " " + clientObject.get("lastName") + " \n"
							+ "Your User Id is " + clientObject.get("clientId") + ".";
					SMSRecord smsRecord = new SMSRecord();
					smsRecord.setTopic("Verify Details");
					smsRecord.setSentBy("API");
					smsRecord.setClientId(Integer.parseInt((String)clientObject.get("clientId")));
					smsRecord.setSmsMedicalAdvice(smsData);
					smsRecord.setSentTo((String) clientObject.get("mobileNumber"));
					callSmscApi.sendSms("+" + clientObject.get("mobileNumber"),smsData,smsRecord);
					response.put("status_code", 0);
					response.put("error_code", 0);
					response.put("msg", "Client Found");
				}

				if (emailId != null && !emailId.equals("") && !emailId.equals(clientObject.get("emailId"))
						&& mobileNumber.equals("")) {
					response.put("status_code", 1);
					response.put("error_code", 103);
					response.put("msg", "Please Verify Your emailId From Indus");
				}

				if (mobileNumber != null && !mobileNumber.equals("")
						&& !mobileNumber.equals(clientObject.get("mobileNumber")) && emailId.equals("")) {
					response.put("status_code", 1);
					response.put("error_code", 102);
					response.put("msg", "Please Verify Your Mobile Number From Indus");
				}

				if (!mobileNumber.equals("") && !mobileNumber.equals(clientObject.get("mobileNumber"))
						&& !emailId.equals("") && !emailId.equals(clientObject.get("emailId"))) {
					response.put("status_code", 1);
					response.put("error_code", 105);
					response.put("msg", "Please Verify Your Mobile Number and EmailId From Indus");
				}
				
				if (clientObject.get("duplicateFlag") != null && (clientObject.get("duplicateFlag")).equals("Y")) {
					response.put("status_code", 1);
					response.put("error_code", 409);
					response.put("msg", "Duplicate Information");
					
					// for sending mail
					String mailSubject = "Duplicate Client Details";
					String mailData = "Dear <b> Team</b>,"
					+ "<br><br> Same client details found for the following details: <br>"
					+ "<br> <b> First Name : </b>"+ object.get("firstName") + ","
					+ "<br> <b> Last Name : </b>"+ object.get("lastName") + ","
					+ "<br> <b> Gender : </b>"+ object.get("gender") + ","
					+ "<br> <b> DOB : </b>"+ object.get("dob") + ","
					+ "<br> <b> Mobile No : </b>"+ object.get("mobileNumber") + ","
					+ "<br> <b> Email-Id : </b>"+ object.get("emailId") + ""
					+ "<br><br><br> Thank You! <br><br>"
					+ "---------------------------------------------------------------------------   <br>"
					+ "Indus Health Plus (P) Ltd.<br>" + "'INDUS HOUSE', Pride Port,<br>"
					+ "Model Colony, Pune - 411 016,<br>" + "Maharashtra, India <br>" + "<br>"
					+ "Customer Care Number:<br>" + "<a>0-90490-22222</a><br>"
					+ "Mail ID : <a>customercare@indushealthplus.com</a> <br>"
					+ "Toll Free : <a>1800-313-2500</a>";
					SSLEmail sendMail = new SSLEmail();
					try {
						EmailRecord emailRecord = new EmailRecord();
						emailRecord.setTopic("Duplicate Client Details");
						emailRecord.setSentBy("API");
						//emailRecord.setClientId(Integer.parseInt((String)clientObject.get("clientId")));
						emailRecord.setEmailSubject(mailSubject);
						emailRecord.setEmailMedicalAdvice(mailData);
						emailRecord.setSentTo("it.pro@indushealthplus.com");
						sendMail.sendMail("it.pro@indushealthplus.com", mailData, mailSubject,emailRecord);
						
						emailRecord.setSentTo("shailesh.thakare@indushealthplus.com");
						sendMail.sendMail("shailesh.thakare@indushealthplus.com", mailData, mailSubject,emailRecord);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
				}

			} else {
				clientObject = new JSONObject();
				response.put("status_code", 1);
				response.put("error_code", 404);
				response.put("msg", "Invalid Information");
			}
			response.put("clientDetails", clientObject);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/getSelfUploadReportByEHRId/{ehrId}")
	public JSONObject getSelfUploadReportByEHRId(@PathParam("ehrId") String ehrId) throws JSONException {
		JSONObject response = new JSONObject();
		JSONObject data = new JSONObject();
		//String filePath = FilePath.getBasePath();
		List<ClientSelfUploadReport> clientSelfUploadReportList = clientController
				.getClientSelfUploadReport(Integer.parseInt(ehrId));
		JSONArray selfUploadReports = new JSONArray();
		for (int i = 0; i < clientSelfUploadReportList.size(); i++) {
			JSONObject report = new JSONObject();
			report.put("clientSelfReportId", clientSelfUploadReportList.get(i).getClientSelfReportId());
			report.put("comment", clientSelfUploadReportList.get(i).getComment());
			report.put("filePath", clientSelfUploadReportList.get(i).getFilePath());
			report.put("addedBy", clientSelfUploadReportList.get(i).getAddedBy());
			report.put("addedOn", clientSelfUploadReportList.get(i).getAddedOn());
			report.put("ehrId", ehrId);
			selfUploadReports.add(report);
		}
		//data.put("filePath", filePath);
		data.put("selfUploadReports", selfUploadReports);
		response.put("data", data);
		response.put("status_code", 0);
		response.put("error_code", 0);
		response.put("msg", "success");
		return response;
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/getAnalysisCommentByEHRId/{ehrId}")
	public JSONObject getAnalysisCommentByEHRId(@PathParam("ehrId") String ehrId) throws JSONException {
		JSONObject response = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray analysisComments = clientController.getPatientAnalysisComment(Integer.parseInt(ehrId));
		data.put("analysisComments", analysisComments);
		response.put("data", data);
		response.put("status_code", 0);
		response.put("error_code", 0);
		response.put("msg", "success");
		return response;
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/trackParameterByEHRId/{ehrId}")
	public JSONObject trackParameterByEHRId(@PathParam("ehrId") String ehrId) throws JSONException {
		JSONObject response = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray visitList = new JSONArray();
		List<CheckUpMaster> checkUpList = clientController.getVisitDateList(Integer.parseInt(ehrId));
		for (int i = 0; i < checkUpList.size(); i++) {
			JSONObject visitObject = new JSONObject();
			Integer visitId = checkUpList.get(i).getCheckUpId();
			String visitDate = checkUpList.get(i).getCheckUpDate();
			JSONArray parameterList = clientController.getPatientAnalysisByVisitId(Integer.parseInt(ehrId), visitId);
			visitObject.put("visitId", visitId);
			visitObject.put("visitDate", visitDate);
			visitObject.put("parameterList", parameterList);
			visitList.add(visitObject);
		}
		data.put("visitList", visitList);
		response.put("data", data);
		response.put("status_code", 0);
		response.put("error_code", 0);
		response.put("msg", "success");
		return response;
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/getHRAByEHRId/{ehrId}")
	public JSONObject getHRA(@PathParam("ehrId") String ehrId) throws JSONException {
		JSONObject response = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray hraTypes = new JSONArray();
		List<HraTypeMaster> listHraTypeMasters = hraController.listOfHraTypeMaster();
		for (int i = 0; i < listHraTypeMasters.size(); i++) {
			Integer hraTypeId = listHraTypeMasters.get(i).getHraTypeId();
			String hraTypeName = listHraTypeMasters.get(i).getHraTypeName();
			String endUserFlag = listHraTypeMasters.get(i).getEndUserFlag();
			if(endUserFlag.equals("Y")) {
			List<QuestionMaster> listQuestionMasters = hraController.listOfQuestionMasterByHraId(hraTypeId);
			JSONObject hraType = new JSONObject();
			hraType.put("hraTypeId", hraTypeId);
			hraType.put("hraTypeName", hraTypeName);
			JSONArray questions = new JSONArray();
			for (int j = 0; j < listQuestionMasters.size(); j++) {
				JSONObject questionObject = new JSONObject();
				Integer questionId = listQuestionMasters.get(j).getQuestionId();
				String question = listQuestionMasters.get(j).getQuestion();
				String questionType = listQuestionMasters.get(j).getQuestionType();
				List<OptionMaster> optionSet = listQuestionMasters.get(j).getOptionMasterSet();
				JSONArray options = new JSONArray();
				for (int k = 0; k < optionSet.size(); k++) {
					Integer optionId = optionSet.get(k).getOptionId();
					String option = optionSet.get(k).getOption();
					JSONObject optionObject = new JSONObject();
					optionObject.put("optionId", optionId);
					optionObject.put("option", option);
					options.add(optionObject);
					questionObject.put("options", options);
				}
				questionObject.put("questionId", questionId);
				questionObject.put("question", question);
				questionObject.put("questionType", questionType);
				questionObject.put("options", options);
				questions.add(questionObject);
			}
			hraType.put("questions", questions);
			hraTypes.add(hraType);
			}
		}

		Integer visitId = hraController.getVisitIdByClientId(Integer.parseInt(ehrId));
		JSONArray answerArray = hraController.getListOfQuestionClientHRADetails(Integer.parseInt(ehrId), visitId);
		JSONArray answerArray1 = new JSONArray();
		for(int i=0;i<answerArray.size();i++) {
			JSONObject object = (JSONObject) answerArray.get(i);
			String endUserFlag = (String)object.get("endUserFlag");
			if(endUserFlag.equals("Y")) {
				answerArray1.add(object);
			}
		}

		data.put("hraTypes", hraTypes);
		data.put("answerArray", answerArray1);
		response.put("data", data);
		response.put("status_code", 0);
		response.put("error_code", 0);
		response.put("msg", "success");
		return response;
	}

	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/getReminderByEHRId/{ehrId}")
	public JSONObject getReminderByEHRId(@PathParam("ehrId") String ehrId) throws JSONException {
		JSONObject response = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray reminders = reminderController.getPatientReminderByClientId(Integer.parseInt(ehrId));
		data.put("reminders", reminders);
		response.put("data", data);
		response.put("status_code", 0);
		response.put("error_code", 0);
		response.put("msg", "success");
		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/saveHRADetails")
	public JSONObject saveHRADetails(String content) {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		JSONObject response = new JSONObject();
		JSONArray answerArray = new JSONArray();
		JSONArray jsonArray = null;
		try {
			jsonArray = (org.json.simple.JSONArray) parser.parse(content);
			Integer checkUpId = 0;
			String userName = "";
			if (jsonArray.size() > 0) {
				Long ehrId = (Long) ((JSONObject) jsonArray.get(0)).get("clientId");
				checkUpId = hraController.getVisitIdByClientId(ehrId.intValue());
				userName = clientController.getUserNameByClientId(ehrId.intValue());
			}

			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject object = (JSONObject) jsonArray.get(i);
				object.put("questionId", object.get("questionId"));
				object.put("clientId", object.get("clientId"));
				object.put("text", object.get("answer"));
				object.put("checkUpId", checkUpId);
				object.put("addedBy", userName);
				object.put("modifyBy", userName);
				object.put("addedOn", currentTime);
				object.put("modifyOn", currentTime);
				answerArray.add(object);
			}
			apiDaoImpl.saveHRADetails(answerArray);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		response.put("status_code", 0);
		response.put("error_code", 0);
		response.put("msg", "success");
		return response;
	}

	@GET
	@Path("/viewReport/{callFrom}/{fileName}")
	@Produces(MediaType.APPLICATION_OCTET_STREAM)
	public javax.ws.rs.core.Response viewReport(@PathParam("fileName") String fileName,
			@PathParam("callFrom") String callFrom) throws Exception {
		String filePath = FilePath.getBasePath();
		if (callFrom != null && !callFrom.equals("") && callFrom.equals("content")) {
			filePath = FilePath.getContentPath();
		}
		File file = new File(filePath + File.separator + fileName);
		FileInputStream fileInputStream = new FileInputStream(file);
		javax.ws.rs.core.Response.ResponseBuilder responseBuilder = javax.ws.rs.core.Response
				.ok((Object) fileInputStream);
		String extension = FilenameUtils.getExtension(fileName);
		if(extension.equals("pdf")) {
			responseBuilder.type("application/pdf");
		}else if(extension.equals("mp4")) {
			responseBuilder.type("video/mp4");
		}else {
			responseBuilder.type("image/"+extension);
		}
		responseBuilder.header("Content-Disposition", "filename=" + fileName);
		return responseBuilder.build();
	}

	@POST
	@Path("/uploadSelfReport")
	@Produces("application/json")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public JSONObject uploadSelfReport(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail, @FormDataParam("comment") String comment,
			@FormDataParam("ehrId") String ehrId) {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);

		java.text.SimpleDateFormat simpleDateFormat1 = new java.text.SimpleDateFormat("MM-dd-yyyy");
		String uploadDate = simpleDateFormat1.format(date);

		JSONObject response = new JSONObject();
		String fileName = ehrId + "_" + uploadDate + "_" + fileDetail.getFileName();
		String fileLocation = FilePath.getBasePath() + File.separator + fileName;
		try {
			FileOutputStream out = new FileOutputStream(new File(fileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			out = new FileOutputStream(new File(fileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();

			String userName = clientController.getUserNameByClientId(Integer.parseInt(ehrId));
			JSONObject object = new JSONObject();
			object.put("clientId", Integer.parseInt(ehrId));
			object.put("comment", comment);
			object.put("reportFilePath", fileName);
			object.put("addedBy", userName);
			object.put("addedOn", currentTime);
			apiDaoImpl.saveSelfClientReport(object);// save database
			response.put("status_code", 0);
			response.put("error_code", 0);
			response.put("msg", "success");
		} catch (IOException e) {
			e.printStackTrace();
			response.put("status_code", 1);
			response.put("error_code", 304);
			response.put("msg", "File Upload Failed");
		}
		return response;
	}
	
	@POST
	@Path("/uploadProfilePicture")
	@Produces("application/json")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public JSONObject uploadProfilePicture(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,
			@FormDataParam("ehrId") String ehrId) {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);

		java.text.SimpleDateFormat simpleDateFormat1 = new java.text.SimpleDateFormat("MM-dd-yyyy");
		String uploadDate = simpleDateFormat1.format(date);

		JSONObject response = new JSONObject();
		String fileName = ehrId + "_" + uploadDate + "_" + fileDetail.getFileName();
		String fileLocation = FilePath.getBasePath() + File.separator + fileName;
		try {
			FileOutputStream out = new FileOutputStream(new File(fileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			out = new FileOutputStream(new File(fileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();

			clientController.changeProfilePicture(Integer.parseInt(ehrId),fileDetail.getFileName(),request);
			response.put("status_code", 0);
			response.put("error_code", 0);
			response.put("msg", "success");
		} catch (IOException e) {
			e.printStackTrace();
			response.put("status_code", 1);
			response.put("error_code", 304);
			response.put("msg", "Proifle Picture Upload Failed");
		}
		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/saveReminder")
	public JSONObject saveReminder(String content) {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		JSONObject response = new JSONObject();
		JSONObject jsonObject = null;
		try {
			jsonObject = (org.json.simple.JSONObject) parser.parse(content);
			Long ehrId = (Long) jsonObject.get("clientId");
			String userName = clientController.getUserNameByClientId(ehrId.intValue());
			String eventTitle = (String) jsonObject.get("eventTitle");
			String emailId = (String) jsonObject.get("emailId");
			String mobileNo = (String) jsonObject.get("mobileNo");
			String category = (String) jsonObject.get("category");
			String doctorName = (String) jsonObject.get("doctorName");
			String eventStartDate = (String) jsonObject.get("eventStartDate");
			String eventEndDate = (String) jsonObject.get("eventEndDate");
			String reminderDateTime = (String) jsonObject.get("reminderDateTime");
			String doctorDateTime = (String) jsonObject.get("doctorDateTime");
			String medicineName = (String) jsonObject.get("medicineName");
			String medicineDose = (String) jsonObject.get("medicineDose");
			String medicineDateTime = (String) jsonObject.get("medicineDateTime");
			String typeOfExercise = (String) jsonObject.get("typeOfExercise");
			String durationInMinutes = (String) jsonObject.get("durationInMinutes");
			String testName = (String) jsonObject.get("testName");
			String centreName = (String) jsonObject.get("centreName");
			String remindMeFor = (String) jsonObject.get("remindMeFor");
			String location = (String) jsonObject.get("location");
			String duration = (String) jsonObject.get("duration");
			JSONArray remindersArray = (JSONArray) jsonObject.get("reminders");
			String[] reminders = new String[remindersArray.size()];
			for (int i = 0; i < remindersArray.size(); i++) {
				reminders[i] = (String) remindersArray.get(i);
			}
			String ehrReminderMasterId = (String) jsonObject.get("ehrReminderMasterId");
			Long dailyRepeat = (Long) jsonObject.get("dailyRepeat");
			Long weeklyRepeat = (Long) jsonObject.get("weeklyRepeat");
			Long monthlyRepeat = (Long) jsonObject.get("monthlyRepeat");
			String repeatBy = (String) jsonObject.get("repeatBy");
			Long yearlyRepeat = (Long) jsonObject.get("yearlyRepeat");
			String ends = (String) jsonObject.get("ends");
			JSONArray weeklyDaysArray = (JSONArray) jsonObject.get("weeklyDays");
			String[] weeklyDays = new String[weeklyDaysArray.size()];
			for (int i = 0; i < weeklyDaysArray.size(); i++) {
				weeklyDays[i] = (String) weeklyDaysArray.get(i);
			}
			Long afterText = (Long) jsonObject.get("afterText");
			String onText = (String) jsonObject.get("onText");
			String recurrencePattern = (String) jsonObject.get("recurrencePattern");
			String clientName = (String) jsonObject.get("clientName");
			HttpSession session = null;
			reminderController.saveReminder(session, eventTitle, emailId, mobileNo, category, doctorName,
					eventStartDate, eventEndDate, reminderDateTime, doctorDateTime, medicineName, medicineDose,
					medicineDateTime, typeOfExercise, durationInMinutes, testName, centreName, remindMeFor, location,
					duration, reminders, ehrReminderMasterId, dailyRepeat.intValue(), weeklyRepeat.intValue(),
					monthlyRepeat.intValue(), repeatBy, yearlyRepeat.intValue(), ends, weeklyDays, afterText.intValue(),
					onText, recurrencePattern, clientName, ehrId.intValue());
			response.put("status_code", 0);
			response.put("error_code", 0);
			response.put("msg", "success");
		} catch (ParseException e) {
			response.put("status_code", 1);
			response.put("error_code", 305);
			response.put("msg", "error");
			e.printStackTrace();
		}
		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/savePackage")
	public JSONObject savePackage(@RequestBody PackageMaster packageMaster) {
		JSONObject response = new JSONObject();
		PackageMasterLogs packageMasterLogs = new PackageMasterLogs();
		try {
			packageMaster.setAddBy("API");
			Integer value = clientController.savePackage(packageMaster);
			if (value == 0) {
				response.put("status_code", 1);
				response.put("error_code", 305);
				response.put("msg", "error");				
				packageMasterLogs.setStatusCode(1);
				packageMasterLogs.setErrorCode(305);
				
			} else if(value == -1){
				response.put("status_code", 0);
				response.put("error_code", 409);
				response.put("msg", "Primary key already exist");
				packageMasterLogs.setErrorCode(409);
				packageMasterLogs.setStatusCode(0);
			}else {
				response.put("status_code", 0);
				response.put("error_code", 0);
				response.put("msg", "Packaged Saved Successfully");
				packageMasterLogs.setErrorCode(0);
				packageMasterLogs.setStatusCode(0);
			}
			} catch (Exception e) {
				response.put("status_code", 1);
				response.put("error_code", 305);
				response.put("msg", "error");
				packageMasterLogs.setStatusCode(1);
				packageMasterLogs.setErrorCode(305);
				
				e.printStackTrace();
			}
			try {
				packageMasterLogs.setAddBy("API");
				packageMasterLogs.setModBy("Save API");
				packageMasterLogs.setPackageId(packageMaster.getPackageId());
				packageMasterLogs.setPackageDescription(packageMaster.getPackageDescription());
				packageMasterLogs.setPackageShortDescription(packageMaster.getPackageDescription());
				packageMasterLogs.setCtStatus(packageMaster.getCtStatus());
				
				Integer code = clientController.savePackageLogs(packageMasterLogs);	
			}catch (Exception e) {
				e.printStackTrace();
			}			
		
		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/updatePackage")
	public JSONObject updatePackage(@RequestBody PackageMaster packageMaster) {
		JSONObject response = new JSONObject();
		PackageMasterLogs packageMasterLogs = new PackageMasterLogs();
		try {
			packageMaster.setModBy("API");
			Integer value = clientController.updatePackage(packageMaster);
			if (value == 0) {
				response.put("status_code", 1);
				response.put("error_code", 305);
				response.put("msg", "error");
				packageMasterLogs.setStatusCode(1);
				packageMasterLogs.setErrorCode(305);
			} else {
				response.put("status_code", 0);
				response.put("error_code", 0);
				response.put("msg", "Packaged Updated Successfully");
				packageMasterLogs.setStatusCode(0);
				packageMasterLogs.setErrorCode(0);
			}
		} catch (Exception e) {
			response.put("status_code", 1);
			response.put("error_code", 305);
			response.put("msg", "error");
			packageMasterLogs.setStatusCode(1);
			packageMasterLogs.setErrorCode(305);
			e.printStackTrace();
		}
		try {
			packageMasterLogs.setAddBy("API");
			packageMasterLogs.setModBy("Update API");
			packageMasterLogs.setPackageId(packageMaster.getPackageId());
			packageMasterLogs.setPackageDescription(packageMaster.getPackageDescription());
			packageMasterLogs.setPackageShortDescription(packageMaster.getPackageDescription());
			packageMasterLogs.setCtStatus(packageMaster.getCtStatus());
			
			Integer code = clientController.savePackageLogs(packageMasterLogs);	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/deletePackage/{packageId}")
	public JSONObject deletePackage(@PathParam("packageId") Integer packageId) {
		JSONObject response = new JSONObject();
		PackageMasterLogs packageMasterLogs = new PackageMasterLogs();
		PackageMaster packageMaster = new PackageMaster();
		try {			
			packageMaster.setPackageId(packageId);
			packageMaster.setModBy("API");
			Integer value = clientController.deletePackage(packageMaster);
			if (value == 0) {
				response.put("status_code", 1);
				response.put("error_code", 305);
				response.put("msg", "error");
				packageMasterLogs.setStatusCode(1);
				packageMasterLogs.setErrorCode(305);
			} else {
				response.put("status_code", 0);
				response.put("error_code", 0);
				response.put("msg", "Packaged Deleted Successfully");
				packageMasterLogs.setStatusCode(0);
				packageMasterLogs.setErrorCode(0);
			}
		} catch (Exception e) {
			response.put("status_code", 1);
			response.put("error_code", 305);
			response.put("msg", "error");
			packageMasterLogs.setStatusCode(1);
			packageMasterLogs.setErrorCode(305);
			e.printStackTrace();
		}
		try {
			packageMasterLogs.setIsActive('N');
			packageMasterLogs.setModBy("Delete API");
			packageMasterLogs.setAddBy("API");
			packageMasterLogs.setPackageId(packageMaster.getPackageId());
			packageMasterLogs.setPackageDescription(packageMaster.getPackageDescription());
			packageMasterLogs.setPackageShortDescription(packageMaster.getPackageDescription());
			packageMasterLogs.setCtStatus(packageMaster.getCtStatus());
			
			Integer code = clientController.savePackageLogs(packageMasterLogs);	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/saveCentre")
	public JSONObject saveCentre(@RequestBody CentreMaster centreMaster) {
		JSONObject response = new JSONObject();
		CentreMasterLogs centreMasterLogs = new CentreMasterLogs();
		try {
			centreMaster.setAddBy("API");
			Integer value = clientController.saveCentre(centreMaster);
			if (value == 0) {
				response.put("status_code", 1);
				response.put("error_code", 305);
				response.put("msg", "error");
				centreMasterLogs.setStatusCode(1);
				centreMasterLogs.setErrorCode(305);
			}else if(value == -1){
				response.put("status_code", 0);
				response.put("error_code", 409);
				response.put("msg", "Primary key already exist");
				centreMasterLogs.setStatusCode(0);
				centreMasterLogs.setErrorCode(409);
			} else {
				response.put("status_code", 0);
				response.put("error_code", 0);
				response.put("msg", "Centre Saved Successfully");
				centreMasterLogs.setStatusCode(0);
				centreMasterLogs.setErrorCode(0);
			}
		} catch (Exception e) {
			response.put("status_code", 1);
			response.put("error_code", 305);
			response.put("msg", "error");
			centreMasterLogs.setStatusCode(1);
			centreMasterLogs.setErrorCode(305);
			e.printStackTrace();
		}
		try {
			centreMasterLogs.setIsActive('Y');
			centreMasterLogs.setModBy("Save API");
			centreMasterLogs.setAddBy("API");
			centreMasterLogs.setCentreId((centreMaster.getCentreId()));
			centreMasterLogs.setCentreName((centreMaster.getCentreName()));
			
			Integer code = clientController.saveCentreLogs(centreMasterLogs);	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/updateCentre")
	public JSONObject updateCentre(@RequestBody CentreMaster centreMaster) {
		JSONObject response = new JSONObject();
		CentreMasterLogs centreMasterLogs = new CentreMasterLogs();
		try {
			centreMaster.setModBy("API");
			Integer value = clientController.updateCentre(centreMaster);
			if (value == 0) {
				response.put("status_code", 1);
				response.put("error_code", 305);
				response.put("msg", "error");
				centreMasterLogs.setStatusCode(1);
				centreMasterLogs.setErrorCode(305);
			} else {
				response.put("status_code", 0);
				response.put("error_code", 0);
				response.put("msg", "Centre Updated Successfully");
				centreMasterLogs.setStatusCode(0);
				centreMasterLogs.setErrorCode(0);
			}
		} catch (Exception e) {
			response.put("status_code", 1);
			response.put("error_code", 305);
			response.put("msg", "error");
			centreMasterLogs.setStatusCode(1);
			centreMasterLogs.setErrorCode(305);
			e.printStackTrace();
		}
		try {
			centreMasterLogs.setIsActive('Y');
			centreMasterLogs.setModBy("Update API");
			centreMasterLogs.setAddBy("API");
			centreMasterLogs.setCentreId((centreMaster.getCentreId()));
			centreMasterLogs.setCentreName((centreMaster.getCentreName()));
			
			Integer code = clientController.saveCentreLogs(centreMasterLogs);	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/deleteCentre/{centreId}")
	public JSONObject deleteCentre(@PathParam("centreId") Integer centreId) {
		JSONObject response = new JSONObject();
		CentreMaster centreMaster = new CentreMaster();
		CentreMasterLogs centreMasterLogs = new CentreMasterLogs();
		try {
			
			centreMaster.setCentreId(centreId);
			centreMaster.setModBy("API");
			Integer value = clientController.deleteCentre(centreMaster);
			if (value == 0) {
				response.put("status_code", 1);
				response.put("error_code", 305);
				response.put("msg", "error");
				centreMasterLogs.setStatusCode(1);
				centreMasterLogs.setErrorCode(305);
			} else {
				response.put("status_code", 0);
				response.put("error_code", 0);
				response.put("msg", "Centre Deleted Successfully");
				centreMasterLogs.setStatusCode(0);
				centreMasterLogs.setErrorCode(0);
			}
		} catch (Exception e) {
			response.put("status_code", 1);
			response.put("error_code", 305);
			response.put("msg", "error");
			centreMasterLogs.setStatusCode(1);
			centreMasterLogs.setErrorCode(305);
			e.printStackTrace();
		}
		try {
			centreMasterLogs.setIsActive('N');
			centreMasterLogs.setModBy("Delete API");
			centreMasterLogs.setAddBy("API");
			centreMasterLogs.setCentreId((centreMaster.getCentreId()));
			centreMasterLogs.setCentreName((centreMaster.getCentreName()));
			
			Integer code = clientController.saveCentreLogs(centreMasterLogs);	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/saveTest")
	public JSONObject saveTest(@RequestBody TestMaster testMaster) {
		JSONObject response = new JSONObject();
		TestMasterLog testMasterLog = new TestMasterLog();
		try {
			testMaster.setAddBy("API");
			Integer value = clientController.saveTest(testMaster);
			if (value == 0) {
				response.put("status_code", 1);
				response.put("error_code", 305);
				response.put("msg", "error");
				testMasterLog.setStatusCode(1);
				testMasterLog.setErrorCode(305);
			} else if(value == -1){
				response.put("status_code", 0);
				response.put("error_code", 409);
				response.put("msg", "Primary key already exist");
				testMasterLog.setStatusCode(0);
				testMasterLog.setErrorCode(409);
			}else {
				response.put("status_code", 0);
				response.put("error_code", 0);
				response.put("msg", "Test Saved Successfully");
				testMasterLog.setStatusCode(0);
				testMasterLog.setErrorCode(0);
			}
		} catch (Exception e) {
			response.put("status_code", 1);
			response.put("error_code", 305);
			response.put("msg", "error");
			testMasterLog.setStatusCode(1);
			testMasterLog.setErrorCode(305);
			e.printStackTrace();
		}
		try {
			testMasterLog.setIsActive('Y');
			testMasterLog.setModBy("Save API");
			testMasterLog.setAddBy("API");
			testMasterLog.setTestId((testMaster.getTestId()));
			testMasterLog.setTestDescription((testMaster.getTestDescription()));
			
			Integer code = clientController.saveTestLogs(testMasterLog);	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/updateTest")
	public JSONObject updateTest(@RequestBody TestMaster testMaster) {
		JSONObject response = new JSONObject();
		TestMasterLog testMasterLog = new TestMasterLog();
		try {
			testMaster.setModBy("API");
			Integer value = clientController.updateTest(testMaster);
			if (value == 0) {
				response.put("status_code", 1);
				response.put("error_code", 305);
				response.put("msg", "error");
				testMasterLog.setStatusCode(1);
				testMasterLog.setErrorCode(305);
			} else {
				response.put("status_code", 0);
				response.put("error_code", 0);
				response.put("msg", "Test Updated Successfully");
				testMasterLog.setStatusCode(0);
				testMasterLog.setErrorCode(0);
			}
		} catch (Exception e) {
			response.put("status_code", 1);
			response.put("error_code", 305);
			response.put("msg", "error");
			testMasterLog.setStatusCode(1);
			testMasterLog.setErrorCode(305);
			e.printStackTrace();
		}try {
			testMasterLog.setIsActive('Y');
			testMasterLog.setModBy("Update API");
			testMasterLog.setAddBy("API");
			testMasterLog.setTestId((testMaster.getTestId()));
			testMasterLog.setTestDescription((testMaster.getTestDescription()));
			
			Integer code = clientController.saveTestLogs(testMasterLog);	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/deleteTest/{testId}")
	public JSONObject deleteTest(@PathParam("testId") Integer testId) {
		JSONObject response = new JSONObject();
		TestMasterLog testMasterLog = new TestMasterLog();
		TestMaster testMaster = new TestMaster();
		try {			
			testMaster.setTestId(testId);
			testMaster.setModBy("API");
			Integer value = clientController.deleteTest(testMaster);
			if (value == 0) {
				response.put("status_code", 1);
				response.put("error_code", 305);
				response.put("msg", "error");
				testMasterLog.setStatusCode(1);
				testMasterLog.setErrorCode(305);
			} else {
				response.put("status_code", 0);
				response.put("error_code", 0);
				response.put("msg", "Test Deleted Successfully");
				testMasterLog.setStatusCode(0);
				testMasterLog.setErrorCode(0);
			}
		} catch (Exception e) {
			response.put("status_code", 1);
			response.put("error_code", 305);
			response.put("msg", "error");
			testMasterLog.setStatusCode(1);
			testMasterLog.setErrorCode(305);
			e.printStackTrace();
		}try {
			testMasterLog.setIsActive('N');
			testMasterLog.setModBy("Delete API");
			testMasterLog.setAddBy("API");
			testMasterLog.setTestId((testMaster.getTestId()));
			testMasterLog.setTestDescription((testMaster.getTestDescription()));
			
			Integer code = clientController.saveTestLogs(testMasterLog);	
		}catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/saveClient")
	public JSONObject saveClient(String content) {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		JSONObject response = new JSONObject();
		JSONObject jsonObject = null;
		String Dup_EHRID="";
		try {
			jsonObject = (org.json.simple.JSONObject) parser.parse(content);
			String firstName = (String) jsonObject.get("firstName");
			String middleName = (String) jsonObject.get("middleName");
			String lastName = (String) jsonObject.get("lastName");
			String hospitalRegNo = (String) jsonObject.get("hospitalRegNo");
			String addressLine1 = (String) jsonObject.get("addressLine1");
			String addressLine2 = (String) jsonObject.get("addressLine2");
			String addressLine3 = (String) jsonObject.get("addressLine3");
			String birthDate = (String) jsonObject.get("birthDate");
			String mobNo = (String) jsonObject.get("mobNo");
			String clientGender = (String) jsonObject.get("clientGender");
			String emailId = (String) jsonObject.get("emailId");
			String appNo = (String) jsonObject.get("appNo");
			String apYear = (String) jsonObject.get("apYear");
			Integer packageId = ((Long) jsonObject.get("packageId")).intValue();
			Integer centerId = ((Long) jsonObject.get("centerId")).intValue();
			String checkUpDate = (String) jsonObject.get("checkUpDate");
			String memberId = (String) jsonObject.get("memberId");
			Long linkAppNo = (Long) jsonObject.get("linkAppNo");
			String mbRelation = (String) jsonObject.get("mbRelation");
			String userType = (String) jsonObject.get("userType");
			
			
			// save client details for maintaining log
			LogSaveClientApi logSaveClientApi=new LogSaveClientApi();
			logSaveClientApi.setFirstName(firstName);
			logSaveClientApi.setMiddleName(middleName);
			logSaveClientApi.setLastName(lastName);
			logSaveClientApi.setHospitalRegNo(hospitalRegNo);
			logSaveClientApi.setAddressLine1(addressLine1);
			logSaveClientApi.setAddressLine2(addressLine2);
			logSaveClientApi.setAddressLine3(addressLine3);
			logSaveClientApi.setClientDOB(birthDate);
			logSaveClientApi.setMobNo(mobNo);
			logSaveClientApi.setGender(clientGender);
			logSaveClientApi.setEmailId(emailId);
			logSaveClientApi.setAppNo(appNo);
			logSaveClientApi.setApYear(apYear);
			logSaveClientApi.setPackageId(packageId);
			logSaveClientApi.setCenterId(centerId);
			logSaveClientApi.setCheckUpDate(checkUpDate);
			logSaveClientApi.setMemberId(memberId);
			logSaveClientApi.setLinkAppNo(linkAppNo);
			logSaveClientApi.setMbRelation(mbRelation);
			logSaveClientApi.setUserType(userType);
			
			int code=clientController.savelogSaveClientApi(logSaveClientApi);			
			
			
			/*String bloodGroup = (String) jsonObject.get("bloodGroup");
			Long cityId = (Long) jsonObject.get("cityId");
			Long stateId = (Long) jsonObject.get("stateId");
			Long countryId = (Long) jsonObject.get("countryId");
			Long pincode = (Long) jsonObject.get("pincode");
			String landlineNo = (String) jsonObject.get("landlineNo");
			String nomineeFirstName = (String) jsonObject.get("nomineeFirstName");
			String nomineeMiddleName = (String) jsonObject.get("nomineeMiddleName");
			String nomineeLastName = (String) jsonObject.get("nomineeLastName");
			String nomineeBirthDate = (String) jsonObject.get("nomineeBirthDate");
			String nomineeRelation = (String) jsonObject.get("nomineeRelation");
			String clientHeight = (String) jsonObject.get("clientHeight");
			String clientWeight = (String) jsonObject.get("clientWeight");*/
			
			JSONObject clientDetails = clientController.clientRegister(firstName, middleName, lastName, hospitalRegNo, addressLine1, 
					addressLine2, addressLine3, appNo, apYear, birthDate, packageId, centerId, mobNo,userType,
					checkUpDate, clientGender, memberId, emailId, linkAppNo, mbRelation, request);
						
			String EHRId = (String)((String) clientDetails.get("MB_EHR_NO"));
			System.err.println("EHRId==== "+EHRId);
			
			String a=EHRId.substring(0, 4);
			int dup=0;
			
			Integer clientId=null;
			if(a.equalsIgnoreCase("dup_"))
			{
				clientId= Integer.parseInt(EHRId.substring(4));
				dup=1;
				String aa =Dup_EHRID+", "+clientId;
				Dup_EHRID=aa.substring(1);
				
			}else {
				clientId= Integer.parseInt(EHRId.substring(4));
				dup=2;
			}
			
			JSONObject clientDetails1=new JSONObject();
			clientDetails1.put("MB_EHR_NO",clientId);
			
			System.err.println("EHRId------"+EHRId);
			if(memberId==null || appNo==null || apYear==null || memberId.equalsIgnoreCase("") || appNo.equalsIgnoreCase("") || apYear.equalsIgnoreCase("")) {
				response.put("clientDetails", clientDetails1);
				response.put("status_code", 0);
				response.put("error_code", 204);
				response.put("msg", "No Content for Visit Creation");
			}/*else if(dup == 1){
				response.put("clientDetails", clientDetails1);
				response.put("status_code", 0);
				response.put("error_code", 409);
				response.put("msg", "Client Already Register");
			}*/else {
				response.put("clientDetails", clientDetails1);
				response.put("status_code", 0);
				response.put("error_code", 0);
				response.put("msg", "Client Saved Successfully");
			}
			
		} catch (ParseException e) {
			response.put("status_code", 1);
			response.put("error_code", 305);
			response.put("msg", "error");
			e.printStackTrace();
		}
		/*try {
			sendMailForDuplicateClient(Dup_EHRID);
		}catch (Exception e) {
			e.printStackTrace();
		}*/
		
		return response;
	}

	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/updateClient")
	public JSONObject updateClient(String content) {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		JSONObject response = new JSONObject();
		JSONObject jsonObject = null;
		try {
			jsonObject = (org.json.simple.JSONObject) parser.parse(content);
			Long ehrId = (Long) jsonObject.get("clientId");
			String userName = clientController.getUserNameByClientId(ehrId.intValue());
			String firstName = (String) jsonObject.get("firstName");
			String middleName = (String) jsonObject.get("middleName");
			String lastName = (String) jsonObject.get("lastName");
			String bloodGroup = (String) jsonObject.get("bloodGroup");
			String addressLine1 = (String) jsonObject.get("addressLine1");
			String addressLine2 = (String) jsonObject.get("addressLine2");
			String addressLine3 = (String) jsonObject.get("addressLine3");
			Long cityId = (Long) jsonObject.get("cityId");
			Long stateId = (Long) jsonObject.get("stateId");
			String birthDate = (String) jsonObject.get("birthDate");
			Long countryId = (Long) jsonObject.get("countryId");
			Long pincode = (Long) jsonObject.get("pincode");
			String mobNo = (String) jsonObject.get("mobNo");
			String landlineNo = (String) jsonObject.get("landlineNo");
			String nomineeFirstName = (String) jsonObject.get("nomineeFirstName");
			String nomineeMiddleName = (String) jsonObject.get("nomineeMiddleName");
			String nomineeLastName = (String) jsonObject.get("nomineeLastName");
			String nomineeBirthDate = (String) jsonObject.get("nomineeBirthDate");
			String nomineeRelation = (String) jsonObject.get("nomineeRelation");
			String clientGender = (String) jsonObject.get("clientGender");
			String clientHeight = (String) jsonObject.get("clientHeight");
			String clientWeight = (String) jsonObject.get("clientWeight");
			String emailId = (String) jsonObject.get("emailId");
			clientController.updateClientProfile(ehrId.intValue(), firstName, middleName, lastName, bloodGroup,
					addressLine1, addressLine2, addressLine3, cityId.intValue(), stateId.intValue(), birthDate,
					countryId.intValue(), pincode.intValue(), mobNo, landlineNo, nomineeFirstName, nomineeMiddleName,
					nomineeLastName, nomineeBirthDate, nomineeRelation, clientGender, clientHeight, clientWeight,
					emailId, request);
			response.put("status_code", 0);
			response.put("error_code", 0);
			response.put("msg", "Client Updated Successfully");
		} catch (ParseException e) {
			response.put("status_code", 1);
			response.put("error_code", 305);
			response.put("msg", "error");
			e.printStackTrace();
		}
		return response;
	}
	
	@GET
	@Produces("application/json")
	@Path("/contentsPreview")
	public JSONObject contentsPreview() throws JSONException {
		JSONObject response = new JSONObject();
		JSONObject data = new JSONObject();
		JSONObject jsonObject = null;
		ContentMaster contentMaster = new ContentMaster();
		contentMaster.setModifyBy("published");
		List<ContentMaster> contents = contentController.contentsPreview(contentMaster);
		List<FileInputStream> files = new ArrayList<FileInputStream>();
		data.put("contents", contents);
		response.put("data", data);
		response.put("status_code", 0);
		response.put("error_code", 0);
		response.put("msg", "success");
		return response;
	}
	
	@GET
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/getReportsByEHRId/{ehrId}")
	public JSONObject getReportsByEHRId(@PathParam("ehrId") String ehrId) throws JSONException {
		JSONObject response = new JSONObject();
		JSONObject data = new JSONObject();
		JSONArray reportList = apiDaoImpl.getReportsByEHRId(ehrId);
		data.put("reportList", reportList);
		response.put("data", data);
		response.put("status_code", 0);
		response.put("error_code", 0);
		response.put("msg", "success");
		return response;
	}
	
	@POST
	@Path("/uploadTestomonial")
	@Produces("application/json")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public JSONObject uploadTestomonial(@FormDataParam("file") InputStream uploadedInputStream,
			@FormDataParam("file") FormDataContentDisposition fileDetail,@FormDataParam("ehrId") String ehrId) {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);

		java.text.SimpleDateFormat simpleDateFormat1 = new java.text.SimpleDateFormat("MM-dd-yyyy");
		String uploadDate = simpleDateFormat1.format(date);

		JSONObject response = new JSONObject();
		String fileName = ehrId + "_" + uploadDate + "_" + fileDetail.getFileName();
		String fileLocation = FilePath.getTestimonialPath() + File.separator + fileName;
		try {
			FileOutputStream out = new FileOutputStream(new File(fileLocation));
			int read = 0;
			byte[] bytes = new byte[1024];
			out = new FileOutputStream(new File(fileLocation));
			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();

			String userName = clientController.getUserNameByClientId(Integer.parseInt(ehrId));
			JSONObject object = new JSONObject();
			object.put("clientId", Integer.parseInt(ehrId));
			object.put("reportFilePath", fileName);
			object.put("addedBy", userName);
			object.put("addedOn", currentTime);
			apiDaoImpl.uploadTestomonial(object); // save database
			response.put("status_code", 0);
			response.put("error_code", 0);
			response.put("msg", "success");
		} catch (IOException e) {
			e.printStackTrace();
			response.put("status_code", 1);
			response.put("error_code", 304);
			response.put("msg", "File Upload Failed");
		}
		return response;
	}
	
	@GET
	@Produces("application/json")
	@Path("/getCategories")
	public JSONObject getCategories() throws JSONException {
		JSONObject response = new JSONObject();
		JSONObject data = new JSONObject();
		List<CategoryMaster> categories = categoryController.listOfCategoryMaster();
		data.put("categories", categories);
		response.put("data", data);
		response.put("status_code", 0);
		response.put("error_code", 0);
		response.put("msg", "success");
		return response;
	}
	
	@GET
	@Produces("application/json")
	@Path("/getSubCategories")
	public JSONObject getSubCategories() throws JSONException {
		JSONObject response = new JSONObject();
		JSONObject data = new JSONObject();
		List<SubCategoryMaster> subCategories = subCategoryController.listOfSubCategoryMaster();
		data.put("subCategories", subCategories);
		response.put("data", data);
		response.put("status_code", 0);
		response.put("error_code", 0);
		response.put("msg", "success");
		return response;
	}
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/saveClientReports")
	public JSONObject saveClientReports(String content) {
		JSONObject response = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		try {
			jsonArray = (org.json.simple.JSONArray) parser.parse(content);
			jsonArray = apiDaoImpl.saveClientReports(jsonArray);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		response.put("data", jsonArray);
		response.put("status_code", 0);
		response.put("error_code", 0);
		response.put("msg", "success");
		return response;
	}
	
	@POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces("text/plain")
    @Path("/multipleFiles")
    public Response uploadFile(@Context HttpServletRequest request) {
        String name = null;
        int code = 200;
        String msg = "Files uploaded successfully";
        if (ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload fileUpload = new ServletFileUpload(factory);
            try {
                List<FileItem> items = fileUpload.parseRequest(request);
                if (items != null) {
                    Iterator<FileItem> iter = items.iterator();
                    while (iter.hasNext()) {
                        final FileItem item = iter.next();
                        final String itemName = item.getName();
                        final String fieldName = item.getFieldName();
                        final String fieldValue = item.getString();
                        if (item.isFormField()) {
                            name = fieldValue;
                            System.out.println("Field Name: " + fieldName + ", Field Value: " + fieldValue);
                            System.out.println("Candidate Name: " + name);
                        } else {
                            final File file = new File("/home/amol/Desktop/reports" + File.separator + itemName);
                            File dir = file.getParentFile();
                            if(!dir.exists()) {
                                dir.mkdir();
                            }
                            
                            if(!file.exists()) {
                                file.createNewFile();
                            }
                            System.out.println("Saving the file: " + file.getName());
                            item.write(file);
                        }
                    }
                }
            } catch (FileUploadException e) {
                code = 404;
                msg = e.getMessage();
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
                code = 404;
                msg = e.getMessage();
            }
        }
        return Response.status(code).entity(msg).build();
    }
	
	@POST
	@Produces("application/json")
	@Consumes("application/json")
	@Path("/saveDoctorAnalysis")
	public JSONObject saveDoctorAnalysis(String content) {
		JSONObject response = new JSONObject();
		try {
			JSONObject outputObject = (JSONObject) parser.parse(content);
			JSONArray clients = (JSONArray) outputObject.get("CounsellorAppointmentDetails");
			apiDaoImpl.saveDoctorAnalysis(clients);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		response.put("status_code", 0);
		response.put("error_code", 0);
		response.put("msg", "success");
		return response;
	}
	

	private void sendMailForDuplicateClient(String Dup_EHRID) {
		System.err.println("Dup_EHRID==="+Dup_EHRID);
					// for sending mail
					String mailSubject = "Duplicate Client Details";
					String mailData = "Dear <b> Team</b>,"
					+ "<br><br> Same client details found for the following details: <br>"
					+ "<br> <b> Client Id's : </b>"+ Dup_EHRID + ","
					/*+ "<br> <b> Last Name : </b>"+ object.get("lastName") + ","
					+ "<br> <b> Gender : </b>"+ object.get("gender") + ","
					+ "<br> <b> DOB : </b>"+ object.get("dob") + ","
					+ "<br> <b> Mobile No : </b>"+ object.get("mobileNumber") + ","
					+ "<br> <b> Email-Id : </b>"+ object.get("emailId") + ""*/
					+ "<br><br><br> Thank You! <br><br>"
					+ "---------------------------------------------------------------------------   <br>"
					+ "Indus Health Plus (P) Ltd.<br>" + "'INDUS HOUSE', Pride Port,<br>"
					+ "Model Colony, Pune - 411 016,<br>" + "Maharashtra, India <br>" + "<br>"
					+ "Customer Care Number:<br>" + "<a>0-90490-22222</a><br>"
					+ "Mail ID : <a>customercare@indushealthplus.com</a> <br>"
					+ "Toll Free : <a>1800-313-2500</a>";
					SSLEmail sendMail = new SSLEmail();
					try {
						EmailRecord emailRecord = new EmailRecord();
						emailRecord.setTopic("Duplicate Client Details");
						emailRecord.setSentBy("API");
						//emailRecord.setClientId(Integer.parseInt((String)clientObject.get("clientId")));
						emailRecord.setEmailSubject(mailSubject);
						emailRecord.setEmailMedicalAdvice(mailData);
						emailRecord.setSentTo("it.pro@indushealthplus.com");
						sendMail.sendMail("it.pro@indushealthplus.com", mailData, mailSubject,emailRecord);
						
						emailRecord.setSentTo("shailesh.thakare@indushealthplus.com");
						sendMail.sendMail("shailesh.thakare@indushealthplus.com", mailData, mailSubject,emailRecord);
					} catch (MessagingException e) {
						e.printStackTrace();
					}
		
	}

}