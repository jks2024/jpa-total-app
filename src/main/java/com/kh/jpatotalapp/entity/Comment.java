package com.kh.jpatotalapp.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne // 다대일 관계 설정 : 한 회원은 여러 댓글을 작성할 수 있다.
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(length = 1000)
    private String content;

    private Date regDate;

    @PrePersist
    public void prePersist() {
        regDate = new Date();
    }
}
