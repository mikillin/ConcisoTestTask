/** consts **/
const defaultHistoryOptionsAmount = 20;

var map;
var center;
var autocomplete;
var place;
var HistoryItemsArray = [];


class HistoryItem {

    constructor(description, lng, lat, zoom) {
        this.description = description;
        this.lng = lng;
        this.lat = lat;
        this.zoom = zoom;
    }
}


function go() {
    let selectHistory = document.getElementById("selectHistory");
    let newOption = document.createElement("option");
    let currentSearchAddressFieldValue = document.getElementById("searchAddressField").value;


    $.ajax("http://localhost:8080/getHistory", {
        crossOrigin: true,
        success: function (data) {
            if (data)
                HistoryItemsArray = JSON.parse(data);
            console.log("ajax success " + data);
            console.log("ajax success " + JSON.stringify(HistoryItemsArray, null, 2));

        },
        error: function (data) {
            if (data)
                HistoryItemsArray = JSON.parse(data);
            console.log("ajax error " + data);
            console.log("ajax error " + JSON.stringify(HistoryItemsArray, null, 2));

        }
    });


    //check for the same place
    if (HistoryItemsArray) {
        for (let i = 0; i < HistoryItemsArray.length; i++)
            if (HistoryItemsArray[i].description == currentSearchAddressFieldValue) {
                return;
            }
    }
    newOption.text = currentSearchAddressFieldValue;
    selectHistory.add(newOption, selectHistory[1]);

    if (selectHistory.length > defaultHistoryOptionsAmount)
        selectHistory.remove(defaultHistoryOptionsAmount + 1); // + 1    - default "History"


    map.panTo(center);
    map.setZoom(11);

    let lat = autocomplete.getPlace().geometry.location.lat();
    let lng = autocomplete.getPlace().geometry.location.lng();
    let zoom = map.getZoom();

    let newHistoryItem = new HistoryItem(currentSearchAddressFieldValue, lng, lat, zoom);


    //form structure with required information
    HistoryItemsArray.push(newHistoryItem);
    //delete redundant results
    while (HistoryItemsArray.length > defaultHistoryOptionsAmount)
        HistoryItemsArray.shift();

    //save data in cookie as JSON object
    $.cookie('ConcisoTestTask', JSON.stringify(HistoryItemsArray, null, 2));

}


function initMap() {

    initMainMap();
    initMapCustomControls();
    //initCurrentPosition();
    initAutocomplete();
    initHistory();
    updateStoredData();
}

var initMainMap = function () {

    map = new google.maps.Map(document.getElementById('map'), {
        zoom: 3,
        mapTypeControl: false,
        zoomControl: true,
        scaleControl: true,
        streetViewControl: false,
        fullscreenControl: true,
        center: {lat: 0, lng: 0}
    });

    map.addListener('dragend', function () {

        cleanCustomControls();
    });

    map.addListener('zoom_changed', function () {

        cleanCustomControls();
    });

}

function cleanCustomControls() {

    var searchAddressField = document.getElementById("searchAddressField");
    var selectHistory = document.getElementById("selectHistory");
    selectHistory.selectedIndex = 0;
    searchAddressField.value = "";

}

function initMapCustomControls() {

    var searchAddressField = document.getElementById("searchAddressField");
    var submitButton = document.getElementById("submitButton");
    var selectHistory = document.getElementById("selectHistory");

    map.controls[google.maps.ControlPosition.TOP_CENTER].push(searchAddressField);
    map.controls[google.maps.ControlPosition.TOP_CENTER].push(submitButton);
    map.controls[google.maps.ControlPosition.TOP_CENTER].push(selectHistory);

    document.addEventListener("load", function () {
        $("#searchControls").removeClass("invisible");
    });

}

function updateStoredData() {

    if ($.cookie('ConcisoTestTask'))
        HistoryItemsArray = JSON.parse($.cookie('ConcisoTestTask'));

    for (let i = 0; i < HistoryItemsArray.length && i < defaultHistoryOptionsAmount; i++) {
        let newOption = document.createElement("option");
        newOption.text = HistoryItemsArray[i].description;
        selectHistory.add(newOption, selectHistory[1]);
    }


}


function initAutocomplete() {

    autocomplete = new google.maps.places.Autocomplete($("#searchAddressField")[0], {});
    google.maps.event.addListener(autocomplete, 'place_changed', function () {
        place = autocomplete.getPlace();
        if (!place.geometry) {
            return;
        }
        center = new google.maps.LatLng(place.geometry.location.lat(), place.geometry.location.lng());
    });
}

function initHistory() {
    //use history data
    $("#selectHistory").change(function () {
        let currentHistoryVal = $("#selectHistory").val();
        $("#searchAddressField").val(currentHistoryVal);

        if (HistoryItemsArray) {
            for (let i = 0; i < HistoryItemsArray.length; i++)
                if (HistoryItemsArray[i].description == currentHistoryVal) {
                    center = new google.maps.LatLng(HistoryItemsArray[i].lat, HistoryItemsArray[i].lng);
                    map.panTo(center);
                }
        }
    })
}

function initCurrentPosition() {
    //search for current position to enhance usability
    var infoWindow = new google.maps.InfoWindow({map: map});
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };

            infoWindow.setPosition(pos);
            infoWindow.setContent('Location found');
            map.setCenter(pos);
        }, function () {
            handleLocationError(true, infoWindow, map.getCenter());
        });
    }
}