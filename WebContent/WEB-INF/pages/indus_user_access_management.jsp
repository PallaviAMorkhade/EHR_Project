<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<!-- Template Name: Clip-Two - Responsive Admin Template build with Twitter Bootstrap 3.x | Author: ClipTheme -->
<!--[if IE 8]><html class="ie8" lang="en"><![endif]-->
<!--[if IE 9]><html class="ie9" lang="en"><![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- start: HEAD -->
<head>
<title>Indus|User Access Management</title>
<!-- start: META -->
<!--[if IE]><meta http-equiv='X-UA-Compatible' content="IE=edge,IE=9,IE=8,chrome=1" /><![endif]-->
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="" name="description" />
<meta content="" name="author" />
<!-- end: META -->

<!-- start: GOOGLE FONTS -->
<link
	href="http://fonts.googleapis.com/css?family=Lato:300,400,400italic,600,700|Raleway:300,400,500,600,700|Crete+Round:400italic"
	rel="stylesheet" type="text/css" />
<!-- end: GOOGLE FONTS -->
<!-- start: MAIN CSS -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/vendor/bootstrap/css/bootstrap.min.css"/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/vendor/fontawesome/css/font-awesome.min.css"/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/vendor/themify-icons/themify-icons.min.css"/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/vendor/animate.css/animate.min.css"/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/vendor/perfect-scrollbar/perfect-scrollbar.min.css"/>"
	media="screen">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/vendor/switchery/switchery.min.css"/>"
	media="screen">
<!-- end: MAIN CSS -->
<!-- start: CLIP-TWO CSS -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/assets/css/styles.css"/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/assets/css/plugins.css"/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/assets/css/themes/theme-4.css"/>">
<!-- end: CLIP-TWO CSS -->

<!-- start: CSS REQUIRED FOR THIS PAGE ONLY -->
<link rel="stylesheet" media="screen"
	href="<c:url value="/indus/resources/vendor/jstree/themes/default/style.min.css"/>">
<!-- end: CSS REQUIRED FOR THIS PAGE ONLY -->

<!--Start of header css -->
<link rel="stylesheet"
	href="<c:url value="/indus/resources/alertify/alertify.core.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/indus/resources/alertify/alertify.default.css"/>"
	id="toggleCSS" />
<!--End of header css -->

<link
	href="<c:url value="/indus/resources/vendor/bootstrap-touchspin/jquery.bootstrap-touchspin.min.css"/>"
	rel="stylesheet" media="screen">
<link
	href="<c:url value="/indus/resources/vendor/select2/select2.min.css"/>"
	rel="stylesheet" media="screen">
<link
	href="<c:url value="/indus/resources/vendor/bootstrap-datepicker/bootstrap-datepicker3.standalone.min.css"/>"
	rel="stylesheet" media="screen">
<link
	href="<c:url value="/indus/resources/vendor/bootstrap-timepicker/bootstrap-timepicker.min.css"/>"
	rel="stylesheet" media="screen">


<!-- for smart search -->
<link
	href="<c:url value="/indus/resources/vendor/jquery-ui/jquery-ui-1.10.1.custom.min.css"/>"
	rel="stylesheet" media="screen">

<style>
.fixedHeight {
	font-size: 12px;
	max-height: 250px;
	margin-bottom: 10px;
	overflow-x: scroll;
	z-index: 1000000;
}
</style>

<script
	src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>"></script>

</head>

<%
	String userName = (String) session.getAttribute("clientUserName");
	String clientFirstName = (String) session.getAttribute("userFirstName");
	Integer clientUserId = (Integer) session.getAttribute("clientMasterId");
%>
<jsp:include page="indus_access_control.jsp"></jsp:include>
<!-- end: HEAD -->
<body>
	<span id="clientMasterId" class="hidden"><%=clientUserId%></span>
	<span id="clientUserId" class="hidden"><%=userName%></span>
	<div id="app">
		<!-- sidebar -->
		<jsp:include page="indus_left_menu.jsp"></jsp:include>
		<!-- / sidebar -->
		<div class="app-content">
			<!-- start: TOP NAVBAR -->
			<jsp:include page="indus_header.jsp"></jsp:include>
			<!-- end: TOP NAVBAR -->
			<div class="main-content">
				<div class="wrap-content container" id="container">
					<!-- start: DASHBOARD TITLE -->
					<section id="page-title">
						<div class="row">
							<div class="col-sm-7 padding_zero">
								<!-- <h1 class="mainTitle">Indus EHR know your health the smart way</h1> -->
								<font style="font-size: 17px;"><b>Welcome</b><b> <%=clientFirstName%></b></font>
							</div>
						</div>
					</section>
					<div class="row" id="mainBody">

						<div id="app">
							<div class="app-content">
								<!-- end: TOP NAVBAR -->
								<div class="wrap-content container" id="container">
									<!-- start: DYNAMIC TABLE -->
									<div class="container-fluid container-fullw bg-white"
										id='ajaxContent'>
										<h4>User Access management</h4>
										<div class="row">
											<div class="col-md-1" style="padding: 10px 17px;">
												<h5 class="text-bold margin-bottom-15">Name</h5>
											</div>
											<div class="col-md-3">
												<div class="form-group">
													<input class="form-control" placeholder="Search User"
														id="searchBox" name="searchBox" type="search"
														autocomplete="off">
												</div>
											</div>

											<div class="col-md-8">
												<span id="userIdForUserAccess" class="hidden"></span>
												<table
													class="table table-striped table-bordered table-hover table-full-width"
													id="sample_1">
													<thead>
														<tr>
															<th><i class="fa fa-user"></i> User Name : <span
																id="userNameForUserAccess"></span></th>
															<th>User Role : <span id="userTypeForUserAccess"></span></th>
														</tr>
													</thead>
													<!-- <tbody id="userMasterTable">
													</tbody> -->
												</table>

											</div>

										</div>

										<div class="row userAccessMainDiv" style="display: none;">
											<div class="col-md-12">
												<table
													class="table  table-striped table-bordered table-hover table-responsive"
													id="sample_1">
													<thead>
														<tr>
															<th>Module Name</th>
															<th>View</th>
															<th>Edit</th>
															<th>Delete</th>
														</tr>
													</thead>
													<tbody id="userAccessTable">
														<tr>
															<td>Dashboard</td>
															<td><input type="checkbox" value="checkbox1"
																name="dashboardCheckbox" class="userAccessView view_1"
																id="1"> <label for="view"> </label></td>
															<!-- <td>
														<input type="checkbox" value="checkbox2" name="dashboardCheckbox" class="userAccessEdit edit_1" id="1">
														<label for="edit">
														</label>
													</td>
													<td>
														<input type="checkbox" value="checkbox3" name="dashboardCheckbox" class="userAccessDelete delete_1" id="1">
														<label for="delete">
														</label>
													</td> -->
														</tr>

														<tr>
															<td>Database</td>
															<td><input type="checkbox" value="checkbox1"
																name="databaseCheckbox" class="userAccessView view_2"
																id="2"> <label for="view"> </label></td>
														</tr>
														
														<tr>
															<td>Beneficiary API</td>
															<td><input type="checkbox" value="checkbox1"
																name="apiCheckbox" class="userAccessView view_42"
																id="42"> <label for="view"> </label></td>
														</tr>
														
														<tr>
															<td>Work Management</td>
															<td><input type="checkbox" value="checkbox1"
																name="workManagementCheckbox" class="userAccessView view_40"
																id="40"> <label for="view"> </label></td>
														</tr>

														<tr>
															<td>Profile</td>
															<td colspan="3"><a class="btn btn-xs btn-orange"
																onclick="showProfileSubMenu()">View Acces <i
																	class="glyphicon glyphicon-circle-arrow-down"></i></a></td>
														</tr>

														<tr id="profileSubMenu" style="display: none;">
															<td colspan="2">
																<table
																	class="table table-striped table-bordered table-hover table-full-width"
																	id="sample_1">
																	<thead>
																		<tr>
																			<th>Sub Module Name</th>
																			<th>View</th>
																			<th>Edit</th>
																			<th>Delete</th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td>Demograph</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="demographCheckbox"
																				class="userAccessView view_3" id="3"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="demographCheckbox"
																				class="userAccessEdit edit_3" id="3"> <label
																				for="edit"> </label></td>
																			<!-- <td>
														<input type="checkbox" value="checkbox3" name="demographCheckbox" class="userAccessDelete delete_3" id="3">
														<label for="delete">
														</label>
													</td> -->
																		</tr>

																		<tr>
																			<td>My Reports</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="myReportsCheckbox"
																				class="userAccessView view_4" id="4"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="myReportsCheckbox"
																				class="userAccessEdit edit_4" id="4"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="myReportsCheckbox"
																				class="userAccessDelete delete_4" id="4"> <label
																				for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Verification</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="verificationCheckbox"
																				class="userAccessView view_5" id="5"> <label
																				for="view"> </label></td>
																			<!-- <td>
														<input type="checkbox" value="checkbox2" name="verificationCheckbox" class="userAccessEdit edit_5" id="5">
														<label for="edit">
														</label>
													</td>
													<td>
														<input type="checkbox" value="checkbox3" name="verificationCheckbox" class="userAccessDelete delete_5" id="5">
														<label for="delete">
														</label>
													</td> -->
																		</tr>

																		<tr>
																			<td>HRA</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="hraCheckbox" class="userAccessView view_6"
																				id="6"> <label for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="hraCheckbox" class="userAccessEdit edit_6"
																				id="6"> <label for="edit"> </label></td>
																			<!-- <td>
														<input type="checkbox" value="checkbox3" name="hraCheckbox" class="userAccessDelete delete_6" id="6">
														<label for="delete">
														</label>
													</td> -->
																		</tr>

																		<tr>
																			<td>Self Upload</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="otherDetailsCheckbox"
																				class="userAccessView view_7" id="7"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="otherDetailsCheckbox"
																				class="userAccessEdit edit_7" id="7"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="otherDetailsCheckbox"
																				class="userAccessDelete delete_7" id="7"> <label
																				for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Reminders</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="remindersCheckbox"
																				class="userAccessView view_8" id="8"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="remindersCheckbox"
																				class="userAccessEdit edit_8" id="8"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="remindersCheckbox"
																				class="userAccessDelete delete_8" id="8"> <label
																				for="delete"> </label></td>
																		</tr>


																	</tbody>
																</table>
															</td>
														</tr>


														<tr>
															<td>Data Management</td>
															<td colspan="3"><a class="btn btn-xs btn-orange"
																onclick="showDataManagementSubMenu()">View Acces <i
																	class="glyphicon glyphicon-circle-arrow-down"></i></a></td>
														</tr>

														<tr id="dataManagementSubMenu" style="display: none;">
															<td colspan="2">

																<table
																	class="table table-striped table-bordered table-hover table-full-width"
																	id="sample_1">
																	<thead>
																		<tr>
																			<th>Module Name</th>
																			<th>View</th>
																			<th>Edit</th>
																			<th>Delete</th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td>Data Entry</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="dataEntryCheckbox"
																				class="userAccessView view_9" id="9"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="dataEntryCheckbox"
																				class="userAccessEdit edit_9" id="9"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="dataEntryCheckbox"
																				class="userAccessDelete delete_9" id="9"> <label
																				for="delete"> </label></td>
																		</tr>

																	</tbody>
																</table>
															</td>
														</tr>

														<tr>
															<td>Interaction</td>
															<td colspan="3"><a class="btn btn-xs btn-orange"
																onclick="showInteractionSubMenu()">View Acces <i
																	class="glyphicon glyphicon-circle-arrow-down"></i></a></td>
														</tr>


														<tr id="interactionSubMenu" style="display: none;">
															<td colspan="2">

																<table
																	class="table table-striped table-bordered table-hover table-full-width"
																	id="sample_1">
																	<thead>
																		<tr>
																			<th>Module Name</th>
																			<th>View</th>
																			<th>Edit</th>
																			<th>Delete</th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td>Analysis</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="analysisCheckbox"
																				class="userAccessView view_29" id="29"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="analysisCheckbox"
																				class="userAccessEdit edit_29" id="29"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="analysisCheckbox"
																				class="userAccessDelete delete_29" id="29">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Engagement</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="engagementCheckbox"
																				class="userAccessView view_10" id="10"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="engagementCheckbox"
																				class="userAccessEdit edit_10" id="10"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="engagementCheckbox"
																				class="userAccessDelete delete_10" id="10">
																				<label for="delete"> </label></td>
																		</tr>
																	</tbody>
																</table>
															</td>
														</tr>


														<tr>
															<td>Administator</td>
															<td colspan="3"><a class="btn btn-xs btn-orange"
																onclick="showAdministratorSubMenu()">View Acces <i
																	class="glyphicon glyphicon-circle-arrow-down"></i></a></td>
														</tr>

														<tr id="administratorSubMenu" style="display: none;">
															<td colspan="2">

																<table
																	class="table table-striped table-bordered table-hover table-full-width"
																	id="sample_1">
																	<thead>
																		<tr>
																			<th>Module Name</th>
																			<th>View</th>
																			<th>Edit</th>
																			<th>Delete</th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td>Account Management</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="accountManagementCheckbox"
																				class="userAccessView view_11" id="11"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="accountManagementCheckbox"
																				class="userAccessEdit edit_11" id="11"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="accountManagementCheckbox"
																				class="userAccessDelete delete_11" id="11">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>User Access Management</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="userAccessManagementCheckbox"
																				class="userAccessView view_12" id="12"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="userAccessManagementCheckbox"
																				class="userAccessEdit edit_12" id="12"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="userAccessManagementCheckbox"
																				class="userAccessDelete delete_12" id="12">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>HRA Type Master</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="hraTypeMasterCheckbox"
																				class="userAccessView view_13" id="13"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="hraTypeMasterCheckbox"
																				class="userAccessEdit edit_13" id="13"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="hraTypeMasterCheckbox"
																				class="userAccessDelete delete_13" id="13">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Question Master</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="questionMasterCheckbox"
																				class="userAccessView view_14" id="14"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="questionMasterCheckbox"
																				class="userAccessEdit edit_14" id="14"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="questionMasterCheckbox"
																				class="userAccessDelete delete_14" id="14">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Parameter Master</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="parameterMasterCheckbox"
																				class="userAccessView view_15" id="15"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="parameterMasterCheckbox"
																				class="userAccessEdit edit_15" id="15"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="parameterMasterCheckbox"
																				class="userAccessDelete delete_15" id="15">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Center Wise Parameter Master</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="centerWiseParameterMasterCheckbox"
																				class="userAccessView view_16" id="16"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="centerWiseParameterMasterCheckbox"
																				class="userAccessEdit edit_16" id="16"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="centerWiseParameterMasterCheckbox"
																				class="userAccessDelete delete_16" id="16">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Unit Master</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="unitMasterCheckbox"
																				class="userAccessView view_17" id="17"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="unitMasterCheckbox"
																				class="userAccessEdit edit_17" id="17"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="unitMasterCheckbox"
																				class="userAccessDelete delete_17" id="17">
																				<label for="delete"> </label></td>
																		</tr>
																		
																		<tr>
																			<td>Package Test Relation</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="packageTestRelationCheckbox"
																				class="userAccessView view_43" id="43"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="packageTestRelationCheckbox"
																				class="userAccessEdit edit_43" id="43"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="packageTestRelationCheckbox"
																				class="userAccessDelete delete_43" id="43">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Reject Master</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="rejectMasterCheckbox"
																				class="userAccessView view_18" id="18"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="rejectMasterCheckbox"
																				class="userAccessEdit edit_18" id="18"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="rejectMasterCheckbox"
																				class="userAccessDelete delete_18" id="18">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Email Template Master</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="emailTemplateMasterCheckbox"
																				class="userAccessView view_19" id="19"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="emailTemplateMasterCheckbox"
																				class="userAccessEdit edit_19" id="19"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="emailTemplateMasterCheckbox"
																				class="userAccessDelete delete_19" id="19">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Sms Template Master</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="smsTemplateMasterCheckbox"
																				class="userAccessView view_20" id="20"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="smsTemplateMasterCheckbox"
																				class="userAccessEdit edit_20" id="20"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="smsTemplateMasterCheckbox"
																				class="userAccessDelete delete_20" id="20">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Action Master</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="actionMasterCheckbox"
																				class="userAccessView view_21" id="21"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="actionMasterCheckbox"
																				class="userAccessEdit edit_21" id="21"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="actionMasterCheckbox"
																				class="userAccessDelete delete_21" id="21">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Sub Action Master</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="subActionMasterCheckbox"
																				class="userAccessView view_22" id="22"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="subActionMasterCheckbox"
																				class="userAccessEdit edit_22" id="22"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="subActionMasterCheckbox"
																				class="userAccessDelete delete_22" id="22">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Visit Type Master</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="visitTypeMasterCheckbox"
																				class="userAccessView view_23" id="23"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="visitTypeMasterCheckbox"
																				class="userAccessEdit edit_23" id="23"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="visitTypeMasterCheckbox"
																				class="userAccessDelete delete_23" id="23">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Client Registration</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="clientRegistrationCheckbox"
																				class="userAccessView view_24" id="24"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="clientRegistrationCheckbox"
																				class="userAccessEdit edit_24" id="24"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="clientRegistrationCheckbox"
																				class="userAccessDelete delete_24" id="24">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Client Integration</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="clientIntegrationCheckbox"
																				class="userAccessView view_30" id="30"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="clientIntegrationCheckbox"
																				class="userAccessEdit edit_30" id="30"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="clientIntegrationCheckbox"
																				class="userAccessDelete delete_30" id="30">
																				<label for="delete"> </label></td>
																		</tr>
																		
																		<tr>
																			<td>Analysis Comment Master</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="clientIntegrationCheckbox"
																				class="userAccessView view_39" id="39"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="clientIntegrationCheckbox"
																				class="userAccessEdit edit_39" id="39"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="clientIntegrationCheckbox"
																				class="userAccessDelete delete_39" id="39">
																				<label for="delete"> </label></td>
																		</tr>

																	</tbody>
																</table>
															</td>
														</tr>
														
														<tr>
															<td>Content Master</td>
															<td colspan="3"><a class="btn btn-xs btn-orange"
																onclick="showContentMasterSubMenu()">View Acces <i
																	class="glyphicon glyphicon-circle-arrow-down"></i></a></td>
														</tr>

														<tr id="contentMasterSubMenu" style="display: none;">
															<td colspan="2">
																<table
																	class="table table-striped table-bordered table-hover table-full-width"
																	id="sample_1">
																	<thead>
																		<tr>
																			<th>Module Name</th>
																			<th>View</th>
																			<th>Edit</th>
																			<th>Delete</th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td>Category Master</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="categoryMasterCheckbox"
																				class="userAccessView view_33" id="33"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="categoryMasterCheckbox"
																				class="userAccessEdit edit_33" id="33"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="categoryMasterCheckbox"
																				class="userAccessDelete delete_33" id="33">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Sub Category Master</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="subCategoryCheckbox"
																				class="userAccessView view_34" id="34"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="subCategoryCheckbox"
																				class="userAccessEdit edit_34" id="34"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="subCategoryCheckbox"
																				class="userAccessDelete delete_34" id="34">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Content Article</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="contentArticleCheckbox"
																				class="userAccessView view_31" id="31"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="contentArticleCheckbox"
																				class="userAccessEdit edit_31" id="31"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="contentArticleCheckbox"
																				class="userAccessDelete delete_31" id="31">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Content Preview</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="contentPreviewCheckbox"
																				class="userAccessView view_35" id="35"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="contentPreviewCheckbox"
																				class="userAccessEdit edit_35" id="35"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="contentPreviewCheckbox"
																				class="userAccessDelete delete_35" id="35">
																				<label for="delete"> </label></td>
																		</tr>

																	</tbody>
																</table>
															</td>
														</tr>
														
														
														<tr>
															<td>Health Report Master</td>
															<td colspan="3"><a class="btn btn-xs btn-orange"
																onclick="showHealthReportMasterSubMenu()">View Acces <i
																	class="glyphicon glyphicon-circle-arrow-down"></i></a></td>
														</tr>

														<tr id="healthReportMasterSubMenu" style="display: none;">
															<td colspan="2">
																<table
																	class="table table-striped table-bordered table-hover table-full-width"
																	id="sample_1">
																	<thead>
																		<tr>
																			<th>Module Name</th>
																			<th>View</th>
																			<th>Edit</th>
																			<th>Delete</th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td>Frequency Master</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="categoryMasterCheckbox"
																				class="userAccessView view_38" id="38"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="categoryMasterCheckbox"
																				class="userAccessEdit edit_38" id="38"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="categoryMasterCheckbox"
																				class="userAccessDelete delete_38" id="38">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Parameter Details</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="subCategoryCheckbox"
																				class="userAccessView view_36" id="36"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="subCategoryCheckbox"
																				class="userAccessEdit edit_36" id="36"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="subCategoryCheckbox"
																				class="userAccessDelete delete_36" id="36">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>HRA Details</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="contentArticleCheckbox"
																				class="userAccessView view_37" id="37"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="contentArticleCheckbox"
																				class="userAccessEdit edit_37" id="37"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="contentArticleCheckbox"
																				class="userAccessDelete delete_37" id="37">
																				<label for="delete"> </label></td>
																		</tr>

																	</tbody>
																</table>
															</td>
														</tr>
														

														<tr>
															<td>Dashboard Master</td>
															<td colspan="3"><a class="btn btn-xs btn-orange"
																onclick="showDashboardMasterSubMenu()">View Acces <i
																	class="glyphicon glyphicon-circle-arrow-down"></i></a></td>
														</tr>

														<tr id="dashboardMasterSubMenu" style="display: none;">
															<td colspan="2">
																<table
																	class="table table-striped table-bordered table-hover table-full-width"
																	id="sample_1">
																	<thead>
																		<tr>
																			<th>Module Name</th>
																			<th>View</th>
																			<th>Edit</th>
																			<th>Delete</th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td>Feed Type Master</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="feedTypeMasterCheckbox"
																				class="userAccessView view_25" id="25"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="feedTypeMasterCheckbox"
																				class="userAccessEdit edit_25" id="25"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="feedTypeMasterCheckbox"
																				class="userAccessDelete delete_25" id="25">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Health Feed Master</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="healthFeedCheckbox"
																				class="userAccessView view_26" id="26"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="healthFeedCheckbox"
																				class="userAccessEdit edit_26" id="26"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="healthFeedCheckbox"
																				class="userAccessDelete delete_26" id="26">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Upload Video Master</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="uploadVideoMasterCheckbox"
																				class="userAccessView view_27" id="27"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="uploadVideoMasterCheckbox"
																				class="userAccessEdit edit_27" id="27"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="uploadVideoMasterCheckbox"
																				class="userAccessDelete delete_27" id="27">
																				<label for="delete"> </label></td>
																		</tr>

																		<tr>
																			<td>Link Master</td>
																			<td><input type="checkbox" value="checkbox1"
																				name="linkMasterCheckbox"
																				class="userAccessView view_28" id="28"> <label
																				for="view"> </label></td>
																			<td><input type="checkbox" value="checkbox2"
																				name="linkMasterCheckbox"
																				class="userAccessEdit edit_28" id="28"> <label
																				for="edit"> </label></td>
																			<td><input type="checkbox" value="checkbox3"
																				name="linkMasterCheckbox"
																				class="userAccessDelete delete_28" id="28">
																				<label for="delete"> </label></td>
																		</tr>

																	</tbody>
																</table>
															</td>
														</tr>

													</tbody>
												</table>
											</div>
										</div>

										<div class="row pull-right userAccessMainDiv"
											style="display: none;">
											<div class="col-md-3">
												<a class="btn btn-wide btn-primary" onclick="assignAccess()">Save
													Access <i class="fa fa-share"></i>
												</a>
											</div>
										</div>

									</div>
									<!-- end: DYNAMIC TABLE -->

								</div>
							</div>

						</div>

					</div>
					<!-- end: DASHBOARD TITLE -->
				</div>
			</div>
		</div>
		<!-- start: FOOTER -->
		<footer>
			<div class="footer-inner">
				<div class="pull-left">
					<span class="text-bold text-uppercase"> INDUS HEALTH PLUS
						&copy; </span> <span>2019-2020</span>
				</div>
				<div class="pull-right">
					<span class="go-top"><i class="ti-angle-up"></i></span>
				</div>
			</div>
		</footer>
		<!-- end: FOOTER -->

	</div>
	<!-- start: MAIN JAVASCRIPTS -->

	<%-- <script
		src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>"></script> --%>

	<script
		src="<c:url value="/indus/resources/vendor/bootstrap/js/bootstrap.min.js"/>"></script>

	<script
		src="<c:url value="/indus/resources/vendor/modernizr/modernizr.js"/>"></script>

	<script
		src="<c:url value="/indus/resources/vendor/jquery-cookie/jquery.cookie.js"/>"></script>

	<script
		src="<c:url value="/indus/resources/vendor/perfect-scrollbar/perfect-scrollbar.min.js"/>"></script>

	<script
		src="<c:url value="/indus/resources/vendor/switchery/switchery.min.js"/>"></script>

	<script
		src="<c:url value="/indus/resources/vendor/jquery.sparkline/jquery.sparkline.min.js"/>"></script>
	<!-- end: MAIN JAVASCRIPTS -->

	<script src="<c:url value="/indus/resources/alertify/alertify.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/custom_js/indus_header.js"/>"></script>
	<script src="<c:url value="/indus/resources/custom_js/index.js"/>"></script>

	<!-- start: CLIP-TWO JAVASCRIPTS -->

	<script src="<c:url value="/indus/resources/assets/js/main.js"/>"></script>

	<script
		src="<c:url value="/indus/resources/vendor/jquery-ui/jquery-ui-1.10.1.custom.min.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/custom_js/indus_user_access_management.js"/>"></script>

	<script
		src="<c:url value="/indus/resources/assets/js/form-elements.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/maskedinput/jquery.maskedinput.min.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/bootstrap-touchspin/jquery.bootstrap-touchspin.min.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/autosize/autosize.min.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/selectFx/classie.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/selectFx/selectFx.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/select2/select2.min.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/bootstrap-datepicker/bootstrap-datepicker.min.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/bootstrap-timepicker/bootstrap-timepicker.min.js"/>"></script>

	<script>
		jQuery(document).ready(function() {
			Main.init();
			FormElements.init();
		});
	</script>

	<!-- end: JavaScript Event Handlers for this page -->
	<!-- end: CLIP-TWO JAVASCRIPTS -->
</body>
<jsp:include page="indus_popups.jsp"></jsp:include>
</html>
