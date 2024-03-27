package com.hms.indus.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.CategoryMaster;
import com.hms.indus.bo.SubCategoryMaster;
import com.hms.indus.dao.SubCategoryDao;


@Repository
public class SubCategoryDaoImpl implements SubCategoryDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public String saveSubCategory(SubCategoryMaster subCategoryMaster) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(subCategoryMaster);
		} catch (Exception e) {

		}
		return "Sub-Category saved successfully";
	}

	@Override
	public String deleteSubCategory(SubCategoryMaster subCategoryMaster) {
		SubCategoryMaster subCategoryMaster1 = (SubCategoryMaster) sessionFactory
				.getCurrentSession().get(SubCategoryMaster.class,
						subCategoryMaster.getSubCategoryId());
		subCategoryMaster1.setIsActive(false);
		subCategoryMaster1.setModifyBy(subCategoryMaster.getModifyBy());
		subCategoryMaster1.setModifyOn(subCategoryMaster.getModifyOn());
		sessionFactory.getCurrentSession().update(subCategoryMaster1);
		return "Sub-Category Deleted Successfully";
	}

	@Override
	public List<SubCategoryMaster> listOfSubCategoryMaster() {
		String hql = "from SubCategoryMaster WHERE isActive = :isActive";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("isActive", true);
		List<SubCategoryMaster> subCategoryMasters  = (List<SubCategoryMaster>) query.list();
		List<SubCategoryMaster> subCategoryMasters1  = new ArrayList<SubCategoryMaster>();
		for(SubCategoryMaster subCategoryMaster : subCategoryMasters) {
			SubCategoryMaster subCategoryMaster1 = new SubCategoryMaster();
			subCategoryMaster1.setSubCategoryId(subCategoryMaster.getSubCategoryId());
			subCategoryMaster1.setSubCategory(subCategoryMaster.getSubCategory());
			
			CategoryMaster categoryMaster = new CategoryMaster();
			categoryMaster.setCategoryId(subCategoryMaster.getCategoryMaster().getCategoryId());
			categoryMaster.setCategory(subCategoryMaster.getCategoryMaster().getCategory());
			subCategoryMaster1.setCategoryMaster(categoryMaster);
			
			subCategoryMasters1.add(subCategoryMaster1);
		}
		return subCategoryMasters1;
	}

	@Override
	public SubCategoryMaster getSubCategoryBySubCategoryId(Integer subCategoryId) {
		String hql = "from SubCategoryMaster WHERE subCategoryId = :subCategoryId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("subCategoryId", subCategoryId);
		SubCategoryMaster subCategoryMaster  = (SubCategoryMaster) query.uniqueResult();
		SubCategoryMaster subCategoryMaster1 = new SubCategoryMaster();
		subCategoryMaster1.setSubCategoryId(subCategoryMaster.getSubCategoryId());
		subCategoryMaster1.setSubCategory(subCategoryMaster.getSubCategory());
		
		CategoryMaster categoryMaster = new CategoryMaster();
		categoryMaster.setCategoryId(subCategoryMaster.getCategoryMaster().getCategoryId());
		categoryMaster.setCategory(subCategoryMaster.getCategoryMaster().getCategory());
		subCategoryMaster1.setCategoryMaster(categoryMaster);
		return subCategoryMaster1;
	}

	@Override
	public Long maxCountCategory() {
		Long maxCountCategory = 0l;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SubCategoryMaster.class);
		criteria.setProjection(
				Projections.projectionList().
				add(Projections.groupProperty("categoryMaster.categoryId")).
				add(Projections.rowCount(), "count"));
	    criteria.addOrder(Order.desc("count"));
	    criteria.setMaxResults(1);
	    List subCategoryMasters = criteria.list();
	    if (subCategoryMasters!=null) {
	    	Object[] row = (Object[]) subCategoryMasters.get(0);
	    	maxCountCategory = (Long)row[1];
	    }
	    return maxCountCategory;
	}

}
