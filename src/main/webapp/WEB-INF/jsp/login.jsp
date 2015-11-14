<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<jsp:include page="fragments/head.jsp"/>

<body>

<div class="container">

    <jsp:include page="fragments/bodyHeader.jsp" />

    <form role="form" action="/login" method="post">
        <div class="form-group">
            <label for="inputName">이름</label>
            <input type="text" name="username" class="form-control" id="inputUsername" placeholder="이름을 입력하세요">
        </div>
        <div class="form-group">
            <label for="inputPassword">패스워드</label>
            <input type="text" name="password" class="form-control" id="inputPassword" placeholder="패스워드을 입력하세요">
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>

    <br/>
    <jsp:include page="fragments/footer.jsp" />

</div> <!-- /container -->

</body>
</html>