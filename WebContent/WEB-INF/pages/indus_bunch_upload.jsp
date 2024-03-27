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
				<h4 style="margin-top: 5px;">Bunch Upload</h4>
				<!-- start: USER PROFILE -->
				<div class="container-fluid container-fullw bg-white">

					<div class="row">
						<div class="col-md-12">
							<h4>The excel file must be in following format...</h4>
							<table class="table table-condensed table-hover ">
								<thead>
									<tr>
										<th>Product</th>
										<th>Batch No.</th>
										<th>Expiry</th>
										<th>Quantity</th>
										<th>MRP</th>
										<th>Purchase Rate</th>
										<th>Bill Rate</th>
										<th>Rate</th>
										<th>Company</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>CAPETAZ</td>
										<td>CAP123</td>
										<td>11/2016</td>
										<td>10</td>
										<td>70</td>
										<td>50</td>
										<td>50</td>
										<td>70</td>
										<td>ABC</td>
									</tr>
								</tbody>


							</table>
						</div>
					</div>

					<div class="row" style="margin-top: 2%;">
						<form id="importReportExcelForm" name="importReportExcelForm">

							<div class="col-md-12">

								<div class="col-md-3">
									<input type="file" name="file" id="importReportFile" required>
								</div>
								<div class="col-md-3">
									<input type="submit" value="Start Import"
										class="btn btn-xs btn-info">
								</div>
							</div>
						</form>
					</div>


				</div>
				<!-- end: USER PROFILE -->
			</div>
		</div>
	</div>

	<!-- start: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->

	<script
		src="<c:url value="/indus/resources/custom_js/indus_patient_integration.js"/>"></script>

	<!-- start: JavaScript Event Handlers for this page -->
	<script>
		jQuery(document).ready(function() {

			//Program a custom submit function for the form
			$("#importExcelForm").submit(function(event) {
				//disable the default form submission
				event.preventDefault();
				var fileName = $('#importFile').val();
				if (fileName != "" && fileName != null) {
					//grab all form data  
					var formData = new FormData($(this)[0]);
					$.ajax({
						url : 'readExcel',
						type : 'POST',
						data : formData,
						async : false,
						cache : false,
						contentType : false,
						processData : false,
						success : function(returndata) {
							alert(returndata);
						}
					});
					return false;
				} else {
					alert("Please select file first");
				}
			});

			jQuery.noConflict();
		});
	</script>

</body>
</html>
