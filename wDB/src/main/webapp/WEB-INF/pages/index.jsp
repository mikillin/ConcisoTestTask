<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html>
<head>
    <title>
        Sergey's Test project
    </title>

    <!-- meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no">

    <!-- css -->
    <link rel="stylesheet" type="text/css" href="/resources/css/main.css">

    <!-- scripts -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="/resources/js/js.cookie.js"></script>
    <script type="text/javascript" src="/resources/js/json2.js"></script>
    <script type="text/javascript" src="/resources/js/cookieinfo.min.js" id="cookieinfo"></script>

    <script type="text/javascript" src="/resources/js/main.js"></script>

    <script defer async
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAHkxXivUMUY-WKiXtDEFfH9u0-M6VD0ZE&libraries=places&callback=initMap">
    </script>
</head>

<body>
<div class="container">
    <div id="searchControls" class="invisible">

        <input type="text" class="col-3" placeholder="I'm searching for" id="searchAddressField"
               name="searchAddressField"/>

        <input type="button" class="col-3" value="Go" onclick="go();" id="submitButton" name="submitButton"/>

        <select id="selectHistory" class="col-3" name="selectHistory">
            <option value="">History</option>
        </select>

    </div>

    <div id="wrapper">
        <div id="map">
        </div>
    </div>

    <div class="logos">
        <img id="googleLogo" src="/resources/image/google/agreement/powered_by_google_on_white_hdpi.png"
             alt="Powered by Google logo"/>
        <img id="ConcisoLogo" src="/resources/image/conciso/logo.png" alt="Supported by Conciso"/>

    </div>
</div>


</body>
</html>