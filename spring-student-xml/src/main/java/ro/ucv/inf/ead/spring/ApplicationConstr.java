package ro.ucv.inf.ead.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import ro.ucv.inf.ead.spring.model.Student;
import ro.ucv.inf.ead.spring.service.StudentService;

public class ApplicationConstr {

  public void process() {

    ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext-constr.xml");

    StudentService studentService = appContext.getBean("studentService", StudentService.class);
    studentService.enrollStudent(new Student("Mihai", "Informatica"));
    studentService.enrollStudent(new Student("Maria", "Informatica"));
    System.out.println(studentService.getAllEnrolledStudents());

    appContext.close();
  }

  public static void main(String[] args) {
    ApplicationConstr app = new ApplicationConstr();
    app.process();
  }

}
