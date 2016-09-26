<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<u:showReport />

<c:forEach items="${requestScope.report}" var="entry">
<div>
	<form action="ResetRegistration" method="post">
	
			<span> 
				<c:out value="${entry.getFacultyName()}" />
			</span> 
			<span> 
				<c:out value="${entry.getFullName()}" />
			</span> 
			<span> 
				<c:out value="${entry.getGradesSum()}" />
			</span>
			<span>
				<input type="hidden" name="entrantId" value="${entry.getEntrantId()}">
				<button class="submit"><u:loc str="reset"/></button>
			</span>
		
	</form>
</div>
</c:forEach>

<hr>
<u:showCroppedRegistrations />

<c:forEach items="${requestScope.reportCropped}" var="entryC">
<div>
	<form action="ResetRegistration" method="post">
	
			<span> 
				<c:out value="${entryC.getFacultyName()}" />
			</span> 
			<span> 
				<c:out value="${entryC.getFullName()}" />
			</span> 
			<span> 
				<c:out value="${entryC.getGradesSum()}" />
			</span>
			<span>
				<input type="hidden" name="entrantId" value="${entryC.getEntrantId()}">
				<button class="submit"><u:loc str="reset"/></button>
			</span>
		
	</form>
</div>
</c:forEach>


<hr>