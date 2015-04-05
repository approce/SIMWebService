<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="myTag" uri="/tlds/ContestTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="fragments/staticHead.jsp"/>
    <%-- my css --%>
    <link rel="stylesheet" href="/resources/css/userRequests.css">
    <%-- busy progress bar: --%>
    <link rel="stylesheet" href="/resources/css/busyInput.css">
    <%-- angular requests script --%>
    <script src="/resources/js/created/profileRequests.js"></script>
    <%-- angular --%>
</head>
<body ng-app="myApp">
<jsp:include page="fragments/header.jsp"/>
<div class="container">
    <myTag:navbar row="0"/>
    <div class="col-sm-offset-1 col-sm-18 col-md-offset-1 col-md-14 col-lg-offset-1 col-lg-14">
        <div class="panel panel-primary">
            <div class="panel-heading">
                Your current requests
            </div>
            <div class="panel-body" ng-controller="requestsController">
                <div class="myRequest panel panel-default">
                    <ul class="list-group" ng-repeat="request in requests">
                        <myTag:serviceRequest/>
                    </ul>
                </div>
                <button class="btn btn-xs btn-success center-block" style="text-align: center;margin-bottom: 10px"
                        data-toggle="collapse" data-target="#accordion"><span
                        class="glyphicon glyphicon-plus"></span> Add new Request
                </button>
                <div id="accordion" class="collapse panel panel-default" style="padding: 10px 5px 15px 5px">
                    <ul class="form-group" style="list-style-type: none">
                        <li class="col-sm-8" ng-repeat="propose in proposes">
                            <img class="serviceImage" src="/resources/images/{{propose.iconPath}}" height="20"
                                 width="20"/>
                            <label class="serviceTitle" ng-click="createRequest(propose)"
                                   style="cursor: pointer">{{propose.fullName}}</label>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</body>
</html>