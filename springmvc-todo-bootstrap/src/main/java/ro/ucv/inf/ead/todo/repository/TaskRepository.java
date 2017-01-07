package ro.ucv.inf.ead.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ro.ucv.inf.ead.todo.model.Task;
import ro.ucv.inf.ead.todo.model.User;

@Repository("taskRepository")
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {
 // @Query("SELECT p FROM User p WHERE p.name LIKE :searchTerm OR p.email LIKE :searchTerm")
  public List<Task> findByUserId(Long userId);
    
}