<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="adminSlidePanel" %>
<%@ attribute name="page" %>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" rel="home" href="/">SimService</a>
    </div>
    <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
            <security:authorize access="!isAuthenticated()">
                <li><a href="#" data-toggle="modal" data-target="#loginModal">Login</a></li>
            </security:authorize>

            <security:authorize access="isAuthenticated()">
                <li><a href="requests">My profile</a></li>
            </security:authorize>

        </ul>
        <ul class="nav navbar-right navbar-nav" style="margin-right: 10px">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-user"></i> John
                    Smith <b
                            class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li>
                        <a href="#"><i class="glyphicon glyphicon-user"></i> Profile</a>
                    </li>
                    <li>
                        <a href="#"><i class="glyphicon glyphicon-cog"></i> Settings</a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#"><i class="glyphicon glyphicon-off"></i> Log Out</a>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
    <c:if test="${not empty adminSlidePanel}">
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav side-nav">
                <li class="<c:out value="${page=='dashboard' ? 'active': ''}"/>">
                    <a href="dashboard"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                </li>

                <li class="<c:out value="${page=='users' ? 'active': ''}"/>">
                    <a href="users"><i class="fa fa-fw fa-user"></i>Users</a>
                </li>

                <li class="<c:out value="${page=='requestsPool' ? 'active': ''}"/>">

                </li>

                <li class="<c:out value="${page=='services' ? 'active': ''}"/>">
                    <a href="javascript:" data-toggle="collapse" data-target="#services"><i
                            class="fa fa-fw fa-arrows-v"></i> Services <i class="fa fa-fw fa-caret-down"></i></a>
                    <ul id="services" class="collapse">
                        <li>
                            <a href="services"><i class="fa fa-fw fa-arrow-circle-right"></i> General</a>
                        </li>
                        <li>
                            <a href="servicesTop"><i class="fa fa-fw fa-arrow-circle-right"></i> TOP</a>
                        </li>
                    </ul>
                </li>

                <li class="<c:out value="${page=='requests' ? 'active': ''}"/>">


                    <a href="javascript:" data-toggle="collapse" data-target="#requests"><i
                            class="fa fa-fw fa-dashboard"></i> Requests <i class="fa fa-fw fa-caret-down"></i></a>
                    <ul id="requests" class="collapse">
                        <li>
                            <a href="requests"><i class="fa fa-fw fa-edit"></i>Requests</a>
                        </li>
                        <li>
                            <a href="requestsPool"><i class="fa fa-fw fa-dashboard"></i> Requests Pool</a>
                        </li>
                    </ul>

                </li>

                <li>
                    <a href="charts.html"><i class="fa fa-fw fa-bar-chart-o"></i> Charts</a>
                </li>
                <li>
                    <a href="tables.html"><i class="fa fa-fw fa-table"></i> Tables</a>
                </li>
                <li>
                    <a href="forms.html"><i class="fa fa-fw fa-edit"></i> Forms</a>
                </li>
                <li>
                    <a href="bootstrap-elements.html"><i class="fa fa-fw fa-desktop"></i> Bootstrap Elements</a>
                </li>
                <li>
                    <a href="bootstrap-grid.html"><i class="fa fa-fw fa-wrench"></i> Bootstrap Grid</a>
                </li>
                <li>
                    <a href="javascript:" data-toggle="collapse" data-target="#demo"><i
                            class="fa fa-fw fa-arrows-v"></i> Dropdown <i class="fa fa-fw fa-caret-down"></i></a>
                    <ul id="demo" class="collapse">
                        <li>
                            <a href="#">Dropdown Item</a>
                        </li>
                        <li>
                            <a href="#">Dropdown Item</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="blank-page.html"><i class="fa fa-fw fa-file"></i> Blank Page</a>
                </li>
                <li>
                    <a href="index-rtl.html"><i class="fa fa-fw fa-dashboard"></i> RTL Dashboard</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </c:if>

</div>