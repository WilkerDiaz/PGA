<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

Nombres: ${userInfo.name} <br>
Apellidos: ${userInfo.lastname} <br>
Cedula: ${userInfo.identityCard} <br>
Sexo: ${userInfo.sex} <br>
Fecha de Nacimiento: <fmt:formatDate value="${userInfo.birthdate}" pattern="dd/MM/yyyy"/> <br>
Telefono ${userInfo.phone} <br>
Email: ${userInfo.email} <br>
Eres usuario desde: <fmt:formatDate value="${userInfo.created}" pattern="dd/MM/yyyy"/>
