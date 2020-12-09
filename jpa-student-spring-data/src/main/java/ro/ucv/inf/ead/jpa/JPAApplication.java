package ro.ucv.inf.ead.jpa;

import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ro.ucv.inf.ead.jpa.model.Address;
import ro.ucv.inf.ead.jpa.model.Course;
import ro.ucv.inf.ead.jpa.model.Student;
import ro.ucv.inf.ead.jpa.service.CourseService;
import ro.ucv.inf.ead.jpa.service.StudentService;

public class JPAApplication {

   public void process() {
    ApplicationContext appContext = new ClassPathXmlApplicationContext("jpaContext.xml");

    StudentService studentService = appContext.getBean("studentService", StudentService.class);
    CourseService courseService = appContext.getBean("courseService", CourseService.class);

    Student s1 = new Student("Mihai", "Computer Science");
    Student s2 = new Student("Maria", "Mathematics");

    Address address1 = new Address();
    address1.setCity("Craiova");
    address1.setAddress("A.I. Cuza, 13");

    s1.setAddress(address1);

    studentService.addStudent(s1);
    studentService.addStudent(s2);
    System.out.println("List of all students: " + studentService.findAll());

    Course course1 = new Course("Data Mining");
    Course course2 = new Course("Oriented Object Programming");

    courseService.addCourse(course1);
    courseService.addCourse(course2);
    
    
    //Enroll added students to courses.
    courseService.enrollStudent(course1.getId(), s1.getId());
    courseService.enrollStudent(course1.getId(), s2.getId());

    courseService.enrollStudent(course2.getId(), s1.getId());

    // Search a student by name.     
    String studentName = "Maria";
    System.out.println("Search student with name: " + studentName);
    Student student = studentService.findStudentByName(studentName);
    if (student != null) {
      System.out.println("Found student " + student);
      
      //Update the student name and faculty. 
      student.setName("Madalina");
      student.setFaculty("Informatics");
      studentService.updateStudent(student);
    } else {
      System.out.println("Not found student with name: " + studentName);
    }
    
    System.out.println(studentService.findAll());
    
    Long courseId = course1.getId();
    Course c = courseService.findCourse(courseId);
    if (c != null) {
      System.out.println("Found course with " + courseId + ": " + c);  
    } else {
      System.out.println("Not found course with " + courseId);
    }
    Set<Student> students = courseService.findEnrolledStudents(courseId);
    System.out.println("Students enrolled to " + c + ": " + students);
    
    System.out.println("Unenroll student " + s2 + " from course " + c);
    courseService.unenrollStudent(course1.getId(), s2.getId());
    
    System.out.println("Students enrolled to " + c + ": " + courseService.findEnrolledStudents(courseId));

    // studentService.deleteStudent(s1.getId());

  }

  public static void main(String[] args) {
    JPAApplication app = new JPAApplication();
    app.process();
  }

}
