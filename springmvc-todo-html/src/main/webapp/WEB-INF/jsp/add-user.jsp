<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

	<h1>Add User</h1>
	<form:form method="post" modelAttribute="user">
		
		<table>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="email">Email</form:label></td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:password path="password" /></td>
				<td><form:errors path="password" cssClass="error" /></td>
			</tr>
			<tr><td colspan="2" align="right"><input  type="submit" value="Add" /></td></tr>
		</table>
		<form:errors path="*" cssClass="errorblock" element="div" />
	</form:form>

<!-- Page content (End)    -->
<%@ include file="footer.jsp" %>