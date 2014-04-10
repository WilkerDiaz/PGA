<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
		jQuery(document).ready(function() {
		    jQuery('#mycarousel').jcarousel();
		});
		</script>
		
		<script type="text/javascript">
		jQuery(document).ready(function() {
		    jQuery('#mycarousel2').jcarousel();
		});
</script>

<script type="text/javascript" src="/resources/web/js/jquery.jcarousel.min.js"></script>

<div class="row">
   
   <div class="small-10 push-2 columns">
   
   <!-- Site Nav -->
	   <nav class="breadcrumbs">
	   	  <a href="/">principal</a>
	   	  <c:forEach items="${path}" var="category" varStatus="loop">
			  <a href="${prev}/${category.nameFormatted}">${category.name}</a>
		   	  <c:set var="prev" value="${prev}/${category.nameFormatted}" scope="request" />
	      </c:forEach>
	   </nav>
   </div>
</div>

<c:if test="${wishList}"> 
	<div id="addedToWishList" class="row">
	   <br>
	   <div class="small-10 push-2 columns">
			<div data-alert="" class="alert-box success radius">
				${wishListMsg}
				<a href="#" class="close">×</a>
			</div>
		</div>
	</div>
</c:if>

<div class="row">
   <div class="small-6 push-2 columns">

	   <h3>${title}</h3>  

	    <div class="zoom">
	    	<img id="bigImage" class="drag-image" data-info-src="${product.MProductId}/1"  src="/Images/i/500/500/${product.MProductId}/1"/>
	    </div>
	    
       <br>
       <div class="outer">
	   		<span class="imgZoomIn">&nbsp;</span>
	       	<span class="imgZoomOut">&nbsp;</span>
		    <span class="fix"></span>
	   </div>
      
	   <c:forEach var="i" begin="1" end="${productPhotos}"> 
		<a class="th radius">
			<img class="thumb" data-info-src="${product.MProductId}/${i}" data-big-src="/Images/i/500/500/${product.MProductId}/${i}" src="/Images/i/60/60/${product.MProductId}/${i}">
		</a>
		</c:forEach>
    </div>
    
    <div class="small-4 columns">
    	<div class="bodySpace100"></div>
	    
	    <ul class="pricing-table">

		  <li class="price">
		  <span class="productPromoPrice">Bs: <fmt:formatNumber value="${product.finalprice}" type="number" pattern="#,##0.00"/></span> 
		  <c:if test="${product.promo=='Y'}"><span class="productOrigPrice">reg. Bs: 
		  <fmt:formatNumber value="${product.originalprice}" type="number" pattern="#,##0.00"/></span></c:if>
		  </li>

		  <li class="title myAccordion">
		    <div class="draw"><span class="pointerArrowRight" id="overviewArrow"></span>Descripción</div>
		  </li>
		  <li class="justified-bullet-item drawContent" id="overview"> 
		  <c:if test="${product.overview!=null}">${product.overview}</c:if>
		  <c:if test="${product.overview==null}">Este es un producto de calidad, alta durabilidad y diseño artístico.<br><br>Exlusivo de Abstracta.</c:if>
		  </li> 
		  <li class="title myAccordion">
		    <div class="draw"><span class="pointerArrowRight"></span>Detalles</div>
		  </li>
		  <li class="bullet-item drawContent">
			  Color: ${product.color}
			  <br>
			  Materiales: ${product.material1} ${product.material2}
			  <br>
			  Altura: ${product.height} mts
			  <br>
			  Ancho: ${product.width} mts
			  <br>
			  Peso: ${product.weight} kg
			  <br>
			  <c:if test="${product.model!=null}"> 
			  Modelo: ${product.model}
			  <br>
			  </c:if>
		  </li>
		  <li class="title myAccordion">
		    <div class="draw"><span class="pointerArrowRight"></span>Recomendaciones de Uso</div>
		  </li>
		  <li class="bullet-item drawContent">
			  Se recomienda para este producto...
			  <br>a
			  <br>b
			  <br>c
			  <br>d
			  <br>e
			  <br>f
		  </li>   
		  <li class="title myAccordion">
		    <div class="draw"><span class="pointerArrowRight"></span>Políticas de Devolución</div>
		  </li>
		  <li class="justified-bullet-item drawContent">
			  En Abstracta buscamos ofrecerte la mejor experiencia de compra, sin embargo si no estás satisfecho con tu compra te guiamos para que el proceso de cambio, devolución o crédito sea sencillo para ti.
			  <br><br>
			  Dispondrás  de 15 días contados a partir desde la recepción de tu envió, para solicitar cambios o devoluciones.  Para aquellas que correspondan a defectos de fábrica contaras 90 días de garantía a partir de la facturación.
			  <br>
			  <br>
			  <a href="/Servicio-Al-Cliente/devoluciones-y-cambios/">Leer más sobre las políticas de devolución.</a>
		   </li>  
		   
		  <li class="cta-button">
		  
		  <input class="productQty" id="qtyAdd" maxlength="2" onkeypress="return isNumberKey(event)" value="1">
		  <a onclick="addItem()" id="addItem" class="button" data-item-id="${product.MProductId}">+ carrito</a>
		  <br>
		  <a href="/Cuenta/AgregarAFavoritos?item=${product.MProductId}">agregar a favoritos</a>
		  <br>
		  <br>
		  </li>		
		<li>
        <br>
	      <div id="socialPost">   
		        <!-- Twitter -->
		        <a href="https://twitter.com/share" class="twitter-share-button" 
		        data-url="http://abstracta.com.ve/productos/${product.nameFormatted}/${product.MProductId}"
		        data-text="${product.name} | Abstracta" 
		        data-counturl="http://abstracta.com.ve/productos/${product.nameFormatted}/${product.MProductId}"
		        data-via="AbstractaHogar" 
		        data-lang="es" data-count="horizontal">Tweet</a>

		        <!-- Facebook -->
		        <div class="fb-like" 
		        data-href="http://abstracta.com.ve/"
		        data-width="100" data-height="200" data-colorscheme="light" data-layout="button_count" 
		        data-action="like" data-show-faces="false" data-send="false">
		        </div>     
		     </div>
		  <div style="clear:both"></div>
      	<br>
		</li>
		</ul>
	</div>    
</div>

<div class="row">
	<br>
	<br>
	<br>
	<br>
</div>

<c:if test="${trendSet!=null}"> 
<div class="row">
    <div class="large-12 large-centered columns">

       <!-- Related Trend Set Products --> 
        <hr>
        <h4>Búsquedas relacionadas</h4>  
        <div id="wrap">
		  <ul id="mycarousel" class="jcarousel-skin-tango">
		  	<c:forEach items="${trendSet}" var="product">
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
       <br>
    </div>
</div>
</c:if>

<c:if test="${complements!=null}"> 
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
<br>
<br>
<br>
<br> 

<div id="addItemReveal" class="reveal-modal"></div>
<div id="fb-root"></div>

<script >
(function($) {
     
	$('#addedToWishList').delay(500).show("blind",1000);
	$('#addedToWishList').delay(10000).hide("blind",1000);
	
	$('.drawContent').hide();
	$('#overview').delay(800).show(800);
	$('#overviewArrow').delay(900).toggleClass("pointerArrowDown", 0);
	
	$('.myAccordion').click(function() {
	  $(this).next().slideToggle();
	  $(this).children().children().toggleClass("pointerArrowDown");
	  return false;
	});

})(jQuery);
</script>