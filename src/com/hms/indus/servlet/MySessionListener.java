package com.hms.indus.servlet;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.hms.indus.controller.ClientController;
import com.hms.indus.util.ApplicationContextUtil;

public class MySessionListener implements HttpSessionListener {
	
	static Map<String, HttpSession> sessions = new HashMap<String, HttpSession>();
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		//System.out.println("Session created");
		HttpSession session = event.getSession();
        sessions.put(session.getId(), session);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession httpSession=event.getSession();
		//System.out.println("clientUserName:"+(String)httpSession.getAttribute("clientUserName"));
		if((Integer)httpSession.getAttribute("clientMasterId")!=null){
		ClientController clientController = (ApplicationContextUtil
				.getApplicationContext())
				.getBean(ClientController.class);
		clientController.removeClientLocked((Integer)httpSession.getAttribute("clientMasterId"));
		}
		sessions.remove(event.getSession().getId());
	}
	
	public static HttpSession find(String sessionId) {
		/*System.out.println(sessions.size()+" sessionId:"+sessionId);
		for (Map.Entry<String, HttpSession> entry : sessions.entrySet()) {
		    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}*/
        return sessions.get(sessionId);
    }
	
}
