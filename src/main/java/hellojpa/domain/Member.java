package hellojpa.domain;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;
    @Column(name = "USERNAME")
    private String name;

  /*  @Column(name = "TEAM_ID")
    private Long teamId;*/
    //DB에 맞춰서 모델링

    @ManyToOne //member many Team one
    @JoinColumn(name = "TEAM_ID")
    private Team team;




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

    public Team getTeam() {
        return team;
    }

    //연관관계 편의 매소드 매번 하려면 실수 할 일 있으니, 메서드화
/*
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
*/

    public void setTeam(Team team) {
        this.team = team;
    }
}
