<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="myTag" uri="/tlds/ContestTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="fragments/staticHead.jsp"/>
    <%-- wenzhixin table --%>
    <link href="resources/wenzhixin/bootstrap-table.min.css">
    <script src="resources/wenzhixin/bootstrap-table.min.js"></script>
</head>
<body ng-app="myApp">
<jsp:include page="fragments/header.jsp"/>
<div class="container">
    <myTag:navbar row="1"/>
    <div class="col-sm-offset-1 col-sm-18 col-md-offset-1 col-md-14 col-lg-offset-1 col-lg-14">
        <div class="panel panel-primary">
            <div class="panel-heading">
                History of your requests
            </div>
            <table data-toggle="table" data-url="requestHistory">
                <thead>
                <tr>
                    <th data-field="id">ID</th>
                    <th data-field="service">Service</th>
                    <th data-field="price">Price</th>
                    <th data-field="status">Status</th>
                    <th data-field="started">Started</th>
                    <th data-field="number">Number</th>
                    <th data-field="code">Code</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</body>
</html>