<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<u:showSubjects />
<form action="CreateEntrant" method="post">
	<p>
		<u:loc str="last-name"/> uk:<br> <input name="lastName_uk" required/>
	</p>

	<p>
		<u:loc str="first-name"/> uk:<br> <input name="firstName_uk" required/>
	</p>

	<p>
		<u:loc str="middle-name"/> uk:<br> <input name="middleName_uk" required/>
	</p>

	<p>
		<u:loc str="adress"/> uk:<br> <input name="adress_uk" required/>
	</p>

	<p>
		<u:loc str="oblast"/> uk:<br> <input name="oblast_uk" required/>
	</p>

	<p>
		<u:loc str="school"/> uk:<br> <input name="school_uk" required/>
	</p>
	<hr>
	<p>
		<u:loc str="last-name"/> en:<br> <input name="lastName_en" required/>
	</p>

	<p>
		<u:loc str="first-name"/> en:<br> <input name="firstName_en" required/>
	</p>

	<p>
		<u:loc str="middle-name"/> en:<br> <input name="middleName_en" required/>
	</p>

	<p>
		<u:loc str="adress"/> en:<br> <input name="adress_en" required/>
	</p>

	<p>
		<u:loc str="oblast"/> en:<br> <input name="oblast_en" required/>
	</p>

	<p>
		<u:loc str="school"/> en:<br> <input name="school_en" required/>
	</p>
	<hr>
	<p>
		<u:loc str="last-name"/> ru:<br> <input name="lastName_ru" required/>
	</p>

	<p>
		<u:loc str="first-name"/> ru:<br> <input name="firstName_ru" required/>
	</p>

	<p>
		<u:loc str="middle-name"/> ru:<br> <input name="middleName_ru" required/>
	</p>

	<p>
		<u:loc str="adress"/> ru:<br> <input name="adress_ru" required/>
	</p>

	<p>
		<u:loc str="oblast"/> ru:<br> <input name="oblast_ru" required/>
	</p>

	<p>
		<u:loc str="school"/> ru:<br> <input name="school_ru" required/>
	</p>
	<hr>
	<p>
		Email:<br> <input type="email" name="email" required/>
	</p>
	
	<p>
		<u:loc str="telephone"/> :<br> <input name="tel" placeholder="(+38)012-345-6789" required/>
	</p>

	<hr>

	<c:forEach items="${requestScope.subjects}" var="subject">
		<span class="grades-names"><c:out value="${subject.getName()}" /></span>
		<input name="sub-${subject.getId()}" required/><br>
	</c:forEach>

	<p>
		<button class="submit"><u:loc str="create"/></button>
	</p>

</form>
