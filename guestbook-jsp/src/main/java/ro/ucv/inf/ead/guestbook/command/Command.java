package ro.ucv.inf.ead.guestbook.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface for executing an operation.
 * This is used to implement the Command Design Pattern.
 */
public interface Command {
	/**
	 * Perform an application-specific operation.
	 * 
	 * @param request  an HttpServletRequest object that contains the request the client has made of the servlet.
	 * @param response an HttpServletResponse object that contains the response the servlet sends to the client.
	 * 
	 * @return The JSP file name that will be displayed after the operation is executed. 
	 */
	public String execute(HttpServletRequest request, HttpServletResponse response);

}
