<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="../fragments/head.jsp"/>
<body>

<div class="container">

    <jsp:include page="../fragments/bodyHeader.jsp" />

    <form role="form" action="/members/new" method="post">
        <div class="form-group">
            <label for="inputName">이름</label>
            <input type="text" name="name" class="form-control" id="inputName" placeholder="이름을 입력하세요">
        </div>
        <div class="form-group">
            <label for="inputPassword">패스워드</label>
            <input type="text" name="password" class="form-control" id="inputPassword" placeholder="패스워드을 입력하세요">
        </div>
        <div class="form-group">
            <label for="city">도시</label>
            <input type="text" name="city" class="form-control" id="city" placeholder="도시를 입력하세요">
        </div>
        <div class="form-group">
            <label for="street">거리</label>
            <input type="text" name="street" class="form-control" id="street" placeholder="거리를 입력하세요">
        </div>
        <div class="form-group">
            <label for="zipcode">우편번호</label>
            <input type="text" name="zipcode" class="form-control" id="zipcode" placeholder="우편번호를 입력하세요">
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-default">Submit</button>
    </form>
    <br/>
    <jsp:include page="../fragments/footer.jsp" />
</div> <!-- /container -->

</body>
</html>
