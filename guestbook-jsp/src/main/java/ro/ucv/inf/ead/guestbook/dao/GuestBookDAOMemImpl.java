package ro.ucv.inf.ead.guestbook.dao;

import java.util.LinkedList;
import java.util.List;

import ro.ucv.inf.ead.guestbook.model.Comment;

/**
 * In Memory DAO Implementation.
 */
public class GuestBookDAOMemImpl implements GuestBookDAO {
  /**
   * Keep the list of guestbook messages.
   */
  private List<Comment> comments = new LinkedList<>();

  /**
   * The last comment id. It is used to generate unique ids.
   */
  private long lastCommentId = 0;

  /**
   * Find a comment with specified id.
   * 
   * @param id  The comment Id.
   * @return The comment with specified id or <code>null</code> if comment not found.
   */
  public Comment findComment(long id) {
    Comment comment = null;
    for (Comment currentComment : comments) {
      if (currentComment.getId() == id) {
        comment = currentComment;
        break;
      }
    }

    return comment;
  }

  /**
   * Add a comment to guestbook.
   * 
   * @param comment  The message to be added.
   */
  public void addComment(Comment comment) {
    comment.setId(getNextCommentId());
    comments.add(comment);
  }

  /**
   * Gets the list of messages from guestbook.
   * 
   * @return A list containing the guest book messages.
   */
  public List<Comment> getAllComments() {
    return comments;
  }

  /**
   * Delete comment with specified id.
   * 
   * @param id The comment Id.
   * 
   * @return <code>true</true> if operation is done.
   */
  public boolean deleteComment(long id) {
    Comment comment = findComment(id);
    if (comment != null) {
      return comments.remove(comment);
    }

    return false;
  }

  /**
   * Provide a unique comment ID.
   * 
   * @return A new comment ID.
   */
  private synchronized long getNextCommentId() {
    return ++lastCommentId;
  }
}
