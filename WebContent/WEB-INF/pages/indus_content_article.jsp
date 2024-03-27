<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Choices includes -->
<link rel="stylesheet"
	href="<c:url value="/indus/resources/assets/css/bootstrap-tagsinput.css"/>">
<!-- End Of Choices includes -->

<!-- Add by Amol Saware For Auto-Suggestion -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/assets/css/bootstrap-chosen.css"/>" />

<!-- CKEditor -->
	<%-- <link rel="stylesheet"
	href="<c:url value="/indus/resources/assets/ckeditor/samples/css/samples.css"/>" />
	<link rel="stylesheet"
	href="<c:url value="/indus/resources/assets/ckeditor/samples/toolbarconfigurator/lib/codemirror/neo.css"/>" /> --%>
<!-- End Of CKEditor -->

<script
	src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>"></script>

<style>
/* #categoryTable td:hover { 
   background-color: #ccc;
} */

.selected {
	background-color: #b9b3f9;
}
</style>
	
<%
	String clientFirstName = (String) session.getAttribute("userFirstName");
%>
<body>
	<div id="app">
		<div class="app-content">
			<div>
				<div class="wrap-content container container-fluid bg-white" id="container">
					<!-- start: DYNAMIC TABLE -->
					<div class="">
					<div class="row" style="margin-bottom: 10px;">
					<div class="col-md-2">
						<h4>Add Content</h4>
						</div>
						<div class="col-md-2 pull-right">
						<button class="btn btn-primary start btn-sm" onclick="viewContentList()">
																	<i class="glyphicon glyphicon-eye-open"></i> <span>View List</span>
																</button>
						</div>
					</div>
						<div class="row">
							<div id="addContentMasterDiv" class="col-md-11 col-xs-11"
								style="margin-bottom: 10px; border: 1px solid; padding: 20px; margin-left: 16px;
								overflow: auto; max-height: 490px;">
								<div class="form-group">
									<label class="control-label"> Content Id <!-- <span
										class="symbol required"></span> -->
									</label> <input value="1" type="text" placeholder=""
										class="form-control" id="contentId" disabled>
								</div>
								<div class="form-group">
									<label class="control-label"> Title <span
										class="symbol required"></span>
									</label> <input type="text" placeholder="Title" class="form-control"
										id="contentTitle" name="contentTitle">
								</div>
								<div class="form-group">
									<label for="contentTags" class="control-label"> Tags <!-- <span
										class="symbol required"></span> -->
									</label> <input class="form-control" id="contentTags" type="text"
										placeholder="Enter something" data-role="tagsinput">
								</div>
								
								<div class="form-group">
									<label class="control-label"> Tags Status </label>
									<table
										class="table table-striped table-bordered table-hover table-full-width">
										<thead>
											<tr>
												<th>Tags</th>
												<th>Status</th>
											</tr>
										</thead>
										<tbody id="tagStatus">
										</tbody>
									</table>
								</div>
								
								<div class="form-group">
									<label class="control-label"> Description <!-- <span
										class="symbol required"></span> -->
									</label>
									<textarea placeholder="Content description"
											class="form-control" id="contentDescription"
											name="contentDescription"></textarea>
								</div>
								
								<div class="form-group" style="overflow: auto; max-height: 200px;">
									<table id="categoryTable"
										class="table table-bordered table-full-width">
										<thead>
											<tr id="categoryHeading">
												<th>Category</th>
											</tr>
										</thead>
										<tbody id="categoryBody">
										</tbody>
									</table>
								</div>
								
								<form id="contentUpload" action="#" method="POST"
									enctype="multipart/form-data">

									<div class="form-group">
										<label class="control-label"> Upload File Type</label>
										
										<div class="row">
										<div class="col-md-6">
      <div class="row">
					
						<div class="col-md-3 col-xs-4">
							<input type="radio" checked="" value="image" name="uploadType"> Image<br>
						</div>
						<div class="col-md-3 col-xs-4">
							<input type="radio" value="video" name="uploadType"> Video<br>
						</div>
            <div class="col-md-3 col-xs-4">
							<input type="radio" value="pdf" name="uploadType"> PDF<br>
						</div>
				</div>
    </div>
   
									<div class="col-md-3">	
										<div class="files" id="files1">
											<span class="btn btn-success fileinput-button"> <i
												class="glyphicon glyphicon-plus"></i> <span>Select
													File</span> <input id="uploadReportFile" type="file"
												name="files1" accept="image/*" />
											</span> <br />
											<ul class="fileList hidden"></ul>
										</div>
										</div>
										
										 </div>

										<div class="row">
											<div class="span16">
												<table class="zebra-striped">
													<tbody class="files"></tbody>
												</table>
											</div>
										</div>
									</div>

									<button type="x" id="uploadContentBtn" class="hidden"></button>

								</form>

								<div class="form-group">
									<label class="control-label"> Upload List </label>
									<table
										class="table table-striped table-bordered table-hover table-full-width">
										<thead>
											<tr>
												<th>File Name</th>
												<th>File Size</th>
												<th>Remove</th>
											</tr>
										</thead>
										<tbody id="uploadContentFiles">
										</tbody>
									</table>
								</div>
								<input id="contentMasterId" class="hidden" />
								<div class="row">
									<div class="col-md-3 col-md-offset-4 col-xs-3 col-xs-offset-5"
										id="saveContentBtnDiv">
										<button id="saveContentBtn"
											class="btn btn-primary btn-wide pull-right" type="button"
											id="saveContentMasterBtn" onclick="uploadBtn()">
											Save <i class="fa fa-arrow-circle-right"></i>
										</button>
									</div>

									<div id="updateContentBtnDiv" style="display: none;">
										<div class="col-md-3 col-md-offset-2 col-xs-3 col-xs-offset-3">
											<button class="btn btn-primary btn-wide pull-right"
												onclick="uploadBtn()" type="button"
												id="updateContentMasterBtn">Update</button>
										</div>
										<div class="col-md-3 col-xs-4" style="margin-left: 30px;">
											<button class="btn btn-primary btn-wide pull-right"
												onclick="clearContentMaster()" type="button"
												id="cancelContentMasterBtn">Cancel</button>
										</div>
									</div>

								</div>

							</div>

							<!-- <div class="col-md-6 col-xs-12" style="overflow: auto; max-height: 490px;">
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
									<tbody id="contentMasterTableBody">
									</tbody>
								</table>
							</div> -->
						</div>
					</div>
					<!-- end: DYNAMIC TABLE -->

				</div>
			</div>
		</div>

	</div>
	
	<script
		src="<c:url value="/indus/resources/custom_js/indus_content_article.js"/>"></script>

	<!-- Choices includes -->
	<script
		src="<c:url value="/indus/resources/vendor/bootstrap/js/bootstrap.min.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/assets/js/bootstrap-tagsinput.js"/>"></script>
	<!-- End Choices includes -->
	
	<!-- CKEditor -->
	<script src="<c:url value="/indus/resources/assets/ckeditor/ckeditor.js"/>"></script>
	<script src="<c:url value="/indus/resources/assets/ckeditor/samples/js/sample.js"/>"></script>
	<!-- End Of CKEditor -->
	
	<!-- Add by Amol Saware For Auto-Suggestion -->
	<script	src="<c:url value="/indus/resources/assets/js/chosen.jquery.js"/>"></script>

	<script>
		jQuery(document)
				.ready(
						function() {
							//jQuery.noConflict();
							//CkEditor
							CKEDITOR.replace('contentDescription');

							$.fn.fileUploader = function(filesToUpload,
									sectionIdentifier) {
								var fileIdCounter = 0;
								var reportIndex = fileIdCounter + 1;
								this
										.closest(".files")
										.change(
												function(evt) {
													var output = [];

													//$('#reportLength').html($('.template-upload').length+1+$('#displayReportTableBody tr').length);
													for (var i = 0; i < evt.target.files.length; i++) {
														fileIdCounter++;
														var file = evt.target.files[i];
														var fileExtension = file.type;
														var fileType = $("input[name='uploadType']:checked").val();
														if(fileExtension.indexOf(fileType) > -1){
															
															//for single content upload
															$("#uploadContentFiles").empty();
															for (var j = 0; j < filesToUpload.length; ++j) {
																if (filesToUpload[j].id
																		.indexOf(sectionIdentifier) >= 0)
																	filesToUpload.splice(j, 1);
															}
															//End of single content upload
															
														var fileId = sectionIdentifier
																+ fileIdCounter;
														
														filesToUpload.push({
															id : fileId,
															file : file
														});

														var removeLink1 = "<a class=\"removeContentFile btn btn-danger cancel btn-xs removeSingleContent\" href=\"#\" data-fileid=\"" + fileId + "\">Remove</a>";
														var removeLink = "<a class=\"removeContentFile btn btn-danger cancel btn-xs removeContent_"+fileId+" \" href=\"#\" data-fileid=\"" + fileId + "\">Remove</a>";
														output
																.push(
																		"<li class='content_"+fileId+"'><strong>",
																		escape(file.name),
																		"</strong> - ",
																		file.size,
																		" bytes. &nbsp; &nbsp; ",
																		removeLink,
																		"</li> ");
														
														var content = '<tr class="content_'+fileId+'">'
																+ '<td><p class="contentName contentFileName_'+reportIndex+'">'
																+ file.name
																+ '</p></td>'
																+ '<td><p class="size"></p>'
																+ file.size
																+ ' bytes. &nbsp; &nbsp; </td>'
																+ '<td>'
																+ removeLink1
																+ '</td></tr>';
														$("#uploadContentFiles")
																.append(content);
														reportIndex++;
														
														}else{
															alert("Please Select Proper format");
														}

													}
													;

													$(this)
															.children(
																	".fileList").append(output.join(""));

													//reset the input to null - nice little chrome bug!
													evt.target.value = null;
												});

								$(document)
										.on(
												"click",
												".removeContentFile",
												function(e) {
													e.preventDefault();

													var fileId = $(this)
															.parent().children(
																	"a").data(
																	"fileid");
													// loop through the files array and check if the name of that file matches FileName
													// and get the index of the match
													for (var i = 0; i < filesToUpload.length; ++i) {
														if (filesToUpload[i].id === fileId)
															filesToUpload
																	.splice(i,
																			1);
													}
													$(".content_" + fileId)
															.remove();
													//$(this).parent().remove();
												});

								this.clear = function() {
									for (var i = 0; i < filesToUpload.length; ++i) {
										if (filesToUpload[i].id
												.indexOf(sectionIdentifier) >= 0)
											filesToUpload.splice(i, 1);
									}

									$(this).children(".fileList").empty();
									$("#uploadContentFiles").empty();
								}

								return this;
							};

							(function() {
								var filesToUpload = [];

								var files1Uploader = $("#files1").fileUploader(
										filesToUpload, "files1");

								$("#uploadContentBtn")
										.click(
												function(e) {
													e.preventDefault();
													var contentTitle = $(
															"#contentTitle")
															.val();
													if (contentTitle == null
															|| contentTitle == "") {
														alert("Please Enter Content Title");
													} else {
														$('#pleaseWait').show();
														var formData = new FormData();
														for (var i = 0, len = filesToUpload.length; i < len; i++) {
															formData
																	.append(
																			"files",
																			filesToUpload[i].file);
														}
														formData
																.append(
																		'callFrom',
																		'content');
														$
																.ajax({
																	url : "../UploadFile",
																	data : formData,
																	processData : false,
																	contentType : false,
																	type : "POST",
																	success : function(
																			data) {
																		saveContent();
																		$(
																				'#pleaseWait')
																				.hide();
																		files1Uploader
																				.clear();
																	},
																	error : function(
																			data) {
																		$(
																				'#pleaseWait')
																				.hide();
																		alert("ERROR - "
																				+ data.responseText);
																	}
																});
													}
												});
							})()

						});
	</script>

</body>
</html>










