package com.hms.indus.service;

import java.util.List;

import com.hms.indus.bo.CentreMaster;
import com.hms.indus.bo.CentreMasterLogs;
import com.hms.indus.bo.PackageMaster;

public interface CentreService {
	
	List<CentreMaster> getListOfCentres();

	CentreMaster getCenterByCenterId(int centerId);
	
	Integer saveCentre(CentreMaster centreMaster);

	Integer updateCentre(CentreMaster centreMaster);

	Integer deleteCentre(CentreMaster centreMaster);

	int saveCentreLogs(CentreMasterLogs centreMasterLogs);

}
