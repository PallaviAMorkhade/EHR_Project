package com.hms.indus.bo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "report_type_master")
public class ReportTypeMaster {
	
	@Id
	@GeneratedValue
	@Column(name = "report_type_id")
	private Integer reportTypeId;
	
	@Column(name = "report_type_desc")
	private String reportDescription;
	
	@Column(name = "isactive")
	private String reportIsActive;
	
	@Column(name = "add_at")
	private String addAt;
	
	@Column(name = "add_by")
	private String addBy;
	
	@Column(name = "mod_at")
	private String modAt;
	
	@Column(name = "report_type_mastercol")
	private String reportTypeMasterCol;
	
	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private PackageMaster packageMaster;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "reportTypeMaster")
	private Set<ClientUploadReport> clientUploadReportSet = new HashSet<ClientUploadReport>();

	public Integer getReportTypeId() {
		return reportTypeId;
	}

	public String getReportDescription() {
		return reportDescription;
	}

	public String getReportIsActive() {
		return reportIsActive;
	}

	public String getAddAt() {
		return addAt;
	}

	public String getAddBy() {
		return addBy;
	}

	public String getModAt() {
		return modAt;
	}

	public String getReportTypeMasterCol() {
		return reportTypeMasterCol;
	}

	public PackageMaster getPackageMaster() {
		return packageMaster;
	}

	public Set<ClientUploadReport> getClientUploadReportSet() {
		return clientUploadReportSet;
	}

	public void setReportTypeId(Integer reportTypeId) {
		this.reportTypeId = reportTypeId;
	}

	public void setReportDescription(String reportDescription) {
		this.reportDescription = reportDescription;
	}

	public void setReportIsActive(String reportIsActive) {
		this.reportIsActive = reportIsActive;
	}

	public void setAddAt(String addAt) {
		this.addAt = addAt;
	}

	public void setAddBy(String addBy) {
		this.addBy = addBy;
	}

	public void setModAt(String modAt) {
		this.modAt = modAt;
	}

	public void setReportTypeMasterCol(String reportTypeMasterCol) {
		this.reportTypeMasterCol = reportTypeMasterCol;
	}

	public void setPackageMaster(PackageMaster packageMaster) {
		this.packageMaster = packageMaster;
	}

	public void setClientUploadReportSet(
			Set<ClientUploadReport> clientUploadReportSet) {
		this.clientUploadReportSet = clientUploadReportSet;
	}

}
