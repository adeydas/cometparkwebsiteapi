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
<link rel="stylesheet" type="text/css" href="http://abhis.ws/cometpark/management.css" >
</head>
<body>

<!-- star menu -->
<div id="menu">
	<ul>
		<li class="current_page_item"><a href="#"></a></li>
		<li><a href="${pageContext.request.contextPath}/admin/controller">Parking Spot</a></li>
		<li><a href="${pageContext.request.contextPath}/admin/lot">Parking Lot</a></li> 
		<!--  <li><a href="${pageContext.request.contextPath}/admin/welcome">Reports</a></li> -->
	</ul>
</div>
<!-- end menu -->
<!-- start page -->
<div id="page">
	<!-- start content -->
	<div id="content">
		<div class="post">
			<div class="title">
				<h2><a href="#">About Managing Comet Park</a></h2>
				
			</div>
			<div class="entry">
				<p>
				We can manage Comet Park sensor , controller, parking spots, parking lots by inserting new information, view an existing one, delete information about  non existing ones, update information on existing ones.
				Information will be retrieved or updated to and from MySQL server.
				</p>
			</div>
		</div>
		</div>
	

</body>
</html>
