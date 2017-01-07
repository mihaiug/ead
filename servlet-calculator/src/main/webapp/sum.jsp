<!DOCTYPE html>
<html>
<head>

<title>Sum Calculator using JSPs</title>
</head>
<body>

	<h1>Sum Calculator using JSPs</h1>
	<form action="sum.jsp" method="get">
		Number 1: <input type="text" name="number1" value="<%=request.getParameter("number1")!= null? request.getParameter("number1"):"0"%>"><br>
		Number 2: <input type="text" name="number2" value="<%=request.getParameter("number2")!= null? request.getParameter("number2"):"0"%>"><br>
		<input type="submit" name="action" value="Evaluate"><br>
	</form>

	<%
	  if (request.getParameter("action") != null) {
	    float number1 = 0;
	    float number2 = 0;
	    float sum = 0;
	    StringBuffer errorMessage = new StringBuffer();
	    String number1Param = request.getParameter("number1");
	    String number2Param = request.getParameter("number2");
	    if (number1Param != null) {
	      try {
	        number1 = Float.parseFloat(number1Param);
	      } catch (NumberFormatException e) {
	        errorMessage.append("Invalid value for 'number1' parameter: " + number1Param);
	      }
	    } else {
	      errorMessage.append("Missing number1 parameter.");
	    }
	    if (number2Param != null) {
	      try {
	        number2 = Float.parseFloat(number2Param);
	      } catch (NumberFormatException e) {
	        errorMessage.append("Invalid value for 'number2' parameter: " + number2Param);
	      }
	    } else {
	      errorMessage.append("Missing number2 parameter.");
	    }
	    if (errorMessage.length() == 0) {
	      sum = number1 + number2;
	      out.println(number1 + " + " + number2 + " = " + sum);
	    } else {
	      out.println("<div style='color:red'>" + errorMessage.toString() + "</div>");
	    }
	  }
	%>
</body>
</html>