package com.hms.indus.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.hms.indus.bo.ContentFile;
import com.hms.indus.bo.ContentMaster;
import com.hms.indus.bo.PublishDetail;
import com.hms.indus.bo.PublishMaster;
import com.hms.indus.bo.SubCategoryMaster;
import com.hms.indus.service.CategoryService;
import com.hms.indus.service.ContentService;
import com.hms.indus.service.SubCategoryService;

@Controller
@RequestMapping(value = "/content")
public class ContentController {

	@Autowired ContentService contentService;
	
	@Autowired CategoryService categoryService;
	
	@Autowired SubCategoryService subCategoryService;
	 
	@RequestMapping(value = "/saveContent", method = RequestMethod.POST)
	public @ResponseBody String saveContent(@RequestBody ContentMaster contentMaster, 
			HttpSession session) {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("MM-dd-yyyy");
		String currentTime = simpleDateFormat.format(date);
		String addedBy=(String)session.getAttribute("userName");
		if(contentMaster.getContentId()==null) {
			contentMaster.setAddedBy(addedBy);
			contentMaster.setAddedOn(date);
		}else {
			contentMaster.setModifyBy(addedBy);
			contentMaster.setModifyOn(date);
		}
		contentMaster.setIsActive(true);
		for(ContentFile contentFile : contentMaster.getContentFiles()) {
			contentFile.setContentMaster(contentMaster);
			contentFile.setFileName(currentTime+"_"+contentFile.getFileName());
		}
		contentService.saveContent(contentMaster);
		return "Content Saved Successfully";
	}
	
	@RequestMapping(value = "/deleteContent", method = RequestMethod.POST)
	public @ResponseBody String deleteContent(@RequestParam("contentMasterId") Integer contentId, 
			HttpSession session) {
		java.util.Date date = new java.util.Date();
		String addedBy=(String)session.getAttribute("userName");
		ContentMaster contentMaster = new ContentMaster();
		contentMaster.setContentId(contentId);
		contentMaster.setModifyBy(addedBy);
		contentMaster.setModifyOn(date);
		contentMaster.setIsActive(false);
		contentService.deleteContent(contentMaster);
		return "Content Deleted Successfully";
	}
	
	@RequestMapping(value = "/listOfContentMaster", method = RequestMethod.GET)
	public @ResponseBody List<ContentMaster> listOfContentMaster() {
		return contentService.listOfContentMaster();
	}
	
	@RequestMapping(value = "/getContentByContentId", method = RequestMethod.GET)
	public @ResponseBody ContentMaster getContentByContentId(HttpServletRequest request,@RequestParam("contentMasterId") Integer contentId) {
		ContentMaster contentMaster = contentService.getContentByContentId(contentId);
		return contentMaster;
	}
	
	@RequestMapping(value = "/categoryTable", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> categoryTable() {
		Map<String,Object> model=new HashMap<String,Object>();
		List<CategoryMaster> categoryMasters= categoryService.listOfCategorySubCategory();
		Long maxCountCategory = subCategoryService.maxCountCategory();
		model.put("maxCountCategory", maxCountCategory);
		model.put("categoryMasters", categoryMasters);
		return model;
	}
	
	@RequestMapping(value = "/contentsPreview", method = RequestMethod.POST)
	public @ResponseBody List<ContentMaster> contentsPreview(@RequestBody ContentMaster contentMaster) {
		return contentService.contentsPreview(contentMaster);
	}
	
	@RequestMapping(value = "/publishContent", method = RequestMethod.POST)
	public @ResponseBody String publishContent(@RequestBody PublishDetail publishDetail, 
			HttpSession session) {
		java.util.Date date = new java.util.Date();
		String addedBy=(String)session.getAttribute("userName");
		if(publishDetail.getDetailId()==null) {
			publishDetail.setAddedBy(addedBy);
			publishDetail.setAddedOn(date);
		}else {
			publishDetail.setModifyBy(addedBy);
			publishDetail.setModifyOn(date);
		}
		for(PublishMaster publishMaster : publishDetail.getPublishMasters()) {
			publishMaster.setPublishDetail(publishDetail);
		}
		contentService.publishContent(publishDetail);
		return "Content Publish Successfully";
	}

}
