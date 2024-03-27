package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.CheckUpMaster;
import com.hms.indus.bo.GetBeneficiaryApiLog;
import com.hms.indus.dao.CheckUpDao;
import com.hms.indus.service.CheckUpService;

@Service
public class CheckUpServiceImpl implements CheckUpService{
	
	@Autowired
	CheckUpDao checkUpDao;

	@Override
	@Transactional
	public Integer saveCheckUpMaster(CheckUpMaster checkUpMaster,HttpSession session) {
		return checkUpDao.saveCheckUpMaster(checkUpMaster,session);
	}

	@Override
	@Transactional
	public List<CheckUpMaster> getVisitDateList(Integer clientId) {
		return checkUpDao.getVisitDateList(clientId);
	}

	@Override
	@Transactional
	public CheckUpMaster getVisitById(Integer visitId) {
		return checkUpDao.getVisitById(visitId);
	}

}
