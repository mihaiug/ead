package ro.ucv.inf.ead.jpa.service;

import java.util.List;

import ro.ucv.inf.ead.jpa.model.Student;

public interface StudentService {

  public Student findStudent(Long id);
  
  public Student findStudentByName(String name);
  
  public List<Student> findAll();

  public Student addStudent(Student student);
  
  public void updateStudent(Student student);
  
  public void deleteStudent(Long id);
  
}