package com.hms.indus.service;

import java.util.List;

import com.hms.indus.bo.UnitMaster;

public interface UnitService {
	
	String saveUnitMaster(UnitMaster unitMaster);

	String deleteUnitMaster(Integer unitId);

	String updateUnitMaster(UnitMaster unitMaster);
	
	List<UnitMaster> listOfUnitMaster();

}
