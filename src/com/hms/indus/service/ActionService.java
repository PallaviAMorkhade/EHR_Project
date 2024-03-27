package com.hms.indus.service;

import java.util.List;

import com.hms.indus.bo.ActionMaster;

public interface ActionService {
	
	String saveActionMaster(ActionMaster actionMaster);

	String deleteActionMaster(ActionMaster actionMaster);

	String updateActionMaster(ActionMaster actionMaster);
	
	List<ActionMaster> listOfActionMaster();

	ActionMaster getActionMasterByActionId(Integer actionMasterId);

}
