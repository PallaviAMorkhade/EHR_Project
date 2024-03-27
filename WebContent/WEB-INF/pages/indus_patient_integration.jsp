<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script
	src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>"></script>

<body>
	<div id="app">
		<div class="app-content">
			<!-- <div class="main-content"> -->
			<div class="wrap-content container" id="container">
				<h4 style="margin-top: 5px;">Client Integration</h4>
				<!-- start: USER PROFILE -->
				<div class="container-fluid container-fullw bg-white">
						<div class="row">

							<div class="col-lg-6">
								<div class="form-group">
									<label class="control-label"> Original Client Id <span
										class="symbol required"></span></label> <input type="text" onkeyup="numericOnly(this)""
										class="form-control" id="originalClientId" placeholder="Original Client Id">
								</div>
							</div>
							
							<div class="col-lg-6">
								<div class="form-group">
									<label class="control-label"> Replaceable Client Id <span
										class="symbol required"></span></label> <input type="text" onkeyup="numericOnly(this)"
										class="form-control" id="replaceableClientId" placeholder="Replaceable Client Id">
								</div>
							</div>

						</div>
						
						<div class="row">

							<div class="col-lg-6">
								<div class="form-group">
									<label class="control-label"> Original Client Name <span
										class="symbol required"></span></label> <input type="text"
										class="form-control" id="originalClientName" placeholder="Original Client Name">
								</div>
							</div>
							
							<div class="col-lg-6">
								<!-- <div class="form-group">
									<label class="control-label"> Replaceable Client Name <span
										class="symbol required"></span></label> <input type="text"
										class="form-control" id="replaceableClientName" placeholder="Replaceable Client Name">
								</div> -->
								<label class="control-label"> Replaceable Client Name </label>
							<table
								class="table table-striped table-bordered table-hover table-full-width">
								<thead>
									<tr>
										<!-- <th class="center">#</th> -->
										<th>Client Id</th>
										<th>Client Name</th>
										<th>Remove</th>
									</tr>
								</thead>
								<tbody id="replaceableClientData">
								</tbody>
							</table>
						</div>

						</div>

						<div class="row">
							<div class="col-md-3 col-md-offset-3">
								<input type="button" value="Merge" onclick="mergeClient()"
									class="btn btn-info pull-right">
							</div>
						</div>
				</div>
				<!-- end: USER PROFILE -->
			</div>
		</div>
	</div>

	<!-- start: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->

	<script src="<c:url value="/indus/resources/assets/js/main.js"/>"></script>
	<script src="<c:url value="/indus/resources/assets/js/form-elements.js"/>"></script>

	<script
		src="<c:url value="/indus/resources/custom_js/indus_patient_integration.js"/>"></script>

	<!-- start: JavaScript Event Handlers for this page -->
	<script>
		jQuery(document).ready(function() {
			//Main.init();
			//FormElements.init();
			jQuery.noConflict();
		});
	</script>

</body>
</html>
