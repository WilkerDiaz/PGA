<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
   
   <div class="small-10 push-2 columns">
	   
	   <!-- Site Nav -->
	   <nav class="breadcrumbs">
	   	  <a href="/">principal</a>
	   	  <c:forEach items="${path}" var="category" varStatus="loop">
			  <a 
			  <c:if test="${loop.last}">class="current"</c:if>
			  href="${prev}/${category.nameFormatted}">${category.name}</a>
		   	  <c:set var="prev" value="${prev}/${category.nameFormatted}" scope="request" />
	      </c:forEach>
	   </nav>
	   
	   <div>
	   <br>
	   <h3 class="alignleft">${title}</h3>  
	      
	   <c:if test="${showRoomProducts!=null}"> 
		   <a href="#" data-dropdown="drop" class="small secondary radius button dropdown alignright orderBy">
		   	ordenar
		   </a>
		   <ul id="drop" data-dropdown-content="" class="f-dropdown orderByDrop" style="position: absolute; top: 73px; left: -99999px;">
	          <li><a id="sort_Asc">Nombre (Ascendente)</a></li>
	          <li><a id="sort_Desc">Nombre (Descendente)</a></li>
	          <li><a id="sort_Price1">Precio (Ascendente)</a></li>
	          <li><a id="sort_Price2">Precio (Descendente)</a></li>
	        </ul>
        </c:if>
        </div>
			   
       <ul class="large-block-grid-3" id="sortable">
       <!-- Shows Dynamically the products images -->
       <c:forEach items="${showRoomProducts}" var="i" end="2000">
	       <li id="i_${i.MProductId}" style="position:relative">
	       <a title="${i.name}"  href="/productos/${i.nameFormatted}/${i.MProductId}">
		       
		       <img class="showRoomImageWrap finalImage" name="imga" id="imga" alt="${i.name}" src="/Images/i/270/270/${i.MProductId}/1" data-other-src="/Images/i/270/270/${i.MProductId}/2">
		       
		       <c:if test="${i.newproduct=='Y'}"> 
		       <span class="newItem"></span>
			   </c:if>
		       <span class="showRoomName">${i.name}<br></span>
		       <c:if test="${i.promo=='Y'}"> 
		       <span class="showRoomOrigPrice">Bs:<fmt:formatNumber value="${i.originalprice}" type="number" pattern="#,##0.00"/></span>
		       <span class="showRoomPromoPrice">Bs:</span>
		       <span class="showRoomPromoPrice"><fmt:formatNumber value="${i.finalprice}" type="number" pattern="#,##0.00"/></span>
		       </c:if>
			   <c:if test="${i.promo=='N'}">
			   <span class="showRoomPrice">Bs:</span>
			   <span class="showRoomPrice"><fmt:formatNumber value="${i.finalprice}" type="number" pattern="#,##0.00"/></span>
			   </c:if>
			   <input type="hidden" id="price" value="${i.finalprice}">
		    </a>
		    </li>
       </c:forEach>
       </ul>     
       
       <a href="#" class="back-to-top go-top"> <img src="/resources/web/css/icons/upArrow.png" alt="subir"> subir </a>
       
       
       	    
       
       <c:if test="${showRoomProducts!=null}">
       <div>       
	       <hr>
	       <a href="#" class="back-to-top alignleft">Subir</a>
		   <a href="#" data-dropdown="drop" class="small secondary radius button dropdown alignright orderBy">
		   	ordenar
		   </a>
		   <ul id="drop" data-dropdown-content="" class="f-dropdown orderByDrop" style="position: absolute; top: 73px; left: -99999px;">
	          <li><a id="sort_Asc">Nombre (Ascendente)</a></li>
	          <li><a id="sort_Desc">Nombre (Descendente)</a></li>
	          <li><a id="sort_Price1">Precio (Ascendente)</a></li>
	          <li><a id="sort_Price2">Precio (Descendente)</a></li>
	        </ul>
	        <div style="clear: both;"></div>
		   <br>
        </div>
        </c:if> 
       
       <!-- No Results From Search -->
		<c:if test="${noResults}">  
			<jsp:include page="NoResults.jsp" flush="true"/>  
		</c:if> 
       
       <c:if test="${noProducts}">
    	<div class="panel callout radius">
		  <!-- Content here -->
		  Lo sentimos pero en estos momentos no tenemos productos para esta sección.
		  <br>
		  <br>
		  En abstracta estamos en constante incorporacion de nuevos productos, 
		  así que no deje de revisar nuestro catálogo.
		</div> 
	    </c:if>  
    </div>
</div>