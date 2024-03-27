package com.hms.indus.dao.daoimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.IntegerType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.CheckUpMaster;
import com.hms.indus.bo.ContentMaster;
import com.hms.indus.bo.PackageMaster;
import com.hms.indus.bo.PackageMasterLogs;
import com.hms.indus.bo.TestMaster;
import com.hms.indus.dao.PackageDao;

@Repository
public class PackageDaoImpl implements PackageDao{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<TestMaster> getTestByPackageId(Integer packageId) {
		SQLQuery query = null;
		List<TestMaster> testMasterList=new ArrayList<TestMaster>();
		try {
		query = sessionFactory
				.getCurrentSession()
				.createSQLQuery("Select test.test_id, test.test_desc, testpackage.sequence, testpackage.ehr_test_package_id FROM ehr_test_package testpackage inner join ehr_test_master test ON testpackage.test_id=test.test_id where testpackage.package_id ="+packageId);
		List<Object[]> testRows = query.list();
		for (Object[] rows : testRows) 
		{
			TestMaster testMaster=new TestMaster();
			if (rows[0]!= null) {
				testMaster.setTestId(Integer.parseInt(rows[0].toString()));
			}
			if (rows[1]!= null) {
				testMaster.setTestDescription(rows[1].toString());
			}
			if (rows[2]!= null) {
				testMaster.setSequence(Integer.parseInt(rows[2].toString()));
			}else {
				testMaster.setSequence(0);
			}
			if (rows[3]!= null) {
				testMaster.setEhrTestPackageId(Integer.parseInt(rows[3].toString()));
			}
			testMasterList.add(testMaster);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.sort(testMasterList, new TestMaster());
		return testMasterList;
	}
	
	@Override
	public List<TestMaster> getTestByPackageIdInSequence(Integer packageId) {
		SQLQuery query = null;
		List<TestMaster> testMasterList=new ArrayList<TestMaster>();
		try {
		query = sessionFactory
				.getCurrentSession()
				.createSQLQuery("Select test.test_id, test.test_desc,sequence FROM ehr_test_package testpackage inner join"
				+" ehr_test_master test ON testpackage.test_id = test.test_id where testpackage.package_id = "+packageId+" and sequence Is not null order by sequence");
		List<Object[]> testRows = query.list();
		for (Object[] rows : testRows) 
		{
			TestMaster testMaster=new TestMaster();
			if (rows[0]!= null) {
				testMaster.setTestId(Integer.parseInt(rows[0].toString()));
			}
			if (rows[1]!= null) {
				testMaster.setTestDescription(rows[1].toString());
			}
			testMasterList.add(testMaster);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testMasterList;
	}

	@Override
	public List<PackageMaster> getListOfPackageMaster() {
		List<PackageMaster> packageMasterList=new ArrayList<PackageMaster>();
		SQLQuery query = null;
		try {		
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery("select package_id,package_desc from ehr_package_master");
			List<Object[]> testRows = query.list();
			for (Object[] rows : testRows) 
			{
				PackageMaster packageMaster=new PackageMaster();
				if (rows[0]!= null) {
					packageMaster.setPackageId(Integer.parseInt(rows[0].toString()));
				}
				if (rows[1]!= null) {
					packageMaster.setPackageDescription(rows[1].toString());
				}
				packageMasterList.add(packageMaster);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return packageMasterList;
	}

	@Override
	public Integer savePackage(PackageMaster packageMaster) {
		Integer response = 0;
		int count = 0;
			try {
				SQLQuery reportSql = sessionFactory
						.getCurrentSession()
						.createSQLQuery("SELECT count(package_id) as package_id FROM ehr_package_master where package_id="+packageMaster.getPackageId()+" ");

				reportSql.addScalar("package_id", IntegerType.INSTANCE);
				count  = (Integer) reportSql.uniqueResult();
				System.err.println("package_id count====="+count);	
			
			if(count > 0) {
				response=-1;
			}else {
				response = (Integer) sessionFactory.getCurrentSession().save(packageMaster);
			}			
			
		} catch (Exception e) {
			response = 0;
		}
		return response;
	}

	@Override
	public Integer updatePackage(PackageMaster packageMaster) {
		Integer response = 0;
		try {
			String hqlUpdate = "update PackageMaster set packageDescription=:packageDescription, "
					+ "packageShortDescription=:packageShortDescription, "
					+ "modAt=:modAt, modBy=:modBy "
					+ "where packageId=:packageId";
			Query query = sessionFactory.getCurrentSession().createQuery(hqlUpdate);
			query.setParameter("packageDescription",packageMaster.getPackageDescription());
			query.setParameter("packageShortDescription",packageMaster.getPackageShortDescription());
			query.setParameter("modAt",packageMaster.getModAt());
			query.setParameter("modBy",packageMaster.getModBy());
			query.setParameter("packageId",packageMaster.getPackageId());
			query.executeUpdate();
		response = 1;
		} catch (Exception e) {
			response = 0;
		}
		return response;
	}

	@Override
	public Integer deletePackage(PackageMaster packageMaster) {
		Integer response = 0;
		try {
			String hqlUpdate = "update PackageMaster set isActive=:isActive, "
					+ "modAt=:modAt, modBy=:modBy "
					+ "where packageId=:packageId";
			Query query = sessionFactory.getCurrentSession().createQuery(hqlUpdate);
			query.setParameter("isActive",packageMaster.getIsActive());
			query.setParameter("modAt",packageMaster.getModAt());
			query.setParameter("modBy",packageMaster.getModBy());
			query.setParameter("packageId",packageMaster.getPackageId());
			query.executeUpdate();
		response = 1;
		} catch (Exception e) {
			response = 0;
		}
		return response;
	}
	
	@Override
	public int savePackageLogs(PackageMasterLogs packageMasterLogs) {
		Integer response = 0;
			try {
				response = (Integer) sessionFactory.getCurrentSession().save(packageMasterLogs);	
				
		} catch (Exception e) {
			response = 0;
		}
		return response;
	}
	
//added by kishor for save or update test against package from package test relation module
public int saveTestAgainstPackage(JSONArray testDetailsArr) {
			int r=0;
			try {
				
			for (int i = 0; i < testDetailsArr.size(); i++) {

				JSONObject analysisObject = (JSONObject) testDetailsArr.get(i);
				JSONArray listTest = (JSONArray) analysisObject.get("listTest");

			//	System.err.println("testDetailsssss==" + listTest.size() + "+==**==" + listTest);
				for (int j = 0; j < listTest.size(); j++) {
					JSONObject listTestObj = (JSONObject) listTest.get(j);

					Integer ehrTestPackageId = Integer.parseInt( (String) listTestObj.get("ehrTestPackageId"));
					Integer packageId = Integer.parseInt( (String) listTestObj.get("packageId"));
					Integer testId = Integer.parseInt( (String) listTestObj.get("testId"));
					Integer sequence = Integer.parseInt( (String) listTestObj.get("sequence"));
					String testDescription = (String) listTestObj.get("testDescription");
					

					if(ehrTestPackageId > 0) {
						SQLQuery checkupQuery = sessionFactory
								.getCurrentSession()
								.createSQLQuery(
										"UPDATE ehr_test_package SET package_id="+packageId+", test_id="+testId+", sequence ="+sequence+" WHERE ehr_test_package_id="+ehrTestPackageId+" ");
						//visitId = (Integer) checkupQuery.uniqueResult();
						checkupQuery.executeUpdate();
						r=2;
						}else {
							SQLQuery checkupQuery = sessionFactory
									.getCurrentSession()
									.createSQLQuery(
											"INSERT INTO ehr_test_package (package_id, test_id, sequence) VALUES ("+packageId+", "+testId+", "+sequence+") ");
							//visitId = (Integer) checkupQuery.uniqueResult();
							checkupQuery.executeUpdate();
							r=1;
						}

					/*System.err.println("ehrTestPackageId****=" + ehrTestPackageId);
					System.err.println("packageId****=" + packageId);
					System.err.println("testId****=" + testId);
					System.err.println("sequence****=" + sequence);
					System.err.println("testDescription****=" + testDescription);*/
				}
				
			}
	} catch (Exception e) {
			
			e.printStackTrace();
			System.err.println("ehatException:- Class Name :"
					+ e.getStackTrace()[0].getClassName() + " Method Name : "
					+ e.getStackTrace()[0].getMethodName() + " Line No :"
					+ e.getStackTrace()[0].getLineNumber());
			r= 0;
		}
			return r;
		}

}
