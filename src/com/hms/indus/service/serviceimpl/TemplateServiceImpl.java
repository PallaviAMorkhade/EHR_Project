package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.SmsTemplateMaster;
import com.hms.indus.bo.TemplateMaster;
import com.hms.indus.dao.TemplateDao;
import com.hms.indus.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {

	@Autowired
	TemplateDao templateDao;

	@Override
	@Transactional
	public String saveTemplateMaster(TemplateMaster templateMaster) {
		return templateDao.saveTemplateMaster(templateMaster);
	}

	@Override
	@Transactional
	public String deleteTemplateMaster(TemplateMaster templateMaster) {
		return templateDao.deleteTemplateMaster(templateMaster);
	}

	@Override
	@Transactional
	public String updateTemplateMaster(TemplateMaster templateMaster) {
		return templateDao.updateTemplateMaster(templateMaster);
	}

	@Override
	@Transactional
	public List<TemplateMaster> listOfTemplateMaster() {
		return templateDao.listOfTemplateMaster();
	}

	@Override
	@Transactional
	public TemplateMaster getTemplateMasterByTemplateId(Integer templateMasterId) {
		return templateDao.getTemplateMasterByTemplateId(templateMasterId);
	}

	@Override
	@Transactional
	public String saveSmsTemplateMaster(SmsTemplateMaster smsTemplateMaster) {
		return templateDao.saveSmsTemplateMaster(smsTemplateMaster);
	}

	@Override
	@Transactional
	public String deleteSmsTemplateMaster(SmsTemplateMaster smstemplateMaster) {
		return templateDao.deleteSmsTemplateMaster(smstemplateMaster);
	}

	@Override
	@Transactional
	public String updateSmsTemplateMaster(SmsTemplateMaster smstemplateMaster) {
		return templateDao.updateSmsTemplateMaster(smstemplateMaster);
	}

	@Override
	@Transactional
	public List<SmsTemplateMaster> listOfSmsTemplateMaster() {
		return templateDao.listOfSmsTemplateMaster();
	}

	@Override
	@Transactional
	public SmsTemplateMaster getSmsTemplateMasterByTemplateId(
			Integer smsTemplateMasterId) {
		return templateDao.getSmsTemplateMasterByTemplateId(smsTemplateMasterId);
	}
	
	
}
