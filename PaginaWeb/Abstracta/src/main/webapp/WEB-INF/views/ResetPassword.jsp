<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     	
<jsp:include page="Header.jsp" flush="true"/>

<div class="width1000Center">

	<div class="row">
		<div class="resetPasswordMsg">
			<c:if test="${successMessage!=null}">
				<div data-alert="" class="alert-box success radius">
					${successMessage}
					<a href="#" class="close">×</a>
				</div>
			</c:if>
			<c:if test="${errorMessage!=null}">
				<div data-alert="" class="alert-box error radius">
					${errorMessage}
					<a href="#" class="close">×</a>
				</div>
			</c:if>
		</div>
	</div>

	<div class="row">
		<div class="small-8 push-2 columns simpleBorder">	
			<h4>Restablecer Contraseña</h4>
			
			<form:form method='post' action='/Cuenta/OlvideMiClave' commandName='newEmailReset'>
			  <div class="row">
				  <div class="large-6 columns">
				      <label>nueva contraseña</label>
				      <span class="formError"><form:errors path='email'/></span>
				      <form:input type="text" path="email" placeholder="tu nueva contraseña" maxlength="50"/>
				  </div>
			  </div>
			  <div class="row">
				  <div class="large-6 columns">
				      <label>confirmar nueva contraseña</label>
				      <span class="formError"><form:errors path='email'/></span>
				      <form:input type="text" path="email" placeholder="confirmacion de tu nueva contraseña" maxlength="50"/>
				  </div>
			  </div>
			  <br>
			  <div class="row">
			    <div class="large-6 columns">
			      <input id="passwordRecovery" type="submit" value="restablecer" class="sendButton"> 
			    </div>
			  </div>
			</form:form>
		</div>
	</div>
</div>

<!-- FOOTER -->     	
<jsp:include page="Footer.jsp" flush="true"/>