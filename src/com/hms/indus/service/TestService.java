package com.hms.indus.service;

import java.util.List;

import com.hms.indus.bo.PackageMaster;
import com.hms.indus.bo.TestMaster;
import com.hms.indus.bo.TestMasterLog;


public interface TestService {
	
	List<TestMaster> listOfTestMaster();
	
	List<TestMaster> getPendingTestByClientId(Integer clientId);
	
	Integer saveTest(TestMaster testMaster);

	Integer updateTest(TestMaster testMaster);

	Integer deleteTest(TestMaster testMaster);

	int saveTestLogs(TestMasterLog testMasterLog);

}
