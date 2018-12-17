<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

	<h1>Update Task</h1>
	<form:form method="post" modelAttribute="task">		
		<table>
			<tr>
				<td><form:label path="name">Name:</form:label></td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="status">Status</form:label></td>
				<td>
					<form:select path="status" required="true">
						<form:option value="">--Select--</form:option>	
						<form:options items="${statuses}" itemLabel="displayName"/>			
					</form:select>				
				</td>
				<td><form:errors path="status" cssClass="error" /></td>
			</tr>
			
			<tr>
				<td><form:label path="dueDate">Due Date (dd-MM-yyyy):</form:label></td>
				<td><form:input path="dueDate" /></td>
				<td><form:errors path="dueDate" cssClass="error" /></td>
			</tr>
			 <tr>
				<td><form:label path="userId">Assigned User:</form:label></td>
				<td>
					<form:select path="userId" required="true">
						<form:option value="">--Select--</form:option>	
						<form:options items="${users}" itemLabel="name" itemValue="id" />			
					</form:select>
				</td>
				<td><form:errors path="userId" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="description">Description:</form:label></td>
				<td><form:textarea path="description" /></td>
				<td><form:errors path="description" cssClass="error" /></td>
			</tr>
			
			<tr><td colspan="2" align="right"><input  type="submit" value="Update" /></td></tr>
		</table>
		<form:errors path="*" cssClass="errorblock" element="div" />
	</form:form>

<!-- Page content (End)    -->
<%@ include file="footer.jsp" %>