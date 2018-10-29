package ro.ucv.inf.ead.guestbook.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.ucv.inf.ead.guestbook.dao.GuestBookDAO;

/**
 * Handle remove a comment.
 *
 */
public class DeleteComment implements Command {

  /**
   * The guestbook data access manager.
   */
  private GuestBookDAO guestBookDao = null;

  public DeleteComment(GuestBookDAO guestBookDao) {
    super();
    this.guestBookDao = guestBookDao;
  }

  public String execute(HttpServletRequest request, HttpServletResponse response) {
    // Read parameters from request.
    String idParam = request.getParameter("id");
    String errorMessage = null;
    long id = 0;
    try {
      id = Long.parseLong(idParam);
    } catch (NumberFormatException e) {
      errorMessage = "Parameter id has invalid value:" + idParam;
    }

    if (errorMessage != null) {
      request.setAttribute("errorMessage", errorMessage);
    } else {
      guestBookDao.deleteComment(id);
    }
    return "index.jsp";
  }

}
