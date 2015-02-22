app.controller('loginController', ['$scope', '$http', function ($scope, $http) {
    $scope.wrongPassword = false;
    $scope.username;
    $scope.password;
    $scope.isLoading = false;

    $scope.doLogin = function () {
        //send ajax:
        $scope.isLoading = true;
        $http({
            url: 'security_check',
            method: "POST",
            params: {username: $scope.username, password: $scope.password}
        })
            .success(function (data) {
                location.replace('services');
                $scope.isLoading = false;
            })
            .error(function (data) {
                //if wrongPassword:
                if (data.wrongPassword) {
                    $scope.wrongPassword = true;
                }
                $scope.isLoading = false;
            });
    }
}]);
