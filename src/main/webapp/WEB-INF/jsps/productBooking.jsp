<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${available}">
	<input type="submit" value="Buy (${price} EUR)"/>
</c:if>

<!--  
<c:if test="${not available}">
	<div>The product is not available</div>
</c:if> -->