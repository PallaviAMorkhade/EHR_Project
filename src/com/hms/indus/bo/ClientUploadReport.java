package com.hms.indus.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "client_report_line")
public class ClientUploadReport {
	
	@Id
	@GeneratedValue
	@Column(name = "client_report_line_id")
	private Integer clientReportLineId;
	
	@Column(name = "report_desc")
	private String reportDescription;
	
	@Column(name = "report_filepath")
	private String filePath;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "client_report_id")
	private ClientReportHead clientReportHead;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "package_id")
	private PackageMaster packageMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "test_id")
	private TestMaster testMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "report_type_id")
	private ReportTypeMaster reportTypeMaster;
	
	@Column(name = "report_isactive")
	private String reportIsActive;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "clientUploadReportSet")
	private List<ReportVerification> reportVerificationSet = new ArrayList<ReportVerification>();
	
	@Column(name = "edit_report_status")
	private Integer editReportStatus;
	
	@Column(name = "report_date")
	private String reportDate;
	
	@Column(name = "is_email_Send")
	private String isEmailSend="N";
	
	@Transient
	private List<DataEntryVerification> dataEntryVerifications;
	
	public List<DataEntryVerification> getDataEntryVerifications() {
		return dataEntryVerifications;
	}
	public void setDataEntryVerifications(List<DataEntryVerification> dataEntryVerifications) {
		this.dataEntryVerifications = dataEntryVerifications;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public Integer getEditReportStatus() {
		return editReportStatus;
	}
	public void setEditReportStatus(Integer editReportStatus) {
		this.editReportStatus = editReportStatus;
	}
	
	public List<ReportVerification> getReportVerificationSet() {
		return reportVerificationSet;
	}
	public void setReportVerificationSet(
			List<ReportVerification> reportVerificationSet) {
		this.reportVerificationSet = reportVerificationSet;
	}
	public String getReportIsActive() {
		return reportIsActive;
	}
	public void setReportIsActive(String reportIsActive) {
		this.reportIsActive = reportIsActive;
	}
	public Integer getClientReportLineId() {
		return clientReportLineId;
	}
	public ClientReportHead getClientReportHead() {
		return clientReportHead;
	}
	public ReportTypeMaster getReportTypeMaster() {
		return reportTypeMaster;
	}
	public void setClientReportLineId(Integer clientReportLineId) {
		this.clientReportLineId = clientReportLineId;
	}
	public void setClientReportHead(ClientReportHead clientReportHead) {
		this.clientReportHead = clientReportHead;
	}
	public void setReportTypeMaster(ReportTypeMaster reportTypeMaster) {
		this.reportTypeMaster = reportTypeMaster;
	}
	public String getReportDescription() {
		return reportDescription;
	}
	public void setReportDescription(String reportDescription) {
		this.reportDescription = reportDescription;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public PackageMaster getPackageMaster() {
		return packageMaster;
	}
	public TestMaster getTestMaster() {
		return testMaster;
	}
	public void setPackageMaster(PackageMaster packageMaster) {
		this.packageMaster = packageMaster;
	}
	public void setTestMaster(TestMaster testMaster) {
		this.testMaster = testMaster;
	}
	public String getIsEmailSend() {
		return isEmailSend;
	}
	public void setIsEmailSend(String isEmailSend) {
		this.isEmailSend = isEmailSend;
	}
	
	
}
