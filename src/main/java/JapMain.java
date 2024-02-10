import hellojpa.domain.Member;
import hellojpa.domain.Order;
import hellojpa.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JapMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            //팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            //회원 저장
            Member member = new Member();
            member.setName("member1");
            //member.changeTeam(team); //단방향 연관관계 설정, 참조 저장
            //member.setTeamId(team.getId());//외래키 식별자를 직접다름
            em.persist(member);

            //team.addMember(memeber); //이런식으로도 짜기도 함

            /*team.getMembers().add(member); 편의 메서드로 처리 추천*/
            //물론 없어도 JAP가 데이터를 가져와서 세팅해주긴 함.
            //만약 안넣어준다면, 1차 캐시 안에만 있기 때문에 값이 없음.
            //객체지향적으로 생각했을때, 양쪽으로 넣어주는 것이 좋음.

            //조회도 두번할 필요없음
            Member findMember = em.find(Member.class, member.getId());
            //참조를 사용해서 연관관계 조회

            List<Member> members = findMember.getTeam().getMembers();
            //양방향으로 서로 탐색 가능

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

    }
}
