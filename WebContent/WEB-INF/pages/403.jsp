<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Access Denied</title>
<script>
/* function goBack() {
    window.history.back();
} */
</script>
</head>
<body>
	<center>
		<br /> <br /> <br />
		<h1>
			Sorry you dont have access to this page <span style="color: red;">${username}</span>
		</h1>
		<a href="homepage"><button>Go to Homepage</button></a>
		<!-- <button onclick="goBack()">Go Back</button> -->
	</center>

</body>
</html>