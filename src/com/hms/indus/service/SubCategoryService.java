package com.hms.indus.service;

import java.util.List;

import com.hms.indus.bo.SubCategoryMaster;

public interface SubCategoryService {
	
	String saveSubCategory(SubCategoryMaster subCategoryMaster);

	String deleteSubCategory(SubCategoryMaster subCategoryMaster);
	
	List<SubCategoryMaster> listOfSubCategoryMaster();

	SubCategoryMaster getSubCategoryBySubCategoryId(Integer subCategoryId);

	Long maxCountCategory();

}
