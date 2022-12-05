<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Top10</title>
</head>
<body>
<h1>Top 10 Books</h1>

<table id="top10" style="margin:auto">
 	<tr style="font-size:18px"> 
		<th>ID</th>
		<th>Title</th>
		<th>Author</th>
		<th>Rating</th>
		
	</tr>  
	<c:forEach items="${products}" var="product">
		<tr>
			<td>${product.id}</td>
			<td>${product.title}</td>
			<td>${product.author}</td>
			<td>${product.rating}</td>
		<!-- <td><a href="/products/${product.id}">Details</a></td> -->	
	</tr>
	</c:forEach>
</table>
</body>
</html>