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
</head>
<body>

	
	<div id="map"
		style="width: 900px; height: 1700px;"></div>

	<script type="text/javascript">
	
	//get geo location
	var options = {
				  enableHighAccuracy: true,
				  timeout: 5000,
				  maximumAge: 0
				};
		var crd;
		function success(pos) {
			  crd = pos.coords;

			  console.log('Your current position is:');
			  console.log('Latitude : ' + crd.latitude);
			  console.log('Longitude: ' + crd.longitude);
			  console.log('More or less ' + crd.accuracy + ' meters.');
			  
			  map.setCenter(crd.latitude, crd.longitude);
			};

			function error(err) {
			  console.warn('ERROR(' + err.code + '): ' + err.message);
			  
			};

  navigator.geolocation.getCurrentPosition(success, error, options);
	
	
    var locations = [
                   
      ${latlonpairs}
    ];
//32.985393, -96.745719
    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 19,
      center: new google.maps.LatLng(${centroidlat}, ${centroidlon}),
      mapTypeId: google.maps.MapTypeId.HYBRID
    });
    


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