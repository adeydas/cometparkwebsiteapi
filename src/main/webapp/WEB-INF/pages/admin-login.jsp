<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang ="en-US">
<head>
<meta charset = "UTF-8" />
<h1>Comet Park_Admin_Authentication Form</h1>
<link rel="stylesheet" type="text/css" href=http://abhis.ws/cometpark/login.css>
</head>
<body >
<ul>
<form:form method="post" action="${pageContext.request.contextPath}/admin/logincheck">
<tr>
<colgroup align="right" valign="top">
<td>Username:</td>
<td><input type="text" name="username" id="username" autofocus required /></td>
</tr>
<tr>
<td>Password:</td>
<td><input type="password" name="password" id="password" maxlength="100" required /></td>
</tr>
<tr>
<td></td>
<td><input type="submit" value="Submit">
</tr>
</form:form>
</ul>

</body>
</html>