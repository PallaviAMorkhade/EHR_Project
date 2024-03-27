<%@page import="org.json.simple.JSONObject"%>
<%@page import="com.hms.indus.bo.ClientMaster"%>
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
<title>Indus|DashBoard</title>
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

<script
	src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>"></script>
<style>
.progress-marker {
	width: 1px;
	height: 0px;
	/* background: black; */
	margin-bottom: -6px;
}
</style>
</head>
<%
	HttpSession httpSession = request.getSession();
	String userName = (String) session.getAttribute("clientUserName");
	String clientFirstName = (String) session
			.getAttribute("userFirstName");
	Integer clientUserId = (Integer) session
			.getAttribute("clientMasterId");
%>

<jsp:include page="indus_access_control.jsp"></jsp:include>

<!-- end: HEAD -->
<body>
	<span id="clientMasterId" class="hidden"><%=clientUserId%></span>
	<span id="clientUserId" class="hidden"><%=userName%></span>
	<span id="clientEmailId" class="hidden"></span>

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
					<section id="page-title" style="padding: 0; text-align: left;">
						<div class="row">
							<div class="col-sm-3 padding_zero">
								<!-- <h1 class="mainTitle">Indus EHR know your health the smart way</h1> -->
								<font style="font-size: 17px;"><b>Welcome</b><b> <%=clientFirstName%></b>
									${message} </font>
							</div>

							<div class="col-sm-9"
								style="margin-top: 5px; margin-bottom: -10px;">
								<h4 id="dashboardHealthFeed"></h4>
							</div>

						</div>
					</section>

					<div class="row" id="mainBody">

						<%-- <div class="col-md-12">
							<jsp:include page="indus_coming_soon.jsp"></jsp:include>
						</div> --%>

						<%
							String userTypeId = (String) httpSession.getAttribute("userTypeId");
							if ((String) httpSession.getAttribute("loginUserType") != null) {
						%>

						<div class="col-md-8">

							<div class="row">
								<div class="col-md-12" id="pendingTestsDiv"></div>
							</div>

							<!-- start : Chart -->
							<div class="container-fluid container-fullw padding-bottom-10">
								<div class="row">
									<div class="col-sm-12">
										<div class="row">
											<div class="col-md-12 col-lg-12">
												<div class="panel panel-white no-radius">
													<div class="panel-heading border-light">
														<h4 class="panel-title">Health Statistics</h4>
													</div>
													<div class="panel-body">
													
														<div class="row" id="parameterHealthStatistics_265"
															style="display: none;">
															<div class="col-md-3">
																<h5 class="inline-block no-margin">Haemoglobin</h5>
																<h6>(<span id="lowerValue_265"></span>-<span id="upperValue_265"></span>)</h6>
															</div>
															<div class="col-md-9">
																<div class="progress progress-xs no-radius"
																	style="overflow: initial;">
																	<div id="parameterValue_265" class="progress-marker"
																		style="margin-bottom: 15px;"></div>
																	<!-- <div id="lowerValue_265" class="progress-marker"
																		style="height: 0px; margin-bottom: 0px;"></div>
																	<div id="upperValue_265" class="progress-marker"
																		style="height: 23px;"></div> -->
																	<div id="firstWidth_265"
																		class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="80"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<div id="secondWidth_265"
																		class="progress-bar progress-bar-success"
																		role="progressbar" aria-valuenow="40"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<div id="thirdWidth_265"
																		class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="80"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<span id="parameterValueIcon_265"><i
																		class="fa fa-2x fa-caret-up text-green"
																		style="line-height: 0;"></i></span>
																</div>
																<span>0</span> <span class="pull-right"
																	id="maxValue_265">20</span>
															</div>
														</div>
														
														<div class="row" id="parameterHealthStatistics_266"
															style="display: none;">
															<div class="col-md-3">
																<h5 class="inline-block no-margin">Total WBC Count</h5>
																<h6>(<span id="lowerValue_266"></span>-<span id="upperValue_266"></span>)</h6>
															</div>
															<div class="col-md-9">
																<div class="progress progress-xs no-radius"
																	style="overflow: initial;">
																	<div id="parameterValue_266" class="progress-marker"
																		style="margin-bottom: 15px;"></div>
																	<!-- <div id="lowerValue_266" class="progress-marker"
																		style="height: 0px; margin-bottom: 0px;"></div>
																	<div id="upperValue_266" class="progress-marker"
																		style="height: 23px;"></div> -->
																	<div id="firstWidth_266"
																		class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="80"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<div id="secondWidth_266"
																		class="progress-bar progress-bar-success"
																		role="progressbar" aria-valuenow="40"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<div id="thirdWidth_266"
																		class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="80"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<span id="parameterValueIcon_266"><i
																		class="fa fa-2x fa-caret-up text-green"
																		style="line-height: 0;"></i></span>
																</div>
																<span>0</span> <span class="pull-right"
																	id="maxValue_266">800000</span>
															</div>
														</div>
														
														<div class="row" id="parameterHealthStatistics_293"
															style="display: none;">
															<div class="col-md-3">
																<h5 class="inline-block no-margin">RBC Count</h5>
																<h6>(<span id="lowerValue_293"></span>-<span id="upperValue_293"></span>)</h6>
															</div>
															<div class="col-md-9">
																<div class="progress progress-xs no-radius"
																	style="overflow: initial;">
																	<div id="parameterValue_293" class="progress-marker"
																		style="margin-bottom: 0px;"></div>
																	<!-- <div id="lowerValue_293" class="progress-marker"
																		style="height: 0px; margin-bottom: 0px;"></div>
																	<div id="upperValue_293" class="progress-marker"
																		style="height: 23px;"></div> -->
																	<div id="firstWidth_293"
																		class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="80"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<div id="secondWidth_293"
																		class="progress-bar progress-bar-success"
																		role="progressbar" aria-valuenow="40"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<div id="thirdWidth_293"
																		class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="80"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<span id="parameterValueIcon_293"><i
																		class="fa fa-2x fa-caret-up text-green"
																		style="line-height: 0;"></i></span>
																</div>
																<span>0</span> <span class="pull-right"
																	id="maxValue_293">10</span>
															</div>
														</div>
														
														<div class="row" id="parameterHealthStatistics_281"
															style="display: none;">
															<div class="col-md-3">
																<h5 class="inline-block no-margin">Plasma Glucose
																	(F)</h5>
																<h6>(<span id="lowerValue_281"></span>-<span id="upperValue_281"></span>)</h6>
															</div>
															<div class="col-md-9">
																<div class="progress progress-xs no-radius"
																	style="overflow: initial;">
																	<div id="parameterValue_281" class="progress-marker"
																		style="margin-bottom: 15px;"></div>
																	<!-- <div id="lowerValue_281" class="progress-marker"
																		style="height: 0px; margin-bottom: 0px;"></div>
																	<div id="upperValue_281" class="progress-marker"
																		style="height: 23px;"></div> -->
																	<div id="firstWidth_281"
																		class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="80"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<div id="secondWidth_281"
																		class="progress-bar progress-bar-success"
																		role="progressbar" aria-valuenow="40"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<div id="thirdWidth_281"
																		class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="80"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<span id="parameterValueIcon_281"><i
																		class="fa fa-2x fa-caret-up text-green"
																		style="line-height: 0;"></i></span>
																</div>
																<span>0</span> <span class="pull-right"
																	id="maxValue_281">1000</span>
															</div>
														</div>
														
														<div class="row" id="parameterHealthStatistics_347"
															style="display: none;">
															<div class="col-md-3">
																<h5 class="inline-block no-margin">Serum SGPT</h5>
																<h6>(<span id="lowerValue_347"></span>-<span id="upperValue_347"></span>)</h6>
															</div>
															<div class="col-md-9">
																<div class="progress progress-xs no-radius"
																	style="overflow: initial;">
																	<div id="parameterValue_347" class="progress-marker"
																		style="margin-bottom: 15px;"></div>
																	<!-- <div id="lowerValue_347" class="progress-marker"
																		style="height: 0px; margin-bottom: 0px;"></div>
																	<div id="upperValue_347" class="progress-marker"
																		style="height: 23px;"></div> -->
																	<div id="firstWidth_347"
																		class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="80"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<div id="secondWidth_347"
																		class="progress-bar progress-bar-success"
																		role="progressbar" aria-valuenow="40"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<div id="thirdWidth_347"
																		class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="80"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<span id="parameterValueIcon_347"><i
																		class="fa fa-2x fa-caret-up text-green"
																		style="line-height: 0;"></i></span>
																</div>
																<span>0</span> <span class="pull-right"
																	id="maxValue_347">60</span>
															</div>
														</div>
														
														<div class="row" id="parameterHealthStatistics_298"
															style="display: none;">
															<div class="col-md-3">
																<h5 class="inline-block no-margin">Serum Billirubin
																	Total</h5>
																<h6>(<span id="lowerValue_298"></span>-<span id="upperValue_298"></span>)</h6>
															</div>
															<div class="col-md-9">
																<div class="progress progress-xs no-radius"
																	style="overflow: initial;">
																	<div id="parameterValue_298" class="progress-marker"
																		style="margin-bottom: 0px;"></div>
																	<!-- <div id="lowerValue_298" class="progress-marker"
																		style="height: 0px; margin-bottom: 0px;"></div>
																	<div id="upperValue_298" class="progress-marker"
																		style="height: 23px;"></div> -->
																	<div id="firstWidth_298"
																		class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="80"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<div id="secondWidth_298"
																		class="progress-bar progress-bar-success"
																		role="progressbar" aria-valuenow="40"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<div id="thirdWidth_298"
																		class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="80"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<span id="parameterValueIcon_298"><i
																		class="fa fa-2x fa-caret-up text-green"
																		style="line-height: 0;"></i></span>
																</div>
																<span>0</span> <span class="pull-right"
																	id="maxValue_298">20</span>
															</div>
														</div>
														
														<div class="row" id="parameterHealthStatistics_300"
															style="display: none;">
															<div class="col-md-3">
																<h5 class="inline-block no-margin">Serum Creatinine</h5>
																<h6>(<span id="lowerValue_300"></span>-<span id="upperValue_300"></span>)</h6>
															</div>
															<div class="col-md-9">
																<div class="progress progress-xs no-radius"
																	style="overflow: initial;">
																	<div id="parameterValue_300" class="progress-marker"
																		style="margin-bottom: 15px;"></div>
																	<!-- <div id="lowerValue_300" class="progress-marker"
																		style="height: 0px; margin-bottom: 0px;"></div>
																	<div id="upperValue_300" class="progress-marker"
																		style="height: 23px;"></div> -->
																	<div id="firstWidth_300"
																		class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="80"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<div id="secondWidth_300"
																		class="progress-bar progress-bar-success"
																		role="progressbar" aria-valuenow="40"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<div id="thirdWidth_300"
																		class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="80"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<span id="parameterValueIcon_300"><i
																		class="fa fa-2x fa-caret-up text-green"
																		style="line-height: 0;"></i></span>
																</div>
																<span>0</span> <span class="pull-right"
																	id="maxValue_300">10</span>
															</div>
														</div>
														
														<div class="row" id="parameterHealthStatistics_280"
															style="display: none;">
															<div class="col-md-3">
																<h5 class="inline-block no-margin">Serum TSH</h5>
																<h6>(<span id="lowerValue_280"></span>-<span id="upperValue_280"></span>)</h6>
															</div>
															<div class="col-md-9">
																<div class="progress progress-xs no-radius"
																	style="overflow: initial;">
																	<div id="parameterValue_280" class="progress-marker"
																		style="margin-bottom: 15px;"></div>
																	<!-- <div id="lowerValue_280" class="progress-marker"
																		style="height: 0px; margin-bottom: 0px;"></div>
																	<div id="upperValue_280" class="progress-marker"
																		style="height: 23px;"></div> -->
																	<div id="firstWidth_280"
																		class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="80"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<div id="secondWidth_280"
																		class="progress-bar progress-bar-success"
																		role="progressbar" aria-valuenow="40"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<div id="thirdWidth_280"
																		class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="80"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<span id="parameterValueIcon_280"><i
																		class="fa fa-2x fa-caret-up text-green"
																		style="line-height: 0;"></i></span>
																</div>
																<span>0</span> <span class="pull-right"
																	id="maxValue_280">15</span>
															</div>
														</div>
														
														<div class="row" id="parameterHealthStatistics_319"
															style="display: none;">
															<div class="col-md-3">
																<h5 class="inline-block no-margin">Serum Vitamin
																	B12</h5>
																<h6>(<span id="lowerValue_319"></span>-<span id="upperValue_319"></span>)</h6>
															</div>
															<div class="col-md-9">
																<div class="progress progress-xs no-radius"
																	style="overflow: initial;">
																	<div id="parameterValue_319" class="progress-marker"
																		style="margin-bottom: 15px;"></div>
																	<!-- <div id="lowerValue_319" class="progress-marker"
																		style="height: 0px; margin-bottom: 0px;"></div>
																	<div id="upperValue_319" class="progress-marker"
																		style="height: 23px;"></div> -->
																	<div id="firstWidth_319"
																		class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="80"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<div id="secondWidth_319"
																		class="progress-bar progress-bar-success"
																		role="progressbar" aria-valuenow="40"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<div id="thirdWidth_319"
																		class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="80"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<span id="parameterValueIcon_319"><i
																		class="fa fa-2x fa-caret-up text-green"
																		style="line-height: 0;"></i></span>
																</div>
																<span>0</span> <span class="pull-right"
																	id="maxValue_319">2500</span>
															</div>
														</div>

														<div class="row" id="parameterHealthStatistics_324"
															style="display: none;">
															<div class="col-md-3">
																<h5 class="inline-block no-margin">Calcium Score</h5>
																<h6>
																	(<span id="lowerValue_324"></span>-<span
																		id="upperValue_324"></span>)
																</h6>
															</div>
															<div class="col-md-9">
																<div class="progress progress-xs no-radius"
																	style="overflow: initial;">
																	<div id="parameterValue_324" class="progress-marker"
																		style="margin-bottom: 15px;"></div>
																	<!-- <div id="lowerValue_324" class="progress-marker" style="height: 0px; margin-bottom: 0px;"></div>
																		<div id="upperValue_324" class="progress-marker" style="height: 23px;"></div> -->
																	<div id="firstWidth_324"
																		class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="80"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<div id="secondWidth_324"
																		class="progress-bar progress-bar-success"
																		role="progressbar" aria-valuenow="40"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<div id="thirdWidth_324"
																		class="progress-bar progress-bar-danger"
																		role="progressbar" aria-valuenow="80"
																		aria-valuemin="0" aria-valuemax="100"></div>
																	<span id="parameterValueIcon_324"><i
																		class="fa fa-2x fa-caret-up text-green"
																		style="line-height: 0;"></i></span>
																</div>
																<span>0</span> <span class="pull-right"
																	id="maxValue_324">1500</span>
															</div>
														</div>

													</div>
												</div>

												<div class="row">
													<div class="col-md-12">
														<h4>Doctor's Comment</h4>
													</div>
													<div class="col-md-12">
													<div class="row" id="analysisCommentsDashboard"></div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- end: Chart -->



						</div>

						<div class="col-md-4">

							<div class="row" style="margin-top: 10px;">
								<h5>Quick Links</h5>
							</div>
							<div class="row">
								<h4 style="padding: 10px 0px 0px;">
									<a id="dashboardLink" target="_blank" href=""></a>
								</h4>
							</div>

							<div class="row">
								<h5>Featured Video</h5>
							</div>
							<div class="row">
								<video height="260" width="340" controls="">
									<source id="dashboardVideo" type="video/mp4" src=""></source>
									Your browser does not support the video tag.
								</video>
								<!-- preview?fileName=Airtel 4G (MP4)(PaGalWorld.CC).mp4 -->
							</div>

							<div class="row">
								<h5 id="dashboardVideoDescription"></h5>
							</div>

						</div>

						<%
							} else if (userTypeId.equals("3") ||  userTypeId.equals("14")) {
						%>
								
						<div class="col-md-5">
                             <div class="col-md-8 col-sm-4 col-xs-9">
								<h5 class="text-bold margin-top-25 margin-bottom-15">Date
									Range</h5>
								<div class="input-group input-daterange datepicker">
									<input id="startDate" name="startDate" type="text" class="form-control" onchange="assignDateNew();" /> <span
										class="input-group-addon bg-primary">to</span> <input
										id="endDate" type="text" class="form-control" />
								</div>
							</div>
							<div class="col-md-2 col-sm-2 col-xs-2 margin-top-57">
								<button type="button" class="btn btn-info"
									onclick="getRepresentativeDashboard()">Search</button>
							</div>
						<div class="col-md-12">

	


							<p><div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label"> Total Turn Up </label>
										<h4 id="turnUpNoOfClient"></h4>
									</div>
								</div>

								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label"> Total Upload </label>
										<h4 id="uploadReportClient"></h4>
									</div>
								</div>

							</div>
							
							
							
							<div class="row">

								<!-- <div class="col-md-6">
									<div class="form-group">
										<label class="control-label">  No.of Reports Uploaded</label>
										<h4 id="noOfReportsUploaded"></h4>
									</div>
								</div> -->

								<div class="col-md-6" onclick="rejectedReportListAll()" style="cursor: pointer;">
									<div class="form-group" title="View All Rejected List">
										<label class="control-label"> <font color="red"> No.of All Incomplete Reports </font></label>
										<h4 id="reportsRejected"></h4>
									</div>
								</div>
								
								<div class="col-md-6" onclick="totalPendingVisits()" style="cursor: pointer;">
									<div class="form-group" title="View Pending List">
										<label class="control-label">  <font color="red">Total Upload Pending</font> </label>
										<h4 id="pendingUpload"></h4>
									</div>
								</div>

							</div>
							
							

							<div class="row">

								<!-- <div class="col-md-6">
									<div class="form-group">
										<label class="control-label">  No.of Reports Upload By You</label>
										<h4 id="uploadReportCountByUser"></h4>
									</div>
								</div> -->

								<!-- <div class="col-md-6" onclick="rejectedReportList()" style="cursor: pointer;">
									<div class="form-group" title="View Your Rejected List">
										<label class="control-label"> <font color="red">No.of My Incomplete Reports </font></label>
										<h4 id="rejectedReportByUser"></h4>
									</div>
								</div> -->

							</div>
							
							
							<div class="row">

								
								<!-- <div class="col-md-6" onclick="totalPendingVisits()" style="cursor: pointer;">
									<div class="form-group" title="View Pending List">
										<label class="control-label">  <font color="red">Total Pending Upload</font> </label>
										<h4 id="pendingUpload"></h4>
									</div>
								</div> -->


							</div>



						</div>
						</div>
						<!-- <br></br> -->
						<div class="col-md-5" id="pichart" style="height: 350px; width: 50%;"></div>
						<div class="col-md-1"></div>

						<%
							} else {
						%>
						<div class="col-md-12">
							<jsp:include page="indus_coming_soon.jsp"></jsp:include>

							<!-- <button onclick="callWebServiceGetBeneficiary()">API</button> 
							<button onclick="callWebServiceGetCheckUpStatus()">API</button> -->

						</div>

						<%
							}
						%>
						
		<div class="row" id="disclaimerNote" style="display:none;">
			<div class="col-md-12">
								<h6 style="margin-top: 100px;"><b>Disclaimer-</b>
									The consultation is not intended to replace a complete medical
									evaluation by treating doctor or any other specialist. The
									consultation, opinion and/or recommendation is limited and
									provisional. The analysis is purely done in the absence of a
									physical examination which may affect the ability to diagnose a
									condition or disease. Follow-up with your treating doctor or
									physician is advisable</h6>
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
	<!-- start: CLIP-TWO JAVASCRIPTS -->

	<script src="<c:url value="/indus/resources/assets/js/main.js"/>"></script>

	<script src="<c:url value="/indus/resources/alertify/alertify.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/custom_js/indus_header.js"/>"></script>
	<script src="<c:url value="/indus/resources/custom_js/index.js"/>"></script>
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
		
		<!-- <script src="/indus/resources/vendor/jquery-1.11.1.min.js"></script>-->
		<!-- <script src="/indus/resources/vendor/jquery.canvasjs.min.js"></script> -->
		<script 
		src="<c:url value="/indus/resources/vendor/canvasJs/jquery.canvasjs.min.js"/>"></script>
		
		
<!-- <script src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script> -->
<!-- <script src="https://canvasjs.com/assets/script/jquery.canvasjs.min.js"></script>
 -->		
	<script src="<c:url value="/indus/resources/vendor/jquery-ui/jquery-ui-1.10.1.custom.min.js"/>"></script>
	
	<script
		src="<c:url value="/indus/resources/vendor/bootstrap-datepicker/bootstrap-datepicker.min.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/bootstrap-timepicker/bootstrap-timepicker.min.js"/>"></script>



	<script>
		jQuery(document).ready(function() {
			Main.init();
			FormElements.init();
			//callWebServiceGetBeneficiary();
			//callWebServiceGetCheckUpStatus();
			//callWebServiceUpdateEHRID();
		});
	</script>
	<!-- end: JavaScript Event Handlers for this page -->
	<!-- end: CLIP-TWO JAVASCRIPTS -->

	<!-- <script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-79904946-1', 'auto');
  ga('send', 'pageview');

</script> -->


</body>
<%
	//HttpSession session=request.getSession();
	if ((String) httpSession.getAttribute("loginUserType") != null) {
%>
<script>
	//loadDemoghraphicPage();
	dashboard();
</script>
<%
	}
%>

<%try{
	if (userTypeId == null || userTypeId.equals(null) || userTypeId.equals("null")) {
		userTypeId = "-";
	}
}catch(Exception e){
	e.printStackTrace();
}
	if ( !userTypeId.equals("-") && userTypeId.equals("3") ||  userTypeId.equals("14")) {
%>
<script>
	getRepresentativeDashboard();
</script>
<%
	}
%>

<%
	if ((String) httpSession.getAttribute("uploadVideo") != null) {
%>
<script>
	alertify.error("File has been uploaded successfully!");
	loadUploadVideoMaster();
</script>
<%
	}
%>

<%
	if ((String) httpSession.getAttribute("reportRejectList") != null) {
%>
<script>
	loadReportPage();
</script>
<%
	httpSession.removeAttribute("reportRejectList");
	}
%>

<jsp:include page="indus_popups.jsp"></jsp:include>

<%
	Integer count = (Integer) session.getAttribute("count");
	if (count != null) {
		if (count == 0) {
			//session.setAttribute("count", 1);
%>
<script>
	jQuery(document).ready(function() {
		$('#changePasswordModal').modal();
	});

	$('#termsAndConditionModal').modal({
		backdrop : 'static',
		keyboard : false
	});

	$(window).click(function() {
		$("#termsAndConditionModal .modal-content").click(function(event) {
			event.stopPropagation();
		});
		alert("Please accept Terms and Conditions");
	});
</script>
<%
	}
	}
%>

</html>

