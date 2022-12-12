<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>


<meta charset="ISO-8859-1">
<title>Insert title here</title>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<style>
body, h1, h2, h3, h4, h5, h6 {
	font-family: Verdana,sans-serif}
</style>
<title>Personal settings</title>

</head>

<body>
<div class="w3-display-container w3-content" style="max-width: 1500px;">
<div class="w3-bar w3-white w3-large">

<a class="w3-bar-item w3-button w3-lime w3-mobile w3-right" style="margin-top:15px; float:right" href="/logout">Logout</a>
<a href="/add" class=" w3-center w3-bar-item w3-button w3-lime w3-mobilec" style="margin-top:15px;float:right; margin-right:5px">Products</a>


</div>
	
		<div class="w3-bar w3-center w3-white w3-large">
			<h2 class="w3-center ">Welcome ${user.getUsername()}</h2>
			<img src="/profile-pictures/${user.getPictures().getName()}" alt="">
			<h3 class="w3-center">Your Personal Information</h3>
			
		
			<div class="w3-center">
				<p>First Name: ${user.getFirstName()}</p>
				<p>Last Name: ${user.getLastName()}</p>
				<p>Email: ${user.getEmail()}</p>
				<p>Number: ${user.getNumber()}</p>


			</div>
			<div>
				<h4 class="w3-center">Update your information</h4>
				<br />

				<form method="POST" action="/personal">

				<form class="w3-center" method="POST" action="/personal">

				<div class="w3-center">
					<input type="hidden" name="username" readonly="readonly"
						value="${user.getUsername()}" /> <label> First Name:</label> <input
						type="text" name="firstName" value="${user.getFirstName()}" /> <label>
						Last Name:</label> <input type="text" name="lastName"
						value="${user.getLastName()}" /><label> Email:</label> <input
						type="text" name="email" value="${user.getEmail()}" /> <label>
						Number:</label> <input type="text" name="number"
						value="${user.getNumber()}" /> 
						<input type="submit"
						class="w3-bar-item w3-button w3-khaki w3-mobile" value="Update" style="margin-left:47%; margin-top:25px; border-radius: 15px"/>
						
				</div>		
						
				</form>

			</div>

		</div>
		
	</div>
</body>
</html>