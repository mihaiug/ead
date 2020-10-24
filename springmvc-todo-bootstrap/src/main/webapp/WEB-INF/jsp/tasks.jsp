<% pageContext.setAttribute("activeTab", "tasks");  %>
<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

    <h1>List of tasks</h1>
     <c:if test="${not empty message}">
	    <div class="alert alert-success"><c:out value="${message}"/></div>
	</c:if>
	
    <c:if test="${not empty errorMessage}">
	 <div class="alert alert-danger"><c:out value="${errorMessage}"/></div>
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
    		<td><c:out value="${task.name}"/></td>
    		<td><c:out value="${task.status.displayName}"/></td>
    		<td><c:out value="${task.user.name}"/></td>
    		<td><c:out value="${task.dueDate}"/></td>
    		<td><c:out value="${task.description}"/></td>
    		<td><a href="<c:url value='/task/update?id=${task.id}'/>">Edit</a> | <a href="<c:url value='/task/delete?id=${task.id}'/>">Delete</a></td>
    	</tr>    	
	    </c:forEach>
	  </tbody>  
    </table>  

<!-- Page content (End)    -->
<%@ include file="footer.jsp" %>