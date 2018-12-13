package ro.ucv.inf.ead.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ro.ucv.inf.ead.jpa.model.Student;

@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Student> {
  @Query("SELECT s FROM Student s WHERE s.name = :name")
  Student findByName(@Param("name") String name);
}