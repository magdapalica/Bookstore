<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--  
<c:choose>
	<c:when test="${userHasRentedProduct}">
		<form action="/rateLender" method="post">
			<input type="hidden" name="productId" value="${product.id}"/>
			<input type="hidden" name="lenderId" value="${product.owner.id}"/>
			<input type="number" name="rating" min="1" max="5" value="5">
			<input type="submit" value="Rate Lender">
		</form>
	</c:when>
</c:choose>
 -->