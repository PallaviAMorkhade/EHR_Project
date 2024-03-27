package com.hms.indus.bo;

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
@Table(name = "ehr_get_beneficiary_api_log")
public class GetBeneficiaryApiLog {
	
	
	
	@Id
	@GeneratedValue
	@Column(name = "beneficiary_log")
	private Long beneficiaryLog;
	
	@Column(name = "MB_EHR_NO")
	private Long MB_EHR_NO;
	
	@Column(name = "MB_YEAR")
	private String MB_YEAR;
	
	@Column(name = "mb_app_ao")
	private Long mbAppNo;
	
	@Column(name = "mb_benificiary")
	private Long mbBenificiary;
	
	@Column(name = "MB_NAME")
	private String MB_NAME;
	
	
	@Column(name = "BENE_FIRST_NAME")
	private String BENE_FIRST_NAME;
	
	@Column(name = "BENE_MIDDLE_NAME")
	private String BENE_MIDDLE_NAME;
	
	
	@Column(name = "BENE_LAST_NAME")
	private String BENE_LAST_NAME;
	
	
	@Column(name = "MB_ADDRESS1")
	private String MB_ADDRESS1;
	
	@Column(name = "MB_ADDRESS2")
	private String MB_ADDRESS2;
	
	
	@Column(name = "MB_ADDRESS3")
	private String MB_ADDRESS3;
	
	
	@Column(name = "MB_TEL_NO")
	private String MB_TEL_NO;
	
	
	@Column(name = "MB_AGE")
	private Long MB_AGE;
	
	
	@Column(name = "MB_SEX")
	private String MB_SEX;
	
	
	@Column(name = "MB_DOB")
	private String MB_DOB;
	
	@Column(name = "MB_EMAIL")
	private String MB_EMAIL;
	
	@Column(name = "MB_REMARK")
	private String MB_REMARK;
	
	@Column(name = "AP_YEAR")
	private String AP_YEAR;
	
	@Column(name = "AP_APP_NO")
	private Long AP_APP_NO;
	

	
	@Column(name = "AP_APP_STATUS")
	private String AP_APP_STATUS;
	
	@Column(name = "AP_SALE_TYPE")
	private String AP_SALE_TYPE;
	
	
	@Column(name = "AP_MEMBER_ID")
	private String AP_MEMBER_ID;
	
	@Column(name = "AP_CENTER")
	private Long AP_CENTER;
	
	
	@Column(name = "AP_PRODUCT")
	private Long AP_PRODUCT;
	
	@Column(name = "AP_APP_DATE")
	private String AP_APP_DATE;
	
	@Column(name = "AP_SPECIAL_QUOTA_YN")
	private String AP_SPECIAL_QUOTA_YN;
	
	@Column(name = "AP_CHECKUP_DATE")
	private String AP_CHECKUP_DATE;
	
	@Column(name = "AP_CENTER_REGN_NO")
	private String AP_CENTER_REGN_NO;
	

	@Column(name = "AP_BENIFICIARY_ID")
	private Long AP_BENIFICIARY_ID;
	
	
	@Column(name = "AP_COUPON_ID")
	private Long AP_COUPON_ID;
	
	@Column(name = "AP_CT_TEST")
	private String AP_CT_TEST;
	
	@Column(name = "AP_NONCT_TEST")
	private String AP_NONCT_TEST;
	
	@Column(name = "AP_PATH_DATE")
	private String AP_PATH_DATE;
	
	@Column(name = "AP_PATH_CENTER")
	private Long AP_PATH_CENTER;
	
	@Column(name = "AP_PATH_TEST")
	private String AP_PATH_TEST;
	
	@Column(name = "AP_CT_CHKUP_DATE")
	private String AP_CT_CHKUP_DATE;
	
	@Column(name = "AP_NON_CT_CHKUP_DATE")
	private String AP_NON_CT_CHKUP_DATE;
	
	@Column(name = "AP_CT_BENIFICIARY_ID")
	private Long AP_CT_BENIFICIARY_ID;
	
	@Column(name = "AP_CT_CENTER")
	private Long AP_CT_CENTER;

	@Column(name = "AP_LINK_APP_NO")
	private Long AP_LINK_APP_NO;
	
	@Column(name = "AP_CT_APP_GIVEN")
	private String AP_CT_APP_GIVEN;
	
	@Column(name = "MB_VISIT_ID")
	private Long MB_VISIT_ID;
	
	
	@Column(name = "added_on",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date addedOn;
	
	@Column(name = "modify_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyOn;
	
	@Column(name = "MB_RELATION")
	private String MB_RELATION;
	
	

	public Long getBeneficiaryLog() {
		return beneficiaryLog;
	}

	public void setBeneficiaryLog(Long beneficiaryLog) {
		this.beneficiaryLog = beneficiaryLog;
	}

	public Long getMB_EHR_NO() {
		return MB_EHR_NO;
	}

	public void setMB_EHR_NO(Long mB_EHR_NO) {
		MB_EHR_NO = mB_EHR_NO;
	}

	public String getMB_YEAR() {
		return MB_YEAR;
	}

	public void setMB_YEAR(String mB_YEAR) {
		MB_YEAR = mB_YEAR;
	}

	public Long getMbAppNo() {
		return mbAppNo;
	}

	public void setMbAppNo(Long mbAppNo) {
		this.mbAppNo = mbAppNo;
	}

	public Long getMbBenificiary() {
		return mbBenificiary;
	}

	public void setMbBenificiary(Long mbBenificiary) {
		this.mbBenificiary = mbBenificiary;
	}

	public String getMB_NAME() {
		return MB_NAME;
	}

	public void setMB_NAME(String mB_NAME) {
		MB_NAME = mB_NAME;
	}

	public String getBENE_FIRST_NAME() {
		return BENE_FIRST_NAME;
	}

	public void setBENE_FIRST_NAME(String bENE_FIRST_NAME) {
		BENE_FIRST_NAME = bENE_FIRST_NAME;
	}

	public String getBENE_MIDDLE_NAME() {
		return BENE_MIDDLE_NAME;
	}

	public void setBENE_MIDDLE_NAME(String bENE_MIDDLE_NAME) {
		BENE_MIDDLE_NAME = bENE_MIDDLE_NAME;
	}

	public String getBENE_LAST_NAME() {
		return BENE_LAST_NAME;
	}

	public void setBENE_LAST_NAME(String bENE_LAST_NAME) {
		BENE_LAST_NAME = bENE_LAST_NAME;
	}

	public String getMB_ADDRESS1() {
		return MB_ADDRESS1;
	}

	public void setMB_ADDRESS1(String mB_ADDRESS1) {
		MB_ADDRESS1 = mB_ADDRESS1;
	}

	public String getMB_ADDRESS2() {
		return MB_ADDRESS2;
	}

	public void setMB_ADDRESS2(String mB_ADDRESS2) {
		MB_ADDRESS2 = mB_ADDRESS2;
	}

	public String getMB_ADDRESS3() {
		return MB_ADDRESS3;
	}

	public void setMB_ADDRESS3(String mB_ADDRESS3) {
		MB_ADDRESS3 = mB_ADDRESS3;
	}

	public String getMB_TEL_NO() {
		return MB_TEL_NO;
	}

	public void setMB_TEL_NO(String mB_TEL_NO) {
		MB_TEL_NO = mB_TEL_NO;
	}

	public Long getMB_AGE() {
		return MB_AGE;
	}

	public void setMB_AGE(Long mB_AGE) {
		MB_AGE = mB_AGE;
	}

	public String getMB_SEX() {
		return MB_SEX;
	}

	public void setMB_SEX(String mB_SEX) {
		MB_SEX = mB_SEX;
	}

	public String getMB_DOB() {
		return MB_DOB;
	}

	public void setMB_DOB(String mB_DOB) {
		MB_DOB = mB_DOB;
	}

	public String getMB_EMAIL() {
		return MB_EMAIL;
	}

	public void setMB_EMAIL(String mB_EMAIL) {
		MB_EMAIL = mB_EMAIL;
	}

	public String getMB_REMARK() {
		return MB_REMARK;
	}

	public void setMB_REMARK(String mB_REMARK) {
		MB_REMARK = mB_REMARK;
	}

	public String getAP_YEAR() {
		return AP_YEAR;
	}

	public void setAP_YEAR(String aP_YEAR) {
		AP_YEAR = aP_YEAR;
	}

	public Long getAP_APP_NO() {
		return AP_APP_NO;
	}

	public void setAP_APP_NO(Long aP_APP_NO) {
		AP_APP_NO = aP_APP_NO;
	}

	public String getAP_APP_STATUS() {
		return AP_APP_STATUS;
	}

	public void setAP_APP_STATUS(String aP_APP_STATUS) {
		AP_APP_STATUS = aP_APP_STATUS;
	}

	public String getAP_SALE_TYPE() {
		return AP_SALE_TYPE;
	}

	public void setAP_SALE_TYPE(String aP_SALE_TYPE) {
		AP_SALE_TYPE = aP_SALE_TYPE;
	}

	public String getAP_MEMBER_ID() {
		return AP_MEMBER_ID;
	}

	public void setAP_MEMBER_ID(String aP_MEMBER_ID) {
		AP_MEMBER_ID = aP_MEMBER_ID;
	}

	public Long getAP_CENTER() {
		return AP_CENTER;
	}

	public void setAP_CENTER(Long aP_CENTER) {
		AP_CENTER = aP_CENTER;
	}

	public Long getAP_PRODUCT() {
		return AP_PRODUCT;
	}

	public void setAP_PRODUCT(Long aP_PRODUCT) {
		AP_PRODUCT = aP_PRODUCT;
	}

	public String getAP_APP_DATE() {
		return AP_APP_DATE;
	}

	public void setAP_APP_DATE(String aP_APP_DATE) {
		AP_APP_DATE = aP_APP_DATE;
	}

	public String getAP_SPECIAL_QUOTA_YN() {
		return AP_SPECIAL_QUOTA_YN;
	}

	public void setAP_SPECIAL_QUOTA_YN(String aP_SPECIAL_QUOTA_YN) {
		AP_SPECIAL_QUOTA_YN = aP_SPECIAL_QUOTA_YN;
	}

	public String getAP_CHECKUP_DATE() {
		return AP_CHECKUP_DATE;
	}

	public void setAP_CHECKUP_DATE(String aP_CHECKUP_DATE) {
		AP_CHECKUP_DATE = aP_CHECKUP_DATE;
	}

	public String getAP_CENTER_REGN_NO() {
		return AP_CENTER_REGN_NO;
	}

	public void setAP_CENTER_REGN_NO(String aP_CENTER_REGN_NO) {
		AP_CENTER_REGN_NO = aP_CENTER_REGN_NO;
	}

	public Long getAP_BENIFICIARY_ID() {
		return AP_BENIFICIARY_ID;
	}

	public void setAP_BENIFICIARY_ID(Long aP_BENIFICIARY_ID) {
		AP_BENIFICIARY_ID = aP_BENIFICIARY_ID;
	}

	public Long getAP_COUPON_ID() {
		return AP_COUPON_ID;
	}

	public void setAP_COUPON_ID(Long aP_COUPON_ID) {
		AP_COUPON_ID = aP_COUPON_ID;
	}

	public String getAP_CT_TEST() {
		return AP_CT_TEST;
	}

	public void setAP_CT_TEST(String aP_CT_TEST) {
		AP_CT_TEST = aP_CT_TEST;
	}

	public String getAP_NONCT_TEST() {
		return AP_NONCT_TEST;
	}

	public void setAP_NONCT_TEST(String aP_NONCT_TEST) {
		AP_NONCT_TEST = aP_NONCT_TEST;
	}

	public String getAP_PATH_DATE() {
		return AP_PATH_DATE;
	}

	public void setAP_PATH_DATE(String aP_PATH_DATE) {
		AP_PATH_DATE = aP_PATH_DATE;
	}

	public Long getAP_PATH_CENTER() {
		return AP_PATH_CENTER;
	}

	public void setAP_PATH_CENTER(Long aP_PATH_CENTER) {
		AP_PATH_CENTER = aP_PATH_CENTER;
	}

	public String getAP_PATH_TEST() {
		return AP_PATH_TEST;
	}

	public void setAP_PATH_TEST(String aP_PATH_TEST) {
		AP_PATH_TEST = aP_PATH_TEST;
	}

	public String getAP_CT_CHKUP_DATE() {
		return AP_CT_CHKUP_DATE;
	}

	public void setAP_CT_CHKUP_DATE(String aP_CT_CHKUP_DATE) {
		AP_CT_CHKUP_DATE = aP_CT_CHKUP_DATE;
	}

	public String getAP_NON_CT_CHKUP_DATE() {
		return AP_NON_CT_CHKUP_DATE;
	}

	public void setAP_NON_CT_CHKUP_DATE(String aP_NON_CT_CHKUP_DATE) {
		AP_NON_CT_CHKUP_DATE = aP_NON_CT_CHKUP_DATE;
	}

	public Long getAP_CT_BENIFICIARY_ID() {
		return AP_CT_BENIFICIARY_ID;
	}

	public void setAP_CT_BENIFICIARY_ID(Long aP_CT_BENIFICIARY_ID) {
		AP_CT_BENIFICIARY_ID = aP_CT_BENIFICIARY_ID;
	}

	public Long getAP_CT_CENTER() {
		return AP_CT_CENTER;
	}

	public void setAP_CT_CENTER(Long aP_CT_CENTER) {
		AP_CT_CENTER = aP_CT_CENTER;
	}

	public Long getAP_LINK_APP_NO() {
		return AP_LINK_APP_NO;
	}

	public void setAP_LINK_APP_NO(Long aP_LINK_APP_NO) {
		AP_LINK_APP_NO = aP_LINK_APP_NO;
	}

	public String getAP_CT_APP_GIVEN() {
		return AP_CT_APP_GIVEN;
	}

	public void setAP_CT_APP_GIVEN(String aP_CT_APP_GIVEN) {
		AP_CT_APP_GIVEN = aP_CT_APP_GIVEN;
	}

	public Long getMB_VISIT_ID() {
		return MB_VISIT_ID;
	}

	public void setMB_VISIT_ID(Long mB_VISIT_ID) {
		MB_VISIT_ID = mB_VISIT_ID;
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

	public String getMB_RELATION() {
		return MB_RELATION;
	}

	public void setMB_RELATION(String mB_RELATION) {
		MB_RELATION = mB_RELATION;
	}


}
