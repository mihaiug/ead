package ro.ucv.inf.ead.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.ucv.inf.ead.jpa.exception.DuplicateRecordException;
import ro.ucv.inf.ead.jpa.exception.RecordNotFoundException;
import ro.ucv.inf.ead.jpa.model.Student;
import ro.ucv.inf.ead.jpa.repository.StudentRepository;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

  @Autowired
  private StudentRepository studentRepository;

  public StudentServiceImpl() {
  }

  @Override
  public Student findStudent(Long id) {

    return studentRepository.findOne(id);
  }

  @Override
  @Transactional(readOnly = true)
  public Student findStudentByName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Student name cannot be null");
    }
    return studentRepository.findByName(name);
  }

  @Override
  public List<Student> findAll() {
    return studentRepository.findAll();
  }

  @Override
  @Transactional
  public Student addStudent(Student student) {
    if (student.getName() == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }

    if (student.getFaculty() == null) {
      throw new IllegalArgumentException("Faculty cannot be null");
    }

    // Before add check if user with same name already exists.
    Student existingStudent = studentRepository.findByName(student.getName());
    if (existingStudent != null) {
      throw new DuplicateRecordException("Student with same name already exists");
    }
    return studentRepository.save(student);
  }

  @Override
  @Transactional
  public void updateStudent(Student student) {
    Student existingStudent = studentRepository.findOne(student.getId());
    if (existingStudent == null) {
      throw new RecordNotFoundException("Student with id " + student.getId() + " not found");
    }
    studentRepository.save(student);
  }

  @Override
  @Transactional
  public void deleteStudent(Long id) {
    studentRepository.delete(id);

  }

  public void setStudentRepository(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

}
