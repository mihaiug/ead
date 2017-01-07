package ro.ucv.inf.ead.guestbook.db;

public class DBException extends RuntimeException {
  public DBException() {
    super();
  }

  public DBException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public DBException(final String message) {
    super(message);
  }

  public DBException(final Throwable cause) {
    super(cause);
  }
}

