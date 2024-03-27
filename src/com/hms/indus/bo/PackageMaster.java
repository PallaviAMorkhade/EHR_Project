package com.hms.indus.bo;

import java.io.Serializable;
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
@Table(name = "ehr_package_master")
public class PackageMaster implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "packageMaster")
	@LazyCollection(value=LazyCollectionOption.FALSE)
	private Set<CheckUpMaster> checkUpMasterSet = new HashSet<CheckUpMaster>();
	
	@OneToOne(fetch = FetchType.LAZY, mappedBy = "packageMaster")
	@LazyCollection(value=LazyCollectionOption.FALSE)
	private ReportTypeMaster reportTypeMaster;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "packageMasterList")
	private List<TestMaster> testMasterList = new ArrayList<TestMaster>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "packageMaster")
	@LazyCollection(value=LazyCollectionOption.FALSE)
	private List<ClientUploadReport> clientUploadReportList = new ArrayList<ClientUploadReport>();

	@JsonIgnore
	public Set<CheckUpMaster> getCheckUpMasterSet() {
		return checkUpMasterSet;
	}

	@JsonIgnore
	public ReportTypeMaster getReportTypeMaster() {
		return reportTypeMaster;
	}

	public void setCheckUpMasterSet(Set<CheckUpMaster> checkUpMasterSet) {
		this.checkUpMasterSet = checkUpMasterSet;
	}

	public void setReportTypeMaster(ReportTypeMaster reportTypeMaster) {
		this.reportTypeMaster = reportTypeMaster;
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

	@JsonIgnore
	public List<TestMaster> getTestMasterList() {
		return testMasterList;
	}

	public void setTestMasterList(List<TestMaster> testMasterList) {
		this.testMasterList = testMasterList;
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
	
	
}
