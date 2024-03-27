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

<!-- <link rel="stylesheet" href="assets/css/themes/theme-1.css" id="skin_color" /> -->

<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/assets/css/themes/theme-4.css"/>">

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
<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/assets/css/jquery.typeahead.css"/>">

<link href="<c:url value="/indus/resources/vendor/bootstrap-fileinput/jasny-bootstrap.min.css"/>"
	rel="stylesheet" media="screen">	 

<%-- <link rel="stylesheet" id="skin_color" type="text/css"
	href="<c:url value="/indus/resources/assets/css/themes/theme-1.css"/>" > --%>

<!-- end: CLIP-TWO CSS -->
<!-- start: CSS REQUIRED FOR THIS PAGE ONLY -->
<!-- end: CSS REQUIRED FOR THIS PAGE ONLY -->
<!-- </head> -->
<!-- end: HEAD -->

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

<%
	String clientFirstName = (String) session
			.getAttribute("userFirstName");
%>
<body>
	<div id="app">
		<!-- sidebar -->
		<%-- <jsp:include page="indus_header.jsp"></jsp:include> --%>
		<!-- / sidebar -->
		<div class="app-content">
			<!-- start: TOP NAVBAR -->
			<%-- <jsp:include page="indus_left_menu.jsp"></jsp:include> --%>
			<!-- end: TOP NAVBAR class="main-content"-->
			<div>
				<div class="wrap-content container" id="container">
					<!-- start: DASHBOARD TITLE -->
					<%-- <section id="page-title">
						<div class="row">
							<div class="col-sm-7 padding_zero">
								<!-- <h1 class="mainTitle">Indus EHR know your health the smart way</h1> -->
								<font style="font-size: 17px;"><b>Welcome</b><b> <%=clientFirstName%></b></font>
							</div>
							<div class="col-sm-5"></div>
						</div>
					</section> --%>
					<!-- end: DASHBOARD TITLE -->
					<!-- start: DYNAMIC TABLE -->
					<div class="container-fluid container-fullw bg-white">
						<h4>Account management</h4>
						<!-- <div class="row" id="selectUserTypeDiv" style="display:none;">
							<div class="col-md-2">
								<label>Select User Type:</label>
							</div>
							<div class="col-md-3">
								<select class="form-control" id="userTypeMaster" name="userTypeMaster">
								</select>
							</div>
						</div> -->
						<div class="row" id="selectUserTypeDiv" style="display:none;">
							<div class="col-md-3 col-sm-6 col-xs-12 margin-top-3">
								<div class="form-group">
									<h5 class="text-bold margin-bottom-15">Select User Type</h5>
									<select class="form-control" id="userTypeMaster">
									</select>
								</div>
							</div>
							<div class="col-md-3 col-sm-6 col-xs-12 margin-top-3">
								<div class="form-group">
									<h5 class="text-bold margin-bottom-15">User Name</h5>
									<input class="form-control" placeholder="Search By Name"
										id="searchByUserName" onkeyup="searchByUserName()">
								</div>
							</div>
							<div class="col-md-3 col-sm-6 col-xs-12 margin-top-3">
								<div class="form-group">
									<h5 class="text-bold margin-bottom-15">User Id</h5>
									<input class="form-control" placeholder="Search By Id"
										id="searchByUserId">
								</div>
							</div>
							<div class="col-md-3 col-sm-6 col-xs-12 margin-top-3">
								<div class="form-group">
									<h5 class="text-bold margin-bottom-15">User Type</h5>
									<select onchange="listOfUserMaster()" class="form-control" id="searchByUserType">
									</select>
								</div>
							</div>
						</div>
						<div class="row" style="margin-top:10px;">
							<div id="createUserDiv" class="col-md-5 col-xs-11" style="margin-bottom:10px;border: 1px solid; padding: 20px; margin-left: 16px;max-height:450px;min-height:450px;overflow-y:scroll;display:none;">
								<div id="userMasterInfoDiv" style="display:none;">
								<span class="hidden" id="userId"></span>
								<div class="form-group">
									<label class="control-label"> Title <span
										class="symbol required"></span>
									</label> <select class="form-control" id="userTypeSelectTitle" name="userTypeSelectTitle">
										<option value="">Select Title</option>
										<option value="Dr.">Dr.</option>
										<option value="Mr.">Mr.</option>
										<option value="Mrs.">Mrs.</option>
										<option value="Miss.">Miss.</option>
									</select> 
								</div>
								<div class="form-group">
									<label class="control-label"> First Name <span
										class="symbol required"></span>
									</label> <input type="text" placeholder="First Name"
										class="form-control" id="firstName" name="firstName">
								</div>
								<div class="form-group">
									<label class="control-label"> Middle Name
									</label> <input type="text" placeholder="Middle Name"
										class="form-control" id="middleName" name="middleName">
								</div>
								<div class="form-group">
									<label class="control-label"> Last Name <span
										class="symbol required"></span>
									</label> <input type="text" placeholder="Last Name"
										class="form-control" id="lastName" name="lastName">
								</div>
								<div class="form-group">
									<label class="control-label"> User Name <span
										class="symbol required"></span>
									</label> <input type="text" placeholder="User Name" onkeyup="isAvailEmailId()"
										class="form-control" id="userName" name="userName">
										<span class="errorUserName_1" id="userNameErrorSpan" style="color: red;display:none;">Sorry User name already exist</span>
										<span id="currentUserNameSpan" style="display:none;"></span>
								</div>
								<div class="form-group">
									<label class="control-label"> Password <span
										class="symbol required"></span>
									</label> <input type="text" placeholder="Password"
										class="form-control" id="password" name="password">
								</div>
								
								<div id="forHRUser" style="display:none;" class="form-group">
									<label class="control-label"> Member Code<span
										class="symbol required"></span>
									</label> <input type="text" placeholder="Member Code"
										class="form-control" id="memberCode" name="memberCode">
								</div>
								
								<div class="form-group">
									<label class="control-label"> Center
									</label> <select class="form-control" id="centerAccSelect" name="centerAccSelect" multiple>
										
									</select> 
								</div>
								<div class="form-group">
									<label class="control-label"> User Type
									</label> <select class="form-control" id="userTypeSelect" name="userType" disabled>
										
									</select> 
								</div>
								
								<div id="forDataEntryOperator" style="display:none;">
									<div class="form-group">
									<label class="control-label"> Test / Package
									</label> 
								<select id="forAsigningTestPackage" class="form-control" onchange="testPackageAccountManagement()">
									<option val="package">Package</option><option val="test">Test</option>
								</select>
								</div>
								<div class="form-group">
									<label class="control-label"> Select Test/Package
									</label> 
									<select	class="form-control" id="testPackageListAccMngt" multiple>
									</select>
								</div>
								</div>								
								
								
								<div class="row">
									<div class="col-md-3 col-md-offset-5 col-xs-3 col-xs-offset-5" id="saveUserMasterBtnDiv" style="padding: 5px 5px 15px;">
									<button class="btn btn-primary btn-wide pull-right" onclick="saveUserMaster()"
										type="button" id="saveUserMasterBtn">
										Save <i class="fa fa-arrow-circle-right"></i>
									</button>
									</div>
									
									<div id="updateUserMasterBtnDiv" style="display:none;padding: 5px 5px 45px 5px;">
									<div class="col-md-3 col-md-offset-3 col-xs-3 col-xs-offset-3">
									<button class="btn btn-primary btn-wide pull-right" onclick="updateUserMaster()"
										type="button" id="updateUserMasterBtn">
										Update
									</button>
									</div>
									<div class="col-md-3 col-xs-4" style="margin-left: 30px;">
									<button class="btn btn-primary btn-wide pull-right"
										type="button" id="cancelUserMasterBtn">
										Cancel
									</button>
									</div>
									</div>
								</div>
							</div>
							</div>
							<div class="col-md-6" style="max-height: 450px; overflow-y: auto;">
								<table
									class="table table-striped table-bordered table-hover table-full-width"
									id="sample_1">
									<thead>
										<tr>
											<th>#</th>
											<th>Name</th>
											<th>User Type</th>
											<th class="userAccessEditTableHeading">Edit</th>
											<th class="userAccessDeleteTableHeading">Delete</th>
											<!-- <th>Status</th> -->
										</tr>
									</thead>
									<tbody id="userMasterTableBody">

									</tbody>
								</table>
							</div>
						</div>				
					</div>
					<!-- end: DYNAMIC TABLE -->
				</div>
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
		src="<c:url value="/indus/resources/vendor/Chart.js/Chart.min.js"/>"></script> --%>

	<%-- <script
		src="<c:url value="/indus/resources/vendor/jquery.sparkline/jquery.sparkline.min.js"/>"></script> --%>


	<!-- end: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
	<!-- start: CLIP-TWO JAVASCRIPTS -->
	<%-- <script src="<c:url value="/indus/resources/assets/js/main.js"/>"></script> --%>
	<!-- start: JavaScript Event Handlers for this page -->

	<%-- <script src="<c:url value="/indus/resources/assets/js/index.js"/>"></script> --%>

	<%-- <script
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

	<!-- for smart search -->
	<script
		src="<c:url value="/indus/resources/assets/js/jquery.typeahead.js"/>"></script> --%>
		
	<!-- later code -->	
	<%-- <script
		src="<c:url value="/indus/resources/vendor/bootstrap-fileinput/jasny-bootstrap.js"/>"></script> --%>

	<%-- <script src="<c:url value="/indus/resources/custom_js/demograhics.js"/>"></script> --%>
	<%-- <script src="<c:url value="/indus/resources/custom_js/indus_header.js"/>"></script> --%>
	<script src="<c:url value="/indus/resources/vendor/jquery-ui/jquery-ui-1.10.1.custom.min.js"/>"></script>
	<script src="<c:url value="/indus/resources/custom_js/indus_account_management.js"/>"></script>
	
	<script>
		jQuery(document)
				.ready(
						function() {
							//Main.init();
							//FormElements.init();
							/* listOfUserTypeMaster();
							listOfUserMaster(); */
						});

	</script>
	<%-- <script src="<c:url value="/indus/resources/custom_js/indus_database.js"/>"></script>
	<script src="<c:url value="/indus/resources/custom_js/indus_hra.js"/>"></script> --%>
	<!-- end: JavaScript Event Handlers for this page -->
	<!-- end: CLIP-TWO JAVASCRIPTS -->
</body>
</html>
