<%@ tag description="I am view for each social servise proposition" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade bootstrap-dialog type-primary" id="loginModal" ng-controller="loginController">
    <div class="modal-dialog modal-sm ">
        <div class="modal-content">
            <div class="modal-header " style="padding: 10px">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="bootstrap-dialog-title">Sign in</h4>
            </div>
            <div class="modal-body" style="padding:20px">
                <div class="alert alert-danger" role="alert" style="padding: 5px" ng-show="wrongPassword">
                    <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                    Unable to log in.
                    <a href="signup">Forgot password?</a>
                </div>
                <form id="loginForm" name="loginForm" method="POST" class="form-signin">
                    <input name="username" ng-model="username" class="form-control" placeholder="Username" required
                           autofocus>
                    <input name="password" ng-model="password" class="form-control" placeholder="Password"
                           type="password" required>
                </form>
                <button type="button" ladda="isLoading" data-style="zoom-in" ng-click="doLogin()"
                        class="btn btn-primary pull-right" style="display:inline;">Submit
                </button>
            </div>
            <div class="modal-footer">
                <div class="form-group" style="text-align:center;margin-bottom: 0px">
                    <a href="signup">Need an account?</a>
                </div>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div>
<!-- /.modal -->

<script>

</script>