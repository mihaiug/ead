package ro.ucv.inf.ead.guestbook;

import java.io.IOException;

import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import ro.ucv.inf.ead.guestbook.command.*;
import ro.ucv.inf.ead.guestbook.dao.*;
import ro.ucv.inf.ead.guestbook.model.Comment;

/**
 * GuestBook Servlet Controller.
 */
public class GuestBookServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  /**
   * The guestbook data access manager.
   */
  private GuestBookDAO guestBookDao = null;

  /**
   * Keeps list of available commands.
   */
  private Map<String, Command> commands = new HashMap<>();

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
    
    // List of available actions
    commands.put("add", new AddComment(guestBookDao));
    commands.put("delete", new DeleteComment(guestBookDao));
    commands.put("list", new ListComments(guestBookDao));

  
    //Add some messages.
    guestBookDao.addComment(new Comment("Mihai", "mihai@inf.ucv.ro", new Date(), "Hello from Mihai"));
    guestBookDao.addComment(new Comment("Maria", "maria@inf.ucv.ro", new Date(), "Hello from Maria"));

  }

  /**
   * Called by the server (via the service method) to allow a servlet to handle a GET request.
   * 
   * @param request  an HttpServletRequest object that contains the request the client has made of the servlet.
   * @param response an HttpServletResponse object that contains the response the servlet sends to the client.
   * 
   */
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String action = request.getParameter("action");
    if (action == null || action.trim().isEmpty() || !commands.containsKey(action)) {
      action = "list";
    }
    System.out.println("Action = " + action);
    Command command = commands.get(action);
    if (command != null) {
      String page = command.execute(request, response);
      RequestDispatcher view = request.getRequestDispatcher(page);
      view.forward(request, response);
    }

  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
