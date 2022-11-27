package ro.ucv.inf.ead.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import ro.ucv.inf.ead.spring.repository.StudentRepository;
import ro.ucv.inf.ead.spring.repository.StudentRepositoryImpl;
import ro.ucv.inf.ead.spring.service.StudentService;
import ro.ucv.inf.ead.spring.service.StudentServiceImpl;

public class ApplicationConfig {

  @Bean(name="studentRepository", initMethod = "init", destroyMethod = "shutdown" )
  @Scope("singleton")
  public StudentRepository createStudentRepository() {
    System.out.println("Create studentRepository bean from " + this.getClass().getCanonicalName());
    return new StudentRepositoryImpl();
  }
  
  @Bean("studentService")
  public StudentService createStudentService() {
    return new StudentServiceImpl(createStudentRepository());
  }
}
