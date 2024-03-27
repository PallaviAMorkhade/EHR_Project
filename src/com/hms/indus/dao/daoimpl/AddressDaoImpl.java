package com.hms.indus.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.CityMaster;
import com.hms.indus.bo.CountryMaster;
import com.hms.indus.bo.StateMaster;
import com.hms.indus.dao.AddressDao;

@Repository
public class AddressDaoImpl implements AddressDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<CityMaster> getListOfCitys() {
		List<CityMaster> cityMasterList = new ArrayList<CityMaster>();
		SQLQuery query = null;
		try {
			query = sessionFactory.getCurrentSession().createSQLQuery(
					"SELECT city_id,city_name from city_master order by city_name");
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				CityMaster cityMaster = new CityMaster();
				if (row[0] != null) {
					cityMaster.setCityId(Integer.parseInt(row[0].toString()));
				}
				if (row[1] != null) {
					cityMaster.setCityName(row[1].toString());
				}
				cityMasterList.add(cityMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityMasterList;
	}

	@Override
	public List<StateMaster> getListOfStates() {
		List<StateMaster> stateMasterList = new ArrayList<StateMaster>();
		SQLQuery query = null;
		try {
			query = sessionFactory.getCurrentSession().createSQLQuery(
					"SELECT state_id,state_name from state_master order by state_name");
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				StateMaster stateMaster = new StateMaster();
				if (row[0] != null) {
					stateMaster.setStateId(Integer.parseInt(row[0].toString()));
				}
				if (row[1] != null) {
					stateMaster.setStateName(row[1].toString());
				}
				stateMasterList.add(stateMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stateMasterList;
	}

	@Override
	public List<CountryMaster> getListOfCountrys() {
		List<CountryMaster> countryMasterList = new ArrayList<CountryMaster>();
		SQLQuery query = null;
		try {
			query = sessionFactory.getCurrentSession().createSQLQuery(
					"SELECT country_id,country_name from country_master order by country_name");
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				CountryMaster countryMaster = new CountryMaster();
				if (row[0] != null) {
					countryMaster.setCountryId(Integer.parseInt(row[0]
							.toString()));
				}
				if (row[1] != null) {
					countryMaster.setCountryName(row[1].toString());
				}
				countryMasterList.add(countryMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryMasterList;
	}

	@Override
	public List<StateMaster> getStateByCountryId(Integer countryId) {
		List<StateMaster> stateMasterList = new ArrayList<StateMaster>();
		SQLQuery query = null;
		try {
			query = sessionFactory.getCurrentSession().createSQLQuery(
					"SELECT state_id,state_name from state_master where country_id = "+countryId+" order by state_name");
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				StateMaster stateMaster = new StateMaster();
				if (row[0] != null) {
					stateMaster.setStateId(Integer.parseInt(row[0].toString()));
				}
				if (row[1] != null) {
					stateMaster.setStateName(row[1].toString());
				}
				stateMasterList.add(stateMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stateMasterList;
	}

	@Override
	public List<CityMaster> getCityByStateId(Integer stateId) {
		List<CityMaster> cityMasterList = new ArrayList<CityMaster>();
		SQLQuery query = null;
		try {
			query = sessionFactory.getCurrentSession().createSQLQuery(
					"SELECT city_id,city_name from city_master where state_id = "+stateId+" order by city_name");
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				CityMaster cityMaster = new CityMaster();
				if (row[0] != null) {
					cityMaster.setCityId(Integer.parseInt(row[0].toString()));
				}
				if (row[1] != null) {
					cityMaster.setCityName(row[1].toString());
				}
				cityMasterList.add(cityMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cityMasterList;
	}

}
