package ro.ucv.inf.ead.guestbook.dao;

import java.util.List;

import ro.ucv.inf.ead.guestbook.model.Comment;

/**
 * Data Access Objects Interface.
 */
public interface GuestBookDAO {

  /**
   * Find a comment with specified id.
   * 
   * @param id The comment Id.
   */
  public Comment findComment(long id);

  /**
   * Add a comment to guestbook.
   * 
   * @param comment The message to be added.
   */
  public void addComment(Comment comment);

  /**
   * Gets the list of messages from guestbook.
   * 
   * @return A list containing the guest book messages.
   */
  public List<Comment> getAllComments();

  /**
   * Delete comment with specified id.
   * 
   * @param id The comment Id.
   * 
   * @return <code>true</true> if operation is done.
   */
  public boolean deleteComment(long id);

}