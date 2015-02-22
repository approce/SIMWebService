<%@ tag description="I am view for each social servise proposition" pageEncoding="UTF-8" %>

<%@ attribute name="id" required="true" %>
<%@ attribute name="fullName" required="true" %>
<%@ attribute name="iconPath" required="true" %>
<%@ attribute name="price" required="true" %>

<div id="service${id}" class="servicePickerWrapper col-sm-8" onclick="addRequest(${id});">
    <div class="panel panel-info">
        <div class="panel-body  servicePicker">
            <img class="serviceImage" src="resources/images/${iconPath}" height="32" width="32"/>
            <label class="serviceTitle">${fullName}</label>
            <label class="label label-success servicePrice pull-right">${price}$</label>
        </div>
    </div>
</div>