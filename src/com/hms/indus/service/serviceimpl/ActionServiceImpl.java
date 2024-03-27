package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.ActionMaster;
import com.hms.indus.dao.ActionDao;
import com.hms.indus.service.ActionService;

@Service
public class ActionServiceImpl implements ActionService{
	
	@Autowired
	ActionDao actionDao;

	@Override
	@Transactional
	public String saveActionMaster(ActionMaster actionMaster) {
		return actionDao.saveActionMaster(actionMaster);
	}

	@Override
	@Transactional
	public String deleteActionMaster(ActionMaster actionMaster) {
		return actionDao.deleteActionMaster(actionMaster);
	}

	@Override
	@Transactional
	public String updateActionMaster(ActionMaster actionMaster) {
		return actionDao.updateActionMaster(actionMaster);
	}

	@Override
	@Transactional
	public List<ActionMaster> listOfActionMaster() {
		return actionDao.listOfActionMaster();
	}

	@Override
	@Transactional
	public ActionMaster getActionMasterByActionId(Integer actionMasterId) {
		return actionDao.getActionMasterByActionId(actionMasterId);
	}
	
	

}
