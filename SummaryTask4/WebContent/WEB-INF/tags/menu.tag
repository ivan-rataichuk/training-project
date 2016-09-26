<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	<c:choose>
		<c:when test="${sessionScope.visitor.getCredentials() == 'user'}">
			<div>
				<form action="PageSelect" method="post">
					<input type="hidden" name="page" value="facultyList">
					<button class="submit"><u:loc str="facultyList"/></button>
				</form>
			</div>
			
			<c:if test="${sessionScope.visitor.getEntrantId() < 1}">
				<div>
					<form action="PageSelect" method="post">
						<input type="hidden" name="page" value="createEntrant">
						<button class="submit"><u:loc str="createEntrant"/></button>
					</form>
				</div>
			</c:if>
			
			<c:if test="${sessionScope.visitor.getEntrantId() > 0}">
				<div>
					<form action="PageSelect" method="post">
						<input type="hidden" name="page" value="updateEntrant">
						<button class="submit"><u:loc str="updateEntrant"/></button>
					</form>
				</div>
				<div>
					<form action="PageSelect" method="post">
						<input type="hidden" name="page" value="uploadFile">
						<button class="submit"><u:loc str="uploadFile"/></button>
					</form>
				</div>
				<div>
					<form action="PageSelect" method="post">
						<input type="hidden" name="page" value="showRegistrations">
						<button class="submit"><u:loc str="showRegistrations"/></button>
					</form>
				</div>
			</c:if>
			
	    </c:when>
		<c:when test="${sessionScope.visitor.getCredentials() == 'admin'}">
			<div>
				<form action="PageSelect" method="post">
					<input type="hidden" name="page" value="registerUser">
					<button class="submit"><u:loc str="createAdmin"/></button>
				</form>
			</div>
			<div>
				<form action="PageSelect" method="post">
					<input type="hidden" name="page" value="createSubject">
					<button class="submit"><u:loc str="createSubject"/></button>
				</form>
			</div>
			<div>
				<form action="PageSelect" method="post">
					<input type="hidden" name="page" value="createFaculty">
					<button class="submit"><u:loc str="createFaculty"/></button>
				</form>
			</div>
			<div>
				<form action="PageSelect" method="post">
					<input type="hidden" name="page" value="updateFaculty">
					<button class="submit"><u:loc str="showFacultys"/></button>
				</form>
			</div>
			<div>
				<form action="PageSelect" method="post">
					<input type="hidden" name="page" value="showEntrants">
					<button class="submit"><u:loc str="showEntrants"/></button>
				</form>
			</div>
			<div>
				<form action="PageSelect" method="post">
					<input type="hidden" name="page" value="showReport">
					<button class="submit"><u:loc str="showRegList"/></button>
				</form>
			</div>
			<div>
				<form action="PageSelect" method="post">
					<input type="hidden" name="page" value="showFacultysReport">
					<button class="submit"><u:loc str="showReport"/></button>
				</form>
			</div>
	    </c:when>
	    <c:otherwise>
	    	<div>
	    		<div>
				<form action="PageSelect" method="post">
					<input type="hidden" name="page" value="facultyList">
					<button class="submit"><u:loc str="facultyList"/></button>
				</form>
			</div>
	       		<form action="PageSelect" method="post">
					<input type="hidden" name="page" value="registerUser">
					<button class="submit"><u:loc str="createUser"/></button>
				</form>
	       	</div>
	    </c:otherwise>
	</c:choose>


	