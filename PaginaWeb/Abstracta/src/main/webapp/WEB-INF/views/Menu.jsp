<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<div id="floating-menu">
	<ul>
	  	<c:forEach items="${menu}" var="i">
			<c:if test="${i.classlevel2Id!=requestScope.prevItemLvl2 && requestScope.prevItemLvl2!=null}">
				</ul>
				</li>
			</c:if>	
			<c:if test="${i.classlevel1Id!=requestScope.prevItemLvl1 && requestScope.prevItemLvl1!=null}">
				</ul>
				</li>
			</c:if>	
			<c:if test="${i.classlevel1Id!=requestScope.prevItemLvl1}">
				<c:if test="${i.classlevel1Id!=requestScope.prevItemLvl1 && i.classlevel1Id!=1}">
					<li class='has-sub'><a href='/${i.classlevel1NameFormatted}'>
						<span>${i.classlevel1Name}</span></a>
				</c:if>
				<c:if test="${i.classlevel1Id==1}">
					<li><a href='/${i.classlevel1Name}'>
						<span>${i.classlevel1Name}</span></a>
				</c:if>
				<ul>
			</c:if>	
            <c:if test="${i.classlevel2Id!=requestScope.prevItemLvl2 && i.classlevel1Id!=1}">
				<li <c:if test="${i.classlevel3Id>0}">class='has-sub'</c:if> >
					<a href='/${i.classlevel1NameFormatted}/${i.classlevel2NameFormatted}'>
						<span>${i.classlevel2Name}</span></a>
				<ul>
			</c:if>
			<c:if test="${i.classlevel1Id!=1 && i.classlevel3Id>0}">
				<li><a id='1' href='/${i.classlevel1NameFormatted}/${i.classlevel2NameFormatted}/${i.classlevel3NameFormatted}'>
						<span>${i.classlevel3Name}</span></a></li>
			</c:if>
            <c:set var="prevItemLvl1" value="${i.classlevel1Id}" scope="request" />
            <c:set var="prevItemLvl2" value="${i.classlevel2Id}" scope="request" />       
		</c:forEach>
	</ul>
</div>
