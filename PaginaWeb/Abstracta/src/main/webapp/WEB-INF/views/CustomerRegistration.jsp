<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!-- HEADER -->     	
<jsp:include page="Header.jsp" flush="true"/>

<div class="width1000Center">

	<div class="row">
		<div class="small-10 push-1 columns simpleBorder">	
			<h4>Registro</h4>
			<br>
			
			<form:form method='post' action='/Cuenta/Registrate' commandName='newCustomer'>  
			  <div class="row">
			    <div class="large-6 columns">
			      <label>nombre</label>
			      <span class="formError"><form:errors path='name'/></span>
			      <form:input type="text" path="name" placeholder="ejemp: Andrés" maxlength="40"/>
			    </div>
			    <div class="large-6 columns">
			      <label>apellidos</label>
			      <span class="formError"><form:errors path='lastname'/></span>
			      <form:input path="lastname" type="text" placeholder="ejemp: Rivera Gonzalez" maxlength="40"/>
			    </div>
			  </div>
			  
			  <div class="row">
			    <div class="large-6 columns">
			      <label>cédula / RIF</label>
			      <span class="formError"><form:errors path='identityCard'/></span>
			      <form:input path="identityCard" type="text" placeholder="introduzca su numero de documento"/>
			    </div>
			    <div class="large-6 columns">
			      <label>teléfono</label>
			      <span class="formError"><form:errors path='phone'/></span>
			      <form:input path="phone" type="tel" placeholder="ejemp: (212)-263-1234" />
			    </div>
			  </div>
			  
			  <div class="row">
			    <div class="large-6 columns">
			    <label>fecha de nacimiento</label>
			    <span class="formError"><form:errors path="birthdate"/></span>
			       <form:input path="birthdate" type="text" placeholder="ingresa tu fecha de nacimiento" cssClass="datepicker" readonly="true"/>
			  	</div>
			  
			   	<div class="large-6 columns">
			      <label>sexo</label>
			      <span class="formError"><form:errors path="sex"/></span>
			      <form:select path="sex">
			      	<form:option value="">selecciona</form:option>
			        <form:option value="F">Femenino</form:option>
			        <form:option value="M">Masculino</form:option>
			      </form:select>
			    </div> 
			  </div>
	
			  <div class="row">
			    <div class="large-6 columns">
			      <label>e-mail</label>
			      <span class="formError"><form:errors path="email"/></span>
			       <form:input path="email" type="email" placeholder="ejemp: perezpedro@gmail.com" />
			    </div>
			    <div class="large-6 columns">
			      <label>confirmar e-mail</label>
			      <span class="formError">
			      	<form:errors path="confirmEmail"/>
			      	<form:errors path="emailMatch"/>
			      </span>
			       <form:input path="confirmEmail" type="email" placeholder="ejemp: perezpedro@gmail.com" />
			    </div>
			  </div>
			  
			  <div class="row">
			    <div class="large-6 columns">
			      <label>contraseña</label>
			      <span class="formError"><form:errors path="password"/></span>
			       <form:input path="password" type="password" placeholder="escribe tu nueva contraseña aquí" maxlength="20"/>
			    </div>
			    <div class="large-6 columns">
			      <label>confirmar contraseña</label>
			      <span class="formError">
				      <form:errors path="confirmPassword"/>
				      <form:errors path="passwordMatch"/>
			      </span>
			      <form:input path="confirmPassword" type="password" placeholder="vuelve a escribir tu nueva contraseña" maxlength="20" />
			    </div>
			  </div>
			  
			  <br>
			
			 <div class="row">
			 	<form:checkbox path="newsletter" value="true"></form:checkbox><span class="font09">Quiero recibir ofertas y promociones por e-mail.</span>
			 </div>
			 <div class="row">	
			  	 <form:checkbox path="agreement"></form:checkbox>
			  	 <span class="font09">Acepto los <a href="#">Términos y Condiciones</a>.</span>
			 	 <span class="formError"><form:errors path="agreement"/></span>
			 </div>	
			 <br>
			 <div class="row">
			   <div class="small-2 small-centered columns">
			   	<input id="register" type="submit" value="continuar" class="sendButton">
			 	</div>
			 </div> 
			</form:form>
			
		</div>
	</div>
</div>

<!-- FOOTER -->     	
<jsp:include page="Footer.jsp" flush="true"/>