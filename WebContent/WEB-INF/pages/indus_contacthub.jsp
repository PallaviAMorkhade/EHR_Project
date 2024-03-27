<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
	
 <!-- for smart search -->
 <link href="<c:url value="/indus/resources/vendor/jquery-ui/jquery-ui-1.10.1.custom.min.css"/>"
	rel="stylesheet" media="screen">
	
 <script
		src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>"></script>
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

<body>
	<div id="app">
		<div class="app-content">
			<!-- end: TOP NAVBAR -->
				<div class="wrap-content container" id="container">
					<!-- start: DYNAMIC TABLE -->
					<div class="container-fluid container-fullw bg-white" id='ajaxContent'>
						<div class="row">
							<div class="col-md-3 col-sm-4 col-xs-9">
								<h5 class="text-bold margin-top-25 margin-bottom-15">Date Range</h5>
								<div class="input-group input-daterange datepicker">
									<input id="startDate" name="startDate" type="text" class="form-control" onchange="assignDate();" /> <span
										class="input-group-addon bg-primary">to</span> <input
										id="endDate" type="text" class="form-control" />
								</div>
							</div>
							 <div class="col-md-1 col-sm-2 col-xs-2 margin-top-57">
								<button type="button" class="btn btn-info"
									onclick="getRecordByDate()">Search</button>
							</div> 
														
							<div class="col-md-2 col-sm-6 col-xs-12 margin-top-3">
								<div class="form-group">
									<h5 class="text-bold margin-top-25 margin-bottom-15">Name</h5>
									<input class="form-control" placeholder="Search" id="searchBox" name="searchBox" type="search" autocomplete="off">
								</div>
							</div>

							<div class="col-md-1 col-sm-6 col-xs-12 margin-top-3">
								<div class="form-group">
									<h5 class="text-bold margin-top-25 margin-bottom-15">Client Id</h5>
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
						<div class="col-md-1 col-sm-1 col-xs-12 margin-top-3">
							<div class="form-group">
								<h5 class="text-bold margin-top-25 margin-bottom-15">Email/Sms</h5>
								<button type="button" class="btn btn-info"
									onclick="sendEmailAndSmsBtnNew()">Send</button>

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
											<th>View</th>
											<th>Action</th>
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

<script src="<c:url value="/indus/resources/vendor/jquery-ui/jquery-ui-1.10.1.custom.min.js"/>"></script>
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
		
	<script src="<c:url value="/indus/resources/custom_js/indus_database.js"/>"></script>


	<!-- start: JavaScript Event Handlers for this page -->
	<script>
		jQuery(document).ready(function() {
			/* $('.datepicker').datepicker({
		        format: 'dd-mm-yyyy',
		        autoclose: true
		    }); */
			
			//Main.init();
			FormElements.init();
			jQuery.noConflict();
		});
	</script>

</body>
</html>
