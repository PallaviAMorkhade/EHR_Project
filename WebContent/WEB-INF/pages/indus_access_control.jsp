<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div id="pleaseWait" style="text-align: center;">
        <img style="margin-top: 250px;" height="43px" src="<c:url value="/indus/resources/assets/images/loading_black.gif"/>"/>
        <div style="margin-top: 10px; color: white">
            <b>Please wait...</b>
        </div>
</div>

<%
	JSONObject object=null;
	if((JSONObject)session.getAttribute("jsonObject")!=null){
	  object=(JSONObject)session.getAttribute("jsonObject");
	}
%>
			<span id="isDelete" class="hidden">
			<%
			if((JSONObject)session.getAttribute("jsonObject")!=null)
					{
			%>
				<%=(String)object.get("isDelete")%>
			<%}
			else{
				%>
				7,8
			<%
			}
			%>
			</span>
			
			<span id="isWrite" class="hidden">
			<%
			if((JSONObject)session.getAttribute("jsonObject")!=null)
					{
			%>
				<%=(String)object.get("isWrite")%>
			<%} 
			else{
				%>
				7,8
			<%
			}
			%>
			</span>
			
			<span id="isRead" class="hidden">
			<%
			if((JSONObject)session.getAttribute("jsonObject")!=null)
					{
			%>
				<%=(String)object.get("isRead")%>
			<%
			}
			else{
				%>
			1,3,4,6,7
			<%
			}
			%>
			</span>
			
			<span id="loginType" class="hidden"><%=(String)session.getAttribute("loginUserType")%></span>

			<span id="currentPageId" class="hidden"></span>
			
			<span id="interactionPageId" class="hidden"></span>
			
			<script>
			jQuery(document).ready(function() {
				setTimeout(function(){ getUserAccess(); }, 10);
			});
			</script>
			
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-79904946-1', 'auto');
  ga('send', 'pageview');

</script>
			