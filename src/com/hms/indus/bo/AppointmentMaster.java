package com.hms.indus.bo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "ehr_appointment_master")
public class AppointmentMaster {
	
	@Id
	@GeneratedValue
	@Column(name = "appointment_id")
	private Integer appointmentId;
	
	@Column(name = "ap_year")
	private String apYear;
	
	@Column(name = "ap_app_no")
	private String apAppNo;
	
	@Column(name = "ap_app_status")
	private String apAppStatus;
	
	@Column(name = "ap_sale_type")
	private String apSaleType;
	
	@Column(name = "ap_member_id")
	private String apMemberId;
	
	@Column(name = "ap_center")
	private String apCenter;
	
	@Column(name = "ap_product")
	private String apProduct;
	
	@Column(name = "ap_app_date")
	private String apAppDate;
	
	@Column(name = "ap_special_quota_status")
	private String apSpecialQuotaStatus;
	
	@Column(name = "ap_check_up_date")
	private String apCheckUpDate;
	
	@Column(name = "ap_center_reg_no")
	private String apCenterRegNo;
	
	@Column(name = "ap_beneficiary_id")
	private String apBeneficiaryId;
	
	@Column(name = "ap_coupon_id")
	private String apCouponId;
	
	@Column(name = "ap_non_ct_test")
	private String apNonCttest;
	
	@Column(name = "ap_ct_test")
	private String apCtTest;
	
	@Column(name = "ap_path_date")
	private String apPathDate;
	
	@Column(name = "ap_path_center")
	private String apPathCenter;
	
	@Column(name = "ap_path_test")
	private String apPathTest;
	
	@Column(name = "ap_ct_check_up_date")
	private String apCtCheckUpDate;
	
	@Column(name = "ap_non_ct_check_up_date")
	private String apNonCtCheckUpDate;
	
	@Column(name = "ap_ct_beneficiary_id")
	private String apCtBeneficiaryId;
	
	@Column(name = "ap_ct_center")
	private String apCtCenter;
	
	@Column(name = "ap_link_app_no")
	private String apLinkAppNo;
	
	@Column(name = "ap_ct_app_given")
	private String apCtAppGiven;
	
	@Column(name = "mb_year")
	private String mbYear;
	
	@Column(name = "mb_relation")
	private String mbRelation;
	
	@Column(name = "mb_benificiary")
	private Long mbBenificiary;
	
	@Column(name = "checkup_id")
	private Integer checkupId;
	
	@Column(name = "added_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date addedOn;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private ClientMaster clientMaster;
	
	public Long getMbBenificiary() {
		return mbBenificiary;
	}

	public void setMbBenificiary(Long mbBenificiary) {
		this.mbBenificiary = mbBenificiary;
	}
	
	public ClientMaster getClientMaster() {
		return clientMaster;
	}

	public String getMbYear() {
		return mbYear;
	}

	public String getMbRelation() {
		return mbRelation;
	}

	public void setMbYear(String mbYear) {
		this.mbYear = mbYear;
	}

	public void setMbRelation(String mbRelation) {
		this.mbRelation = mbRelation;
	}

	public void setClientMaster(ClientMaster clientMaster) {
		this.clientMaster = clientMaster;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public String getApYear() {
		return apYear;
	}

	public String getApAppNo() {
		return apAppNo;
	}

	public String getApAppStatus() {
		return apAppStatus;
	}

	public String getApSaleType() {
		return apSaleType;
	}

	public String getApMemberId() {
		return apMemberId;
	}

	public String getApCenter() {
		return apCenter;
	}

	public String getApProduct() {
		return apProduct;
	}

	public String getApAppDate() {
		return apAppDate;
	}

	public String getApSpecialQuotaStatus() {
		return apSpecialQuotaStatus;
	}

	public String getApCheckUpDate() {
		return apCheckUpDate;
	}

	public String getApCenterRegNo() {
		return apCenterRegNo;
	}

	public String getApBeneficiaryId() {
		return apBeneficiaryId;
	}

	public String getApCouponId() {
		return apCouponId;
	}

	public String getApNonCttest() {
		return apNonCttest;
	}

	public String getApCtTest() {
		return apCtTest;
	}

	public String getApPathDate() {
		return apPathDate;
	}

	public String getApPathCenter() {
		return apPathCenter;
	}

	public String getApPathTest() {
		return apPathTest;
	}

	public String getApCtCheckUpDate() {
		return apCtCheckUpDate;
	}

	public String getApNonCtCheckUpDate() {
		return apNonCtCheckUpDate;
	}

	public String getApCtBeneficiaryId() {
		return apCtBeneficiaryId;
	}

	public String getApCtCenter() {
		return apCtCenter;
	}

	public String getApLinkAppNo() {
		return apLinkAppNo;
	}

	public String getApCtAppGiven() {
		return apCtAppGiven;
	}

	public void setAppointmentId(Integer appointmentId) {
		this.appointmentId = appointmentId;
	}

	public void setApYear(String apYear) {
		this.apYear = apYear;
	}

	public void setApAppNo(String apAppNo) {
		this.apAppNo = apAppNo;
	}

	public void setApAppStatus(String apAppStatus) {
		this.apAppStatus = apAppStatus;
	}

	public void setApSaleType(String apSaleType) {
		this.apSaleType = apSaleType;
	}

	public void setApMemberId(String apMemberId) {
		this.apMemberId = apMemberId;
	}

	public void setApCenter(String apCenter) {
		this.apCenter = apCenter;
	}

	public void setApProduct(String apProduct) {
		this.apProduct = apProduct;
	}

	public void setApAppDate(String apAppDate) {
		this.apAppDate = apAppDate;
	}

	public void setApSpecialQuotaStatus(String apSpecialQuotaStatus) {
		this.apSpecialQuotaStatus = apSpecialQuotaStatus;
	}

	public void setApCheckUpDate(String apCheckUpDate) {
		this.apCheckUpDate = apCheckUpDate;
	}

	public void setApCenterRegNo(String apCenterRegNo) {
		this.apCenterRegNo = apCenterRegNo;
	}

	public void setApBeneficiaryId(String apBeneficiaryId) {
		this.apBeneficiaryId = apBeneficiaryId;
	}

	public void setApCouponId(String apCouponId) {
		this.apCouponId = apCouponId;
	}

	public void setApNonCttest(String apNonCttest) {
		this.apNonCttest = apNonCttest;
	}

	public void setApCtTest(String apCtTest) {
		this.apCtTest = apCtTest;
	}

	public void setApPathDate(String apPathDate) {
		this.apPathDate = apPathDate;
	}

	public void setApPathCenter(String apPathCenter) {
		this.apPathCenter = apPathCenter;
	}

	public void setApPathTest(String apPathTest) {
		this.apPathTest = apPathTest;
	}

	public void setApCtCheckUpDate(String apCtCheckUpDate) {
		this.apCtCheckUpDate = apCtCheckUpDate;
	}

	public void setApNonCtCheckUpDate(String apNonCtCheckUpDate) {
		this.apNonCtCheckUpDate = apNonCtCheckUpDate;
	}

	public void setApCtBeneficiaryId(String apCtBeneficiaryId) {
		this.apCtBeneficiaryId = apCtBeneficiaryId;
	}

	public void setApCtCenter(String apCtCenter) {
		this.apCtCenter = apCtCenter;
	}

	public void setApLinkAppNo(String apLinkAppNo) {
		this.apLinkAppNo = apLinkAppNo;
	}

	public void setApCtAppGiven(String apCtAppGiven) {
		this.apCtAppGiven = apCtAppGiven;
	}

	public Integer getCheckupId() {
		return checkupId;
	}

	public void setCheckupId(Integer checkupId) {
		this.checkupId = checkupId;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}
	
	

}
