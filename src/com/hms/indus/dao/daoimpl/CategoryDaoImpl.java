package com.hms.indus.dao.daoimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.CategoryMaster;
import com.hms.indus.bo.SubCategoryMaster;
import com.hms.indus.dao.CategoryDao;

@Repository
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public String saveCategory(CategoryMaster categoryMaster) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(categoryMaster);
		} catch (Exception e) {

		}
		return "Category saved successfully";
	}

	@Override
	public String deleteCategory(CategoryMaster categoryMaster) {
		CategoryMaster categoryMaster1 = (CategoryMaster) sessionFactory.getCurrentSession().get(CategoryMaster.class,
				categoryMaster.getCategoryId());
		categoryMaster1.setIsActive(false);
		categoryMaster1.setModifyBy(categoryMaster.getModifyBy());
		categoryMaster1.setModifyOn(categoryMaster.getModifyOn());
		sessionFactory.getCurrentSession().update(categoryMaster1);
		return "Category Deleted Successfully";
	}

	@Override
	public List<CategoryMaster> listOfCategoryMaster() {
		String hql = "from CategoryMaster WHERE isActive = :isActive";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("isActive", true);
		List<CategoryMaster> categoryMasters = (List<CategoryMaster>) query.list();
		List<CategoryMaster> categoryMasters1 = new ArrayList<CategoryMaster>();
		for (CategoryMaster categoryMaster : categoryMasters) {
			CategoryMaster categoryMaster1 = new CategoryMaster();
			categoryMaster1.setCategoryId(categoryMaster.getCategoryId());
			categoryMaster1.setCategory(categoryMaster.getCategory());
			categoryMasters1.add(categoryMaster1);
		}
		return categoryMasters1;
	}

	@Override
	public CategoryMaster getCategoryByCategoryId(Integer categoryId) {
		String hql = "from CategoryMaster WHERE categoryId = :categoryId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("categoryId", categoryId);
		CategoryMaster categoryMaster = (CategoryMaster) query.uniqueResult();
		CategoryMaster categoryMaster1 = new CategoryMaster();
		categoryMaster1.setCategoryId(categoryMaster.getCategoryId());
		categoryMaster1.setCategory(categoryMaster.getCategory());
		return categoryMaster1;
	}

	@Override
	public List<CategoryMaster> listOfCategorySubCategory() {
		String hql = "from CategoryMaster WHERE isActive = :isActive";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("isActive", true);
		List<CategoryMaster> categoryMasters = (List<CategoryMaster>) query.list();
		List<CategoryMaster> categoryMasters1 = new ArrayList<CategoryMaster>();
		for (CategoryMaster categoryMaster : categoryMasters) {
			CategoryMaster categoryMaster1 = new CategoryMaster();
			categoryMaster1.setCategoryId(categoryMaster.getCategoryId());
			categoryMaster1.setCategory(categoryMaster.getCategory());
			Set<SubCategoryMaster> subCategoryMasters = new HashSet<SubCategoryMaster>();
			for (SubCategoryMaster subCategoryMaster : categoryMaster.getSubCategories()) {
				SubCategoryMaster subCategoryMaster1 = new SubCategoryMaster();
				subCategoryMaster1.setSubCategoryId(subCategoryMaster.getSubCategoryId());
				subCategoryMaster1.setSubCategory(subCategoryMaster.getSubCategory());
				subCategoryMasters.add(subCategoryMaster1);
			}
			categoryMaster1.setSubCategories(subCategoryMasters);
			categoryMasters1.add(categoryMaster1);
		}
		return categoryMasters1;
	}

	@Override
	public List<SubCategoryMaster> subCategoriesByCategoryId(Integer categoryId) {
		String hql = "from SubCategoryMaster WHERE categoryMaster.categoryId = :categoryId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("categoryId", categoryId);
		List<SubCategoryMaster> subCategoryMasters = (List<SubCategoryMaster>) query.list();
		List<SubCategoryMaster> subCategoryMasters1 = new ArrayList<SubCategoryMaster>();
		for (SubCategoryMaster subCategoryMaster : subCategoryMasters) {
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

}
