package com.hms.indus.service;

import java.util.List;

import com.hms.indus.bo.RejectMaster;

public interface RejectService {

	String saveRejectMaster(RejectMaster rejectMaster);

	String deleteRejectMaster(RejectMaster rejectMaster);

	String updateRejectMaster(RejectMaster rejectMaster);
	
	List<RejectMaster> listOfRejectMaster();

	RejectMaster getRejectMasterByRejectId(Integer rejectMasterId);
}
