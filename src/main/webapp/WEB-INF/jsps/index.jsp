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
body,h1,h2,h3,h4,h5,h6 {font-family: Verdana,sans-serif} 
</style>
<!--  -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
	function searchForProdcuts() {
		$("#productList").load(
			"/productList",
			{
				"search": document.getElementById("search").value,
				"author": document.getElementById("author-select").value,
				"category": document.getElementById("category-select").value,
				"title": document.getElementById("title-select").value,
				"maxPrice": document.getElementById("max-price").value
			
			}
		);
	}
 
	
</script>
<title>BookStorage</title>
</head>
<body onload="searchForProdcuts()">

<!-- Navigation Upper bar -->
<div class="w3-display-container w3-content" style="max-width:1500px;">

<div class="w3-bar w3-large">
 
</div>
<jsp:include page="headerBar.jsp"/>

	<div class="w3-center">
		<h1>Bookstore</h1>
	</div>
	
	<div class="w3-center" style="margin:15px 25% 15px; width:50%;" >
	<input class="w3-input w3-border" id="search" type="text" placeholder="Search for anything ..." autofocus oninput="searchForProdcuts()"/>
	</div>
	<div class="w3-center" id="filters">
	<h5>Filters</h5>
	
		<label for="author-select">Author:</label>
		 <select name="authors" id="author-select" onchange="searchForProdcuts()">
		    <option value="">Any</option>
			<c:forEach items="${authors}" var="author">
				<option value="${author}">${author}</option>
			</c:forEach>
		</select>
		
		<label for="category-select">Category:</label>
		<select name="categories" id="category-select" onchange="searchForProdcuts()">
		    <option value="">Any</option>
			<c:forEach items="${categories}" var="category">
				<option value="${category}">${category}</option>
			</c:forEach>
		</select>
		
		<label for="title-select">Title:</label>
		<select name="titles" id="title-select" onchange="searchForProdcuts()">
		    <option value="">Any</option>
			<c:forEach items="${titles}" var="title">
				<option value="${title}">${title}</option>
			</c:forEach>
		</select>	
		
		  <label for="max-price">Maximum price:</label>
		  <input
		  	type="number"
		  	id="max-price"
		  	name="max-price"
		  	value="9999"
		  	min="1"
		  	max="9999"
		  	oninput="searchForProdcuts()"
		  >
		
	<div style="margin-top:50px">
	  <h5> Books </h5>	</div>
	<div id="productList">
   		<jsp:include page="productList.jsp"/>
	</div>
	</div>
</div>


</body>
</html>