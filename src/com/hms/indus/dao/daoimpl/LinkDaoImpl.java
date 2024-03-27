package com.hms.indus.dao.daoimpl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.dao.LinkDao;
import com.hms.indus.util.DateConvertUtil;

@Repository
public class LinkDaoImpl implements LinkDao{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public JSONArray listOfLinkMaster() {
		JSONArray jsonArray = new JSONArray();
		try {
			SQLQuery listOfLink = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select link_id,link,added_by,added_on,modify_by,modify_on,link_description from ehr_link_master where link_delete_flag = 1");
			List<Object[]> rows = listOfLink.list();
			for (Object[] row : rows) {
				JSONObject object = new JSONObject();
				if (row[0] != null) {
					object.put("linkId", row[0].toString());
				}
				if (row[1] != null) {
					object.put("link", row[1].toString());
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
					object.put("linkDescription", row[6].toString());
				}
				jsonArray.add(object);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public void updateLinkMaster(JSONObject object) {
		Integer linkMasterId = (Integer) object.get("linkMasterId");
		String link = (String) object.get("link");
		String linkDescription = (String) object.get("linkDescription");
		String addedBy = (String) object.get("addedBy");
		String addedOn = (String) object.get("addedOn");
		
		try {
			String sql = "update ehr_link_master set modify_by='"
					+ addedBy + "',modify_on='" + addedOn + "',link='"+link+"',link_description='"+linkDescription+"' where link_id=" + linkMasterId;
			SQLQuery healthFeedDelete = sessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			healthFeedDelete.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String saveLinkMaster(JSONObject object) {
		String link = (String) object.get("link");
		String linkDescription = (String) object.get("linkDescription");
		String linkDate = (String) object.get("linkDate");
		String addedBy = (String) object.get("addedBy");
		String addedOn = (String) object.get("addedOn");
		try {
			String sql = "INSERT INTO ehr_link_master(link,added_by,added_on,link_delete_flag,link_description,link_date) "
					+ "values('"
					+ link
					+ "','"
					+ addedBy
					+ "','"
					+ addedOn
					+ "','1','"+linkDescription+"','"+linkDate+"')";
			SQLQuery linkInsert = sessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			linkInsert.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saved successfully";
	}

	@Override
	public void deleteLinkMaster(JSONObject object) {
		Integer linkMasterId = (Integer) object.get("linkMasterId");
		String addedBy = (String) object.get("addedBy");
		String addedOn = (String) object.get("addedOn");
		
		try {
			String sql = "update ehr_link_master set modify_by='"
					+ addedBy + "',modify_on='" + addedOn + "',link_delete_flag=0 where link_id=" + linkMasterId;
			SQLQuery linkDelete = sessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			linkDelete.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public JSONObject getLinkMasterByLinkId(Integer linkMasterId) {
		JSONObject object = new JSONObject();
		try {
			SQLQuery listOfLink = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select link_id,link,added_by,added_on,modify_by,modify_on,link_description,link_date from ehr_link_master where link_id ="+linkMasterId);
			List<Object[]> rows = listOfLink.list();
			for (Object[] row : rows) {
				if (row[0] != null) {
					object.put("linkId", row[0].toString());
				}
				if (row[1] != null) {
					object.put("link", row[1].toString());
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
					object.put("linkDescription", row[6].toString());
				}
				if (row[7] != null) {
					object.put("linkDate", DateConvertUtil.convertDate(row[7].toString()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

}
