package ro.ucv.inf.ead.jpa.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.ucv.inf.ead.jpa.exception.DuplicateRecordException;
import ro.ucv.inf.ead.jpa.exception.RecordNotFoundException;
import ro.ucv.inf.ead.jpa.model.Course;
import ro.ucv.inf.ead.jpa.model.Student;
import ro.ucv.inf.ead.jpa.repository.CourseRepository;
import ro.ucv.inf.ead.jpa.repository.StudentRepository;

@Service("courseService")
public class CourseServiceImpl implements CourseService {

  @Autowired
  private CourseRepository courseRepository;

  @Autowired
  private StudentRepository studentRepository;

  @PersistenceContext
  private EntityManager em;

  public CourseServiceImpl() {
  }

  @Override
  @Transactional(readOnly = true)
  public Course findCourse(Long id) {
    Course course = courseRepository.findById(id).orElse(null);
    return course;
  }

  @Transactional(readOnly = true)
  public Set<Student> findEnrolledStudents(Long courseId) {
    Course course = courseRepository.findOneWithStudentsById(courseId);
    if (course != null) {
      return course.getStudents();
    } else {
      return Collections.emptySet();
    }
  }

  @Override
  public Course findCourseByName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Course name cannot be null");
    }
    return courseRepository.findByName(name);
  }

  @Override
  public List<Course> findAll() {
    return courseRepository.findAll();
  }

  @Override
  @Transactional
  public Course addCourse(Course course) {
    if (course.getName() == null) {
      throw new IllegalArgumentException("Name cannot be null");
    }

    // Before add check if course with same name already exists.
    Course existingCourse = courseRepository.findByName(course.getName());
    if (existingCourse != null) {
      throw new DuplicateRecordException("Course with same name already exists");
    }
    return courseRepository.save(course);
  }

  @Override
  @Transactional
  public void updateCourse(Course course) {
    Course existingCourse = findCourse(course.getId());
    if (existingCourse == null) {
      throw new RecordNotFoundException("Course with id " + course.getId() + " not found");
    }
    courseRepository.save(course);
  }

  @Override
  @Transactional
  public void deleteCourse(Long id) {
    courseRepository.deleteById(id);

  }

  public void setCourseRepository(CourseRepository courseRepository) {
    this.courseRepository = courseRepository;
  }

  @Override
  @Transactional
  public void enrollStudent(Long courseId, Long studentId) {
    Course course = findCourse(courseId);
    if (course == null) {
      throw new RecordNotFoundException("Course with id " + courseId + " not found");
    }

    Student student = studentRepository.findById(studentId).orElse(null);
    if (student == null) {
      throw new RecordNotFoundException("Student with id " + studentId + " not found");
    }

    course.getStudents().add(student);
    student.getCourses().add(course);

    studentRepository.save(student);

  }

  @Override
  @Transactional
  public void unenrollStudent(Long courseId, Long studentId) {
    Course course = findCourse(courseId);
    if (course == null) {
      throw new RecordNotFoundException("Course with id " + courseId + " not found");
    }

    Student student = studentRepository.findById(studentId).orElse(null);
    if (student == null) {
      throw new RecordNotFoundException("Student with id " + studentId + " not found");
    }

    student.getCourses().remove(course);

    studentRepository.save(student);

  }

}
