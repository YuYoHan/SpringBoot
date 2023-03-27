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
//            Member findMember = entityManager.find(Member.class, 1L);

            // JPA입장에서는 코드를 짤때 테이블을 대상으로 짜지않는다.
            // Member라는 객체를 대상으로 짜는 것이다.
            List<Member> result = entityManager.createQuery("select m from Member as m", Member.class)
                    // .setFirstResult(5)
                    // .setMaxResults(8)
                    // ↑ 이것들이 limit 5, 8 SQL문과 같은 의미입니다.
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();

            for (Member member: result
                 ) {
                System.out.println("member.name = " + member.getName());
            }
//            // permit()을 안해도 데이터베이스의 name이 변경되었다.
//            // Hello → HelloJPA
//            findMember.setName("HelloJPA");

            // 삭제
//            entityManager.remove(findMember);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            entityManager.close();
        }

        emf.close();
    }
}
