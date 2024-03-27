package com.hms.indus.dao.daoimpl;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.dao.VideoDao;
import com.hms.indus.util.DateConvertUtil;

@Repository
public class VideoDaoImpl implements VideoDao{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public JSONArray listOfVideoMaster() {
		JSONArray jsonArray = new JSONArray();
		try {
			SQLQuery listOfVideo = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select video_id,file_name,added_by,added_on,modify_by,modify_on,video_description,video_date from ehr_video_master where video_delete_flag = 1");
			List<Object[]> rows = listOfVideo.list();
			for (Object[] row : rows) {
				JSONObject object = new JSONObject();
				if (row[0] != null) {
					object.put("videoId", row[0].toString());
				}
				if (row[1] != null) {
					object.put("fileName", row[1].toString());
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
					object.put("videoDescription", row[6].toString());
				}
				if (row[7] != null) {
					object.put("videoDate", row[7].toString());
				}
				jsonArray.add(object);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonArray;
	}

	@Override
	public void updateVideoMaster(JSONObject object) {
		Integer videoMasterId = (Integer) object.get("videoMasterId");
		String videoDescription = (String) object.get("videoDescription");
		String addedBy = (String) object.get("addedBy");
		String addedOn = (String) object.get("addedOn");
		
		try {
			String sql = "update ehr_video_master set modify_by='"
					+ addedBy + "',modify_on='" + addedOn + "',video_description='"+videoDescription+"' where video_id=" + videoMasterId;
			SQLQuery videoDelete = sessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			videoDelete.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public String saveVideoMaster(JSONObject object) {
		String videoDescription = (String) object.get("videoDescription");
		String fileName = (String) object.get("fileName");
		String videoDate = (String) object.get("videoDate");
		String addedBy = (String) object.get("addedBy");
		String addedOn = (String) object.get("addedOn");
		try {
			String sql = "INSERT INTO ehr_video_master(file_name,added_by,added_on,video_delete_flag,video_description,video_date) "
					+ "values('"
					+ fileName
					+ "','"
					+ addedBy
					+ "','"
					+ addedOn
					+ "','1','"+videoDescription+"','"+videoDate+"')";
			SQLQuery videoInsert = sessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			videoInsert.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saved successfully";
	}

	@Override
	public void deleteVideoMaster(JSONObject object) {
		Integer videoMasterId = (Integer) object.get("videoMasterId");
		String addedBy = (String) object.get("addedBy");
		String addedOn = (String) object.get("addedOn");
		
		try {
			String sql = "update ehr_video_master set modify_by='"
					+ addedBy + "',modify_on='" + addedOn + "',video_delete_flag=0 where video_id=" + videoMasterId;
			SQLQuery videoDelete = sessionFactory.getCurrentSession()
					.createSQLQuery(sql);
			videoDelete.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public JSONObject getVideoMasterByVideoId(Integer videoMasterId) {
		JSONObject object = new JSONObject();
		try {
			SQLQuery listOfVideo = sessionFactory
					.getCurrentSession()
					.createSQLQuery(
							"select video_id,file_name,added_by,added_on,modify_by,modify_on,video_description,video_date from ehr_video_master where video_delete_flag = 1");
			List<Object[]> rows = listOfVideo.list();
			for (Object[] row : rows) {
				if (row[0] != null) {
					object.put("videoId", row[0].toString());
				}
				if (row[1] != null) {
					object.put("fileName", row[1].toString());
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
					object.put("videoDescription", row[6].toString());
				}
				if (row[7] != null) {
					object.put("videoDate", DateConvertUtil.convertDate(row[7].toString()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
	
	@Override
	public String getMaxId(){
		SQLQuery videoId = sessionFactory.getCurrentSession().createSQLQuery("select max(video_id) from ehr_video_master where video_delete_flag=1");
		Integer maxVideoId = (Integer) videoId.uniqueResult();
		SQLQuery healthFeedId = sessionFactory.getCurrentSession().createSQLQuery("select max(health_feed_id) from ehr_health_feed where health_feed_delete_flag=1");
		Integer maxHealthFeedId = (Integer) healthFeedId.uniqueResult();
		SQLQuery linkId = sessionFactory.getCurrentSession().createSQLQuery("select max(link_id) from ehr_link_master where link_delete_flag=1");
		Integer maxLinkId = (Integer) linkId.uniqueResult();
		String maxId=maxVideoId+"_"+maxHealthFeedId+"_"+maxLinkId;
		return maxId;
	}

}
