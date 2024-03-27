package com.hms.indus.bo;

import java.util.ArrayList;
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

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "ehr_test_master_log")
public class TestMasterLog {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "test_id_log")
	private Integer testIdLog;
	
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
	
	@Column(name = "status_code")
	private Integer statusCode;
	
	@Column(name = "error_code")
	private Integer errorCode;

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
