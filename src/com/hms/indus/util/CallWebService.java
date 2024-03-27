package com.hms.indus.util;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hms.indus.controller.ReminderController;
import com.hms.indus.controller.WebServiceController;

public class CallWebService implements ServletContextListener {

	protected static Timer timer = null;

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.err.println("CallWebService Listener initialized.");
		TimerTask vodTimer = new VodTimerTask();
		timer = new Timer();
		try {
			// every 60 minutes
			timer.schedule(vodTimer, (5 * 1000 * 60), (60 * 1000 * 60));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	class VodTimerTask extends TimerTask {
		@Override
		public void run() {
			try {
				System.out.println("VodTimerTask..." + new Date());
				//Uncomment code at the time of giving WAR file for Live and Test server and comment for us
				WebServiceController webServiceController = (ApplicationContextUtil
						.getApplicationContext())
						.getBean(WebServiceController.class);
				webServiceController.callWebServiceGetBeneficiary(null,null);
				webServiceController.callWebServiceGetCheckUpStatus();
				
				ReminderController reminderController = (ApplicationContextUtil
						.getApplicationContext())
						.getBean(ReminderController.class);
				reminderController.getAllReminder();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			timer.cancel();
			System.err.println("CallWebService Listener has been shutdown");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
