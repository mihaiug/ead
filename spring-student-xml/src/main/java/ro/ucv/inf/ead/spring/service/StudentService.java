package ro.ucv.inf.ead.spring.service;

import java.util.List;

import ro.ucv.inf.ead.spring.model.Student;

public interface StudentService {

  void enrollStudent(Student student);

  List<Student> getAllEnrolledStudents();

}