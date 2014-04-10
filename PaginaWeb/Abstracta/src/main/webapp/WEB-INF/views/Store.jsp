<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- HEADER -->
<jsp:include page="Header.jsp" />

<!-- Separate the header -->
<c:if test="${slider==null}">
<div class="interSpace10"> </div>
</c:if> 

<!-- MENU -->
<div class="width1000Center">
<jsp:include page="Menu.jsp" flush="true"/>
</div>

<!-- SLIDER -->
<c:if test="${slider!=null}"> 
	<jsp:include page="Slider.jsp" flush="true"/>	
</c:if> 

<div class="width1000Center">
<!-- PANELES DE COLORES -->
<c:if test="${colorPanels}">  
	<jsp:include page="ColorBox.jsp" flush="true"/>  
</c:if>

<!-- CONTENIDO SUB-CATEGORIAS -->
<c:if test="${title!=null && slider!=null}">  
	<jsp:include page="SubCategory.jsp" flush="true"/>  
</c:if>

<!-- PRODUCT DETAIL INFO -->
<c:if test="${product!=null}">  
	<jsp:include page="Product.jsp" flush="true"/>  
</c:if>

<!-- PAGE NOT FOUND -->
<c:if test="${pageNotFound}">  
	<jsp:include page="NotFound.jsp" flush="true"/>  
</c:if>

<!-- PRODUCT SHOW ROOM -->
<c:if test="${showRoom}">  
	<jsp:include page="ProductShowRoom.jsp" flush="true"/>  
</c:if> 

<!-- No Results From Search -->
<c:if test="${bodySpace}">  
	<div class="bodySpace200"></div>
</c:if>  

<!-- Reveal Modal -->    
<c:if test="${colorPanels}">  
	<jsp:include page="Reveal.jsp" flush="true"/>
</c:if>

</div>

<!-- FOOTER -->     	
<jsp:include page="Footer.jsp" flush="true"/>