<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String clientFirstName = (String) session
			.getAttribute("userFirstName");
%>

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

<body>
	<div id="app">
		<div class="app-content">
			<div>
				<div class="wrap-content container" id="container">

					<!-- start: DYNAMIC TABLE -->
					<div class="container-fluid container-fullw bg-white">
						<h4>Add Health Feed Master</h4>
							<div class="row">
							<div id="addHealthFeedMasterDiv" class="col-md-5 col-xs-11" style="margin-bottom:10px;border: 1px solid; padding: 20px; margin-left: 16px;">
								<div class="form-group">
									<label class="control-label">Health Feed Id <!-- <span
										class="symbol required"></span> -->
									</label> 
									<input value="1" type="text" placeholder="" class="form-control" id="healthFeedId" name="healthFeedId" disabled>
								</div>
								<div class="form-group">
									<label class="control-label"> Health Feed <span
										class="symbol required"></span>
									</label> <input type="text" placeholder="Health Feed"
										class="form-control" id="healthFeed" name="healthFeed">
								</div>
								
								<div class="form-group">
									<label class="control-label"> Health Feed Description <span
										class="symbol required"></span>
									</label> <textarea placeholder="Health Feed description"
										class="form-control" id="healthFeedDescription" name="healthFeedDescription"></textarea>
								</div>
								<div class="form-group">
									<label class="control-label"> Date <span
										class="symbol required"></span></label> <input
										class="form-control datepicker" placeholder="Date"
										id="healthFeedDate" type="text">
								</div>
								
								<div class="form-group">
									<label class="control-label"> Feed Type <span
										class="symbol required"></span>
									</label> <select class="form-control" id="feedTypeMasterSelect"
										name="feedTypeMasterSelect"></select>
								</div>
								
								<span id="healthFeedMasterId" class="hidden"></span>
								<div class="row">
									<div class="col-md-3 col-md-offset-5 col-xs-3 col-xs-offset-5" id="saveHealthFeedBtnDiv">
									<button class="btn btn-primary btn-wide pull-right" onclick="saveHealthFeedMaster()"
										type="button" id="saveHealthFeedMasterBtn">
										Save <i class="fa fa-arrow-circle-right"></i>
									</button>
									</div>
									
									<div id="updateHealthFeedBtnDiv" style="display:none;">
									<div class="col-md-3 col-md-offset-3 col-xs-3 col-xs-offset-3">
									<button class="btn btn-primary btn-wide pull-right" onclick="updateHealthFeedMaster()"
										type="button" id="updateHealthFeedMasterBtn">
										Update
									</button>
									</div>
									<div class="col-md-3 col-xs-4" style="margin-left: 30px;">
									<button class="btn btn-primary btn-wide pull-right"
										type="button" id="cancelHealthFeedMasterBtn">
										Cancel
									</button>
									</div>
									</div>
									
								</div>

							</div>
							<div class="col-md-6 col-xs-12 table-responsive" style="max-height: 470px; overflow-y: auto;">
								<table
									class="table table-striped table-bordered table-hover table-full-width"
									id="sample_1">
									<thead>
										<tr>
											<th>#</th>
											<th>Health Feed</th>
											<th>Feed Type</th>
											<th class="userAccessEditTableHeading">Edit</th>
											<th class="userAccessDeleteTableHeading">Delete</th>
										</tr>
									</thead>
									<tbody id="healthFeedMasterTableBody">

									</tbody>
								</table>
							</div>
						</div>				
					</div>
					<!-- end: DYNAMIC TABLE -->

				</div>
			</div>
		</div>
		</div>
		
		<!-- start: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->

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

	<script src="<c:url value="/indus/resources/custom_js/indus_health_feed_master.js"/>"></script>

	<!-- start: JavaScript Event Handlers for this page -->
	<script>
		jQuery(document).ready(function() {
			//Main.init();
			FormElements.init();
			jQuery.noConflict();
		});
	</script>
		
</body>
</html>
