<header class="navbar navbar-default navbar-static-top">
				<!-- start: NAVBAR HEADER -->
				<div class="navbar-header">
					<a href="#"
						class="sidebar-mobile-toggler pull-left hidden-md hidden-lg"
						class="btn btn-navbar sidebar-toggle"
						data-toggle-class="app-slide-off" data-toggle-target="#app"
						data-toggle-click-outside="#sidebar"> <i
						class="ti-align-justify"></i>
					</a> <a class="navbar-brand" href="homepage"> <img
						height="60px" src="resources/assets/images/indus-logo-new2.png">
					</a> <a href="#"
						class="sidebar-toggler pull-right visible-md visible-lg"
						data-toggle-class="app-sidebar-closed" data-toggle-target="#app">
						<i class="ti-align-justify"></i>
					</a> <a class="pull-right menu-toggler visible-xs-block"
						id="menu-toggler" data-toggle="collapse" href=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <i
						class="ti-view-grid"></i>
					</a>
				</div>
				<!-- end: NAVBAR HEADER -->
				<!-- start: NAVBAR COLLAPSE -->
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-right">
						<!-- start: MESSAGES DROPDOWN -->
						<li class="dropdown">
						<%
		HttpSession httpSession=request.getSession();
		if((String)httpSession.getAttribute("loginUserType")!=null){
	%>
		<a target="popup"
							class="dropdown-toggle" data-target="#myModal"
							data-toggle="modal"> <span class="dot-badge partition-red"></span>
								<i class="ti-comment"></i> <span>Feedback</span>
						</a>
	<%
		}
	%></li>
	 <!-- start: LANGUAGE SWITCHER --> <!-- start: USER OPTIONS DROPDOWN -->
						<li class="dropdown current-user"><a href
							class="dropdown-toggle" data-toggle="dropdown"> <span
								class="username"><%=session.getAttribute("userName") %> <i
									class="ti-angle-down"></i></span>
						</a>
							<ul class="dropdown-menu dropdown-dark">
								<!--<li onclick="loadDemoghraphicPage()"><a> Profile </a></li>
								<li><a href=""> My Calendar </a></li>
								<li><a hef=""> My Messages (3) </a></li> -->
								<li><a target="popup" data-target="#changePasswordModal"
									data-toggle="modal"> Change Password </a></li>
								<!-- <li><a href=""> Lock Screen </a></li> -->
								<li><a href="logout"> Log Out </a></li>
							</ul></li>
						<!-- end: USER OPTIONS DROPDOWN -->
					</ul>

					<div class="col-sm-7 padding_zero">
							<!-- <h1 class="mainTitle">Indus EHR know your health the smart way</h1> -->
							<!-- <font style="font-size: 27px; color: rgb(210, 35, 42);">Indus EHR</font> -->
							<!-- <strong	style="font-size: 20px; color: rgb(245, 131, 69);"> Samajhkar Jiyo Behtar </strong> -->
							<img height="49px" src="resources/assets/images/indus samajhkar jiyo behtar logo.png" style="margin-left: -15px;">
					</div>
					<!-- start: MENU TOGGLER FOR MOBILE DEVICES -->
					<div class="close-handle visible-xs-block menu-toggler"
						data-toggle="collapse" href=".navbar-collapse">
						<div class="arrow-left"></div>
						<div class="arrow-right"></div>
					</div>
					<!-- end: MENU TOGGLER FOR MOBILE DEVICES -->
				</div>
				<a class="dropdown-off-sidebar" style="display:none;"
					data-toggle-class="app-offsidebar-open" data-toggle-target="#app"
					data-toggle-click-outside="#off-sidebar"> &nbsp; </a>
				<!-- end: NAVBAR COLLAPSE -->
			</header>