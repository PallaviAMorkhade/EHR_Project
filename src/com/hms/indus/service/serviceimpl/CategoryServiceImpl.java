package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.CategoryMaster;
import com.hms.indus.bo.SubCategoryMaster;
import com.hms.indus.dao.CategoryDao;
import com.hms.indus.service.CategoryService;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryDao categoryDao;

	@Override
	public String saveCategory(CategoryMaster categoryMaster) {
		return categoryDao.saveCategory(categoryMaster);
	}

	@Override
	public String deleteCategory(CategoryMaster categoryMaster) {
		return categoryDao.deleteCategory(categoryMaster);
	}

	@Override
	public List<CategoryMaster> listOfCategoryMaster() {
		return categoryDao.listOfCategoryMaster();
	}

	@Override
	public CategoryMaster getCategoryByCategoryId(Integer categoryId) {
		return categoryDao.getCategoryByCategoryId(categoryId);
	}

	@Override
	public List<CategoryMaster> listOfCategorySubCategory() {
		return categoryDao.listOfCategorySubCategory();
	}

	@Override
	public List<SubCategoryMaster> subCategoriesByCategoryId(Integer categoryId) {
		return categoryDao.subCategoriesByCategoryId(categoryId);
	}

}
