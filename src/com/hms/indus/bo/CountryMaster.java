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
@Table(name = "country_master")
public class CountryMaster {

	@Id
	@GeneratedValue
	@Column(name = "country_id")
	private Integer countryId;
	
	@Column(name = "country_name")
	private String countryName;
	
	@OneToMany(mappedBy = "countryMaster")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<StateMaster> stateMaster;

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@JsonIgnore
	public List<StateMaster> getStateMaster() {
		return stateMaster;
	}

	public void setStateMaster(List<StateMaster> stateMaster) {
		this.stateMaster = stateMaster;
	}
	
	
}

