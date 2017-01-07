<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>GuestBook List Messages</title>
</head>
<body>
<h1>Guestbook</h1>
<c:if test="${not empty errorMessage}">
	<p><i>Error: <c:out value="${errorMessage}"/></i></p>
</c:if>

		<table border="1" cellspacing="0" cellpadding="2">
		<thead>
			<tr>
				<th>ID</th>
				<th>Date</th>
				<th>Name</th>
				<th>Message</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${comments}" var="comment">
				<tr>
					<td><c:out value="${comment.id}" /></td>
					<td><c:out value="${comment.date}" /></td>
					<td><c:out value="${comment.userName}" /></td>
					<td><c:out value="${comment.message}" /></td>
					<td><a	href="GuestBook?action=delete&id=<c:out value="${comment.id }"/>">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<p>
		Add new message:
		<form action="GuestBook" method="post">
		<input type="hidden" name="action" value="add"> <br/>
		Name: <input type='text' name='userName'> <br>
		Email: <input type='text' name='userEmail'> <br>
		Message: <br>
		<textarea name='message' rows='4' cols='50'></textarea> <br>
		<input type='submit' value='Add'> <br>
		</form>
	</p>
</body>
</html>