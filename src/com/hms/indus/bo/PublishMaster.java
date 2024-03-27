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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "ehr_publish_master")
public class PublishMaster implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "publish_id")
	private Integer publishId;
	
	@Column(name = "publish_date")
	@Temporal(TemporalType.TIMESTAMP)
	private Date publishDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "content_id")
	private ContentMaster contentMaster;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "detail_id")
	private PublishDetail publishDetail;
	
	@Column(name="is_active", columnDefinition = "BOOLEAN")
	private Boolean isActive;

	public Integer getPublishId() {
		return publishId;
	}

	public void setPublishId(Integer publishId) {
		this.publishId = publishId;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public ContentMaster getContentMaster() {
		return contentMaster;
	}

	public void setContentMaster(ContentMaster contentMaster) {
		this.contentMaster = contentMaster;
	}

	public PublishDetail getPublishDetail() {
		return publishDetail;
	}

	public void setPublishDetail(PublishDetail publishDetail) {
		this.publishDetail = publishDetail;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
