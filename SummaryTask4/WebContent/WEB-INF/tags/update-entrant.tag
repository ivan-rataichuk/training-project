<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<u:showEntrant />
<p>Update Entrant</p>

<div>
	<form action="UpdateEntrant" method="post">

		<c:forEach items="${requestScope.entrant.getInfoList()}" var="eInfo">

			<p>
				<u:loc str="last-name"/> ${eInfo.getLang()}:<br> <input name="lastName_${eInfo.getLang()}"
					value="${eInfo.getLastName()}" required/>
			</p>

			<p>
				<u:loc str="first-name"/> ${eInfo.getLang()}:<br> <input name="firstName_${eInfo.getLang()}"
					value="${eInfo.getFirstName()}" required/>
			</p>

			<p>
				<u:loc str="middle-name"/> ${eInfo.getLang()}:<br> <input name="middleName_${eInfo.getLang()}"
					value="${eInfo.getMiddleName()}" required/>
			</p>

			<p>
				<u:loc str="adress"/> ${eInfo.getLang()}:<br> <input name="adress_${eInfo.getLang()}"
					value="${eInfo.getAdress()}" required/>
			</p>

			<p>
				<u:loc str="oblast"/> ${eInfo.getLang()}:<br> <input name="oblast_${eInfo.getLang()}"
					value="${eInfo.getOblast()}" required/>
			</p>

			<p>
				<u:loc str="school"/> ${eInfo.getLang()}:<br> <input name="school_${eInfo.getLang()}"
					value="${eInfo.getSchool()}" required/>
			</p>
			<hr>
		</c:forEach>


		<p>
			Email:<br> <input name="email"
				value="${requestScope.entrant.getEmail()}" required/>
		</p>
		<p>
			<u:loc str="telephone"/> :<br> <input name="tel" value="${requestScope.entrant.getTel()}" required/>
		</p>

		<hr>
		
		<p><u:loc str="grades"/></p>
		<c:forEach items="${requestScope.grades}" var="grade">
			<span class="grades-names"><c:out value="${grade.getSubjectName()}" /></span>
			<input name="gradeId-${grade.getId()}" value="${grade.getGrade()}" required/><br>
		</c:forEach>


		<button class="submit"><u:loc str="updateEntrant"/></button>
	</form>

</div>
