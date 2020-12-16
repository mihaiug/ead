<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

    <h1>List Users</h1>
    <c:if test="${not empty message}">
	 <div class="infoblock"><c:out value="${message}"/></div>
	</c:if>
	
    <c:if test="${not empty errorMessage}">
	 <div class="errorblock"><c:out value="${errorMessage}"/></div>
	</c:if>

    <div><a href="tasks">View Tasks List</a> | <a href="<c:url value='/user/add'/>">Add New User</a></div>
    <table border="1" cellpadding="4" cellspacing="0">
    	<tr>
    		<th>Id</th>
    		<th>Name</th>
    		<th>Email</th>
    		<th>Operations</th>
    	</tr>
	    <c:forEach items="${users}" var="user">
	    <tr>
    		<td><c:out value="${user.id}"/></td>
    		<td><c:out value="${user.name}"/></td>
    		<td><c:out value="${user.email}"/></td>
    		<td><a href="<c:url value='/user/update?id=${user.id}'/>">Edit</a> | <a href="<c:url value='/user/delete?id=${user.id}'/>">Delete</a></td>
    	</tr>    	
	    </c:forEach>
    </table>  
    
<!-- Page content (End)    -->
<%@ include file="footer.jsp" %>