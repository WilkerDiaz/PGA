<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="Header.jsp"/>

<div class="width1000Center">
<jsp:include page="Menu.jsp" flush="true"/>
</div>

<div class="interSpace10"></div>
<div class="width1000Center">
	
	<div class="row">
		   <div class="small-10 push-2 columns">
		
			<!-- Site Nav -->
		   	<nav class="breadcrumbs">
			   	<a href="/">principal</a>
			   	<a href="/">servicio al cliente</a>
				<a class="current" href="/Servicio-Al-Cliente/">${service.name}</a>
		   	</nav>
		   	
		   	<br>
		   	<h4>${service.title}</h4>

		   	<div id="costumerServiceParagraph">${service.contenttext}</div>
	    </div>
	</div>	
</div>

<c:if test="${service.contenttext!=null}"> 
<div class="bodySpace100"></div>
</c:if>
<c:if test="${service.contenttext==null}"> 
<div class="bodySpace500"></div>
</c:if>
<jsp:include page="Footer.jsp"></jsp:include>



