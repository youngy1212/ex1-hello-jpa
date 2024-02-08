package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        //code

        //트랜잭션 안에서 실행되어야함.
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            //비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("hello");

            //영속
            em.persist(member);

            Member findmember = em.find(Member.class, 101L);
            //DB select 안나옴 1차 캐시 조회
            Member findmember2 = em.find(Member.class, 101L);
            //똑같은걸 두 번 조회할때는 캐시 조회하여 동일성 보장
            //DB조회 , 캐시조회

            //지연 commit 전까지는 DB에서 전송하지 않음.
            em.persist(findmember);
            em.persist(findmember2);
            

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); //사용후 닫기
        }

        emf.close();
    }
}
