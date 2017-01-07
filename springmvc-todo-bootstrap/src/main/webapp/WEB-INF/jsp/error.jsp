<%@ include file="header.jsp" %>
<!-- Page content (Begin)  -->

	<h1>Error</h1>
	<div class="alert alert-danger">
	  	Error: ${exception.message}<br/> 
	  	
	  	Url: ${url}
	</div>

<!-- Page content (End)    -->
<%@ include file="footer.jsp" %>	  