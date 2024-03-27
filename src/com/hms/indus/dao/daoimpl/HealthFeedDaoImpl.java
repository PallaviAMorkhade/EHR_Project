package com.hms.indus.dao.daoimpl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.dao.HealthFeedDao;
import com.hms.indus.util.DateConvertUtil;

@Repository
public class HealthFeedDaoImpl implements HealthFeedDao{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public JSONArray listOfHealthFeedMaster() {
		JSONArray jsonArray = new JSONArray();
		try {
			SQLQuery listOfHealthFeed = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select ehr_health_feed.feed_type_id,ehr_feed_type.feed_type,ehr_health_feed.added_by,ehr_health_feed.added_on,ehr_health_feed.modify_by,ehr_health_feed.modify_on,health_feed_id,health_feed from ehr_health_feed left join ehr_feed_type ON ehr_health_feed.feed_type_id = ehr_feed_type.feed_type_id where health_feed_delete_flag = 1");
			List<Object[]> rows = listOfHealthFeed.list();
			for (Object[] row : rows) {
				JSONObject object = new JSONObject();
				if (row[0] != null) {
					object.put("feedTypeId", row[0].toString());
				}
				if (row[1] != null) {
					object.put("feedType", row[1].toString());
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
					object.put("healthFeedId", row[6].toString());
				}
				if (row[7] != null) {
					object.put("healthFeed", row[7].toString());
				}
				jsonArray.add(object);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public void updateHealthFeedMaster(JSONObject object) {
		Integer healthFeedMasterId = (Integer) object.get("healthFeedMasterId");
		Integer feedTypeId = (Integer) object.get("feedTypeId");
		String healthFeed = (String) object.get("healthFeed");
		String healthFeedDescription = (String) object.get("healthFeedDescription");
		String addedBy = (String) object.get("addedBy");
		String addedOn = (String) object.get("addedOn");
		try {
			String sql = "update ehr_health_feed set modify_by='"
					+ addedBy + "',modify_on='" + addedOn + "',feed_type_id='"
					+ feedTypeId + "',health_feed='"+healthFeed+"',health_feed_description='"+healthFeedDescription+"' where health_feed_id=" + healthFeedMasterId;
			SQLQuery feedTypeUpdate = sessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			feedTypeUpdate.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String saveHealthFeedMaster(JSONObject object) {
		Integer feedTypeId = (Integer) object.get("feedTypeId");
		String healthFeed = (String) object.get("healthFeed");
		String healthFeedDate = (String) object.get("healthFeedDate");
		String healthFeedDescription = (String) object.get("healthFeedDescription");
		String addedBy = (String) object.get("addedBy");
		String addedOn = (String) object.get("addedOn");
		try {
			String sql = "INSERT INTO ehr_health_feed(feed_type_id,added_by,added_on,health_feed_delete_flag,health_feed,health_feed_date,health_feed_description) "
					+ "values('"
					+ feedTypeId
					+ "','"
					+ addedBy
					+ "','"
					+ addedOn
					+ "','1','"+healthFeed+"','"+healthFeedDate+"','"+healthFeedDescription+"')";
			SQLQuery healthFeedInsert = sessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			healthFeedInsert.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saved successfully";
	}

	@Override
	public void deleteHealthFeedMaster(JSONObject object) {
		Integer healthFeedMasterId = (Integer) object.get("healthFeedMasterId");
		String addedBy = (String) object.get("addedBy");
		String addedOn = (String) object.get("addedOn");
		try {
			String sql = "update ehr_health_feed set modify_by='"
					+ addedBy + "',modify_on='" + addedOn + "',health_feed_delete_flag=0 where health_feed_id=" + healthFeedMasterId;
			SQLQuery healthFeedDelete = sessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			healthFeedDelete.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public JSONObject getHealthFeedMasterByHealthFeedId(
			Integer healthFeedMasterId) {
		JSONObject object = new JSONObject();
		try {
			SQLQuery listOfFeedType = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select ehr_health_feed.feed_type_id,ehr_feed_type.feed_type,ehr_health_feed.added_by,ehr_health_feed.added_on,ehr_health_feed.modify_by,ehr_health_feed.modify_on,health_feed_id,health_feed,health_feed_date,health_feed_description from ehr_health_feed left join ehr_feed_type ON ehr_health_feed.feed_type_id = ehr_feed_type.feed_type_id where health_feed_id="+healthFeedMasterId);
			List<Object[]> rows = listOfFeedType.list();
			for (Object[] row : rows) {
				if (row[0] != null) {
					object.put("feedTypeId", row[0].toString());
				}
				if (row[1] != null) {
					object.put("feedType", row[1].toString());
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
					object.put("healthFeedId", row[6].toString());
				}
				if (row[7] != null) {
					object.put("healthFeed", row[7].toString());
				}
				if (row[8] != null) {
					object.put("healthFeedDate", DateConvertUtil.convertDate(row[8].toString()));
				}
				if (row[9] != null) {
					object.put("healthFeedDescription", row[9].toString());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

}
