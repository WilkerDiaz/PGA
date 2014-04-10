<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<table class="tableCartDetail lineItems">
	<tbody>
	<tr class="decoline itemHeaderRow">
       	<th></th>
        <th class="itemShipping"></th>
        <th class="itemPrice col_right">precio unitario</th>
        <th class="itemQty col_left">cantidad</th>
        <th class="itemTotal col_right">total</th>
   	</tr>	
	<c:forEach items="${cartItems}" var="i">
    	<tr class="tableline" id="cartline" data-item-id="${i.cartLineId}">
        	<td class="itemPhoto">
        	<div class="tableRow">
        		<a href="/productos/${i.nameFormatted}/${i.productId}">
	        	<img name="imga" id="imga" alt="${i.name}" src="/Images/i/110/110/${i.productId}/1">
	        	</a>
	        </div>
			</td>
	        <td id="itemInfo">
	        	<div class="tableRow">
	        		<div class="tableRowTop">
			        	<a class="itemName" href="/productos/${i.nameFormatted}/${i.productId}">${i.name}</a><br>
			            <span class="itemId">código: ${i.productValue}</span>
	        		</div>
	        		<div class="tableRowBottom">
	        			<p><span class="availability">en stock y listo para enviar</span></p>
	        			<a id="removeItem" class="removeItem actionOnCart">eliminar</a>
	        			&nbsp;·&nbsp;
	        			
	        			<sec:authorize access="isAuthenticated()" var="logged">
						   	<a id="moveItemToWishList" class="moveItemToWishList actionOnCart">mover a favoritos</a>
						</sec:authorize>
						<c:if test="${!logged}">
							<a href="/Cuenta/MoveItemToWishList?cartLine=${i.cartLineId}" 
	        					 class="actionOnCart">mover a favoritos</a>
						</c:if>
	        		</div>
	        	</div>
            </td>
            <td id="itemPrice" class="itemPrice pad_top_20 col_right">
            	<div class="tableRow">
	        		<div class="tableRowTop">
		            	<span>Bs:</span> 
		            	<span><fmt:formatNumber value="${i.finalPrice}" type="number" pattern="#,##0.00"/></span>
		            </div>
		            <div class="tableRowBottom">
	        		</div>
	       		</div>
	       	</td> 
            <td class="itemQty col_left">
            	<div class="tableRow">
	        		<div class="tableRowTop">
		            	<input id="qty" maxlength="2" onkeypress="return isNumberKey(event)" value="${i.desiredQty}"/> 
		            	<a id="updateQty" class="updateQty">actualizar</a>
	            	</div>
	        		<div class="tableRowBottom">
	        		</div>
	        	</div>
            </td> 
            <td class="itemTotal pad_top_20 col_right">
            	<div class="tableRow">
	        		<div class="tableRowTop">
		            	<span>Bs:</span> 
		            	<span id="itemTotal"><fmt:formatNumber value="${i.totalPrice}" type="number" pattern="#,##0.00"/></span>
		            </div>
	        		<div class="tableRowBottom">
	        		</div>
	       		</div>
            </td>
        </tr>   
    </c:forEach>
    </tbody>
	</table>  