<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="products" style="margin:auto">
<tr style="font-size:18px"> 
		<th>ID</th>
		<th>Title</th>
		<th>Author</th>
		<th>Rating</th>
	</tr>  
	<c:forEach items="${top10}" var="productRating">
		<tr>
			<td>${productRating.product.id}</td>
			<td>${productRating.product.title}</td>
			<td>${productRating.product.author}</td>
			<td>${productwithrating.rating}</td>
			<td><a href="/products/${top10.id}">Top 10</a></td>
		</tr>
	</c:forEach>
</table>
