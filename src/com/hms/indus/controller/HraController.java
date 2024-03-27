package com.hms.indus.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hms.indus.bo.CheckUpMaster;
import com.hms.indus.bo.ClientMaster;
import com.hms.indus.bo.HraTypeMaster;
import com.hms.indus.bo.OptionMaster;
import com.hms.indus.bo.ParameterReportDetail;
import com.hms.indus.bo.QuestionMaster;
import com.hms.indus.bo.QuestionReportDetail;
import com.hms.indus.service.CheckUpService;
import com.hms.indus.service.ClientService;
import com.hms.indus.service.HraService;
import com.hms.indus.util.AccessControl;

@Controller
@RequestMapping(value = "/hra")
public class HraController {

	@Autowired
	HraService hraService;

	@Autowired
	ClientService clientService;
	
	@Autowired
	CheckUpService checkUpService;

	@RequestMapping(value = "/listOfHraTypeMaster", method = RequestMethod.POST)
	public @ResponseBody List<HraTypeMaster> listOfHraTypeMaster() {
		List<HraTypeMaster> listHraTypeMasters = hraService.listOfAllHraType();
		return listHraTypeMasters;
	}

	@RequestMapping(value = "/updateHRATypeMaster", method = RequestMethod.GET)
	public @ResponseBody String updateHRATypeMaster(HttpServletRequest request, HttpSession session,
			@RequestParam("hraTypeMasterName") String hraTypeMasterName,
			@RequestParam("hraPrintFlag") String hraPrintFlag,
			@RequestParam("endUserFlag") String endUserFlag,
			@RequestParam("hraTypeMasterId") Integer hraTypeMasterId) {
		String currentPageId = "13";
		String response = "";
		boolean access = AccessControl.isWriteAccess(request, currentPageId);
		if (access == true) {
			HraTypeMaster hraTypeMaster = new HraTypeMaster();
			hraTypeMaster.setHraTypeName(hraTypeMasterName);
			hraTypeMaster.setHraPrintFlag(hraPrintFlag);
			hraTypeMaster.setEndUserFlag(endUserFlag);
			hraTypeMaster.setHraTypeId(hraTypeMasterId);

			// for current time and date
			java.util.Date date = new java.util.Date();
			java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addedOn = simpleDateFormat.format(date);
			String addedBy = (String) session.getAttribute("userName");
			hraTypeMaster.setModifyBy(addedBy);
			hraTypeMaster.setModifyOn(addedOn);
			hraService.updateHRATypeMaster(hraTypeMaster);
			response = "true";
		} else {
			response = "false";
		}
		return response;
	}

	@RequestMapping(value = "/saveHRATypeMaster", method = RequestMethod.GET)
	public @ResponseBody String saveHRATypeMaster(HttpSession session,
			@RequestParam("hraTypeMasterName") String hraTypeMasterName,
			@RequestParam("hraPrintFlag") String hraPrintFlag,
			@RequestParam("endUserFlag") String endUserFlag) {
		HraTypeMaster hraTypeMaster = new HraTypeMaster();
		hraTypeMaster.setHraTypeName(hraTypeMasterName);
		hraTypeMaster.setHraPrintFlag(hraPrintFlag);
		hraTypeMaster.setEndUserFlag(endUserFlag);
		hraTypeMaster.setHraTypeMasterDeleteFlag(1);

		// for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String addedOn = simpleDateFormat.format(date);
		String addedBy = (String) session.getAttribute("userName");
		hraTypeMaster.setAddedBy(addedBy);
		hraTypeMaster.setAddedOn(addedOn);
		String response = hraService.saveHRATypeMaster(hraTypeMaster);
		return response;
	}

	@RequestMapping(value = "/deleteHRATypeMaster", method = RequestMethod.GET)
	public @ResponseBody String deleteHRATypeMaster(HttpServletRequest request, HttpSession session,
			@RequestParam("hraTypeMasterId") Integer hraTypeMasterId) {
		String currentPageId = "13";
		String response = "";
		boolean access = AccessControl.isDeleteAccess(request, currentPageId);
		if (access == true) {
			HraTypeMaster hraTypeMaster = new HraTypeMaster();
			hraTypeMaster.setHraTypeId(hraTypeMasterId);
			// for current time and date
			java.util.Date date = new java.util.Date();
			java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addedOn = simpleDateFormat.format(date);
			String addedBy = (String) session.getAttribute("userName");
			hraTypeMaster.setModifyBy(addedBy);
			hraTypeMaster.setModifyOn(addedOn);
			hraService.deleteHRATypeMaster(hraTypeMaster);
			response = "true";
		} else {
			response = "false";
		}
		return response;
	}

	@RequestMapping(value = "/saveQuestionMaster", method = RequestMethod.POST)
	public @ResponseBody String saveQuestionMaster(HttpSession session, @RequestParam("question") String question,
			@RequestParam("questionDisplay") String questionDisplay,
			@RequestParam("questionType") String questionType, @RequestParam("hraTypeMasterId") Integer hraTypeMasterId,
			HttpServletRequest request) {
		String[] optionId = request.getParameterValues("optionId[]");

		HraTypeMaster hraTypeMaster = new HraTypeMaster();
		hraTypeMaster.setHraTypeId(hraTypeMasterId);

		QuestionMaster questionMaster = new QuestionMaster();
		questionMaster.setQuestion(question);
		questionMaster.setQuestionDisplay(questionDisplay);
		questionMaster.setQuestionType(questionType);
		questionMaster.setQuestionMasterDeleteFlag(1);
		// for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String addedOn = simpleDateFormat.format(date);
		String addedBy = (String) session.getAttribute("userName");
		questionMaster.setAddedBy(addedBy);
		questionMaster.setAddedOn(addedOn);
		questionMaster.setHraTypeMaster(hraTypeMaster);

		List<OptionMaster> optionMasterSet = new ArrayList<OptionMaster>();
		if (optionId != null) {
			for (int i = 0; i < optionId.length; i++) {
				if (optionId[i] != null && optionId[i] != "") {
					OptionMaster optionMaster = new OptionMaster();
					String option = optionId[i];
					optionMaster.setOption(option);
					optionMaster.setOptionMasterDeleteFlag(1);
					optionMaster.setQuestionMaster(questionMaster);
					optionMasterSet.add(optionMaster);
				}
			}
		}
		questionMaster.setOptionMasterSet(optionMasterSet);
		String response = hraService.saveQuestionMaster(questionMaster);
		return response;
	}

	@RequestMapping(value = "/listOfQuestionMaster", method = RequestMethod.POST)
	public @ResponseBody List<QuestionMaster> listOfQuestionMaster() {
		List<QuestionMaster> listQuestionMasters = hraService.listOfAllQuestions();
		return listQuestionMasters;
	}

	@RequestMapping(value = "/getQuestionMasterByQuestionId", method = RequestMethod.POST)
	public @ResponseBody QuestionMaster getQuestionMasterByQuestionId(HttpServletRequest request,
			@RequestParam("questionId") Integer questionId) {
		String currentPageId = "14";
		boolean access = AccessControl.isWriteAccess(request, currentPageId);
		QuestionMaster questionMaster = new QuestionMaster();
		if (access == true) {
			questionMaster = hraService.getQuestionMasterByQuestionId(questionId);
		} else {
			questionMaster.setAddedBy("false");
		}
		return questionMaster;
	}

	@RequestMapping(value = "/updateQuestionMaster", method = RequestMethod.POST)
	public @ResponseBody String updateQuestionMaster(HttpSession session,@RequestParam("question") String question,
			@RequestParam("questionDisplay") String questionDisplay,
			@RequestParam("questionType") String questionType, @RequestParam("hraTypeMasterId") Integer hraTypeMasterId,
			@RequestParam("questionMasterId") Integer questionMasterId, HttpServletRequest request) {
		String[] optionId = request.getParameterValues("optionId[]");

		HraTypeMaster hraTypeMaster = new HraTypeMaster();
		hraTypeMaster.setHraTypeId(hraTypeMasterId);

		QuestionMaster questionMaster = new QuestionMaster();
		questionMaster.setQuestionId(questionMasterId);
		questionMaster.setQuestion(question);
		questionMaster.setQuestionDisplay(questionDisplay);
		questionMaster.setQuestionType(questionType);
		questionMaster.setQuestionMasterDeleteFlag(1);
		// for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String addedOn = simpleDateFormat.format(date);
		String addedBy = (String) session.getAttribute("userName");
		questionMaster.setModifyBy(addedBy);
		questionMaster.setModifyOn(addedOn);
		questionMaster.setHraTypeMaster(hraTypeMaster);

		List<OptionMaster> optionMasterSet = new ArrayList<OptionMaster>();
		for (int i = 0; i < optionId.length; i++) {
			OptionMaster optionMaster = new OptionMaster();
			String optionArray = optionId[i];
			String[] option = optionArray.split("_");
			if (!option[1].equals("undefined")) {
				optionMaster.setOptionId(Integer.parseInt(option[1]));
			}
			optionMaster.setOption(option[0]);
			optionMaster.setOptionMasterDeleteFlag(1);
			optionMaster.setQuestionMaster(questionMaster);
			optionMasterSet.add(optionMaster);
		}
		questionMaster.setOptionMasterSet(optionMasterSet);
		String response = hraService.updateQuestionMaster(questionMaster);
		return response;
	}

	@RequestMapping(value = "/deleteOptionMaster", method = RequestMethod.POST)
	public @ResponseBody List<OptionMaster> deleteOptionMaster(@RequestParam("questionId") Integer questionId,
			@RequestParam("optionId") Integer optionId) {
		List<OptionMaster> optionMasterList = hraService.deleteOptionMaster(questionId, optionId);
		return optionMasterList;
	}

	@RequestMapping(value = "/listOfQuestionMasterByHraId", method = RequestMethod.POST)
	public @ResponseBody List<QuestionMaster> listOfQuestionMasterByHraId(
			@RequestParam("hraTypeMasterId") Integer hraTypeMasterId) {
		List<QuestionMaster> listQuestionMasters = hraService.listOfAllQuestionsByHraId(hraTypeMasterId);
		return listQuestionMasters;
	}

	@RequestMapping(value = "/deleteQuestionMaster", method = RequestMethod.GET)
	public @ResponseBody String deleteQuestionMaster(HttpServletRequest request,HttpSession session,
			@RequestParam("questionId") Integer questionId) {
		String currentPageId = "14";
		String response = "";
		boolean access = AccessControl.isDeleteAccess(request, currentPageId);
		if (access == true) {
			QuestionMaster questionMaster = new QuestionMaster();
			questionMaster.setQuestionId(questionId);
			// for current time and date
			java.util.Date date = new java.util.Date();
			java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String addedOn = simpleDateFormat.format(date);
			String addedBy = (String) session.getAttribute("userName");
			questionMaster.setModifyBy(addedBy);
			questionMaster.setModifyOn(addedOn);
			hraService.deleteQuestionMaster(questionMaster);
			response = "true";
		} else {
			response = "false";
		}
		return response;
	}

	@RequestMapping(value = "/saveClientHRADetails", method = RequestMethod.POST)
	public @ResponseBody String saveClientHRADetails(HttpServletRequest request) {
		String[] optionChecked = request.getParameterValues("optionChecked[]");
		String[] uncheckedOption = request.getParameterValues("uncheckedOption[]");
		String[] textAnswer = request.getParameterValues("textAnswer[]");
		String checkUpId = request.getParameter("checkUpId");
		HttpSession session = request.getSession();
		Integer clientId = (Integer) session.getAttribute("clientId");
		String userName = (String) session.getAttribute("userName");
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);

		JSONArray jsonArray = new JSONArray();
		JSONArray uncheckedArray = new JSONArray();
		JSONObject object = null;

		if (optionChecked != null) {
			for (int i = 0; i < optionChecked.length; i++) {
				String[] question = optionChecked[i].split("_");

				String questionId = question[0];
				Set<String> optionSet = new HashSet<String>();
				for (int j = 0; j < optionChecked.length; j++) {
					String[] questionArray = optionChecked[j].split("_");
					String anotharQuestionId = questionArray[0];
					if (questionId.equals(anotharQuestionId)) {
						optionSet.add(questionArray[1]);
					}
				}
				String options = StringUtils.collectionToDelimitedString(optionSet, ",");

				String optionId = question[1];
				object = new JSONObject();
				object.put("clientId", clientId);
				object.put("addedBy", userName);
				object.put("modifyBy", userName);
				object.put("addedOn", currentTime);
				object.put("modifyOn", currentTime);
				object.put("questionId", questionId);
				object.put("checkUpId",checkUpId);
				object.put("text", options);
				jsonArray.add(object);
			}
		}
		if (textAnswer != null) {
			for (int i = 0; i < textAnswer.length; i++) {
				String[] answer = textAnswer[i].split("_");
				String text = answer[0];
				String questionId = answer[1];
				object = new JSONObject();
				object.put("clientId", clientId);
				object.put("addedBy", userName);
				object.put("modifyBy", userName);
				object.put("addedOn", currentTime);
				object.put("modifyOn", currentTime);
				object.put("questionId", questionId);
				object.put("checkUpId",checkUpId);
				object.put("text", text);
				jsonArray.add(object);
			}
		}

		if (uncheckedOption != null) {
			for (int i = 0; i < uncheckedOption.length; i++) {
				String[] question = uncheckedOption[i].split("_");
				String questionId = question[0];
				Set<String> optionSet = new HashSet<String>();
				for (int j = 0; j < uncheckedOption.length; j++) {
					String[] questionArray = uncheckedOption[j].split("_");
					String anotharQuestionId = questionArray[0];
					if (questionId.equals(anotharQuestionId)) {
						optionSet.add(questionArray[1]);
					}
				}
				/*
				 * String options = StringUtils.collectionToDelimitedString( optionSet, ",");
				 */

				String optionId = question[1];
				object = new JSONObject();
				object.put("clientId", clientId);
				object.put("addedBy", userName);
				object.put("modifyBy", userName);
				object.put("addedOn", currentTime);
				object.put("modifyOn", currentTime);
				object.put("questionId", questionId);
				object.put("checkUpId", checkUpId);
				uncheckedArray.add(object);
			}
		}
		String response = hraService.saveClientHRADetails(jsonArray, uncheckedArray);
		return "response";
	}

	@RequestMapping(value = "/getListOfQuestionClientHRADetails", method = RequestMethod.POST)
	public @ResponseBody JSONArray getListOfQuestionClientHRADetails(
			@RequestParam("clientId") Integer clientId,@RequestParam("visitId") Integer visitId) {
		JSONArray jsonArray = hraService.getListOfQuestionClientHRADetails(clientId,visitId);
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject object = (JSONObject) jsonArray.get(i);
			String questionId = (String) object.get("questionId");
			QuestionMaster questionMaster = hraService.getQuestionMasterByQuestionId(Integer.parseInt(questionId));
			String questionType = questionMaster.getQuestionType();
			String question = questionMaster.getQuestion();
			String textAnswer = (String) object.get("textAnswer");
			HraTypeMaster hraTypeMaster = questionMaster.getHraTypeMaster();
			Integer hraTypeId = hraTypeMaster.getHraTypeId();
			String hraTypeName = hraTypeMaster.getHraTypeName();
			String endUserFlag = hraTypeMaster.getEndUserFlag();
			List<OptionMaster> optionSet = questionMaster.getOptionMasterSet();
			object.put("hraTypeId", hraTypeId);
			object.put("hraTypeName", hraTypeName);
			object.put("endUserFlag", endUserFlag);
			object.put("questionId", questionId);
			object.put("question", question);
			object.put("optionSet", optionSet);
			object.put("questionType", questionType);
			if (questionType.equals("checkbox")) {
				/*String[] optionIds = textAnswer.split(",");
				for (int j = 0; j < optionIds.length; j++) {
					object.put("optionId" + j + "", optionIds[j]);
				}*/
			} else {
				object.put("textAnswer", textAnswer);
			}
		}
		return jsonArray;
	}

	@RequestMapping(value = "/hraPrintPage", method = RequestMethod.GET)
	public ModelAndView hraPrintPage(@RequestParam("clientId") Integer clientId,@RequestParam("visitId") Integer visitId) {
		ClientMaster clientMaster = clientService.getClientByclientId(clientId);
		JSONArray jsonArray = hraService.getListOfQuestionClientHRADetails(clientId,visitId);
		HashMap<Integer, JSONArray> hashMap = new HashMap<Integer, JSONArray>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject object = (JSONObject) jsonArray.get(i);
			String questionId = (String) object.get("questionId");
			QuestionMaster questionMaster = hraService.getQuestionMasterByQuestionId(Integer.parseInt(questionId));
			String questionType = questionMaster.getQuestionType();
			String question = questionMaster.getQuestion();
			String textAnswer = (String) object.get("textAnswer");
			HraTypeMaster hraTypeMaster = questionMaster.getHraTypeMaster();
			Integer hraTypeId = hraTypeMaster.getHraTypeId();
			String hraTypeName = hraTypeMaster.getHraTypeName();
			String hraPrintFlag = hraTypeMaster.getHraPrintFlag();
			List<OptionMaster> optionSet = questionMaster.getOptionMasterSet();
			object.put("hraTypeId", hraTypeId);
			object.put("hraTypeName", hraTypeName);
			object.put("hraPrintFlag", hraPrintFlag);
			object.put("questionId", questionId);
			object.put("question", question);
			object.put("optionSet", optionSet);
			object.put("questionType", questionType);
			if (questionType.equals("checkbox")) {
				String[] optionIds = textAnswer.split(",");
				for (int j = 0; j < optionIds.length; j++) {
					object.put("optionId" + j + "", optionIds[j]);
				}
			} else {
				object.put("textAnswer", textAnswer);
			}

			if (hashMap.containsKey(hraTypeId)) {
				JSONArray questionArray = hashMap.get(hraTypeId);
				questionArray.add(object);
				hashMap.put(hraTypeId, questionArray);
			} else {
				JSONArray questionArray = new JSONArray();
				questionArray.add(object);
				hashMap.put(hraTypeId, questionArray);
			}

		}
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("indus_hra_print");
		modelAndView.addObject("clientMaster", clientMaster);
		modelAndView.addObject("hashMap", hashMap);
		return modelAndView;
	}
	
	@RequestMapping(value = "/copyHRAFromVisit", method = RequestMethod.POST)
	public @ResponseBody String copyHRAFromVisit(HttpSession session,
			@RequestParam("clientId") Integer clientId,@RequestParam("visitId") Integer visitId) {
		String userName = (String) session.getAttribute("userName");
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		JSONObject object = new JSONObject();
		object.put("addedBy", userName);
		object.put("addedOn", currentTime);
		object.put("modifyBy", userName);
		object.put("modifyOn", currentTime);
		object.put("clientId", clientId);
		object.put("visitId", visitId);
		return hraService.copyHRAFromVisit(object);
	}
	
	@RequestMapping(value = "/getVisitIdByClientId", method = RequestMethod.GET)
	public @ResponseBody Integer getVisitIdByClientId(@RequestParam("clientId") Integer clientId) {
		return hraService.getVisitIdByClientId(clientId);
	}
	
	@RequestMapping(value = "/healthReport", method = RequestMethod.GET)
	public ModelAndView healthReport(@RequestParam("clientId") Integer clientId,
			@RequestParam("visitId") Integer visitId) {
		ClientMaster clientMaster = clientService.getClientByclientId(clientId);
		JSONArray jsonArray = hraService.getListOfQuestionClientHRADetails(clientId,visitId);
		
		JSONArray visitList = new JSONArray();
		List<CheckUpMaster> checkUpList = checkUpService.getVisitDateList(clientId);
		for (int i = 0; i < checkUpList.size(); i++) {
			JSONObject visitObject = new JSONObject();
			visitId = checkUpList.get(i).getCheckUpId();
			String visitDate = checkUpList.get(i).getCheckUpDate();
			JSONArray parameterList = clientService.getPatientAnalysis(clientId, visitId);
			visitObject.put("visitId", visitId);
			visitObject.put("visitDate", visitDate);
			visitObject.put("parameterList", parameterList);
			visitList.add(visitObject);
		}
		
		Float hraHealthScore = 0f;
		Integer hraHealthScoreCount = 0;
		Float parameterHealthScore = 0f;
		Integer parameterHealthScoreCount = 0;
		HashMap<Integer, JSONArray> hashMap = new HashMap<Integer, JSONArray>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject object = (JSONObject) jsonArray.get(i);
			String questionId = (String) object.get("questionId");
			QuestionMaster questionMaster = hraService.getQuestionMasterByQuestionId(Integer.parseInt(questionId));
			String questionType = questionMaster.getQuestionType();
			String question = questionMaster.getQuestion();
			String questionDisplay = questionMaster.getQuestionDisplay();
			String textAnswer = (String) object.get("textAnswer");
			HraTypeMaster hraTypeMaster = questionMaster.getHraTypeMaster();
			Integer hraTypeId = hraTypeMaster.getHraTypeId();
			String hraTypeName = hraTypeMaster.getHraTypeName();
			String hraPrintFlag = hraTypeMaster.getHraPrintFlag();
			List<OptionMaster> optionSet = questionMaster.getOptionMasterSet();
			object.put("hraTypeId", hraTypeId);
			object.put("hraTypeName", hraTypeName);
			object.put("hraPrintFlag", hraPrintFlag);
			object.put("questionId", questionId);
			object.put("question", question);
			object.put("questionDisplay", questionDisplay);
			object.put("optionSet", optionSet);
			object.put("questionType", questionType);
			if (questionType.equals("checkbox")) {
				String[] optionIds = textAnswer.split(",");
				for (int j = 0; j < optionIds.length; j++) {
					object.put("optionId" + j + "", optionIds[j]);
				}
			} else {
				object.put("textAnswer", textAnswer);
				object.put("questionReportDetail", questionMaster.getQuestionReportDetail());
			}

			if (hashMap.containsKey(hraTypeId)) {
				JSONArray questionArray = hashMap.get(hraTypeId);
				questionArray.add(object);
				hashMap.put(hraTypeId, questionArray);
			} else {
				JSONArray questionArray = new JSONArray();
				questionArray.add(object);
				hashMap.put(hraTypeId, questionArray);
			}

			for(OptionMaster optionMaster : optionSet) {
				QuestionReportDetail questionReportDetail = optionMaster.getQuestionReportDetail();
				if(questionReportDetail!=null) {
					Float healthScore = questionReportDetail.getHealthScore();
					hraHealthScore = hraHealthScore + healthScore;
					hraHealthScoreCount++;
				}
			}
			
			QuestionReportDetail questionReportDetail = questionMaster.getQuestionReportDetail();
			if(questionReportDetail!=null) {
				Float healthScore = questionReportDetail.getHealthScore();
				hraHealthScore = hraHealthScore + healthScore;
				hraHealthScoreCount++;
			}
			
		}
		
		/*for(int i=0;i<patientParameter.size();i++) {
			ParameterReportDetail parameterReportDetail = (ParameterReportDetail)((JSONObject) patientParameter.get(i)).get("parameterReportDetail");
			if(parameterReportDetail!=null) {
				Float healthScore = (Float) parameterReportDetail.getHealthScore();
				parameterHealthScore = parameterHealthScore + healthScore;
				parameterHealthScoreCount++;
			}
		}*/
		
		hraHealthScore = hraHealthScore / hraHealthScoreCount;
		parameterHealthScore = parameterHealthScore / parameterHealthScoreCount;
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("indus_health_report");
		modelAndView.addObject("clientMaster", clientMaster);
		modelAndView.addObject("visitList", visitList);
		modelAndView.addObject("hashMap", hashMap);
		modelAndView.addObject("hraHealthScore", hraHealthScore);
		modelAndView.addObject("parameterHealthScore", parameterHealthScore);
		return modelAndView;
	}
	
	@RequestMapping(value = "/mergePreCouncelling", method = RequestMethod.POST)
	public @ResponseBody String mergePreCouncelling(HttpSession session,
			@RequestParam("clientId") Integer clientId,@RequestParam("visitId") Integer visitId) {
		String userName = (String) session.getAttribute("userName");
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		JSONObject object = new JSONObject();
		object.put("mergedBy", userName);
		object.put("mergedOn", currentTime);
		object.put("clientId", clientId);
		object.put("visitId", visitId);
		return hraService.mergePreCouncelling(object);
	}
	
	@RequestMapping(value = "/checkPreCouncelling", method = RequestMethod.GET)
	public @ResponseBody Integer checkPreCouncelling(@RequestParam("clientId") Integer clientId,
			@RequestParam("visitId") Integer visitId) {
		JSONObject object = new JSONObject();
		object.put("clientId", clientId);
		object.put("visitId", visitId);
		return hraService.checkPreCouncelling(object);
	}
	
}