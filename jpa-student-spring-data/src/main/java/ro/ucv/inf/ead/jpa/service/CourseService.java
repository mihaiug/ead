package ro.ucv.inf.ead.jpa.service;

import java.util.List;
import java.util.Set;

import ro.ucv.inf.ead.jpa.model.Course;
import ro.ucv.inf.ead.jpa.model.Student;

public interface CourseService {

  public Course findCourse(Long id);

  public Course findCourseByName(String name);

  public Set<Student> findEnrolledStudents(Long courseId);

  public List<Course> findAll();

  public Course addCourse(Course course);

  public void updateCourse(Course course);

  public void deleteCourse(Long id);

  public void enrollStudent(Long courseId, Long studentId);

  public void unenrollStudent(Long courseId, Long studentId);

}