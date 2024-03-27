package com.hms.indus.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ehr_users")
public class UserMaster {

	@Id
	@GeneratedValue
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "middle_name")
	private String middleName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "password")
	private String newPassword;
	
	@Column(name = "isactive")
	private Character isActive;
	
	@Column(name = "add_at")
	private String addAt;
	
	@Column(name = "add_by")
	private String addBy;
	
	@Column(name = "modify_at")
	private String modifyAt;
	
	@Column(name = "modify_by")
	private String modifyBy;
	
	@Column(name = "login_status")
	private String loginStatus;
	
	@Column(name = "login_time")
	private String loginTime;

	@ManyToOne
	@JoinColumn(name = "user_type_id")
	private UserTypeMaster userTypeMaster;
	
	@JoinColumn(name = "centers")
	private String centers;
	
	@JoinColumn(name = "packages")
	private String packages;
	
	@JoinColumn(name = "tests")
	private String tests;
	
	@Column(name = "member_code")
	private String memberCode;
	

	public String getPackages() {
		return packages;
	}

	public String getTests() {
		return tests;
	}

	public void setPackages(String packages) {
		this.packages = packages;
	}

	public void setTests(String tests) {
		this.tests = tests;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Character getIsActive() {
		return isActive;
	}

	public void setIsActive(Character isActive) {
		this.isActive = isActive;
	}

	public String getAddAt() {
		return addAt;
	}

	public void setAddAt(String addAt) {
		this.addAt = addAt;
	}

	public String getAddBy() {
		return addBy;
	}

	public void setAddBy(String addBy) {
		this.addBy = addBy;
	}

	public String getLoginStatus() {
		return loginStatus;
	}

	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public UserTypeMaster getUserTypeMaster() {
		return userTypeMaster;
	}

	public void setUserTypeMaster(UserTypeMaster userTypeMaster) {
		this.userTypeMaster = userTypeMaster;
	}

	public String getCenters() {
		return centers;
	}

	public void setCenters(String centers) {
		this.centers = centers;
	}

	public String getTitle() {
		return title;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getModifyAt() {
		return modifyAt;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setModifyAt(String modifyAt) {
		this.modifyAt = modifyAt;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	
	
}
