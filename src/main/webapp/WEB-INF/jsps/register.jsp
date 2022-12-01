<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta charset="ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", Arial, Helvetica, sans-serif}
</style>
<title>Register</title>
</head>
<jsp:include page="headerBar.jsp"/>
<body>
<div class="w3-center" style="margin-top:80px">
	<h3>Register to Bookstore</h3>
	</div>
	<div>${message}</div>
	<form action="/register" method="post" enctype="multipart/form-data">
		<div style="margin-top:25px">
			<label for="username"></label>
			<input class = "w3-input w3-border w3-center" type="text" name="username" placeholder="Username" style="width:300px; margin:10px auto">
		</div>
		<div style="margin-top:25px">
			<label for="firstName"></label>
			<input class = "w3-input w3-border w3-center" type="text" name="firstName" placeholder="First Name" style="width:300px; margin:10px auto">
		</div>
			<div style="margin-top:25px">
			<label for="lastName"></label>
			<input class = "w3-input w3-border w3-center" type="text" name="lastName" placeholder="Last Name" style="width:300px; margin:10px auto">
		</div>
			<div style="margin-top:25px">
			<label for="email"></label>
			<input class = "w3-input w3-border w3-center" type="text" name="email" placeholder="email" style="width:300px; margin:10px auto">
		</div>
			<div style="margin-top:25px">
			<label for="number"></label>
			<input class = "w3-input w3-border w3-center" type="text" name="number" placeholder="Phone number" style="width:300px; margin:10px auto">
		</div>
		
		<div>
			<label for="password"></label>
			<input class="w3-input w3-border w3-center" type="password" name="password" placeholder="Password" style="width:300px; margin:auto">		
		</div>
		<div><input class="w3-input w3-border w3-center" type="file" id="files" name="file" style="width:300px; margin:auto"/></div>
		<div class="w3-center" style="margin-top: 15px">
		<a href="/" class="w3-bar-item w3-button w3-khaki w3-mobile w3-margin-left"><i class="home"> </i>Home</a>
		<input class="w3-bar-item w3-button w3-khaki w3-mobile" type="submit" value="Register">
		</div>
		
	</form>

</body>
</html>