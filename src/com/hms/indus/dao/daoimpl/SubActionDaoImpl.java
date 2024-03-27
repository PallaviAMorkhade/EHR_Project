package com.hms.indus.dao.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.SubActionMaster;
import com.hms.indus.dao.SubActionDao;

@Repository
public class SubActionDaoImpl implements SubActionDao{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public String saveSubActionMaster(SubActionMaster subActionMaster) {
		try {
			sessionFactory.getCurrentSession().save(subActionMaster);
		} catch (Exception e) {
			
		}
		return "Sub Action Master saved successfully";
	}

	@Override
	public String deleteSubActionMaster(SubActionMaster subActionMaster) {
		try {
			SubActionMaster subActionMaster2 = (SubActionMaster) sessionFactory.getCurrentSession().get(SubActionMaster.class, subActionMaster.getSubActionId());
			subActionMaster2.setSubActionDeleteFlag(0);
			subActionMaster2.setModifyBy(subActionMaster.getModifyBy());
			subActionMaster2.setModifyOn(subActionMaster.getModifyOn());
			sessionFactory.getCurrentSession().update(subActionMaster2);
		} catch (Exception e) {
			
		}
		return "Sub Action Master deleted";
	}

	@Override
	public String updateSubActionMaster(SubActionMaster subActionMaster) {
		try {
			SubActionMaster subActionMaster2 = (SubActionMaster) sessionFactory.getCurrentSession().get(SubActionMaster.class, subActionMaster.getSubActionId());
			subActionMaster2.setSubAction(subActionMaster.getSubAction());
			subActionMaster2.setModifyBy(subActionMaster.getModifyBy());
			subActionMaster2.setModifyOn(subActionMaster.getModifyOn());
			sessionFactory.getCurrentSession().update(subActionMaster2);
		} catch (Exception e) {
			
		}
		return "Sub Action Master updated";
	}

	@Override
	public List<SubActionMaster> listOfSubActionMaster() {
		List<SubActionMaster> subActionMasterList = null;
		try {
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(SubActionMaster.class);
			criteria.add(Restrictions.eq("subActionDeleteFlag", 1));
			subActionMasterList=criteria.list();
		} catch (Exception e) {

		}
		return subActionMasterList;
	}

	@Override
	public SubActionMaster getSubActionMasterBySubActionId(
			Integer subActionMasterId) {
		SubActionMaster subActionMaster=new SubActionMaster();
		try {
			subActionMaster=(SubActionMaster) sessionFactory.getCurrentSession().get(SubActionMaster.class, subActionMasterId);
		} catch (Exception e) {
			
		}
		return subActionMaster;
	}

	@Override
	public List<SubActionMaster> listOfSubActionMasterByActionId(
			Integer actionId) {
		List<SubActionMaster> subActionMasterList = null;
		try {
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(SubActionMaster.class);
			criteria.add(Restrictions.eq("actionMaster.actionId", actionId));
			criteria.add(Restrictions.eq("subActionDeleteFlag", 1));
			subActionMasterList=criteria.list();
		} catch (Exception e) {

		}
		return subActionMasterList;
	}

}
