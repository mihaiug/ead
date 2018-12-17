package ro.ucv.inf.ead.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ro.ucv.inf.ead.todo.model.Task;

@Repository("taskRepository")
public interface TaskRepository extends JpaRepository<Task, Long>, JpaSpecificationExecutor<Task> {

  public List<Task> findByUserId(Long userId);

}