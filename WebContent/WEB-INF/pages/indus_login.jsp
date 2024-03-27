<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<!-- Template Name: Clip-Two - Responsive Admin Template build with Twitter Bootstrap 3.x | Author: ClipTheme -->
<!--[if IE 8]><html class="ie8" lang="en"><![endif]-->
<!--[if IE 9]><html class="ie9" lang="en"><![endif]-->
<!--[if !IE]><!-->
<html lang="en">
<!--<![endif]-->
<!-- start: HEAD -->
<!-- start: HEAD -->
<head>
<title>Login</title>
<!-- start: META -->
<!--[if IE]><meta http-equiv='X-UA-Compatible' content="IE=edge,IE=9,IE=8,chrome=1" /><![endif]-->
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta content="" name="description" />
<meta content="" name="author" />
<!-- end: META -->
<!-- start: GOOGLE FONTS -->
<link
	href="http://fonts.googleapis.com/css?family=Lato:300,400,400italic,600,700|Raleway:300,400,500,600,700|Crete+Round:400italic"
	rel="stylesheet" type="text/css" />
<!-- end: GOOGLE FONTS -->
<!-- start: MAIN CSS -->
<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/vendor/bootstrap/css/bootstrap.min.css"/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/vendor/fontawesome/css/font-awesome.min.css"/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/vendor/themify-icons/themify-icons.min.css"/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/vendor/animate.css/animate.min.css"/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/vendor/perfect-scrollbar/perfect-scrollbar.min.css"/>"
	media="screen">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/vendor/switchery/switchery.min.css"/>"
	media="screen">

<!-- end: MAIN CSS -->
<!-- start: CLIP-TWO CSS -->

<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/assets/css/styles.css"/>">

<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/assets/css/plugins.css"/>">

<!-- <link rel="stylesheet" href="assets/css/themes/theme-1.css" id="skin_color" /> -->

<link rel="stylesheet" type="text/css"
	href="<c:url value="/indus/resources/assets/css/themes/theme-1.css"/>">

<!-- start: MAIN JAVASCRIPTS -->

<script
	src="<c:url value="/indus/resources/vendor/jquery/jquery.min.js"/>"></script>

<script
	src="<c:url value="/indus/resources/vendor/bootstrap/js/bootstrap.min.js"/>"></script>

<script
	src="<c:url value="/indus/resources/vendor/modernizr/modernizr.js"/>"></script>

<script
	src="<c:url value="/indus/resources/vendor/jquery-cookie/jquery.cookie.js"/>"></script>

<script
	src="<c:url value="/indus/resources/vendor/perfect-scrollbar/perfect-scrollbar.min.js"/>"></script>

<script
	src="<c:url value="/indus/resources/vendor/switchery/switchery.min.js"/>"></script>


<!-- end: MAIN JAVASCRIPTS -->
<!-- start: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
<script
	src="<c:url value="/indus/resources/vendor/jquery-validation/jquery.validate.min.js"/>"></script>


<!-- end: JAVASCRIPTS REQUIRED FOR THIS PAGE ONLY -->
<!-- start: CLIP-TWO JAVASCRIPTS -->
<script src="<c:url value="/indus/resources/assets/js/main.js"/>"></script>
<!-- start: JavaScript Event Handlers for this page -->

<script src="<c:url value="/indus/resources/assets/js/login.js"/>"></script>

<link rel="stylesheet"
	href="<c:url value="/indus/resources/alertify/alertify.core.css"/>" />
<link rel="stylesheet"
	href="<c:url value="/indus/resources/alertify/alertify.default.css"/>"
	id="toggleCSS" />
<script src="<c:url value="/indus/resources/alertify/alertify.js"/>"></script>	
<script>
			jQuery(document).ready(function() {
				Main.init();
				Login.init();
			});
			
			function login() {
				
				if($("#userNameId").val()!=null && $("#userNameId").val()!="" && $.trim($("#userNameId").val()).length>0)
				{
					if($("#password").val()!=null && $("#password").val()!="")
					{
						$("#loginForm").submit();
					}
					else
					{
						alertify.error("Password should not be empty");
						return;
					}	
				}
				else
				{
					alertify.error("Username should not be empty");
					return;
				}	
				
	}
			
			// checking while reset password
			 function isNotAvailEmailId(id) {
				jQuery.ajax({
					type : "POST",
					data :{emailid:$('#' + id).val()},
					url : "isAvailEmailId",
					error : function() {
						alert("error");
					},
					success : function(r) {
						 if(r==true){
							$('#resetPasswordForm').attr("action","/IndusHealth/indus/resetPassword");
							$('#sendMeResetInstruction').removeClass("disabled");
						}
						else{
							$('#resetPasswordForm').attr("action"," ");
							$('#sendMeResetInstruction').addClass("disabled");
							alert("Email id is not registered with us..!");
							//$('#' + id).val("");
							//$('#' + id).focus();
						} 
					}
				});
			} 
			
			function forgetPassword(){
				if($("#userNameId").val()!=null && $("#userNameId").val()!="" && $.trim($("#userNameId").val()).length>0)
				{
					jQuery.ajax({
						type : "POST",
						data :{username:$("#userNameId").val()},
						url : "isAvailUserName",
						error : function() {
							alert("error");
						},
						success : function(r) {
							var details=r.split("#");
							 if(details[0]=="true"){
								$('#resetPasswordUsername').val($("#userNameId").val());
								$('#forgetPasswordModal').modal('show');
								$('#forgetPasswordMobNo').html(details[1]);
								$('#resetPasswordMobNo').val(details[1]);
								$('#resetPasswordEmail').val(details[2]);
							}
							else{
								alert("Sorry username not exist");
							} 
						}
					});
				}
				else
				{
					alert("Username should not be empty");
				}
			}
			
		</script>
<!-- end: JavaScript Event Handlers for this page -->
<!-- end: CLIP-TWO JAVASCRIPTS -->

<%-- <link rel="stylesheet" id="skin_color" type="text/css"
	href="<c:url value="/indus/resources/assets/css/themes/theme-1.css"/>" > --%>

<!-- end: CLIP-TWO CSS -->
<!-- start: CSS REQUIRED FOR THIS PAGE ONLY -->
<!-- end: CSS REQUIRED FOR THIS PAGE ONLY -->
</head>
<!-- end: HEAD -->
<!-- start: BODY -->

<style>
body {
    background:url("resources/assets/images/login-background.jpg");
    height:100vh;
    background-size:cover;
}
</style>

<body class="login">
	<!-- start: LOGIN -->
	<div class="row">
		<div
			class="main-login col-xs-11 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-8">
			<!-- <div class="logo margin-top-30">
				<img width="190px" height="75px" src="resources/assets/images/indus-logo-new2.png">
			</div> -->
			<!-- start: LOGIN BOX -->
			<div class="box-login" style="margin-top: 45%; opacity: 0.7;margin-right: 5%;">
			
<%-- <div style="border: 1px solid black; width: 300px; padding-top: 10px;">
			<br /> Please enter your username and password to login ! <br /> <span
				style="color: red">${message}</span> <br />
			<form:form method="post" action="/IndusHealth/indus/login"
				modelAttribute="users">
				<table>
					<tr>
						<td>Username:</td>
						<td><form:input path="username" /></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><form:input path="password" /></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input type="submit" /></td>
					</tr>
				</table>
			</form:form>
		</div> --%>
			
				<form:form class="form-login" commandName="login"
					action="/IndusHealth/indus/loginAuthentication" method="POST" id="loginForm">
					<fieldset>
						<legend> Sign in to your Indus EHR </legend>
						
						<c:if test="${not empty msg}">
   								<%-- <font color=red> ${msg}</font> --%>
   								<script>alert("Your Indus EHR password is reset successfully!");</script>
						</c:if>
						
						<!-- <p>Please enter your username and password to log in.</p> -->
						<div class="form-group">
							<span class="input-icon"> 
								<form:input path="userName" type="text" id="userNameId" required="true"
									name="userNameId" placeholder="Username" class="form-control" />
								<i class="fa fa-user"></i>
							</span>
						</div>
						<div class="form-group form-actions">
							<span class="input-icon">
							 <form:input path="newPassword"
									type="password" id="password" name="password" required="true"
									placeholder="Password" class="form-control password" /> <i
								class="fa fa-lock"></i><a class="forgot" onclick="forgetPassword()"> I forgot my password </a>
							</span>
							
							<c:if test="${not empty error}">
   								<font color=red> ${error}</font>
							</c:if>
						</div>
						<div class="form-actions">
							<div class="checkbox clip-check check-primary">
								<input type="checkbox" id="remember" value="1"> <label
									for="remember"> Keep me signed in </label>
							</div>
							<!-- <input type="text" id="lock" value="02-07-20199" name="lock" class="hidden"> -->
							<button type="submit" class="btn btn-primary pull-right" onclick="login();">
								Login <i class="fa fa-arrow-circle-right"></i>
							</button>
						</div>
						<div class="new-account">
							<!-- Don't have an account yet? <a href="login_registration.html">
								Create an account </a> -->
						</div>
					</fieldset>
				</form:form>
				<!-- start: COPYRIGHT -->
				<div class="copyright">
					<span class="text-bold text-uppercase"> INDUS HEALTH PLUS &copy; </span> <span>2019-2020</span>
				</div>
				<!-- end: COPYRIGHT -->
			</div>
			<!-- end: LOGIN BOX -->
		</div>
	</div>
	<!-- end: LOGIN -->
	
	<!-- ForgetPassword Popup -->
<div class="modal fade" id="forgetPasswordModal" tabindex="-1"
	role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		 <div class="modal-content">
			<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Reset Password</h4>
				</div>
		 <form:form action="/IndusHealth/indus/resetPassword" method="POST" id="resetPasswordForm"> 
			<div class="modal-body">
			<input type="text" id="resetPasswordUsername" name="resetPasswordUsername" class="hidden">
			<input type="text" id="resetPasswordMobNo" name="resetPasswordMobNo" class="hidden">
			<input type="text" id="resetPasswordEmail" name="resetPasswordEmail" class="hidden">
			
			
				<div class="row">
					<div class="col-md-12">
						<h4>Your password will be sent on this number :<span id="forgetPasswordMobNo"> </span> </h4>
					</div>
				</div>
					<!-- <div class="row">
						<div class="col-md-4" style="margin-top: 7px;">
							<p>
								<span>*</span>Enter your email Id
							</p>
						</div>
						<div class="col-md-8">
							<div class="form-group form-actions">
								<span class="input-icon"> <input type="email" onchange="isNotAvailEmailId('resetMailId')"
									name="emailId" id="resetMailId" name="password" placeholder="Email Id"
									class="form-control" /><i class="fa fa-user"></i>
								</span>
							</div>
						</div>
					</div> -->
					 <%-- <form:form method="post" action="/IndusHealth/indus/resetPassword"
						 class="form-horizontal" role="form">
						<div class="form-group">
							<label for="exampleInputEmail1">Enter your emailId</label> <i
								class="fa fa-envelope"></i>
							<form:input path="emailId" class="form-control" id="resetMailId"
								style="width: 36%;" type="email"
								onchange="isNotAvailEmailId('resetMailId')" />
						</div>
						<div class="form-actions">
							<form:button type="submit" class="btn btn-info">Send Me Reset
								Instructions</form:button>
						</div>
					</form:form> --%>

					<!-- <div class="login-helpers">
						<a href="#" data-toggle="modal" data-target="#myLoginModal"
							onclick="HidePopup('myForgetPasswordModal')"> Back to Login</a> <br>
					</div> -->
				</div>
				<div class="modal-footer">
						<button type="submit" class="btn btn-primary" id="sendMeResetInstruction">Send Me Reset Instructions
					</button>
				</div>
				</form:form>
			</div>

		</div>
	</div>

<!-- end of ForgetPassword Popup -->


</body>
<!-- end: BODY -->

<!-- <script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-79904946-1', 'auto');
  ga('send', 'pageview');

</script> -->

</html>
		
