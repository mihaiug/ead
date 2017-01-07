package ro.ucv.inf.ead.todo.service;

import java.util.List;

import ro.ucv.inf.ead.todo.model.User;

public interface UserService {
  public User findUser(Long userId);

  public User findUser(String name);

  public List<User> findAllUsers();

  public User add(User user);

  public User update(User user);

  public void delete(Long userId);
}
