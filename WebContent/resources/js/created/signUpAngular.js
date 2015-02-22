var usernameRegexp = /^[a-zA-Z0-9_]{3,20}$/;

//using for check if username already exist in DB:
app.directive('uniqueUsername', ['$http', function ($http) {
    return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {
            scope.busy = false;
            scope.$watch(attrs.ngModel, function (value) {
                //0. Hide old error messages:
                ctrl.$setValidity('isTaken', true);
                ctrl.$setValidity('invalidChars', true);

                //1. Check if username not empty and matches pattern:
                if (!value || !usernameRegexp.test(value)) {
                    return;
                }

                //set busy (loading icon) before ajax:
                scope.busy = true;
                //2. Send request to server:
                $http({
                    url: 'signup/checkUserName',
                    method: "GET",
                    params: {username: value}
                })
                    //3.0. If responsed is success:
                    .success(function (data) {
                        //set busy false:
                        scope.busy = false;
                    })
                    //3.1. If error responsed:
                    .error(function (data) {
                        if (data.exist) {
                            //if responsed "exist":
                            ctrl.$setValidity('isTaken', false);
                        } else if (data.invalid) {
                            //if responsed "invalid":
                            ctrl.$setValidity('pattern', false);
                        }
                        //set busy false:
                        scope.busy = false;
                    });
            })
        }
    }
}]);

//used for password match check:
app.directive('match', [function () {
    return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {

            scope.$watch('[' + attrs.ngModel + ', ' + attrs.match + ']', function (value) {
                ctrl.$setValidity('match', value[0] === value[1]);
            }, true);
        }
    }
}]);