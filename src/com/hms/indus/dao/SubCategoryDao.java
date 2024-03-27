package com.hms.indus.dao;

import java.util.List;

import com.hms.indus.bo.SubCategoryMaster;

public interface SubCategoryDao {
	
	String saveSubCategory(SubCategoryMaster subCategoryMaster);

	String deleteSubCategory(SubCategoryMaster subCategoryMaster);
	
	List<SubCategoryMaster> listOfSubCategoryMaster();

	SubCategoryMaster getSubCategoryBySubCategoryId(Integer subCategoryId);
	
	Long maxCountCategory();

}
