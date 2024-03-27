package com.hms.indus.service.serviceimpl;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.dao.LinkDao;
import com.hms.indus.service.LinkService;

@Service
public class LinkServiceImpl implements LinkService{
	
	@Autowired
	LinkDao linkDao;

	@Override
	@Transactional
	public JSONArray listOfLinkMaster() {
		return linkDao.listOfLinkMaster();
	}

	@Override
	@Transactional
	public void updateLinkMaster(JSONObject object) {
		linkDao.updateLinkMaster(object);		
	}

	@Override
	@Transactional
	public String saveLinkMaster(JSONObject object) {
		return linkDao.saveLinkMaster(object);
	}

	@Override
	@Transactional
	public void deleteLinkMaster(JSONObject object) {
		linkDao.deleteLinkMaster(object);
	}

	@Override
	@Transactional
	public JSONObject getLinkMasterByLinkId(Integer linkMasterId) {
		return linkDao.getLinkMasterByLinkId(linkMasterId);
	}

}
