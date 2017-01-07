package ro.ucv.inf.ead.guestbook.dao;

import java.util.List;

import ro.ucv.inf.ead.guestbook.model.Comment;
import ro.ucv.inf.ead.guestbook.model.User;

/**
 * Data Access Objects Interface.
 * 
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

  /**
   * Add a user to admin users.
   * 
   * @param user The user to be added.
   */
  public void addUser(User user);

  /**
   * Find a user with specified id.
   * 
   * @param id The user Id.
   */
  public User findUser(long id);

  /**
   * Find a user with specified email.
   * 
   * @param email The user email.
   */
  public User findUser(String email);
  
  /**
   * Find a user with specified email and password.
   * 
   * @param email The user email.
   * @param password The user password.
   */
  public User findUserByEmailAndPassword(String email, String password);

}