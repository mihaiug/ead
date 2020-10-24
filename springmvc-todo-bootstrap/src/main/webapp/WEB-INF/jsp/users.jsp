<% pageContext.setAttribute("activeTab", "users");  %>
<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

    <h1>Users List</h1>
    <c:if test="${not empty message}">
	    <div class="alert alert-success alert-dismissable">
		    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		    ${message}
	    </div>
	</c:if>
	
    <c:if test="${not empty errorMessage}">
	 <div class="alert alert-danger alert-dismissable">
		 <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
		 ${errorMessage}
	</div>
	</c:if>

    <div><a href="<c:url value='/user/add'/>" class="btn btn-info" role="button">Add New User</a></div>
    
    <table class="table">
    <thead>
      <tr>
			<th>Id</th>
			<th>Name</th>
			<th>Email</th>
			<th>Operations</th>
      </tr>
    </thead>
    <tbody>
	    <c:forEach items="${users}" var="user">
	    <tr>
    		<td><c:out value="${user.id}"/></td>
    		<td><c:out value="${user.name}"/></td>
    		<td><c:out value="${user.email}"/></td>
    		<td><a href="<c:url value='/user/update?id=${user.id}'/>">Edit</a> | <a href="<c:url value='/user/delete?id=${user.id}'/>">Delete</a></td>
    	</tr>    	
	    </c:forEach>
	  </tbody>  
    </table>  
    
<!-- Page content (End)    -->
<%@ include file="footer.jsp" %>