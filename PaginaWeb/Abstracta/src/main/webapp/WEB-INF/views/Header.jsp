<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<!--[if IE 8]><html class="no-js lt-ie9" lang="en"><![endif]-->
<!--[if gt IE 8]><!--><html class="no-js" lang="en"><!--<![endif]-->
	<head>	
    	<meta charset="utf-8" />
    	<meta name="http-equiv" content="Content-type: text/html; charset=UTF-8"/>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
    	
    	<title>Abstracta</title>
    	
    	<link rel="shortcut icon" href="/resources/web/css/icons/favicon.ico" type="image/x-icon">
		<link rel="icon" href="/resources/web/css/icons/favicon.ico" type="image/x-icon">
    	<link rel="stylesheet" href="/resources/web/css/docs.css" />
    	<link rel="stylesheet" href="/resources/web/css/style.css" />
    	<link rel="stylesheet" href="/resources/web/css/skin.css" />
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js"></script>
		<script type="text/javascript" src="/resources/web/js/validation.js" charset="utf-8"></script>
		<script src="https://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	
		
		<link rel="stylesheet" href="https://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
		
    </head>
	<body>
	
	<div id="headerWrap">
		<a href="/"><span class="abs_LogoMain">&nbsp;</span></a>
		<div class="headerRightBox">
			<div class="headerRightBoxTop">	    
				<div class="alignleft pad_top_7">
								
					<div id="accountMenu">
						<a href="/Cuenta/">
						   	<sec:authorize access="isAuthenticated()" var="logged">
						   	<div id="accountName"><sec:authentication property="principal.username" /></div>
						   	</sec:authorize>
						   	<c:if test="${!logged}">iniciar sesión</c:if>
					    </a>
					    <ul id="accountMenuList" data-dropdown-content="" class="f-dropdown accountDrop" style="position: absolute;">
				          <sec:authorize access="isAuthenticated()" var="logged">
				          	<li><a href="/Cuenta/" id="myAccount">mi cuenta</a></li>
				          	<li><a href="/Cuenta/Favoritos" id="myAccount">mis favoritos</a></li>
			          	  	<li><a href="/Cuenta/Logout" id="logout">cerrar sesión</a></li>
			          	  </sec:authorize>
				          <c:if test="${!logged}">
						  	<li><a href="/Cuenta/">iniciar sesión</a></li>
						  	<li><a href="/Cuenta/Registrate">regístrate</a></li>
				          	<li><a href="/Cuenta/OlvideMiClave">¿olvidaste tu clave?</a></li>
						  </c:if>
				        </ul>
					</div>
					
				</div>
				<div class="checkOut">
					<a href="/Carrito" class="checkOutLink">checkout</a>
				</div>
				<div class="alignright">
					<a class="" href="/Carrito" style="color: black;">
					<img alt="" src="/resources/web/css/icons/cart2.png">
					<span>mi carrito: <span id="basketCount">0</span></span></a>
				</div>
			</div>	
			<div class="headerRightBoxBottom">		
				<div class="headerSearch">
	               	<form id="searchForm" action="/Search" class="searchForm" method="get">
	                	<div id="header">
						   	<input id="searchtext" name="query" type="text" placeholder="buscar">
						    <input type="submit" value="" class="searchButton" id="searchButton">       
							<div style="clear:both;"></div>
						</div>
	                </form>
	            </div>
            </div>
		</div>
	</div>
	<div class="decoLine">&nbsp;</div>
		
