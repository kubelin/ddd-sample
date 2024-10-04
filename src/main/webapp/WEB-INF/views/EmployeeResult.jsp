<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session Information</title>
<style>
table {
	border-collapse: collapse;
	width: 80%;
}

th, td {
	border: 1px solid black;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

.error-message {
	background-color: #ffebee;
	border: 1px solid #f44336;
	padding: 10px;
	margin: 10px 0;
	border-radius: 4px;
}

.error-message h2 {
	color: #d32f2f;
	margin-top: 0;
}
</style>
<title>Employee Search Result</title>
</head>
<body>
	<table>
		<tr>
			<th>Attribute</th>
			<th>Value</th>
		</tr>
		<tr>
			<td>Session ID</td>
			<td><%=session.getId()%></td>
		</tr>
		<tr>
			<td>employeeID</td>
			<td>${employeeId}</td>
		</tr>
		<tr>
			<td>Creation Time</td>
			<td><fmt:formatDate value="${creationTime}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
		<tr>
			<td>Last Accessed Time</td>
			<td><fmt:formatDate value="${lastAccessedTime}"
					pattern="yyyy-MM-dd HH:mm:ss" /></td>
		</tr>
	</table>

	<h2>Employee Search Result</h2>
	<c:if test="${empty errorMessage}">
		<c:if test="${searchPerformed}">
			<p>Search criteria:</p>
			<ul>
				<li>Name: ${name}</li>
				<li>Employee ID: ${employeeId}</li>
				<li>Phone Number: ${phoneNumber}</li>
			</ul>

			<!-- 여기에 실제 검색 결과를 표시합니다. -->
			<p>Search results would be displayed here.</p>
		</c:if>
	</c:if>
	<c:if test="${not empty errorMessage}">
		<div class="error-message">
			<h2>Error</h2>
			<p>${errorMessage}</p>
		</div>
	</c:if>




	<a href="/employee/search">Back to Search</a>
</body>
</html>