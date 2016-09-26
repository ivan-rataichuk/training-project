<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="lang-form">
	<form action="ChangeLang" method="post">
		<span class="lang-input">
			<input type="radio" id="lang_uk" name="lang" value="uk" onchange="this.form.submit();"
				<c:if test="${sessionScope.locale.getLanguage() == 'uk'}">
					<c:out value=" checked "/>
				</c:if>
			> <label for="lang_uk">УКР</label> 
		</span>
		<span class="lang-input">
			<input type="radio"	id="lang_en" name="lang" value="en" onchange="this.form.submit();"
				<c:if test="${sessionScope.locale.getLanguage() == 'en'}">
					<c:out value=" checked "/>
				</c:if>
			> <label for="lang_en">EN</label> 
		</span>
		<span class="lang-input">
			<input type="radio" id="lang_ru" name="lang" value="ru" onchange="this.form.submit();"
				<c:if test="${sessionScope.locale.getLanguage() == 'ru'}">
					<c:out value=" checked "/>
				</c:if>
			> <label for="lang_ru">РУС</label>
		</span>
	</form>
</div>