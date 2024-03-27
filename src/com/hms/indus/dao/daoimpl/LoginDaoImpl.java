package com.hms.indus.dao.daoimpl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hms.indus.bo.ClientMaster;
import com.hms.indus.bo.UserMaster;
import com.hms.indus.bo.Users;
import com.hms.indus.dao.LoginDao;


@Repository
public class LoginDaoImpl implements LoginDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean checkAuthentication(String userName, String password,HttpServletRequest request) {
		String decryptPassword = "";
		boolean encryptPassword = false;
		Integer clientId=0;
		String clientFirstName="";
		Integer count=0;
		
		//for user
		String decryptUserPassword = "";
		//boolean encryptUserPassword = false;
		String userId="";
		String userFirstName="";
		String userLastName="";
		String userTypeId="";
		String packages="";
		String memberCode="";
		String centers="";
		String isactive="";
		String isLeader="";
		try
		{
			
			Criteria criteria1 = sessionFactory.getCurrentSession()
					.createCriteria(UserMaster.class).createAlias("userTypeMaster", "userTypeMaster");
			if (userName != null && userName!="") {
				criteria1.add(Restrictions.eq("userName", userName));
			}
			ProjectionList proList1 = Projections.projectionList();
			proList1.add(Projections.property("newPassword"));
			proList1.add(Projections.property("userId"));
			proList1.add(Projections.property("firstName"));
			proList1.add(Projections.property("lastName"));
			proList1.add(Projections.property("userTypeMaster.userTypeId"));
			proList1.add(Projections.property("packages"));
			proList1.add(Projections.property("centers"));
			proList1.add(Projections.property("isActive"));
			proList1.add(Projections.property("userTypeMaster.isLeader"));
			proList1.add(Projections.property("memberCode"));
			criteria1.setProjection(proList1);

			List<Object[]> result1 = criteria1.list();
			for(Object[] master:result1)
			{
				if(master[0]!=null)
					decryptUserPassword=master[0].toString();
				if(master[1]!=null)
					userId=master[1].toString();
				
				if(master[2]!=null)
					userFirstName=master[2].toString();
				
				if(master[3]!=null)
					userLastName=master[3].toString();
				
				if(master[4]!=null)
					userTypeId=master[4].toString();
				
				if(master[5]!=null)
					packages=master[5].toString();
				
				if(master[6]!=null)
					centers=master[6].toString();
				
				if(master[7]!=null)
					isactive=master[7].toString();
				
				if(master[8]!=null)
					isLeader=master[8].toString();
				
				if(master[9]!=null)
					memberCode=master[9].toString();
			}
			
			if(decryptUserPassword!=null && decryptUserPassword!="")
			{
				if(decryptUserPassword.equals(password))
					encryptPassword =true;
			}	
			
			if(encryptPassword)
			{
				HttpSession session=request.getSession();
				session.setAttribute("userName", userName);
				session.setAttribute("password", password);
				session.setAttribute("userId", userId);
				session.setAttribute("userFirstName", userFirstName);
				session.setAttribute("userLastName", userLastName);
				session.setAttribute("count", 1);
				session.setAttribute("userTypeId", userTypeId);
				session.setAttribute("packages", packages);
				session.setAttribute("centers", centers);
				session.setAttribute("isactive", isactive);
				session.setAttribute("isLeader", isLeader);
				session.setAttribute("memberCode", memberCode);
			}
			
			//for indus client login
			if(result1.size()==0){
				
				Criteria criteria = sessionFactory.getCurrentSession()
						.createCriteria(ClientMaster.class);
				
				if (userName != null && userName!="") {
					criteria.add(Restrictions.eq("userId", userName));
				}

				ProjectionList proList = Projections.projectionList();
				proList.add(Projections.property("password"));
				proList.add(Projections.property("clientId"));
				proList.add(Projections.property("firstName"));
				proList.add(Projections.property("count"));
				proList.add(Projections.property("isActive"));
				criteria.setProjection(proList);

				List<Object[]> result = criteria.list();
				for(Object[] master:result)
				{
					if(master[0]!=null)
						decryptPassword=master[0].toString();
					
					if(master[1]!=null)
						clientId=Integer.parseInt(master[1].toString());
					
					if(master[2]!=null)
						clientFirstName=master[2].toString();
					
					if(master[3]!=null)
						count=Integer.parseInt(master[3].toString());
					
					if(master[4]!=null)
						isactive=master[4].toString();
				}
				
				if(decryptPassword!=null && decryptPassword!="")
				{
					if(decryptPassword.equals(password))
						encryptPassword =true;
				}	
				
				if(encryptPassword)
				{
					HttpSession session=request.getSession();
					session.setAttribute("userName", userName);
					session.setAttribute("password", password);
					session.setAttribute("clientId", clientId);
					session.setAttribute("userFirstName", clientFirstName);
					session.setAttribute("count", count);
			    	session.setAttribute("clientMasterId", clientId);
			    	session.setAttribute("clientUserName", userName);
			    	session.setAttribute("loginUserType", "clientLogin");
			    	session.setAttribute("clientUserName", userName);
			    	session.setAttribute("isactive", isactive);
			    	session.setAttribute("isLeader", isLeader);
				}
				//return encryptPassword;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptPassword;
	}

	@Override
	public boolean isAvailEmailId(String emailid) {
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(ClientMaster.class);
			criteria.add(Restrictions.eq("emailId", emailid));
			//criteria.add(Restrictions.eq("userId", emailid));
			List<ClientMaster> ltClientMaster = (List<ClientMaster>) criteria.list();
			if (ltClientMaster.size() > 0) {
				return true;
			} 
			
			if(ltClientMaster.size()==0){
				Criteria criteria1 = sessionFactory.getCurrentSession()
						.createCriteria(UserMaster.class);
					criteria1.add(Restrictions.eq("userName", emailid));
				List<UserMaster> userList=(List<UserMaster>)criteria1.list();
				if (userList.size() > 0) {
					return true;
				}
			}
			
			else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean resetPassword(ClientMaster clientMaster) {
		Boolean flag = false;
		try {		
				Criteria criteria = sessionFactory.getCurrentSession().createCriteria(ClientMaster.class);
				criteria.add(Restrictions.eq("userId", clientMaster.getUserId()));
				ClientMaster clientMaster1 = (ClientMaster) criteria.uniqueResult();
				clientMaster1.setPassword(clientMaster.getPassword());
				if(clientMaster1.getContactFlag().equals("Y")) {
					flag=true;
				}else {
					flag=false;
				}
			} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	
	Session session = null;
	Transaction tx = null;

	@Override
	public Users findByUserName(String username) {
		session = sessionFactory.openSession();
		tx = session.getTransaction();
		session.beginTransaction();
		Users user = (Users) session.load(Users.class, new String(username));
		tx.commit();
		return user;
	}

	@Override
	public boolean isAvailUserName(String username) {
		try {
			Criteria criteria = sessionFactory.getCurrentSession()
					.createCriteria(ClientMaster.class);
			criteria.add(Restrictions.eq("userId", username));
			List<ClientMaster> ltClientMaster = (List<ClientMaster>) criteria.list();
			if (ltClientMaster.size() > 0) {
				return true;
			} 
			
			if(ltClientMaster.size()==0){
				Criteria criteria1 = sessionFactory.getCurrentSession()
						.createCriteria(UserMaster.class);
					criteria1.add(Restrictions.eq("userName", username));
				List<UserMaster> userList=(List<UserMaster>)criteria1.list();
				if (userList.size() > 0) {
					return true;
				}
			}
			
			else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}


