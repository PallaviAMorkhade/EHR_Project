package com.hms.indus.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.hms.indus.util.FilePath;

@WebServlet("/UploadFile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 200, // 200MB
		maxRequestSize = 1024 * 1024 * 500) // 500MB
public class UploadFile extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		Integer clientId = 0;
		if(session.getAttribute("clientId") != null) {
			clientId = Integer.parseInt(session.getAttribute("clientId").toString());
		}
		// for current time and date
		java.util.Date date = new java.util.Date();
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("MM-dd-yyyy");
		String currentTime = simpleDateFormat.format(date);
		String UPLOAD_DIRECTORY = FilePath.getBasePath();
		// System.err.println("***"+request.getParameter("callFrom"));

		// For Content Upload Path
		if (request.getParameter("callFrom") != null && request.getParameter("callFrom").equals("content")) {
			UPLOAD_DIRECTORY = FilePath.getContentPath();
		}

		try {
			for (Part part : request.getParts()) {
				String fileName = extractFileName(part);
				if (fileName != null && fileName != "") {
					if (request.getParameter("callFrom") != null && request.getParameter("callFrom").equals("content")) {
						part.write(UPLOAD_DIRECTORY + File.separator + (currentTime + "_" + fileName));
					}else {
						part.write(UPLOAD_DIRECTORY + File.separator + (clientId +"_"+ currentTime + "_" + fileName));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("UploadFile -- File has been uploaded successfully!");
	}

	/**
	 * Extracts file name from HTTP header content-disposition
	 */
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

}