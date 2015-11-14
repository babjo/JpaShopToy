<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<div class="header">
	<ul class="nav nav-pills pull-right">
		<%--<li class="active"><a href="/">Home</a></li>--%>	
		<li><a href="/">Home</a></li>
		<sec:authorize access="isAnonymous()">
			<li><a href="${CONTEXT }/login">로그인</a></li>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<li>
				<form method="post"
					action="${pageContext.request.contextPath}/logout" id="form-logout">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form> 
				<a href="#"onclick="document.getElementById('form-logout').submit();"> <sec:authentication property="principal.username" /> 로그아웃</a>
			</li>
		</sec:authorize>


		<%--<li><a href="#">About</a></li>--%>
		<%--<li><a href="#">Contact</a></li>--%>
	</ul>
	<a href="/"><h3 class="text-muted">HELLO SHOP</h3></a>
</div>