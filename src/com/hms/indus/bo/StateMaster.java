package com.hms.indus.bo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "state_master")
public class StateMaster {

	@Id
	@GeneratedValue
	@Column(name = "state_id")
	private Integer stateId;
	
	@Column(name = "state_name")
	private String stateName;
	
	@ManyToOne
	@JoinColumn(name = "country_id")
	private CountryMaster countryMaster;
	
	@OneToMany(mappedBy = "stateMaster")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<CityMaster> cityMaster;

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public CountryMaster getCountryMaster() {
		return countryMaster;
	}

	public void setCountryMaster(CountryMaster countryMaster) {
		this.countryMaster = countryMaster;
	}

	@JsonIgnore
	public List<CityMaster> getCityMaster() {
		return cityMaster;
	}

	public void setCityMaster(List<CityMaster> cityMaster) {
		this.cityMaster = cityMaster;
	}
	
}
