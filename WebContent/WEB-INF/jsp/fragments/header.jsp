<%@taglib prefix="myTag" uri="/tlds/ContestTags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<myTag:headerNav/>

<security:authorize access="!isAuthenticated()">
    <%-- login page included in all pages: --%>
    <myTag:login/>
</security:authorize>
