<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<table id="products" style="margin:auto">
	<tr style="font-size:18px">
		<th>ID</th>
		<th>Category</th>
		<th>Description</th>
		<th>Author</th>
		<th>Title</th>
		<th>Price</th>
	</tr>
	<c:forEach items="${products}" var="product">
		<tr>
			<td>${product.id}</td>
			<td>${product.category}</td>
			<td>${product.description}</td>
			<td>${product.author}</td>
			<td>${product.title}</td>
			<td>${product.price}</td>
			<td><a href="/products/${product.id}">Details</a></td>
			<td>
				<form action="deleteProduct" method="post">
					<input type="hidden" name="id" value="${product.id}"/>
					<input type="submit" value="Delete"/>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>