package ro.ucv.inf.ead.spring.repository;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import ro.ucv.inf.ead.spring.model.Student;

@Repository("studentRepository")
public class StudentRepositoryImpl implements StudentRepository {

  private static final Logger logger = LoggerFactory.getLogger(StudentRepositoryImpl.class);

  private List<Student> students = new LinkedList<>();

  public StudentRepositoryImpl() {
    logger.debug("Create a {} object.", StudentRepositoryImpl.class.getName());
  }

  @Override
  public List<Student> findAll() {
    return students;
  }

  @Override
  public void addStudent(Student student) {
    students.add(student);
  }

  /**
   * Run after all properties are set on the bean.
   */
  @PostConstruct
  public void init() {
    logger.debug("Call StudentRepositoryImpl.init()");
    System.out.println("Call StudentRepositoryImpl.init()");
  }

   /**
   * Run when the bean is destroyed.
   */
  @PreDestroy
  public void shutdown() {
    logger.debug("Call StudentRepositoryImpl.shutdown()");
    System.out.println("Call StudentRepositoryImpl.shutdown()");
  }

}
