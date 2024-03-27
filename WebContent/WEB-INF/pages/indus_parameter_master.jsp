<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
						<h4>Parameter management</h4>
						<div id="selectTestDiv" class="row">
							<div class="col-md-2">
								<label>Select Test:</label>
							</div>
							<div class="col-md-3">
								<select class="form-control" id="testMasterSelect" name="testMasterSelect">
									
								</select>
							</div>
						</div>
						<div class="row" style="margin-top:10px;">
							<div id="displayParameterDiv" class="col-md-5 col-xs-11" style="margin-bottom:10px;border: 1px solid; padding: 20px; margin-left: 16px;max-height:450px;min-height:450px;overflow-y:scroll;">
								<div id="parameterDiv" style="display:none;">
								<div class="form-group">
								<div class="defaultParameterDiv">
									<label class="control-label"> Parameter <span
										class="symbol required"></span>
									</label> <input type="text" placeholder="Parameter"
										class="form-control" id="parameter_0" name="parameter"> <i id="addParameterBtn" class="fa fa-plus-square"></i>

								<div class="row">
									<div class="col-md-3 col-xs-4">
										<label>Normal Values</label>
									</div>
									<form id="valuesForm_0" class="valuesForm">
										<div class="col-md-3 col-xs-4">
											<input type="radio" class="normalValue_1" name="values_0"
												value="individual" checked=""> Individual<br>
										</div>
										<div class="col-md-3 col-xs-4">
											<input type="radio" class="normalValue_2" name="values_0"
												value="general"> General<br>
										</div>
									</form>
								</div>

								<div class="row" id="criticalValueDiv_0">
									<div class="col-md-6">
										<label class="control-label"> Critical Low Value <span
											class="symbol required"></span>
										</label> <input type="text" id="criticalLowValue_0" onkeyup="numericOnly(this),lowHighValidation('low',this)"
											class="form-control" placeholder="Critical Low Value">
									</div>

									<div class="col-md-6">
										<label class="control-label"> Critical High Value <span
											class="symbol required"></span>
										</label> <input type="text" id="criticalHighValue_0" onkeyup="numericOnly(this),lowHighValidation('high',this)"
											class="form-control" placeholder="Critical High Value">
									</div>
								</div>
								<hr>
								</div>
								
								<div id="newParameters"></div>
								
								</div>

						<div class="row">
							<div class="col-md-3 col-md-offset-5" id="saveParameterBtnDiv">
								<button class="btn btn-primary btn-wide pull-right" onclick="saveParameterMaster()"
									type="button" id="saveParameterMasterBtn">
									Save <i class="fa fa-arrow-circle-right"></i>
								</button>
							</div>

							<div id="updateParameterBtnDiv" style="display: none;">
								<div class="col-md-3 col-md-offset-3 col-xs-3 col-xs-offset-3">
									<button class="btn btn-primary btn-wide pull-right" onclick="updateParameterMaster()"
										type="button" id="updateParameterMasterBtn">Update</button>
								</div>
								<div class="col-md-3 col-xs-4" style="margin-left: 30px;">
									<button class="btn btn-primary btn-wide pull-right"
										type="button" id="cancelParameterMasterBtn">Cancel</button>
								</div>
							</div>
						</div>
						</div>
					</div>
							<div class="col-md-6 col-xs-12" style="max-height: 450px; overflow-y: scroll;">
								<table
									class="table table-striped table-bordered table-hover table-full-width"
									id="sample_1">
									<thead>
										<tr>
											<th>#</th>
											<th>Parameter</th>
											<th>Test</th>
											<th class="userAccessEditTableHeading">Edit</th>
											<th class="userAccessDeleteTableHeading">Delete</th>
										</tr>
									</thead>
									<tbody id="parameterMasterTableBody">

									</tbody>
								</table>
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
							/*listOfTestMaster();
							listOfParameterMaster();
							 Main.init();
							FormElements.init();*/
						});

	</script>
	
