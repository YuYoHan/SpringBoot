package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager entityManager = emf.createEntityManager();

        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try {
            Member member =new Member();
            member.setId(100L);
            member.setName("Hello");


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            entityManager.close();
        }

        emf.close();
    }
}
