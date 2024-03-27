package com.hms.indus.dao;

import java.util.List;

import com.hms.indus.bo.RejectMaster;

public interface RejectDao {

	String saveRejectMaster(RejectMaster rejectMaster);

	String deleteRejectMaster(RejectMaster rejectMaster);

	String updateRejectMaster(RejectMaster rejectMaster);
	
	List<RejectMaster> listOfRejectMaster();

	RejectMaster getRejectMasterByRejectId(Integer rejectMasterId);
}
