package hellojpa;

import jakarta.persistence.*;

import java.util.Date;


@Entity
@SequenceGenerator(name = "MEMBER_SEQ_GENERATOR",
sequenceName = "MEMBER_SEQ", initialValue = 1, allocationSize = 50)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "MEMBER_SEQ_GENERATOR")
    private Long id;
    @Column(name = "name", nullable = false, columnDefinition = "varchar(100) default ‘EMPTY'")
    private String username;
    private Integer age;
    @Enumerated(EnumType.STRING) //Enum을 받아온다.
    private RoleType roleType;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate; //자바 데이터 타입과 DB 데이터 타입이 다르기 떄문
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Lob
    private String description;

    public Member() {
    }


}
