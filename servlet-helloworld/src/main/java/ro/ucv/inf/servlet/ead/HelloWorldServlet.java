package ro.ucv.inf.servlet.ead;

import java.io.*;
import java.util.Date;

import javax.servlet.*;
import javax.servlet.http.*;


public class HelloWorldServlet extends HttpServlet {


/**
   * Called by the server (via the service method) to allow a servlet to handle a GET request. 
   * 
   * @param request an HttpServletRequest object that contains the request the client has made of the servlet.
   * @param response an HttpServletResponse object that contains the response the servlet sends to the client.    
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
	@Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.println("<html>");
    out.println("<body>");
    out.println("<h1>Servlet: Hello World!</h1>");
    out.print("Today is: " + new Date().toString());
    out.println("</body>");
    out.println("</html>");
  }
}
