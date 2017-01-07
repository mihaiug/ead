package ro.ucv.inf.ead.guestbook.command;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.ucv.inf.ead.guestbook.dao.GuestBookDAO;
import ro.ucv.inf.ead.guestbook.model.Comment;

public class ListComments implements Command {

  /**
   * The guestbook data access manager.
   */
  private GuestBookDAO guestBookDao = null;

  public ListComments(GuestBookDAO guestBookDao) {
    this.guestBookDao = guestBookDao;
  }

  public String execute(HttpServletRequest request, HttpServletResponse response) {
    System.out.println("List comments");
    List<Comment> comments = guestBookDao.getAllComments();

    request.setAttribute("comments", comments);
    return "list.jsp";
  }

}
