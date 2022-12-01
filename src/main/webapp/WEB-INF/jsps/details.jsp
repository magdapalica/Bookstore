<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/css?family=Raleway">
<style>
body, h1, h2, h3, h4, h5, h6 {
	font-family: "Raleway", Arial, Helvetica, sans-serif
}
</style>
<title>${product.title}</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<script>

	
	function onAmountChanged() {
		const amount = document.getElementById("amount").value;
		const buy = document.getElementById("buy");
		
		buy.value = "Buy ("+ ${product.price}*amount+") ";
		
	}
</script>
</head>

<body onload="onAmountChanged()">
	<div class="w3-display-container w3-content" style="max-width: 1500px;">
	<jsp:include page="headerBar.jsp"/>
		<div class="w3-bar w3-white w3-large">
			<h3 style="font-family: Helvetica" class="w3-center">${product.title}</h3>
			<div class= "w3-center">
			<p>${product.description}</p> </div>

			<div class= "w3-center">
				<c:forEach items="${product.getPictures()}" var="picture">
					<img src="/product-pictures/${picture.getName()}" alt="">
				</c:forEach>
			</div>

		
			<div class="w3-center" style="margin-bottom: 25px" id="productRating">
				<jsp:include page="productRating.jsp" />
			</div>

			<form class="w3-center" action="/buyProduct" method="post">
				<input type="hidden" name="productId" value="${product.id}" />
				<label for="qty">Qty: </label> 
				<input type="number" id="amount" name="amount" value="1" oninput="onAmountChanged()">
				<div id="productBooking"><jsp:include page="productBooking.jsp" /></div>
				  <input type="submit" value="Buy (${price} EUR)" id="buy"/>
			</form>

		</div>
	</div>
	<jsp:include page="homeBar.jsp"/>
</body>
</html>
