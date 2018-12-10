package ro.ucv.inf.ead.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAApplicationEM {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("student-punit");
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    // Perform finds, execute queries

    // Update entities
    em.getTransaction().commit();

    em.close();
    emf.close();
  }

}
