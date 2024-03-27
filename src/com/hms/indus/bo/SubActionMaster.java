package com.hms.indus.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ehr_sub_action_master")
public class SubActionMaster {
	
	@Id
	@GeneratedValue
	@Column(name = "sub_action_id")
	private Integer subActionId;
	
	@Column(name = "sub_action")
	private String subAction;
	
	@Column(name = "added_by")
	private String addedBy;
	
	@Column(name = "added_on")
	private String addedOn;
	
	@Column(name = "modify_by")
	private String modifyBy;
	
	@Column(name = "modify_on")
	private String modifyOn;
	
	@Column(name="sub_action_delete_flag")
	private Integer subActionDeleteFlag;

	@ManyToOne
	@JoinColumn(name = "action_id")
	private ActionMaster actionMaster;

	public Integer getSubActionId() {
		return subActionId;
	}

	public String getSubAction() {
		return subAction;
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

	public Integer getSubActionDeleteFlag() {
		return subActionDeleteFlag;
	}

	public ActionMaster getActionMaster() {
		return actionMaster;
	}

	public void setSubActionId(Integer subActionId) {
		this.subActionId = subActionId;
	}

	public void setSubAction(String subAction) {
		this.subAction = subAction;
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

	public void setSubActionDeleteFlag(Integer subActionDeleteFlag) {
		this.subActionDeleteFlag = subActionDeleteFlag;
	}

	public void setActionMaster(ActionMaster actionMaster) {
		this.actionMaster = actionMaster;
	}
	
}
