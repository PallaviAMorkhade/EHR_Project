package com.hms.indus.dao.daoimpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.CheckUpMaster;
import com.hms.indus.bo.GetBeneficiaryApiLog;
import com.hms.indus.bo.PackageMaster;
import com.hms.indus.bo.TestMaster;
import com.hms.indus.bo.VisitTypeMaster;
import com.hms.indus.dao.CheckUpDao;
import com.hms.indus.util.DateConvertUtil;

@Repository
public class CheckUpDaoImpl implements CheckUpDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Integer saveCheckUpMaster(CheckUpMaster checkUpMaster,HttpSession session) {
		Integer checkUpId = null;
		int count=0;
		try {
			System.err.println("Get Client ID ====="+checkUpMaster.getClientMaster().getClientId());
			System.err.println("Get checkUpId if available====="+checkUpMaster.getCheckUpId());
			SQLQuery reportSql = sessionFactory
					.getCurrentSession()
					.createSQLQuery("select count(checkup_id) as checkup_id from checkup_master where ap_member_id='"+checkUpMaster.getApMemberId()+"' and ap_app_no="+checkUpMaster.getApAppNo()+" and ap_year='"+checkUpMaster.getApYear()+"' ");

			reportSql.addScalar("checkup_id", IntegerType.INSTANCE);
			count  = (Integer) reportSql.uniqueResult();
			System.err.println("checkup_master count No====="+count);			
			
			if(count > 0) {
				SQLQuery reportSql1 = sessionFactory
						.getCurrentSession()
						.createSQLQuery("select (checkup_id) as checkup_id from checkup_master where ap_member_id='"+checkUpMaster.getApMemberId()+"' and ap_app_no="+checkUpMaster.getApAppNo()+" and ap_year='"+checkUpMaster.getApYear()+"' order by checkup_id desc limit 1 ");

				reportSql1.addScalar("checkup_id", IntegerType.INSTANCE);
				checkUpId  = (Integer) reportSql1.uniqueResult();
				System.err.println("INNNNNN IF count greater than zero checkUpId====="+checkUpId);				
				
			}else {
				sessionFactory.getCurrentSession().saveOrUpdate(checkUpMaster);
				checkUpId = checkUpMaster.getCheckUpId();
				System.err.println("else count is zero then call save checkUpId====="+checkUpId);
			}					
	
			//for current time and date
			java.util.Date date = new java.util.Date();
			java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = simpleDateFormat.format(date);
			if(session!=null){
				String addedBy = (String) session.getAttribute("userName");
				if (addedBy != null && !addedBy.equals("")) {
					String sql = "INSERT INTO ehr_visit_log(added_by,added_on,check_up_id) "
							+ "values('"
							+ addedBy
							+ "','"
							+ currentTime
							+ "','"
							+ checkUpId
							+ "')";
					SQLQuery queryInsert = sessionFactory.getCurrentSession()
							.createSQLQuery(sql);
					queryInsert.executeUpdate();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkUpId;
	}

	@Override
	public List<CheckUpMaster> getVisitDateList(Integer clientId) {
		List<CheckUpMaster> checkUpMasterList = new ArrayList<CheckUpMaster>();
		try {
			/*Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(CheckUpMaster.class);
			criteria.add(Restrictions.eq("clientMaster.clientId", clientId));
			ProjectionList projList = Projections.projectionList();
			projList.add(Projections.property("checkUpId"));
			projList.add(Projections.property("checkUpDate"));
			projList.add(Projections.property("visitTypeMaster.visitTypeId"));
			projList.add(Projections.property("packageMaster.packageId"));
			projList.add(Projections.property("testMaster.testId"));
			projList.add(Projections.property("healthReport"));
			criteria.setProjection(projList);*/
			
			//String sql = "Select checkup_id,checkup_date,visit_type_id,cm.package_id,test_id,health_report,pm.package_description FROM checkup_master cm left join package_master pm on cm.package_id = pm.package_id where cm.is_deleted='N' and client_id = :clientId";
			String sql = "Select checkup_id,checkup_date,visit_type_id,cm.package_id,test_id,health_report,pm.package_short_desc FROM checkup_master cm left join ehr_package_master pm on cm.package_id = pm.package_id where cm.is_deleted='N' and client_id = :clientId";
			Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
			query.setParameter("clientId", clientId);
			List<Object[]> result = query.list();
			for (Object[] master : result) {
				CheckUpMaster checkUpMaster = new CheckUpMaster();
				if (master[0] != null) {
					checkUpMaster.setCheckUpId(Integer.parseInt(master[0]
							.toString()));
				}
				if (master[1] != null) {
					String checkUpDate=DateConvertUtil.convertDate(master[1].toString());
					checkUpMaster.setCheckUpDate(checkUpDate);
				}
				VisitTypeMaster visitTypeMaster = new VisitTypeMaster();
				if (master[2] != null) {
					visitTypeMaster.setVisitTypeId(Integer.parseInt(master[2]
							.toString()));
				} else {
					visitTypeMaster.setVisitTypeId(0);
				}
				checkUpMaster.setVisitTypeMaster(visitTypeMaster);
				PackageMaster packageMaster=new PackageMaster();
				if(master[3] != null){
					packageMaster.setPackageId(Integer.parseInt(master[3].toString()));
				}
				if (master[4] != null) {
					TestMaster testMaster=new TestMaster();
					testMaster.setTestId(Integer.parseInt(master[4].toString()));
					checkUpMaster.setTestMaster(testMaster);
				}
				if (master[5] != null) {
					checkUpMaster.setHealthReport(Boolean.parseBoolean(master[5].toString()));
				}
				if(master[6] != null){
					packageMaster.setPackageDescription(master[6].toString());
				}
				checkUpMaster.setPackageMaster(packageMaster);
				checkUpMasterList.add(checkUpMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return checkUpMasterList;
	}
	
	@Override
	public CheckUpMaster getVisitById(Integer visitId) {
		String sql = "Select checkup_id,checkup_date,visit_type_id,package_id,test_id,health_report FROM checkup_master where checkup_id = :visitId";
		Query query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.setParameter("visitId", visitId);
		Object master[] = (Object[]) query.uniqueResult();
		CheckUpMaster checkUpMaster = new CheckUpMaster();
		if (master != null) {
			checkUpMaster.setCheckUpId(Integer.parseInt(master[0]
					.toString()));
		}
		if (master[1] != null) {
			String checkUpDate=DateConvertUtil.convertDate(master[1].toString());
			checkUpMaster.setCheckUpDate(checkUpDate);
		}
		VisitTypeMaster visitTypeMaster = new VisitTypeMaster();
		if (master[2] != null) {
			visitTypeMaster.setVisitTypeId(Integer.parseInt(master[2]
					.toString()));
		} else {
			visitTypeMaster.setVisitTypeId(0);
		}
		checkUpMaster.setVisitTypeMaster(visitTypeMaster);
		if(master[3] != null){
			PackageMaster packageMaster=new PackageMaster();
			packageMaster.setPackageId(Integer.parseInt(master[3].toString()));
			checkUpMaster.setPackageMaster(packageMaster);
		}
		if (master[4] != null) {
			TestMaster testMaster=new TestMaster();
			testMaster.setTestId(Integer.parseInt(master[4].toString()));
			checkUpMaster.setTestMaster(testMaster);
		}
		if (master[5] != null) {
			checkUpMaster.setHealthReport(Boolean.parseBoolean(master[5].toString()));
		}
		return checkUpMaster;
	}

}
