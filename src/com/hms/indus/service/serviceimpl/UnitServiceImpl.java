package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.UnitMaster;
import com.hms.indus.dao.UnitDao;
import com.hms.indus.service.UnitService;

@Service
public class UnitServiceImpl implements UnitService{
	
	@Autowired
	UnitDao unitDao;

	@Override
	@Transactional
	public String saveUnitMaster(UnitMaster unitMaster) {
		return unitDao.saveUnitMaster(unitMaster);
	}

	@Override
	@Transactional
	public String deleteUnitMaster(Integer unitId) {
		return unitDao.deleteUnitMaster(unitId);
	}

	@Override
	@Transactional
	public String updateUnitMaster(UnitMaster unitMaster) {
		return unitDao.updateUnitMaster(unitMaster);
	}

	@Override
	@Transactional
	public List<UnitMaster> listOfUnitMaster() {
		return unitDao.listOfUnitMaster();
	}

}
