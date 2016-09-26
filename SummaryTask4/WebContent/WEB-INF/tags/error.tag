<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


	<c:if test="${requestScope.message.isError()}">
	<c:choose>
		<c:when test="${requestScope.message.getCause() == 'auth'}">
			<div id="mes">
				<span class="close">X</span>
				<div>
				<form action="RemindPassword" method="post">
					<p>
						<u:loc str="auth-error"/><br>
						<u:loc str="auth-error-mes"/>		
					</p>
					Email:
					<input name="email" />
					<p>
						<button class="submit"><u:loc str="send"/></button>
					</p>
				</form>
				</div>
			</div>
		</c:when>
		
		<c:when test="${requestScope.message.getCause() == 'val'}">
			<div id="mes">
				<span class="close">X</span>
				<div>
					<u:loc str="val-error"/>		
				</div>
			</div>
		</c:when>
		
		<c:when test="${requestScope.message.getCause() == 'user'}">
			<div id="mes">
				<span class="close">X</span>
				<div>
					<u:loc str="user-error"/>		
				</div>
			</div>
		</c:when>
		
		<c:when test="${requestScope.message.getCause() == 'reg'}">
			<div id="mes">
				<span class="close">X</span>
				<div>
					<u:loc str="reg-error"/>		
				</div>
			</div>
		</c:when>
		
		<c:when test="${requestScope.message.getCause() == 'file'}">
			<div id="mes">
				<span class="close">X</span>
				<div>
					<u:loc str="file-error"/>		
				</div>
			</div>
		</c:when>
	</c:choose>
	
	
	
	</c:if>
	
	