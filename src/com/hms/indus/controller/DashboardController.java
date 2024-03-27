package com.hms.indus.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hms.api.APIController;
import com.hms.indus.bo.TaskMaster;
import com.hms.indus.bo.TaskSlave;
import com.hms.indus.bo.TestMaster;
import com.hms.indus.bo.UserMaster;
import com.hms.indus.service.ClientService;
import com.hms.indus.service.HealthFeedService;
import com.hms.indus.service.LinkService;
import com.hms.indus.service.TestService;
import com.hms.indus.service.VideoService;
import com.hms.indus.util.ApplicationContextUtil;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController {

	@Autowired
	VideoService videoService;

	@Autowired
	HealthFeedService healthFeedService;

	@Autowired
	LinkService linkService;

	@Autowired
	TestService testService;

	@Autowired
	ClientService clientService;
	
	JSONParser parser = new JSONParser();

	@RequestMapping(value = "/getDashboard", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getVideoMasterByVideoId(HttpServletRequest request,
			HttpSession httpSession) {
		Integer clientId = (Integer) httpSession.getAttribute("clientId");// Integer.parseInt((String)httpSession.getAttribute("clientId"));
		String maxId = videoService.getMaxId();
		Integer maxVideoId = null;
		if (!maxId.split("_")[0].equals("null")) {
			maxVideoId = Integer.parseInt(maxId.split("_")[0]);
		}
		Integer maxHealthFeedId = null;
		if (!maxId.split("_")[1].equals("null")) {
			maxHealthFeedId = Integer.parseInt(maxId.split("_")[1]);
		}
		Integer maxLinkId = null;
		if (!maxId.split("_")[2].equals("null")) {
			maxLinkId = Integer.parseInt(maxId.split("_")[2]);
		}

		JSONObject videoObject = videoService.getVideoMasterByVideoId(maxVideoId);
		JSONObject healthFeedObject = healthFeedService.getHealthFeedMasterByHealthFeedId(maxHealthFeedId);
		JSONObject linkObject = linkService.getLinkMasterByLinkId(maxLinkId);
		JSONArray analysisComments = clientService.getPatientAnalysisComment(clientId, 0);
		List<TestMaster> pendingTestMasterList = testService.getPendingTestByClientId(clientId);

		String parameterId = "324,280,300,298,293,265,347,266,319,281";
		JSONArray healthStatisticsList = clientService.getPatientHealthStatistics(clientId, parameterId);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("videoObject", videoObject);
		model.put("healthFeedObject", healthFeedObject);
		model.put("linkObject", linkObject);
		model.put("pendingTestMasterList", pendingTestMasterList);
		model.put("healthStatisticsList", healthStatisticsList);
		model.put("analysisComments", analysisComments);
		return model;
	}

	@RequestMapping(value = "/getRepresentativeDashboard", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getRepresentativeDashboard(@RequestParam("startDate") String UiStartDate,
			@RequestParam("endDate") String UiEndDate,
			HttpServletRequest request,
			HttpSession httpSession) {

		String startDate ="";
		String endDate ="";
		 System.err.println(UiStartDate+"**"+ UiEndDate+"**");
		if(UiStartDate.equalsIgnoreCase("NoDate") && UiEndDate.equalsIgnoreCase("NoDate"))
		{
			System.err.println(UiStartDate+"////"+ UiEndDate+"///*");
			Calendar cal = Calendar.getInstance();
			DateFormat apiDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			endDate = apiDateFormat.format(cal.getTime());// "01/12/2016";
			cal.add(Calendar.DATE, -8);
			startDate = apiDateFormat.format(cal.getTime());// "01/01/2016";
			
		}else {
			startDate = UiStartDate;
			endDate   = UiEndDate;
	
		}
		
		String userName = (String) httpSession.getAttribute("userName");// "amol";
		String centerId = (String) httpSession.getAttribute("centers");
		if (centerId.isEmpty()) {
			centerId = "0";
		}
		 System.err.println(centerId+"**"+ startDate+"**"+endDate+"**"+ userName);
		String count = clientService.getCountForRepresentativeDashboard(centerId, startDate, endDate, userName);

		Map<String, Object> model = new HashMap<String, Object>();
		model.put("turnUpNoOfClient", count.split("_")[0]);
		model.put("uploadReportClient", count.split("_")[1]);
		model.put("uploadReportCountByUser", count.split("_")[2]);
		model.put("rejectedReportByUser", count.split("_")[3]);
		model.put("rejectedReportAll", count.split("_")[4]);
		model.put("clientReportCountVisitWise", count.split("_")[5]);
		model.put("clientReportNotUplodedVisitWise", count.split("_")[6]);
		
		
		return model;
	}

	@RequestMapping(value = "/rejectedReportList", method = RequestMethod.POST)
	public @ResponseBody JSONArray rejectedReportList(@RequestParam("startDate") String UiStartDate,
			@RequestParam("endDate") String UiEndDate,@RequestParam("listData") String listData,HttpServletRequest request, HttpSession httpSession) {
		String startDate ="";
		String endDate ="";
		 System.err.println(UiStartDate+"**"+ UiEndDate+"**");
		if(UiStartDate.equalsIgnoreCase("NoDate") && UiEndDate.equalsIgnoreCase("NoDate"))
		{
			System.err.println(UiStartDate+"////"+ UiEndDate+"///*");
			Calendar cal = Calendar.getInstance();
			DateFormat apiDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			endDate = apiDateFormat.format(cal.getTime());// "01/12/2016";
			cal.add(Calendar.DATE, -8);
			startDate = apiDateFormat.format(cal.getTime());// "01/01/2016";
			
		}else {
			startDate = UiStartDate;
			endDate   = UiEndDate;
	
		}
		String userName = (String) httpSession.getAttribute("userName");// "amol";
		String centerId = (String) httpSession.getAttribute("centers");

		JSONArray rejectedReportList = clientService.rejectedReportList(centerId, startDate, endDate, userName,listData);
		return rejectedReportList;
	}
	

	@RequestMapping(value = "/totalPendingVisits", method = RequestMethod.POST)
	public @ResponseBody JSONArray totalPendingVisits(@RequestParam("startDate") String UiStartDate,
			@RequestParam("endDate") String UiEndDate,@RequestParam("listData") String listData,HttpServletRequest request, HttpSession httpSession) {
		String startDate ="";
		String endDate ="";
		 System.err.println(UiStartDate+"**"+ UiEndDate+"**");
		if(UiStartDate.equalsIgnoreCase("NoDate") && UiEndDate.equalsIgnoreCase("NoDate"))
		{
			System.err.println(UiStartDate+"////"+ UiEndDate+"///*");
			Calendar cal = Calendar.getInstance();
			DateFormat apiDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			endDate = apiDateFormat.format(cal.getTime());// "01/12/2016";
			cal.add(Calendar.DATE, -8);
			startDate = apiDateFormat.format(cal.getTime());// "01/01/2016";
			
		}else {
			startDate = UiStartDate;
			endDate   = UiEndDate;
	
		}
		String userName = (String) httpSession.getAttribute("userName");// "amol";
		String centerId = (String) httpSession.getAttribute("centers");

		JSONArray rejectedReportList = clientService.totalPendingVisits(centerId, startDate, endDate, userName,listData);
		return rejectedReportList;
	}

	@RequestMapping(value = "/redirectRejectedReportListPage", method = RequestMethod.POST)
	public @ResponseBody void redirectRejectedReportListPage(@RequestParam("userId") String userId,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		HttpSession httpSession = request.getSession();
		String userIdd=userId.substring(3);
		httpSession.setAttribute("reportRejectList", "1");
		clientService.getClientByUserId(Integer.parseInt(userIdd), request);
	}
	
	
	@RequestMapping(value = "/redirectReportUploadListPage", method = RequestMethod.POST)
	public @ResponseBody void redirectReportUploadListPage(@RequestParam("userId") String userId,
			HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		HttpSession httpSession = request.getSession();
		String userIdd=userId.substring(3);
		httpSession.setAttribute("reportRejectList", "1");
		clientService.getClientByUserId(Integer.parseInt(userIdd), request);
	}

	// For Team Management
	Integer pageSize = 10;
	@RequestMapping(value = "/countVisitStatus", method = RequestMethod.GET)
	public @ResponseBody Integer countVisitStatus(@RequestParam("status") String status,
			@RequestParam("userId") String userId,@RequestParam("pageId") Integer pageId, HttpSession session) {
		String isLeader = (String) session.getAttribute("isLeader");
		String userTypeId = (String) session.getAttribute("userTypeId");
		JSONObject object = new JSONObject();
		object.put("status", status);
		object.put("userId", userId);
		object.put("isLeader", isLeader);
		object.put("userTypeId", userTypeId);
		pageSize = 10;
		object.put("pageSize", pageSize);
		return clientService.countVisitStatus(object);
	}
	
	@RequestMapping(value = "/getWork", method = RequestMethod.GET)
	public @ResponseBody JSONArray getPendingWork(@RequestParam("status") String status,
		@RequestParam("userId") String userId,@RequestParam("pageId") Integer pageId,
		@RequestParam(value = "searchBy", required=false) String searchBy,
		@RequestParam(value = "searchText", required=false) String searchText,
		HttpSession session) {
		String isLeader = (String) session.getAttribute("isLeader");
		String userTypeId = (String) session.getAttribute("userTypeId");
		//Code For sending Clients to API
		/*if(status.equals("11")) {
			String apiUrl = "http://115.113.153.199/EHRAPI/EHR/GetCounsellorAppointment";
			try {
				URL url = new URL(apiUrl);                    
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();                    
				conn.setDoInput(true);                    
				conn.setDoOutput(true);                    
				conn.setRequestMethod("POST");                    
				conn.setRequestProperty("Content-Type", "application/json");                                        
				OutputStream os = conn.getOutputStream();
				JSONObject input = new JSONObject();
				input.put("CounsellorFromDate","30-Dec-2018");
				input.put("CounsellorToDate","31-Dec-2018");
				input.put("CounsellorId","");
				os.write(input.toJSONString().getBytes());                    
				os.flush();                    
				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("HTTP error code : " + conn.getResponseCode()+" HTTP message "+conn.getResponseMessage());                    
				}                    
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));                    
				String output = br.readLine();                    
				conn.disconnect();
				APIController apiController = new APIController();
				apiController.saveDoctorAnalysis(output);
				} catch (Exception ex) {                    
					ex.printStackTrace();                
				}
		}*/
		
		JSONObject object = new JSONObject();
		object.put("searchBy", searchBy);
		object.put("searchText", searchText);
		object.put("status", status);
		object.put("userId", userId);
		object.put("isLeader", isLeader);
		object.put("userTypeId", userTypeId);
		object.put("pageId", pageId);
		if(searchBy != null && !searchText.equals("")) {
			pageSize = 30;
		} else {
			pageSize = 10;
		}
		object.put("pageSize", pageSize);
		JSONArray jsonArray = clientService.getWork(object);
		return jsonArray;
	}

	@RequestMapping(value = "/assignTask", method = RequestMethod.POST)
	public @ResponseBody String assignTask(@RequestBody TaskMaster taskMaster, HttpSession session) {
		String assignedBy = (String) session.getAttribute("userId");
		// For Team Management
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		String addedByUserId = (String) session.getAttribute("userId");
		JSONArray clients = new JSONArray();
		
		UserMaster assignByMaster = new UserMaster();
		assignByMaster.setUserId(Integer.parseInt(assignedBy));
		taskMaster.setAssignedBy(assignByMaster);
		for (TaskSlave taskSlave : taskMaster.getTaskSlaves()) {
			taskSlave.setTaskMaster(taskMaster);
			Integer checkupId = taskSlave.getCheckUpId();
			Integer clientId = taskSlave.getClientId();
			Integer workStatusId = taskSlave.getWorkStatusId();
			taskMaster.setWorkStatusId(workStatusId);
			JSONObject client = new JSONObject();
			client.put("clientId", clientId);
			client.put("checkupId", checkupId);
			client.put("statusId", workStatusId);
			client.put("changedBy", addedByUserId);
			client.put("changedOn", currentTime);
			clients.add(client);
		}
		clientService.assignTask(taskMaster);
		clientService.changeClientStatus(clients);
		return "Task Assign Successfully";
	}

	@RequestMapping(value = "/submitTask", method = RequestMethod.POST)
	public @ResponseBody String submitTask(@RequestParam("clients") String clients, HttpSession session) {
		// For Team Management
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		String addedByUserId = (String) session.getAttribute("userId");
		JSONParser parser = new JSONParser();
		JSONArray clientArray = null;
		try {
			clientArray = (JSONArray) parser.parse(clients);
			for(int i=0;i<clientArray.size();i++) {
				JSONObject client = (JSONObject) clientArray.get(i);
				client.put("changedBy", addedByUserId);
				client.put("changedOn", currentTime);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String response = clientService.submitTask(clientArray);
		return response;
	}
	
	@RequestMapping(value = "/getClientStatus", method = RequestMethod.GET)
	public @ResponseBody JSONArray getClientStatus(
		@RequestParam(value = "clientId", required=false) Integer clientId,
		@RequestParam(value = "visitId", required=false) Integer visitId) {
		JSONObject object = new JSONObject();
		object.put("clientId", clientId);
		object.put("visitId", visitId);
		JSONArray jsonArray = clientService.getClientStatus(object);
		return jsonArray;
	}
	
	@RequestMapping(value = "/getClientStatusAll", method = RequestMethod.GET)
	public @ResponseBody JSONArray getClientStatusAll(
		@RequestParam(value = "clientId", required=false) Integer clientId,
		@RequestParam(value = "visitId", required=false) Integer visitId) {
		JSONObject object = new JSONObject();
		object.put("clientId", clientId);
		object.put("visitId", visitId);
		JSONArray jsonArray = clientService.getClientStatusAll(object);
		return jsonArray;
	}
	
	@RequestMapping(value = "/closeVisit", method = RequestMethod.POST)
	public @ResponseBody String closeVisit(@RequestParam("clients") String clients, HttpSession session) {
		// For Team Management
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		String addedByUserId = (String) session.getAttribute("userId");
		JSONParser parser = new JSONParser();
		JSONArray clientArray = null;
		try {
			clientArray = (JSONArray) parser.parse(clients);
			for(int i=0;i<clientArray.size();i++) {
				JSONObject client = (JSONObject) clientArray.get(i);
				client.put("changedBy", addedByUserId);
				client.put("changedOn", currentTime);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String response = clientService.closeVisit(clientArray);
		return response;
	}
	
	@RequestMapping(value = "/checkStatusBeforeSubmitTask", method = RequestMethod.POST)
	public @ResponseBody String checkStatusBeforeSubmitTask(@RequestParam("clients") String clients, HttpSession session) {
		// For Team Management
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		String addedByUserId = (String) session.getAttribute("userId");
		JSONParser parser = new JSONParser();
		JSONArray clientArray = null;
		try {
			clientArray = (JSONArray) parser.parse(clients);
			for(int i=0;i<clientArray.size();i++) {
				JSONObject client = (JSONObject) clientArray.get(i);
				client.put("changedBy", addedByUserId);
				client.put("changedOn", currentTime);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String response = clientService.checkStatusBeforeSubmitTask(clientArray);
		return response;
	}

}
