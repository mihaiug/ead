package ro.ucv.inf.ead.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import ro.ucv.inf.ead.spring.config.ApplicationConfig;
import ro.ucv.inf.ead.spring.model.Student;
import ro.ucv.inf.ead.spring.service.StudentService;


public class Application {

  public void process(){

    AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);
    
    StudentService studentService = appContext.getBean("studentService", StudentService.class);
    studentService.enrollStudent(new Student("Mihai", "Informatics"));
    studentService.enrollStudent(new Student("Maria", "Informatics"));
    
    System.out.println("All enrolled students: " + studentService.getAllEnrolledStudents());
    appContext.close();
    
 }
 
  public static void main(String[] args) {
    Application app = new Application();
    app.process();  
  }

}
