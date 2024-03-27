package com.hms.indus.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ehr_client_self_upload_report")
public class ClientSelfUploadReport {
	
	@Id
	@GeneratedValue
	@Column(name = "client_self_report_id")
	private Integer clientSelfReportId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private ClientMaster clientMaster;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "report_filepath")
	private String filePath;
	
	@Column(name = "added_by")
	private String addedBy;
	
	@Column(name = "added_on")
	private String addedOn;
	
	@Column(name = "modify_by")
	private String modifyBy;
	
	@Column(name = "modify_on")
	private String modifyOn;
	
	@Column(name = "report_isactive")
	private String reportIsActive;
	
	@Column(name = "edit_report_status")
	private Integer editReportStatus;

	public Integer getEditReportStatus() {
		return editReportStatus;
	}

	public void setEditReportStatus(Integer editReportStatus) {
		this.editReportStatus = editReportStatus;
	}

	public Integer getClientSelfReportId() {
		return clientSelfReportId;
	}

	public ClientMaster getClientMaster() {
		return clientMaster;
	}

	public String getComment() {
		return comment;
	}

	public String getFilePath() {
		return filePath;
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

	public String getReportIsActive() {
		return reportIsActive;
	}

	public void setClientSelfReportId(Integer clientSelfReportId) {
		this.clientSelfReportId = clientSelfReportId;
	}

	public void setClientMaster(ClientMaster clientMaster) {
		this.clientMaster = clientMaster;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
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

	public void setReportIsActive(String reportIsActive) {
		this.reportIsActive = reportIsActive;
	}
	
	

}
