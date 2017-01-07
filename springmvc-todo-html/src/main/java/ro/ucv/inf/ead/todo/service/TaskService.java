package ro.ucv.inf.ead.todo.service;

import java.util.List;

import ro.ucv.inf.ead.todo.model.Task;

public interface TaskService {

  public Task findTask(Long taskId);

  public List<Task> findAllTasks();
  
  public List<Task> findUserTasks(Long userId);

  public Task add(Task task);

  public Task update(Task task);

  public void delete(Long taskId);
}
