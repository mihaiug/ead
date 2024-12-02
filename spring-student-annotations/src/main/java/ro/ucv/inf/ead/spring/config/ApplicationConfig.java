package ro.ucv.inf.ead.spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import ro.ucv.inf.ead.spring.repository.StudentRepository;
import ro.ucv.inf.ead.spring.repository.StudentRepositoryImpl;
import ro.ucv.inf.ead.spring.service.StudentService;
import ro.ucv.inf.ead.spring.service.StudentServiceImpl;

@Configuration
@ComponentScan("ro.ucv.inf.ead.spring")
public class ApplicationConfig {

  @Bean
  public StudentRepository studentRepository() {
    System.out.println("Create studentRepository bean from " + this.getClass().getCanonicalName());
    return new StudentRepositoryImpl();
  }
  
  @Bean
  public StudentService studentService() {
    System.out.println("Create studentService bean from " + this.getClass().getCanonicalName());
    return new StudentServiceImpl();
  }
}
