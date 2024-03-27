<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Add by Kishor Lokhande For Auto-Suggestion -->

<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/assets/css/bootstrap-chosen.css"/>" />
	
	<script
	src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>">
	</script>
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
			<div class="container-fluid container-fullw ">
				<h4>Add Package Test Relation</h4>
				<div class="row">
					<div id="addPackageDiv" class="col-md-10 col-xs-11"
						style="margin-bottom: 10px; border: 1px solid; padding: 20px; margin-left: 30px;">

						<!-- <div class="form-group">
							<label class="control-label"> Package Name <span
								class="symbol required"></span>
							</label> <select id=packageName class="form-control"></select>
						</div> -->
						
						<div class="form-group">
						<label class="control-label"> Package Name <span
								class="symbol required"></span>
							</label>
									<select id="packageName" data-placeholder="Select User"
										class="form-control chosen-select"></select>
								</div>
								
								<button class="btn btn-primary btn-wide" onclick="getTestFromPackageId()"
										type="button" id="getTestBtn" style="margin-left:370px ">
										Get Tests<i class="fa fa-arrow-circle-right"></i>
									</button>
						
						<span id="unitMasterId" class="hidden"></span>
						

					</div>


				</div>
				<div id="addTestDiv" class="col-md-5 col-xs-11"
					style="margin-bottom: 10px; border: 1px solid; padding: 20px; margin-left: 16px; display: none;">

					<div class="form-group">
									<label class="control-label"> Test Name <span
										class="symbol required"></span>
									</label> 
										<select id=testName class="form-control"></select>
								</div>

					<div class="form-group">
						<label class="control-label"> Test </label> <select
							class="form-control" id="testName" name="testName" multiple>

						</select>
					</div>

					<div class="col-md-3 col-md-offset-5 col-xs-3 col-xs-offset-5"
						id="saveTestBtnDiv">
						<button class="btn btn-primary btn-wide pull-right"
							onclick="saveTestBtn()" type="button"
							id="saveTestBtn">
							Add Test <i class="fa fa-arrow-circle-right"></i>
						</button>
					</div>

				</div>


				<div id="addTestDiv1" class="col-md-5 col-xs-11"
					style="margin-bottom: 10px; border: 1px solid; padding: 20px; margin-left: 16px;display: none;">


					<div class="form-group">
						<label class="control-label"> Test </label> <select
							class="form-control" id="testName1" name="testName" multiple>

						</select>
					</div>



				</div>


			</div>
			<!-- end: DYNAMIC TABLE -->
			<div id="addTestLeftDiv" class="col-md-5 col-xs-11"
				style="margin-bottom: 10px; border: 1px solid; padding: 20px; margin-left: 16px; margin-top: 20px;">
<div class="col-md-5-1 center"> <font size="4"><b>All Tests</b></font></div>
				<table class='table table-bordered' style='width: 100%;'>
					<thead class='cf'>
						<tr>

							<th class='col-md-5-1 center' style='height: 21.5px;'><div
									class='TextFont'>Test Id</div></th>
							<th class='col-md-5-1 center' style='height: 21.5px;'><div
									class='TextFont'>Test Name</div></th>
							<th class='col-md-2-1 center' style='height: 21.5px;'><input
								type='button' value='>>' onclick=''></th>

						</tr>
					</thead>

				</table>

			

			<div class='col-sm-12-1'
				style='height: 220px; width: 100%; overflow-y: scroll; border: 1px solid #ddd; margin-top: -21px;'>

				<table class='table table-striped table-condensed cf'>
					<tbody id="leftDiv">

						

					</tbody>
				</table>
			</div>
		</div>
		
		<div id="addTestRightDiv" class="col-md-5 col-xs-11"
				style="margin-bottom: 10px; border: 1px solid; padding: 20px; margin-left: 16px; margin-top: 20px; ">
<div class="col-md-5-1 center"> <font size="4"><b>Selected Tests</b></font></div>

				<table class='table table-bordered' style='width: 100%;'>
					<thead class='cf'>
						<tr>

							<th class='col-md-5-1 center' style='height: 21.5px;'><div
									class='TextFont'>Test Id</div></th>
							<th class='col-md-5-1 center' style='height: 21.5px;'><div
									class='TextFont'>Test Name</div></th>
							<th class='col-md-2-1 center' style='height: 21.5px;'>
							<button class="btn btn-primary btn-wide pull-right" onclick="saveTestBtn()"
										type="button" id="saveTestBtnnn" style="display: none;">
										Save Test<i class="fa fa-arrow-circle-right"></i>
									</button>
							
							</th>
								
								

						</tr>
					</thead>

				</table>

			

			<div class='col-sm-12-1'
				style='height: 220px; width: 100%; overflow-y: scroll; border: 1px solid #ddd; margin-top: -21px;'>

				<table class='table table-striped table-condensed cf'>
					<tbody id="rightDiv">



					</tbody>
				</table>
			</div>
		</div>

	</div>
		</div>

<script
		src="<c:url value="/indus/resources/vendor/bootstrap/js/bootstrap.min.js"/>"></script>

<!-- Add by Amol Saware For Auto-Suggestion -->
	<script
		src="<c:url value="/indus/resources/assets/js/chosen.jquery.js"/>"></script>
	<script
		src="<c:url value="/indus/resources/vendor/bootstrap/js/bootstrap.min.js"/>"></script>
	
	<%-- <script src="<c:url value="/indus/resources/custom_js/indus_unit_master.js"/>"></script> --%>
	<script src="<c:url value="/indus/resources/custom_js/indus_package_test_relation.js"/>"></script>
	
	
		
	<script>
		jQuery(document).ready(function() {
							//listOfUnitMaster();
						});

	</script>
	
