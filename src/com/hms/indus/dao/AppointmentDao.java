package com.hms.indus.dao;

import java.util.List;

import com.hms.indus.bo.AppointmentMaster;

public interface AppointmentDao {

	Integer saveAppointmentMaster(AppointmentMaster appointmentMaster);

	List<AppointmentMaster> getAppointmentList();
}
