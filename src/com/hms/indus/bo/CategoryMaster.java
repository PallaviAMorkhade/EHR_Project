package com.hms.indus.bo;

import java.io.Serializable;
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

@Entity
@Table(name = "ehr_category_master")
public class CategoryMaster implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private Integer categoryId;
	
	@Column(name = "category")
	private String category;
	
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
	
	@Column(name="is_active", columnDefinition = "BOOLEAN")
	private Boolean isActive;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "categoryMaster")
	private Set<SubCategoryMaster> subCategories = new HashSet<SubCategoryMaster>(0);

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Set<SubCategoryMaster> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Set<SubCategoryMaster> subCategories) {
		this.subCategories = subCategories;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
