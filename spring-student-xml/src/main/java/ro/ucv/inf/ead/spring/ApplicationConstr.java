package ro.ucv.inf.ead.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ro.ucv.inf.ead.spring.model.Student;
import ro.ucv.inf.ead.spring.service.StudentService;

public class ApplicationConstr {

  public void process() {

    ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext-constr.xml");

    StudentService studentService = appContext.getBean("studentService", StudentService.class);
    studentService.enrollStudent(new Student("Mihai", "Informatics"));
    studentService.enrollStudent(new Student("Maria", "Informatics"));
    
    System.out.println("All enrolled students: " + studentService.getAllEnrolledStudents());

    appContext.close();
  }

  public static void main(String[] args) {
    ApplicationConstr app = new ApplicationConstr();
    app.process();
  }

}
