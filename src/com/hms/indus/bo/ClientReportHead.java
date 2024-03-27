package com.hms.indus.bo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "client_report_head")
public class ClientReportHead {
	
	@Id
	@GeneratedValue
	@Column(name = "client_report_id")
	private Integer clientReportId;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "client_id")
	private ClientMaster clientMaster;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "center_id")
	private CentreMaster centreMaster;
	
	@Column(name = "client_report_date")
	private String clientReportDate;
	
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
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "client_checkup_id")
	private CheckUpMaster checkUpMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "clientReportHead",cascade=CascadeType.ALL)
	private List<ClientUploadReport> clientUploadReportSet = new ArrayList<ClientUploadReport>();

	public Integer getClientReportId() {
		return clientReportId;
	}

	public ClientMaster getClientMaster() {
		return clientMaster;
	}

	public CentreMaster getCentreMaster() {
		return centreMaster;
	}

	public String getClientReportDate() {
		return clientReportDate;
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

	public CheckUpMaster getCheckUpMaster() {
		return checkUpMaster;
	}

	public void setClientReportId(Integer clientReportId) {
		this.clientReportId = clientReportId;
	}

	public void setClientMaster(ClientMaster clientMaster) {
		this.clientMaster = clientMaster;
	}

	public void setCentreMaster(CentreMaster centreMaster) {
		this.centreMaster = centreMaster;
	}

	public void setClientReportDate(String clientReportDate) {
		this.clientReportDate = clientReportDate;
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

	public void setCheckUpMaster(CheckUpMaster checkUpMaster) {
		this.checkUpMaster = checkUpMaster;
	}

	public List<ClientUploadReport> getClientUploadReportSet() {
		return clientUploadReportSet;
	}

	public void setClientUploadReportSet(
			List<ClientUploadReport> clientUploadReportSet) {
		this.clientUploadReportSet = clientUploadReportSet;
	}

	

}
