<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag description="I am left nav bar for user profile menu" pageEncoding="UTF-8" %>

<%@ attribute name="row" required="true" %>
<ul class="nav nav-pills nav-stacked col-sm-5 col-md-5 col-lg-offset-1 col-lg-4">
    <li role="presentation" class="<c:out value="${row==0 ? 'active': ''}"/>">
        <a href="executable">My requests</a>
    </li>
    <li role="presentation" class="<c:out value="${row==1 ? 'active': ''}"/>">
        <a href="history">Balance history</a>
    </li>
</ul>