package hellojpa.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String name;

    //period 기간
    @Embedded
    private Period workPeriod;
    //주소
    @Embedded
    private Address homeAddress;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="city",
                    column=@Column(name="work_city")),
            @AttributeOverride(name="street",
                    column=@Column(name="work_street")),
            @AttributeOverride(name="zipcode",
                    column=@Column(name="work_zipcode"))
            //컬럼 이름은 다르게 매핑
    })
    private Address workAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Period getWorkPeriod() {
        return workPeriod;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }
}
