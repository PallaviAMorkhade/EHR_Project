package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.ParameterMaster;
import com.hms.indus.bo.ParameterReportDetail;
import com.hms.indus.dao.ParameterDao;
import com.hms.indus.service.ParameterService;

@Service
public class ParameterServiceImpl implements ParameterService{
	
	@Autowired
	ParameterDao parameterDao;

	@Override
	@Transactional
	public String saveParameterMaster(ParameterMaster parameterMaster) {
		return parameterDao.saveParameterMaster(parameterMaster);
	}

	@Override
	@Transactional
	public String deleteParameterMaster(ParameterMaster parameterMaster) {
		return parameterDao.deleteParameterMaster(parameterMaster);
	}

	@Override
	@Transactional
	public String updateParameterMaster(ParameterMaster parameterMaster) {
		return parameterDao.updateParameterMaster(parameterMaster);
	}

	@Override
	@Transactional
	public List<ParameterMaster> listOfParameterMaster() {
		return parameterDao.listOfParameterMaster();
	}

	@Override
	@Transactional
	public List<ParameterMaster> getParameterByTestId(Integer testId) {
		return parameterDao.getParameterByTestId(testId);
	}

	@Override
	@Transactional
	public ParameterMaster getParameterByParameterId(Integer parameterId) {
		return parameterDao.getParameterByParameterId(parameterId);
	}

	@Override
	@Transactional
	public String saveTestResult(JSONArray jsonArray) {
		return parameterDao.saveTestResult(jsonArray);
	}

	@Override
	@Transactional
	public JSONArray getTestResultsByClientId(Integer clientId,Integer testId,Integer reportId,Integer centreId) {
		return parameterDao.getTestResultsByClientId(clientId,testId,reportId,centreId);
	}

	@Override
	@Transactional
	public String saveParameterValues(JSONArray jsonArray) {
		return parameterDao.saveParameterValues(jsonArray);
	}

	@Override
	@Transactional
	public JSONArray getParameterResultsByCentreId(Integer centreId,
			Integer testId, Integer parameterId) {
		return parameterDao.getParameterResultsByCentreId(centreId,testId, parameterId);
	}

	@Override
	@Transactional
	public JSONArray getParameterValuesByCenterIdAndTestId(Integer centerId,
			Integer testId,Integer clientId) {
		return parameterDao.getParameterValuesByCenterIdAndTestId(centerId, testId, clientId);
	}

	@Override
	@Transactional
	public String saveParameterReportDetails(List<ParameterReportDetail> parameterReportDetails) {
		return parameterDao.saveParameterReportDetails(parameterReportDetails);
	}

	@Override
	@Transactional
	public List<ParameterReportDetail> getParameterReportByParameterId(Integer parameterId) {
		return parameterDao.getParameterReportByParameterId(parameterId);
	}

	@Override
	@Transactional
	public List<ParameterMaster> listParameterReportDetail() {
		return parameterDao.listParameterReportDetail();
	}

	@Override
	@Transactional
	public String deleteParameterReportDetail(ParameterReportDetail parameterReportDetail) {
		return parameterDao.deleteParameterReportDetail(parameterReportDetail);
	}

}
