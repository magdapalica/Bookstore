<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="products" style="margin:auto">
<tr style="font-size:18px"> 
		<th>ID</th>
		<th>Title</th>
		<th>Author</th>
		<th>Rating</th>
	</tr>  
	<c:forEach items="${top10}" var="productRatingList">
		<tr>
			<td>${productRatingList.product.id}</td>
			<td>${productRatingList.product.title}</td>
			<td>${productRatingList.product.author}</td>
			<td>${productRatingList.rating}</td>
		</tr>
	</c:forEach>
</table>
