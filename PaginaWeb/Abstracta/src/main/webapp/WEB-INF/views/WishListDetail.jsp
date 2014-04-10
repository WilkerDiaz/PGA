<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<a href="#" data-dropdown="drop" class="small secondary radius button dropdown alignright orderBy">Fecha Agregado</a>
<ul id="drop" data-dropdown-content="" class="f-dropdown orderByDrop" style="position: absolute; top: 73px; left: -99999px;">
      <li><a id="sort_Date_Desc">Fecha Agregado</a></li>
      <li><a id="sort_Asc">Nombre (Ascendente)</a></li>
      <li><a id="sort_Desc">Nombre (Descendente)</a></li>
      <li><a id="sort_Price1">Precio (Ascendente)</a></li>
      <li><a id="sort_Price2">Precio (Descendente)</a></li>
</ul>

<table id="wishList" class="tableCartDetail lineItems">
	<tbody>
	<c:forEach items="${wishListItems}" var="i">
    	<tr class="tableline" id="wishlistline" data-item-id="${i.wishListLineId}">
        	<td class="itemPhoto">
        	<div class="tableRow">
        		<a href="/productos/${i.nameFormatted}/${i.productValue}">
	        	<img name="imga" id="imga" alt="${i.name}" src="/Images/i/110/110/${i.productValue}/1">
	        	</a>
	        </div>
			</td>
	        <td id="itemInfo">
	        	<div class="tableRow">
	        		<div class="tableRowTop">
			        	<a class="wl_itemName" href="/productos/${i.nameFormatted}/${i.productValue}">${i.name}</a><br>
			            <div class="wl_itemPrice">
				            <span>Bs:</span> 
			            	<span><fmt:formatNumber value="${i.finalPrice}" type="number" pattern="#,##0.00"/></span>
		            	</div>
	        		</div>
	        		<div class="wl_RowBottom">
	        			<p><span class="availability">en stock y listo para enviar</span></p>
	        		</div>
	        	</div>
            </td>
            <td class="col_button pad_top_20 col_left">
            	<div class="tableRow">
	        		<div class="wl_RowTopDown">
	        			<span class="addedOn">Agregado el <fmt:formatDate value="${i.created}" pattern="dd-MM-yyyy"/></span>
	        			<a class="moveItemToCart button">mover al carrito</a>
		            </div>
	        		<div class="wl_RowBottom">
	        			<a id="removeItem" class="removeItemFromWishList actionOnCart">eliminar</a>
	        		</div>
	       		</div>
            </td>
        </tr>   
    </c:forEach>
    </tbody>
	</table>  