package ro.ucv.inf.ead.guestbook.command;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.ucv.inf.ead.guestbook.dao.GuestBookDAO;
import ro.ucv.inf.ead.guestbook.model.Comment;


/**
 * Handle add a comment.
 *
 */
public class AddComment implements Command {

  /**
   * The guestbook data access manager.
   */
  private GuestBookDAO guestBookDao = null;

  public AddComment(GuestBookDAO guestBookDao) {
    this.guestBookDao = guestBookDao;
  }

  public String execute(HttpServletRequest request, HttpServletResponse response) {
    // Read parameters from request.
    String userName = request.getParameter("userName");
    String userEmail = request.getParameter("userEmail");
    String message = request.getParameter("message");
    Date date = new Date();

    // Parameters validation.

    boolean error = false;
    if (userName == null || userName.trim().isEmpty()) {
      request.setAttribute("errorMessage", "Username is empty");
      error = true;
    }
    
    //TODO: Add more validations.

    if (!error) {
      Comment comment = new Comment();
      comment.setUserName(userName);
      comment.setUserEmail(userEmail);
      comment.setMessage(message);
      comment.setDate(date);
      guestBookDao.addComment(comment);
    }

    return "index.jsp";
  }

}
