<%@ tag description="I am view for user service requests" pageEncoding="UTF-8" %>

<li class="list-group-item form-horizontal " style="padding-bottom: 1px">
    <div class="header row">
        <%-- service name --%>
        <div class="serviceTitle col-lg-6 col-sm-6 col-xs-6" style="min-width:150px;padding: 0px">
            <img class="serviceImage" src="resources/images/{{request.iconPath}}" height="20"
                 width="20">
            <label>{{request.serviceName}}</label>
        </div>
        <%-- service name --%>

        <div class="serviceStatus col-lg-12 col-sm-12 col-xs-10" style="min-width: 200px;max-width:220px;padding: 0px">
            <label>Status: </label>
            <label class="label label-warning" ng-show="request.status=='STOP'">Stop</label>
            <label class="label label-default" ng-show="request.status=='PREPARE'">Prepare request</label>
            <label class="label label-info" ng-show="request.status=='WAIT_NUMBER'">Wait for avaible number</label>
            <label class="label label-success" ng-show="request.status=='NUMBER_SUBMIT'">Wait for number
                submit</label>
            <label class="label label-info" ng-show="request.status=='WAIT_CODE'">Wait for code</label>
            <label class="label label-success" ng-show="request.status=='COMPLETED'">Completed</label>
            <label class="label label-danger" ng-show="request.status=='NUMBER_REJECT'">Number reject</label>
        </div>

        <%-- price --%>
        <div class="servicePrice col-lg-6 col-sm-4 col-xs-4" style="max-width: 100px;padding: 0px">
            <label>Price: 1.1$</label>
        </div>
        <%-- price --%>

    </div>
    <div class="requestsWrapper">
        <%-- div with number and code --%>
        <div class="row" ng-show="request.status!='PREPARE' && request.status!='STOP'">
            <div class="col-sm-12 col-xs-12"> <%-- number --%>
                <label class="control-label col-sm-6 col-xs-6"
                       ng-show="request.status!='PREPARING' && request.status!='STOP'">
                    Number:
                </label>
                <i class="glyphicon glyphicon-refresh glyphicon-refresh-animate"
                   ng-show="request.status=='WAIT_NUMBER'"></i>
                <label class="value-label label label-primary col-sm-6 col-xs-6"
                       ng-show="request.number!=0">
                    {{request.number}}
                </label>
            </div>
            <div class="col-sm-12 col-xs-12"> <%-- code --%>
                <label class="control-label col-sm-6 col-xs-6"
                       ng-show="request.status=='WAIT_CODE' || request.status=='COMPLETED'">
                    Code:
                </label>
                <i class="glyphicon glyphicon-refresh glyphicon-refresh-animate"
                   ng-show="request.status=='WAIT_CODE'"></i>
                <label class="value-label label label-success col-sm-6 col-xs-6" style="min-width: 100px"
                       ng-show="request.code!=null">
                    {{request.code}}
                </label>
            </div>
        </div>
        <div class="buttons row text-center" style="margin-bottom: 10px"> <%-- div with controll buttons --%>
            <div class="submitNumber" ng-show="request.status=='NUMBER_SUBMIT'">
                <button class="btn btn-primary btn-xs" ng-click="submitNumber(request,true)">
                    Submit
                </button>
                <button class="btn btn-default btn-xs" ng-click="submitNumber(request,false)">
                    Dismiss
                </button>
            </div>
            <div class="startRequest" ng-show="request.status=='STOP'">
                <button class="btn btn-primary btn-xs" ng-click="startRequest(request)">
                    Start
                </button>
                <button class="btn btn-danger btn-xs" ng-click="removeRequest(request)">
                    Remove
                </button>
            </div>
            <div class="closeRequest" ng-show="request.status=='COMPLETED' || request.status=='NUMBER_REJECT'">
                <button class="btn btn-success btn-xs" ng-click="finishRequest(request)">
                    Close
                </button>
            </div>
        </div>
    </div>
</li>