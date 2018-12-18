package ro.ucv.inf.ead.todo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.ucv.inf.ead.todo.exception.RecordNotFoundException;
import ro.ucv.inf.ead.todo.model.Task;
import ro.ucv.inf.ead.todo.repository.TaskRepository;

@Service("taskService")
public class TaskServiceImpl implements TaskService {

  private static final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

  @Autowired
  private TaskRepository taskRepository;

  @Override
  public Task findTask(Long taskId) {
    Task task = taskRepository.findById(taskId).orElse(null);
    return task;
  }

  @Override
  @Transactional(readOnly = true)
  public List<Task> findAllTasks() {
    return taskRepository.findAll();
  }

  @Override
  @Transactional(readOnly = true)
  public List<Task> findUserTasks(Long userId) {
    return taskRepository.findByUserId(userId);
  }

  @Override
  @Transactional
  public Task add(Task task) {
    taskRepository.save(task);
    return task;
  }

  @Override
  @Transactional
  public Task update(Task task) {
    Task existingTask = taskRepository.findById(task.getId()).orElse(null);
    if (existingTask == null) {
      String errorMessage = "Task with id " + task.getId() + " not found";
      logger.error(errorMessage);
      throw new RecordNotFoundException(errorMessage);
    }
    return taskRepository.save(task);
  }

  @Override
  @Transactional
  public void delete(Long taskId) {
    Task task = taskRepository.findById(taskId).orElse(null);
    logger.debug("Delete task with id: " + taskId);
    if (task != null) {
      taskRepository.deleteById(taskId);
    } else {
      String errorMessage = "Task with id " + taskId + " not found";
      logger.error(errorMessage);
      throw new RecordNotFoundException(errorMessage);
    }
  }

}
