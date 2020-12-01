package ro.ucv.inf.ead.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ro.ucv.inf.ead.spring.model.Student;
import ro.ucv.inf.ead.spring.service.StudentService;


public class Application {

  public void process(){

    ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");      
    StudentService studentService = appContext.getBean("studentService", StudentService.class);
    studentService.enrollStudent(new Student("Mihai", "Informatics"));
    studentService.enrollStudent(new Student("Maria", "Informatics"));
    
    System.out.println("All enrolled students: " + studentService.getAllEnrolledStudents());
    
 }
 
  public static void main(String[] args) {
    Application app = new Application();
    app.process();  
  }

}
