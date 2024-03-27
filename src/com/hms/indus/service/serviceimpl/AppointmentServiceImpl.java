package com.hms.indus.service.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.AppointmentMaster;
import com.hms.indus.dao.AppointmentDao;
import com.hms.indus.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService{

	@Autowired
	AppointmentDao appointmentDao;
	
	@Override
	@Transactional
	public Integer saveAppointmentMaster(AppointmentMaster appointmentMaster) {
		return appointmentDao.saveAppointmentMaster(appointmentMaster);
	}

	@Override
	@Transactional
	public List<AppointmentMaster> getAppointmentList() {
		return appointmentDao.getAppointmentList();
	}

}
