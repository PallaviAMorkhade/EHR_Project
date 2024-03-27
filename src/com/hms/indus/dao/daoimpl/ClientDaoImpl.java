package com.hms.indus.dao.daoimpl;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.CentreMaster;
import com.hms.indus.bo.CentreMasterLogs;
import com.hms.indus.bo.CheckUpMaster;
import com.hms.indus.bo.CityMaster;
import com.hms.indus.bo.ClientMaster;
import com.hms.indus.bo.ClientReportHead;
import com.hms.indus.bo.ClientSelfUploadReport;
import com.hms.indus.bo.ClientUploadReport;
import com.hms.indus.bo.CountryMaster;
import com.hms.indus.bo.DataEntryVerification;
import com.hms.indus.bo.EmailRecord;
import com.hms.indus.bo.GetBeneficiaryApiLog;
import com.hms.indus.bo.LogSaveClientApi;
import com.hms.indus.bo.PackageMaster;
import com.hms.indus.bo.PackageMasterLogs;
import com.hms.indus.bo.ParameterReportDetail;
import com.hms.indus.bo.RejectMaster;
import com.hms.indus.bo.ReportVerification;
import com.hms.indus.bo.SMSRecord;
import com.hms.indus.bo.StateMaster;
import com.hms.indus.bo.TaskMaster;
import com.hms.indus.bo.TestMaster;
import com.hms.indus.dao.ClientDao;
import com.hms.indus.util.DateConvertUtil;

@Repository
public class ClientDaoImpl implements ClientDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public ClientMaster getResetPasswordMobNoByUsername(String userName) {
		String mobNo = "";
		String emailId = "";
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(ClientMaster.class);
			if (userName != null && userName != "") {
				criteria.add(Restrictions.eq("userId", userName));
			}

			ProjectionList proList = Projections.projectionList();
			proList.add(Projections.property("mobNo"));
			proList.add(Projections.property("emailId"));
			criteria.setProjection(proList);

			List<Object[]> result = criteria.list();
			for (Object[] master : result) {
				if (master[0] != null)
					mobNo = master[0].toString();

				if (master[1] != null)
					emailId = master[1].toString();
			}

			// for indus user login
			/*
			 * if(result.size()==0){ Criteria criteria1 =
			 * sessionFactory.getCurrentSession()
			 * .createCriteria(UserMaster.class); if (userName != null &&
			 * userName!="") { criteria1.add(Restrictions.eq("userName",
			 * userName)); } ProjectionList proList1 =
			 * Projections.projectionList();
			 * proList1.add(Projections.property("mobNo"));
			 * proList1.add(Projections.property("emailId"));
			 * criteria1.setProjection(proList1);
			 * 
			 * List<Object[]> result1 = criteria1.list(); for(Object[]
			 * master:result1) { if(master[0]!=null) mobNo=master[0].toString();
			 * 
			 * if(master[1]!=null) emailId=master[1].toString(); } }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		ClientMaster clientMaster = new ClientMaster();
		clientMaster.setMobNo(mobNo);
		clientMaster.setEmailId(emailId);
		return clientMaster;
	}

	@Override
	public ClientMaster getClientByUserId(Integer clientId,
			HttpServletRequest request) {
		ClientMaster clientMaster = new ClientMaster();
		try {
			if (clientId != null) {
				/*Criteria criteria = sessionFactory.getCurrentSession()
						.createCriteria(ClientMaster.class);
				criteria.add(Restrictions.eq("userId", userId));
				clientMaster = (ClientMaster) criteria.uniqueResult();*/
				
				SQLQuery query = null;
				try {
					query = sessionFactory
							.getCurrentSession()
							.createSQLQuery("select clients.client_id,clients.add_at,clients.add_by,clients.client_address1,clients.client_address2,clients.client_address3,clients.client_blood_group,"
										+"clients.center_id,clients.client_city,clients.age,clients.client_dob,clients.client_full_name,clients.client_height,clients.client_locked,clients.client_locked_by,"
										+"clients.client_weight,clients.count,clients.client_country,clients.client_email,clients.client_first_name,clients.undertaking_form_path,clients.client_gender,"
										+"clients.height_in_cm,clients.client_isactive,clients.is_agree_t_c,clients.is_scanned,clients.is_undertaking,clients.is_visited,clients.client_landline_no,"
										+"clients.client_last_name,clients.member_id,clients.client_middle_name,clients.client_mob_no,clients.mod_at,clients.mod_by,clients.mothers_miden_name,clients.nominee_age,"
										+"clients.nominee_dob,clients.nominee_first_name,clients.nominee_last_name,clients.nominee_middle_name,clients.nominee_relation,clients.nominee_name,clients.package_id,"
										+"clients.client_password,clients.client_photo,clients.client_pincode,clients.remark,clients.self_beneficiary,clients.client_state,clients.client_user_id,clients.user_session_id,"
										+"clients.weight_in_kg,city.city_id,city.city_name,city.state_id as city_state_id,country.country_id,country.country_name,state.state_id,state.country_id as state_country_id,state.state_name,clients.mb_relation,clients.contact_flag from clients"
										+" left outer join city_master city on clients.client_city=city.city_id left outer join country_master country on clients.client_country=country.country_id "
										+" left outer join state_master state on clients.client_state=state.state_id where clients.client_id ='"+clientId+"'");
					List<Object[]> rows = query.list();
					for (Object[] row : rows) {
						if (row[0] != null) {
							clientMaster.setClientId(Integer.parseInt(row[0].toString()));
						}
						if (row[1] != null) {
							clientMaster.setAddAt(row[1].toString());
						}
						if (row[2] != null) {
							clientMaster.setAddBy(row[2].toString());
						}
						if (row[3] != null) {
							clientMaster.setAddressLine1(row[3].toString());
						}
						if (row[4] != null) {
							clientMaster.setAddressLine2(row[4].toString());
						}
						if (row[5] != null) {
							clientMaster.setAddressLine3(row[5].toString());
						}
						if (row[6] != null) {
							clientMaster.setBloodGroup(row[6].toString());
						}
						if (row[7] != null) {
							CentreMaster centreMaster = new CentreMaster();
							centreMaster.setCentreId(Integer.parseInt(row[7].toString()));
							clientMaster.setCentreMaster(centreMaster);
						}
						if (row[9] != null) {
							clientMaster.setClientAge(Integer.parseInt(row[9].toString()));
						}
						if (row[10] != null) {
							clientMaster.setClientDOB(row[10].toString());
						}
						if (row[11] != null) {
							clientMaster.setClientFullName(row[11].toString());
						}
						if (row[12] != null) {
							clientMaster.setClientHeight(row[12].toString());
						}
						if (row[15] != null) {
							clientMaster.setClientWeight(row[15].toString());
						}
						if (row[16] != null) {
							clientMaster.setCount(Integer.parseInt(row[16].toString()));
						}
						if (row[18] != null) {
							clientMaster.setEmailId(row[18].toString());
						}
						if (row[19] != null) {
							clientMaster.setFirstName(row[19].toString());
						}
						if (row[21] != null) {
							clientMaster.setGender(row[21].toString());
						}
						if (row[22] != null) {
							clientMaster.setHeightInCm(row[22].toString());
						}
						if (row[23] != null) {
							clientMaster.setIsActive(row[23].toString());
						}
						if (row[28] != null) {
							clientMaster.setLandlineNo(row[28].toString());
						}
						if (row[29] != null) {
							clientMaster.setLastName(row[29].toString());
						}
						if (row[30] != null) {
							clientMaster.setMemberId(row[30].toString());
						}
						if (row[31] != null) {
							clientMaster.setMiddleName(row[31].toString());
						}
						if (row[32] != null) {
							clientMaster.setMobNo(row[32].toString());
						}
						if (row[35] != null) {
							clientMaster.setMothersMidenName(row[35].toString());
						}
						if (row[36] != null) {
							clientMaster.setNomineeAge(Integer.parseInt(row[36].toString()));
						}
						if (row[37] != null) {
							clientMaster.setNomineeDOB(row[37].toString());
						}
						if (row[38] != null) {
							clientMaster.setNomineeFirstName(row[38].toString());
						}
						if (row[39] != null) {
							clientMaster.setNomineeLastName(row[39].toString());
						}
						if (row[40] != null) {
							clientMaster.setNomineeMiddleName(row[40].toString());
						}
						if (row[41] != null) {
							clientMaster.setNomineeRelation(row[41].toString());
						}
						if (row[43] != null) {
							PackageMaster packageMaster = new PackageMaster();
							packageMaster.setPackageId(Integer.parseInt(row[43].toString()));
							clientMaster.setPackageMaster(packageMaster);
						}
						if (row[44] != null) {
							clientMaster.setPassword(row[44].toString());
						}
						if (row[45] != null) {
							clientMaster.setPhotoUrl(row[45].toString());
						}
						if (row[46] != null) {
							clientMaster.setPinCode(Integer.parseInt(row[46].toString()));
						}
						if (row[50] != null) {
							clientMaster.setUserId(row[50].toString());
						}
						if (row[52] != null) {
							clientMaster.setWeightInKg(row[52].toString());
						}
						if (row[53] != null) {
							CityMaster cityMaster = new CityMaster();
							cityMaster.setCityId(Integer.parseInt(row[53].toString()));
							cityMaster.setCityName(row[54].toString());
							StateMaster stateMaster = new StateMaster();
							stateMaster.setStateId(Integer.parseInt(row[55].toString()));
							clientMaster.setCityMaster(cityMaster);
						}
						if (row[56] != null) {
							CountryMaster countryMaster = new CountryMaster();
							countryMaster.setCountryId(Integer.parseInt(row[56].toString()));
							countryMaster.setCountryName(row[57].toString());
							clientMaster.setCountryMaster(countryMaster);
						}
						if (row[58] != null) {
							StateMaster stateMaster = new StateMaster();
							stateMaster.setStateId(Integer.parseInt(row[58].toString()));
							CountryMaster countryMaster = new CountryMaster();
							countryMaster.setCountryId(Integer.parseInt(row[59].toString()));
							stateMaster.setStateName(row[60].toString());
							clientMaster.setStateMaster(stateMaster);
						}if (row[61] != null) {
							clientMaster.setMbRelation(row[61].toString());
						}if (row[62] != null) {
							clientMaster.setContactFlag(row[62].toString());
						}
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// HttpServletRequest request = null;
				HttpSession session = request.getSession();
				session.setAttribute("client", clientMaster);
				// session.setAttribute("userName", clientMaster.getUserId());
				session.setAttribute("clientMasterId",
						clientMaster.getClientId());
				session.setAttribute("clientUserName", clientMaster.getUserId());
				session.setAttribute("clientId", clientMaster.getClientId());

				/*clientMaster.setClientLocked(1);
				clientMaster.setUserSessionId(session.getId());*/

				if ((String) session.getAttribute("userId") == null) {
					clientMaster.setCount(1);
				}else{
					//clientMaster.setClientLockedBy(Integer.parseInt((String) session.getAttribute("userId")));
					try {
						SQLQuery query1 = sessionFactory
								.getCurrentSession()
								.createSQLQuery("update clients set client_locked=1,client_locked_by='"+Integer.parseInt((String) session.getAttribute("userId"))+"',user_session_id='"+session.getId()+"' where client_id='"+clientId+"'");
						query1.executeUpdate();
					}catch(Exception e){
						e.printStackTrace();
					}
				}

				if (clientMaster.getClientDOB() != null && clientMaster.getClientDOB() !="" && !clientMaster.getClientDOB().equals("NULL") && !clientMaster.getClientDOB().equals("-")) {
					java.util.Date date = new java.util.Date();
					java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
							"yyyy-MM-dd");
					String currentTime = simpleDateFormat.format(date);
					//String clientDOB = (clientMaster.getClientDOB()).replaceAll("-", "/");
					java.util.Date clientBirthDate = simpleDateFormat
							.parse(clientMaster.getClientDOB());
					java.util.Date currentYear = simpleDateFormat
							.parse(currentTime);
					Integer age = currentYear.getYear()
							- clientBirthDate.getYear();
					clientMaster.setClientAge(age);
				}
				else{
					clientMaster.setClientAge(0);
				}
				
				if (clientMaster.getNomineeDOB() != null && clientMaster.getNomineeDOB() !="" && !clientMaster.getNomineeDOB().equals("NULL") && !clientMaster.getNomineeDOB().equals("-")) {
					java.util.Date date = new java.util.Date();
					java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
							"yyyy-MM-dd");
					String currentTime = simpleDateFormat.format(date);
					//String nomineeDOB = (clientMaster.getNomineeDOB()).replaceAll("-", "/");
					java.util.Date nomineeBirthDate = simpleDateFormat
							.parse(clientMaster.getNomineeDOB());
					java.util.Date currentYear = simpleDateFormat
							.parse(currentTime);
					Integer nomineeAge = currentYear.getYear()
							- nomineeBirthDate.getYear();
					clientMaster.setNomineeAge(nomineeAge);
				}
				else{
					clientMaster.setNomineeAge(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientMaster;
	}

	@Override
	public List<ClientUploadReport> getClientUploadReport(Integer clientId) {
		List<ClientUploadReport> clientUploadReports = new ArrayList<ClientUploadReport>();
		SQLQuery query = null;
		SQLQuery query1 = null;
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select report_desc, report_filepath,head.added_by,head.added_on,client_report_line_id,test_id,package_id,head.center_id,center_name,edit_report_status,report_date from client_report_line line inner join client_report_head head on line.client_report_id=head.client_report_id left join ehr_center_master on head.center_id=ehr_center_master.center_id where client_id="
									+ clientId
									+ " and line.report_isactive='Y'");
			/*
			 * "select report_desc,report_filepath from client_report_line where client_report_id in(select client_report_id from client_report_head where client_id="
			 * +clientId+")"
			 */
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ClientUploadReport clientUploadReport = new ClientUploadReport();
				ClientReportHead clientReportHead = new ClientReportHead();
				if (row[0] != null)
					clientUploadReport.setReportDescription(row[0].toString());
				else
					clientUploadReport.setReportDescription("");

				if (row[1] != null)
					clientUploadReport.setFilePath(row[1].toString());
				else
					clientUploadReport.setFilePath("");

				if (row[2] != null) {
					clientReportHead.setAddedBy(row[2].toString());
				}

				if (row[3] != null) {
					String addedOn = DateConvertUtil.convertDate(row[3]
							.toString());
					clientReportHead.setAddedOn(addedOn + " "
							+ row[3].toString().split(" ")[1]);
				}

				clientUploadReport.setClientReportHead(clientReportHead);

				if (row[4] != null) {
					List<ReportVerification> reportVerificationSet = new ArrayList<ReportVerification>();
					clientUploadReport.setClientReportLineId(Integer
							.parseInt(row[4].toString()));
					query1 = sessionFactory
							.getCurrentSession()
							.createSQLQuery(
									"Select verifyReport.reject_id,verifyReport.reject_comment,verifyReport.verify_comment,verifyReport.approved_by,verifyReport.approved_on,reject_reason FROM clientreportverify_clientreportline verifyLine inner join report_verification verifyReport ON verifyLine.client_report_verify_id = verifyReport.client_report_verify_id left join ehr_reject_master On verifyReport.reject_id=ehr_reject_master.reject_id where client_report_line_id = "
											+ Integer.parseInt(row[4]
													.toString())
											+ " order by verifyLine.client_report_verify_id DESC LIMIT 1;");
					List<Object[]> reportRows = query1.list();
					for (Object[] rows1 : reportRows) {
						ReportVerification reportVerification = new ReportVerification();
						if (rows1[0] != null) {
							RejectMaster rejectMaster = new RejectMaster();
							rejectMaster.setRejectId(Integer.parseInt(rows1[0]
									.toString()));
							rejectMaster.setRejectReason(rows1[5].toString());
							reportVerification.setRejectMaster(rejectMaster);
							// reportVerification.setReasonToReject(rows1[0].toString());
						}
						if (rows1[1] != null) {
							reportVerification.setRejectComment(rows1[1]
									.toString());
						}
						if (rows1[2] != null) {
							reportVerification.setVerifyComment(rows1[2]
									.toString());
						}
						if (rows1[3] != null) {
							reportVerification.setApprovedBy(rows1[3]
									.toString());
						}
						if (rows1[4] != null) {
							reportVerification.setApprovedOn(DateConvertUtil.convertDateTime(rows1[4]
									.toString()));
						}
						reportVerificationSet.add(reportVerification);
					}
					clientUploadReport
							.setReportVerificationSet(reportVerificationSet);
				}
				if (row[5] != null) {
					TestMaster testMaster = new TestMaster();
					testMaster.setTestId(Integer.parseInt(row[5].toString()));
					clientUploadReport.setTestMaster(testMaster);
				}
				if (row[6] != null) {
					PackageMaster packageMaster = new PackageMaster();
					packageMaster.setPackageId(Integer.parseInt(row[6]
							.toString()));
					clientUploadReport.setPackageMaster(packageMaster);
				}
				CentreMaster centreMaster = new CentreMaster();
				if (row[7] != null) {
					centreMaster
							.setCentreId(Integer.parseInt(row[7].toString()));
				}
				if (row[8] != null) {
					centreMaster.setCentreName(row[8].toString());
				}
				clientReportHead.setCentreMaster(centreMaster);
				if (row[9] != null) {
					clientUploadReport.setEditReportStatus(Integer
							.parseInt(row[9].toString()));
				}
				if (row[10] != null) {
					clientUploadReport.setReportDate(row[10].toString());
				}
				clientUploadReports.add(clientUploadReport);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientUploadReports;
	}

	@Override
	public Boolean changePassword(ClientMaster clientMaster) {
		Boolean flag = false;
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(ClientMaster.class);
			criteria.add(Restrictions.eq("clientId", clientMaster.getClientId()));
			ClientMaster clientMaster1 = (ClientMaster) criteria.uniqueResult();
			if (clientMaster1.getPassword().equals(clientMaster.getFirstName())) {
				clientMaster1.setPassword(clientMaster.getLastName());
				// sessionFactory.getCurrentSession().update(clientMaster1);
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public String getCountClientMaster(String startDate, String endDate,HttpServletRequest request) {
		SQLQuery query = null;
		HttpSession session=request.getSession();
		String userTypeId=(String) session.getAttribute("userTypeId");
		String packages=(String) session.getAttribute("packages");
		String memberCode=(String) session.getAttribute("memberCode");
		String centers=(String) session.getAttribute("centers");
		System.err.println("memberCode=="+memberCode);
		
		if(packages.isEmpty()){
			packages="0";
		}
		try {
			if(userTypeId.equals("4") || userTypeId.equals("13")){
				query = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								/*"SELECT count(distinct clients.client_id) FROM checkup_master left join clients ON clients.client_id = checkup_master.client_id left join ehr_package_master ON clients.package_id = ehr_package_master.package_id WHERE checkup_master.package_id in("+packages+") and "
										+ " (checkup_master.checkup_date BETWEEN '"+ startDate + "' AND '"+ endDate	+ "') ");*/
							
				
				"SELECT count(distinct clients.client_id) FROM checkup_master left join clients ON clients.client_id = checkup_master.client_id left join ehr_package_master ON clients.package_id = ehr_package_master.package_id WHERE "
				+ " (checkup_master.checkup_date BETWEEN '"+ startDate + "' AND '"+ endDate	+ "') and member_id Like '"+memberCode+"%' ");
		
			}else if(userTypeId.equals("14")) {
				query = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								"SELECT count(distinct clients.client_id) FROM checkup_master left join clients ON clients.client_id = checkup_master.client_id left join ehr_package_master ON clients.package_id = ehr_package_master.package_id WHERE "
										+ " (checkup_master.checkup_date BETWEEN '"+ startDate + "' AND '"+ endDate	+ "') and checkup_master.center_id in ("+centers+")");
			}
			else {
		query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT count(distinct clients.client_id) FROM checkup_master left join clients ON clients.client_id = checkup_master.client_id left join ehr_package_master ON clients.package_id = ehr_package_master.package_id WHERE "
								+ " (checkup_master.checkup_date BETWEEN '"+ startDate + "' AND '"+ endDate	+ "') ");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		BigInteger count = (BigInteger) query.uniqueResult();
		return count.toString();
	}

	@Override
	public List<ClientMaster> getClientMastersByDate(String startDate,
			String endDate, int startIndex,HttpServletRequest request) {
		List<ClientMaster> listClientMasters = new ArrayList<ClientMaster>();
		SQLQuery query = null;
		HttpSession session=request.getSession();
		String userTypeId=(String) session.getAttribute("userTypeId");
		String packages=(String) session.getAttribute("packages");
		String memberCode=(String) session.getAttribute("memberCode");
		String centers=(String) session.getAttribute("centers");
		System.err.println("centers=="+centers);
		if(packages.isEmpty()){
			packages="0";
		}
		try {
			if(userTypeId.equals("4") || userTypeId.equals("13")){
				query = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								/*"SELECT client_first_name,client_last_name,client_mob_no,package_desc,clients.add_at,client_user_id,clients.client_id,checkup_date,clients.client_email FROM checkup_master left join clients on clients.client_id=checkup_master.client_id left join ehr_package_master ON clients.package_id = ehr_package_master.package_id WHERE checkup_master.package_id in("+packages+") and "
										+ " (checkup_master.checkup_date BETWEEN '"+ startDate + "' AND '"+ endDate	+ "') and client_isactive='Y' group by client_id");*/
								
								"SELECT client_first_name,client_last_name,client_mob_no,package_desc,clients.add_at,client_user_id,clients.client_id,checkup_date,clients.client_email FROM checkup_master left join clients on clients.client_id=checkup_master.client_id left join ehr_package_master ON clients.package_id = ehr_package_master.package_id WHERE "
								+ " (checkup_master.checkup_date BETWEEN '"+ startDate + "' AND '"+ endDate	+ "') and client_isactive='Y' and clients.is_deleted='N' and member_id Like '"+memberCode+"%' group by client_id desc");
			}else if(userTypeId.equals("14")) {
				query = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								"SELECT client_first_name,client_last_name,client_mob_no,package_desc,clients.add_at,client_user_id,clients.client_id,checkup_date,clients.client_email FROM checkup_master left join clients on clients.client_id=checkup_master.client_id left join ehr_package_master ON clients.package_id = ehr_package_master.package_id WHERE "
										+ " (checkup_master.checkup_date BETWEEN '"+ startDate + "' AND '"+ endDate	+ "') and client_isactive='Y' and checkup_master.center_id in ("+centers+") and clients.is_deleted='N' group by client_id");
			}
			else {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"SELECT client_first_name,client_last_name,client_mob_no,package_desc,clients.add_at,client_user_id,clients.client_id,checkup_date,clients.client_email FROM checkup_master left join clients on clients.client_id=checkup_master.client_id left join ehr_package_master ON clients.package_id = ehr_package_master.package_id WHERE "
									+ " (checkup_master.checkup_date BETWEEN '"+ startDate + "' AND '"+ endDate	+ "') and client_isactive='Y' and clients.is_deleted='N' group by client_id");
			}
			query.setFirstResult(startIndex);
			query.setMaxResults(10);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ClientMaster clientMaster = new ClientMaster();
				if (row[0] != null) {
					clientMaster.setFirstName(row[0].toString());
				}
				if (row[1] != null) {
					clientMaster.setLastName(row[1].toString());
				}
				if (row[2] != null) {
					clientMaster.setMobNo(row[2].toString());
				}
				PackageMaster packageMaster = new PackageMaster();
				if (row[3] != null) {
					packageMaster.setPackageDescription(row[3].toString());
				} else {
					packageMaster.setPackageDescription("-");
				}
				clientMaster.setPackageMaster(packageMaster);
				if (row[4] != null) {
					clientMaster.setAddAt(DateConvertUtil.convertDateTime(row[4].toString()));
				}
				if (row[5] != null) {
					clientMaster.setUserId(row[5].toString());
				}
				if (row[6] != null) {
					clientMaster
							.setClientId(Integer.parseInt(row[6].toString()));
				}
				if (row[8] != null) {
					clientMaster
							.setEmailId(row[8].toString());
				}
				listClientMasters.add(clientMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listClientMasters;
	}

	@Override
	public List<ClientMaster> autoSuggestionClient(String letter,HttpServletRequest request) {
		List<ClientMaster> listClientMasters = new ArrayList<ClientMaster>();
		HttpSession session=request.getSession();
		String userTypeId=(String) session.getAttribute("userTypeId");
		String packages=(String) session.getAttribute("packages");
		String memberCode=(String) session.getAttribute("memberCode");
		String centers=(String) session.getAttribute("centers");
		if(packages.isEmpty()){
			packages="0";
		}
		//String centers=(String) session.getAttribute("centers");
		SQLQuery query = null;
		try {
			if(userTypeId.equals("4") || userTypeId.equals("13")){
				query = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								/*"SELECT client_first_name,client_last_name,client_mob_no,client_id,client_full_name FROM clients WHERE (client_full_name LIKE '%"
										+ letter
										+ "%' or client_first_name LIKE '%"
										+ letter
										+ "%' or client_last_name LIKE '%"
										+ letter + "%') and package_id in("+packages+") and client_isactive='Y'");*/
								"SELECT client_first_name,client_last_name,client_mob_no,client_id,client_full_name FROM clients WHERE (client_full_name LIKE '%"
								+ letter
								+ "%' or client_first_name LIKE '%"
								+ letter
								+ "%' or client_last_name LIKE '%"
								+ letter + "%') and client_isactive='Y' and is_deleted='N' and member_id Like '"+memberCode+"%'");
			}else if(userTypeId.equals("14")) {
				query = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								"SELECT client_first_name,client_last_name,client_mob_no,client_id,client_full_name FROM clients WHERE (client_full_name LIKE '%"
										+ letter
										+ "%' or client_first_name LIKE '%"
										+ letter
										+ "%' or client_last_name LIKE '%"
										+ letter + "%') and is_deleted='N' and clients.center_id in ("+centers+") and client_isactive='Y' ");
			}
			else {
				query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"SELECT client_first_name,client_last_name,client_mob_no,client_id,client_full_name FROM clients WHERE (client_full_name LIKE '%"
									+ letter
									+ "%' or client_first_name LIKE '%"
									+ letter
									+ "%' or client_last_name LIKE '%"
									+ letter + "%') and is_deleted='N' and client_isactive='Y' ");
			}
			query.setFirstResult(0);
			query.setMaxResults(100);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ClientMaster clientMaster = new ClientMaster();
				if (row[0] != null) {
					clientMaster.setFirstName(row[0].toString());
				}
				if (row[1] != null) {
					clientMaster.setLastName(row[1].toString());
				}
				if (row[2] != null) {
					clientMaster.setMobNo(row[2].toString());
				}
				if (row[3] != null) {
					clientMaster.setClientDOB(row[3].toString());
				}
				if (row[4] != null) {
					clientMaster.setClientFullName(row[4].toString());
				}
				listClientMasters.add(clientMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listClientMasters;
	}

	@Override
	public ClientMaster getClientByClientId(int clientId) {
		SQLQuery query = null;
		ClientMaster clientMaster = new ClientMaster();
		clientMaster.setClientId(clientId);
		
		try {
			
				query = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								"SELECT client_first_name,client_last_name,client_mob_no,package_desc,clients.add_at,client_email,client_user_id,clients.package_id,clients.center_id,checkup_master.checkup_date,checkup_master.checkup_id,classification,member_id,client_middle_name,is_verified,client_isactive,client_gender,client_blood_group,age,client_height,client_weight,contact_flag FROM clients left join ehr_package_master on clients.package_id=ehr_package_master.package_id left join checkup_master ON checkup_master.client_id=clients.client_id left join ehr_analysis_comment on ehr_analysis_comment.client_id = clients.client_id WHERE clients.client_id ="
										+ clientId);			

			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				if (row[0] != null) {
					clientMaster.setFirstName(row[0].toString().trim());
				}
				else{
					clientMaster.setFirstName("");
				}
				if (row[1] != null) {
					clientMaster.setLastName(row[1].toString().trim());
				}
				else{
					clientMaster.setLastName("");
				}
				if (row[2] != null) {
					clientMaster.setMobNo(row[2].toString());
				}
				PackageMaster packageMaster = new PackageMaster();
				if (row[3] != null && !row[3].toString().equals("null")) {
					packageMaster.setPackageDescription(row[3].toString());
				}else {
					packageMaster.setPackageDescription("");
				}
				if (row[4] != null) {
					clientMaster.setAddAt(DateConvertUtil.convertDateTime(row[4].toString()));
				}
				if (row[5] != null) {
					clientMaster.setEmailId(row[5].toString());
				}
				if (row[6] != null) {
					clientMaster.setUserId(row[6].toString());
				}
				if (row[7] != null) {
					packageMaster.setPackageId(Integer.parseInt(row[7]
							.toString()));
				}
				if (row[8] != null) {
					CentreMaster centreMaster = new CentreMaster();
					centreMaster
							.setCentreId(Integer.parseInt(row[8].toString()));
					clientMaster.setCentreMaster(centreMaster);
				}
				Set<CheckUpMaster> checkUpMasterSet = new HashSet<CheckUpMaster>();
				CheckUpMaster checkUpMaster = new CheckUpMaster();
				if (row[9] != null) {
					checkUpMaster.setCheckUpDate(row[9].toString());
				}
				if (row[10] != null) {
					checkUpMaster.setCheckUpId(Integer.parseInt(row[10]
							.toString()));
				}
				if (row[12] != null) {
					clientMaster.setMemberId(row[12].toString());
				}
				else{
					clientMaster.setMemberId("");
				}
				if (row[13] != null) {
					clientMaster.setMiddleName(row[13].toString().trim());
				}
				else{
					clientMaster.setMiddleName("");
				}
				if (row[14] != null) {
					clientMaster.setIsVerified(row[14].toString());
				}
				if (row[15] != null) {
					clientMaster.setIsActive(row[15].toString());
				}
				if (row[16] != null) {
					clientMaster.setGender(row[16].toString());
				}
				if (row[17] != null) {
					clientMaster.setBloodGroup(row[17].toString());
				}
				if (row[18] != null) {
					clientMaster.setClientAge(Integer.parseInt(row[18].toString()));
				}
				if (row[19] != null) {
					clientMaster.setClientHeight(row[19].toString());
				}
				if (row[20] != null) {
					clientMaster.setClientWeight(row[20].toString());
				}
				if (row[21] != null) {
					clientMaster.setContactFlag(row[21].toString());
				}
				checkUpMasterSet.add(checkUpMaster);
				clientMaster.setCheckUpMasterSet(checkUpMasterSet);
				clientMaster.setPackageMaster(packageMaster);

				/*SQLQuery query1 = null;
				query1 = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								"SELECT GROUP_CONCAT(test_result_status SEPARATOR ', ') as flag FROM ehr_test_result where client_id="
										+ clientId);
				List<String> rows1 = query1.list();
				for (String row1 : rows1) {
					if (row1 != null) {
						if (rows1.toString().contains("abnormal low")
								|| rows1.toString().contains("abnormal high")) {
							clientMaster.setAddBy("abnormal");
						} else {
							clientMaster.setAddBy("normal");
						}
					}
				}*/
				if (row[11] != null) {
					clientMaster.setAddBy(row[11].toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientMaster;
	}

	@Override
	public int verifyClientUploadReport(ReportVerification reportVerification) {
		try {
			sessionFactory.getCurrentSession().save(reportVerification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public String deletePatientReport(Integer clientReportLineId) {
		String response = "";
		ClientUploadReport clientReportLine = new ClientUploadReport();
		try {
			SQLQuery query = sessionFactory
					.getCurrentSession()
					.createSQLQuery("SELECT count(*) FROM clientreportverify_clientreportline crcl"
							+ " inner join report_verification rv on crcl.client_report_verify_id = rv.client_report_verify_id"
							+ " where reject_id IS NULL and client_report_line_id="+clientReportLineId);
			BigInteger count = (BigInteger) query.uniqueResult();
			if(count.intValue()==0) {
			clientReportLine = (ClientUploadReport) sessionFactory
					.getCurrentSession().get(ClientUploadReport.class,
							clientReportLineId);
			clientReportLine.setReportIsActive("D");
			sessionFactory.getCurrentSession().update(clientReportLine);
			response = "Report deleted";
			}else {
				response = "Once Report Is Verified It Cannot Be Deleted";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@Override
	public String saveClientReport(ClientReportHead clientReportHead) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(clientReportHead);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Reports saved successfully";
	}

	@Override
	public List<ClientMaster> getAllClientMastersList() {
		List<ClientMaster> listClientMasters = new ArrayList<ClientMaster>();
		SQLQuery query = null;
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"SELECT client_first_name,client_middle_name,client_last_name,client_mob_no,client_address1,client_address2,client_address3,client_email,client_full_name,client_id from clients order by client_id desc");
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ClientMaster clientMaster = new ClientMaster();
				if (row[0] != null) {
					clientMaster.setFirstName(row[0].toString());
				}
				if (row[1] != null) {
					clientMaster.setMiddleName(row[1].toString());
				}
				if (row[2] != null) {
					clientMaster.setLastName(row[2].toString());
				}
				if (row[3] != null) {
					clientMaster.setMobNo(row[3].toString());
				}
				if (row[4] != null) {
					clientMaster.setAddressLine1(row[4].toString());
				}
				if (row[5] != null) {
					clientMaster.setAddressLine2(row[5].toString());
				}
				if (row[6] != null) {
					clientMaster.setAddressLine3(row[6].toString());
				}
				if (row[7] != null) {
					clientMaster.setEmailId(row[7].toString());
				}
				if (row[8] != null) {
					clientMaster.setClientFullName(row[8].toString());
				}
				if (row[9] != null) {
					clientMaster
							.setClientId(Integer.parseInt(row[9].toString()));
				}
				listClientMasters.add(clientMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listClientMasters;
	}

	@Override
	public String saveClientMaster(ClientMaster clientMaster) {
		String clientId = null;
		Integer clientIdd = null;
		try {
			System.err.println("DOB"+clientMaster.getClientDOB());
			Integer count=duplicateCheck( clientMaster);
			
			System.err.println("count"+count);

			if(count == null) {
			sessionFactory.getCurrentSession().saveOrUpdate(clientMaster);
			clientIdd= clientMaster.getClientId();
			clientId = clientMaster.getClientId().toString();
			
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(ClientMaster.class);
			criteria.add(Restrictions.eq("clientId", clientIdd));
			ClientMaster clientMaster1 = (ClientMaster) criteria.uniqueResult();
			/*
			  if(clientMaster.getEmailId()!=null){
			  	clientMaster1.setUserId(clientMaster.getEmailId()); 
			  } else{
			*/
			clientMaster1.setUserId("EHR" + clientIdd);
			clientId= "org_" + clientId;
			// }
			}else {
				clientId= "dup_" + count;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientId;
	}

	@Override
	public ClientMaster getClientCenterPackageByUserId(Integer clientId,Integer visitId) {
		SQLQuery query = null;
		String query1= "";
		System.err.println("visitId=="+visitId);
		ClientMaster clientMaster = new ClientMaster();
		try {
			if(visitId==0) {
				//Changed by kishor (package_desc to package_short_desc)
				query1="SELECT checkup_master.package_id,package_short_desc,checkup_master.center_id,center_name,checkup_id,checkup_date FROM clients left join checkup_master ON clients.client_id =checkup_master.client_id left join ehr_package_master ON ehr_package_master.package_id = checkup_master.package_id left join ehr_center_master ON ehr_center_master.center_id = checkup_master.center_id WHERE clients.client_id = "+clientId+" ";
			}else {
				//Changed by kishor (package_desc to package_short_desc)
				query1="SELECT checkup_master.package_id,package_short_desc,checkup_master.center_id,center_name,checkup_id,checkup_date FROM clients left join checkup_master ON clients.client_id =checkup_master.client_id left join ehr_package_master ON ehr_package_master.package_id = checkup_master.package_id left join ehr_center_master ON ehr_center_master.center_id = checkup_master.center_id WHERE clients.client_id = "+clientId+" and checkup_master.checkup_id = "+visitId+" ";
			}
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(query1);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				PackageMaster packageMaster = new PackageMaster();
				CentreMaster centreMaster = new CentreMaster();
				Set<CheckUpMaster> checkUpMasterSet = new HashSet<CheckUpMaster>();
				if (row[0] != null) {
					packageMaster.setPackageId(Integer.parseInt(row[0]
							.toString()));
				}
				if (row[1] != null) {
					packageMaster.setPackageDescription(row[1].toString());
				}
				if (row[2] != null) {
					centreMaster
							.setCentreId(Integer.parseInt(row[2].toString()));
				}
				if (row[3] != null) {
					centreMaster.setCentreName(row[3].toString());
				}
				CheckUpMaster checkUpMaster = new CheckUpMaster();
				if (row[4] != null) {
					checkUpMaster.setCheckUpId(Integer.parseInt(row[4]
							.toString()));
				}
				if (row[5] != null) {
					String checkUpDate = DateConvertUtil.convertDate(row[5]
							.toString());
					checkUpMaster.setCheckUpDate(checkUpDate);
				}
				checkUpMasterSet.add(checkUpMaster);
				clientMaster.setPackageMaster(packageMaster);
				clientMaster.setCentreMaster(centreMaster);
				clientMaster.setCheckUpMasterSet(checkUpMasterSet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientMaster;
	}

	@Override
	public String updateClientProfile(ClientMaster clientMaster2) {
		try {
			ClientMaster clientMaster = (ClientMaster) sessionFactory
					.getCurrentSession().get(ClientMaster.class,
							clientMaster2.getClientId());
			System.err.println("daoimpl"+clientMaster.getClientId());
			clientMaster.setFirstName(clientMaster2.getFirstName());
			clientMaster.setMiddleName(clientMaster2.getMiddleName());
			clientMaster.setLastName(clientMaster2.getLastName());
			String clientFullName=(clientMaster2.getFirstName()).trim()+" "+(clientMaster2.getMiddleName()).trim()+" "+(clientMaster2.getLastName()).trim();
			clientMaster.setClientFullName(clientFullName);
			clientMaster.setBloodGroup(clientMaster2.getBloodGroup());
			clientMaster.setAddressLine1(clientMaster2.getAddressLine1());
			clientMaster.setAddressLine2(clientMaster2.getAddressLine2());
			clientMaster.setAddressLine3(clientMaster2.getAddressLine3());
			clientMaster.setMobNo(clientMaster2.getMobNo());
			clientMaster.setLandlineNo(clientMaster2.getLandlineNo());
			clientMaster.setCityMaster(clientMaster2.getCityMaster());
			clientMaster.setStateMaster(clientMaster2.getStateMaster());
			clientMaster.setCountryMaster(clientMaster2.getCountryMaster());
			clientMaster.setClientDOB(clientMaster2.getClientDOB());
			clientMaster.setNomineeFirstName(clientMaster2
					.getNomineeFirstName());
			clientMaster.setNomineeMiddleName(clientMaster2
					.getNomineeMiddleName());
			clientMaster.setNomineeLastName(clientMaster2.getNomineeLastName());
			clientMaster.setNomineeDOB(clientMaster2.getNomineeDOB());
			clientMaster.setNomineeRelation(clientMaster2.getNomineeRelation());
			clientMaster.setClientHeight(clientMaster2.getClientHeight());
			clientMaster.setClientWeight(clientMaster2.getClientWeight());
			clientMaster.setGender(clientMaster2.getGender());
			clientMaster.setClientAge(clientMaster2.getClientAge());
			clientMaster.setNomineeAge(clientMaster2.getNomineeAge());
			clientMaster.setModAt(clientMaster2.getModAt());
			clientMaster.setModBy(clientMaster2.getModBy());
			clientMaster.setPinCode(clientMaster2.getPinCode());
			clientMaster.setEmailId(clientMaster2.getEmailId());
			clientMaster.setHeightInCm(clientMaster2.getHeightInCm());
			clientMaster.setWeightInKg(clientMaster2.getWeightInKg());
			String sql = "INSERT INTO ehr_demoghraphic_log(modify_by,modify_on,client_id,client_first_name,client_middle_name,client_last_name,client_dob,client_gender,client_blood_group,client_address1,client_address2,client_address3,client_pincode,client_city,client_state,client_country,client_mob_no,client_email,client_landline_no,nominee_relation,nominee_first_name,nominee_middle_name,nominee_last_name,nominee_dob,client_height,client_weight) "
					+ "values('"
					+ clientMaster2.getModBy()
					+ "','"
					+ clientMaster2.getModAt()
					+ "',"
					+ "'"
					+ clientMaster2.getClientId()
					+ "','"
					+ clientMaster2.getFirstName()
					+ "','"
					+ clientMaster2.getMiddleName()
					+ "','"
					+ clientMaster2.getLastName()
					+ "','"
					+ clientMaster2.getClientDOB()
					+ "','"
					+ clientMaster2.getGender()
					+ "','"
					+ clientMaster2.getBloodGroup()
					+ "','"
					+ clientMaster2.getAddressLine1()
					+ "','"
					+ clientMaster2.getAddressLine2()
					+ "','"
					+ clientMaster2.getAddressLine3()
					+ "','"
					+ clientMaster2.getPinCode()
					+ "','"
					+ clientMaster2.getCityMaster().getCityId()
					+ "','"
					+ clientMaster2.getStateMaster().getStateId()
					+ "','"
					+ clientMaster2.getCountryMaster().getCountryId()
					+ "','"
					+ clientMaster2.getMobNo()
					+ "','"
					+ clientMaster2.getEmailId()
					+ "','"
					+ clientMaster2.getLandlineNo()
					+ "','"
					+ clientMaster2.getNomineeRelation()
					+ "','"
					+ clientMaster2.getNomineeFirstName()
					+ "','"
					+ clientMaster2.getNomineeMiddleName()
					+ "','"
					+ clientMaster2.getNomineeLastName()
					+ "','"
					+ clientMaster2.getNomineeDOB()
					+ "','"
					+ clientMaster2.getClientHeight()
					+ "','"
					+ clientMaster2.getClientWeight() + "')";
			SQLQuery queryInsert = sessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			queryInsert.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Profile updated";
	}

	@Override
	public String changeProfilePicture(ClientMaster clientMaster2) {
		try {
			ClientMaster clientMaster = (ClientMaster) sessionFactory
					.getCurrentSession().get(ClientMaster.class,
							clientMaster2.getClientId());
			clientMaster.setPhotoUrl(clientMaster2.getPhotoUrl());

			String sql = "INSERT INTO ehr_demoghraphic_log(modify_by,modify_on,client_id,client_photo) "
					+ "values('"
					+ clientMaster2.getModBy()
					+ "','"
					+ clientMaster2.getModAt()
					+ "','"
					+ clientMaster2.getClientId()
					+ "','"
					+ clientMaster2.getPhotoUrl() + "')";
			SQLQuery queryInsert = sessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			queryInsert.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientMaster2.getPhotoUrl();
	}

	@Override
	public String deleteProfilePicture(ClientMaster clientMaster2) {
		ClientMaster clientMaster = new ClientMaster();
		try {
			clientMaster = (ClientMaster) sessionFactory.getCurrentSession()
					.get(ClientMaster.class, clientMaster2.getClientId());
			clientMaster.setPhotoUrl("");
			String sql = "INSERT INTO ehr_demoghraphic_log(modify_by,modify_on,client_id) "
					+ "values('"
					+ clientMaster2.getModBy()
					+ "','"
					+ clientMaster2.getModAt()
					+ "','"
					+ clientMaster2.getClientId() + "')";
			SQLQuery queryInsert = sessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			queryInsert.executeUpdate();
		} catch (Exception e) {

		}
		return clientMaster.getPhotoUrl();
	}

	@Override
	public ClientReportHead getClientReportByClientReportLineId(
			Integer clientReportLineId) {
		ClientReportHead clientReportHead = new ClientReportHead();
		SQLQuery query = null;
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select report_desc,report_filepath,client_report_line_id,center_name,head.client_report_date,test_id,package_id,head.center_id,report_date from client_report_line line inner join client_report_head head ON line.client_report_id = head.client_report_id inner join ehr_center_master center ON center.center_id = head.center_id where client_report_line_id = "
									+ clientReportLineId);
			List<Object[]> rows = query.list();
			List<ClientUploadReport> clientUploadReportSet = new ArrayList<ClientUploadReport>();
			for (Object[] row : rows) {
				ClientUploadReport clientUploadReport = new ClientUploadReport();
				if (row[0] != null) {
					clientUploadReport.setReportDescription(row[0].toString());
				}
				if (row[1] != null) {
					clientUploadReport.setFilePath(row[1].toString());
				}
				if (row[2] != null) {
					clientUploadReport.setClientReportLineId(Integer
							.parseInt(row[2].toString()));
				}
				if (row[3] != null) {
					CentreMaster centreMaster = new CentreMaster();
					centreMaster.setCentreName(row[3].toString());
					clientReportHead.setCentreMaster(centreMaster);
				}
				if (row[4] != null) {
					clientReportHead.setClientReportDate(row[4].toString());
				}
				if (row[8] != null) {
					clientUploadReport.setReportDate(DateConvertUtil.convertDateDMY(row[8].toString()));
				}else {
					clientUploadReport.setReportDate("");
				}
				clientUploadReportSet.add(clientUploadReport);
			}
			clientReportHead.setClientUploadReportSet(clientUploadReportSet);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientReportHead;
	}

	@Override
	public boolean sendSmsAndMail(Integer clientId) {
		boolean sendFlag = true;
		try {
			SQLQuery query = null;
			SQLQuery query1 = null;
			int count = 0;
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select client_report_line_id,report_desc from client_report_line line inner join client_report_head head on line.client_report_id=head.client_report_id where client_id="
									+ clientId
									+ " and line.report_isactive='Y'");
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				if (row[0] != null) {
					query1 = sessionFactory
							.getCurrentSession()
							.createSQLQuery(
									"Select verifyReport.reject_id,verifyReport.reject_comment,verifyReport.verify_comment FROM clientreportverify_clientreportline verifyLine inner join report_verification verifyReport ON verifyLine.client_report_verify_id = verifyReport.client_report_verify_id where client_report_line_id = "
											+ Integer.parseInt(row[0]
													.toString()));
					List<Object[]> reportRows = query1.list();
					for (Object[] rows1 : reportRows) {
						if (rows1[0] == null && rows1[2] != null) {
							count++;
						}
					}
				}
			}
			System.err.println("sendFlag----"+count);
			if (count == 1) {
				sendFlag = false;
				return sendFlag;
			} else {
				sendFlag = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sendFlag;
	}

	@Override
	public boolean sendUserNameAndPassword(ClientMaster clientMaster) {
		Boolean flag = false;
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(ClientMaster.class);
			criteria.add(Restrictions.eq("userId", clientMaster.getUserId()));
			ClientMaster clientMaster1 = (ClientMaster) criteria.uniqueResult();
			clientMaster1.setPassword(clientMaster.getPassword());
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public List<ClientMaster> autoSuggestionClientTestResults(String letter) {
		List<ClientMaster> listClientMasters = new ArrayList<ClientMaster>();
		SQLQuery query = null;
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select distinct(ehr_test_result.client_id),client_first_name,client_last_name,client_mob_no,client_full_name from ehr_test_result inner join clients on ehr_test_result.client_id=clients.client_id WHERE (client_full_name LIKE '%"
									+ letter
									+ "%' or client_first_name LIKE '%"
									+ letter
									+ "%' or client_last_name LIKE '%"
									+ letter + "%') and client_isactive='Y'");
			query.setFirstResult(0);
			query.setMaxResults(100);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ClientMaster clientMaster = new ClientMaster();
				if (row[0] != null) {
					clientMaster.setClientDOB(row[0].toString());
				}
				if (row[1] != null) {
					clientMaster.setFirstName(row[1].toString());
				}
				if (row[2] != null) {
					clientMaster.setLastName(row[2].toString());
				}
				if (row[3] != null) {
					clientMaster.setMobNo(row[3].toString());
				}
				if (row[4] != null) {
					clientMaster.setClientFullName(row[4].toString());
				}
				listClientMasters.add(clientMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listClientMasters;
	}

	@Override
	public List<ClientMaster> getClientMastersByDateTestResults(
			String startDate, String endDate, int startIndex) {
		List<ClientMaster> listClientMasters = new ArrayList<ClientMaster>();
		SQLQuery query = null;
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select distinct(ehr_test_result.client_id),client_first_name,client_last_name,client_mob_no,client_full_name,client_user_id,package_desc,ehr_test_result.package_id,clients.add_at,client_email FROM ehr_test_result left join clients on ehr_test_result.client_id=clients.client_id left join ehr_package_master on ehr_test_result.package_id=ehr_package_master.package_id WHERE "
							+ " (ehr_test_result.added_on BETWEEN '"+ startDate +"' AND '"+ endDate +"') and client_isactive='Y' ");
			query.setFirstResult(startIndex);
			query.setMaxResults(10);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ClientMaster clientMaster = new ClientMaster();
				if (row[0] != null) {
					clientMaster
							.setClientId(Integer.parseInt(row[0].toString()));
				}
				if (row[1] != null) {
					clientMaster.setFirstName(row[1].toString());
				}
				if (row[2] != null) {
					clientMaster.setLastName(row[2].toString());
				}
				if (row[3] != null) {
					clientMaster.setMobNo(row[3].toString());
				}
				if (row[4] != null) {
					clientMaster.setClientFullName(row[4].toString());
				}
				if (row[5] != null) {
					clientMaster.setUserId(row[5].toString());
				}
				PackageMaster packageMaster = new PackageMaster();
				if (row[6] != null) {
					packageMaster.setPackageDescription(row[6].toString());
				}
				if (row[7] != null) {
					if(!row[7].toString().equals("null")){
					packageMaster.setPackageId(Integer.parseInt(row[7]
							.toString()));
					}
				}
				clientMaster.setPackageMaster(packageMaster);
				if (row[8] != null) {
					clientMaster.setAddAt(DateConvertUtil.convertDateTime(row[8].toString()));
				}
				if (row[9] != null) {
					clientMaster.setEmailId(row[9].toString());
				}
				SQLQuery query1 = null;
				query1 = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								"SELECT GROUP_CONCAT(test_result_status SEPARATOR ', ') as flag FROM ehr_test_result where client_id="
										+ row[0].toString());
				List<String> rows1 = query1.list();
				for (String row1 : rows1) {
					if (row1 != null) {
						if (rows1.toString().contains("abnormal low")
								|| rows1.toString().contains("abnormal high")) {
							clientMaster.setAddBy("abnormal");
						} else {
							clientMaster.setAddBy("normal");
						}
					}
				}
				listClientMasters.add(clientMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listClientMasters;
	}
	
	@Override
	public ClientMaster getClientMastersByClientIdTestResults(Integer clientId) {
		ClientMaster clientMaster = new ClientMaster();
		SQLQuery query = null;
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select distinct(ehr_test_result.client_id),client_first_name,client_last_name,client_mob_no,client_full_name,client_user_id,package_desc,ehr_test_result.package_id,clients.add_at,client_email FROM ehr_test_result left join clients on ehr_test_result.client_id=clients.client_id left join ehr_package_master on ehr_test_result.package_id=ehr_package_master.package_id WHERE client_isactive='Y' and ehr_test_result.client_id = "+clientId);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				if (row[0] != null) {
					clientMaster
							.setClientId(Integer.parseInt(row[0].toString()));
				}
				if (row[1] != null) {
					clientMaster.setFirstName(row[1].toString());
				}
				if (row[2] != null) {
					clientMaster.setLastName(row[2].toString());
				}
				if (row[3] != null) {
					clientMaster.setMobNo(row[3].toString());
				}
				if (row[4] != null) {
					clientMaster.setClientFullName(row[4].toString());
				}
				if (row[5] != null) {
					clientMaster.setUserId(row[5].toString());
				}
				PackageMaster packageMaster = new PackageMaster();
				if (row[6] != null) {
					packageMaster.setPackageDescription(row[6].toString());
				}
				if (row[7] != null) {
					if(!row[7].toString().equals("null")){
					packageMaster.setPackageId(Integer.parseInt(row[7]
							.toString()));
					}
				}
				clientMaster.setPackageMaster(packageMaster);
				if (row[8] != null) {
					clientMaster.setAddAt(DateConvertUtil.convertDateTime(row[8].toString()));
				}
				if (row[9] != null) {
					clientMaster.setEmailId(row[9].toString());
				}
				SQLQuery query1 = null;
				query1 = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								"SELECT GROUP_CONCAT(test_result_status SEPARATOR ', ') as flag FROM ehr_test_result where client_id="
										+ row[0].toString());
				List<String> rows1 = query1.list();
				for (String row1 : rows1) {
					if (row1 != null) {
						if (rows1.toString().contains("abnormal low")
								|| rows1.toString().contains("abnormal high")) {
							clientMaster.setAddBy("abnormal");
						} else {
							clientMaster.setAddBy("normal");
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientMaster;
	}
	
	@Override
	public List<ClientMaster> getRecordByMemberIdTestResults(String memberId){
		List<ClientMaster> clientMasters = new ArrayList<ClientMaster>();
		SQLQuery query = null;
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select distinct(ehr_test_result.client_id),client_first_name,client_last_name,client_mob_no,client_full_name,client_user_id,package_desc,ehr_test_result.package_id,clients.add_at,client_email FROM ehr_test_result left join clients on ehr_test_result.client_id=clients.client_id left join ehr_package_master on ehr_test_result.package_id=ehr_package_master.package_id WHERE client_isactive='Y' and member_id = '"+memberId+"' group by ehr_test_result.client_id");
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ClientMaster clientMaster = new ClientMaster();
				if (row[0] != null) {
					clientMaster
							.setClientId(Integer.parseInt(row[0].toString()));
				}
				if (row[1] != null) {
					clientMaster.setFirstName(row[1].toString());
				}
				if (row[2] != null) {
					clientMaster.setLastName(row[2].toString());
				}
				if (row[3] != null) {
					clientMaster.setMobNo(row[3].toString());
				}
				if (row[4] != null) {
					clientMaster.setClientFullName(row[4].toString());
				}
				if (row[5] != null) {
					clientMaster.setUserId(row[5].toString());
				}
				PackageMaster packageMaster = new PackageMaster();
				if (row[6] != null) {
					packageMaster.setPackageDescription(row[6].toString());
				}
				if (row[7] != null) {
					if(!row[7].toString().equals("null")){
					packageMaster.setPackageId(Integer.parseInt(row[7]
							.toString()));
					}
				}
				clientMaster.setPackageMaster(packageMaster);
				if (row[8] != null) {
					clientMaster.setAddAt(DateConvertUtil.convertDateTime(row[8].toString()));
				}
				if (row[9] != null) {
					clientMaster.setEmailId(row[9].toString());
				}
				SQLQuery query1 = null;
				query1 = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								"SELECT GROUP_CONCAT(test_result_status SEPARATOR ', ') as flag FROM ehr_test_result where client_id="
										+ row[0].toString());
				List<String> rows1 = query1.list();
				for (String row1 : rows1) {
					if (row1 != null) {
						if (rows1.toString().contains("abnormal low")
								|| rows1.toString().contains("abnormal high")) {
							clientMaster.setAddBy("abnormal");
						} else {
							clientMaster.setAddBy("normal");
						}
					}
				}
				clientMasters.add(clientMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientMasters;
	}

	@Override
	public List<ClientMaster> clientTestResultsDropDown(String value,
			int startIndex) {
		List<ClientMaster> listClientMasters = new ArrayList<ClientMaster>();
		SQLQuery query = null;
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"SELECT ehr_analysis_comment.client_id,client_first_name,client_last_name,client_mob_no,client_full_name,client_user_id,clients.package_id,package_desc,clients.add_at,client_email,classification FROM ehr_analysis_comment inner join clients ON ehr_analysis_comment.client_id = clients.client_id left join ehr_package_master ON clients.package_id = ehr_package_master.package_id where client_isactive='Y' and ehr_analysis_comment.is_active='Y' and classification like '"+ value + "%'");
			query.setFirstResult(startIndex);
			query.setMaxResults(10);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ClientMaster clientMaster = new ClientMaster();
				if (row[0] != null) {
					clientMaster
							.setClientId(Integer.parseInt(row[0].toString()));
				}
				if (row[1] != null) {
					clientMaster.setFirstName(row[1].toString());
				}
				if (row[2] != null) {
					clientMaster.setLastName(row[2].toString());
				}
				if (row[3] != null) {
					clientMaster.setMobNo(row[3].toString());
				}
				if (row[4] != null) {
					clientMaster.setClientFullName(row[4].toString());
				}
				if (row[5] != null) {
					clientMaster.setUserId(row[5].toString());
				}
				PackageMaster packageMaster = new PackageMaster();
				if (row[6] != null && !row[6].equals("null")) {
					packageMaster.setPackageId(Integer.parseInt(row[6]
							.toString()));
				}
				if (row[7] != null) {
					packageMaster.setPackageDescription(row[7].toString());
				}
				clientMaster.setPackageMaster(packageMaster);
				if (row[8] != null) {
					clientMaster.setAddAt(DateConvertUtil.convertDateTime(row[8].toString()));
				}
				if (row[9] != null) {
					clientMaster.setEmailId(row[9].toString());
				}
				if (row[10] != null) {
					clientMaster.setAddBy(row[10].toString());
				}
				/*SQLQuery query1 = null;
				query1 = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								"SELECT GROUP_CONCAT(test_result_status SEPARATOR ', ') as flag FROM ehr_test_result where client_id="
										+ row[0].toString());
				List<String> rows1 = query1.list();
				for (String row1 : rows1) {
					if (row1 != null) {
						if (value.equals("abnormal")) {
							if (rows1.toString().contains("abnormal low")
									|| rows1.toString().contains(
											"abnormal high")) {
								clientMaster.setAddBy("abnormal");
							}
							listClientMasters.add(clientMaster);
						}
						if (value.equals("normal")) {
							if (!rows1.toString().contains("abnormal low")
									&& !rows1.toString().contains(
											"abnormal high")) {
								clientMaster.setAddBy("normal");
								listClientMasters.add(clientMaster);
							}
						}
					}
				}*/
				listClientMasters.add(clientMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listClientMasters;
	}

	@Override
	public String getCountClientMasterTestResult(String startDate,
			String endDate) {
		SQLQuery query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"select count(distinct(client_id)) from ehr_test_result WHERE "
						+ " (ehr_test_result.added_on BETWEEN '"+ startDate +"' AND '"+ endDate +"')");
		BigInteger count = (BigInteger) query.uniqueResult();
		return count.toString();
	}
	
	@Override
	public String saveEmail(EmailRecord emailRecord) {
		sessionFactory.getCurrentSession().save(emailRecord);
		return "Email Sent Successfully";
	}
	
	@Override
	public String saveMsg(SMSRecord smsRecord) {
		sessionFactory.getCurrentSession().save(smsRecord);
		return "Msg Sent Successfully";
	}

	@Override
	public String saveEmailLog(JSONArray jsonArray) {
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject object = (JSONObject) jsonArray.get(i);
			String sentBy = (String) object.get("sentBy");
			String sentOn = (String) object.get("sentOn");
			String sentToEmail = (String) object.get("sentToEmail");
			Integer emailTemplateId = (Integer) object.get("emailTemplateId");
			String emailMedicalAdvice = (String) object
					.get("emailMedicalAdvice");
			String sentToMobNo = (String) object.get("sentToMobNo");
			Integer smsTemplateId = (Integer) object.get("smsTemplateId");
			String smsMedicalAdvice = (String) object.get("smsMedicalAdvice");
			String emailSubject = (String) object.get("emailSubject");
			Integer clientId = (Integer) object.get("clientId");

			if (emailTemplateId != null) {
				String sql = "INSERT INTO ehr_email_record(sent_by,sent_on,sent_to_email,template_id,email_medical_advice,email_subject,client_id) "
						+ "values('"
						+ sentBy
						+ "','"
						+ sentOn
						+ "','"
						+ sentToEmail
						+ "',"
						+ emailTemplateId
						+ ",'"
						+ emailMedicalAdvice
						+ "','"
						+ emailSubject
						+ "','"
						+ clientId + "')";
				SQLQuery queryInsert = sessionFactory.getCurrentSession()
						.createSQLQuery(sql);
				queryInsert.executeUpdate();
			}
			if (smsTemplateId != null) {
				String sql = "INSERT INTO ehr_sms_record(sent_by,sent_on,sent_to_mob_no,sms_template_id,sms_medical_advice,client_id) "
						+ "values('"
						+ sentBy
						+ "','"
						+ sentOn
						+ "','"
						+ sentToMobNo
						+ "','"
						+ smsTemplateId
						+ "','"
						+ smsMedicalAdvice + "','" + clientId + "')";
				SQLQuery queryInsert = sessionFactory.getCurrentSession()
						.createSQLQuery(sql);
				queryInsert.executeUpdate();
			}

			/*
			 * String hql =
			 * "INSERT INTO ehr_follow_up(sent_by,sent_on,sent_to_email,template_id,email_medical_advice,sent_to_mob_no,sms_template_id,sms_medical_advice) "
			 * +
			 * "values('"+sentBy+"','"+sentOn+"','"+sentToEmail+"',"+emailTemplateId
			 * +
			 * ",'"+emailMedicalAdvice+"','"+sentToMobNo+"','"+smsTemplateId+"','"
			 * +smsMedicalAdvice+"')"; SQLQuery queryInsert =
			 * sessionFactory.getCurrentSession().createSQLQuery(hql);
			 * queryInsert.executeUpdate();
			 */
		}
		return "email saved";
	}

	@Override
	public List<ClientMaster> getAllClientMastersListGreaterThanClientId(
			Integer clientId) {
		List<ClientMaster> listClientMasters = new ArrayList<ClientMaster>();
		SQLQuery query = null;
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"SELECT client_first_name,client_middle_name,client_last_name,client_mob_no,client_address1,client_address2,client_address3,client_email,client_full_name,client_id from clients where client_id >"
									+ clientId);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ClientMaster clientMaster = new ClientMaster();
				if (row[0] != null) {
					clientMaster.setFirstName(row[0].toString());
				}
				if (row[1] != null) {
					clientMaster.setMiddleName(row[1].toString());
				}
				if (row[2] != null) {
					clientMaster.setLastName(row[2].toString());
				}
				if (row[3] != null) {
					clientMaster.setMobNo(row[3].toString());
				}
				if (row[4] != null) {
					clientMaster.setAddressLine1(row[4].toString());
				}
				if (row[5] != null) {
					clientMaster.setAddressLine2(row[5].toString());
				}
				if (row[6] != null) {
					clientMaster.setAddressLine3(row[6].toString());
				}
				if (row[7] != null) {
					clientMaster.setEmailId(row[7].toString());
				}
				if (row[8] != null) {
					clientMaster.setClientFullName(row[8].toString());
				}
				if (row[9] != null) {
					clientMaster
							.setClientId(Integer.parseInt(row[9].toString()));
				}
				listClientMasters.add(clientMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listClientMasters;
	}

	@Override
	public Integer getClientTimeline(Integer clientId) {
		Integer value = 1;
		SQLQuery query = null;
		SQLQuery query1 = null;
		SQLQuery query2 = null;
		SQLQuery query3 = null;
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select client_report_line_id,report_desc from client_report_line line inner join client_report_head head on line.client_report_id=head.client_report_id where client_checkup_id="
									+ clientId
									+ " and line.report_isactive='Y'");
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				/*if (value == 3) {
					return value;
				}*/
				if (row[0] != null) {
					value = 2;
					query1 = sessionFactory
							.getCurrentSession()
							.createSQLQuery(
									"Select verifyReport.verify_comment,verifyReport.reject_id FROM clientreportverify_clientreportline verifyLine inner join report_verification verifyReport ON verifyLine.client_report_verify_id = verifyReport.client_report_verify_id where client_report_line_id = "
											+ Integer.parseInt(row[0]
													.toString())
											+ " order by verifyLine.client_report_verify_id DESC LIMIT 1;");
					List<Object[]> reportRows = query1.list();
					for (Object[] rows1 : reportRows) {
						if (rows1[0] != null) {
							value = 3;
							break;
						}
					}
					break;
				}
			}
			
			if(rows.size() > 0){
				query2 = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								"SELECT ehr_follow_up_record_id,follow_up_date FROM ehr_follow_up_record where client_id="+clientId);
				List<Object[]> row=query2.list();
				if(row.size()>0){
					value=4;
				}
			
				if(row.size() > 0){
					query3 = sessionFactory
							.getCurrentSession()
							.createSQLQuery(
									"SELECT count,client_full_name FROM clients where client_id="+clientId);
					List<Object[]> row2=query3.list();
					for (Object[] rows1 : row2) {
						if (rows1[0] != null) {
							if(rows1[0].toString().equals("1")){
							value = 5;
							break;
							}
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

	@Override
	public String saveFollowUpRecord(JSONObject object) {
		try {
			String followUpDate = (String) object.get("followUpDate");
			String time = (String) object.get("time");
			Integer actionId = (Integer) object.get("actionId");
			Integer centerId = (Integer) object.get("centerId");
			Integer clientId = (Integer) object.get("clientId");
			String activities = (String) object.get("activities");
			String comment = (String) object.get("comment");
			String addedBy = (String) object.get("addedBy");
			String addedOn = (String) object.get("addedOn");
			String callResultStatus = (String) object.get("callResultStatus");
			Integer subActionId = (Integer) object.get("subActionId");
			Integer status = (Integer) object.get("status");
			Integer visitId = (Integer) object.get("visitId");
			String sql = "INSERT INTO ehr_follow_up_record(follow_up_date,time,action_id,center_id,activities,comment,added_by,added_on,call_result_status,client_id,sub_action_id,engmt_status,visit_id) "
					+ "values('"
					+ followUpDate
					+ "','"
					+ time
					+ "','"
					+ actionId
					+ "',"
					+ centerId
					+ ",'"
					+ activities
					+ "','"
					+ comment
					+ "','"
					+ addedBy
					+ "','"
					+ addedOn
					+ "','"
					+ callResultStatus
					+ "','" + clientId + "','" + subActionId + "','" + status + "','" + visitId + "')";
			SQLQuery queryInsert = sessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			queryInsert.executeUpdate();
			
				String statusSql = "UPDATE ehr_visit_status set engmt_status='"+status+"', engmt_status_on='"
						+ addedOn + "' where checkup_id = "+visitId;
				SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(statusSql);
				query.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "follow up record saved";
	}

	@Override
	public JSONArray getFollowUpRecord(Integer clientId) {
		SQLQuery query = null;
		JSONArray jsonArray = new JSONArray();
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select follow_up_date,time,ehr_follow_up_record.action_id,ehr_follow_up_record.center_id,activities,comment,ehr_follow_up_record.added_by,ehr_follow_up_record.added_on,ehr_follow_up_record_id,action,call_result_status,ehr_follow_up_record.sub_action_id,sub_action,engmt_status from ehr_follow_up_record left join ehr_action_master on ehr_follow_up_record.action_id=ehr_action_master.action_id left join ehr_sub_action_master ON ehr_follow_up_record.sub_action_id = ehr_sub_action_master.sub_action_id where visit_id="
									+ clientId);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				JSONObject jsonObject = new JSONObject();
				if (row[0] != null) {
					jsonObject.put("followUpDate", DateConvertUtil.convertDate(row[0].toString()));
				}
				if (row[1] != null) {
					jsonObject.put("time", row[1].toString());
				}
				if (row[2] != null) {
					jsonObject.put("actionId", row[2].toString());
				}
				if (row[3] != null) {
					jsonObject.put("centerId", row[3].toString());
				}
				if (row[4] != null) {
					jsonObject.put("activities", row[4].toString());
				}
				if (row[5] != null) {
					jsonObject.put("comment", row[5].toString());
				}
				if (row[6] != null) {
					jsonObject.put("addedBy", row[6].toString());
				}
				if (row[7] != null) {
					jsonObject.put("addedOn", row[7].toString());
				}
				if (row[8] != null) {
					jsonObject.put("ehrFollowUpRecordId", row[8].toString());
				}
				if (row[9] != null) {
					jsonObject.put("action", row[9].toString());
				}
				if (row[10] != null) {
					jsonObject.put("callResultStatus", row[10].toString());
				}
				if (row[11] != null) {
					jsonObject.put("subActionId", row[11].toString());
				}
				if (row[12] != null) {
					jsonObject.put("subAction", row[12].toString());
				}
				if (row[13] != null) {
					String engmtStatus = "";
					if(row[13].toString().equals("0")) {
						engmtStatus = "Pending";
					}else if(row[13].toString().equals("1")) {
						engmtStatus = "Re-schedule";
					}else {
						engmtStatus = "Completed";
					}
					jsonObject.put("engmtStatus", engmtStatus);
					jsonObject.put("engmtStatusId", row[13].toString());
				}else {
					jsonObject.put("engmtStatus","-");
					jsonObject.put("engmtStatusId", "0");
				}
				jsonArray.add(jsonObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public JSONObject getFollowUpRecordById(Integer ehrFollowUpRecordId) {
		SQLQuery query = null;
		JSONObject jsonObject = new JSONObject();
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select follow_up_date,time,ehr_follow_up_record.action_id,ehr_follow_up_record.center_id,activities,comment,ehr_follow_up_record.added_by,ehr_follow_up_record.added_on,ehr_follow_up_record_id,action,call_result_status,ehr_follow_up_record.sub_action_id,sub_action,engmt_status from ehr_follow_up_record inner join ehr_action_master on ehr_follow_up_record.action_id=ehr_action_master.action_id left join ehr_sub_action_master ON ehr_follow_up_record.sub_action_id = ehr_sub_action_master.sub_action_id where ehr_follow_up_record_id="
									+ ehrFollowUpRecordId);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				if (row[0] != null) {
					jsonObject.put("followUpDate", row[0].toString());
				}
				if (row[1] != null) {
					jsonObject.put("time", row[1].toString());
				}
				if (row[2] != null) {
					jsonObject.put("actionId", row[2].toString());
				}
				if (row[3] != null) {
					jsonObject.put("centerId", row[3].toString());
				}
				if (row[4] != null) {
					jsonObject.put("activities", row[4].toString());
				}
				if (row[5] != null) {
					jsonObject.put("comment", row[5].toString());
				}
				if (row[6] != null) {
					jsonObject.put("addedBy", row[6].toString());
				}
				if (row[7] != null) {
					jsonObject.put("addedOn", row[7].toString());
				}
				if (row[8] != null) {
					jsonObject.put("ehrFollowUpRecordId", row[8].toString());
				}
				if (row[9] != null) {
					jsonObject.put("action", row[9].toString());
				}
				if (row[10] != null) {
					jsonObject.put("callResultStatus", row[10].toString());
				}
				if (row[11] != null) {
					jsonObject.put("subActionId", row[11].toString());
				}
				if (row[12] != null) {
					jsonObject.put("subAction", row[12].toString());
				}
				if (row[13] != null) {
					String engmtStatus = "";
					if(row[13].toString().equals("0")) {
						engmtStatus = "Pending";
					}else if(row[13].toString().equals("1")) {
						engmtStatus = "Re-schedule";
					}else {
						engmtStatus = "Completed";
					}
					jsonObject.put("engmtStatus", engmtStatus);
					jsonObject.put("engmtStatusId", row[13].toString());
				}else {
					jsonObject.put("engmtStatus","-");
					jsonObject.put("engmtStatusId", "0");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	@Override
	public JSONArray getEmailRecordByClientId(Integer clientId) {
		SQLQuery query = null;
		JSONArray jsonArray = new JSONArray();
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select ehr_email_record_id,sent_by,sent_on,sent_to_email,template_id,email_medical_advice,email_subject,client_id,topic from ehr_email_record where client_id="
									+ clientId);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				JSONObject jsonObject = new JSONObject();
				if (row[0] != null) {
					jsonObject.put("ehrEmailRecordId", row[0].toString());
				}
				if (row[1] != null) {
					jsonObject.put("sentBy", row[1].toString());
				}
				if (row[2] != null) {
					jsonObject.put("sentOn", row[2].toString());
				}
				if (row[3] != null) {
					jsonObject.put("sentToEmail", row[3].toString());
				}
				if (row[4] != null) {
					jsonObject.put("templateId", row[4].toString());
				}
				if (row[5] != null) {
					jsonObject.put("emailMedicalAdvice", row[5].toString());
				}
				if (row[6] != null) {
					jsonObject.put("emailSubject", row[6].toString());
				}
				if (row[7] != null) {
					jsonObject.put("clientId", row[7].toString());
				}
				if (row[8] != null) {
					jsonObject.put("topic", row[8].toString());
				}
				jsonArray.add(jsonObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public JSONArray getSmsRecordByClientId(Integer clientId) {
		SQLQuery query = null;
		JSONArray jsonArray = new JSONArray();
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select ehr_sms_record_id,sent_by,sent_on,sent_to_mob_no,sms_template_id,sms_medical_advice,client_id,topic from ehr_sms_record where client_id="
									+ clientId);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				JSONObject jsonObject = new JSONObject();
				if (row[0] != null) {
					jsonObject.put("ehrSmsRecordId", row[0].toString());
				}
				if (row[1] != null) {
					jsonObject.put("sentBy", row[1].toString());
				}
				if (row[2] != null) {
					jsonObject.put("sentOn", row[2].toString());
				}
				if (row[3] != null) {
					jsonObject.put("sentToMobNo", row[3].toString());
				}
				if (row[4] != null) {
					jsonObject.put("smsTemplateId", row[4].toString());
				}
				if (row[5] != null) {
					jsonObject.put("smsMedicalAdvice", row[5].toString());
				}
				if (row[6] != null) {
					jsonObject.put("clientId", row[6].toString());
				}
				if (row[7] != null) {
					jsonObject.put("topic", row[7].toString());
				}
				jsonArray.add(jsonObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public JSONObject getEmailRecordByEmailId(Integer emailId) {
		SQLQuery query = null;
		JSONObject jsonObject = new JSONObject();
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select ehr_email_record_id,sent_by,sent_on,sent_to_email,template_id,email_medical_advice,email_subject,client_id,topic from ehr_email_record where ehr_email_record_id="
									+ emailId);
			List<Object[]> rows = query.list();
			// JSONArray jsonArray = new JSONArray();
			for (Object[] row : rows) {
				if (row[0] != null) {
					jsonObject.put("ehrEmailRecordId", row[0].toString());
				}
				if (row[1] != null) {
					jsonObject.put("sentBy", row[1].toString());
				}
				if (row[2] != null) {
					jsonObject.put("sentOn", row[2].toString());
				}
				if (row[3] != null) {
					jsonObject.put("sentToEmail", row[3].toString());
				}
				if (row[4] != null) {
					jsonObject.put("templateId", row[4].toString());
				}
				if (row[5] != null) {
					jsonObject.put("emailMedicalAdvice", row[5].toString());
				}
				if (row[6] != null) {
					jsonObject.put("emailSubject", row[6].toString());
				}
				if (row[7] != null) {
					jsonObject.put("clientId", row[7].toString());
				}
				if (row[8] != null) {
					jsonObject.put("topic", row[8].toString());
				}
				// jsonArray.add(jsonObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	@Override
	public JSONObject getSmsRecordBySmsId(Integer smsId) {
		SQLQuery query = null;
		JSONObject jsonObject = new JSONObject();
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select ehr_sms_record_id,sent_by,sent_on,sent_to_mob_no,sms_template_id,sms_medical_advice,client_id,topic from ehr_sms_record where ehr_sms_record_id="
									+ smsId);
			List<Object[]> rows = query.list();
			// JSONArray jsonArray = new JSONArray();
			for (Object[] row : rows) {
				if (row[0] != null) {
					jsonObject.put("ehrSmsRecordId", row[0].toString());
				}
				if (row[1] != null) {
					jsonObject.put("sentBy", row[1].toString());
				}
				if (row[2] != null) {
					jsonObject.put("sentOn", row[2].toString());
				}
				if (row[3] != null) {
					jsonObject.put("sentToMobNo", row[3].toString());
				}
				if (row[4] != null) {
					jsonObject.put("smsTemplateId", row[4].toString());
				}
				if (row[5] != null) {
					jsonObject.put("smsMedicalAdvice", row[5].toString());
				}
				if (row[6] != null) {
					jsonObject.put("clientId", row[6].toString());
				}
				if (row[7] != null) {
					jsonObject.put("topic", row[7].toString());
				}
				// jsonArray.add(jsonObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	@Override
	public JSONArray getEmailRecordByDate(Integer clientId, String startDate,
			String endDate) {
		SQLQuery query = null;
		JSONArray jsonArray = new JSONArray();
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select ehr_email_record_id,sent_by,sent_on,sent_to_email,template_id,email_medical_advice,email_subject,client_id,topic from ehr_email_record where ehr_email_record.sent_on BETWEEN '"+startDate+"' AND '"+endDate+"' and client_id="
									+ clientId);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				JSONObject jsonObject = new JSONObject();
				if (row[0] != null) {
					jsonObject.put("ehrEmailRecordId", row[0].toString());
				}
				if (row[1] != null) {
					jsonObject.put("sentBy", row[1].toString());
				}
				if (row[2] != null) {
					jsonObject.put("sentOn", row[2].toString());
				}
				if (row[3] != null) {
					jsonObject.put("sentToEmail", row[3].toString());
				}
				if (row[4] != null) {
					jsonObject.put("templateId", row[4].toString());
				}
				if (row[5] != null) {
					jsonObject.put("emailMedicalAdvice", row[5].toString());
				}
				if (row[6] != null) {
					jsonObject.put("emailSubject", row[6].toString());
				}
				if (row[7] != null) {
					jsonObject.put("clientId", row[7].toString());
				}
				if (row[8] != null) {
					jsonObject.put("topic", row[8].toString());
				}
				jsonArray.add(jsonObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public JSONArray getSmsRecordByDate(Integer clientId, String startDate,
			String endDate) {
		SQLQuery query = null;
		JSONArray jsonArray = new JSONArray();
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select ehr_sms_record_id,sent_by,sent_on,sent_to_mob_no,sms_template_id,sms_medical_advice,client_id,topic from ehr_sms_record where ehr_sms_record.sent_on BETWEEN '"+startDate+"' AND '"+endDate+"' and client_id="
									+ clientId);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				JSONObject jsonObject = new JSONObject();
				if (row[0] != null) {
					jsonObject.put("ehrSmsRecordId", row[0].toString());
				}
				if (row[1] != null) {
					jsonObject.put("sentBy", row[1].toString());
				}
				if (row[2] != null) {
					jsonObject.put("sentOn", row[2].toString());
				}
				if (row[3] != null) {
					jsonObject.put("sentToMobNo", row[3].toString());
				}
				if (row[4] != null) {
					jsonObject.put("smsTemplateId", row[4].toString());
				}
				if (row[5] != null) {
					jsonObject.put("smsMedicalAdvice", row[5].toString());
				}
				if (row[6] != null) {
					jsonObject.put("clientId", row[6].toString());
				}
				if (row[7] != null) {
					jsonObject.put("topic", row[7].toString());
				}
				jsonArray.add(jsonObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public String getCountClientTestResultsDropDown(String value) {
		/*Integer count = 0;
		SQLQuery query = null;
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select distinct(ehr_test_result.client_id),ehr_test_result.package_id FROM ehr_test_result WHERE test_result_status Like'"
									+ value + "%'");
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				if (row[0] != null) {
					SQLQuery query1 = null;
					query1 = sessionFactory
							.getCurrentSession()
							.createSQLQuery(
									"SELECT GROUP_CONCAT(test_result_status SEPARATOR ', ') as flag FROM ehr_test_result where client_id="
											+ row[0].toString());
					List<String> rows1 = query1.list();
					for (String row1 : rows1) {
						if (row1 != null) {
							if (value.equals("abnormal")) {
								if (rows1.toString().contains("abnormal low")
										|| rows1.toString().contains(
												"abnormal high")) {

								}
								count++;
							}
							if (value.equals("normal")) {
								if (!rows1.toString().contains("abnormal low")
										&& !rows1.toString().contains(
												"abnormal high")) {
									count++;
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		SQLQuery clientCount = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT count(distinct(client_id)) FROM ehr_analysis_comment where classification like '" +value+ "%'");
		BigInteger clientMasterCount = (BigInteger) clientCount.uniqueResult();
		return clientMasterCount.toString();
	}
	
	public String convertDate(String dateToBeConvert) {
		if (dateToBeConvert != null) {
			DateFormat inputDateFormatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			Date date = null;
			try {
				date = inputDateFormatter.parse(dateToBeConvert);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String outputDateFormatter = "yyyy-MM-dd";
			SimpleDateFormat sdf = new SimpleDateFormat(outputDateFormatter);
			String parsedDate = sdf.format(date);
			return parsedDate;
		} else {
			return null;
		}
	}

	@Override
	public String saveClientCheckUpDetails(JSONArray jsonArray) {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String addedOn = simpleDateFormat.format(date);
		try {
			for (int i = 0; i < jsonArray.size(); i++) {
				org.json.simple.JSONObject jsonObject2 = (org.json.simple.JSONObject) jsonArray.get(i);
				String apYear = (String) jsonObject2.get("AP_YEAR");
				Long apAppNo = (Long) jsonObject2.get("AP_APP_NO");
				String apMemberId = (String) jsonObject2.get("AP_MEMBER_ID");
				Long testCode = (Long) jsonObject2.get("TESTCODE");
				String status = (String) jsonObject2.get("STATUS");
				String reason = (String) jsonObject2.get("REASON");
				String mmMemberId = (String) jsonObject2.get("MM_MEMBER_ID");
				String mmCity = (String) jsonObject2.get("MM_CITY");
				String mmStateId = (String) jsonObject2.get("MM_STATE");
				String mmEmail = (String) jsonObject2.get("MM_EMAIL");
				String mmRenewalDueDate = null;
				if(jsonObject2.get("MM_RENEWAL_DUEDATE") != null) {
					mmRenewalDueDate = (String) jsonObject2.get("MM_RENEWAL_DUEDATE");
					mmRenewalDueDate = convertDate(mmRenewalDueDate);
				}
				String mmMembershipStatus = (String) jsonObject2
						.get("MM_MEMBERSHIP_STATUS");
				String mmMemberType = (String) jsonObject2.get("MM_MEMBER_TYPE");
				String mmSubCity = (String) jsonObject2.get("MM_SUBCITY");
				Double mmCorpSchemeNo = (Double) jsonObject2.get("MM_CORP_SCHEME_NO");
				String mmCorpSrNo = (String) jsonObject2.get("MM_CORP_SR_NO");
				Double mmCurrentLevel = (Double) jsonObject2.get("MM_CURR_LEVEL");
				String mmIndusIdYN = (String) jsonObject2.get("MM_INDUS_ID_YN");
				Double mmParentId = (Double) jsonObject2.get("MM_PARENT_ID");
				Double mmLangId = (Double) jsonObject2.get("MM_LANG_ID");
				Double mmPrCountry = (Double) jsonObject2.get("MM_PR_COUNTRY");
	
				String sql = "INSERT INTO ehr_client_checkup_details(ap_year,ap_app_no,ap_member_id,test_code,status,reason,mm_member_id,mm_city,mm_state,mm_email,mm_renewal_due_date,mm_membership_status,"
						+ "mm_member_type,mm_sub_city,mm_corp_scheme_no,mm_corp_sr_no,mm_curr_level,mm_indus_id_yn,mm_parent_id,mm_lang_id,mm_pr_country,added_on) "
						+ "values('"+apYear+ "','"+apAppNo+ "','"+apMemberId+ "',"+testCode+ ",'"
						+ status
						+ "','"
						+ reason
						+ "','"
						+ mmMemberId
						+ "','"
						+ mmCity
						+ "','"
						+ mmStateId
						+ "','"
						+ mmEmail
						+ "'," ;
						if(mmRenewalDueDate != null) {
							sql = sql + "'"+mmRenewalDueDate+"'" ;
						} else {
							Date renewDate = null;
							sql = sql + renewDate ;
						}
						sql = sql + ",'"
						+ mmMembershipStatus
						+ "','"
						+ mmMemberType
						+ "','"
						+ mmSubCity
						+ "','"
						+ mmCorpSchemeNo
						+ "','"
						+ mmCorpSrNo
						+ "','"
						+ mmCurrentLevel
						+ "',"
						+ "'"
						+ mmIndusIdYN
						+ "','"
						+ mmParentId
						+ "','"
						+ mmLangId
						+ "','"
						+ mmPrCountry
						+ "','" + addedOn + "')"
						+ " ON DUPLICATE KEY UPDATE status = '"+status+"',reason = '"+reason+"',mm_member_id = '"+mmMemberId+"',mm_city = '"+mmCity+"',"
						+ " mm_state = '"+mmStateId+"',mm_email = '"+mmEmail+"', mm_renewal_due_date = ";
						if(mmRenewalDueDate != null) {
							sql = sql + "'"+mmRenewalDueDate+"'" ;
						} else {
							Date renewDate = null;
							sql = sql + renewDate ;
						}
						sql = sql + ",mm_membership_status = '"+mmMembershipStatus+"'," 
						+ " mm_member_type = '"+mmMemberType+"',mm_sub_city = '"+mmSubCity+"',mm_corp_scheme_no = '"+mmCorpSchemeNo+"',mm_corp_sr_no = '"+mmCorpSrNo+"',"
						+ " mm_curr_level = '"+mmCurrentLevel+"',mm_indus_id_yn = '"+mmIndusIdYN+"',mm_parent_id = '"+mmParentId+"',mm_lang_id = '"+mmLangId+"',"
						+ " mm_pr_country = '"+mmPrCountry+"',added_on = '"+addedOn+"'";
				SQLQuery queryInsert = sessionFactory.getCurrentSession()
						.createSQLQuery(sql);
				queryInsert.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "record saved successfully";
	}

	@Override
	public JSONArray getClientCheckUpDetails() {
		SQLQuery query = null;
		JSONArray jsonArray = new JSONArray();
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"SELECT ap_year,ap_app_no,ap_member_id,test_code FROM ehr_client_checkup_details");
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				JSONObject jsonObject = new JSONObject();
				if (row[0] != null) {
					jsonObject.put("apYear", row[0].toString());
				}
				if (row[1] != null) {
					jsonObject
							.put("apAppNo", Long.parseLong(row[1].toString()));
				}
				if (row[2] != null) {
					jsonObject.put("apMemberId",
							Long.parseLong(row[2].toString()));
				}
				if (row[3] != null) {
					jsonObject.put("testCode",
							Long.parseLong(row[3].toString()));
				}
				jsonArray.add(jsonObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public JSONObject getEmailSmsCountByClientId(Integer clientId) {
		SQLQuery query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT (SELECT count(ehr_email_record_id) from ehr_email_record where client_id = "
								+ clientId
								+ ") as emailCount,(SELECT count(ehr_sms_record_id) from ehr_sms_record where client_id = "
								+ clientId + ") as smsCount");
		JSONObject jsonObject = new JSONObject();
		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			if (row[0] != null) {
				jsonObject.put("emailCount", row[0].toString());
			}
			if (row[1] != null) {
				jsonObject.put("smsCount", row[1].toString());
			}
		}
		return jsonObject;
	}

	@Override
	public String submitFeedback(JSONObject object) {
		try {
			Integer clientId = (Integer) object.get("clientId");
			String sentToEmail = (String) object.get("sentToEmail");
			String sentOn = (String) object.get("sentOn");
			String feedback = (String) object.get("feedback");

			String sql = "INSERT INTO ehr_feedback(sent_on,sent_to_email,client_id,feedback) "
					+ "values('"
					+ sentOn
					+ "','"
					+ sentToEmail
					+ "','"
					+ clientId + "','" + feedback + "')";
			SQLQuery queryInsert = sessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			queryInsert.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Feedback saved successfully";
	}

	@Override
	public String saveSelfClientReport(
			List<ClientSelfUploadReport> clientSelfUploadReportList) {
		try {
			for (ClientSelfUploadReport clientSelfUploadReport : clientSelfUploadReportList) {
				sessionFactory.getCurrentSession().saveOrUpdate(
						clientSelfUploadReport);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "reports saved successfully";
	}

	@Override
	public List<ClientSelfUploadReport> getClientSelfUploadReport(
			Integer clientId) {
		List<ClientSelfUploadReport> clientSelfUploadReportList = new ArrayList<ClientSelfUploadReport>();
		SQLQuery query = null;
		SQLQuery query1 = null;
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select client_self_report_id,report_filepath,added_by,added_on,client_id,comment,report_isactive,edit_report_status from ehr_client_self_upload_report where client_id="
									+ clientId + " and report_isactive='Y'");
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ClientSelfUploadReport clientSelfUploadReport = new ClientSelfUploadReport();
				ClientReportHead clientReportHead = new ClientReportHead();
				if (row[0] != null) {
					clientSelfUploadReport.setClientSelfReportId(Integer
							.parseInt(row[0].toString()));
				}
				if (row[1] != null) {
					clientSelfUploadReport.setFilePath(row[1].toString());
				}
				if (row[2] != null) {
					clientSelfUploadReport.setAddedBy(row[2].toString());
				}
				if (row[3] != null) {
					String addedOn = DateConvertUtil.convertDate(row[3]
							.toString());
					clientSelfUploadReport.setAddedOn(addedOn);
				}
				if (row[4] != null) {
					ClientMaster clientMaster = new ClientMaster();
					clientMaster
							.setClientId(Integer.parseInt(row[4].toString()));
					clientSelfUploadReport.setClientMaster(clientMaster);
				}
				if (row[5] != null) {
					clientSelfUploadReport.setComment(row[5].toString());
				}
				if (row[6] != null) {
					clientSelfUploadReport.setReportIsActive(row[6].toString());
				}
				if (row[7] != null) {
					clientSelfUploadReport.setEditReportStatus(Integer
							.parseInt(row[7].toString()));
				}
				clientSelfUploadReportList.add(clientSelfUploadReport);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientSelfUploadReportList;
	}

	@Override
	public String deleteSelfClientReport(
			ClientSelfUploadReport clientSelfUploadReport) {
		ClientSelfUploadReport clientSelfUploadReport2 = new ClientSelfUploadReport();
		try {
			clientSelfUploadReport2 = (ClientSelfUploadReport) sessionFactory
					.getCurrentSession().get(ClientSelfUploadReport.class,
							clientSelfUploadReport.getClientSelfReportId());
			clientSelfUploadReport2.setReportIsActive("D");
			clientSelfUploadReport2.setModifyBy(clientSelfUploadReport
					.getModifyBy());
			clientSelfUploadReport2.setModifyOn(clientSelfUploadReport
					.getModifyOn());
			sessionFactory.getCurrentSession().update(clientSelfUploadReport2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Report deleted";
	}

	@Override
	public List<ClientUploadReport> getVisitByVisitId(Integer clientId,
			Integer visitId) {
		List<ClientUploadReport> clientUploadReports = new ArrayList<ClientUploadReport>();
		SQLQuery query = null;
		SQLQuery query1 = null;
		SQLQuery query2 = null;
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select report_desc,report_filepath,head.added_by,head.added_on,client_report_line_id,test_id,line.package_id,head.center_id,center_name,edit_report_status,report_date,pck.analysis_flag,line.is_email_send from client_report_line line inner join client_report_head head ON line.client_report_id = head.client_report_id left join ehr_center_master ON head.center_id = ehr_center_master.center_id left join ehr_package_master pck ON pck.package_id = line.package_id where client_id ="
									+ clientId
									+ " and client_checkup_id ="
									+ visitId
									+ " and line.report_isactive = 'Y' ");			
			
			
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ClientUploadReport clientUploadReport = new ClientUploadReport();
				ClientReportHead clientReportHead = new ClientReportHead();
				if (row[0] != null)
					clientUploadReport.setReportDescription(row[0].toString());
				else
					clientUploadReport.setReportDescription("");

				if (row[1] != null)
					clientUploadReport.setFilePath(row[1].toString());
				else
					clientUploadReport.setFilePath("");

				if (row[2] != null) {
					clientReportHead.setAddedBy(row[2].toString());
				}

				if (row[3] != null) {
					String addedOn = DateConvertUtil.convertDateTime(row[3]
							.toString());
					clientReportHead.setAddedOn(addedOn);
				}

				clientUploadReport.setClientReportHead(clientReportHead);

				if (row[4] != null) {
					List<ReportVerification> reportVerificationSet = new ArrayList<ReportVerification>();
					clientUploadReport.setClientReportLineId(Integer
							.parseInt(row[4].toString()));
					query1 = sessionFactory
							.getCurrentSession()
							.createSQLQuery(
									"Select verifyReport.reject_id,verifyReport.reject_comment,verifyReport.verify_comment,verifyReport.approved_by,verifyReport.approved_on,reject_reason FROM clientreportverify_clientreportline verifyLine inner join report_verification verifyReport ON verifyLine.client_report_verify_id = verifyReport.client_report_verify_id left join ehr_reject_master On verifyReport.reject_id=ehr_reject_master.reject_id where client_report_line_id = "
											+ clientUploadReport.getClientReportLineId()
											+ " order by verifyLine.client_report_verify_id DESC LIMIT 1;");
					List<Object[]> reportRows = query1.list();

					for (Object[] rows1 : reportRows) {
						ReportVerification reportVerification = new ReportVerification();
						if (rows1[0] != null) {
							RejectMaster rejectMaster = new RejectMaster();
							rejectMaster.setRejectId(Integer.parseInt(rows1[0]
									.toString()));
							rejectMaster.setRejectReason(rows1[5].toString());
							reportVerification.setRejectMaster(rejectMaster);
							// reportVerification.setReasonToReject(rows1[0].toString());
						}
						if (rows1[1] != null) {
							reportVerification.setRejectComment(rows1[1]
									.toString());
						}
						if (rows1[2] != null) {
							reportVerification.setVerifyComment(rows1[2]
									.toString());
						}
						if (rows1[3] != null) {
							reportVerification.setApprovedBy(rows1[3]
									.toString());
						}
						if (rows1[4] != null) {
							reportVerification.setApprovedOn(rows1[4]
									.toString());
						}
						reportVerificationSet.add(reportVerification);
					}
					clientUploadReport
							.setReportVerificationSet(reportVerificationSet);
					
					//For Data Entry Comments
					String hql = "from DataEntryVerification WHERE clientReportLineId = :clientReportLineId ORDER BY verifyId DESC";
					Query hqlQuery = sessionFactory.getCurrentSession().createQuery(hql);
					hqlQuery.setParameter("clientReportLineId", clientUploadReport.getClientReportLineId());
					hqlQuery.setMaxResults(1);
					List<DataEntryVerification> dataEntryVerifications1 = (List<DataEntryVerification>) hqlQuery.list();
					List<DataEntryVerification> dataEntryVerifications = new ArrayList<DataEntryVerification>();
					for(DataEntryVerification dataEntryVerification : dataEntryVerifications1) {
						dataEntryVerifications.add(dataEntryVerification);
					}
					clientUploadReport.setDataEntryVerifications(dataEntryVerifications);
				}
				if (row[5] != null) {
					TestMaster testMaster = new TestMaster();
					testMaster.setTestId(Integer.parseInt(row[5].toString()));
					clientUploadReport.setTestMaster(testMaster);
				}
				if (row[6] != null) {
					PackageMaster packageMaster = new PackageMaster();
					packageMaster.setPackageId(Integer.parseInt(row[6]
							.toString()));
					clientUploadReport.setPackageMaster(packageMaster);
				}

				CentreMaster centreMaster = new CentreMaster();
				// if(Integer.parseInt(row[4].toString())>=333754){
				if (row[7] != null) {
					centreMaster
							.setCentreId(Integer.parseInt(row[7].toString()));
				}
				if (row[8] != null) {
					centreMaster.setCentreName(row[8].toString());
				}
				/*
				 * } else{ query2 = sessionFactory .getCurrentSession()
				 * .createSQLQuery(
				 * "SELECT centre_id,centre_name FROM indus.centre_info where centre_id= "
				 * +row[7].toString()); List<Object[]> centreRows =
				 * query2.list();
				 * 
				 * for (Object[] centre : centreRows) { if (centre[0] != null){
				 * centreMaster
				 * .setCentreId(Integer.parseInt(centre[0].toString())); } if
				 * (centre[1] != null){
				 * centreMaster.setCentreName(centre[1].toString()); } } }
				 */

				clientReportHead.setCentreMaster(centreMaster);
				if (row[9] != null) {
					clientUploadReport.setEditReportStatus(Integer
							.parseInt(row[9].toString()));
				}
				if (row[10] != null && !row[10].toString().equals("")) {
					String reportDate = DateConvertUtil.convertDate(row[10]
								.toString());
					clientUploadReport.setReportDate(reportDate);
				}if (row[11] != null && !row[11].toString().equals("")) {
					Character analysisFlag = ((Character)(row[11]));
					//clientUploadReport.getPackageMaster().getAnalysisFlag();
					clientUploadReport.getPackageMaster().setAnalysisFlag(analysisFlag);
				}if (row[12] != null && !row[12].toString().equals("")) {
					clientUploadReport.setIsEmailSend(row[12].toString());
				}else {
					clientUploadReport.setReportDate("");
				}
				clientUploadReports.add(clientUploadReport);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientUploadReports;
	}

	@Override
	public ClientMaster isClientLocked(Integer clientId, HttpServletRequest request) {
		Boolean flag = false;
		ClientMaster clientMaster = new ClientMaster();
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(ClientMaster.class);
			if (clientId != null){
				/*criteria.add(Restrictions.eq("userId", userId));
				clientMaster = (ClientMaster) criteria.uniqueResult();*/
				
				SQLQuery query = null;
				try {
					query = sessionFactory
							.getCurrentSession()
							.createSQLQuery("select client_locked,client_locked_by,user_session_id, concat(first_name,' ',last_name) as name"
									+ " from clients left join ehr_users on user_id = client_locked_by where client_id ='"+clientId+"'");
					List<Object[]> rows = query.list();
					for (Object[] row : rows) {
						if (row[0] != null) {
							clientMaster.setClientLocked(Integer.parseInt(row[0].toString()));
						}if (row[1] != null) {
							clientMaster.setClientLockedBy(Integer.parseInt(row[1].toString()));
						}if (row[2] != null) {
							clientMaster.setUserSessionId(row[2].toString());
						}if (row[3] != null) {
							clientMaster.setRemark(row[3].toString());
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			if (clientMaster.getClientLocked() == 1) {
				flag = true;
			}

			HttpSession session = request.getSession();
			/*String sessionId = session.getId();
			HttpSession getSessionBySessionId = MySessionListener
					.find(clientMaster.getUserSessionId());
			if (getSessionBySessionId != null) {
				flag = true;
			} else {
				flag = false;
			}*/
			
			//code for locked by user id access
			if(flag==true && clientMaster.getClientLockedBy().equals(Integer.parseInt((String)session.getAttribute("userId")))){
				flag = false;
				clientMaster.setClientLocked(0);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientMaster;
	}

	@Override
	public Boolean removeClientLocked(Integer clientId) {
		Boolean flag = false;
		ClientMaster clientMaster = new ClientMaster();
		try {
			if (clientId != null){
				/*Criteria criteria = sessionFactory.getCurrentSession()
						.createCriteria(ClientMaster.class);
				criteria.add(Restrictions.eq("userId", userId));
				clientMaster = (ClientMaster) criteria.uniqueResult();
				clientMaster.setClientLocked(0);
				clientMaster.setClientLockedBy(0);
				clientMaster.setUserSessionId("");*/
				
				try {
					SQLQuery query = sessionFactory
							.getCurrentSession()
							.createSQLQuery("update clients set client_locked=0,client_locked_by=0,user_session_id='' where client_id='"+clientId+"'");
					query.executeUpdate();
				}catch(Exception e){
					e.printStackTrace();
				}
				
			}
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public String getCountForRepresentativeDashboard(String centerId,
			String startDate, String endDate, String userName) {
		String count = "";

		SQLQuery clientCount = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						/*"Select count(client_id) from checkup_master where center_id in( '"
								+ centerId
								+ "' ) and STR_TO_DATE(checkup_master.checkup_date, '%m/%d/%Y') BETWEEN STR_TO_DATE('"
								+ startDate
								+ "', '%m/%d/%Y') AND STR_TO_DATE('" + endDate
								+ "', '%m/%d/%Y');"*/
						
						//changed by Kishor
						"Select count(client_id) from checkup_master where center_id in( '"
						+ centerId
						+ "' ) and checkup_master.checkup_date between '"+startDate+"' AND '"+endDate+"';"
						
						);
		BigInteger clientMasterCount = (BigInteger) clientCount.uniqueResult();

		SQLQuery reportCount = sessionFactory
				.getCurrentSession()
				.createSQLQuery(/*"SELECT COUNT(*) FROM client_report_head head inner join client_report_line line ON head.client_report_id = line.client_report_id"
						+" inner join checkup_master checkup on head.client_checkup_id = checkup.checkup_id where "
						+" STR_TO_DATE(checkup.checkup_date, '%m/%d/%Y') BETWEEN STR_TO_DATE('"+startDate+"', '%m/%d/%Y') AND STR_TO_DATE('"+endDate+"', '%m/%d/%Y')"*/
						
						//Changed by Kishor
						"SELECT COUNT(*) FROM client_report_head head inner join client_report_line line ON head.client_report_id = line.client_report_id"
						+" inner join checkup_master checkup on head.client_checkup_id = checkup.checkup_id where checkup.center_id in("+centerId+") and"
						+" checkup.checkup_date between '"+startDate+"' AND '"+endDate+"' ");
						/*"SELECT count(client_report_line_id) FROM client_report_line where client_report_id in(SELECT client_report_id FROM client_report_head where client_id in(Select client_id from checkup_master where STR_TO_DATE(checkup_master.checkup_date, '%m/%d/%Y') BETWEEN STR_TO_DATE('"
								+ startDate
								+ "','%m/%d/%Y') AND STR_TO_DATE('"
								+ endDate + "','%m/%d/%Y')));"*/
		BigInteger clientReportCount = (BigInteger) reportCount.uniqueResult();

		SQLQuery uploadReportByUser = sessionFactory
				.getCurrentSession()
				.createSQLQuery(/*"SELECT COUNT(*) FROM client_report_head head inner join client_report_line line ON head.client_report_id = line.client_report_id"
							+" inner join checkup_master checkup on head.client_checkup_id = checkup.checkup_id where "
							+" STR_TO_DATE(checkup.checkup_date, '%m/%d/%Y') BETWEEN STR_TO_DATE('"+startDate+"', '%m/%d/%Y') AND STR_TO_DATE('"+endDate+"', '%m/%d/%Y')"
							+" and added_by = '"+userName+"';"*/
						
						//Changed by kishor
						"SELECT COUNT(*) FROM client_report_head head inner join client_report_line line ON head.client_report_id = line.client_report_id"
						+" inner join checkup_master checkup on head.client_checkup_id = checkup.checkup_id where checkup.center_id in("+centerId+") and "
						+" checkup.checkup_date between '"+startDate+"' AND '"+endDate+"' "
						+" and added_by = '"+userName+"';" );
		
						/*"SELECT count(client_report_line_id) FROM client_report_line where client_report_id in (SELECT client_report_id FROM client_report_head where added_by='"
								+ userName
								+ "' and client_id in (Select client_id from checkup_master where STR_TO_DATE(checkup_master.checkup_date, '%m/%d/%Y') BETWEEN STR_TO_DATE('"
								+ startDate
								+ "', '%m/%d/%Y') AND STR_TO_DATE('"
								+ endDate
								+ "', '%m/%d/%Y')));"*/
		BigInteger uploadReportCountByUser = (BigInteger) uploadReportByUser
				.uniqueResult();

		SQLQuery uploadReportRejectedByUser = sessionFactory
				.getCurrentSession()
				.createSQLQuery(/*"SELECT count(*) FROM client_report_head head inner join client_report_line line on head.client_report_id = line.client_report_id"
							+" inner join clientreportverify_clientreportline report_line on line.client_report_line_id = report_line.client_report_line_id	inner join"
							+" report_verification report_ver on report_line.client_report_verify_id = report_ver.client_report_verify_id inner join"
							+" checkup_master checkup on head.client_checkup_id = checkup.checkup_id where reject_id is not null and "
							+" STR_TO_DATE(checkup.checkup_date, '%m/%d/%Y') BETWEEN STR_TO_DATE('"+startDate+"', '%m/%d/%Y') AND STR_TO_DATE('"+endDate+"', '%m/%d/%Y')"
							+" and added_by = '"+userName+"';"*/
						
						//Changed by kishor
						"SELECT count(*) FROM client_report_head head inner join client_report_line line on head.client_report_id = line.client_report_id"
						+" inner join clientreportverify_clientreportline report_line on line.client_report_line_id = report_line.client_report_line_id	inner join"
						+" report_verification report_ver on report_line.client_report_verify_id = report_ver.client_report_verify_id inner join"
						+" checkup_master checkup on head.client_checkup_id = checkup.checkup_id where checkup.center_id in("+centerId+") and reject_id is not null and "
						+" checkup.checkup_date between '"+startDate+"' AND '"+endDate+"' "
						+" and added_by = '"+userName+"';" );
						/*"SELECT count(client_report_verify_id) FROM report_verification where reject_id is not null and client_report_verify_id in (SELECT max(client_report_verify_id) FROM clientreportverify_clientreportline where client_report_line_id in (SELECT client_report_line_id FROM client_report_line where report_isactive='Y' and client_report_id in (SELECT client_report_id FROM client_report_head where added_by = '"
								+ userName
								+ "' and client_id in (Select client_id from checkup_master where STR_TO_DATE(checkup_master.checkup_date, '%m/%d/%Y') BETWEEN STR_TO_DATE('"
								+ startDate
								+ "', '%m/%d/%Y') AND STR_TO_DATE('"
								+ endDate
								+ "', '%m/%d/%Y')))) group by client_report_line_id );"*/
		BigInteger rejectedReportByUser = (BigInteger) uploadReportRejectedByUser
				.uniqueResult();
		
		//Added by kishor for total rejected report count
		SQLQuery uploadReportRejectedAll = sessionFactory
				.getCurrentSession()
				.createSQLQuery(						
						//Changed by kishor
						"SELECT count(*) FROM client_report_head head inner join client_report_line line on head.client_report_id = line.client_report_id"
						+" inner join clientreportverify_clientreportline report_line on line.client_report_line_id = report_line.client_report_line_id	inner join"
						+" report_verification report_ver on report_line.client_report_verify_id = report_ver.client_report_verify_id inner join"
						+" checkup_master checkup on head.client_checkup_id = checkup.checkup_id where checkup.center_id in("+centerId+") and reject_id is not null and line.report_isactive !='D' and "
						+" checkup.checkup_date between '"+startDate+"' AND '"+endDate+"';" );
						
		BigInteger rejectedReportAll = (BigInteger) uploadReportRejectedAll
				.uniqueResult();
		
		//Added by Kishor for Total report uploaded group by Visits
		SQLQuery reportCountVisitsWise = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						//Changed by Kishor
						"select count(*) from (SELECT COUNT(*) FROM client_report_head head inner join client_report_line line ON head.client_report_id = line.client_report_id"
						+" inner join checkup_master checkup on head.client_checkup_id = checkup.checkup_id where checkup.center_id in("+centerId+") and line.report_isactive !='D' and"
						+" checkup.checkup_date between '"+startDate+"' AND '"+endDate+"' group by  head.client_checkup_id ) as count");
						
		BigInteger clientReportCountVisitWise = (BigInteger) reportCountVisitsWise.uniqueResult();
		
		
		//Added by Kishor for Total report not uploaded group by Visits
				SQLQuery reportNotUplodedVisitWise = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								//Changed by Kishor
								"Select count(checkup_id) from checkup_master"
								+ " where center_id in("+centerId+") "
								+ " and checkup_id  not in(SELECT (checkup.checkup_id) FROM"
								+ " client_report_head head INNER JOIN "
								+ " client_report_line line ON head.client_report_id = line.client_report_id"
								+ " INNER JOIN"
								+ " checkup_master checkup ON head.client_checkup_id = checkup.checkup_id"
								+ " WHERE checkup.center_id IN ("+centerId+") and line.report_isactive !='D'"
								+ " AND checkup.checkup_date BETWEEN '"+startDate+"' AND '"+endDate+"'"
								+ " GROUP BY head.client_checkup_id )"
								+ " and checkup_master.checkup_date between '"+startDate+"' AND '"+endDate+"' ");
								
				BigInteger clientReportNotUplodedVisitWise = (BigInteger) reportNotUplodedVisitWise.uniqueResult();

		count = clientMasterCount + "_" + clientReportCount + "_"
				+ uploadReportCountByUser + "_" + rejectedReportByUser + "_" + rejectedReportAll + "_" + clientReportCountVisitWise + "_" +clientReportNotUplodedVisitWise;
		return count;
	}

	@Override
	public JSONArray rejectedReportList(String centerId, String startDate,
			String endDate, String userName, String listData) {
		SQLQuery query = null;
		JSONArray jsonArray = new JSONArray();
		try {
			
			if(listData.equalsIgnoreCase("all")) {
				query = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								//Add by Kishor
								
								"SELECT report_ver.client_report_verify_id,report_ver.reject_id,reject_comment,reject_reason,approved_by,"
								+" report_line.client_report_line_id,report_filepath,head.client_id,report_desc,client_first_name,client_middle_name,client_last_name,client_user_id,ehr_package_master.package_short_desc,checkup.checkup_date "
								+" FROM client_report_head head inner join client_report_line line on head.client_report_id = line.client_report_id"
								+" inner join clientreportverify_clientreportline report_line on line.client_report_line_id = report_line.client_report_line_id	inner join"
								+" report_verification report_ver on report_line.client_report_verify_id = report_ver.client_report_verify_id inner join"
								+" checkup_master checkup on head.client_checkup_id = checkup.checkup_id inner join ehr_reject_master reject on reject.reject_id = report_ver.reject_id "
								+" inner join clients on head.client_id = clients.client_id left join ehr_package_master on checkup.package_id = ehr_package_master.package_id where checkup.center_id in("+centerId+") and report_ver.reject_id is not null and line.report_isactive !='D' and "
								+" checkup.checkup_date between '"+startDate+"' AND '"+endDate+"';");
			}else {
				query = sessionFactory
						.getCurrentSession()
						.createSQLQuery(/*"SELECT report_ver.client_report_verify_id,report_ver.reject_id,reject_comment,reject_reason,approved_by,"
								+" report_line.client_report_line_id,report_filepath,head.client_id,report_desc,client_first_name,client_middle_name,client_last_name,client_user_id "
								+" FROM client_report_head head inner join client_report_line line on head.client_report_id = line.client_report_id"
								+" inner join clientreportverify_clientreportline report_line on line.client_report_line_id = report_line.client_report_line_id	inner join"
								+" report_verification report_ver on report_line.client_report_verify_id = report_ver.client_report_verify_id inner join"
								+" checkup_master checkup on head.client_checkup_id = checkup.checkup_id inner join ehr_reject_master reject on reject.reject_id = report_ver.reject_id "
								+" inner join clients on head.client_id = clients.client_id where report_ver.reject_id is not null and "
								+" STR_TO_DATE(checkup.checkup_date, '%m/%d/%Y') BETWEEN STR_TO_DATE('"+startDate+"', '%m/%d/%Y') AND STR_TO_DATE('"+endDate+"', '%m/%d/%Y')"
								+" and head.added_by = '"+userName+"';"*/
								
								
								//Changed by Kishor
								
								"SELECT report_ver.client_report_verify_id,report_ver.reject_id,reject_comment,reject_reason,approved_by,"
								+" report_line.client_report_line_id,report_filepath,head.client_id,report_desc,client_first_name,client_middle_name,client_last_name,client_user_id,ehr_package_master.package_short_desc,checkup.checkup_date "
								+" FROM client_report_head head inner join client_report_line line on head.client_report_id = line.client_report_id"
								+" inner join clientreportverify_clientreportline report_line on line.client_report_line_id = report_line.client_report_line_id	inner join"
								+" report_verification report_ver on report_line.client_report_verify_id = report_ver.client_report_verify_id inner join"
								+" checkup_master checkup on head.client_checkup_id = checkup.checkup_id inner join ehr_reject_master reject on reject.reject_id = report_ver.reject_id "
								+" inner join clients on head.client_id = clients.client_id left join ehr_package_master on checkup.package_id = ehr_package_master.package_id where checkup.center_id in("+centerId+") and report_ver.reject_id is not null and line.report_isactive !='D' and "
								+" checkup.checkup_date between '"+startDate+"' AND '"+endDate+"' "
								+" and head.added_by = '"+userName+"';"
								
								
								);
								/*"SELECT report_verification.client_report_verify_id,report_verification.reject_id,reject_comment,reject_reason,approved_by,clientreportverify_clientreportline.client_report_line_id,report_filepath,client_report_head.client_id,report_desc,client_first_name,client_middle_name,client_last_name,client_user_id FROM report_verification left join ehr_reject_master ON report_verification.reject_id = ehr_reject_master.reject_id left join clientreportverify_clientreportline on report_verification.client_report_verify_id=clientreportverify_clientreportline.client_report_verify_id left join client_report_line on clientreportverify_clientreportline.client_report_line_id=client_report_line.client_report_line_id left join client_report_head on client_report_line.client_report_id=client_report_head.client_report_id left join clients on client_report_head.client_id=clients.client_id where report_verification.client_report_verify_id in(SELECT client_report_verify_id FROM indus.report_verification where reject_id is not null and client_report_verify_id in (SELECT max(client_report_verify_id) FROM clientreportverify_clientreportline where client_report_line_id in (SELECT client_report_line_id FROM client_report_line where report_isactive='Y' and client_report_id in (SELECT client_report_id FROM client_report_head where added_by = '"
										+ userName
										+ "' and client_id in (Select client_id from checkup_master where STR_TO_DATE(checkup_master.checkup_date, '%m/%d/%Y') BETWEEN STR_TO_DATE('"
										+ startDate
										+ "', '%m/%d/%Y') AND STR_TO_DATE('"
										+ endDate
										+ "', '%m/%d/%Y')))) group by client_report_line_id ));"*/
			}
			

			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				JSONObject jsonObject = new JSONObject();
				if (row[0] != null) {
					jsonObject.put("clientReportVerifyId", row[0].toString());
				}
				if (row[1] != null) {
					jsonObject.put("rejectId", row[1].toString());
				}
				if (row[2] != null) {
					jsonObject.put("rejectComment", row[2].toString());
				}
				if (row[3] != null) {
					jsonObject.put("rejectReason", row[3].toString());
				}
				if (row[4] != null) {
					jsonObject.put("approvedBy", row[4].toString());
				}
				if (row[5] != null) {
					jsonObject.put("clientReportLineId", row[5].toString());
				}
				if (row[6] != null) {
					jsonObject.put("reportFilepath", row[6].toString());
				}
				if (row[7] != null) {
					jsonObject.put("clientId", row[7].toString());
				}
				if (row[8] != null) {
					jsonObject.put("reportDescription", row[8].toString());
				}
				if (row[9] != null) {
					jsonObject.put("firstName", row[9].toString());
				}
				if (row[10] != null) {
					jsonObject.put("middleName", row[10].toString());
				}
				if (row[11] != null) {
					jsonObject.put("lastName", row[11].toString());
				}
				if (row[12] != null) {
					jsonObject.put("userId", row[12].toString());
				}
				if (row[13] != null) {
					jsonObject.put("packageShortDesc", row[13].toString());
				}
				if (row[14] != null) {
					jsonObject.put("checkupDate", row[14].toString());
				}
				jsonArray.add(jsonObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
	
	
	
	@Override
	public JSONArray totalPendingVisits(String centerId, String startDate,
			String endDate, String userName, String listData) {
		SQLQuery query = null;
		JSONArray jsonArray = new JSONArray();
		try {
			
			
				query = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								//Add by Kishor
								
									"Select clients.client_id, clients.client_first_name,"
									+ " clients.client_middle_name, clients.client_last_name,"
									+ " clients.client_user_id,ehr_package_master.package_short_desc,checkup_master.checkup_date"
									+ " from checkup_master"
									+ " inner join clients"
									+ " on checkup_master.client_id = clients.client_id "
									+ " left join ehr_package_master on checkup_master.package_id = ehr_package_master.package_id" 
									+ " where"
									+ " checkup_master.center_id in("+centerId+")"
									+ " and checkup_master.checkup_id  not in(SELECT (checkup.checkup_id)FROM client_report_head head"
									+ " INNER JOIN"
									+ " client_report_line line ON head.client_report_id = line.client_report_id"
									+ " INNER JOIN"
									+ " checkup_master checkup ON head.client_checkup_id = checkup.checkup_id"
									+ " WHERE checkup.center_id IN ("+centerId+")"
									+ " AND checkup.checkup_date BETWEEN '"+startDate+"' AND '"+endDate+"' "
									+ " GROUP BY head.client_checkup_id )"
									+ " and checkup_master.checkup_date between '"+startDate+"' AND '"+endDate+"'");

			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				JSONObject jsonObject = new JSONObject();
				if (row[0] != null) {
					jsonObject.put("clientId", row[0].toString());
				}
				if (row[1] != null) {
					jsonObject.put("firstName", row[1].toString());
				}
				if (row[2] != null) {
					jsonObject.put("middleName", row[2].toString());
				}
				if (row[3] != null) {
					jsonObject.put("lastName", row[3].toString());
				}
				if (row[4] != null) {
					jsonObject.put("userId", row[4].toString());
				}
				if (row[5] != null) {
					jsonObject.put("packageShortDesc", row[5].toString());
				}
				if (row[6] != null) {
					jsonObject.put("checkupDate", row[6].toString());
				}
				jsonArray.add(jsonObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
	
	@Override
	public JSONArray getPatientAnalysis(Integer clientId,Integer visitId) {
		HashMap<String, String> hashMap = new HashMap<String,String>();
		SQLQuery query = null;
		JSONArray jsonArray = new JSONArray();
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery("SELECT etr.added_on,etr.parameter_id,epm.parameter_name,etr.parameter_value,etr.test_id,etm.test_desc,etr.test_result_status,etr.analysis_comment,etr.report_id,case clients.client_gender when 'M' then 1 when 'F' then 2 end as value_for,crh.center_id,epm.is_display,etr.status_id,es.status,etr.na_flag FROM ehr_test_result etr inner join ehr_parameter_master epm on etr.parameter_id = epm.parameter_id inner join ehr_test_master etm on etr.test_id = etm.test_id inner join clients on etr.client_id = clients.client_id left join client_report_line crl on etr.report_id = crl.client_report_line_id left join client_report_head crh on crl.client_report_id = crh.client_report_id LEFT JOIN ehr_status es ON etr.status_id = es.status_id WHERE etr.client_id ='"+clientId+"' and crh.client_checkup_id="+visitId);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				JSONObject jsonObject = new JSONObject();
				if (row[0] != null) {
					jsonObject.put("addedOn", DateConvertUtil.convertDateTime(row[0].toString()));
				}
				if (row[1] != null) {
					jsonObject.put("parameterId", row[1].toString());
				}
				if (row[2] != null) {
					jsonObject.put("parameterName", row[2].toString());
				}
				if (row[3] != null) {
					jsonObject.put("parameterValue", row[3].toString());
				}
				if (row[4] != null) {
					jsonObject.put("testId", row[4].toString());
				}
				if (row[5] != null) {
					jsonObject.put("testDescription", row[5].toString());
				}
				if (row[6] != null) {
					jsonObject.put("testResultStatus", row[6].toString());
					if(row[6].toString().equals("null")){
						jsonObject.put("testResultStatus", "-");
					}
				}
				if (row[7] != null) {
					jsonObject.put("analysisComment", row[7].toString());
					if(row[7].toString().equals("null") || row[7].toString().equals("undefined")){
						jsonObject.put("analysisComment", "");
					}
				}
				else{
					jsonObject.put("analysisComment", "");
				}
				if (row[8] != null) {
					jsonObject.put("reportId", row[8].toString());
				}
				if (row[9] != null) {
					jsonObject.put("valueFor", row[9].toString()+","+5);
				}
				if (row[10] != null) {
					jsonObject.put("centerId", row[10].toString());
				}
				if (row[11] != null) {
					jsonObject.put("isDisplay", row[11].toString());
				}
				if (row[12] != null) {
					jsonObject.put("statusId", row[12].toString());
					jsonObject.put("status", row[13].toString());
					
					//For fetching report data details
					String sql = "SELECT eqrd.health_score as healthScore,eqrd.report_statement as reportStatement,eqrd.test_id as testId,eqrd.reason,eqrd.frequency_number as frequencyNumber,eqrd.frequency_id as frequencyId,efm.frequency,etm.test_desc as testName FROM ehr_parameter_report_details eqrd inner join ehr_frequency_master efm on eqrd.frequency_id = efm.frequency_id inner join ehr_test_master etm on eqrd.test_id = etm.test_id where eqrd.is_active=true and eqrd.parameter_id="+row[1].toString()+" and eqrd.status_id="+row[12].toString(); 
					SQLQuery query2 = sessionFactory
							.getCurrentSession()
							.createSQLQuery(sql);
					query2.setResultTransformer(Transformers.aliasToBean(ParameterReportDetail.class));
					ParameterReportDetail parameterReportDetail = (ParameterReportDetail) query2.uniqueResult();
					jsonObject.put("parameterReportDetail", parameterReportDetail);
				}else {
					jsonObject.put("statusId", null);
					jsonObject.put("status", "-");
				}
				
				if (row[14] != null) {
					jsonObject.put("naFlag", row[14].toString());
				}else {
					jsonObject.put("naFlag", "false");
				}
				
				String key = row[4].toString()+"@#@#"+row[1].toString()+"@#@#"+row[9].toString()+"@#@#"+row[10].toString();
				if(hashMap.containsKey(key)){
					String[] value = hashMap.get(key).split("@#@#");
					jsonObject.put("lowerValue", value[0]);
					jsonObject.put("upperValue", value[1]);
					jsonObject.put("unitId", value[2]);
					jsonObject.put("unitName", value[3]);
					jsonObject.put("generalComment", value[4]);
					jsonObject.put("normalValue", value[5]);
				}else{				
				query = sessionFactory
						.getCurrentSession()
						.createSQLQuery("SELECT lower_value,upper_value,epv.unit_id,unit_name,general_comment,normal_value FROM ehr_parameter_values epv left join ehr_unit_master eum on epv.unit_id = eum.unit_id where test_id="+row[4].toString()+" and parameter_id="+row[1].toString()+" and value_for in("+row[9].toString()+","+5+") and center_id="+row[10].toString());
				List<Object[]> rows1 = query.list();
				for (Object[] row1 : rows1) {
					if (row[0] != null) {
						jsonObject.put("lowerValue", row1[0].toString());
						if(row1[0].toString().equals("null")){
							jsonObject.put("lowerValue", "-");
						}
					}
					if (row1[1] != null) {
						jsonObject.put("upperValue", row1[1].toString());
						if(row1[1].toString().equals("null")){
							jsonObject.put("upperValue", "-");
						}
					}
					if (row1[2] != null) {
						jsonObject.put("unitId", row1[2].toString());
					}
					if (row1[3] != null) {
						jsonObject.put("unitName", row1[3].toString());
					}else{
						jsonObject.put("unitName", "-");
					}
					if (row1[4] != null) {
						jsonObject.put("generalComment", row1[4].toString());
					}
					if (row1[5] != null) {
						jsonObject.put("normalValue", row1[5].toString());
						if (row1[5].toString().equals("2")) {
							jsonObject.put("lowerValue", "-");
							jsonObject.put("upperValue", "-");
						}
					}
					
					hashMap.put(key,jsonObject.get("lowerValue")+"@#@#"+jsonObject.get("upperValue")+"@#@#"+jsonObject.get("unitId")+"@#@#"+jsonObject.get("unitName")+"@#@#"+jsonObject.get("generalComment")+"@#@#"+jsonObject.get("normalValue"));
					
				}
				}
				jsonObject.put("visitId", visitId);
				jsonArray.add(jsonObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public String savePatientAnalysis(JSONArray commentArray){
		for(int i=0;i<commentArray.size();i++){
			JSONObject object = (JSONObject) commentArray.get(i);
			String comment = (String) object.get("comment");
			if(comment.equals("")) {
				comment = null;
			}
			String[] testResultId = ((String) object.get("testResultId")).split("@@");
			try {
				/*update ehr_test_result set analysis_comment='"+comment+"' where report_id="+testResultId[0]+" and test_id="+testResultId[2]+" and parameter_id="+testResultId[3]*/				
				String sql = "INSERT INTO ehr_test_result(client_id,test_id,parameter_id,report_id) " +
						"values('"+testResultId[1]+"',"+testResultId[2]+",'"+testResultId[3]+"','"+testResultId[0]+"')" +
						" ON DUPLICATE KEY UPDATE status_id="+comment+"";
				SQLQuery query = sessionFactory
						.getCurrentSession()
						.createSQLQuery(sql);
				query.executeUpdate();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return "saved";
	}

	@Override
	public String saveClientAnalysisComment(JSONObject object) {
		String addedBy = (String) object.get("addedBy");
		String addedOn = (String) object.get("addedOn");
		Integer clientId = (Integer) object.get("clientId");
		Integer visitId = (Integer) object.get("visitId");
		String classification = (String) object.get("classification");
		//String clientAnalysisComment = (String) object.get("clientAnalysisComment");
		JSONArray analysisComments = (JSONArray) object.get("analysisComments");
		try {
			for(int i=0;i<analysisComments.size();i++){
				JSONObject analysisObject = (JSONObject) analysisComments.get(i);
				String commentId = (String) analysisObject.get("commentId");
				String comment = (String) analysisObject.get("comment");
				Boolean isActive = (Boolean) analysisObject.get("isActive");
				SQLQuery query = sessionFactory
						.getCurrentSession()
						.createSQLQuery("update ehr_analysis_comment set is_active='N' where client_id="+clientId+" and checkup_id="+visitId+" and comment_type_id="+commentId);
				query.executeUpdate();
				
				if(isActive) {
				SQLQuery queryInsert = sessionFactory
						.getCurrentSession()
						.createSQLQuery("insert into ehr_analysis_comment( analysis_comment,comment_type_id,client_id,classification,checkup_id,added_by,added_on,is_active ) values('"+comment+"','"+commentId+"','"+clientId+"','"+classification+"','"+visitId+"','"+addedBy+"','"+addedOn+"','Y')");
				queryInsert.executeUpdate();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "saved";
	}
	
	@Override
	public JSONArray getPatientAnalysisComment(Integer clientId,Integer visitId) {
		SQLQuery query = null;
		JSONArray jsonArray = new JSONArray();
		try {
			if(visitId.equals(0)) {
			SQLQuery checkupQuery = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"SELECT checkup_id FROM checkup_master WHERE client_id = "+clientId+" order by checkup_id desc limit 1");
			visitId = (Integer) checkupQuery.uniqueResult();
			}

			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"SELECT eam.analysis_comment_id,eam.analysis_comment,eam.classification,eam.added_by,eam.added_on,eam.checkup_id,eam.comment_type_id,comment FROM ehr_analysis_comment eam inner join ehr_analysis_comment_master eacm on eam.comment_type_id = eacm.comment_id where eam.client_id="+clientId+" and eam.checkup_id="+visitId+" and eam.is_active='Y' order by eacm.sequence ");
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				JSONObject jsonObject = new JSONObject();
				if (row[0] != null) {
					jsonObject.put("analysisCommentId", row[0].toString());
				}
				if (row[1] != null) {
					jsonObject.put("analysisComment", row[1].toString());
					if(row[1].toString().equals("null") || row[1].toString().equals("undefined")){
						jsonObject.put("analysisComment", "");
					}
				}
				else{
					jsonObject.put("analysisComment", "");
				}
				if (row[2] != null) {
					jsonObject.put("classification", row[2].toString());
				}
				if (row[3] != null) {
					jsonObject.put("addedBy", row[3].toString());
				}
				if (row[4] != null) {
					jsonObject.put("addedOn", DateConvertUtil.convertDateTime(row[4].toString()));
				}
				if (row[5] != null) {
					jsonObject.put("visitId", row[5].toString());
				}
				if (row[6] != null) {
					jsonObject.put("commentTypeId", row[6].toString());
				}
				if (row[7] != null) {
					jsonObject.put("comment", row[7].toString());
				}
				jsonArray.add(jsonObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public String getCountClientMasterAnalysisResults(String startDate,
			String endDate) {
		SQLQuery query = sessionFactory
				.getCurrentSession()
				.createSQLQuery("SELECT count( analysis_comment_id ) FROM ehr_analysis_comment WHERE ehr_analysis_comment.is_active='Y' and (added_on BETWEEN '"+startDate+"' AND '"+endDate+"')");
		BigInteger count = (BigInteger) query.uniqueResult();
		return count.toString();
	}

	@Override
	public List<ClientMaster> getClientMastersByAnalysisResults(
			String startDate, String endDate, int startIndex) {
		List<ClientMaster> listClientMasters = new ArrayList<ClientMaster>();
		SQLQuery query = null;
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"SELECT ehr_analysis_comment.client_id,client_first_name,client_last_name,client_mob_no,client_full_name,client_user_id,clients.package_id,package_desc,clients.add_at,client_email,classification FROM ehr_analysis_comment inner join clients ON ehr_analysis_comment.client_id = clients.client_id left join ehr_package_master ON clients.package_id = ehr_package_master.package_id where client_isactive='Y' and ehr_analysis_comment.is_active='Y' and (added_on BETWEEN '"+startDate+"' AND '"+endDate+"')");
			query.setFirstResult(startIndex);
			query.setMaxResults(10);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ClientMaster clientMaster = new ClientMaster();
				if (row[0] != null) {
					clientMaster
							.setClientId(Integer.parseInt(row[0].toString()));
				}
				if (row[1] != null) {
					clientMaster.setFirstName(row[1].toString());
				}
				if (row[2] != null) {
					clientMaster.setLastName(row[2].toString());
				}
				if (row[3] != null) {
					clientMaster.setMobNo(row[3].toString());
				}
				if (row[4] != null) {
					clientMaster.setClientFullName(row[4].toString());
				}
				if (row[5] != null) {
					clientMaster.setUserId(row[5].toString());
				}
				PackageMaster packageMaster = new PackageMaster();
				if (row[6] != null && !row[6].equals("null")) {
					packageMaster.setPackageId(Integer.parseInt(row[6]
							.toString()));
				}
				if (row[7] != null) {
					packageMaster.setPackageDescription(row[7].toString());
				}
				clientMaster.setPackageMaster(packageMaster);
				if (row[8] != null) {
					clientMaster.setAddAt(DateConvertUtil.convertDateTime(row[8].toString()));
				}
				if (row[9] != null) {
					clientMaster.setEmailId(row[9].toString());
				}
				if (row[10] != null) {
					clientMaster.setAddBy(row[10].toString());
				}
				listClientMasters.add(clientMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listClientMasters;
	}

	@Override
	public List<ClientMaster> autoSuggestionClientAnalysisResults(
			String searchKeyword) {
		List<ClientMaster> listClientMasters = new ArrayList<ClientMaster>();
		SQLQuery query = null;
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select distinct(ehr_analysis_comment.client_id),client_first_name,client_last_name,client_mob_no,client_full_name from ehr_analysis_comment inner join clients on ehr_analysis_comment.client_id=clients.client_id WHERE (client_full_name LIKE '%"
									+ searchKeyword
									+ "%' or client_first_name LIKE '%"
									+ searchKeyword
									+ "%' or client_last_name LIKE '%"
									+ searchKeyword + "%') and client_isactive='Y'");
			query.setFirstResult(0);
			query.setMaxResults(100);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				ClientMaster clientMaster = new ClientMaster();
				if (row[0] != null) {
					clientMaster.setClientId(Integer.parseInt(row[0].toString()));
				}
				if (row[1] != null) {
					clientMaster.setFirstName(row[1].toString());
				}
				if (row[2] != null) {
					clientMaster.setLastName(row[2].toString());
				}
				if (row[3] != null) {
					clientMaster.setMobNo(row[3].toString());
				}
				if (row[4] != null) {
					clientMaster.setClientFullName(row[4].toString());
				}
				listClientMasters.add(clientMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listClientMasters;
	}
	
	@Override
	public JSONArray getPatientHealthStatistics(Integer clientId,String parameterId) {
		HashMap<String, String> hashMap = new HashMap<String,String>();
		SQLQuery query = null;
		JSONArray jsonArray = new JSONArray();
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery("SELECT etr.added_on,etr.parameter_id,epm.parameter_name,etr.parameter_value,etr.test_id,etm.test_desc,etr.test_result_status,etr.analysis_comment,etr.report_id,case clients.client_gender when 'M' then 1 when 'F' then 2 end as value_for,crh.center_id FROM ehr_test_result etr inner join ehr_parameter_master epm on etr.parameter_id = epm.parameter_id inner join ehr_test_master etm on etr.test_id = etm.test_id inner join clients on etr.client_id = clients.client_id left join client_report_line crl on etr.report_id = crl.client_report_line_id left join client_report_head crh on crl.client_report_id = crh.client_report_id WHERE etr.parameter_id in ("+parameterId+") and etr.client_id ="+clientId);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				JSONObject jsonObject = new JSONObject();
				if (row[0] != null) {
					jsonObject.put("addedOn", DateConvertUtil.convertDate(row[0].toString()));
					jsonObject.put("addedTime", (row[0].toString()).split(" ")[1]);
				}
				if (row[1] != null) {
					jsonObject.put("parameterId", row[1].toString());
				}
				if (row[2] != null) {
					jsonObject.put("parameterName", row[2].toString());
				}
				if (row[3] != null) {
					jsonObject.put("parameterValue", row[3].toString());
				}
				if (row[4] != null) {
					jsonObject.put("testId", row[4].toString());
				}
				if (row[5] != null) {
					jsonObject.put("testDescription", row[5].toString());
				}
				if (row[6] != null) {
					jsonObject.put("testResultStatus", row[6].toString());
					if(row[6].toString().equals("null")){
						jsonObject.put("testResultStatus", "-");
					}
				}
				if (row[7] != null) {
					jsonObject.put("analysisComment", row[7].toString());
					if(row[7].toString().equals("null") || row[7].toString().equals("undefined")){
						jsonObject.put("analysisComment", "");
					}
				}
				else{
					jsonObject.put("analysisComment", "");
				}
				if (row[8] != null) {
					jsonObject.put("reportId", row[8].toString());
				}
				if (row[9] != null) {
					jsonObject.put("valueFor", row[9].toString()+","+5);
				}
				if (row[10] != null) {
					jsonObject.put("centerId", row[10].toString());
				}
				
				String key = row[4].toString()+"@#@#"+row[1].toString()+"@#@#"+row[9].toString()+"@#@#"+row[10].toString();
				if(hashMap.containsKey(key)){
					String[] value = hashMap.get(key).split("@#@#");
					jsonObject.put("lowerValue", value[0]);
					jsonObject.put("upperValue", value[1]);
					jsonObject.put("unitId", value[2]);
					jsonObject.put("unitName", value[3]);
					jsonObject.put("generalComment", value[4]);
					jsonObject.put("normalValue", value[5]);
				}else{				
				query = sessionFactory
						.getCurrentSession()
						.createSQLQuery("SELECT lower_value,upper_value,epv.unit_id,unit_name,general_comment,normal_value FROM ehr_parameter_values epv left join ehr_unit_master eum on epv.unit_id = eum.unit_id where test_id="+row[4].toString()+" and parameter_id="+row[1].toString()+" and value_for in("+row[9].toString()+","+5+") and center_id="+row[10].toString());
				List<Object[]> rows1 = query.list();
				for (Object[] row1 : rows1) {
					if (row[0] != null) {
						jsonObject.put("lowerValue", row1[0].toString());
						if(row1[0].toString().equals("null")){
							jsonObject.put("lowerValue", "-");
						}
					}
					if (row1[1] != null) {
						jsonObject.put("upperValue", row1[1].toString());
						if(row1[1].toString().equals("null")){
							jsonObject.put("upperValue", "-");
						}
					}
					if (row1[2] != null) {
						jsonObject.put("unitId", row1[2].toString());
					}
					if (row1[3] != null) {
						jsonObject.put("unitName", row1[3].toString());
					}else{
						jsonObject.put("unitName", "-");
					}
					if (row1[4] != null) {
						jsonObject.put("generalComment", row1[4].toString());
					}
					if (row1[5] != null) {
						jsonObject.put("normalValue", row1[5].toString());
						if (row1[5].toString().equals("2")) {
							jsonObject.put("lowerValue", "-");
							jsonObject.put("upperValue", "-");
						}
					}
					
					hashMap.put(key,jsonObject.get("lowerValue")+"@#@#"+jsonObject.get("upperValue")+"@#@#"+jsonObject.get("unitId")+"@#@#"+jsonObject.get("unitName")+"@#@#"+jsonObject.get("generalComment")+"@#@#"+jsonObject.get("normalValue"));
					
				}
				}
				jsonArray.add(jsonObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	
		/*SQLQuery query = null;
		JSONArray jsonArray = new JSONArray();
		try {
			"SELECT test_result_id,ehr_test_result.parameter_id,ehr_parameter_master.parameter_name,parameter_value,ehr_test_result.test_id,ehr_test_master.test_desc,ehr_test_result.added_on,test_result_status,ehr_parameter_values.lower_value,ehr_parameter_values.upper_value,ehr_parameter_values.general_comment,ehr_parameter_values.unit_id,ehr_parameter_values.value_for,ehr_test_result.analysis_comment,clients.client_gender,unit_name FROM ehr_test_result left join clients on ehr_test_result.client_id = clients.client_id left join ehr_parameter_master on ehr_test_result.parameter_id=ehr_parameter_master.parameter_id left join ehr_test_master on ehr_test_result.test_id=ehr_test_master.test_id left join ehr_parameter_values on ehr_test_result.parameter_id = ehr_parameter_values.parameter_id and ehr_parameter_values.center_id = clients.center_id left join ehr_unit_master on ehr_parameter_values.unit_id = ehr_unit_master.unit_id where ehr_parameter_values.is_active='Y' and ehr_test_result.client_id="+clientId
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery("SELECT test_result_id,ehr_test_result.parameter_id,ehr_parameter_master.parameter_name,parameter_value,ehr_test_result.test_id,ehr_test_master.test_desc,ehr_test_result.added_on,test_result_status,ehr_parameter_values.lower_value,ehr_parameter_values.upper_value,ehr_parameter_values.general_comment,ehr_parameter_values.unit_id,ehr_parameter_values.value_for,ehr_test_result.analysis_comment,clients.client_gender,unit_name FROM ehr_test_result left join clients on ehr_test_result.client_id = clients.client_id left join ehr_parameter_master on ehr_test_result.parameter_id=ehr_parameter_master.parameter_id left join ehr_test_master on ehr_test_result.test_id=ehr_test_master.test_id inner join client_report_line on ehr_test_result.report_id = client_report_line.client_report_line_id left join client_report_head on client_report_line.client_report_id = client_report_head.client_report_id	LEFT JOIN ehr_parameter_values ON (ehr_test_result.parameter_id = ehr_parameter_values.parameter_id AND client_report_head.center_id = ehr_parameter_values.center_id) LEFT JOIN ehr_unit_master ON ehr_parameter_values.unit_id = ehr_unit_master.unit_id WHERE ehr_test_result.parameter_id IN ("+parameterId+") and ehr_parameter_values.is_active = 'Y' and ehr_test_result.client_id ="+clientId);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				JSONObject jsonObject = new JSONObject();
				if (row[0] != null) {
					jsonObject.put("testResultId", row[0].toString());
				}
				if (row[1] != null) {
					jsonObject.put("parameterId", row[1].toString());
				}
				if (row[2] != null) {
					jsonObject.put("parameterName", row[2].toString());
				}
				if (row[3] != null) {
					jsonObject.put("parameterValue", row[3].toString());
				}
				if (row[4] != null) {
					jsonObject.put("testId", row[4].toString());
				}
				if (row[5] != null) {
					jsonObject.put("testDescription", row[5].toString());
				}
				if (row[6] != null) {
					jsonObject.put("addedOn", DateConvertUtil.convertDate(row[6].toString()));
				}
				if (row[7] != null) {
					jsonObject.put("testResultStatus", row[7].toString());
					if(row[7].toString().equals("null")){
						jsonObject.put("testResultStatus", "-");
					}
				}
				if (row[8] != null) {
					jsonObject.put("lowerValue", row[8].toString());
					if(row[8].toString().equals("null")){
						jsonObject.put("lowerValue", "-");
					}
				}
				if (row[9] != null) {
					jsonObject.put("upperValue", row[9].toString());
					if(row[9].toString().equals("null")){
						jsonObject.put("upperValue", "-");
					}
				}
				if (row[10] != null) {
					jsonObject.put("generalComment", row[10].toString());
				}
				if (row[11] != null) {
					jsonObject.put("unitId", row[11].toString());
				}
				if (row[12] != null) {
					jsonObject.put("valueFor", row[12].toString());
				}
				if (row[13] != null) {
					jsonObject.put("analysisComment", row[13].toString());
					if(row[13].toString().equals("null") || row[13].toString().equals("undefined")){
						jsonObject.put("analysisComment", "");
					}
				}
				else{
					jsonObject.put("analysisComment", "");
				}
				if (row[11] != null) {
					jsonObject.put("unitId", row[11].toString());
				}
				if (row[15] != null) {
					jsonObject.put("unitName", row[15].toString());
				}else{
					jsonObject.put("unitName", "-");
				}
				
				if (row[14] != null) {
					if (row[14].toString().equals("M")) {
						if (row[12] != null) {
							if (row[12].toString().equals("1")) {
								jsonObject.put("valueFor", "1");
								if (row[8] != null) {
									jsonObject.put("lowerValue", row[8].toString());
									if(row[8].toString().equals("null")){
										jsonObject.put("lowerValue", "-");
									}
								}
								if (row[9] != null) {
									jsonObject.put("upperValue", row[9].toString());
									if(row[9].toString().equals("null")){
										jsonObject.put("upperValue", "-");
									}
								}
								jsonArray.add(jsonObject);
							}
						}
					}
					else if (row[14].toString().equals("F")) {
						if (row[12] != null) {
							if (row[12].toString().equals("2")) {
								jsonObject.put("valueFor", "2");
								if (row[8] != null) {
									jsonObject.put("lowerValue", row[8].toString());
									if(row[8].toString().equals("null")){
										jsonObject.put("lowerValue", "-");
									}
								}
								if (row[9] != null) {
									jsonObject.put("upperValue", row[9].toString());
									if(row[9].toString().equals("null")){
										jsonObject.put("upperValue", "-");
									}
								}
								jsonArray.add(jsonObject);
							}
						}
					}
				}
				if (row[12] != null) {
					if (row[12].toString().equals("5")) {
						jsonObject.put("valueFor", "5");
						jsonObject.put("lowerValue", "-");
						jsonObject.put("upperValue", "-");
						jsonArray.add(jsonObject);
					}
				}
				//jsonArray.add(jsonObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;*/
	}

	@Override
	public String saveReminder(JSONArray jsonArray) {
		List<String> idList=new ArrayList<String>();
		BigInteger lastId = null ;
		for(int i=0;i<jsonArray.size();i++){
			JSONObject object = (JSONObject) jsonArray.get(i);
			String addedBy = (String) object.get("addedBy");
			String addedOn = (String) object.get("addedOn");
			Integer clientId = (Integer) object.get("clientId");
			String emailId = (String) object.get("emailId");
			String mobileNo = (String) object.get("mobileNo");
			String category = (String) object.get("category");
			String eventTitle = (String) object.get("eventTitle");
			String doctorName = (String) object.get("doctorName");
			String doctorDateTime = (String) object.get("doctorDateTime");
			String medicineName = (String) object.get("medicineName");
			String medicineDose = (String) object.get("medicineDose");
			String medicineDateTime = (String) object.get("medicineDateTime");
			String typeOfExercise=(String)object.get("typeOfExercise");
			String durationInMinutes = (String) object.get("durationInMinutes");
			String testName = (String) object.get("testName");
			String centreName = (String) object.get("centreName");
			String remindMeFor = (String) object.get("remindMeFor");
			String location = (String) object.get("location");
			String duration = (String) object.get("duration");
			String reminderDateTime=(String)object.get("reminderDateTime");
			String eventStartDate = (String) object.get("eventStartDate");
			String eventEndDate = (String) object.get("eventEndDate");
			String reminderMasterId = (String) object.get("reminderMasterId");
			String recurrencePattern = (String) object.get("recurrencePattern");
			String clientName = (String) object.get("clientName");
			System.err.println("ddddate   dao----"+reminderDateTime);
			try {
				SQLQuery queryInsert = sessionFactory
						.getCurrentSession()
						.createSQLQuery("insert into ehr_reminder( email_id,mobile_no,category,event_title,doctor_name,doctor_date_time,medicine_name,medicine_dose,medicine_date_time,reminder_date_time,client_id,added_by,added_on,is_active,type_exercise,duration_in_minutes,test_name,centre_name,location,duration,remind_me_for,start_event,end_event,ehr_reminder_master_id,recurrence_pattern,client_name ) " +
								"values('"+emailId+"','"+mobileNo+"','"+category+"','"+eventTitle+"','"+doctorName+"','"+doctorDateTime+"','"+medicineName+"','"+medicineDose+"','"+medicineDateTime+"','"+reminderDateTime+"','"+clientId+"','"+addedBy+"','"+addedOn+"','Y','"+typeOfExercise+"','"+durationInMinutes+"','"+testName+"','"+centreName+"','"+location+"','"+duration+"','"+remindMeFor+"','"+eventStartDate+"','"+eventEndDate+"','"+reminderMasterId+"','"+recurrencePattern+"','"+clientName+"');");
				queryInsert.executeUpdate();
				lastId = (BigInteger) sessionFactory.getCurrentSession().createSQLQuery("SELECT LAST_INSERT_ID()").uniqueResult();
				idList.add(lastId.toString());
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		try{
		String list = idList.toString();
		String groupId = list.substring(1, list.length() - 1).replace(", ", ",");
		SQLQuery updateReminder = sessionFactory
				.getCurrentSession()
				.createSQLQuery("update ehr_reminder set group_id='"+lastId.toString()+"' where reminder_id in("+groupId+");");
		updateReminder.executeUpdate();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "saved";
	}
	
	@Override
	public JSONArray getPatientReminder(Integer clientId) {
		SQLQuery query = null;
		JSONArray jsonArray = new JSONArray();
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"SELECT reminder_id,email_id,mobile_no,category,event_title,doctor_name,doctor_date_time,medicine_name,medicine_dose,medicine_date_time,reminder_date_time,start_event,end_event,ehr_reminder_master_id,recurrence_pattern,client_id,is_active,type_exercise,duration_in_minutes,test_name,centre_name,location,duration,remind_me_for,group_id,client_name,added_by,added_on from ehr_reminder where is_active='Y' and client_id="+clientId);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				JSONObject jsonObject = new JSONObject();
				if (row[0] != null) {
					jsonObject.put("reminderId", row[0].toString());
				}
				if (row[1] != null) {
					jsonObject.put("emailId", row[1].toString());
				}
				if (row[2] != null) {
					jsonObject.put("mobileNo", row[2].toString());
				}
				if (row[3] != null) {
					jsonObject.put("category", row[3].toString());
				}
				if (row[4] != null) {
					jsonObject.put("eventTitle", row[4].toString());
				}
				if (row[5] != null) {
					jsonObject.put("doctorName", row[5].toString());
				}
				if (row[6] != null) {
					jsonObject.put("doctorDateTime", row[6].toString());
				}
				if (row[7] != null) {
					jsonObject.put("medicineName", row[7].toString());
				}
				if (row[8] != null) {
					jsonObject.put("medicineDose", row[8].toString());
				}
				if (row[9] != null) {
					jsonObject.put("medicineDateTime", row[9].toString());
				}
				if (row[10] != null) {
					jsonObject.put("reminderDateTime", row[10].toString());
				}
				if (row[11] != null) {
					jsonObject.put("startEvent", row[11].toString());
				}
				if (row[12] != null) {
					jsonObject.put("endEvent", row[12].toString());
				}
				if (row[13] != null) {
					jsonObject.put("ehrReminderMasterId", row[13].toString());
				}
				if (row[14] != null) {
					jsonObject.put("recurrencePattern", row[14].toString());
				}
				if (row[15] != null) {
					jsonObject.put("clientId", row[15].toString());
				}
				if (row[16] != null) {
					jsonObject.put("isActive", row[16].toString());
				}
				if (row[17] != null) {
					jsonObject.put("typeExercise", row[17].toString());
				}
				if (row[18] != null) {
					jsonObject.put("durationInMinutes", row[18].toString());
				}
				if (row[19] != null) {
					jsonObject.put("testName", row[19].toString());
				}
				if (row[20] != null) {
					jsonObject.put("centreName", row[20].toString());
				}
				if (row[21] != null) {
					jsonObject.put("location", row[21].toString());
				}
				if (row[22] != null) {
					jsonObject.put("duration", row[22].toString());
				}
				if (row[23] != null) {
					jsonObject.put("remindMeFor", row[23].toString());
				}
				if (row[24] != null) {
					jsonObject.put("groupId", row[24].toString());
				}
				if (row[25] != null) {
					jsonObject.put("clientName", row[25].toString());
				}
				jsonArray.add(jsonObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
	
	@Override
	public String acceptTermsAndConditions(ClientMaster clientMaster2) {
		try {
			ClientMaster clientMaster = (ClientMaster) sessionFactory
					.getCurrentSession().get(ClientMaster.class,
							clientMaster2.getClientId());
			clientMaster.setCount(clientMaster2.getCount());
			clientMaster.setIsAgree(clientMaster2.getIsAgree());
		}catch(Exception e){
			
		}
		return "accepted";
	}
	
	@Override
	public JSONArray getAllReminder(String date) {
		SQLQuery query = null;
		JSONArray jsonArray = new JSONArray();
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"SELECT reminder_id,email_id,mobile_no,category,event_title,doctor_name,doctor_date_time,medicine_name,medicine_dose,medicine_date_time,reminder_date_time,start_event,end_event,ehr_reminder_master_id,recurrence_pattern,client_id,is_active,type_exercise,duration_in_minutes,test_name,centre_name,location,duration,remind_me_for,client_name,added_by,added_on from ehr_reminder where is_active='Y' and reminder_date_time like '"+date+"'");//'%07/16/2016 2:%PM'
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				JSONObject jsonObject = new JSONObject();
				if (row[0] != null) {
					jsonObject.put("reminderId", row[0].toString());
				}
				if (row[1] != null) {
					jsonObject.put("emailId", row[1].toString());
				}
				if (row[2] != null) {
					jsonObject.put("mobileNo", row[2].toString());
				}
				if (row[3] != null) {
					jsonObject.put("category", row[3].toString());
				}
				if (row[4] != null) {
					jsonObject.put("eventTitle", row[4].toString());
				}
				if (row[5] != null) {
					jsonObject.put("doctorName", row[5].toString());
				}
				if (row[6] != null) {
					jsonObject.put("doctorDateTime", row[6].toString());
				}
				if (row[7] != null) {
					jsonObject.put("medicineName", row[7].toString());
				}
				if (row[8] != null) {
					jsonObject.put("medicineDose", row[8].toString());
				}
				if (row[9] != null) {
					jsonObject.put("medicineDateTime", row[9].toString());
				}
				if (row[10] != null) {
					jsonObject.put("reminderDateTime", row[10].toString());
				}
				if (row[11] != null) {
					jsonObject.put("startEvent", row[11].toString());
				}
				if (row[12] != null) {
					jsonObject.put("endEvent", row[12].toString());
				}
				if (row[13] != null) {
					jsonObject.put("ehrReminderMasterId", row[13].toString());
				}
				if (row[14] != null) {
					jsonObject.put("recurrencePattern", row[14].toString());
				}
				if (row[15] != null) {
					jsonObject.put("clientId", row[15].toString());
				}
				if (row[16] != null) {
					jsonObject.put("isActive", row[16].toString());
				}
				if (row[17] != null) {
					jsonObject.put("typeExercise", row[17].toString());
				}
				if (row[18] != null) {
					jsonObject.put("durationInMinutes", row[18].toString());
				}
				if (row[19] != null) {
					jsonObject.put("testName", row[19].toString());
				}
				if (row[20] != null) {
					jsonObject.put("centreName", row[20].toString());
				}
				if (row[21] != null) {
					jsonObject.put("location", row[21].toString());
				}
				if (row[22] != null) {
					jsonObject.put("duration", row[22].toString());
				}
				if (row[23] != null) {
					jsonObject.put("remindMeFor", row[23].toString());
				}
				if (row[24] != null) {
					jsonObject.put("clientName", row[24].toString());
				}
				jsonArray.add(jsonObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
	
	@Override
	public String deleteReminder(String reminderId,String groupId) {
		try{
			if(reminderId!=null && !reminderId.equals("0") && groupId!=null && !groupId.equals("0")){
				SQLQuery deleteReminderId = sessionFactory
					.getCurrentSession()
					.createSQLQuery("update ehr_reminder set is_active='N' where group_id in("+groupId+") and reminder_id >= "+reminderId);
			deleteReminderId.executeUpdate();
			}
			else if(reminderId!=null && !reminderId.equals("0")){
			SQLQuery deleteReminderId = sessionFactory
				.getCurrentSession()
				.createSQLQuery("update ehr_reminder set is_active='N' where reminder_id ="+reminderId);
		deleteReminderId.executeUpdate();
			}
			else{
				SQLQuery deleteGroupReminderId = sessionFactory
						.getCurrentSession()
						.createSQLQuery("update ehr_reminder set is_active='N' where group_id in("+groupId+")");
				deleteGroupReminderId.executeUpdate();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "deleted";
	}

	@Override
	public List<ClientMaster> getRecordByMemberId(String memberId,Integer startIndex, HttpServletRequest request) {
		SQLQuery query = null;
		List<ClientMaster> clientMasters = new ArrayList<ClientMaster>();
		
		HttpSession session=request.getSession();
		String userTypeId=(String) session.getAttribute("userTypeId");
		String packages=(String) session.getAttribute("packages");
		String memberCode=(String) session.getAttribute("memberCode");
		String centers=(String) session.getAttribute("centers");
		System.err.println("centers id= "+centers);
		if(packages.isEmpty()){
			packages="0";
		}
		String memId=memberCode+memberId;
		
		try {
			
				if(userTypeId.equals("4") || userTypeId.equals("13")){
					if(memberId.contains(memberCode)) {
					query = sessionFactory
							.getCurrentSession()
							.createSQLQuery(
									"SELECT client_first_name,client_last_name,client_mob_no,package_desc,clients.add_at,client_email,client_user_id,clients.package_id,clients.center_id,checkup_master.checkup_date,checkup_master.checkup_id,classification,clients.client_id FROM clients left join ehr_package_master on clients.package_id=ehr_package_master.package_id left join checkup_master ON checkup_master.client_id=clients.client_id left join ehr_analysis_comment on ehr_analysis_comment.client_id = clients.client_id WHERE clients.is_deleted='N' and clients.member_id ='"+ memberId+"' and client_isactive='Y' group by client_id");
					}
				}else if(userTypeId.equals("14")){
					query = sessionFactory
							.getCurrentSession()
							.createSQLQuery(
									"SELECT client_first_name,client_last_name,client_mob_no,package_desc,clients.add_at,client_email,client_user_id,clients.package_id,clients.center_id,checkup_master.checkup_date,checkup_master.checkup_id,classification,clients.client_id FROM clients left join ehr_package_master on clients.package_id=ehr_package_master.package_id left join checkup_master ON checkup_master.client_id=clients.client_id left join ehr_analysis_comment on ehr_analysis_comment.client_id = clients.client_id WHERE clients.is_deleted='N'  and checkup_master.center_id in ("+centers+") and clients.member_id ='"+ memberId+"' and client_isactive='Y' group by client_id");
					
				}else {
					query = sessionFactory
							.getCurrentSession()
							.createSQLQuery(
									"SELECT client_first_name,client_last_name,client_mob_no,package_desc,clients.add_at,client_email,client_user_id,clients.package_id,clients.center_id,checkup_master.checkup_date,checkup_master.checkup_id,classification,clients.client_id FROM clients left join ehr_package_master on clients.package_id=ehr_package_master.package_id left join checkup_master ON checkup_master.client_id=clients.client_id left join ehr_analysis_comment on ehr_analysis_comment.client_id = clients.client_id WHERE clients.is_deleted='N' and clients.member_id ='"+ memberId+"' and client_isactive='Y' group by client_id");
					
				}
				query.setFirstResult(startIndex);
				query.setMaxResults(10);
				List<Object[]> rows = query.list();
				for (Object[] row : rows) {
					ClientMaster clientMaster = new ClientMaster();
					if (row[0] != null) {
						clientMaster.setFirstName(row[0].toString());
					}
					if (row[1] != null) {
						clientMaster.setLastName(row[1].toString());
					}
					if (row[2] != null) {
						clientMaster.setMobNo(row[2].toString());
					}
					PackageMaster packageMaster = new PackageMaster();
					if (row[3] != null && !row[3].toString().equals("null")) {
						packageMaster.setPackageDescription(row[3].toString());
					}else {
						packageMaster.setPackageDescription("");
					}
					if (row[4] != null) {
						clientMaster.setAddAt(DateConvertUtil.convertDateTime(row[4].toString()));
					}
					if (row[5] != null) {
						clientMaster.setEmailId(row[5].toString());
					}
					if (row[6] != null) {
						clientMaster.setUserId(row[6].toString());
					}
					if (row[7] != null) {
						packageMaster.setPackageId(Integer.parseInt(row[7]
								.toString()));
					}
					if (row[8] != null) {
						CentreMaster centreMaster = new CentreMaster();
						centreMaster
								.setCentreId(Integer.parseInt(row[8].toString()));
						clientMaster.setCentreMaster(centreMaster);
					}
					Set<CheckUpMaster> checkUpMasterSet = new HashSet<CheckUpMaster>();
					CheckUpMaster checkUpMaster = new CheckUpMaster();
					if (row[9] != null) {
						checkUpMaster.setCheckUpDate(row[9].toString());
					}
					if (row[10] != null) {
						checkUpMaster.setCheckUpId(Integer.parseInt(row[10]
								.toString()));
					}
					checkUpMasterSet.add(checkUpMaster);
					clientMaster.setCheckUpMasterSet(checkUpMasterSet);
					clientMaster.setPackageMaster(packageMaster);

					if (row[11] != null) {
						clientMaster.setAddBy(row[11].toString());
					}
					if (row[12] != null) {
						clientMaster.setClientId(Integer.parseInt(row[12].toString()));
					}
					clientMasters.add(clientMaster);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientMasters;
	}

	@Override
	public String getCountByMemberId(String memberId) {
		SQLQuery query = null;
		try {
		query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT count(member_id) FROM clients WHERE member_id ='"+memberId+"'");
		}catch(Exception e){
			e.printStackTrace();
		}
		BigInteger count = (BigInteger) query.uniqueResult();
		return count.toString();
	}

	@Override
	public JSONObject getAnalysisCommentByClientId(Integer clientId) {
		JSONObject object = new JSONObject();
		try {
			SQLQuery analysisComment = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select analysis_comment_id,analysis_comment,added_by,added_on,modify_by,modify_on,client_id,classification from ehr_analysis_comment where is_active='Y' and client_id ="+clientId);
			List<Object[]> rows = analysisComment.list();
			for (Object[] row : rows) {
				if (row[0] != null) {
					object.put("analysisCommentId", row[0].toString());
				}
				if (row[1] != null) {
					object.put("analysisComment", row[1].toString());
				}
				if (row[2] != null) {
					object.put("addedBy", row[2].toString());
				}
				if (row[3] != null) {
					object.put("addedOn", row[3].toString());
				}
				if (row[4] != null) {
					object.put("modifyBy", row[4].toString());
				}
				if (row[5] != null) {
					object.put("modifyOn", row[5].toString());
				}
				if (row[6] != null) {
					object.put("clientId", row[6].toString());
				}
				if (row[7] != null) {
					object.put("classification", row[7].toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public void mergeClient(JSONObject object) {
		String addedBy = (String) object.get("addedBy");
		String addedOn = (String) object.get("addedOn");
		String remoteIp = (String) object.get("remoteIp");
		Integer originalClientId = (Integer) object.get("originalClientId");
		String replaceableClientIdArray = (String) object.get("replaceableClientIdArray");
		
		SQLQuery clientsTable = sessionFactory
				.getCurrentSession()
				.createSQLQuery("update clients set client_isactive='N' where client_id in("+replaceableClientIdArray+")");
		clientsTable.executeUpdate();
		
		SQLQuery checkupMasterTable = sessionFactory
				.getCurrentSession()
				.createSQLQuery("update checkup_master set previous_client_id = client_id,client_id='"+originalClientId+"' where client_id in("+replaceableClientIdArray+")");
		checkupMasterTable.executeUpdate();
		
		SQLQuery clientHraDetailsTable = sessionFactory
				.getCurrentSession()
				.createSQLQuery("update client_hra_details set previous_client_id = client_id,client_id='"+originalClientId+"' where client_id in("+replaceableClientIdArray+")");
		clientHraDetailsTable.executeUpdate();
		
		SQLQuery clientReportHeadTable = sessionFactory
				.getCurrentSession()
				.createSQLQuery("update client_report_head set previous_client_id = client_id,client_id='"+originalClientId+"' where client_id in("+replaceableClientIdArray+")");
		clientReportHeadTable.executeUpdate();
		
		SQLQuery ehrAnalysisCommentTable = sessionFactory
				.getCurrentSession()
				.createSQLQuery("update ehr_analysis_comment set previous_client_id = client_id,client_id='"+originalClientId+"' where client_id in("+replaceableClientIdArray+")");
		ehrAnalysisCommentTable.executeUpdate();
		
		SQLQuery ehrAppointmentMasterTable = sessionFactory
				.getCurrentSession()
				.createSQLQuery("update ehr_appointment_master set previous_client_id = client_id,client_id='"+originalClientId+"' where client_id in("+replaceableClientIdArray+")");
		ehrAppointmentMasterTable.executeUpdate();
		
		SQLQuery ehrClientHraLogTable = sessionFactory
				.getCurrentSession()
				.createSQLQuery("update ehr_client_hra_log set previous_client_id = client_id,client_id='"+originalClientId+"' where client_id in("+replaceableClientIdArray+")");
		ehrClientHraLogTable.executeUpdate();
		
		SQLQuery ehrClientSelfUploadReportTable = sessionFactory
				.getCurrentSession()
				.createSQLQuery("update ehr_client_self_upload_report set previous_client_id = client_id,client_id='"+originalClientId+"' where client_id in("+replaceableClientIdArray+")");
		ehrClientSelfUploadReportTable.executeUpdate();
		
		SQLQuery ehrDemoghraphicLogTable = sessionFactory
				.getCurrentSession()
				.createSQLQuery("update ehr_demoghraphic_log set previous_client_id = client_id,client_id='"+originalClientId+"' where client_id in("+replaceableClientIdArray+")");
		ehrDemoghraphicLogTable.executeUpdate();
		
		SQLQuery ehrEmailRecordTable = sessionFactory
				.getCurrentSession()
				.createSQLQuery("update ehr_email_record set previous_client_id = client_id,client_id='"+originalClientId+"' where client_id in("+replaceableClientIdArray+")");
		ehrEmailRecordTable.executeUpdate();
		
		SQLQuery ehrFeedbackTable = sessionFactory
				.getCurrentSession()
				.createSQLQuery("update ehr_feedback set previous_client_id = client_id,client_id='"+originalClientId+"' where client_id in("+replaceableClientIdArray+")");
		ehrFeedbackTable.executeUpdate();
		
		SQLQuery ehrFollowUpRecordTable = sessionFactory
				.getCurrentSession()
				.createSQLQuery("update ehr_follow_up_record set previous_client_id = client_id,client_id='"+originalClientId+"' where client_id in("+replaceableClientIdArray+")");
		checkupMasterTable.executeUpdate();
		
		SQLQuery ehrReminderTable = sessionFactory
				.getCurrentSession()
				.createSQLQuery("update ehr_reminder set previous_client_id = client_id,client_id='"+originalClientId+"' where client_id in("+replaceableClientIdArray+")");
		ehrReminderTable.executeUpdate();
		
		SQLQuery ehrSmsRecordTable = sessionFactory
				.getCurrentSession()
				.createSQLQuery("update ehr_sms_record set previous_client_id = client_id,client_id='"+originalClientId+"' where client_id in("+replaceableClientIdArray+")");
		ehrSmsRecordTable.executeUpdate();
		
		SQLQuery ehrTestResultTable = sessionFactory
				.getCurrentSession()
				.createSQLQuery("update ehr_test_result set previous_client_id = client_id,client_id='"+originalClientId+"' where client_id in("+replaceableClientIdArray+")");
		ehrTestResultTable.executeUpdate();
		
		SQLQuery queryInsert = sessionFactory
				.getCurrentSession()
				.createSQLQuery("insert into ehr_client_merge_details( original_client_id,replaceable_client_id,added_by,added_on,remote_ip ) " +
						"values('"+originalClientId+"','"+replaceableClientIdArray+"','"+addedBy+"','"+addedOn+"','"+remoteIp+"');");
		queryInsert.executeUpdate();
		
	}

	@Override
	public String getUserNameByClientId(Integer clientId) {
		SQLQuery query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT client_user_id FROM clients WHERE client_id = "+clientId+" limit 1");
		String userName = (String) query.uniqueResult();
		return userName;
	}
	
	@Override
	public void changeClientStatus(JSONArray clients) {
		for (int i = 0; i < clients.size(); i++) {
			JSONObject client = (JSONObject) clients.get(i);
			Integer clientId = Integer.parseInt(client.get("clientId").toString());
			Integer checkupId = Integer.parseInt(client.get("checkupId").toString());
			Integer statusId = Integer.parseInt(client.get("statusId").toString());
			String changedBy = (String) client.get("changedBy");
			String changedOn = (String) client.get("changedOn");
			try {
				/*String sql = "INSERT INTO ehr_visit_status(client_id,checkup_id,work_status_id,changed_on,changed_by) "
						+ "values('" + clientId + "'," + checkupId + ",'" + statusId + "','" + changedOn + "','"
						+ changedBy + "')" + " ON DUPLICATE KEY UPDATE work_status_id=" + statusId + ",changed_on='"
						+ changedOn + "'" + ",changed_by='" + changedBy + "'";
				SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
				query.executeUpdate();*/
				
				//added by kishor for automatic open close visit when upload report again this per. Id
				String sql = "INSERT INTO ehr_visit_status(client_id,checkup_id,work_status_id,changed_on,changed_by) "
						+ "values('" + clientId + "'," + checkupId + ",'" + statusId + "','" + changedOn + "','"
						+ changedBy + "')" + " ON DUPLICATE KEY UPDATE work_status_id=" + statusId + ",changed_on='"
						+ changedOn + "'" + ",changed_by='" + changedBy + "' , is_closed=null ";
				SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
				query.executeUpdate();
				
				System.err.println("insert");
				String sql1 = "INSERT INTO ehr_visit_status_all(client_id,checkup_id,work_status_id,changed_on,changed_by) "
						+ "values('" + clientId + "'," + checkupId + ",'" + statusId + "','" + changedOn + "','"
						+ changedBy + "')";
				SQLQuery query1 = sessionFactory.getCurrentSession().createSQLQuery(sql1);
				query1.executeUpdate();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public Integer countVisitStatus(JSONObject object) {
		String isLeader = (String) object.get("isLeader");
		String userId = (String) object.get("userId");
		String userTypeId = (String) object.get("userTypeId");
		Integer pageSize = (Integer) object.get("pageSize");
		String status = (String) object.get("status");
		String query = "";
		if(object.get("status").equals("1")) {
			query = "SELECT count(work_status_id) as pages FROM ehr_visit_status where work_status_id = "+status;
		}else {
			String sql = "";
			String evsSql = "";
			if(isLeader.equals("N") && !userTypeId.equals("12")) {
				sql = " and etm.assigned_to = "+userId+" and ets.submitted_on is NULL ";
			} else if(isLeader.equals("Y") && status.equals("4")) {
				evsSql = " or evs.work_status_id = 9 ";
			}
			query = "SELECT count(ets.task_slave_id) as pages" +
					" FROM" + 
					" ehr_task_slave ets" + 
					" INNER JOIN ehr_task_master etm" + 
					" ON etm.task_id = ets.task_id" + sql + 
					" where " +
					" task_slave_id in (" + 
					" SELECT max(task_slave_id) as task_slave_id FROM ehr_task_slave ets1" + 
					" inner join ehr_visit_status as evs " +
					" where ets1.checkup_id = evs.checkup_id and evs.is_closed is null and " +
					" (evs.work_status_id = "+status+"" + 
					evsSql +") group by evs.checkup_id)";
		}
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(query);
		sqlQuery.addScalar("pages", LongType.INSTANCE);
		Long countResults = (Long) sqlQuery.uniqueResult();
		int pageCount = (int) (Math.ceil(countResults / pageSize));
		return pageCount;
	}

	@Override
	public JSONArray getWork(JSONObject object) {
		SQLQuery query = null;
		JSONArray jsonArray = new JSONArray();
		String isLeader = (String) object.get("isLeader");
		String userId = (String) object.get("userId");
		String userTypeId = (String) object.get("userTypeId");
		String status = (String) object.get("status");
		Integer pageId = (Integer) object.get("pageId");
		Integer pageSize = (Integer) object.get("pageSize");
		String searchBy = (String) object.get("searchBy");
		String searchText = (String) object.get("searchText");
		String searchByQuery = "";
		try {
			//Start Of Pagination
			if(pageId==1){
				pageId = 0;
			} else{  
	        	pageId = (pageId-1)*pageSize;  
	        }
			//End Of Pagination
			
			if(searchBy != null) {
				if(searchBy.equals("name")) {
					searchByQuery = "c.client_full_name like '%"+searchText+"%' and";
				} else if(searchBy.equals("clientId")) {
					searchByQuery = "evs.client_id = '"+searchText+"' and";
				} else if(searchBy.equals("visitId")) {
					searchByQuery = "evs.checkup_id = '"+searchText+"' and";
				} else if(searchBy.equals("memberId")) {
					searchByQuery = "c.member_id = '"+searchText+"' and";
				}
			}
			
			if(object.get("status").equals("1") || object.get("status").equals("15") || object.get("status").equals("16")) {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery("SELECT c.client_id,c.client_full_name as client_name,c.member_id,evs.checkup_id,cm.checkup_date,c.client_user_id," + 
							" cm.package_id,pm.package_desc,pm.analysis_flag" +
							" FROM ehr_visit_status evs" + 
							" INNER JOIN" + 
							" clients c" + 
							" ON evs.client_id = c.client_id" + 
							" INNER JOIN" + 
							" checkup_master cm" + 
							" ON evs.checkup_id = cm.checkup_id" + 
							" left join ehr_package_master pm on cm.package_id = pm.package_id" +
							" where cm.is_deleted='N' and evs.is_closed is null and " +
							searchByQuery +
							" evs.work_status_id ="+status);
			query.setFirstResult(pageId);
			query.setMaxResults(pageSize);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				JSONObject jsonObject = new JSONObject();
				if (row[0] != null) {
					jsonObject.put("clientId", row[0].toString());
				}
				if (row[1] != null) {
					jsonObject.put("clientName", row[1].toString());
				}
				if (row[2] != null) {
					jsonObject.put("memberId", row[2].toString());
				}else {
					jsonObject.put("memberId","-");
				}
				if (row[3] != null) {
					jsonObject.put("checkupId", row[3].toString());
				}
				if (row[4] != null) {
					jsonObject.put("checkupDate", DateConvertUtil.convertDate(row[4].toString()));
				}
				if (row[5] != null) {
					jsonObject.put("clientUserId", row[5].toString());
				}
				if (row[6] != null) {
					jsonObject.put("packageId", row[6].toString());
				}
				if (row[7] != null) {
					jsonObject.put("packageName", row[7].toString());
				}else {
					jsonObject.put("packageName", "-");
				}
				if (row[8] != null) {
					jsonObject.put("analysisFlag", row[8].toString());
				}else {
					jsonObject.put("analysisFlag", "N");
				}
				jsonArray.add(jsonObject);
			}
			
			} else {
				String sql = "";
				String evsSql = "";
				if(isLeader.equals("N") && !userTypeId.equals("12")) {
					sql = " and etm.assigned_to = "+userId+" and ets.submitted_on is NULL ";
				} else if(isLeader.equals("Y") && status.equals("4")) {
					evsSql = " or evs.work_status_id = 9 ";
				}
				String newQuery = "SELECT c.client_id,c.client_full_name as client_name,c.member_id,cm.checkup_id,cm.checkup_date," +
						" etm.assigned_by, etm.assigned_to, etm.assigned_on," + 
						" concat(aby.title,' ',aby.first_name,' ',aby.last_name) as assigned_by_name," + 
						" concat(ato.title,' ',ato.first_name,' ',ato.last_name) as assigned_to_name," +
						" ets.task_id,c.client_user_id," +
						" cm.package_id,pm.package_desc,pm.analysis_flag" +
						" FROM" + 
						" ehr_task_slave ets" + 
						" INNER JOIN ehr_task_master etm" + 
						"            ON etm.task_id = ets.task_id" + sql + 
						"            INNER JOIN" + 
						"        clients c" + 
						"            ON ets.client_id = c.client_id" + 
						"    INNER JOIN" + 
						"        checkup_master cm" + 
						"            ON ets.checkup_id = cm.checkup_id" + 
						"            inner join" + 
						"        ehr_users aby" + 
						"            on aby.user_id = etm.assigned_by" + 
						"    inner join" + 
						"        ehr_users ato" + 
						"            on ato.user_id =  etm.assigned_to" + 
						"    left join" + 
						"        ehr_package_master pm" + 
						"            on cm.package_id = pm.package_id" +
						" where cm.is_deleted='N' and " +
						" task_slave_id in (" + 
						" SELECT max(task_slave_id) as task_slave_id FROM ehr_task_slave ets1" + 
						" inner join ehr_visit_status as evs " +
						" where " +
						searchByQuery + " ets1.checkup_id = evs.checkup_id and evs.is_closed is null and (evs.work_status_id = "+status+"" + 
						evsSql +") group by evs.checkup_id) order by task_slave_id desc";
				query = sessionFactory
						.getCurrentSession()
						.createSQLQuery(newQuery);
				query.setFirstResult(pageId);
				query.setMaxResults(pageSize);
				List<Object[]> rows = query.list();
				for (Object[] row : rows) {
					JSONObject jsonObject = new JSONObject();
					if (row[0] != null) {
						jsonObject.put("clientId", row[0].toString());
					}
					if (row[1] != null) {
						jsonObject.put("clientName", row[1].toString());
					}
					if (row[2] != null) {
						jsonObject.put("memberId", row[2].toString());
					}else {
						jsonObject.put("memberId","-");
					}
					if (row[3] != null) {
						jsonObject.put("checkupId", row[3].toString());
					}
					if (row[4] != null) {
						jsonObject.put("checkupDate", DateConvertUtil.convertDate(row[4].toString()));
					}
					if (row[5] != null) {
						jsonObject.put("assignedBy", row[5].toString());
					}
					if (row[6] != null) {
						jsonObject.put("assignedTo", row[6].toString());
					}
					if (row[7] != null) {
						jsonObject.put("assignedOn", DateConvertUtil.convertDateTime(row[7].toString()));
					}
					if (row[8] != null) {
						jsonObject.put("assignedByName", row[8].toString());
					}
					if (row[9] != null) {
						jsonObject.put("assignedToName", row[9].toString());
					}
					if (row[10] != null) {
						jsonObject.put("taskId", row[10].toString());
					}
					if (row[11] != null) {
						jsonObject.put("clientUserId", row[11].toString());
					}
					if (row[12] != null) {
						jsonObject.put("packageId", row[12].toString());
					}
					if (row[13] != null) {
						jsonObject.put("packageName", row[13].toString());
					}else {
						jsonObject.put("packageName", "-");
					}
					if (row[14] != null) {
						jsonObject.put("analysisFlag", row[14].toString());
					}else {
						jsonObject.put("analysisFlag", "N");
					}
					jsonArray.add(jsonObject);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public String assignTask(TaskMaster taskMaster) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(taskMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Task Assign Successfully";
	}

	@Override
	public String submitTask(JSONArray clients) {
		JSONArray clientArray = new JSONArray();
		for(int i=0;i<clients.size();i++) {
			JSONObject client = (JSONObject) clients.get(i);
			Integer clientId = Integer.parseInt(client.get("clientId").toString());
			Integer checkupId = Integer.parseInt(client.get("checkupId").toString());
			Integer taskId = Integer.parseInt(client.get("taskId").toString());
			String changedBy = (String) client.get("changedBy");
			String changedOn = (String) client.get("changedOn");
			String statusId = (String) client.get("workStatusId");
			String analysisFlag = (String) client.get("analysisFlag");
			Integer workStatusId = 0;
			if(statusId.equals("2") || statusId.equals("8")) {
				workStatusId = isAllReportVerified(client);//Check All Report Verified or Not
				
				if(workStatusId.equals(15)) {
					JSONObject clientObject = new JSONObject();
					clientObject.put("ehrId", clientId);
					clientObject.put("visitId", checkupId);
					clientObject.put("doctorId", changedBy);
					clientArray.add(clientObject);
				}
			}else if(statusId.equals("5")){
				workStatusId = 7;//Temporary for NA Flag
			}else if(statusId.equals("11")){
				workStatusId = 16;
			}else if(statusId.equals("13")){
				workStatusId = 14;
			}
			client.put("statusId", workStatusId);
			
			String hqlUpdate = "update TaskSlave set submittedOn=:submittedOn "
					+ "where clientId=:clientId and checkUpId=:checkupId "
					+ "and taskMaster.taskId=:taskId";
			Query query = sessionFactory.getCurrentSession().createQuery(hqlUpdate);
			query.setParameter("submittedOn",new Date());
			query.setParameter("clientId",clientId);
			query.setParameter("checkupId",checkupId);
			query.setParameter("taskId",taskId);
			query.executeUpdate();
		}
		
		changeClientStatus(clients);
		
		//Code For sending Clients to API
		/*if(clientArray.size() > 0) {
			String apiUrl = hllInvUrl + "stockImport";
			try {                    
				URL url = new URL(apiUrl);                    
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();                    
				conn.setDoInput(true);                    
				conn.setDoOutput(true);                    
				conn.setRequestMethod("POST");                    
				conn.setRequestProperty("Content-Type", "application/json");                                        
				OutputStream os = conn.getOutputStream();                    
				os.write(clientArray.toJSONString().getBytes());                    
				os.flush();                    
				if (conn.getResponseCode() != 200) {                        
					throw new RuntimeException("HTTP error code : " + conn.getResponseCode()+" HTTP message "+conn.getResponseMessage());                    
				}                    
				BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));                    
				String output = br.readLine();                    
				conn.disconnect();                    
				System.err.println("output:"+output);                
				} catch (Exception ex) {                    
					ex.printStackTrace();                
				}
		}*/
		
		return "Task Submitted Successfully";
	}
	
	@Override
	public Integer isAllReportVerified(JSONObject client) {
		Integer clientId = Integer.parseInt(client.get("clientId").toString());
		Integer checkupId = Integer.parseInt(client.get("checkupId").toString());
		String statusId = (String) client.get("workStatusId");
		String analysisFlag = (String) client.get("analysisFlag");
		
		SQLQuery reportSql = sessionFactory.getCurrentSession().createSQLQuery(
				" SELECT GROUP_CONCAT(crl.client_report_line_id) as report_ids,count(*) as count" + 
				" FROM client_report_head crh" + 
				" INNER JOIN" + 
				" client_report_line crl" + 
				" ON crh.client_report_id = crl.client_report_id" + 
				" where crl.report_isactive = 'Y' and " + 
				" crh.client_id = "+clientId+" and crh.client_checkup_id = "+checkupId+"" + 
				" group by crh.client_checkup_id");
		reportSql.addScalar("report_ids", StringType.INSTANCE);
		reportSql.addScalar("count", IntegerType.INSTANCE);
		Object[] reports = (Object[]) reportSql.uniqueResult();
		if(reports == null) {
			return 3;
		}
		String reportIds = (String) reports[0];
		Integer reportCount = (Integer) reports[1];
		
		String sql = "";
		if(statusId.equals("2")) {
			sql = " SELECT COUNT(*) as verify_report_count FROM (" + 
					" SELECT MAX(rv.client_report_verify_id) AS client_report_verify_id" + 
					" FROM clientreportverify_clientreportline crv" + 
					" inner join report_verification rv on crv.client_report_verify_id = rv.client_report_verify_id" + 
					" WHERE rv.reject_id is NULL and" + 
					" crv.client_report_line_id IN ("+reportIds+")" + 
					" group by crv.client_report_line_id\n" + 
					") as temp";
		}else if(statusId.equals("8")) {
			sql = " select count(*) as verify_report_count from ehr_data_entry_verification where is_reject_verify = 1 and verify_id in (SELECT " + 
					" max(verify_id) as verify_id" + 
					" FROM" + 
					" ehr_data_entry_verification edev" + 
					" WHERE edev.client_report_line_id IN ("+reportIds+")" + 
					" group by" + 
					" edev.client_report_line_id)";
		}
		
		SQLQuery verifyReportSql = sessionFactory.getCurrentSession().createSQLQuery(sql);
		verifyReportSql.addScalar("verify_report_count", IntegerType.INSTANCE);
		Integer verifyReportCount = (Integer) verifyReportSql.uniqueResult();
		
		Integer workStatusId = 3;
		if(statusId.equals("2") && reportCount.equals(verifyReportCount)) {
			workStatusId = 4;
		} else if(statusId.equals("8")) {
			if(reportCount.equals(verifyReportCount) && analysisFlag.equals("Y")) {
				workStatusId = 15;
			}else if(reportCount.equals(verifyReportCount)) {
				workStatusId = 10;
			}else {
				workStatusId = 9;
			}
		}
		return workStatusId;
	}

	@Override
	public String dataEntryVerify(DataEntryVerification dataEntryVerification) {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(dataEntryVerification);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Success";
	}

	@Override
	public Integer getVisitStatus(Integer visitId) {
		String sql = "SELECT IFNULL(sum(work_status_id), 0) as work_status_id FROM ehr_visit_status where checkup_id="+visitId;
		SQLQuery workStatusSql = sessionFactory.getCurrentSession().createSQLQuery(sql);
		workStatusSql.addScalar("work_status_id", IntegerType.INSTANCE);
		Integer status = (Integer) workStatusSql.uniqueResult();
		return status;
	}

	@Override
	public String generateReport(CheckUpMaster checkUpMaster) {
		String hqlUpdate = "update CheckUpMaster set healthReport=:healthReport,"
				+ " hrGeneratedOn=:hrGeneratedOn,hrGeneratedBy=:hrGeneratedBy"
				+ " where checkUpId=:checkupId";
		Query query = sessionFactory.getCurrentSession().createQuery(hqlUpdate);
		query.setParameter("healthReport",true);
		query.setParameter("hrGeneratedOn",new Date());
		query.setParameter("hrGeneratedBy",checkUpMaster.getHrGeneratedBy());
		query.setParameter("checkupId",checkUpMaster.getCheckUpId());
		query.executeUpdate();
		return "Report Generated Successfully";
	}
	
	@Override
	public void updateEHRIDAPI(JSONArray jsonArray) {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		try {
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				Integer clientId=(Integer) jsonObject.get("MB_EHR_NO");
				Integer visitId=(Integer) jsonObject.get("MB_VISIT_ID");
				if(clientId == null || clientId.equals(null)) {
					clientId = 0;
				}if(visitId == null || visitId.equals(null)) {
					visitId = 0;
				}
				String sql = "INSERT INTO ehr_update_api(client_id,visit_id,app_no,ap_year,added_on,relation,status_code,error_code) " + "values('"
						//+ jsonObject.get("MB_EHR_NO") + "','" + jsonObject.get("MB_VISIT_ID") + "','" 
						+ clientId + "','" + visitId + "','" 
						+ jsonObject.get("MB_APP_NO") + "','"
						+ jsonObject.get("MB_YEAR") + "','" + currentTime + "','"
						+ jsonObject.get("MB_RELATION") + "','"
						+ jsonObject.get("status_code") + "','"
						+ jsonObject.get("error_code") 
						+ "')";
				SQLQuery queryInsert = sessionFactory.getCurrentSession().createSQLQuery(sql);
				queryInsert.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public JSONArray getClientStatus(JSONObject object) {
		Integer clientId = (Integer) object.get("clientId");
		Integer visitId = (Integer) object.get("visitId");
		String searchBy = "";
		JSONArray jsonArray = new JSONArray();
		if(clientId != null) {
			searchBy = "evs.client_id = "+clientId;
		}else if(visitId != null) {
			searchBy = "evs.checkup_id = "+visitId;
		}
		String sql = "SELECT " + 
				"    evs.client_id, evs.checkup_id, evs.work_status_id," + 
				"    concat(c.client_first_name,' ',c.client_middle_name,' ',c.client_last_name) as client_name," + 
				"    ews.status,cm.checkup_date,evs.is_closed,concat(u.first_name,' ',u.last_name) as username" + 
				" FROM" + 
				"    ehr_visit_status evs" + 
				"    inner join ehr_work_status ews" + 
				"    on evs.work_status_id = ews.status_id" + 
				"    inner join clients c" + 
				"    on evs.client_id = c.client_id" + 
				"    inner join checkup_master cm" + 
				"    on evs.checkup_id = cm.checkup_id" + 
				" left join ehr_users u on evs.closed_by = u.user_id" +
				" WHERE cm.is_deleted='N' and  "+searchBy;
		
		Query query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(sql);
		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			JSONObject jsonObject = new JSONObject();
			if (row[0] != null) {
				jsonObject.put("clientId", row[0].toString());
			}
			if (row[1] != null) {
				jsonObject.put("checkupId", row[1].toString());
			}
			if (row[2] != null) {
				jsonObject.put("workStatusId", row[2].toString());
			}
			if (row[3] != null) {
				jsonObject.put("clientName", row[3].toString());
			}
			if (row[4] != null) {
				jsonObject.put("status", row[4].toString());
			}
			if (row[5] != null) {
				jsonObject.put("checkupDate", DateConvertUtil.convertDate(row[5].toString()));
			}
			if (row[6] != null && row[6].toString().equals("Y")) {
				jsonObject.put("status",row[4].toString()+ " - Temporarily visit closed by : "+row[7].toString());
			}
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}

	@Override
	public String closeVisit(JSONArray clients) {
		for (int i = 0; i < clients.size(); i++) {
			JSONObject client = (JSONObject) clients.get(i);
			Integer clientId = Integer.parseInt(client.get("clientId").toString());
			Integer checkupId = Integer.parseInt(client.get("checkupId").toString());
			String closedBy = (String) client.get("changedBy");
			String closedOn = (String) client.get("changedOn");
			String narration = (String) client.get("narration");
			try {
				String sql = "UPDATE ehr_visit_status set is_closed='Y', closed_on='"
						+ closedOn + "'" + ",closed_by='" + closedBy + "',narration='"+narration+"' "
						+ " where checkup_id = "+checkupId;
				SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
				query.executeUpdate();
				
				
				String sql1 = "INSERT INTO ehr_visit_status_all(client_id,checkup_id,is_closed,changed_on,changed_by,closed_by,work_status_id,narration) "
						+ "values('" + clientId + "'," + checkupId + ",'Y','" + closedOn + "','"
						+ closedBy + "', '"+closedBy+"',1,'"+narration+"')";
				SQLQuery query1 = sessionFactory.getCurrentSession().createSQLQuery(sql1);
				query1.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "Visit Closed Successfully";
	}
	
	@Override
	public Integer duplicateCheck(ClientMaster clientMaster) {
		System.err.println("testsing");
		System.err.println("DOB"+clientMaster.getClientDOB());

		//for testing purpose only
		/*clientMaster.setFirstName("Mr. Sakharam");
		clientMaster.setLastName("Pawar");
		clientMaster.setGender("M");
		clientMaster.setClientDOB("1970-02-04");
		clientMaster.setMobNo("919766497799");
		clientMaster.setEmailId("Suraj.rajput7@gmail.com");*/
		String fName="";
		String lName="";
		String gender="";
		String dob="";
		//String memberId="";
		
		if(clientMaster.getFirstName()==null || clientMaster.getFirstName()=="null") {
			fName=" client_first_name is null";
			
		}else {
			fName=" client_first_name = '" + clientMaster.getFirstName() +"'";
		}
		
		if(clientMaster.getLastName()==null || clientMaster.getLastName()=="null") {
			lName=" AND client_last_name is null";
			
		}else {
			lName=" AND client_last_name = '" + clientMaster.getLastName() +"'";
		}
		
		if(clientMaster.getGender()==null || clientMaster.getGender()=="null") {			
			gender=" AND client_gender is null";
		}else {
			gender=" AND client_gender = '" + clientMaster.getGender() + "'";
		}
		
		if(clientMaster.getClientDOB()==null || clientMaster.getClientDOB()=="null") {			
			dob=" AND client_dob is null";
		}else {
			dob=" AND client_dob = '" + clientMaster.getClientDOB() + "'";
		}
		
		/*if(clientMaster.getMemberId()==null || clientMaster.getMemberId()=="null") {			
			memberId=" AND member_id is null";
		}else {
			memberId=" AND member_id = '" + clientMaster.getMemberId() + "'";
		}*/
		
		String sql = "select max(client_id) as client_id from clients where "
				+ fName
				+ lName
				+ gender
				+ dob;
				//+ memberId;
		
		/*String sql = "select client_id from clients where client_first_name = '" + clientMaster.getFirstName() + "'"
				+ " AND client_last_name = '" + clientMaster.getLastName() +"'"
				+ " AND client_gender = '" + clientMaster.getGender() + "'"
				+ " AND client_dob = '" + clientMaster.getClientDOB() + "'";*/
				/*+ " AND (client_mob_no = '" + clientMaster.getMobNo() + "' "
				+ " OR client_email='" + clientMaster.getEmailId()+ "') limit 1"*/
		SQLQuery clientSql = sessionFactory.getCurrentSession().createSQLQuery(sql);
		clientSql.addScalar("client_id", IntegerType.INSTANCE);
		Integer clientId = (Integer)clientSql.uniqueResult();
		return clientId;
	}

	//added by kishor for update Y flag of isSendEmail after send email with report
	@Override
	public int isSendEmail(int clientId, String clientReportName) {
		int returnInt=0;
		try {
			SQLQuery query1 = sessionFactory
					.getCurrentSession()
					.createSQLQuery("update client_report_line set is_email_send= 'Y' where client_report_line_id="+clientId+" ");
			query1.executeUpdate();
			returnInt=1;
		}catch(Exception e){
			e.printStackTrace();
			returnInt=0;
		}
		return returnInt;
	}

	//This function added by kishor for Check status of verified reports mail is send or not.
	@Override
	public String checkStatusBeforeSubmitTask(JSONArray clients) {
		String msg = "Check Visit ID";
		int count = 0;
		JSONArray clientArray = new JSONArray();
		for (int i = 0; i < clients.size(); i++) {
			JSONObject client = (JSONObject) clients.get(i);
			Integer clientId = Integer.parseInt(client.get("clientId")
					.toString());
			Integer checkupId = Integer.parseInt(client.get("checkupId")
					.toString());
			Integer taskId = Integer.parseInt(client.get("taskId").toString());
			String changedBy = (String) client.get("changedBy");
			String changedOn = (String) client.get("changedOn");
			// String statusId = (String) client.get("workStatusId");
			// String analysisFlag = (String) client.get("analysisFlag");

			SQLQuery reportSql = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							" SELECT GROUP_CONCAT(crl.client_report_line_id) as report_ids,count(*) as count"
									+ " FROM client_report_head crh"
									+ " INNER JOIN"
									+ " client_report_line crl"
									+ " ON crh.client_report_id = crl.client_report_id"
									+ " where crl.report_isactive = 'Y' and "
									+ " crh.client_id = "
									+ clientId
									+ " and crh.client_checkup_id = "
									+ checkupId
									+ ""
									+ " group by crh.client_checkup_id");
			reportSql.addScalar("report_ids", StringType.INSTANCE);
			reportSql.addScalar("count", IntegerType.INSTANCE);
			Object[] reports = (Object[]) reportSql.uniqueResult();
			if (reports == null) {
				// reports.length = 0;
			}
			String reportIds = (String) reports[0];
			Integer reportCount = (Integer) reports[1];
			String sql = "";

			sql = " SELECT COUNT(*) as verify_report_count FROM ("
					+ " SELECT MAX(rv.client_report_verify_id) AS client_report_verify_id"
					+ " FROM clientreportverify_clientreportline crv"
					+ " inner join report_verification rv on crv.client_report_verify_id = rv.client_report_verify_id"
					+ " WHERE rv.reject_id is NULL and"
					+ " crv.client_report_line_id IN (" + reportIds + ")"
					+ " group by crv.client_report_line_id\n" + ") as temp";

			SQLQuery verifyReportSql = sessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			verifyReportSql.addScalar("verify_report_count",
					IntegerType.INSTANCE);
			Integer verifyReportCount = (Integer) verifyReportSql
					.uniqueResult();

			// System.err.println("reportCount.equals(verifyReportCount)  "+
			// reportCount +"== "+verifyReportCount);
			if (reportCount.equals(verifyReportCount)) {

				String sql2 = "";
				String sql1 = "";
				String sql3 = "";

				sql3 = " SELECT GROUP_CONCAT(distinct crv.client_report_line_id) AS client_report_verify_id"
						+ " FROM clientreportverify_clientreportline crv"
						+ " inner join report_verification rv on crv.client_report_verify_id = rv.client_report_verify_id"
						+ " WHERE rv.reject_id is NULL"
						+ " and crv.client_report_line_id IN ("
						+ reportIds
						+ ")";

				SQLQuery verifyReportSql3 = sessionFactory.getCurrentSession()
						.createSQLQuery(sql3);
				verifyReportSql3.addScalar("client_report_verify_id",
						StringType.INSTANCE);
				String verifyReportIds = (String) verifyReportSql3
						.uniqueResult();

				sql2 = "select count(*) as verify_analysis_flag from client_report_line crl join ehr_package_master epm ON epm.package_id = crl.package_id"
						+ " where crl.client_report_line_id in ("
						+ verifyReportIds + ") and epm.analysis_flag = 'Y'";

				SQLQuery verifyReportSql2 = sessionFactory.getCurrentSession()
						.createSQLQuery(sql2);
				verifyReportSql2.addScalar("verify_analysis_flag",
						IntegerType.INSTANCE);
				Integer analysisFlagCount2 = (Integer) verifyReportSql2
						.uniqueResult();

				sql1 = "select count(*) as verify_count from client_report_line where client_report_line_id in ("
						+ verifyReportIds + ") and is_email_send = 'Y' ";
				SQLQuery verifyReportSql1 = sessionFactory.getCurrentSession()
						.createSQLQuery(sql1);
				verifyReportSql1
						.addScalar("verify_count", IntegerType.INSTANCE);
				Integer verifyEmailCount = (Integer) verifyReportSql1
						.uniqueResult();

				// System.err.println("analysisFlagCount2 == verifyEmailCount  "+
				// analysisFlagCount2 +"== "+verifyEmailCount);
				if (analysisFlagCount2 == verifyEmailCount) {
					// msg="";
				} else {
					msg = msg + "," + checkupId;
					count++;
				}

			} else {

				if (count == 0) {
					msg = "";
				} /*else {
					msg = msg;
				}*/
			}
		}

		return msg;
	}

	@Override
	public String BeneficiaryId(String clientIdd,Integer visitId) {
		String BeneficiaryId="";
		int clientIddd=Integer.parseInt(clientIdd);
		try {
			
			System.err.println("clientIddd----"+clientIddd+"---------clientIddd----"+visitId);
			//clientIddd=150154;
			//visitId=52245;
			SQLQuery reportSql = sessionFactory
					.getCurrentSession()
					//.createSQLQuery("SELECT CONCAT(A.ap_year, '', A.ap_app_no) As beneficiary_id FROM clients c JOIN ehr_appointment_master A ON (c.client_id = A.client_id AND c.member_id = A.ap_member_id) JOIN checkup_master cm ON (A.old_ap_check_up_date = cm.old_checkup_date AND A.ap_check_up_date = cm.checkup_date AND c.client_id = cm.client_id) WHERE A.client_id = "+clientIddd+" AND cm.checkup_id = "+visitId+" ");
					.createSQLQuery("SELECT CONCAT(A.ap_year, '', A.ap_app_no) As beneficiary_id FROM clients c JOIN ehr_appointment_master A ON (c.client_id = A.client_id AND c.member_id = A.ap_member_id) JOIN checkup_master cm ON (c.client_id = cm.client_id AND A. client_id=cm.client_id AND A.checkup_id = cm.checkup_id ) WHERE A.client_id = "+clientIddd+" AND cm.checkup_id = "+visitId+" limit 1 ");

			reportSql.addScalar("beneficiary_id", StringType.INSTANCE);
			BeneficiaryId  = (String) reportSql.uniqueResult();						
			
			System.err.println("hhhhh----"+BeneficiaryId);
			if(BeneficiaryId==null) {
				BeneficiaryId="No Data Available";
			}else {
				BeneficiaryId=BeneficiaryId;
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
			BeneficiaryId="error";
		}
		return BeneficiaryId;
	}
	
	@Override
	public void updatEmailResponseAPI(JSONArray jsonArray) {
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = simpleDateFormat.format(date);
		try {
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject jsonObject = (JSONObject) jsonArray.get(i);
				String sql = "INSERT INTO ehr_update_email_response_api(client_id,visit_id,beneficiary_id,client_report_line_id,response_code,added_on) " + "values('"
						+ jsonObject.get("clientId") + "',"
						+ "'" + jsonObject.get("visitId") + "','" 
						+ jsonObject.get("BeneficiaryId") + "','"
						+ jsonObject.get("clientReportLineId") + "','"
						+ jsonObject.get("responseCode") + "','" + currentTime
						+ "')";
				SQLQuery queryInsert = sessionFactory.getCurrentSession().createSQLQuery(sql);
				queryInsert.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ClientMaster getClientByclientIdSearch(Integer clientId, HttpServletRequest request) {
		SQLQuery query = null;
		ClientMaster clientMaster = new ClientMaster();
		clientMaster.setClientId(clientId);
		
		HttpSession session=request.getSession();
		String userTypeId=(String) session.getAttribute("userTypeId");
		String packages=(String) session.getAttribute("packages");
		String memberCode=(String) session.getAttribute("memberCode");
		String centers=(String) session.getAttribute("centers");
		System.err.println("centers id= "+centers +"userTypeId-= "+userTypeId);
		
		System.err.println("getClientByclientIdSearchmmmmmm==="+memberCode);
		if(packages.isEmpty()){
			packages="0";
		}
		try {
			if(userTypeId.equals("4") || userTypeId.equals("13")){
				query = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								"SELECT client_first_name,client_last_name,client_mob_no,package_desc,clients.add_at,client_email,client_user_id,clients.package_id,clients.center_id,checkup_master.checkup_date,checkup_master.checkup_id,classification,member_id,client_middle_name,is_verified,client_isactive,client_gender,client_blood_group,age,client_height,client_weight,contact_flag FROM clients left join ehr_package_master on clients.package_id=ehr_package_master.package_id left join checkup_master ON checkup_master.client_id=clients.client_id left join ehr_analysis_comment on ehr_analysis_comment.client_id = clients.client_id WHERE clients.client_id ="+ clientId+" and clients.is_deleted='N' and member_id Like '"+memberCode+"%' ");
			}else if(userTypeId.equals("14")){
				query = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								"SELECT client_first_name,client_last_name,client_mob_no,package_desc,clients.add_at,client_email,client_user_id,clients.package_id,clients.center_id,checkup_master.checkup_date,checkup_master.checkup_id,classification,member_id,client_middle_name,is_verified,client_isactive,client_gender,client_blood_group,age,client_height,client_weight,contact_flag FROM clients left join ehr_package_master on clients.package_id=ehr_package_master.package_id left join checkup_master ON checkup_master.client_id=clients.client_id left join ehr_analysis_comment on ehr_analysis_comment.client_id = clients.client_id WHERE clients.is_deleted='N' and checkup_master.center_id in ("+centers+") and clients.client_id ="
										+ clientId);
			}else {
				query = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								"SELECT client_first_name,client_last_name,client_mob_no,package_desc,clients.add_at,client_email,client_user_id,clients.package_id,clients.center_id,checkup_master.checkup_date,checkup_master.checkup_id,classification,member_id,client_middle_name,is_verified,client_isactive,client_gender,client_blood_group,age,client_height,client_weight,contact_flag FROM clients left join ehr_package_master on clients.package_id=ehr_package_master.package_id left join checkup_master ON checkup_master.client_id=clients.client_id left join ehr_analysis_comment on ehr_analysis_comment.client_id = clients.client_id WHERE clients.is_deleted='N' and clients.client_id ="
										+ clientId);
			}
			

			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				if (row[0] != null) {
					clientMaster.setFirstName(row[0].toString().trim());
				}
				else{
					clientMaster.setFirstName("");
				}
				if (row[1] != null) {
					clientMaster.setLastName(row[1].toString().trim());
				}
				else{
					clientMaster.setLastName("");
				}
				if (row[2] != null) {
					clientMaster.setMobNo(row[2].toString());
				}
				PackageMaster packageMaster = new PackageMaster();
				if (row[3] != null && !row[3].toString().equals("null")) {
					packageMaster.setPackageDescription(row[3].toString());
				}else {
					packageMaster.setPackageDescription("");
				}
				if (row[4] != null) {
					clientMaster.setAddAt(DateConvertUtil.convertDateTime(row[4].toString()));
				}
				if (row[5] != null) {
					clientMaster.setEmailId(row[5].toString());
				}
				if (row[6] != null) {
					clientMaster.setUserId(row[6].toString());
				}
				if (row[7] != null) {
					packageMaster.setPackageId(Integer.parseInt(row[7]
							.toString()));
				}
				if (row[8] != null) {
					CentreMaster centreMaster = new CentreMaster();
					centreMaster
							.setCentreId(Integer.parseInt(row[8].toString()));
					clientMaster.setCentreMaster(centreMaster);
				}
				Set<CheckUpMaster> checkUpMasterSet = new HashSet<CheckUpMaster>();
				CheckUpMaster checkUpMaster = new CheckUpMaster();
				if (row[9] != null) {
					checkUpMaster.setCheckUpDate(row[9].toString());
				}
				if (row[10] != null) {
					checkUpMaster.setCheckUpId(Integer.parseInt(row[10]
							.toString()));
				}
				if (row[12] != null) {
					clientMaster.setMemberId(row[12].toString());
				}
				else{
					clientMaster.setMemberId("");
				}
				if (row[13] != null) {
					clientMaster.setMiddleName(row[13].toString().trim());
				}
				else{
					clientMaster.setMiddleName("");
				}
				if (row[14] != null) {
					clientMaster.setIsVerified(row[14].toString());
				}
				if (row[15] != null) {
					clientMaster.setIsActive(row[15].toString());
				}
				if (row[16] != null) {
					clientMaster.setGender(row[16].toString());
				}
				if (row[17] != null) {
					clientMaster.setBloodGroup(row[17].toString());
				}
				if (row[18] != null) {
					clientMaster.setClientAge(Integer.parseInt(row[18].toString()));
				}
				if (row[19] != null) {
					clientMaster.setClientHeight(row[19].toString());
				}
				if (row[20] != null) {
					clientMaster.setClientWeight(row[20].toString());
				}
				if (row[21] != null) {
					clientMaster.setContactFlag(row[21].toString());
				}
				checkUpMasterSet.add(checkUpMaster);
				clientMaster.setCheckUpMasterSet(checkUpMasterSet);
				clientMaster.setPackageMaster(packageMaster);

				if (row[11] != null) {
					clientMaster.setAddBy(row[11].toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clientMaster;
	}

	@Override
	public int savelogSaveClientApi(LogSaveClientApi logSaveClientApi) {
		Integer logId = null;
		int a = 0;
		try {
			logSaveClientApi.setAddedOn(new Date(new java.util.Date().getTime()));
			//sessionFactory.getCurrentSession().saveOrUpdate(logSaveClientApi);
			sessionFactory.getCurrentSession().save(logSaveClientApi);
			//logId = logSaveClientApi.getLogId();
			
			/*Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(LogSaveClientApi.class);
			criteria.add(Restrictions.eq("logId", logId));*/
			a=1;
		} catch (Exception e) {
			e.printStackTrace();
			a=0;
		}
		return a;
	}

	@Override
	public int saveBeneficiaryApiLog(GetBeneficiaryApiLog getBeneficiaryApiLog) {
		Long logId = null;
		int a = 0;
		try {
			//sessionFactory.getCurrentSession().saveOrUpdate(getBeneficiaryApiLog);
			sessionFactory.getCurrentSession().save(getBeneficiaryApiLog);
			logId = getBeneficiaryApiLog.getBeneficiaryLog();
			
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(GetBeneficiaryApiLog.class);
			criteria.add(Restrictions.eq("logId", logId));
			a=1;
		} catch (Exception e) {
			e.printStackTrace();
			a=0;
		}
		return a;
	}
	
	
	@Override
	public JSONArray getClientStatusAll(JSONObject object) {
		Integer clientId = (Integer) object.get("clientId");
		Integer visitId = (Integer) object.get("visitId");
		String searchBy = "";
		JSONArray jsonArray = new JSONArray();
		if(clientId != null) {
			searchBy = "evs.client_id = "+clientId;
		}else if(visitId != null) {
			searchBy = "evs.checkup_id = "+visitId;
		}
		String sql = "SELECT " + 
				"    evs.client_id, evs.checkup_id, evs.work_status_id," + 
				"    concat(c.client_first_name,' ',c.client_middle_name,' ',c.client_last_name) as client_name," + 
				"    ews.status,evs.changed_on,evs.is_closed,concat(u.first_name,' ',u.last_name) as username,evs.narration" + 
				" FROM" + 
				"    ehr_visit_status_all evs" + 
				"    inner join ehr_work_status ews" + 
				"    on evs.work_status_id = ews.status_id" + 
				"    inner join clients c" + 
				"    on evs.client_id = c.client_id" + 
				"    inner join checkup_master cm" + 
				"    on evs.checkup_id = cm.checkup_id" + 
				" left join ehr_users u on (evs.closed_by = u.user_id or evs.changed_by = u.user_id)" +
				" WHERE cm.is_deleted='N' and evs.client_id = "+clientId+" and evs.checkup_id = "+visitId+" ";
		
		Query query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(sql);
		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			JSONObject jsonObject = new JSONObject();
			if (row[0] != null) {
				jsonObject.put("clientId", row[0].toString());
			}
			if (row[1] != null) {
				jsonObject.put("checkupId", row[1].toString());
			}
			if (row[2] != null) {
				jsonObject.put("workStatusId", row[2].toString());
			}
			if (row[3] != null) {
				jsonObject.put("clientName", row[3].toString());
			}
			if (row[4] != null) {
				jsonObject.put("status", row[4].toString());
			}
			if (row[5] != null) {
				jsonObject.put("checkupDate", DateConvertUtil.convertDateTime(row[5].toString()));
			}
			/*if (row[6] != null && row[6].toString().equals("Y")) {
				jsonObject.put("status",row[4].toString()+ " - Temporarily visit closed by : "+row[7].toString()+". Narration - "+row[8].toString());
			}*/
			if (row[6] != null && row[6].toString().equals("Y")) {
				jsonObject.put("status",row[4].toString()+ " - Temporarily visit closed. Narration - "+row[8].toString());
			}
			if (row[7] != null) {
				jsonObject.put("UserName", row[7].toString());
			}
			jsonArray.add(jsonObject);
		}
		return jsonArray;
	}


	@Override
	public String updateSmsIsOnOffFlag(ClientMaster clientMaster) {
		try {
			/*ClientMaster clientMaster = (ClientMaster) sessionFactory
					.getCurrentSession().get(ClientMaster.class,
							clientMaster2.getClientId());*/
			System.err.println("daoimpl"+clientMaster.getClientId());
			System.err.println("getContactFlag"+clientMaster.getContactFlag());

			SQLQuery query1 = sessionFactory
					.getCurrentSession()
					.createSQLQuery("update clients set contact_flag= '"+clientMaster.getContactFlag()+"',mod_by= '"+clientMaster.getModBy()+"',mod_at= '"+clientMaster.getModAt()+"' where client_id="+clientMaster.getClientId()+" ");
			query1.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "SMS On OFF updated";
	}

}
