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
							<div class="panel panel-white no-radius" style="min-height: 500px;">
								<div class="panel-heading border-light">
									<h4 class="panel-title">
										<span class="text-bold">Add Question Details</span>
									</h4>
									<ul class="panel-heading-tabs border-light">
										<li>
											<div class="pull-right">
												<div class="btn-group">
													<button onclick="saveQuestionReportDetails()"
														class="btn btn-primary">Save</button>
												</div>
											</div>
										</li>
										<li>
											<div class="pull-right">
												<div class="btn-group">
													<button onclick="listQuestionReportDetail()"
														class="btn btn-primary">View List</button>
												</div>
											</div>
										</li>
										<li class="panel-tools" onclick="clearQuestionReportDetails()"><a
											href="#" class="btn btn-transparent btn-sm panel-refresh"
											data-placement="top" data-toggle="tooltip"
											data-original-title="Refresh"><i class="ti-reload"></i></a></li>
									</ul>
								</div>
								<div class="panel-body">

									<div class="row">
										<div class="col-md-8">
											<div class="form-group">
												<label class="control-label"> Question <span
													class="symbol required"></span>
												</label> <select id="questionList"
													onchange="getQuestionReportByQuestionId(this.value)"
													data-placeholder="Select Question" class="chosen-select"></select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="control-label"> Question Type </label> <Input
													type="text" class="form-control" id="questionType"
													disabled>
											</div>
										</div>
									</div>

									<div id="questionReports"></div>

								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>

	</div>

	<script
		src="<c:url value="/indus/resources/custom_js/indus_hra_report_details.js"/>"></script>

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
			
		});
	</script>

</body>

<!-- pop for Parameter Report Detail List -->
<div class="modal fade" id="viewQuestionReportModal" tabindex="-1"
	role="dialog" aria-labelledby="viewContentModal" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Question Report Details</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12" style="overflow: auto; max-height: 450px;">
						<table
							class="table table-striped table-bordered table-hover table-full-width">
							<thead>
								<tr>
									<th class="center">#</th>
									<th>Question</th>
									<th>Question Type</th>
									<th class="userAccessEditTableHeading">Edit</th>
									<th class="userAccessDeleteTableHeading">Delete</th>
								</tr>
							</thead>
							<tbody id="questionReportTableBody">
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