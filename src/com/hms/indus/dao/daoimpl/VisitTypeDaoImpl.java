package com.hms.indus.dao.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.VisitTypeMaster;
import com.hms.indus.dao.VisitTypeDao;

@Repository
public class VisitTypeDaoImpl implements VisitTypeDao{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public String saveVisitTypeMaster(VisitTypeMaster visitTypeMaster) {
		try {
			sessionFactory.getCurrentSession().save(visitTypeMaster);
		} catch (Exception e) {
			
		}
		return "Visit Type Master saved successfully";
	}

	@Override
	public String deleteVisitTypeMaster(VisitTypeMaster visitTypeMaster) {
		try {
			VisitTypeMaster visitTypeMaster2 = (VisitTypeMaster) sessionFactory.getCurrentSession().get(VisitTypeMaster.class, visitTypeMaster.getVisitTypeId());
			visitTypeMaster2.setVisitTypeDeleteFlag(0);
			visitTypeMaster2.setModifyBy(visitTypeMaster.getModifyBy());
			visitTypeMaster2.setModifyOn(visitTypeMaster.getModifyOn());
			sessionFactory.getCurrentSession().update(visitTypeMaster2);
		} catch (Exception e) {
			
		}
		return "Visit Type Master deleted";
	}

	@Override
	public String updateVisitTypeMaster(VisitTypeMaster visitTypeMaster) {
		try {
			VisitTypeMaster visitTypeMaster2 = (VisitTypeMaster) sessionFactory.getCurrentSession().get(VisitTypeMaster.class, visitTypeMaster.getVisitTypeId());
			visitTypeMaster2.setVisitType(visitTypeMaster.getVisitType());
			visitTypeMaster2.setModifyBy(visitTypeMaster.getModifyBy());
			visitTypeMaster2.setModifyOn(visitTypeMaster.getModifyOn());
			sessionFactory.getCurrentSession().update(visitTypeMaster);
		} catch (Exception e) {
			
		}
		return "visit Type Master updated";
	}

	@Override
	public List<VisitTypeMaster> listOfVisitTypeMaster() {
		List<VisitTypeMaster> visitTypeMasterList = null;
		try {
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(VisitTypeMaster.class);
			criteria.add(Restrictions.eq("visitTypeDeleteFlag", 1));
			visitTypeMasterList=criteria.list();
		} catch (Exception e) {

		}
		return visitTypeMasterList;
	}

	@Override
	public VisitTypeMaster getVisitTypeMasterByVisitTypeId(
			Integer visitTypeMasterId) {
		VisitTypeMaster visitTypeMaster=new VisitTypeMaster();
		try {
			visitTypeMaster=(VisitTypeMaster) sessionFactory.getCurrentSession().get(VisitTypeMaster.class, visitTypeMasterId);
		} catch (Exception e) {
			
		}
		return visitTypeMaster;
	}

}
