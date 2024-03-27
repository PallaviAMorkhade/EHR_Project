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
					<!-- start: DYNAMIC TABLE -->
					<div class="container-fluid container-fullw bg-white">
						<h4>Add Unit Master</h4>
							<div class="row">
							<div id="addUnitMasterDiv" class="col-md-5 col-xs-11" style="margin-bottom:10px;border: 1px solid; padding: 20px; margin-left: 16px;">
								<div class="form-group">
									<label class="control-label"> Unit Id <!-- <span
										class="symbol required"></span> -->
									</label> 
									<input type="text" placeholder="" class="form-control" id="unitId" name="unitId" disabled>
								</div>
								<div class="form-group">
									<label class="control-label"> Unit Name <span
										class="symbol required"></span>
									</label> <input type="text" placeholder="Unit Name"
										class="form-control" id="unitMasterName" name="unitName">
								</div>
								<span id="unitMasterId" class="hidden"></span>
								<div class="row">
									<div class="col-md-3 col-md-offset-5 col-xs-3 col-xs-offset-5" id="saveUnitBtnDiv">
									<button class="btn btn-primary btn-wide pull-right" onclick="saveUnitMasterBtn()"
										type="button" id="saveUnitMasterBtn">
										Save <i class="fa fa-arrow-circle-right"></i>
									</button>
									</div>
									
									<div id="updateUnitBtnDiv" style="display:none;">
									<div class="col-md-3 col-md-offset-3 col-xs-3 col-xs-offset-3">
									<button class="btn btn-primary btn-wide pull-right" onclick="updateUnitMasterBtn()"
										type="button" id="updateUnitMasterBtn">
										Update
									</button>
									</div>
									<div class="col-md-3 col-xs-4" style="margin-left: 30px;">
									<button class="btn btn-primary btn-wide pull-right"
										type="button" id="cancelUnitMasterBtn">
										Cancel
									</button>
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
											<th>Unit Name</th>
											<th class="userAccessEditTableHeading">Edit</th>
											<th class="userAccessDeleteTableHeading">Delete</th>
										</tr>
									</thead>
									<tbody id="unitMasterTableBody">

									</tbody>
								</table>
							</div>
						</div>				
					</div>
					<!-- end: DYNAMIC TABLE -->
			</div>
		</div>
	</div>
	
	<script src="<c:url value="/indus/resources/custom_js/indus_unit_master.js"/>"></script>
		
	<script>
		jQuery(document).ready(function() {
							//listOfUnitMaster();
						});

	</script>
	
