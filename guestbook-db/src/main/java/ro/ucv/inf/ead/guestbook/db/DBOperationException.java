package ro.ucv.inf.ead.guestbook.db;

public class DBOperationException extends DBException {
  public DBOperationException() {
    super();
  }

  public DBOperationException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public DBOperationException(final String message) {
    super(message);
  }

  public DBOperationException(final Throwable cause) {
    super(cause);
  }
}

