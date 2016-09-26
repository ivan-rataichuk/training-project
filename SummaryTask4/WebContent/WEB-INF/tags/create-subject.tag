<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="CreateSubject" method="post">
	<p>
		Назва:<br>
		<input name="name_uk" required>
	</p>
	<p>
		Name:<br>
		<input name="name_en" required>
	</p>
	<p>
		Название:<br>
		<input name="name_ru" required>
	</p>
	<p>
		<button class="submit"><u:loc str="create"/></button>
	</p>

</form>
