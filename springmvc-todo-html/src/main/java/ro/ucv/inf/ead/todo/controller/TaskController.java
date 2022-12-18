package ro.ucv.inf.ead.todo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ro.ucv.inf.ead.todo.dto.TaskDTO;
import ro.ucv.inf.ead.todo.model.Task;
import ro.ucv.inf.ead.todo.model.User;
import ro.ucv.inf.ead.todo.service.TaskService;
import ro.ucv.inf.ead.todo.service.UserService;

@Controller
public class TaskController {

  private final Logger logger = LoggerFactory.getLogger(TaskController.class);
  @Autowired
  private TaskService taskService;

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/tasks", method = RequestMethod.GET)
  public String getTasks(Model model) {
    List<Task> tasks = taskService.findAllTasks();
    model.addAttribute("tasks", tasks);
    return "tasks";
  }


  @RequestMapping(value = "/task/add", method = RequestMethod.GET)
  public String getAddTaskForm(Model model) {
    // Add list of users to model to populate the users selector.
    List<User> users = userService.findAllUsers();
    model.addAttribute("users", users);

    TaskDTO task = new TaskDTO();
    model.addAttribute("task", task);
    return "add-task";
  }

  /**
   * This method will be called on form submission, handling POST request for
   * saving task in database. It also validates the task input
   */
  @RequestMapping(value = "/task/add", method = RequestMethod.POST)
  public String addTaskForm(@Valid @ModelAttribute("task") TaskDTO taskDTO, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      logger.error("Add task error: " + result.getAllErrors());
      // Add list of users to model to populate the users selector.
      List<User> users = userService.findAllUsers();
      model.addAttribute("users", users);
      return "add-task";
    } else {
      Task task = new Task();
      task.setName(taskDTO.getName());
      task.setDescription(taskDTO.getDescription());
      task.setStatus(Task.Status.NOT_STARTED);
      task.setDueDate(taskDTO.getDueDate());
      task.setUser(new User(taskDTO.getUserId()));
      taskService.add(task);
      redirectAttributes.addFlashAttribute("message", "Successfully added..");
      return "redirect:/tasks";
    }
  }

  @RequestMapping(value = "/task/update", method = RequestMethod.GET)
  public String getEditTaskForm(@RequestParam(value = "id", required = true) Long id,
      Model model,  RedirectAttributes redirectAttributes) {
    Task task = taskService.findTask(id);
    if (task != null) {
      logger.debug("Edit task {}", task);
      // Create and put a TaskDTO needed to edit task.
      TaskDTO taskDTO = new TaskDTO();
      taskDTO.setId(task.getId());
      taskDTO.setName(task.getName());
      taskDTO.setDescription(task.getDescription());
      taskDTO.setStatus(task.getStatus());
      taskDTO.setDueDate(task.getDueDate());
      taskDTO.setUserId(task.getUser().getId()); 
      
      model.addAttribute("task", taskDTO);
      
      // Add to model supplementary attributes needed to construct the edit form.
      // Add list of users to model to populate the users selector.
      List<User> users = userService.findAllUsers();
      model.addAttribute("users", users);
      // Add list of possible statutes.
      model.addAttribute("statuses", Task.Status.values());
      return "update-task";
    } else {
      logger.error("Edit error: Task with id {} not found", id);
      redirectAttributes.addFlashAttribute("errorMessage", "Task with specified id not found");
      return "redirect:/tasks";
    }
    
  }

  @RequestMapping(value = "/task/update", method = RequestMethod.POST)
  public String updateTask(@Valid @ModelAttribute("task") TaskDTO taskDTO, BindingResult result, 
      Model model,  RedirectAttributes redirectAttributes) {
    if (result.hasErrors()) {
      logger.error("Update task validation error: " + result.getAllErrors());
      
      // Add to model supplementary attributes needed to construct the edit form.
      // Add list of users to model to populate the users selector.
      List<User> users = userService.findAllUsers();
      model.addAttribute("users", users);
      // Add list of possible statutes.
      model.addAttribute("statuses", Task.Status.values());
      
      return "update-task";
      
    } else {
      Task task = new Task();
      task.setId(taskDTO.getId());
      task.setName(taskDTO.getName());
      task.setDescription(taskDTO.getDescription());
      task.setStatus(taskDTO.getStatus());
      task.setDueDate(taskDTO.getDueDate());
      task.setUser(new User(taskDTO.getUserId()));
      taskService.update(task); 
      
      return "redirect:/tasks";
    }
  }
  
  @RequestMapping(value = "/task/delete", method = RequestMethod.GET)
  public String deleteTask(@Valid @ModelAttribute("id") Long id,  
      BindingResult result,  RedirectAttributes redirectAttributes) {
    try{
      taskService.delete(id);
      redirectAttributes.addFlashAttribute("message", "Successfully deleted..");
    } catch (Exception e){
      redirectAttributes.addFlashAttribute("errorMessage", "Delete error: " + e.getMessage());
    }
    
     return "redirect:/tasks";
  }

}
