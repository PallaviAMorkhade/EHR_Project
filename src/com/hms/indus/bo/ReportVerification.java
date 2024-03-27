package com.hms.indus.bo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "report_verification")
public class ReportVerification {
	
	@Id
	@GeneratedValue
	@Column(name = "client_report_verify_id")
	private Integer clientReportVerifyId;
	
	@ManyToOne
	@JoinColumn(name = "reject_id")
	private RejectMaster rejectMaster;
	
	@Column(name = "reject_comment")
	private String rejectComment;
	
	@Column(name = "verify_comment")
	private String verifyComment;
	
	@Column(name = "approved_by")
	private String approvedBy;
	
	@Column(name = "approved_on")
	private String approvedOn;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "clientreportverify_clientreportline",joinColumns = { 
			@JoinColumn(name = "client_report_verify_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "client_report_line_id", 
					nullable = false, updatable = false) })
	private List<ClientUploadReport> clientUploadReportSet = new ArrayList<ClientUploadReport>();

	public RejectMaster getRejectMaster() {
		return rejectMaster;
	}

	public void setRejectMaster(RejectMaster rejectMaster) {
		this.rejectMaster = rejectMaster;
	}

	public Integer getClientReportVerifyId() {
		return clientReportVerifyId;
	}

	public String getRejectComment() {
		return rejectComment;
	}

	public String getVerifyComment() {
		return verifyComment;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public String getApprovedOn() {
		return approvedOn;
	}

	public void setClientReportVerifyId(Integer clientReportVerifyId) {
		this.clientReportVerifyId = clientReportVerifyId;
	}
	
	public void setRejectComment(String rejectComment) {
		this.rejectComment = rejectComment;
	}

	public void setVerifyComment(String verifyComment) {
		this.verifyComment = verifyComment;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public void setApprovedOn(String approvedOn) {
		this.approvedOn = approvedOn;
	}

	public List<ClientUploadReport> getClientUploadReportSet() {
		return clientUploadReportSet;
	}

	public void setClientUploadReportSet(
			List<ClientUploadReport> clientUploadReportSet) {
		this.clientUploadReportSet = clientUploadReportSet;
	}
	

}
