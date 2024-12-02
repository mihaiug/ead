package ro.ucv.inf.ead.spring.repository;

import java.util.*;

import org.springframework.stereotype.Repository;

import ro.ucv.inf.ead.spring.model.Student;

@Repository("studentRepository")
public class StudentRepositoryImpl implements StudentRepository {
     
  List<Student> students = new LinkedList<>();
  
  
  public StudentRepositoryImpl(){
    System.out.println("Create a StudentRepositoryImpl object.");
  }
  
  public List<Student> findAll(){
    return students;
  }
  

  public void addStudent(Student student){
    students.add(student);
  }
   
 }
