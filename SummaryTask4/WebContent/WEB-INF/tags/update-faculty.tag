<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<u:showFacultys />

<div>
	<c:forEach items="${sessionScope.facultys}" var="faculty">
		<hr>
		<p>
			<c:out value="${faculty.getInfo().getName()}" />
		</p>
		<p>
			<c:out value="${faculty.getInfo().getDescription()}" />
		</p>
		<p>
			<c:forEach items="${faculty.getInfo().getSubjectNames()}"
				var="subjectName">
				<span> <c:out value="${subjectName}" />
				</span>
			</c:forEach>
		</p>

		<p>
			<span> 
				<u:loc str="pos-info"/>: <c:out value="${faculty.getPositions()}" />
			</span> 
			<span> 
				<u:loc str="bud-pos-info"/>: <c:out value="${faculty.getBudgetPositions()}" />
			</span> 
		</p>

		<form action="SelectFaculty" method="post">
			<input type="hidden" name="facultyId" value="${faculty.getId()}" /> 
			<button class="submit"><u:loc str="update"/></button>
		</form>
		<form action="DeleteFaculty" method="post">
			<input type="hidden" name="facultyId" value="${faculty.getId()}" /> 
			<button class="submit"><u:loc str="delete"/></button>
		</form>
	</c:forEach>
</div>