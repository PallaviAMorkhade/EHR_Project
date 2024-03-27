package com.hms.indus.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.UnitMaster;
import com.hms.indus.dao.UnitDao;

@Repository
public class UnitDaoImpl implements UnitDao{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public String saveUnitMaster(UnitMaster unitMaster) {
		try {
			sessionFactory.getCurrentSession().save(unitMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Unit Master saved successfully";
	}

	@Override
	public String deleteUnitMaster(Integer unitId) {
		try {
			UnitMaster unitMaster = (UnitMaster) sessionFactory.getCurrentSession().get(UnitMaster.class, unitId);
			unitMaster.setUnitMasterDeleteFlag(0);
			sessionFactory.getCurrentSession().update(unitMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Unit Master deleted";
	}

	@Override
	public String updateUnitMaster(UnitMaster unitMaster) {
		try {
		UnitMaster unitMaster2 = (UnitMaster) sessionFactory.getCurrentSession().get(UnitMaster.class, unitMaster.getUnitId());
		unitMaster2.setModifyBy(unitMaster.getModifyBy());
		unitMaster2.setModifyOn(unitMaster.getModifyOn());
		unitMaster2.setUnitName(unitMaster.getUnitName());
		sessionFactory.getCurrentSession().update(unitMaster2);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Unit Master updated";
	}

	@Override
	public List<UnitMaster> listOfUnitMaster() {
		List<UnitMaster> unitMasterList=new ArrayList<UnitMaster>();
		try {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(UnitMaster.class);
		criteria.add(Restrictions.eq("unitMasterDeleteFlag", 1));
		unitMasterList=criteria.list();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return unitMasterList;
	}

}
