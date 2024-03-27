package com.hms.indus.dao.daoimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.TestMaster;
import com.hms.indus.bo.TestMasterLog;
import com.hms.indus.dao.TestDao;

@Repository
public class TestDaoImpl implements TestDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<TestMaster> listOfTestMaster() {
		List<TestMaster> testMasterList = new ArrayList<TestMaster>();
		SQLQuery query = null;
		try {
			query = sessionFactory.getCurrentSession().createSQLQuery(
					"SELECT test_id,test_desc from ehr_test_master");
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				TestMaster testMaster = new TestMaster();
				if (row[0] != null) {
					testMaster.setTestId(Integer.parseInt(row[0].toString()));
				}
				if (row[1] != null) {
					testMaster.setTestDescription(row[1].toString());
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
	public List<TestMaster> getPendingTestByClientId(Integer clientId) {
		List<TestMaster> testMasterList = new ArrayList<TestMaster>();
		SQLQuery query = null;
		try {
			/*query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"SELECT test_id,test_desc from ehr_test_master where test_id in(SELECT test_code FROM ehr_client_checkup_details where ap_app_no=(select ap_app_no from ehr_appointment_master where client_id="
									+ clientId
									+ ") and ap_year=(select ap_year from ehr_appointment_master where client_id="
									+ clientId + ") and status='pending' )");*/
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"SELECT test_id,test_desc FROM ehr_client_checkup_details inner join ehr_appointment_master"+
							" on ehr_appointment_master.ap_app_no = ehr_client_checkup_details.ap_app_no and ehr_appointment_master.ap_year = ehr_client_checkup_details.ap_year inner join ehr_test_master on ehr_test_master.test_id = ehr_client_checkup_details.test_code"
							+" where status='pending' and client_id="+clientId);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				TestMaster testMaster = new TestMaster();
				if (row[0] != null) {
					testMaster.setTestId(Integer.parseInt(row[0].toString()));
				}
				if (row[1] != null) {
					testMaster.setTestDescription(row[1].toString());
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
	public Integer saveTest(TestMaster testMaster) {
		Integer response = 0;		
		int count = 0;
		try {
			SQLQuery reportSql = sessionFactory
					.getCurrentSession()
					.createSQLQuery("SELECT count(test_id) as test_id FROM ehr_test_master where test_id="+testMaster.getTestId()+" ");

			reportSql.addScalar("test_id", IntegerType.INSTANCE);
			count  = (Integer) reportSql.uniqueResult();
			System.err.println("test_id count====="+count);	
		
		if(count > 0) {
			response=-1;
		}else {
			response = (Integer) sessionFactory.getCurrentSession().save(testMaster);

		}
		} catch (Exception e) {
			response = 0;
		}
		return response;
	}

	@Override
	public Integer updateTest(TestMaster testMaster) {
		Integer response = 0;
		try {
			String hqlUpdate = "update TestMaster set testDescription=:testDescription, "
					+ "modAt=:modAt, modBy=:modBy "
					+ "where testId=:testId";
			Query query = sessionFactory.getCurrentSession().createQuery(hqlUpdate);
			query.setParameter("testDescription",testMaster.getTestDescription());
			query.setParameter("modAt",testMaster.getModAt());
			query.setParameter("modBy",testMaster.getModBy());
			query.setParameter("testId",testMaster.getTestId());
			query.executeUpdate();
		response = 1;
		} catch (Exception e) {
			response = 0;
		}
		return response;
	}

	@Override
	public Integer deleteTest(TestMaster testMaster) {
		Integer response = 0;
		try {
			String hqlUpdate = "update TestMaster set isActive=:isActive, "
					+ "modAt=:modAt, modBy=:modBy "
					+ "where testId=:testId";
			Query query = sessionFactory.getCurrentSession().createQuery(hqlUpdate);
			query.setParameter("isActive",testMaster.getIsActive());
			query.setParameter("modAt",testMaster.getModAt());
			query.setParameter("modBy",testMaster.getModBy());
			query.setParameter("testId",testMaster.getTestId());
			query.executeUpdate();
		response = 1;
		} catch (Exception e) {
			response = 0;
		}
		return response;
	}

	@Override
	public int saveTestLogs(TestMasterLog testMasterLog) {
		Integer response = 0;
		try {
			response = (Integer) sessionFactory.getCurrentSession().save(testMasterLog);

		} catch (Exception e) {
			response = 0;
		}
		return response;
	}

}
