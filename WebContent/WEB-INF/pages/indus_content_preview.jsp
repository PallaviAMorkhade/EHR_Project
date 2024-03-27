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

<style>
.thumbnail {
	height: 510px;
}

div.contentDescription {
	height: 100px;
	word-wrap: break-word;
	overflow: hidden;
}

.control-label {
	padding: 8px;
	text-align: right;
	color: #333;
	font-size: 14px;
	font-weight: bold;
}
</style>

<%
	String clientFirstName = (String) session.getAttribute("userFirstName");
%>
<body>
	<div id="app">
		<div class="app-content">
			<div>
				<div class="wrap-content container" id="container">
					<div class="row">
						<div class="col-md-12">
							<div class="row" style="padding: 10px 50px 10px 15px;">

								<div class="col-md-2">
									<label> Search By </label> <select id="searchBy"
										class="form-control" onchange="searchByChange(this)">
										<option value="">ALL</option>
										<option value="published">Published</option>
									</select>
								</div>

								<div class="col-md-2">
									<label> Category </label> <select
										onchange="categoryChange(this)" class="form-control"
										id="categories"></select>
								</div>

								<div class="col-md-2">
									<label> Sub-Category </label> <select
										onchange="subCategoryChange(this)" class="form-control"
										id="subCategories" disabled="disabled">
											<option value="">ALL</option>
										</select>
								</div>

								<div class="col-md-1 pull-right">
									<button class="btn btn-o btn-primary start btn-sm"
										onclick="proceedContent()">
										<i class="glyphicon glyphicon-eye-open"></i> <span>Proceed
										</span>
									</button>
								</div>

								<div class="col-md-1 pull-right">
									<button class="btn btn-o btn-danger start btn-sm"
										onclick="selectAllContent()">
										<i class="glyphicon glyphicon-ok-circle"></i> <span>
											Select All </span>
									</button>
								</div>

								<div class="col-md-1 pull-right" style="margin-right: 17px;">
									<button class="btn btn-o btn-danger start btn-sm"
										onclick="unSelectAllContent()">
										<i class="glyphicon glyphicon-remove-circle"></i> <span>
											UnSelect All </span>
									</button>
								</div>
								<input type="hidden" id="lastContentId"> <input
									type="hidden" id="contentCount" value="0">
							</div>
						</div>

						<div id="scrollContents" class="col-md-12"
							style="overflow: auto; max-height: 465px;">
							<div class="row">
								<div class="col-sm-12 col-md-12">
									<div class="panel panel-transparent">
										<div class="panel-body">
											<div class="row" id="contentPreviewDiv"></div>
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

	<script src="<c:url value="/indus/resources/vendor/moment/moment.js"/>"></script>

	<script
		src="<c:url value="/indus/resources/custom_js/indus_content_article.js"/>"></script>

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
			//Main.init();
			FormElements.init();
			jQuery.noConflict();
		});
	</script>

</body>

<!-- pop for Content View -->
<div class="modal fade" id="contentReadMoreModal" tabindex="-1"
	role="dialog" aria-labelledby="contentReadMoreModal" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">View Content</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<div class="panel panel-transparent">
							<div class="panel-heading">
								<div id="contentTitleModal" class="panel-title"></div>
							</div>
							<div class="panel-body">
								<div id="contentDescriptionModal" class="text-small"></div>
								<div class="row">
									<div class="col-sm-6 col-md-12">
										<div class="thumbnail">
											<img style="width: 100%; height: 420px; display: none;"
												id="contentImage" alt="" src="">
											<!-- resources/assets/images/lion-565818_640.jpg -->

											<video id="contentVideoTag" controls=""
												style="width: 100%; height: 420px; display: none;">
												<source id="contentVideo" type="video/mp4" src="">
												</source>
												Your browser does not support the video tag.
											</video>
											<!-- preview?fileName=Add movie coverposter to a mp4 file.mp4 -->

											<iframe style="display: none;" id="viewContentPdf" src=""
												width="100%" height="420px"></iframe>
											<!-- readPdf?pdfFileName=codpaste-teachingpack.pdf -->

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<!-- <button type="button" class="btn btn-primary btn-o">Publish</button> -->
				<button type="button" class="btn btn-danger btn-o"
					data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<!--End of pop for Content View -->

<!-- pop for Content Publish -->
<div class="modal fade" id="contentPublishModal" tabindex="-1"
	role="dialog" aria-labelledby="contentPublishModal" aria-hidden="true">
	<div class="modal-dialog modal-lg" style="width: 98%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Publish Content</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-3">
						<div class="form-group">
							<label class="col-sm-6 control-label"> Days Interruption </label>
							<div class="col-sm-4">
								<input id="daysInteruption" type="text" class="form-control"
									onkeyup="numericOnly(this)">
							</div>
							<div class="col-sm-2">
								<label class="control-label" style="margin-left: -30px;">DAYS</label>
							</div>
						</div>
					</div>

					<div class="col-md-2">
						<div class="form-group">
							<label class="col-sm-3 control-label"> Repeat </label>
							<div class="col-sm-9">
								<select id="repeatContent" class="form-control center">
									<option value="">NONE</option>
									<option value="daily">DAILY</option>
									<option value="weekly">WEEKLY</option>
									<option value="monthly">MONTHLY</option>
									<option value="quarterly">QUARTERLY</option>
									<option value="halfYearly">HALF YEARLY</option>
									<option value="yearly">YEARLY</option>
								</select>
							</div>
						</div>
					</div>

					<div class="col-md-3">
						<div class="form-group">
							<label class="col-sm-6 control-label"> No. Of Occurences
							</label>
							<div class="col-sm-4">
								<input id="occurences" type="text" class="form-control"
									onkeyup="numericOnly(this)">
							</div>
						</div>
					</div>

					<div class="col-md-3">
						<div class="form-group">
							<label class="col-sm-4 control-label"> Start Date <span
								class="symbol required"></span>
							</label>
							<div class="col-sm-5">
								<input class="form-control datepicker center"
									placeholder="Start Date" id="startDate" type="text">
							</div>
						</div>
					</div>

					<div class="col-md-1">
						<button onclick="submitContent()" type="button"
							class="btn btn-primary btn-o">SUBMIT</button>
					</div>

					<div class="col-md-6">
						<div class="panel panel-white"
							style="overflow: auto; height: 400px;">
							<div class="panel-body">
								<div class="table-responsive">
									<table id="sample-table-1"
										class="table table-bordered table-hover">
										<thead>
											<tr>
												<th class="center">#</th>
												<th>Article Name</th>
												<th style="width: 33%;">Sequence</th>
											</tr>
										</thead>
										<tbody id="contentTableBody">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>

					<div class="col-md-6">
						<div class="panel panel-white"
							style="overflow: auto; height: 400px;">
							<div class="panel-body">
								<div class="table-responsive">
									<table id="sample-table-1"
										class="table table-bordered table-hover">
										<thead>
											<tr>
												<th class="center">#</th>
												<th>Article Name</th>
												<th style="width: 33%;">Date</th>
											</tr>
										</thead>
										<tbody id="contentSchedule">
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
			<div class="modal-footer">
				<button onclick="publishContent()" class="btn btn-primary btn-o">Publish</button>
				<button class="btn btn-danger btn-o"
					onclick="clearContentPublishModal()" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<!--End of pop for Content Publish -->

</html>
