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
<title>Indus|Data Entry</title>
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
	
<!-- for new jstree -->
<link rel="stylesheet" href="<c:url value="/indus/resources/assets/css/jquery.treeview.css"/>">
<!-- End of jstree -->	
	
</head>
<!-- end: HEAD -->
<%
	ClientMaster clientMaster=(ClientMaster)request.getAttribute("client");
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
			<!-- end: TOP NAVBAR -->
			<!-- <div class="main-content"> -->
				<div class="wrap-content container" id="container">
					<h4 class="no-margin inline-block padding-5">
												<%=clientMaster.getFirstName() +" "+  clientMaster.getLastName()%>
												<span class="block text-small text-left"></span>
					</h4>

				<button onclick="loadDemoghraphicPage()" type="button" class="btn btn-primary interactionTabShow" style="display: none;">
					Back to Engagement
				</button>

				<!-- start: DYNAMIC TABLE -->
					<div class="container-fluid container-fullw bg-white">
						<div class="row" style="margin-bottom: 10px;">
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
									
						<div class="row">
							<div class="col-md-12 table-responsive" style="max-height: 500px; overflow-y: scroll;">
								<table
									class="table table-striped table-bordered table-hover table-full-width"
									id="sample_1">
									<thead>
										<tr>
																			<th class="center">#</th>
																			<th></th>
																			<th>Test Name</th>
																			<th>Centre Name</th>
																			<th>Uploader</th>
																			<th>Report Verify Date</th>
																		</tr>
									</thead>
									<tbody id="verifiedPatientReportData">

									</tbody>
								</table>
							</div>
						</div>
					</div>
					<!-- end: DYNAMIC TABLE -->

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

	<!-- for smart search -->
	<%-- <script
		src="<c:url value="/indus/resources/assets/js/jquery.typeahead.js"/>"></script> --%>
		
		
	<!-- //later code	 -->
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
		
		<!-- end: MAIN JAVASCRIPTS -->
		
		<!-- start: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
		<script src="<c:url value="/indus/resources/vendor/jstree/jstree.min.js"/>"></script>
		<!-- end: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
		<!-- start: CLIP-TWO JAVASCRIPTS -->
		<script src="<c:url value="/indus/resources/assets/js/main.js"/>"></script>
		<!-- start: JavaScript Event Handlers for this page -->
		<script src="<c:url value="/indus/resources/assets/js/ui-treeview.js"/>"></script>
		
		<script src="<c:url value="/indus/resources/assets/js/jquery.treeview.js"/>"></script>
		<script src="<c:url value="/indus/resources/assets/js/demo.js"/>"></script>
		
		<script>
			jQuery(document).ready(function() {
				//Main.init();
				UITreeview.init();
				
				$(function() {
				    $('.current-user').on('click', function() {
				        $(this).toggleClass('open');
				    });
				});
				
			});

	</script>
	<%-- <script src="<c:url value="/indus/resources/custom_js/indus_header.js"/>"></script> --%>

	<script src="<c:url value="/indus/resources/custom_js/indus_data_management.js"/>"></script>
	
		
		<!--End of jstree new -->

	<!-- end: JavaScript Event Handlers for this page -->
	<!-- end: CLIP-TWO JAVASCRIPTS -->


	<div class="modal fade bs-example-modal-lg" id="dataEntryReportModal"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<div class="modal-dialog modal-dialog modal-lg" style="width: 98%;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<div class="row">
						<div class="col-md-2 col-xs-11">
							<h4 class="modal-title" id="myModalLabel">Data entry</h4>
						</div>
						<div class="col-md-6 col-xs-11">
							<h4 class="modal-title" id="testNameDataEntry"></h4>
						</div>
					</div>
				</div>
				<div class="modal-body">
					<div class="row">
						<span class="hidden" id="dataEntryClientReportLineId"></span> <span
							class="hidden" id="dataEntryTestId"></span> <span class="hidden"
							id="centerId"></span> <span class="hidden" id="packageId"></span>
						<div class="col-sm-8 col-md-8">

							<div class="tabbable no-margin no-padding">
								<ul class="nav nav-tabs" id="myTab">
									<li class="active padding-top-5 padding-left-5"><a
										data-toggle="tab" href="#pdf"> PDF </a></li>
									<li class="padding-top-5"><a data-toggle="tab"
										href="#text"> Text </a></li>
								</ul>
								<div class="tab-content">

									<div id="pdf" class="active tab-pane fade in">
										<!-- <iframe id="dataEntryReport" src="" width="102%" height="470px"></iframe> -->
										<object id="dataEntryReport" data="" width="102%"
											height="470px"></object>
									</div>
									<div id="text" class="tab-pane fade in">
										<!-- <iframe id="dataEntryReportText" src="" width="102%" height="470px"></iframe> -->
										<object id="dataEntryReportText" data="" width="102%"
											height="470px"></object>
									</div>


								</div>

							</div>

						</div>
						<div class="col-sm-4 col-md-4" id="displayTestDiv"
							style="display: none; overflow: auto; max-height: 515px;">
							<table
								class="table table-striped table-bordered table-hover table-full-width"
								id="sample_1">
								<thead>
									<tr>
										<th>Parameter Name</th>
										<th>Parameter Result</th>
										<th>Normal Values</th>
									</tr>
								</thead>
								<tbody id="testReportData">
								</tbody>
							</table>
						</div>


						<div class="col-sm-5 col-md-4" id="displayPackageDiv"
							style="display: none;">
							<div class="panel panel-white">
								<!-- <div class="panel-heading">
								<h4 class="panel-title">
									Basic <span class="text-bold">Tree</span>
								</h4>
							</div> -->
								<div class="panel-body"
									style="max-height: 515px; overflow-y: scroll;">
									<ul id="testList"></ul>

									<ul id="browser" class="filetree">

									</ul>
								</div>
							</div>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<div class="row">
					<input type="hidden" id="workStatus">
						<div class="col-md-4 dataEntryCommentDiv" style="display:none;">
							<div class="form-group">
								<textarea class="form-control" id="dataEntryComment"
									style="max-width: 100%; width: 584px; height: 66px;"
									maxlength="5000" placeholder="Comment"></textarea>
							</div>
						</div>
						<div class="col-md-2 dataEntryCommentDiv" style="display:none;">
							<button id="dataEntryVerifyBtn" onclick="dataEntryVerify(true)"
								class="btn btn-primary btn-wide btn-sm" type="button"
								style="margin-right: 4px;">Verify</button>
							<span id="verifiedMessage" style="display:none;" class="color_green">Verified <i class="fa fa-check"></i></span>
							<button id="dataEntryRejectBtn" style="margin-top: 5px;" onclick="dataEntryVerify(false)"
								class="btn btn-danger btn-wide btn-sm" type="button">
								Reject</button>
							<span id="rejectedMessage" style="display:none;" class="color_red">Rejected <i class="fa fa-close"></i></span>
						</div>
						<div class="col-md-2 pull-right">
							<button type="button" class="btn btn-primary btn-o"
								data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary saveResultBtn" id="">Save
							</button>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>

</body>
</html>