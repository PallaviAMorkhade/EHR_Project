package com.hms.indus.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.FrequencyMaster;
import com.hms.indus.dao.FrequencyDao;

@Repository
public class FrequencyDaoImpl implements FrequencyDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public String saveFrequency(FrequencyMaster frequencyMaster) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(frequencyMaster);
		} catch (Exception e) {

		}
		return "Frequency saved successfully";
	}

	@Override
	public String deleteFrequency(FrequencyMaster frequencyMaster) {
		FrequencyMaster frequencyMaster1 = (FrequencyMaster) sessionFactory.getCurrentSession().get(FrequencyMaster.class,
				frequencyMaster.getFrequencyId());
		frequencyMaster1.setIsActive(false);
		frequencyMaster1.setModifyBy(frequencyMaster.getModifyBy());
		frequencyMaster1.setModifyOn(frequencyMaster.getModifyOn());
		sessionFactory.getCurrentSession().update(frequencyMaster1);
		return "Frequency Deleted Successfully";
	}

	@Override
	public List<FrequencyMaster> listOfFrequencyMaster() {
		String hql = "from FrequencyMaster WHERE isActive = :isActive";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("isActive", true);
		List<FrequencyMaster> frequencyMasters = (List<FrequencyMaster>) query.list();
		List<FrequencyMaster> frequencyMasters1 = new ArrayList<FrequencyMaster>();
		for (FrequencyMaster frequencyMaster : frequencyMasters) {
			FrequencyMaster frequencyMaster1 = new FrequencyMaster();
			frequencyMaster1.setFrequencyId(frequencyMaster.getFrequencyId());
			frequencyMaster1.setFrequency(frequencyMaster.getFrequency());
			frequencyMasters1.add(frequencyMaster1);
		}
		return frequencyMasters1;
	}

	@Override
	public FrequencyMaster getFrequencyByFrequencyId(Integer frequencyId) {
		String hql = "from FrequencyMaster WHERE frequencyId = :frequencyId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("frequencyId", frequencyId);
		FrequencyMaster frequencyMaster = (FrequencyMaster) query.uniqueResult();
		FrequencyMaster frequencyMaster1 = new FrequencyMaster();
		frequencyMaster1.setFrequencyId(frequencyMaster.getFrequencyId());
		frequencyMaster1.setFrequency(frequencyMaster.getFrequency());
		return frequencyMaster1;
	}


}
