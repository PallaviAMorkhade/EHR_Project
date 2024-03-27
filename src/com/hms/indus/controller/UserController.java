package com.hms.indus.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hms.indus.bo.MenuMaster;
import com.hms.indus.bo.UnitMaster;
import com.hms.indus.bo.UserMaster;
import com.hms.indus.bo.UserTypeMaster;
import com.hms.indus.service.ParameterService;
import com.hms.indus.service.UnitService;
import com.hms.indus.service.UserTypeService;
import com.hms.indus.util.AccessControl;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	
	@Autowired
	UserTypeService userTypeMasterService;
	
	@Autowired
	UnitService unitService;
	
	@Autowired
	ParameterService parameterService;
	
	@RequestMapping(value = "/getListOfUserTypeMaster", method = RequestMethod.POST)
	public @ResponseBody List<UserTypeMaster> getListOfUserTypeMaster() {
			List<UserTypeMaster> listUserTypeMasters=userTypeMasterService.getListOfUserTypeMaster();
			return listUserTypeMasters;
	}
	
	@RequestMapping(value = "/saveUserMaster", method = RequestMethod.POST)
	public @ResponseBody String saveUserMaster(HttpServletRequest request,@RequestParam("title") String title,@RequestParam("firstName") String firstName,
			@RequestParam("middleName") String middleName,@RequestParam("lastName") String lastName,@RequestParam("userName") String userName,
			@RequestParam("password") String password,@RequestParam("userTypeSelect") String userTypeSelect,@RequestParam("centers[]") String centers,HttpSession session
			,@RequestParam("testPackageId[]") String testPackageId,@RequestParam("testPackage") String testPackage,@RequestParam("memberCode") String memberCode) {
		String addedByUserName = (String) session.getAttribute("userName");
		
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		
		UserMaster userMaster=new UserMaster();
		userMaster.setTitle(title);
		userMaster.setFirstName(firstName);
		userMaster.setMiddleName(middleName);
		userMaster.setLastName(lastName);
		userMaster.setUserName(userName);
		userMaster.setNewPassword(password);
		userMaster.setMemberCode(memberCode);
		
		
		UserTypeMaster userTypeMaster=new UserTypeMaster();
		userTypeMaster.setUserTypeId(Integer.parseInt(userTypeSelect));
		userMaster.setUserTypeMaster(userTypeMaster);
		
		userMaster.setIsActive('Y');
		userMaster.setAddAt(currentTime);
		userMaster.setAddBy(addedByUserName);
		userMaster.setLoginStatus("1");
		userMaster.setCenters(centers.toString());
		
		if(testPackage.equals("Test")){
			userMaster.setTests(testPackageId.toString());
		}
		else{
			userMaster.setPackages(testPackageId.toString());
		}
		
		userTypeMasterService.saveUserMaster(userMaster);
		return "saved successfully";
	}

	@RequestMapping(value = "/getListOfUserMaster", method = RequestMethod.POST)
	public @ResponseBody List<UserMaster> getListOfUserMaster(@RequestParam(required=false,value="userTypeId") Integer userTypeId) {
			List<UserMaster> listUserMasters=userTypeMasterService.getListOfUserMaster(userTypeId);
			return listUserMasters;
	}
	
	@RequestMapping(value = "/updateUserMaster", method = RequestMethod.POST)
	public @ResponseBody String updateUserMaster(HttpServletRequest request,@RequestParam("title") String title,@RequestParam("firstName") String firstName,
			@RequestParam("middleName") String middleName,@RequestParam("lastName") String lastName,@RequestParam("userName") String userName,@RequestParam("userId") Integer userId,
			@RequestParam("password") String password,@RequestParam("userTypeSelect") String userTypeSelect,@RequestParam("centers[]") String centers,HttpSession session
			,@RequestParam("testPackageId[]") String testPackageId,@RequestParam("testPackage") String testPackage,@RequestParam("memberCode") String memberCode) {
		String addedByUserName = (String) session.getAttribute("userName");
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		
		UserMaster userMaster=new UserMaster();
		userMaster.setUserId(userId);
		userMaster.setTitle(title);
		userMaster.setFirstName(firstName);
		userMaster.setMiddleName(middleName);
		userMaster.setLastName(lastName);
		userMaster.setUserName(userName);
		userMaster.setNewPassword(password);
		userMaster.setMemberCode(memberCode);
		
		UserTypeMaster userTypeMaster=new UserTypeMaster();
		userTypeMaster.setUserTypeId(Integer.parseInt(userTypeSelect));
		userMaster.setUserTypeMaster(userTypeMaster);
		
		userMaster.setIsActive('Y');
		
		userMaster.setModifyAt(currentTime);
		userMaster.setModifyBy(addedByUserName);
		userMaster.setLoginStatus("1");
		userMaster.setCenters(centers.toString());
		
		if(testPackage.equals("Test")){
			userMaster.setTests(testPackageId.toString());
		}
		else{
			userMaster.setPackages(testPackageId.toString());
		}
		
		userTypeMasterService.updateUserMaster(userMaster);
		return "updated successfully";
	}
	
	@RequestMapping(value = "/deleteUserMaster", method = RequestMethod.POST)
	public @ResponseBody String deleteUserMaster(HttpServletRequest request,@RequestParam("userId") Integer userId) {
		String currentPageId="11";
		String response="";
		boolean access=AccessControl.isDeleteAccess(request, currentPageId);
		if(access==true){
			UserMaster userMaster=new UserMaster();
			userMaster.setUserId(userId);
			userTypeMasterService.deleteUserMaster(userMaster);
			response="true";
		}
		else{
			response="false";
		}
		return response;
	}
	
	@RequestMapping(value = "/getUserMasterByUserId", method = RequestMethod.POST)
	public @ResponseBody UserMaster getUserMasterByUserId(HttpServletRequest request,@RequestParam("userId") Integer userId) {
		String currentPageId="11";
		UserMaster userMaster=new UserMaster();
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		if(access==true){
			userMaster=userTypeMasterService.getUserByUserId(userId);
		}
		else{
			userMaster.setAddBy("false");
		}
		return userMaster;
	}
	
	@RequestMapping(value = "/setLoginStatusUserMaster", method = RequestMethod.POST)
	public @ResponseBody String setLoginStatusUserMaster(@RequestParam("userId") Integer userId,@RequestParam("loginStatus") String loginStatus) {
		UserMaster userMaster=new UserMaster();
		userMaster.setUserId(userId);
		userMaster.setLoginStatus(loginStatus);
		userTypeMasterService.setLoginStatusUserMaster(userMaster);
		return "Login status updated successfully";
	}
	
	@RequestMapping(value = "/getListOfMenuMaster", method = RequestMethod.POST)
	public @ResponseBody List<MenuMaster> getListOfMenuMaster() {
			List<MenuMaster> listMenuMasters=userTypeMasterService.getListOfMenuMaster();
			return listMenuMasters;
	}
	
	@RequestMapping(value = "/saveUnitMaster", method = RequestMethod.POST)
	public @ResponseBody String saveUnitMaster(@RequestParam("unitMasterName") String unitMasterName,HttpSession session) {
		
		String addedBy = (String) session.getAttribute("userName");
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		
		UnitMaster unitMaster=new UnitMaster();
		unitMaster.setUnitName(unitMasterName);
		unitMaster.setAddedBy(addedBy);
		unitMaster.setAddedOn(currentTime);
		unitMaster.setUnitMasterDeleteFlag(1);
		String response=unitService.saveUnitMaster(unitMaster);
		return response;
	}
	
	@RequestMapping(value = "/updateUnitMaster", method = RequestMethod.POST)
	public @ResponseBody String updateUnitMaster(HttpServletRequest request,HttpSession session,@RequestParam("unitMasterId") Integer unitMasterId,@RequestParam("unitMasterName") String unitMasterName) {
		String currentPageId="17";
		String response="";
		boolean access=AccessControl.isWriteAccess(request, currentPageId);
		if(access==true){
			String modifyBy = (String) session.getAttribute("userName");
			java.util.Date date = new java.util.Date();
			java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String currentTime = simpleDateFormat.format(date);
			
			UnitMaster unitMaster=new UnitMaster();
			unitMaster.setUnitName(unitMasterName);
			unitMaster.setUnitId(unitMasterId);
			unitMaster.setModifyBy(modifyBy);
			unitMaster.setModifyOn(currentTime);
			unitService.updateUnitMaster(unitMaster);
			response="true";
		}
		else{
			response="false";
		}
		return response;
	}
	
	@RequestMapping(value = "/listOfUnitMaster", method = RequestMethod.POST)
	public @ResponseBody List<UnitMaster> listOfUnitMaster() {
		List<UnitMaster> listUnitMaster = unitService.listOfUnitMaster();
		return listUnitMaster;
	}
	
	@RequestMapping(value = "/deleteUnitMaster", method = RequestMethod.POST)
	public @ResponseBody String deleteUnitMaster(HttpServletRequest request,@RequestParam("unitMasterId") Integer unitMasterId) {
		String currentPageId="17";
		String response="";
		boolean access=AccessControl.isDeleteAccess(request, currentPageId);
		if(access==true){
			unitService.deleteUnitMaster(unitMasterId);
			response="true";
		}
		else{
			response="false";
		}
		return response;
	}
	
	@RequestMapping(value = "/saveParameterValues", method = RequestMethod.POST)
	public @ResponseBody String saveParameterValues(HttpSession session,HttpServletRequest request,@RequestParam("centreId") String centreId,
			@RequestParam("testId") String testId,@RequestParam("parameterId") String parameterId,@RequestParam("normalValue") String normalValue,
			@RequestParam("parameterName") String parameterName,@RequestParam("generalComment") String generalComment) {
		
		String[] parameterValues = request.getParameterValues("parameterValues[]");
		//for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		String addedBy=(String)session.getAttribute("userName");
		
		JSONArray jsonArray = new JSONArray();
		JSONObject object = null;
		if(generalComment!=null && generalComment!=""){
			object = new JSONObject();
			object.put("parameterId", parameterId);
			object.put("centreId", centreId);
			object.put("testId", testId);
			object.put("normalValue", normalValue);
			object.put("parameterName", parameterName);
			object.put("addedBy", addedBy);
			object.put("addedOn", currentTime);
			object.put("modifyOn", currentTime);
			object.put("modifyBy", addedBy);
			object.put("valueFor", "5");
			object.put("generalComment", generalComment);
			jsonArray.add(object);
		}
		if (parameterValues != null) {
			for (int i = 0; i < parameterValues.length; i++) {
				String[] parameter = parameterValues[i].split("_");
				String lowerValue = parameter[0];
				String upperValue = parameter[1];
				String unitId = parameter[2];
				String valueFor =parameter[3];
				
				object = new JSONObject();
				object.put("parameterId", parameterId);
				object.put("centreId", centreId);
				object.put("testId", testId);
				object.put("normalValue", normalValue);
				object.put("parameterName", parameterName);
				object.put("addedBy", addedBy);
				object.put("addedOn", currentTime);
				object.put("modifyOn", currentTime);
				object.put("modifyBy", addedBy);
				
				object.put("lowerValue", lowerValue);
				object.put("upperValue", upperValue);
				object.put("unitId", unitId);
				object.put("valueFor", valueFor);
				jsonArray.add(object);
			}
		}
		String response=parameterService.saveParameterValues(jsonArray);
		return "Parameter values saved successfully";
	}
	
	@RequestMapping(value = "/getParameterResultsByCentreId", method = RequestMethod.POST)
	public @ResponseBody JSONArray getParameterResultsByCentreId(@RequestParam("centreId") Integer centreId,@RequestParam("testId") Integer testId,@RequestParam("parameterId") Integer parameterId) {
		JSONArray jsonArray=parameterService.getParameterResultsByCentreId(centreId, testId,parameterId);
		return jsonArray;
	}
	
	@RequestMapping(value = "/autoSuggestionsUsers", method = RequestMethod.POST)
	public @ResponseBody List<UserMaster> autoSuggestionsUsers(@RequestParam("searchKeyword") String searchKeyword) {
			List<UserMaster> listUserMasters=userTypeMasterService.autoSuggestionsUsers(searchKeyword);
			return listUserMasters;
	}
	
	@RequestMapping(value = "/getUserByUserId", method = RequestMethod.POST)
	public @ResponseBody UserMaster getUserByUserId(@RequestParam("userId") Integer userId) {
			UserMaster userMasters=userTypeMasterService.getUserByUserId(userId);
			return userMasters;
	}
	
	@RequestMapping(value = "/saveUserAccess", method = RequestMethod.POST)
	public @ResponseBody String saveUserAccess(HttpSession session,HttpServletRequest request,@RequestParam("userId") Integer userId) {
		if(userId!=1){
		String[] userAccessView = request.getParameterValues("userAccessView[]");
		String[] userAccessEdit = request.getParameterValues("userAccessEdit[]");
		String[] userAccessDelete = request.getParameterValues("userAccessDelete[]");
		//for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		String addedBy=(String)session.getAttribute("userName");
		String userViewAccess = Arrays.toString(userAccessView);
		String userEditAccess = Arrays.toString(userAccessEdit);
		String userDeleteAccess = Arrays.toString(userAccessDelete);
			JSONObject object = new JSONObject();
				object.put("userId", userId);
				object.put("addedBy", addedBy);
				object.put("addedOn", currentTime);
				object.put("modifyOn", currentTime);
				object.put("modifyBy", addedBy);
				object.put("isRead", userViewAccess.replaceAll("[\\[\\](){}]",""));
				object.put("isWrite", userEditAccess.replaceAll("[\\[\\](){}]",""));
				object.put("isDelete", userDeleteAccess.replaceAll("[\\[\\](){}]",""));
		String respone=userTypeMasterService.saveUserAccess(object);
		}
		return "Access saved successfully";
	}
	
	@RequestMapping(value = "/getUserAccessByUserId", method = RequestMethod.POST)
	public @ResponseBody JSONObject getUserAccessByUserId(@RequestParam("userId") Integer userId) {
		JSONObject object=userTypeMasterService.getUserAccessByUserId(userId);
		return object;
	}
}
