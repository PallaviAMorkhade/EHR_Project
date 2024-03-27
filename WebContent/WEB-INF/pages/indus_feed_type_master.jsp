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
						<h4>Add Feed Type Master</h4>
							<div class="row">
							<div id="addFeedTypeMasterDiv" class="col-md-5 col-xs-11" style="margin-bottom:10px;border: 1px solid; padding: 20px; margin-left: 16px;">
								<div class="form-group">
									<label class="control-label"> Feed Type Id <!-- <span
										class="symbol required"></span> -->
									</label> 
									<input type="text" value="1" placeholder="" class="form-control" id="feedTypeId" name="feedTypeId" disabled>
								</div>
								<div class="form-group">
									<label class="control-label"> Feed Type <span
										class="symbol required"></span>
									</label> <input type="text" placeholder="Feed Type"
										class="form-control" id="feedTypeMaster" name="feedType">
								</div>
								<span id="feedTypeMasterId" class="hidden"></span>
								<div class="row">
									<div class="col-md-3 col-md-offset-5 col-xs-3 col-xs-offset-5" id="saveFeedTypeBtnDiv">
									<button class="btn btn-primary btn-wide pull-right" onclick="saveFeedTypeMasterBtn()"
										type="button" id="saveFeedTypeMasterBtn">
										Save <i class="fa fa-arrow-circle-right"></i>
									</button>
									</div>
									
									<div id="updateFeedTypeBtnDiv" style="display:none;">
									<div class="col-md-3 col-md-offset-3 col-xs-3 col-xs-offset-3">
									<button class="btn btn-primary btn-wide pull-right" onclick="updateFeedTypeMasterBtn()"
										type="button" id="updateFeedMasterBtn">
										Update
									</button>
									</div>
									<div class="col-md-3 col-xs-4" style="margin-left: 30px;">
									<button class="btn btn-primary btn-wide pull-right"
										type="button" id="cancelFeedTypeMasterBtn">
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
											<th>Feed Type </th>
											<th class="userAccessEditTableHeading">Edit</th>
											<th class="userAccessDeleteTableHeading">Delete</th>
										</tr>
									</thead>
									<tbody id="feedTypeMasterTableBody">

									</tbody>
								</table>
							</div>
						</div>				
					</div>
					<!-- end: DYNAMIC TABLE -->
			</div>
		</div>
	</div>
	
	<script src="<c:url value="/indus/resources/custom_js/indus_feed_type_master.js"/>"></script>	
