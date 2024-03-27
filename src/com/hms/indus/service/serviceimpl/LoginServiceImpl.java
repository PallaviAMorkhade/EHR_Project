package com.hms.indus.service.serviceimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hms.indus.bo.ClientMaster;
import com.hms.indus.bo.EmailRecord;
import com.hms.indus.bo.SMSRecord;
import com.hms.indus.bo.UserRole;
import com.hms.indus.bo.Users;
import com.hms.indus.dao.LoginDao;
import com.hms.indus.service.LoginService;
import com.hms.indus.util.CallSmscApi;
import com.hms.indus.util.SSLEmail;

@Service//("loginService")
public class LoginServiceImpl implements LoginService{

	@Autowired
	LoginDao loginDao;
	
	@Override
	@Transactional
	public boolean checkAuthentication(String userName, String password,HttpServletRequest request) {
		return loginDao.checkAuthentication(userName,password,request);
	}

	@Override
	@Transactional
	public boolean isAvailEmailId(String emailid) {
		return loginDao.isAvailEmailId(emailid);
	}

	@Override
	@Transactional
	public boolean resetPassword(ClientMaster clientMaster) {
		try {
			Random rnd = new Random();
			Integer n = 10000000 + rnd.nextInt(90000000);
			String text = "i"+n.toString()+"p";
			clientMaster.setPassword(text);
			if(loginDao.resetPassword(clientMaster)){
				
				if(clientMaster.getMobNo()!=null && !clientMaster.getMobNo().equals("")){
					String smsData = "Your Indus EHR password is reset successfully!, Login Id: "+clientMaster.getUserId()+",Password: "+text+", Visit http://ehr.indushealthplus.com/indus/login  Regards,Team INDUS";
					CallSmscApi callSmscApi = new CallSmscApi();
					SMSRecord smsRecord = new SMSRecord();
					smsRecord.setTopic("Reset Password");
					smsRecord.setSentBy("Client");
					smsRecord.setClientId(clientMaster.getClientId());
					smsRecord.setSmsMedicalAdvice(smsData);
					smsRecord.setSentTo(clientMaster.getMobNo());
					callSmscApi.sendSms("+"+clientMaster.getMobNo(),smsData,smsRecord);
				}

				if(clientMaster.getEmailId()!=null && !clientMaster.getEmailId().equals("")){
					String mailSubject = "Reset password for indus health care";
					String mailData = "Your Indus EHR password is reset successfully!, <br>Login Id: "+clientMaster.getUserId()+",<br> Password: "+text+", <br> Visit http://ehr.indushealthplus.com/indus/login <br> Regards,Team INDUS";
					SSLEmail sendMail = new SSLEmail();
					EmailRecord emailRecord = new EmailRecord();
					emailRecord.setTopic("Reset Password");
					emailRecord.setSentBy("Client");
					emailRecord.setClientId(clientMaster.getClientId());
					emailRecord.setEmailSubject(mailSubject);
					emailRecord.setEmailMedicalAdvice(mailData);
					emailRecord.setSentTo(clientMaster.getEmailId());
					sendMail.sendMail(clientMaster.getEmailId(), mailData, mailSubject, emailRecord);
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	@Transactional
	public boolean isAvailUserName(String username) {
		return loginDao.isAvailUserName(username);
	}
	
	/*@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		Users user = loginDao.findByUserName(username);

		List<GrantedAuthority> authorities = buildUserAuthority(user
				.getUserRole());

		return buildUserForAuthentication(user, authorities);
	}

	private User buildUserForAuthentication(Users user,
			List<GrantedAuthority> authorities) {
		return new User(user.getUsername(), user.getPassword(),
				user.isEnabled(), true, true, true, authorities);
	}

	private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (UserRole userRole : userRoles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(
				setAuths);

		return Result;
	}*/

}
