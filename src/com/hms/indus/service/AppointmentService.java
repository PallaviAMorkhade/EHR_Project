package com.hms.indus.service;

import java.util.List;

import com.hms.indus.bo.AppointmentMaster;


public interface AppointmentService {
	
	Integer saveAppointmentMaster(AppointmentMaster appointmentMaster);

	List<AppointmentMaster> getAppointmentList();

}
