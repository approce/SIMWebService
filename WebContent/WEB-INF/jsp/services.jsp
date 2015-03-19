<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="myTag" uri="/tlds/ContestTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html ng-app="myApp">
<head>
    <jsp:include page="fragments/staticHead.jsp"/>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<script type="text/javascript" src="resources/js/created/services.js"></script>
<div class="container">
    <div class="col-sm-20 col-sm-offset-2">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <label>Please, choose service</label>
            </div>
            <div class="panel-body">
                <c:forEach items="${services}" var="service">
                    <myTag:service id="${service.id}" fullName="${service.fullName}"
                                   iconPath="${service.iconPath}" price="${service.price}"/>
                </c:forEach>
            </div>
            <div class="panel-footer">
                <label>There is no your Service?</label>
                <button class="btn btn-primary btn-xs">Add service</button>
            </div>
        </div>
    </div>
</div>
<!-- /.container -->
</body>
</html>