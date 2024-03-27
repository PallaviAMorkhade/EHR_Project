<%@page import="org.json.simple.JSONObject"%>
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
<title>Indus|Database</title>
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
<link rel="stylesheet" media="screen" href="<c:url value="/indus/resources/vendor/jstree/themes/default/style.min.css"/>">
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
<link href="<c:url value="/indus/resources/vendor/jquery-ui/jquery-ui-1.10.1.custom.min.css"/>"
	rel="stylesheet" media="screen">
	
<style>
 .fixedHeight {
        font-size:12px;
        max-height: 250px;
        margin-bottom: 10px;
        overflow: auto;
        z-index: 1000000;
        width: 18%;
 }
</style>

<script
		src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>"></script>

</head>

<%
	String userName=(String)session.getAttribute("clientUserName");
	String clientFirstName=(String)session.getAttribute("userFirstName");
	Integer clientUserId=(Integer)session.getAttribute("clientMasterId");
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
					<section id="page-title">
						<div class="row">
							<div class="col-sm-7 padding_zero">
								<!-- <h1 class="mainTitle">Indus EHR know your health the smart way</h1> -->
								<font style="font-size: 17px;"><b>Welcome</b><b> <%=clientFirstName %></b></font>
							</div>
						</div>
					</section>
					<div class="row" id="mainBody">
					
						<div id="app">
		<div class="app-content">
			<!-- end: TOP NAVBAR -->
				<div class="wrap-content container" id="container">
					<!-- start: DYNAMIC TABLE -->
					<div class="container-fluid container-fullw bg-white" id='ajaxContent'>
						<div class="row">
							<div class="col-md-3 col-sm-4 col-xs-9">
								<h5 class="text-bold margin-top-25 margin-bottom-15">Date
									Range</h5>
								<div class="input-group input-daterange datepicker">
									<input id="startDate" name="startDate" type="text" class="form-control" onchange="assignDate();" /> <span
										class="input-group-addon bg-primary">to</span> <input
										id="endDate" type="text" class="form-control" />
								</div>
							</div>
							<div class="col-md-2 col-sm-2 col-xs-2 margin-top-57">
								<button type="button" class="btn btn-info"
									onclick="getRecordByDate()">Search</button>
								<button id="menu_42" style="display:none;" type="button" class="btn btn-danger"
									onclick="callWebServiceGetBeneficiary()">Get</button>
							</div>
														
							<div class="col-md-3 col-sm-6 col-xs-12 margin-top-3">
								<div class="form-group">
									<h5 class="text-bold margin-top-25 margin-bottom-15">Name</h5>
									<input class="form-control" placeholder="Search" id="searchBox" name="searchBox" type="search" autocomplete="off">
								</div>
							</div>

							<div class="col-md-2 col-sm-6 col-xs-12 margin-top-3">
								<div class="form-group">
									<h5 class="text-bold margin-top-25 margin-bottom-15">EHR Id</h5>
									<input type="text" id="searchByClientId" class="form-control"
										placeholder="Search">
								</div>
							</div>
							
							<div class="col-md-2 col-sm-6 col-xs-12 margin-top-3">
								<div class="form-group">
									<h5 class="text-bold margin-top-25 margin-bottom-15">Member Id</h5>
									<input type="text" id="searchByMemberId" class="form-control"
										placeholder="Search">
								</div>
							</div>

						</div>

<!-- <button onclick="getAllReminder()">getAllReminder</button> -->

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
											<th>Added Date</th>
										</tr>
									</thead>
									<tbody id="clientRecordTable">

									</tbody>
								</table>
							</div>
						</div>

						<div class="pull-right">
							<ul class="pagination pagination-blue margin-bottom-10" id="patientRecordPagination">
							</ul>
						</div>
						<div class="row">
						<div class="col-md-4 col-md-offset-8">
						<div class="pull-right">
							<ul class="pagination pagination-blue margin-bottom-10" id="totalNumberOfPages">
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
	<script src="<c:url value="/indus/resources/custom_js/indus_header.js"/>"></script>
	<script src="<c:url value="/indus/resources/custom_js/index.js"/>"></script>
	
	<!-- start: CLIP-TWO JAVASCRIPTS -->
	
	<script src="<c:url value="/indus/resources/assets/js/main.js"/>"></script>
	
	<script src="<c:url value="/indus/resources/vendor/jquery-ui/jquery-ui-1.10.1.custom.min.js"/>"></script>
	<script src="<c:url value="/indus/resources/custom_js/indus_database.js"/>"></script>
	
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
