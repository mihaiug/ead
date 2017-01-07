<% pageContext.setAttribute("activeTab", "tasks");  %>
<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

	<h1>Add Task</h1>
	
	<form:form method="post" modelAttribute="task" class="form-horizontal">
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
        	
        	     <spring:bind path="dueDate">
		            <div class="form-group col-md-12 ${status.error ? 'has-error' : ''}">
		                <label class="control-label col-md-3" for="dueDate">Due Date</label>
		                <div class="col-md-7">
		                     <div class="input-group date" id="dueDatePicker">
	  							<form:input type="text" path="dueDate" id="dueDate" class="form-control input-sm"/>
	  							<span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span>
							</div>
		                    <form:errors path="dueDate" cssStyle="help-inline"/>	                    
		                </div>
		            </div>
	            </spring:bind>
	            
        	   	<script type="text/javascript">
					$('#dueDatePicker').datepicker({
						format: 'dd-mm-yyyy',
						autoclose: true,
						todayHighlight: true
					});
				</script>
		
        	</div>
        	
        	<div class="row">
	            <spring:bind path="userId">
	            <div class="form-group col-md-12 ${status.error ? 'has-error' : ''}">
	                <label class="control-label col-md-3" for="password">Assigned User</label>
	                <div class="col-md-7">                    
	                    <form:select path="userId" required="true" class="form-control input-sm">
							<form:option value="">--Select--</form:option>	
							<form:options items="${users}" itemLabel="name" itemValue="id" />			
						</form:select>                    
	                    <form:errors path="userId" cssStyle="help-inline"/>	                 
	                </div>
	            </div>
	            </spring:bind>
        	</div>

        	 <div class="row">
	            <spring:bind path="description">
	            <div class="form-group col-md-12 ${status.error ? 'has-error' : ''}">
	                <label class="control-label col-md-3" for="description">Description</label>
	                <div class="col-md-7">
	                    <form:textarea path="description" id="description" class="form-control input-sm"/>
	                    <form:errors path="description" cssStyle="help-inline"/>	                 
	                </div>
	            </div>
	            </spring:bind>
        	</div>
			
			
			<div class="row">
	            <div class="form-actions">
	                <input type="submit" value="Add" class="btn btn-default pull-right">
	            </div>
	        </div>
	 </div>
	</form:form>

		
<!-- Page content (End)    -->
<%@ include file="footer.jsp" %>