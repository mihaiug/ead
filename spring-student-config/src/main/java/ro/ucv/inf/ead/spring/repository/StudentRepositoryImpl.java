package ro.ucv.inf.ead.spring.repository;

import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.ucv.inf.ead.spring.model.Student;


public class StudentRepositoryImpl implements StudentRepository {
  
  private static final Logger logger = LoggerFactory.getLogger(StudentRepositoryImpl.class);
     
  private List<Student> students = new LinkedList<>();
  
  public StudentRepositoryImpl(){
    logger.debug("Create a {} object.", StudentRepositoryImpl.class.getName());
  }
  
  @Override
  public List<Student> findAll(){
    return students;
  }
  
  @Override
  public void addStudent(Student student){
    students.add(student);
  }
  
  /**
   * Run after all properties are set on the bean.
   */
  public void init() {
    logger.debug("Call StudentRepositoryImpl.init()");
    System.out.println("Call StudentRepositoryImpl.init()");
  }

   /**
   * Run when the bean is destroyed.
   */
  public void shutdown() {
    logger.debug("Call StudentRepositoryImpl.shutdown()");
    System.out.println("Call StudentRepositoryImpl.shutdown()");
  }

 }
