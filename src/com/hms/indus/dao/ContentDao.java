package com.hms.indus.dao;

import java.util.List;

import com.hms.indus.bo.ContentMaster;
import com.hms.indus.bo.PublishDetail;

public interface ContentDao {
	
	String saveContent(ContentMaster contentMaster);

	String updateContent(ContentMaster contentMaster);
	
	String deleteContent(ContentMaster contentMaster);
	
	List<ContentMaster> listOfContentMaster();

	ContentMaster getContentByContentId(Integer contentId);

	List<ContentMaster> contentsPreview(ContentMaster contentMaster);
	
	String publishContent(PublishDetail publishDetail);

}
