package com.hms.indus.dao.daoimpl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.dao.FeedTypeDao;

@Repository
public class FeedTypeDaoImpl implements FeedTypeDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public JSONArray listOfFeedTypeMaster() {
		JSONArray jsonArray = new JSONArray();
		try {
			SQLQuery listOfFeedType = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select feed_type_id,feed_type,added_by,added_on,modify_by,modify_on from ehr_feed_type where feed_type_delete_flag=1");
			List<Object[]> rows = listOfFeedType.list();
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
				jsonArray.add(object);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public String updateFeedTypeMaster(JSONObject object) {

		Integer feedTypeMasterId = (Integer) object.get("feedTypeMasterId");
		String feedType = (String) object.get("feedType");
		String addedBy = (String) object.get("addedBy");
		String addedOn = (String) object.get("addedOn");

		try {
			String sql = "update ehr_feed_type set modify_by='"
					+ addedBy + "',modify_on='" + addedOn + "',feed_type='"
					+ feedType + "' where feed_type_id=" + feedTypeMasterId;
			SQLQuery feedTypeUpdate = sessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			feedTypeUpdate.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "updated successfully";
	}

	@Override
	public String saveFeedTypeMaster(JSONObject object) {

		String feedType = (String) object.get("feedType");
		String addedBy = (String) object.get("addedBy");
		String addedOn = (String) object.get("addedOn");

		try {
			String sql = "INSERT INTO ehr_feed_type(feed_type,added_by,added_on,feed_type_delete_flag) "
					+ "values('"
					+ feedType
					+ "','"
					+ addedBy
					+ "','"
					+ addedOn
					+ "','1')";
			SQLQuery feedTypeInsert = sessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			feedTypeInsert.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saved successfully";
	}

	@Override
	public void deleteFeedTypeMaster(JSONObject object) {
		Integer feedTypeMasterId = (Integer) object.get("feedTypeMasterId");
		String addedBy = (String) object.get("addedBy");
		String addedOn = (String) object.get("addedOn");

		try {
			String sql = "update ehr_feed_type set modify_by='"
					+ addedBy + "',modify_on='" + addedOn + "',feed_type_delete_flag=0 where feed_type_id=" + feedTypeMasterId;
			SQLQuery feedTypeDelete = sessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			feedTypeDelete.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public JSONObject getFeedTypeMasterByFeedTypeId(Integer feedTypeMasterId) {
		JSONObject object = new JSONObject();
		try {
			SQLQuery listOfFeedType = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select feed_type_id,feed_type,added_by,added_on,modify_by,modify_on from ehr_feed_type where feed_type_id="+feedTypeMasterId);
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
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

}
