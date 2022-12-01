<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
	<c:when test="${productVotes == 0}">
		<span>This product has not been rated, yet.</span>
	</c:when>
	<c:when test="${productVotes == 1}">
		<span>Product rating: ${productRating}/5 (1 vote)</span>
	</c:when>
	<c:otherwise>
		<span>Product rating: ${productRating}/5 (${productVotes} votes)</span>
	</c:otherwise>
</c:choose>

<c:if test="${userHasBuyedProduct}">
	<form action="/rateProduct" method="post">
		<input type="hidden" name="productId" value="${product.id}"/>
		<input type="number" name="rating" min="1" max="5" value="5">
		<input type="submit" value="Rate Product">
	</form>
</c:if>
