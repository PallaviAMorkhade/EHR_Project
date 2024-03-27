package com.hms.indus.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Amol Saware
 */
public class TomcatAccessLogCleanerServlet implements ServletContextListener {

	protected static Timer timer = null;

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.err.println("Tomcat Log initialized.");
		TimerTask vodTimer = new VodTimerTask();
		timer = new Timer();
		try {
			// every 24 hours
			timer.schedule(vodTimer, (60 * 1000 * 60), (24 * 1000 * 60));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	class VodTimerTask extends TimerTask {
		@Override
		public void run() {
			try {
				System.out.println("tomcatAccessLogCleaner..." + new Date());
				TomcatAccessLogCleaner tomcatAccessLogCleaner = new TomcatAccessLogCleaner(new File("/var/log/tomcat7"),
						4);// Change tomcat log path
				tomcatAccessLogCleaner.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			timer.cancel();
			System.err.println("Tomcat Log Listener has been shutdown");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
