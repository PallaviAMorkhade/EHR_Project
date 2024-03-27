package com.hms.indus.bo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "ehr_data_entry_verification")
public class DataEntryVerification implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "verify_id")
	private Integer verifyId;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "client_report_line_id")
	private Integer clientReportLineId;
	
	@Column(name="is_reject_verify", columnDefinition = "BOOLEAN")
	private Boolean isRejectVerify;
	
	@Column(name = "added_by",updatable=false)
	private String addedBy;
	
	@Column(name = "added_on",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date addedOn;
	
	@Transient
	private Integer clientId;

	@Transient
	private Integer checkUpId;
	
	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getCheckUpId() {
		return checkUpId;
	}

	public void setCheckUpId(Integer checkUpId) {
		this.checkUpId = checkUpId;
	}

	public Integer getVerifyId() {
		return verifyId;
	}

	public void setVerifyId(Integer verifyId) {
		this.verifyId = verifyId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getClientReportLineId() {
		return clientReportLineId;
	}

	public void setClientReportLineId(Integer clientReportLineId) {
		this.clientReportLineId = clientReportLineId;
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
	
	public Boolean getIsRejectVerify() {
		return isRejectVerify;
	}

	public void setIsRejectVerify(Boolean isRejectVerify) {
		this.isRejectVerify = isRejectVerify;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
