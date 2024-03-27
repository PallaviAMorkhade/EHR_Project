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
						<h4>Add Sub Action Master</h4>
							<div class="row">
							<div id="addSubActionMasterDiv" class="col-md-5 col-xs-11" style="margin-bottom:10px;border: 1px solid; padding: 20px; margin-left: 16px;">
								<div class="form-group">
									<label class="control-label">Sub Action Id <!-- <span
										class="symbol required"></span> -->
									</label> 
									<input value="1" type="text" placeholder="" class="form-control" id="subActionId" name="subActionId" disabled>
								</div>
								<div class="form-group">
									<label class="control-label"> Sub Action <span
										class="symbol required"></span>
									</label> <input type="text" placeholder="Sub Action"
										class="form-control" id="subAction" name="subAction">
								</div>
								
								<div class="form-group">
									<label class="control-label"> Action <span
										class="symbol required"></span>
									</label> <select class="form-control" id="actionMasterSelect"
										name="actionMasterSelect"></select>
								</div>
										
								<span id="subActionMasterId" class="hidden"></span>
								<div class="row">
									<div class="col-md-3 col-md-offset-5 col-xs-3 col-xs-offset-5" id="saveSubActionBtnDiv">
									<button class="btn btn-primary btn-wide pull-right" onclick="saveSubActionMaster()"
										type="button" id="saveSubActionMasterBtn">
										Save <i class="fa fa-arrow-circle-right"></i>
									</button>
									</div>
									
									<div id="updateSubActionBtnDiv" style="display:none;">
									<div class="col-md-3 col-md-offset-3 col-xs-3 col-xs-offset-3">
									<button class="btn btn-primary btn-wide pull-right" onclick="updateSubActionMaster()"
										type="button" id="updateSubActionMasterBtn">
										Update
									</button>
									</div>
									<div class="col-md-3 col-xs-4" style="margin-left: 30px;">
									<button class="btn btn-primary btn-wide pull-right"
										type="button" id="cancelSubActionMasterBtn">
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
											<th>Sub Action</th>
											<th>Action</th>
											<th class="userAccessEditTableHeading">Edit</th>
											<th class="userAccessDeleteTableHeading">Delete</th>
										</tr>
									</thead>
									<tbody id="subActionMasterTableBody">

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
		<script src="<c:url value="/indus/resources/custom_js/indus_sub_action_master.js"/>"></script>
</body>
</html>
