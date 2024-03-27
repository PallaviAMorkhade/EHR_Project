package com.hms.indus.dao.daoimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.CentreMaster;
import com.hms.indus.bo.CentreMasterLogs;
import com.hms.indus.bo.TestMaster;
import com.hms.indus.dao.CenterDao;

@Repository
public class CenterDaoImpl implements CenterDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<CentreMaster> getListOfCentres() {
		// Criteria
		// criteria=sessionFactory.getCurrentSession().createCriteria(CentreMaster.class);
		// criteria.add(Restrictions.eq("hraTypeMasterDeleteFlag", 1));
		List<CentreMaster> centreMasterList = new ArrayList<CentreMaster>();
		SQLQuery query = null;
		try {
			query = sessionFactory.getCurrentSession().createSQLQuery(
					"SELECT center_id,center_name from ehr_center_master");
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				CentreMaster centreMaster = new CentreMaster();
				if (row[0] != null) {
					centreMaster
							.setCentreId(Integer.parseInt(row[0].toString()));
				}
				if (row[1] != null) {
					centreMaster.setCentreName(row[1].toString());
				}
				centreMasterList.add(centreMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Collections.sort(centreMasterList, new CentreMaster());
		return centreMasterList;
	}

	@Override
	public CentreMaster getCenterByCenterId(int centerId) {
		CentreMaster centreMaster = new CentreMaster();
		SQLQuery query = null;
		try {
			query = sessionFactory.getCurrentSession().createSQLQuery("SELECT center_id,center_name from ehr_center_master where center_id="+centerId);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				if (row[0] != null) {
					centreMaster
							.setCentreId(Integer.parseInt(row[0].toString()));
				}
				if (row[1] != null) {
					centreMaster.setCentreName(row[1].toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return centreMaster;
	}

	@Override
	public Integer saveCentre(CentreMaster centreMaster) {
		Integer response = 0;		
		int count = 0;
		try {
			SQLQuery reportSql = sessionFactory
					.getCurrentSession()
					.createSQLQuery("SELECT count(center_id) as center_id FROM ehr_center_master where center_id="+centreMaster.getCentreId()+" ");

			reportSql.addScalar("center_id", IntegerType.INSTANCE);
			count  = (Integer) reportSql.uniqueResult();
			System.err.println("center_id count====="+count);	
		
		if(count > 0) {
			response=-1;
		}else {
			response = (Integer) sessionFactory.getCurrentSession().save(centreMaster);

		}
		
		} catch (Exception e) {
			response = 0;
		}
		return response;
	}

	@Override
	public Integer updateCentre(CentreMaster centreMaster) {
		Integer response = 0;
		try {
			String hqlUpdate = "update CentreMaster set centreName=:centreName, "
					+ "modAt=:modAt, modBy=:modBy "
					+ "where centreId=:centreId";
			Query query = sessionFactory.getCurrentSession().createQuery(hqlUpdate);
			query.setParameter("centreName",centreMaster.getCentreName());
			query.setParameter("modAt",centreMaster.getModAt());
			query.setParameter("modBy",centreMaster.getModBy());
			query.setParameter("centreId",centreMaster.getCentreId());
			query.executeUpdate();
		response = 1;
		} catch (Exception e) {
			response = 0;
		}
		return response;
	}

	@Override
	public Integer deleteCentre(CentreMaster centreMaster) {
		Integer response = 0;
		try {
			String hqlUpdate = "update CentreMaster set isActive=:isActive, "
					+ "modAt=:modAt, modBy=:modBy "
					+ "where centreId=:centreId";
			Query query = sessionFactory.getCurrentSession().createQuery(hqlUpdate);
			query.setParameter("isActive",centreMaster.getIsActive());
			query.setParameter("modAt",centreMaster.getModAt());
			query.setParameter("modBy",centreMaster.getModBy());
			query.setParameter("centreId",centreMaster.getCentreId());
			query.executeUpdate();
		response = 1;
		} catch (Exception e) {
			response = 0;
		}
		return response;
	}
	
	@Override
	public int saveCentreLogs(CentreMasterLogs centreMasterLogs) {
		Integer response = 0;
		try {
			response = (Integer) sessionFactory.getCurrentSession().save(centreMasterLogs);

		} catch (Exception e) {
			response = 0;
		}
		return response;
	}

}
