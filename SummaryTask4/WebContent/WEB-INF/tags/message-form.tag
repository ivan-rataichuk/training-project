<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${sessionScope.visitor.getCredentials() != 'admin'}">
	<form id="message-form" action="SendMessage" method="post">
		<p>
			<u:loc str="yourEmail"/> : <br> <input name="mailFrom" required/>
		</p>
		<p>
			<u:loc str="subject"/>: <br> <input name="subject" required/>
		</p>
		<p>
			<u:loc str="message"/>: <br>
			<textarea name="message" required></textarea>
		</p>
		<p class="center">
			<button class="submit"><u:loc str="send"/></button>
		</p>

	</form>
</c:if>