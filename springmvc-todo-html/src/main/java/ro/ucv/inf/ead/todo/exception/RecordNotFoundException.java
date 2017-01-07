package ro.ucv.inf.ead.todo.exception;

public class RecordNotFoundException extends RuntimeException {
  public RecordNotFoundException() {
    super();
  }

  public RecordNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public RecordNotFoundException(final String message) {
    super(message);
  }

  public RecordNotFoundException(final Throwable cause) {
    super(cause);
  }
}
