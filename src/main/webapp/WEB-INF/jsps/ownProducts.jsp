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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>
	function searchForProdcuts() {
		$("#productList").load(
			"/productList",
			{
				"search": document.getElementById("search").value,
				"title": document.getElementById("title-select").value,
				"category": document.getElementById("category-select").value,
				"maxPrice": document.getElementById("max-price").value,
				"author": document.getElementById("author").value,
				
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

	<div class="w3-center w3-purple">
		<h1>My Products</h1>
	</div>

	<div class="w3-center w3-purple">
		<h2>Awaiting Confirmation</h2>
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
				<td>${buy.product.type}</td>
		
				<td><a href="/products/${buy.product.id}">Details</a></td>
				<td>
					<form action="rejectBuy" method="post">
						<input type="hidden" name="buyId" value="${buy.id}"/>
						<input type="submit" value="Delete"/>
					</form>
				</td>
				<td>
					<form action="rejectBuy" method="post">
						<input type="hidden" name="buyId" value="${buy.id}"/>
						<input type="submit" value="Confirm"/>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>

	 <div class="w3-center w3-purple">
		<h2>Awaiting Return</h2>
	</div>
	<!--  
	<table style="margin:auto">
		<tr style="font-size:18px">
			<th>Product</th>
			<th>Start</th>
			<th>End</th>
			<th></th>
			<th></th>
		</tr> -->
		<c:forEach items="${confirmedBuy}" var="buy">
			<tr>
				<td>${buy.product.type}</td>
				<td>${buy.start}</td>
				<td>${buy.end}</td>
				<td><a href="/products/${buy.product.id}">Details</a></td>
				<!--  
				<td>
					<form action="confirmReturn" method="post">
						<input type="hidden" name="rentId" value="${rent.id}"/>
						<input type="submit" value="Confirm Return"/>
					</form>
				</td>
				-->
			</tr>
		</c:forEach>
	</table>
</div>

<jsp:include page="homeBar.jsp"/>
</body>
</html>