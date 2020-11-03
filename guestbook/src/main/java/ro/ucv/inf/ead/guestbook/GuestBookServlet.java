package ro.ucv.inf.ead.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.ucv.inf.ead.guestbook.dao.GuestBookDAO;
import ro.ucv.inf.ead.guestbook.dao.GuestBookDAOMemImpl;
import ro.ucv.inf.ead.guestbook.model.Comment;

/**
 * Servlet implementation class GuestBookServlet.
 */
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * The guestbook data access manager.
     */
    private GuestBookDAO guestBookDao = null;
 
    public GuestBookServlet() {
        super();
       
    }

	/**
	 * The servlet container calls the <code>init</code> method exactly once after instantiating the servlet. 
	 * The <code>init</code> method must complete successfully before the servlet can receive any requests. 
	 * 
	 * @param config a ServletConfig object containing the servlet's configuration and initialization parameters.
	 */
	public void init(ServletConfig config) throws ServletException {
		guestBookDao = new GuestBookDAOMemImpl();
		guestBookDao.addComment(new Comment(1, "Mihai", "mihai@inf.ucv.ro", new Date(), "Hello from Mihai"));
		guestBookDao.addComment(new Comment(2, "Maria", "maria@inf.ucv.ro", new Date(), "Hello from Maria"));
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
		String action = request.getParameter("action");
		if ("add".equals(action)){
			doAddComment(request, response);
		}
		doListComments(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
    }

	
	/**
	 * Handle add message operation.
	 *  
	 * @param request an HttpServletRequest object that contains the request the client has made of the servlet.
	 * @param response an HttpServletResponse object that contains the response the servlet sends to the client.
	 * 
	 */	
	protected void doAddComment(HttpServletRequest request, HttpServletResponse response)  {
		
		//Read parameters from request.
		String userName = request.getParameter("userName");
		String userEmail = request.getParameter("userEmail");
		String message = request.getParameter("message");
		Date date = new Date();
		
		//TODO: Add parameters validation.
	
		Comment comment = new Comment();
		comment.setUserName(userName);
		comment.setUserEmail(userEmail);
		comment.setMessage(message);
		comment.setDate(date);
		
		guestBookDao.addComment(comment);
		
	}
	
	
	/**
	 * Display the messages.
	 *  
	 * @param request an HttpServletRequest object that contains the request the client has made of the servlet.
	 * @param response an HttpServletResponse object that contains the response the servlet sends to the client.
	 * 
	 */	
	protected void doListComments(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		List<Comment> comments = guestBookDao.getAllComments();
		
		out.println("<html>");
		out.println("<body>");
		out.println("<h1>Guestbook</h1>");
		
		out.println("<ul>");
		for (Comment comment : comments) {
			out.print("<li>");
			out.print(comment.getDate() + " - " + comment.getMessage());
			out.println("</li>");
		}
		out.println("</ul>");
		
		//Display the add comment form.
		out.print("<form>");
		out.println("<input type='hidden' name='action' value='add' method='post'> <br>");
		out.println("Name: <input type='text' name='userName'> <br>");
		out.println("Email: <input type='text' name='userEmail'> <br>");
		out.println("Message:<br> <textarea name='message' rows='4' cols='50'></textarea> <br>");
		out.println("<input type='submit' value='Add'> <br>");
		out.print("</form>");
		
		out.println("</body>");
		out.println("</html>");
	}

	
}
