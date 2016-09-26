<%@ include file="/WEB-INF/jspf/directive/page.jspf"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>

<html>
<%@ include file="/WEB-INF/jspf/head.jspf"%>
<body>
	<u:showFacultys/>
	
	<tg:error/>

	<div id="header">
		<tg:login-form/>
		<tg:authentification/>
		<img src="img/icon.png">
		<tg:language/>
	</div>

	<div id="side-menu">
		<tg:menu/>
	</div>
	
	<div id="main-content">
		<c:choose>
			<c:when test="${(sessionScope.page == 'facultyList') || (empty sessionScope.page)}">
				<tg:faculty-list/>
			</c:when>
			<c:when test="${sessionScope.page == 'createEntrant'}">
				<tg:create-entrant/>
			</c:when>
			<c:when test="${sessionScope.page == 'createFaculty'}">
				<tg:create-faculty/>
			</c:when>
			<c:when test="${sessionScope.page == 'createSubject'}">
				<tg:create-subject/>
			</c:when>
			<c:when test="${sessionScope.page == 'registerUser'}">
				<tg:register-user/>
			</c:when>
			<c:when test="${sessionScope.page == 'showEntrants'}">
				<tg:show-entrants/>
			</c:when>
			<c:when test="${sessionScope.page == 'showFaculty'}">
				<tg:show-faculty/>
			</c:when>
			<c:when test="${sessionScope.page == 'showFacultysReport'}">
				<tg:show-facultys-report/>
			</c:when>
			<c:when test="${sessionScope.page == 'showRegistrations'}">
				<tg:show-registrations/>
			</c:when>
			<c:when test="${sessionScope.page == 'showReport'}">
				<tg:show-report/>
			</c:when>
			<c:when test="${sessionScope.page == 'updateEntrant'}">
				<tg:update-entrant/>
			</c:when>
			<c:when test="${sessionScope.page == 'updateFaculty'}">
				<tg:update-faculty/>
			</c:when>
			<c:when test="${sessionScope.page == 'uploadFile'}">
				<tg:upload-file/>
			</c:when>
		</c:choose>
		
	</div>
	
	<div id="message">
		<tg:message-form/>
	</div>
	
	<div id="footer">
		<span>
			&copy; Ivan Rataichuk 
		</span>
	</div>

</body>
</html>