package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.CityMaster;
import com.hms.indus.bo.CountryMaster;
import com.hms.indus.bo.StateMaster;
import com.hms.indus.dao.AddressDao;
import com.hms.indus.dao.CenterDao;
import com.hms.indus.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Autowired
	AddressDao addressDao;

	@Override
	@Transactional
	public List<CityMaster> getListOfCitys() {
		return addressDao.getListOfCitys();
	}

	@Override
	@Transactional
	public List<StateMaster> getListOfStates() {
		return addressDao.getListOfStates();
	}

	@Override
	@Transactional
	public List<CountryMaster> getListOfCountrys() {
		return addressDao.getListOfCountrys();
	}

	@Override
	@Transactional
	public List<StateMaster> getStateByCountryId(Integer countryId) {
		return addressDao.getStateByCountryId(countryId);
	}

	@Override
	@Transactional
	public List<CityMaster> getCityByStateId(Integer stateId) {
		return addressDao.getCityByStateId(stateId);
	}


}
