<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- <style>
#centreMasterSelect option{
 width:70%;   
}
</style> -->

<!-- end: HEAD -->
<%
	String clientFirstName = (String) session
			.getAttribute("userFirstName");
%>
	<div id="app">
		<!-- sidebar -->
		<!-- / sidebar -->
		<div class="app-content">
			<!-- start: TOP NAVBAR -->
			<!-- end: TOP NAVBAR -->
				<div class="wrap-content container" id="container">
					<div class="container-fluid container-fullw bg-white">
						<h4>Centre wise parameter values</h4>
						<div class="row" style="margin-top:10px;">
							<div class="col-md-6 col-xs-12" style="margin-bottom:10px;max-height: 450px; overflow-y: scroll;">
								<table
									class="table table-striped table-bordered table-hover table-full-width"
									id="sample_1">
									<thead>
										<tr>
											<th>#</th>
											<th>Test Name</th>
											<th class="userAccessEditTableHeading">Edit</th>
										</tr>
									</thead>
									<tbody id="testMasterTableBody">

									</tbody>
								</table>
							</div>
							
					<div id="displayCenterParameterDiv" class="col-md-5 col-xs-11" style="border: 1px solid; padding: 20px; margin-left: 16px;max-height:450px;min-height:450px;overflow-y:scroll;">
							<div id="centreParametreDiv" style="display:none;">
							<div class="row">
								<div class="col-md-4">
									<label>Select Centre:</label>
								</div>
								<div class="col-md-8">
									<select class="form-control" id="centreMasterSelect"
										name="centreMasterSelect">

									</select>
								</div>
							</div>
							
							<div class="row" style="margin-top: 15px;">
								<div class="col-md-4 col-xs-4">
									<label>Test Name:</label>
								</div>
								<div class="col-md-8 col-xs-6">
									<span id="testNameSpan"></span>
									<span id="testIdSpan" class="hidden"></span>
									<span id="parameterIdSpan" class="hidden"></span>
								</div>
							</div>
							
					<div id="displayTestParameterDiv" style="display:none;">
							<div class="row" style="margin-top:15px;"><!-- max-height: 300px; overflow-y: scroll; -->
								<table
									class="table table-striped table-bordered table-hover table-full-width"
									id="sample_1">
									<thead>
										<tr>
											<th>#</th>
											<th>Parameter Name</th>
											<th>Edit</th>
										</tr>
									</thead>
									<tbody id="parameterMasterValuesTableBody">

									</tbody>
								</table>
							</div>
					</div>
					
						<!-- <div class="row">
							<div class="col-md-3 col-md-offset-5" id="saveParameterBtnDiv">
								<button class="btn btn-primary btn-wide pull-right"
									type="button" id="saveParameterMasterBtn">
									Save <i class="fa fa-arrow-circle-right"></i>
								</button>
							</div>

							<div id="updateParameterBtnDiv" style="display: none;">
								<div class="col-md-3 col-md-offset-3">
									<button class="btn btn-primary btn-wide pull-right"
										type="button" id="updateParameterMasterBtn">Update</button>
								</div>
								<div class="col-md-3" style="margin-left: 30px;">
									<button class="btn btn-primary btn-wide pull-right"
										type="button" id="cancelParameterMasterBtn">Cancel</button>
								</div>
							</div>
						</div> -->
					</div>
							</div>
						</div>				
					</div>
					<!-- end: DYNAMIC TABLE -->
			</div>
		</div>
	</div>
	
	<script src="<c:url value="/indus/resources/custom_js/indus_parameter_master.js"/>"></script>
		
	<script>
		jQuery(document)
				.ready(
						function() {
							/* getListOfCentres();
							listOfTestMasterTableBody();
							listOfUnitMasterCentreWise(); */
							/* listOfTestMaster();
							listOfParameterMaster();
							Main.init();
							FormElements.init();*/
						});

	</script>
	
