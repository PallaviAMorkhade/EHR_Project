package com.hms.indus.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "ehr_test_master")
public class TestMaster implements Comparator<TestMaster>,Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "test_id")
	private Integer testId;
	
	@Column(name = "test_desc")
	private String testDescription;
	
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
	
	@Transient
	@Column(name = "sequence")
	private Integer sequence;
	
	@Transient
	@Column(name = "ehr_test_package_id")
	private Integer ehrTestPackageId;
	
	
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ehr_test_package",joinColumns = { 
			@JoinColumn(name = "package_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "test_id", 
					nullable = false, updatable = false) })
	@LazyCollection(value=LazyCollectionOption.FALSE)
	private List<PackageMaster> packageMasterList = new ArrayList<PackageMaster>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "testMaster")
	@LazyCollection(value=LazyCollectionOption.FALSE)
	private List<ClientUploadReport> clientUploadReportList = new ArrayList<ClientUploadReport>();
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "testMaster")
	@LazyCollection(value=LazyCollectionOption.FALSE)
	private List<ParameterMaster> parameterMasterList = new ArrayList<ParameterMaster>();
	
	@JsonIgnore
	public List<ParameterMaster> getParameterMasterList() {
		return parameterMasterList;
	}

	public void setParameterMasterList(List<ParameterMaster> parameterMasterList) {
		this.parameterMasterList = parameterMasterList;
	}

	@JsonIgnore
	public List<ClientUploadReport> getClientUploadReportList() {
		return clientUploadReportList;
	}

	public void setClientUploadReportList(
			List<ClientUploadReport> clientUploadReportList) {
		this.clientUploadReportList = clientUploadReportList;
	}

	public Integer getTestId() {
		return testId;
	}

	public String getTestDescription() {
		return testDescription;
	}

	public Character getIsActive() {
		return isActive;
	}

	public String getAddBy() {
		return addBy;
	}

	public String getModBy() {
		return modBy;
	}

	@JsonIgnore
	public List<PackageMaster> getPackageMasterList() {
		return packageMasterList;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}

	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}

	public void setIsActive(Character isActive) {
		this.isActive = isActive;
	}

	public void setAddBy(String addBy) {
		this.addBy = addBy;
	}

	public void setModBy(String modBy) {
		this.modBy = modBy;
	}

	public void setPackageMasterList(List<PackageMaster> packageMasterList) {
		this.packageMasterList = packageMasterList;
	}

	@Override
	public int compare(TestMaster o1, TestMaster o2) {
		return o1.getTestDescription().compareToIgnoreCase(o2.getTestDescription());
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
	
	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getEhrTestPackageId() {
		return ehrTestPackageId;
	}

	public void setEhrTestPackageId(Integer ehrTestPackageId) {
		this.ehrTestPackageId = ehrTestPackageId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
        int result = 1;
        result = prime * result + testId; 
        return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TestMaster) {
			TestMaster testMaster = (TestMaster) obj;
            return (testMaster.getTestId().equals(this.testId));
        } else {
            return false;
        }
	}
	
}
