package hellojpa.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    //양방향 매핑
    @OneToMany(mappedBy = "team")
    List<Member> members = new ArrayList<Member>();


    //연관관계 편의 메소드 2 , 양졲 보단 한쪽에만 있는게 좋음
    public void addMember(Member member) {
        member.setTeam(this);
        members.add(member);
    }


    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

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
}
