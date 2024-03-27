package com.hms.indus.dao;

import java.util.List;

import com.hms.indus.bo.SubActionMaster;

public interface SubActionDao {

	String saveSubActionMaster(SubActionMaster subActionMaster);

	String deleteSubActionMaster(SubActionMaster subActionMaster);

	String updateSubActionMaster(SubActionMaster subActionMaster);
	
	List<SubActionMaster> listOfSubActionMaster();

	SubActionMaster getSubActionMasterBySubActionId(Integer subActionMasterId);
	
	List<SubActionMaster> listOfSubActionMasterByActionId(Integer actionId);
}
