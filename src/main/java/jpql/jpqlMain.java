package jpql;

import jakarta.persistence.*;

import java.util.List;


public class jpqlMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("user1");
            member.setAge(20);
            member.setTeam(team);

            em.persist(member);

            em.flush();
            em.clear();


            //페이징 쿼리
            String query = "select m from Member m inner join m.team t;";
            List<Member> result = em.createQuery(query, Member.class)
                    .setFirstResult(10)
                    .setMaxResults(20)
                    .getResultList();


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

    }
}
