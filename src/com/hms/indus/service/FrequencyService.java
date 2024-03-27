package com.hms.indus.service;

import java.util.List;

import com.hms.indus.bo.FrequencyMaster;

public interface FrequencyService {
	
	String saveFrequency(FrequencyMaster frequencyMaster);

	String deleteFrequency(FrequencyMaster frequencyMaster);
	
	List<FrequencyMaster> listOfFrequencyMaster();

	FrequencyMaster getFrequencyByFrequencyId(Integer frequencyId);

}
