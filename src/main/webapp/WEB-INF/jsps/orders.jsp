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
				"maxPrice": document.getElementById("maxPrice").value
			
			}
		);
	}
	
</script>
<title>My Products</title>
</head>
<body class="w3-white">

<!-- Navigation Upper bar -->
<div class="w3-display-container w3-content" style="max-width:1500px;">

<div class="w3-bar w3-white w3-large">
 
</div>
<jsp:include page="headerBar.jsp"/>

	<div class="w3-center">
		<h1>My Products</h1>
	</div>

	<div class="w3-center ">
		<h4>Awaiting Confirmation</h4>
	</div>
	<table style="margin:auto">
		<tr style="font-size:18px">
			<th>Product</th>
			
			<th></th>
			<th></th>
			<th></th>
		</tr>
		<c:forEach items="${requestedBuy}" var="buy">
			<tr>
				<td>${buy.product.title}</td>
		
				<td><a href="/products/${buy.product.id}">Details</a></td>
				<td>
					<form action="rejectBuy" method="post">
						<input type="hidden" name="buyId" value="${buy.id}"/>
						<input type="submit" value="Delete"/>
					</form>
				</td>
				<td>
					<form action="confirmBuy" method="post">
						<input type="hidden" name="buyId" value="${buy.id}"/>
						<input type="submit" value="Confirm"/>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	</table>


  <a href="/" class="w3-bar-item w3-button w3-khaki w3-mobile w3-center" style="margin-top:30px; margin-left:700px; border-radius: 15px"><i class="home"> </i> Add More Products</a>
  </div>
</body>
</html>