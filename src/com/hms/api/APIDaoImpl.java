package com.hms.api;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.TaskMaster;
import com.hms.indus.bo.TaskSlave;
import com.hms.indus.bo.UserMaster;

@Repository
public class APIDaoImpl {

	@Autowired
	SessionFactory sessionFactory;

	@Transactional
	public JSONObject getClientByEHRId(String clientId) {
		JSONObject clientObject = null;
		try {
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
					"Select client_id,client_mob_no,client_email,client_first_name,client_last_name,user_type,client_dob,client_gender,client_photo,contact_flag from clients where client_id = " + clientId);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				clientObject = new JSONObject();
				clientObject.put("clientId", row[0].toString());
				if(row[1]!=null) {
					clientObject.put("mobileNumber", row[1].toString());
				}else {
					clientObject.put("mobileNumber", "");
				}
				
				if(row[2]!=null) {
					clientObject.put("emailId", row[2].toString());
				}else {
					clientObject.put("emailId", "");
				}
				clientObject.put("firstName", row[3].toString());
				clientObject.put("lastName", row[4].toString());
				clientObject.put("userType", row[5].toString());
				clientObject.put("dob", row[6].toString());
				clientObject.put("gender", row[7].toString());
				if(row[8]!=null) {
					clientObject.put("profilePicture", row[8].toString());
				}else {
					clientObject.put("profilePicture", "demo-profile-pic.jpg");
				}
				clientObject.put("contactFlag", row[9].toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientObject;
	}

	@Transactional
	public void saveOTP(JSONObject object) {
		String clientId = (String) object.get("clientId");
		String otp = (String) object.get("otp");
		String otpSendAt = (String) object.get("otpSendAt");
		try {
			String sql = "update clients set otp='" + otp + "',otp_send_at='" + otpSendAt + "' where client_id="
					+ clientId;
			SQLQuery updateOTP = sessionFactory.getCurrentSession().createSQLQuery(sql);
			updateOTP.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public JSONObject getOTPByEHRId(String clientId) {
		JSONObject clientObject = null;
		try {
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
					"Select client_id,client_mob_no,client_email,otp,otp_send_at,client_first_name,client_last_name,user_type,contact_flag from clients where client_id = "
							+ clientId);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				clientObject = new JSONObject();
				clientObject.put("clientId", row[0].toString());
				if(row[1]!=null) {
					clientObject.put("mobileNumber", row[1].toString());
				}else {
					clientObject.put("mobileNumber", "");
				}
				
				if(row[2]!=null) {
					clientObject.put("emailId", row[2].toString());
				}else {
					clientObject.put("emailId", "");
				}
				
				clientObject.put("otp", row[3].toString());
				clientObject.put("otpSendAt", row[4].toString());
				clientObject.put("firstName", row[5].toString());
				clientObject.put("lastName", row[6].toString());
				clientObject.put("userType", row[7].toString());
				clientObject.put("contactFlag", row[8].toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientObject;
	}

	@Transactional
	public JSONObject verifyDetails(JSONObject object) {
		JSONObject clientObject = null;
		String firstName = (String) object.get("firstName");
		String lastName = (String) object.get("lastName");
		String gender = (String) object.get("gender");
		String dob = (String) object.get("dob");
		String mobileNumber = (String) object.get("mobileNumber");
		String emailId = (String) object.get("emailId");
		try {
			SQLQuery query = null;
			String sql = "Select client_id,client_mob_no,client_email,client_first_name,client_last_name,user_type,contact_flag from clients ";
			query = sessionFactory.getCurrentSession()
					.createSQLQuery(sql	+ " where client_first_name = '" + firstName + "' AND client_last_name = '" + lastName
							+ "' AND " + "client_gender = '" + gender + "' AND client_dob = '" + dob + "'"
			+ " AND (client_mob_no = '" + mobileNumber + "' OR client_email='" + emailId + "')");
			List<Object[]> rows = query.list();
			if(rows.size()==0) {
				query = sessionFactory.getCurrentSession()
						.createSQLQuery(sql	+ " where client_first_name = '" + firstName + "' AND client_last_name = '" + lastName
								+ "' AND " + "client_gender = '" + gender + "' AND client_dob = '" + dob + "'");
			}
			
			rows = query.list();
			if(rows.size() > 1) {
				clientObject = new JSONObject();
				clientObject.put("duplicateFlag", "Y");
			}else {
			for (Object[] row : rows) {
				clientObject = new JSONObject();
				clientObject.put("clientId", row[0].toString());
				
				if(row[1]!=null) {
					clientObject.put("mobileNumber", row[1].toString());
					clientObject.put("mobileStatus", "");
					if(mobileNumber!=null && !mobileNumber.equals("") && !mobileNumber.equals(row[1].toString())) {
						clientObject.put("mobileStatus", "Please Verify Your Mobile Number");
					}
				}else {
					clientObject.put("mobileNumber", "");
					clientObject.put("mobileStatus", "Mobile Number is not registered");
				}
				
				if(row[2]!=null) {
					clientObject.put("emailId", row[2].toString());
					clientObject.put("emailIdStatus", "");
					if(emailId!=null && !emailId.equalsIgnoreCase("") && !emailId.equalsIgnoreCase(row[2].toString())) {
						clientObject.put("emailIdStatus", "Please Verify Your emailId");
					}
				}else {
					clientObject.put("emailId", "");
					clientObject.put("emailIdStatus", "emailId is not registered");
				}
				clientObject.put("firstName", row[3].toString());
				clientObject.put("lastName", row[4].toString());
				if(row[5] != null) {
					clientObject.put("userType", row[5].toString());
				}else {
					clientObject.put("userType","C");
				}
				clientObject.put("contactFlag", row[6].toString());
			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientObject;
	}
	
	@Transactional
	public void saveHRADetails(JSONArray answerArray) {
		for(int i=0;i<answerArray.size();i++){
			//System.out.println(jsonArray.size()+"**json object********"+jsonArray.get(i));
			JSONObject object=(JSONObject) answerArray.get(i);
			
			String modifyBy=(String) object.get("modifyBy");
			String addedOn=(String) object.get("addedOn");
			String modifyOn=(String) object.get("modifyOn");
			String addedBy=(String) object.get("addedBy");
			Long clientId=(Long) object.get("clientId");
			Long questionId=(Long) object.get("questionId");
			//String optionId=(String) object.get("optionId");
			String text=(String) object.get("text");
			Integer checkUpId=(Integer) object.get("checkUpId");
			String isDeleted = "N";
			if(text.equals("")) {
				isDeleted = "Y";
			}
			String sql = "INSERT INTO client_hra_details(added_on, added_by,client_id,question_id,text_answer,checkup_id) " +
					"values('"+addedOn+"','"+addedBy+"','"+clientId+"',"+questionId+",'"+text+"','"+checkUpId+"')" +
					" ON DUPLICATE KEY UPDATE is_deleted='"+isDeleted+"', text_answer='"+text+"', modify_by='"+modifyBy+"',modify_on='"+modifyOn+"'";
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
			query.executeUpdate();
			
			String clientLog = "INSERT INTO ehr_client_hra_log (modify_by, modify_on,client_id,question_id,text_answer,checkup_id) " +
					"values('"+modifyBy+"','"+modifyOn+"','"+clientId+"',"+questionId+",'"+text+"','"+checkUpId+"')";
			SQLQuery clientLogInsert = sessionFactory.getCurrentSession().createSQLQuery(clientLog);
			clientLogInsert.executeUpdate();
			
		}
	}
	
	@Transactional
	public String saveSelfClientReport(JSONObject object) {
		try {
			String addedBy=(String) object.get("addedBy");
			String addedOn=(String) object.get("addedOn");
			String comment=(String) object.get("comment");
			String reportFilePath=(String) object.get("reportFilePath");
			Integer clientId=(Integer) object.get("clientId");
			String sql = "INSERT INTO ehr_client_self_upload_report (client_id,comment,report_filepath,added_on, added_by,report_isactive,edit_report_status) " +
					"values('"+clientId+"','"+comment+"','"+reportFilePath+"','"+addedOn+"','"+addedBy+"','Y','0')";
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Reports Saved Ssuccessfully";
	}
	
	@Transactional
	public String uploadTestomonial(JSONObject object) {
		try {
			String addedBy=(String) object.get("addedBy");
			String addedOn=(String) object.get("addedOn");
			String reportFilePath=(String) object.get("reportFilePath");
			Integer clientId=(Integer) object.get("clientId");
			String sql = "INSERT INTO ehr_client_testomonial (client_id,report_filepath,added_on, added_by) " +
					"values('"+clientId+"','"+reportFilePath+"','"+addedOn+"','"+addedBy+"')";
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Testomonial Uploaded Ssuccessfully";
	}
	
	@Transactional
	public JSONArray getReportsByEHRId(String clientId) {
		JSONArray reportList = new JSONArray();
		try {
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
					"SELECT" + 
					"    report_desc," + 
					"    report_filepath," + 
					"    head.added_by," + 
					"    head.added_on," + 
					"    client_report_line_id," + 
					"    test_id," + 
					"    package_id," + 
					"    head.center_id," + 
					"    center_name," + 
					"    edit_report_status," + 
					"    report_date," +
					" client_checkup_id " +
					" FROM" + 
					"    client_report_line line" + 
					"        INNER JOIN" + 
					"    client_report_head head ON line.client_report_id = head.client_report_id" + 
					"        LEFT JOIN" + 
					"    ehr_center_master ON head.center_id = ehr_center_master.center_id" + 
					" WHERE" + 
					"    client_id = "+clientId+ "" + 
					"        AND line.report_isactive = 'Y'");
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				JSONObject reportObject = new JSONObject();
				reportObject.put("reportDesc", row[0].toString());
				reportObject.put("reportPath", row[1].toString());
				reportObject.put("addedby", row[2].toString());
				reportObject.put("addedOn", row[3].toString());
				reportObject.put("clientReportLineId", row[4].toString());
				if(row[5] != null) {
					reportObject.put("testId", row[5].toString());
				}else {
					reportObject.put("testId", "-");
				}
				if(row[6] != null) {
					reportObject.put("packageId", row[6].toString());
				}else {
					reportObject.put("packageId", "-");
				}
				if(row[7] != null) {
					reportObject.put("centerId", row[7].toString());
				}else {
					reportObject.put("centerId", "-");
				}
				if(row[8] != null) {
					reportObject.put("centerName", row[8].toString());
				}else {
					reportObject.put("centerName", "-");
				}
				if(row[10] != null) {
					reportObject.put("reportDate", row[10].toString());
				}else {
					reportObject.put("reportDate","-");
				}
				reportObject.put("visitId", row[11].toString());
				
				SQLQuery sql = sessionFactory.getCurrentSession().createSQLQuery("SELECT client_report_verify_id FROM clientreportverify_clientreportline where client_report_line_id = "+row[4].toString()+" order by client_report_verify_id desc limit 1");
				sql.addScalar("client_report_verify_id", StringType.INSTANCE);
				String clientReportVerifyId = (String)sql.uniqueResult();
				
				SQLQuery sql1 = sessionFactory.getCurrentSession().createSQLQuery("SELECT count(*) as count FROM report_verification where client_report_verify_id = "+clientReportVerifyId+" and reject_id is NULL");
				sql1.addScalar("count", IntegerType.INSTANCE);
				Integer count = (Integer)sql1.uniqueResult();
				if(count > 0) {
					reportList.add(reportObject);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reportList;
	}
	
	@Transactional
	public JSONArray saveClientReports(JSONArray jsonArray) {
		JSONArray data = new JSONArray();
		for(int i=0;i<jsonArray.size();i++){
			java.util.Date date = new java.util.Date();
			java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			JSONObject object=(JSONObject) jsonArray.get(i);
			String modifyBy=(String) object.get("modifyBy");
			String modifyOn=(String) object.get("modifyOn");
			String addedBy=(String) object.get("addedBy");
			String addedOn=simpleDateFormat.format(date);
			Long clientId=(Long) object.get("ehrId");
			Long visitId=(Long) object.get("visitId");
			Long packageId=(Long) object.get("packageId");
			Long testId=(Long) object.get("testId");
			
			if(packageId==0) {
				packageId = null;
			}
			if(testId==0) {
				testId = null;
			}
			Long centerId=(Long) object.get("centerId");
			String fileName=(String) object.get("fileName");
			String reportDesc=(String) object.get("reportDescription");
			
			String reportHeadSql = "INSERT INTO client_report_head(added_on, added_by,client_id,center_id,client_checkup_id,report_isactive) " +
					"values('"+addedOn+"','"+addedBy+"','"+clientId+"',"+centerId+","+visitId+",'Y')";
			SQLQuery reportHeadQuery = sessionFactory.getCurrentSession().createSQLQuery(reportHeadSql);
			reportHeadQuery.executeUpdate();
			
			BigInteger clientReportHeadId = (BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult();
			String reportLineSql = "INSERT INTO client_report_line(client_report_id, report_desc,report_filepath,report_isactive,package_id,test_id) " +
					"values('"+clientReportHeadId.intValue()+"','"+reportDesc+"','"+fileName+"','Y',"+packageId+","+testId+")";
			SQLQuery reportLineQuery = sessionFactory.getCurrentSession().createSQLQuery(reportLineSql);
			reportLineQuery.executeUpdate();
			
			BigInteger clientReportId = (BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult();
			JSONObject response = new JSONObject();
			response.put("ehrId", clientId);
			response.put("visitId", visitId);
			response.put("reportId", clientReportId.intValue());
			data.add(response);
		}
		return data;
	}

	@Transactional
	public void saveDoctorAnalysis(JSONArray clients) {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String changedOn=simpleDateFormat.format(date);
		Integer statusId = 11;
		
		for (int i = 0; i < clients.size(); i++) {
			JSONObject client = (JSONObject) clients.get(i);
			Integer clientId = Integer.parseInt(client.get("EHRNo").toString());
			Integer checkupId = Integer.parseInt(client.get("visitId").toString());
			Integer doctorId = Integer.parseInt(client.get("CounsellorId").toString());
			
			SQLQuery userQuery = sessionFactory.getCurrentSession().createSQLQuery("SELECT user_id as userId FROM ehr_users where doctor_id="+doctorId);
			userQuery.addScalar("userId", IntegerType.INSTANCE);
			Integer userId = (Integer)userQuery.uniqueResult();
			
			TaskMaster taskMaster = new TaskMaster();
			UserMaster assignToMaster = new UserMaster();
			assignToMaster.setUserId(userId);
			taskMaster.setAssignedTo(assignToMaster);
			taskMaster.setAssignedBy(assignToMaster);
			taskMaster.setWorkStatusId(statusId);
			taskMaster.setAssignedOn(new Date());
			taskMaster.setStatus("open");
			
			List<TaskSlave> taskSlaves = new ArrayList<TaskSlave>();
			TaskSlave taskSlave = new TaskSlave();
			taskSlave.setClientId(clientId);
			taskSlave.setCheckUpId(checkupId);
			taskSlave.setTaskMaster(taskMaster);
			taskSlaves.add(taskSlave);
			taskMaster.setTaskSlaves(taskSlaves);
			try {
				sessionFactory.getCurrentSession().saveOrUpdate(taskMaster);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				String sql = "INSERT INTO ehr_visit_status(client_id,checkup_id,work_status_id,changed_on,changed_by) "
						+ "values('" + clientId + "'," + checkupId + ",'" + statusId + "','" + changedOn + "',0)" + " ON DUPLICATE KEY UPDATE work_status_id=" + statusId + ",changed_on='"
						+ changedOn + "'" + ",changed_by=0";
				SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
				query.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
