package com.hms.indus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hms.indus.bo.SubCategoryMaster;
import com.hms.indus.service.SubCategoryService;

@Controller
@RequestMapping(value = "/subCategory")
public class SubCategoryController {

	@Autowired SubCategoryService subCategoryService;
	 
	@RequestMapping(value = "/saveSubCategory", method = RequestMethod.POST)
	public @ResponseBody String saveSubCategory(@RequestBody SubCategoryMaster subCategoryMaster, 
			HttpSession session) {
		java.util.Date date = new java.util.Date();
		String addedBy=(String)session.getAttribute("userName");
		if(subCategoryMaster.getSubCategoryId()==null) {
			subCategoryMaster.setAddedBy(addedBy);
			subCategoryMaster.setAddedOn(date);
		}else {
			subCategoryMaster.setModifyBy(addedBy);
			subCategoryMaster.setModifyOn(date);
		}
		subCategoryMaster.setIsActive(true);
		subCategoryService.saveSubCategory(subCategoryMaster);
		return "SubCategory Saved Successfully";
	}
	
	@RequestMapping(value = "/deleteSubCategory", method = RequestMethod.GET)
	public @ResponseBody String deleteSubCategory(@RequestParam("subCategoryMasterId") Integer subCategoryId, 
			HttpSession session) {
		java.util.Date date = new java.util.Date();
		String addedBy=(String)session.getAttribute("userName");
		SubCategoryMaster subCategoryMaster = new SubCategoryMaster();
		subCategoryMaster.setSubCategoryId(subCategoryId);
		subCategoryMaster.setModifyBy(addedBy);
		subCategoryMaster.setModifyOn(date);
		subCategoryMaster.setIsActive(false);
		subCategoryService.deleteSubCategory(subCategoryMaster);
		return "SubCategory Deleted Successfully";
	}
	
	@RequestMapping(value = "/listOfSubCategoryMaster", method = RequestMethod.GET)
	public @ResponseBody List<SubCategoryMaster> listOfSubCategoryMaster() {
		return subCategoryService.listOfSubCategoryMaster();
	}
	
	@RequestMapping(value = "/getSubCategoryBySubCategoryId", method = RequestMethod.GET)
	public @ResponseBody SubCategoryMaster getSubCategoryBySubCategoryId(HttpServletRequest request,@RequestParam("subCategoryMasterId") Integer subCategoryId) {
		SubCategoryMaster subCategoryMaster = subCategoryService.getSubCategoryBySubCategoryId(subCategoryId);
		return subCategoryMaster;
	}
	
	@RequestMapping(value = "/maxCountCategory", method = RequestMethod.GET)
	public @ResponseBody Long maxCountCategory() {
		return subCategoryService.maxCountCategory();
	}

}
