package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.CentreMaster;
import com.hms.indus.bo.CentreMasterLogs;
import com.hms.indus.dao.CenterDao;
import com.hms.indus.service.CentreService;

@Service
public class CenterServiceImpl implements CentreService{

	@Autowired
	CenterDao centerDao;
	
	@Override
	@Transactional
	public List<CentreMaster> getListOfCentres() {
		return centerDao.getListOfCentres();
	}

	@Override
	@Transactional
	public CentreMaster getCenterByCenterId(int centerId) {
		return centerDao.getCenterByCenterId(centerId);
	}

	@Override
	@Transactional
	public Integer saveCentre(CentreMaster centreMaster) {
		return centerDao.saveCentre(centreMaster);
	}

	@Override
	@Transactional
	public Integer updateCentre(CentreMaster centreMaster) {
		return centerDao.updateCentre(centreMaster);
	}

	@Override
	@Transactional
	public Integer deleteCentre(CentreMaster centreMaster) {
		return centerDao.deleteCentre(centreMaster);
	}
	
	@Override
	@Transactional
	public int saveCentreLogs(CentreMasterLogs centreMasterLogs) {
		return centerDao.saveCentreLogs(centreMasterLogs);
	}

	
}
