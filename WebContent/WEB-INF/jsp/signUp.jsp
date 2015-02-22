<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <jsp:include page="fragments/staticHead.jsp"/>

    <%-- busy progress bar: --%>
    <link rel="stylesheet" href="resources/css/busyInput.css">
    <link rel="stylesheet" href="resources/css/registration.css">
    <%-- busy progress bar: --%>

    <%-- angular signUp script --%>
    <script src="resources/js/created/signUpAngular.js"></script>
    <%-- angular --%>
</head>
<body ng-app="myApp">
<jsp:include page="fragments/header.jsp"/>
<div class="container col-sm-8 col-sm-offset-8" style="min-width: 360px">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h3 class="panel-title">Sign up</h3>
        </div>
        <div class="panel-body">
            <form name="user" autocomplete="off" novalidate="true" method="POST" action="signup" class="regForm">
                <div class="username form-group  has-feedback"
                     ng-class="{'has-error': user.username.$invalid && user.username.$dirty}">
                    <label class="control-label">Username:</label>
                    <input type="text" name="username" class="form-control input-sm" ng-model="username"
                           ng-minlength=3 ng-maxlength=20 ng-pattern="/^[a-zA-Z0-9_]{0,}$/" unique-username autofocus
                           required/>
                    <i class="glyphicon glyphicon-refresh glyphicon-refresh-animate form-control-feedback "
                       ng-show="busy"></i>

                    <div class="help-inlines">
                                <span class="help-inline control-label" ng-show="user.username.$error.minlength">
                                    Username should be at least 3 characters length.
                                </span>
                                <span class="help-inline control-label" ng-show="user.username.$error.maxlength">
                                    Username should be less than 20 characters.
                                </span>

                                <span class="help-inline control-label" ng-show="user.username.$error.isTaken">
                                    Username already exist, please try another one.
                                </span>

                                <span class="help-inline control-label" ng-show="user.username.$error.pattern">
                                    Wrong username format. Please user latin symbols, digits and symbol "_"
                                </span>

                                <span class="help-inline control-label"
                                      ng-show="user.username.$dirty && user.username.$error.required">
                                    Please enter username
                                </span>
                    </div>
                </div>
                <div class="email form-group" ng-class="{'has-error': user.email.$invalid && user.email.$dirty}">
                    <label class="control-label">Email:</label>
                    <input name="email" class="form-control input-sm" ng-model="email" type="email" required/>

                    <div class="help-inlines">
                                <span class="help-inline control-label" ng-show="user.email.$error.email">
                                    Wrong email format
                                </span>
                                <span class="help-inline control-label"
                                      ng-show="user.email.$dirty && user.email.$error.required">
                                    Please enter your email
                                </span>
                    </div>
                </div>
                <div class="password form-group"
                     ng-class="{'has-error': user.password.$invalid && user.password.$dirty}">
                    <label class="control-label">Password:</label>
                    <input name="password" class="form-control input-sm" type="password"
                           ng-model="password" ng-pattern="/^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,}$/"
                           required/>

                    <div class="help-inlines">
                                <span class="help-inline control-label" ng-show="user.password.$error.pattern">
                                    Password must contain at least one letter, at least one number, and be longer than six charaters.
                                </span>
                                <span class="help-inline control-label"
                                      ng-show="user.password.$dirty && user.password.$error.required">
                                    Please enter password
                                </span>
                    </div>
                </div>
                <div class="passwordConfirm form-group"
                     ng-class="{'has-error': user.passwordConfirm.$invalid && user.passwordConfirm.$dirty}">

                    <label class="control-label">Confirm password:</label>

                    <input name="passwordConfirm" class="form-control input-sm" type="password"
                           ng-model="passwordConfirm" match="password" required="" ng-onfocus>

                    <div class="help-inlines">
                                <span class="help-inline control-label" ng-show="user.passwordConfirm.$error.match">
                                    Passwords doesn't match
                                </span>
                                <span class="help-inline control-label"
                                      ng-show="user.passwordConfirm.$dirty && user.passwordConfirm.$error.required">
                                    Please repeat your password
                                </span>
                    </div>
                </div>
                <div class="centered-form col-sm-24 center-block">
                    <button type="submit" class="btn btn-primary center-block ladda-button"
                            data-style="expand-left" ng-disabled="user.$invalid">
                        <span class="ladda-label">Submit</span>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>