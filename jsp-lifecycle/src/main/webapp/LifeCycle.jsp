<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP Java Syntax</title>
</head>
<body>
 <%!
 //When a container loads a JSP it invokes the jspInit() method before servicing any requests
 /**
  * It is called first when the JSP is requested and is employed to initialize objects 
  * and variables that are used throughout the lifetime of the JSP.
  */
 public void jspInit(){
  System.out.println("call jspInit()");
 }
 

 /**
  * The destroy() method is automatically called when the JSP terminates normally.
  * It isn’t called by JSP when it terminates.
  * It is used for cleanup where resources used during the execution of the JSP are released.
  */
 public void jspDestroy() {
   // Your cleanup code goes here.
   System.out.println("call jspDestroy()");
}
 
 %>
	
  
  <!-- Declaration. Executed when page is initialized -->
  <%! Date jspFirstRequestDate = new Date(); %>
  
  <!-- Expression -->
  <p>The first call of JSP was at: <%=jspFirstRequestDate%></p>
  <p>Today is:  <%=new Date().toString()%></p>
  
  <!-- Scriptlet -->
  <% 
    int a = 10;
    int b = 2;
    int c = a-b;    
    out.print(a + "-" + b + " = " + c);
  %>

</body>
</html>