package com.hms.indus.dao.daoimpl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.ClientMaster;
import com.hms.indus.bo.HraTypeMaster;
import com.hms.indus.bo.MenuMaster;
import com.hms.indus.bo.UserMaster;
import com.hms.indus.bo.UserTypeMaster;
import com.hms.indus.dao.UserTypeMasterDao;

@Repository
public class UserTypeMasterDaoImpl implements UserTypeMasterDao{
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public List<UserTypeMaster> getListOfUserTypeMaster() {
		List<UserTypeMaster> userTypeMasterList=new ArrayList<UserTypeMaster>();
		try {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(UserTypeMaster.class);
		//criteria.add(Restrictions.eq("hraTypeMasterDeleteFlag", 1));
		userTypeMasterList=criteria.list();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userTypeMasterList;
	}

	@Override
	public String saveUserMaster(UserMaster userMaster) {
		try {
		Integer userId=(Integer) sessionFactory.getCurrentSession().save(userMaster);
		//for giving access to user
		String isRead="";
		String isWrite="";
		String isDelete="";
		Integer userTypeId=userMaster.getUserTypeMaster().getUserTypeId();
		if(userTypeId==1 || userTypeId==2){
			isRead="1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39";
			isWrite="1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39";
			isDelete="1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39";
		}
		if(userTypeId==3 || userTypeId==4){
			isRead="1,2";
			isWrite="3";
			isDelete="4,6,9,10";
		}
		if(userTypeId==5){
			isRead="1,2,4,6";
			isWrite="3";
			isDelete="10";
		}
		if(userTypeId==13){
			isRead="1,2,4,6";
			isWrite=null;
			isDelete=null;
		}if(userTypeId==14){
			isRead="1,2,3,4";
			isWrite="4";
			isDelete="4";
		}
		String hql = "INSERT INTO ehr_user_access_management(added_on, added_by,user_id,is_read,is_write,is_delete) " +
				"values('"+userMaster.getAddAt()+"','"+userMaster.getAddBy()+"','"+userId+"','"+isRead+"','"+isWrite+"','"+isDelete+"')";
		SQLQuery queryInsert = sessionFactory.getCurrentSession().createSQLQuery(hql);
		queryInsert.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "saved successfully";
	}
	
	@Override
	public List<UserMaster> getListOfUserMaster(Integer userTypeId) {
		List<UserMaster> userMasterList=new ArrayList<UserMaster>();
		try {
		/*Criteria criteria=sessionFactory.getCurrentSession().createCriteria(UserMaster.class);
		criteria.add(Restrictions.eq("isActive", 'Y'));
		if(userTypeId!=null && userTypeId!=0){
			criteria.add(Restrictions.eq("userTypeMaster.userTypeId", userTypeId));
		}*/
			String typeQuery = "";
			if(userTypeId!=null && userTypeId!=0){
				typeQuery = "ehr_users.user_type_id="+userTypeId+" and ";
			}
			SQLQuery query = sessionFactory.getCurrentSession()
					.createSQLQuery(
							"SELECT first_name, middle_name, last_name, user_id,title,user_type_name,ehr_users.user_type_id FROM ehr_users inner join user_type_master on ehr_users.user_type_id=user_type_master.user_type_id WHERE "+typeQuery+" isactive = 'Y'");
				List<Object[]> rows = query.list();
				for (Object[] row : rows)
				{
					UserMaster userMaster = new UserMaster();
					if (row[0] != null){
						userMaster.setFirstName(row[0].toString());
					}
					if (row[1] != null){
						userMaster.setMiddleName(row[1].toString());
					}
					if (row[2] != null){
						userMaster.setLastName(row[2].toString());
					}
					if (row[3] != null){
						userMaster.setUserId(Integer.parseInt(row[3].toString()));
					}
					if (row[4] != null){
						userMaster.setTitle(row[4].toString());
					}
					if (row[5] != null){
						UserTypeMaster userTypeMaster=new UserTypeMaster();
						userTypeMaster.setUserTypeName(row[5].toString());
						userTypeMaster.setUserTypeId(Integer.parseInt(row[6].toString()));
						userMaster.setUserTypeMaster(userTypeMaster);
					}
					userMasterList.add(userMaster);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userMasterList;
	}

	@Override
	public String updateUserMaster(UserMaster userMaster) {
		try {
		UserMaster userMaster2 = (UserMaster) sessionFactory.getCurrentSession().get(UserMaster.class, userMaster.getUserId());
		userMaster2.setUserId(userMaster.getUserId());
		userMaster2.setTitle(userMaster.getTitle());
		userMaster2.setFirstName(userMaster.getFirstName());
		userMaster2.setMiddleName(userMaster.getMiddleName());
		userMaster2.setLastName(userMaster.getLastName());
		userMaster2.setUserName(userMaster.getUserName());
		userMaster2.setNewPassword(userMaster.getNewPassword());
		userMaster2.setMemberCode(userMaster.getMemberCode());
		
		UserTypeMaster userTypeMaster=new UserTypeMaster();
		userTypeMaster.setUserTypeId(userMaster.getUserTypeMaster().getUserTypeId());
		userMaster2.setUserTypeMaster(userTypeMaster);
		userMaster2.setIsActive('Y');
		userMaster2.setModifyAt(userMaster.getModifyAt());
		userMaster2.setModifyBy(userMaster.getModifyBy());
		userMaster2.setLoginStatus("1");
		userMaster2.setCenters(userMaster.getCenters());
		userMaster2.setTests(userMaster.getTests());
		userMaster2.setPackages(userMaster.getPackages());
		sessionFactory.getCurrentSession().update(userMaster2);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "User Master updated";
	}

	@Override
	public String deleteUserMaster(UserMaster userMaster) {
		try {
		UserMaster userMaster2 = (UserMaster) sessionFactory.getCurrentSession().get(UserMaster.class, userMaster.getUserId());
		userMaster2.setIsActive('N');
		sessionFactory.getCurrentSession().update(userMaster2);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "User Master deleted";
	}

	@Override
	public UserMaster getUserBuUserId(int userId) {
		UserMaster userMaster = new UserMaster();
		try {
			userMaster=(UserMaster) sessionFactory.getCurrentSession().get(UserMaster.class, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userMaster;
	}
	
	@Override
	public String setLoginStatusUserMaster(UserMaster userMaster) {
		try {
		UserMaster userMaster2 = (UserMaster) sessionFactory.getCurrentSession().get(UserMaster.class, userMaster.getUserId());
		userMaster2.setLoginStatus(userMaster.getLoginStatus());
		sessionFactory.getCurrentSession().update(userMaster2);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Login status updated successfully";
	}

	@Override
	public List<MenuMaster> getListOfMenuMaster() {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(MenuMaster.class);
		//criteria.add(Restrictions.eq("hraTypeMasterDeleteFlag", 1));
		List<MenuMaster> menuMasterList=criteria.list();
		return menuMasterList;
	}
	
	@Override
	public List<UserMaster> autoSuggestionsUsers(String letter) {
		List<UserMaster> listUserMasters = new ArrayList<UserMaster>();
		SQLQuery query = null;
		try {
				query = sessionFactory.getCurrentSession()
					.createSQLQuery(
							"SELECT first_name,middle_name,last_name,user_id,title,user_type_name FROM ehr_users inner join user_type_master on ehr_users.user_type_id=user_type_master.user_type_id WHERE isactive='Y' and concat(first_name,' ',middle_name,'', last_name) like '%"+letter+"%'");
				query.setFirstResult(0);
				query.setMaxResults(100);
				List<Object[]> rows = query.list();
				for (Object[] row : rows)
				{
					UserMaster userMaster=new UserMaster();
					if (row[0] != null){
						userMaster.setFirstName(row[0].toString());
					}
					if (row[1] != null){
						userMaster.setMiddleName(row[1].toString());
					}
					if (row[2] != null){
						userMaster.setLastName(row[2].toString());
					}
					if (row[3] != null){
						userMaster.setUserId(Integer.parseInt(row[3].toString()));
					}
					if (row[4] != null){
						userMaster.setTitle(row[4].toString());
					}
					if (row[5] != null){
						UserTypeMaster userTypeMaster=new UserTypeMaster();
						userTypeMaster.setUserTypeName(row[5].toString());
						userMaster.setUserTypeMaster(userTypeMaster);
					}
					listUserMasters.add(userMaster);
				}
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return listUserMasters;
	}

	@Override
	public UserMaster getUserByUserId(Integer userId) {
		SQLQuery query = null;
		UserMaster userMaster=new UserMaster();
		try {
				query = sessionFactory.getCurrentSession()
					.createSQLQuery(
							"SELECT first_name, middle_name, last_name, user_id,title,user_type_name FROM ehr_users inner join user_type_master on ehr_users.user_type_id=user_type_master.user_type_id WHERE user_id="+userId+" and isactive = 'Y'");
				List<Object[]> rows = query.list();
				for (Object[] row : rows)
				{
					if (row[0] != null){
						userMaster.setFirstName(row[0].toString());
					}
					if (row[1] != null){
						userMaster.setMiddleName(row[1].toString());
					}
					if (row[2] != null){
						userMaster.setLastName(row[2].toString());
					}
					if (row[3] != null){
						userMaster.setUserId(Integer.parseInt(row[3].toString()));
					}
					if (row[4] != null){
						userMaster.setTitle(row[4].toString());
					}
					if (row[5] != null){
						UserTypeMaster userTypeMaster=new UserTypeMaster();
						userTypeMaster.setUserTypeName(row[5].toString());
						userMaster.setUserTypeMaster(userTypeMaster);
					}
				}
			}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userMaster;
	}

	@Override
	public String saveUserAccess(JSONObject object) {
		try {
		Integer userId=(Integer) object.get("userId");
		String addedBy=(String) object.get("addedBy");
		String addedOn=(String) object.get("addedOn");
		String modifyBy=(String) object.get("modifyBy");
		String modifyOn=(String) object.get("modifyOn");
		String isRead=(String) object.get("isRead");
		String isWrite=(String) object.get("isWrite");
		String isDelete=(String) object.get("isDelete");
		SQLQuery query = sessionFactory
				.getCurrentSession()
				.createSQLQuery("select user_id from ehr_user_access_management where user_id="+userId);
		List<Object> queryList=query.list();
		if(queryList.size()==0){
			String hql = "INSERT INTO ehr_user_access_management(added_on, added_by,user_id,is_read,is_write,is_delete) " +
					"values('"+addedOn+"','"+addedBy+"','"+userId+"','"+isRead+"','"+isWrite+"','"+isDelete+"')";
			SQLQuery queryInsert = sessionFactory.getCurrentSession().createSQLQuery(hql);
			queryInsert.executeUpdate();
		}
		else{
			String hql = "update ehr_user_access_management set modify_by='"+modifyBy+"',modify_on='"+modifyOn+"',is_read='"+isRead+"',is_write='"+isWrite+"',is_delete='"+isDelete+"' where user_id="+userId;
			SQLQuery queryUpdate = sessionFactory.getCurrentSession().createSQLQuery(hql);
			queryUpdate.executeUpdate();
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Access saved successfully";
	}

	@Override
	public JSONObject getUserAccessByUserId(Integer userId) {
		JSONObject object=new JSONObject();
		try {
		SQLQuery query = sessionFactory
				.getCurrentSession()
				.createSQLQuery("select user_id,is_read,is_write,is_delete from ehr_user_access_management where user_id="+userId);
		
		List<Object[]> rows = query.list();
		for (Object[] row : rows)
		{
			if (row[0] != null){
				object.put("userId", row[0].toString());
			}
			if (row[1] != null){
				object.put("isRead", row[1].toString());
			}
			if (row[2] != null){
				object.put("isWrite", row[2].toString());
			}
			if (row[3] != null){
				object.put("isDelete", row[3].toString());
			}
		}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return object;
	}
	
	@Override
	public Boolean changePasswordUser(UserMaster userMaster) {
		Boolean flag = false;
		try {		
				Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserMaster.class);
				criteria.add(Restrictions.eq("userId", userMaster.getUserId()));
				UserMaster userMaster1 = (UserMaster) criteria.uniqueResult();
				if(userMaster1.getNewPassword().equals(userMaster.getFirstName()))
				{
					userMaster1.setNewPassword(userMaster.getLastName());
					flag=true;
				}
			} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

}
