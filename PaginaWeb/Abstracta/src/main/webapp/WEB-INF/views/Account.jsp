<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- HEADER -->     	
<jsp:include page="Header.jsp" flush="true"/>

<br>

<div class="width1000Center">

	<div class="row">
		<h4>Panel de Administración de Tu Cuenta</h4>
		<p>Hola <strong>${userName}</strong>, desde el panel de adrministración de tu cuenta, tienes la posibilidad de conocer las actividad recientes y actualizar la información de tu cuenta. Utiliza el menú a tu izquierda para ver o editar la información.</p>
	</div>
	<hr>
	
	<div class="row">	
		<dl class="tabs vertical" data-tab="">
		  <dd <c:if test="${userInfo!=null}"> class="active"</c:if>><a href="/Cuenta/">mis datos</a></dd>
		  <dd <c:if test="${wishList}"> class="active"</c:if>><a href="/Cuenta/Favoritos?from=Account">mis favoritos</a></dd>
		  <dd <c:if test="${orders}"> class="active"</c:if>><a href="/Cuenta/Ordenes">mis ordenes</a></dd>
		  <dd><a href="/Cuenta/Logout">cerrar sesión</a></dd>
		</dl>
		
		<div class="tabs-content vertical">
		  <div class="content active" id="content">
			
			<c:if test="${userInfo!=null}"> 
				<jsp:include page="UserInfo.jsp" flush="true"/>
			</c:if>
				
			<c:if test="${orders}"> 
				<jsp:include page="Orders.jsp" flush="true"/>
			</c:if>
			
			<c:if test="${wishList}"> 
				
				<c:if test="${!noItems}">
					<jsp:include page="WishListDetail.jsp" flush="true"/>
				</c:if>
				
				<c:if test="${noItems}">
					<div id="noItems" style="<c:if test="${!noItems}">display:none</c:if>">
						<div class="row">
							<div class="large-12 large-centered columns">
								<div class="panel callout radius">
									No hay artículos en tus favoritos.
								</div>
							</div>
						</div>
					</div>
				</c:if>
		
			</c:if>
		  
		  </div>
		
		</div>
		<div class="clearfix"></div>
	</div>
</div>

<div class="bodySpace100"></div>

<script>
	$(document).foundation().foundation('joyride', 'start');
</script>

<!-- FOOTER -->     	
<jsp:include page="Footer.jsp" flush="true"/>