<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String clientFirstName = (String) session
			.getAttribute("userFirstName");
%>
<body>
	<div id="app">
		<div class="app-content">
			<div>
				<div class="wrap-content container" id="container">
					<!-- start: DYNAMIC TABLE -->
					<div class="container-fluid container-fullw bg-white">
						<h4>Add Sms Template Master</h4>
							<div class="row">
							<div id="addSmsTemplateDiv" class="col-md-5 col-xs-11" style="margin-bottom:10px;border: 1px solid; padding: 20px; margin-left: 16px;">
								<div class="form-group">
									<label class="control-label"> Template Id <!-- <span
										class="symbol required"></span> -->
									</label> 
									<input value="1" type="text" placeholder="" class="form-control" id="smsTemplateId" name="smsTemplateId" disabled>
								</div>
								<div class="form-group">
									<label class="control-label"> Template Name <span
										class="symbol required"></span>
									</label> <input placeholder="Template Name"
										class="form-control" id="smsTemplateName" name="smsTemplateName">
								</div>
								<div class="form-group">
									<label class="control-label"> Template Description <span
										class="symbol required"></span>
									</label> <textarea placeholder="Template description"
										class="form-control" id="smsTemplateDescription" name="smsTemplateDescription"></textarea>
								</div>
								<span id="smsTemplateMasterId" class="hidden"></span>
								<div class="row">
									<div class="col-md-3 col-md-offset-5 col-xs-3 col-xs-offset-5" id="saveSmsTemplateBtnDiv">
									<button class="btn btn-primary btn-wide pull-right" onclick="saveSmsTemplateMaster()"
										type="button" id="saveSmsTemplateMasterBtn">
										Save <i class="fa fa-arrow-circle-right"></i>
									</button>
									</div>
									
									<div id="updateSmsTemplateBtnDiv" style="display:none;">
									<div class="col-md-3 col-md-offset-3 col-xs-3 col-xs-offset-3">
									<button class="btn btn-primary btn-wide pull-right" onclick="updateSmsTemplateMaster()"
										type="button" id="updateSmsTemplateMasterBtn">
										Update
									</button>
									</div>
									<div class="col-md-3 col-xs-4" style="margin-left: 30px;">
									<button class="btn btn-primary btn-wide pull-right"
										type="button" id="cancelSmsTemplateMasterBtn">
										Cancel
									</button>
									</div>
									</div>
									
								</div>

							</div>
							<div class="col-md-6 col-xs-12">
								<table
									class="table table-striped table-bordered table-hover table-full-width"
									id="sample_1">
									<thead>
										<tr>
											<th>#</th>
											<th>Template Name</th>
											<th class="userAccessEditTableHeading">Edit</th>
											<th class="userAccessDeleteTableHeading">Delete</th>
										</tr>
									</thead>
									<tbody id="smsTemplateMasterTableBody">

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
		<script src="<c:url value="/indus/resources/custom_js/indus_sms_template_master.js"/>"></script>
	<script>
		jQuery(document)
				.ready(
						function() {
							/*listOfTemplateMaster();
							 Main.init();
							FormElements.init();
							listOfHraTypeMasterTableBody(); */
						});

	</script>

	<!-- end: JavaScript Event Handlers for this page -->
	<!-- end: CLIP-TWO JAVASCRIPTS -->
</body>
</html>
