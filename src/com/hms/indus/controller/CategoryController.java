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

import com.hms.indus.bo.CategoryMaster;
import com.hms.indus.bo.SubCategoryMaster;
import com.hms.indus.service.CategoryService;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

	@Autowired 
	CategoryService categoryService;
	 
	@RequestMapping(value = "/saveCategory", method = RequestMethod.POST)
	public @ResponseBody String saveCategory(@RequestBody CategoryMaster categoryMaster, 
			HttpSession session) {
		java.util.Date date = new java.util.Date();
		String addedBy=(String)session.getAttribute("userName");
		if(categoryMaster.getCategoryId()==null) {
			categoryMaster.setAddedBy(addedBy);
			categoryMaster.setAddedOn(date);
		}else {
			categoryMaster.setModifyBy(addedBy);
			categoryMaster.setModifyOn(date);
		}
		categoryMaster.setIsActive(true);
		categoryService.saveCategory(categoryMaster);
		return "Category Saved Successfully";
	}
	
	@RequestMapping(value = "/deleteCategory", method = RequestMethod.GET)
	public @ResponseBody String deleteCategory(@RequestParam("categoryMasterId") Integer categoryId, 
			HttpSession session) {
		java.util.Date date = new java.util.Date();
		String addedBy=(String)session.getAttribute("userName");
		CategoryMaster categoryMaster = new CategoryMaster();
		categoryMaster.setCategoryId(categoryId);
		categoryMaster.setModifyBy(addedBy);
		categoryMaster.setModifyOn(date);
		categoryMaster.setIsActive(false);
		categoryService.deleteCategory(categoryMaster);
		return "Category Deleted Successfully";
	}
	
	@RequestMapping(value = "/listOfCategoryMaster", method = RequestMethod.GET)
	public @ResponseBody List<CategoryMaster> listOfCategoryMaster() {
		return categoryService.listOfCategoryMaster();
	}
	
	@RequestMapping(value = "/getCategoryByCategoryId", method = RequestMethod.GET)
	public @ResponseBody CategoryMaster getCategoryByCategoryId(HttpServletRequest request,@RequestParam("categoryMasterId") Integer categoryId) {
		CategoryMaster categoryMaster = categoryService.getCategoryByCategoryId(categoryId);
		return categoryMaster;
	}
	
	@RequestMapping(value = "/subCategoriesByCategoryId", method = RequestMethod.GET)
	public @ResponseBody List<SubCategoryMaster> subCategoriesByCategoryId(@RequestParam("categoryId") Integer categoryId) {
		return categoryService.subCategoriesByCategoryId(categoryId);
	}

}
