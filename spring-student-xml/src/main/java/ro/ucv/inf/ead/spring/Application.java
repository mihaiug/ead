package ro.ucv.inf.ead.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ro.ucv.inf.ead.spring.model.Student;
import ro.ucv.inf.ead.spring.service.StudentService;

public class Application {

  public void process() {
    String beansConfigFile = "applicationContext.xml";
    // String beansConfigFile = "applicationContext-mysql.xml";

    try (ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext(beansConfigFile)) {

      StudentService studentService = appContext.getBean("studentService", StudentService.class);
      studentService.enrollStudent(new Student("Mihai", "Informatics"));
      studentService.enrollStudent(new Student("Maria", "Mathematics"));

      System.out.println("All enrolled students: " + studentService.getAllEnrolledStudents());
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  public static void main(String[] args) {
    Application app = new Application();
    app.process();
  }

}
