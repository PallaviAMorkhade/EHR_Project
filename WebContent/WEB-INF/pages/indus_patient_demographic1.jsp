<%@page import="com.hms.indus.bo.ClientMaster"%>

<%
	ClientMaster clientMaster=(ClientMaster)request.getAttribute("client");
	String clientFirstName=(String)session.getAttribute("userFirstName");
	Integer count=(Integer)session.getAttribute("count");
	if(count==0){
		%>
<%
	}
%>
<body>
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
											<div class="anchor_width pull-right">
												<div id="wizard" class="swMain">
													<ul class="anchor">
														<li><a href="#step-1" class="selected" isdone="1"
															rel="1">
																<div class="stepNumber">01</div> <span class="stepDesc"><small>
																		Demographics</small></span>
														</a></li>
														<li><a href="#step-2" class="selected" isdone="0"
															rel="2">
																<div class="stepNumber">02</div> <span class="stepDesc">
																	<small> Tests Done </small>
															</span>
														</a></li>
														<li><a href="#step-3" class="selected" isdone="0"
															rel="3">
																<div class="stepNumber">03</div> <span class="stepDesc">
																	<small>Reports Verified</small>
															</span>
														</a></li>
														<li><a href="#step-4" class="disabled" isdone="0"
															rel="4">
																<div class="stepNumber">04</div> <span class="stepDesc">
																	<small> Complete </small>
															</span>
														</a></li>
														<li><a href="#step-4" class="disabled" isdone="0"
															rel="4">
																<div class="stepNumber">05</div> <span class="stepDesc">
																	<small> Coming Soon </small>
															</span>
														</a></li>
													</ul>
												</div>
											</div>
										</div>
										<div class="clearfix padding-5 space5">
											<div class="col-xs-1 text-center no-padding">
												<div class="border-right border-dark">
													<a class="text-dark" href="#"> <i
														class="fa fa-heart-o text-red"></i> 0
													</a>
												</div>
											</div>
											<div class="col-xs-1 text-center no-padding">
												<div class="border-right border-dark">
													<a class="text-dark" href="#"> <i
														class="fa fa-bookmark-o text-green"></i> 0
													</a>
												</div>
											</div>

											<div class="col-xs-1 text-center no-padding">
												<a class="text-dark" href="#"><i
													class="fa fa-comment-o text-azure"></i> 0</a>
											</div>
											<div class="col-xs-4 no-padding">
												<a class="text-dark" href="#">-</a>
											</div>
										</div>
										<div class="tabbable no-margin no-padding">
											<ul class="nav nav-tabs" id="myTab">
												<li class="active padding-top-5 padding-left-5"><a
													data-toggle="tab" href="#panel_overview"> Demographics
												</a></li>
												<li class="padding-top-5"><a data-toggle="tab"
													href="#users_following" onclick="getUploadedReport(<%=clientMaster.getClientId()%>);"> My reports </a></li>
												<li class="padding-top-5" ><a data-toggle="tab"
													href="#hra"> Health Risk Assessment </a></li>
												
												<li class="padding-top-5"><a data-toggle="tab"
													href="#verification"> Verification </a></li>
												<li class="padding-top-5" ><a data-toggle="tab"
													href="#otherDetails"> Other Details </a></li>
												<li class="padding-top-5" ><a data-toggle="tab"
													href="#reminders"> Reminders </a></li>
												<!-- <li class="padding-top-5"><a data-toggle="tab"
													href="#users_following"> Call Log </a></li> -->
											</ul>
											<div class="tab-content">
												<div id="panel_overview" class="tab-pane fade in active">
													<div class="row">
														<div class="col-sm-5 col-md-4">
															<div class="user-left">
																<div class="center">
																	<h4><%=clientMaster.getFirstName() +" "+  clientMaster.getLastName()%></h4>
																	<div class="fileinput fileinput-new"
																		data-provides="fileinput">
																		<div class="user-image">
																			<div class="fileinput-new thumbnail">
																				<img 
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
																				 alt="">
																			</div>
																			<div
																				class="fileinput-preview fileinput-exists thumbnail"></div>
																			<div class="user-image-buttons">
																				<span class="btn btn-azure btn-file btn-sm"><span
																					class="fileinput-new"><i
																						class="fa fa-pencil"></i></span><span
																					class="fileinput-exists"><i
																						class="fa fa-pencil"></i></span> <input type="file">
																				</span> <a href="#"
																					class="btn fileinput-exists btn-red btn-sm"
																					data-dismiss="fileinput"> <i
																					class="fa fa-times"></i>
																				</a>
																			</div>
																		</div>
																	</div>
																	<hr class="profile_hr">
																	<div class="social-icons block">
																		<ul>
																			<li data-placement="top"
																				data-original-title="Twitter"
																				class="social-twitter tooltips"><a
																				href="http://www.twitter.com" target="_blank">
																					Twitter </a></li>
																			<li data-placement="top"
																				data-original-title="Facebook"
																				class="social-facebook tooltips"><a
																				href="http://facebook.com" target="_blank">
																					Facebook </a></li>
																			<li data-placement="top" data-original-title="Google"
																				class="social-google tooltips"><a
																				href="http://google.com" target="_blank">
																					Google+ </a></li>
																			<li data-placement="top"
																				data-original-title="LinkedIn"
																				class="social-linkedin tooltips"><a
																				href="http://linkedin.com" target="_blank">
																					LinkedIn </a></li>
																			<li data-placement="top" data-original-title="Github"
																				class="social-github tooltips"><a href="#"
																				target="_blank"> Github </a></li>
																		</ul>
																	</div>
																	<hr class="profile_hr">
																</div>
																<table class="table table-condensed">

																	<tbody>
																		<tr>
																			<td>First Name</td>
																			<td>
																			<%
																			if(clientMaster.getFirstName()!=null && clientMaster.getFirstName()!="")
																				out.print(clientMaster.getFirstName());
																			else
																				out.print("-");
																				%>
																			
																			</td>
																			<td><a href="#panel_edit_account"
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>Middle Name</td>
																			
																			<td>
																			<%
																			if(clientMaster.getMiddleName()!=null && clientMaster.getMiddleName()!="")
																				out.print(clientMaster.getMiddleName());
																			else
																				out.print("-");
																				%>
																			
																			</td>
																			<td><a href="#panel_edit_account"
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>Last Name</td>
																			<td>
																			<%
																				if(clientMaster.getLastName()!=null && clientMaster.getLastName()!="")
																					out.print(clientMaster.getLastName());
																				else
																					out.print("-");
																			%>
																			</td>
																			<td><a href="#panel_edit_account"
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>Birth/Age</td>
																			<td>
																			<%
																				if(clientMaster.getClientDOB()!=null)
																				{	
																					if(clientMaster.getClientAge()!=null)
																					{
																						out.print(clientMaster.getClientDOB()+" / "+clientMaster.getClientAge());
																					}
																					else
																					{
																						out.print(clientMaster.getClientDOB()+" / -");
																					}
																				}
																				else
																				{
																					if(clientMaster.getClientAge()!=0)
																					{
																						out.print(" / "+clientMaster.getClientAge());
																					}
																					else
																					{
																						out.print(" / "+clientMaster.getClientAge());
																					}
																				}
																			%>
																			</td>
																			<td><a href="#panel_edit_account"
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>Height/Weight/Sex</td>
																			<td>-</td>
																			<td><a href="#panel_edit_account"
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>Blood Group</td>
																			<td>
																			<%
																				if(clientMaster.getBloodGroup()!=null && clientMaster.getBloodGroup()!="")
																					out.print(clientMaster.getBloodGroup());
																				else
																					out.print("-");
																			%>
																			</td>
																			<td><a href="#panel_edit_account"
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																	</tbody>
																</table>

															</div>
														</div>
														<div class="col-sm-5 col-md-4">
															<table class="table">
																<tbody>
																	<tr>
																		<td>Relation with Nominee</td>
																		<td>-</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>First Name</td>
																		<td>-</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>Middle Name</td>
																		<td>-</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>Last Name</td>
																		<td>-</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>Birth/Age</td>
																		<td>-</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																</tbody>
															</table>
															<table class="table">
																<thead>
																	<tr>
																		<th colspan="3">Contact Information</th>
																	</tr>
																</thead>
																<tbody>
																	<tr>
																		<td>Address 1</td>
																		<td>
																		<%
																			if(clientMaster.getAddressLine1()!=null && clientMaster.getAddressLine1()!=null)
																				out.print(clientMaster.getAddressLine1());
																			else
																				out.print("-");
																		%>
																		</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>Address 2</td>
																		<td>
																		<%
																			if(clientMaster.getAddressLine2()!=null && clientMaster.getAddressLine2()!=null)
																				out.print(clientMaster.getAddressLine2());
																			else
																				out.print("-");
																		%>
																		</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>Address 3</td>
																		<td>
																		<%
																			if(clientMaster.getAddressLine3()!=null && clientMaster.getAddressLine3()!="")
																				out.print(clientMaster.getAddressLine3());
																			else
																				out.print("-");
																		%>
																		</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>City</td>
																		<td>
																		<%
																			if(clientMaster.getCityMaster().getCityName()!=null)
																				out.print(clientMaster.getCityMaster().getCityName());
																			else
																				out.print("-");
																		%>
																		</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>State</td>
																		<td>
																		<%
																			if(clientMaster.getStateMaster().getStateName()!=null)
																				out.print(clientMaster.getStateMaster().getStateName());
																			else
																				out.print("-");
																		%>
																		</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>Country</td>
																		<td>
																		<%
																			if(clientMaster.getCountryMaster().getCountryName()!=null)
																				out.print(clientMaster.getCountryMaster().getCountryName());
																		%>
																		</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																	<tr>
																		<td>Pincode</td>
																		<td>
																		<%
																			if(clientMaster.getPinCode()!=0)
																				out.print(clientMaster.getPinCode());
																		%>
																		</td>
																		<td><a href="#panel_edit_account"
																			class="show-tab"><i
																				class="fa fa-pencil edit-user-info"></i></a></td>
																	</tr>
																</tbody>
															</table>
														</div>

														<div class="col-sm-5 col-md-4">
															<!-- <div class="row space20">
																<button type="button"
																	class="btn btn-wide btn-o btn-success">
																	Edit/Update</button>
																<button type="button"
																	class="btn btn-wide btn-o btn-info">Refresh</button>
															</div> -->
															<div class="panel" id="activities">

																<div collapse="activities" ng-init="activities=false"
																	class="panel-wrapper">
																	<!-- <div class="panel-body">
																		<ul class="timeline-xs">
																			<li class="timeline-item success">
																				<div class="margin-left-15">
																					<div class="text-muted text-small">2 minutes
																						ago</div>
																					<p>
																						<a class="text-info" href> Steven </a> has
																						completed his account.
																					</p>
																				</div>
																			</li>
																			<li class="timeline-item">
																				<div class="margin-left-15">
																					<div class="text-muted text-small">12:30</div>
																					<p>Staff Meeting</p>
																				</div>
																			</li>
																			<li class="timeline-item danger">
																				<div class="margin-left-15">
																					<div class="text-muted text-small">11:11</div>
																					<p>Completed new layout.</p>
																				</div>
																			</li>
																			<li class="timeline-item info">
																				<div class="margin-left-15">
																					<div class="text-muted text-small">Thu, 12
																						Jun</div>
																					<p>
																						Contacted <a class="text-info" href> Microsoft
																						</a> for license upgrades.
																					</p>
																				</div>
																			</li>

																		</ul>
																	</div> -->
																</div>
																<!-- <div class="list-group-item list-group-item-success">
																	<b>Well done! </b> Undertaking Uploaded
																	<button type="button" class="btn btn-o btn-success">
																		View</button>
																</div> -->
																<br />
																<table class="table table-condensed">

																	<tbody>
																		<tr>
																			<td>Mobile</td>
																			<td>
																			<%
																				if(clientMaster.getMobNo()!=null)
																					out.print(clientMaster.getMobNo());
																			%>
																			</td>
																			<td><a href="#panel_edit_account"
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>Land Line</td>
																			<td>
																			<%
																				if(clientMaster.getLandlineNo()!=null)
																					out.print(clientMaster.getLandlineNo());
																			%>
																			</td>
																			<td><a href="#panel_edit_account"
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																		<tr>
																			<td>Email</td>
																			<td><a href="">
																			<%
																				if(clientMaster.getEmailId()!=null)
																					out.print(clientMaster.getEmailId());
																			%>
																			</a></td>
																			<td><a href="#panel_edit_account"
																				class="show-tab"><i
																					class="fa fa-pencil edit-user-info"></i></a></td>
																		</tr>
																	</tbody>
																</table>
															</div>

														</div>
													</div>
												</div>
												<div id="users_following" class="tab-pane fade padding-bottom-5">
													<div class="row">
															<div class="col-md-12">
																<div class="col-md-12 space20">
																	<button type="submit" class="btn btn-primary start" data-toggle="modal" data-target="#uploadReportModal">
																		<i class="glyphicon glyphicon-upload"></i>
																		<span>Upload</span>
																	</button>
																	</div>
																	</div>
																</div>
													<div class="panel-scroll height-200 ps-container">
														<table class="table no-margin">
																	<thead>
																		<tr>
																			<th class="center">#</th>
																			<th>Test Name</th>
																			<th class="hidden-xs">Uploader</th>
																			<th>Upload Date</th>
																			<th class="hidden-xs">Upload Time</th>
																			<th>Action</th>
																		</tr>
																	</thead>
															<tbody id="uploadReportData">
																<!-- <tr>
																	<td class="center"><img alt="image"
																		class="img-circle"
																		src="resources/assets/images/avatar-3-small.jpg"></td>
																	<td><span class="text-small block text-light">Visual
																			Designer</span><span>Steven Thompson</span></td>
																	<td class="center">
																		<div class="cl-effect-13">
																			<a href=""> view more </a>
																		</div>
																	</td>
																</tr>
																<tr>
																	<td class="center"><img alt="image"
																		class="img-circle"
																		src="resources/assets/images/avatar-5-small.jpg"></td>
																	<td><span class="text-small block text-light">Senior
																			Designer</span><span>Kenneth Ross</span></td>
																	<td class="center">
																		<div class="cl-effect-13">
																			<a href=""> view more </a>
																		</div>
																	</td>
																</tr>
																<tr>
																	<td class="center"><img alt="image"
																		class="img-circle"
																		src="resources/assets/images/avatar-4-small.jpg"></td>
																	<td><span class="text-small block text-light">Web
																			Editor</span><span>Ella Patterson</span></td>
																	<td class="center">
																		<div class="cl-effect-13">
																			<a href=""> view more </a>
																		</div>
																	</td>
																</tr> -->
																
															</tbody>
														</table>
														<div class="ps-scrollbar-x-rail"
															style="left: 0px; bottom: 3px;">
															<div class="ps-scrollbar-x"
																style="left: 0px; width: 0px;"></div>
														</div>
														<div class="ps-scrollbar-y-rail"
															style="top: 0px; right: 3px;">
															<div class="ps-scrollbar-y"
																style="top: 0px; height: 0px;"></div>
														</div>
													</div>
												</div>
												
												<div id="hra" class="tab-pane fade padding-bottom-5">
													<div class="panel-scroll height-200 ps-container">
														<table class="table no-margin">
															<jsp:include page="indus_coming_soon.jsp"></jsp:include>
															
														</table>
														<div class="ps-scrollbar-x-rail"
															style="left: 0px; bottom: 3px;">
															<div class="ps-scrollbar-x"
																style="left: 0px; width: 0px;"></div>
														</div>
														<div class="ps-scrollbar-y-rail"
															style="top: 0px; right: 3px;">
															<div class="ps-scrollbar-y"
																style="top: 0px; height: 0px;"></div>
														</div>
													</div>
												</div>
												
												
												<div id="verification" class="tab-pane fade padding-bottom-5">
													<div class="panel-scroll height-200 ps-container">
														<table class="table no-margin">
															<jsp:include page="indus_coming_soon.jsp"></jsp:include>
															
														</table>
														<div class="ps-scrollbar-x-rail"
															style="left: 0px; bottom: 3px;">
															<div class="ps-scrollbar-x"
																style="left: 0px; width: 0px;"></div>
														</div>
														<div class="ps-scrollbar-y-rail"
															style="top: 0px; right: 3px;">
															<div class="ps-scrollbar-y"
																style="top: 0px; height: 0px;"></div>
														</div>
													</div>
												</div>
												
												<div id="otherDetails" class="tab-pane fade padding-bottom-5">
													<div class="panel-scroll height-200 ps-container">
														<table class="table no-margin">
															<jsp:include page="indus_coming_soon.jsp"></jsp:include>
															
														</table>
														<div class="ps-scrollbar-x-rail"
															style="left: 0px; bottom: 3px;">
															<div class="ps-scrollbar-x"
																style="left: 0px; width: 0px;"></div>
														</div>
														<div class="ps-scrollbar-y-rail"
															style="top: 0px; right: 3px;">
															<div class="ps-scrollbar-y"
																style="top: 0px; height: 0px;"></div>
														</div>
													</div>
												</div>
												
												<div id="reminders" class="tab-pane fade padding-bottom-5">
													<div class="panel-scroll height-200 ps-container">
														<table class="table no-margin">
															<jsp:include page="indus_coming_soon.jsp"></jsp:include>
															
														</table>
														<div class="ps-scrollbar-x-rail"
															style="left: 0px; bottom: 3px;">
															<div class="ps-scrollbar-x"
																style="left: 0px; width: 0px;"></div>
														</div>
														<div class="ps-scrollbar-y-rail"
															style="top: 0px; right: 3px;">
															<div class="ps-scrollbar-y"
																style="top: 0px; height: 0px;"></div>
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
	
	<script>
		jQuery(document).ready(function() {
			Main.init();
		});
	</script>
	<!-- end: JavaScript Event Handlers for this page -->
	<!-- end: CLIP-TWO JAVASCRIPTS -->
	
</body>
</html>
