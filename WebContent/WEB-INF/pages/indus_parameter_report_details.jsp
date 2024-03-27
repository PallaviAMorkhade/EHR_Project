<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Add by Amol Saware For Auto-Suggestion -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/assets/css/bootstrap-chosen.css"/>" />

<script
	src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>"></script>

<%
	String clientFirstName = (String) session.getAttribute("userFirstName");
%>
<body>
	<div id="app">
		<div class="app-content">
			<div>
				<div class="wrap-content container container-fluid bg-white"
					id="container">

					<div class="row" style="margin-top: 10px;">
						<div class="col-sm-12">
							<div class="panel panel-white no-radius">
								<div class="panel-heading border-light">
									<h4 class="panel-title">
										<span class="text-bold">Add Parameter Details</span>
									</h4>
									<ul class="panel-heading-tabs border-light">
										<li>
											<div class="pull-right">
												<div class="btn-group">
													<button onclick="saveParameterReportDetails()"
														class="btn btn-primary">Save</button>
												</div>
											</div>
										</li>
										<li>
											<div class="pull-right">
												<div class="btn-group">
													<button onclick="listParameterReportDetail()"
														class="btn btn-primary">View List</button>
												</div>
											</div>
										</li>
										<li class="panel-tools"
											onclick="clearParameterReportDetails()"><a href="#"
											class="btn btn-transparent btn-sm panel-refresh"
											data-placement="top" data-toggle="tooltip"
											data-original-title="Refresh"><i class="ti-reload"></i></a></li>
									</ul>
								</div>
								<div class="panel-body">

									<div class="row">
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label"> Parameter <span
													class="symbol required"></span>
												</label> <select id="parameterList"
													onchange="getParameterReportByParameterId(this.value)"
													data-placeholder="Select Parameter" class="chosen-select"></select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label"> Parameter Type </label> <Input
													type="text" class="form-control" id="parameterType"
													disabled>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label"> Test Name </label> <Input
													type="text" class="form-control" id="testName" disabled>
											</div>
										</div>
									</div>

									<div class="panel panel-white no-radius">
										<div class="panel-heading border-light">
											<h4 class="panel-title">
												<span class="text-bold">Normal Details</span>
											</h4>
											<div class="panel-tools">
												<a href="#"
													class="btn btn-transparent btn-sm panel-collapse"
													data-placement="top" data-toggle="tooltip"
													data-original-title="Collapse"><i
													class="ti-minus collapse-off"></i><i
													class="ti-plus collapse-on"></i></a>
											</div>
										</div>
										<div class="panel-body">

											<div class="row">
												<div class="col-md-2">
													<div class="form-group">
														<label class="control-label"> Helath Score </label> <Input
															onkeyup="numericOnly(this)" type="text"
															class="form-control" id="healthScore_1">
													</div>
												</div>
												<div class="col-md-5">
													<div class="form-group">
														<label class="control-label"> Lab Test / Check-ups
														</label> <select id="test_1" data-placeholder="Search Parameter"
															class="chosen-select testList"></select>
													</div>
												</div>
												<div class="col-md-5">
													<div class="row">
														<div class="col-md-3">
															<div class="form-group">
																<label class="control-label"> Frequency </label> <Input
																	type="text" class="form-control" id="frequencyNumber_1">
															</div>
														</div>
														<div class="col-md-9">
															<div class="form-group">
																<label class="control-label"></label> <select
																	id="frequency_1" style="margin-top: 5px;"
																	class="form-control frequencyList">
																</select>
															</div>
														</div>
													</div>
												</div>
											</div>

											<div class="form-group">
												<label class="control-label"> Report Statement </label>
												<textarea placeholder="Report Statement"
													class="form-control" id="reportStatement_1"></textarea>
											</div>

											<div class="form-group">
												<label class="control-label"> Reason </label>
												<textarea placeholder="Reason" class="form-control"
													id="reason_1"></textarea>
											</div>

										</div>
									</div>

									<div class="panel panel-white no-radius">
										<div class="panel-heading border-light">
											<h4 class="panel-title">
												<span class="text-bold">Abnormal Details</span>
											</h4>
											<div class="panel-tools">
												<a href="#"
													class="btn btn-transparent btn-sm panel-collapse"
													data-placement="top" data-toggle="tooltip"
													data-original-title="Collapse"><i
													class="ti-minus collapse-off"></i><i
													class="ti-plus collapse-on"></i></a>
											</div>
										</div>
										<div class="panel-body">

											<div class="row">
												<div class="col-md-2">
													<div class="form-group">
														<label class="control-label"> Helath Score </label> <Input
															onkeyup="numericOnly(this)" type="text"
															class="form-control" id="healthScore_2">
													</div>
												</div>
												<div class="col-md-5">
													<div class="form-group">
														<label class="control-label"> Lab Test / Check-ups
														</label> <select id="test_2" data-placeholder="Search Parameter"
															class="chosen-select testList"></select>
													</div>
												</div>
												<div class="col-md-5">
													<div class="row">
														<div class="col-md-3">
															<div class="form-group">
																<label class="control-label"> Frequency </label> <Input
																	type="text" class="form-control" id="frequencyNumber_2">
															</div>
														</div>
														<div class="col-md-9">
															<div class="form-group">
																<label class="control-label"></label> <select
																	id="frequency_2" style="margin-top: 5px;"
																	class="form-control frequencyList">
																</select>
															</div>
														</div>
													</div>
												</div>
											</div>

											<div class="form-group">
												<label class="control-label"> Report Statement </label>
												<textarea placeholder="Report Statement"
													class="form-control" id="reportStatement_2"></textarea>
											</div>

											<div class="form-group">
												<label class="control-label"> Reason </label>
												<textarea placeholder="Reason" class="form-control"
													id="reason_2"></textarea>
											</div>

										</div>
									</div>

									<div class="panel panel-white no-radius" id="urgentDetailPanel">
										<div class="panel-heading border-light">
											<h4 class="panel-title">
												<span class="text-bold">Urgent Details</span>
											</h4>
											<div class="panel-tools">
												<a href="#"
													class="btn btn-transparent btn-sm panel-collapse"
													data-placement="top" data-toggle="tooltip"
													data-original-title="Collapse"><i
													class="ti-minus collapse-off"></i><i
													class="ti-plus collapse-on"></i></a>
											</div>
										</div>
										<div class="panel-body">

											<div class="row">
												<div class="col-md-2">
													<div class="form-group">
														<label class="control-label"> Helath Score </label> <Input
															onkeyup="numericOnly(this)" type="text"
															class="form-control" id="healthScore_3">
													</div>
												</div>
												<div class="col-md-5">
													<div class="form-group">
														<label class="control-label"> Lab Test / Check-ups
														</label> <select id="test_3" data-placeholder="Search Parameter"
															class="chosen-select testList"></select>
													</div>
												</div>
												<div class="col-md-5">
													<div class="row">
														<div class="col-md-3">
															<div class="form-group">
																<label class="control-label"> Frequency </label> <Input
																	type="text" class="form-control" id="frequencyNumber_3">
															</div>
														</div>
														<div class="col-md-9">
															<div class="form-group">
																<label class="control-label"></label> <select
																	id="frequency_3" style="margin-top: 5px;"
																	class="form-control frequencyList">
																</select>
															</div>
														</div>
													</div>
												</div>
											</div>

											<div class="form-group">
												<label class="control-label"> Report Statement </label>
												<textarea placeholder="Report Statement"
													class="form-control" id="reportStatement_3"></textarea>
											</div>

											<div class="form-group">
												<label class="control-label"> Reason </label>
												<textarea placeholder="Reason" class="form-control"
													id="reason_3"></textarea>
											</div>

										</div>
									</div>

									<div class="panel panel-white no-radius"
										id="borderlineDetailPanel">
										<div class="panel-heading border-light">
											<h4 class="panel-title">
												<span class="text-bold">Borderline Details</span>
											</h4>
											<div class="panel-tools">
												<a href="#"
													class="btn btn-transparent btn-sm panel-collapse"
													data-placement="top" data-toggle="tooltip"
													data-original-title="Collapse"><i
													class="ti-minus collapse-off"></i><i
													class="ti-plus collapse-on"></i></a>
											</div>
										</div>
										<div class="panel-body">

											<div class="row">
												<div class="col-md-2">
													<div class="form-group">
														<label class="control-label"> Helath Score </label> <Input
															onkeyup="numericOnly(this)" type="text"
															class="form-control" id="healthScore_4">
													</div>
												</div>
												<div class="col-md-5">
													<div class="form-group">
														<label class="control-label"> Lab Test / Check-ups
														</label> <select id="test_4" data-placeholder="Search Parameter"
															class="chosen-select testList"></select>
													</div>
												</div>
												<div class="col-md-5">
													<div class="row">
														<div class="col-md-3">
															<div class="form-group">
																<label class="control-label"> Frequency </label> <Input
																	type="text" class="form-control" id="frequencyNumber_4">
															</div>
														</div>
														<div class="col-md-9">
															<div class="form-group">
																<label class="control-label"></label> <select
																	id="frequency_4" style="margin-top: 5px;"
																	class="form-control frequencyList">
																</select>
															</div>
														</div>
													</div>
												</div>
											</div>

											<div class="form-group">
												<label class="control-label"> Report Statement </label>
												<textarea placeholder="Report Statement"
													class="form-control" id="reportStatement_4"></textarea>
											</div>

											<div class="form-group">
												<label class="control-label"> Reason </label>
												<textarea placeholder="Reason" class="form-control"
													id="reason_4"></textarea>
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

	</div>

	<script
		src="<c:url value="/indus/resources/custom_js/indus_parameter_report_details.js"/>"></script>

	<!-- CKEditor -->
	<script
		src="<c:url value="/indus/resources/assets/ckeditor/ckeditor.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/assets/ckeditor/samples/js/sample.js"/>"></script>
	<!-- End Of CKEditor -->

	<!-- Add by Amol Saware For Auto-Suggestion -->
	<script
		src="<c:url value="/indus/resources/assets/js/chosen.jquery.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/bootstrap/js/bootstrap.min.js"/>"></script>

	<script>
		jQuery(document).ready(function() {
			//CkEditor
			CKEDITOR.replace('reportStatement_1');
			CKEDITOR.replace('reportStatement_2');
			CKEDITOR.replace('reportStatement_3');
			CKEDITOR.replace('reportStatement_4');
		});
	</script>

</body>

<!-- pop for Parameter Report Detail List -->
<div class="modal fade" id="viewParameterReportModal" tabindex="-1"
	role="dialog" aria-labelledby="viewContentModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Parameter Report Details</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12" style="overflow: auto; max-height: 450px;">
						<table
							class="table table-striped table-bordered table-hover table-full-width">
							<thead>
								<tr>
									<th class="center">#</th>
									<th>Title</th>
									<th class="userAccessEditTableHeading">Edit</th>
									<th class="userAccessDeleteTableHeading">Delete</th>
								</tr>
							</thead>
							<tbody id="parameterReportTableBody">
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-o"
					data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<!--End of pop for Parameter Report Detail List -->

</html>










