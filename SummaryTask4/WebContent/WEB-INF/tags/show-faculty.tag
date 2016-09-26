<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<u:showFaculty />
<u:showSubjects />

<form action="UpdateFaculty" method="post">

	<c:forEach items="${requestScope.faculty.getInfoList()}" var="info">
		<p>
		<u:loc str="name"/> ${info.getLang()}:<br>
		<input name="name_${info.getLang()}" value="${info.getName()}" required/>
	</p>
	<p>
		<u:loc str="description"/> ${info.getLang()}:<br>
		<textarea name="description_${info.getLang()}" required>${info.getDescription()}</textarea>
	</p>
	</c:forEach>
	<hr>
	<select name="subject_m" id="sub-1" class="sub-select" required>
		<option value=""><u:loc str="select-sub"/></option>
		<c:forEach items="${requestScope.subjects}" var="item">
			<option value="<c:out value="${item.getId()}"/>"><c:out
					value="${item.getName()}" /></option>
		</c:forEach>
	</select> 
	<select name="subject_s" id="sub-2" class="sub-select" required>
		<option value=""><u:loc str="select-sub"/></option>
		<c:forEach items="${requestScope.subjects}" var="item">
			<option value="<c:out value="${item.getId()}"/>"><c:out
					value="${item.getName()}" /></option>
		</c:forEach>
	</select> 
	<select name="subject_t" id="sub-3" class="sub-select" required>
		<option value=""><u:loc str="select-sub"/></option>
		<c:forEach items="${requestScope.subjects}" var="item">
			<option value="<c:out value="${item.getId()}"/>"><c:out
					value="${item.getName()}" /></option>
		</c:forEach>
	</select>
	<hr>

	<p>
		<u:loc str="pos-info"/>:<br> 
		<input name="pos_quantity" value="${requestScope.faculty.getPositions()}" required/>
	</p>
	<p>
		<u:loc str="bud-pos-info"/>:<br> 
		<input name="bud_pos_quantity" value="${requestScope.faculty.getBudgetPositions()}" required/>
	</p>

	<hr>
	
	
	
	<p>
		<input type="hidden" name="facultyId" value="${requestScope.faculty.getId()}" />
		<button class="submit"><u:loc str="update"/></button>
	</p>

</form>