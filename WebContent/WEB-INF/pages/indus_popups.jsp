<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- <script src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>"></script> --%>
<%-- <script
	src="<c:url value="/indus/resources/vendor/jquery-file-upload/vendor/jquery.ui.widget.js"/>"></script>
<!-- The Templates plugin is included to render the upload/download listings -->
<!-- <script src="http://blueimp.github.io/JavaScript-Templates/js/tmpl.min.js"></script> -->
<script
	src="<c:url value="/indus/resources/vendor/jquery-file-upload/tmpl.min.js"/>"></script>
<!-- The Load Image plugin is included for the preview images and image resizing functionality -->
<script
	src="<c:url value="/indus/resources/vendor/javascript-Load-Image/load-image.all.min.js"/>"></script>
<!-- The Canvas to Blob plugin is included for image resizing functionality -->
<!-- <script src="http://blueimp.github.io/JavaScript-Canvas-to-Blob/js/canvas-to-blob.min.js"></script> -->
<script
	src="<c:url value="/indus/resources/vendor/jquery-file-upload/canvas-to-blob.min.js"/>"></script>
<!-- blueimp Gallery script -->
<!-- <script src="http://blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script> -->
<script
	src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.blueimp-gallery.min.js"/>"></script>
<!-- The Iframe Transport is required for browsers without support for XHR file uploads -->
<script
	src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.iframe-transport.js"/>"></script>
<!-- The basic File Upload plugin -->
<script
	src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload.js"/>"></script>
<!-- The File Upload processing plugin -->
<script
	src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload-process.js"/>"></script>
<!-- The File Upload image preview & resize plugin -->
<script
	src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload-image.js"/>"></script>
<!-- The File Upload audio preview plugin -->
<script
	src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload-audio.js"/>"></script>
<!-- The File Upload video preview plugin -->
<script
	src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload-video.js"/>"></script>
<!-- The File Upload validation plugin -->
<script
	src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload-validate.js"/>"></script>
<!-- The File Upload user interface plugin -->
<script
	src="<c:url value="/indus/resources/vendor/jquery-file-upload/jquery.fileupload-ui.js"/>"></script>

<!-- The main application script -->
<script
	src="<c:url value="/indus/resources/vendor/jquery-file-upload/main.js"/>"></script> --%>
	
<script>
jQuery(document).ready(function() {
	
	$.fn.fileUploader = function (filesToUpload, sectionIdentifier) {
	    var fileIdCounter = 0;
	    var reportIndex = fileIdCounter + 1;
	    this.closest(".files").change(function (evt) {
	        var output = [];
	        
	        $('#reportLength').html($('.template-upload').length+1+$('#displayReportTableBody tr').length);
	        for (var i = 0; i < evt.target.files.length; i++) {
	            fileIdCounter++;
	            var file = evt.target.files[i];
	            
	          	//for single report upload
	        	if(sectionIdentifier=="files2"){
	        		for (var j = 0; j < filesToUpload.length; ++j) {
						if (filesToUpload[j].id
								.indexOf(sectionIdentifier) >= 0)
							filesToUpload.splice(j, 1);
					}
	        	}
	        	//End of single report upload

	        	var fileId = sectionIdentifier + fileIdCounter;
	            filesToUpload.push({
	                id: fileId,
	                file: file
	            });

	            var removeLink1 = "<a class=\"removeFile btn btn-danger cancel btn-xs removeSingleReport\" href=\"#\" data-fileid=\"" + fileId + "\">Remove</a>";
	            var removeLink = "<a class=\"removeFile btn btn-danger cancel btn-xs removeReport_"+fileId+" \" href=\"#\" data-fileid=\"" + fileId + "\">Remove</a>";
	            output.push("<li><strong>", escape(file.name), "</strong> - ", file.size, " bytes. &nbsp; &nbsp; ", removeLink, "</li> ");
	            
	            
						var report = '<tr class="template-upload fade in">';
						
						if ($('#currentPageId').html()!=7) {

							if($("#visitSelect option:first").val()!=$('#visitSelect').val()){ 
								report = report + '<td><Select onchange="testPackageList(this)" class="testPackageSelectList testPackageSelect_'+reportIndex+'" style="width: 100px; margin-right: 15px;"><option val="package">Package</option><option val="test">Test</option></select></td>';
							} else {
								report = report + '<td><input class="hidden testPackageSelect_'+reportIndex+'" style="width: 100px; margin-right: 15px;" value="firstVisit"></td>';
							} 
							report = report + '<td><Select class="assignTestPackage testSelectList testSelect_'+reportIndex+'" style="width: 100px; margin-right: 15px;"></select></td>'
									+' <td><Select class="centerSelectList centreSelect_'+reportIndex+'" style="width: 100px; margin-right: 15px;"></select></td>'
									+' <td><input style="width:67%;" class="form-control datepicker reportDate reportDate_'+reportIndex+'" type="text"></td>';
										
							 } else { 		
								report = report + '<td><input placeholder="Put your comment here" class="comment comment_'+reportIndex+'" type="text"></td>';
							 }
						
						report = report
								+ '<td><p class="name fileName_'+reportIndex+'">'+file.name+'</p></td>'
								+ '<td><p class="size"></p>'+file.size+' bytes. &nbsp; &nbsp; </td>'
								+ '<td>'+removeLink1+'</td></tr>';
						$("#reportDiv").append(report);
						reportIndex++;
	            
	        };
	        
	        if($("#visitSelect option:first").val()==$('#visitSelect').val()){
				 getPatientTest(($('#visitSelect').val()).split("_")[1]);
				}
				else{
				 listOfPackageMasterReport(0);
				}
			getListOfCentres();
			getPatientCenter($('#clientMasterId').html());
		
			//For disable future dates
			$('.reportDate').datepicker({
		        format: 'dd-mm-yyyy',
		        endDate: '+0d',
		        autoclose: true
		    });

	        $(this).children(".fileList")
	            .append(output.join(""));

	        //reset the input to null - nice little chrome bug!
	        evt.target.value = null;
	    });

	    $(this).on("click", ".removeFile", function (e) {
	        e.preventDefault();
	        var fileId = $(this).parent().children("a").data("fileid");

	        // loop through the files array and check if the name of that file matches FileName
	        // and get the index of the match
	        for (var i = 0; i < filesToUpload.length; ++i) {
	            if (filesToUpload[i].id === fileId)
	                filesToUpload.splice(i, 1);
	        }

	        $(this).parent().remove();
	    });

	    this.clear = function () {
	        for (var i = 0; i < filesToUpload.length; ++i) {
	            if (filesToUpload[i].id.indexOf(sectionIdentifier) >= 0)
	                filesToUpload.splice(i, 1);
	        }

	        $(this).children(".fileList").empty();
	    }

	    return this;
	};
	
	(function () {
	    var filesToUpload = [];

	    var files1Uploader = $("#files1").fileUploader(filesToUpload, "files1");
	    
	    var files2Uploader = $("#files2").fileUploader(filesToUpload, "files2");

	    $("#uploadBtn").click(function (e) {
	    	$('#pleaseWait').show();
	        e.preventDefault();

	        var formData = new FormData();

	        for (var i = 0, len = filesToUpload.length; i < len; i++) {
	            formData.append("files", filesToUpload[i].file);
	        }
	        
	        $.ajax({
	            url: "../UploadFile",
	            data: formData,
	            processData: false,
	            contentType: false,
	            type: "POST",
	            success: function (data) {
	            	if ($('#currentPageId').html()!=7) { 
	            		savePatientReport();
	            	}else{
	            		saveSelfClientReport();
	            	}
	            	$('#pleaseWait').hide();
	                files1Uploader.clear();
	                files2Uploader.clear();
	            },
	            error: function (data) {
	            	$('#pleaseWait').hide();
	                alert("ERROR - " + data.responseText);
	            }
	        });
	    });
	})()

	});
	</script>

<%-- <link rel="stylesheet" media="screen" href="<c:url value="/indus/resources/vendor/jstree/themes/default/style.min.css"/>"> --%>

<!-- for new jstree -->
<%-- <link rel="stylesheet" href="<c:url value="/indus/resources/assets/css/jquery.treeview.css"/>"> --%>
<!-- End of jstree -->

<!-- <td>
			{% if (!o.files.error) { %}
			<div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
			{% } %}
			</td>
			<td></td> -->

<!-- <td><span class="checkUpDate checkUpDate_{%=dateCount()%}"></span></td> -->

<!-- The template to display files available for upload -->
<script id="template-upload" type="text/x-tmpl">

			{% for (var i=0, file; file=o.files[i]; i++) { $('#reportLength').html($('.template-upload').length+1+$('#displayReportTableBody tr').length);
				
if($("#visitSelect option:first").val()==$('#visitSelect').val()){
 getPatientTest($('#clientMasterId').html());
}
else{
 listOfPackageMasterReport(0);
}

				getListOfCentres();getPatientCenter($('#clientMasterId').html()); %}
			<tr class="template-upload fade">

{% if ($('#currentPageId').html()!=7) { %}

{%if($("#visitSelect option:first").val()!=$('#visitSelect').val()){ %}
 <td><Select onchange="testPackageList(this)" class="testPackageSelectList testPackageSelect_{%=dateCount()%}" style="width: 100px; margin-right: 15px;"><option val="package">Package</option><option val="test">Test</option></select></td>
{% } else { %}
<td><input class="hidden testPackageSelect_{%=dateCount()%}" style="width: 100px; margin-right: 15px;" value="firstVisit"></td>
{% } %}	
			<td><Select class="assignTestPackage testSelectList testSelect_{%=count()%}" style="width: 100px; margin-right: 15px;"></select></td>
			<td><Select class="centerSelectList centreSelect_{%=count()%}" style="width: 100px; margin-right: 15px;"></select></td>			
			<td><input style="width:67%;" class="form-control datepicker reportDate reportDate_{%=count()%}" type="text"></td>
			
	{% } else { %}		
	<td><input placeholder="Put your comment here" class="comment comment_{%=commentCount()%}" type="text"></td>
{% } %}	
			<td>
			<p class="name fileName_{%=count()%}">{%=file.name%}</p>
			{% if (file.error) { %}
			<div><span class="label label-danger">Error</span> {%=file.error%}</div>
			{% } %}
			</td>
			<td><p class="size">{%=o.formatFileSize(file.size)%}</p></td>
			
			<td>
			{% if (!o.files.error && !i && !o.options.autoUpload) { %}
			<button class="hidden btn btn-success start uploadSingleReport btn-xs">
			<i class="glyphicon glyphicon-upload"></i>
			<span>Upload</span>
			</button>
			{% } %}
			{% if (!i) { %}
			<button class="btn btn-danger cancel btn-xs removeSingleReport">
			<i class="glyphicon glyphicon-trash"></i>
			<span>Remove</span>
			</button>
			{% } %}
			</td>
			</tr>
			{% } %}
		</script>
<!-- The template to display files available for download -->
<script id="template-download" type="text/x-tmpl">
			{% for (var i=0, file; file=o.files[i]; i++) { %}
			<tr class="template-download fade">
			<td></td>
			<td></td>
			<td>
			<span class="preview">
			{% if (file.thumbnailUrl) { %}
			<a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
			{% } %}
			</span>
			</td>
			<td>
			<p class="name">
			{% if (file.url) { %}
			<a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
			{% } else { %}
			<span>{%=file.name%}</span>
			{% } %}
			</p>
			</td>
			<td>
			<span class="size">{%=o.formatFileSize(file.size)%}</span>
			</td>
			<td></td>
			<td><i class="fa fa-check"></i></td>
			<td>
			{% if (file.deleteUrl) { %}
			<button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
			<i class="glyphicon glyphicon-trash"></i>
			<span>Delete</span>
			</button>
			<input type="checkbox" name="delete" value="1" class="toggle">
			{% } else { %}
			<button class="btn btn-danger cancel btn-xs removeSingleReport">
			<i class="glyphicon glyphicon-trash"></i>
			<span>Remove</span>
			</button>
			{% } %}
			</td>
			</tr>
			{% } %}
		</script>

<script>

	function dateCount() {
		var k = parseInt($('#k').html())+1;
		$('#k').html(k);
		return k;
	}

	function count() {
		return $('#k').html();
	}

	var r = 1;
	function commentCount() {
		$('#k').html(r);
		return r++;
	}
	
	$(document).on("change",".selectReport",function(){
		  //FormElements.init();
		//For disable future dates
		$('.reportDate').datepicker({
	        format: 'dd-mm-yyyy',
	        endDate: '+0d',
	        autoclose: true
	    });
	});
	
	
	
	/* $(document)
			.on("change",
					"#uploadReportFile",
					function() {
						$('#reportLength').html($('.template-upload').length+1+$('#displayReportTableBody tr').length);
 							var reportIndex = 1;
 							var files = document.getElementById("uploadReportFile").files;
 							for (var i = 0; i < files.length; i++) {
 								var report = '<tr class="template-upload fade in">';
 								
 								if ($('#currentPageId').html()!=7) { 

 									if($("#visitSelect option:first").val()!=$('#visitSelect').val()){ 
 										report = report + '<td><Select onchange="testPackageList(this)" class="testPackageSelectList testPackageSelect_'+reportIndex+'" style="width: 100px; margin-right: 15px;"><option val="package">Package</option><option val="test">Test</option></select></td>';
 									} else {
 										report = report + '<td><input class="hidden testPackageSelect_'+reportIndex+'" style="width: 100px; margin-right: 15px;" value="firstVisit"></td>';
 									} 
 									report = report + '<td><Select class="assignTestPackage testSelectList testSelect_'+reportIndex+'" style="width: 100px; margin-right: 15px;"></select></td>'
 											+' <td><Select class="centerSelectList centreSelect_'+reportIndex+'" style="width: 100px; margin-right: 15px;"></select></td>'
 											+' <td><input style="width:67%;" class="form-control datepicker reportDate reportDate_'+reportIndex+'" type="text"></td>';
 												
 									 } else { 		
 										report = report + '<td><input placeholder="Put your comment here" class="comment comment_'+reportIndex+'" type="text"></td>';
 									 }
 								
 								report = report
 										+ '<td><p class="name fileName_'+reportIndex+'">'+files[i].name+'</p></td>'
 										+ '<td><p class="size"></p>'+files[i].size+'</td>'
 										+ '<td><button class="btn btn-danger cancel btn-xs removeSingleReport"><i class="glyphicon glyphicon-trash"></i><span>Remove</span></button></td></tr>';
 								$("#reportDiv").append(report);
 							}
 							
 							if($("#visitSelect option:first").val()==$('#visitSelect').val()){
 								 getPatientTest($('#clientMasterId').html());
 								}
 								else{
 								 listOfPackageMasterReport(0);
 								}
 							getListOfCentres();
 							getPatientCenter($('#clientMasterId').html());
							
 							//For disable future dates
 							$('.reportDate').datepicker({
 						        format: 'dd-mm-yyyy',
 						        endDate: '+0d',
 						        autoclose: true
 						    });
					}); */
</script>
<!-- start: JavaScript Event Handlers for this page -->

<!-- pop for feedback -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Feedback</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<textarea placeholder="Feedback" id="feedbackTextarea"
							class="width form-control"></textarea>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-o"
					data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary"
					onclick="submitFeedback()">Submit</button>
			</div>
		</div>
	</div>
</div>
<!--End of pop for feedback -->

<!-- pop for change password -->
<div class="modal fade" id="changePasswordModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Change Password</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-4" style="margin-top: 7px;">
						<p>
							<span>*</span>Current Password
						</p>
					</div>
					<div class="col-md-8">
						<div class="form-group form-actions">
							<span class="input-icon"> <input type="password"
								id="currentPassword" name="password"
								placeholder="Current Password" class="form-control password" />
								<i class="fa fa-lock"></i>
							</span>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4" style="margin-top: 7px;">
						<p>
							<span>*</span>New Password
						</p>
					</div>
					<div class="col-md-8">
						<div class="form-group form-actions">
							<span class="input-icon"> <input type="password"
								id="newPassword" name="password" placeholder="New Password"
								class="form-control password" /> <i class="fa fa-lock"></i>
							</span>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-md-4" style="margin-top: 7px;">
						<p>
							<span>*</span>Confirm Password
						</p>
					</div>
					<div class="col-md-8">
						<div class="form-group form-actions">
							<span class="input-icon"> <input type="password"
								id="confirmPassword" name="password"
								placeholder="Confirm Password" class="form-control password" />
								<i class="fa fa-lock"></i>
							</span>
						</div>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary"
					onclick="changePassword()">Change Password</button>
			</div>
		</div>
	</div>
</div>
<!--End of pop for change password -->

<!-- Modal for upload report-->
<div class="modal fade" id="uploadReportModal" tabindex="-1"
	role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog modal-lg" style="width: 98%;">
		<div class="modal-content">
		
        <!-- The file upload form used as target for the file upload widget -->
        <form id="fileupload" action="#" method="POST" enctype="multipart/form-data">
        
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Upload Files</h4>
			</div>
			<span class="hidden" id="clientReportLineIdEdit"></span> 
			<span class="hidden" id="k">0</span>

			<div class="modal-body" id="uploadReportModelBody">
				<%-- <form class="uploadReportForm" name="fileupload" id="fileupload"
					action="../UploadServlet" method="post"
					enctype="multipart/form-data"> --%>
					<div class="row">
						<div class="col-sm-4 col-md-3 uploadReport">
							<h5 class="over-title margin-bottom-15">
								Upload <span class="text-bold">All Files</span>
							</h5>
							<h5>Select Files</h5>
							<div class="div_border">
								<div class="well">Drag Your Files Here...</div>
								<div class="well">Upload only PDF and JPEG File</div>
								<!-- <div class="well">Another drop zone with its own settings</div> -->
								


            <div class="files" id="files1">
                <span class="btn btn-success fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i> <span>Select
										Multiple Files</span> 
							 <input id="uploadReportFile" type="file" name="files1" multiple />
                </span>
                <br />
                <ul class="fileList hidden"></ul>
            </div>

            <br>
            <br>


            <br>
            <div class="row hidden">
                <div class="span16">
                    <table class="zebra-striped"><tbody class="files"></tbody></table>
                </div>
            </div>
        

								<!-- <span class="btn btn-success fileinput-button"> <i
									class="glyphicon glyphicon-plus"></i> <span>Select
										Multiple Files</span> <input class='selectReport' type="file" id="myFile" name="file"
									multiple="">
								</span> <br /> <br />
								 <span class="btn btn-success fileinput-button"> <i
								class="glyphicon glyphicon-plus"></i> <span>Select
									Single File</span>
								<input type="file" name="file">
							</span> -->
							</div>
						</div>

						<div class="col-sm-10 col-md-9" id="displayReportDiv">
							<!-- <h5 class="over-title margin-bottom-15 uploadReport">
							Upload <span class="text-bold">Queue</span>
						</h5> -->

							<div class="row" id="addNewVisitDiv" style="margin-bottom: 10px;margin-top: 10px;">
								<div class="col-md-1 col-xs-6">
									<select class="center" id="visitSelect"
										onchange="changeVisitSelectListPopUp(this)">
									</select>
								</div>
								<div class="col-md-2 col-xs-6">
									<input id="showVisitDate" class="form-control center"
										type="text" disabled> <input id="visitDate"
										class="form-control center hidden" type="text">
								</div>
								<div class="col-md-2 col-xs-6">
									<select class="form-control" id="visitTypeMasterSelect"
										disabled>
									</select>
								</div>
								<div class="col-md-2 col-xs-6">
									<select class="form-control centerSelectList"
										id="popUpCenterList" disabled>
									</select>
								</div>
								<div class="col-md-1 col-xs-6">
									<select id="forNewVisit"
										onchange="testPackageListForNewVisit()" disabled="disabled">
										<option val="package">Package</option>
										<option val="test">Test</option>
									</select>
								</div>
								<div class="col-md-2 col-xs-6">
									<select class="form-control testSelect_forNewVisit"
										id="popUpTestPackageList" disabled="disabled">
										<!-- <option value="230">Healthfriend</option> -->
										
									</select>
								</div>
								<div class="col-md-1 col-xs-6">
									<a class="btn btn-sm btn-success" onclick="addNewVisit()">ADD</a>
								</div>
								<div class="col-md-1 col-xs-6">
									<a class="btn btn-sm btn-success" onclick="saveNewVisit()">Save</a>
								</div>
							</div>


							<div class="row">
								<div class="col-md-8">
									<p class="hidden" id="packageDetails">
										Package Name : <span id="packageNameUploadReportPopUp"></span>
									</p>
								</div>
								<div class="col-md-4">
									<p class="uploadReport">
										Queue Length : <span id="reportLength"></span>
									</p>
								</div>
							</div>
							<div class="table-responsive" style="max-height: 350px;">
								<table role="presentation" class="table table-striped">
									<thead>
										<tr>
											<th class="clientUploadReportHide"></th>
											<th class="clientUploadReportHide">Test Name</th>
											<th class="clientUploadReportHide">Center Name</th>
											<th class="clientUploadReportHide">Report Date</th>
											<th class="clientUploadReportShow">Remark</th>
											<th>Name</th>
											<th class="hidden-xs">Size</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody class="files" id="reportDiv"></tbody>
									<tbody id="displayReportTableBody"></tbody>
								</table>
							</div>
							<br /> <br />
							<div class="uploadReport">
								<button id="uploadAllPatientReport" type="button"
									class="hidden btn btn-primary start">
									<i class="glyphicon glyphicon-upload"></i> Upload All
								</button>
								<button onclick="removePatientReport()"
									id="removeAllPatientReport" type="button"
									class="hidden btn btn-sm btn-o btn-primary start">
									<i class="fa fa-trash"></i> Remove All
								</button>
							</div>
							<div class="editReportPopUp">
								<!-- <span class="btn btn-success fileinput-button"> <i
									class="glyphicon glyphicon-plus"></i> <span>Add File</span> <input
									type="file" name="file" onchange="editReportFile(event)" class='selectReport'>
								</span> -->
								
								<div class="files" id="files2">
                <span class="btn btn-success fileinput-button">
                    <i class="glyphicon glyphicon-plus"></i> <span> Add File </span> 
							 <input id="uploadReportFile" onchange="editReportFile(event)" type="file" name="files2"/>
                </span>
                <br />
                <ul class="fileList hidden"></ul>
            </div>
            
							</div>
						</div>
					</div>
				<%-- </form> --%>
				
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-sm btn-o"
					data-dismiss="modal">Close</button>
                <button type="x" id="uploadBtn" class="btn btn-sm btn-primary">Save</button>
				<!-- <button type="button" class="btn btn-sm btn-primary"
					id="savePatientReportBtn" onclick="savePatientReport()">Save
				</button>
				<button type="button" class="btn btn-sm btn-primary"
					id="saveSelfClientReportBtn" onclick="saveSelfClientReport()">Save
				</button> -->
			</div>
			</form>
		</div>
	</div>
</div>
<!-- End of Modal for upload report -->

<!-- Modal for verify upload report -->
<div class="modal fade bs-example-modal-lg" id="verifyUploadReportModal" style="z-index: 10000 ! important;"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog modal-lg" style="width: 98%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Verify Selection</h4>
			</div>
			<div class="modal-body">
				<div class="row">
				<span class="hidden" id="clientReportName"></span>
					<span class="hidden" id="clientReportLineId"></span> <span
						class="hidden" id="nextId"></span> <span class="hidden"
						id="lastId"></span> <span class="hidden" id="ocrValues"></span>
					<div class="col-sm-8 col-md-8">
						<!-- <iframe id="displayPatientReport" src="" width="100%" height="507px"></iframe> -->
						<object id="displayPatientReport" data="" width="100%" height="507px"></object>
					</div>
					<div class="col-sm-4 col-md-4">
						<div class="row">
							<div class="checkbox clip-check check-primary"
								style="margin-top: 0px; margin-bottom: 0px;">
								<input type="checkbox" id="selectAll" value="1"><label
									for="selectAll">Select All</label>
							</div>
						</div>
						<div id="checkAll">
							<div class="row">
								<div class="checkbox clip-check check-primary"
									style="margin-top: 0px; margin-bottom: 0px;">
									<input type="checkbox" id="checkbox1" value="1"><label
										for="checkbox1">* All Pages present in Report</label>
								</div>
							</div>
							<div class="row">
								<div class="checkbox clip-check check-primary"
									style="margin-top: 0px; margin-bottom: 0px;">
									<input type="checkbox" id="checkbox2" value="1"><label
										for="checkbox2">* Value & Range Verified</label>
								</div>
							</div>
							<div class="row">
								<div class="checkbox clip-check check-primary"
									style="margin-top: 0px; margin-bottom: 0px;">
									<input type="checkbox" id="checkbox3" value="1"><label
										for="checkbox3">* Client Name = Name on Report</label>
								</div>
							</div>
							<div class="row">
								<div class="checkbox clip-check check-primary"
									style="margin-top: 0px; margin-bottom: 0px;">
									<input type="checkbox" id="checkbox4" value="1"><label
										for="checkbox4">* Report readble</label>
								</div>
							</div>
						</div>
						<br />
						<div class="row">
							<label>Comment</label>
							<textarea placeholder="Comment" id="verifyComment"
								class="width form-control" style="width: 97%"></textarea>
							<p class="fontsize11">Select all* marked items to verify
								reject</p>
						</div>
						<div class="row">
							<button id="verifyReportBtn" type="button"
								class="btn btn-primary start right disabled"
								onclick="verifyReport()">Verify</button>
						</div>
						<br />
						<div class="row">
							Reason to reject <select id="reasonToReject"
								class="form-control width" style="width: 97%">
							</select>
						</div>
						<br />
						<div class="row">
							Comment
							<textarea placeholder="Comment" id="rejectComment"
								class="width form-control" style="width: 97%"></textarea>
						</div>
						<br />
						<div class="row">
							<button id="rejectReportBtn" type="button"
								class="btn btn-danger delete" onclick="rejectReport()">
								<span>Reject</span>
							</button>
						</div>

					</div>

				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-o"
					onclick="nextReport()">Next</button>
				<button type="button" class="btn btn-primary btn-o"
					data-dismiss="modal">Close</button>
				<!-- <button type="button" class="btn btn-primary">Save changes</button> -->
			</div>
		</div>
	</div>
</div>
<!-- End of Modal for verify upload report -->

<!-- pop for HRA Question -->
<div class="modal fade" id="questionModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="questionModalLabel"></h4>
			</div>
			<div class="modal-body" id="hraQuestionBody"
				style="max-height: 500px; overflow-y: scroll; overflow-x: hidden;">

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-o"
					data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id="saveHRADetaisBtn"
					onclick="saveHRADetais()">Save</button>
			</div>
		</div>
	</div>
</div>
<!--End of pop HRA Question -->

<!-- pop for parameter values -->
<div class="modal fade" id="parameterValuesModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 50%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="parameterDivTitle"></h4>
			</div>
			<div class="modal-body" style="padding: 15px 30px 20px;">

				<div class="row" style="margin-top: 10px;">
					<div class="col-md-3">
						<label>Parameter Name</label>
					</div>
					<div class="col-md-9">
						<input type="text" placeholder="Parameter Name"
							id="parameterNameCenterWise" class="form-control">
					</div>
				</div>
				
				<div class="row" style="margin-top: 10px;" id="criticalLowValueDiv">
					<div class="col-md-3">
						<label>Critical Low Value</label>
					</div>
					<div class="col-md-9">
						<input type="text" placeholder="Critical Low Value"
							id="criticalLowValueDisplay" class="form-control" disabled="disabled">
					</div>
				</div>
				
				<div class="row" style="margin-top: 10px;" id="criticalHighValueDiv">
					<div class="col-md-3">
						<label>Critical High Value</label>
					</div>
					<div class="col-md-9">
						<input type="text" placeholder="Critical High Value"
							id="criticalHighValueDisplay" class="form-control" disabled="disabled">
					</div>
				</div>

				<div class="row">
					<div class="col-md-3 col-xs-4">
						<label>Normal Values</label>
					</div>
					<form id="valuesForm">
						<div class="col-md-3 col-xs-4">
							<input class="normalValue_1" type="radio" name="values"
								value="individual" disabled="disabled" checked> Individual<br>
						</div>
						<div class="col-md-3 col-xs-4">
							<input class="normalValue_2" type="radio" name="values"
								value="general" disabled="disabled"> General<br>
						</div>
					</form>
				</div>
				<input type="hidden" id="criticalLowValue">
				<input type="hidden" id="criticalHighValue">
				<div class="table-responsive" id="individualValuesDiv"
					style="margin-top: 10px;">
					<table
						class="table table-striped table-bordered table-hover table-full-width"
						id="sample_1">
						<thead>
							<tr>
								<th>Value type</th>
								<th>Lower</th>
								<th>Upper</th>
								<th>Unit</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Male</td>
								<td><input type="text" id="lower_1" onkeyup="numericOnly(this),parameterValueValidation('low',this)"></td>
								<td><input type="text" id="upper_1" onkeyup="numericOnly(this),parameterValueValidation('high',this)"></td>
								<td><select class="unitSelectList" id="unit_1"></select></td>
							</tr>
							<tr>
								<td>Female</td>
								<td><input type="text" id="lower_2" onkeyup="numericOnly(this),parameterValueValidation('low',this)"></td>
								<td><input type="text" id="upper_2" onkeyup="numericOnly(this),parameterValueValidation('high',this)"></td>
								<td><select class="unitSelectList" id="unit_2"></select></td>
							</tr>
							<tr>
								<td>Child</td>
								<td><input type="text" id="lower_3" onkeyup="numericOnly(this),parameterValueValidation('low',this)"></td>
								<td><input type="text" id="upper_3" onkeyup="numericOnly(this),parameterValueValidation('high',this)"></td>
								<td><select class="unitSelectList" id="unit_3"></select></td>
							</tr>
							<tr>
								<td>Neonate</td>
								<td><input type="text" id="lower_4" onkeyup="numericOnly(this),parameterValueValidation('low',this)"></td>
								<td><input type="text" id="upper_4" onkeyup="numericOnly(this),parameterValueValidation('high',this)"></td>
								<td><select class="unitSelectList" id="unit_4"></select></td>
							</tr>

						</tbody>
					</table>
				</div>
				<div class="row" id="generalValuesDiv"
					style="display: none; padding: 15px;">
					<div class="row">
						<div class="col-md-3">
							<label>General</label>
						</div>
						<div class="col-md-9">
							<textarea placeholder="General" id="generalComment"
								style="height: 35px;" class="width form-control"></textarea>
						</div>
					</div>
					<div class="row" style="margin-top: 10px;">
						<div class="col-md-3">
							<label>End Word</label>
						</div>
						<div class="col-md-9">
							<textarea placeholder="End Word" id="parameterValueTo"
								style="height: 35px;" class="width form-control"></textarea>
						</div>
					</div>

				</div>

			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-o"
					data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary"
					id="saveParameterValuesBtn" onclick="saveParameterValues()">Save
					changes</button>
			</div>
		</div>
	</div>
</div>
<!--End of pop parameter values -->

<!-- Modal for view upload report -->
<div class="modal fade bs-example-modal-lg" id="viewReportModal" style="z-index: 10000 ! important;"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<div class="row">
					<div class="col-md-2 col-xs-11">
						<h4 class="modal-title" id="myModalLabel">View report</h4>
					</div>
					<div class="col-md-6 col-xs-11">
						<h4 class="modal-title" id="testNameViewReport"></h4>
					</div>
				</div>
			</div>
			<div class="modal-body">
				<!-- <iframe id="displayViewReport" src="" width="100%" height="530px"></iframe> -->
				<object id="displayViewReport" data="" width="100%" height="530px"></object>
			</div>
		</div>
	</div>
</div>
<!-- End of Modal for view upload report -->

<!-- Modal for send email and sms -->
<div class="modal fade bs-example-modal-lg" id="sendEmailAndSmsModal"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog modal-lg" style="width: 98%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Send Email and Sms</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-8">
						<!-- <h5 class="text-bold margin-bottom-15">Email Template :</h5> -->
						<div class="form-group">
							To:
							<div id="emailSentToList">

								<!-- <div class="vR">
									<span email="amol1421993@gmail.com" class="vN">
										<div class="vT">amol1421993@gmail.com</div>
										<div class="vM" id="amol1421993@gmail.com" onclick="removeContact(this)"></div></span>
								</div> -->

							</div>
						</div>
						<div class="form-group">
							Subject: <input type="text" id="emailSubject"
								class="form-control">
						</div>
						<div class="form-group">
							<select id="emailTemplateSelectListInteraction"
								onchange="emailTemplateChange()" class="form-control width">
							</select>
						</div>
						<div class="form-group">
							<textarea class="ckeditor form-control" cols="10" rows="10"
								id="emailMedicalAdvice" name="medicalAdvice"></textarea>
							<!-- <textarea placeholder="Medical advice" class="form-control"
								id="emailMedicalAdvice" name="medicalAdvice"></textarea> -->
						</div>
					</div>

					<div class="col-md-4">

						<div class="form-group">
							To:
							<div id="smsSentToList"></div>
						</div>

						<!-- <h5 class="text-bold margin-bottom-15">Sms Template :</h5> -->
						<div class="form-group">
							<select id="smsTemplateSelectListInteraction"
								onchange="smsTemplateChange()" class="form-control width">
							</select>
						</div>
						<div class="form-group">
							<textarea placeholder="Medical advice" class="form-control"
								id="smsMedicalAdvice" name="medicalAdvice"></textarea>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3 col-md-offset-5">
						<div class="form-group">
							<button type="button" class="btn btn-primary"
								onclick="sendEmailAndSms()">Send</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- End of Modal for send email and sms -->

<!-- pop for Follow up record -->
<div class="modal fade" id="followUpRecordModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					onclick="clearFollowUpRecord()" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Follow Up Record</h4>
			</div>
			<div class="modal-body">

				<!-- <div class="row">

					<div class="col-md-6">
						<h5 class="text-bold">Call Result</h5>
						<div class="form-group">
							<select id="callResultStatus"
								class="form-control width followUpPop">
								<option value="fa fa-exclamation-triangle color_red">Not
									Answered</option>
								<option value="fa fa-star color_yellow">Answered</option>
								<option value="fa fa-flag color_golden">Answered with
									Follow Up</option>
							</select>
						</div>
					</div>

					<div class="col-md-6">
						<h5 class="text-bold">Plan Of Action</h5>
						<div class="form-group">
							<select id="actionSelectListInteraction"
								onchange="listOfSubActionMasterInteraction($('#actionSelectListInteraction').val())"
								class="form-control width followUpPop">
							</select>
						</div>
					</div>

					<div class="col-md-6">
						<h5 class="text-bold">SubAction</h5>
						<div class="form-group">
							<select id="subActionSelectListInteraction"
								class="form-control width followUpPop">
							</select> <input type="text" id="showSubAction" disabled>
						</div>
					</div>

					<div class="col-md-6">
						<h5 class="text-bold">Center</h5>
						<div class="form-group">
							<select id="centerSelectListInteraction"
								class="form-control width followUpPop">
							</select>
						</div>
					</div>

					<div class="col-md-6">
						<h5 class="text-bold">Next Follow Up Date</h5>
						<div class="form-group">
							<input class="form-control datepicker followUpPop"
								id="followUpDate" type="text">
						</div>
					</div>

					<div class="col-md-6">
						<h5 class="text-bold">Time</h5>
						<div class="form-group">
							<input type="text" id="timepicker-default"
								class="form-control followUpPop">
						</div>
					</div>

					<div class="col-md-6">
						<h5 class="text-bold">Activities</h5>
						<div class="form-group">
							<textarea placeholder="Activities"
								class="form-control followUpPop" id="activitiesInteraction"
								name="activitiesInteraction"></textarea>
						</div>
					</div>

					<div class="col-md-6">
						<h5 class="text-bold">Comment</h5>
						<div class="form-group">
							<textarea placeholder="Comment" class="form-control followUpPop"
								id="commentInteraction" name="commentInteraction"></textarea>
						</div>
					</div>
				</div> -->
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-o"
					onclick="clearFollowUpRecord()" data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary"
					onclick="saveFollowUpRecord()">Save
				</button>
			</div>
		</div>
	</div>
</div>
<!--End of pop Follow up record -->

<!-- pop for Email And Sms View -->
<div class="modal fade" id="emailSmsModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">View</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<div class="form-group">
							<div class="row">
								<div class="col-md-2">To :</div>
								<div class="col-md-10">
									<h5 id="emailSentTo"></h5>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12" id="emailSubjectPopUpDiv">
						<div class="form-group">
							<div class="row">
								<div class="col-md-2">Subject :</div>
								<div class="col-md-10">
									<h5 id="emailSubjectPopUp"></h5>
								</div>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group">
							<div class="row">
								<div class="col-md-2">Text :</div>
								<div class="col-md-10">
									<h5 id="emailBody" style="word-wrap: break-word;"></h5>
								</div>
							</div>
						</div>
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
<!--End of pop Email And Sms View -->

<!-- pop for Rejected report list -->
<div class="modal fade" id="rejectedReportListModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 70%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Incomplete Report List</h4>
			</div>
			<div class="modal-body">

				<!-- start: DYNAMIC TABLE -->
				<div class="container-fluid container-fullw bg-white">
					<div class="row">
						<div class="col-md-12 table-responsive"
							style="max-height: 500px; overflow: auto;">
							<table
								class="table table-striped table-bordered table-hover table-full-width"
								id="sample_1">
								<thead>
									<tr>
										<th class="center">Sr.No.</th>
										<th>Client Id</th>
										<th>Client Name</th>
										<!-- <th>Report Name</th> -->
										<th>Product Name</th>
										<th>Appointment Date</th>
										<th>Reject Reason</th>
										<th>Rejected By</th>
									</tr>
								</thead>
								<tbody id="rejectedReportList">

								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- end: DYNAMIC TABLE -->


			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-o"
					data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<!--End of pop Rejected report list -->




<!-- pop up for Total pending report list -->
<div class="modal fade" id="totalPendingVisitsModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 70%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Pending Reports Upload List</h4>
			</div>
			<div class="modal-body">

				<!-- start: DYNAMIC TABLE -->
				<div class="container-fluid container-fullw bg-white">
					<div class="row">
						<div class="col-md-12 table-responsive"
							style="max-height: 500px; overflow: auto;">
							<table
								class="table table-striped table-bordered table-hover table-full-width"
								id="sample_1">
								<thead>
									<tr>
										<th class="center">Sr.No.</th>
										<th>Client Id</th>
										<th>Client Name</th>
										<th>User Id</th>
										<th>Product Name</th>
										<th>Appointment Date</th>
										<!-- <th>Rejected By</th> -->
									</tr>
								</thead>
								<tbody id="totalPendingVisits">

								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- end: DYNAMIC TABLE -->


			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-o"
					data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<!--End of pop up for Total pending report list -->



<!-- pop for Analysis-->
<div class="modal fade bs-example-modal-lg" id="analysisModal"
	tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
	aria-hidden="true">
	<div class="modal-dialog modal-dialog modal-lg" style="width: 98%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<div class="row">
					<div class="col-md-2 col-xs-11">
						<h4 class="modal-title" id="myModalLabel">Analysis</h4>
					</div>
					<!-- <div class="col-md-6 col-xs-11">
							<h4 class="modal-title" id="testNameDataEntry"></h4>
						</div> -->
				</div>
			</div>
			<div class="modal-body">
			
			<div class="row" style="margin-bottom: 10px;">
										<div class="col-md-2 col-sm-4 col-xs-12">
											<select class="form-control center"
												id="visitSelectOnMyReportPage"
												onchange="changeVisitSelectListInteraction(this)">
											</select>
										</div>
										<div class="col-md-2 col-sm-4 col-xs-12">
											<input id="showVisitDateOnMyReportPage"
												class="form-control center" type="text" disabled>
										</div>
										<div class="col-md-2 col-sm-4 col-xs-12">
											<select class="form-control center"
												id="visitTypeMasterSelectOnMyReportPage" disabled>
											</select>
										</div>
										<div class="col-md-2 col-sm-4 col-xs-12 pull-right">
											<button id="generateReportBtn" type="button"
														class="btn btn-danger btn-sm" onclick="generateReport()">
														Generate Health Report
											</button>
										</div>
									</div>
									
				<div class="row">
					<div class="col-sm-12 col-md-12">
						<span id="analysisClientId" class="hidden"></span>
						<div class="tabbable no-margin no-padding">
							<ul class="nav nav-tabs" id="myTab">
								<li id="engmntPopId" class="active padding-top-5 padding-left-5"><a
									data-toggle="tab" href="#engagement"> Engagement </a></li>
								<li id="analysisPopId" class="padding-top-5 padding-left-5"><a
									data-toggle="tab" href="#analysis"> Analysis </a></li>
								<li class="padding-top-5"><a data-toggle="tab"
									href="#demoghraphic"> Demoghraphic </a></li>
								<li class="padding-top-5 padding-left-5"
									onclick="reportInteraction()"><a data-toggle="tab"
									href="#report"> Report </a></li>
								<li class="padding-top-5"
									onclick="listOfHraTypeMasterInteraction()"><a
									data-toggle="tab" href="#hra"> HRA </a></li>
							</ul>
							<div class="tab-content">
							
							<div id="engagement" class="tab-pane fade in">
							
							<div class="row">

					<div class="col-md-6">
							<div class="row">

					<div class="col-md-6">
						<h5 class="text-bold">Call Result</h5>
						<div class="form-group">
							<select id="callResultStatus"
								class="form-control width followUpPop">
								<option value="fa fa-exclamation-triangle color_red">Not
									Answered</option>
								<option value="fa fa-star color_yellow">Answered</option>
								<option value="fa fa-flag color_golden">Answered with
									Follow Up</option>
							</select>
						</div>
					</div>

					<div class="col-md-6">
						<h5 class="text-bold">Plan Of Action</h5>
						<div class="form-group">
							<select id="actionSelectListInteraction"
								onchange="listOfSubActionMasterInteraction($('#actionSelectListInteraction').val())"
								class="form-control width followUpPop">
							</select>
						</div>
					</div>

					<div class="col-md-6">
						<h5 class="text-bold">SubAction</h5>
						<div class="form-group">
							<select id="subActionSelectListInteraction"
								class="form-control width followUpPop">
							</select> <input class="form-control" type="text" id="showSubAction" style="display:none;" disabled>
						</div>
					</div>

					<div class="col-md-6">
						<h5 class="text-bold">Center</h5>
						<div class="form-group">
							<select id="centerSelectListInteraction"
								class="form-control width followUpPop">
							</select>
						</div>
					</div>

					<div class="col-md-6">
						<h5 class="text-bold">Next Follow Up Date</h5>
						<div class="form-group">
							<input class="form-control datepicker followUpPop"
								id="followUpDate" type="text">
						</div>
					</div>

					<div class="col-md-6">
						<h5 class="text-bold">Time</h5>
						<div class="form-group">
							<input type="text" id="timepicker-default"
								class="form-control followUpPop">
						</div>
					</div>

					<div class="col-md-6">
						<h5 class="text-bold">Activities</h5>
						<div class="form-group">
							<textarea placeholder="Activities"
								class="form-control followUpPop" id="activitiesInteraction"
								name="activitiesInteraction"></textarea>
						</div>
					</div>

					<div class="col-md-6">
						<h5 class="text-bold">Comment</h5>
						<div class="form-group">
							<textarea placeholder="Comment" class="form-control followUpPop"
								id="commentInteraction" name="commentInteraction"></textarea>
						</div>
					</div>
					
					<div class="col-md-6">
						<h5 class="text-bold">Status</h5>
						<div class="form-group">
							<select id="engmtStatus"
								class="form-control width followUpPop">
								<option value="0">Pending</option>
								<option value="1">Re-schedule</option>
								<option value="2">Completed</option>
							</select>
						</div>
					</div>
					
				</div>
				
				<div class="row">
					<div class="col-md-12 center">
							<button id="saveFollowUpRecordBtn" type="button" class="btn btn-primary"
								onclick="saveFollowUpRecord()">Save
							</button>
							<button type="button" class="btn btn-danger"
								onclick="clearFollowUpRecord()">Reset</button>
					</div>
				</div>
				
				</div>
							
						<div class="col-md-6">
							<div class="row">
									<div
										class="panel-scroll ps-container table-responsive mousescroll"
										style="max-height: 400px;">
										<table class="table no-margin">
											<thead>
										<tr>
											<th class="center">#</th>
											<th>Date</th>
											<th>Follow Up Date</th>
											<th>Engmt Status</th>
											<th>View</th>
										</tr>
									</thead>
									<!-- <tbody id="followUpTableBody"></tbody> -->
										<tbody id="engmntTblBody"></tbody>
										</table>
										<div class="ps-scrollbar-x-rail"
											style="left: 0px; bottom: 3px;">
											<div class="ps-scrollbar-x" style="left: 0px; width: 0px;"></div>
										</div>
										<div class="ps-scrollbar-y-rail" style="top: 0px; right: 3px;">
											<div class="ps-scrollbar-y" style="top: 0px; height: 0px;"></div>
										</div>
									</div>
								</div>
							</div>
						</div>
						</div>

								<div id="analysis" class="tab-pane fade in">

									<!-- start: DYNAMIC TABLE -->
									<div class="container-fluid container-fullw bg-white">
										<div class="row">
											<div class="col-md-12 table-responsive"
												style="max-height: 400px; overflow: auto;">
												<table
													class="table table-striped table-bordered table-hover table-full-width"
													id="sample_1">
													<thead>
														<tr>
															<th class="center">Sr.No.</th>
															<th>Date</th>
															<th>Test</th>
															<th>Parameter Name</th>
															<th>Parameter Value</th>
															<th>Min</th>
															<th>Max</th>
															<th>Unit Name</th>
															<th>Abnormal</th>
															<th>Status</th>
															<th>Comment</th>
														</tr>
													</thead>
													<tbody id="analysisParameterList">
													</tbody>
												</table>
											</div>
										</div>

										<div class="row" style="margin-top:15px;">
											<div class="col-md-2 col-sm-3 col-xs-12">
												<div class="form-group">
													<label class="control-label"> Added On
													</label> <input type="text"
														class="form-control center" id="analysisAddedOn" disabled>
												</div>
											</div>
											
											<div class="col-md-2 col-sm-3 col-xs-12">
												<div class="row">
												<div class="form-group">
													<label class="control-label"> Comment Type
													</label>
													<select class="form-control" id="commentType">
												</select>
												</div>
												</div>
												
												<div class="row">
												<div class="form-group">
													<label class="control-label"> Comment
													</label> <textarea class="form-control" id="clientAnalysisComment"
													style="max-width: 100%;" maxlength="5000"></textarea>
												</div>
												</div>
												
												<div class="row">
												<div class="form-group">
													<button onclick="addAnalysisComment()"
													class="btn btn-success btn-sm">ADD</button>
												</div>
												</div>
												
											</div>
											
											<div class="col-md-5 col-sm-3 col-xs-12">
												<div class="form-group">
													<label class="control-label"> Comments
													</label> 
													<div style="max-height: 200px; overflow: auto;">
												<table
													class="table table-striped table-bordered table-hover table-full-width">
													<thead>
														<tr>
															<th class="center">#</th>
															<th>Comment Type</th>
															<th>Comment</th>
															<th>Action</th>
														</tr>
													</thead>
													<tbody id="analysisComments"></tbody>
													<tbody class="hidden" id="removedAnalysisComments"></tbody>
												</table>
											</div>
												</div>
											</div>
											
											<div class="col-md-2 col-sm-3 col-xs-12">
												<div class="form-group">
													<label class="control-label"> Classification <span
														class="symbol required"></span>
													</label> 
													<select class="form-control" id="clientClassification">
													<option value="">Select</option>
													<option value="Normal">Normal</option>
													<option value="Abnormal">Abnormal</option>
													<option value="Borderline">Borderline</option>
													<option value="Urgent">Urgent</option>
												</select>
												</div>
											</div>

											<div style="margin-top: 22px;"
												class="col-md-1 col-sm-3 col-xs-12 pull-right">
												<div class="row">
													<button id="savePatientAnalysisBtn" type="button"
														class="btn btn-primary" onclick="savePatientAnalysis()">Save</button>
												</div>
											</div>

										</div>

									</div>
									<!-- end: DYNAMIC TABLE -->

									<!-- <div class="row pull-right">
										<button id="savePatientAnalysisBtn" type="button"
											class="btn btn-primary" onclick="savePatientAnalysis()">Save</button>
									</div> -->

								</div>
								<div id="demoghraphic" class="tab-pane fade in">
									<div class="row">
										<div class="col-sm-5 col-md-4">
											<div class="user-left">
												<div class="center">
													<h4></h4>
													<div class="fileinput fileinput-new"
														data-provides="fileinput">
														<div class="user-image">
															<div class="fileinput-new thumbnail">
																<img id="profilePictureImg" src="" />
															</div>
														</div>
													</div>
													<hr class="profile_hr">
												</div>
												<table class="table table-condensed">
													<tbody>
														<tr>
															<td>First Name</td>
															<td id="displayFirstNameTd"></td>
														</tr>
														<tr>
															<td>Middle Name</td>
															<td id="displayMiddleNameTd"></td>
														</tr>
														<tr>
															<td>Last Name</td>
															<td id="displayLastNameTd"></td>
														</tr>
														<tr>
															<td>DOB/Age</td>
															<td id="displayDOBTd"></td>
														</tr>

														<tr>
															<td>Gender</td>
															<td id="displayGenderTd"></td>
														</tr>

														<tr>
															<td>Height</td>
															<td id="displayHeightTd"></td>
														</tr>

														<tr>
															<td>Weight</td>
															<td id="displayWeightTd"></td>
														</tr>
														<tr>
															<td>Blood Group</td>
															<td id="displayBloodGroupTd"></td>
														</tr>
													</tbody>
												</table>

											</div>
										</div>
										<div class="col-sm-5 col-md-4">
											<table class="table">
												<thead>
													<tr>
														<th colspan="3">Contact Information</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>Address 1</td>
														<td id="displayAddress1Td"></td>
													</tr>
													<tr>
														<td>Address 2</td>
														<td id="displayAddress2Td"></td>
													</tr>
													<tr>
														<td>Address 3</td>
														<td id="displayAddress3Td"></td>
													</tr>
													<tr>
														<td>City</td>
														<td id="displayCityNameTd"></td>
													</tr>
													<tr>
														<td>State</td>
														<td id="displayStateNameTd"></td>
													</tr>
													<tr>
														<td>Country</td>
														<td id="displayCountryNameTd"></td>
													</tr>
													<tr>
														<td>Pincode</td>
														<td id="displayPincodeTd"></td>
													</tr>
													<tr>
															<td>Mobile</td>
															<td id="displayMobNoTd"></td>
														</tr>
														<tr>
															<td>Land Line</td>
															<td id="displayLandlineNoTd"></td>
														</tr>
														<tr>
															<td>Email</td>
															<td id="displayEmailIdTd"></td>
														</tr>
														<tr>
															<td>User Name</td>
															<td id="displayUserNameTd"></td>
														</tr>
												</tbody>
											</table>
										</div>

										<div class="col-sm-5 col-md-4">
											<!-- <div class="row space20" id="updateProfileBtnRefreshDiv">
																<button type="button" id="updateUserProfile"
																	class="btn btn-wide btn-o btn-success">
																	Update</button>
																<button type="button" onclick="refreshDemoghraphic()"
																	class="btn btn-wide btn-o btn-info">Refresh</button>
															</div> -->
											<div class="panel" id="activities">
												<table class="table">
												<thead>
													<tr>
														<th colspan="3">Nominee Details</th>
													</tr>
												</thead>
												<tbody>
													<tr>
														<td>Relation with Nominee</td>
														<td id="displayNomineeRelationTd"></td>
													</tr>
													<tr>
														<td>First Name</td>
														<td id="displayNomineeFirstNameTd"></td>
													</tr>
													<tr>
														<td>Middle Name</td>

														<td id="displayNomineeMiddleNameTd"></td>
													</tr>
													<tr>
														<td>Last Name</td>
														<td id="displayNomineeLastNameTd"></td>
													</tr>
													<tr>
														<td>DOB/Age</td>
														<td id="displayNomineeDOBTd"></td>
													</tr>
												</tbody>
											</table>
											</div>
										</div>
									</div>
								</div>
								
								<div id="report" class="tab-pane fade in">
									<div
										class="panel-scroll ps-container table-responsive mousescroll"
										style="max-height: 400px;">
										<table class="table no-margin">
											<thead>
												<tr>
													<th class="center">Sr. No.</th>
													<th></th>
													<th>Test Name</th>
													<th>Centre Name</th>
													<th>Uploader</th>
													<th>Upload Date</th>
													<th>Report Date</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody id="uploadReportDataInteraction">

											</tbody>
										</table>
										<div class="ps-scrollbar-x-rail"
											style="left: 0px; bottom: 3px;">
											<div class="ps-scrollbar-x" style="left: 0px; width: 0px;"></div>
										</div>
										<div class="ps-scrollbar-y-rail" style="top: 0px; right: 3px;">
											<div class="ps-scrollbar-y" style="top: 0px; height: 0px;"></div>
										</div>
									</div>
								</div>
								
								<div id="hra" class="tab-pane fade in">
									<div class="row">
										<div class="col-md-12 mousescroll" id="hraQuestionDiv"
											style="max-height: 400px;"></div>
									</div>
								</div>

							</div>

						</div>

					</div>

				</div>
			</div>
			<!-- <div class="modal-footer">
				<button type="button" class="btn btn-primary btn-o"
					data-dismiss="modal">Close</button>
				<button type="button" class="btn btn-primary" id="">Save
				</button>
			</div> -->
		</div>
	</div>
</div>
<!--End of pop Analysis -->

<!-- opacity: 1; background-image: url("resources/assets/images/login-background.jpg"); background-size: cover;  -->

<!-- pop for terms and conditions -->
<div class="modal fade" id="termsAndConditionModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	style='opacity: 1; background-image: url("resources/assets/images/login-background.jpg"); background-size: cover;'>
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<!-- <button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button> -->
				<h4 class="modal-title">Terms and Conditions</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-xs-12">
						<div style="border: 1px solid #e5e5e5; height: 200px; overflow: auto; padding: 10px;">
							<jsp:include page="indus_terms_and_condition.jsp"></jsp:include>
						</div>
					</div>
				</div>

				<div class="row">
					<div class="col-xs-6 col-xs-offset-3">
						<div class="checkbox clip-check check-primary">
								<input type="checkbox" id="agreeTermsAndCondition" onchange="agreeTermsAndCondition()"> <label for="agreeTermsAndCondition">I agree with the Terms and Conditions </label>
						</div>
					</div>
				</div>

			</div>
			<div class="modal-footer">
				<!-- <button type="button" class="btn btn-primary btn-o"
					data-dismiss="modal">Close</button> -->
				<button type="button" class="btn btn-primary disabled"
					id="acceptTermsAndConditionBtn" onclick="acceptTermsAndCondition()">Accept
				</button>
			</div>
		</div>
	</div>
</div>
<!--End of pop terms and conditions -->

<!-- pop for Reminder Recurrence -->
<div class="modal fade" id="reminderRecurrenceModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 45%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title" id="repeatRemTitle"></h4>
			</div>
			<div class="modal-body">

				<div class="row repeatTime" id="oneTimeDiv" style="display:none;">
					<div class="col-md-12">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label class="col-sm-4 control-label recurrenceLabel">
									Reminder Date & Time </label>
								<div class="col-sm-8">
									<input type="text" id="reminderDateTime" class="form-control dateTimePicker">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label recurrenceLabel">
									Schedule On </label>
								<div class="col-sm-2">
									<input id="scheduleTime" type="text" class="form-control">
								</div>
								<div class="col-sm-3">
									<select id="scheduleFormat" class="form-control">
									<option value="hours"> Hours </option>
									<option value="days"> Days </option>
									</select>
								</div>
								<div class="col-sm-2">
									<button type="button" onclick="reminderSummary()">Add</button>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label recurrenceLabel">
									Summary </label>
								<div class="col-sm-8" id="reminderSummaryPopUp">
								<table
									class="table table-striped table-bordered table-hover table-full-width"
									id="sample_1">
									<thead>
										<tr>
											<th>#</th>
											<th>Reminder Date & Time</th>
											<th>Delete</th>
										</tr>
									</thead>
									<tbody id="reminderTableBody">
		
									</tbody>
								</table>
								</div>
							</div>
						</form>
					</div>
				</div>

				<div class="row repeatTime" id="repeatDiv" style="display:none;">
					<div class="col-md-12">
						<form class="form-horizontal" role="form">
							<div class="form-group">
								<label class="col-sm-4 control-label recurrenceLabel"> Repeats </label>
								<div class="col-sm-8">
									<select id="recurrenceRepeat" onchange="changeRecurrenceRepeat()" class="form-control">
									<option class="daily" value="1"> Daily </option>
									<option class="weekly" value="2"> Weekly </option>
									<option class="monthly" value="3"> Monthly </option>
									<option class="yearly" value="4"> Yearly </option>
									</select>
								</div>
							</div>
							
							<div id="dailyDiv" class="recurrenceRepeatDiv">
								<div class="form-group">
								<label class="col-sm-4 control-label recurrenceLabel"> Repeat every </label>
								<div class="col-sm-8">
									<select id="dailyRepeat" style="width: 20%;" onchange="repeatEvery()">
									<option	selected="selected" value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									<option value="16">16</option>
									<option value="17">17</option>
									<option value="18">18</option>
									<option value="19">19</option>
									<option value="20">20</option>
									<option value="21">21</option>
									<option value="22">22</option>
									<option value="23">23</option>
									<option value="24">24</option>
									<option value="25">25</option>
									<option value="26">26</option>
									<option value="27">27</option>
									<option value="28">28</option>
									<option value="29">29</option>
									<option value="30">30</option></select>  <label class="recurrenceLabel">days</label>
								</div>
							</div>
							</div>
							
							<div id="weeklyDiv" class="recurrenceRepeatDiv" style="display:none;">
								<div class="form-group">
								<label class="col-sm-4 control-label recurrenceLabel"> Repeat every </label>
								<div class="col-sm-8">
									<select id="weeklyRepeat" style="width: 20%;">
									<option	selected="selected" value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									<option value="16">16</option>
									<option value="17">17</option>
									<option value="18">18</option>
									<option value="19">19</option>
									<option value="20">20</option>
									<option value="21">21</option>
									<option value="22">22</option>
									<option value="23">23</option>
									<option value="24">24</option>
									<option value="25">25</option>
									<option value="26">26</option>
									<option value="27">27</option>
									<option value="28">28</option>
									<option value="29">29</option>
									<option value="30">30</option></select>  <label class="recurrenceLabel">weeks</label>
								</div>
							</div>
							<div class="form-group">
							<label class="col-sm-4 control-label recurrenceLabel"> Repeat On </label>
								<div class="col-sm-8">
								<div class="row">
																					<div class="col-md-3">
																						<div class="checkbox clip-check check-primary">
																							<input class="weeklyDays" type="checkbox"
																								id="SUNDAY" value="SUNDAY"> <label
																								for="SUNDAY"> S </label>
																						</div>
																					</div>
																					<div class="col-md-3">
																						<div class="checkbox clip-check check-primary">
																							<input class="weeklyDays" type="checkbox"
																								id="MONDAY" value="MONDAY"> <label
																								for="MONDAY"> M </label>
																						</div>
																					</div>
																					<div class="col-md-3">
																						<div class="checkbox clip-check check-primary">
																							<input class="weeklyDays" type="checkbox"
																								id="TUESDAY" value="TUESDAY"> <label
																								for="TUESDAY"> T </label>
																						</div>
																					</div>
																					<div class="col-md-3">
																						<div class="checkbox clip-check check-primary">
																							<input class="weeklyDays" type="checkbox"
																								id="WEDNESDAY" value="WEDNESDAY"> <label
																								for="WEDNESDAY"> W </label>
																						</div>
																					</div>
																					<div class="col-md-3">
																						<div class="checkbox clip-check check-primary">
																							<input class="weeklyDays" type="checkbox"
																								id="THURSDAY" value="THURSDAY"> <label
																								for="THURSDAY"> T </label>
																						</div>
																					</div>
																					<div class="col-md-3">
																						<div class="checkbox clip-check check-primary">
																							<input class="weeklyDays" type="checkbox"
																								id="FRIDAY" value="FRIDAY"> <label
																								for="FRIDAY"> F </label>
																						</div>
																					</div>
																					<div class="col-md-3">
																						<div class="checkbox clip-check check-primary">
																							<input class="weeklyDays" type="checkbox"
																								id="SATURDAY" value="SATURDAY"> <label
																								for="SATURDAY"> S </label>
																						</div>
																					</div>
																				</div>
																				</div>
							</div>
							</div>
							
							<div id="monthlyDiv" class="recurrenceRepeatDiv" style="display:none;">
								<div class="form-group">
								<label class="col-sm-4 control-label recurrenceLabel"> Repeat every </label>
								<div class="col-sm-8">
									<select id="monthlyRepeat" style="width: 20%;">
									<option	selected="selected" value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									<option value="16">16</option>
									<option value="17">17</option>
									<option value="18">18</option>
									<option value="19">19</option>
									<option value="20">20</option>
									<option value="21">21</option>
									<option value="22">22</option>
									<option value="23">23</option>
									<option value="24">24</option>
									<option value="25">25</option>
									<option value="26">26</option>
									<option value="27">27</option>
									<option value="28">28</option>
									<option value="29">29</option>
									<option value="30">30</option></select>  <label class="recurrenceLabel">months</label>
								</div>
							</div>
							<div class="form-group">
							<label class="col-sm-4 control-label recurrenceLabel"> Repeat by </label>
								<div class="col-sm-8">
									<div class="row">
									<div class="col-md-12">
										<div class="radio clip-radio radio-primary">
											<input type="radio" id="repeatBy1" name="repeatBy" checked="checked"
												value="1"> <label for="repeatBy1"> day of the month </label>
										</div>
									</div>
									<div class="col-md-12">
										<div class="radio clip-radio radio-primary">
											<input type="radio" id="repeatBy2" name="repeatBy"
												value="2"> <label for="repeatBy2"> day of the week </label>
										</div>
									</div>
																				</div>
																				</div>
							</div>
							</div>
							
							<div id="yearlyDiv" class="recurrenceRepeatDiv" style="display:none;">
								<div class="form-group">
								<label class="col-sm-4 control-label recurrenceLabel"> Repeat every </label>
								<div class="col-sm-8">
									<select id="yearlyRepeat" style="width: 20%;">
									<option	selected="selected" value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4">4</option>
									<option value="5">5</option>
									<option value="6">6</option>
									<option value="7">7</option>
									<option value="8">8</option>
									<option value="9">9</option>
									<option value="10">10</option>
									<option value="11">11</option>
									<option value="12">12</option>
									<option value="13">13</option>
									<option value="14">14</option>
									<option value="15">15</option>
									<option value="16">16</option>
									<option value="17">17</option>
									<option value="18">18</option>
									<option value="19">19</option>
									<option value="20">20</option>
									<option value="21">21</option>
									<option value="22">22</option>
									<option value="23">23</option>
									<option value="24">24</option>
									<option value="25">25</option>
									<option value="26">26</option>
									<option value="27">27</option>
									<option value="28">28</option>
									<option value="29">29</option>
									<option value="30">30</option></select>  <label class="recurrenceLabel">years</label>
								</div>
							</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-4 control-label recurrenceLabel"> Starts On </label>
								<div class="col-sm-8">
									<input id="startsOn" type="text" class="form-control" disabled>
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-4 control-label recurrenceLabel"> Ends </label>
								<div class="col-sm-8">
									<div class="row">
										<div class="radio clip-radio radio-primary">
											<input type="radio" id="never" name="recurrenceRepeat"
												value="1" checked="checked"> <label for="never"> Never </label>
										</div>
									</div>
									<div class="row">
										<div class="radio clip-radio radio-primary">
											<input type="radio" id="after" name="recurrenceRepeat"
												value="2"> <label for="after"> After <input id="afterText" type="text" style="width: 20%;" value="4"> Occurences </label>
										</div>
									</div>
									<div class="row">
										<div class="radio clip-radio radio-primary">
											<input type="radio" id="on" name="recurrenceRepeat"
												value="3"> <label for="on"> On <input id="onText" type="text" style="width: 80%;" class="dateTimePicker"> </label>
										</div>
									</div>
								</div>
							</div>
							<!-- <div class="form-group">
								<label class="col-sm-4 control-label recurrenceLabel"> Summary </label>
								<div class="col-sm-8">
									<label class="control-label recurrenceLabel" id="summary"> Daily </label>
								</div>
							</div> -->
						</form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-o" onclick="resetReminder()"
					data-dismiss="modal">Cancel</button>
				<button type="button" class="btn btn-primary btn-o" onclick="setReminder()">Done</button>
			</div>
		</div>
	</div>
</div>
<!--End of pop Reminder Recurrence -->

<!-- pop for delete pop reminder -->
<div class="modal fade" id="deleteReminderModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Delete Reminder Event</h4>
			</div>
			<div class="modal-body">
			<div class="row">
				<div class="col-md-12">
					<label class="recurrenceLabel">Would you like to delete only this event, all events in the series, or this and all future events in the series?</label>			
				</div>
			</div>
				<div class="row">
					<div class="col-xs-3">
						<button type="button" class="btn btn-primary btn-xs" id="reminderIdDeleteBtn"
							onclick="deleteReminder(this)" data-dismiss="modal">Only this instance</button>
					</div>
					<div class="col-xs-9"><label class="recurrenceLabel">All other events in the series will remain.</label></div>
					</div>
				<div class="row">
					<div class="col-xs-3">
						<button type="button" class="btn btn-primary btn-xs" id="followingReminderDeleteBtn"
							onclick="deleteReminder(this)" data-dismiss="modal">All following</button>
					</div>
					<div class="col-xs-9"><label class="recurrenceLabel">This and all the following events will be deleted.</label></div>
				</div>
				<div class="row">
					<div class="col-xs-3">
						<button type="button" class="btn btn-primary btn-xs" id="groupReminderDeleteBtn"
							onclick="deleteReminder(this)" data-dismiss="modal">All event in the series</button>
					</div>
					<div class="col-xs-9"><label class="recurrenceLabel">All events in the series will be deleted.</label></div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-o"
					data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>
<!--End of delete pop reminder -->

<!-- pop for Content List -->
<div class="modal fade" id="viewContentModal" tabindex="-1" role="dialog"
	aria-labelledby="viewContentModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Contents</h4>
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
									<tbody id="contentMasterTableBody">
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
<!--End of pop for Content List -->

<!-- pop for Task Details -->
<div class="modal fade" id="assignWorkModal" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" style="width: 55%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Task Details</h4>
			</div>
			<div class="modal-body">

				<div class="row">
					<div class="col-md-12">
						<form class="form-horizontal" role="form">
							<input type="hidden" id="workStatusId">
							<div class="form-group">
								<label class="col-sm-2 control-label recurrenceLabel">
									Assign To </label>
								<div class="col-sm-4">
									<select id="userTypeMaster"
										class="form-control" disabled>
									</select>
								</div>
								<div class="col-sm-6">
									<select id="users" data-placeholder="Select User"
										class="form-control chosen-select"></select>
								</div>
							</div>

							<div class="form-group">
									<div class="col-md-6 hidden">
										<div class="row">
											<label class="col-sm-4 control-label recurrenceLabel">
												Status </label>
											<div class="col-sm-8">
												<select id="taskStatus" class="form-control">
													<option value="open">Open</option>
													<option value="inprogress">In Progress</option>
													<option value="completed">Completed</option>
													<option value="defered">Deferred</option>
													<option value="canceled">Canceled</option>
												</select>
											</div>
										</div>
									</div>

									<div class="col-md-6 hidden">
										<div class="row">
												<label class="col-sm-4 control-label recurrenceLabel">
													Progress (%) </label>
												<div class="col-sm-8">
													<select id="progress" class="form-control">
														<option value="0">0</option>
														<option value="10">10</option>
														<option value="25">25</option>
														<option value="50">50</option>
														<option value="75">75</option>
														<option value="100">100</option>
													</select>
												</div>
										</div>
									</div>
							</div>

							<div class="form-group hidden">
								<label class="col-sm-2 control-label recurrenceLabel">
									Task Description </label>
								<div class="col-sm-10">
									<textarea placeholder="Task Description"
										style="max-width: 100%;" class="form-control"
										id="taskDescription"></textarea>
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-primary btn-o"
					onclick="" data-dismiss="modal">Cancel</button>
				<button class="btn btn-primary btn-o"
					onclick="assignTask()">Assign</button>
			</div>
		</div>
	</div>
</div>
<!--End of pop Task Details -->

<!-- pop for Verified Report List -->
<div class="modal fade" id="verifiedReportModal" tabindex="-1"
	role="dialog" aria-labelledby="modal" aria-hidden="true">
	<div class="modal-dialog" style="width: 70%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Reports
					<b id="reportModalPatientName"></b>
				</h4>
			</div>
			<div class="modal-body">
			<input type="hidden" id="workVisitId" />
				<div class="row">
					<div class="col-md-12" style="overflow: auto; max-height: 450px;">
						<table class="table no-margin">
							<thead>
								<tr>
									<th class="center">#</th>
									<th>Test Name</th>
									<th>Centre Name</th>
									<th>Uploader</th>
									<th>Upload Date</th>
									<th>Upload Time</th>
									<th></th>
									<th>Action</th>
									<th></th>
								</tr>
							</thead>
							<tbody id="verifiedReportDataAnalysis">
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
<!--End of pop for Verified Report List -->

<!-- pop for Client Status List -->
<div class="modal fade" id="clientStatusModal" tabindex="-1"
	role="dialog" aria-labelledby="modal" aria-hidden="true">
	<div class="modal-dialog" style="width: 70%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Client Status</h4>
			</div>
			<div class="modal-body">
			<input type="hidden" id="workVisitId" />
				<div class="row">
					<div class="col-md-12" style="overflow: auto; max-height: 450px;">
						<table class="table no-margin">
							<thead>
								<tr>
									<th class="center">#</th>
									<th>Client Name</th>
									<th>Client Id</th>
									<th>Visit Id</th>
									<th>Visit Date</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody id="clientStatusBody">
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
<!--End of pop for Client Status List -->


<!-- pop for Client Status All List -->
<div class="modal fade" id="clientStatusModalAll" tabindex="-1"
	role="dialog" aria-labelledby="modal" aria-hidden="true">
	<div class="modal-dialog" style="width: 70%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Client All  Status</h4>
			</div>
			<div class="modal-body">
			<input type="hidden" id="workVisitId" />
				<div class="row">
					<div class="col-md-12" style="overflow: auto; max-height: 450px;">
						<table class="table no-margin">
							<thead>
								<tr>
									<!-- <th class="center">#</th>
									<th>Client Name</th>
									<th>Client Id</th>
									<th>Visit Id</th>
									<th>Action Done On</th>
									<th>Action Done By</th>
									<th>Action Details</th> -->
									
									<th class="center">#</th>
									<th class="col-md-2">Client Name</th>
									<th class="col-md-1">Client Id</th>
									<th class="col-md-1">Visit Id</th>
									<th class="col-md-2">Action Done On</th>
									<th class="col-md-2">Action Done By</th>
									<th class="col-md-4">Action Details</th>
								
								</tr>
							</thead>
							<tbody id="clientStatusBodyAll">
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
<!--End of pop for Client Status All List -->

<!-- pop for contact hub -->
<div class="modal fade" id="contactHubModal" tabindex="-1" role="dialog"
	aria-labelledby="modal" aria-hidden="true">
	<div class="modal-dialog" style="width: 70%;">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Contact Hub( <label style="color: black;" id="cName"></label> )</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-4 col-xs-9">
						<h5 class="text-bold margin-bottom-15">Date Range</h5>
						<div class="input-group input-daterange datepicker">
							<input id="emailStartDate" name="startDate" type="text"
								class="form-control" onchange="assignDate();" /> <span
								class="input-group-addon bg-primary">to</span> <input
								id="emailEndDate" type="text" class="form-control" />
						</div>
					</div>
					<div class="col-md-1 margin-top-32 col-xs-3">
						<button type="button" class="btn btn-info"
							onclick="getEmailAndSmsRecordByDateNew()">Search</button>
					</div>

					<form id="getEmailSmsForm" onchange="getEmailSmsFormNew()">
						<div class="col-md-2 margin-top-32 col-xs-4">
							<div class="form-group">
								<input type="radio" name="contactHubValues" value="all" checked>
								All<br>
							</div>
						</div>

						<div class="col-md-2 margin-top-32 col-xs-3">
							<div class="form-group">
								<input type="radio" name="contactHubValues" value="email">
								Email<br>
							</div>
						</div>

						<div class="col-md-2 margin-top-32 col-xs-3">
							<div class="form-group">
								<input type="radio" name="contactHubValues" value="sms">
								SMS<br>
							</div>
						</div>
					</form>
				</div>

				<div class="panel-scroll ps-container table-responsive mousescroll"
					style="max-height: 280px;">
					<table class="table no-margin" id="sample_1">
						<thead>
							<tr>
								<th>#</th>
								<th>Email/Sms</th>
								<th>Topic</th>
								<th>Sent By</th>
								<th>Sent Date</th>
								<th>View</th>
							</tr>
						</thead>
						<tbody id="contactHubTableBody">

						</tbody>
					</table>
					<div class="ps-scrollbar-x-rail" style="left: 0px; bottom: 3px;">
						<div class="ps-scrollbar-x" style="left: 0px; width: 0px;"></div>
					</div>
					<div class="ps-scrollbar-y-rail" style="top: 0px; right: 3px;">
						<div class="ps-scrollbar-y" style="top: 0px; height: 0px;"></div>
					</div>
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
<!--End of pop for contact hub -->

<style>
.recurrenceLabel{
	font-weight:bold;font-size:14px;color:#333
}
</style>