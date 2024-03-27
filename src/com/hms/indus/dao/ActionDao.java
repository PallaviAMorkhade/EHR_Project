package com.hms.indus.dao;

import java.util.List;

import com.hms.indus.bo.ActionMaster;

public interface ActionDao {
	
	String saveActionMaster(ActionMaster actionMaster);

	String deleteActionMaster(ActionMaster actionMaster);

	String updateActionMaster(ActionMaster actionMaster);
	
	List<ActionMaster> listOfActionMaster();

	ActionMaster getActionMasterByActionId(Integer actionMasterId);

}
