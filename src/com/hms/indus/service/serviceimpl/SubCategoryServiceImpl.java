package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.SubCategoryMaster;
import com.hms.indus.dao.SubCategoryDao;
import com.hms.indus.service.SubCategoryService;

@Service
@Transactional
public class SubCategoryServiceImpl implements SubCategoryService{
	
	@Autowired
	SubCategoryDao subCategoryDao;

	@Override
	public String saveSubCategory(SubCategoryMaster subCategoryMaster) {
		return subCategoryDao.saveSubCategory(subCategoryMaster);
	}

	@Override
	public String deleteSubCategory(SubCategoryMaster subCategoryMaster) {
		return subCategoryDao.deleteSubCategory(subCategoryMaster);
	}

	@Override
	public List<SubCategoryMaster> listOfSubCategoryMaster() {
		return subCategoryDao.listOfSubCategoryMaster();
	}

	@Override
	public SubCategoryMaster getSubCategoryBySubCategoryId(Integer subCategoryId) {
		return subCategoryDao.getSubCategoryBySubCategoryId(subCategoryId);
	}

	@Override
	public Long maxCountCategory() {
		return subCategoryDao.maxCountCategory();
	}


}
