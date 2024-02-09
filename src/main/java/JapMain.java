import hellojpa.domain.Member;
import hellojpa.domain.Order;
import hellojpa.domain.Team;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

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
            member.setTeam(team); //단방향 연관관계 설정, 참조 저장
            //member.setTeamId(team.getId());//외래키 식별자를 직접다름
            em.persist(member);

            //조회도 두번할 필요없음
            Member findMember = em.find(Member.class, member.getId());
            //참조를 사용해서 연관관계 조회
            Team findTeam = findMember.getTeam();

            // 새로운 팀B
            Team newTeam = em.find(Team.class, 100L);
            // 회원1에 새로운 팀B 설정
            member.setTeam(newTeam);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

    }
}
