<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Search</title>
  
</head>
<body>
	<h2>Employee Search</h2>
	<form action="/employee/search" method="post">
		<div>
			<label for="name">Name:</label> <input type="text" id="name"
				name="name">
		</div>
		<div>
			<label for="employeeId">Employee ID:</label> <input type="text"
				id="employeeId" name="employeeId">
		</div>
		<div>
			<label for="phoneNumber">Phone Number:</label> <input type="text"
				id="phoneNumber" name="phoneNumber">
		</div>
		<div>
			<input type="submit" value="Search">
		</div>
	</form>
</body>
</html>