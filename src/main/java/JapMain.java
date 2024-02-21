import hellojpa.domain.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;

public class JapMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Member member = new Member();
            member.setName("hello");
            em.persist(member);

            em.flush();
            em.close();

            Member findMember = em.find(Member.class, member.getId());
            System.out.println("findMember"+findMember.getId());
            System.out.println("findMember"+findMember.getName());

            //getReference 로 불러온 데이터를 비교할때는 반드시 instance of  사용

            Member reference = em.getReference(Member.class, member.getId());
            System.out.println("reference"+reference.getClass());
            // 답은 class hellojpa.memeber가 나옴
            //-> 왜 프록시가 아니지? 같은 영속성 안에서 같은 결과가 발생
            //반대의 경우도 성립 프록시가 먼저, 그후 memeber여도 프록시가 나옴


            //em.detach();
            em.clear(); //영속성 컨텍스트 종료

            reference.getName(); //오류 영속성 종료후 프록시를 호출시 오류 반환
            //LazyInitializationException


            //프록시 초기화 확인
            System.out.println(emf.getPersistenceUnitUtil().isLoaded(reference));

            //클래스 확인
            System.out.println(reference.getClass());

            //강제로 초기화
            reference.getName();
            //또는
            Hibernate.initialize(reference); //강제 초기화 (JPA 표준은 없음)

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

    }
}
