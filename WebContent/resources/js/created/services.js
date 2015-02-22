/**
 * Created by Роман on 15.02.2015.
 */
function addRequest(index) {
    $.get("addRequest?serviceId=" + index, function (data) {
        if (data.success == true) {
            //on success: redirect to requests:
            window.location = "requests";
        }
    });
}