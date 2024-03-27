<%@page import="com.hms.indus.bo.ClientMaster"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
	
<script src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>"></script>
		
<%
	ClientMaster clientMaster=(ClientMaster)request.getAttribute("client");
%>
<body>
	<span id="clientEmailId" class="hidden"><%=clientMaster.getEmailId()%></span>
	<span id="clientMobileNo" class="hidden"><%=clientMaster.getMobNo()%></span>
	<div id="app">
		<div class="app-content">
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
											
											<div class="pull-right">
												<!-- <button type="button" class="btn btn-info">Dial Me</button> -->
													
												<button type="button" class="btn btn-info"
													onclick="sendEmailAndSmsContactHubPageBtn()">Send Email and Sms</button>
											</div>
											
											<!-- <div class="anchor_width pull-right interactionTabHide">
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
											</div> -->
										</div>
										<div class="clearfix padding-5 space5">
											<div class="col-xs-1 text-center no-padding">
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
											</div>
											<div class="col-xs-3 no-padding hidden">
												<span class="text-dark packageNameSpan"></span>
											</div>
											<div class="col-xs-3 no-padding hidden">
												<span class="text-dark centerNameSpan"></span>
											</div>
											<div class="col-xs-3 no-padding">
												Check Up Date: <span class="text-dark lastCheckUpDate"></span>
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
												<li onclick="loadVerificationPage()" class="interactionTabHide padding-top-5 verificationTab menu_5" style="display:none;"><a data-toggle="tab"
													href="#verification"> Verification </a></li>
												<li onclick="loadOtherDetailsPage()" class="padding-top-5 menu_7" style="display:none;"><a data-toggle="tab"
													href="#otherDetails"> Self Upload </a></li>
												<li onclick="loadReminderPage()" class="padding-top-5 menu_8" style="display:none;"><a data-toggle="tab"
													href="#reminders"> Reminders </a></li>
													
												<li onclick="loadFollowUpPage()" class="padding-top-5 interactionTabShow" style="display:none;"><a data-toggle="tab"
													href="#followUpRecord"> Follow Up Record </a></li>
												
												<li onclick="loadContactHubPage()" class="active padding-top-5 interactionTabShow" style="display:none;"><a data-toggle="tab"
													href="#contactHub"> Contact Hub </a></li>
												<li onclick="loadDataEntryPage()" class="padding-top-5 interactionTabShow" style="display:none;"><a data-toggle="tab"
													href="#dataEntry"> Data Entry </a></li>
												
												<!-- <li class="padding-top-5"><a data-toggle="tab"
													href="#users_following"> Call Log </a></li> -->
											</ul>
											<div class="tab-content">
												
			<div id="contactHub" class="active tab-pane fade in">
										<div class="row">
											<div class="col-md-4 col-xs-9">
												<h5 class="text-bold margin-bottom-15">Date
													Range</h5>
												<div class="input-group input-daterange datepicker">
													<input id="emailStartDate" name="startDate" type="text"
														class="form-control" onchange="assignDate();" /> <span
														class="input-group-addon bg-primary">to</span> <input
														id="emailEndDate" type="text" class="form-control" />
												</div>
											</div>
											<div class="col-md-1 margin-top-32 col-xs-3">
												<button type="button" class="btn btn-info"
													onclick="getEmailAndSmsRecordByDate()">Search</button>
											</div>
											
				<form id="getEmailSmsForm" onchange="getEmailSmsForm()">
											<div class="col-md-2 margin-top-32 col-xs-4">
												<div class="form-group">
													<input type="radio" name="contactHubValues" value="all" checked> All<br>
												</div>
											</div>

											<div class="col-md-2 margin-top-32 col-xs-3">
												<div class="form-group">
													<input type="radio" name="contactHubValues" value="email" > Email<br>
												</div>
											</div>

											<div class="col-md-2 margin-top-32 col-xs-3">
												<div class="form-group">
													<input type="radio" name="contactHubValues" value="sms"> SMS<br>
												</div>
											</div>
				</form>
										</div>

												<div class="panel-scroll ps-container table-responsive mousescroll" style="max-height: 280px;">
														<table
									class="table no-margin"
									id="sample_1">
									<thead>
										<tr>
											<th>#</th>
											<th>Email/Sms</th>
											<th>Sent By</th>
											<th>Sent Date</th>
											<th>View</th>
										</tr>
									</thead>
									<tbody id="contactHubTableBody">

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
	
	<script src="<c:url value="/indus/resources/assets/js/main.js"/>"></script>
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

	<%-- <script src="<c:url value="/indus/resources/custom_js/indus_interaction.js"/>"></script> --%>
	
	<!-- start: JavaScript Event Handlers for this page -->
	<script>
		jQuery(document).ready(function() {
			//Main.init();
			FormElements.init();
			jQuery.noConflict();
		});
	</script>
	 
</body>
</html>
