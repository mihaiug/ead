package ro.ucv.inf.ead.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ro.ucv.inf.ead.spring.model.Student;
import ro.ucv.inf.ead.spring.service.StudentService;

public class Application {

  public void process() {

    ApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

    StudentService studentService = appContext.getBean("studentService", StudentService.class);
    studentService.enrollStudent(new Student("Mihai", "Informatica"));
    studentService.enrollStudent(new Student("Maria", "Informatica"));
    System.out.println(studentService.getAllEnrolledStudents());

  }

  public static void main(String[] args) {
    Application app = new Application();
    app.process();
  }

}
