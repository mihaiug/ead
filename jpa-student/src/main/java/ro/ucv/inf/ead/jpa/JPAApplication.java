package ro.ucv.inf.ead.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ro.ucv.inf.ead.jpa.model.Address;
import ro.ucv.inf.ead.jpa.model.Course;
import ro.ucv.inf.ead.jpa.model.Phone;
import ro.ucv.inf.ead.jpa.model.Student;

public class JPAApplication {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("student-punit");
    EntityManager em = emf.createEntityManager();

    try {
      try {
        System.out.println("Adding students - Begin");
        em.getTransaction().begin();

        // Add few courses.
        Course course1 = new Course("Data Mining");
        em.persist(course1);

        Course course2 = new Course("Oriented Object Programming");
        em.persist(course2);

        Address address1 = new Address();
        address1.setCity("Craiova");
        address1.setAddress("A.I. Cuza, 13");
        em.persist(address1);

        Student student1 = new Student("Mihai", "Computer Science");
        student1.setAddress(address1);

        student1.getCourses().add(course1);
        student1.getCourses().add(course2);

        em.persist(student1);

        Student student2 = new Student("Maria", "Informatics");

        Phone phone = new Phone();
        phone.setNumber("222222");
        phone.setType("Home");
        phone.setStudent(student2);

        student2.getPhones().add(phone);
        em.persist(student2);

        em.flush();

        em.getTransaction().commit();
        // em.clear();
        System.out.println("Adding students - End");

      } catch (Exception e) {
        System.err.println("Adding students - Error: " + e.getMessage());
        e.printStackTrace();
        em.getTransaction().rollback();
        System.exit(1);
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

      System.out.println("Students from Craiova:" + students);

    } finally {
      em.close();
      emf.close();
    }
    System.out.println("End application");

  }

}
