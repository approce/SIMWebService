<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="myTag" uri="/tlds/ContestTags" %>
<!DOCTYPE html>
<html>
<head>
    <myTag:adminHead/>
    <script src="../resources/js/created/admin/users.js" type="text/javascript"></script>
    <link href="../resources/wenzhixin/bootstrap-table.min.css">
    <script src="../resources/wenzhixin/bootstrap-table.min.js"></script>
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
                            <i class="fa fa-dashboard"></i> <a href="dashboard">Dashboard</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-user"></i> Users
                        </li>
                    </ol>
                </div>
            </div>
            <!-- /.row -->
            <div class="col-lg-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title"><i class="fa fa-fw fa-bar-chart-o"></i> Registration dynamic</h3>
                    </div>
                    <div class="panel-body">
                        <div id="morris-bar-chart"></div>
                        <div class="text-right">
                            <a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-8">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Last registered</h3>
                    </div>
                    <div class="panel-body">
                        <table data-toggle="table"
                               data-url="users/all"
                               data-pagination="true"
                               data-side-pagination="server"
                               data-page-list="[5, 10, 20, 50, 100, 200]"
                               data-search="true">
                            <thead>
                            <tr>
                                <th data-sortable="true" data-field="id">ID</th>
                                <th data-sortable="true" data-field="username">Username</th>
                                <th data-sortable="true" data-field="balance">Balance</th>
                                <th data-field="requests">Requests</th>
                            </tr>
                            </thead>
                        </table>
                        <div class="text-right">
                            <a href="#">View Details <i class="fa fa-arrow-circle-right"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>
</body>