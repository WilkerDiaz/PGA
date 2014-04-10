<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mi Carrito | Abstracta</title>

<link rel="shortcut icon" href="/resources/web/css/icons/favicon.ico" type="image/x-icon">
<link rel="icon" href="/resources/web/css/icons/favicon.ico" type="image/x-icon">
<link rel="stylesheet" href="/resources/web/css/docs.css" />
<link rel="stylesheet" href="/resources/web/css/style.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
<script type="text/javascript" src="/resources/web/js/validation.js"></script>		
<script src="https://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>

</head>
<body>

<div id="headerWrap">
		<a href="/"><span class="abs_LogoMain">&nbsp;</span></a>
		<div class="headerRightBox">
			<br>
			<div class="headerRightBoxBottom">		
				<div class="headerSearch">
	               	<form id="searchForm" action="/Search" class="searchForm" method="get">
	                	<div id="header">
						   	<input id="searchtext" name="query" type="text" placeholder="buscar">
						    <input type="submit" value="" class="searchButton" id="searchButton">       
							<div style="clear:both;"></div>
						</div>
	                </form>
	            </div>
            </div>
		</div>
	</div>
	<div class="decoLine">&nbsp;</div>
<br>
<div class="width1000Center">


<c:if test="${wishList}"> 
	<div id="addedToWishList" class="row">
	   <br>
	   <div class="panel callout radius">
	   <div class="successCheck">${wishListMsg}</div>
	   </div>
	</div>
</c:if>

<c:if test="${!noItems}">
<div id="cartDetail">
	<div class="row">
		<jsp:include page="CartDetail.jsp" />
	</div>
	<div class="row">
	    <div class="small-5 push-7 columns">
	    <div>    
	    	<div class="alignleft">
		    	<span>artículos</span><br>
		    	<span>impuesto</span><br>
		    	<span>envío</span><br>
		    	<div class="pad_top_20">
		    		<span style="font-weight: bold;">total a pagar</span>
		    	</div>
	    	</div>   
	    	<div class="alignright">
	    		<span>Bs:</span><span id="merchandise"><fmt:formatNumber value="${merchandise}" type="number" pattern="#,##0.00"/></span><br>
	    		<span>Bs:</span><span id="tax"><fmt:formatNumber value="${tax}" type="number" pattern="#,##0.00"/></span><br>
	    		<span>Bs:</span><span id="shipping"><fmt:formatNumber value="${shipping}" type="number" pattern="#,##0.00"/></span><br>
	    	
		    	<div class="pad_top_20">
			    	<span style="font-weight: bold;">Bs:</span>
			    	<span id="total" style="font-weight: bold;">
			    		<fmt:formatNumber value="${total}" type="number" pattern="#,##0.00"/>
			    	</span>
		    	</div>
	    	</div>
	        <div style="clear: both;"></div>
        </div>   
	    </div>
	</div>
	<br>
	<hr>
	<div class="row">
	    <div class="small-4 columns">	
		</div>
		<div class="small-4 columns">
			<a href="/Pago/" class="button small noSize">continuar checkout</a>
		</div>	
	</div>
	<div class="bodySpace100"></div>
</div>
</c:if>

<div id="noItems" style="<c:if test="${!noItems}">display:none</c:if>">
	<div class="row">
		<div class="panel callout radius">
			<!-- Content here -->
			No hay artículos en su carrito.
			<br>
			<br>
			Visite nuestras categorías para encontrar más productos.
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