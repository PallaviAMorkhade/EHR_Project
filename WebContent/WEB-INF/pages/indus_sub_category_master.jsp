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
						<h4>Add Sub Category Master</h4>
							<div class="row">
							<div id="addSubCategoryMasterDiv" class="col-md-5 col-xs-11" style="margin-bottom:10px;border: 1px solid; padding: 20px; margin-left: 16px;">
								<div class="form-group">
									<label class="control-label">Sub Category Id <!-- <span
										class="symbol required"></span> -->
									</label> 
									<input value="1" type="text" placeholder="" class="form-control" id="subCategoryId" name="subCategoryId" disabled>
								</div>
								<div class="form-group">
									<label class="control-label"> Sub Category <span
										class="symbol required"></span>
									</label> <input type="text" placeholder="Sub Category"
										class="form-control" id="subCategory" name="subCategory">
								</div>
								
								<div class="form-group">
									<label class="control-label"> Category <span
										class="symbol required"></span>
									</label> <select class="form-control" id="categoryMasterSelect"
										name="categoryMasterSelect"></select>
								</div>
										
								<span id="subCategoryMasterId" class="hidden"></span>
								<div class="row">
									<div class="col-md-3 col-md-offset-5 col-xs-3 col-xs-offset-5" id="saveSubCategoryBtnDiv">
									<button class="btn btn-primary btn-wide pull-right" onclick="saveSubCategoryMaster()"
										type="button" id="saveSubCategoryMasterBtn">
										Save <i class="fa fa-arrow-circle-right"></i>
									</button>
									</div>
									
									<div id="updateSubCategoryBtnDiv" style="display:none;">
									<div class="col-md-3 col-md-offset-3 col-xs-3 col-xs-offset-3">
									<button class="btn btn-primary btn-wide pull-right" onclick="saveSubCategoryMaster()"
										type="button" id="updateSubCategoryMasterBtn">
										Update
									</button>
									</div>
									<div class="col-md-3 col-xs-4" style="margin-left: 30px;">
									<button class="btn btn-primary btn-wide pull-right"
										type="button" id="cancelSubCategoryMasterBtn" onclick="clearSubCategoryMaster()">
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
											<th>Sub Category</th>
											<th>Category</th>
											<th class="userAccessEditTableHeading">Edit</th>
											<th class="userAccessDeleteTableHeading">Delete</th>
										</tr>
									</thead>
									<tbody id="subCategoryMasterTableBody">

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
		<script src="<c:url value="/indus/resources/custom_js/indus_sub_category_master.js"/>"></script>
</body>
</html>
