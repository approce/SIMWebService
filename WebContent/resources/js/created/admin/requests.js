function requestsController($scope) {
    $.get("requests/last", function (data) {
        for (var i = 0; i < data.length; i++) {
            data[i].calendar = new Date(data[i].calendar).toISOString().slice(2, 10);
        }
        Morris.Area({
            element: 'last-requests-chart',
            data: data,
            xkey: 'calendar',
            ykeys: ['done','fail'],
            labels: ['done', 'fail'],
            pointSize: 2,
            hideHover: 'auto',
            resize: true
        })
    });
}