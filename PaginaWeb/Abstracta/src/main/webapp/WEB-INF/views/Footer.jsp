<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="footwrap">
	<div id="footer">
		<div id="normalFooter">
		<div id="normalFooterTop">
			<div id="normalFooterTopLeft">
				
				<!-- NEWSLETTER -->
				<div class="title">subscríbete</div>
				<div class="emailFormContainer">                    
	                 <div class="footerEmailMessage"></div>
	                 <input onkeypress="subscribe(event)" id="newsletterMail" type="email" placeholder="ingresa tu email">
	                 <a onclick="subscribe(event)" class="emailSubmit">enviar</a>    
	                 <div class="newsletterMsg"></div>
                </div>
			</div>
			
			<!-- links -->
			<div id="normalFooterTopRight">
                    <ul class="footerMainLinks">
                        <li><a href="/Gift-Cards/">gift cards</a></li>
                        <li><a href="/Tiendas/">tiendas</a></li>
                        <li><a href="/Cuenta/">mi cuenta</a></li>
                    </ul>
                    <div class="footerCol1 footerCol">
	                    <ul>
	                        <li class="title"><span>ayuda</span> </li>
	                        
	                        <li><a href="/Servicio-Al-Cliente/">servicio al cliente</a></li>
	                        
	                        <c:forEach items="${costumerservice}" var="i">
	                        <li><a href="/Servicio-Al-Cliente/${i.nameFormatted}/">${i.name}</a></li>
	                        </c:forEach>
	                    </ul>
                    </div>
                    <div class="footerCol2 footerCol">
	                    <ul>
	                        <li class="title">conoce Abstracta</li>
	                        <li><a href="/Acerca-de/">acerca de nosotros</a> </li>
	                    </ul>
	                    <ul>
	                        <li class="title">arquitectos/diseñadores</li>
	                        <li><a href="/Programa-De-Fidelidad/">Conoce nuestro programa de fidelidad</a></li>
	                    </ul>
                    </div>
                    <div class="footerCol3 footerCol">
                        <ul>
                            <li class="title">mantente en contacto</li>
                            <li><a href="#">contáctanos</a> </li>
                            <li><a href="/Feedback/" id="lnkGiveUsFeedBack">danos tu opinión</a> </li>
                        </ul>
                    </div>
                </div>
            </div>
		</div>
		<div style="clear:both;"></div>
		<div id="normalFooterBottom">
                <div class="dropUp">
                    <a target="_blank" class="dropUpLink" href="http://www.boconcept.com/es-ve/">
                        <span class="boconcept_logo">&nbsp;</span>
                        <span class="dropUp_arrow">&nbsp;</span>
                    </a>
                </div>
                <div class="dropUp">
                    <a target="_blank" class="dropUpLink" href="http://www.capuy.com/">
                        <span class="capuy_logo">&nbsp;</span>
                        <span class="dropUp_arrow">&nbsp;</span>
                    </a>
                </div>
                <div class="footerTerms">
                    <ul>
                        <li class="privacyPolicy"><a href="">políticas de privacidad</a> </li>
                        <li><span>©2014 ABSTRACTA</span> </li>
                    </ul>
                </div>
                <!-- Facebook -->
                <div class="fbLike">
			        <div class="fb-like" 
			        data-href="http://abstracta.com.ve/"
			        data-width="100" data-height="200" data-colorscheme="light" data-layout="button_count" 
			        data-action="like" data-show-faces="false" data-send="false">
			        </div>  
				</div>
				
				 <!-- Twitter -->
		        <a href="https://twitter.com/share" class="twitter-share-button" 
		        data-url="http://abstracta.com.ve/"
		        data-text="Abstracta.com.ve | Los mejores podructos para el hogar." 
		        data-counturl="http://abstracta.com.ve/"
		        data-via="AbstractaHogar" 
		        data-lang="es" data-count="horizontal">Tweet</a>
				
            </div>
	</div>
</div>


<div class="main-footer-bottom">
   	
   	<div class="row">
   		<br>
   		<br>
   		<div class="large-4 push-7 columns">
     		<ul class="home-social">
         		<li><a href="#" onclick="window.open('http://www.twitter.com/abstractahogar')"class="twitter"></a></li>
         		<li><a href="#" onclick="window.open('http://www.facebook.com/pages/Abstracta/264961156099')" class="facebook"></a></li>
         		<li><a href="#" class="mail"></a></li>
       		</ul>
       	</div>
       	<div class="large-8 pull-3 columns">
       		<p class="copyright">1999-2014 ABSTRACTA. Todos los derechos reservados</p>
   		</div> 
     </div>
</div>

<!-- FINAL DEL BODY -->
</div>

<!-- SCRIPTS -->
<script src="/resources/web/js/foundation.min.js"></script>
	
<script>
	$(document).foundation();
      
   	// For Kitchen Sink Page
  	$('#start-jr').on('click', function() {
   		$(document).foundation('joyride','start');
   	});
</script>

	
<a class="feedback" data-reveal-id="feedback" href="/Feedback/">Feedback</a>


<div id="feedback" class="reveal-modal">

<form class="custom" data-abide="" novalidate="novalidate">
          <fieldset>
            <legend>Danos tu opinión</legend>

            <div class="row">
              <div class="large-12 columns">
                <label for="name">Nombre y Apellido <small>requerido</small></label>
                <input type="text" id="name" placeholder="Ejemplo: Juan López" name="nombre" required="">
                <small class="error" data-error-message="">No se</small>
              </div>
            </div>
            <div class="row">
              <div class="large-6 columns">
                <label for="email">Correo Electrónico <small>requerido</small></label>
                <input type="email" id="email" placeholder="ejemplo@correo.com">
                <small class="error">Correo válido requerido.</small>
              </div>
              <div class="large-4 columns">
                <label for="phone">Telefono</label>
                <input type="tel" id="phone" placeholder="1 234-567-8910">
                <small class="error" data-error-message="">Por favor ingrese un telefono con un formato válido.</small>
              </div>
            </div>

            <div class="row">
              <div class="large-12 columns">
                <hr>
              </div>
            </div>
            
            <div class="row">
            	<div class="large-12 columns">
            	<label for="name">Qué le parece nuestra página web <small>requerido</small></label>
            	</div>	
            </div>
			<br>
            <div class="row">
              <div class="large-2 columns">
                <label for="radio1"><input name="radio1" type="radio" id="radio1" required="" class="hidden-field"><span class="custom radio"></span> &nbsp;&nbsp;mala</label>
              </div>
              <div class="large-2 columns">
                <label for="radio1"><input name="radio1" type="radio" id="radio1" required="" class="hidden-field"><span class="custom radio"></span> regular</label>
              </div>
              <div class="large-2 columns">
                <label for="radio1"><input name="radio1" type="radio" id="radio1" required="" class="hidden-field"><span class="custom radio"></span> buena</label>
              </div>
              <div class="large-2 columns">
                <label for="radio1"><input name="radio1" type="radio" id="radio1" required="" class="hidden-field"><span class="custom radio"></span> excelente</label>
              </div>
              
            </div>

            <div class="row">
              <div class="large-12 columns">
                <hr>
              </div>
            </div>

            <div class="row">
              <div class="large-12 columns">
                <label for="remarks">Comentarios</label>
                <textarea id="remarks" name="remarks" placeholder="Escriba sus comentarios aquí."></textarea>
              </div>
            </div>

            <div class="row">
              <div class="large-12 columns">
                <button type="submit" class="medium button green">Enviar</button>
              </div>
            </div>

          </fieldset>
        </form>
        <a class="close-reveal-modal">×</a>
</div>

</body>
</html>
