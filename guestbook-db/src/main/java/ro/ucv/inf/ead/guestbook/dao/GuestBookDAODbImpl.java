package ro.ucv.inf.ead.guestbook.dao;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ro.ucv.inf.ead.guestbook.db.DBConnectionManager;
import ro.ucv.inf.ead.guestbook.db.DBException;
import ro.ucv.inf.ead.guestbook.db.DBOperationException;
import ro.ucv.inf.ead.guestbook.model.Comment;
import ro.ucv.inf.ead.guestbook.model.User;

/**
 * In Memory DAO Implementation.
 * 
 */
public class GuestBookDAODbImpl implements GuestBookDAO {

  /**
   * Database connection manager.
   */
  private DBConnectionManager dbConnectionManager = null;


  public GuestBookDAODbImpl() throws IOException, DBException {
    dbConnectionManager = new DBConnectionManager();
  }

  /**
   * Find a comment with specified id.
   * 
   * @param id The comment Id.
   * @return The comment with specified id or <code>null</code> if comment not found.
   */
  public Comment findComment(long id) throws DBOperationException {
    Comment comment = null;
    try {
      String query = "SELECT * FROM comments WHERE id = ?";
      PreparedStatement preparedStatement = dbConnectionManager.getConnection().prepareStatement(query);
      preparedStatement.setLong(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        comment = new Comment();
        comment.setId(resultSet.getLong("id"));
        comment.setUserName(resultSet.getString("userName"));
        comment.setUserEmail(resultSet.getString("userEmail"));
        comment.setMessage(resultSet.getString("message"));
        comment.setDate(resultSet.getDate("date"));
      }
      resultSet.close();
      preparedStatement.close();
    } catch (SQLException e) {
      throw new DBOperationException(e.getMessage(), e);
    }

    return comment;
  }

  /**
   * Add a comment to guestbook.
   * 
   * @param comment The message to be added.
   */
  public void addComment(Comment comment) {
    try {
      String query = "INSERT INTO comments (userName, userEmail, `date`, message) VALUES (?,?,?,?)";
      PreparedStatement preparedStatement = dbConnectionManager.getConnection().prepareStatement(query);
      preparedStatement.setString(1, comment.getUserName());
      preparedStatement.setString(2, comment.getUserEmail());
      preparedStatement.setTimestamp(3, new java.sql.Timestamp(comment.getDate().getTime()));
      preparedStatement.setString(4, comment.getMessage());
      preparedStatement.executeUpdate();
      preparedStatement.close();
    } catch (SQLException e) {
      throw new DBOperationException(e.getMessage(), e);
    }
  }

  /**
   * Gets the list of messages from guestbook.
   * 
   * @return A list containing the guest book messages.
   */
  public List<Comment> getAllComments() {
    List<Comment> comments = new ArrayList<Comment>();
    try {
      String query = "SELECT * FROM comments";
      PreparedStatement preparedStatement = dbConnectionManager.getConnection().prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery(query);
      while (resultSet.next()) {
        Comment comment = new Comment();
        comment.setId(resultSet.getLong("id"));
        comment.setUserName(resultSet.getString("userName"));
        comment.setUserEmail(resultSet.getString("userEmail"));
        comment.setMessage(resultSet.getString("message"));
        comment.setDate(resultSet.getTimestamp("date"));
        comments.add(comment);
      }
      resultSet.close();
      preparedStatement.close();
    } catch (SQLException e) {
      throw new DBOperationException(e.getMessage(), e);
    }
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
    boolean delete = false;
    try {
      String query = "DELETE FROM comments WHERE id = ?";
      PreparedStatement preparedStatement = dbConnectionManager.getConnection().prepareStatement(query);
      preparedStatement.setLong(1, id);
      int rowCount = preparedStatement.executeUpdate();
      preparedStatement.close();
      delete = rowCount > 0;
    } catch (SQLException e) {
      throw new DBOperationException(e.getMessage(), e);
    } 
    return delete;
  }

 

  /**
   * Add a user to admin users.
   * 
   * @param user The user to be added.
   */
  public void addUser(User user) {
    try {
      String query = "INSERT INTO users (name, email, password) VALUES (?,?,MD5(?))";
      PreparedStatement preparedStatement = dbConnectionManager.getConnection().prepareStatement(query);
      preparedStatement.setString(1, user.getName());
      preparedStatement.setString(2, user.getEmail());
      preparedStatement.setString(3, user.getPassword());
      preparedStatement.executeUpdate();
      preparedStatement.close();
    } catch (SQLException e) {
      throw new DBOperationException(e.getMessage(), e);
    }
  }

  /**
   * Find a user with specified id.
   * 
   * @param id The user Id.
   */
  public User findUser(long id) {
    User user = null;
    
    try {
      String query = "SELECT * FROM users WHERE id = ?";
      PreparedStatement preparedStatement = dbConnectionManager.getConnection().prepareStatement(query);
      preparedStatement.setLong(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));        
      }
      resultSet.close();
      preparedStatement.close();
    } catch (SQLException e) {
      throw new DBOperationException(e.getMessage(), e);
    }

    
    return user;
  }

  /**
   * Find a user with specified email.
   * 
   * @param email The user email.
   */
  public User findUser(String email) {
    User user = null;
    try {
      String query = "SELECT * FROM users WHERE email = ?";
      PreparedStatement preparedStatement = dbConnectionManager.getConnection().prepareStatement(query);
      preparedStatement.setString(1, email);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));        
      }
      resultSet.close();
      preparedStatement.close();
    } catch (SQLException e) {
      throw new DBOperationException(e.getMessage(), e);
    }
    return user;
  }
  
  /**
   * Find a user with specified email and password.
   * 
   * @param email The user email.
   * @param password The user password.
   */
  public User findUserByEmailAndPassword(String email, String password){
    User user = null;
    try {
      String query = "SELECT * FROM users WHERE email = ? and password = MD5(?)";
      PreparedStatement preparedStatement = dbConnectionManager.getConnection().prepareStatement(query);
      preparedStatement.setString(1, email);
      preparedStatement.setString(2, password);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));        
      }
      resultSet.close();
      preparedStatement.close();
    } catch (SQLException e) {
      throw new DBOperationException(e.getMessage(), e);
    }
    return user;
  }


  /**
   * This is for tests only.
   */
  public static void main(String[] args) throws DBException, IOException {
    GuestBookDAO guestBookDAO = new GuestBookDAODbImpl();
    guestBookDAO.addComment(new Comment(0, "Mihai", "mihai@inf.ucv.ro", new Date(), "Hello from Mihai"));

    Comment comment = guestBookDAO.findComment(1L);
    System.out.println("Comment = " + comment);
    
    List<Comment> comments = guestBookDAO.getAllComments();
    System.out.println(comments);
  }
}
