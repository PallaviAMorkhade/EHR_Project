<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String clientFirstName = (String) session
			.getAttribute("userFirstName");
	session.removeAttribute("uploadVideo");
%>

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
			<div>
				<div class="wrap-content container" id="container">
					<!-- start: DYNAMIC TABLE -->
					<div class="container-fluid container-fullw bg-white">
						<h4>Upload Video Master</h4>
							<div class="row">
							<div id="addVideoDiv" class="col-md-5 col-xs-11" style="margin-bottom:10px;border: 1px solid; padding: 20px; margin-left: 16px;">
								<div class="form-group">
									<label class="control-label"> Video Id <!-- <span
										class="symbol required"></span> -->
									</label> 
									<input value="1" type="text" placeholder="" class="form-control" id="videoId" name="videoId" disabled>
								</div>
								<!-- <div class="form-group">
									<label class="control-label"> Link <span
										class="symbol required"></span>
									</label> <input placeholder="Link"
										class="form-control" id="link" name="link">
								</div> -->
								
	<form method="post" action="../UploadVideo" id="uploadVideoForm"
		enctype="multipart/form-data">
		Select file to upload: <input id="videoFiles" type="file" name="file" required /><br />
		<br /> <input type="submit" value="Upload" class="uploadVideo hidden"></form>
	
								
								<div class="form-group">
									<label class="control-label"> Video Description <span
										class="symbol required"></span>
									</label> <textarea placeholder="Video description"
										class="form-control" id="videoDescription" name="videoDescription" ></textarea>
								</div>
								<div class="form-group">
									<label class="control-label"> Date <span
										class="symbol required"></span></label> <input
										class="form-control datepicker" placeholder="Date"
										id="videoDate" name="videoDate" type="text" >
								</div>
								<span id="videoMasterId" class="hidden"></span>
								<div class="row">
									<div class="col-md-3 col-md-offset-5 col-xs-3 col-xs-offset-5" id="saveVideoBtnDiv">
									<button class="btn btn-primary btn-wide pull-right" onclick="saveVideoMaster()"
										type="button" id="savevideoMasterBtn">
										Save <i class="fa fa-arrow-circle-right"></i>
									</button>
									</div>
									
									<div id="updateVideoBtnDiv" style="display:none;">
									<div class="col-md-3 col-md-offset-3 col-xs-3 col-xs-offset-3">
									<button class="btn btn-primary btn-wide pull-right" onclick="updateVideoMaster()"
										type="button" id="updateVideoMasterBtn">
										Update
									</button>
									</div>
									<div class="col-md-3 col-xs-4" style="margin-left: 30px;">
									<button class="btn btn-primary btn-wide pull-right"
										type="button" id="cancelVideoMasterBtn">
										Cancel
									</button>
									</div>
									</div>
									
								</div>
<%-- </form> --%>
							</div>
			
							<div class="col-md-6 col-xs-12" style="max-height: 450px; overflow-y: auto;">
								<table
									class="table table-striped table-bordered table-hover table-full-width"
									id="sample_1">
									<thead>
										<tr>
											<th>#</th>
											<th>Video</th>
											<th class="userAccessEditTableHeading">Edit</th>
											<th class="userAccessDeleteTableHeading">Delete</th>
										</tr>
									</thead>
									<tbody id="videoMasterTableBody">

									</tbody>
								</table>
							</div>
						</div>				
					</div>
					<!-- end: DYNAMIC TABLE -->
				</div>
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

	<script src="<c:url value="/indus/resources/custom_js/indus_video_master.js"/>"></script>

	<!-- start: JavaScript Event Handlers for this page -->
	<script>
		jQuery(document).ready(function() {
			//Main.init();
			FormElements.init();
			jQuery.noConflict();
		});
	</script>
		
</body>
</html>
