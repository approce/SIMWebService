<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="myTag" uri="/tlds/ContestTags" %>
<!DOCTYPE html>
<html>
<head>
    <myTag:adminHead/>
    <script src="../resources/js/created/admin/requests.js" type="text/javascript"></script>
    <link href="../resources/wenzhixin/bootstrap-table.min.css">
    <script src="../resources/wenzhixin/bootstrap-table.min.js"></script>
</head>


<body ng-app>

<myTag:headerNav adminSlidePanel="true" page="requestsPool"/>

<div id="wrapper" ng-controller="requestsController">
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
                            <i class="fa fa-edit"></i> Requests
                        </li>
                    </ol>
                </div>
            </div>

            <div class="row">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title"><i class="fa fa-long-arrow-right"></i> Request static pool</h3>
                    </div>
                    <div class="panel-body">
                        <table data-toggle="table"
                               data-url="requests/pool"
                               data-show-refresh="true"
                               data-pagination="true">
                            <thead>
                            <tr>
                                <th data-field="index" data-sortable="true">index</th>
                                <th data-field="id" data-sortable="true">ID</th>
                                <th data-field="service" data-sortable="true">Service</th>
                                <th data-field="username" data-sortable="true">Username</th>
                                <th data-field="status" data-sortable="true">Status</th>
                                <th data-field="transactions">Transactions</th>
                            </tr>
                            </thead>
                        </table>
                        <script>
                            function toDate(value) {
                                var date = new Date(value).toISOString();
                                return date.slice(5, 10) + " " + date.slice(11, 19);
                            }
                        </script>
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