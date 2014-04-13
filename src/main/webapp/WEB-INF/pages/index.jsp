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
<script type="text/javascript">
	function checkAndRun(f) {
		var t = document.getElementById("selector").value;
		if (t == "--Select from map--") {
			alert("Select a lot");
		} else {
			window.location = "searchspaces?lot=" + t + "&fn=" + f;
		}
	}
</script>
<form>
 <p>Select Parking Lot: <input name="selector" id="selector" type="text" value="--Select from map--" /> <br />
 <input type="button" value="View vacant spots" onClick="return checkAndRun('v');" />
 <input type="button" value="View occupied spots" onClick="return checkAndRun('o');" />
 </p>
 </form>
  <div id="map" style="position: absolute; margin-top: 20px; margin-left: 10px; width: 500px; height: 500px;"></div>

  <script type="text/javascript">
    var locations = [
      ${latlonpairs}
    ];

    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 15,
      center: new google.maps.LatLng(32.985393, -96.745719),
      mapTypeId: google.maps.MapTypeId.SATELLITE
    });

    var infowindow = new google.maps.InfoWindow();

    var marker, i;

    for (i = 0; i < locations.length; i++) {  
      marker = new google.maps.Marker({
        position: new google.maps.LatLng(locations[i][1], locations[i][2]),
        map: map
      });

      google.maps.event.addListener(marker, 'click', (function(marker, i) {
        return function() {
          document.getElementById("selector").value=locations[i][0];
        }
      })(marker, i));
    }
  </script>
</body>
</html>