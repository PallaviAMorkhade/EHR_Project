package com.hms.indus.dao;

import java.util.List;

import com.hms.indus.bo.VisitTypeMaster;

public interface VisitTypeDao {
	
	String saveVisitTypeMaster(VisitTypeMaster visitTypeMaster);

	String deleteVisitTypeMaster(VisitTypeMaster visitTypeMaster);

	String updateVisitTypeMaster(VisitTypeMaster visitTypeMaster);
	
	List<VisitTypeMaster> listOfVisitTypeMaster();

	VisitTypeMaster getVisitTypeMasterByVisitTypeId(Integer visitTypeMasterId);

}
