<%@ tag description="I am view for user service requests" pageEncoding="UTF-8" %>

<li class="list-group-item" style="padding-bottom: 1px">
    <div class="header">
        <img class="serviceImage" src="resources/images/{{request.iconPath}}" height="20"
             width="20">
        <label style="width:140px">{{request.serviceName}}</label>
        <label>Status: </label>
        <label class="label label-warning" ng-show="request.status=='STOP'">Stop</label>
        <label class="label label-default" ng-show="request.status=='PREPARE'">Prepare request</label>
        <label class="label label-info" ng-show="request.status=='WAIT_FOR_NUMBER'">Wait for avaible number</label>
        <label class="label label-success" ng-show="request.status=='WAIT_FOR_NUMBER_SUBMIT'">Wait for number
            submit</label>
        <label class="label label-info" ng-show="request.status=='WAIT_FOR_CODE'">Wait for code</label>
        <label class="label label-success" ng-show="request.status=='COMPLETED'">Completed</label>
    </div>
    <div class="requestsWrapper form-horizontal">
        <div class="row" ng-show="request.status=='STOP'">
            <button class="btn btn-primary btn-xs" ng-click="startRequest(request)">
                Request for avaible number
            </button>

        </div>

        <div class="row" ng-show="request.status!='PREPARE' && request.status!='STOP'">
            <label class="col-lg-3 col-md-4 col-sm-5 col-xs-4 control-label"
                   ng-show="request.status!='PREPARING' && request.status!='STOP'">
                Number:
            </label>
            <i class="glyphicon glyphicon-refresh glyphicon-refresh-animate"
               ng-show="request.status=='WAIT_FOR_NUMBER'"></i>
            <label class="value-label label label-primary col-lg-4 col-md-5 col-sm-6 col-xs-4"
                   ng-show="request.number!=0">
                {{request.number}}
            </label>
        </div>
        <div class="row col-lg-offset-1 col-md-offset-2 col-sm-offset-2
                                            col-lg-6 col-md-7 col-sm-9"
             ng-show="request.status=='WAIT_FOR_NUMBER_SUBMIT'">
            <button class="btn btn-primary btn-xs" ng-click="submitNumber(request,'true')">
                Submit
            </button>
            <button class="btn btn-default btn-xs" ng-click="submitNumber(request,'false')">
                Dismiss
            </button>
        </div>

        <div class="row">
            <label class="col-lg-3 col-md-4 col-sm-5 col-xs-4 control-label"
                   ng-show="request.status=='WAIT_FOR_CODE' || request.status=='COMPLETED'">
                Code:
            </label>
            <i class="glyphicon glyphicon-refresh glyphicon-refresh-animate"
               ng-show="request.status=='WAIT_FOR_CODE'"></i>
            <label class="value-label label label-success col-lg-4 col-md-5 col-sm-6 col-xs-4"
                   ng-show="request.code!=null">
                {{request.code}}
            </label>
        </div>
        <div class="row">

        </div>
    </div>
</li>