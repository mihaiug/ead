package ro.ucv.inf.ead.spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import ro.ucv.inf.ead.spring.model.Student;
import ro.ucv.inf.ead.spring.repository.StudentRepository;


public class StudentServiceImpl implements StudentService {
  
  private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
  
  //@Autowired
  private StudentRepository studentRepository;

  public StudentServiceImpl() {
    System.out.println("Create StudentServiceImpl using default constructor");
  }
  
  public StudentServiceImpl(StudentRepository studentRepository) {
    System.out.println("Create StudentServiceImpl using constructor with args");
    this.studentRepository = studentRepository;
  }

  @Override
  public void enrollStudent(Student student) {
    logger.debug("Start enrolling user: {}", student);
    if (student.getName() == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }

    if (student.getFaculty() == null) {
      throw new IllegalArgumentException("Faculty cannot be null");
    }
    studentRepository.addStudent(student);
    logger.info("User {} was enrolled with success", student);
  }

  @Override
  public List<Student> getAllEnrolledStudents() {
    return studentRepository.findAll();
  }

  public void setStudentRepository(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }
}
