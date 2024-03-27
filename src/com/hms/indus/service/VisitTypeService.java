package com.hms.indus.service;

import java.util.List;

import com.hms.indus.bo.VisitTypeMaster;

public interface VisitTypeService {
	
	String saveVisitTypeMaster(VisitTypeMaster visitTypeMaster);

	String deleteVisitTypeMaster(VisitTypeMaster visitTypeMaster);

	String updateVisitTypeMaster(VisitTypeMaster visitTypeMaster);
	
	List<VisitTypeMaster> listOfVisitTypeMaster();

	VisitTypeMaster getVisitTypeMasterByVisitTypeId(Integer visitTypeMasterId);
}
