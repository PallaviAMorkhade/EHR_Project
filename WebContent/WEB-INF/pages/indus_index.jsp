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
<%-- <link
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
	href="<c:url value="/indus/resources/assets/css/themes/theme-4.css"/>"> --%>

<%-- <link rel="stylesheet" id="skin_color" type="text/css"
	href="<c:url value="/indus/resources/assets/css/themes/theme-1.css"/>" > --%>

<!-- end: CLIP-TWO CSS -->
<!-- start: CSS REQUIRED FOR THIS PAGE ONLY -->
<!-- end: CSS REQUIRED FOR THIS PAGE ONLY -->
</head>
<!-- end: HEAD -->
<%
	String clientFirstName=(String)session.getAttribute("userFirstName");
%>
<body>
	<span id="clientMasterId" class="hidden"></span>
	<span id="clientUserId" class="hidden"></span>
	<div id="app">
		<!-- sidebar -->
			<jsp:include page="indus_header.jsp"></jsp:include>
		<!-- / sidebar -->
		<div class="app-content">
			<!-- start: TOP NAVBAR -->
				<jsp:include page="indus_left_menu.jsp"></jsp:include>
			<!-- end: TOP NAVBAR -->
			<div class="main-content">
				<div class="wrap-content container" id="container" >
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
					
						<jsp:include page="indus_coming_soon.jsp"></jsp:include>
					
					</div>
					<!-- end: DASHBOARD TITLE -->
				
				</div>
			</div>
		</div>
		<!-- start: FOOTER -->
		<footer>
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
		</footer>
		<!-- end: FOOTER -->
		
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
	<%-- <script
		src="<c:url value="/indus/resources/vendor/Chart.js/Chart.min.js"/>"></script> --%>

	<script
		src="<c:url value="/indus/resources/vendor/jquery.sparkline/jquery.sparkline.min.js"/>"></script>


	<!-- end: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
	<!-- start: CLIP-TWO JAVASCRIPTS -->
	<script src="<c:url value="/indus/resources/assets/js/main.js"/>"></script>
	<!-- start: JavaScript Event Handlers for this page -->
	<%-- <script src="<c:url value="/indus/resources/custom_js/indus_header.js"/>"></script> --%>
	<script src="<c:url value="/indus/resources/custom_js/index.js"/>"></script>
	
	<%-- <!-- tooltip js -->
		<script src="<c:url value="/indus/resources/assets/js/ui-elements.js"/>"></script>
		<script src="<c:url value="/indus/resources/vendor/bootstrap-progressbar/bootstrap-progressbar.min.js"/>"></script>
		<script src="<c:url value="/indus/resources/vendor/twbs-pagination/jquery.twbsPagination.min.js"/>"></script>
		<script src="<c:url value="/indus/resources/vendor/bootstrap-rating/bootstrap-rating.min.js"/>"></script>
	<!-- tooltip js --> --%>
	<script>
			jQuery(document).ready(function() {
				Main.init();
				//UIElements.init();
				//callWebServiceGetBeneficiary();
			});
		</script>
	<!-- end: JavaScript Event Handlers for this page -->
	<!-- end: CLIP-TWO JAVASCRIPTS -->
</body>
</html>
