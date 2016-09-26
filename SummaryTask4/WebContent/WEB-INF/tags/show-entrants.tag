<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<u:showEntrants />
<u:showSubjects />
<div class="et-header">
	<span class="block"><u:loc str="block"/></span>
	<span class="name"><u:loc str="entrant-name"/></span>
	<span class="tel"><u:loc str="telephone"/></span>
	<c:forEach items="${requestScope.subjects}" var="subject">
		<span class="sub"> 
			<c:out value="${subject.getName()}" />
		</span>
	</c:forEach>
	<span class="cert">
		<u:loc str="certificate"/>
	</span>
</div>
<div class="et">
<c:forEach items="${requestScope.entrants}" var="entrant">
	<div class="et-info">
		<form action="BlockEntrant" method="post">
				<span class="block">
					<input type="checkbox"
						<c:if test="${entrant.isBlocked()}">
							<c:out value=" checked "/>
						</c:if>
					onchange="this.form.submit();"> 
					<input type="hidden" name="entrantId" value="${entrant.getId()}"> 
				</span>	
				<span class="name">
					<c:out value="${entrant.getInfo().getLastName()}" />&nbsp;
					<c:out value="${entrant.getInfo().getFirstName()}" />&nbsp;
					<c:out value="${entrant.getInfo().getMiddleName()}" />
				</span>
				
				<span class="tel">
					<c:out value="${entrant.getTel()}" />
				</span>

				<c:forEach items="${entrant.getGl()}" var="grade">
					<span class="sub"> 
						<c:out value="${grade.getGrade()}" />
					</span>
				</c:forEach>

				<span class="cert">
					<a target="_blank" href="${entrant.getCertificateURL()}">Certificate</a>
				</span>
				
			</form>
		</div>
	</c:forEach>
</div>