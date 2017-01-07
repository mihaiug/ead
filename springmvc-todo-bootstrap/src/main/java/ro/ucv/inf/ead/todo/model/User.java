package ro.ucv.inf.ead.todo.model;

import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @NotNull(message = "The user name can not be null")
  @Size(min = 1, message = "The user name can not be empty")
  @Column(name = "name", nullable = false, length = 64)
  private String name;

  @NotBlank(message = "The user email can not be empty")
  @Email(message = "The user email is invalid")
  @Column(name = "email", nullable = false, unique = true)
  private String email;

  @Column(name = "password")
  private String password;

  @OneToMany(mappedBy = "user")
  private Collection<Task> tasks = new LinkedList<Task>();

  public User() {
  }
  
  public User(Long id) {
    this.id = id;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Collection<Task> getTasks() {
    return tasks;
  }

  public void setTasks(Collection<Task> tasks) {
    this.tasks = tasks;
  }

}