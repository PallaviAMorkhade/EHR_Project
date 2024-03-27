package com.hms.indus.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_type_master")
public class UserTypeMaster {

	@Id
	@GeneratedValue
	@Column(name = "user_type_id")
	private Integer userTypeId;
	
	@Column(name = "user_type_name")
	private String userTypeName;
	
	@Column(name = "is_leader")
	private String isLeader;

	public String getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(String isLeader) {
		this.isLeader = isLeader;
	}

	public Integer getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(Integer userTypeId) {
		this.userTypeId = userTypeId;
	}

	public String getUserTypeName() {
		return userTypeName;
	}

	public void setUserTypeName(String userTypeName) {
		this.userTypeName = userTypeName;
	}
}
