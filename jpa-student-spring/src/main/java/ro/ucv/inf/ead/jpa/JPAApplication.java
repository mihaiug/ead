package ro.ucv.inf.ead.jpa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ro.ucv.inf.ead.jpa.model.Address;
import ro.ucv.inf.ead.jpa.model.Course;
import ro.ucv.inf.ead.jpa.model.Student;
import ro.ucv.inf.ead.jpa.service.StudentService;

public class JPAApplication {

  public void process() {
    ApplicationContext appContext = new ClassPathXmlApplicationContext("jpaContext.xml");
    StudentService studentService = appContext.getBean("studentService", StudentService.class);
    Student s1 = new Student("Mihai", "Computer Science");
    Student s2 = new Student("Maria", "Mathematics");
    
    studentService.addStudent(s1);
    studentService.addStudent(s2);
    System.out.println("List of all students: " + studentService.findAll());
    
    studentService.deleteStudent(s1.getId());
    
    String studentName = "Maria";
    Student s3 = studentService.findStudentByName(studentName);    
    if(s3 != null){ 
      System.out.println("Found student " + s3);
      s3.setName("Madalina");
      s3.setFaculty("Law");
      
      Address address = new Address();
      address.setCity("Craiova");
      address.setAddress("A.I. Cuza, 13");
      
      s3.setAddress(address);
      studentService.updateStudent(s3);
    } else {
      System.out.println("Not found student with name: " + studentName);
    }
    System.out.println(studentService.findAll());
  }

  public static void main(String[] args) {
    JPAApplication app = new JPAApplication();
    app.process();
  }

}
