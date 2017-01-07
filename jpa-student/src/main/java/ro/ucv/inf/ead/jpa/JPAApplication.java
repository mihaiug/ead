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
        Student student1 = new Student("Mihai", "Computer Science");

        Address address1 = new Address();
        address1.setCity("Craiova");
        address1.setAddress("A.I. Cuza, 13");

        em.persist(address1);
        student1.setAddress(address1);

        Course course1 = new Course("Data Mining");
        em.persist(course1);

        Course course2 = new Course("Oriented Object Programming");
        em.persist(course2);

        student1.getCourses().add(course1);
        student1.getCourses().add(course2);

        em.persist(student1);

        Student student2 = new Student("Maria", "Informatics");
        Course c = new Course();
        c.setId(course1.getId());
        student2.getCourses().add(c);

        Phone phone = new Phone();
        phone.setNumber("222222");
        phone.setType("Home");
        phone.setStudent(student2);

        em.persist(phone);
        // student2.getPhones().add(phone);
        em.persist(student2);

        em.getTransaction().commit();
        System.out.println("Adding students - End");

      } catch (Exception e) {
        System.err.println("Adding students - Error: " + e.getMessage());
        e.printStackTrace();
        em.getTransaction().rollback();
      }

      Query query = em.createQuery("SELECT s FROM Student s");

      List<Student> students = (List<Student>) query.getResultList();

      System.out.println(students);

      Course c = em.find(Course.class, new Long(1));
      System.out.println("students who follow the course " + c +":");
      System.out.println("\t" + c.getStudents());

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
