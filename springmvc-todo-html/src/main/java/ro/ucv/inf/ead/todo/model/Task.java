package ro.ucv.inf.ead.todo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "tasks")
public class Task {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(length = 512)
  private String description;

  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern = "dd-MM-yyyy")
  @Column(name = "dueDate")
  private Date dueDate;

  @Enumerated(EnumType.STRING)
  @Column(name = "status")
  private Status status = Status.NOT_STARTED;

  @ManyToOne
  @JoinColumn(name = "userId", nullable = false)
  private User user;

  public Task() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Task[");
    sb.append("id=" + id);
    sb.append(", name=" + name);
    sb.append(", description=" + description);
    sb.append(", status =" + status);
    if (getUser() != null) {
      sb.append(", userId =" + getUser().getId());
    }
    sb.append("]");
    return sb.toString();

  }

  public static enum Status {
    NOT_STARTED("not started"), IN_PROGRESS("in progress"), DONE("done");

    private final String displayName;

    Status(String displayName) {
      this.displayName = displayName;
    }

    public String getDisplayName() {
      return displayName;
    }
  }

}
