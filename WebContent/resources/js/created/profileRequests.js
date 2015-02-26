app.controller('requestsController', ['$scope', '$http', function ($scope, $http) {
    $scope.requests;
    //init requests:
    $http.get("getRequests").
        success(function (data) {
            $scope.requests = data;
            //kostil:
            //for each request (if status == wait4number):
            for (var i = 0; i < $scope.requests.length; i++) {
                console.log($scope.requests[i].status);
                if ($scope.requests[i].status == "WAIT_NUMBER") {
                    //start long polling:
                    $scope.getRequestNumber($scope.requests[i]);
                }
                if ($scope.requests[i].status == "WAIT_CODE") {
                    //start long polling for get code:
                    $scope.getRequestCode($scope.requests[i]);
                }
            }
        });

    //refresh requests:
    $scope.getUserRequests = function () {
        $http.get("getRequests").
            success(function (data, status, headers, config) {
                alert('1');
            });
    };

    $scope.startRequest = function (request) {
        $http.get("startRequest?id=" + request.id, request)
            .success(function (data, status, headers, config) {
                if (data.success == true) {
                    //set new status to request view:
                    request.status = "WAIT_NUMBER";
                    $scope.getRequestNumber(request);
                } else {
                    //show error:
                    //break;
                }
                //send long polling request for number:

            });
    };
    //long polling ajax to get Number:
    $scope.getRequestNumber = function (request) {
        console.log("start polling " + request.id);
        $http.get("getRequestNumber?id=" + request.id, request)
            .success(function (data) {
                if (data == "timeOut") {
                    console.log("received timeOut");
                    //start new pool:
                    $scope.getRequestNumber(request);
                } else {
                    //if number receivedd:
                    request.number = data;
                    request.status = "NUMBER_SUBMIT";
                    console.log("received " + data);
                }
            });
    };
    //user submit number ajax:
    $scope.submitNumber = function (request, submit) {
        console.log("submit request " + request.id + " " + submit);
        $http.get("submitRequestNumber?id=" + request.id + "&submit=" + submit)
            .success(function (data) {
                console.log(data);
            });

        if (submit == true) {
            request.status = "WAIT_CODE";
            //send ajax to get code:
            $scope.getRequestCode(request);
        } else {
            request.status = "NUMBER_REJECT";
        }
    };

    $scope.getRequestCode = function (request) {
        console.log('start get code for request ' + request.id);
        $http.get("getRequestCode?id=" + request.id, request)
            .success(function (data) {
                if (data == "timeOut") {
                    console.log("received timeOut");
                    //start new pool:
                    $scope.getRequestCode(request);
                } else {
                    //if code receivedd:
                    request.code = data;
                    request.status = "COMPLETED";
                    console.log("received " + data);
                }
            });
    };

    $scope.finishRequest = function (request) {
        console.log('finishing request ' + request.id);
        $http.get("setFinishRequest?requestId=" + request.id, request)
            .success(function (data) {
                if (data == "OK") {
                    $scope.requests.splice($scope.requests.indexOf(request), 1);
                }
            });
    }


}]);


/*$scope.requests = [{"id": "1233", "service": "Vkontakte", "status": "Preparing", "number": "0", "code": "0"},
 {"id": "1343", "service": "Odnoklassniki", "status": "Wait4Number", "number": "0", "code": "0"},
 {"id": "1343", "service": "Mamba", "status": "Wait4NumberSubmit", "number": "+380667692065", "code": "0"},
 {"id": "1343", "service": "Steam", "status": "Wait4Code", "number": "+380667692065", "code": "0"},
 {"id": "1343", "service": "Steam", "status": "Completed", "number": "+380667692065", "code": "8547"}];*/