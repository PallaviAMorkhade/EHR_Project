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
						<h4>Add Frequency Master</h4>
							<div class="row">
							<div id="addFrequencyMasterDiv" class="col-md-5 col-xs-11" style="margin-bottom:10px;border: 1px solid; padding: 20px; margin-left: 16px;">
								<div class="form-group">
									<label class="control-label"> Frequency Id <!-- <span
										class="symbol required"></span> -->
									</label> 
									<input value="1" type="text" placeholder="" class="form-control" id="frequencyId" disabled>
								</div>
								<div class="form-group">
									<label class="control-label"> Frequency <span
										class="symbol required"></span>
									</label> <input type="text" placeholder="Frequency"
										class="form-control" id="frequency">
								</div>
								<span id="frequencyMasterId" class="hidden"></span>
								<div class="row">
									<div class="col-md-3 col-md-offset-5 col-xs-3 col-xs-offset-5" id="saveFrequencyBtnDiv">
									<button class="btn btn-primary btn-wide pull-right" onclick="saveFrequencyMaster()"
										type="button" id="saveFrequencyMasterBtn">
										Save <i class="fa fa-arrow-circle-right"></i>
									</button>
									</div>
									
									<div id="updateFrequencyBtnDiv" style="display:none;">
									<div class="col-md-3 col-md-offset-3 col-xs-3 col-xs-offset-3">
									<button class="btn btn-primary btn-wide pull-right" onclick="saveFrequencyMaster()"
										type="button" id="updateFrequencyMasterBtn">
										Update
									</button>
									</div>
									<div class="col-md-3 col-xs-4" style="margin-left: 30px;">
									<button class="btn btn-primary btn-wide pull-right"
										type="button" id="cancelFrequencyMasterBtn" onclick="clearFrequencyMaster()">
										Cancel
									</button>
									</div>
									</div>
									
								</div>

							</div>
							<div class="col-md-6 col-xs-12" style="max-height: 450px; overflow-y: auto;">
								<table
									class="table table-striped table-bordered table-hover table-full-width"
									id="sample_1">
									<thead>
										<tr>
											<th class="center">#</th>
											<th>Category</th>
											<th class="userAccessEditTableHeading">Edit</th>
											<th class="userAccessDeleteTableHeading">Delete</th>
										</tr>
									</thead>
									<tbody id="frequencyMasterTableBody">
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
		
	<script src="<c:url value="/indus/resources/custom_js/indus_frequency_master.js"/>"></script>
</body>
</html>
