package com.hms.indus.bo;

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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "clients")
public class ClientMaster {

	@Id
	@GeneratedValue
	@Column(name = "client_id")
	private Integer clientId;
	
	@Column(name = "user_type")
	private String userType;
	
	@Column(name = "mb_relation")
	private String mbRelation;
	
	@Column(name = "client_first_name")
	private String firstName;
	
	@Column(name = "client_middle_name")
	private String middleName;
	
	@Column(name = "client_last_name")
	private String lastName;
	
	@Column(name = "client_dob")
	private String clientDOB;
	
	@Column(name = "client_gender")
	private String gender;
	
	@Column(name = "client_blood_group")
	private String bloodGroup;
	
	@Column(name = "client_address1")
	private String addressLine1;
	
	@Column(name = "client_address2")
	private String addressLine2;
	
	@Column(name = "client_address3")
	private String addressLine3;
	
	@Column(name = "client_pincode")
	private int pinCode;
	
	@ManyToOne
	@JoinColumn(name = "client_country")
	@NotFound( action = NotFoundAction.IGNORE )
	private CountryMaster countryMaster;
	
	@ManyToOne
	@JoinColumn(name = "client_state")
	@NotFound( action = NotFoundAction.IGNORE )
	private StateMaster stateMaster;
	
	@ManyToOne
	@JoinColumn(name = "client_city")
	@NotFound( action = NotFoundAction.IGNORE )
	private CityMaster cityMaster;
	
	@Column(name = "client_mob_no")
	private String mobNo;
	
	@Column(name = "client_email")
	private String emailId;
	
	@Column(name = "client_landline_no")
	private String landlineNo;

	@Column(name = "client_photo")
	private String photoUrl;
	
	@Column(name = "client_user_id")
	private String userId;
	
	@Column(name = "client_password")
	private String password;
	
	@Column(name = "age")
	private Integer clientAge;
	
	@Column(name = "client_isactive")
	private String isActive;
	
	@Column(name = "member_id")
	private String memberId;
	
	@Column(name = "self_beneficiary")
	private String selfBeneficiary;
	
	@Column(name = "is_agree_t_c")
	private String isAgree;
	
	@Column(name = "mothers_miden_name")
	private String mothersMidenName;
	
	@Column(name = "nominee_name")
	private String nominneName;
	
	@Column(name = "undertaking_form_path")
	private String formPath;
	
	@Column(name = "add_at")
	private String addAt;
	
	@Column(name = "add_by")
	private String addBy;
	
	@Column(name = "mod_at")
	private String modAt;
	
	@Column(name = "mod_by")
	private String modBy;
	
	@Column(name = "is_visited")
	private String isVisited;
	
	@Column(name = "is_scanned")
	private String isScanned;
	
	@Column(name = "is_undertaking")
	private String isUndertaking;
	
	@Column(name = "remark")
	private String remark;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "center_id")
	private CentreMaster centreMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "package_id")
	private PackageMaster packageMaster;
	
	@Column(name = "count")
	private Integer count;
	
	@Column(name = "client_locked")
	private Integer clientLocked;
	
	@Column(name = "client_locked_by")
	private Integer clientLockedBy;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clientMaster")
	private Set<ClientReportHead> clientReportHeadSet = new HashSet<ClientReportHead>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clientMaster")
	private Set<CheckUpMaster> checkUpMasterSet = new HashSet<CheckUpMaster>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clientMaster")
	private Set<AppointmentMaster> AppointmentMasterSet = new HashSet<AppointmentMaster>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clientMaster")
	private Set<ClientSelfUploadReport> clientSelfUploadReportSet = new HashSet<ClientSelfUploadReport>();
	
	@Column(name = "nominee_relation")
	private String nomineeRelation;
	
	@Column(name = "nominee_first_name")
	private String nomineeFirstName;
	
	@Column(name = "nominee_middle_name")
	private String nomineeMiddleName;
	
	@Column(name = "nominee_last_name")
	private String nomineeLastName;
	
	@Column(name = "nominee_dob")
	private String nomineeDOB;
	
	@Column(name = "client_height")
	private String clientHeight;
	
	@Column(name = "client_weight")
	private String clientWeight;
	
	@Column(name = "nominee_age")
	private Integer nomineeAge;
	
	@Column(name = "client_full_name")
	private String clientFullName;
	
	@Column(name = "height_in_cm")
	private String heightInCm;
	
	@Column(name = "weight_in_kg")
	private String weightInKg;
	
	@Column(name = "user_session_id")
	private String userSessionId;
	
	@Column(name = "is_verified")
	private String isVerified;
	
	@Column(name = "contact_flag")
	private String contactFlag;
	
	public String getContactFlag() {
		return contactFlag;
	}

	public void setContactFlag(String contactFlag) {
		this.contactFlag = contactFlag;
	}

	public String getMbRelation() {
		return mbRelation;
	}

	public void setMbRelation(String mbRelation) {
		this.mbRelation = mbRelation;
	}
	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getIsVerified() {
		return isVerified;
	}

	public void setIsVerified(String isVerified) {
		this.isVerified = isVerified;
	}

	public String getUserSessionId() {
		return userSessionId;
	}

	public void setUserSessionId(String userSessionId) {
		this.userSessionId = userSessionId;
	}

	public Set<ClientSelfUploadReport> getClientSelfUploadReportSet() {
		return clientSelfUploadReportSet;
	}

	public String getHeightInCm() {
		return heightInCm;
	}

	public String getWeightInKg() {
		return weightInKg;
	}

	public void setClientSelfUploadReportSet(
			Set<ClientSelfUploadReport> clientSelfUploadReportSet) {
		this.clientSelfUploadReportSet = clientSelfUploadReportSet;
	}

	public void setHeightInCm(String heightInCm) {
		this.heightInCm = heightInCm;
	}

	public void setWeightInKg(String weightInKg) {
		this.weightInKg = weightInKg;
	}

	public Integer getClientLocked() {
		return clientLocked;
	}

	public void setClientLocked(Integer clientLocked) {
		this.clientLocked = clientLocked;
	}
	
	public Integer getClientLockedBy() {
		return clientLockedBy;
	}

	public void setClientLockedBy(Integer clientLockedBy) {
		this.clientLockedBy = clientLockedBy;
	}

	public Set<ClientReportHead> getClientReportHeadSet() {
		return clientReportHeadSet;
	}

	public Set<CheckUpMaster> getCheckUpMasterSet() {
		return checkUpMasterSet;
	}

	public void setClientReportHeadSet(Set<ClientReportHead> clientReportHeadSet) {
		this.clientReportHeadSet = clientReportHeadSet;
	}

	public void setCheckUpMasterSet(Set<CheckUpMaster> checkUpMasterSet) {
		this.checkUpMasterSet = checkUpMasterSet;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
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

	public String getClientDOB() {
		return clientDOB;
	}

	public void setClientDOB(String clientDOB) {
		this.clientDOB = clientDOB;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
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

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public CountryMaster getCountryMaster() {
		return countryMaster;
	}

	public void setCountryMaster(CountryMaster countryMaster) {
		this.countryMaster = countryMaster;
	}

	public StateMaster getStateMaster() {
		return stateMaster;
	}

	public void setStateMaster(StateMaster stateMaster) {
		this.stateMaster = stateMaster;
	}

	public CityMaster getCityMaster() {
		return cityMaster;
	}

	public void setCityMaster(CityMaster cityMaster) {
		this.cityMaster = cityMaster;
	}

	public String getMobNo() {
		return mobNo;
	}

	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getLandlineNo() {
		return landlineNo;
	}

	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getClientAge() {
		return clientAge;
	}

	public void setClientAge(Integer clientAge) {
		this.clientAge = clientAge;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getSelfBeneficiary() {
		return selfBeneficiary;
	}

	public void setSelfBeneficiary(String selfBeneficiary) {
		this.selfBeneficiary = selfBeneficiary;
	}

	public String getIsAgree() {
		return isAgree;
	}

	public void setIsAgree(String isAgree) {
		this.isAgree = isAgree;
	}

	public String getMothersMidenName() {
		return mothersMidenName;
	}

	public void setMothersMidenName(String mothersMidenName) {
		this.mothersMidenName = mothersMidenName;
	}

	public String getNominneName() {
		return nominneName;
	}

	public void setNominneName(String nominneName) {
		this.nominneName = nominneName;
	}

	public String getFormPath() {
		return formPath;
	}

	public void setFormPath(String formPath) {
		this.formPath = formPath;
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

	public String getModAt() {
		return modAt;
	}

	public void setModAt(String modAt) {
		this.modAt = modAt;
	}

	public String getModBy() {
		return modBy;
	}

	public void setModBy(String modBy) {
		this.modBy = modBy;
	}

	public String getIsVisited() {
		return isVisited;
	}

	public void setIsVisited(String isVisited) {
		this.isVisited = isVisited;
	}

	public String getIsScanned() {
		return isScanned;
	}

	public void setIsScanned(String isScanned) {
		this.isScanned = isScanned;
	}

	public String getIsUndertaking() {
		return isUndertaking;
	}

	public void setIsUndertaking(String isUndertaking) {
		this.isUndertaking = isUndertaking;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public CentreMaster getCentreMaster() {
		return centreMaster;
	}

	public void setCentreMaster(CentreMaster centreMaster) {
		this.centreMaster = centreMaster;
	}

	public PackageMaster getPackageMaster() {
		return packageMaster;
	}

	public void setPackageMaster(PackageMaster packageMaster) {
		this.packageMaster = packageMaster;
	}

	public String getNomineeRelation() {
		return nomineeRelation;
	}

	public String getNomineeFirstName() {
		return nomineeFirstName;
	}

	public String getNomineeMiddleName() {
		return nomineeMiddleName;
	}

	public String getNomineeLastName() {
		return nomineeLastName;
	}

	public String getNomineeDOB() {
		return nomineeDOB;
	}

	public String getClientHeight() {
		return clientHeight;
	}

	public String getClientWeight() {
		return clientWeight;
	}

	public void setNomineeRelation(String nomineeRelation) {
		this.nomineeRelation = nomineeRelation;
	}

	public void setNomineeFirstName(String nomineeFirstName) {
		this.nomineeFirstName = nomineeFirstName;
	}

	public void setNomineeMiddleName(String nomineeMiddleName) {
		this.nomineeMiddleName = nomineeMiddleName;
	}

	public void setNomineeLastName(String nomineeLastName) {
		this.nomineeLastName = nomineeLastName;
	}

	public void setNomineeDOB(String nomineeDOB) {
		this.nomineeDOB = nomineeDOB;
	}

	public void setClientHeight(String clientHeight) {
		this.clientHeight = clientHeight;
	}

	public void setClientWeight(String clientWeight) {
		this.clientWeight = clientWeight;
	}

	public Integer getNomineeAge() {
		return nomineeAge;
	}

	public void setNomineeAge(Integer nomineeAge) {
		this.nomineeAge = nomineeAge;
	}

	public String getClientFullName() {
		return clientFullName;
	}

	public void setClientFullName(String clientFullName) {
		this.clientFullName = clientFullName;
	}

	public Set<AppointmentMaster> getAppointmentMasterSet() {
		return AppointmentMasterSet;
	}

	public void setAppointmentMasterSet(Set<AppointmentMaster> appointmentMasterSet) {
		AppointmentMasterSet = appointmentMasterSet;
	}
	
	
	
	
}
