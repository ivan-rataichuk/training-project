<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="UploadFile" method="post" enctype="multipart/form-data">

	<p>
		<u:loc str="select-file"/>: <input type="file" name="uploadFile" />
	</p>
	<p>
		<button class="submit"><u:loc str="uploadFile"/></button>
	</p>

</form>