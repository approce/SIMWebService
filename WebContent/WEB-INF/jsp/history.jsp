<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="myTag" uri="/tlds/ContestTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="fragments/staticHead.jsp"/>
    <%-- wenzhixin table --%>
    <link href="/resources/wenzhixin/bootstrap-table.min.css">
    <script src="/resources/wenzhixin/bootstrap-table.min.js"></script>
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
            <table data-toggle="table"
                   data-url="history/show"
                   data-pagination="true"
                   data-side-pagination="server"
                   data-page-list="[5, 10, 20, 50, 100, 200]"
                   >
                <thead>
                <tr>
                    <th data-field="id">ID</th>
                    <th data-field="service">Service</th>
                    <th data-field="price">Price</th>
                    <th data-field="status">Status</th>
                    <th data-field="started" data-formatter="toDate">Started</th>
                    <th data-field="number">Number</th>
                    <th data-field="code">Code</th>
                </tr>
                </thead>
            </table>
            <script>
                function toDate(value) {
                    var date = new Date(value).toISOString();
                    return date.slice(5, 10) + " " + date.slice(11, 19);
                }
            </script>
        </div>
    </div>
</body>
</html>