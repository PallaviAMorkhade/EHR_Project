<%@page import="com.hms.indus.bo.ClientMaster"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- <!DOCTYPE html>
<!-- Template Name: Clip-Two - Responsive Admin Template build with Twitter Bootstrap 3.x | Author: ClipTheme -->
<!--[if IE 8]><html class="ie8" lang="en"><![endif]-->
<!--[if IE 9]><html class="ie9" lang="en"><![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- start: HEAD -->
<head>
<title>Indus|My Reports</title>
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
<!-- <link
	href="http://fonts.googleapis.com/css?family=Lato:300,400,400italic,600,700|Raleway:300,400,500,600,700|Crete+Round:400italic"
	rel="stylesheet" type="text/css" /> -->
<!-- end: GOOGLE FONTS -->
<!-- start: MAIN CSS -->
<link rel="stylesheet" type="text/css"
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
	rel="stylesheet" media="screen">
<!-- end: MAIN CSS -->

<!-- start: CLIP-TWO CSS -->
<link rel="stylesheet"
	href="<c:url value="/indus/resources/assets/css/styles.css"/>">
<link rel="stylesheet"
	href="<c:url value="/indus/resources/assets/css/plugins.css"/>">
<link rel="stylesheet"
	href="<c:url value="/indus/resources/assets/css/themes/theme-4.css"/>" />
<!-- end: CLIP-TWO CSS -->
<!-- start: CSS REQUIRED FOR THIS PAGE ONLY -->
<link href="<c:url value="/indus/resources/vendor/bootstrap-fileinput/jasny-bootstrap.min.css"/>"
	rel="stylesheet" media="screen">
<!-- end: CSS REQUIRED FOR THIS PAGE ONLY -->

</head> --%>
<!-- end: HEAD -->
<%
	ClientMaster clientMaster=(ClientMaster)request.getAttribute("client");
	String clientFirstName=(String)session.getAttribute("userFirstName");
%>

<%-- <span class="hidden" id="clientId"><%=session.getAttribute("clientId") %></span> --%>
<body> <!-- onload="getVerifyUploadedReport(<%-- <%=session.getAttribute("clientId") %> --%>)" -->
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
											<div class="anchor_width pull-right">
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
																	<small> Complete </small>
															</span>
														</a></li>
														<li><a id="step-5" href="#step-5" class="" isdone="0"
															rel="5">
																<div class="stepNumber">05</div> <span class="stepDesc">
																	<small> Coming Soon </small>
															</span>
														</a></li>
													</ul>
												</div>
											</div>
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
										</div>
										<div class="tabbable no-margin no-padding">
											<ul class="nav nav-tabs" id="myTab">
												<li onclick="loadDemoghraphicPage()" class="padding-top-5 padding-left-5 demoghraphicTab menu_3" style="display:none;"><a
													data-toggle="tab" href="#panel_overview"> Demographics
												</a></li>
												<li onclick="loadReportPage()" class="padding-top-5 myReportTab menu_4" style="display:none;"><a data-toggle="tab"
													href="#users_following"> Reports </a></li>
												<li onclick="loadHRAPage()" class="padding-top-5 hraTab menu_6" style="display:none;" ><a data-toggle="tab"
													href="#hra"> HRA </a></li>
												<li onclick="loadVerificationPage()" class="interactionTabHide active padding-top-5 verificationTab menu_5" style="display:none;"><a data-toggle="tab"
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
												<%-- <div id="panel_overview" class="tab-pane fade">
													<div class="row">
														<div class="col-sm-5 col-md-4">
															<div class="user-left">
																<div class="center">
																	<h4><%=clientMaster.getFirstName() +" "+  clientMaster.getLastName()%></h4>
																	<div class="fileinput fileinput-new"
																		data-provides="fileinput">
																		<div class="user-image">
																			<div class="fileinput-new thumbnail">
																				<img 
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
																			<div
																				class="fileinput-preview fileinput-exists thumbnail"></div>
																			<div class="user-image-buttons">
																				<span class="btn btn-azure btn-file btn-sm"><span
																					class="fileinput-new"><i
																						class="fa fa-pencil"></i></span><span
																					class="fileinput-exists"><i
																						class="fa fa-pencil"></i></span> <input type="file">
																				</span> <a href="#"
																					class="btn fileinput-exists btn-red btn-sm"
																					data-dismiss="fileinput"> <i
																					class="fa fa-times"></i>
																				</a>
																			</div>
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
																			<td>
																			<%
																			if(clientMaster.getFirstName()!=null && clientMaster.getFirstName()!="")
																				out.print(clientMaster.getFirstName());
																			else
																				out.print("-");
																				%>
																			
																			</td>
																			<td><a href="#panel_edit_account"
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>Middle Name</td>
																			
																			<td>
																			<%
																			if(clientMaster.getMiddleName()!=null && clientMaster.getMiddleName()!="")
																				out.print(clientMaster.getMiddleName());
																			else
																				out.print("-");
																				%>
																			
																			</td>
																			<td><a href="#panel_edit_account"
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>Last Name</td>
																			<td>
																			<%
																				if(clientMaster.getLastName()!=null && clientMaster.getLastName()!="")
																					out.print(clientMaster.getLastName());
																				else
																					out.print("-");
																			%>
																			</td>
																			<td><a href="#panel_edit_account"
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>Birth/Age</td>
																			<td>
																			<%
																				if(clientMaster.getClientDOB()!=null)
																				{	
																					if(clientMaster.getClientAge()!=null)
																					{
																						out.print(clientMaster.getClientDOB()+" / "+clientMaster.getClientAge());
																					}
																					else
																					{
																						out.print(clientMaster.getClientDOB()+" / -");
																					}
																				}
																				else
																				{
																					if(clientMaster.getClientAge()!=0)
																					{
																						out.print(" / "+clientMaster.getClientAge());
																					}
																					else
																					{
																						out.print(" / "+clientMaster.getClientAge());
																					}
																				}
																			%>
																			</td>
																			<td><a href="#panel_edit_account"
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>Height/Weight/Sex</td>
																			<td>-</td>
																			<td><a href="#panel_edit_account"
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>Blood Group</td>
																			<td>
																			<%
																				if(clientMaster.getBloodGroup()!=null && clientMaster.getBloodGroup()!="")
																					out.print(clientMaster.getBloodGroup());
																				else
																					out.print("-");
																			%>
																			</td>
																			<td><a href="#panel_edit_account"
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																	</tbody>
																</table>

															</div>
														</div>
														<div class="col-sm-5 col-md-4">
															<table class="table">
																<tbody>
																	<tr>
																		<td>relation with nominee</td>
																		<td>-</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>First Name</td>
																		<td>-</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>Middle Name</td>
																		<td>-</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>Last Name</td>
																		<td>-</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>Birth/Age</td>
																		<td>-</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																</tbody>
															</table>
															<table class="table">
																<thead>
																	<tr>
																		<th colspan="3">Contact Information</th>
																	</tr>
																</thead>
																<tbody>
																	<tr>
																		<td>Address 1</td>
																		<td>
																		<%
																			if(clientMaster.getAddressLine1()!=null && clientMaster.getAddressLine1()!=null)
																				out.print(clientMaster.getAddressLine1());
																			else
																				out.print("-");
																		%>
																		</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>Address 2</td>
																		<td>
																		<%
																			if(clientMaster.getAddressLine2()!=null && clientMaster.getAddressLine2()!=null)
																				out.print(clientMaster.getAddressLine2());
																			else
																				out.print("-");
																		%>
																		</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>Address 3</td>
																		<td>
																		<%
																			if(clientMaster.getAddressLine3()!=null && clientMaster.getAddressLine3()!="")
																				out.print(clientMaster.getAddressLine3());
																			else
																				out.print("-");
																		%>
																		</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>City</td>
																		<td>
																		<%
																			if(clientMaster.getCityMaster().getCityName()!=null)
																				out.print(clientMaster.getCityMaster().getCityName());
																			else
																				out.print("-");
																		%>
																		</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>State</td>
																		<td>
																		<%
																			if(clientMaster.getStateMaster().getStateName()!=null)
																				out.print(clientMaster.getStateMaster().getStateName());
																			else
																				out.print("-");
																		%>
																		</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>Country</td>
																		<td>
																		<%
																			if(clientMaster.getCountryMaster().getCountryName()!=null)
																				out.print(clientMaster.getCountryMaster().getCountryName());
																		%>
																		</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>Pincode</td>
																		<td>
																		<%
																			if(clientMaster.getPinCode()!=0)
																				out.print(clientMaster.getPinCode());
																		%>
																		</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																</tbody>
															</table>
														</div>

														<div class="col-sm-5 col-md-4">
															<!-- <div class="row space20">
																<button type="button"
																	class="btn btn-wide btn-o btn-success">
																	Edit/Update</button>
																<button type="button"
																	class="btn btn-wide btn-o btn-info">Refresh</button>
															</div> -->
															<div class="panel" id="activities">

																<div collapse="activities" ng-init="activities=false"
																	class="panel-wrapper">
																	<!-- <div class="panel-body">
																		<ul class="timeline-xs">
																			<li class="timeline-item success">
																				<div class="margin-left-15">
																					<div class="text-muted text-small">2 minutes
																						ago</div>
																					<p>
																						<a class="text-info" href> Steven </a> has
																						completed his account.
																					</p>
																				</div>
																			</li>
																			<li class="timeline-item">
																				<div class="margin-left-15">
																					<div class="text-muted text-small">12:30</div>
																					<p>Staff Meeting</p>
																				</div>
																			</li>
																			<li class="timeline-item danger">
																				<div class="margin-left-15">
																					<div class="text-muted text-small">11:11</div>
																					<p>Completed new layout.</p>
																				</div>
																			</li>
																			<li class="timeline-item info">
																				<div class="margin-left-15">
																					<div class="text-muted text-small">Thu, 12
																						Jun</div>
																					<p>
																						Contacted <a class="text-info" href> Microsoft
																						</a> for license upgrades.
																					</p>
																				</div>
																			</li>

																		</ul>
																	</div> -->
																</div>
																<!-- <div class="list-group-item list-group-item-success">
																	<b>Well done! </b> Undertaking Uploaded
																	<button type="button" class="btn btn-o btn-success">
																		View</button>
																</div> -->
																<br />
																<table class="table table-condensed">

																	<tbody>
																		<tr>
																			<td>Mobile</td>
																			<td>
																			<%
																				if(clientMaster.getMobNo()!=null)
																					out.print(clientMaster.getMobNo());
																			%>
																			</td>
																			<td><a href="#panel_edit_account"
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>Land Line</td>
																			<td>
																			<%
																				if(clientMaster.getLandlineNo()!=null)
																					out.print(clientMaster.getLandlineNo());
																			%>
																			</td>
																			<td><a href="#panel_edit_account"
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>Email</td>
																			<td><a href="">
																			<%
																				if(clientMaster.getEmailId()!=null)
																					out.print(clientMaster.getEmailId());
																			%>
																			</a></td>
																			<td><a href="#panel_edit_account"
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																	</tbody>
																</table>
															</div>

														</div>
													</div>
												</div> --%>
												<div id="users_following" class="tab-pane fade padding-bottom-5">
													<div class="row">
															<div class="col-md-12">
																<div class="col-md-12 space20">
																	<button type="submit" class="btn btn-primary start" data-toggle="modal" data-target="#uploadReportModal">
																		<i class="glyphicon glyphicon-upload"></i>
																		<span>Upload</span>
																	</button>
																	</div>
																	</div>
																</div>
													<div class="panel-scroll height-200 ps-container" style="overflow-y: scroll;">
														<table class="table no-margin">
															<thead>
																		<tr>
																			<th class="center">#</th>
																			<th>Test Name</th>
																			<th class="hidden-xs">Uploader</th>
																			<th>Upload Date</th>
																			<th class="hidden-xs">Upload Time</th>
																			<th>Action</th>
																		</tr>
																	</thead>
																	<tbody id="uploadReportData">
																		<!-- <tr>
																			<td class="center">1</td>
																			<td class="hidden-xs">BSL Profile</td>
																			<td>Mr. John Doe</td>
																			<td>10 Dec. 2015</td>
																			<td class="hidden-xs">03:20:00</td>
																			<td>
																				<div class="visible-md visible-lg hidden-sm hidden-xs">
																					<a href="#" class="btn btn-transparent btn-xs" tooltip-placement="top" tooltip="Edit"><i class="fa fa-binoculars"></i></a>
																					<a href="#" class="btn btn-transparent btn-xs" tooltip-placement="top" tooltip="Edit"><i class="fa fa-pencil"></i></a>
																					<a href="#" class="btn btn-transparent btn-xs tooltips" tooltip-placement="top" tooltip="Share"><i class="fa fa-share"></i></a>
																					<a href="#" class="btn btn-transparent btn-xs tooltips" tooltip-placement="top" tooltip="Remove"><i class="fa fa-times fa fa-white"></i></a>
																				</div>
																			</td>
																		</tr> -->
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
												
												<div id="hra" class="tab-pane fade padding-bottom-5" >
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
												
												<div id="verification" class="tab-pane fade padding-bottom-5  in active">
													<!-- <div class="row">
															<div class="col-md-12">
																<div class="col-md-12 space20">
																	The fileinput-button span is used to style the file input field as button
																 <button type="button" id="verifySelectionBtn" class="btn btn-primary start" data-toggle="modal" data-target="#verifyUploadReportModal">
																		<span>Verify Selection</span>
																	</button>
																	 <span class="btn btn-success fileinput-button"> 
																		<span>Verify All</span>
																		<input type="button">
																	</span> 
																	<button type="reset" class="btn btn-warning cancel">
																		<span>Recheck Request</span>
																	</button>
																	<button type="button" class="btn btn-danger delete">
																		<span>Reject All</span>
																	</button> 
																	The loading indicator is shown during file processing
																		<span class="fileupload-loading"></span>
																	</div>
																	</div>
																</div> -->
													<div class="panel-scroll ps-container table-responsive mousescroll" style="max-height: 330px;">
														<table class="table no-margin">
															<thead>
																		<tr>
																			<th class="center">#</th>
																			<th>Test Name</th>
																			<th>Centre Name</th>
																			<th>Uploader</th>
																			<th>Upload Date</th>
																			<th>Upload Time</th>
																			<th></th>
																			<th>Action</th>
																			<th></th>
																		</tr>
																	</thead>
																	<tbody id="verifiedReportData">
																		
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
		<!-- start: FOOTER -->
		<!-- <footer>
			<div class="footer-inner">
				<div class="pull-left">
					&copy; <span class="current-year"></span><span
						class="text-bold text-uppercase">OrcaSys</span>. <span>All
						rights reserved</span>
				</div>
				<div class="pull-right">
					<span class="go-top"><i class="ti-angle-up"></i></span>
				</div>
			</div>
		</footer> -->
		<!-- end: FOOTER -->
		<!-- start: OFF-SIDEBAR -->
		<!-- <div id="off-sidebar" class="sidebar">
			<div class="sidebar-wrapper">
				<ul class="nav nav-tabs nav-justified">
					<li class="active"><a href="#off-users"
						aria-controls="off-users" role="tab" data-toggle="tab"> <i
							class="ti-comments"></i>
					</a></li>
					<li><a href="#off-favorites" aria-controls="off-favorites"
						role="tab" data-toggle="tab"> <i class="ti-heart"></i>
					</a></li>
					<li><a href="#off-settings" aria-controls="off-settings"
						role="tab" data-toggle="tab"> <i class="ti-settings"></i>
					</a></li>
				</ul>
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="off-users">
						<div id="users" toggleable active-class="chat-open">
							<div class="users-list">
								<div class="sidebar-content perfect-scrollbar">
									<h5 class="sidebar-title">On-line</h5>
									<ul class="media-list">
										<li class="media"><a data-toggle-class="chat-open"
											data-toggle-target="#users" href="#"> <i
												class="fa fa-circle status-online"></i> <img alt="..."
												src="../resources/assets/images/avatar-2.jpg" class="media-object">
												<div class="media-body">
													<h4 class="media-heading">Nicole Bell</h4>
													<span> Content Designer </span>
												</div>
										</a></li>
										<li class="media"><a data-toggle-class="chat-open"
											data-toggle-target="#users" href="#">
												<div class="user-label">
													<span class="label label-success">3</span>
												</div> <i class="fa fa-circle status-online"></i> <img alt="..."
												src="../resources/assets/images/avatar-3.jpg" class="media-object">
												<div class="media-body">
													<h4 class="media-heading">Steven Thompson</h4>
													<span> Visual Designer </span>
												</div>
										</a></li>
										<li class="media"><a data-toggle-class="chat-open"
											data-toggle-target="#users" href="#"> <i
												class="fa fa-circle status-online"></i> <img alt="..."
												src="../resources/assets/images/avatar-4.jpg" class="media-object">
												<div class="media-body">
													<h4 class="media-heading">Ella Patterson</h4>
													<span> Web Editor </span>
												</div>
										</a></li>
										<li class="media"><a data-toggle-class="chat-open"
											data-toggle-target="#users" href="#"> <i
												class="fa fa-circle status-online"></i> <img alt="..."
												src="../resources/assets/images/avatar-5.jpg" class="media-object">
												<div class="media-body">
													<h4 class="media-heading">Kenneth Ross</h4>
													<span> Senior Designer </span>
												</div>
										</a></li>
									</ul>
									<h5 class="sidebar-title">Off-line</h5>
									<ul class="media-list">
										<li class="media"><a data-toggle-class="chat-open"
											data-toggle-target="#users" href="#"> <img alt="..."
												src="../resources/assets/images/avatar-6.jpg" class="media-object">
												<div class="media-body">
													<h4 class="media-heading">Nicole Bell</h4>
													<span> Content Designer </span>
												</div>
										</a></li>
										<li class="media"><a data-toggle-class="chat-open"
											data-toggle-target="#users" href="#">
												<div class="user-label">
													<span class="label label-success">3</span>
												</div> <img alt="..." src="../resources/assets/images/avatar-7.jpg"
												class="media-object">
												<div class="media-body">
													<h4 class="media-heading">Steven Thompson</h4>
													<span> Visual Designer </span>
												</div>
										</a></li>
										<li class="media"><a data-toggle-class="chat-open"
											data-toggle-target="#users" href="#"> <img alt="..."
												src="../resources/assets/images/avatar-8.jpg" class="media-object">
												<div class="media-body">
													<h4 class="media-heading">Ella Patterson</h4>
													<span> Web Editor </span>
												</div>
										</a></li>
										<li class="media"><a data-toggle-class="chat-open"
											data-toggle-target="#users" href="#"> <img alt="..."
												src="../resources/assets/images/avatar-9.jpg" class="media-object">
												<div class="media-body">
													<h4 class="media-heading">Kenneth Ross</h4>
													<span> Senior Designer </span>
												</div>
										</a></li>
										<li class="media"><a data-toggle-class="chat-open"
											data-toggle-target="#users" href="#"> <img alt="..."
												src="../resources/assets/images/avatar-10.jpg" class="media-object">
												<div class="media-body">
													<h4 class="media-heading">Ella Patterson</h4>
													<span> Web Editor </span>
												</div>
										</a></li>
										<li class="media"><a data-toggle-class="chat-open"
											data-toggle-target="#users" href="#"> <img alt="..."
												src="../resources/assets/images/avatar-5.jpg" class="media-object">
												<div class="media-body">
													<h4 class="media-heading">Kenneth Ross</h4>
													<span> Senior Designer </span>
												</div>
										</a></li>
									</ul>
								</div>
							</div>
							<div class="user-chat">
								<div class="chat-content">
									<div class="sidebar-content perfect-scrollbar">
										<a class="sidebar-back pull-left" href="#"
											data-toggle-class="chat-open" data-toggle-target="#users"><i
											class="ti-angle-left"></i> <span>Back</span></a>
										<ol class="discussion">
											<li class="messages-date">Sunday, Feb 9, 12:58</li>
											<li class="self">
												<div class="message">
													<div class="message-name">Peter Clark</div>
													<div class="message-text">Hi, Nicole</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/default-user.png" alt="">
													</div>
												</div>
												<div class="message">
													<div class="message-name">Nicole Bell</div>
													<div class="message-text">How are you?</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/default-user.png" alt="">
													</div>
												</div>
											</li>
											<li class="other">
												<div class="message">
													<div class="message-name">Nicole Bell</div>
													<div class="message-text">Hi, i am good</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/avatar-2.jpg" alt="">
													</div>
												</div>
											</li>
											<li class="self">
												<div class="message">
													<div class="message-name">Peter Clark</div>
													<div class="message-text">Glad to see you ;)</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/default-user.png" alt="">
													</div>
												</div>
											</li>
											<li class="messages-date">Sunday, Feb 9, 13:10</li>
											<li class="other">
												<div class="message">
													<div class="message-name">Nicole Bell</div>
													<div class="message-text">What do you think about my
														new Dashboard?</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/avatar-2.jpg" alt="">
													</div>
												</div>
											</li>
											<li class="messages-date">Sunday, Feb 9, 15:28</li>
											<li class="other">
												<div class="message">
													<div class="message-name">Nicole Bell</div>
													<div class="message-text">Alo...</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/avatar-2.jpg" alt="">
													</div>
												</div>
												<div class="message">
													<div class="message-name">Nicole Bell</div>
													<div class="message-text">Are you there?</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/avatar-2.jpg" alt="">
													</div>
												</div>
											</li>
											<li class="self">
												<div class="message">
													<div class="message-name">Peter Clark</div>
													<div class="message-text">Hi, i am here</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/default-user.png" alt="">
													</div>
												</div>
												<div class="message">
													<div class="message-name">Nicole Bell</div>
													<div class="message-text">Your Dashboard is great</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/default-user.png" alt="">
													</div>
												</div>
											</li>
											<li class="messages-date">Friday, Feb 7, 23:39</li>
											<li class="other">
												<div class="message">
													<div class="message-name">Nicole Bell</div>
													<div class="message-text">How does the binding and
														digesting work in AngularJS?, Peter?</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/avatar-2.jpg" alt="">
													</div>
												</div>
											</li>
											<li class="self">
												<div class="message">
													<div class="message-name">Peter Clark</div>
													<div class="message-text">oh that's your question?</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/default-user.png" alt="">
													</div>
												</div>
												<div class="message">
													<div class="message-name">Peter Clark</div>
													<div class="message-text">little reduntant, no?</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/default-user.png" alt="">
													</div>
												</div>
												<div class="message">
													<div class="message-name">Peter Clark</div>
													<div class="message-text">literally we get the
														question daily</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/default-user.png" alt="">
													</div>
												</div>
											</li>
											<li class="other">
												<div class="message">
													<div class="message-name">Nicole Bell</div>
													<div class="message-text">I know. I, however, am not
														a nerd, and want to know</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/avatar-2.jpg" alt="">
													</div>
												</div>
											</li>
											<li class="self">
												<div class="message">
													<div class="message-name">Peter Clark</div>
													<div class="message-text">for this type of question,
														wouldn't it be better to try Google?</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/default-user.png" alt="">
													</div>
												</div>
											</li>
											<li class="other">
												<div class="message">
													<div class="message-name">Nicole Bell</div>
													<div class="message-text">Lucky for us :)</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/avatar-2.jpg" alt="">
													</div>
												</div>
											</li>
										</ol>
									</div>
								</div>
								<div class="message-bar">
									<div class="message-inner">
										<a class="link icon-only" href="#"><i class="fa fa-camera"></i></a>
										<div class="message-area">
											<textarea placeholder="Message"></textarea>
										</div>
										<a class="link" href="#"> Send </a>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div role="tabpanel" class="tab-pane" id="off-favorites">
						<div class="users-list">
							<div class="sidebar-content perfect-scrollbar">
								<h5 class="sidebar-title">Favorites</h5>
								<ul class="media-list">
									<li class="media"><a href="#"> <img alt="..."
											src="../resources/assets/images/avatar-7.jpg" class="media-object">
											<div class="media-body">
												<h4 class="media-heading">Nicole Bell</h4>
												<span> Content Designer </span>
											</div>
									</a></li>
									<li class="media"><a href="#">
											<div class="user-label">
												<span class="label label-success">3</span>
											</div> <img alt="..." src="../resources/assets/images/avatar-6.jpg"
											class="media-object">
											<div class="media-body">
												<h4 class="media-heading">Steven Thompson</h4>
												<span> Visual Designer </span>
											</div>
									</a></li>
									<li class="media"><a href="#"> <img alt="..."
											src="../resources/assets/images/avatar-10.jpg" class="media-object">
											<div class="media-body">
												<h4 class="media-heading">Ella Patterson</h4>
												<span> Web Editor </span>
											</div>
									</a></li>
									<li class="media"><a href="#"> <img alt="..."
											src="../resources/assets/images/avatar-2.jpg" class="media-object">
											<div class="media-body">
												<h4 class="media-heading">Kenneth Ross</h4>
												<span> Senior Designer </span>
											</div>
									</a></li>
									<li class="media"><a href="#"> <img alt="..."
											src="../resources/assets/images/avatar-4.jpg" class="media-object">
											<div class="media-body">
												<h4 class="media-heading">Ella Patterson</h4>
												<span> Web Editor </span>
											</div>
									</a></li>
									<li class="media"><a href="#"> <img alt="..."
											src="../resources/assets/images/avatar-5.jpg" class="media-object">
											<div class="media-body">
												<h4 class="media-heading">Kenneth Ross</h4>
												<span> Senior Designer </span>
											</div>
									</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div role="tabpanel" class="tab-pane" id="off-settings">
						<div class="sidebar-content perfect-scrollbar">
							<h5 class="sidebar-title">General Settings</h5>
							<ul class="media-list">
								<li class="media">
									<div class="padding-10">
										<div class="display-table-cell">
											<input type="checkbox" class="js-switch" checked />
										</div>
										<span
											class="display-table-cell vertical-align-middle padding-left-10">Enable
											Notifications</span>
									</div>
								</li>
								<li class="media">
									<div class="padding-10">
										<div class="display-table-cell">
											<input type="checkbox" class="js-switch" />
										</div>
										<span
											class="display-table-cell vertical-align-middle padding-left-10">Show
											your E-mail</span>
									</div>
								</li>
								<li class="media">
									<div class="padding-10">
										<div class="display-table-cell">
											<input type="checkbox" class="js-switch" checked />
										</div>
										<span
											class="display-table-cell vertical-align-middle padding-left-10">Show
											Offline Users</span>
									</div>
								</li>
								<li class="media">
									<div class="padding-10">
										<div class="display-table-cell">
											<input type="checkbox" class="js-switch" checked />
										</div>
										<span
											class="display-table-cell vertical-align-middle padding-left-10">E-mail
											Alerts</span>
									</div>
								</li>
								<li class="media">
									<div class="padding-10">
										<div class="display-table-cell">
											<input type="checkbox" class="js-switch" />
										</div>
										<span
											class="display-table-cell vertical-align-middle padding-left-10">SMS
											Alerts</span>
									</div>
								</li>
							</ul>
							<div class="save-options">
								<button class="btn btn-success">
									<i class="icon-settings"></i><span>Save Changes</span>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div> -->
		<!-- end: OFF-SIDEBAR -->
	</div>
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
	<!-- start: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
	<%-- <script
		src="<c:url value="/indus/resources/vendor/bootstrap-fileinput/jasny-bootstrap.js"/>"></script>

	<!-- end: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
	<!-- start: CLIP-TWO JAVASCRIPTS -->
	<script src="<c:url value="/indus/resources/assets/js/main.js"/>"></script> --%>
	<%-- <script src="<c:url value="/indus/resources/custom_js/demographics.js"/>"></script> --%>
	
	<script
		src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>"></script>
	<script src="<c:url value="/indus/resources/custom_js/indus_verification.js"/>"></script>
	
	<%-- <script src="<c:url value="/indus/resources/custom_js/index.js"/>"></script> --%>

	<!-- start: JavaScript Event Handlers for this page -->
	<script>
		jQuery(document).ready(function() {
			//Main.init();
			jQuery.noConflict();
			getClientCenterPackageByUserId($('#clientMasterId').html());
		});
	</script>
	<!-- end: JavaScript Event Handlers for this page -->
	<!-- end: CLIP-TWO JAVASCRIPTS -->
</body>
</html>
