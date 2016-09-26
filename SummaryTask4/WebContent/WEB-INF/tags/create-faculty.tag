<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<u:showSubjects />

<form id="createUser_form" action="CreateFaculty" method="post">

	<p>
		Назва:<br> <input name="name_uk" required/>
	</p>
	<p>
		Name:<br> <input name="name_en" required/>
	</p>
	<p>
		Название:<br> <input name="name_ru" required>
	</p>
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
		<u:loc str="pos-info"/>:<br> <input name="pos_quantity" required/>
	</p>
	<p>
		<u:loc str="bud-pos-info"/>:<br> <input name="bud_pos_quantity" required/>
	</p>

	<hr>
	<p>
		Опис:<br>
		<textarea name="description_uk" required></textarea>
	</p>
	<p>
		Description:<br>
		<textarea name="description_en" required></textarea>
	</p>
	<p>
		Описание:<br>
		<textarea name="description_ru" required></textarea>
	</p>
	<p>
		<button class="submit"><u:loc str="create"/></button>
	</p>

</form>
