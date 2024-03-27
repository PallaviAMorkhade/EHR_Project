package com.hms.indus.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hms.indus.bo.ClientMaster;
import com.hms.indus.bo.UserMaster;
import com.hms.indus.bo.Users;
import com.hms.indus.service.ClientService;
import com.hms.indus.service.LoginService;
import com.hms.indus.service.UserTypeService;
import com.hms.indus.util.FilePath;

@Controller
public class LoginContoller {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	UserTypeService userTypeMasterService;
	
	@Autowired
	ClientService clientService;

	@RequestMapping(value = "/loginAuthentication", method = RequestMethod.POST)
	public void getUserProfile(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String userName=request.getParameter("userName");
		String password=request.getParameter("newPassword");
		//String lock=request.getParameter("lock");

		boolean result=loginService.checkAuthentication(userName,password,request);
		HttpSession session=request.getSession();
		
		/*SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();					
		String curDate=formatter.format(date);
		System.err.println("lock======____"+curDate.compareTo("09-03-2020"));
		&& curDate.compareTo("09-03-2020")  < 0*/
		if(result)
		{
			/*session.setAttribute("userName", userName);
			session.setAttribute("password", password);*/
			String userId=(String) session.getAttribute("userId");
			String isactive=(String) session.getAttribute("isactive");
			if(userId!=null && isactive.equals("Y")){
				JSONObject object=userTypeMasterService.getUserAccessByUserId(Integer.parseInt(userId));
				session.setAttribute("jsonObject", object);
				response.sendRedirect("homepage");//index
			}else if(isactive.equals("Y")){
				response.sendRedirect("homepage");//index
			}else { //if(isactive.equals("N"))
				response.sendRedirect("incorrectLogin");
			}
		}
		else
		{
			response.sendRedirect("incorrectLogin");
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try
		{
			HttpSession session=request.getSession();
			Integer clientId=(Integer) session.getAttribute("clientMasterId");
			if(clientId!=null){
				clientService.removeClientLocked(clientId);
			}
			session.invalidate();
			
		/*	requestDispatcher=request.getRequestDispatcher();
			requestDispatcher.forward(request, response);*/
			
			response.sendRedirect("login");
		}
		catch(Exception e)
		{
			
		}
	}
	
	@RequestMapping(value = "/readImage", method = RequestMethod.GET)
	public void readPhoto(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try
		{
			/*response.setContentType("image/jpeg");*/
			String imageName=request.getParameter("url");
			String pathToWeb = FilePath.getBasePath();
			File f = new File(pathToWeb + File.separator + imageName);
			ImageInputStream iis = ImageIO.createImageInputStream(f);

			Iterator<ImageReader> imageReaders = ImageIO.getImageReaders(iis);
			
			BufferedImage bi = ImageIO.read(f);
			OutputStream out = response.getOutputStream();

			while (imageReaders.hasNext()) {
			    ImageReader reader = (ImageReader) imageReaders.next();
			    ImageIO.write(bi,  reader.getFormatName(), out);
			}
			out.close();
		}
		catch(Exception e)
		{
			
		}
	}
	
	@RequestMapping(value = "/readPdf", method = RequestMethod.GET)
	public void readPdf(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try
		{
			 String pdfName = request.getParameter("pdfFileName");
			 String reportDestination = FilePath.getBasePath() + File.separator + pdfName;
			// System.out.println("reportDestination:"+reportDestination);
			 FileInputStream fis = new FileInputStream(new File(reportDestination));
			 // Fast way to copy a bytearray from InputStream to OutputStream
			 org.apache.commons.io.IOUtils.copy(fis, response.getOutputStream());
			 response.setContentType("application/pdf");
			 response.setHeader("Content-Disposition", "attachment; filename=" + reportDestination);
			 response.flushBuffer();
		}
		catch(Exception e)
		{
			
		}
	}
	
	@RequestMapping(value = "/readContentPdf", method = RequestMethod.GET)
	public void readContentPdf(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try
		{
			 String pdfName = request.getParameter("pdfFileName");
			 String reportDestination = FilePath.getContentPath() + File.separator + pdfName;
			// System.out.println("reportDestination:"+reportDestination);
			 FileInputStream fis = new FileInputStream(new File(reportDestination));
			 // Fast way to copy a bytearray from InputStream to OutputStream
			 org.apache.commons.io.IOUtils.copy(fis, response.getOutputStream());
			 response.setContentType("application/pdf");
			 response.setHeader("Content-Disposition", "attachment; filename=" + reportDestination);
			 response.flushBuffer();
		}
		catch(Exception e)
		{
			
		}
	}
	
	@RequestMapping(value = "/readTextFile", method = RequestMethod.GET)
	public void readTextFile(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try
		{
			 String textFileName = request.getParameter("textFileName");
			 String reportDestination = FilePath.getBasePath() + File.separator + textFileName;
			 //System.out.println("reportDestination:"+reportDestination);
			 FileInputStream fis = new FileInputStream(new File(reportDestination));
			 // Fast way to copy a bytearray from InputStream to OutputStream
			 org.apache.commons.io.IOUtils.copy(fis, response.getOutputStream());
			 /*response.setContentType("application/pdf");
			 response.setHeader("Content-Disposition", "attachment; filename=" + reportDestination);
			 response.flushBuffer();*/
		}
		catch(Exception e)
		{
			
		}
	}
	
	@RequestMapping(value = "/preview", method = RequestMethod.GET)
	@ResponseBody public FileSystemResource getPreview(HttpServletRequest request,HttpServletResponse response) {
	    String fileName = request.getParameter("fileName");
	    String reportDestination = FilePath.getBasePath() + File.separator + fileName;
	    return new FileSystemResource(reportDestination);
	}
	
	@RequestMapping(value = "/previewContentVideo", method = RequestMethod.GET)
	@ResponseBody public FileSystemResource previewContentVideo(HttpServletRequest request,HttpServletResponse response) {
	    String fileName = request.getParameter("fileName");
	    String reportDestination = FilePath.getContentPath() + File.separator + fileName;
	    return new FileSystemResource(reportDestination);
	}
	
	@RequestMapping(value = "/isAvailEmailId", method = RequestMethod.POST)
	public @ResponseBody boolean isAvailEmailId(@RequestParam("emailid") String emailId) {
		boolean flag=false;
		try
		{
			flag=loginService.isAvailEmailId(emailId);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}
	
	@RequestMapping(value = "/isAvailUserName", method = RequestMethod.POST)
	public @ResponseBody String isAvailUserName(@RequestParam("username") String username) {
		boolean flag=false;
		String details="";
		ClientMaster clientMaster=new ClientMaster();
		try
		{
			flag=loginService.isAvailUserName(username);
			clientMaster=clientService.getResetPasswordMobNoByUsername(username);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		details=flag+"#"+clientMaster.getMobNo()+"#"+clientMaster.getEmailId();
		return details;
	}
	
	@RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	public ModelAndView resetPassword(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		String username=request.getParameter("resetPasswordUsername");
		String mobNo=request.getParameter("resetPasswordMobNo");
		String emailId=request.getParameter("resetPasswordEmail");
		ClientMaster clientMaster=new ClientMaster();
		clientMaster.setUserId(username);
		clientMaster.setMobNo(mobNo);
		clientMaster.setEmailId(emailId);
		if(mobNo!=null || emailId!=null){
		if (loginService.resetPassword(clientMaster)) {
			modelAndView
					.addObject(
							"msg",
							"Your Indus EHR password is reset successfully!");
		} else {
			modelAndView.addObject("msg", "Something went wrong..!");
		}
		}
		modelAndView.addObject("login", new UserMaster());
		modelAndView.setViewName("indus_login");
		return modelAndView;
	}

	/////////////Spring security
	@RequestMapping(value = { "/", "/home" })
	public String getUserDefault() {
		return "home";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.POST)
	public String logout(ModelMap model) {
		return "login";
	}

	/*@RequestMapping("/login")
	public ModelAndView getLoginForm(@ModelAttribute Users users,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		String message = "";
		if (error != null) {
			message = "Incorrect username or password !";
		} else if (logout != null) {
			message = "Logout successful !";
		}
		return new ModelAndView("indus_login", "message", message);
	}*/

	@RequestMapping("/admin**")
	public String getAdminProfile() {
		return "admin";
	}

	@RequestMapping("/user**")
	public String getUserProfile() {
		return "user";
	}

	/*@RequestMapping("/403")
	public ModelAndView getAccessDenied() {
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		String username = "";
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			username = userDetail.getUsername();
		}
		return new ModelAndView("403", "username", username);
	}*/
}
