package com.hms.indus.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ehr_visit_type_master")
public class VisitTypeMaster {
	
	@Id
	@GeneratedValue
	@Column(name = "visit_type_id")
	private Integer visitTypeId;
	
	@Column(name = "visit_type")
	private String visitType;
	
	@Column(name = "added_by")
	private String addedBy;
	
	@Column(name = "added_on")
	private String addedOn;
	
	@Column(name = "modify_by")
	private String modifyBy;
	
	@Column(name = "modify_on")
	private String modifyOn;
	
	@Column(name="visit_type_delete_flag")
	private Integer visitTypeDeleteFlag;

	public Integer getVisitTypeId() {
		return visitTypeId;
	}

	public String getVisitType() {
		return visitType;
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

	public Integer getVisitTypeDeleteFlag() {
		return visitTypeDeleteFlag;
	}

	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}

	public void setVisitType(String visitType) {
		this.visitType = visitType;
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

	public void setVisitTypeDeleteFlag(Integer visitTypeDeleteFlag) {
		this.visitTypeDeleteFlag = visitTypeDeleteFlag;
	}

}
