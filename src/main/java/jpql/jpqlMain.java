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
            member.setType(MemberType.ADMIN);

            em.persist(member);

            em.flush();
            em.clear();


            //enum의 경우 패키지명까지 해줘야함!!
            String query = "select m.username,'HELLO',TRUE from Member m"+
                    "where m.type = :userType";
            List<Member> result = em.createQuery(query, Member.class)
                    .setParameter("userType",MemberType.ADMIN)
                    .getResultList();

            //"where m.type = jpql.MemberTyep.ADMIN 으로 작성
            // 파라미터 매칭해주는 경우는 MemberType.ADMIN.";

            //조건식
            String query1 =
                    "select"+
                        "case when m.age <= 10 then '학생요금'"+
                        "case when m.age >= 60 then '경로요금'"+
                        "eles '일반요금'"+
                        "end"+
                    "from Member m";
            em.createQuery(query1,String.class).getResultList();


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

    }
}
