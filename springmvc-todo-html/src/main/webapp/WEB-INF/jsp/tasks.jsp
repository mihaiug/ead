<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

    <h1>List of tasks</h1>
    <c:if test="${not empty message}">
	 <div class="infoblock">${message}</div>
	</c:if>
	
    <c:if test="${not empty errorMessage}">
	 <div class="errorblock">${errorMessage}</div>
	</c:if>
	
    <div><a href="<c:url value='/task/add'/>">Add New Task</a> | <a href="<c:url value='/users'/>">View Users</a></div>
    <table border="1" cellpadding="4" cellspacing="0">
    	<tr>
    		<th>Id</th>
    		<th>Name</th>
    		<th>Status</th>
    		<th>Assigned User</th>    		
    		<th>Due Date</th>
    		<th>Description</th>
    		<th colspan="2">Operations</th>
    	</tr>
	    <c:forEach items="${tasks}" var="task">
	    <tr>
    		<td>${task.id}</td>
    		<td>${task.name}</td>
    		<td>${task.status.displayName}</td>
    		<td>${task.user.name}</td>
    		<td>${task.dueDate}</td>
    		<td>${task.description}</td>
    		<td><a href="<c:url value='/task/update?id=${task.id}'/>">Edit</a></td>
    		<td><a href="<c:url value='/task/delete?id=${task.id}'/>">Delete</a></td>
    	</tr>    	
	    </c:forEach>
    </table>  
    
<!-- Page content (End)    -->
<%@ include file="footer.jsp" %>