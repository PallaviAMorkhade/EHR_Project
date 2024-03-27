package com.hms.indus.dao;

import java.util.List;

import com.hms.indus.bo.CityMaster;
import com.hms.indus.bo.CountryMaster;
import com.hms.indus.bo.StateMaster;

public interface AddressDao {

	List<CityMaster> getListOfCitys();
	
	List<StateMaster> getListOfStates();
	
	List<CountryMaster> getListOfCountrys();

	List<StateMaster> getStateByCountryId(Integer countryId);
	
	List<CityMaster> getCityByStateId(Integer stateId);
}
