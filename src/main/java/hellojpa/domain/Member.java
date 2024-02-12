package hellojpa.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    @Column(name = "USERNAME")
    private String name;

    private String city;
    private String Street;
    private String zipcode;

    //만약 양방향 설정하고싶다면, 추천하지 않음.
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

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

    //연관관계 편의 매소드 매번 하려면 실수 할 일 있으니, 메서드화
/*
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }


    public void setTeam(Team team) {
        this.team = team;
    }
  */

}
