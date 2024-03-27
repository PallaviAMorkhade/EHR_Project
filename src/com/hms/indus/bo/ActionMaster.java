package com.hms.indus.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ehr_action_master")
public class ActionMaster {
	
	@Id
	@GeneratedValue
	@Column(name = "action_id")
	private Integer actionId;
	
	@Column(name = "action")
	private String action;
	
	@Column(name = "added_by")
	private String addedBy;
	
	@Column(name = "added_on")
	private String addedOn;
	
	@Column(name = "modify_by")
	private String modifyBy;
	
	@Column(name = "modify_on")
	private String modifyOn;
	
	@Column(name="action_delete_flag")
	private Integer actionDeleteFlag;

	public Integer getActionId() {
		return actionId;
	}

	public String getAction() {
		return action;
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

	public Integer getActionDeleteFlag() {
		return actionDeleteFlag;
	}

	public void setActionId(Integer actionId) {
		this.actionId = actionId;
	}

	public void setAction(String action) {
		this.action = action;
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

	public void setActionDeleteFlag(Integer actionDeleteFlag) {
		this.actionDeleteFlag = actionDeleteFlag;
	}
	
}
