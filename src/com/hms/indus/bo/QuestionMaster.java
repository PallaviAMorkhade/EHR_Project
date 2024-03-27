package com.hms.indus.bo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "question_master")
public class QuestionMaster {
	
	@Id
	@GeneratedValue
	@Column(name = "question_id")
	private Integer questionId;
	
	@Column(name = "question")
	private String question;
	
	@Column(name = "question_type")
	private String questionType;
	
	@Column(name = "question_display")
	private String questionDisplay;
	
	@Column(name = "added_by")
	private String addedBy;
	
	@Column(name = "added_on")
	private String addedOn;
	
	@Column(name = "modify_by")
	private String modifyBy;
	
	@Column(name = "modify_on")
	private String modifyOn;
	
	@ManyToOne
	@JoinColumn(name = "hra_type_id")
	private HraTypeMaster hraTypeMaster;
	
	@Column(name="question_master_delete_flag")
	private Integer questionMasterDeleteFlag;
	
	@OneToMany(mappedBy = "questionMaster",cascade=CascadeType.ALL)
	@LazyCollection(value=LazyCollectionOption.FALSE)
	private List<OptionMaster> optionMasterSet;
	
	@Transient
	private QuestionReportDetail questionReportDetail;
	
	public QuestionReportDetail getQuestionReportDetail() {
		return questionReportDetail;
	}

	public void setQuestionReportDetail(QuestionReportDetail questionReportDetail) {
		this.questionReportDetail = questionReportDetail;
	}
	
	public String getQuestionDisplay() {
		return questionDisplay;
	}

	public void setQuestionDisplay(String questionDisplay) {
		this.questionDisplay = questionDisplay;
	}

	public Integer getQuestionMasterDeleteFlag() {
		return questionMasterDeleteFlag;
	}

	public void setQuestionMasterDeleteFlag(Integer questionMasterDeleteFlag) {
		this.questionMasterDeleteFlag = questionMasterDeleteFlag;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public String getQuestion() {
		return question;
	}

	public String getQuestionType() {
		return questionType;
	}

	public HraTypeMaster getHraTypeMaster() {
		return hraTypeMaster;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public void setHraTypeMaster(HraTypeMaster hraTypeMaster) {
		this.hraTypeMaster = hraTypeMaster;
	}

	public List<OptionMaster> getOptionMasterSet() {
		return optionMasterSet;
	}

	public void setOptionMasterSet(List<OptionMaster> optionMasterSet) {
		this.optionMasterSet = optionMasterSet;
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
