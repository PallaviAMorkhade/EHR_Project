package com.hms.indus.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hms.indus.util.FilePath;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final String UPLOAD_DIRECTORY = FilePath.getBasePath();

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	
	HttpSession session=request.getSession();
	Integer clientId = Integer.parseInt(session.getAttribute("clientId").toString());
	//for current time and date
	java.util.Date date = new java.util.Date();
	java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("MM-dd-yyyy");
	String currentTime = simpleDateFormat.format(date);
	// process only if its multipart content
	if (isMultipart) {
		// Create a factory for disk-based file items
		FileItemFactory factory = new DiskFileItemFactory();
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// Parse the request
			List<FileItem> multiparts = upload.parseRequest(request);

			for (FileItem item : multiparts) {
				if (!item.isFormField()) {
					String name = new File(item.getName()).getName();
					String fileName=clientId+"_"+currentTime+"_"+ name;
					item.write(new File(UPLOAD_DIRECTORY + File.separator + fileName));
					System.out.println("UploadServlet -- File has been uploaded successfully!");
				}
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("File upload failed");
		}
	}
}
	
}
