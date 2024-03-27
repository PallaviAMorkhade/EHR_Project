package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.VisitTypeMaster;
import com.hms.indus.dao.VisitTypeDao;
import com.hms.indus.service.VisitTypeService;

@Service
public class VisitTypeServiceImpl implements VisitTypeService{
	
	@Autowired
	VisitTypeDao visitTypeDao;

	@Override
	@Transactional
	public String saveVisitTypeMaster(VisitTypeMaster visitTypeMaster) {
		return visitTypeDao.saveVisitTypeMaster(visitTypeMaster);
	}

	@Override
	@Transactional
	public String deleteVisitTypeMaster(VisitTypeMaster visitTypeMaster) {
		return visitTypeDao.deleteVisitTypeMaster(visitTypeMaster);
	}

	@Override
	@Transactional
	public String updateVisitTypeMaster(VisitTypeMaster visitTypeMaster) {
		return visitTypeDao.updateVisitTypeMaster(visitTypeMaster);
	}

	@Override
	@Transactional
	public List<VisitTypeMaster> listOfVisitTypeMaster() {
		return visitTypeDao.listOfVisitTypeMaster();
	}

	@Override
	@Transactional
	public VisitTypeMaster getVisitTypeMasterByVisitTypeId(
			Integer visitTypeMasterId) {
		return visitTypeDao.getVisitTypeMasterByVisitTypeId(visitTypeMasterId);
	}

}
