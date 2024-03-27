package com.hms.indus.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hms.indus.bo.ClientMaster;
import com.hms.indus.bo.ClientUploadReport;
import com.hms.indus.bo.UserMaster;
import com.hms.indus.service.ClientService;
import com.hms.indus.util.AccessControl;

@Controller
public class HomeController {
	
	@Autowired
	ClientService clientService;
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accessDenied(HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		String userName=(String) session.getAttribute("userName");
		modelAndView.addObject("username", userName);
		modelAndView.setViewName("403");
		return modelAndView;
	}
	
	@RequestMapping(value = "/feedTypeMaster", method = RequestMethod.POST)
	public ModelAndView feedTypeMaster(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"feedTypeMaster");
		if(access==true){
			modelAndView.setViewName("indus_feed_type_master");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/healthFeedMaster", method = RequestMethod.POST)
	public ModelAndView healthFeedMaster(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"healthFeedMaster");
		if(access==true){
			modelAndView.setViewName("indus_health_feed_master");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/uploadVideoMaster", method = RequestMethod.POST)
	public ModelAndView uploadVideoMaster(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"uploadVideoMaster");
		if(access==true){
			modelAndView.setViewName("indus_video_master");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/linkMaster", method = RequestMethod.POST)
	public ModelAndView linkMaster(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"linkMaster");
		if(access==true){
			modelAndView.setViewName("indus_link_master");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/templateMaster", method = RequestMethod.GET)
	public ModelAndView templateMaster(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"templateMaster");
		if(access==true){
			modelAndView.setViewName("indus_template_master");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/clientRegistrationPage", method = RequestMethod.POST)
	public ModelAndView clientRegistrationPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"clientRegistrationPage");
		if(access==true){
			modelAndView.setViewName("indus_patient_registration");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/clientIntegrationPage", method = RequestMethod.GET)
	public ModelAndView clientIntegrationPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"clientIntegrationPage");
		if(access==true){
			modelAndView.setViewName("indus_patient_integration");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/smsTemplateMaster", method = RequestMethod.GET)
	public ModelAndView smsTemplateMaster(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"smsTemplateMaster");
		if(access==true){
			modelAndView.setViewName("indus_sms_template_master");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/rejectMaster", method = RequestMethod.GET)
	public ModelAndView rejectMaster(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"rejectMaster");
		if(access==true){
			modelAndView.setViewName("indus_reject_master");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/followUpPage", method = RequestMethod.POST)
	public ModelAndView followUpPage(@RequestParam("clientId") Integer clientId,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession httpSession=request.getSession();
		//String userId=(String) httpSession.getAttribute("clientUserName");
		ClientMaster clientMaster=new ClientMaster();
		try
		{
			clientMaster=clientService.getClientByUserId(clientId,request);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		request.setAttribute("client",clientMaster);
		modelAndView.setViewName("indus_follow_up_record");
		return modelAndView;
	}
	
	@RequestMapping(value = "/contactHubPage", method = RequestMethod.POST)
	public ModelAndView contactHubPage(@RequestParam("clientId") Integer clientId,HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession httpSession=request.getSession();
		//String userId=(String) httpSession.getAttribute("clientUserName");
		ClientMaster clientMaster=new ClientMaster();
		try
		{
			clientMaster=clientService.getClientByUserId(clientId,request);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		request.setAttribute("client",clientMaster);
		modelAndView.setViewName("indus_Contact_Hub_Page");
		return modelAndView;
	}
	
	@RequestMapping(value = "/loadContactHubPageNew", method = RequestMethod.POST)
	public ModelAndView loadContactHubPageNew(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession httpSession=request.getSession();
		//String userId=(String) httpSession.getAttribute("clientUserName");
		ClientMaster clientMaster=new ClientMaster();
		/*try
		{
			clientMaster=clientService.getClientByUserId(clientId,request);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}*/
		//request.setAttribute("client",clientMaster);
		modelAndView.setViewName("indus_contacthub");
		return modelAndView;
	}
	
	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public ModelAndView homepage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"homepage");
		if(access==true){
			modelAndView.setViewName("indus_homepage");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/calendar", method = RequestMethod.GET)
	public ModelAndView calendar(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"reminders");
		if(access==true){
			modelAndView.setViewName("indus_calendar");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/userProfile", method = RequestMethod.GET)
	public ModelAndView getUserProfile(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"userProfile");
		if(access==true){
			modelAndView.setViewName("indus_user_profile");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView getIndexPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"index");
		if(access==true){
			modelAndView.setViewName("indus_index");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/database", method = RequestMethod.GET)
	public ModelAndView getDatabase(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"database");
		
		HttpSession httpSession=request.getSession();
		Integer clientId=(Integer) httpSession.getAttribute("clientMasterId");
		if(clientId!=null){
			clientService.removeClientLocked(clientId);
			httpSession.setAttribute("clientMasterId", 0);
			httpSession.setAttribute("clientUserName", null);
			httpSession.setAttribute("clientId", null);
		}
		
		if(access==true){
			modelAndView.setViewName("indus_database");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/HRATypeMaster", method = RequestMethod.GET)
	public ModelAndView HRATypeManagement(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"HRATypeMaster");
		if(access==true){
			modelAndView.setViewName("indus_hra_type_master");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/actionMaster", method = RequestMethod.GET)
	public ModelAndView actionMaster(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"actionMaster");
		if(access==true){
			modelAndView.setViewName("indus_action_master");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/subActionMaster", method = RequestMethod.GET)
	public ModelAndView subActionMaster(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"subActionMaster");
		if(access==true){
			modelAndView.setViewName("indus_sub_action_master");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/visitTypeMaster", method = RequestMethod.GET)
	public ModelAndView visitTypeMaster(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"visitTypeMaster");
		if(access==true){
			modelAndView.setViewName("indus_visit_type_master");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/HRAQuestionMaster", method = RequestMethod.GET)
	public ModelAndView HRAQuestionManagement(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"HRAQuestionMaster");
		if(access==true){
			modelAndView.setViewName("indus_hra_question_master");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/accountManagement", method = RequestMethod.GET)
	public ModelAndView accountManagement(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"accountManagement");
		if(access==true){
			modelAndView.setViewName("indus_account_management");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/parameterMaster", method = RequestMethod.GET)
	public ModelAndView parameterMaster(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"parameterMaster");
		if(access==true){
			modelAndView.setViewName("indus_parameter_master");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/unitMaster", method = RequestMethod.GET)
	public ModelAndView unitMaster(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"unitMaster");
		if(access==true){
			modelAndView.setViewName("indus_unit_master");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/packageTestRelation", method = RequestMethod.GET)
	public ModelAndView packageTestRelation(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"packageTestRelation");
		if(access==true){
			modelAndView.setViewName("indus_package_test_relation");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/parameterValues", method = RequestMethod.GET)
	public ModelAndView parameterValues(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"parameterValues");
		if(access==true){
			modelAndView.setViewName("indus_parameter_values_centre");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/userAccessManagement", method = RequestMethod.GET)
	public ModelAndView userAccessManagement(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"userAccessManagement");
		if(access==true){
			modelAndView.setViewName("indus_user_access_management");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLoginPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("login", new UserMaster());
		modelAndView.setViewName("indus_login");
		return modelAndView;
	}
	
	@RequestMapping(value = "/incorrectLogin", method = RequestMethod.GET)
	public ModelAndView incorrectLoginPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("login", new UserMaster());
		HttpSession session=request.getSession();
		String isactive=(String) session.getAttribute("isactive");
		if(isactive!=null && isactive.equals("N")){
			modelAndView.addObject("error", "Your username and password is not activated...!");
			session.setAttribute("isactive","");
		}else{
			modelAndView.addObject("error", "Invalid UserName Or Password");
		}
		modelAndView.setViewName("indus_login");
		return modelAndView;
	}
	
	@RequestMapping(value = "/demograph", method = RequestMethod.GET)
	public ModelAndView getDemographicPage(@RequestParam("clientId") Integer clientId,HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession httpSession=request.getSession();
		boolean access=AccessControl.getAccessControl(request,"demograph");
		if(access==true){
			//String userId=(String) httpSession.getAttribute("clientUserName");//userName
			ClientMaster clientMaster=new ClientMaster();
			try
			{
				clientMaster=clientService.getClientByUserId(clientId,request);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			request.setAttribute("client",clientMaster );
			modelAndView.addObject("login", new UserMaster());
			modelAndView.setViewName("indus_patient_demographic");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/hra", method = RequestMethod.GET)
	public ModelAndView gethraPage(@RequestParam("clientId") Integer clientId,HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession httpSession=request.getSession();
		boolean access=AccessControl.getAccessControl(request,"hra");
		if(access==true){
			String userId=(String) httpSession.getAttribute("clientUserName");//userName
			ClientMaster clientMaster=new ClientMaster();
			try
			{
				clientMaster=clientService.getClientByUserId(clientId,request);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			request.setAttribute("client",clientMaster );
			
			modelAndView.addObject("login", new UserMaster());
			modelAndView.setViewName("indus_patient_hra");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/others", method = RequestMethod.GET)
	public ModelAndView getOthersPage(@RequestParam("clientId") Integer clientId,HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession httpSession=request.getSession();
		boolean access=AccessControl.getAccessControl(request,"others");
		if(access==true){
			String userId=(String) httpSession.getAttribute("clientUserName");//userName
			ClientMaster clientMaster=new ClientMaster();
			try
			{
				clientMaster=clientService.getClientByUserId(clientId,request);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			request.setAttribute("client",clientMaster );
			
			modelAndView.addObject("login", new UserMaster());
			modelAndView.setViewName("indus_patient_others");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/reminders", method = RequestMethod.GET)
	public ModelAndView getRemindersPage(@RequestParam("clientId") Integer clientId,HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession httpSession=request.getSession();
		boolean access=AccessControl.getAccessControl(request,"reminders");
		if(access==true){
			String userId=(String) httpSession.getAttribute("clientUserName");//userName
			ClientMaster clientMaster=new ClientMaster();
			try
			{
				clientMaster=clientService.getClientByUserId(clientId,request);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			request.setAttribute("client",clientMaster );
			
			modelAndView.addObject("login", new UserMaster());
			modelAndView.setViewName("indus_patient_reminders");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/reports", method = RequestMethod.GET)
	public ModelAndView getMyReportPage(HttpServletRequest request,HttpServletResponse response
			,@RequestParam("clientId") Integer clientId) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession httpSession=request.getSession();
		boolean access=AccessControl.getAccessControl(request,"reports");
		if(access==true){
			//String userId=(String) httpSession.getAttribute("clientUserName");//userName
			ClientMaster clientMaster=new ClientMaster();
			try
			{
				clientMaster=clientService.getClientByUserId(clientId,request);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			request.setAttribute("client",clientMaster );
			
			modelAndView.addObject("login", new UserMaster());
			modelAndView.setViewName("indus_patient_reports");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/verification", method = RequestMethod.GET)
	public ModelAndView getVerificationPage(HttpServletRequest request,HttpServletResponse response
			,@RequestParam("clientId") Integer clientId) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession httpSession=request.getSession();
		boolean access=AccessControl.getAccessControl(request,"verification");
		if(access==true){
			//String userId=(String) httpSession.getAttribute("clientUserName");//userName
			ClientMaster clientMaster=new ClientMaster();
			try
			{
				clientMaster=clientService.getClientByUserId(clientId,request);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			request.setAttribute("client",clientMaster );
			
			modelAndView.addObject("login", new UserMaster());
			modelAndView.setViewName("indus_patient_verification");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/otherDetails", method = RequestMethod.GET)
	public ModelAndView getOtherDetailsPage(@RequestParam("clientId") Integer clientId,HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		HttpSession httpSession=request.getSession();
		boolean access=AccessControl.getAccessControl(request,"others");
		if(access==true){
			//String userId=(String) httpSession.getAttribute("clientUserName");//userName
			ClientMaster clientMaster=new ClientMaster();
			try
			{
				clientMaster=clientService.getClientByUserId(clientId,request);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			request.setAttribute("client",clientMaster );
			
			modelAndView.addObject("login", new UserMaster());
			modelAndView.setViewName("indus_patient_other_details");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/inbox", method = RequestMethod.GET)
	public ModelAndView getInboxPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("indus_inbox");
		return modelAndView;
	}
	
	@RequestMapping(value = "/dataManagement", method = RequestMethod.GET)
	public ModelAndView getDataManagementPage(HttpServletRequest request,HttpServletResponse response,
			@RequestParam("clientId") Integer clientId) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"dataManagement");
		if(access==true){
			HttpSession httpSession=request.getSession();
			//String userId=(String) httpSession.getAttribute("clientUserName");
			ClientMaster clientMaster=new ClientMaster();
			try
			{
				clientMaster=clientService.getClientByUserId(clientId,request);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			request.setAttribute("client",clientMaster);
			modelAndView.setViewName("indus_data_management");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/interaction", method = RequestMethod.GET)
	public ModelAndView getInteractionPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"interaction");
		
		HttpSession httpSession=request.getSession();
		Integer clientId=(Integer) httpSession.getAttribute("clientMasterId");
		if(clientId!=null){
			clientService.removeClientLocked(clientId);
			httpSession.setAttribute("clientMasterId", 0);
			httpSession.setAttribute("clientUserName", null);
			httpSession.setAttribute("clientId", null);
		}
		
		if(access==true){
			modelAndView.setViewName("indus_interaction");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/analysis", method = RequestMethod.GET)
	public ModelAndView getAnalysisPage(HttpServletRequest request,HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"analysis");
		HttpSession httpSession=request.getSession();
		Integer clientId=(Integer) httpSession.getAttribute("clientMasterId");
		if(clientId!=null){
			clientService.removeClientLocked(clientId);
			httpSession.setAttribute("clientMasterId", 0);
			httpSession.setAttribute("clientUserName", null);
			httpSession.setAttribute("clientId", null);
		}
		
		if(access==true){
			modelAndView.setViewName("indus_analysis");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/clientUploadReport", method = RequestMethod.POST)
	public @ResponseBody List<ClientUploadReport> getClientUploadReport(@RequestParam("clientId") Integer clientId,HttpServletRequest request,HttpServletResponse response) {
		List<ClientUploadReport> clientUploadReports=new ArrayList<ClientUploadReport>();
		try
		{
			clientUploadReports=clientService.getClientUploadReport(clientId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return clientUploadReports;
		
	}
	
	@RequestMapping(value = "/contentArticlePage", method = RequestMethod.GET)
	public ModelAndView contentArticlePage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"contentArticlePage");
		if(access==true){
			modelAndView.setViewName("indus_content_article");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/contentPreviewPage", method = RequestMethod.GET)
	public ModelAndView contentPreviewPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"contentPreview");
		if(access==true){
			modelAndView.setViewName("indus_content_preview");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/categoryMaster", method = RequestMethod.GET)
	public ModelAndView categoryMaster(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"categoryMaster");
		if(access==true){
			modelAndView.setViewName("indus_category_master");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/subCategoryMaster", method = RequestMethod.GET)
	public ModelAndView subCategoryMaster(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"subCategoryMaster");
		if(access==true){
			modelAndView.setViewName("indus_sub_category_master");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/frequencyMasterPage", method = RequestMethod.GET)
	public ModelAndView frequencyMasterPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"frequencyMaster");
		if(access==true){
			modelAndView.setViewName("indus_frequency_master");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/parameterDetailsPage", method = RequestMethod.GET)
	public ModelAndView parameterDetailsPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"parameterDetails");
		if(access==true){
			modelAndView.setViewName("indus_parameter_report_details");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/hraDetailsPage", method = RequestMethod.GET)
	public ModelAndView hraDetailsPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"hraDetails");
		if(access==true){
			modelAndView.setViewName("indus_hra_report_details");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/analysisCommentMaster", method = RequestMethod.GET)
	public ModelAndView analysisCommentMaster(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"analysisCommentMaster");
		if(access==true){
			modelAndView.setViewName("indus_analysis_comment_master");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/workManagement", method = RequestMethod.GET)
	public ModelAndView workManagement(HttpServletRequest request,HttpSession httpSession) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("indus_work_management");
		Integer clientId=(Integer) httpSession.getAttribute("clientMasterId");
		if(clientId!=null){
			clientService.removeClientLocked(clientId);
			httpSession.setAttribute("clientMasterId", 0);
			httpSession.setAttribute("clientUserName", null);
			httpSession.setAttribute("clientId", null);
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/bunchUploadPage", method = RequestMethod.GET)
	public ModelAndView bunchReportUploadPage(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		boolean access=AccessControl.getAccessControl(request,"bunchUploadPage");
		if(access==true){
			modelAndView.setViewName("indus_bunch_upload");
		}
		else{
			modelAndView.setViewName("403");
		}
		return modelAndView;
	}
	
	/*@RequestMapping(value="/readExcel",method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	public @ResponseBody String readExcel(HttpServletRequest request,@RequestParam("file") MultipartFile file) {
		String UPLOAD_DIRECTORY = com.hms.indus.util.FilePath.getBasePath();
		String fileName=file.getOriginalFilename();
		File serverLocation=new File(UPLOAD_DIRECTORY);
		if(!serverLocation.exists()){
			serverLocation.mkdir();
		}
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				File serverFile = new File(UPLOAD_DIRECTORY + File.separator + fileName);
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			productService.pushStockByExcel(UPLOAD_DIRECTORY + fileName);
		}
		return "File Imported Successfully";
	}
	
	@Override
	@Transactional
	public String pushStockByExcel(String filePath) {
		return productDao.pushStockByExcel(filePath);
	}
	
	@Override
	public String pushStockByExcel(String file) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String currentTime = dateFormat.format(date);
		Map<String, Integer> cellValue = new HashMap<String, Integer>();
		String filePath = file;
		try {
			InputStream ExcelFileToRead = new FileInputStream(filePath);
			XSSFWorkbook wb = new XSSFWorkbook(ExcelFileToRead);
			XSSFWorkbook test = new XSSFWorkbook();
			XSSFSheet sheet = wb.getSheetAt(0);
			XSSFRow row;
			XSSFCell cell;
			Iterator rows = sheet.rowIterator();
			while (rows.hasNext()) {
				row = (XSSFRow) rows.next();
				Iterator cells = row.cellIterator();
				XSSFCell productName = null;
				XSSFCell batchCode = null;
				XSSFCell expiry = null;
				XSSFCell quantity = null;
				XSSFCell mrp = null;
				XSSFCell purchaseRate = null;
				XSSFCell billRate = null;
				XSSFCell rate = null;
				Integer shelf = 0;
				Double tax = 0.0;
				Double amt = 0.0;
				
				 * while (cells.hasNext()) {
				 
				productName = row.getCell(0);
				batchCode = row.getCell(1);
				expiry = row.getCell(2);
				quantity = row.getCell(3);
				mrp = row.getCell(4);
				purchaseRate = row.getCell(5);
				billRate = row.getCell(6);
				rate = row.getCell(7);

				// }
				// cell=(XSSFCell) cells.next();
				if (productName != null) {
					if (cellValue.containsKey(productName.toString().trim())) {
						
					} 
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Saved Successfully";
	}*/
	
}
