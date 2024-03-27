package com.hms.indus.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ehr_unit_master")
public class UnitMaster {
	
	@Id
	@GeneratedValue
	@Column(name = "unit_id")
	private Integer unitId;
	
	@Column(name = "unit_name")
	private String unitName;
	
	@Column(name = "added_by")
	private String addedBy;
	
	@Column(name = "added_on")
	private String addedOn;
	
	@Column(name = "modify_by")
	private String modifyBy;
	
	@Column(name = "modify_on")
	private String modifyOn;
	
	@Column(name="unit_master_delete_flag")
	private Integer unitMasterDeleteFlag;
	
	public Integer getUnitMasterDeleteFlag() {
		return unitMasterDeleteFlag;
	}

	public void setUnitMasterDeleteFlag(Integer unitMasterDeleteFlag) {
		this.unitMasterDeleteFlag = unitMasterDeleteFlag;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public String getUnitName() {
		return unitName;
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

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
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
	
	

}
