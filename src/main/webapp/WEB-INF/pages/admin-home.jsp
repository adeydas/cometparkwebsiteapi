<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>CometParkAdministration</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link rel="stylesheet" type="text/css" href="http://abhis.ws/cometpark/management.css"" >
</head>
<body>

<!-- star menu -->
<div id="menu">
	<ul>
		<li class="current_page_item"><a href="#"></a></li>
		<li><a href="http://abhis.ws:8080/cometparkapi/welcome">USERS</a></li>
				<li><a href="http://abhis.ws:8080/cometparkapi/admin/login">ADMIN</a></li>
	</ul>
</div>
<!-- end menu -->
<!-- start page -->
<div id="page">
	<!-- start content -->
	<div id="content">
		<div class="post">
			<div class="title">
				<h2><a href="#"> Comet Park</a></h2>
				
			</div>
			<div class="entry">
				<h2>
				Welcome to Comet Park!
								</h2>
					<p>
					<h3>Users:<h3> Please click on users to check the available parking spots in UTD parking lots. 
					You will be able to locate a spot just by following the location being shown by the markers on the UTD map.
					Have a safe driving.
					</p>
					<p>
					<h4>Admin:</h4> Please click on the Admin to login to the Comet Park system.
					Successful authentication allows you to manage the UTD parking spots in the parking lots.
					</p>
			</div>
		</div>
		</div>
	

</body>
</html>
