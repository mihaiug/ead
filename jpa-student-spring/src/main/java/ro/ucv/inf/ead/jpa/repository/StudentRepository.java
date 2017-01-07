package ro.ucv.inf.ead.jpa.repository;

import java.util.List;

import ro.ucv.inf.ead.jpa.model.Student;

public interface StudentRepository {

  public Student findOne(Long id);

  public Student findByName(String name);

  public List<Student> findAll();

  public Student save(Student student);

  public void delete(Long id);

}