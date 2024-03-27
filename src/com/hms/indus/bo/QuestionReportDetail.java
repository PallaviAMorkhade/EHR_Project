package com.hms.indus.bo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "ehr_question_report_details")
public class QuestionReportDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private QuestionReportId questionReportId;

	@Column(name = "health_score")
	private Float healthScore;

	@Column(name = "report_statement")
	private String reportStatement;

	@Column(name = "test_id")
	private Integer testId;
	
	@Transient
	private String testName;

	@Column(name = "frequency_number")
	private String frequencyNumber;

	@Column(name = "frequency_id")
	private Integer frequencyId;
	
	@Transient
	private String frequency;

	@Column(name = "reason")
	private String reason;
	
	@Column(name = "is_active", columnDefinition = "BOOLEAN")
	private Boolean isActive;

	@Column(name = "added_by", updatable = false)
	private String addedBy;

	@Column(name = "added_on", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date addedOn;

	@Column(name = "modify_by")
	private String modifyBy;

	@Column(name = "modify_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyOn;
	
	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public QuestionReportId getQuestionReportId() {
		return questionReportId;
	}

	public void setQuestionReportId(QuestionReportId questionReportId) {
		this.questionReportId = questionReportId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Float getHealthScore() {
		return healthScore;
	}

	public void setHealthScore(Float healthScore) {
		this.healthScore = healthScore;
	}

	public String getReportStatement() {
		return reportStatement;
	}

	public void setReportStatement(String reportStatement) {
		this.reportStatement = reportStatement;
	}

	public Integer getTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public String getFrequencyNumber() {
		return frequencyNumber;
	}

	public void setFrequencyNumber(String frequencyNumber) {
		this.frequencyNumber = frequencyNumber;
	}

	public Integer getFrequencyId() {
		return frequencyId;
	}

	public void setFrequencyId(Integer frequencyId) {
		this.frequencyId = frequencyId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyOn() {
		return modifyOn;
	}

	public void setModifyOn(Date modifyOn) {
		this.modifyOn = modifyOn;
	}


}
