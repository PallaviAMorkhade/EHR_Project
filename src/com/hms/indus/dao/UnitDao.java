package com.hms.indus.dao;

import java.util.List;

import com.hms.indus.bo.UnitMaster;

public interface UnitDao {
	
	String saveUnitMaster(UnitMaster unitMaster);

	String deleteUnitMaster(Integer unitId);

	String updateUnitMaster(UnitMaster unitMaster);
	
	List<UnitMaster> listOfUnitMaster();

}
