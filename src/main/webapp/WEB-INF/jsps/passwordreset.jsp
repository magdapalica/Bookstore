<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: Verdana,sans-serif}
</style>
<title>Reset Password</title>
</head>
<body>
<jsp:include page="headerBar.jsp"/>
<div class="w3-display-container w3-content" style="max-width:1500px;">
<div class="w3-center">
	<h3>Reset Password</h3>
</div>
	<form action="/passwordreset" method="post">
		<div style="margin-top:25px">
			<label for="username"></label>
			<input class = "w3-input w3-border w3-center"type="text" name="username" placeholder="Username" style="width:300px; margin:10px auto">
		</div>
		<div>
			<label for="password"></label>
			<input class="w3-input w3-border w3-center" type="password" name="password" placeholder="New Password" style="width:300px; margin:auto">		
		</div>
		<div class="w3-center" style="margin-top: 15px">
		<a href="/" class="w3-bar-item w3-button w3-khaki w3-mobile w3-margin-left" style="border-radius: 15px"><i class="home"> </i>Home</a>
		<input class="w3-bar-item w3-button w3-khaki w3-mobile" style="border-radius: 15px" type="submit" value="Reset my password" style="margin:auto">
		</div>
		
	</form>
	<div class="w3-center" style="margin-top: 15px">
		<a href="/register"><h5>Register for Shazar</h5></a>
	</div>
	</div>
</body>
</html>