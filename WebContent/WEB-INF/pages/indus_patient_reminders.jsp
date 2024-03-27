<%@page import="com.hms.indus.bo.ClientMaster"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<link
	href="<c:url value="/indus/resources/vendor/fullcalendar/fullcalendar.min.css"/>"
	rel="stylesheet" media="screen">
<link
	href="<c:url value="/indus/resources/vendor/bootstrap-datetimepicker/bootstrap-datetimepicker.min.css"/>"
	rel="stylesheet" media="screen">

<%-- <link
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
	rel="stylesheet" media="screen"> --%>


<%
	ClientMaster clientMaster = (ClientMaster) request
			.getAttribute("client");

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
											<%if (clientMaster.getPhotoUrl() != null
					&& clientMaster.getPhotoUrl() != "") {%>
											src="readImage?url=<%=clientMaster.getPhotoUrl()%>"
											<%} else {%> src="resources/assets/images/default-user.png"
											<%}%> class="img-circle pull-left" alt="">
										<h4 class="no-margin inline-block padding-5">
											<%=clientMaster.getFirstName() + " "
					+ clientMaster.getLastName()%>
											<span class="block text-small text-left"><center>
													<b>-</b>
												</center></span>
										</h4>

										<%
		HttpSession httpSession=request.getSession();
		if((String)httpSession.getAttribute("loginUserType")!=null){
		
		}
		else{
	%>
										<div
											class="anchor_width pull-right interactionTabHide timeline"
											style="display: none;">
											<div id="wizard" class="swMain">
												<ul class="anchor">
													<li><a id="step-1" href="#step-1" class="selected"
														isdone="1" rel="1">
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
											<li onclick="loadDemoghraphicPage()"
												class="padding-top-5 padding-left-5 demoghraphicTab menu_3"
												style="display: none;"><a data-toggle="tab"
												href="#panel_overview"> Demographics </a></li>
											<li onclick="loadReportPage()"
												class="padding-top-5 myReportTab menu_4"
												style="display: none;"><a data-toggle="tab"
												href="#users_following"> Reports </a></li>
											<li onclick="loadHRAPage()"
												class="padding-top-5 hraTab menu_6" style="display: none;"><a
												data-toggle="tab" href="#hra"> HRA </a></li>
											<li onclick="loadVerificationPage()"
												class="interactionTabHide padding-top-5 verificationTab menu_5"
												style="display: none;"><a data-toggle="tab"
												href="#verification"> Verification </a></li>
											<li onclick="loadOtherDetailsPage()"
												class="padding-top-5 menu_7" style="display: none;"><a
												data-toggle="tab" href="#otherDetails"> Self Upload </a></li>
											<li onclick="loadReminderPage()"
												class="active padding-top-5 menu_8" style="display: none;"><a
												data-toggle="tab" href="#reminders"> Reminders </a></li>

											<li onclick="loadFollowUpPage()"
												class="padding-top-5 interactionTabShow"
												style="display: none;"><a data-toggle="tab"
												href="#followUpRecord"> Follow Up Record </a></li>
											<li onclick="loadContactHubPage()"
												class="padding-top-5 interactionTabShow"
												style="display: none;"><a data-toggle="tab"
												href="#contactHub"> Contact Hub </a></li>
											<li onclick="loadDataEntryPage()"
												class="padding-top-5 interactionTabShow"
												style="display: none;"><a data-toggle="tab"
												href="#dataEntry"> Data Entry </a></li>

											<!-- <li class="padding-top-5"><a data-toggle="tab"
													href="#users_following"> Call Log </a></li> -->
										</ul>
										<div class="tab-content">
											<div id="reminders"
												class="active tab-pane fade in padding-bottom-5">
												<div class="panel-scroll ps-container">
													<%-- <jsp:include page="indus_coming_soon.jsp"></jsp:include> --%>

													<!-- start: CALENDAR -->
													<div class="row">
														<div class="col-sm-12 space20">
															<a href="#newFullEvent"
																class="btn btn-primary btn-o add-event"><i
																class="fa fa-plus"></i> Add New Event</a>
														</div>
														<div class="col-sm-12">
															<div id='full-calendar'></div>
														</div>
														<!-- <div class="col-sm-3">
															<h4 class="space20">Draggable categories</h4>
															<div id="event-categories">
																<div class="event-category event-generic"
																	data-class="generic">Generic</div>
																<div class="event-category event-home" data-class="home">
																	Home</div>
																<div class="event-category event-job" data-class="job">
																	Job</div>
																<div class="event-category event-off-site-work"
																	data-class="off-site-work">Off-site work</div>
																<div class="event-category event-to-do"
																	data-class="to-do">To Do</div>
																<div class="event-category event-cancelled"
																	data-class="cancelled">Cancelled</div>
																<div class="checkbox">
																	<label> <input type="checkbox" class="grey"
																		id="drop-remove" /> Remove after drop
																	</label>
																</div>
															</div>
														</div> -->
													</div>
													<!-- end: CALENDAR -->
													<!-- start: EVENTS ASIDE -->
													<div
														class="modal fade modal-aside horizontal right events-modal"
														tabindex="-1" role="dialog"
														aria-labelledby="myLargeModalLabel" aria-hidden="true">
														<div class="modal-dialog modal-dialog modal-md"
															style="overflow:auto;">
															<div class="modal-content">
															<form class="form-full-event">
																<div class="modal-body">
																	<div class="form-group hidden">
																		<label> ID </label> <input type="text" id="event-id">
																	</div>
																	<div class="form-group hidden">
																		<span id="eventIdSpan"></span>
																		<span id="groupIdSpan"></span>
																		<span id="recurrencePatternSpan"></span>
																	</div>
																	
																	<div class="form-group">
																		<label> Event Title </label> <input type="text"
																			id="eventTitle" placeholder="Enter title"
																			class="form-control underline text-large"
																			name="eventName">
																	</div>
																	<div class="form-group">
																		<label> Reminder For </label> <input type="text"
																			id="clientName" placeholder="Reminder For"
																			class="form-control underline text-large"
																			name="clientName">
																	</div>
																	<div class="form-group">
																		<label> Email Id & Mobile No. </label>
																		<div class="row">
																			<div class="col-md-6">
																				<input class="form-control" placeholder="Email Id"
																					id="emailId" type="text">
																			</div>
																			<div class="col-md-6">
																					<input type="text" id="mobileNo"
																						placeholder="Mobile No." class="form-control">
																			</div>
																		</div>
																	</div>
																	
																	<div class="form-group">
																	<label> Category </label>
																		<div class="row">
																			<div class="col-md-12">
																				<select class="form-control" id="categoryOption" onchange="changeCategory()">
																					<option value="">Select Category</option>
																					<option value="consultation">Dr.Consultation</option>
																					<option value="exercise">Exercise</option>
																					<option value="labTest">Lab Test</option>
																					<option value="medicine">Medicine</option>
																					<option value="others">Others</option>
																				</select>
																			</div>
																		</div>
																	</div>

																	<!-- <div class="form-group">
																		<label> Category </label>
																		<div class="row">
																			<div class="col-xs-4">
																				<div class="radio clip-radio radio-primary">
																					<input type="radio" id="consultation"
																						checked="checked" name="optionCategory"
																						value="consultation" class="event-categories">
																					<label for="consultation"> 
																					<span class="fa fa-circle text-purple"></span>
																						Dr.Consultation
																					</label>
																				</div>
																			</div>
																			<div class="col-xs-4">
																				<div class="radio clip-radio radio-primary">
																					<input type="radio" id="exercise"
																						name="optionCategory" value="exercise"
																						class="event-categories"> <label
																						for="exercise"> <span
																						class="fa fa-circle text-yellow"></span> Exercise
																					</label>
																				</div>
																			</div>
																			<div class="col-xs-4">
																				<div class="radio clip-radio radio-primary">
																					<input type="radio" id="labTest"
																						name="optionCategory" value="labTest"
																						class="event-categories"> <label
																						for="labTest"> <span
																						class="fa fa-circle text-info"></span> Lab Test
																					</label>
																				</div>
																			</div>
																			<div class="col-xs-4">
																				<div class="radio clip-radio radio-primary">
																					<input type="radio" id="medicine"
																						name="optionCategory" value="medicine"
																						class="event-categories"> <label
																						for="medicine"> <span
																						class="fa fa-circle text-green"></span> Medicine
																					</label>
																				</div>
																			</div>
																			<div class="col-xs-4">
																				<div class="radio clip-radio radio-primary">
																					<input type="radio" id="others"
																						name="optionCategory" value="others"
																						class="event-categories"> <label
																						for="others"> <span
																						class="fa fa-circle text-orange"></span> Others
																					</label>
																				</div>
																			</div>
																		</div>
																	</div> -->

																	<div id="consultationDiv" class="categoryDiv" style="display: none;">
																		<div class="form-group">
																			<label> Doctor Name </label> <input type="text"
																				id="doctorName" placeholder="Doctor Name"
																				class="form-control underline text-large"
																				name="doctorName">
																		</div>

																		<div class="form-group">
																			<label> Doctor Date & Time </label>
																			<div class="row">
																				<div class="col-md-6">
																						<input type="text" id="doctorDateTime"
																							class="form-control dateTimePicker">
																				</div>
																			</div>
																		</div>
																	</div>
																	
																	<div id="labTestDiv" class="categoryDiv" style="display: none;">
																		<div class="form-group">
																			<label> Test Name </label> <input type="text"
																				id="testName" placeholder="Test Name"
																				class="form-control underline text-large"
																				name="testName">
																		</div>

																		<div class="form-group">
																			<label> Centre Name </label> <input type="text"
																				id="centreName" placeholder="Centre Name"
																				class="form-control underline text-large"
																				name="centreName">
																		</div>
																	</div>
																	
																	<div id="exerciseDiv" class="categoryDiv" style="display: none;">
																		<div class="form-group">
																			<label> Type of exercise </label> <input type="text"
																				id="typeOfExercise" placeholder="Type of exercise"
																				class="form-control underline text-large"
																				name="typeOfExercise">
																		</div>

																		<div class="form-group">
																			<label> Duration in Minutes </label> <input type="text"
																				id="durationInMinutes" placeholder="Duration in Minutes"
																				class="form-control underline text-large"
																				name="durationInMinutes">
																		</div>
																	</div>

																	<div id="medicineDiv" class="categoryDiv"
																		style="display: none;">
																		<div class="form-group">
																			<label> Medicine Name </label> <input type="text"
																				id="medicineName" placeholder="Medicine Name"
																				class="form-control underline text-large"
																				name="medicineName">
																		</div>
																		<div class="form-group">
																			<label> Medicine Dose </label> <input type="text"
																				id="medicineDose" placeholder="Medicine Dose"
																				class="form-control underline text-large"
																				name="medicineDose">
																		</div>
																		<div class="form-group">
																			<label> Medicine Date & Time </label>
																			<div class="row">
																				<div class="col-md-6">
																					<input type="text" id="medicineDateTime"
																						class="form-control dateTimePicker">
																				</div>
																			</div>
																		</div>
																	</div>
																	
																	<div id="othersDiv" class="categoryDiv" style="display: none;">
																		<div class="form-group">
																			<label> Remind me for </label> <input type="text"
																				id="remindMeFor" placeholder="Remind me for"
																				class="form-control underline text-large"
																				name="remindMeFor">
																		</div>
																		<div class="form-group">
																			<label> Location </label> <input type="text"
																				id="location" placeholder="Location"
																				class="form-control underline text-large"
																				name="location">
																		</div>
																		<div class="form-group">
																			<label> Duration </label> <input type="text"
																				id="duration" placeholder="Duration"
																				class="form-control underline text-large"
																				name="duration">
																		</div>
																	</div>
																	
																	<div class="form-group">
																		<div class="row">
																			<div class="col-md-6">
																				<label> Start </label> <span class="input-icon">
																					<input type="text" id="start-date-time"
																					class="form-control underline"
																					name="eventStartDate" /> <i class="ti-calendar"></i>
																				</span>
																			</div>
																			<div class="col-md-6">
																				<label> End </label> <span class="input-icon">
																					<input type="text" id="end-date-time"
																					class="form-control underline" name="eventEndDate" />
																					<i class="ti-calendar"></i>
																				</span>
																			</div>
																		</div>
																	</div>
																	
																	<div class="form-group">
																		<div class="row">
																			<div class="col-md-6 col-xs-12">
																				<div class="radio clip-radio radio-primary">
																					<input type="radio" id="oneTime"
																						name="recurrencePattern" value="1"> <label
																						for="oneTime"> One Time </label>
																				</div>
																			</div>
																			
																			<div class="col-md-6 col-xs-12">
																				<div class="radio clip-radio radio-primary">
																					<input type="radio" id="repeat"
																						name="recurrencePattern" value="2"> <label
																						for="repeat"> Repeat </label>
																				</div>
																			</div>
																		</div>
																	</div>
																	
																	<div class="form-group" id="reminderSummaryDiv" style="display:none;">
																			<label class="recurrenceLabel"> Summary </label>
																			<div class="row" id="oneTimeReminderSummaryDiv">
																				<div class="col-md-12">
																					<table
																						class="table table-striped table-bordered table-hover table-full-width"
																						id="sample_1">
																						<thead>
																							<tr>
																								<th>#</th>
																								<th>Reminder Date & Time</th>
																								<th>Delete</th>
																							</tr>
																						</thead>
																						<tbody id="eventReminderSummary">

																						</tbody>
																					</table>
																				</div>
																			</div>
																			
																			<div class="row" id="repeatReminderSummaryDiv">
																				<div class="col-md-12">
																					<label id="repeatReminderSummary" class="recurrenceLabel"> </label>
																				</div>
																			</div>
																		</div>
																	
																	<!-- <div class="form-group">
																		<label> Reminder Date & Time </label>
																		<div class="row">
																			<div class="col-md-6">
																				<div class="form-group">
																					<input type="text" id="reminderDateTime"
																						class="form-control dateTimePicker">
																				</div>
																			</div>
																		</div>
																	</div> -->


																	<!-- <div class="form-group">
																		<label> Recurrence Pattern </label>
																		<div class="row">
																			<div class="col-xs-2">
																				<div class="radio clip-radio radio-primary">
																					<input type="radio" id="daily"
																						name="recurrencePattern" value="1"> <label
																						for="daily"> Daily </label>
																				</div>
																			</div>
																			<div class="col-xs-10 recurrencePatternDiv"
																				id="dailyDiv" style="display: none;">
																				<div class="row">
																					<div class="col-xs-1">
																						<div class="radio clip-radio radio-primary">
																							<input type="radio" id="recurrencePatternDaily1"
																								name="recurrencePatternDaily" value="1">
																							<label for="recurrencePatternDaily1"></label>
																						</div>
																					</div>
																					<div class="col-xs-6">
																						Every <input id="recurrencePatternDailyInput1"
																							type="text" maxlength="2" style="width: 15%">
																						day(s)
																					</div>
																				</div>

																				<div class="row">
																					<div class="col-xs-1">
																						<div class="radio clip-radio radio-primary">
																							<input type="radio" id="recurrencePatternDaily2"
																								name="recurrencePatternDaily" value="2">
																							<label for="recurrencePatternDaily2"> </label>
																						</div>
																					</div>
																					<div class="col-xs-6">
																						<input id="recurrencePatternDailyInput2"
																							type="text" maxlength="2" style="width: 15%">
																						Every weekday
																					</div>
																				</div>
																			</div>
																		</div>
																		<div class="row">
																			<div class="col-xs-2">
																				<div class="radio clip-radio radio-primary">
																					<input type="radio" id="weekly"
																						name="recurrencePattern" value="2"> <label
																						for="weekly"> Weekly </label>
																				</div>
																			</div>

																			<div class="col-xs-10 recurrencePatternDiv"
																				id="weeklyDiv" style="display: none;">
																				<div class="row">
																					<div class="col-md-3">
																						<div class="checkbox clip-check check-primary">
																							<input class="weeklyDays" type="checkbox"
																								id="monday" value="monday"> <label
																								for="monday"> Monday </label>
																						</div>
																					</div>
																					<div class="col-md-3">
																						<div class="checkbox clip-check check-primary">
																							<input class="weeklyDays" type="checkbox"
																								id="tuesday" value="tuesday"> <label
																								for="tuesday"> Tuesday </label>
																						</div>
																					</div>
																					<div class="col-md-3">
																						<div class="checkbox clip-check check-primary">
																							<input class="weeklyDays" type="checkbox"
																								id="wednesday" value="wednesday"> <label
																								for="wednesday"> Wednesday </label>
																						</div>
																					</div>
																					<div class="col-md-3">
																						<div class="checkbox clip-check check-primary">
																							<input class="weeklyDays" type="checkbox"
																								id="thursday" value="thursday"> <label
																								for="thursday"> Thursday </label>
																						</div>
																					</div>
																					<div class="col-md-3">
																						<div class="checkbox clip-check check-primary">
																							<input class="weeklyDays" type="checkbox"
																								id="friday" value="friday"> <label
																								for="friday"> Friday </label>
																						</div>
																					</div>
																					<div class="col-md-3">
																						<div class="checkbox clip-check check-primary">
																							<input class="weeklyDays" type="checkbox"
																								id="saturday" value="saturday"> <label
																								for="saturday"> Saturday </label>
																						</div>
																					</div>
																					<div class="col-md-3">
																						<div class="checkbox clip-check check-primary">
																							<input class="weeklyDays" type="checkbox"
																								id="sunday" value="sunday"> <label
																								for="sunday"> Sunday </label>
																						</div>
																					</div>
																				</div>
																			</div>
																		</div>
																		<div class="row">
																			<div class="col-xs-2">
																				<div class="radio clip-radio radio-primary">
																					<input type="radio" id="monthly"
																						name="recurrencePattern" value="3"> <label
																						for="monthly"> Monthly </label>
																				</div>
																			</div>
																			<div class="col-xs-10 recurrencePatternDiv"
																				id="monthlyDiv" style="display: none;">
																				<div class="row">
																					<div class="col-xs-2">
																						<div class="radio clip-radio radio-primary">
																							<input type="radio"
																								id="recurrencePatternMonthly1"
																								name="recurrencePatternMonthly" value="1">
																							<label for="recurrencePatternMonthly1">
																								Day </label>
																						</div>
																					</div>

																					<div class="col-xs-3">
																						<input id="recurrencePatternMonthlyInput1"
																							type="text" maxlength="2" style="width: 35%">
																						of every
																					</div>
																					<div class="col-xs-3">
																						<input id="recurrencePatternMonthlyInput2"
																							type="text" maxlength="2" style="width: 35%">
																						months
																					</div>
																				</div>

																				<div class="row">
																					<div class="col-xs-2">
																						<div class="radio clip-radio radio-primary">
																							<input type="radio"
																								id="recurrencePatternMonthly2"
																								name="recurrencePatternMonthly" value="2">
																							<label for="recurrencePatternMonthly2">
																								The </label>
																						</div>
																					</div>

																					<div class="col-xs-6">
																						<select id="recurrencePatternMonthlySelect1">
																							<option>First</option>
																							<option>Second</option>
																							<option>Third</option>
																							<option>Fourth</option>
																							<option>Last</option>
																						</select> <select id="recurrencePatternMonthlySelect2">
																							<option>Monday</option>
																							<option>Tuesday</option>
																							<option>Wednesday</option>
																							<option>Thursday</option>
																							<option>Friday</option>
																							<option>Saturday</option>
																							<option>Sunday</option>
																						</select> of every
																					</div>
																					<div class="col-xs-3">
																						<input id="recurrencePatternMonthlyInput3"
																							type="text" maxlength="2" style="width: 35%">
																						months
																					</div>
																				</div>

																			</div>
																		</div>
																		<div class="row">
																			<div class="col-xs-2">
																				<div class="radio clip-radio radio-primary">
																					<input type="radio" id="yearly"
																						name="recurrencePattern" value="4"> <label
																						for="yearly"> Yearly </label>
																				</div>
																			</div>
																			<div class="col-xs-10 recurrencePatternDiv"
																				id="yearlyDiv" style="display: none;">

																				<div class="row">
																					<div class="col-xs-6">
																						Recur every <input
																							id="recurrencePatternYearlyInput1" type="text"
																							maxlength="2" style="width: 15%"> year(s)
																					</div>
																				</div>

																				<div class="row">
																					<div class="col-xs-3">
																						<div class="radio clip-radio radio-primary">
																							<input type="radio" id="recurrencePatternYearly1"
																								name="recurrencePatternYearly" value="1">
																							<label for="recurrencePatternYearly1"> On
																							</label>
																						</div>
																					</div>

																					<div class="col-xs-3">
																						<select id="recurrencePatternYearlySelect1">
																							<option>January</option>
																							<option>February</option>
																							<option>March</option>
																							<option>April</option>
																							<option>May</option>
																							<option>June</option>
																							<option>July</option>
																							<option>August</option>
																							<option>September</option>
																							<option>Octomber</option>
																							<option>November</option>
																							<option>December</option>
																						</select>
																					</div>
																					<div class="col-xs-3">
																						<input id="recurrencePatternYearlyInput2"
																							type="text" maxlength="2" style="width: 35%">
																					</div>
																				</div>

																				<div class="row">
																					<div class="col-xs-3">
																						<div class="radio clip-radio radio-primary">
																							<input type="radio" id="recurrencePatternYearly2"
																								name="recurrencePatternYearly" value="2">
																							<label for="recurrencePatternYearly2"> On
																								The </label>
																						</div>
																					</div>

																					<div class="col-xs-5">
																						<select id="recurrencePatternYearlySelect2">
																							<option>First</option>
																							<option>Second</option>
																							<option>Third</option>
																							<option>Fourth</option>
																							<option>Last</option>
																						</select> <select id="recurrencePatternYearlySelect3">
																							<option>Monday</option>
																							<option>Tuesday</option>
																							<option>Wednesday</option>
																							<option>Thursday</option>
																							<option>Friday</option>
																							<option>Saturday</option>
																							<option>Sunday</option>
																						</select> of
																					</div>
																					<div class="col-xs-3">
																						<select id="recurrencePatternYearlySelect4">
																							<option>January</option>
																							<option>February</option>
																							<option>March</option>
																							<option>April</option>
																							<option>May</option>
																							<option>June</option>
																							<option>July</option>
																							<option>August</option>
																							<option>September</option>
																							<option>Octomber</option>
																							<option>November</option>
																							<option>December</option>
																						</select>
																					</div>
																				</div>

																			</div>
																		</div>
																	</div> -->
																</div>
																<div class="modal-footer">
																	<button type="button" class="btn btn-danger btn-o delete-event" id="deleteReminderBtn"
																	data-toggle="modal" data-target="#deleteReminderModal" style="display:none;">Delete</button>
																	<button class="btn btn-primary btn-o save-event" id="saveReminderBtn"
																		onclick="saveReminder()" type="button">Save</button>
																</div>
																</form>
															</div>
														</div>
													</div>
												</div>
												<!-- end: EVENTS ASIDE -->

												<div class="ps-scrollbar-x-rail"
													style="left: 0px; bottom: 3px;">
													<div class="ps-scrollbar-x" style="left: 0px; width: 0px;"></div>
												</div>
												<div class="ps-scrollbar-y-rail"
													style="top: 0px; right: 3px;">
													<div class="ps-scrollbar-y" style="top: 0px; height: 0px;"></div>
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

	<!-- start: MAIN JAVASCRIPTS -->
	<script
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
		src="<c:url value="/indus/resources/vendor/switchery/switchery.min.js"/>"></script>
	<!-- end: MAIN JAVASCRIPTS -->

	<!-- start: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
	<script
		src="<c:url value="/indus/resources/vendor/jquery-ui/jquery-ui-1.10.2.custom.min.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/moment/moment.min.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/jquery-validation/jquery.validate.min.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/fullcalendar/fullcalendar.min.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"/>"></script>
	<!-- end: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
	<!-- start: CLIP-TWO JAVASCRIPTS -->
	<script src="<c:url value="/indus/resources/assets/js/main.js"/>"></script>
	<!-- start: JavaScript Event Handlers for this page -->
	<script
		src="<c:url value="/indus/resources/assets/js/pages-calendar.js"/>"></script>

	 <%--<script
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
		src="<c:url value="/indus/resources/vendor/bootstrap-timepicker/bootstrap-timepicker.min.js"/>"></script> --%>

	<script src="<c:url value="/indus/resources/custom_js/indus_patient_reminders.js"/>"></script>
	<script>
		jQuery(document).ready(function() {
			//Main.init();
			setTimeout(function(){ Calendar.init(); }, 1000);
			//FormElements.init();
			$('.dateTimePicker').datetimepicker();
			$('.dropdown-toggle').dropdown();
			/* $('.timePicker').datetimepicker({
				 datepicker:false
			}); */
			//jQuery.noConflict();
		});
	</script>

</body>
</html>
