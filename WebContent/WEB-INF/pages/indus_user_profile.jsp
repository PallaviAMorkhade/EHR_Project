<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<!--<![endif]-->
<!-- start: HEAD -->
<head>
<title>User Profile</title>

<!-- start: CSS REQUIRED FOR THIS PAGE ONLY -->

<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/vendor/bootstrap-fileinput/jasny-bootstrap.min.css"/>"
	rel="stylesheet" media="screen">

<!-- end: CSS REQUIRED FOR THIS PAGE ONLY -->
</head>
<!-- end: HEAD -->
<body>
	<div id="app">
		<!-- sidebar -->
		<jsp:include page="indus_header.jsp"></jsp:include>
		<!-- / sidebar -->
		<div class="app-content">
			<!-- start: TOP NAVBAR -->
			<jsp:include page="indus_left_menu.jsp"></jsp:include>
			<!-- end: TOP NAVBAR -->
			<div class="main-content">
				<div class="wrap-content container" id="container">
					<!-- start: PAGE TITLE -->
					<section id="page-title">
						<div class="row">
							<div class="col-sm-8">
								<h1 class="mainTitle">Patient Activity Workbench</h1>
								
							</div>
							<!-- <ol class="breadcrumb">
								<li><span>Pages</span></li>
								<li class="active"><span>User Profile</span></li>
							</ol> -->

							<!-- start: WIZARD DEMO -->
							<div class="container-fluid container-fullw bg-white">
								<div class="row">
									<div class="col-md-12">
										<!-- start: WIZARD FORM -->
											<div id="wizard" class="swMain">
												<!-- start: WIZARD SEPS -->
												<ul>
													<li><a href="#step-1">
															<div class="stepNumber">1</div> <span class="stepDesc"><small>
																	Patient Details </small></span>
													</a></li>
													<li><a href="#step-2">
															<div class="stepNumber">2</div> <span class="stepDesc">
																<small> HRA </small>
														</span>
													</a></li>
													<li><a href="#step-3">
															<div class="stepNumber">3</div> <span class="stepDesc">
																<small> Test Conducted </small>
														</span>
													</a></li>
													<li><a href="#step-4">
															<div class="stepNumber">4</div> <span class="stepDesc">
																<small> Verification </small>
														</span>
													</a></li>
													<li><a href="#step-5">
															<div class="stepNumber">5</div> <span class="stepDesc">
																<small> Patient Contacted </small>
														</span>
													</a></li>
												</ul>
												<!-- end: WIZARD SEPS -->
											</div>
									</div>
								</div>
							</div>

						</div>
					</section>
					<!-- end: PAGE TITLE -->
					<!-- start: USER PROFILE -->
					<div class="container-fluid container-fullw bg-white">
						<div class="row">
							<div class="col-md-12">
								<div class="tabbable">
									<ul class="nav nav-tabs tab-padding tab-space-3 tab-blue"
										id="myTab4">
										<li class="active"><a data-toggle="tab"
											href="#panel_overview"> Demographics </a></li>
										<li><a data-toggle="tab" href="#panel_edit_account">
												Health Risk Assesment </a></li>
										<li><a data-toggle="tab" href="#panel_projects">
												Upload Details </a></li>
										<li><a data-toggle="tab" href="#panel_verification">
												Verification </a></li>
										<li><a data-toggle="tab" href="#panel_other"> Other
												Details </a></li>
										<li><a data-toggle="tab" href="#panel_reminders">
												Reminders </a></li>
										<li><a data-toggle="tab" href="#panel_callLog"> Call
												log </a></li>
									</ul>
									<div class="tab-content">
										<div id="panel_overview" class="tab-pane fade in active">
											<div class="row">
												<div class="col-sm-5 col-md-4">
													<div class="user-left">
														<div class="center">
															<h4>Peter Clark</h4>
															<div class="fileinput fileinput-new"
																data-provides="fileinput">
																<div class="user-image">
																	<div class="fileinput-new thumbnail">
																		<img src="../resources/assets/images/avatar-1-xl.jpg"
																			alt="">
																	</div>
																	<div
																		class="fileinput-preview fileinput-exists thumbnail"></div>
																	<div class="user-image-buttons">
																		<span class="btn btn-azure btn-file btn-sm"><span
																			class="fileinput-new"><i class="fa fa-pencil"></i></span><span
																			class="fileinput-exists"><i
																				class="fa fa-pencil"></i></span> <input type="file">
																		</span> <a href="#"
																			class="btn fileinput-exists btn-red btn-sm"
																			data-dismiss="fileinput"> <i class="fa fa-times"></i>
																		</a>
																	</div>
																</div>
															</div>
															<hr>
															<div class="social-icons block">
																<ul>
																	<li data-placement="top" data-original-title="Twitter"
																		class="social-twitter tooltips"><a
																		href="http://www.twitter.com" target="_blank">
																			Twitter </a></li>
																	<li data-placement="top" data-original-title="Facebook"
																		class="social-facebook tooltips"><a
																		href="http://facebook.com" target="_blank">
																			Facebook </a></li>
																	<li data-placement="top" data-original-title="Google"
																		class="social-google tooltips"><a
																		href="http://google.com" target="_blank"> Google+
																	</a></li>
																	<li data-placement="top" data-original-title="LinkedIn"
																		class="social-linkedin tooltips"><a
																		href="http://linkedin.com" target="_blank">
																			LinkedIn </a></li>
																	<li data-placement="top" data-original-title="Github"
																		class="social-github tooltips"><a href="#"
																		target="_blank"> Github </a></li>
																</ul>
															</div>
															<hr>
														</div>
														<table class="table table-condensed">
															<thead>
																<tr>
																	<th colspan="3">Contact Information</th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<td>url</td>
																	<td><a href="#"> www.example.com </a></td>
																	<td><a href="#panel_edit_account" class="show-tab"><i
																			class="fa fa-pencil edit-user-info"></i></a></td>
																</tr>
																<tr>
																	<td>email:</td>
																	<td><a href=""> peter@example.com </a></td>
																	<td><a href="#panel_edit_account" class="show-tab"><i
																			class="fa fa-pencil edit-user-info"></i></a></td>
																</tr>
																<tr>
																	<td>phone:</td>
																	<td>(641)-734-4763</td>
																	<td><a href="#panel_edit_account" class="show-tab"><i
																			class="fa fa-pencil edit-user-info"></i></a></td>
																</tr>
																<tr>
																	<td>skye</td>
																	<td><a href=""> peterclark82 </a></td>
																	<td><a href="#panel_edit_account" class="show-tab"><i
																			class="fa fa-pencil edit-user-info"></i></a></td>
																</tr>
															</tbody>
														</table>
														<table class="table">
															<thead>
																<tr>
																	<th colspan="3">General information</th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<td>Position</td>
																	<td>UI Designer</td>
																	<td><a href="#panel_edit_account" class="show-tab"><i
																			class="fa fa-pencil edit-user-info"></i></a></td>
																</tr>
																<tr>
																	<td>Last Logged In</td>
																	<td>56 min</td>
																	<td><a href="#panel_edit_account" class="show-tab"><i
																			class="fa fa-pencil edit-user-info"></i></a></td>
																</tr>
																<tr>
																	<td>Position</td>
																	<td>Senior Marketing Manager</td>
																	<td><a href="#panel_edit_account" class="show-tab"><i
																			class="fa fa-pencil edit-user-info"></i></a></td>
																</tr>
																<tr>
																	<td>Supervisor</td>
																	<td><a href="#"> Kenneth Ross </a></td>
																	<td><a href="#panel_edit_account" class="show-tab"><i
																			class="fa fa-pencil edit-user-info"></i></a></td>
																</tr>
																<tr>
																	<td>Status</td>
																	<td><span class="label label-sm label-info">Administrator</span></td>
																	<td><a href="#panel_edit_account" class="show-tab"><i
																			class="fa fa-pencil edit-user-info"></i></a></td>
																</tr>
															</tbody>
														</table>
														<table class="table">
															<thead>
																<tr>
																	<th colspan="3">Additional information</th>
																</tr>
															</thead>
															<tbody>
																<tr>
																	<td>Birth</td>
																	<td>21 October 1982</td>
																	<td><a href="#panel_edit_account" class="show-tab"><i
																			class="fa fa-pencil edit-user-info"></i></a></td>
																</tr>
																<tr>
																	<td>Groups</td>
																	<td>New company web site development, HR
																		Management</td>
																	<td><a href="#panel_edit_account" class="show-tab"><i
																			class="fa fa-pencil edit-user-info"></i></a></td>
																</tr>
															</tbody>
														</table>
													</div>
												</div>
												<div class="col-sm-7 col-md-8">
													<div class="row space20">
														<div class="col-sm-3">
															<button
																class="btn btn-icon margin-bottom-5 margin-bottom-5 btn-block">
																<i
																	class="ti-layers-alt block text-primary text-extra-large margin-bottom-10"></i>
																Projects
															</button>
														</div>
														<div class="col-sm-3">
															<button class="btn btn-icon margin-bottom-5 btn-block">
																<i
																	class="ti-comments block text-primary text-extra-large margin-bottom-10"></i>
																Messages <span class="badge badge-danger"> 23 </span>
															</button>
														</div>
														<div class="col-sm-3">
															<button class="btn btn-icon margin-bottom-5 btn-block">
																<i
																	class="ti-calendar block text-primary text-extra-large margin-bottom-10"></i>
																Calendar
															</button>
														</div>
														<div class="col-sm-3">
															<button class="btn btn-icon margin-bottom-5 btn-block">
																<i
																	class="ti-flag block text-primary text-extra-large margin-bottom-10"></i>
																Notifications
															</button>
														</div>
													</div>
													<div class="panel panel-white" id="activities">
														<div class="panel-heading border-light">
															<h4 class="panel-title text-primary">Recent
																Activities</h4>
															<paneltool class="panel-tools"
																tool-collapse="tool-collapse" tool-refresh="load1"
																tool-dismiss="tool-dismiss"></paneltool>
														</div>
														<div collapse="activities" ng-init="activities=false"
															class="panel-wrapper">
															<div class="panel-body">
																<ul class="timeline-xs">
																	<li class="timeline-item success">
																		<div class="margin-left-15">
																			<div class="text-muted text-small">2 minutes
																				ago</div>
																			<p>
																				<a class="text-info" href> Steven </a> has completed
																				his account.
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
																			<div class="text-muted text-small">Thu, 12 Jun
																			</div>
																			<p>
																				Contacted <a class="text-info" href> Microsoft </a>
																				for license upgrades.
																			</p>
																		</div>
																	</li>
																	<li class="timeline-item">
																		<div class="margin-left-15">
																			<div class="text-muted text-small">Tue, 10 Jun
																			</div>
																			<p>Started development new site</p>
																		</div>
																	</li>
																	<li class="timeline-item">
																		<div class="margin-left-15">
																			<div class="text-muted text-small">Sun, 11 Apr
																			</div>
																			<p>
																				Lunch with <a class="text-info" href> Nicole </a> .
																			</p>
																		</div>
																	</li>
																	<li class="timeline-item warning">
																		<div class="margin-left-15">
																			<div class="text-muted text-small">Wed, 25 Mar
																			</div>
																			<p>server Maintenance.</p>
																		</div>
																	</li>
																	<li class="timeline-item">
																		<div class="margin-left-15">
																			<div class="text-muted text-small">Fri, 20 Mar
																			</div>
																			<p>
																				New User Registration <a class="text-info" href>
																					more details </a> .
																			</p>
																		</div>
																	</li>
																</ul>
															</div>
														</div>
													</div>
													<div class="panel panel-white space20">
														<div class="panel-heading">
															<h4 class="panel-title">Recent Tweets</h4>
														</div>
														<div class="panel-body">
															<ul class="ltwt">
																<li class="ltwt_tweet">
																	<p class="ltwt_tweet_text">
																		<a href class="text-info"> @Shakespeare </a> Some are
																		born great, some achieve greatness, and some have
																		greatness thrust upon them.
																	</p> <span class="block text-light"><i
																		class="fa fa-fw fa-clock-o"></i> 2 minuts ago</span>
																</li>
															</ul>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div id="panel_edit_account" class="tab-pane fade">
											<form action="#" role="form" id="form">
												<fieldset>
													<legend> Account Info </legend>
													<div class="row">
														<div class="col-md-6">
															<div class="form-group">
																<label class="control-label"> First Name </label> <input
																	type="text" placeholder="Peter" class="form-control"
																	id="firstname" name="firstname">
															</div>
															<div class="form-group">
																<label class="control-label"> Last Name </label> <input
																	type="text" placeholder="Clark" class="form-control"
																	id="lastname" name="lastname">
															</div>
															<div class="form-group">
																<label class="control-label"> Email Address </label> <input
																	type="email" placeholder="peter@example.com"
																	class="form-control" id="email" name="email">
															</div>
															<div class="form-group">
																<label class="control-label"> Phone </label> <input
																	type="email" placeholder="(641)-734-4763"
																	class="form-control" id="phone" name="email">
															</div>
															<div class="form-group">
																<label class="control-label"> Password </label> <input
																	type="password" placeholder="password"
																	class="form-control" name="password" id="password">
															</div>
															<div class="form-group">
																<label class="control-label"> Confirm Password </label>
																<input type="password" placeholder="password"
																	class="form-control" id="password_again"
																	name="password_again">
															</div>
														</div>
														<div class="col-md-6">
															<div class="form-group">
																<label class="control-label"> Gender </label>
																<div class="clip-radio radio-primary">
																	<input type="radio" value="female" name="gender"
																		id="us-female"> <label for="us-female">
																		Female </label> <input type="radio" value="male" name="gender"
																		id="us-male" checked> <label for="us-male">
																		Male </label>
																</div>
															</div>
															<div class="row">
																<div class="col-md-4">
																	<div class="form-group">
																		<label class="control-label"> Zip Code </label> <input
																			class="form-control" placeholder="12345" type="text"
																			name="zipcode" id="zipcode">
																	</div>
																</div>
																<div class="col-md-8">
																	<div class="form-group">
																		<label class="control-label"> City </label> <input
																			class="form-control tooltips"
																			placeholder="London (UK)" type="text"
																			data-original-title="We'll display it when you write reviews"
																			data-rel="tooltip" title="" data-placement="top"
																			name="city" id="city">
																	</div>
																</div>
															</div>
															<div class="form-group">
																<label> Image Upload </label>
																<div class="fileinput fileinput-new"
																	data-provides="fileinput">
																	<div class="fileinput-new thumbnail">
																		<img src="assets/images/avatar-1-xl.jpg" alt="">
																	</div>
																	<div
																		class="fileinput-preview fileinput-exists thumbnail"></div>
																	<div class="user-edit-image-buttons">
																		<span class="btn btn-azure btn-file"><span
																			class="fileinput-new"><i class="fa fa-picture"></i>
																				Select image</span><span class="fileinput-exists"><i
																				class="fa fa-picture"></i> Change</span> <input type="file">
																		</span> <a href="#" class="btn fileinput-exists btn-red"
																			data-dismiss="fileinput"> <i class="fa fa-times"></i>
																			Remove
																		</a>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</fieldset>
												<fieldset>
													<legend> Additional Info </legend>
													<div class="row">
														<div class="col-md-6">
															<div class="form-group">
																<label class="control-label"> Twitter </label> <span
																	class="input-icon"> <input class="form-control"
																	type="text" placeholder="Text Field"> <i
																	class="fa fa-twitter"></i>
																</span>
															</div>
															<div class="form-group">
																<label class="control-label"> Facebook </label> <span
																	class="input-icon"> <input class="form-control"
																	type="text" placeholder="Text Field"> <i
																	class="fa fa-facebook"></i>
																</span>
															</div>
															<div class="form-group">
																<label class="control-label"> Google Plus </label> <span
																	class="input-icon"> <input class="form-control"
																	type="text" placeholder="Text Field"> <i
																	class="fa fa-google-plus"></i>
																</span>
															</div>
														</div>
														<div class="col-md-6">
															<div class="form-group">
																<label class="control-label"> Github </label> <span
																	class="input-icon"> <input class="form-control"
																	type="text" placeholder="Text Field"> <i
																	class="fa fa-github"></i>
																</span>
															</div>
															<div class="form-group">
																<label class="control-label"> Linkedin </label> <span
																	class="input-icon"> <input class="form-control"
																	type="text" placeholder="Text Field"> <i
																	class="fa fa-linkedin"></i>
																</span>
															</div>
															<div class="form-group">
																<label class="control-label"> Skype </label> <span
																	class="input-icon"> <input class="form-control"
																	type="text" placeholder="Text Field"> <i
																	class="fa fa-skype"></i>
																</span>
															</div>
														</div>
													</div>
												</fieldset>
												<div class="row">
													<div class="col-md-12">
														<div>
															Required Fields
															<hr>
														</div>
													</div>
												</div>
												<div class="row">
													<div class="col-md-8">
														<p>By clicking UPDATE, you are agreeing to the Policy
															and Terms &amp; Conditions.</p>
													</div>
													<div class="col-md-4">
														<button class="btn btn-primary pull-right" type="submit">
															Update <i class="fa fa-arrow-circle-right"></i>
														</button>
													</div>
												</div>
											</form>
										</div>
										<div id="panel_projects" class="tab-pane fade">
											<table class="table" id="projects">
												<thead>
													<tr>
														<th>Project Name</th>
														<th class="hidden-xs">Client</th>
														<th>Proj Comp</th>
														<th class="hidden-xs">%Comp</th>
														<th class="hidden-xs center">Priority</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>IT Help Desk</td>
														<td class="hidden-xs">Master Company</td>
														<td>11 november 2014</td>
														<td class="hidden-xs">
															<div class="progress active progress-xs">
																<div style="width: 70%" aria-valuemax="100"
																	aria-valuemin="0" aria-valuenow="70" role="progressbar"
																	class="progress-bar progress-bar-warning">
																	<span class="sr-only"> 70% Complete (danger)</span>
																</div>
															</div>
														</td>
														<td class="center hidden-xs"><span
															class="label label-danger">Critical</span></td>
													</tr>
													<tr>
														<td>PM New Product Dev</td>
														<td class="hidden-xs">Brand Company</td>
														<td>12 june 2014</td>
														<td class="hidden-xs">
															<div class="progress active progress-xs">
																<div style="width: 40%" aria-valuemax="100"
																	aria-valuemin="0" aria-valuenow="40" role="progressbar"
																	class="progress-bar progress-bar-info">
																	<span class="sr-only"> 40% Complete</span>
																</div>
															</div>
														</td>
														<td class="center hidden-xs"><span
															class="label label-warning">High</span></td>
													</tr>
													<tr>
														<td>ClipTheme Web Site</td>
														<td class="hidden-xs">Internal</td>
														<td>11 november 2014</td>
														<td class="hidden-xs">
															<div class="progress active progress-xs">
																<div style="width: 90%" aria-valuemax="100"
																	aria-valuemin="0" aria-valuenow="90" role="progressbar"
																	class="progress-bar progress-bar-success">
																	<span class="sr-only"> 90% Complete</span>
																</div>
															</div>
														</td>
														<td class="center hidden-xs"><span
															class="label label-success">Normal</span></td>
													</tr>
													<tr>
														<td>Local Ad</td>
														<td class="hidden-xs">UI Fab</td>
														<td>15 april 2014</td>
														<td class="hidden-xs">
															<div class="progress active progress-xs">
																<div style="width: 50%" aria-valuemax="100"
																	aria-valuemin="0" aria-valuenow="50" role="progressbar"
																	class="progress-bar progress-bar-warning">
																	<span class="sr-only"> 50% Complete</span>
																</div>
															</div>
														</td>
														<td class="center hidden-xs"><span
															class="label label-success">Normal</span></td>
													</tr>
													<tr>
														<td>Design new theme</td>
														<td class="hidden-xs">Internal</td>
														<td>2 october 2014</td>
														<td class="hidden-xs">
															<div class="progress active progress-xs">
																<div style="width: 20%" aria-valuemax="100"
																	aria-valuemin="0" aria-valuenow="20" role="progressbar"
																	class="progress-bar progress-bar-success">
																	<span class="sr-only"> 20% Complete (warning)</span>
																</div>
															</div>
														</td>
														<td class="center hidden-xs"><span
															class="label label-danger">Critical</span></td>
													</tr>
													<tr>
														<td>IT Help Desk</td>
														<td class="hidden-xs">Designer TM</td>
														<td>6 december 2014</td>
														<td class="hidden-xs">
															<div class="progress active progress-xs">
																<div style="width: 40%" aria-valuemax="100"
																	aria-valuemin="0" aria-valuenow="40" role="progressbar"
																	class="progress-bar progress-bar-warning">
																	<span class="sr-only"> 40% Complete (warning)</span>
																</div>
															</div>
														</td>
														<td class="center hidden-xs"><span
															class="label label-warning">High</span></td>
													</tr>
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- end: USER PROFILE -->
				</div>
			</div>
		</div>
		<!-- start: FOOTER -->
		<footer>
			<div class="footer-inner">
				<div class="pull-left">
					&copy; <span class="current-year"></span><span
						class="text-bold text-uppercase">ClipTheme</span>. <span>All
						rights reserved</span>
				</div>
				<div class="pull-right">
					<span class="go-top"><i class="ti-angle-up"></i></span>
				</div>
			</div>
		</footer>
		<!-- end: FOOTER -->
		<!-- start: OFF-SIDEBAR -->
		<div id="off-sidebar" class="sidebar">
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
												src="assets/images/avatar-2.jpg" class="media-object">
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
												src="assets/images/avatar-3.jpg" class="media-object">
												<div class="media-body">
													<h4 class="media-heading">Steven Thompson</h4>
													<span> Visual Designer </span>
												</div>
										</a></li>
										<li class="media"><a data-toggle-class="chat-open"
											data-toggle-target="#users" href="#"> <i
												class="fa fa-circle status-online"></i> <img alt="..."
												src="assets/images/avatar-4.jpg" class="media-object">
												<div class="media-body">
													<h4 class="media-heading">Ella Patterson</h4>
													<span> Web Editor </span>
												</div>
										</a></li>
										<li class="media"><a data-toggle-class="chat-open"
											data-toggle-target="#users" href="#"> <i
												class="fa fa-circle status-online"></i> <img alt="..."
												src="assets/images/avatar-5.jpg" class="media-object">
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
												src="assets/images/avatar-6.jpg" class="media-object">
												<div class="media-body">
													<h4 class="media-heading">Nicole Bell</h4>
													<span> Content Designer </span>
												</div>
										</a></li>
										<li class="media"><a data-toggle-class="chat-open"
											data-toggle-target="#users" href="#">
												<div class="user-label">
													<span class="label label-success">3</span>
												</div> <img alt="..." src="assets/images/avatar-7.jpg"
												class="media-object">
												<div class="media-body">
													<h4 class="media-heading">Steven Thompson</h4>
													<span> Visual Designer </span>
												</div>
										</a></li>
										<li class="media"><a data-toggle-class="chat-open"
											data-toggle-target="#users" href="#"> <img alt="..."
												src="assets/images/avatar-8.jpg" class="media-object">
												<div class="media-body">
													<h4 class="media-heading">Ella Patterson</h4>
													<span> Web Editor </span>
												</div>
										</a></li>
										<li class="media"><a data-toggle-class="chat-open"
											data-toggle-target="#users" href="#"> <img alt="..."
												src="assets/images/avatar-9.jpg" class="media-object">
												<div class="media-body">
													<h4 class="media-heading">Kenneth Ross</h4>
													<span> Senior Designer </span>
												</div>
										</a></li>
										<li class="media"><a data-toggle-class="chat-open"
											data-toggle-target="#users" href="#"> <img alt="..."
												src="assets/images/avatar-10.jpg" class="media-object">
												<div class="media-body">
													<h4 class="media-heading">Ella Patterson</h4>
													<span> Web Editor </span>
												</div>
										</a></li>
										<li class="media"><a data-toggle-class="chat-open"
											data-toggle-target="#users" href="#"> <img alt="..."
												src="assets/images/avatar-5.jpg" class="media-object">
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
														<img src="../resources/assets/images/avatar-1.jpg" alt="">
													</div>
												</div>
												<div class="message">
													<div class="message-name">Nicole Bell</div>
													<div class="message-text">How are you?</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/avatar-1.jpg" alt="">
													</div>
												</div>
											</li>
											<li class="other">
												<div class="message">
													<div class="message-name">Nicole Bell</div>
													<div class="message-text">Hi, i am good</div>
													<div class="message-avatar">
														<img src="assets/images/avatar-2.jpg" alt="">
													</div>
												</div>
											</li>
											<li class="self">
												<div class="message">
													<div class="message-name">Peter Clark</div>
													<div class="message-text">Glad to see you ;)</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/avatar-1.jpg" alt="">
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
														<img src="assets/images/avatar-2.jpg" alt="">
													</div>
												</div>
											</li>
											<li class="messages-date">Sunday, Feb 9, 15:28</li>
											<li class="other">
												<div class="message">
													<div class="message-name">Nicole Bell</div>
													<div class="message-text">Alo...</div>
													<div class="message-avatar">
														<img src="assets/images/avatar-2.jpg" alt="">
													</div>
												</div>
												<div class="message">
													<div class="message-name">Nicole Bell</div>
													<div class="message-text">Are you there?</div>
													<div class="message-avatar">
														<img src="assets/images/avatar-2.jpg" alt="">
													</div>
												</div>
											</li>
											<li class="self">
												<div class="message">
													<div class="message-name">Peter Clark</div>
													<div class="message-text">Hi, i am here</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/avatar-1.jpg" alt="">
													</div>
												</div>
												<div class="message">
													<div class="message-name">Nicole Bell</div>
													<div class="message-text">Your Dashboard is great</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/avatar-1.jpg" alt="">
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
														<img src="assets/images/avatar-2.jpg" alt="">
													</div>
												</div>
											</li>
											<li class="self">
												<div class="message">
													<div class="message-name">Peter Clark</div>
													<div class="message-text">oh that's your question?</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/avatar-1.jpg" alt="">
													</div>
												</div>
												<div class="message">
													<div class="message-name">Peter Clark</div>
													<div class="message-text">little reduntant, no?</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/avatar-1.jpg" alt="">
													</div>
												</div>
												<div class="message">
													<div class="message-name">Peter Clark</div>
													<div class="message-text">literally we get the
														question daily</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/avatar-1.jpg" alt="">
													</div>
												</div>
											</li>
											<li class="other">
												<div class="message">
													<div class="message-name">Nicole Bell</div>
													<div class="message-text">I know. I, however, am not
														a nerd, and want to know</div>
													<div class="message-avatar">
														<img src="assets/images/avatar-2.jpg" alt="">
													</div>
												</div>
											</li>
											<li class="self">
												<div class="message">
													<div class="message-name">Peter Clark</div>
													<div class="message-text">for this type of question,
														wouldn't it be better to try Google?</div>
													<div class="message-avatar">
														<img src="../resources/assets/images/avatar-1.jpg" alt="">
													</div>
												</div>
											</li>
											<li class="other">
												<div class="message">
													<div class="message-name">Nicole Bell</div>
													<div class="message-text">Lucky for us :)</div>
													<div class="message-avatar">
														<img src="assets/images/avatar-2.jpg" alt="">
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
											src="assets/images/avatar-7.jpg" class="media-object">
											<div class="media-body">
												<h4 class="media-heading">Nicole Bell</h4>
												<span> Content Designer </span>
											</div>
									</a></li>
									<li class="media"><a href="#">
											<div class="user-label">
												<span class="label label-success">3</span>
											</div> <img alt="..." src="assets/images/avatar-6.jpg"
											class="media-object">
											<div class="media-body">
												<h4 class="media-heading">Steven Thompson</h4>
												<span> Visual Designer </span>
											</div>
									</a></li>
									<li class="media"><a href="#"> <img alt="..."
											src="assets/images/avatar-10.jpg" class="media-object">
											<div class="media-body">
												<h4 class="media-heading">Ella Patterson</h4>
												<span> Web Editor </span>
											</div>
									</a></li>
									<li class="media"><a href="#"> <img alt="..."
											src="assets/images/avatar-2.jpg" class="media-object">
											<div class="media-body">
												<h4 class="media-heading">Kenneth Ross</h4>
												<span> Senior Designer </span>
											</div>
									</a></li>
									<li class="media"><a href="#"> <img alt="..."
											src="assets/images/avatar-4.jpg" class="media-object">
											<div class="media-body">
												<h4 class="media-heading">Ella Patterson</h4>
												<span> Web Editor </span>
											</div>
									</a></li>
									<li class="media"><a href="#"> <img alt="..."
											src="assets/images/avatar-5.jpg" class="media-object">
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
		</div>
		<!-- end: OFF-SIDEBAR -->
		<!-- start: SETTINGS -->
		<div class="settings panel panel-default hidden-xs hidden-sm"
			id="settings">
			<button ct-toggle="toggle" data-toggle-class="active"
				data-toggle-target="#settings" class="btn btn-default">
				<i class="fa fa-spin fa-gear"></i>
			</button>
			<div class="panel-heading">Style Selector</div>
			<div class="panel-body">
				<!-- start: FIXED HEADER -->
				<div class="setting-box clearfix">
					<span class="setting-title pull-left"> Fixed header</span> <span
						class="setting-switch pull-right"> <input type="checkbox"
						class="js-switch" id="fixed-header" />
					</span>
				</div>
				<!-- end: FIXED HEADER -->
				<!-- start: FIXED SIDEBAR -->
				<div class="setting-box clearfix">
					<span class="setting-title pull-left">Fixed sidebar</span> <span
						class="setting-switch pull-right"> <input type="checkbox"
						class="js-switch" id="fixed-sidebar" />
					</span>
				</div>
				<!-- end: FIXED SIDEBAR -->
				<!-- start: CLOSED SIDEBAR -->
				<div class="setting-box clearfix">
					<span class="setting-title pull-left">Closed sidebar</span> <span
						class="setting-switch pull-right"> <input type="checkbox"
						class="js-switch" id="closed-sidebar" />
					</span>
				</div>
				<!-- end: CLOSED SIDEBAR -->
				<!-- start: FIXED FOOTER -->
				<div class="setting-box clearfix">
					<span class="setting-title pull-left">Fixed footer</span> <span
						class="setting-switch pull-right"> <input type="checkbox"
						class="js-switch" id="fixed-footer" />
					</span>
				</div>
				<!-- end: FIXED FOOTER -->
				<!-- start: THEME SWITCHER -->
				<div class="colors-row setting-box">
					<div class="color-theme theme-1">
						<div class="color-layout">
							<label> <input type="radio" name="setting-theme"
								value="theme-1"> <span class="ti-check"></span> <span
								class="split header"> <span class="color th-header"></span>
									<span class="color th-collapse"></span>
							</span> <span class="split"> <span class="color th-sidebar"><i
										class="element"></i></span> <span class="color th-body"></span>
							</span>
							</label>
						</div>
					</div>
					<div class="color-theme theme-2">
						<div class="color-layout">
							<label> <input type="radio" name="setting-theme"
								value="theme-2"> <span class="ti-check"></span> <span
								class="split header"> <span class="color th-header"></span>
									<span class="color th-collapse"></span>
							</span> <span class="split"> <span class="color th-sidebar"><i
										class="element"></i></span> <span class="color th-body"></span>
							</span>
							</label>
						</div>
					</div>
				</div>
				<div class="colors-row setting-box">
					<div class="color-theme theme-3">
						<div class="color-layout">
							<label> <input type="radio" name="setting-theme"
								value="theme-3"> <span class="ti-check"></span> <span
								class="split header"> <span class="color th-header"></span>
									<span class="color th-collapse"></span>
							</span> <span class="split"> <span class="color th-sidebar"><i
										class="element"></i></span> <span class="color th-body"></span>
							</span>
							</label>
						</div>
					</div>
					<div class="color-theme theme-4">
						<div class="color-layout">
							<label> <input type="radio" name="setting-theme"
								value="theme-4"> <span class="ti-check"></span> <span
								class="split header"> <span class="color th-header"></span>
									<span class="color th-collapse"></span>
							</span> <span class="split"> <span class="color th-sidebar"><i
										class="element"></i></span> <span class="color th-body"></span>
							</span>
							</label>
						</div>
					</div>
				</div>
				<div class="colors-row setting-box">
					<div class="color-theme theme-5">
						<div class="color-layout">
							<label> <input type="radio" name="setting-theme"
								value="theme-5"> <span class="ti-check"></span> <span
								class="split header"> <span class="color th-header"></span>
									<span class="color th-collapse"></span>
							</span> <span class="split"> <span class="color th-sidebar"><i
										class="element"></i></span> <span class="color th-body"></span>
							</span>
							</label>
						</div>
					</div>
					<div class="color-theme theme-6">
						<div class="color-layout">
							<label> <input type="radio" name="setting-theme"
								value="theme-6"> <span class="ti-check"></span> <span
								class="split header"> <span class="color th-header"></span>
									<span class="color th-collapse"></span>
							</span> <span class="split"> <span class="color th-sidebar"><i
										class="element"></i></span> <span class="color th-body"></span>
							</span>
							</label>
						</div>
					</div>
				</div>
				<!-- end: THEME SWITCHER -->
			</div>
		</div>
		<!-- end: SETTINGS -->
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
		src="<c:url value="/indus/resources/vendor/bootstrap-fileinput/jasny-bootstrap.js"/>"></script>

	<!-- end: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
	<!-- start: CLIP-TWO JAVASCRIPTS -->

	<script src="<c:url value="/indus/resources/assets/js/main.js"/>"></script>
	<!-- start: JavaScript Event Handlers for this page -->
	<script>
			jQuery(document).ready(function() {
				Main.init();
			});
		</script>
	<!-- end: JavaScript Event Handlers for this page -->
	<!-- end: CLIP-TWO JAVASCRIPTS -->
</body>
</html>
