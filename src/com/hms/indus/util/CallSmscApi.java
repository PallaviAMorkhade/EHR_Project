package com.hms.indus.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.hms.indus.bo.SMSRecord;
import com.hms.indus.controller.ClientController;

public class CallSmscApi {
	
	ClientController clientController = (ApplicationContextUtil.getApplicationContext())
			.getBean(ClientController.class);
	
	public CallSmscApi() {
	}

	public void sendSms(String mobilenumber,String message,SMSRecord smsRecord) {
		String postData = "";
		String retval = "";
		String User = "indusplus";
		String passwd = "smsplus";
		//mobilenumber = "+917875545421";//For testing
		String sid = "Indus";
		String mtype = "N";
		String DR = "Y";
		try {
			postData += "User=" + URLEncoder.encode(User, "UTF-8") + "&passwd="
					+ passwd + "&mobilenumber=" + mobilenumber + "&message="
					+ URLEncoder.encode(message, "UTF-8") + "&sid=" + sid
					+ "&mtype=" + mtype + "&DR=" + DR;
			URL url = new URL("http://smscountry.com/SMSCwebservice_Bulk.aspx");
			HttpURLConnection urlconnection = (HttpURLConnection) url
					.openConnection();
			urlconnection.setRequestMethod("POST");
			urlconnection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			urlconnection.setDoOutput(true);
			OutputStreamWriter out = new OutputStreamWriter(
					urlconnection.getOutputStream());
			out.write(postData);
			out.close();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					urlconnection.getInputStream()));
			String decodedString;
			while ((decodedString = in.readLine()) != null) {
				retval += decodedString;
			}
			in.close();
			
			//Save Msg log on database
			clientController.saveMsg(smsRecord);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.err.println("msg sent successfully.:"+retval);
	}
}