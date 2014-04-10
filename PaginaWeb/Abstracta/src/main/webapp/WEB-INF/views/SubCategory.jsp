<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<br>
<div class="row">
   <h2>${title}</h2>  
   <!-- Shows Dynamically the sub-categorys images -->
   <ul class="large-block-grid-3">
       <!-- Shows Dynamically the products images -->
       <c:forEach items="${showRoomSubCategorys}" var="i">
	       <li>
		       <a href="${i.link}"><img class="th" src="/Images/${i.target}/400/250/1/1"></a>
			   <br>
			   <a href="${i.link}" class="tiny button">${i.name} (ver más...)</a>
		   </li>
       </c:forEach>
    </ul> 
</div>