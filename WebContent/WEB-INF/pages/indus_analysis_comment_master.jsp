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
						<!-- <h4>Add Analysis Comment Master</h4> -->

						<div class="row">
							<div id="addAnalysisCommentMasterDiv" class="col-sm-6">
								<div class="panel panel-white" id="panel1">
									<div class="panel-heading border-light">
										<h4 class="panel-title text-primary">Add Analysis Comment</h4>
									</div>
									<div class="panel-body" style="display: block;">
										<div class="form-group">
											<label class="control-label"> Comment Id <!-- <span
										class="symbol required"></span> -->
											</label> <input value="1" type="text" placeholder=""
												class="form-control" id="commentId" disabled>
										</div>
										<div class="form-group">
											<label class="control-label"> Comment <span
												class="symbol required"></span>
											</label> <input type="text" placeholder="Comment"
												class="form-control" id="comment">
										</div>
										<span id="commentMasterId" class="hidden"></span>
										<div class="row">
											<div
												class="col-md-3 col-md-offset-5 col-xs-3 col-xs-offset-5"
												id="saveCommentBtnDiv">
												<button class="btn btn-primary btn-wide pull-right"
													onclick="saveCommentMaster()" type="button"
													id="saveCommentMasterBtn">
													Save <i class="fa fa-arrow-circle-right"></i>
												</button>
											</div>

											<div id="updateCommentBtnDiv" style="display: none;">
												<div
													class="col-md-3 col-md-offset-3 col-xs-3 col-xs-offset-3">
													<button class="btn btn-primary btn-wide pull-right"
														onclick="saveCommentMaster()" type="button"
														id="updateCommentMasterBtn">Update</button>
												</div>
												<div class="col-md-3 col-xs-4" style="margin-left: 30px;">
													<button class="btn btn-primary btn-wide pull-right"
														type="button" id="cancelCommentMasterBtn"
														onclick="clearCommentMaster()">Cancel</button>
												</div>
											</div>

										</div>
									</div>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="panel panel-white load1" id="panel2">
									<div class="panel-heading border-light">
										<h4 class="panel-title text-primary">Comments</h4>
									</div>
									<div class="panel-body">
										<div style="max-height: 450px; overflow-y: auto;">
											<table
												class="table table-striped table-bordered table-hover table-full-width"
												id="sample_1">
												<thead>
													<tr>
														<th class="center">#</th>
														<th>Comment</th>
														<th class="userAccessEditTableHeading">Edit</th>
														<th class="userAccessDeleteTableHeading">Delete</th>
													</tr>
												</thead>
												<tbody id="commentMasterTableBody">
												</tbody>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
					<!-- end: DYNAMIC TABLE -->

				</div>
			</div>
		</div>

	</div>

	<script
		src="<c:url value="/indus/resources/custom_js/indus_analysis_comment_master.js"/>"></script>
</body>
</html>
