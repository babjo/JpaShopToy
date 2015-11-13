<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header">
    <ul class="nav nav-pills pull-right">
        <%--<li class="active"><a href="/">Home</a></li>--%>
        <li><a href="/">Home</a></li>
		<sec:authorize access="isAnonymous()">
			<li><a href="${CONTEXT }/j_spring_security_check">로그인</a></li>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<li><a href="${CONTEXT }/j_spring_security_logout">로그아웃</a></li>
		</sec:authorize>
		<%--<li><a href="#">About</a></li>--%>
        <%--<li><a href="#">Contact</a></li>--%>
    </ul>
    <a href="/"><h3 class="text-muted">HELLO SHOP</h3></a>
</div>