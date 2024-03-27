package com.hms.indus.bo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "hra_type_master")
public class HraTypeMaster {
	
	@Id
	@GeneratedValue
	@Column(name = "hra_type_id")
	private Integer hraTypeId;
	
	@Column(name = "hra_type_name")
	private String hraTypeName;
	
	@Column(name = "hra_print_flag")
	private String hraPrintFlag;
	
	@Column(name = "end_user_flag")
	private String endUserFlag;
	
	@Column(name="hra_type_master_delete_flag")
	private Integer hraTypeMasterDeleteFlag;
	
	@Column(name = "added_by")
	private String addedBy;
	
	@Column(name = "added_on")
	private String addedOn;
	
	@Column(name = "modify_by")
	private String modifyBy;
	
	@Column(name = "modify_on")
	private String modifyOn;
	
	@OneToMany(mappedBy = "hraTypeMaster")
	@LazyCollection(value=LazyCollectionOption.FALSE)
	private List<QuestionMaster> questionMaster;
	
	public String getEndUserFlag() {
		return endUserFlag;
	}

	public void setEndUserFlag(String endUserFlag) {
		this.endUserFlag = endUserFlag;
	}

	public String getHraPrintFlag() {
		return hraPrintFlag;
	}

	public void setHraPrintFlag(String hraPrintFlag) {
		this.hraPrintFlag = hraPrintFlag;
	}

	public Integer getHraTypeMasterDeleteFlag() {
		return hraTypeMasterDeleteFlag;
	}

	public void setHraTypeMasterDeleteFlag(Integer hraTypeMasterDeleteFlag) {
		this.hraTypeMasterDeleteFlag = hraTypeMasterDeleteFlag;
	}

	public Integer getHraTypeId() {
		return hraTypeId;
	}

	public String getHraTypeName() {
		return hraTypeName;
	}

	public void setHraTypeId(Integer hraTypeId) {
		this.hraTypeId = hraTypeId;
	}

	public void setHraTypeName(String hraTypeName) {
		this.hraTypeName = hraTypeName;
	}

	@JsonIgnore
	public List<QuestionMaster> getQuestionMaster() {
		return questionMaster;
	}

	public void setQuestionMaster(List<QuestionMaster> questionMaster) {
		this.questionMaster = questionMaster;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public String getAddedOn() {
		return addedOn;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public String getModifyOn() {
		return modifyOn;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public void setAddedOn(String addedOn) {
		this.addedOn = addedOn;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public void setModifyOn(String modifyOn) {
		this.modifyOn = modifyOn;
	}
	

}
