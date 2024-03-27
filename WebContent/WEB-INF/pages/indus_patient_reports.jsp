<%@page import="com.hms.indus.bo.ClientMaster"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- <!DOCTYPE html> -->
<!-- Template Name: Clip-Two - Responsive Admin Template build with Twitter Bootstrap 3.x | Author: ClipTheme -->
<!--[if IE 8]><html class="ie8" lang="en"><![endif]-->
<!--[if IE 9]><html class="ie9" lang="en"><![endif]-->
<!--[if !IE]><!-->
<!-- <html lang="en">
<![endif]
start: HEAD
<head>
<title>Indus|My Reports</title>
start: META
[if IE]><meta http-equiv='X-UA-Compatible' content="IE=edge,IE=9,IE=8,chrome=1" /><![endif]
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="" name="description" />
<meta content="" name="author" /> -->
<!-- end: META -->
<!-- start: GOOGLE FONTS -->
<!-- <link
	href="http://fonts.googleapis.com/css?family=Lato:300,400,400italic,600,700|Raleway:300,400,500,600,700|Crete+Round:400italic"
	rel="stylesheet" type="text/css" /> -->
<!-- end: GOOGLE FONTS -->
<!-- start: MAIN CSS -->
<%-- <link rel="stylesheet" type="text/css"
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
	rel="stylesheet" media="screen"> --%>
<!-- end: MAIN CSS -->

<!-- start: CLIP-TWO CSS -->
<%-- <link rel="stylesheet"
	href="<c:url value="/indus/resources/assets/css/styles.css"/>">
<link rel="stylesheet"
	href="<c:url value="/indus/resources/assets/css/plugins.css"/>">
<link rel="stylesheet"
	href="<c:url value="/indus/resources/assets/css/themes/theme-4.css"/>" /> --%>
<!-- end: CLIP-TWO CSS -->
<!-- start: CSS REQUIRED FOR THIS PAGE ONLY -->
<%-- <link href="<c:url value="/indus/resources/vendor/bootstrap-fileinput/jasny-bootstrap.min.css"/>"
	rel="stylesheet" media="screen"> --%>
	
	
	<!-- tooltip css -->
	<%-- <link href="<c:url value="/indus/resources/vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.0.min.css"/>"
	rel="stylesheet" media="screen">
	<link href="<c:url value="/indus/resources/vendor/bootstrap-rating/bootstrap-rating.css"/>"
	rel="stylesheet" media="screen"> --%>
	
<!-- end: CSS REQUIRED FOR THIS PAGE ONLY -->

<style type="text/css">
.centered {
	width: 100%;
	margin-left: auto;
	margin-right: auto;
	text-align: center;
}
</style>

<!-- </head> -->
<!-- end: HEAD -->
<%
	ClientMaster clientMaster=(ClientMaster)request.getAttribute("client");
	String clientFirstName="";
	if((String)session.getAttribute("userFirstName")!=null)
	{
		clientFirstName=(String)session.getAttribute("userFirstName");
	}
%>
<body>

		<%
			HttpSession httpSession=request.getSession();
			if((String)httpSession.getAttribute("loginUserType")!=null){
		%>
		<span id="clientLoginUserType" class="hidden">1</span>
		<%
			}
		%>

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
											
											<%
		//HttpSession httpSession=request.getSession();
		if((String)session.getAttribute("loginUserType")!=null){
		
		}
		else{
	%>
											
											<div class="anchor_width pull-right interactionTabHide timeline" style="display:none;">
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
												<li onclick="loadDemoghraphicPage()" class="padding-top-5 padding-left-5 demoghraphicTab menu_3" style="display:none;"><a
													data-toggle="tab" href="#panel_overview"> Demographics
												</a></li>
												<li onclick="loadReportPage()" class="active padding-top-5 myReportTab menu_4" style="display:none;"><a data-toggle="tab"
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
												<li onclick="loadContactHubPage()" class="padding-top-5 interactionTabShow" style="display:none;"><a data-toggle="tab"
													href="#contactHub"> Contact Hub </a></li>
												<li onclick="loadDataEntryPage()" class="padding-top-5 interactionTabShow" style="display:none;"><a data-toggle="tab"
													href="#dataEntry"> Data Entry </a></li>
												<!-- <li class="padding-top-5"><a data-toggle="tab"
													href="#users_following"> Call Log </a></li> -->
											</ul>
											<div class="tab-content">
												<div id="users_following" class="tab-pane fade padding-bottom-5 in active">
													<div class="row">
														
														<!-- <div class="col-md-2 col-sm-6 col-xs-12">
								<select	class="form-control center" id="visitSelectOnMyReportPage" onchange="changeVisitSelectList(this)">
								</select>
							</div>
							<div class="col-md-2 col-sm-6 col-xs-12">
								<input id="showVisitDateOnMyReportPage" class="form-control center" type="text" disabled>
							</div>
							<div class="col-md-2 col-sm-6 col-xs-12">
								<select	class="form-control center" id="visitTypeMasterSelectOnMyReportPage" disabled>
								</select>
							</div> -->
														<div class="col-md-offset-6 col-md-4 col-sm-6 col-xs-12">
															<a id="verificationFailed" class="list-group-item list-group-item-danger hidden"> <b>Oh Snap! Verification Failed -
																	Check & Change all marked reports</b> and try submitting
																agian.
															</a>
														</div>
														<div class="col-md-2 col-sm-6 col-xs-12">
															<div class="col-md-12 space20">
																<button type="button" class="btn btn-primary start btn-sm" id="uploadReportBtn" style="display:none;">
																	<i class="glyphicon glyphicon-upload"></i> <span>Upload</span>
																</button>
															</div>
														</div>
													</div>
													<div class="panel-scroll ps-container table-responsive mousescroll" style="max-height: 280px;">
														<table class="table no-margin">
															<thead>
																		<tr>
																			<th class="center">Sr. No.</th>
																			<th></th>
																			<th>Test Name</th>
																			<th>Centre Name</th>
																			<th class="reportUploader" style="display:none;">Uploader</th>
																			<th class="reportUploaderDate" style="display:none;">Upload Date</th>
																			<th>Report Date</th>
																			<th>Action</th>
																		</tr>
																	</thead>
																	<tbody id="uploadReportData">
																		
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
												
												<div id="hra" class="tab-pane fade padding-bottom-5">
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
												
												
												<div id="verification" class="tab-pane fade padding-bottom-5">
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
		src="<c:url value="/indus/resources/vendor/switchery/switchery.min.js"/>"></script>  --%>

	<!-- end: MAIN JAVASCRIPTS -->
	<!-- start: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
	<%-- <script
		src="<c:url value="/indus/resources/vendor/bootstrap-fileinput/jasny-bootstrap.js"/>"></script> --%>

	<!-- end: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
	<!-- start: CLIP-TWO JAVASCRIPTS -->
	<%-- <script src="<c:url value="/indus/resources/assets/js/main.js"/>"></script>  --%>
	<%-- <script src="<c:url value="/indus/resources/vendor/jquery-file-upload/vendor/jquery.ui.widget.js"/>"></script>
		<!-- The Templates plugin is included to render the upload/download listings -->
		<script src="http://blueimp.github.io/JavaScript-Templates/js/tmpl.min.js"></script>
		<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
		<script src="<c:url value="/indus/resources/vendor/javascript-Load-Image/load-image.all.min.js"/>"></script>
		<!-- The Canvas to Blob plugin is included for image resizing functionality -->
		<script src="http://blueimp.github.io/JavaScript-Canvas-to-Blob/js/canvas-to-blob.min.js"></script>
		<!-- blueimp Gallery script -->
		<script src="http://blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script>
	<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
		<script src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.iframe-transport.js"/>"></script>
		<!-- The basic File Upload plugin -->
		<script src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload.js"/>"></script>
		<!-- The File Upload processing plugin -->
		<script src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload-process.js"/>"></script>
		<!-- The File Upload image preview & resize plugin -->
		<script src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload-image.js"/>"></script>
		<!-- The File Upload audio preview plugin -->
		<script src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload-audio.js"/>"></script>
		<!-- The File Upload video preview plugin -->
		<script src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload-video.js"/>"></script>
		<!-- The File Upload validation plugin -->
		<script src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload-validate.js"/>"></script>
		<!-- The File Upload user interface plugin -->
		<script src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload-ui.js"/>"></script>
		<!-- The main application script -->
		<script src="<c:url value="/indus/resources/vendor/jquery-file-upload/main.js"/>"></script> --%>

<!-- {% if (file.error) { %}
			<div><span class="label label-danger">Error</span> {%=file.error%}</div>
			{% } %} -->
	<!-- start: JavaScript Event Handlers for this page -->
	<%-- <script src="<c:url value="/indus/resources/assets/js/main.js"/>"></script> --%>
	
	<script src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>"></script>
	
	<!-- tooltip js -->
		<script src="<c:url value="/indus/resources/assets/js/ui-elements.js"/>"></script>
		<script src="<c:url value="/indus/resources/vendor/bootstrap-progressbar/bootstrap-progressbar.min.js"/>"></script>
		<script src="<c:url value="/indus/resources/vendor/twbs-pagination/jquery.twbsPagination.min.js"/>"></script>
		<script src="<c:url value="/indus/resources/vendor/bootstrap-rating/bootstrap-rating.min.js"/>"></script>
	<!-- tooltip js -->
	
	<script src="<c:url value="/indus/resources/custom_js/demograhics.js"/>"></script>
	<%-- <script src="<c:url value="/indus/resources/custom_js/index.js"/>"></script> --%>
	
	<script>
		jQuery(document).ready(function() {
			//Main.init();
			getClientCenterPackageByUserId($('#clientMasterId').html());
			//UIElements.init();
			jQuery.noConflict();
			/* $('[data-toggle="tooltip"]').tooltip(); */
		});
		
	</script>
	
<%--<script src="<c:url value="/indus/resources/assets/js/jquery.ajaxfileupload.js"/>"></script>--%>
<%-- <script src="<c:url value="/indus/resources/assets/js/jquery.form.js"/>" ></script>
<script src="<c:url value="/indus/resources/assets/js/fileUploadScript.js"/>"></script>  --%>

	<!-- end: JavaScript Event Handlers for this page -->
	<!-- end: CLIP-TWO JAVASCRIPTS -->
</body>
</html>
