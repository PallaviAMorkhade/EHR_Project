package com.hms.indus.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ehr_parameter_master")
public class ParameterMaster {
	
	@Id
	@GeneratedValue
	@Column(name = "parameter_id")
	private Integer parameterId;
	
	@Column(name = "parameter_name")
	private String parameterName;
	
	@Column(name = "is_active")
	private Character isActive;
	
	@Column(name = "add_at")
	private String addAt;
	
	@Column(name = "add_by")
	private String addBy;
	
	@Column(name = "mod_at")
	private String modAt;
	
	@Column(name = "mod_by")
	private String modBy;
	
	@Column(name = "normal_value")
	private String normalValue;
	
	@Column(name = "critical_low_value")
	private Double criticalLowValue;
	
	@Column(name = "critical_high_value")
	private Double criticalHighValue;
	
	@ManyToOne
	@JoinColumn(name = "test_id")
	private TestMaster testMaster;

	public Integer getParameterId() {
		return parameterId;
	}

	public String getParameterName() {
		return parameterName;
	}

	public String getNormalValue() {
		return normalValue;
	}

	public void setNormalValue(String normalValue) {
		this.normalValue = normalValue;
	}

	public Double getCriticalLowValue() {
		return criticalLowValue;
	}

	public void setCriticalLowValue(Double criticalLowValue) {
		this.criticalLowValue = criticalLowValue;
	}

	public Double getCriticalHighValue() {
		return criticalHighValue;
	}

	public void setCriticalHighValue(Double criticalHighValue) {
		this.criticalHighValue = criticalHighValue;
	}

	public Character getIsActive() {
		return isActive;
	}

	public String getAddAt() {
		return addAt;
	}

	public String getAddBy() {
		return addBy;
	}

	public String getModAt() {
		return modAt;
	}

	public String getModBy() {
		return modBy;
	}

	public TestMaster getTestMaster() {
		return testMaster;
	}

	public void setParameterId(Integer parameterId) {
		this.parameterId = parameterId;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public void setIsActive(Character isActive) {
		this.isActive = isActive;
	}

	public void setAddAt(String addAt) {
		this.addAt = addAt;
	}

	public void setAddBy(String addBy) {
		this.addBy = addBy;
	}

	public void setModAt(String modAt) {
		this.modAt = modAt;
	}

	public void setModBy(String modBy) {
		this.modBy = modBy;
	}

	public void setTestMaster(TestMaster testMaster) {
		this.testMaster = testMaster;
	}
	

}
