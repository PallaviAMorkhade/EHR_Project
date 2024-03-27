package com.hms.indus.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "ehr_publish_details")
public class PublishDetail implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "detail_id")
	private Integer detailId;
	
	@Column(name = "days_interuption")
	private Integer daysInteruption;
	
	@Column(name = "repeat_content")
	private String repeatContent;
	
	@Column(name = "occurences")
	private Integer occurences;
	
	@Column(name = "start_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Column(name="is_active", columnDefinition = "BOOLEAN")
	private Boolean isActive;
	
	@Column(name = "added_by",updatable=false)
	private String addedBy;
	
	@Column(name = "added_on",updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date addedOn;
	
	@Column(name = "modify_by")
	private String modifyBy;
	
	@Column(name = "modify_on")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifyOn;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publishDetail",cascade=CascadeType.ALL)
	private Set<PublishMaster> publishMasters = new HashSet<PublishMaster>(0);

	public Integer getDetailId() {
		return detailId;
	}

	public void setDetailId(Integer detailId) {
		this.detailId = detailId;
	}

	public Integer getDaysInteruption() {
		return daysInteruption;
	}

	public void setDaysInteruption(Integer daysInteruption) {
		this.daysInteruption = daysInteruption;
	}

	public String getRepeatContent() {
		return repeatContent;
	}

	public void setRepeatContent(String repeatContent) {
		this.repeatContent = repeatContent;
	}

	public Integer getOccurences() {
		return occurences;
	}

	public void setOccurences(Integer occurences) {
		this.occurences = occurences;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyOn() {
		return modifyOn;
	}

	public void setModifyOn(Date modifyOn) {
		this.modifyOn = modifyOn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Set<PublishMaster> getPublishMasters() {
		return publishMasters;
	}

	public void setPublishMasters(Set<PublishMaster> publishMasters) {
		this.publishMasters = publishMasters;
	}
	
}
