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

            for(int i = 0; i<100; i++){
                Member member = new Member();
                member.setUsername("user"+i);
                member.setAge(i);
                em.persist(member);

            }

            em.flush();
            em.clear();


            //영속성 컨테스트가 작동할까?
            List<Member> result = em.createQuery("select m From Member m", Member.class)
                    .getResultList();

            Member findMember = result.get(0);
            findMember.setAge(20);//바뀜!(영속성 컨텍스트에 의해 관리되는중)
            //엔티티 프로젝션


            List<MemberDTO> resultList = em.createQuery("select new jqpl.MemberDTO(m.username, m.age) From Member m", MemberDTO.class)
                    .getResultList();
            MemberDTO memberDTO = resultList.get(0); //첫번쨰 값 가져오기


            //페이징 쿼리
            List<Member> result2 = em.createQuery("select m from Member m order by m.name desc", Member.class)
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
