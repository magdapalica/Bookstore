<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

	<style>
		.badge1 {
			position:relative;
		}
		.badge1[data-badge]:after {
			content:attr(data-badge);
			position:absolute;
			top:2px;
			right:2px;
			font-size:.8em;
			background: red;
			color:white;
			width:20px;height:20px;
			text-align:center;
			line-height:18px;
			border-radius:50%;
			box-shadow:0 0 1px #333;
		}
		.badge1[data-badge="0"]:after {
			display: none;
		}
	</style>
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			let elem = document.getElementById("own-products");
			if (elem)
				elem.dataset.badge = "${notificationCount}";
		});
	</script>

  <div class="w3-bar w3-white w3-large">
    <a href="/" ><img src="/images/bookstore-logo.webp" width=50></a>
    
    <security:authorize access="isAuthenticated()">
	    <a href="/logout" class="w3-bar-item w3-button w3-purple w3-right w3-mobile">Logout</a>
    	<a href="/ownProducts" id="own-products" class="w3-bar-item w3-button w3-right w3-blue w3-mobile badge1" data-badge="">
    		My Products
    	</a>
    </security:authorize>
    <security:authorize access="not isAuthenticated()">
	    <a href="/login" class="w3-bar-item w3-button w3-purple w3-right w3-mobile">Login</a>
    </security:authorize>

  </div>