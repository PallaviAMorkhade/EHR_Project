package com.hms.indus.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.CategoryMaster;
import com.hms.indus.bo.ParameterMaster;
import com.hms.indus.bo.ParameterReportDetail;
import com.hms.indus.bo.TestMaster;
import com.hms.indus.dao.ParameterDao;

@Repository
public class ParameterDaoImpl implements ParameterDao{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public String saveParameterMaster(ParameterMaster parameterMaster) {
		try {
			sessionFactory.getCurrentSession().save(parameterMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Parameter saved successfully";
	}

	@Override
	public String deleteParameterMaster(ParameterMaster parameterMaster) {
		try {
		/*ParameterMaster parameterMaster2 = (ParameterMaster) sessionFactory.getCurrentSession().get(ParameterMaster.class, parameterMaster.getParameterId());*/
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(ParameterMaster.class);
		ProjectionList proList = Projections.projectionList();
		proList.add(Projections.property("parameterId"));
		proList.add(Projections.property("parameterName"));
		proList.add(Projections.property("addAt"));
		proList.add(Projections.property("addBy"));
		proList.add(Projections.property("testMaster.testId"));
		crit.add(Restrictions.eq("parameterId", parameterMaster.getParameterId()));
		crit.setProjection(proList);
		ParameterMaster parameterMaster2=new ParameterMaster();
		List<Object[]> result=crit.list();		
		for(Object object[]:result)
		{
			if(object[0]!=null)
			{
				parameterMaster2.setParameterId(Integer.parseInt(object[0].toString()));
			}
			
			if(object[1]!=null)
			{
				parameterMaster2.setParameterName(object[1].toString());
			}
			
			if(object[2]!=null)
			{
				parameterMaster2.setAddAt(object[2].toString());
			}
			
			if(object[3]!=null)
			{
				parameterMaster2.setAddBy(object[3].toString());
			}
			
			if(object[4]!=null)
			{
				TestMaster testMaster=new TestMaster();
				testMaster.setTestId(Integer.parseInt(object[4].toString()));
				parameterMaster2.setTestMaster(testMaster);
			}
			
		}
		parameterMaster2.setIsActive('N');
		parameterMaster2.setModAt(parameterMaster.getModAt());
		parameterMaster2.setModBy(parameterMaster.getModBy());
		sessionFactory.getCurrentSession().update(parameterMaster2);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Parameter deleted";
	}

	@Override
	public String updateParameterMaster(ParameterMaster parameterMaster) {
		try {
		if(parameterMaster.getParameterId()!=null){
		/*ParameterMaster parameterMaster2 = (ParameterMaster) sessionFactory.getCurrentSession().get(ParameterMaster.class, );*/
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(ParameterMaster.class);
		ProjectionList proList = Projections.projectionList();
		proList.add(Projections.property("parameterId"));
		proList.add(Projections.property("isActive"));
		proList.add(Projections.property("addAt"));
		proList.add(Projections.property("addBy"));
		crit.add(Restrictions.eq("parameterId", parameterMaster.getParameterId()));
		crit.setProjection(proList);
		ParameterMaster parameterMaster2=new ParameterMaster();
		List<Object[]> result=crit.list();		
		for(Object object[]:result)
		{
			if(object[0]!=null)
			{
				parameterMaster2.setParameterId(Integer.parseInt(object[0].toString()));
			}
			
			if(object[1]!=null)
			{
				parameterMaster2.setIsActive(object[1].toString().charAt(0));
			}
			if(object[2]!=null)
			{
				parameterMaster2.setAddAt(object[2].toString());
			}
			
			if(object[3]!=null)
			{
				parameterMaster2.setAddBy(object[3].toString());
			}
		}
		parameterMaster2.setParameterId(parameterMaster.getParameterId());
		parameterMaster2.setParameterName(parameterMaster.getParameterName());
		parameterMaster2.setModAt(parameterMaster.getAddAt());
		parameterMaster2.setModBy(parameterMaster.getAddBy());
		parameterMaster2.setTestMaster(parameterMaster.getTestMaster());
		parameterMaster2.setNormalValue(parameterMaster.getNormalValue());
		parameterMaster2.setCriticalLowValue(parameterMaster.getCriticalLowValue());
		parameterMaster2.setCriticalHighValue(parameterMaster.getCriticalHighValue());
		sessionFactory.getCurrentSession().update(parameterMaster2);
		}
		else{
			sessionFactory.getCurrentSession().save(parameterMaster);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Parameter updated";
	}

	@Override
	public List<ParameterMaster> listOfParameterMaster() {
		/*Criteria criteria=sessionFactory.getCurrentSession().createCriteria(ParameterMaster.class);
		criteria.add(Restrictions.eq("isActive", 'Y'));*/
		SQLQuery query = null;
		List<ParameterMaster> parameterMasterList=new ArrayList<ParameterMaster>();
		try {
		query = sessionFactory.getCurrentSession()
				.createSQLQuery("select parameter_id,parameter_name,test_desc from ehr_parameter_master inner join ehr_test_master on ehr_parameter_master.test_id=ehr_test_master.test_id where ehr_parameter_master.is_active='Y';");
		List<Object[]> rows = query.list();
		for (Object[] row : rows) 
		{
			ParameterMaster parameterMaster=new ParameterMaster();
			if(row[0] != null){
				parameterMaster.setParameterId(Integer.parseInt(row[0].toString()));
			}
			if(row[1] != null){
				parameterMaster.setParameterName(row[1].toString());
			}
			if(row[2] != null){
				TestMaster testMaster=new TestMaster();
				testMaster.setTestDescription(row[2].toString());
				parameterMaster.setTestMaster(testMaster);
			}
			parameterMasterList.add(parameterMaster);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parameterMasterList;
	}

	@Override
	public List<ParameterMaster> getParameterByTestId(Integer testId) {
		/*Criteria criteria=sessionFactory.getCurrentSession().createCriteria(ParameterMaster.class);
		criteria.add(Restrictions.eq("testMaster.testId", testId));
		criteria.add(Restrictions.eq("isActive", 'Y'));*/
		SQLQuery query = null;
		List<ParameterMaster> parameterMasterList=new ArrayList<ParameterMaster>();
		try {
		query = sessionFactory.getCurrentSession()
				.createSQLQuery("select parameter_id,parameter_name,normal_value,critical_low_value,critical_high_value from ehr_parameter_master where test_id="+testId+" and is_active='Y';");
		List<Object[]> rows = query.list();
		for (Object[] row : rows) 
		{
			ParameterMaster parameterMaster=new ParameterMaster();
			if(row[0] != null){
				parameterMaster.setParameterId(Integer.parseInt(row[0].toString()));
			}
			if(row[1] != null){
				parameterMaster.setParameterName(row[1].toString());
			}
			if(row[2] != null){
				parameterMaster.setNormalValue(row[2].toString());
			}
			if(row[3] != null){
				parameterMaster.setCriticalLowValue(Double.parseDouble(row[3].toString()));
			}
			if(row[4] != null){
				parameterMaster.setCriticalHighValue(Double.parseDouble(row[4].toString()));
			}
			TestMaster testMaster=new TestMaster();
			testMaster.setTestId(testId);
			parameterMaster.setTestMaster(testMaster);
			parameterMasterList.add(parameterMaster);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parameterMasterList;
	}

	@Override
	public ParameterMaster getParameterByParameterId(Integer parameterId) {
		ParameterMaster parameterMaster = new ParameterMaster();
		try {
			SQLQuery query = null;
			query = sessionFactory.getCurrentSession()
					.createSQLQuery("select parameter_id,parameter_name,test_desc,ehr_parameter_master.test_id,ehr_parameter_master.add_at,ehr_parameter_master.add_by,ehr_parameter_master.normal_value from ehr_parameter_master inner join ehr_test_master on ehr_parameter_master.test_id=ehr_test_master.test_id where ehr_parameter_master.parameter_id='"+parameterId+"'");
			List<Object[]> rows = query.list();
			for (Object[] row : rows) 
			{
				if(row[0] != null){
					parameterMaster.setParameterId(Integer.parseInt(row[0].toString()));
				}
				if(row[1] != null){
					parameterMaster.setParameterName(row[1].toString());
				}
				if(row[3] != null){
					TestMaster testMaster=new TestMaster();
					testMaster.setTestId(Integer.parseInt(row[3].toString()));
					testMaster.setTestDescription(row[2].toString().trim());
					parameterMaster.setTestMaster(testMaster);
				}
				if(row[4]!=null)
				{
					parameterMaster.setAddAt(row[4].toString());
				}
				if(row[5]!=null)
				{
					parameterMaster.setAddBy(row[5].toString());
				}
				if(row[6]!=null)
				{
					parameterMaster.setNormalValue(row[6].toString());
					if(row[6].toString().equals("2")) {
						parameterMaster.setModBy("General");
					}else{
						parameterMaster.setModBy("Individual");
					}
				}
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return parameterMaster;
	}

	@Override
	public String saveTestResult(JSONArray jsonArray) {
		try {
		for(int i=0;i<jsonArray.size();i++){
			JSONObject object=(JSONObject) jsonArray.get(i);
			String modifyBy=(String) object.get("modifyBy");
			String modifyOn=(String) object.get("modifyOn");
			String addedOn=(String) object.get("addedOn");
			String addedBy=(String) object.get("addedBy");
			String clientId=(String) object.get("clientId");
			String parameterId=(String) object.get("parameterId");
			String parameterValue=(String) object.get("parameterValue");
			String testId=(String) object.get("testId");
			String testResultStatus=(String) object.get("testResultStatus");
			String packageId=(String) object.get("packageId");
			String reportId=(String) object.get("reportId");
			String naFlag=(String) object.get("naFlag");
			/*SQLQuery query = null;
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery("select client_id,test_id,parameter_id from ehr_test_result where client_id="+clientId+" and test_id="+testId+" and parameter_id="+parameterId+" and report_id="+reportId);
			List<Object> queryList=query.list();
			if(queryList.size()==0){
				String hql = "INSERT INTO ehr_test_result(added_on,added_by,client_id,test_id,package_id,parameter_id,parameter_value,modify_by,modify_on,test_result_status,report_id) " +
						"values('"+addedOn+"','"+addedBy+"','"+clientId+"',"+testId+",'"+packageId+"','"+parameterId+"','"+parameterValue+"','"+modifyBy+"','"+modifyOn+"','"+testResultStatus+"','"+reportId+"')";
				SQLQuery queryInsert = sessionFactory.getCurrentSession().createSQLQuery(hql);
				queryInsert.executeUpdate();
			}
			else{
				String hql = "update ehr_test_result set modify_by='"+modifyBy+"', added_on='"+addedOn+"',modify_on='"+modifyOn+"',added_by='"+addedBy+"',parameter_value='"+parameterValue+"',test_result_status='"+testResultStatus+"',package_id='"+packageId+"' where client_id='"+clientId+"' and test_id="+testId+" and parameter_id="+parameterId+" and report_id="+reportId;
				SQLQuery queryInsert = sessionFactory.getCurrentSession().createSQLQuery(hql);
				queryInsert.executeUpdate();
			}*/
			
			String hql = "INSERT INTO ehr_test_result(added_on,added_by,client_id,test_id,package_id,parameter_id,parameter_value,test_result_status,report_id,na_flag) " +
					"values('"+addedOn+"','"+addedBy+"','"+clientId+"',"+testId+",'"+packageId+"','"+parameterId+"','"+parameterValue+"','"+testResultStatus+"','"+reportId+"','"+naFlag+"')" +
					" ON DUPLICATE KEY UPDATE modify_by='"+modifyBy+"',modify_on='"+modifyOn+"',parameter_value='"+parameterValue+"',test_result_status='"+testResultStatus+"',na_flag = '"+naFlag+"' ";
			SQLQuery queryInsert = sessionFactory.getCurrentSession().createSQLQuery(hql);
			queryInsert.executeUpdate();
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "test result saved";
	}
	
	@Override
	public JSONArray getTestResultsByClientId(Integer clientId,Integer testId,Integer reportId,Integer centreId){
		SQLQuery query = null;
		JSONArray jsonArray = new JSONArray();
		// get parameter values By centerId and testId
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"SELECT epv.parameter_id,epv.normal_value,epv.parameter_name,epv.general_comment,epv.lower_value,epv.upper_value,epv.unit_id, "
									+ " eum.unit_name FROM ehr_parameter_values epv left join ehr_unit_master eum on eum.unit_id=epv.unit_id "
									+ " where epv.center_id="
									+ centreId
									+ " and epv.test_id="
									+ testId
									+ " and epv.is_active='Y' and epv.value_for in (5,(SELECT case client_gender "
									+ " when 'M' then 1 when 'F' then 2 end as gender FROM clients where client_id="
									+ clientId + " limit 1))");
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				JSONObject jsonObject = new JSONObject();
				if (row[0] != null) {
					jsonObject.put("parameterId", row[0].toString());
				}
				if (row[1] != null) {
					jsonObject.put("normalValue", row[1].toString());
				}
				if (row[2] != null) {
					jsonObject.put("parameterName", row[2].toString());
				}
				if (row[3] != null) {
					jsonObject.put("generalComment", row[3].toString());
				}
				if (row[4] != null) {
					jsonObject.put("lowerValue", row[4].toString());
				}
				if (row[5] != null) {
					jsonObject.put("upperValue", row[5].toString());
				}
				if (row[6] != null) {
					jsonObject.put("unitId", row[6].toString());
				}
				if (row[7] != null) {
					jsonObject.put("unitName", row[7].toString());
				}
				jsonArray.add(jsonObject);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		// get test results By clientId,testId and reportId
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"SELECT parameter_id,parameter_value,na_flag FROM ehr_test_result where client_id="
									+ clientId
									+ " and test_id="
									+ testId
									+ " and report_id=" + reportId);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("testId", testId);
				if (row[0] != null) {
					jsonObject.put("parameterId", row[0].toString());
				}
				if (row[1] != null) {
					jsonObject.put("parameterValue", row[1].toString());
				}if (row[2] != null) {
					jsonObject.put("naFlag", row[2].toString());
					if(row[2].toString().equals("true")) {
						jsonObject.put("parameterValue","");
					}
				}
				jsonArray.add(jsonObject);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}
	
	@Override
	public JSONArray getParameterValuesByCenterIdAndTestId(Integer centerId,
			Integer testId,Integer clientId) {
		SQLQuery query = null;
		JSONArray jsonArray = new JSONArray();
		try {
		query = sessionFactory
				.getCurrentSession()
				.createSQLQuery("select parameter_id,normal_value,parameter_name,general_comment,lower_value,upper_value,ehr_parameter_values.unit_id,unit_name,value_for,client_gender from ehr_parameter_values inner join clients on clients.client_id="+clientId+" left join ehr_unit_master on ehr_unit_master.unit_id=ehr_parameter_values.unit_id where ehr_parameter_values.center_id="+centerId+" and test_id="+testId+" and is_active='Y';");
		List<Object[]> rows = query.list();
		for (Object[] row : rows) 
		{
			JSONObject jsonObject=new JSONObject();
			if(row[0] != null){
				jsonObject.put("parameterId", row[0].toString());
			}
			if(row[1] != null){
				jsonObject.put("normalValue", row[1].toString());
			}
			if(row[2] != null){
				jsonObject.put("parameterName", row[2].toString());
			}
			if(row[3] != null){
				jsonObject.put("generalComment", row[3].toString());
			}
			
			if (row[9] != null) {
				if (row[9].toString().equals("M")) {
					if (row[8] != null) {
						if (row[8].toString().equals("1")) {
							jsonObject.put("valueFor", "1");
							if(row[4] != null){
								jsonObject.put("lowerValue", row[4].toString());
							}
							if(row[5] != null){
								jsonObject.put("upperValue", row[5].toString());
							}
							if(row[6] != null){
								jsonObject.put("unitId", row[6].toString());
							}
							if (row[7] != null) {
								jsonObject.put("unitName", row[7].toString());
							}
						}
					}
				}
				if (row[9].toString().equals("F")) {
					if (row[8] != null) {
						if (row[8].toString().equals("2")) {
							jsonObject.put("valueFor", "2");
							if(row[4] != null){
								jsonObject.put("lowerValue", row[4].toString());
							}
							if(row[5] != null){
								jsonObject.put("upperValue", row[5].toString());
							}
							if(row[6] != null){
								jsonObject.put("unitId", row[6].toString());
							}
							if (row[7] != null) {
								jsonObject.put("unitName", row[7].toString());
							}
						}
					}
				}
			}
			jsonArray.add(jsonObject);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public String saveParameterValues(JSONArray jsonArray) {
		try {
		for(int i=0;i<jsonArray.size();i++){
			JSONObject object=(JSONObject) jsonArray.get(i);
		String modifyBy=(String) object.get("modifyBy");
		String modifyOn=(String) object.get("modifyOn");
		String addedOn=(String) object.get("addedOn");
		String addedBy=(String) object.get("addedBy");
		String centreId=(String) object.get("centreId");
		String parameterId=(String) object.get("parameterId");
		String testId=(String) object.get("testId");
		String normalValue=(String) object.get("normalValue");
		String parameterName=(String) object.get("parameterName");
		String generalComment=(String) object.get("generalComment");		
		String lowerValue=(String) object.get("lowerValue");
		String upperValue=(String) object.get("upperValue");
		String unitId=(String) object.get("unitId");
		if(unitId == null || unitId.equals("null") || unitId==""){
			unitId="0";
		}
		String valueFor=(String) object.get("valueFor");
		
		SQLQuery query1 = null;
		query1 = sessionFactory
				.getCurrentSession()
				.createSQLQuery("select normal_value,parameter_value_id,center_id,test_id,parameter_id from ehr_parameter_values where center_id="+centreId+" and test_id="+testId+" and parameter_id="+parameterId+" and is_active='Y';");
		List<Object[]> rows = query1.list();
			if (rows.size() > 0) {
				for (Object[] row : rows) {
					if (row[0] != null) {
						String normal_value = row[0].toString();
						if (normal_value.equals(normalValue)) {
							SQLQuery query = null;
							query = sessionFactory
									.getCurrentSession()
									.createSQLQuery("select center_id,test_id,parameter_id,value_for from ehr_parameter_values where center_id="+centreId+" and test_id="+testId+" and parameter_id="+parameterId+" and value_for="+valueFor);
							List<Object> queryList=query.list();
							if(queryList.size()==0){
								String hql = "INSERT INTO ehr_parameter_values(added_on,added_by,center_id,test_id,parameter_id,normal_value,parameter_name,general_comment,lower_value,upper_value,unit_id,value_for,is_active) " +
										"values('"+addedOn+"','"+addedBy+"','"+centreId+"',"+testId+",'"+parameterId+"','"+normalValue+"','"+parameterName+"','"+generalComment+"','"+lowerValue+"','"+upperValue+"','"+unitId+"','"+valueFor+"','Y')";
								SQLQuery queryInsert = sessionFactory.getCurrentSession().createSQLQuery(hql);
								queryInsert.executeUpdate();
								}
							else{
								String hql = "update ehr_parameter_values set modify_by='"+modifyBy+"',modify_on='"+modifyOn+"',normal_value='"+normalValue+"',parameter_name='"+parameterName+"',general_comment='"+generalComment+"',lower_value='"+lowerValue+"',upper_value='"+upperValue+"',value_for='"+valueFor+"',unit_id='"+unitId+"',is_active='Y' where center_id="+centreId+" and test_id="+testId+" and parameter_id="+parameterId+" and value_for="+valueFor;
								SQLQuery queryInsert = sessionFactory.getCurrentSession().createSQLQuery(hql);
								queryInsert.executeUpdate();
							}
						}
						else{
							String hql = "update ehr_parameter_values set modify_by='"+modifyBy+"',modify_on='"+modifyOn+"',is_active='N' where parameter_value_id ="+row[1].toString();
							SQLQuery queryInsert = sessionFactory.getCurrentSession().createSQLQuery(hql);
							queryInsert.executeUpdate();
						}
					}
				}
				SQLQuery query = null;
				query = sessionFactory
						.getCurrentSession()
						.createSQLQuery("select center_id,test_id,parameter_id,value_for from ehr_parameter_values where center_id="+centreId+" and test_id="+testId+" and parameter_id="+parameterId+" and value_for="+valueFor);
				List<Object> queryList=query.list();
				if(queryList.size()==0){
					String hql = "INSERT INTO ehr_parameter_values(added_on,added_by,center_id,test_id,parameter_id,normal_value,modify_by,modify_on,parameter_name,general_comment,lower_value,upper_value,unit_id,value_for,is_active) " +
							"values('"+addedOn+"','"+addedBy+"','"+centreId+"',"+testId+",'"+parameterId+"','"+normalValue+"','"+modifyBy+"','"+modifyOn+"','"+parameterName+"','"+generalComment+"','"+lowerValue+"','"+upperValue+"','"+unitId+"','"+valueFor+"','Y')";
					SQLQuery queryInsert = sessionFactory.getCurrentSession().createSQLQuery(hql);
					queryInsert.executeUpdate();
					}
				else{
					String hql = "update ehr_parameter_values set modify_by='"+modifyBy+"',modify_on='"+modifyOn+"',normal_value='"+normalValue+"',parameter_name='"+parameterName+"',general_comment='"+generalComment+"',lower_value='"+lowerValue+"',upper_value='"+upperValue+"',value_for='"+valueFor+"',unit_id='"+unitId+"',is_active='Y' where center_id="+centreId+" and test_id="+testId+" and parameter_id="+parameterId+" and value_for="+valueFor;
					SQLQuery queryInsert = sessionFactory.getCurrentSession().createSQLQuery(hql);
					queryInsert.executeUpdate();
				}
			}
		
			else{
		SQLQuery query = null;
		query = sessionFactory
				.getCurrentSession()
				.createSQLQuery("select center_id,test_id,parameter_id,value_for from ehr_parameter_values where center_id="+centreId+" and test_id="+testId+" and parameter_id="+parameterId+" and value_for="+valueFor);
		List<Object> queryList=query.list();
		if(queryList.size()==0){
			String hql = "INSERT INTO ehr_parameter_values(added_on,added_by,center_id,test_id,parameter_id,normal_value,parameter_name,general_comment,lower_value,upper_value,unit_id,value_for,is_active) " +
					"values('"+addedOn+"','"+addedBy+"','"+centreId+"',"+testId+",'"+parameterId+"','"+normalValue+"','"+parameterName+"','"+generalComment+"','"+lowerValue+"','"+upperValue+"','"+unitId+"','"+valueFor+"','Y')";
			SQLQuery queryInsert = sessionFactory.getCurrentSession().createSQLQuery(hql);
			queryInsert.executeUpdate();
			}
		else{
			String hql = "update ehr_parameter_values set modify_by='"+modifyBy+"',modify_on='"+modifyOn+"',normal_value='"+normalValue+"',parameter_name='"+parameterName+"',general_comment='"+generalComment+"',lower_value='"+lowerValue+"',upper_value='"+upperValue+"',value_for='"+valueFor+"',unit_id='"+unitId+"',is_active='Y' where center_id="+centreId+" and test_id="+testId+" and parameter_id="+parameterId+" and value_for="+valueFor;
			SQLQuery queryInsert = sessionFactory.getCurrentSession().createSQLQuery(hql);
			queryInsert.executeUpdate();
		}
			}
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "parameter values saved";
	}

	@Override
	public JSONArray getParameterResultsByCentreId(Integer centreId,
			Integer testId, Integer parameterId) {
		SQLQuery query = null;
		JSONArray jsonArray = new JSONArray();
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery("select epm.parameter_id,epm.normal_value,"
							+ " epm.critical_low_value,epm.critical_high_value,"
							+ " epv.parameter_name,epv.general_comment,epv.lower_value,epv.upper_value,epv.unit_id,epv.value_for"
							+ " from ehr_parameter_master epm "
							+ " inner join ehr_parameter_values epv on epm.parameter_id = epv.parameter_id "
							+ " where epm.parameter_id="+parameterId+" and epv.center_id="+centreId+" and epv.is_active='Y'");
		if(query.list().size() == 0) {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery("select epm.parameter_id,epm.normal_value,"
							+ " epm.critical_low_value,epm.critical_high_value"
							+ " from ehr_parameter_master epm "
							+ " where epm.parameter_id="+parameterId+" and epm.is_active='Y'");
		}
		List<Object[]> rows = query.list();
		for (Object[] row : rows) 
		{
			JSONObject jsonObject=new JSONObject();
			if(row[0] != null){
				jsonObject.put("parameterId", row[0].toString());
			}
			if(row[1] != null){
				jsonObject.put("normalValue", row[1].toString());
			}
			if(row[2] != null){
				jsonObject.put("criticalLowValue",Double.parseDouble(row[2].toString()));
			}
			if(row[3] != null){
				jsonObject.put("criticalHighValue",Double.parseDouble(row[3].toString()));
			}
			if(row.length > 4) {
			if(row[4] != null){
				jsonObject.put("parameterName", row[4].toString());
			}
			if(row[5] != null){
				jsonObject.put("generalComment", row[5].toString());
			}
			if(row[6] != null){
				jsonObject.put("lowerValue", row[6].toString());
			}
			if(row[7] != null){
				jsonObject.put("upperValue", row[7].toString());
			}
			if(row[8] != null){
				jsonObject.put("unitId", row[8].toString());
			}
			if(row[9] != null){
				jsonObject.put("valueFor", row[9].toString());
			}
			}
			jsonArray.add(jsonObject);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	//For Parameter Report Details
	@Override
	public String saveParameterReportDetails(List<ParameterReportDetail> parameterReportDetails) {
		try {
			for(int i=0;i<parameterReportDetails.size();i++) {
				sessionFactory.getCurrentSession().saveOrUpdate(parameterReportDetails.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Parameter saved successfully";
	}
	
	@Override
	public List<ParameterReportDetail> getParameterReportByParameterId(Integer parameterId) {
		String hql = "from ParameterReportDetail WHERE isActive = :isActive and parameterReportId.parameterId = :parameterId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("isActive", true);
		query.setParameter("parameterId", parameterId);
		List<ParameterReportDetail> parameterReportDetails = (List<ParameterReportDetail>) query.list();
		List<ParameterReportDetail> parameterReportDetails1 = new ArrayList<ParameterReportDetail>();
		for (ParameterReportDetail parameterReportDetail : parameterReportDetails) {
			ParameterReportDetail parameterReportDetail1 = new ParameterReportDetail();
			parameterReportDetail1.setParameterReportId(parameterReportDetail.getParameterReportId());
			parameterReportDetail1.setHealthScore(parameterReportDetail.getHealthScore());
			parameterReportDetail1.setReportStatement(parameterReportDetail.getReportStatement());
			parameterReportDetail1.setTestId(parameterReportDetail.getTestId());
			parameterReportDetail1.setFrequencyNumber(parameterReportDetail.getFrequencyNumber());
			parameterReportDetail1.setFrequencyId(parameterReportDetail.getFrequencyId());
			parameterReportDetail1.setReason(parameterReportDetail.getReason());
			parameterReportDetails1.add(parameterReportDetail1);
		}
		return parameterReportDetails1;
	}
	
	@Override
	public List<ParameterMaster> listParameterReportDetail() {
		SQLQuery query = null;
		List<ParameterMaster> parameterMasterList=new ArrayList<ParameterMaster>();
		try {
		query = sessionFactory.getCurrentSession()
				.createSQLQuery("select epm.parameter_id,epm.parameter_name from ehr_parameter_master epm inner join ehr_parameter_report_details eprd on epm.parameter_id = eprd.parameter_id where epm.is_active='Y' and eprd.is_active = 1 group by eprd.parameter_id");
		List<Object[]> rows = query.list();
		for (Object[] row : rows) 
		{
			ParameterMaster parameterMaster=new ParameterMaster();
			if(row[0] != null){
				parameterMaster.setParameterId(Integer.parseInt(row[0].toString()));
			}
			if(row[1] != null){
				parameterMaster.setParameterName(row[1].toString());
			}
			parameterMasterList.add(parameterMaster);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parameterMasterList;
	}
	
	@Override
	public String deleteParameterReportDetail(ParameterReportDetail parameterReportDetail) {
		try {
			String hqlUpdate = "UPDATE ParameterReportDetail set isActive = :isActive, modifyBy = :modifyBy, modifyOn = :modifyOn WHERE parameterReportId.parameterId = :parameterId";
			Query query = sessionFactory.getCurrentSession().createQuery(hqlUpdate);
			query.setParameter("isActive", false);
			query.setParameter("modifyBy", parameterReportDetail.getModifyBy());
			query.setParameter("modifyOn", parameterReportDetail.getModifyOn());
			query.setParameter("parameterId", parameterReportDetail.getParameterReportId().getParameterId());
			int result = query.executeUpdate();
			// System.out.println("Rows affected: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Parameter Report Details Deleted Successfully";
	}


}
