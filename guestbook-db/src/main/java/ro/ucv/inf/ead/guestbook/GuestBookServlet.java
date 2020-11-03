package ro.ucv.inf.ead.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ro.ucv.inf.ead.guestbook.dao.*;
import ro.ucv.inf.ead.guestbook.model.Comment;
import ro.ucv.inf.ead.guestbook.model.User;

/**
 * Servlet implementation class GuestBookServlet.
 * 
 */
public class GuestBookServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  /**
   * The guestbook data access manager.
   */
  private GuestBookDAO guestBookDao = null;

  /**
   * @see HttpServlet#HttpServlet()
   */
  public GuestBookServlet() {
    super();
  }

  /**
   * The servlet container calls the <code>init</code> method exactly once after instantiating the servlet. The
   * <code>init</code> method must complete successfully before the servlet can receive any requests.
   * 
   * @param config a ServletConfig object containing the servlet's configuration and initialization parameters.
   */
  @Override
  public void init(ServletConfig config) throws ServletException {
    try {
      // guestBookDao = new GuestBookDAOMemImpl(); //In memory data storage implementation.
      guestBookDao = new GuestBookDAODbImpl(); // In database data storage implementation.
    } catch (Exception e) {
      throw new UnavailableException("Create GuestBookDAO error: " + e.getMessage());
    }
    // Add administrator if not exists
    if (guestBookDao.findUser("admin@inf.ucv.ro") == null) {
      guestBookDao.addUser(new User(0, "Administrator", "admin@inf.ucv.ro", "admin"));
    }

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
    boolean logged = false;
    HttpSession session = request.getSession(true);
    if (session.isNew()) {
      System.out.println("Create session: " + session.getId());
    } else {
      logged = session.getAttribute("user") != null;
    }

    String action = request.getParameter("action");
    if ("add".equals(action)) {
      doAddComment(request, response);

    } else if ("login".equals(action)) {
      doLogin(session, request, response);

    } else if (logged) {

      if ("delete".equals(action)) {
        doDeleteComment(request, response);

      } else if ("logout".equals(action)) {
        doLogout(session, request, response);

      }
    }
    doListComments(session, request, response);
  }
  
  @Override
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
  protected void doAddComment(HttpServletRequest request, HttpServletResponse response) {

    // Read parameters from request.
    String userName = request.getParameter("userName");
    String userEmail = request.getParameter("userEmail");
    String message = request.getParameter("message");
    Date date = new Date();

    // Validate input parameters.
    if (userName == null || userName.trim().length() == 0) {
        request.setAttribute("error", "User name can not be empty!");
        return;
    }
    if (userEmail == null || userEmail.trim().length() == 0) {
        request.setAttribute("error", "User email can not be empty!");
        return;
    }
    if (message == null || message.trim().length() == 0) {
      request.setAttribute("error", "Message can not be empty!");
      return;
    }

    Comment comment = new Comment();
    comment.setUserName(userName);
    comment.setUserEmail(userEmail);
    comment.setMessage(message);
    comment.setDate(date);
    guestBookDao.addComment(comment);

  }

  /**
   * Handle delete message operation.
   * 
   * @param request an HttpServletRequest object that contains the request the client has made of the servlet.
   * @param response an HttpServletResponse object that contains the response the servlet sends to the client.
   * 
   */
  protected void doDeleteComment(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String commentIdParam = request.getParameter("commentId");
    long commentId = 0;
    if (commentIdParam != null && !commentIdParam.isEmpty()) {
      try {
        commentId = Long.parseLong(commentIdParam);
      } catch (NumberFormatException e) {
        request.setAttribute("error", "Invalid id: " + commentIdParam);
      }
    } else {
      request.setAttribute("error", "Missing parameter commentId.");
    }
    if (commentId != 0) {
      guestBookDao.deleteComment(commentId);
    }
  }

  /**
   * Display the messages.
   * 
   * @param request an HttpServletRequest object that contains the request the client has made of the servlet.
   * @param response an HttpServletResponse object that contains the response the servlet sends to the client.
   * 
   */
  protected void doListComments(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();

    List<Comment> comments = guestBookDao.getAllComments();

    out.println("<html>");
    out.println("<body>");

    User user = null;
    boolean logged = false;
    if ((user = (User) session.getAttribute("user")) != null) {
      logged = true;
      out.println("<div style='text-align:right'> Welcome: " + user.getName() + " | <a href='" + request.getRequestURI() + "?action=logout'>Logout</a></div>");
    } else {
      out.println("<div style='text-align:right'><a href='login.html'>Login</a></div>");
    }
    out.println("<h1>Guestbook</h1>");
    if (request.getAttribute("error") != null) {
      out.println("<p style='color:red'>" + request.getAttribute("error") + "</p>");
    }
    out.println("<ul>");
    for (Comment comment : comments) {
      out.print("<li>");
      out.print(comment.getDate() + " - " + comment.getMessage());
      if (logged) {
        out.println(" - <a href='" + request.getContextPath() + "?action=delete&commentId=" + comment.getId() + "'>Delete</a>");
      }
      out.println("</li>");
    }
    out.println("</ul>");

    // Display the add comment form.
    out.print("<form action='" + request.getRequestURI() + "' method='post'>");
    out.println("<input type='hidden' name='action' value='add'> <br>");
    out.println("Name: <input type='text' name='userName'> <br>");
    out.println("Email: <input type='text' name='userEmail'> <br>");
    out.println("Message:<br> <textarea name='message' rows='4' cols='50'></textarea> <br>");
    out.println("<input type='submit' value='Add'> <br>");
    out.print("</form>");

    out.println("</body>");
    out.println("</html>");
  }

  /**
   * Handle login operation.
   * 
   * @param request an HttpServletRequest object that contains the request the client has made of the servlet.
   * @param response an HttpServletResponse object that contains the response the servlet sends to the client.
   * 
   */
  protected boolean doLogin(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

    // Read parameters from request.
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    User user = guestBookDao.findUserByEmailAndPassword(email, password);

    if (user != null) {
      session.setAttribute("user", user);
      return true;
    } else {
      request.setAttribute("error", "Login failed");
    }
    return false;

  }

  /**
   * Handle logout operation.
   * 
   * @param request an HttpServletRequest object that contains the request the client has made of the servlet.
   * @param response an HttpServletResponse object that contains the response the servlet sends to the client.
   * 
   */
  protected boolean doLogout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {

    session.removeAttribute("user");
    return false;

  }
}
