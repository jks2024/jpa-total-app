package com.kh.jpatotalapp.entity;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "member")
@Getter @Setter @ToString
@NoArgsConstructor
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;
    @Column(unique = true)
    private String email;
    private String image;
    private Date regDate;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments;

    @OneToMany(mappedBy = "member")
    private List<Board> boards;

    @PrePersist // DB에 INSERT 되기 전에 실행되는 메소드
    public void prePersist() {
        regDate = new Date();
    }
}
