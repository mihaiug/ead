package ro.ucv.inf.ead.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ro.ucv.inf.ead.jpa.model.Address;
import ro.ucv.inf.ead.jpa.model.Course;
import ro.ucv.inf.ead.jpa.model.Phone;
import ro.ucv.inf.ead.jpa.model.Student;

public class JPAApplicationAdd {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("student-punit");
    EntityManager em = emf.createEntityManager();

    try {
      System.out.println("Adding students - Begin Transaction");
      em.getTransaction().begin();
      Student student = new Student("Mihai", "Computer Science");

      Address address = new Address();
      address.setCity("Craiova");
      address.setAddress("A.I. Cuza, 13");

      em.persist(address);
      student.setAddress(address);

      // Add few courses.
      Course course1 = new Course("Data Mining");
      em.persist(course1);

      Course course2 = new Course("Oriented Object Programming");
      em.persist(course2);

      student.getCourses().add(course1);
      student.getCourses().add(course2);

      
      Phone phone1 = new Phone();
      phone1.setNumber("2222221");
      phone1.setType("Vodafone");     
      phone1.setStudent(student);
     // em.persist(phone1);
      student.getPhones().add(phone1);
      
      em.persist(student);
      em.getTransaction().commit();
      System.out.println("Adding students - End Transaction");

    } catch (Exception e) {
      System.err.println("Adding students - Rollback. Error: " + e.getMessage());
      // e.printStackTrace();
      em.getTransaction().rollback();
    } finally {
      em.close();
      emf.close();
    }

  }

}
