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
<title>Login</title>
</head>
<body>
<jsp:include page="headerBar.jsp"/>
<div class="w3-display-container w3-content" style="max-width:1500px;">
<div class="w3-center">
	<h3>Log in to BookStorage</h3>
</div>
	<form action="/login" method="post">
		<div style="margin-top:25px">
			<label for="username"></label>
			<input class = "w3-input w3-border w3-center"type="text" name="username" placeholder="Username" style="width:300px; margin:10px auto">
		</div>
		<div>
			<label for="password"></label>
			<input class="w3-input w3-border w3-center" type="password" name="password" placeholder="Password" style="width:300px; margin:auto">		
		</div>
		<div class="w3-center" style="margin-top: 15px">
		<a href="/" class="w3-bar-item w3-button w3-khaki w3-mobile w3-margin-left" style="border-radius: 15px"><i class="home"> </i>Home</a>
		<input class="w3-bar-item w3-button w3-khaki w3-mobile" style="border-radius: 15px" type="submit" value="Log in" style="margin:auto">
		</div>
		
		<!-- Remember me function -->
		<div class="w3-center" style="margin-top: 15px">
		<input type="checkbox" id="remember" name="remember-me">
		<label for="remember">
		Remember Me
		</label>
		</div>
		
	</form>
	<div class="w3-center" style="margin-top: 15px">
		<a href="/register"><h5>Register for BookStorage</h5></a>
		<a href="/passwordreset"><h5>Forgot Password</h5></a>
	</div>
	</div>
</body>
</html>