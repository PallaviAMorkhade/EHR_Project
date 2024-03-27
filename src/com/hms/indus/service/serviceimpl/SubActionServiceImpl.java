package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.SubActionMaster;
import com.hms.indus.dao.SubActionDao;
import com.hms.indus.service.SubActionService;

@Service
public class SubActionServiceImpl implements SubActionService{
	
	@Autowired
	SubActionDao subActionDao;

	@Override
	@Transactional
	public String saveSubActionMaster(SubActionMaster subActionMaster) {
		return subActionDao.saveSubActionMaster(subActionMaster);
	}

	@Override
	@Transactional
	public String deleteSubActionMaster(SubActionMaster subActionMaster) {
		return subActionDao.deleteSubActionMaster(subActionMaster);
	}

	@Override
	@Transactional
	public String updateSubActionMaster(SubActionMaster subActionMaster) {
		return subActionDao.updateSubActionMaster(subActionMaster);
	}

	@Override
	@Transactional
	public List<SubActionMaster> listOfSubActionMaster() {
		return subActionDao.listOfSubActionMaster();
	}

	@Override
	@Transactional
	public SubActionMaster getSubActionMasterBySubActionId(
			Integer subActionMasterId) {
		return subActionDao.getSubActionMasterBySubActionId(subActionMasterId);
	}

	@Override
	@Transactional
	public List<SubActionMaster> listOfSubActionMasterByActionId(
			Integer actionId) {
		return subActionDao.listOfSubActionMasterByActionId(actionId);
	}

}
