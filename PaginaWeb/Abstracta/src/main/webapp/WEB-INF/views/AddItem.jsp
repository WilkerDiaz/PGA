<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Artículo Agregado a Su Carrito</title>
</head>
<body>

<br>
<div class="row">
    <div class="small-6 columns">
		
		<c:if test="${qty>1}"><h6 class="addedItem">Se agregaron ${qty} artículos a tu carrito.</h6></c:if>
		<c:if test="${qty==1}"><h6 class="addedItem">Se agregó ${qty} artículo a tu carrito.</h6></c:if>
		<div class="small-6 columns">
		<img src="/Images/i/120/120/${itemAdded.MProductId}/1">
		</div>
		<div class="small-6 columns">
		<span class="addedItemName">${itemAdded.name}</span><br><br>
		<span class="addedItemprice">Bs: <fmt:formatNumber value="${itemAdded.finalprice}" type="number" pattern="#,##0.00"/></span><br>
		<span class="addedItemprice">cantidad: ${qty}</span><br>
		<span class="addedItemprice">código: ${itemAdded.value}</span>
		</div>
	</div>
	<div class="small-6 columns">
		
		<span class="showRoomName">tu carrito contiene: ${cartTotal.totalQty} artículo<c:if test="${cartTotal.totalQty>1}">s</c:if><br><br></span>
		<span> <br></span>
		<span></span>
		
		<table class="tableCartDetail">
			<tr>
				<td class="itemSubTotalAdd col_200">monto en artículos estimado:</td>
				<td class="itemSubTotalAdd col_right">Bs: <fmt:formatNumber value="${cartTotal.merchandise}" type="number" pattern="#,##0.00"/></td>
			</tr>
			<tr>
				<td class="itemSubTotalAdd">envío estimado:</td>
				<td class="itemSubTotalAdd col_right">Bs: <fmt:formatNumber value="${cartTotal.shipping}" type="number" pattern="#,##0.00"/></td>
			</tr>
			<tr>
				<td class="itemTotal">total estimado:</td>
				<td class="itemTotalAdd col_100 col_right">Bs: <fmt:formatNumber value="${cartTotal.total}" type="number" pattern="#,##0.00"/></td>
			</tr>
		</table>
	</div>	
</div>
<br>
<br>
<div class="row">
    <div class="small-4 columns">
		<a href="#" class="button small noSize close-reveal-modal-custom">continuar comprando</a>
	</div>
	<div class="small-4 columns">
		<a href="/Carrito" class="button small noSize">checkout</a>
	</div>	
</div>
<a class="close-reveal-modal">×</a>
</body>
</html>