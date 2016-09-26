<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="CreateUser" method="post">
	<p>
		<u:loc str="login"/>:<br> <input name="login" required/>
	</p>

	<p>
		<u:loc str="password"/>:<br> <input name="password" required/>
	</p>

	<p>
		Email:<br> <input type="email" name="email" required/>
	</p>

	<p>
		<button class="submit"><u:loc str="create"/></button>
	</p>

</form>