<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
<title>Comet Park - Choose parking lot</title>
<script src="http://maps.google.com/maps/api/js?sensor=false"
	type="text/javascript"></script>
<script src="http://code.jquery.com/jquery-2.1.0.min.js" type="text/javascript"></script>
</head>
<body>

	<input type="hidden" name="lotnameholder" id="lotnameholder" value="${lotnameholder }" />
	
	<div id="map"
		style="width: 900px; height: 1700px;"></div>

	<script type="text/javascript">
	
	
	
  
//32.985393, -96.745719
  var map = new google.maps.Map(document.getElementById('map'), {
    zoom: 19,
    center: new google.maps.LatLng(${centroidlat}, ${centroidlon}),
    mapTypeId: google.maps.MapTypeId.HYBRID
  });
  
	var lotnameid = document.getElementById('lotnameholder').value;
	
	$(document).ready(function() {
		  // run the first time; all subsequent calls will take care of themselves
		  setTimeout(executeQuery, 5000);
		});
	function executeQuery() {
		  $.ajax({
		    url: 'http://abhis.ws:8080/cometparkapi/searchspaces/' + lotnameid + '/markers',
		    success: function(data) {
		      // do something with the return value here if you like
		      alert(data);
		    }
		  });
		  setTimeout(executeQuery, 5000); // you could choose not to continue on failure...
		}

	
	
 
	
    var locations = [
                   
      ${latlonpairs}
    ];



    var infowindow = new google.maps.InfoWindow();

    var marker, i;

    for (i = 0; i < locations.length; i++) {  
      marker = new google.maps.Marker({
        position: new google.maps.LatLng(locations[i][1], locations[i][2]),
        map: map,
        title: locations[i][4],
        icon: "http://abhis.ws/misc/imgs/" + locations[i][5]
      });

      google.maps.event.addListener(marker, 'click', (function(marker, i) {
        return function() {
        	 infowindow.setContent(locations[i][4]);
             infowindow.open(map, marker);
        }
      })(marker, i));
    }
  </script>
</body>
</html>