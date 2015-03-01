function servicesController($scope) {
    $.get("services/all", function (data) {
        Morris.Bar({
            element: 'services-statistic-chart',
            data: data,
            xkey: 'fullName',
            ykeys: ['count'],
            labels: ['count'],
            barRatio: 0.4,
            xLabelAngle: 35,
            hideHover: 'auto',
            resize: true
        })
    });
}

function servicesTopController($scope) {
    $.get("services/top", function (data) {
        Morris.Line({
            element: 'services-top-chart',
            data: data,
            xkey: 'date',
            ykeys: ['Vkontakte', 'Instagram', 'Twitter'],
            labels: ['Vkontakte', 'Instagram', 'Twitter']
        });
    });
}