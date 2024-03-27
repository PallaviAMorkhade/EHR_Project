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
<title>Indus | Engagement</title>
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
	overflow: auto;
    z-index: 1000000;
    width: 12%;
}

.vR {
    font-size: 13px;
    display: inline-block;
    padding: 3px;
    vertical-align: top;
}

.vN {
    background-color: #F5F5F5;
    border: 1px solid #D9D9D9;
    cursor: default;
    display: block;
    height: 23px;
    white-space: nowrap;
    border-radius: 3px;
}

.vT {
    display: inline-block;
    color: #222;
    margin: 2px 5px;
    max-width: 325px;
    max-height: 17px;
    overflow: hidden;
    text-overflow: ellipsis;
    direction: ltr;
    cursor: move;
}

.vM {
    display: inline-block;
    width: 14px;
    height: 20px;
    background: transparent url("//ssl.gstatic.com/apps/gadgets/contactarea/contactarea_sprite_2.gif") no-repeat scroll -4px 0px;
    opacity: 0.6;
    vertical-align: top;
    cursor: pointer;
}


</style>

<script
		src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>"></script>

</head>

<%
	String userName = (String) session.getAttribute("clientUserName");
	String clientFirstName = (String) session
			.getAttribute("userFirstName");
	Integer clientUserId = (Integer) session
			.getAttribute("clientMasterId");
%>

<!-- end: HEAD -->
<jsp:include page="indus_access_control.jsp"></jsp:include>

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
									<div class="container-fluid container-fullw bg-white"
										id='ajaxContent'>
										<!-- start: DYNAMIC TABLE -->
										<div class="row">
											<div class="col-md-4 col-sm-3 col-xs-8">
												<h5 class="text-bold margin-top-25 margin-bottom-15">Date
													Range</h5>
												<div class="input-group input-daterange datepicker">
													<input id="startDate" name="startDate" type="text"
														class="form-control" onchange="assignDate();" /> <span
														class="input-group-addon bg-primary">to</span> <input
														id="endDate" type="text" class="form-control" />
												</div>
											</div>
											<div class="col-md-1 col-sm-2 col-xs-2 margin-top-57">
												<button type="button" class="btn btn-info"
													onclick="getRecordByDate()">Search</button>
											</div>

											<div class="col-md-2 col-sm-3 col-xs-12 margin-top-3">
												<div class="form-group">
													<h5 class="text-bold margin-top-25 margin-bottom-15">Name</h5>
													<input class="form-control" placeholder="Search"
														id="searchBox" name="searchBox" type="search"
														autocomplete="off">
												</div>
											</div>

											<div class="col-md-2 col-sm-2 col-xs-12 margin-top-3">
												<div class="form-group">
													<h5 class="text-bold margin-top-25 margin-bottom-15">Records</h5>
													<select id="recordSelectList" class="form-control width">
														<option value="">Select</option>
								<option value="Urgent">Urgent</option>
								<option value="Abnormal">Abnormal</option>
								<option value="Borderline">Borderline</option>
								<option value="Normal">Normal</option>
													</select>
												</div>
											</div>

											<div class="col-md-1 col-sm-1 col-xs-12 margin-top-57">
												<button type="button" class="btn btn-info"
													onclick="sendEmailAndSmsBtn()">Send Email and Sms</button>
											</div>

										</div>

										<div class="row">
											<div class="col-md-12 table-responsive">
												<table
													class="table table-striped table-bordered table-hover table-full-width"
													id="sample_1">
													<thead>
														<tr>
															<th>First Name</th>
															<th>Last Name</th>
															<th>Mobile</th>
															<!-- <th>Package</th> -->
															<th>Date</th>
															<th>Status</th>
														</tr>
													</thead>
													<tbody id="clientRecordTable">

													</tbody>
												</table>
											</div>
										</div>

										<div class="pull-right">
											<ul class="pagination pagination-blue margin-bottom-10"
												id="patientRecordPagination">
											</ul>
										</div>
										<div class="row">
											<div class="col-md-4 col-md-offset-8">
												<div class="pull-right">
													<ul class="pagination pagination-blue margin-bottom-10"
														id="totalNumberOfPages">
													</ul>
												</div>
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
					<span class="text-bold text-uppercase"> INDUS HEALTH PLUS &copy; </span> <span>2019-2020</span>
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
		src="<c:url value="/indus/resources/custom_js/indus_interaction.js"/>"></script>
		
	<script
		src="<c:url value="/indus/resources/custom_js/indus_analysis.js"/>"></script>
	
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

		<!-- script for text editor -->
		
		<script src="<c:url value="/indus/resources/vendor/ckeditor/ckeditor.js"/>"></script>
		<script src="<c:url value="/indus/resources/vendor/ckeditor/adapters/jquery.js"/>"></script>
		<script src="<c:url value="/indus/resources/assets/js/form-text-editor.js"/>"></script>
		
	<script>
		jQuery(document).ready(function() {
			Main.init();
			FormElements.init();
			TextEditor.init();
		});
	</script>

	<!-- end: JavaScript Event Handlers for this page -->
	<!-- end: CLIP-TWO JAVASCRIPTS -->
</body>
<jsp:include page="indus_popups.jsp"></jsp:include>
</html>




<%-- <%@page import="com.hms.indus.bo.ClientMaster"%>
<%@page language="java" contentType="text/html; charset=ISO-8859-1"
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

<%
	ClientMaster clientMaster=(ClientMaster)request.getAttribute("client");
	String clientFirstName=(String)session.getAttribute("userFirstName");
%>
<body>
	<div id="app">
		<div class="app-content">
			<div>
				<div class="wrap-content container" id="container">
					<!-- start: USER PROFILE -->
					<div class="container-fluid container-fullw bg-white">
						<div class="row">
							<div class="col-md-12" id="mainDiv">
								<div class="row">
									<div class="col-md-3">
										<h5 class="text-bold margin-top-25 margin-bottom-15">Follow Up Date</h5>
										<div class="form-group">
											<input class="form-control datepicker" id="followUpDate" type="text">
										</div>
									</div>

									<div class="col-md-3">
										<h5 class="text-bold margin-top-25 margin-bottom-15">Time</h5>
										<div class="form-group">
											<input type="text" id="timepicker-default"
												class="form-control">
										</div>
									</div>

									<div class="col-md-3">
										<h5 class="text-bold margin-top-25 margin-bottom-15">Plan
											of action</h5>
										<div class="form-group">
											<select id="actionSelectListInteraction"
												class="form-control width">
											</select>
										</div>
									</div>

									<div class="col-md-3">
										<h5 class="text-bold margin-top-25 margin-bottom-15">Center</h5>
										<div class="form-group">
											<select id="centerSelectListInteraction"
												class="form-control width">
											</select>
										</div>
									</div>
								</div>
								
								<h5 class="text-bold margin-top-25">Medical Advice :</h5>
								<div class="row">
									<div class="col-md-6">
										<h5 class="text-bold margin-bottom-15">Template :</h5>
										<div class="form-group">
											<select id="templateSelectListInteraction"
												class="form-control width">
											</select>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-6">
										<div class="form-group">
											<textarea placeholder="Medical advice"
											class="form-control" id="medicalAdvice" name="medicalAdvice"></textarea>
										</div>
									</div>
								</div>
								
								<div class="row">
									<div class="col-md-2">
										<div class="form-group">
											<button type="button" class="btn btn-primary" onclick="sendMedicalAdvice()">Send</button>
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
	</div>
	<!-- start: MAIN JAVASCRIPTS -->
	
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

	<script src="<c:url value="/indus/resources/custom_js/indus_interaction.js"/>"></script>
	
	<!-- start: JavaScript Event Handlers for this page -->
	<script>
		jQuery(document).ready(function() {
			Main.init();
			FormElements.init();
		});
	</script>
	<!-- end: JavaScript Event Handlers for this page -->
</body>
 --%>