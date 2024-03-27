package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.FrequencyMaster;
import com.hms.indus.dao.FrequencyDao;
import com.hms.indus.service.FrequencyService;

@Service
@Transactional
public class FrequencyServiceImpl implements FrequencyService{
	
	@Autowired
	FrequencyDao frequencyDao;

	@Override
	public String saveFrequency(FrequencyMaster frequencyMaster) {
		return frequencyDao.saveFrequency(frequencyMaster);
	}

	@Override
	public String deleteFrequency(FrequencyMaster frequencyMaster) {
		return frequencyDao.deleteFrequency(frequencyMaster);
	}

	@Override
	public List<FrequencyMaster> listOfFrequencyMaster() {
		return frequencyDao.listOfFrequencyMaster();
	}

	@Override
	public FrequencyMaster getFrequencyByFrequencyId(Integer frequencyId) {
		return frequencyDao.getFrequencyByFrequencyId(frequencyId);
	}

	

}
