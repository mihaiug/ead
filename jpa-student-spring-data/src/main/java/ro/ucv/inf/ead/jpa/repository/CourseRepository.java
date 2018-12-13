package ro.ucv.inf.ead.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ro.ucv.inf.ead.jpa.model.Course;

@Repository("courseRepository")
public interface CourseRepository extends JpaRepository<Course, Long>, JpaSpecificationExecutor<Course> {

  Course findByName(@Param("name") String name);

  /**
   * Find the course with specified id along with the students attending the course.
   * 
   * @param id The course id.
   * @return The searched course or null if not found.
   */
  @Query("SELECT course FROM Course course LEFT JOIN FETCH course.students WHERE course.id =:id") // The 'FETCH course.students' force load LAZY students collection from Course
  Course findOneWithStudentsById(@Param("id") Long id);
}