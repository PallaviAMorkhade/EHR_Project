package com.hms.indus.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.hms.indus.bo.ParameterMaster;
import com.hms.indus.bo.ParameterReportDetail;


public interface ParameterService {
	
	String saveParameterMaster(ParameterMaster parameterMaster);

	String deleteParameterMaster(ParameterMaster parameterMaster);

	String updateParameterMaster(ParameterMaster parameterMaster);
	
	List<ParameterMaster> listOfParameterMaster();

	List<ParameterMaster> getParameterByTestId(Integer testId);

	ParameterMaster getParameterByParameterId(Integer parameterId);

	String saveTestResult(JSONArray jsonArray);
	
	JSONArray getTestResultsByClientId(Integer clientId,Integer testId,Integer reportId,Integer centreId);

	String saveParameterValues(JSONArray jsonArray);

	JSONArray getParameterResultsByCentreId(Integer centreId, Integer testId,
			Integer parameterId);

	JSONArray getParameterValuesByCenterIdAndTestId(Integer centerId,Integer testId,Integer clientId);
	
	//For Parameter Report Details
	String saveParameterReportDetails(List<ParameterReportDetail> parameterReportDetails);
	
	List<ParameterReportDetail> getParameterReportByParameterId(Integer parameterId);
	
	List<ParameterMaster> listParameterReportDetail();

	String deleteParameterReportDetail(ParameterReportDetail parameterReportDetail);

}
