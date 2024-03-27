package com.hms.indus.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tempuri.IService1Proxy;

import com.hms.indus.bo.AppointmentMaster;
import com.hms.indus.bo.CentreMaster;
import com.hms.indus.bo.CheckUpMaster;
import com.hms.indus.bo.ClientMaster;
import com.hms.indus.bo.GetBeneficiaryApiLog;
import com.hms.indus.bo.PackageMaster;
import com.hms.indus.service.AppointmentService;
import com.hms.indus.service.CentreService;
import com.hms.indus.service.CheckUpService;
import com.hms.indus.service.ClientService;

import java.io.Reader;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/webService")
public class WebServiceController {

	@Autowired
	ClientService clientService;

	@Autowired
	CheckUpService checkUpService;

	@Autowired
	AppointmentService appointmentService;

	@Autowired
	CentreService centreService;

	//String fromDate="13-Jul-2018";
	//String toDate="14-Jul-2018";

	@RequestMapping(value = "/callWebServiceGetBeneficiary", method = RequestMethod.GET)
	public @ResponseBody JSONArray callWebServiceGetBeneficiary(@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) throws ParseException {
		HttpSession session = null;
		JSONArray jsonArrayOfClientId = new JSONArray();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		DateFormat apiDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		if (fromDate != null && !fromDate.equals("") && toDate != null && !toDate.equals("")) {
			try {
				Date fromDate1 = formatter.parse(fromDate);
				fromDate = apiDateFormat.format(fromDate1);
				Date toDate1 = formatter.parse(toDate);
				toDate = apiDateFormat.format(toDate1);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}else {
			Calendar cal = Calendar.getInstance();
			toDate = apiDateFormat.format(cal.getTime());
			cal.add(Calendar.DATE, -1);
			fromDate = apiDateFormat.format(cal.getTime());
		}
		String username = "admin";
		String password = "123";
		IService1Proxy service1Proxy = new IService1Proxy();
		org.json.JSONObject jsonObject;
		JSONArray jsonArray = null;
		try {
			String responseService = service1Proxy.getBeneficiary(fromDate, toDate, username, password);
			//System.out.println("responseService***************:"+responseService);
			jsonObject = new org.json.JSONObject(responseService);
			String jsonResult = (String) jsonObject.get("Result");
			jsonArray = (JSONArray) new JSONParser().parse(jsonResult);
			System.err.println("jsonResult----------:"+jsonResult);
		} catch (org.json.simple.parser.ParseException | RemoteException | JSONException e) {
			e.printStackTrace();
		}
		
		try {
			GetBeneficiaryApiLog getBeneficiaryApiLog = new GetBeneficiaryApiLog();
			for (int i = 0; i < jsonArray.size(); i++) {
				org.json.simple.JSONObject jsonObject2 = (org.json.simple.JSONObject) jsonArray.get(i);

				Long mbEhrNo = null;
				if (jsonObject2.get("MB_EHR_NO") != null) {
					mbEhrNo = (Long) jsonObject2.get("MB_EHR_NO");
				}
				String mbYear = (String) jsonObject2.get("MB_YEAR");
				Long mbAppNo = null;
				if (jsonObject2.get("MB_APP_NO") != null) {
					mbAppNo = (Long) jsonObject2.get("MB_APP_NO");
				}
				Long mbBenificiary = (Long) jsonObject2.get("MB_BENIFICIARY");
				String mbName = null;
				if (jsonObject2.get("MB_NAME") != null) {
					mbName = (String) jsonObject2.get("MB_NAME");
				}
				String mbFirstName = null;
				if (jsonObject2.get("BENE_FIRST_NAME") != null) {
					mbFirstName = (String) jsonObject2.get("BENE_FIRST_NAME");
					mbFirstName = mbFirstName.trim();
				}
				String mbMiddleName = null;
				if (jsonObject2.get("BENE_MIDDLE_NAME") != null) {
					mbMiddleName = (String) jsonObject2.get("BENE_MIDDLE_NAME");
					mbMiddleName = mbMiddleName.trim();
				}
				String mbLastName = null;
				if (jsonObject2.get("BENE_LAST_NAME") != null) {
					mbLastName = (String) jsonObject2.get("BENE_LAST_NAME");
					mbLastName = mbLastName.trim();
				}
				String addressLine1 = null;
				if (jsonObject2.get("MB_ADDRESS1") != null) {
					addressLine1 = (String) jsonObject2.get("MB_ADDRESS1");
				}
				String addressLine2 = null;
				if (jsonObject2.get("MB_ADDRESS2") != null) {
					addressLine2 = (String) jsonObject2.get("MB_ADDRESS2");
				}
				String addressLine3 = null;
				if (jsonObject2.get("MB_ADDRESS3") != null) {
					addressLine3 = (String) jsonObject2.get("MB_ADDRESS3");
				}
				String mobNo = null;
				if (jsonObject2.get("MB_TEL_NO") != null) {
					mobNo = (String) jsonObject2.get("MB_TEL_NO");
				}
				Long mbAge = null;
				if (jsonObject2.get("MB_AGE") != null) {
					mbAge = (Long) jsonObject2.get("MB_AGE");
				}
				String mbSex = (String) jsonObject2.get("MB_SEX");
				String mbRelation = null;
				if (jsonObject2.get("MB_RELATION") != null) {
					mbRelation = (String) jsonObject2.get("MB_RELATION");
				}
				String mbDOB = null;
				if (jsonObject2.get("MB_DOB") != null) {
					mbDOB = (String) jsonObject2.get("MB_DOB");
				}
				String mbEmail = null;
				if (jsonObject2.get("MB_EMAIL") != null) {
					mbEmail = (String) jsonObject2.get("MB_EMAIL");
				}
				String mbReamrk = null;
				if (jsonObject2.get("MB_REMARK") != null) {
					mbReamrk = (String) jsonObject2.get("MB_REMARK");
				}
				String apYear = null;
				if (jsonObject2.get("AP_YEAR") != null) {
					apYear = (String) jsonObject2.get("AP_YEAR");
				}
				Long apAppNo = null;
				if (jsonObject2.get("AP_APP_NO") != null) {
					apAppNo = (Long) jsonObject2.get("AP_APP_NO");
				}
				String apStatus = (String) jsonObject2.get("AP_APP_STATUS");
				String apSaleType = (String) jsonObject2.get("AP_SALE_TYPE");
				String apMemberId = (String) jsonObject2.get("AP_MEMBER_ID");
				Long apCentreId = (Long) jsonObject2.get("AP_CENTER");
				Long apPackageId = (Long) jsonObject2.get("AP_PRODUCT");
				String apDate = (String) jsonObject2.get("AP_APP_DATE");
				String apSpecialQuota = (String) jsonObject2.get("AP_SPECIAL_QUOTA_YN");
				String apCheckUpdate = null;
				if (jsonObject2.get("AP_CHECKUP_DATE") != null) {
					apCheckUpdate = (String) jsonObject2.get("AP_CHECKUP_DATE");
				}
				String apCentreRegNo = null;
				if (jsonObject2.get("AP_CENTER_REGN_NO") != null) {
					apCentreRegNo = (String) jsonObject2.get("AP_CENTER_REGN_NO");
				}
				Long apBeneficiaryId = (Long) jsonObject2.get("AP_BENIFICIARY_ID");
				Long apCouponId = null;
				if (jsonObject2.get("AP_COUPON_ID") != null) {
					apCouponId = (Long) jsonObject2.get("AP_COUPON_ID");
				}
				String apCtTest = null;
				if (jsonObject2.get("AP_CT_TEST") != null) {
					apCtTest = (String) jsonObject2.get("AP_CT_TEST");
				}
				String apNonCtTest = null;
				if (jsonObject2.get("AP_NONCT_TEST") != null) {
					apNonCtTest = (String) jsonObject2.get("AP_NONCT_TEST");
				}
				String apPathDate = null;
				if (jsonObject2.get("AP_PATH_DATE") != null) {
					apPathDate = (String) jsonObject2.get("AP_PATH_DATE");
				}
				Long apPathCentre = null;
				if (jsonObject2.get("AP_PATH_CENTER") != null) {
					apPathCentre = (Long) jsonObject2.get("AP_PATH_CENTER");
				}
				String apPathTest = null;
				if (jsonObject2.get("AP_PATH_TEST") != null) {
					apPathTest = (String) jsonObject2.get("AP_PATH_TEST");
				}
				String apCtCheckUpDate = null;
				if (jsonObject2.get("AP_CT_CHKUP_DATE") != null) {
					apCtCheckUpDate = (String) jsonObject2.get("AP_CT_CHKUP_DATE");
				}
				String apNonCtCheckUpDate = null;
				if (jsonObject2.get("AP_NON_CT_CHKUP_DATE") != null) {
					apNonCtCheckUpDate = (String) jsonObject2.get("AP_NON_CT_CHKUP_DATE");
				}
				Long apCtBeneficiaryId = null;
				if (jsonObject2.get("AP_CT_BENIFICIARY_ID") != null) {
					apCtBeneficiaryId = (Long) jsonObject2.get("AP_CT_BENIFICIARY_ID");
				}
				Long apCtCentreId = (Long) jsonObject2.get("AP_CT_CENTER");
				Long apLinkAppNo = null;
				if (jsonObject2.get("AP_LINK_APP_NO") != null) {
					apLinkAppNo = (Long) jsonObject2.get("AP_LINK_APP_NO");
				}
				String apCtAppGiven = null;
				if (jsonObject2.get("AP_CT_APP_GIVEN") != null) {
					apCtAppGiven = (String) jsonObject2.get("AP_CT_APP_GIVEN");
				}
				Long checkUpId = null;
				if (jsonObject2.get("MB_VISIT_ID") != null) {
					checkUpId = (Long) jsonObject2.get("MB_VISIT_ID");
				}

				
				System.err.println("long value of client Id=="  +mbEhrNo);
				getBeneficiaryApiLog.setMB_EHR_NO(mbEhrNo);
				getBeneficiaryApiLog.setMB_YEAR(mbYear);
				getBeneficiaryApiLog.setMbAppNo(mbAppNo);
				getBeneficiaryApiLog.setMbBenificiary(mbBenificiary);
				getBeneficiaryApiLog.setMB_NAME(mbName);			
				getBeneficiaryApiLog.setBENE_FIRST_NAME(mbFirstName);
				getBeneficiaryApiLog.setBENE_MIDDLE_NAME(mbMiddleName);
				getBeneficiaryApiLog.setBENE_LAST_NAME(mbLastName);			
				getBeneficiaryApiLog.setMB_ADDRESS1(addressLine1);
				getBeneficiaryApiLog.setMB_ADDRESS2(addressLine2);
				getBeneficiaryApiLog.setMB_ADDRESS3(addressLine3);			
				getBeneficiaryApiLog.setMB_TEL_NO(mobNo);
				getBeneficiaryApiLog.setMB_AGE(mbAge);
				getBeneficiaryApiLog.setMB_SEX(mbSex);			
				getBeneficiaryApiLog.setMB_DOB(mbDOB);
				getBeneficiaryApiLog.setMB_EMAIL(mbEmail);
				getBeneficiaryApiLog.setMB_REMARK(mbReamrk);			
				getBeneficiaryApiLog.setMB_DOB(mbDOB);
				getBeneficiaryApiLog.setMB_EMAIL(mbEmail);
				getBeneficiaryApiLog.setMB_REMARK(mbReamrk);			
				getBeneficiaryApiLog.setAP_YEAR(apYear);
				getBeneficiaryApiLog.setAP_APP_NO(apAppNo);
				getBeneficiaryApiLog.setAP_APP_STATUS(apStatus);			
				getBeneficiaryApiLog.setAP_SALE_TYPE(apSaleType);
				getBeneficiaryApiLog.setAP_MEMBER_ID(apMemberId);
				getBeneficiaryApiLog.setAP_CENTER(apCentreId);			
				getBeneficiaryApiLog.setAP_PRODUCT(apPackageId);
				getBeneficiaryApiLog.setAP_APP_DATE(apDate);
				getBeneficiaryApiLog.setAP_SPECIAL_QUOTA_YN(apSpecialQuota);			
				getBeneficiaryApiLog.setAP_CHECKUP_DATE(convertDate(apCheckUpdate));
				getBeneficiaryApiLog.setAP_CENTER_REGN_NO(apCentreRegNo);
				getBeneficiaryApiLog.setAP_BENIFICIARY_ID(apBeneficiaryId);			
				getBeneficiaryApiLog.setAP_COUPON_ID(apCouponId);
				getBeneficiaryApiLog.setAP_CT_TEST(apCtTest);
				getBeneficiaryApiLog.setAP_NONCT_TEST(apNonCtTest);			
				getBeneficiaryApiLog.setAP_PATH_DATE(apPathDate);
				getBeneficiaryApiLog.setAP_PATH_CENTER(apPathCentre);
				getBeneficiaryApiLog.setAP_PATH_TEST(apPathTest);			
				getBeneficiaryApiLog.setAP_CT_CHKUP_DATE(apCtCheckUpDate);
				getBeneficiaryApiLog.setAP_NON_CT_CHKUP_DATE(apNonCtCheckUpDate);
				getBeneficiaryApiLog.setAP_CT_BENIFICIARY_ID(apCtBeneficiaryId);			
				getBeneficiaryApiLog.setAP_CT_CENTER(apCtCentreId);
				getBeneficiaryApiLog.setAP_LINK_APP_NO(apLinkAppNo);
				getBeneficiaryApiLog.setAP_CT_APP_GIVEN(apCtAppGiven);			
				getBeneficiaryApiLog.setMB_VISIT_ID(checkUpId);
				getBeneficiaryApiLog.setMB_RELATION(mbRelation);
				getBeneficiaryApiLog.setAddedOn(new Date(new java.util.Date().getTime()));
				
				int a=clientService.saveBeneficiaryApiLog(getBeneficiaryApiLog);
				
				
				
			}
			}catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
		int errorCode=0;
		int statusCode=0;
		String msg="";
		ClientMaster clientMaster = null;
		PackageMaster packageMaster = null;
		CentreMaster centreMaster = null;
		CheckUpMaster checkUpMaster = null;
		AppointmentMaster appointmentMaster = null;
		for (int i = 0; i < jsonArray.size(); i++) {
			org.json.simple.JSONObject jsonObject2 = (org.json.simple.JSONObject) jsonArray.get(i);

			Long mbEhrNo = null;
			if (jsonObject2.get("MB_EHR_NO") != null) {
				mbEhrNo = (Long) jsonObject2.get("MB_EHR_NO");
			}
			String mbYear = (String) jsonObject2.get("MB_YEAR");
			Long mbAppNo = null;
			if (jsonObject2.get("MB_APP_NO") != null) {
				mbAppNo = (Long) jsonObject2.get("MB_APP_NO");
			}
			Long mbBenificiary = (Long) jsonObject2.get("MB_BENIFICIARY");
			String mbName = null;
			if (jsonObject2.get("MB_NAME") != null) {
				mbName = (String) jsonObject2.get("MB_NAME");
			}
			String mbFirstName = null;
			if (jsonObject2.get("BENE_FIRST_NAME") != null) {
				mbFirstName = (String) jsonObject2.get("BENE_FIRST_NAME");
				mbFirstName = mbFirstName.trim();
			}
			String mbMiddleName = null;
			if (jsonObject2.get("BENE_MIDDLE_NAME") != null) {
				mbMiddleName = (String) jsonObject2.get("BENE_MIDDLE_NAME");
				mbMiddleName = mbMiddleName.trim();
			}
			String mbLastName = null;
			if (jsonObject2.get("BENE_LAST_NAME") != null) {
				mbLastName = (String) jsonObject2.get("BENE_LAST_NAME");
				mbLastName = mbLastName.trim();
			}
			String addressLine1 = null;
			if (jsonObject2.get("MB_ADDRESS1") != null) {
				addressLine1 = (String) jsonObject2.get("MB_ADDRESS1");
			}
			String addressLine2 = null;
			if (jsonObject2.get("MB_ADDRESS2") != null) {
				addressLine2 = (String) jsonObject2.get("MB_ADDRESS2");
			}
			String addressLine3 = null;
			if (jsonObject2.get("MB_ADDRESS3") != null) {
				addressLine3 = (String) jsonObject2.get("MB_ADDRESS3");
			}
			String mobNo = null;
			if (jsonObject2.get("MB_TEL_NO") != null) {
				mobNo = (String) jsonObject2.get("MB_TEL_NO");
			}
			Long mbAge = null;
			if (jsonObject2.get("MB_AGE") != null) {
				mbAge = (Long) jsonObject2.get("MB_AGE");
			}
			String mbSex = (String) jsonObject2.get("MB_SEX");
			String mbRelation = null;
			if (jsonObject2.get("MB_RELATION") != null) {
				mbRelation = (String) jsonObject2.get("MB_RELATION");
			}
			String mbDOB = null;
			if (jsonObject2.get("MB_DOB") != null) {
				mbDOB = (String) jsonObject2.get("MB_DOB");
			}
			String mbEmail = null;
			if (jsonObject2.get("MB_EMAIL") != null) {
				mbEmail = (String) jsonObject2.get("MB_EMAIL");
			}
			String mbReamrk = null;
			if (jsonObject2.get("MB_REMARK") != null) {
				mbReamrk = (String) jsonObject2.get("MB_REMARK");
			}
			String apYear = null;
			if (jsonObject2.get("AP_YEAR") != null) {
				apYear = (String) jsonObject2.get("AP_YEAR");
			}
			Long apAppNo = null;
			if (jsonObject2.get("AP_APP_NO") != null) {
				apAppNo = (Long) jsonObject2.get("AP_APP_NO");
			}
			String apStatus = (String) jsonObject2.get("AP_APP_STATUS");
			String apSaleType = (String) jsonObject2.get("AP_SALE_TYPE");
			String apMemberId = (String) jsonObject2.get("AP_MEMBER_ID");
			Long apCentreId = (Long) jsonObject2.get("AP_CENTER");
			Long apPackageId = (Long) jsonObject2.get("AP_PRODUCT");
			String apDate = (String) jsonObject2.get("AP_APP_DATE");
			String apSpecialQuota = (String) jsonObject2.get("AP_SPECIAL_QUOTA_YN");
			String apCheckUpdate = null;
			if (jsonObject2.get("AP_CHECKUP_DATE") != null) {
				apCheckUpdate = (String) jsonObject2.get("AP_CHECKUP_DATE");
			}
			String apCentreRegNo = null;
			if (jsonObject2.get("AP_CENTER_REGN_NO") != null) {
				apCentreRegNo = (String) jsonObject2.get("AP_CENTER_REGN_NO");
			}
			Long apBeneficiaryId = (Long) jsonObject2.get("AP_BENIFICIARY_ID");
			Long apCouponId = null;
			if (jsonObject2.get("AP_COUPON_ID") != null) {
				apCouponId = (Long) jsonObject2.get("AP_COUPON_ID");
			}
			String apCtTest = null;
			if (jsonObject2.get("AP_CT_TEST") != null) {
				apCtTest = (String) jsonObject2.get("AP_CT_TEST");
			}
			String apNonCtTest = null;
			if (jsonObject2.get("AP_NONCT_TEST") != null) {
				apNonCtTest = (String) jsonObject2.get("AP_NONCT_TEST");
			}
			String apPathDate = null;
			if (jsonObject2.get("AP_PATH_DATE") != null) {
				apPathDate = (String) jsonObject2.get("AP_PATH_DATE");
			}
			Long apPathCentre = null;
			if (jsonObject2.get("AP_PATH_CENTER") != null) {
				apPathCentre = (Long) jsonObject2.get("AP_PATH_CENTER");
			}
			String apPathTest = null;
			if (jsonObject2.get("AP_PATH_TEST") != null) {
				apPathTest = (String) jsonObject2.get("AP_PATH_TEST");
			}
			String apCtCheckUpDate = null;
			if (jsonObject2.get("AP_CT_CHKUP_DATE") != null) {
				apCtCheckUpDate = (String) jsonObject2.get("AP_CT_CHKUP_DATE");
			}
			String apNonCtCheckUpDate = null;
			if (jsonObject2.get("AP_NON_CT_CHKUP_DATE") != null) {
				apNonCtCheckUpDate = (String) jsonObject2.get("AP_NON_CT_CHKUP_DATE");
			}
			Long apCtBeneficiaryId = null;
			if (jsonObject2.get("AP_CT_BENIFICIARY_ID") != null) {
				apCtBeneficiaryId = (Long) jsonObject2.get("AP_CT_BENIFICIARY_ID");
			}
			Long apCtCentreId = (Long) jsonObject2.get("AP_CT_CENTER");
			Long apLinkAppNo = null;
			if (jsonObject2.get("AP_LINK_APP_NO") != null) {
				apLinkAppNo = (Long) jsonObject2.get("AP_LINK_APP_NO");
			}
			String apCtAppGiven = null;
			if (jsonObject2.get("AP_CT_APP_GIVEN") != null) {
				apCtAppGiven = (String) jsonObject2.get("AP_CT_APP_GIVEN");
			}
			Long checkUpId = null;
			if (jsonObject2.get("MB_VISIT_ID") != null) {
				checkUpId = (Long) jsonObject2.get("MB_VISIT_ID");
			}

			/*GetBeneficiaryApiLog getBeneficiaryApiLog = new GetBeneficiaryApiLog();
			
			getBeneficiaryApiLog.setMB_EHR_NO(mbEhrNo);
			getBeneficiaryApiLog.setMB_YEAR(mbYear);
			getBeneficiaryApiLog.setMbAppNo(mbAppNo);
			getBeneficiaryApiLog.setMbBenificiary(mbBenificiary);
			getBeneficiaryApiLog.setMB_NAME(mbName);			
			getBeneficiaryApiLog.setBENE_FIRST_NAME(mbFirstName);
			getBeneficiaryApiLog.setBENE_MIDDLE_NAME(mbMiddleName);
			getBeneficiaryApiLog.setBENE_LAST_NAME(mbLastName);			
			getBeneficiaryApiLog.setMB_ADDRESS1(addressLine1);
			getBeneficiaryApiLog.setMB_ADDRESS2(addressLine2);
			getBeneficiaryApiLog.setMB_ADDRESS3(addressLine3);			
			getBeneficiaryApiLog.setMB_TEL_NO(mobNo);
			getBeneficiaryApiLog.setMB_AGE(mbAge);
			getBeneficiaryApiLog.setMB_SEX(mbSex);			
			getBeneficiaryApiLog.setMB_DOB(mbDOB);
			getBeneficiaryApiLog.setMB_EMAIL(mbEmail);
			getBeneficiaryApiLog.setMB_REMARK(mbReamrk);			
			getBeneficiaryApiLog.setMB_DOB(mbDOB);
			getBeneficiaryApiLog.setMB_EMAIL(mbEmail);
			getBeneficiaryApiLog.setMB_REMARK(mbReamrk);			
			getBeneficiaryApiLog.setAP_YEAR(apYear);
			getBeneficiaryApiLog.setAP_APP_NO(apAppNo);
			getBeneficiaryApiLog.setAP_APP_STATUS(apStatus);			
			getBeneficiaryApiLog.setAP_SALE_TYPE(apSaleType);
			getBeneficiaryApiLog.setAP_MEMBER_ID(apMemberId);
			getBeneficiaryApiLog.setAP_CENTER(apCentreId);			
			getBeneficiaryApiLog.setAP_PRODUCT(apPackageId);
			getBeneficiaryApiLog.setAP_APP_DATE(apDate);
			getBeneficiaryApiLog.setAP_SPECIAL_QUOTA_YN(apSpecialQuota);			
			getBeneficiaryApiLog.setAP_CHECKUP_DATE(apCheckUpdate);
			getBeneficiaryApiLog.setAP_CENTER_REGN_NO(apCentreRegNo);
			getBeneficiaryApiLog.setAP_BENIFICIARY_ID(apBeneficiaryId);			
			getBeneficiaryApiLog.setAP_COUPON_ID(apCouponId);
			getBeneficiaryApiLog.setAP_CT_TEST(apCtTest);
			getBeneficiaryApiLog.setAP_NONCT_TEST(apNonCtTest);			
			getBeneficiaryApiLog.setAP_PATH_DATE(apPathDate);
			getBeneficiaryApiLog.setAP_PATH_CENTER(apPathCentre);
			getBeneficiaryApiLog.setAP_PATH_TEST(apPathTest);			
			getBeneficiaryApiLog.setAP_CT_CHKUP_DATE(apCtCheckUpDate);
			getBeneficiaryApiLog.setAP_NON_CT_CHKUP_DATE(apNonCtCheckUpDate);
			getBeneficiaryApiLog.setAP_CT_BENIFICIARY_ID(apCtBeneficiaryId);			
			getBeneficiaryApiLog.setAP_CT_CENTER(apCtCentreId);
			getBeneficiaryApiLog.setAP_LINK_APP_NO(apLinkAppNo);
			getBeneficiaryApiLog.setAP_CT_APP_GIVEN(apCtAppGiven);			
			getBeneficiaryApiLog.setMB_VISIT_ID(checkUpId);
			getBeneficiaryApiLog.setAddedOn(new Date(new java.util.Date().getTime()));
			
			int a=clientService.saveBeneficiaryApiLog(getBeneficiaryApiLog);*/
			
			
			
			clientMaster = new ClientMaster();
			clientMaster.setFirstName(mbFirstName);
			clientMaster.setMiddleName(mbMiddleName);
			clientMaster.setLastName(mbLastName);
			clientMaster.setClientFullName(mbName);
			clientMaster.setMemberId(apMemberId);
			clientMaster.setAddressLine1(addressLine1);
			clientMaster.setAddressLine2(addressLine2);
			clientMaster.setAddressLine3(addressLine3);
			clientMaster.setMobNo(mobNo);
			if (mbAge != null) {
				clientMaster.setClientAge((mbAge.intValue()));
			}
			clientMaster.setGender(mbSex);
			clientMaster.setEmailId(mbEmail);
			if (mbDOB != null) {
				clientMaster.setClientDOB(convertDate(mbDOB));
			}
			clientMaster.setRemark(mbReamrk);
			clientMaster.setUserId(mbEmail);

			packageMaster = new PackageMaster();
			packageMaster.setPackageId(apPackageId.intValue());
			clientMaster.setPackageMaster(packageMaster);

			centreMaster = new CentreMaster();
			centreMaster.setCentreId(apCentreId.intValue());
			clientMaster.setCentreMaster(centreMaster);

			java.util.Date date = new java.util.Date();
			java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String currentTime = simpleDateFormat.format(date);
			clientMaster.setAddAt(currentTime);
			clientMaster.setAddBy("API");
			clientMaster.setIsActive("Y");
			clientMaster.setCount(0);
			clientMaster.setMbRelation(mbRelation);
			int dup=0;
			System.err.println("mbEhrNo for all operation---"+mbEhrNo);
			Integer clientId = null;
			if (mbEhrNo != null && mbEhrNo != 0) {
				clientMaster.setClientId(mbEhrNo.intValue());
				clientId = mbEhrNo.intValue();
				System.err.println("If Client Not Save----clientId---"+clientId);
			} else {
				//For duplicate checking
				clientId = clientService.duplicateCheck(clientMaster);
				System.err.println("else Client Save----clientId---"+clientId);
				//if(clientId != null) {
					if(clientId == null) {
						String clientIdd = clientService.saveClientMaster(clientMaster);
						System.err.println("duplicate new----clientId---"+clientIdd);
						String a=clientIdd.substring(0, 4);
						
						
						if(a.equalsIgnoreCase("dup_"))
						{
							clientId= Integer.parseInt(clientIdd.substring(4));
							dup=1;
							
						}else {
							clientId= Integer.parseInt(clientIdd.substring(4));
							dup=2;
						}
						
				}
				clientMaster.setClientId(clientId);
			}

			checkUpMaster = new CheckUpMaster();
			checkUpMaster.setCheckUpDate(convertDate(apDate));
			if (apCentreRegNo != null && apCentreRegNo != "") {
				checkUpMaster.setHospitalRegistrationNo(apCentreRegNo);
			}
			checkUpMaster.setCentreMaster(centreMaster);
			checkUpMaster.setPackageMaster(packageMaster);
			checkUpMaster.setClientMaster(clientMaster);

			appointmentMaster = new AppointmentMaster();
			appointmentMaster.setClientMaster(clientMaster);
			if (apDate != null) {
				appointmentMaster.setApAppDate(convertDate(apDate));
			}
			if(apAppNo != null) {
				appointmentMaster.setApAppNo(apAppNo.toString());
				checkUpMaster.setApAppNo(apAppNo.toString());
			}
			appointmentMaster.setApAppStatus(apStatus);
			if(apBeneficiaryId != null) {
				appointmentMaster.setApBeneficiaryId(apBeneficiaryId.toString());
			}
			if(apCentreId != null) {
				appointmentMaster.setApCenter(apCentreId.toString());
			}
			appointmentMaster.setApCenterRegNo(apCentreRegNo);
			if (apCheckUpdate != null) {
				appointmentMaster.setApCheckUpDate(convertDate(apCheckUpdate));
			}
			if (apCouponId != null) {
				appointmentMaster.setApCouponId(apCouponId.toString());
			}
			appointmentMaster.setApCtAppGiven(apCtAppGiven);
			if (apCtBeneficiaryId != null) {
				appointmentMaster.setApCtBeneficiaryId(apCtBeneficiaryId.toString());
			}
			appointmentMaster.setApCtCenter(apCtCentreId.toString());
			if (apCtCheckUpDate != null) {
				appointmentMaster.setApCtCheckUpDate(convertDate(apCtCheckUpDate));
			}
			appointmentMaster.setApCtTest(apCtTest);
			if (apLinkAppNo != null) {
				appointmentMaster.setApLinkAppNo(apLinkAppNo.toString());
			}
			if (apMemberId != null) {
				appointmentMaster.setApMemberId(apMemberId.toString());
			}
			if (apNonCtCheckUpDate != null) {
				appointmentMaster.setApNonCtCheckUpDate(convertDate(apNonCtCheckUpDate));
			}
			appointmentMaster.setApNonCttest(apNonCtTest);
			if(apPathCentre != null) {
				appointmentMaster.setApPathCenter(apPathCentre.toString());
			}
			if (apPathDate != null) {
				appointmentMaster.setApPathDate(convertDate(apPathDate));
			}
			appointmentMaster.setApPathTest(apPathTest);
			if(apPackageId != null) {
				appointmentMaster.setApProduct(apPackageId.toString());
			}
			appointmentMaster.setApSaleType(apSaleType);
			appointmentMaster.setApSpecialQuotaStatus(apSpecialQuota);
			appointmentMaster.setApYear(apYear);
			checkUpMaster.setApYear(apYear);
			appointmentMaster.setMbYear(mbYear);
			appointmentMaster.setMbRelation(mbRelation);
			appointmentMaster.setMbBenificiary(mbBenificiary);
			checkUpMaster.setAddedOn(new Date(new java.util.Date().getTime()));
			appointmentMaster.setAddedOn(new Date(new java.util.Date().getTime()));
			if (apMemberId != null) {
				checkUpMaster.setApMemberId(apMemberId.toString());
			}
			Integer visitId = null;			
			if (checkUpId != null) {
				checkUpMaster.setCheckUpId(checkUpId.intValue());
				visitId = checkUpId.intValue();
				statusCode=0;
				errorCode=0;
				msg="Saved Successfully";
				
			} else {
				if(clientId==null) {
					statusCode=0;
					errorCode=204;
					msg="No Content";
					System.err.println("Iffff----No Content  clientId---"+clientId );
				//}else if(clientId==0 || clientId == -0) {
				}/*else if(dup == 1) {
					statusCode=0;
					errorCode=409; 
					msg="Client Already Register";
					System.err.println("Client Already Register----for null  clientId---"+clientId );
					
					if(apMemberId==null || apAppNo==null || apYear==null || apMemberId.equalsIgnoreCase("") || apYear.equalsIgnoreCase("")) {
						
					}else {
						visitId = checkUpService.saveCheckUpMaster(checkUpMaster, session);
						System.err.println("in Duplicate----- "+visitId);
						appointmentMaster.setCheckupId(visitId);
						System.err.println("Duplicate checkupId----- "+appointmentMaster.getCheckupId());
						appointmentService.saveAppointmentMaster(appointmentMaster);
						//statusCode=0;
						//errorCode=0;
						//msg="Saved Successfully";
						System.err.println("else for Duplicate");
				}
				
				}*/else {
					if(apMemberId==null || apAppNo==null || apYear==null || apMemberId.equalsIgnoreCase("") || apYear.equalsIgnoreCase("")) {
						statusCode=0;
						errorCode=204;
						msg="No Content for Visit Creation";
						System.err.println("Iffff----No Content for Visit Creation---"+apMemberId +" - "+ apAppNo+" - " + apYear );
					}else {
						visitId = checkUpService.saveCheckUpMaster(checkUpMaster, session);
						System.err.println("Get Visit Id From saveCheckUpMaster----- "+visitId);
						appointmentMaster.setCheckupId(visitId);
						System.err.println("Set & get checkupId appointmentMaster----- "+appointmentMaster.getCheckupId());
						appointmentService.saveAppointmentMaster(appointmentMaster);
						statusCode=0;
						errorCode=0;
						msg="Saved Successfully";
						System.err.println("Succeess for saveCheckUpMaster & appointmentMaster ");
				}
				
				}
				
			}

			JSONObject jsonObjectOfClientId = new JSONObject();
			jsonObjectOfClientId.put("MB_EHR_NO", clientId);
			jsonObjectOfClientId.put("MB_APP_NO", mbAppNo);
			jsonObjectOfClientId.put("MB_YEAR", apYear);
			jsonObjectOfClientId.put("MB_VISIT_ID", visitId);
			jsonObjectOfClientId.put("MB_RELATION", mbRelation);
			
			jsonObjectOfClientId.put("status_code", statusCode);
			jsonObjectOfClientId.put("error_code", errorCode);
			jsonObjectOfClientId.put("msg", msg);
			
			jsonArrayOfClientId.add(jsonObjectOfClientId);
			
			
		System.err.println("API Response ppNo="+mbAppNo);
		System.err.println("API Response apYear="+apYear);
		System.err.println("API Response visitId="+visitId);
		System.err.println("API Response mbRelation="+mbRelation);
		System.err.println("API Response statusCode="+statusCode);
		System.err.println("API Response errorCode="+errorCode);
		System.err.println("API Response msg="+msg);
			
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		try {
		System.err.println("jsonArrayOfClientId:" + jsonArrayOfClientId.size());
		if(jsonArrayOfClientId.size() > 0) {
			System.err.println("jsonArrayOfClientId      innnnnnn");
			//callWebServiceUpdateEHRID(jsonArrayOfClientId);//uncomment for live and comment for us
			clientService.updateEHRIDAPI(jsonArrayOfClientId);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
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

	@RequestMapping(value = "/callWebServiceGetCheckUpStatus", method = RequestMethod.POST)
	public @ResponseBody String callWebServiceGetCheckUpStatus() throws ParseException {
		Calendar cal = Calendar.getInstance();
		DateFormat apiDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String toDate = apiDateFormat.format(cal.getTime());
		cal.add(Calendar.DATE, -1);
		String fromDate = apiDateFormat.format(cal.getTime());
		String username = "admin";
		String password = "123";
		IService1Proxy service1Proxy = new IService1Proxy();
		org.json.JSONObject jsonObject;
		JSONArray jsonArray = null;
		try {
			String responseService = service1Proxy.getCheckUpStatus(fromDate, toDate, username, password);
			// System.out.println("responseService***************:"+responseService);
			jsonObject = new org.json.JSONObject(responseService);
			String jsonResult = (String) jsonObject.get("Result");
			jsonArray = (JSONArray) new JSONParser().parse(jsonResult);
			// System.out.print("jsonResult:"+jsonResult);
		} catch (org.json.simple.parser.ParseException | RemoteException | JSONException e) {
			e.printStackTrace();
		}

		if (jsonArray.size() > 0) {
			clientService.saveClientCheckUpDetails(jsonArray);
		}
		return "Records Saved Successfully";
	}

	// @RequestMapping(value = "/callWebServiceUpdateEHRID", method = RequestMethod.POST)
	public String callWebServiceUpdateEHRID(JSONArray jsonArrayOfClientId) {
		String username = "admin";
		String password = "123";
		JSONArray jsonArray = jsonArrayOfClientId;
		IService1Proxy service1Proxy = new IService1Proxy();
		try {
			String responseService = service1Proxy.updateEHRID(jsonArray.toString(), username, password);
			System.out.println("responseService:***********" + responseService);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return "Records Updated Successfully";
	}
	
	@RequestMapping(value = "/GetCounsellorAppointment", method = RequestMethod.GET)
	public String GetCounsellorAppointment(JSONArray jsonArrayOfClientId) {
		String urlname = "http://115.113.153.199/EHRAPI/EHR/GetCounsellorAppointment?CounsellorFromDate=01-Dec-2018&CounsellorToDate=31-Dec-2018&CounsellorId=1233";
        //http://172.16.1.11/EHRAPI/EHR/GetCounsellorAppointment?CounsellorFromDate='2018-Dec-01'&CounsellorToDate='2018-Dec-31'&CounsellorId=0
		try {
            URL url = new URL(urlname);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP error code : " + conn.getResponseCode()+" HTTP message "+conn.getResponseMessage());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output = br.readLine();
            System.err.println("output:"+output);
            conn.disconnect();
            /*ObjectMapper mapper = new ObjectMapper();
            try {
                GetalllabsEntity getalllabsEntity = mapper.readValue(output, GetalllabsEntity.class);
                System.out.println("API Status: " + getalllabsEntity.getStatus());
                System.out.println("API message: " + getalllabsEntity.getMessage());
                for (SubgetalllabsEntity out : getalllabsEntity.getOutput()) {
                	
                }
            } catch (Exception e) {
                System.out.println("Jackson Error: parsing Data: " + e.toString());
            }*/
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		return "Records Updated Successfully";
	}
	
	@RequestMapping(value = "/BeneficiaryApi", method = RequestMethod.POST)
	public Integer BeneficiaryApi(JSONArray clientByclientId) {
		String urlname = "http://115.113.153.199/EHRAPI/EHR/UpdateReportReceiveDate";
		int response=0;
        //http://172.16.1.11/EHRAPI/EHR/GetCounsellorAppointment?CounsellorFromDate='2018-Dec-01'&CounsellorToDate='2018-Dec-31'&CounsellorId=0
		try {
			System.err.println("-------"+clientByclientId.toString());
            URL url = new URL(urlname);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("charset", "utf-8");
            OutputStream os = conn.getOutputStream();
            os.write(clientByclientId.toJSONString().getBytes());
            os.flush();
            response=conn.getResponseCode();
            System.err.println("kjhj---"+conn.getResponseCode());
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP error code : " + conn.getResponseCode()+" HTTP message "+conn.getResponseMessage());
            }
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));
            
            String output = br.readLine();
            System.err.println("output:"+output);
            conn.disconnect();           
            
        } catch (Exception ex) {
            ex.printStackTrace();
            response=0;
        }
		return response;
	}

}
