<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
<c:if test="${not empty sessionScope.visitor}">
<div id="visitor">
	<span class="auth">
		<c:out value="${sessionScope.visitor.getCredentials()}"/>
	</span>
	<span class="auth">
		<c:out value="${sessionScope.visitor.getFirstName()}"/>
	</span>
	<span class="auth">
		<c:out value="${sessionScope.visitor.getLastName()}"/>
	</span>
	<span>
		<a class="button" href="Logout">Logout</a>
	</span>
</div>
</c:if>
	

	