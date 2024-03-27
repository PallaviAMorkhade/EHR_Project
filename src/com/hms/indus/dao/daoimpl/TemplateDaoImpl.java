package com.hms.indus.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.RejectMaster;
import com.hms.indus.bo.SmsTemplateMaster;
import com.hms.indus.bo.TemplateMaster;
import com.hms.indus.dao.TemplateDao;

@Repository
public class TemplateDaoImpl implements TemplateDao{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public String saveTemplateMaster(TemplateMaster templateMaster) {
		try {
			sessionFactory.getCurrentSession().save(templateMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Template Master saved successfully";
	}

	@Override
	public String deleteTemplateMaster(TemplateMaster templateMaster) {
		try {
		TemplateMaster templateMaster2 = (TemplateMaster) sessionFactory.getCurrentSession().get(TemplateMaster.class, templateMaster.getTemplateId());
		templateMaster2.setTemplateDeleteFlag(0);
		templateMaster2.setModifyBy(templateMaster.getModifyBy());
		templateMaster2.setModifyOn(templateMaster.getModifyOn());
		sessionFactory.getCurrentSession().update(templateMaster2);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Template Master deleted";
	}

	@Override
	public String updateTemplateMaster(TemplateMaster templateMaster) {
		try {
		TemplateMaster templateMaster2 = (TemplateMaster) sessionFactory.getCurrentSession().get(TemplateMaster.class, templateMaster.getTemplateId());
		templateMaster2.setTemplateDescription(templateMaster.getTemplateDescription());
		templateMaster2.setModifyBy(templateMaster.getModifyBy());
		templateMaster2.setModifyOn(templateMaster.getModifyOn());
		templateMaster2.setTemplateName(templateMaster.getTemplateName());
		sessionFactory.getCurrentSession().update(templateMaster2);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Template Master updated";
	}

	@Override
	public List<TemplateMaster> listOfTemplateMaster() {
		List<TemplateMaster> templateMasterList=new ArrayList<TemplateMaster>();
		try {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(TemplateMaster.class);
		criteria.add(Restrictions.eq("templateDeleteFlag", 1));
		templateMasterList=criteria.list();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return templateMasterList;
	}

	@Override
	public TemplateMaster getTemplateMasterByTemplateId(Integer templateMasterId) {
		TemplateMaster templateMaster=new TemplateMaster();
		try {
			templateMaster=(TemplateMaster) sessionFactory.getCurrentSession().get(TemplateMaster.class, templateMasterId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return templateMaster;
	}

	@Override
	public String saveSmsTemplateMaster(SmsTemplateMaster smsTemplateMaster) {
		try {
			sessionFactory.getCurrentSession().save(smsTemplateMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Sms Template Master saved successfully";
	}

	@Override
	public String deleteSmsTemplateMaster(SmsTemplateMaster smsTemplateMaster) {
		try {
		SmsTemplateMaster smsTemplateMaster2 = (SmsTemplateMaster) sessionFactory.getCurrentSession().get(SmsTemplateMaster.class, smsTemplateMaster.getSmsTemplateId());
		smsTemplateMaster2.setSmsTemplateDeleteFlag(0);
		smsTemplateMaster2.setModifyBy(smsTemplateMaster.getModifyBy());
		smsTemplateMaster2.setModifyOn(smsTemplateMaster.getModifyOn());
		sessionFactory.getCurrentSession().update(smsTemplateMaster2);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Sms Template Master deleted";
	}

	@Override
	public String updateSmsTemplateMaster(SmsTemplateMaster smsTemplateMaster) {
		try {
		SmsTemplateMaster smsTemplateMaster2 = (SmsTemplateMaster) sessionFactory.getCurrentSession().get(SmsTemplateMaster.class, smsTemplateMaster.getSmsTemplateId());
		smsTemplateMaster2.setSmsTemplateDescription(smsTemplateMaster.getSmsTemplateDescription());
		smsTemplateMaster2.setModifyBy(smsTemplateMaster.getModifyBy());
		smsTemplateMaster2.setModifyOn(smsTemplateMaster.getModifyOn());
		smsTemplateMaster2.setSmsTemplateName(smsTemplateMaster.getSmsTemplateName());
		sessionFactory.getCurrentSession().update(smsTemplateMaster2);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Sms Template Master updated";
	}

	@Override
	public List<SmsTemplateMaster> listOfSmsTemplateMaster() {
		List<SmsTemplateMaster> smsTemplateMasterList=new ArrayList<>();
		try {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(SmsTemplateMaster.class);
		criteria.add(Restrictions.eq("smsTemplateDeleteFlag", 1));
		smsTemplateMasterList=criteria.list();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return smsTemplateMasterList;
	}

	@Override
	public SmsTemplateMaster getSmsTemplateMasterByTemplateId(
			Integer smsTemplateMasterId) {
		SmsTemplateMaster smsTemplateMaster=new SmsTemplateMaster();
		try {
			smsTemplateMaster=(SmsTemplateMaster) sessionFactory.getCurrentSession().get(SmsTemplateMaster.class, smsTemplateMasterId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return smsTemplateMaster;
	}
	

}
