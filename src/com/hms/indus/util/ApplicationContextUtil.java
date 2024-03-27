package com.hms.indus.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextUtil implements ApplicationContextAware {

	private static ApplicationContext contextApp;

	@Override
	public void setApplicationContext(ApplicationContext contextAppTemp)
			throws BeansException {
		contextApp = contextAppTemp;
	}

	public static ApplicationContext getApplicationContext() {
		return (contextApp);
	}

}
