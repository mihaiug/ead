package ro.ucv.inf.ead.todo.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import ro.ucv.inf.ead.todo.model.Task;

public class TaskDTO {
  private Long id;

  @NotBlank(message = "The task name can not be empty")
  private String name;

  private String description;

  @DateTimeFormat(pattern = "dd-MM-yyyy")
  private Date dueDate;

  private Task.Status status;

  @NotNull
  private Long userId;

  public TaskDTO() {

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

  public Task.Status getStatus() {
    return status;
  }

  public void setStatus(Task.Status status) {
    this.status = status;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}
