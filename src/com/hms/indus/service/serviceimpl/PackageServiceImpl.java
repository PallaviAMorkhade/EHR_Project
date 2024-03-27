package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.PackageMaster;
import com.hms.indus.bo.PackageMasterLogs;
import com.hms.indus.bo.TestMaster;
import com.hms.indus.dao.PackageDao;
import com.hms.indus.service.PackageService;

@Service
public class PackageServiceImpl implements PackageService{
	
	@Autowired 
	PackageDao packageDao;
	
	@Override
	@Transactional
	public List<TestMaster> getTestByPackageId(Integer packageId) {
		return packageDao.getTestByPackageId(packageId);
	}

	@Override
	@Transactional
	public List<PackageMaster> getListOfPackageMaster() {
		return packageDao.getListOfPackageMaster();
	}

	@Override
	@Transactional
	public List<TestMaster> getTestByPackageIdInSequence(Integer packageId) {
		return packageDao.getTestByPackageIdInSequence(packageId);
	}

	@Override
	@Transactional
	public Integer savePackage(PackageMaster packageMaster) {
		return packageDao.savePackage(packageMaster);
	}

	@Override
	@Transactional
	public Integer updatePackage(PackageMaster packageMaster) {
		return packageDao.updatePackage(packageMaster);
	}

	@Override
	@Transactional
	public Integer deletePackage(PackageMaster packageMaster) {
		return packageDao.deletePackage(packageMaster);
	}
	
	@Override
	@Transactional
	public int savePackageLogs(PackageMasterLogs packageMasterLogs) {
		return packageDao.savePackageLogs(packageMasterLogs);
	}
	
	@Override
	@Transactional
	public int saveTestAgainstPackage(JSONArray testDetailsArr) {
		int a = packageDao.saveTestAgainstPackage(  testDetailsArr);
		return a;
	}

}
