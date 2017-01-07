package ro.ucv.inf.eja.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SumServlet
 */
public class SumServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public SumServlet() {
    super();

  }

  /**
   * Called by the server (via the service method) to allow a servlet to handle a GET request. 
   * 
   * @param request an HttpServletRequest object that contains the request the client has made of the servlet.
   * @param response an HttpServletResponse object that contains the response the servlet sends to the client.    
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
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

    out.println("<html>");
    out.println("<body>");
    out.println("<h1>Sum Calculator using Servlets</h1>");
    if (errorMessage.length() == 0) {
      sum = number1 + number2;
      out.println(number1 + " + " + number2 + " = " + sum);
    } else {
      out.println("<div style='color:red'>" + errorMessage.toString() + "</div>");
    }
    out.println("</body>");
    out.println("</html>");
    

    out.close();
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
