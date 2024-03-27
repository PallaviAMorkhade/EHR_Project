package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.RejectMaster;
import com.hms.indus.dao.RejectDao;
import com.hms.indus.service.RejectService;

@Service
public class RejectServiceImpl implements RejectService{
	
	@Autowired
	RejectDao rejectDao;

	@Override
	@Transactional
	public String saveRejectMaster(RejectMaster rejectMaster) {
		return rejectDao.saveRejectMaster(rejectMaster);
	}

	@Override
	@Transactional
	public String deleteRejectMaster(RejectMaster rejectMaster) {
		return rejectDao.deleteRejectMaster(rejectMaster);
	}

	@Override
	@Transactional
	public String updateRejectMaster(RejectMaster rejectMaster) {
		return rejectDao.updateRejectMaster(rejectMaster);
	}

	@Override
	@Transactional
	public List<RejectMaster> listOfRejectMaster() {
		return rejectDao.listOfRejectMaster();
	}

	@Override
	@Transactional
	public RejectMaster getRejectMasterByRejectId(Integer rejectMasterId) {
		return rejectDao.getRejectMasterByRejectId(rejectMasterId);
	}

}
