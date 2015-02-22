<%@taglib prefix="myTag" uri="/tlds/ContestTags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" rel="home" href="#">SimService</a>
    </div>
    <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav">
            <li><a href="#">Home</a></li>
            <li><a href="services">Service</a></li>
            <li><a href="#">API</a></li>
            <li><a href="#">Contacts</a></li>
            <security:authorize access="!isAuthenticated()">
                <li><a href="#" data-toggle="modal" data-target="#loginModal">Login</a></li>
            </security:authorize>
            <security:authorize access="isAuthenticated()">
                <li><a href="requests">My profile</a></li>
            </security:authorize>
        </ul>
    </div>
</div>
<!-- /.navbar -->


<security:authorize access="!isAuthenticated()">
    <%-- login page included in all pages: --%>
    <myTag:login/>
</security:authorize>
