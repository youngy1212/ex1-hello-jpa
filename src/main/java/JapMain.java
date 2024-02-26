import hellojpa.domain.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.List;

public class JapMain {

    private static Address address;

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Address address = new Address("city", "street", "10000");

            Member member = new Member();
            member.setName("member1");
            member.setHomeAddress(address);
            em.persist(member);

            //그럼 내가 member1의 정보를 바꾸고싶을떈?? 새로만들어라
            Address newAddress = new Address("newCity", address.getStreet(), address.getZipcode());

            Member member2 = new Member();
            member2.setName("member2");
            member2.setHomeAddress(newAddress);
            em.persist(member2);

            //member.getHomeAddress().setCity("new City"); -> set를 지워서 사이드 이펙트 방지


            //값 타입 컬렉션
            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("피자");
            member.getFavoriteFoods().add("족발");

            member.getAddressHistory().add(new AddressEntity("old1", "enwCC", "100002"));
            member.getAddressHistory().add(new AddressEntity("old2", "enwCC", "100002"));
            // 해당 내역은 persist 하지 않았는데도, 자동으로 insert됨 -> member의 라이프사이클을 따라가기 때문

            em.flush();
            em.clear();
            Member findMemeber = em.find(Member.class,member.getId());
            //select문에 컬렉션은 조회되지 않음 -> 자동 지연로딩
//            List<Address> addressHistory = findMemeber.getAddressHistory();
//            for(Address address1 : addressHistory){
//                System.out.println("address1"+address1.getCity()); //이떄 진짜 값이 조회
//            }

            //값 타입 수정 newCity  ->  homeCity로 이사갔음
            findMemeber.setHomeAddress(new Address("homeCity","home","20000"));

            //컬렉션 값을 수정 -> 치킨 -> 한식
            //변경이 안되고, 삭제후 새로 넣어야함
            findMemeber.getFavoriteFoods().remove("치킨");
            findMemeber.getFavoriteFoods().add("한식");
            //위에서 업데이트가 아닌 new Address로 만든것처럼, 결국 값타입은 통쨰로 갈아 껴야됌!!

//            //마찬가지고, 찾아서 삭제해야함.
//            findMemeber.getAddressHistory().remove(new Address("old1", "enwCC", "100002"));
//            //but 해당 기능은 Equals가 제대로 들어가있어야함!(주의)
//            findMemeber.getAddressHistory().add(new Address("newCtiy1", "enwCC", "100002"));

            //-> 다지우고 다시 한다? 쓰면안된다!!

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }

    }
}
