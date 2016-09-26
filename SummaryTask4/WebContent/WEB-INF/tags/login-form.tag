<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	<c:set var="type" value="${sessionScope.visitor.getCredentials()}" scope="page"/>
	<c:if test="${(type != 'user') && (type != 'admin')}">
		<div id="login-form">
			<form action="Login" method="post">
				<span>
					<u:loc str="login" />
					<input name="login" required/>
				</span>
				<span>
					<u:loc str="password" />
					<input type="password" name="password" required/>
				</span>
				<span>
					<button class="submit"><u:loc str="enter" /></button>
				</span>
			</form>
		</div>
	</c:if>
