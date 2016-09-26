<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<u:showRegistrations />

<c:forEach items="${requestScope.registrations}" var="reg">
	<hr>
	<div>
		<c:out value="${reg.getFacultyName()}" />
		<form action="DeleteRegistration" method="post">
			<input type="hidden" name="regId" value="${reg.getId()}" /> 
			<button class="submit"><u:loc str="delete"/></button>
		</form>
	</div>
</c:forEach>


<hr>
