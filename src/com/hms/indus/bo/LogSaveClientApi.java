package com.hms.indus.bo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "ehr_log_save_client_api")
public class LogSaveClientApi {

	@Id
	@GeneratedValue
	@Column(name = "log_id")
	private Integer logId;
	
	@Column(name = "client_first_name")
	private String firstName;
	
	@Column(name = "client_middle_name")
	private String middleName;
	
	@Column(name = "client_last_name")
	private String lastName;
	
	@Column(name = "hospital_reg_no")
	private String hospitalRegNo;
		
	@Column(name = "client_address1")
	private String addressLine1;
	
	@Column(name = "client_address2")
	private String addressLine2;
	
	@Column(name = "client_address3")
	private String addressLine3;	
	
	@Column(name = "client_dob")
	private String clientDOB;
	
	@Column(name = "client_mob_no")
	private String mobNo;
	
	@Column(name = "client_gender")
	private String gender;
	
	@Column(name = "client_email")
	private String emailId;	
	
	@Column(name = "app_no")
	private String appNo;
	
	@Column(name = "ap_year")
	private String apYear;	
	
	@Column(name = "package_id")
	private Integer packageId;	
	
	@Column(name = "center_id")
	private Integer centerId;		
	
	@Column(name = "link_app_no")
	private Long linkAppNo;
	
	@Column(name = "mb_relation")
	private String mbRelation;
	
	@Column(name = "check_up_date")
	private String checkUpDate;
	
	@Column(name = "member_id")
	private String memberId;
	
	@Column(name = "user_type")
	private String userType;
		
	@Column(name = "added_on",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date addedOn;
	
	@Column(name = "modify_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyOn;

	public Integer getLogId() {
		return logId;
	}

	public void setLogId(Integer logId) {
		this.logId = logId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getHospitalRegNo() {
		return hospitalRegNo;
	}

	public void setHospitalRegNo(String hospitalRegNo) {
		this.hospitalRegNo = hospitalRegNo;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getClientDOB() {
		return clientDOB;
	}

	public void setClientDOB(String clientDOB) {
		this.clientDOB = clientDOB;
	}

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getApYear() {
		return apYear;
	}

	public void setApYear(String apYear) {
		this.apYear = apYear;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public Integer getCenterId() {
		return centerId;
	}

	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}

	public Long getLinkAppNo() {
		return linkAppNo;
	}

	public void setLinkAppNo(Long linkAppNo) {
		this.linkAppNo = linkAppNo;
	}

	public String getMbRelation() {
		return mbRelation;
	}

	public void setMbRelation(String mbRelation) {
		this.mbRelation = mbRelation;
	}

	public String getCheckUpDate() {
		return checkUpDate;
	}

	public void setCheckUpDate(String checkUpDate) {
		this.checkUpDate = checkUpDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public Date getModifyOn() {
		return modifyOn;
	}

	public void setModifyOn(Date modifyOn) {
		this.modifyOn = modifyOn;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}	
	
	
	
	
	
}
