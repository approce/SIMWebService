<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="myTag" uri="/tlds/ContestTags" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="fragments/staticHead.jsp"/>
</head>
<body ng-app="myApp">
<script type="text/javascript">
    function dos() {
        alert('2');
    }
    $(document).ready(function () {
        var something = $('.servicePicker').each(function () {
            this.style.cursor = 'pointer';
            this.onclick = dos;
        });
    });
</script>
<jsp:include page="fragments/header.jsp"/>
<div class="container">
    <div class="col-sm-20 col-sm-offset-2">
        <div class="panel panel-primary">
            <div class="panel-heading">
                <label>Please, choose service</label>
            </div>
            <div class="panel-body">

            </div>
            <div class="panel-footer">
                <label>There is no you Service?</label>
                <button class="btn btn-primary btn-xs">Add service</button>
            </div>
        </div>
    </div>
</div>
<!-- /.container -->
</body>
</html>