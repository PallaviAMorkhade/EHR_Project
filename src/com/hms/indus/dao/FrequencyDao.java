package com.hms.indus.dao;

import java.util.List;

import com.hms.indus.bo.FrequencyMaster;

public interface FrequencyDao {
	
	String saveFrequency(FrequencyMaster frequencyMaster);

	String deleteFrequency(FrequencyMaster frequencyMaster);
	
	List<FrequencyMaster> listOfFrequencyMaster();

	FrequencyMaster getFrequencyByFrequencyId(Integer frequencyId);

}
