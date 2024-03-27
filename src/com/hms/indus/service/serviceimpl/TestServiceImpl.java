package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.CentreMasterLogs;
import com.hms.indus.bo.TestMaster;
import com.hms.indus.bo.TestMasterLog;
import com.hms.indus.dao.TestDao;
import com.hms.indus.service.TestService;

@Service
public class TestServiceImpl implements TestService{
	
	@Autowired
	TestDao testDao;

	@Override
	@Transactional
	public List<TestMaster> listOfTestMaster() {
		return testDao.listOfTestMaster();
	}

	@Override
	@Transactional
	public List<TestMaster> getPendingTestByClientId(Integer clientId) {
		return testDao.getPendingTestByClientId(clientId);
	}

	@Override
	@Transactional
	public Integer saveTest(TestMaster testMaster) {
		return testDao.saveTest(testMaster);
	}

	@Override
	@Transactional
	public Integer updateTest(TestMaster testMaster) {
		return testDao.updateTest(testMaster);
	}

	@Override
	@Transactional
	public Integer deleteTest(TestMaster testMaster) {
		return testDao.deleteTest(testMaster);
	}
	
	@Override
	@Transactional
	public int saveTestLogs(TestMasterLog testMasterLog) {
		return testDao.saveTestLogs(testMasterLog);
	}

}
