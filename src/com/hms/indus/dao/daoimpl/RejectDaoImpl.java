package com.hms.indus.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.RejectMaster;
import com.hms.indus.dao.RejectDao;

@Repository
public class RejectDaoImpl implements RejectDao{

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public String saveRejectMaster(RejectMaster rejectMaster) {
		try {
			sessionFactory.getCurrentSession().save(rejectMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Reject Master saved successfully";
	}

	@Override
	public String deleteRejectMaster(RejectMaster rejectMaster) {
		try {
			RejectMaster rejectMaster2 = (RejectMaster) sessionFactory.getCurrentSession().get(RejectMaster.class, rejectMaster.getRejectId());
			rejectMaster2.setRejectDeleteFlag(0);
			sessionFactory.getCurrentSession().update(rejectMaster2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "HRATypeMaster deleted";
	}

	@Override
	public String updateRejectMaster(RejectMaster rejectMaster) {
		try {
		RejectMaster rejectMaster2 = (RejectMaster) sessionFactory.getCurrentSession().get(RejectMaster.class, rejectMaster.getRejectId());
		rejectMaster2.setRejectReason(rejectMaster.getRejectReason());
		rejectMaster2.setModifyBy(rejectMaster.getModifyBy());
		rejectMaster2.setModifyOn(rejectMaster.getModifyOn());
		sessionFactory.getCurrentSession().update(rejectMaster2);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Reject Master updated";
	}

	@Override
	public List<RejectMaster> listOfRejectMaster() {
		List<RejectMaster> rejectMasterList=new ArrayList<RejectMaster>();
		try {
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(RejectMaster.class);
			criteria.add(Restrictions.eq("rejectDeleteFlag", 1));
			rejectMasterList=criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rejectMasterList;
	}

	@Override
	public RejectMaster getRejectMasterByRejectId(Integer rejectMasterId) {
		RejectMaster rejectMaster=new RejectMaster();
		try {
			 rejectMaster=(RejectMaster) sessionFactory.getCurrentSession().get(RejectMaster.class, rejectMasterId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rejectMaster;
	}

}
