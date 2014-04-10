<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:include page="Header.jsp"/>

<div class="width1000Center">
		
	<div class="row">
	
	
		<c:if test="${not empty confirmAccount}">
			<br>
			<div data-alert="" class="alert-box success radius reduceMarginBottom">
				${confirmAccount}
				<a href="#" class="close">×</a>
			</div>
		</c:if>
	
		<div class="small-5 push-3 columns simpleBorder">	
		   <h3>Iniciar sesión</h3>
	 		
	 		<p class="font09">Por favor ingresa los datos solicitados a continuación:</p>
	 		
			<c:if test="${not empty error}">
					<span class="formError">Tu intento de iniciar sesión falló, por favor trata nuevamente.</span>
					<br/> 
					<br>
			</c:if>
		 
			<form name='login' action="<c:url value='j_spring_security_check' />" method='POST'>
			  <div class="row">
			    <div class="large-9 columns">
			      <label>e-mail</label>
			      
			      <input type='email' name='j_username' placeholder="ingresa tu email" value='${attempUsername}'>
			      <!-- <input type='email' name='j_username' placeholder="ingresa tu email" value='${attempUsername}'> -->
			      						
			    </div>
			  </div>
			  <div class="row">
			    <div class="large-9 columns">
			      <label>contraseña</label>
			      <!-- <input type="password" name='j_password' placeholder="ingresa tu contraseña"/> -->
			      <input type="password" name='j_password' placeholder="ingresa tu contraseña"/>
			    </div>
			  </div>
			  <div class="row">
			    <div class="large-6 columns">
			      <input id="loginButton" type="submit" value="iniciar sesión" class="loginButton"> 
			    </div>
			  </div>
			</form>
			
			<br>
			<a href="/Cuenta/OlvideMiClave" style="font-size: 0.875em; text-decoration: underline;">olvidé mi contraseña</a>
		</div>
	</div>	
</div>

<jsp:include page="Footer.jsp"></jsp:include>