package ro.ucv.inf.ead.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ro.ucv.inf.ead.todo.model.User;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query("select p from User p where p.name = :name")
    User findByName(@Param("name") String name);
    
    
    User findByEmail(@Param("email") String email);
    
    @Query("SELECT p FROM User p WHERE p.name LIKE :searchTerm OR p.email LIKE :searchTerm")
    public List<User> search(@Param("searchTerm") String searchTerm);
    
}