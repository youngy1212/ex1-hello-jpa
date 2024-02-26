package hellojpa.domain;


import jakarta.persistence.*;

@Entity
public class Delivery {

    @Id @GeneratedValue
    private Long id;

    @Embedded
    private Address address;
    private DeliveryStatus status;

    //멤버와 양방향으로 하고싶어
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;


}
