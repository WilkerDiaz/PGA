<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- HEADER -->     	
<jsp:include page="Header.jsp" flush="true"/>

<br>

<div class="width1000Center">
	<div class="large-12 large-centered columns">	
		<div class="margin100">
		
			<c:if test="${accountConfirmed!=null}">
			<div data-alert="" class="alert-box success radius">
				${accountConfirmed}
				<a href="#" class="close">×</a>
			</div>
			</c:if>
			
			<img src="${randomImage}">
			<br><br>
			<a href="/">inicio</a>
		</div>
	</div>
</div>


<div>
<jsp:include page="Footer.jsp" />