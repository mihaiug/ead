package ro.ucv.inf.ead.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ro.ucv.inf.ead.jpa.model.Student;

public class JPAApplicationDelete {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("student-punit");
    EntityManager em = emf.createEntityManager();
    try {
      long id = 1;
      Student student = em.find(Student.class, id);
      if (student != null) {
        System.out.println("Found: " + student);
        System.out.println("\tPhones: " + student.getPhones());
        System.out.println("\tCourses: " + student.getCourses());

        try {
          System.out.println("Delete student - Begin");
          em.getTransaction().begin();
          em.remove(student);
          em.getTransaction().commit();
          System.out.println("Delete student - End");

        } catch (Exception e) {
          System.err.println("Delete student - Error: " + e.getMessage());
          em.getTransaction().rollback();
        }
      } else {
        System.out.printf("Student with id %d not found\n", id);
      }
    } finally {
      em.close();
      emf.close();
    }
  }
}
