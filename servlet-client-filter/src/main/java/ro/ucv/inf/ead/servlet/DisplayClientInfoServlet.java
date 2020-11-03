package ro.ucv.inf.ead.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DisplayClientInfoServlet extends HttpServlet {


/**
   * Called by the server (via the service method) to allow a servlet to handle a GET request. 
   * 
   * @param request an HttpServletRequest object that contains the request the client has made of the servlet.
   * @param response an HttpServletResponse object that contains the response the servlet sends to the client.    
   * 
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Execute doGet()");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Client Request Information</h1>");
		out.println("<p>Request method: " + request.getMethod() + "</p>");
		out.println("<p>Request path: " + request.getRequestURI() + "</p>");
		out.println("<p>Client IP: " + request.getRemoteAddr() + "</p>");
		out.println("<p>HTTP Headers:</p>");
		out.println("<ul>");
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			List<String> headeValue = Collections.list(request.getHeaders(headerName));
			for (String headerValue : headeValue) {
				out.println("<li><code>" + headerName + ": " + headerValue + "</code></li>");
			}
		}
		out.println("</ul>");
		Enumeration<String> paramNames = request.getParameterNames();
		if (paramNames.hasMoreElements()) {
			out.println("<p>HTTP Parameters: </p>");
			out.println("<ul>");
			while (paramNames.hasMoreElements()) {
				String paramName = paramNames.nextElement();
				String paramValues[] = request.getParameterValues(paramName);
				for (String paramValue : paramValues) {
					out.println("<li><code>" + paramName + " = " + paramValue + "</code></li>");
				}
				
			}
			out.println("</ul>");
		}
		out.println("</body>");
		out.println("</html>");
	}
}
