package ro.ucv.inf.ead.spring.repository;

import java.util.List;

import ro.ucv.inf.ead.spring.model.Student;

public interface StudentRepository {

  List<Student> findAll();

  void addStudent(Student student);

  void init();
  void shutdown();
}