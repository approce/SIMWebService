<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="fragments/staticHead.jsp"/>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="container">
    <div class="row centered-form">
        <div class="col-sm-6 col-sm-offset-9">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">Sign In</h3>
                </div>
                <div class="panel-body">
                    <c:if test="${param.error == 1}">
                        <div class="alert alert-danger" role="alert">
                            <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                            Please enter valid login and password.
                        </div>
                    </c:if>
                    <form name="user" novalidate="true" method="POST" action="security_check">
                        <div>
                            <label class="control-label">Username or email</label>
                            <input name="username" class="form-control input-sm">
                        </div>
                        <div style="margin-top: 8px">
                            <label class="control-label">Password <a href="#" style="font-size:12px">(Can't
                                remember?)</a></label>
                            <input name="password" type="password" class="form-control input-sm">
                        </div>
                        <button type="submit" style="margin-top: 15px"
                                class="btn btn-primary">Submit
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
