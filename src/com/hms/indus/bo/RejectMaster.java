package com.hms.indus.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ehr_reject_master")
public class RejectMaster {

	@Id
	@GeneratedValue
	@Column(name = "reject_id")
	private Integer rejectId;
	
	@Column(name = "reject_reason")
	private String rejectReason;
	
	@Column(name = "added_by")
	private String addedBy;
	
	@Column(name = "added_on")
	private String addedOn;
	
	@Column(name = "modify_by")
	private String modifyBy;
	
	@Column(name = "modify_on")
	private String modifyOn;
	
	@Column(name="reject_delete_flag")
	private Integer rejectDeleteFlag;

	public Integer getRejectId() {
		return rejectId;
	}

	public void setRejectId(Integer rejectId) {
		this.rejectId = rejectId;
	}

	public String getRejectReason() {
		return rejectReason;
	}

	public void setRejectReason(String rejectReason) {
		this.rejectReason = rejectReason;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public String getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(String addedOn) {
		this.addedOn = addedOn;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public String getModifyOn() {
		return modifyOn;
	}

	public void setModifyOn(String modifyOn) {
		this.modifyOn = modifyOn;
	}

	public Integer getRejectDeleteFlag() {
		return rejectDeleteFlag;
	}

	public void setRejectDeleteFlag(Integer rejectDeleteFlag) {
		this.rejectDeleteFlag = rejectDeleteFlag;
	}
	
	
}
