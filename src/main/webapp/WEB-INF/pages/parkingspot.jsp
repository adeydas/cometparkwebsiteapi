<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang = "en">
  <head>
    <title>Administrator_Management_Parking Spots.html</title>
    <meta charset = "UTF-8" />
  <link rel="stylesheet" type="text/css" href="http://abhis.ws/cometpark/login.css">
  <script type="text/javascript">
  	function setVal() {
  		var e = document.getElementById("pnumber");
  		var strUser = e.options[e.selectedIndex].value;
  		window.location = 'http://abhis.ws:8080/cometparkapi/admin/controller/' + strUser + '/view';
  	}
  
  	function showLayer() {
  		document.getElementById("dispboard").style.display = 'block';
  	}
  	
  	function hideLayer() {
  		document.getElementById("dispboard").style.display = 'none';
  	}
  	
  	function subform(e) {
  		if (e == 'e') {
  			document.tesla.action = 'http://abhis.ws:8080/cometparkapi/admin/controller/update';
  		} else {
  			document.tesla.action = 'http://abhis.ws:8080/cometparkapi/admin/controller/delete';
  		}
  	}
  </script>
  </head>
  <body onload="hideLayer()" >
  <h1>
  Managing UTD Parking Spots</h1>
  <div align="center">
  <form id='parking spot_management' method='post' action=' '>
  
  
  <p>
  <b>Parking Spot Number:</b>
  <select name="pnumber" id="pnumber" >
  	${rets }
  </select>
  </p>
  
  <input type='button' name='View' value='View' onclick="setVal();" />
  
  <input type='button' name='Insert' value='Insert' onclick="showLayer();" />
  
  
  
  <input type='button' name='Reload' value='Reload' onClick="window.location.reload()" />
    
	
  </p>
  </form>
  </div>
  <p>
  <div align="center">
  <form action="http://abhis.ws:8080/cometparkapi/admin/welcome">
  <input type="submit" value="Management_Homepage">
  </form>
  </div>
  </p>
  <div align="center" style="width:100%; background-color: #FFFFFF;">
  
  ${optional }
  
  </div>
  <div id="dispboard" align="center" style="width:100%; background-color: #FFFFFF;">
  <h2>Insert Data</h2>
  <form action="http://abhis.ws:8080/cometparkapi/admin/controller/insert" method="post">
  	<table border="1">
  		<tr><td>Space ID</td> <td><input type="text" name="id" id="id" /></td></tr>
  		<tr><td>Latitude</td> <td><input type="text" name="latitide" id="latitude" /></td></tr>
  		<tr><td>Longitude</td> <td><input type="text" name="longitude" id="longitude" /></td></tr>
  		<tr><td>Level Type</td> <td><input type="text" name="leveltype" id="leveltype" /></td></tr>
  		<tr><td>Lot ID</td> <td><input type="text" name="lotid" id="lotid" /></td></tr>
  		<tr><td>&nbsp;</td> <td><input type="submit" value="Insert" /></td></tr>
  	</table>
  </form>
  </div>
  </body>
  </html>