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

            Member member = new Member();
            member.setUsername("user1");
            member.setAge(10);
            em.persist(member);

            //두번쨰에 타입정보를 줄 수있음 Member.class (기본적으로 엔티티)
            TypedQuery<Member> query = em.createQuery("select m From Member m where m.username = :username", Member.class);
            TypedQuery<String> query2 = em.createQuery("select m.username From Member m", String.class);

            //age와 name.. 뭘로 받아야할지 모를땐 Query로 받아주면 됨(타입정보가 명확하지 않을때)
            Query query3 = em.createQuery("select m.username m.age From Member m");

            //바인딩
            query.setParameter("username","user1");

            //결과 조회 (여러건) : 결과가 없다면 빈 리스트 반환
            List<Member> resultList = query.getResultList();

            //단건 : 결과가 없고, 둘 이상일 경우 Exception 터짐 (결과가 무조건 하나여야함)
            Member singleResult = query.getSingleResult();
            //Spring Data Jpa 사용시 없으면 null 반환 -> 스프링이 트라이 캐치 해준거라

            //보통은 메소드 체인으로 사용 (추천)
            Member result = em.createQuery("select m From Member m where m.username = :username", Member.class)
                    .setParameter("username","user1")
                    .getSingleResult();


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

    }
}
