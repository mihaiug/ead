package ro.ucv.inf.eja.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LifeCycleDemoServlet
 */
public class LifeCycleDemoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * The servlet constructor.
     */
    public LifeCycleDemoServlet() {
        super();     
    }

	/**
	 * The servlet container calls the <code>init</code> method exactly once after instantiating the servlet. 
	 * The <code>init</code> method must complete successfully before the servlet can receive any requests. 
	 * 
	 * @param config a ServletConfig object containing the servlet's configuration and initialization parameters.
	 */
    @Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		System.out.println("Invoke method init(ServletConfig config).");

		//Read init servlet parameters defined in WEB-INF/web.xml.
		String adminEmail = config.getInitParameter("adminEmail");
		System.out.println("Servlet init parameter adminEmail = " + adminEmail);
		
		//Read context parameters defined in WEB-INF/web.xml
		String organization = getServletContext().getInitParameter("organization");
		System.out.println("Context parameter organization = " + organization);
	}


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
	    System.out.println("Invoke method doGet(HttpServletRequest request, HttpServletResponse response).");	
	    PrintWriter out = response.getWriter();
      
	    response.setContentType("text/html");
	    out.println("<html>");
	    out.println("<body>");
	    out.println("<h1>Servlet Life Cycle</h1>");
	    out.println("<p>Request method: " + request.getMethod() + "</p>");
	    out.println("<p>Request path: " + request.getRequestURI()+ "</p>");
	    out.println("<p>Client IP: " + request.getRemoteAddr()+ "</p>");
	    //List all parameters
	    Enumeration<String> paramNames = request.getParameterNames();
	    if (paramNames.hasMoreElements()) {
		    out.println("<p>Parameters: </p>");
		    out.println("<ul>");
		    
		    while (paramNames.hasMoreElements()){
		      String paramName = paramNames.nextElement();
		      String paramValue[] = request.getParameterValues(paramName);
		      
		      out.println("<li>" + paramName + " = ");
		      if (paramValue.length == 1){
		        out.println(paramValue[0]);
		      } else {
		        out.println("[");
		        for (int i = 0; i < paramValue.length; i++) {
		          out.println(paramValue[i]);
		          if (i <  paramValue.length -1) 
		            out.println(",");
	            }
		        out.println("]");
		      }
		      out.println("</li>");
		    }
		    out.println("</ul>");
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
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("Invoke method doGet(HttpServletRequest request, HttpServletResponse response).");
		response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED, "The POST method is not implemented yet.");
	}
	
	/**
	 * Called by the servlet container to indicate to a servlet that the servlet is being taken out of service.
	 */
    @Override
	public void destroy() {
    	System.out.println("Invoke method destroy().");
		System.out.println("Servlet destroyed!");		
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Begin method service(HttpServletRequest request, HttpServletResponse response).");
		super.service(request, response);
		System.out.println("End method service(HttpServletRequest request, HttpServletResponse response).");
	}
    
    

}
