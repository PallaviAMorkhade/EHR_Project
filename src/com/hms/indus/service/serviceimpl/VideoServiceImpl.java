package com.hms.indus.service.serviceimpl;

import javax.transaction.Transactional;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.dao.VideoDao;
import com.hms.indus.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService{
	
	@Autowired
	VideoDao videoDao;

	@Override
	@Transactional
	public JSONArray listOfVideoMaster() {
		return videoDao.listOfVideoMaster();
	}

	@Override
	@Transactional
	public void updateVideoMaster(JSONObject object) {
		videoDao.updateVideoMaster(object);
	}

	@Override
	@Transactional
	public String saveVideoMaster(JSONObject object) {
		return videoDao.saveVideoMaster(object);
	}

	@Override
	@Transactional
	public void deleteVideoMaster(JSONObject object) {
		videoDao.deleteVideoMaster(object);
	}

	@Override
	@Transactional
	public JSONObject getVideoMasterByVideoId(Integer videoMasterId) {
		return videoDao.getVideoMasterByVideoId(videoMasterId);
	}

	@Override
	@Transactional
	public String getMaxId() {
		return videoDao.getMaxId();
	}

}
