package ro.ucv.inf.ead.todo.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ro.ucv.inf.ead.todo.exception.DuplicateRecordException;
import ro.ucv.inf.ead.todo.exception.RecordNotFoundException;
import ro.ucv.inf.ead.todo.model.User;
import ro.ucv.inf.ead.todo.repository.UserRepository;

@Service("userService")
public class UserServiceImpl implements UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  @Autowired
  private UserRepository userRepository;

  @Override
  public User findUser(Long userId) {
    return userRepository.findById(userId).orElse(null);
  }

  @Override
  public User findUser(String name) {
    return userRepository.findByName(name);
  }

  @Override
  public List<User> findAllUsers() {
    return userRepository.findAll();
  }

  @Override
  @Transactional
  public User add(User user) {
    // Check if already exists a user with same email.
    User existingUser = userRepository.findByEmail(user.getEmail());
    if (existingUser != null) {
      String errorMessage = "Already exists a user with same email: " + user.getEmail();
      logger.error(errorMessage);
      throw new DuplicateRecordException(errorMessage);
    }
    userRepository.save(user);
    return user;
  }

  @Override
  @Transactional
  public User update(User user) {
    User existingUser = userRepository.findById(user.getId()).orElse(null);
    if (existingUser == null) {
      String errorMessage = "User with id " + user.getId() + " not found";
      logger.error(errorMessage);
      throw new RecordNotFoundException(errorMessage);
    }

    // Check if email was changed.
    if (!existingUser.getEmail().equals(user.getEmail())) {
      // Email changed, check again if new email already exists
      if (userRepository.findByEmail(user.getEmail()) != null) {
        String errorMessage = "The new email address already used by another user: " + user.getEmail();
        logger.error(errorMessage);
        throw new DuplicateRecordException(errorMessage);
      }
    }
    if (user.getPassword() == null) {
      // if password is null keep the existing password
      user.setPassword(existingUser.getPassword());
    }

    return userRepository.save(user);
  }

  @Override
  @Transactional
  public void delete(Long userId) {
    User user = userRepository.findById(userId).orElse(null);
    logger.debug("Delete user with id: " + userId);
    if (user != null) {
      userRepository.delete(user);
    } else {
      String errorMessage = "User with id " + userId + " not found";
      logger.error(errorMessage);
      throw new RecordNotFoundException(errorMessage);
    }
  }

}
