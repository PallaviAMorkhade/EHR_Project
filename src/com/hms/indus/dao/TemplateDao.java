package com.hms.indus.dao;

import java.util.List;

import com.hms.indus.bo.SmsTemplateMaster;
import com.hms.indus.bo.TemplateMaster;

public interface TemplateDao {

	//for Email template
	String saveTemplateMaster(TemplateMaster templateMaster);

	String deleteTemplateMaster(TemplateMaster templateMaster);

	String updateTemplateMaster(TemplateMaster templateMaster);
	
	List<TemplateMaster> listOfTemplateMaster();
	
	TemplateMaster getTemplateMasterByTemplateId(Integer templateMasterId);
	
	//for Sms template
		String saveSmsTemplateMaster(SmsTemplateMaster smsTemplateMaster);

		String deleteSmsTemplateMaster(SmsTemplateMaster smstemplateMaster);

		String updateSmsTemplateMaster(SmsTemplateMaster smstemplateMaster);
			
		List<SmsTemplateMaster> listOfSmsTemplateMaster();

		SmsTemplateMaster getSmsTemplateMasterByTemplateId(Integer smsTemplateMasterId);
}
