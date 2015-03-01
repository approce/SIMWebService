function usersController($scope) {
    //get users regstration:
    $.get("users/last?from=2015-02-01", function (data) {
        for (var i = 0; i < data.length; i++) {
            data[i].calendar = new Date(data[i].calendar).toISOString().slice(5, 10);
        }
        Morris.Bar({
            element: 'morris-bar-chart',
            data: data,
            xkey: 'calendar',
            ykeys: ['count'],
            labels: ['count'],
            barRatio: 0.4,
            xLabelAngle: 35,
            hideHover: 'auto',
            resize: true
        });
    });

    $scope.getLastRegistered = function () {

    }
}