package com.hms.indus.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ehr_sms_record")
public class SMSRecord {
	
	@Id
	@GeneratedValue
	@Column(name = "ehr_sms_record_id")
	private Integer id;
	
	@Column(name = "client_id")
	private Integer clientId;
	
	@Column(name = "sms_template_id")
	private Integer templateId;
	
	@Column(name = "topic")
	private String topic;
	
	@Column(name = "sms_medical_advice")
	private String smsMedicalAdvice;
	
	@Column(name="is_active", columnDefinition = "BOOLEAN")
	private Boolean isActive = true;
	
	@Column(name = "sent_to_mob_no")
	private String sentTo;
	
	@Column(name = "sent_by")
	private String sentBy;
	
	@Column(name = "sent_on",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date sentOn = new Date();

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getSentTo() {
		return sentTo;
	}

	public void setSentTo(String sentTo) {
		this.sentTo = sentTo;
	}

	public String getSentBy() {
		return sentBy;
	}

	public void setSentBy(String sentBy) {
		this.sentBy = sentBy;
	}

	public Date getSentOn() {
		return sentOn;
	}

	public void setSentOn(Date sentOn) {
		this.sentOn = sentOn;
	}

	public String getSmsMedicalAdvice() {
		return smsMedicalAdvice;
	}

	public void setSmsMedicalAdvice(String smsMedicalAdvice) {
		this.smsMedicalAdvice = smsMedicalAdvice;
	}
	

}
