package ro.ucv.inf.ead.todo.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ro.ucv.inf.ead.todo.dto.UserDTO;
import ro.ucv.inf.ead.todo.exception.DuplicateRecordException;
import ro.ucv.inf.ead.todo.model.Task;
import ro.ucv.inf.ead.todo.model.User;
import ro.ucv.inf.ead.todo.service.TaskService;
import ro.ucv.inf.ead.todo.service.UserService;

@Controller
public class UserController {

  private final Logger logger = LoggerFactory.getLogger(UserController.class);
  @Autowired
  private UserService userService;
  
  @Autowired
  private TaskService taskService;

  @Autowired
  private PasswordEncoder passwordEncoder;
  
  @RequestMapping(value = "/users", method = RequestMethod.GET)
  public String getUsers(Model model) {
    List<User> users = userService.findAllUsers();
    model.addAttribute("users", users);
    return "users";
  }
  
  @RequestMapping(value = "/user/{userId}/tasks", method = RequestMethod.GET)
  public String getUserTasks(@PathVariable("userId") Long userId, Model model) {
    List<Task> tasks = taskService.findUserTasks(userId);
    model.addAttribute("tasks", tasks);
    return "tasks";
  }
  
  
  @RequestMapping(value = "/user/add", method = RequestMethod.GET)
  public String getAddUserForm(Model model) {
    UserDTO user = new UserDTO();
    model.addAttribute("user", user);
    return "add-user";
  }
  
  /**
   * This method will be called on form submission, handling POST request for
   * add user in database. It also validates the user input
   */
  @RequestMapping(value = "/user/add", method = RequestMethod.POST)
  public String addUserForm(@Valid @ModelAttribute("user") UserDTO user, BindingResult result, RedirectAttributes redirectAttributes) {
    ValidationUtils.rejectIfEmptyOrWhitespace(result, "password", "notEmpty.password", "Password can not be empty");
    if (result.hasErrors()){
      logger.error("Add user error: {}",  result.getAllErrors());
      return "add-user";
    } else {
      try {
        User userToAdd = new User();
        userToAdd.setName(user.getName());
        userToAdd.setEmail(user.getEmail());
        //Before save encode password.
        userToAdd.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.add(userToAdd);
      } catch(DuplicateRecordException e) {
        result.rejectValue("email", "duplicate", "Email already used");
        logger.error("Add user error: " +  result.getAllErrors());
        return "add-user";
      }
      redirectAttributes.addFlashAttribute("message", "Successfully added..");
      return "redirect:/users";
    }
  }
  
  @RequestMapping(value = "/user/update", method = RequestMethod.GET)
  public String getEditUserForm(Model model, @RequestParam(value = "id", required = true) Long id,  RedirectAttributes redirectAttributes) {
    User existingUser = userService.findUser(id);
    if(existingUser != null){
      UserDTO user = new UserDTO();
      user.setId(existingUser.getId());
      user.setName(existingUser.getName());
      user.setEmail(existingUser.getEmail());
      user.setPassword(null);// Do not sent password.
      model.addAttribute("user", user);
      return "update-user";
    } else {
      redirectAttributes.addFlashAttribute("errorMessage", "User not found");
      return "redirect:/users";
    }
    
  }
  
  @RequestMapping(value = "/user/update", method = RequestMethod.POST)
  public String updateUser(@Valid @ModelAttribute("user") UserDTO user, BindingResult result) {
    if (result.hasErrors()) {
      logger.error("Update user error: " + result.getAllErrors());
      return "update-user";
    } else {
      try {
        User userToUpdate = new User();
        userToUpdate.setId(user.getId());
        userToUpdate.setName(user.getName());
        userToUpdate.setEmail(user.getEmail());
        if (user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
          // If password is not null encode password.
          userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
          user.setPassword(null);
        }

        userService.update(userToUpdate);
      } catch (DuplicateRecordException e) {
        result.rejectValue("email", "duplicate", "New email address already used by other user");
        logger.error("Update user error: " + result.getAllErrors());
        return "update-user";
      }
      return "redirect:/users";
    }
  }
  
  @RequestMapping(value = "/user/delete", method = RequestMethod.GET)
  public String deleteUser(@Valid @ModelAttribute("id") Long id,  
      BindingResult result,  RedirectAttributes redirectAttributes) {
    try{
      userService.delete(id);
      redirectAttributes.addFlashAttribute("message", "Successfully deleted.");
    } catch (DataIntegrityViolationException e) {
      String errorMessage = "Can not delete user because is assigned";
      logger.error(errorMessage);
      redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
    } catch (Exception e){
      redirectAttributes.addFlashAttribute("errorMessage", "Delete error: " + e.getMessage());      
    }
    
     return "redirect:/users";
  }
  
    
}
