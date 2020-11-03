<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GuestBook List Messages</title>
</head>
<body>
	<h1>Phonebook with JSTL</h1>
	<c:if test="${not empty errorMessage}">
		<p>
			<i>Error: <c:out value="${errorMessage}" /></i>
		</p>
	</c:if>

	<p>
		Name:<c:out value="${contact.name}" />
	</p>
	<p>
		Phone:<c:out value="${contact.phone}" />
</body>
</html>