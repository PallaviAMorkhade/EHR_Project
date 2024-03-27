package com.hms.indus.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ehr_sms_template_master")
public class SmsTemplateMaster {

	@Id
	@GeneratedValue
	@Column(name = "sms_template_id")
	private Integer smsTemplateId;
	
	@Column(name = "sms_template_name")
	private String smsTemplateName;
	
	@Column(name = "template_description")
	private String smsTemplateDescription;
	
	@Column(name = "added_by")
	private String addedBy;
	
	@Column(name = "added_on")
	private String addedOn;
	
	@Column(name = "modify_by")
	private String modifyBy;
	
	@Column(name = "modify_on")
	private String modifyOn;
	
	@Column(name="sms_template_delete_flag")
	private Integer smsTemplateDeleteFlag;

	public Integer getSmsTemplateId() {
		return smsTemplateId;
	}

	public String getSmsTemplateName() {
		return smsTemplateName;
	}

	public String getSmsTemplateDescription() {
		return smsTemplateDescription;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public String getAddedOn() {
		return addedOn;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public String getModifyOn() {
		return modifyOn;
	}

	public Integer getSmsTemplateDeleteFlag() {
		return smsTemplateDeleteFlag;
	}

	public void setSmsTemplateId(Integer smsTemplateId) {
		this.smsTemplateId = smsTemplateId;
	}

	public void setSmsTemplateName(String smsTemplateName) {
		this.smsTemplateName = smsTemplateName;
	}

	public void setSmsTemplateDescription(String smsTemplateDescription) {
		this.smsTemplateDescription = smsTemplateDescription;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public void setAddedOn(String addedOn) {
		this.addedOn = addedOn;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public void setModifyOn(String modifyOn) {
		this.modifyOn = modifyOn;
	}

	public void setSmsTemplateDeleteFlag(Integer smsTemplateDeleteFlag) {
		this.smsTemplateDeleteFlag = smsTemplateDeleteFlag;
	}
	
	
}
