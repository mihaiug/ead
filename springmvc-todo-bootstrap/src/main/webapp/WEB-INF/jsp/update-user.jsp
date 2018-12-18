<% pageContext.setAttribute("activeTab", "users");  %>
<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

	<h1>Update User</h1>
	<form:form method="post" modelAttribute="user" class="form-horizontal">
	<div role="form">
			
	        <div class="row">
	        	<spring:bind path="name">
	            <div class="form-group col-md-12 ${status.error ? 'has-error' : ''}">
	                <label class="control-label col-md-3" for="name">Name</label>
	                <div class="col-md-7">
	                    <form:input type="text" path="name" id="name" class="form-control input-sm"/>
	                    <form:errors path="name" cssStyle="help-inline"/>	                
	                </div>
	            </div>
	            </spring:bind>
        	</div>
        	
        	<div class="row">
	            <spring:bind path="email">
	            <div class="form-group col-md-12 ${status.error ? 'has-error' : ''}">
	                <label class="control-label col-md-3" for="email">Email</label>
	                <div class="col-md-7">
	                    <form:input type="text" path="email" id="email" class="form-control input-sm"/>
	                    <form:errors path="email" cssStyle="help-inline"/>	                    
	                </div>
	            </div>
	            </spring:bind>
        	</div>
        	
        	 <div class="row">
	            <spring:bind path="password">
	            <div class="form-group col-md-12 ${status.error ? 'has-error' : ''}">
	                <label class="control-label col-md-3" for="password">Password</label>
	                <div class="col-md-7">
	                    <form:password path="password" id="password" class="form-control input-sm"/>
	                    <form:errors path="password" cssStyle="help-inline"/>	                 
	                </div>
	            </div>
	            </spring:bind>
        	</div>
			
			
			<div class="row">
	            <div class="form-actions">
	                <input type="submit" value="Update" class="btn btn-default pull-right">
	            </div>
	        </div>
	 </div>
	</form:form>

<!-- Page content (End)    -->
<%@ include file="footer.jsp" %>