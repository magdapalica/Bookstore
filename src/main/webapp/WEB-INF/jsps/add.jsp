<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", Arial, Helvetica, sans-serif}
</style>
<title>Add product</title>
</head>
<body class="w3-white">
<div class="w3-display-container w3-content" style="max-width:1500px;">

<jsp:include page="headerBar.jsp"/>
	<div class="w3-center w3-purple">
	<h2>BookStorage</h2>
	</div>
	<div class="w3-center">
	<form action="/add" method="post" enctype="multipart/form-data">
		<input type="text" name="category" placeholder="Category"/>
		<input type="text" name="description" placeholder="Description"/>
		<input type="text" name="author" placeholder="Author"/>
		<input type="text" name="title" placeholder="Title"/>
		<input type="number" name="price" min=0 step=0.01 placeholder="Price"/>
		<div><input type="file" id="files" name="file"/></div>
		<div><input type="file" id="files" name="file"/></div>
		<div><input type="file" id="files" name="file"/></div>
		<div><input type="file" id="files" name="file"/></div>
		<input type="submit" value="Create a new product"/>
	</form>
	</div>
	<div id="productList">
   		<jsp:include page="productList.jsp"/>
	</div>
	</div>
</body>
<!-- Bottom navigation taskbar-->
<div class="nav-bar w3-white w3-large w3-center">
  <a href="/" class="w3-bar-item w3-button w3-blue w3-mobile w3-margin-left"><i class="home"> </i>Home</a>
  <a href="#personal" class="w3-bar-item w3-button w3-blue w3-mobile">Personal</a>
  <a href="#information" class="w3-bar-item w3-button w3-blue w3-mobile">Top10</a>
   <a href="#cart" class="w3-bar-item w3-button w3-blue w3-mobile">Cart</a>
</div>
</html>