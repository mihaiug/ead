package ro.ucv.inf.ead.guestbook.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The database connection manager.
 *
 */
public class DBConnectionManager {
  public static final String DEFAULT_CONFIG_FILE = "config.properties";

  private String dbDriver;
  private String dbUrl;
  private String dbUser;
  private String dbPassword;

  private Connection connection;

  public DBConnectionManager() throws IOException, DBException {
    this(DEFAULT_CONFIG_FILE);
  }

  public DBConnectionManager(String configurationFile) throws IOException, DBException {
    InputStream inputStream = DBConnectionManager.class.getClassLoader().getResourceAsStream(configurationFile);
    Properties properties = new Properties();
    properties.load(inputStream);

    dbDriver = properties.getProperty("db.driver");
    dbUrl = properties.getProperty("db.url");
    dbUser = properties.getProperty("db.user");
    dbPassword = properties.getProperty("db.password");

    initialize();
  }

  public void initialize() throws DBException {
    try {
      Class.forName(dbDriver);
      connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    } catch (ClassNotFoundException e) {
      System.err.println("Driver not found: " + e.getMessage());
      throw new DBException("Driver not found: " + e.getMessage(), e);
    } catch (SQLException e) {
      System.err.println("Cannot connect to database: " + e.getMessage());
      throw new DBException("Cannot connect to database: " + e.getMessage(), e);
    }
  }

  public Connection getConnection() {
    if (connection == null) {
      throw new IllegalStateException("Not Connected to Database");
    }
    return connection;
  }

  public void closeConnection() {
    if (connection != null) {
      try {
        connection.close();
        connection = null;
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
