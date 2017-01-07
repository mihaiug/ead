package ro.ucv.inf.ead.spring.repository;

import java.util.LinkedList;
import java.util.List;

import ro.ucv.inf.ead.spring.model.Student;

public class StudentRepositoryImpl implements StudentRepository {

  List<Student> students = new LinkedList<Student>();

  public StudentRepositoryImpl() {
    System.out.println("Create a StudentRepositoryImpl object.");
  }

  public List<Student> findAll() {
    return students;
  }

  public void addStudent(Student student) {
    students.add(student);
  }

  /**
   * Run after all properties are set on the bean.
   */
  public void init() {
    System.out.println("Run StudentRepositoryImpl.init() method");
  }

  /**
   * Run when .
   */
  public void destroy() {
    System.out.println("Run StudentRepositoryImpl.destroy() method");
  }
}
