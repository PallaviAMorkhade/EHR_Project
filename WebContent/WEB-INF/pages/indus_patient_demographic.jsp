<%@page import="com.hms.indus.bo.ClientMaster"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- <!DOCTYPE html>
Template Name: Clip-Two - Responsive Admin Template build with Twitter Bootstrap 3.x | Author: ClipTheme
[if IE 8]><html class="ie8" lang="en"><![endif]
[if IE 9]><html class="ie9" lang="en"><![endif]
[if !IE]><!
<html lang="en">
<![endif]
start: HEAD
<head>
<title>Indus|Demographic</title>
start: META
[if IE]><meta http-equiv='X-UA-Compatible' content="IE=edge,IE=9,IE=8,chrome=1" /><![endif]
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="" name="description" />
<meta content="" name="author" /> -->
<!-- end: META -->
<!-- start: GOOGLE FONTS -->
<!-- <link
	href="http://fonts.googleapis.com/css?family=Lato:300,400,400italic,600,700|Raleway:300,400,500,600,700|Crete+Round:400italic"
	rel="stylesheet" type="text/css" /> -->
<!-- end: GOOGLE FONTS -->
<!-- start: MAIN CSS -->
<%-- <link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/vendor/bootstrap/css/bootstrap.min.css"/>">
<link rel="stylesheet"
	href="<c:url value="/indus/resources/vendor/fontawesome/css/font-awesome.min.css"/>">
<link rel="stylesheet"
	href="<c:url value="/indus/resources/vendor/themify-icons/themify-icons.min.css"/>">
<link
	href="<c:url value="/indus/resources/vendor/animate.css/animate.min.css"/>"
	rel="stylesheet" media="screen">
<link
	href="<c:url value="/indus/resources/vendor/perfect-scrollbar/perfect-scrollbar.min.css"/>"
	rel="stylesheet" media="screen"> --%>
<!-- end: MAIN CSS -->

<!-- start: CLIP-TWO CSS -->
<%-- <link rel="stylesheet"
	href="<c:url value="/indus/resources/assets/css/styles.css"/>">
<link rel="stylesheet"
	href="<c:url value="/indus/resources/assets/css/plugins.css"/>">
<link rel="stylesheet"
	href="<c:url value="/indus/resources/assets/css/themes/theme-4.css"/>" /> --%>
<!-- end: CLIP-TWO CSS -->
<!-- start: CSS REQUIRED FOR THIS PAGE ONLY -->
<%-- <link href="<c:url value="/indus/resources/vendor/bootstrap-fileinput/jasny-bootstrap.min.css"/>"
	rel="stylesheet" media="screen"> --%>
<!-- end: CSS REQUIRED FOR THIS PAGE ONLY -->

		<!-- start: MAIN JAVASCRIPTS -->
		<%-- <script
		src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>"></script>
	
	<script
		src="<c:url value="/indus/resources/vendor/bootstrap/js/bootstrap.min.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/modernizr/modernizr.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/jquery-cookie/jquery.cookie.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/perfect-scrollbar/perfect-scrollbar.min.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/switchery/switchery.min.js"/>"></script> --%>

	<!-- end: MAIN JAVASCRIPTS -->
<!-- </head> -->
<!-- end: HEAD -->

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
<%
	ClientMaster clientMaster=(ClientMaster)request.getAttribute("client");
	String clientFirstName="";
	if((String)session.getAttribute("userFirstName")!=null)
	{
		clientFirstName=(String)session.getAttribute("userFirstName");
	}
	
	if (clientMaster.getFirstName() == null) {
		clientMaster.setFirstName("");
	}
	if (clientMaster.getLastName() == null) {
		clientMaster.setLastName("");
	}
	
%>
<body>
	<div id="app">
		<!-- sidebar -->
			<%-- <jsp:include page="indus_header.jsp"></jsp:include> --%>
		<!-- / sidebar -->
		<div class="app-content">
			<!-- start: TOP NAVBAR -->
				<%-- <jsp:include page="indus_left_menu.jsp"></jsp:include> --%>
			<!-- end: TOP NAVBAR -->
			<!-- <div class="main-content"> -->
				<div class="wrap-content container" id="container">
					<!-- start: DASHBOARD TITLE -->
					
					<!-- end: DASHBOARD TITLE -->
					<!-- start: USER PROFILE -->
					<div class="container-fluid container-fullw bg-white">
						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-white no-radius">
									<div class="panel-body no-padding">
										<div class="padding-10"> 
											<img width="50" height="50" 
											<%
												if(clientMaster.getPhotoUrl()!=null && clientMaster.getPhotoUrl()!="")
												{
											%>
													src="readImage?url=<%=clientMaster.getPhotoUrl() %>"
													
												<%
												}
												else
												{
													%>
													src="resources/assets/images/default-user.png"
													<%
												}
													%>	
												class="img-circle pull-left" alt="">
											<h4 class="no-margin inline-block padding-5">
												<%=clientMaster.getFirstName() +" "+  clientMaster.getLastName()%>
												<span class="block text-small text-left"><center><b>-</b></center></span>
											</h4>
											
											<%
		HttpSession httpSession=request.getSession();
		if((String)httpSession.getAttribute("loginUserType")!=null){
		
		}
		else{
	%>
											
											<div class="col-md-9 col-sm-6 col-xs-12 pull-right interactionTabHide timeline" style="display:none;">
												<div id="wizard" class="swMain">
													<ul class="anchor">
														<li><a id="step-1" href="#step-1" class="selected" isdone="1"
															rel="1">
																<div class="stepNumber">01</div> <span class="stepDesc"><small>
																		Demographics</small></span>
														</a></li>
														<li><a id="step-2" href="#step-2" class="" isdone="0"
															rel="2">
																<div class="stepNumber">02</div> <span class="stepDesc">
																	<small> Tests Done </small>
															</span>
														</a></li>
														<li><a id="step-3" href="#step-3" class="" isdone="0"
															rel="3">
																<div class="stepNumber">03</div> <span class="stepDesc">
																	<small>Reports Verified</small>
															</span>
														</a></li>
														<li><a id="step-4" href="#step-4" class="" isdone="0"
															rel="4">
																<div class="stepNumber">04</div> <span class="stepDesc">
																	<small> Interaction </small>
															</span>
														</a></li>
														<li><a id="step-5" href="#step-5" class="" isdone="0"
															rel="5">
																<div class="stepNumber">05</div> <span class="stepDesc">
																	<small> Complete </small>
															</span>
														</a></li>
													</ul>
												</div>
											</div>
							<%
		}
	%>				
											
										</div>
										<div class="clearfix padding-5 space5">
											<!-- <div class="col-xs-1 text-center no-padding">
												<div class="border-right border-dark">
													<a class="text-dark" href="#"> <i
														class="fa fa-heart-o text-red"></i> 0
													</a>
												</div>
											</div>
											<div class="col-xs-1 text-center no-padding">
												<div class="border-right border-dark">
													<a class="text-dark" href="#"> <i
														class="fa fa-bookmark-o text-green"></i> <span id="emailCount">0</span>
													</a>
												</div>
											</div>

											<div class="col-xs-1 text-center no-padding">
												<a class="text-dark" href="#"><i
													class="fa fa-comment-o text-azure"></i> <span id="smsCount">0</span></a>
											</div> -->
											<div class="col-xs-3 no-padding">
												Package Name: <span class="text-dark packageNameSpan"></span>
											</div>
											<div class="col-xs-3 no-padding hidden">
												<span class="text-dark centerNameSpan"></span>
											</div>
											<div class="col-xs-2 no-padding">
												Check Up Date: <span class="text-dark lastCheckUpDate"></span>
											</div>

										<div class="col-md-2 col-sm-6 col-xs-12">
											<select class="form-control center"
												title="" data-toggle="tooltip"
												id="visitSelectOnMyReportPage"
												onchange="changeVisitSelectList(this)" style="height: 25px; padding: 3px; margin-bottom: -7px;">
											</select>
										</div>
										<div class="col-md-2 col-sm-6 col-xs-12">
											<input id="showVisitDateOnMyReportPage" style="height: 25px; padding: 3px; margin-bottom: -7px;"
												class="form-control center" type="text" disabled>
										</div>
										<div class="col-md-2 col-sm-6 col-xs-12">
											<select class="form-control center" style="height: 25px; padding: 3px; margin-bottom: -7px;"
												id="visitTypeMasterSelectOnMyReportPage" disabled>
											</select>
										</div>

										<div id="healthReportBtnDiv" class="col-md-1 col-sm-6 col-xs-12" style="display:none;">
											<a id="healthReportBtn" class="btn btn-primary btn-sm"
												target="_blank" href=""> <span>Report</span>
											</a>
										</div>

									</div>
										<div class="tabbable no-margin no-padding">
											<ul class="nav nav-tabs" id="myTab">
												<li onclick="loadDemoghraphicPage()" class="active padding-top-5 padding-left-5 demoghraphicTab menu_3" style="display:none;"><a
													data-toggle="tab" href="#panel_overview"> Demographics
												</a></li>
												<li onclick="loadReportPage()" class="padding-top-5 myReportTab menu_4" style="display:none;"><a data-toggle="tab"
													href="#users_following"> Reports </a></li>
												<li onclick="loadHRAPage()" class="padding-top-5 hraTab menu_6" style="display:none;" ><a data-toggle="tab"
													href="#hra"> HRA </a></li>
												<li onclick="loadVerificationPage()" class="interactionTabHide padding-top-5 verificationTab menu_5" style="display:none;"><a data-toggle="tab"
													href="#verification"> Verification </a></li>
												<li onclick="loadOtherDetailsPage()" class="padding-top-5 menu_7" style="display:none;"><a data-toggle="tab"
													href="#otherDetails"> Self Upload </a></li>
												<li onclick="loadReminderPage()" class="padding-top-5 menu_8" style="display:none;"><a data-toggle="tab"
													href="#reminders"> Reminders </a></li>
													
												<li onclick="loadFollowUpPage()" class="padding-top-5 interactionTabShow" style="display:none;"><a data-toggle="tab"
													href="#followUpRecord"> Follow Up Record </a></li>
												<li onclick="loadContactHubPage()" class="padding-top-5 interactionTabShow" style="display:none;"><a data-toggle="tab"
													href="#contactHub"> Contact Hub </a></li>
												<li onclick="loadDataEntryPage()" class="padding-top-5 interactionTabShow" style="display:none;"><a data-toggle="tab"
													href="#dataEntry"> Data Entry </a></li>
												
												<!-- <li class="padding-top-5"><a data-toggle="tab"
													href="#users_following"> Call Log </a></li> -->
											</ul>
											<div class="tab-content">
												<div id="panel_overview" class="tab-pane fade in active">
													<div class="row">
														<div class="col-sm-5 col-md-4">
															<div class="user-left">
																<div class="center">
																	<h4><%=clientMaster.getFirstName() +" "+  clientMaster.getLastName()%></h4>
																	<div class="fileinput fileinput-new"
																		data-provides="fileinput">
																		<div class="user-image">
																			<div class="fileinput-new thumbnail">
																				<img id="profilePictureImg"
																				<%
																					if(clientMaster.getPhotoUrl()!=null && clientMaster.getPhotoUrl()!="")
																					{
																				%>
																						src="readImage?url=<%=clientMaster.getPhotoUrl() %>"
																						
																					<%
																					}
																					else
																					{
																						%>
																						src="resources/assets/images/default-user.png"
																						<%
																					}
																						%>	
																				 alt="">
																			</div>
																			<!-- <div
																				class="fileinput-preview fileinput-exists thumbnail"></div> -->
																			<%-- <div class="user-image-buttons">
																				<span class="btn btn-azure btn-file btn-sm"><span class="fileinput-new"><i
																						class="fa fa-pencil"></i></span>
																						<!-- <span class="fileinput-exists"><i class="fa fa-pencil"></i></span> --> 
																					<form>
																						<input id="changeProfilePicture" type="file" name="file" onchange="changeProfile(event)" />
																					</form>
																				</span> <a onclick="deleteProfilePicture()"
																					class="btn fileinput-exists btn-red btn-sm"
																					data-dismiss="fileinput"> <i
																					class="fa fa-times"></i>
																				</a>
																			</div> --%>
																		</div>
																	</div>
																	<hr class="profile_hr">
																	<div class="social-icons block">
																		<ul>
																			<li data-placement="top"
																				data-original-title="Twitter"
																				class="social-twitter tooltips"><a
																				href="http://www.twitter.com" target="_blank">
																					Twitter </a></li>
																			<li data-placement="top"
																				data-original-title="Facebook"
																				class="social-facebook tooltips"><a
																				href="http://facebook.com" target="_blank">
																					Facebook </a></li>
																			<li data-placement="top" data-original-title="Google"
																				class="social-google tooltips"><a
																				href="http://google.com" target="_blank">
																					Google+ </a></li>
																			<li data-placement="top"
																				data-original-title="LinkedIn"
																				class="social-linkedin tooltips"><a
																				href="http://linkedin.com" target="_blank">
																					LinkedIn </a></li>
																			<li data-placement="top" data-original-title="Github"
																				class="social-github tooltips"><a href="#"
																				target="_blank"> Github </a></li>
																		</ul>
																	</div>
																	<hr class="profile_hr">
																</div>
																<table class="table table-condensed">
																	<tbody>
																		<tr>
																			<td>First Name</td>
																			<td id="displayFirstNameTd">
																			<%
																			if(clientMaster.getFirstName()!=null && clientMaster.getFirstName()!="")
																				out.print(clientMaster.getFirstName());
																			else
																				out.print("-");
																				%>
																			</td>
																			<td id="editFirstNameTd" style="display:none;"><input id="editFirstNameInput" maxlength="110" type="text" value="<%
																			if(clientMaster.getFirstName()!=null && clientMaster.getFirstName()!="")
																				out.print(clientMaster.getFirstName());
																			else
																				out.print("-");
																				%>" style="width: 100%;"></td>
																			<td id="editFirstName"><a 
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>Middle Name</td>
																			
																			<td id="displayMiddleNameTd">
																			<%
																			if(clientMaster.getMiddleName()!=null && clientMaster.getMiddleName()!="")
																				out.print(clientMaster.getMiddleName());
																			else
																				out.print("-");
																				%>
																			
																			</td>
																			<td id="editMiddleNameTd" style="display:none;"><input id="editMiddleNameInput" maxlength="55" type="text" value="<%
																			if(clientMaster.getMiddleName()!=null && clientMaster.getMiddleName()!="")
																				out.print(clientMaster.getMiddleName());
																			else
																				out.print("-");
																				%>" style="width: 100%;"></td>
																			<td id="editMiddleName"><a
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>Last Name</td>
																			<td id="displayLastNameTd">
																			<%
																				if(clientMaster.getLastName()!=null && clientMaster.getLastName()!="")
																					out.print(clientMaster.getLastName());
																				else
																					out.print("-");
																			%>
																			</td>
																			<td id="editLastNameTd" style="display:none;"><input id="editLastNameInput" maxlength="55" type="text" value="<%
																				if(clientMaster.getLastName()!=null && clientMaster.getLastName()!="")
																					out.print(clientMaster.getLastName());
																				else
																					out.print("-");
																			%>" style="width: 100%;"></td>
																			<td id="editLastName"><a
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>DOB/Age</td>
																			<td id="displayDOBTd">
																			<%
																				if(clientMaster.getClientDOB()!=null)
																				{	
																					if(clientMaster.getClientAge()!=null && !clientMaster.getClientAge().equals(0))
																					{
																						out.print(clientMaster.getClientDOB().split(" ")[0]+" / "+clientMaster.getClientAge());
																					}
																					else
																					{
																						out.print(clientMaster.getClientDOB().split(" ")[0]+" / -");
																					}
																				}
																				else
																				{
																						out.print(" / -");
																				}
																			%>
																			</td>
																			<td id="editDOBTd" class="input-group input-daterange disableDate" style="display:none;"><input value="<%=clientMaster.getRemark()%>" id="birthDate" type="text" class="form-control" style="width: 100%;"/></td>
																			<td id="editDOB"><a class="show-tab"><i	class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		
																		<tr>
																			<td>Gender</td>
																			<td id="displayGenderTd">
																			<%
																			if(clientMaster.getGender()!=null && clientMaster.getGender()!="")
																				out.print(clientMaster.getGender());
																			else
																				out.print("-");
																			%>
																			</td>
																			<td id="editGenderTd" style="display:none;"><select id="genderSelect" onchange="genderChange()"><option value="M">M</option><option value="F">F</option></select>
																			<span id="currentGender" class="hidden"><%if(clientMaster.getGender()!=null && clientMaster.getGender()!="")
																						out.print(clientMaster.getGender());
																				else
																						out.print("-");%></span></td>
																			<td id="editGender"><a
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		
																		<tr style="display:none;">
																			<td>Height</td>
																			<td id="displayHeightTd">
																			<%
																			if(clientMaster.getClientHeight()!=null && clientMaster.getClientHeight()!="" && !clientMaster.getClientHeight().equals("NULL") && !clientMaster.getClientHeight().equals("-")){
																				out.print(clientMaster.getClientHeight());
																			if(clientMaster.getClientHeight().contains("Cm")){
																				//out.print(clientMaster.getClientHeight().split(" ")[0]);
																				%>
																				<span class="hidden" id="heightInCm"><%out.print(clientMaster.getClientHeight().split(" ")[0]);%></span>
																				<%
																			}
																			else{
																				%>
																				<span class="hidden" id="heightInFoot"><%out.print(clientMaster.getClientHeight().split(" ")[0]);%></span>
																				<span class="hidden" id="heightInches"><%out.print(clientMaster.getClientHeight().split(" ")[2]);%></span>
																				<%
																			}
																			
																			}
																			else
																				out.print("-");
																			%>
																			</td>
																			<td id="editHeightTd" style="display:none;width: 65%;"><select id="heightUnit" onchange="unitChanged()"><option value="cm">Cm</option><option value="foot">Feet</option></select> <label class="heightInCm" style="width:70%;"><input class="heightInCm" id="editHeightInput" type="text" value="" style="width: 60%;"> Cm </label>
																			<label class="heightInFoot" style="display:none;width: 30%;"> <input class="heightInFoot" id="editHeightFoot" type="text" value="" style="width: 60%;"> ft. </label> <label class="heightInInches" style="display:none;width: 40%;"><input class="heightInInches" id="editHeightInches" type="text" value="" style="width: 42%;"> inches </label></td>																			
																			<td id="editHeight"><a
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		
																		<tr style="display:none;">
																			<td>Weight</td>
																			<td id="displayWeightTd">
																			<%
																			if(clientMaster.getClientWeight()!=null && clientMaster.getClientWeight()!="" && !clientMaster.getClientWeight().equals("NULL") && !clientMaster.getClientWeight().equals("-")){
																				out.print(clientMaster.getClientWeight());
																					%>
																					<span class="hidden" id="weightUnitHidden"><%out.print(clientMaster.getClientWeight().split(" ")[1]);%></span>
																					<span class="hidden" id="weightValueHidden"><%out.print(clientMaster.getClientWeight().split(" ")[0]);%></span>
																					<%
																			}
																			else
																				out.print("-");
																			%>
																			</td>
																			<td id="editWeightTd" style="display:none;"><select id="weightUnit"><option value="kg">Kg</option><option value="pound">pound</option></select> <input id="editWeightInput" type="text" value="" style="width: 35%;"></td>
																			<td id="editWeight"><a
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		
																		
																		
																		<tr>
																			<td>Blood Group</td>
																			<td id="displayBloodGroupTd">
																			<%
																				if(clientMaster.getBloodGroup()!=null && clientMaster.getBloodGroup()!="" && !clientMaster.getBloodGroup().equals("null") && !clientMaster.getBloodGroup().equals("NULL")){
																					out.print(clientMaster.getBloodGroup());
																					
																					%>
																					<span class="hidden" id="bloodGroupHidden"><%out.print(clientMaster.getBloodGroup());%></span>
																					<%
																				}
																				else
																					out.print(" ");
																			%>
																			</td>
																			<td id="editBloodGroupTd" style="display:none;"><select id="bloodGroupList" style="width: 100%;">
																			<option value=" ">Select Blood Group</option>
																			<option value="O Positive">O Positive</option><option value="O Negative">O Negative</option>
																			<option value="A Positive">A Positive</option><option value="A Negative">A Negative</option>
																			<option value="B Positive">B Positive</option><option value="B Negative">B Negative</option>
																			<option value="AB Positive">AB Positive</option><option value="AB Negative">AB Negative</option>
																			</select><%-- <input id="editBloodGroupInput" type="text" value="<%=clientMaster.getBloodGroup()%>" style="width: 100%;"> --%></td>
																			<td id="editBloodGroup"><a
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<%-- <tr>
																			<td>EHR ID</td>
																			<td>
																			<%
																			//HttpSession httpSession=request.getSession();
																			if((String)httpSession.getAttribute("loginUserType")==null) {
																				if(clientMaster.getClientId()!=null)
																					out.print(clientMaster.getClientId());
																				}
																			%>
																			</td>
																			<td><a
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		
																		<tr>
																			<td>Member ID</td>
																			<td>
																			<%
																			if((String)httpSession.getAttribute("loginUserType")==null) {
																				if(clientMaster.getMemberId()!=null)
																					out.print(clientMaster.getMemberId());
																				}
																			%>
																			</td>
																			<td><a
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr> --%>
																	</tbody>
																</table>

															</div>
														</div>
														
														<div class="col-sm-5 col-md-4">		
														<div class="user-left">													
															<table class="table">
																<thead>
																	<tr>
																		<th colspan="3">Contact Information</th>
																	</tr>
																</thead>
																<tbody>
																	<tr>
																		<td style="width: 35%;">Address Line 1</td>
																		<td id="displayAddress1Td">
																		<%
																			if(clientMaster.getAddressLine1()!=null && clientMaster.getAddressLine1()!=null)
																				out.print(clientMaster.getAddressLine1());
																			else
																				out.print("-");
																		%>
																		</td>
																		<td id="editAddress1Td" style="display:none;"><input id="editAddress1Input" maxlength="200" type="text" value="<%
																			if(clientMaster.getAddressLine1()!=null && clientMaster.getAddressLine1()!=null)
																				out.print(clientMaster.getAddressLine1());
																			else
																				out.print("-");
																		%>" style="width: 100%;"></td>
																		<td id="editAddress1"><a
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td style="width: 35%;">Address Line 2</td>
																		<td id="displayAddress2Td">
																		<%
																			if(clientMaster.getAddressLine2()!=null && clientMaster.getAddressLine2()!=null)
																				out.print(clientMaster.getAddressLine2());
																			else
																				out.print("-");
																		%>
																		</td>
																		<td id="editAddress2Td" style="display:none;"><input id="editAddress2Input" maxlength="200" type="text" value="<%
																			if(clientMaster.getAddressLine2()!=null && clientMaster.getAddressLine2()!=null)
																				out.print(clientMaster.getAddressLine2());
																			else
																				out.print("-");
																		%>" style="width: 100%;"></td>
																		<td id="editAddress2"><a
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td style="width: 35%;">Address Line 3</td>
																		<td id="displayAddress3Td">
																		<%
																			if(clientMaster.getAddressLine3()!=null && clientMaster.getAddressLine3()!="")
																				out.print(clientMaster.getAddressLine3());
																			else
																				out.print("-");
																		%>
																		</td>
																		<td id="editAddress3Td" style="display:none;"><input id="editAddress3Input" maxlength="55" type="text" value="<%
																			if(clientMaster.getAddressLine3()!=null && clientMaster.getAddressLine3()!="")
																				out.print(clientMaster.getAddressLine3());
																			else
																				out.print("-");
																		%>" style="width: 100%;"></td>
																		<td id="editAddress3"><a
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>Country</td>
																		<td id="displayCountryNameTd">
																		<%if(clientMaster.getCountryMaster()!=null)//.getCountryName()
																				out.print(clientMaster.getCountryMaster().getCountryName());																		%>
																		</td>
																		<td id="currentCountryId" class="hidden"><%if(clientMaster.getCountryMaster()!=null)//.getCountryName()
																				out.print(clientMaster.getCountryMaster().getCountryId());%></td>
																		<td id="editCountryNameTd" style="display:none;"><select id="countryList" style="width: 100%;"></select></td>
																		<td id="editCountryName"><a
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>State</td>
																		<td id="displayStateNameTd"><%if(clientMaster.getStateMaster()!=null)//.getStateName()
																				out.print(clientMaster.getStateMaster().getStateName());%>
																		</td>
																		<td id="currentStateId" class="hidden"><%if(clientMaster.getStateMaster()!=null)//.getStateName()
																				out.print(clientMaster.getStateMaster().getStateId());%></td>
																		<td id="editStateNameTd" style="display:none;"><select id="stateList" style="width: 100%;"></select></td>
																		<td id="editStateName"><a
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>City</td>
																		<td id="displayCityNameTd">
																		<%if(clientMaster.getCityMaster()!=null)//.getCityName()
																				out.print(clientMaster.getCityMaster().getCityName());%>
																		</td>
																		<td id="currentCityId" class="hidden"><%if(clientMaster.getCityMaster()!=null)//.getCityName()
																			out.print(clientMaster.getCityMaster().getCityId());%></td>
																		<td id="editCityNameTd" style="display:none;"><select id="cityList" style="width: 100%;"></select></td>
																		<td id="editCityName"><a
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>Pincode</td>
																		<td id="displayPincodeTd">
																		<%if(clientMaster.getPinCode()!=0)
																				out.print(clientMaster.getPinCode());%>
																		</td>
																		<td id="editPincodeTd" style="display:none;"><input id="editPincodeInput" type="text" value="<%=clientMaster.getPinCode()%>" style="width: 100%;"></td>
																		<td id="editPincode"><a
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																<!-- </tbody>
															</table>
															
															<table class="table table-condensed">

																	<tbody> -->
																		<tr>
																			<td>Mobile</td>
																			<td id="displayMobNoTd">
																			<%if(clientMaster.getMobNo()!=null && !clientMaster.getMobNo().equals("null"))
																					out.print(clientMaster.getMobNo());%>
																			</td>
																			<td id="editMobNoTd" style="display:none;"><input id="editMobNoInput" type="text" value="<%=clientMaster.getMobNo()%>" style="width: 100%;"></td>
																			<td id="editMobNo"><a
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>Land Line</td>
																			<td id="displayLandlineNoTd">
																			<%
																				if(clientMaster.getLandlineNo()!=null && !clientMaster.getLandlineNo().equals("null") && !clientMaster.getLandlineNo().equals("NULL"))
																					out.print(clientMaster.getLandlineNo());
																			%>
																			</td>
																			<td id="editLandlineNoTd" style="display:none;"><input id="editLandlineNoInput" type="text" value="<%=clientMaster.getLandlineNo()%>" style="width: 100%;"></td>
																			<td id="editLandlineNo"><a
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>Email</td>
																			<td id="displayEmailIdTd"><a>
																			<%
																				if(clientMaster.getEmailId()!=null && !clientMaster.getEmailId().equals("null") && !clientMaster.getEmailId().equals("NULL"))
																					out.print(clientMaster.getEmailId());
																			%>
																			</a></td>
																			<td id="editEmailIdTd" style="display:none;"><input id="editEmailIdInput" type="text" value="<%=clientMaster.getEmailId()%>" style="width: 100%;"></td>
																			<td id="editEmailId"><a href="#panel_edit_account"
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<%-- <tr>
																			<td>User Name</td>
																			<td><a>
																			<%
																				if(clientMaster.getUserId()!=null)
																					out.print(clientMaster.getUserId());
																			%>
																			</a></td>
																			<td><a
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr> --%>
																		
																		
																	</tbody>
																</table>
															</div>
														</div>

														<div class="col-sm-5 col-md-4">
														 <!-- <div class="row space20" id="updateProfileBtnRefreshDiv">
																<button type="button" id="updateUserProfile"
																	class="btn btn-wide btn-o btn-success">
																	Update</button>
																<button type="button" onclick="refreshDemoghraphic()"
																	class="btn btn-wide btn-o btn-info">Refresh</button>
															</div> -->
															<div class="panel" id="activities">
																
																<!-- <div class="list-group-item list-group-item-success">
																	<b>Well done! </b> Undertaking Uploaded
																	<button type="button" class="btn btn-o btn-success">
																		View</button>
																</div> -->
																
																<table class="table" style="display: none">
																<thead>
																	<tr>
																		<th colspan="3">Nominee Details</th>
																	</tr>
																</thead>
																<tbody>
																	<tr>
																		<td>Relation with Nominee</td>
																		<td id="displayNomineeRelationTd"><%
																			if(clientMaster.getNomineeRelation()!=null && clientMaster.getNomineeRelation()!="" && !clientMaster.getNomineeRelation().equals("NULL"))
																				out.print(clientMaster.getNomineeRelation());
																			else
																				out.print("-");
																				%></td>
																		<td id="editNomineeRelationTd" style="display:none;"><input id="editNomineeRelationInput" maxlength="45" type="text" value="<%
																			if(clientMaster.getNomineeRelation()!=null && clientMaster.getNomineeRelation()!="" && !clientMaster.getNomineeRelation().equals("NULL"))
																				out.print(clientMaster.getNomineeRelation());
																			else
																				out.print("");
																				%>" style="width: 100%;"></td>
																		<td id="editNomineeRelation"><a class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																			<td>First Name</td>
																			<td id="displayNomineeFirstNameTd">
																			<%
																			if(clientMaster.getNomineeFirstName()!=null && clientMaster.getNomineeFirstName()!="" && !clientMaster.getNomineeFirstName().equals("NULL"))
																				out.print(clientMaster.getNomineeFirstName());
																			else
																				out.print("-");
																				%>
																			
																			</td>
																			<td id="editNomineeFirstNameTd" style="display:none;"><input id="editNomineeFirstNameInput" maxlength="45" type="text" value="<%
																			if(clientMaster.getNomineeFirstName()!=null && clientMaster.getNomineeFirstName()!="" && !clientMaster.getNomineeFirstName().equals("NULL"))
																				out.print(clientMaster.getNomineeFirstName());
																			else
																				out.print("");
																				%>" style="width: 100%;"></td>
																			<td id="editNomineeFirstName"><a 
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>Middle Name</td>
																			
																			<td id="displayNomineeMiddleNameTd">
																			<%
																			if(clientMaster.getNomineeMiddleName()!=null && clientMaster.getNomineeMiddleName()!="" && !clientMaster.getNomineeMiddleName().equals("NULL"))
																				out.print(clientMaster.getNomineeMiddleName());
																			else
																				out.print("-");
																				%>
																			
																			</td>
																			<td id="editNomineeMiddleNameTd" style="display:none;"><input id="editNomineeMiddleNameInput" maxlength="45" type="text" value="<%
																			if(clientMaster.getNomineeMiddleName()!=null && clientMaster.getNomineeMiddleName()!="" && !clientMaster.getNomineeMiddleName().equals("NULL"))
																				out.print(clientMaster.getNomineeMiddleName());
																			else
																				out.print("");
																				%>" style="width: 100%;"></td>
																			<td id="editNomineeMiddleName"><a
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>Last Name</td>
																			<td id="displayNomineeLastNameTd">
																			<%
																				if(clientMaster.getNomineeLastName()!=null && clientMaster.getNomineeLastName()!="" && !clientMaster.getNomineeLastName().equals("NULL"))
																					out.print(clientMaster.getNomineeLastName());
																				else
																					out.print("-");
																			%>
																			</td>
																			<td id="editNomineeLastNameTd" style="display:none;"><input id="editNomineeLastNameInput" maxlength="45" type="text" value="<%
																				if(clientMaster.getNomineeLastName()!=null && clientMaster.getNomineeLastName()!="" && !clientMaster.getNomineeLastName().equals("NULL"))
																					out.print(clientMaster.getNomineeLastName());
																				else
																					out.print("");
																			%>" style="width: 100%;"></td>
																			<td id="editNomineeLastName"><a
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>DOB/Age</td>
																			<td id="displayNomineeDOBTd">
																			<%
																				if(clientMaster.getNomineeDOB()!=null && !clientMaster.getNomineeDOB().equals("NULL"))
																				{	
																					if(clientMaster.getNomineeAge()!=null && !clientMaster.getNomineeAge().equals(0))
																					{
																						out.print(clientMaster.getNomineeDOB()+" / "+clientMaster.getNomineeAge());
																					}
																					else
																					{
																						out.print(clientMaster.getNomineeDOB()+" / -");
																					}
																				}
																				else
																				{
																					if(clientMaster.getNomineeAge()!=null && clientMaster.getNomineeAge()!=0)
																					{
																						out.print(" / "+clientMaster.getNomineeAge());
																					}
																					else
																					{
																						out.print(" / ");
																					}
																				}
																			%>
																			</td>
																			<td id="editNomineeDOBTd" class="input-group input-daterange disableDate" style="display:none;">
																			<input value="<%if(clientMaster.getNomineeDOB()!=null && clientMaster.getNomineeDOB()!="")
																					out.print(clientMaster.getAddAt());
																				else
																					out.print("");
																			%>" id="nomineeBirthDate" type="text" class="form-control" style="width: 100%;"/></td>
																			<td id="editNomineeDOB"><a class="show-tab"><i	class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																</tbody>
															</table>
															
															
															<table class="table">
																<thead>
																	<tr>
																		<th colspan="3">User Details</th>
																	</tr>
																</thead>
																<tbody>
																
																<tr>
																			<td>User Name</td>
																			<td>
																			<%
																				if(clientMaster.getUserId()!=null)
																					out.print(clientMaster.getUserId());
																			%>
																			</td>
																			<!-- <td><a
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td> -->
																		</tr>
																		 <%if((String)httpSession.getAttribute("loginUserType")==null) {%>
																			
																			<tr>
																			<td>EHR ID</td>
																			<td>
																			<%
																			//HttpSession httpSession=request.getSession();
																			if((String)httpSession.getAttribute("loginUserType")==null) {
																				if(clientMaster.getClientId()!=null)
																					out.print(clientMaster.getClientId());
																				}
																			%>
																			</td>
																			<!-- <td><a
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td> -->
																		</tr>
																		
																		<tr>
																			<td>Member ID</td>
																			<td>
																			<%
																			if((String)httpSession.getAttribute("loginUserType")==null) {
																				if(clientMaster.getMemberId()!=null)
																					out.print(clientMaster.getMemberId());
																				}
																			%>
																			</td>
																			<!-- <td><a
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td> -->
																		</tr>
																		
																		<tr>
																			<td>Relation</td>
																			<td>
																			<%
																			if((String)httpSession.getAttribute("loginUserType")==null) {
																				if(clientMaster.getMbRelation()!=null)
																					out.print(clientMaster.getMbRelation());
																				}
																			%>
																			</td>
																			<!-- <td><a
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td> -->
																		</tr>
																		
																		
																		<tr>
																			<td>SMS On</td>
																			<td id="displaySmsIsOnTd">
																			<%
																			if(clientMaster.getContactFlag()!=null && clientMaster.getContactFlag()!="")
																				out.print(clientMaster.getContactFlag());
																			else
																				out.print("-");
																			%>
																			</td>
																			<td id="editSmsIsOnTd" style="display:none;"><select id="smsIsOnSelect" onchange="smsIsOnChange()"><option value="N">N</option><option value="Y">Y</option></select>
																			<span id="currentSmsIsOn" class="hidden"><%if(clientMaster.getContactFlag()!=null && clientMaster.getContactFlag()!="")
																						out.print(clientMaster.getContactFlag());
																				else
																						out.print("-");%></span></td>
																			<td id="editSmsIsOn"><a
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		
																			
																		<%}%>
																
																
																
																</tbody>
															</table>
																
																
															</div>
														</div>
													</div>
												</div>
												<div id="users_following" class="tab-pane fade padding-bottom-5">
													<div class="panel-scroll height-200 ps-container">
														<table class="table no-margin">
															<tbody id="uploadReportData">
																
															</tbody>
														</table>
														<div class="ps-scrollbar-x-rail"
															style="left: 0px; bottom: 3px;">
															<div class="ps-scrollbar-x"
																style="left: 0px; width: 0px;"></div>
														</div>
														<div class="ps-scrollbar-y-rail"
															style="top: 0px; right: 3px;">
															<div class="ps-scrollbar-y"
																style="top: 0px; height: 0px;"></div>
														</div>
													</div>
												</div>
												
												<div id="hra" class="tab-pane fade padding-bottom-5">
													<div class="panel-scroll height-200 ps-container">
														<%-- <table class="table no-margin">
															<jsp:include page="indus_coming_soon.jsp"></jsp:include>
															
														</table> --%>
														<div class="ps-scrollbar-x-rail"
															style="left: 0px; bottom: 3px;">
															<div class="ps-scrollbar-x"
																style="left: 0px; width: 0px;"></div>
														</div>
														<div class="ps-scrollbar-y-rail"
															style="top: 0px; right: 3px;">
															<div class="ps-scrollbar-y"
																style="top: 0px; height: 0px;"></div>
														</div>
													</div>
												</div>
												
												
												<div id="verification" class="tab-pane fade padding-bottom-5">
													<div class="panel-scroll height-200 ps-container">
														<%-- <table class="table no-margin">
															<jsp:include page="indus_coming_soon.jsp"></jsp:include>
															
														</table> --%>
														<div class="ps-scrollbar-x-rail"
															style="left: 0px; bottom: 3px;">
															<div class="ps-scrollbar-x"
																style="left: 0px; width: 0px;"></div>
														</div>
														<div class="ps-scrollbar-y-rail"
															style="top: 0px; right: 3px;">
															<div class="ps-scrollbar-y"
																style="top: 0px; height: 0px;"></div>
														</div>
													</div>
												</div>
												
												<div id="otherDetails" class="tab-pane fade padding-bottom-5">
													<div class="panel-scroll height-200 ps-container">
														<table class="table no-margin">
															<%-- <jsp:include page="indus_coming_soon.jsp"></jsp:include> --%>
															
														</table>
														<div class="ps-scrollbar-x-rail"
															style="left: 0px; bottom: 3px;">
															<div class="ps-scrollbar-x"
																style="left: 0px; width: 0px;"></div>
														</div>
														<div class="ps-scrollbar-y-rail"
															style="top: 0px; right: 3px;">
															<div class="ps-scrollbar-y"
																style="top: 0px; height: 0px;"></div>
														</div>
													</div>
												</div>
												
												<div id="reminders" class="tab-pane fade padding-bottom-5">
													<div class="panel-scroll height-200 ps-container">
														<table class="table no-margin">
															<%-- <jsp:include page="indus_coming_soon.jsp"></jsp:include> --%>
															
														</table>
														<div class="ps-scrollbar-x-rail"
															style="left: 0px; bottom: 3px;">
															<div class="ps-scrollbar-x"
																style="left: 0px; width: 0px;"></div>
														</div>
														<div class="ps-scrollbar-y-rail"
															style="top: 0px; right: 3px;">
															<div class="ps-scrollbar-y"
																style="top: 0px; height: 0px;"></div>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>

							</div>


						</div>
					</div>
					<!-- end: USER PROFILE -->
				<!-- </div> -->
			</div>
		</div>
	</div>
	
	<!-- start: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
	<%-- <script
		src="<c:url value="/indus/resources/vendor/bootstrap-fileinput/jasny-bootstrap.js"/>"></script> --%>

	<!-- end: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
	<!-- start: CLIP-TWO JAVASCRIPTS -->
	<%-- <script src="<c:url value="/indus/resources/assets/js/main.js"/>"></script> --%>
	<%--  <script src="<c:url value="/indus/resources/custom_js/indus_header.js"/>"> --%>
	<!-- start: JavaScript Event Handlers for this page -->
	
	<script
		src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>"></script>
	
	<script src="<c:url value="/indus/resources/custom_js/demograhics.js"/>"></script>
	
	<%-- <script src="<c:url value="/indus/resources/custom_js/index.js"/>"></script> --%>
	
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
	
	<script src="<c:url value="/indus/resources/assets/js/jquery.ajaxfileupload.js"/>"></script>
	<script>
		jQuery(document).ready(function() {
			//For disable future dates
			$('.disableDate').datepicker({
		        format: 'dd-mm-yyyy',
		        endDate: '+0d',
		        autoclose: true
		    });
			
			//for height
			var heightInCm=$('#heightInCm').html();
			if(heightInCm!=null){
				$('#editHeightInput').val($('#heightInCm').html());
				$('#heightUnit').val("cm");
				unitChanged();
			}else{
				$('#heightUnit').val("foot");
				$('#editHeightFoot').val($('#heightInFoot').html());
				$('#editHeightInches').val($('#heightInches').html());
				unitChanged();
			}
			
			//for weight
			if($('#weightUnitHidden').html()=="Kg"){
				$('#weightUnit').val("kg");
			}
			else{
				$('#weightUnit').val("pound");
			}
			$('#editWeightInput').val($('#weightValueHidden').html());
			
			//for blood group
			$('#bloodGroupList').val($('#bloodGroupHidden').html());
			
			//Main.init();
			getClientCenterPackageByUserId($('#clientMasterId').html());
			FormElements.init();
			
			$('#changeProfilePicture').ajaxfileupload({
				'action' : '../UploadServlet',
				'onComplete' : function(response) {
						
				},
				'onStart' : function() {
					$('#upload').show();
					$('#message').hide();
				}
			});
			jQuery.noConflict();
		});
	</script>
	<!-- end: JavaScript Event Handlers for this page -->
	<!-- end: CLIP-TWO JAVASCRIPTS -->
	
	 <%-- <jsp:include page="indus_popups.jsp"></jsp:include> --%>
	 
</body>
</html>
