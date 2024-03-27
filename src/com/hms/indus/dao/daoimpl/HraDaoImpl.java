package com.hms.indus.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.HraTypeMaster;
import com.hms.indus.bo.OptionMaster;
import com.hms.indus.bo.QuestionMaster;
import com.hms.indus.bo.QuestionReportDetail;
import com.hms.indus.dao.HraDao;

@Repository
public class HraDaoImpl implements HraDao {
	
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public String saveHRATypeMaster(HraTypeMaster hraTypeMaster) {
		try {
			sessionFactory.getCurrentSession().save(hraTypeMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "HRATypeMaster saved successfully";
	}
	
	@Override
	public String deleteHRATypeMaster(HraTypeMaster hraTypeMaster) {
		try {
			HraTypeMaster hraTypeMaster2 = (HraTypeMaster) sessionFactory.getCurrentSession().get(HraTypeMaster.class, hraTypeMaster.getHraTypeId());
			hraTypeMaster2.setHraTypeMasterDeleteFlag(0);
			hraTypeMaster2.setModifyBy(hraTypeMaster.getModifyBy());
			hraTypeMaster2.setModifyOn(hraTypeMaster.getModifyOn());
			sessionFactory.getCurrentSession().update(hraTypeMaster2);
		} catch (Exception e) {
			e.printStackTrace();
		}	
			return "HRATypeMaster deleted";
	}
	
	@Override
	public String updateHRATypeMaster(HraTypeMaster hraTypeMaster) {
			try {
				HraTypeMaster hraTypeMaster2 = (HraTypeMaster) sessionFactory.getCurrentSession().get(HraTypeMaster.class, hraTypeMaster.getHraTypeId());
				hraTypeMaster2.setHraTypeName(hraTypeMaster.getHraTypeName());
				hraTypeMaster2.setHraPrintFlag(hraTypeMaster.getHraPrintFlag());
				hraTypeMaster2.setEndUserFlag(hraTypeMaster.getEndUserFlag());
				hraTypeMaster2.setModifyBy(hraTypeMaster.getModifyBy());
				hraTypeMaster2.setModifyOn(hraTypeMaster.getModifyOn());
				sessionFactory.getCurrentSession().update(hraTypeMaster2);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "HRATypeMaster updated";
	}

	@Override
	public List<HraTypeMaster> listOfAllHraType() {
		List<HraTypeMaster> hraTypeMasterList=new ArrayList<HraTypeMaster>();
		try {
			/*Criteria criteria=sessionFactory.getCurrentSession().createCriteria(HraTypeMaster.class);
			criteria.add(Restrictions.eq("hraTypeMasterDeleteFlag", 1));
			hraTypeMasterList=criteria.list();*/
			
			SQLQuery query = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"SELECT hra_type_id,hra_type_name,hra_print_flag,end_user_flag from hra_type_master where hra_type_master_delete_flag='1'");
			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				HraTypeMaster hraTypeMaster = new HraTypeMaster();
				if (row[0] != null) {
					hraTypeMaster.setHraTypeId(Integer.parseInt(row[0].toString()));
				}
				if (row[1] != null) {
					hraTypeMaster.setHraTypeName(row[1].toString());
				}
				if (row[2] != null) {
					hraTypeMaster.setHraPrintFlag(row[2].toString());
				}
				if (row[3] != null) {
					hraTypeMaster.setEndUserFlag(row[3].toString());
				}
				hraTypeMasterList.add(hraTypeMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hraTypeMasterList;
	}

	@Override
	public String saveQuestionMaster(QuestionMaster questionMaster) {
		try {
			sessionFactory.getCurrentSession().save(questionMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Question saved successfully";
	}

	@Override
	public String deleteQuestionMaster(QuestionMaster questionMaster) {
		try {
			QuestionMaster questionMaster2 = (QuestionMaster) sessionFactory.getCurrentSession().get(QuestionMaster.class, questionMaster.getQuestionId());
			questionMaster2.setQuestionMasterDeleteFlag(0);
			questionMaster2.setModifyBy(questionMaster.getModifyBy());
			questionMaster2.setModifyOn(questionMaster.getModifyOn());
			sessionFactory.getCurrentSession().update(questionMaster2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Question deleted";
	}

	@Override
	public String updateQuestionMaster(QuestionMaster questionMaster) {
		try {
			QuestionMaster questionMaster2 = (QuestionMaster) sessionFactory.getCurrentSession().get(QuestionMaster.class, questionMaster.getQuestionId());
			questionMaster.setAddedBy(questionMaster2.getAddedBy());
			questionMaster.setAddedOn(questionMaster2.getAddedOn());
			sessionFactory.getCurrentSession().merge(questionMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Question updated";
	}

	@Override
	public List<QuestionMaster> listOfAllQuestions() {
		List<QuestionMaster> questionMasterList=new ArrayList<QuestionMaster>();
		SQLQuery query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT qm.question_id,qm.question,qm.question_type,qm.hra_type_id,htm.hra_type_name,qm.question_display FROM question_master qm inner join hra_type_master htm on qm.hra_type_id = htm.hra_type_id WHERE qm.question_master_delete_flag = 1");
		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			QuestionMaster questionMaster = new QuestionMaster();
			if (row[0] != null) {
				questionMaster.setQuestionId(Integer.parseInt(row[0].toString()));
				List<OptionMaster> optionMasters=new ArrayList<OptionMaster>();
				SQLQuery query1 = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								"SELECT om.option_id,om.option_name,om.question_id FROM option_master om where om.option_master_delete_flag=1 and om.question_id="+row[0].toString());
				List<Object[]> rows1 = query1.list();
				for (Object[] row1 : rows1) {
					OptionMaster optionMaster = new OptionMaster();
					if (row[0] != null) {
						optionMaster.setOptionId(Integer.parseInt(row1[0].toString()));
					}
					if (row[1] != null) {
						optionMaster.setOption(row1[1].toString());
					}
					optionMasters.add(optionMaster);
					/*if (row[2] != null) {
						optionMaster.setQuestionMaster(questionMaster);
					}*/
				}
				questionMaster.setOptionMasterSet(optionMasters);
			}
			if (row[1] != null) {
				questionMaster.setQuestion(row[1].toString());
			}
			if (row[2] != null) {
				questionMaster.setQuestionType(row[2].toString());
			}
			HraTypeMaster hraTypeMaster = new HraTypeMaster();
			if (row[3] != null) {
				hraTypeMaster.setHraTypeId(Integer.parseInt(row[3].toString()));
			}
			if (row[4] != null) {
				hraTypeMaster.setHraTypeName(row[4].toString());
			}
			if (row[5] != null) {
				questionMaster.setQuestionDisplay(row[5].toString());
			}
			questionMaster.setHraTypeMaster(hraTypeMaster);
			questionMasterList.add(questionMaster);
		}
		/*try {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(QuestionMaster.class);
		criteria.add(Restrictions.eq("questionMasterDeleteFlag", 1));
		questionMasterList=criteria.list();
		for(int i=0;i<questionMasterList.size();i++){
			List<OptionMaster> optionMasterList=questionMasterList.get(i).getOptionMasterSet();
			List<OptionMaster> optionMasters=new ArrayList<OptionMaster>();
				for(int j=0;j<optionMasterList.size();j++){
					if(optionMasterList.get(j).getOptionMasterDeleteFlag()!=null){
					if(optionMasterList.get(j).getOptionMasterDeleteFlag()==1){
						optionMasters.add(optionMasterList.get(j));
					}
					}
				}
				questionMasterList.get(i).setOptionMasterSet(optionMasters);
		}
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return questionMasterList;
	}

	@Override
	public QuestionMaster getQuestionMasterByQuestionId(Integer questionId) {
		QuestionMaster questionMaster =new QuestionMaster();
		SQLQuery query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT qm.question_id,qm.question,qm.question_type,qm.hra_type_id,htm.hra_type_name,htm.hra_print_flag,qm.question_display,htm.end_user_flag FROM question_master qm inner join hra_type_master htm on qm.hra_type_id = htm.hra_type_id WHERE qm.question_master_delete_flag = 1 AND qm.question_id ="+questionId);
		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			if (row[0] != null) {
				questionMaster.setQuestionId(Integer.parseInt(row[0].toString()));
				List<OptionMaster> optionMasters=new ArrayList<OptionMaster>();
				SQLQuery query1 = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								"SELECT om.option_id,om.option_name,om.question_id FROM option_master om where om.option_master_delete_flag=1 and om.question_id="+row[0].toString());
				List<Object[]> rows1 = query1.list();
				for (Object[] row1 : rows1) {
					OptionMaster optionMaster = new OptionMaster();
					if (row[0] != null) {
						optionMaster.setOptionId(Integer.parseInt(row1[0].toString()));
						
						//For fetching report data details
						String sql = "SELECT eqrd.health_score as healthScore,eqrd.report_statement as reportStatement,eqrd.test_id as testId,eqrd.reason,eqrd.frequency_number as frequencyNumber,eqrd.frequency_id as frequencyId,efm.frequency,etm.test_desc as testName FROM ehr_question_report_details eqrd inner join ehr_frequency_master efm on eqrd.frequency_id = efm.frequency_id inner join ehr_test_master etm on eqrd.test_id = etm.test_id where eqrd.is_active=true and eqrd.question_id="+row[0].toString()+" and eqrd.option_id="+row1[0].toString(); 
						SQLQuery query2 = sessionFactory
								.getCurrentSession()
								.createSQLQuery(sql);
						query2.setResultTransformer(Transformers.aliasToBean(QuestionReportDetail.class));
						QuestionReportDetail questionReportDetail = (QuestionReportDetail) query2.uniqueResult();
						optionMaster.setQuestionReportDetail(questionReportDetail);
					}
					if (row[1] != null) {
						optionMaster.setOption(row1[1].toString());
					}
					optionMasters.add(optionMaster);
				}
				questionMaster.setOptionMasterSet(optionMasters);
				
				if(rows1.size()==0) {
					//For fetching report data details
					String sql = "SELECT eqrd.health_score as healthScore,eqrd.report_statement as reportStatement,eqrd.test_id as testId,eqrd.reason,eqrd.frequency_number as frequencyNumber,eqrd.frequency_id as frequencyId,efm.frequency,etm.test_desc as testName FROM ehr_question_report_details eqrd inner join ehr_frequency_master efm on eqrd.frequency_id = efm.frequency_id inner join ehr_test_master etm on eqrd.test_id = etm.test_id where eqrd.is_active=true and eqrd.question_id="+row[0].toString()+" and eqrd.option_id=0"; 
					SQLQuery query2 = sessionFactory
							.getCurrentSession()
							.createSQLQuery(sql);
					query2.setResultTransformer(Transformers.aliasToBean(QuestionReportDetail.class));
					QuestionReportDetail questionReportDetail = (QuestionReportDetail) query2.uniqueResult();
					questionMaster.setQuestionReportDetail(questionReportDetail);
				}
			}
			if (row[1] != null) {
				questionMaster.setQuestion(row[1].toString());
			}
			if (row[2] != null) {
				questionMaster.setQuestionType(row[2].toString());
			}
			HraTypeMaster hraTypeMaster = new HraTypeMaster();
			if (row[3] != null) {
				hraTypeMaster.setHraTypeId(Integer.parseInt(row[3].toString()));
			}
			if (row[4] != null) {
				hraTypeMaster.setHraTypeName(row[4].toString());
			}
			if (row[5] != null) {
				hraTypeMaster.setHraPrintFlag(row[5].toString());
			}
			if (row[6] != null) {
				questionMaster.setQuestionDisplay(row[6].toString());
			}
			if (row[7] != null) {
				hraTypeMaster.setEndUserFlag(row[7].toString());
			}
			questionMaster.setHraTypeMaster(hraTypeMaster);
		}
		/*try {
		questionMaster = (QuestionMaster) sessionFactory.getCurrentSession().get(QuestionMaster.class, questionId);
			List<OptionMaster> optionMasterList=questionMaster.getOptionMasterSet();
			List<OptionMaster> optionMasters=new ArrayList<OptionMaster>();
				for(int j=0;j<optionMasterList.size();j++){
					if(optionMasterList.get(j).getOptionMasterDeleteFlag()!=null){
					if(optionMasterList.get(j).getOptionMasterDeleteFlag()==1){
						optionMasters.add(optionMasterList.get(j));
					}
					}
				}
				questionMaster.setOptionMasterSet(optionMasters);
				
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return questionMaster;
	}

	@Override
	public List<OptionMaster> deleteOptionMaster(Integer questionId,
			Integer optionId) {
		List<OptionMaster> optionMasterList=new ArrayList<OptionMaster>();
		try {
			OptionMaster optionMaster = (OptionMaster) sessionFactory.getCurrentSession().get(OptionMaster.class, optionId);
			optionMaster.setOptionMasterDeleteFlag(0);
			sessionFactory.getCurrentSession().update(optionMaster);
			
			Criteria criteria=sessionFactory.getCurrentSession().createCriteria(OptionMaster.class);
			criteria.add(Restrictions.eq("questionMaster.questionId", questionId));
			criteria.add(Restrictions.eq("optionMasterDeleteFlag", 1));
			optionMasterList=criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return optionMasterList;
		
	}

	@Override
	public List<QuestionMaster> listOfAllQuestionsByHraId(
			Integer hraTypeMasterId) {
		List<QuestionMaster> questionMasterList=new ArrayList<QuestionMaster>();
		SQLQuery query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT qm.question_id,qm.question,qm.question_type,qm.hra_type_id,htm.hra_type_name FROM question_master qm inner join hra_type_master htm on qm.hra_type_id = htm.hra_type_id WHERE qm.question_master_delete_flag = 1 AND qm.hra_type_id ="+hraTypeMasterId);
		List<Object[]> rows = query.list();
		for (Object[] row : rows) {
			QuestionMaster questionMaster = new QuestionMaster();
			if (row[0] != null) {
				questionMaster.setQuestionId(Integer.parseInt(row[0].toString()));
				List<OptionMaster> optionMasters=new ArrayList<OptionMaster>();
				SQLQuery query1 = sessionFactory
						.getCurrentSession()
						.createSQLQuery(
								"SELECT om.option_id,om.option_name,om.question_id FROM option_master om where om.option_master_delete_flag=1 and om.question_id="+row[0].toString());
				List<Object[]> rows1 = query1.list();
				for (Object[] row1 : rows1) {
					OptionMaster optionMaster = new OptionMaster();
					if (row[0] != null) {
						optionMaster.setOptionId(Integer.parseInt(row1[0].toString()));
					}
					if (row[1] != null) {
						optionMaster.setOption(row1[1].toString());
					}
					optionMasters.add(optionMaster);
					/*if (row[2] != null) {
						optionMaster.setQuestionMaster(questionMaster);
					}*/
				}
				questionMaster.setOptionMasterSet(optionMasters);
			}
			if (row[1] != null) {
				questionMaster.setQuestion(row[1].toString());
			}
			if (row[2] != null) {
				questionMaster.setQuestionType(row[2].toString());
			}
			HraTypeMaster hraTypeMaster = new HraTypeMaster();
			if (row[3] != null) {
				hraTypeMaster.setHraTypeId(Integer.parseInt(row[3].toString()));
			}
			if (row[4] != null) {
				hraTypeMaster.setHraTypeName(row[4].toString());
			}
			questionMaster.setHraTypeMaster(hraTypeMaster);
			questionMasterList.add(questionMaster);
		}
		
		/*try {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(QuestionMaster.class);
		criteria.add(Restrictions.eq("questionMasterDeleteFlag", 1));
		//criteria.add(Restrictions.eq("hraTypeMaster.hraTypeId", hraTypeMasterId));
		questionMasterList=criteria.list();
		for(int i=0;i<questionMasterList.size();i++){
			List<OptionMaster> optionMasterList=questionMasterList.get(i).getOptionMasterSet();
			List<OptionMaster> optionMasters=new ArrayList<OptionMaster>();
				for(int j=0;j<optionMasterList.size();j++){
					if(optionMasterList.get(j).getOptionMasterDeleteFlag()!=null){
					if(optionMasterList.get(j).getOptionMasterDeleteFlag()==1){
						optionMasters.add(optionMasterList.get(j));
					}
					}
				}
				questionMasterList.get(i).setOptionMasterSet(optionMasters);
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		return questionMasterList;
	}

	@Override
	public String saveClientHRADetails(JSONArray jsonArray,JSONArray uncheckedArray) {
		
		for(int i=0;i<uncheckedArray.size();i++){
			JSONObject object=(JSONObject) uncheckedArray.get(i);
			String modifyBy=(String) object.get("modifyBy");
			String addedOn=(String) object.get("addedOn");
			String modifyOn=(String) object.get("modifyOn");
			String addedBy=(String) object.get("addedBy");
			Integer clientId=(Integer) object.get("clientId");
			String questionId=(String) object.get("questionId");
			String checkUpId=(String) object.get("checkUpId");
			
			String hql = "update client_hra_details set modify_by='"+modifyBy+"',modify_on='"+modifyOn+"',is_deleted='Y' where client_id='"+clientId+"' and question_id='"+questionId+"' and checkup_id='"+checkUpId+"'";
			SQLQuery queryInsert = sessionFactory.getCurrentSession().createSQLQuery(hql);
			queryInsert.executeUpdate();
		}
		
		for(int i=0;i<jsonArray.size();i++){
			//System.out.println(jsonArray.size()+"**json object********"+jsonArray.get(i));
			JSONObject object=(JSONObject) jsonArray.get(i);
			
			String modifyBy=(String) object.get("modifyBy");
			String addedOn=(String) object.get("addedOn");
			String modifyOn=(String) object.get("modifyOn");
			String addedBy=(String) object.get("addedBy");
			Integer clientId=(Integer) object.get("clientId");
			String questionId=(String) object.get("questionId");
			String optionId=(String) object.get("optionId");
			String text=(String) object.get("text");
			String checkUpId=(String) object.get("checkUpId");
			
			String sql = "INSERT INTO client_hra_details(added_on, added_by,client_id,question_id,text_answer,checkup_id) " +
					"values('"+addedOn+"','"+addedBy+"','"+clientId+"',"+questionId+",'"+text+"','"+checkUpId+"')" +
					" ON DUPLICATE KEY UPDATE is_deleted='N', text_answer='"+text+"', modify_by='"+modifyBy+"',modify_on='"+modifyOn+"'";
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
			query.executeUpdate();
			
			String clientLog = "INSERT INTO ehr_client_hra_log (modify_by, modify_on,client_id,question_id,text_answer,checkup_id) " +
					"values('"+modifyBy+"','"+modifyOn+"','"+clientId+"',"+questionId+",'"+text+"','"+checkUpId+"')";
			SQLQuery clientLogInsert = sessionFactory.getCurrentSession().createSQLQuery(clientLog);
			clientLogInsert.executeUpdate();
			
		}
		
		JSONObject object1 = (JSONObject) jsonArray.get(0);
		String checkUpId1 = (String) object1.get("checkUpId");
		if (checkUpId1.equals("0")) {
			Integer clientId1 = (Integer) object1.get("clientId");
			String addedOn1 = (String) object1.get("addedOn");
			String addedBy1 = (String) object1.get("addedBy");
			String sql1 = "INSERT INTO ehr_precouncelling(client_id,visit_id,added_on,added_by) " + "values('"
					+ clientId1 + "'," + checkUpId1 + ",'" + addedOn1 + "','" + addedBy1 + "')"
					+ " ON DUPLICATE KEY UPDATE added_on='" + addedOn1 + "'" + ",added_by='" + addedBy1 + "'";
			SQLQuery query1 = sessionFactory.getCurrentSession().createSQLQuery(sql1);
			query1.executeUpdate();
		}
		return "Saved successfully";
	}
	
	@Override
	public JSONArray getListOfQuestionClientHRADetails(Integer clientId,Integer visitId){
		SQLQuery query = null;
		JSONArray jsonArray = new JSONArray();
		try {
			query = sessionFactory
					.getCurrentSession()
					.createSQLQuery("select question_id,text_answer,client_id from client_hra_details where is_deleted='N' and client_id='"+clientId+"' and checkup_id="+visitId);
			List<Object[]> rows = query.list();
			for (Object[] row : rows) 
			{
				JSONObject jsonObject=new JSONObject();
				if(row[0] != null){
					jsonObject.put("questionId",row[0].toString());
				}
				if(row[1] != null){
					jsonObject.put("textAnswer",row[1].toString());
				}
				if(row[2] != null){
					jsonObject.put("clientId",row[2].toString());
				}
				jsonArray.add(jsonObject);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public String copyHRAFromVisit(JSONObject object) {
		String addedOn=(String) object.get("addedOn");
		String addedBy=(String) object.get("addedBy");
		String modifyBy=(String) object.get("modifyBy");
		String modifyOn=(String) object.get("modifyOn");
		Integer clientId=(Integer) object.get("clientId");
		Integer visitId=(Integer) object.get("visitId");
		JSONArray answerArray = getListOfQuestionClientHRADetails(clientId,visitId);
		
		Integer checkUpId = getVisitIdByClientId(clientId);
		for(int i=0;i<answerArray.size();i++) {
			JSONObject answerObject = (JSONObject) answerArray.get(i);
			//String dbClientId=(String) answerObject.get("clientId");
			String questionId=(String) answerObject.get("questionId");
			String text=(String) answerObject.get("textAnswer");
			String sql = "INSERT INTO client_hra_details(added_on, added_by,client_id,question_id,text_answer,checkup_id) " +
					"values('"+addedOn+"','"+addedBy+"','"+clientId+"',"+questionId+",'"+text+"','"+checkUpId+"')" +
					" ON DUPLICATE KEY UPDATE is_deleted='N', text_answer='"+text+"', modify_by='"+modifyBy+"',modify_on='"+modifyOn+"'";
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
			query.executeUpdate();
		}
		return "HRA Copied Successfully";
	}
	
	@Override
	public Integer getVisitIdByClientId(Integer clientId) {
		SQLQuery checkupQuery = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT checkup_id FROM checkup_master WHERE client_id = "+clientId+" order by checkup_id desc limit 1");
		Integer visitId = (Integer) checkupQuery.uniqueResult();
		return visitId;
	}

	//For Question Report Details
	
	@Override
	public String saveQuestionReportDetails(List<QuestionReportDetail> questionReportDetails) {
		try {
			for(int i=0;i<questionReportDetails.size();i++) {
				sessionFactory.getCurrentSession().saveOrUpdate(questionReportDetails.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Question Report Details Saved Successfully";
	}

	@Override
	public List<QuestionReportDetail> getQuestionReportByQuestionId(Integer questionId) {
		String hql = "from QuestionReportDetail WHERE isActive = :isActive and questionReportId.questionId = :questionId";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("isActive", true);
		query.setParameter("questionId", questionId);
		List<QuestionReportDetail> questionReportDetails = (List<QuestionReportDetail>) query.list();
		List<QuestionReportDetail> questionReportDetails1 = new ArrayList<QuestionReportDetail>();
		for (QuestionReportDetail questionReportDetail : questionReportDetails) {
			QuestionReportDetail questionReportDetail1 = new QuestionReportDetail();
			questionReportDetail1.setQuestionReportId(questionReportDetail.getQuestionReportId());
			questionReportDetail1.setHealthScore(questionReportDetail.getHealthScore());
			questionReportDetail1.setReportStatement(questionReportDetail.getReportStatement());
			questionReportDetail1.setTestId(questionReportDetail.getTestId());
			questionReportDetail1.setFrequencyNumber(questionReportDetail.getFrequencyNumber());
			questionReportDetail1.setFrequencyId(questionReportDetail.getFrequencyId());
			questionReportDetail1.setReason(questionReportDetail.getReason());
			questionReportDetails1.add(questionReportDetail1);
		}
		return questionReportDetails1;
	}

	@Override
	public List<QuestionMaster> listQuestionReportDetail() {
		List<QuestionMaster> questionMasterList=new ArrayList<QuestionMaster>();
		SQLQuery query = sessionFactory
				.getCurrentSession()
				.createSQLQuery(
						"SELECT qm.question_id as questionId,qm.question,qm.question_type as questionType FROM question_master qm inner join ehr_question_report_details eqrd on qm.question_id = eqrd.question_id WHERE qm.question_master_delete_flag = 1 and eqrd.is_active = 1 group by eqrd.question_id");
		query.setResultTransformer(Transformers.aliasToBean(QuestionMaster.class));
		questionMasterList = query.list();
		return questionMasterList;
	}

	@Override
	public String deleteQuestionReportDetail(QuestionReportDetail questionReportDetail) {
		try {
			String hqlUpdate = "UPDATE QuestionReportDetail set isActive = :isActive, modifyBy = :modifyBy, modifyOn = :modifyOn WHERE questionReportId.questionId = :questionId";
			Query query = sessionFactory.getCurrentSession().createQuery(hqlUpdate);
			query.setParameter("isActive", false);
			query.setParameter("modifyBy", questionReportDetail.getModifyBy());
			query.setParameter("modifyOn", questionReportDetail.getModifyOn());
			query.setParameter("questionId", questionReportDetail.getQuestionReportId().getQuestionId());
			int result = query.executeUpdate();
			// System.out.println("Rows affected: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Question Report Details Deleted Successfully";
	}

	@Override
	public String mergePreCouncelling(JSONObject object) {
		Integer clientId = Integer.parseInt(object.get("clientId").toString());
		Integer checkupId = Integer.parseInt(object.get("visitId").toString());
		String mergedBy = (String) object.get("mergedBy");
		String mergedOn = (String) object.get("mergedOn");
		try {
			String hql = "update client_hra_details set checkup_id='"+checkupId+"'"
			+ " where client_id='"+clientId+"' and checkup_id=0";
			SQLQuery queryUpdate = sessionFactory.getCurrentSession().createSQLQuery(hql);
			queryUpdate.executeUpdate();
			
			String sql = "update ehr_precouncelling set visit_id='" + checkupId + "',merged_on='" + mergedOn + "'"
					+ ",merged_by='" + mergedBy + "' where client_id='" + clientId + "' and visit_id=0";
			SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
			query.executeUpdate();
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Pre-Counselling Merged Successfully";
	}

	@Override
	public Integer checkPreCouncelling(JSONObject object) {
		Integer clientId = Integer.parseInt(object.get("clientId").toString());
		//Integer checkupId = Integer.parseInt(object.get("visitId").toString());
		String sql = "select count(*) as count from client_hra_details "
				+ " where client_id='"+clientId+"' and checkup_id=0";
		SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(sql);
		query.addScalar("count", IntegerType.INSTANCE);
		Integer count = (Integer)query.uniqueResult();
		return count;
	}

}