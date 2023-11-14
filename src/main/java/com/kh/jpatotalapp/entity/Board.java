package com.kh.jpatotalapp.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.Date;  // JPA에서는 java.util.Date를 사용한다.

@Entity
@Table(name = "board")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardId;
    private String title;
    private String content;
    private String imgPath;
    private Date regDate;

    @ManyToOne
    @JoinColumn(name = "member_id") // 외래키
    private Member member; // 작성자

    @PrePersist
    public void prePersist() {
        regDate = new Date();
    }
}
