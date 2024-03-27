<div class="sidebar app-aside" id="sidebar">
			<div class="sidebar-container perfect-scrollbar">
				<nav>
					<!-- start: SEARCH FORM -->
					<%-- <div class="search-form">
						<a class="s-open" href="#"> <i class="ti-search"></i>
						</a>
						<form class="navbar-form" role="search">
							<a class="s-remove" href="#" target=".navbar-form"> <i
								class="ti-close"></i>
							</a>
							<div class="form-group">
								<input type="text" class="form-control" placeholder="Search...">
								<button class="btn search-button" type="submit">
									<i class="ti-search"></i>
								</button>
							</div>
						</form>
					</div> --%>
					<!-- end: SEARCH FORM -->
					<!-- start: MAIN NAVIGATION MENU -->
					<!-- <div class="navbar-title">
						<span>Main Navigation</span>
					</div> -->
					<ul class="main-navigation-menu">
						<li class="menu" id="menu_1" style="display: none;"><a href="homepage">
								<div class="item-content">
									<div class="item-media">
										<i class="ti-home"></i>
									</div>
									<div class="item-inner">
										<span class="title"> Dashboard </span>
									</div>
								</div>
						</a></li>
						<!-- onclick="loadDatabasePage()" -->
						<li class="menu" id="menu_2" style="display: none;"><a href="database">
								<div class="item-content">
									<div class="item-media">
										<i class="ti-pencil-alt"></i>
									</div>
									<div class="item-inner">
										<span class="title"> Database </span>
									</div>
								</div>
						</a></li>
						
						<%
		HttpSession httpSession=request.getSession();
		if((String)httpSession.getAttribute("loginUserType")==null) {
	%>
						<li class="menu" id="menu_40" style="display: none;" onclick="workManagementPage()"><a>
								<div class="item-content">
									<div class="item-media">
										<i class="ti-view-list-alt"></i>
									</div>
									<div class="item-inner">
										<span class="title"> Work Management </span>
									</div>
								</div>
						</a></li>
						
						<%
		}
	%>

				<li style="display: none;" class="3 4 5 6 7 8">
						<a href="javascript:void(0)">
								<div class="item-content">
									<div class="item-media">
										<i class="ti-settings"></i>
									</div>
									<div class="item-inner">
										<span class="title"> Profile </span><i class="icon-arrow"></i>
									</div>
								</div>
						</a>
							<ul class="sub-menu">
								<li style="display: none;" class="menu" id="menu_3" onclick="loadDemoghraphicPage()"><a class="demoghraphicTab"> <span class="title">
											Demographics </span>
								</a></li>
								<li style="display: none;" class="menu" id="menu_4" onclick="loadReportPage()"><a class="myReportTab"> <span class="title">
											Reports </span>
								</a></li>
								<li style="display: none;" class="menu" id="menu_6" onclick="loadHRAPage()"><a class="hraTab"> <span class="title">
											Health Risk Asses. </span>
								</a></li>
								<li style="display: none;" class="menu" id="menu_5" onclick="loadVerificationPage()"><a class="verificationTab"> <span class="title">
											Verification </span>
								</a></li>
								<li style="display: none;" class="menu" id="menu_7" onclick="loadOtherDetailsPage()"><a class="others"> <span class="title">
											Self Upload </span>
								</a></li>
								<li style="display: none;" class="menu" id="menu_8" onclick="loadReminderPage()"><a class="reminders"> <span class="title">
											Reminders </span>
								</a></li>

							</ul></li>
						<li style="display: none;" class="9"><a href="javascript:void(0)">
								<div class="item-content">
									<div class="item-media">
										<i class="ti-layout-grid2"></i>
									</div>
									<div class="item-inner">
										<span class="title"> Data Management </span><i
											class="icon-arrow"></i>
									</div>
								</div>
						</a>
							<ul class="sub-menu">
								<li style="display: none;" class="menu" id="menu_9" onclick="loadDataEntryPage()" id="dataEntry"><a> <span class="title"> Data
											entry </span>
								</a></li>
							</ul></li>
						
						<li style="display: none;" class="10 29">
						<a href="javascript:void(0)">
								<div class="item-content">
									<div class="item-media">
										<i class="ti-settings"></i>
									</div>
									<div class="item-inner">
										<span class="title"> Interaction </span><i class="icon-arrow"></i>
									</div>
								</div>
						</a>
							<ul class="sub-menu">
								<li style="display: none;" class="menu" id="menu_29"><a href="analysis"> <span class="title">
											Analysis </span>
								</a></li>
								<li style="display: none;" class="menu" id="menu_10"><a href="interaction"> <span class="title">
											Engagement </span>
								</a></li>
							</ul></li>
					</ul>
					<!-- end: MAIN NAVIGATION MENU -->
					<!-- start: CORE FEATURES -->
					
					<%
		if((String)httpSession.getAttribute("loginUserType")==null){
	%>
			<div class="navbar-title">
						<span>Core Features</span>
			</div>
	<%
		}
	%>
					
					<ul class="folders">
						<li style="display: none;" class="11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28"><a href="javascript:void(0)">
								<div class="item-content">
									<div class="item-media">
										<i class="fa fa-user-md fa-fw"></i>
									</div>
									<div class="item-inner">
										<span class="title"> Administrator </span><i
											class="icon-arrow"></i>
									</div>
								</div>
						</a>
							<ul class="sub-menu">
								<li style="display: none;" class="11 12"><a href="#"> <span class="title"> User
											Management </span><i class="icon-arrow"></i>
								</a>
									<ul class="sub-menu">
										<li style="display: none;" class="menu" id="menu_11" onclick="loadAccountManagementPage()"><a id="accountManagement"> <span class="title">
													Account Management </span>
										</a></li>  <!-- onclick="loadUserAccessManagementPage()" -->
										<li style="display: none;" class="menu" id="menu_12"><a href="userAccessManagement"> <span class="title">
													User Access Management </span>
										</a></li>
									</ul></li>

								<li style="display: none;" class="13 14"><a href="#"> <span class="title"> HRA
											Management </span><i class="icon-arrow"></i>
								</a>
									<ul class="sub-menu">
										<li style="display: none;" class="menu" id="menu_13" onclick="loadHRATypeMasterPage()"><a id="HRATypeMaster"> <span class="title">
													HRA Type Master </span>
										</a></li>
										<li style="display: none;" class="menu" id="menu_14" onclick="loadQuestionMasterPage()"><a id="HRAQuestionMaster"> <span class="title">
													Question Master </span>
										</a></li>
									</ul></li>

								<li style="display: none;" class="15 16 17"><a href="#"> <span class="title"> Test
											Management </span><i class="icon-arrow"></i>
								</a>
									<ul class="sub-menu">
										<li style="display: none;" class="menu" id="menu_15" onclick="loadParameterMasterPage()" id="parameterMaster"><a> <span class="title">
													Parameter Master </span>
										</a></li>
										<li style="display: none;" class="menu" id="menu_16" onclick="loadCenterWiseParameterMasterPage()" id="centerWiseParameterMaster"><a> <span
												class="title"> Centre Wise Parameter Master </span>
										</a></li>
										<li style="display: none;" class="menu" id="menu_17" onclick="loadUnitMasterPage()" id="unitMaster"><a> <span class="title">
													Unit Master </span>
										</a></li>
										<li style="display: none;" class="menu" id="menu_43" onclick="loadpackageTestRelation()" id="packageTestRelation"><a> <span class="title">
													Package Test Relation </span>
										</a></li>
										
									</ul></li>
								<li style="" class="menu" id="" onclick="loadContactHubPageNew()" ><a> <span class="title">
											Contact Hub </span></a></li>
								<li style="display: none;" class="menu" id="menu_18" onclick="loadRejectMasterPage()" id="rejectMaster"><a> <span class="title">
											Reject master </span></a></li>
								<li style="display: none;" class="19 20" id="templateMaster"><a> <span class="title">
											Template Master </span><i class="icon-arrow"></i></a>
											
											<ul class="sub-menu">
										<li style="display: none;" class="menu" id="menu_19" onclick="loadTemplateMasterPage()"><a > <span class="title">
													Email Template Master </span>
										</a></li>
										<li style="display: none;" class="menu" id="menu_20" onclick="loadSmsTemplateMasterPage()"><a> <span class="title">
													Sms Template Master </span>
										</a></li>
									</ul>
								</li>
								<li style="display: none;" class="menu" id="menu_21" onclick="loadActionMasterPage()" id="actionMaster"><a> <span class="title">
											Action Master</span></a></li></li>
								<li style="display: none;" class="menu" id="menu_22" onclick="loadSubActionMasterPage()" id="subActionMaster"><a> <span class="title">
											Sub Action Master</span></a></li>
								<li style="display: none;" class="menu" id="menu_23" onclick="loadVisitTypeMasterPage()" id="visitTypeMaster"><a> <span class="title">
											Visit Type Master</span></a></li>
								<li style="display: none;" class="menu" id="menu_24" onclick="clientRegistrationPage()" id="clientRegistration"><a> <span class="title">
											Client Registration</span></a></li>
								<li style="display: none;" class="menu" id="menu_30" onclick="clientIntegrationPage()" id="clientIntegration"><a> <span class="title">
											Client Integration</span></a></li>
											
								<li style="display: none;" class="menu" id="menu_39" onclick="analysisCommentMasterPage()" id="categoryMaster"><a> <span class="title">
											Analysis Comment Master</span></a></li>
										
								<li style="display: none;" class="menu" id="menu_32" onclick="bunchUploadPage()" id="bunchUpload"><a> <span class="title">
											Bulk Data Upload</span></a></li>
											
								<li style="display: none;" class="31 33 34 35"><a href="#"> <span class="title"> Content Master </span><i class="icon-arrow"></i>
								</a>
									<ul class="sub-menu">
										<li style="display: none;" class="menu" id="menu_33" onclick="loadCategoryMasterPage()" id="categoryMaster"><a> <span class="title">
											Category Master</span></a></li>
								<li style="display: none;" class="menu" id="menu_34" onclick="loadSubCategoryMasterPage()" id="subCategoryMaster"><a> <span class="title">
											Sub Category Master</span></a></li>
								<li style="display: none;" class="menu" id="menu_31" onclick="contentArticlePage()" id="contentArticle"><a> <span class="title">
											Content Article</span></a></li>
								<li style="display: none;" class="menu" id="menu_35" onclick="contentPreviewPage()" id="contentPreview"><a> <span class="title">
											Content Preview</span></a></li>
									</ul>
								</li>
								
								<li style="display: none;" class="36 37"><a href="#"> <span class="title"> Health Report Master </span><i class="icon-arrow"></i>
								</a>
									<ul class="sub-menu">
										<li style="display: none;" class="menu" id="menu_38" onclick="frequencyMasterPage()" id="frequencyMaster"><a> <span class="title">
											Frequency Master</span></a></li>
										<li style="display: none;" class="menu" id="menu_36" onclick="parameterDetailsPage()" id="parameterDetails"><a> <span class="title">
											Parameter Details</span></a></li>
										<li style="display: none;" class="menu" id="menu_37" onclick="hraDetailsPage()" id="hraDetails"><a> <span class="title">
											HRA Details</span></a></li>
									</ul>
								</li>
											
						<li style="display: none;" class="25 26 27 28"><a href="#"> <span class="title"> Dashboard Master </span><i class="icon-arrow"></i>
								</a>
									<ul class="sub-menu">
										<li style="display: none;" class="menu" id="menu_25" onclick="loadFeedTypeMaster()"><a> <span class="title">
													Feed Type Master </span>
										</a></li>
										<li style="display: none;" class="menu" id="menu_26" onclick="loadHealthFeedMaster()"><a id="healthFeedMaster"> <span class="title">
													Health Feed Master </span>
										</a></li>
										<li style="display: none;" class="menu" id="menu_27" onclick="loadUploadVideoMaster()"><a id="uploadVideoMaster"> <span class="title">
													Upload Video Master </span>
										</a></li>
										<li style="display: none;" class="menu" id="menu_28" onclick="loadLinkMasterPage()"><a id="linkMaster"> <span class="title">
													Link Master </span>
										</a></li>
									</ul>
									
									</li>
											
											</ul>
					</ul>
					<!-- end: CORE FEATURES -->
				</nav>
			</div>
		</div>