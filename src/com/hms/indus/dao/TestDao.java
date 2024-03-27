package com.hms.indus.dao;

import java.util.List;

import com.hms.indus.bo.TestMaster;
import com.hms.indus.bo.TestMasterLog;

public interface TestDao {

	List<TestMaster> listOfTestMaster();

	List<TestMaster> getPendingTestByClientId(Integer clientId);
	
	Integer saveTest(TestMaster testMaster);

	Integer updateTest(TestMaster testMaster);

	Integer deleteTest(TestMaster testMaster);

	int saveTestLogs(TestMasterLog testMasterLog);
}
