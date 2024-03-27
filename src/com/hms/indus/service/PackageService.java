package com.hms.indus.service;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.hms.indus.bo.PackageMaster;
import com.hms.indus.bo.PackageMasterLogs;
import com.hms.indus.bo.TestMaster;

public interface PackageService {

	List<TestMaster> getTestByPackageId(Integer packageId);

	List<PackageMaster> getListOfPackageMaster();
	
	List<TestMaster> getTestByPackageIdInSequence(Integer packageId);

	Integer savePackage(PackageMaster packageMaster);

	Integer updatePackage(PackageMaster packageMaster);

	Integer deletePackage(PackageMaster packageMaster);

	int savePackageLogs(PackageMasterLogs packageMasterLogs);

	int saveTestAgainstPackage(JSONArray testDetailsArr);
	
}
