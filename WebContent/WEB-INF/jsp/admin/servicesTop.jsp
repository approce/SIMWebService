<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="myTag" uri="/tlds/ContestTags" %>
<!DOCTYPE html>
<html>
<head>
    <myTag:adminHead/>
    <script src="../resources/js/created/admin/services.js" type="text/javascript"></script>
    <link href="../resources/wenzhixin/bootstrap-table.min.css">
    <script src="../resources/wenzhixin/bootstrap-table.min.js"></script>
</head>


<body ng-app>

<myTag:headerNav adminSlidePanel="true" page="services"/>


<div id="wrapper" ng-controller="servicesTopController">
    <div id="page-wrapper">
        <div class="container-fluid">
            <!-- TODO change this Page Heading -->
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
                            <i class="fa fa-arrow-circle-right"></i> Services
                        </li>
                    </ol>
                </div>
            </div>
            <div class="row">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title"><i class="fa fa-bar-chart-o fa-fw"></i> Top services</h3>
                    </div>
                    <div class="panel-body">
                        <div id="services-top-chart"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>