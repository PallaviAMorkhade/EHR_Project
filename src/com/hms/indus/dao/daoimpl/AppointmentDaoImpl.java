package com.hms.indus.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.AppointmentMaster;
import com.hms.indus.dao.AppointmentDao;

@Repository
public class AppointmentDaoImpl implements AppointmentDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public Integer saveAppointmentMaster(AppointmentMaster appointmentMaster) {
		Integer appId = null;
		int count=0;
		try {
			/*if(appointmentMaster.getApMemberId()==null || appointmentMaster.getApAppNo()==null || appointmentMaster.getApYear()==null || appointmentMaster.getApMemberId().equalsIgnoreCase("") || appointmentMaster.getApAppNo().equalsIgnoreCase("") || appointmentMaster.getApYear().equalsIgnoreCase("")) {
				appId= -1;
			}else {*/
				SQLQuery reportSql = sessionFactory
						.getCurrentSession()
						.createSQLQuery("select count(appointment_id) as appointment_id from ehr_appointment_master where ap_member_id='"+appointmentMaster.getApMemberId()+"' and ap_app_no="+appointmentMaster.getApAppNo()+" and ap_year='"+appointmentMaster.getApYear()+"' ");

				reportSql.addScalar("appointment_id", IntegerType.INSTANCE);
				count  = (Integer) reportSql.uniqueResult();
				System.err.println("appointment count====="+count);
				if(count > 0) {
					System.err.println("IFFFFF    appointment appID====="+appId);
				}else {
					//System.err.println("list=="+appointmentMaster.getCheckupId());
					appId = (Integer) sessionFactory.getCurrentSession().save(
							appointmentMaster);
					System.err.println("Elseeeeee  appointment appID====="+appId);
				}
			//}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return appId;
	}

	@Override
	public List<AppointmentMaster> getAppointmentList() {
		List<AppointmentMaster> listAppointmentMasters = new ArrayList<AppointmentMaster>();
		SQLQuery query = null;
		try {
			query = sessionFactory.getCurrentSession().createSQLQuery(
					"SELECT ap_app_no,ap_year from ehr_appointment_master");
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				AppointmentMaster appointmentMaster = new AppointmentMaster();
				if (row[0] != null) {
					appointmentMaster.setApAppNo(row[0].toString());
				}
				if (row[1] != null) {
					appointmentMaster.setApYear(row[1].toString());
				}
				listAppointmentMasters.add(appointmentMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listAppointmentMasters;
	}

}
