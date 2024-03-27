package com.hms.indus.dao;

import java.util.List;

import com.hms.indus.bo.CentreMaster;
import com.hms.indus.bo.CentreMasterLogs;

public interface CenterDao {

	List<CentreMaster> getListOfCentres();

	CentreMaster getCenterByCenterId(int centerId);
	
	Integer saveCentre(CentreMaster centreMaster);

	Integer updateCentre(CentreMaster centreMaster);

	Integer deleteCentre(CentreMaster centreMaster);

	int saveCentreLogs(CentreMasterLogs centreMasterLogs);

}
