package ro.ucv.inf.ead.todo.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserDTO {

  private Long id;

  @NotBlank(message = "The user name can not be empty")
  @Size(max = 64, message = "The user name can exceed 64 characters")
  private String name;

  @NotBlank(message = "The user email can not be empty")
  @Email(message = "The user email is invalid")
  private String email;
  
  private String password;


  public UserDTO() {
  }
  
  public UserDTO(Long id) {
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


}