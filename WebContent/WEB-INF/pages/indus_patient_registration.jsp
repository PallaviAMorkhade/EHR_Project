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

<script
	src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>"></script>

<body>
	<div id="app">
		<div class="app-content">
			<!-- <div class="main-content"> -->
			<div class="wrap-content container" id="container">
				<h4 style="margin-top: 5px;">Client Registration</h4>
				<!-- start: USER PROFILE -->
				<div class="container-fluid container-fullw bg-white">
					<form name="patientRegisterForm" id="patientRegisterForm"
						role="form">
						<div class="row">
							<div class="col-md-3">
								<div class="form-group">
									<label class="control-label"> Gender <span
										class="symbol required"></span></label> <select id="gender"
										class="form-control" onchange="removeBorder(this)">
										<option value="">Gender</option>
										<option value="M">Male</option>
										<option value="F">Female</option>
									</select>
								</div>
							</div>

							<div class="col-lg-3">
								<div class="form-group">
									<label class="control-label"> First Name <span
										class="symbol required"></span></label> <input type="text"
										class="form-control" id="firstName" placeholder="First Name"  onchange="removeBorder(this)">
								</div>
							</div>

							<div class="col-lg-3">
								<div class="form-group">
									<label class="control-label"> Middle Name </label> <input
										type="text" class="form-control" id="middleName"
										placeholder="Middle Name">
								</div>
							</div>

							<div class="col-lg-3">
								<div class="form-group">
									<label class="control-label"> Last Name <span
										class="symbol required"></span></label> <input type="text"
										class="form-control" id="lastName" placeholder="last Name"  onchange="removeBorder(this)">
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-lg-3">
								<div class="form-group">
									<label class="control-label"> Date of Birth </label> <input
										class="form-control disableDate" placeholder="Date of Birth"
										id="dateOfBirth" type="text">
								</div>
							</div>
							<div class="col-lg-3">
								<div class="form-group">
									<label class="control-label">Mobile Number</label> <input
										type="text" class="form-control" id="mobNo"
										placeholder="Mobile Number" onkeyup="numericOnly(this)">
								</div>
							</div>

							<div class="col-lg-3">
								<div class="form-group">
									<label class="control-label">Email Id</label> <input
										type="text" class="form-control" id="emailId"
										placeholder="Email Id">
								</div>
							</div>

							<div class="col-lg-3">
								<div class="form-group">
									<label class="control-label"> Address Line 1 </label> <input
										type="text" class="form-control" id="addressLine1"
										placeholder="Address Line 1">
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-lg-3">
								<div class="form-group">
									<label class="control-label"> Address Line 2 </label> <input
										type="text" class="form-control" id="addressLine2"
										placeholder="Address Line 2">
								</div>
							</div>

							<div class="col-lg-3">
								<div class="form-group">
									<label class="control-label"> Address Line 3 </label> <input
										type="text" class="form-control" id="addressLine3"
										placeholder="Address Line 3">
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-lg-3">
								<div class="form-group">
									<label class="control-label"> Hospital Reg No</label> <input
										type="text" class="form-control" id="hospitalRegNo"
										placeholder="Hospital Reg No">
								</div>
							</div>

							<div class="col-lg-3">
								<div class="form-group">
									<label class="control-label"> Check Up Date <span
										class="symbol required"></span></label> <input
										class="form-control datepicker" placeholder="Check Up Date"
										id="checkUpDate" type="text" onchange="removeBorder(this)">
								</div>
							</div>

							<div class="col-lg-3">
								<div class="form-group">
									<label class="control-label">Appointment Number <span
										class="symbol required"></span></label> <input type="text"
										class="form-control" id="appNo" onkeyup="numericOnly(this)" onchange="removeBorder(this)"
										placeholder="Appointment Number">
								</div>
							</div>

							<div class="col-md-3">
								<div class="form-group">
									<label class="control-label">Appointment year (15-16) <span
										class="symbol required"></span></label> <input
										placeholder="Appointment year" type="text" id="apYear"
										class="form-control"  onchange="removeBorder(this)">
								</div>
							</div>

						</div>

						<div class="row">

							<div class="col-lg-3">
								<div class="form-group">
									<label class="control-label"> Member Id </label> <input
										type="text" class="form-control" id="memberId"
										placeholder="Member Id">
								</div>
							</div>

							<div class="col-lg-3">
								<div class="form-group">
									<label class="control-label"> Member Relation </label> <input
										type="text" class="form-control" id="mbRelation"
										placeholder="Member Relation">
								</div>
							</div>

							<div class="col-lg-3">
								<div class="form-group">
									<label class="control-label"> Center <span
										class="symbol required"></span></label> <select id="centerList"
										class="form-control" onchange="removeBorder(this)"></select>
								</div>
							</div>

							<div class="col-lg-3">
								<div class="form-group">
									<label class="control-label"> Package <span
										class="symbol required"></span></label> <select id="packageList"
										class="form-control" onchange="removeBorder(this)"></select>
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-lg-3">
								<div class="form-group">
									<label class="control-label"> Link App Number </label> <input
										type="text" class="form-control" id="linkAppNo"
										placeholder="Link App Number">
								</div>
							</div>
						</div>

						<div class="row">
							<div class="col-md-3 col-md-offset-3">
								<input type="submit" name="submit" id="submit" value="Register"
									class="btn btn-info pull-right">
							</div>
						</div>
					</form>
				</div>
				<!-- end: USER PROFILE -->
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

	<script
		src="<c:url value="/indus/resources/custom_js/indus_patient_registration.js"/>"></script>

	<!-- start: JavaScript Event Handlers for this page -->
	<script>
		jQuery(document).ready(function() {
			//For disable future dates
			$('.disableDate').datepicker({
		        format: 'dd-mm-yyyy',
		        endDate: '+0d',
		        autoclose: true
		    });
			
			//Main.init();
			FormElements.init();
			jQuery.noConflict();
		});
	</script>

</body>
</html>
