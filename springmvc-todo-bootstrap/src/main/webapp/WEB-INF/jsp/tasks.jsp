<% pageContext.setAttribute("activeTab", "tasks");  %>
<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

    <h1>List of tasks</h1>
     <c:if test="${not empty message}">
	    <div class="alert alert-success">${message}</div>
	</c:if>
	
    <c:if test="${not empty errorMessage}">
	 <div class="alert alert-danger">${errorMessage}</div>
	</c:if>

    
    <div><a href="<c:url value='/task/add'/>" class="btn btn-info" role="button">Add New Task</a></div>
    <table class="table">
    <thead>
      <tr>
    		<th>Id</th>
    		<th>Name</th>
    		<th>Status</th>
    		<th>Assigned User</th>    		
    		<th>Due Date</th>
    		<th>Description</th>
    		<th>Operations</th>
      </tr>
    </thead>
    <tbody>
	    <c:forEach items="${tasks}" var="task">
	    <tr>
    		<td>${task.id}</td>
    		<td>${task.name}</td>
    		<td>${task.status.displayName}</td>
    		<td>${task.user.name}</td>
    		<td>${task.dueDate}</td>
    		<td>${task.description}</td>
    		<td><a href="<c:url value='/task/update?id=${task.id}'/>">Edit</a> | <a href="<c:url value='/task/delete?id=${task.id}'/>">Delete</a></td>
    	</tr>    	
	    </c:forEach>
	  </tbody>  
    </table>  

<!-- Page content (End)    -->
<%@ include file="footer.jsp" %>