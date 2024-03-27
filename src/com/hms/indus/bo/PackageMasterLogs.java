package com.hms.indus.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "ehr_package_master_logs")
public class PackageMasterLogs {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "package_log_id")
	private Integer packageLogId;
	
	@Column(name = "package_id")
	private Integer packageId;
	
	@Column(name = "package_desc")
	private String packageDescription;
	
	@Column(name = "package_short_desc")
	private String packageShortDescription;
	
	@Column(name = "ct_status")
	private String ctStatus;
	
	@Column(name = "is_active")
	private Character isActive;
	
	@Column(name = "add_at",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date addAt;
	
	@Column(name = "add_by",updatable=false)
	private String addBy;
	
	@Column(name = "mod_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modAt;
	
	@Column(name = "mod_by")
	private String modBy;
	
	@Column(name = "analysis_flag",columnDefinition="char(1) default 'N'")
	private Character analysisFlag='N';
	
	
	@Column(name = "status_code")
	private Integer statusCode;
	
	@Column(name = "error_code")
	private Integer errorCode;

	public Integer getPackageLogId() {
		return packageLogId;
	}

	public void setPackageLogId(Integer packageLogId) {
		this.packageLogId = packageLogId;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public String getPackageDescription() {
		return packageDescription;
	}

	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}

	public Character getIsActive() {
		return isActive;
	}

	public void setIsActive(Character isActive) {
		this.isActive = isActive;
	}

	public String getAddBy() {
		return addBy;
	}

	public void setAddBy(String addBy) {
		this.addBy = addBy;
	}

	public String getModBy() {
		return modBy;
	}

	public void setModBy(String modBy) {
		this.modBy = modBy;
	}

	public Date getAddAt() {
		return addAt;
	}

	public void setAddAt(Date addAt) {
		this.addAt = addAt;
	}

	public Date getModAt() {
		return modAt;
	}

	public void setModAt(Date modAt) {
		this.modAt = modAt;
	}

	public String getPackageShortDescription() {
		return packageShortDescription;
	}

	public void setPackageShortDescription(String packageShortDescription) {
		this.packageShortDescription = packageShortDescription;
	}

	public String getCtStatus() {
		return ctStatus;
	}

	public void setCtStatus(String ctStatus) {
		this.ctStatus = ctStatus;
	}

	public Character getAnalysisFlag() {
		return analysisFlag;
	}

	public void setAnalysisFlag(Character analysisFlag) {
		this.analysisFlag = analysisFlag;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	
	
}
