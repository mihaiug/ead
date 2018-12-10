package ro.ucv.inf.ead.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ro.ucv.inf.ead.jpa.model.Course;
import ro.ucv.inf.ead.jpa.model.Student;

public class JPAApplicationRead {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("student-punit");
    EntityManager em = emf.createEntityManager();
    try {
      Student student = em.find(Student.class, 1L);
      if (student != null) {
        System.out.println("Found: " + student);
        System.out.println("\tPhones: " + student.getPhones());
        System.out.println("\tCourses: " + student.getCourses());
      }

      Query query = em.createQuery("SELECT s FROM Student s");

      List<Student> students = (List<Student>) query.getResultList();

      System.out.println("List of all students: " + students);

      // Find course with id 1.
      Course c = em.find(Course.class, 1L);
      if (c != null) {
        em.refresh(c); // Required to force initialize lazy associations.
        System.out.println("Students who follow the course " + c + ": ");
        System.out.println("\t" + c.getStudents());
      }
      query = em.createQuery("SELECT s FROM Student s LEFT OUTER JOIN s.address a WHERE a.city=:city");
      query.setParameter("city", "Craiova");
      students = (List<Student>) query.getResultList();

      System.out.println("Students from Craiova:\n" + students);

    } finally {
      em.close();
      emf.close();
    }
    System.out.println("End application");
  }
}
