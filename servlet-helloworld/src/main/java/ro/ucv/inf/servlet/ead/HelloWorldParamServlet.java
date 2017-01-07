package ro.ucv.inf.servlet.ead;

import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;


public class HelloWorldParamServlet extends HttpServlet {

  /**
   * Called by the server (via the service method) to allow a servlet to handle a GET request. 
   * 
   * @param request an HttpServletRequest object that contains the request the client has made of the servlet.
   * @param response an HttpServletResponse object that contains the response the servlet sends to the client.    
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name = request.getParameter("name");
    String message = null;
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<body>");
    if (name == null || name.isEmpty()){
      message = "Hello World!";
    } else {
      message = "Hello " + name +"!";
    }
    out.println("<h1>" + message + "</h1>");    
    out.println("</body>");
    out.println("</html>");
  }
  
  /**
   * Called by the server (via the service method) to allow a servlet to handle a POST request.
   * 
   * @param request an HttpServletRequest object that contains the request the client has made of the servlet.
   * @param response an HttpServletResponse object that contains the response the servlet sends to the client.
   * 
   * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
