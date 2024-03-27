package com.hms.indus.bo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "ehr_center_master_logs")
public class CentreMasterLogs {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "center_log_id")
	private Integer centreLogId;
	
	@Column(name = "center_id")
	private Integer centreId;
	
	@Column(name = "center_name")
	private String centreName;	
	
	@Column(name = "isactive")
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
	

	public Integer getCentreId() {
		return centreId;
	}

	public void setCentreId(Integer centreId) {
		this.centreId = centreId;
	}

	public String getCentreName() {
		return centreName;
	}

	public void setCentreName(String centreName) {
		this.centreName = centreName;
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

	public Integer getCentreLogId() {
		return centreLogId;
	}

	public void setCentreLogId(Integer centreLogId) {
		this.centreLogId = centreLogId;
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
