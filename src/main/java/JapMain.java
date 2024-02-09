import hellojpa.domain.Member;
import hellojpa.domain.Order;
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

            Order order = em.find(Order.class, 1L);
            Long memberId = order.getMemberId();

            Member member = em.find(Member.class, memberId);
            //order에서 찾고 또 member 찾는 과정? 객체지향적이지 않음.

            Member findMember = order.getMember(); //order안에 member가 있어야 좀더 객체지향적


            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

    }
}
