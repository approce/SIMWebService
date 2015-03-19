<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="myTag" uri="/tlds/ContestTags" %>
<!DOCTYPE html>
<html>
<head>
    <myTag:adminHead/>
    <script src="/resources/js/created/admin/users.js" type="text/javascript"></script>
    <link href="/resources/wenzhixin/bootstrap-table.min.css">
    <script src="/resources/wenzhixin/bootstrap-table.min.js"></script>
    <style>
        .control-label {
            width: 100px;
            text-align: right;
        }
    </style>
</head>


<body ng-app>
<myTag:headerNav adminSlidePanel="true" page="users"/>


<div id="wrapper" ng-controller="usersController">
    <div id="page-wrapper">
        <div class="container-fluid">
            <!-- Page Heading -->
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">
                        Users
                        <small>Statistics Overview</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-dashboard"></i> <a href="/admin/dashboard">Dashboard</a>
                        </li>
                        <li>
                            <i class="fa fa-user"></i> <a href="/admin/users"> Users</a>
                        </li>
                        <li>
                            ${user.username}
                        </li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->
            <div class="col-lg-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> User info</h3>
                    </div>
                    <div class="panel-body">
                        <div class="form-group">
                            <label class="control-label">id:</label>
                            <label>${user.id}</label>
                        </div>

                        <div class="form-group">
                            <label class="control-label">username:</label>
                            <label>${user.username}</label>
                        </div>

                        <div class="form-group">
                            <label class="control-label">email:</label>
                            <label>${user.email}</label>
                        </div>

                        <div class="form-group">
                            <label class="control-label">registration:</label>
                            <label> <fmt:formatDate pattern="yyyy-MM-dd hh:mm"
                                                    value="${user.registered.time}"/></label>
                        </div>

                        <div class="form-group">
                            <label class="control-label">balance:</label>
                            <label>${user.balance}</label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> User Requests</h3>
                    </div>
                    <div class="panel-body">
                        <table data-toggle="table"
                               data-url="/admin/users/requests?username=${user.username}"
                               data-pagination="true"
                               data-side-pagination="server"
                               data-page-list="[5, 10, 20, 50, 100, 200]">
                            <thead>
                            <tr>
                                <th data-field="id" data-sortable="true">ID</th>
                                <th data-field="service" data-sortable="true">Service</th>
                                <th data-field="started" data-sortable="true" data-formatter="toDate">Started</th>
                                <th data-field="status" data-sortable="true">Status</th>
                                <th data-field="transactions">Transactions</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</body>