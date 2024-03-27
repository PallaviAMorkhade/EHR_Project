package com.hms.indus.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

public class AccessControl {
	public static boolean getAccessControl(HttpServletRequest request,String url){
		HttpSession httpSession=request.getSession();
		JSONObject object=(JSONObject)httpSession.getAttribute("jsonObject");
		String isDelete="";
		if(object!=null){
			isDelete=(String)object.get("isDelete");
		}
		String isWrite="";
		if(object!=null){
			isWrite=(String)object.get("isWrite");
		}
		String isRead="";
		if(object!=null){
			isRead=(String)object.get("isRead");
		}
		
		if((String)httpSession.getAttribute("loginUserType")!=null){
			isRead="1,3,4,6,8";
			isWrite="3";
			isDelete="7";
		}
		
		String accessId = "";
		boolean access=false;
		for (Menu menu : Menu.values()){
		if(url.contains(menu.toString())){
				accessId=menu.getId();
			}
		}
		if(isDelete!=null && !isDelete.equals("null")){
			String[] menuId=isDelete.split(",");
			for(int i=0;i<menuId.length;i++){
				//System.out.println("menuId[i]:"+menuId[i]+"accessId:"+accessId);
				if(menuId[i].trim().equals(accessId)){
					access=true;
				}
			}
		}
		if(isWrite!=null && !isWrite.equals("null")){
			String[] menuId=isWrite.split(",");
			for(int i=0;i<menuId.length;i++){
				if(menuId[i].trim().equals(accessId)){
					access=true;
				}
			}
		}
		if(isRead!=null && !isRead.equals("null")){
			String[] menuId=isRead.split(",");
			for(int i=0;i<menuId.length;i++){
				if(menuId[i].trim().equals(accessId)){
					access=true;
				}
			}
		}
		/*if(access==false) {
			System.out.println("403 sorry you dont have access to this page");
		}*/
		return access;
	}  
	
	public static boolean isDeleteAccess(HttpServletRequest request,String currentPageId){
		boolean isAllow=false;
		HttpSession httpSession=request.getSession();
		JSONObject object=(JSONObject)httpSession.getAttribute("jsonObject");
		String isDelete="";
		if(object!=null){
			isDelete=(String)object.get("isDelete");
			if(isDelete!=null && !isDelete.trim().equals("null")){
			String[] checkId=isDelete.split(",");
			for(int i=0;i<checkId.length;i++){
				if(checkId[i].trim().equals(currentPageId)){
					isAllow=true;
					break;
				}
			}
		  }
		}
		if((String)httpSession.getAttribute("loginUserType")!=null){
			if(currentPageId.equals("7")){
				isAllow=true;
			}
			else{
				isAllow=false;
			}
		}
		return isAllow;
	}
	
	public static boolean isWriteAccess(HttpServletRequest request,String currentPageId){
		boolean isAllow=false;
		HttpSession httpSession=request.getSession();
		JSONObject object=(JSONObject)httpSession.getAttribute("jsonObject");
		String isDelete="";
		String isWrite="";
		if(object!=null){
			isDelete=(String)object.get("isDelete");
			if(isDelete!=null && !isDelete.trim().equals("null")){
				String[] checkId=isDelete.split(",");
				for(int i=0;i<checkId.length;i++){
					if(checkId[i].trim().equals(currentPageId)){
						isAllow=true;
						break;
					}
				}
			  }
			  
			isWrite=(String)object.get("isWrite");
			if(isWrite!=null && !isWrite.trim().equals("null") && !isWrite.trim().equals("0")){
			String[] checkId=isWrite.split(",");
			for(int i=0;i<checkId.length;i++){
				if(checkId[i].trim().equals(currentPageId)){
					isAllow=true;
					break;
				}
			}
		  }
		}
		if((String)httpSession.getAttribute("loginUserType")!=null){
			if(currentPageId.equals("7")){
				isAllow=true;
			}
			else{
				isAllow=false;
			}
		}
		return isAllow;
	}

}
