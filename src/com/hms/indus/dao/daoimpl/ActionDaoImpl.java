package com.hms.indus.dao.daoimpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.ActionMaster;
import com.hms.indus.dao.ActionDao;

@Repository
public class ActionDaoImpl implements ActionDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public String saveActionMaster(ActionMaster actionMaster) {
		try {
			sessionFactory.getCurrentSession().save(actionMaster);
		} catch (Exception e) {

		}
		return "Action Master saved successfully";
	}

	@Override
	public String deleteActionMaster(ActionMaster actionMaster) {
		try {
			ActionMaster actionMaster2 = (ActionMaster) sessionFactory
					.getCurrentSession().get(ActionMaster.class,
							actionMaster.getActionId());
			actionMaster2.setActionDeleteFlag(0);
			actionMaster2.setModifyBy(actionMaster.getModifyBy());
			actionMaster2.setModifyOn(actionMaster.getModifyOn());
			sessionFactory.getCurrentSession().update(actionMaster2);
		} catch (Exception e) {

		}
		return "ActionMaster deleted";
	}

	@Override
	public String updateActionMaster(ActionMaster actionMaster) {
		try {
			ActionMaster actionMaster2 = (ActionMaster) sessionFactory
					.getCurrentSession().get(ActionMaster.class,
							actionMaster.getActionId());
			actionMaster2.setAction(actionMaster.getAction());
			actionMaster2.setModifyBy(actionMaster.getModifyBy());
			actionMaster2.setModifyOn(actionMaster.getModifyOn());
			sessionFactory.getCurrentSession().update(actionMaster2);
		} catch (Exception e) {

		}
		return "ActionMaster updated";
	}

	@Override
	public List<ActionMaster> listOfActionMaster() {
		List<ActionMaster> actionMasterList = null;
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(ActionMaster.class);
			criteria.add(Restrictions.eq("actionDeleteFlag", 1));
			actionMasterList = criteria.list();
		} catch (Exception e) {

		}
		return actionMasterList;
	}

	@Override
	public ActionMaster getActionMasterByActionId(Integer actionMasterId) {
		ActionMaster actionMaster = new ActionMaster();
		try {
			actionMaster = (ActionMaster) sessionFactory.getCurrentSession()
					.get(ActionMaster.class, actionMasterId);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return actionMaster;
	}

}
