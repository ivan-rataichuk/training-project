<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="sort-form">
	<form action="SortFacultys" method="post">
		<span>
			<u:loc str="sort"/>:
		</span>
		<span>
			<input type="radio" id="sort_1" name="sort" 
				<c:if test="${sessionScope.sort == 'sort_alph_asc'}">
					<c:out value=" checked "/>
				</c:if>
			value="sort_alph_asc" onchange="this.form.submit();">
			<label for="sort_1"><u:loc str="alphabet"/>  &#x21E9;</label>
		</span>
		<span>
			<input type="radio" id="sort_2" name="sort" 
				<c:if test="${sessionScope.sort == 'sort_alph_desc'}">
					<c:out value=" checked "/>
				</c:if>
			value="sort_alph_desc" onchange="this.form.submit();">
			<label for="sort_2"><u:loc str="alphabet"/> &#x21E7;</label>
		</span>
		<span>
			<input type="radio" id="sort_3" name="sort" 
				<c:if test="${sessionScope.sort == 'sort_pos'}">
					<c:out value=" checked "/>
				</c:if>
			value="sort_pos" onchange="this.form.submit();">
			<label for="sort_3"><u:loc str="positions"/></label>
		</span>
		<span>
			<input type="radio" id="sort_4" name="sort" 
				<c:if test="${sessionScope.sort == 'sort_bud_pos'}">
					<c:out value=" checked "/>
				</c:if>
			value="sort_bud_pos" onchange="this.form.submit();">
			<label for="sort_4"><u:loc str="bud-pos"/></label>
		</span>
	</form>
</div>
	
	<div>
		<c:forEach items="${sessionScope.facultys}" var="faculty">
		<div class="faculty-list">
			<p class="faculty-name">
				<c:out value="${faculty.getInfo().getName()}" />
			</p>
			<p class="faculty-description">
				<c:out value="${faculty.getInfo().getDescription()}" />
			</p>
			<p>
				<c:forEach items="${faculty.getInfo().getSubjectNames()}" var="subjectName">
				<span>
					<c:out value="${subjectName}"/>
				</span>
				</c:forEach>
			</p>

			<p>
				<span>
					<u:loc str="pos-info"/>: <c:out value="${faculty.getPositions()}"/>
				</span>
				<span>
					<u:loc str="bud-pos-info"/>: <c:out value="${faculty.getBudgetPositions()}"/>
				</span>
			</p>
			
			<form action="Register" method="post">
				<input type="hidden" name="facultyId" value="${faculty.getId()}"/>
				<p class="center">
					<button class="submit"><u:loc str="register"/></button>
				</p>
			</form>
		</div>	
		</c:forEach>
	</div>