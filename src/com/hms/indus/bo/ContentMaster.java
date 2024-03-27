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
@Table(name = "ehr_content_master")
public class ContentMaster implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "content_id")
	private Integer contentId;
	
	@Column(name = "content_title")
	private String contentTitle;
	
	@Column(name = "content_description")
	private String contentDescription;
	
	@Column(name = "content_tags")
	private String contentTags;
	
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contentMaster",cascade=CascadeType.ALL)
	@OrderBy("fileId DESC")
	private Set<ContentFile> contentFiles = new HashSet<ContentFile>(0);
	
	@Column(name = "categories")
	private String categories;
	
	@Column(name = "sub_categories")
	private String subCategories;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "contentMaster")
	@OrderBy("publishDate DESC")
	private Set<PublishMaster> publishMasters = new HashSet<PublishMaster>(0);
	
	@Column(name = "tag_status")
	private String tagStatus;

	public String getTagStatus() {
		return tagStatus;
	}

	public void setTagStatus(String tagStatus) {
		this.tagStatus = tagStatus;
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

	public String getCategories() {
		return categories;
	}

	public void setCategories(String categories) {
		this.categories = categories;
	}

	public String getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(String subCategories) {
		this.subCategories = subCategories;
	}

	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public String getContentTitle() {
		return contentTitle;
	}

	public String getContentDescription() {
		return contentDescription;
	}

	public void setContentDescription(String contentDescription) {
		this.contentDescription = contentDescription;
	}

	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}

	public String getContentTags() {
		return contentTags;
	}

	public void setContentTags(String contentTags) {
		this.contentTags = contentTags;
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

	public Set<ContentFile> getContentFiles() {
		return contentFiles;
	}

	public void setContentFiles(Set<ContentFile> contentFiles) {
		this.contentFiles = contentFiles;
	}
	
}
