package com.kh.jpatotalapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "member")
@Getter @Setter @ToString
@NoArgsConstructor
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userId;
    private String name;
    private String password;
    @Column(unique = true)
    private String email;
    private Date regDate;
    @Builder
    public Member(String user, String email, String password, String name, Date regDate) {
        this.userId = user;
        this.email = email;
        this.password = password;
        this.name = name;
        this.regDate = regDate;
    }
}
