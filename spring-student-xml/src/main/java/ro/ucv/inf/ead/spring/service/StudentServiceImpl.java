package ro.ucv.inf.ead.spring.service;

import java.util.List;

import ro.ucv.inf.ead.spring.model.Student;
import ro.ucv.inf.ead.spring.repository.StudentRepository;

public class StudentServiceImpl implements StudentService {

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
    if (student.getName() == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }

    if (student.getFaculty() == null) {
      throw new IllegalArgumentException("Faculty cannot be null");
    }
    studentRepository.addStudent(student);
  }

  @Override
  public List<Student> getAllEnrolledStudents() {
    return studentRepository.findAll();
  }

  public void setStudentRepository(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }
}
