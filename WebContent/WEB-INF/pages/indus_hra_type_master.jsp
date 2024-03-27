<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String clientFirstName = (String) session.getAttribute("userFirstName");
%>
<body>
	<div id="app">
		<div class="app-content">
			<div>
				<div class="wrap-content container" id="container">
					<!-- start: DYNAMIC TABLE -->
					<div class="container-fluid container-fullw bg-white">
						<h4>Add HRA Type Master</h4>
						<div class="row">
							<div id="addHraTypeMasterDiv" class="col-md-5 col-xs-11"
								style="margin-bottom: 10px; border: 1px solid; padding: 20px; margin-left: 16px; display: none;">
								<div class="form-group">
									<label class="control-label"> HRA Type Id <!-- <span
										class="symbol required"></span> -->
									</label> <input type="text" placeholder="" class="form-control"
										id="HRATypeId" name="HRATypeId" disabled>
								</div>
								<div class="form-group">
									<label class="control-label"> HRA Type Name <span
										class="symbol required"></span>
									</label> <input type="text" placeholder="HRA Type Name"
										class="form-control" id="hraTypeMasterName" name="hraTypeName">
								</div>
								<div class="form-group">
									<label class="control-label"> Print Flag </label> <select
										class="form-control" id="printFlag">
										<option value="Y">Enable</option>
										<option value="N">Disable</option>
									</select>
								</div>
								<div class="form-group">
									<label class="control-label"> Show To End User Flag </label> <select
										class="form-control" id="endUserFlag">
										<option value="Y">Enable</option>
										<option value="N">Disable</option>
									</select>
								</div>
								<span id="hraTypeMasterId" class="hidden"></span>
								<div class="row">
									<div class="col-md-3 col-md-offset-5 col-xs-3 col-xs-offset-5"
										id="saveHRABtnDiv">
										<button class="btn btn-primary btn-wide pull-right"
											onclick="saveHRATypeMasterBtn()" type="button"
											id="saveHRATypeMasterBtn">
											Save <i class="fa fa-arrow-circle-right"></i>
										</button>
									</div>

									<div id="updateHRABtnDiv" style="display: none;">
										<div class="col-md-3 col-md-offset-3 col-xs-3 col-xs-offset-3">
											<button class="btn btn-primary btn-wide pull-right"
												onclick="updateHRATypeMasterBtn()" type="button"
												id="updateHRATypeMasterBtn">Update</button>
										</div>
										<div class="col-md-3 col-xs-4" style="margin-left: 30px;">
											<button class="btn btn-primary btn-wide pull-right"
												type="button" id="cancelHRATypeMasterBtn">Cancel</button>
										</div>
									</div>
								</div>

							</div>
							<div class="col-md-6 col-xs-12" style="max-height: 480px; overflow-y: scroll;">
								<table
									class="table table-striped table-bordered table-hover table-full-width">
									<thead>
										<tr>
											<th class="center">#</th>
											<th>HRA Type Master Name</th>
											<th style="width: 10%;">Print Flag</th>
											<th style="width: 15%;">End User Flag</th>
											<th style="width: 10%;" class="userAccessEditTableHeading">Edit</th>
											<th style="width: 10%;" class="userAccessDeleteTableHeading">Delete</th>
										</tr>
									</thead>
									<tbody id="hraTypeMasterTableBody">
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

	<script src="<c:url value="/indus/resources/custom_js/indus_hra.js"/>"></script>
</body>
</html>
