package ro.ucv.inf.ead.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.ucv.inf.ead.spring.model.Student;
import ro.ucv.inf.ead.spring.repository.StudentRepository;

@Service("studentService")
public class StudentServiceImpl implements StudentService {
  
  @Autowired
  private StudentRepository studentRepository;

  public StudentServiceImpl() {
  }
  
  public StudentServiceImpl(StudentRepository studentRepository) {
     this.studentRepository = studentRepository;
  }
  
  public void enrollStudent(Student student) {
    if (student.getName() == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }

    if (student.getFaculty() == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }
    studentRepository.addStudent(student);
  }

  public List<Student> getAllEnrolledStudents() {
    return studentRepository.findAll();
  }
  

  public void setStudentRepository(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }
}
