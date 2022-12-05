<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
<title>Top10</title>
</head>
<body>
<div class="w3-display-container w3-content" style="max-width:1500px;">
<div class="w3-center">
<h2>Top 10 Books</h2> </div>
<div id="productList">
   			<jsp:include page="productRatingList.jsp"/> 		
	</div>
</div> 
</body>
<jsp:include page="bottomBar.jsp"/>
</html>