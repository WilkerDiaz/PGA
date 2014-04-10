<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="Header.jsp"/>
<br>

<div class="width1000Center">

<c:if test="${!noItems}">
<div id="cartDetail">
	<div class="row">
		<jsp:include page="WishListDetail.jsp" />
	</div>
	<br>
	<hr>
	<div class="bodySpace100"></div>
</div>
</c:if>

<c:if test="${complements}"> 
<div class="row">
    <div class="large-12 large-centered columns">
    
   	   <hr>
	   <h4>También combina con:</h4>  
      	
       <!-- Related Trend Set Products -->
	   <div id="wrap">
	   <ul id="mycarousel2" class="jcarousel-skin-tango">
	   <c:forEach items="${complements}" var="product">
	        <li>
	 		<a href="/productos/${product.nameFormatted}/${product.MProductId}">
	 		<img src="/Images/i/170/170/${product.MProductId}/1" data-other-src="/Images/i/170/170/${product.MProductId}/2">
	    	<br><span class="carouselName">${product.name}</span>
	    	<br>
			<c:if test="${product.promo=='Y'}"> 
			<span class="carouselPromoPrice">Bs:</span>  
			<span class="carouselPromoPrice" id="price">${product.finalprice}</span>
			<span class="carouselOrigPrice">Bs:</span> 
			<span class="carouselOrigPrice">${product.originalprice}</span>
		    </c:if>
			<c:if test="${product.promo=='N'}">
			<span class="carouselPrice">Bs:</span>  
			<span class="carouselPrice" id="price">${product.finalprice}</span>
			</c:if>
	    	</a>
	    	</li>
       </c:forEach>
	   </ul>
	   </div>
       <br>       
    </div>
</div>
</c:if>

<div id="noItems" style="<c:if test="${!noItems}">display:none</c:if>">
	<div class="row">
		<div class="panel callout radius">
			<!-- Content here -->
			No hay artículos en tus favoritos.
			<br>
			<br>
			Visita nuestras categorías para encontrar más productos.
		</div>
	</div>
	<div class="row">
	    <div class="small-4 columns">	
		</div>
		<div class="small-4 columns">
			<a href="/" class="button small noSize">continuar comprando</a>
		</div>	
	</div>
	<div class="bodySpace200"></div>
</div>
</div>

<script >
(function($) {
	$('#addedToWishList').delay(500).show("blind",1000);
	$('#addedToWishList').delay(5000).hide("blind",1000);
})(jQuery);
</script>

<div>
<jsp:include page="Footer.jsp" />	
		