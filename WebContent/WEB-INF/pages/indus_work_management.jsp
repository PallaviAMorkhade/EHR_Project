<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Add by Amol Saware For Auto-Suggestion -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/assets/css/bootstrap-chosen.css"/>" />

<script
	src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>"></script>

<%
	String clientFirstName = (String) session.getAttribute("userFirstName");
	String isLeader = (String) session.getAttribute("isLeader");
	String userId = (String) session.getAttribute("userId");
	String userTypeId = (String) session.getAttribute("userTypeId");
%>
<body>
	<div id="app">
		<div class="app-content">
			<div>
				<div class="wrap-content container" id="container">
					<div class="container-fluid container-fullw bg-white">
						<input type="hidden" id="userId" value="<%=userId%>" /> <input
							type="hidden" id="isLeader" value="<%=isLeader%>" /> <input
							type="hidden" id="userTypeId" value="<%=userTypeId%>" /> <input
							type="hidden" id="currentWorkStatusId" />
						<div class="row">
						
							<div class="col-md-3 col-sm-6 col-xs-12 margin-top-3">
								<div class="form-group">
									<h5 class="text-bold">Client Name</h5>
									<input id="searchByName" class="form-control" placeholder="Search"
									onkeyup='searchByName()'>
								</div>
							</div>
							
							<div class="col-md-2 col-sm-6 col-xs-12 margin-top-3">
								<div class="form-group">
									<h5 class="text-bold">EHR Id</h5>
									<input id="searchByClientId" class="form-control" placeholder="Search">
								</div>
							</div>
							
							<div class="col-md-2 col-sm-6 col-xs-12 margin-top-3">
								<div class="form-group">
									<h5 class="text-bold">Visit Id</h5>
									<input id="searchByVisitId" class="form-control" placeholder="Search">
								</div>
							</div>
							
							<div class="col-md-3 col-sm-6 col-xs-12 margin-top-3">
								<div class="form-group">
									<h5 class="text-bold">Member Id</h5>
									<input id="searchByMemberId" class="form-control" placeholder="Search">
								</div>
							</div>

							<div class="col-md-1 col-sm-6 col-xs-12 margin-top-3" style="margin-top:30px;">
								<div class="form-group">
									<button onclick="getClientStatus()"
										class="btn btn-o btn-primary btn-sm">
										<span> Status </span>
									</button>
								</div>
							</div>

							<div class="col-md-12">
								<div class="tabbable">
									<ul class="nav nav-tabs">

										<%
											if (userTypeId.equals("1") || userTypeId.equals("8") || userTypeId.equals("11")
													|| userTypeId.equals("12")) {
										%>
										<li id="reportVeriTab" class="active" onclick="getWork('1')"><a
											href="#reportVerificationTab" data-toggle="tab"> Report
												Verification </a></li>
										<%
											}
											if (userTypeId.equals("1") || userTypeId.equals("8") || userTypeId.equals("11")) {
										%>
										<li id="dateEntrTab" onclick="getWork('4')"><a
											href="#dataEntryTab" data-toggle="tab"> Data Entry </a></li>
										<li id="dateEntrVerTab" onclick="getWork('7')"><a
											href="#dataEntryVerificationTab" data-toggle="tab"> Data
												Entry Verification </a></li>
										<%
											}
											if (userTypeId.equals("1") || userTypeId.equals("7") || userTypeId.equals("10")) {
										%>
										<li id="doctorAnaTab" class="active" onclick="getWork('10')"><a
											href="#doctorAnalysisTab" data-toggle="tab"> Doctor
												Analysis </a></li>
										<li id="engmntTab" onclick="getWork('16')"><a
											href="#engagementTab" data-toggle="tab"> Engagement </a></li>
										<li id="healthRepoTab" onclick="getWork('12')"><a
											href="#healthReportTab" data-toggle="tab"> Health Report
										</a></li>
										<li id="healthReportVerTab" onclick="getWork('14')"><a
											href="#healthReportVerificationTab" data-toggle="tab">
												Health Report Verification </a></li>
										<%
											}
										%>
									</ul>
									<div class="tab-content">
										<div class="tab-pane fade in active"
											id="reportVerificationTab">
											<div class="row">
												<div class="col-md-6 leaderDiv" style="display: none;">
													<div class="form-group">
														<!-- <label class="block">
																Gender
															</label> -->
														<div class="clip-radio radio-primary">
															<input type="radio" value="1" id="status_1"
																onclick="getWork('1')" name="reportVerification"
																checked="checked"> <label for="status_1">
																Pending Work </label> <input type="radio" value="2"
																id="status_2" onclick="getWork('2')"
																name="reportVerification"> <label for="status_2">
																Assigned Work </label> <input onclick="getWork('3')"
																type="radio" value="3" id="status_3"
																name="reportVerification"> <label for="status_3">
																Status </label>
														</div>
													</div>
												</div>

												<div class="col-md-6 leaderDiv leaderDivBtn"
													style="display: none;">

													<button onclick="assignWorkModal('2')"
														style="margin-right: 5px;"
														class="btn btn-o btn-primary btn-sm pull-right">
														<i class="fa fa-share"></i> <span> Assign To </span>
													</button>

													<button onclick="checkedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-ok-circle"></i> <span>
															Checked All </span>
													</button>

													<button onclick="unCheckedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-remove-circle"></i> <span>
															UnChecked All </span>
													</button>
													
													<button onclick="closeVisit(1)"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-remove-circle"></i> <span>
															Close Visit </span>
													</button>

												</div>

												<div class="col-md-offset-8 col-md-4 myWorkDiv"
													style="margin-bottom: 10px; display: none;">

													<button onclick="submitTask('2')"
														style="margin-right: 5px;"
														class="btn btn-o btn-primary btn-sm pull-right">
														<i class="fa fa-share"></i> <span> Submit </span>
													</button>

													<button onclick="checkedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-ok-circle"></i> <span>
															Checked All </span>
													</button>

													<button onclick="unCheckedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-remove-circle"></i> <span>
															UnChecked All </span>
													</button>
																										

												</div>

												<div class="col-md-12 table-responsive">
													<table
														class="table table-striped table-bordered table-hover table-full-width">
														<thead>
															<tr>
																<th class="center" style="width: 6%;">#</th>
																<th>Client Name</th>
																<th>EHR ID</th>
																<th>Visit ID</th>
																<th>Member ID</th>
																<th>Package Name</th>
																<th>Checkup Date</th>
																<th class="assigned" style="display: none;">Assigned
																	Date</th>
																<th class="assigned" style="display: none;">Assigned
																	To</th>
																<th class="assigned" style="display: none;">Assigned
																	By</th>
																<th class="pending" style="display: none;">Action</th>
															</tr>
														</thead>
														<tbody id="reportPendingWork">
														</tbody>
													</table>
												</div>

											</div>
										</div>
										<div class="tab-pane fade" id="dataEntryTab">
											<div class="row">
												<div class="col-md-6 leaderDiv" style="display: none;">
													<div class="form-group">
														<!-- <label class="block">
																Gender
															</label> -->
														<div class="clip-radio radio-primary">
															<input type="radio" value="4" id="status_4"
																onclick="getWork('4')" name="dataEntry"
																checked="checked"> <label for="status_4">
																Pending Work </label> <input type="radio" value="5"
																id="status_5" onclick="getWork('5')" name="dataEntry">
															<label for="status_5"> Assigned Work </label> <input
																onclick="getWork('6')" type="radio" value="6"
																id="status_6" name="dataEntry"> <label
																for="status_6"> Status </label>
														</div>
													</div>
												</div>

												<div class="col-md-6 leaderDiv leaderDivBtn"
													style="display: none;">

													<button onclick="assignWorkModal('5')"
														style="margin-right: 5px;"
														class="btn btn-o btn-primary btn-sm pull-right">
														<i class="fa fa-share"></i> <span> Assign To </span>
													</button>

													<button onclick="checkedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-ok-circle"></i> <span>
															Checked All </span>
													</button>

													<button onclick="unCheckedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-remove-circle"></i> <span>
															UnChecked All </span>
													</button>
													
													<button onclick="closeVisit(4)"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-remove-circle"></i> <span>
															Close Visit </span>
													</button>

												</div>

												<div class="col-md-offset-8 col-md-4 myWorkDiv"
													style="margin-bottom: 10px; display: none;">

													<button onclick="submitTask('5')"
														style="margin-right: 5px;"
														class="btn btn-o btn-primary btn-sm pull-right">
														<i class="fa fa-share"></i> <span> Submit </span>
													</button>

													<button onclick="checkedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-ok-circle"></i> <span>
															Checked All </span>
													</button>

													<button onclick="unCheckedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-remove-circle"></i> <span>
															UnChecked All </span>
													</button>

												</div>

												<div class="col-md-12 table-responsive">
													<table
														class="table table-striped table-bordered table-hover table-full-width">
														<thead>
															<tr>
																<th class="center" style="width: 6%;">#</th>
																<th>Client Name</th>
																<th>EHR ID</th>
																<th>Visit ID</th>
																<th>Member Id</th>
																<th>Package Name</th>
																<th>Checkup Date</th>
																<th class="assigned" style="display: none;">Assigned
																	Date</th>
																<th class="assigned" style="display: none;">Assigned
																	To</th>
																<th class="assigned" style="display: none;">Assigned
																	By</th>
																<th class="pending" style="display: none;">Action</th>
															</tr>
														</thead>
														<tbody id="dataEntryTblBody">
														</tbody>
													</table>
												</div>

											</div>
										</div>
										<div class="tab-pane fade" id="dataEntryVerificationTab">
											<div class="row">
												<div class="col-md-6 leaderDiv" style="display: none;">
													<div class="form-group">
														<!-- <label class="block">
																Gender
															</label> -->
														<div class="clip-radio radio-primary">
															<input type="radio" value="7" id="status_7"
																onclick="getWork('7')" name="dataEntryVerification"
																checked="checked"> <label for="status_7">
																Pending Work </label> <input type="radio" value="8"
																id="status_8" onclick="getWork('8')"
																name="dataEntryVerification"> <label
																for="status_8"> Assigned Work </label> <input
																onclick="getWork('9')" type="radio" value="9"
																id="status_9" name="dataEntryVerification"> <label
																for="status_9"> Rejected </label>
														</div>
													</div>
												</div>

												<div class="col-md-6 leaderDiv leaderDivBtn"
													style="display: none;">

													<button onclick="assignWorkModal('8')"
														style="margin-right: 5px;"
														class="btn btn-o btn-primary btn-sm pull-right">
														<i class="fa fa-share"></i> <span> Assign To </span>
													</button>

													<button onclick="checkedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-ok-circle"></i> <span>
															Checked All </span>
													</button>

													<button onclick="unCheckedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-remove-circle"></i> <span>
															UnChecked All </span>
													</button>
													<button onclick="closeVisit(7)"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-remove-circle"></i> <span>
															Close Visit </span>
													</button>

												</div>

												<div class="col-md-offset-8 col-md-4 myWorkDiv"
													style="margin-bottom: 10px; display: none;">

													<button onclick="submitTask('8')"
														style="margin-right: 5px;"
														class="btn btn-o btn-primary btn-sm pull-right">
														<i class="fa fa-share"></i> <span> Submit </span>
													</button>

													<button onclick="checkedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-ok-circle"></i> <span>
															Checked All </span>
													</button>

													<button onclick="unCheckedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-remove-circle"></i> <span>
															UnChecked All </span>
													</button>

												</div>

												<div class="col-md-12 table-responsive">
													<table
														class="table table-striped table-bordered table-hover table-full-width">
														<thead>
															<tr>
																<th class="center" style="width: 6%;">#</th>
																<th>Client Name</th>
																<th>EHR ID</th>
																<th>Visit ID</th>
																<th>Member Id</th>
																<th>Package Name</th>
																<th>Checkup Date</th>
																<th class="assigned" style="display: none;">Assigned
																	Date</th>
																<th class="assigned" style="display: none;">Assigned
																	To</th>
																<th class="assigned" style="display: none;">Assigned
																	By</th>
																<th class="pending" style="display: none;">Action</th>
															</tr>
														</thead>
														<tbody id="dataEntryVerificationTblBody">
														</tbody>
													</table>
												</div>

											</div>
										</div>

										<div class="tab-pane fade" id="doctorAnalysisTab">
											<div class="row">
												<div class="col-md-6 leaderDiv" style="display: none;">
													<div class="form-group">
														<div class="clip-radio radio-primary">
															<input type="radio" value="10" id="status_10"
																onclick="getWork('10')" name="doctorAnalysis"
																checked="checked"> <label for="status_10">
																Pending Work </label> <input type="radio" value="11"
																id="status_11" onclick="getWork('11')"
																name="doctorAnalysis"> <label for="status_11">
																Assigned Work </label> <input type="radio" value="15" id="status_15"
																onclick="getWork('15')" name="doctorAnalysis"
																checked="checked"> <label for="status_15">
																Overall Pending Work </label>
														</div>
													</div>
												</div>

												<div class="col-md-6 leaderDiv leaderDivBtn"
													style="display: none;">

													<button onclick="assignWorkModal('11')"
														style="margin-right: 5px;"
														class="btn btn-o btn-primary btn-sm pull-right">
														<i class="fa fa-share"></i> <span> Assign To </span>
													</button>

													<button onclick="checkedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-ok-circle"></i> <span>
															Checked All </span>
													</button>

													<button onclick="unCheckedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-remove-circle"></i> <span>
															UnChecked All </span>
													</button>
													
													<button onclick="closeVisit(10)"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-remove-circle"></i> <span>
															Close Visit </span>
													</button>

												</div>

												<div class="col-md-offset-7 col-md-5 myWorkDiv"
													style="margin-bottom: 10px; display: none;">

													<button onclick="submitTask('11')"
														style="margin-right: 5px;"
														class="btn btn-o btn-primary btn-sm pull-right">
														<i class="fa fa-share"></i> <span> Submit </span>
													</button>

													<button onclick="checkedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-ok-circle"></i> <span>
															Checked All </span>
													</button>

													<button onclick="unCheckedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-remove-circle"></i> <span>
															UnChecked All </span>
													</button>

												</div>

												<div class="col-md-12 table-responsive">
													<table
														class="table table-striped table-bordered table-hover table-full-width">
														<thead>
															<tr>
																<th class="center" style="width: 6%;">#</th>
																<th>Client Name</th>
																<th>EHR ID</th>
																<th>Visit ID</th>
																<th>Member Id</th>
																<th>Package Name</th>
																<th>Checkup Date</th>
																<th class="assigned" style="display: none;">Assigned
																	Date</th>
																<th class="assigned" style="display: none;">Assigned
																	To</th>
																<th class="assigned" style="display: none;">Assigned
																	By</th>
																<th class="pending" style="display: none;">Action</th>
															</tr>
														</thead>
														<tbody id="doctorAnalysisTblBody">
														</tbody>
													</table>
												</div>

											</div>
										</div>
										
										<div class="tab-pane fade" id="engagementTab">
											<div class="row">
												
												<div class="col-md-offset-7 col-md-5"
													style="margin-bottom: 10px;">

													<button onclick="submitTask('12')"
														style="margin-right: 5px;"
														class="btn btn-o btn-primary btn-sm pull-right">
														<i class="fa fa-share"></i> <span> Submit </span>
													</button>

													<button onclick="checkedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-ok-circle"></i> <span>
															Checked All </span>
													</button>

													<button onclick="unCheckedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-remove-circle"></i> <span>
															UnChecked All </span>
													</button>
													
													<button onclick="closeVisit(16)"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-remove-circle"></i> <span>
															Close Visit </span>
													</button>

												</div>

												<div class="col-md-12 table-responsive">
													<table
														class="table table-striped table-bordered table-hover table-full-width">
														<thead>
															<tr>
																<th class="center" style="width: 6%;">#</th>
																<th>Client Name</th>
																<th>EHR ID</th>
																<th>Visit ID</th>
																<th>Member Id</th>
																<th>Package Name</th>
																<th>Checkup Date</th>
																<th>Status</th>
																<th class="pending" style="display: none;">Action</th>
															</tr>
														</thead>
														<tbody id="engagementTblBody">
														</tbody>
													</table>
												</div>

											</div>
										</div>

										<div class="tab-pane fade" id="healthReportTab">
											<div class="row">
												<div class="col-md-6 leaderDiv" style="display: none;">
													<div class="form-group">
														<!-- <label class="block">
																Gender
															</label> -->
														<div class="clip-radio radio-primary">
															<input type="radio" value="12" id="status_12"
																onclick="getWork('12')" name="healthReport"
																checked="checked"> <label for="status_12">
																Pending Work </label> <input type="radio" value="13"
																id="status_13" onclick="getWork('13')"
																name="healthReport"> <label for="status_13">
																Assigned Work </label>
														</div>
													</div>
												</div>

												<div class="col-md-6 leaderDiv leaderDivBtn"
													style="display: none;">

													<button onclick="assignWorkModal('13')"
														style="margin-right: 5px;"
														class="btn btn-o btn-primary btn-sm pull-right">
														<i class="fa fa-share"></i> <span> Assign To </span>
													</button>

													<button onclick="checkedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-ok-circle"></i> <span>
															Checked All </span>
													</button>

													<button onclick="unCheckedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-remove-circle"></i> <span>
															UnChecked All </span>
													</button>
													
													<button onclick="closeVisit(12)"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-remove-circle"></i> <span>
															Close Visit </span>
													</button>

												</div>

												<div class="col-md-offset-7 col-md-5 myWorkDiv"
													style="margin-bottom: 10px; display: none;">

													<button onclick="submitTask('13')"
														style="margin-right: 5px;"
														class="btn btn-o btn-primary btn-sm pull-right">
														<i class="fa fa-share"></i> <span> Submit </span>
													</button>

													<button onclick="checkedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-ok-circle"></i> <span>
															Checked All </span>
													</button>

													<button onclick="unCheckedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-remove-circle"></i> <span>
															UnChecked All </span>
													</button>

												</div>

												<div class="col-md-12 table-responsive">
													<table
														class="table table-striped table-bordered table-hover table-full-width">
														<thead>
															<tr>
																<th class="center" style="width: 6%;">#</th>
																<th>Client Name</th>
																<th>EHR ID</th>
																<th>Visit ID</th>
																<th>Member Id</th>
																<th>Package Name</th>
																<th>Checkup Date</th>
																<th class="assigned" style="display: none;">Assigned
																	Date</th>
																<th class="assigned" style="display: none;">Assigned
																	To</th>
																<th class="assigned" style="display: none;">Assigned
																	By</th>
																<th class="pending" style="display: none;">Action</th>
															</tr>
														</thead>
														<tbody id="healthReportTblBody">
														</tbody>
													</table>
												</div>

											</div>
										</div>

										<div class="tab-pane fade" id="healthReportVerificationTab">
											<div class="row">
												<div class="col-md-6 leaderDiv" style="display: none;">
													<div class="form-group">
														<!-- <label class="block">
																Gender
															</label> -->
														<div class="clip-radio radio-primary">
															<input type="radio" value="14" id="status_14"
																onclick="getWork('14')" name="healthReportVerification"
																checked="checked"> <label for="status_14">
																Pending Work </label> <input type="radio" value="15"
																id="status_15" onclick="getWork('15')"
																name="healthReportVerification"> <label
																for="status_15"> Assigned Work </label>
														</div>
													</div>
												</div>

												<div class="col-md-6 leaderDiv leaderDivBtn"
													style="display: none;">

													<button onclick="assignWorkModal('15')"
														style="margin-right: 5px;"
														class="btn btn-o btn-primary btn-sm pull-right">
														<i class="fa fa-share"></i> <span> Assign To </span>
													</button>

													<button onclick="checkedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-ok-circle"></i> <span>
															Checked All </span>
													</button>

													<button onclick="unCheckedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-remove-circle"></i> <span>
															UnChecked All </span>
													</button>
													
													<button onclick="closeVisit(14)"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-remove-circle"></i> <span>
															Close Visit </span>
													</button>

												</div>

												<div class="col-md-offset-7 col-md-5 myWorkDiv"
													style="margin-bottom: 10px; display: none;">

													<button onclick="submitTask('15')"
														style="margin-right: 5px;"
														class="btn btn-o btn-primary btn-sm pull-right">
														<i class="fa fa-share"></i> <span> Submit </span>
													</button>

													<button onclick="checkedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-ok-circle"></i> <span>
															Checked All </span>
													</button>

													<button onclick="unCheckedAll('pendingChk')"
														style="margin-right: 5px;"
														class="btn btn-o btn-danger btn-sm pull-right">
														<i class="glyphicon glyphicon-remove-circle"></i> <span>
															UnChecked All </span>
													</button>

												</div>

												<div class="col-md-12 table-responsive">
													<table
														class="table table-striped table-bordered table-hover table-full-width">
														<thead>
															<tr>
																<th class="center" style="width: 6%;">#</th>
																<th>Client Name</th>
																<th>EHR ID</th>
																<th>Visit ID</th>
																<th>Member Id</th>
																<th>Package Name</th>
																<th>Checkup Date</th>
																<th class="assigned" style="display: none;">Assigned
																	Date</th>
																<th class="assigned" style="display: none;">Assigned
																	To</th>
																<th class="assigned" style="display: none;">Assigned
																	By</th>
																<th class="pending" style="display: none;">Action</th>
															</tr>
														</thead>
														<tbody id="healthReportVerificationTblBody">
														</tbody>
													</table>
												</div>

											</div>
										</div>

										<div class="row">
											<div class="col-md-5 pull-right">
												<ul id="pagination" class="pagination-sm"></ul>
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
	</div>

	<!-- Add by Amol Saware For Auto-Suggestion -->
	<script
		src="<c:url value="/indus/resources/assets/js/chosen.jquery.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/bootstrap/js/bootstrap.min.js"/>"></script>

	<!-- Add by Amol Saware For Pagination -->
	<script
		src="<c:url value="/indus/resources/vendor/twbs-pagination/jquery.twbsPagination.min.js"/>"></script>

	<script
		src="<c:url value="/indus/resources/custom_js/indus_work_management.js"/>"></script>
		
	<script
		src="<c:url value="/indus/resources/custom_js/indus_verification.js"/>"></script>

	<script>
		jQuery(document).ready(function() {
			//jQuery.noConflict();
			
			//For Menu DropDown
			$('.dropdown-toggle').dropdown();
			
			//for handling Analysis modal hide
			$(document).on('hide.bs.modal','#analysisModal', function () {
				jQuery.ajax({
					type : "POST",
					url : "client/removeClientLocked",
					data : {
						"clientId" : $("#clientMasterId").html()
					},
					error : function() {
						//alert("error in webservice");
					},
					success : function(r) {
						//alert("success");
					}
				});
			});
			
		});
	</script>

</body>
</html>
