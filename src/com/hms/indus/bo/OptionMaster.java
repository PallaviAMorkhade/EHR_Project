package com.hms.indus.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "option_master")
public class OptionMaster {
	
	@Id
	@GeneratedValue
	@Column(name = "option_id")
	private Integer optionId;
	
	@Column(name = "option_name")
	private String option;
	
	@Column(name="option_master_delete_flag")
	private Integer optionMasterDeleteFlag;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "question_id")
	private QuestionMaster questionMaster;
	
	@Transient
	private QuestionReportDetail questionReportDetail;
	
	public QuestionReportDetail getQuestionReportDetail() {
		return questionReportDetail;
	}

	public void setQuestionReportDetail(QuestionReportDetail questionReportDetail) {
		this.questionReportDetail = questionReportDetail;
	}

	public Integer getOptionMasterDeleteFlag() {
		return optionMasterDeleteFlag;
	}

	public void setOptionMasterDeleteFlag(Integer optionMasterDeleteFlag) {
		this.optionMasterDeleteFlag = optionMasterDeleteFlag;
	}

	public Integer getOptionId() {
		return optionId;
	}

	public String getOption() {
		return option;
	}

	@JsonIgnore
	public QuestionMaster getQuestionMaster() {
		return questionMaster;
	}

	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public void setQuestionMaster(QuestionMaster questionMaster) {
		this.questionMaster = questionMaster;
	}
}
