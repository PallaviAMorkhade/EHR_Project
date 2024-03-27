package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.ContentMaster;
import com.hms.indus.bo.PublishDetail;
import com.hms.indus.dao.ContentDao;
import com.hms.indus.service.ContentService;

@Service
@Transactional
public class ContentServiceImpl implements ContentService{
	
	@Autowired
	ContentDao contentDao;

	@Override
	public String saveContent(ContentMaster contentMaster) {
		return contentDao.saveContent(contentMaster);
	}

	@Override
	public String updateContent(ContentMaster contentMaster) {
		return contentDao.updateContent(contentMaster);
	}

	@Override
	public String deleteContent(ContentMaster contentMaster) {
		return contentDao.deleteContent(contentMaster);
	}

	@Override
	public List<ContentMaster> listOfContentMaster() {
		return contentDao.listOfContentMaster();
	}

	@Override
	public ContentMaster getContentByContentId(Integer contentId) {
		return contentDao.getContentByContentId(contentId);
	}

	@Override
	public List<ContentMaster> contentsPreview(ContentMaster contentMaster) {
		return contentDao.contentsPreview(contentMaster);
	}

	@Override
	public String publishContent(PublishDetail publishDetail) {
		return contentDao.publishContent(publishDetail);
	}
	
	

}
