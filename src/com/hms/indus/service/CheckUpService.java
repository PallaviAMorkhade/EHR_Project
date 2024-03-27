package com.hms.indus.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.hms.indus.bo.CheckUpMaster;
import com.hms.indus.bo.GetBeneficiaryApiLog;

public interface CheckUpService {
	
	Integer saveCheckUpMaster(CheckUpMaster checkUpMaster,HttpSession session);

	List<CheckUpMaster> getVisitDateList(Integer clientId);
	
	CheckUpMaster getVisitById(Integer visitId);

}
