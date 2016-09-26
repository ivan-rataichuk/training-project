<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<u:showFacultysReport />

<c:forEach items="${requestScope.fReport}" var="entry">
	
	<div class="faculty-list">
	<p>
		<c:out value="${entry.getFacultyName()}" />
	</p>
	
	<span><u:loc str="bud-pos-info"/></span>
	<c:forEach items="${entry.getBudget()}" var="budEntry">
		<div class="report-entry">
			<form action="CheckRegistration" method="post">
				<input type="hidden" name="regId" value="${budEntry.getId()}"> <input
					type="hidden" name="entrantId" value="${budEntry.getEntrantId()}">
				<input type="hidden" name="posType" value="budget"> 
				<span>
					<c:out value="${budEntry.getFullName()}" />
				</span> 
				<span> 
					<c:out value="${budEntry.getGradesSum()}" />
				</span> 
				<c:if test="${!budEntry.isChecked()}">
					<button class="submit"><u:loc str="check"/></button>
				</c:if>
				<c:if test="${budEntry.isChecked()}">
					<span class="img-check"><img src="img/check.png"></span>
				</c:if>
			</form>
		</div>
	</c:forEach>
	<hr>
	<span><u:loc str="contract"/></span>
	<c:forEach items="${entry.getContract()}" var="cEntry">
		<div class="report-entry">
			<form action="CheckRegistration" method="post">
				<input type="hidden" name="regId" value="${cEntry.getId()}"> <input
					type="hidden" name="entrantId" value="${cEntry.getEntrantId()}">
				<input type="hidden" name="posType" value="contract"> 
				<span>
					<c:out value="${cEntry.getFullName()}" />
				</span> 
				<span> 
					<c:out value="${cEntry.getGradesSum()}" />
				</span> 
				<c:if test="${!cEntry.isChecked()}">
					<button class="submit"><u:loc str="check"/></button>
				</c:if>
				<c:if test="${cEntry.isChecked()}">
					<span class="img-check"><img src="img/check.png"></span>
				</c:if>
			</form>
		</div>
	</c:forEach>
</div>
</c:forEach>

<hr>
<div>
	<a class="button" href="ConfirmFinalRegistration">
		<u:loc str="confirm-registrations"/>
	</a>
	
	<a class="button" href="SaveReport">
		<u:loc str="save-report"/>
	</a>
	
	<a class="button" href="report/Report.pdf" target="_blank">PDF</a>

</div>