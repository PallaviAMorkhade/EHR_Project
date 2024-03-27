package com.hms.indus.dao;

import java.util.List;

import com.hms.indus.bo.CategoryMaster;
import com.hms.indus.bo.SubCategoryMaster;

public interface CategoryDao {
	
	String saveCategory(CategoryMaster categoryMaster);

	String deleteCategory(CategoryMaster categoryMaster);
	
	List<CategoryMaster> listOfCategoryMaster();

	CategoryMaster getCategoryByCategoryId(Integer categoryId);

	List<CategoryMaster> listOfCategorySubCategory();

	List<SubCategoryMaster> subCategoriesByCategoryId(Integer categoryId);

}
