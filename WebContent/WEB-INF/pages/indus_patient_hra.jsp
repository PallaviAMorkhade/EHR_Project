<%@page import="com.hms.indus.bo.ClientMaster"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	ClientMaster clientMaster=(ClientMaster)request.getAttribute("client");
	String clientFirstName=(String)session.getAttribute("userFirstName");
%>
<body>
	<div id="app">
		<!-- sidebar -->
			<%-- <jsp:include page="indus_header.jsp"></jsp:include> --%>
		<!-- / sidebar -->
		<div class="app-content">
			<!-- start: TOP NAVBAR -->
				<%-- <jsp:include page="indus_left_menu.jsp"></jsp:include> --%>
			<!-- end: TOP NAVBAR -->
			<!-- <div class="main-content"> -->
				<div class="wrap-content container" id="container">
					
					<!-- start: USER PROFILE -->
					<div class="container-fluid container-fullw bg-white">
						<div class="row">
							<div class="col-md-12">

								<div class="panel panel-white no-radius">

									<div class="panel-body no-padding">
										<div class="padding-10"> 
											<img width="50" height="50" 
											<%
												if(clientMaster.getPhotoUrl()!=null && clientMaster.getPhotoUrl()!="")
												{
											%>
													src="readImage?url=<%=clientMaster.getPhotoUrl() %>"
													
												<%
												}
												else
												{
													%>
													src="resources/assets/images/default-user.png"
													<%
												}
													%>	
												class="img-circle pull-left" alt="">
											<h4 class="no-margin inline-block padding-5">
												<%=clientMaster.getFirstName() +" "+  clientMaster.getLastName()%>
												<span class="block text-small text-left"><center><b>-</b></center></span>
											</h4>
											
											<%
		HttpSession httpSession=request.getSession();
		if((String)httpSession.getAttribute("loginUserType")!=null){
			%>
			<script>
				$("#hraPrintBtnDiv").hide();
			</script>
			<%
		}
		else{
	%>
											
											<div class="anchor_width pull-right interactionTabHide timeline" style="display:none;">
												<div id="wizard" class="swMain">
													<ul class="anchor">
														<li><a id="step-1" href="#step-1" class="selected" isdone="1"
															rel="1">
																<div class="stepNumber">01</div> <span class="stepDesc"><small>
																		Demographics</small></span>
														</a></li>
														<li><a id="step-2" href="#step-2" class="" isdone="0"
															rel="2">
																<div class="stepNumber">02</div> <span class="stepDesc">
																	<small> Tests Done </small>
															</span>
														</a></li>
														<li><a id="step-3" href="#step-3" class="" isdone="0"
															rel="3">
																<div class="stepNumber">03</div> <span class="stepDesc">
																	<small>Reports Verified</small>
															</span>
														</a></li>
														<li><a id="step-4" href="#step-4" class="" isdone="0"
															rel="4">
																<div class="stepNumber">04</div> <span class="stepDesc">
																	<small> Complete </small>
															</span>
														</a></li>
														<li><a id="step-5" href="#step-5" class="" isdone="0"
															rel="5">
																<div class="stepNumber">05</div> <span class="stepDesc">
																	<small> Coming Soon </small>
															</span>
														</a></li>
													</ul>
												</div>
											</div>
											
											<%
		}
	%>
											
										</div>
										<div class="clearfix padding-5 space5">
											<!-- <div class="col-xs-1 text-center no-padding">
												<div class="border-right border-dark">
													<a class="text-dark" href="#"> <i
														class="fa fa-heart-o text-red"></i> 0
													</a>
												</div>
											</div>
											<div class="col-xs-1 text-center no-padding">
												<div class="border-right border-dark">
													<a class="text-dark" href="#"> <i
														class="fa fa-bookmark-o text-green"></i> <span id="emailCount">0</span>
													</a>
												</div>
											</div>

											<div class="col-xs-1 text-center no-padding">
												<a class="text-dark" href="#"><i
													class="fa fa-comment-o text-azure"></i> <span id="smsCount">0</span></a>
											</div> -->
											<div class="col-xs-3 no-padding">
												Package Name: <span class="text-dark packageNameSpan"></span>
											</div>
											<div class="col-xs-3 no-padding hidden">
												<span class="text-dark centerNameSpan"></span>
											</div>
											<div class="col-xs-2 no-padding">
												Check Up Date: <span class="text-dark lastCheckUpDate"></span>
											</div>

										<div class="col-md-2 col-sm-6 col-xs-12">
											<select class="form-control center"
												id="visitSelectOnMyReportPage"
												onchange="changeVisitSelectList(this)" style="height: 25px; padding: 3px; margin-bottom: -7px;">
											</select>
										</div>
										<div class="col-md-2 col-sm-6 col-xs-12">
											<input id="showVisitDateOnMyReportPage" style="height: 25px; padding: 3px; margin-bottom: -7px;"
												class="form-control center" type="text" disabled>
										</div>
										<div class="col-md-2 col-sm-6 col-xs-12">
											<select class="form-control center" style="height: 25px; padding: 3px; margin-bottom: -7px;"
												id="visitTypeMasterSelectOnMyReportPage" disabled>
											</select>
										</div>
										</div>
										<div class="tabbable no-margin no-padding">
											<ul class="nav nav-tabs" id="myTab">
												<li onclick="loadDemoghraphicPage()" class="padding-top-5 padding-left-5 demoghraphicTab menu_3" style="display:none;"><a
													data-toggle="tab" href="#panel_overview"> Demographics
												</a></li>
												<li onclick="loadReportPage()" class="padding-top-5 myReportTab menu_4" style="display:none;"><a data-toggle="tab"
													href="#users_following"> Reports </a></li>
												<li onclick="loadHRAPage()" class="active padding-top-5 hraTab menu_6" style="display:none;" ><a data-toggle="tab"
													href="#hra"> HRA </a></li>
												<li onclick="loadVerificationPage()" class="interactionTabHide padding-top-5 verificationTab menu_5" style="display:none;"><a data-toggle="tab"
													href="#verification"> Verification </a></li>
												<li onclick="loadOtherDetailsPage()" class="padding-top-5 menu_7" style="display:none;"><a data-toggle="tab"
													href="#otherDetails"> Self Upload </a></li>
												<li onclick="loadReminderPage()" class="padding-top-5 menu_8" style="display:none;"><a data-toggle="tab"
													href="#reminders"> Reminders </a></li>
												<li onclick="loadFollowUpPage()" class="padding-top-5 interactionTabShow" style="display:none;"><a data-toggle="tab"
													href="#followUpRecord"> Follow Up Record </a></li>
												<li onclick="loadContactHubPage()" class="padding-top-5 interactionTabShow" style="display:none;"><a data-toggle="tab"
													href="#contactHub"> Contact Hub </a></li>
												<li onclick="loadDataEntryPage()" class="padding-top-5 interactionTabShow" style="display:none;"><a data-toggle="tab"
													href="#dataEntry"> Data Entry </a></li>
												<!-- <li class="padding-top-5"><a data-toggle="tab"
													href="#users_following"> Call Log </a></li> -->
											</ul>
											<div class="tab-content">
											
												<div id="hra1" class="tab-pane fade padding-bottom-5 in active" style="max-height: 280px;">
														<div id="hraPrintBtnDiv" class="row">
														<input type="hidden" id="preCounsellingCount" value="0">
														<div class="col-md-7 pull-right space20">
																<!-- <a id="preCouncellingBtn" class="btn btn-primary btn-sm" onclick="preCouncelling()">
																	<i class="glyphicon glyphicon-duplicate"></i> <span>Pre-Counselling</span>
																</a> -->
																
							<div class="row">
					
						<div class="col-md-3 col-xs-4">
							<input type="radio" checked="" value="hra" name="hraType" onclick="preCouncelling()"> HRA With Visit<br>
						</div>
						<div class="col-md-4 col-xs-4">
							<input type="radio" value="preCounselling" name="hraType" onclick="preCouncelling()"> HRA Without Visit <br>
						</div>
            <div class="col-md-3 col-xs-4">
							<a id="hraPrintBtn" class="btn btn-primary btn-sm" target="_blank" href="">
																	<i class="glyphicon glyphicon-print"></i> <span>Print</span>
																</a>
						</div>
				</div>
																
																<!-- <a id="hraPrintBtn" class="btn btn-primary btn-sm" target="_blank" href="">
																	<i class="glyphicon glyphicon-print"></i> <span>Print</span>
																</a> -->
														</div>
														
														<div class="col-md-offset-2 col-md-3 col-sm-6 col-xs-12">
														
														<div class="pull-right">
																<a class="btn btn-primary btn-sm" onclick="copyHRAFromVisit()">
																	<i class="glyphicon glyphicon-duplicate"></i> <span>Copy From HRA</span>
																</a>
																<a id="mergePreCouncellingBtn" class="btn btn-primary btn-sm">
																	<span>Merge</span>
																</a>
														</div>
														</div>
														
														</div>
														
														<div class="row">
															<div class="col-md-9 col-sm-9 col-xs-12 mousescroll" id="hraQuestionDiv" style="max-height: 350px;">
														
															</div>
															<div class="col-md-3 col-sm-3 col-xs-12 mousescroll" id="hraHistoryBtnDiv" style="max-height: 350px;">
																
															</div>
														</div>
												</div>
												
											</div>
										</div>
									</div>
								</div>

							</div>


						</div>
					</div>
			</div>
		</div>
	</div>
	
	<script
		src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>"></script>
	<script src="<c:url value="/indus/resources/custom_js/indus_hra.js"/>"></script>
	
	<!-- start: JavaScript Event Handlers for this page -->
	<script>
		jQuery(document).ready(function() {
			
			//$('#hraPrintBtn').attr('href','hra/hraPrintPage?clientId='+$("#clientMasterId").html());
			
			//Main.init();
			/* listOfHraTypeMaster();
			getListOfQuestionClientHRADetails();
			getClientCenterPackageByUserId($('#clientMasterId').html()); */
			jQuery.noConflict();
			
			//for handling question modal hide
			$(document).on('hide.bs.modal','#questionModal', function () {
				preCouncelling();
			});
			
		});
	</script>
	<!-- end: JavaScript Event Handlers for this page -->
</body>
</html>
