package com.kh.jpatotalapp.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class CommentDto {
    private Long commentId;
    private Long boardId;
    private String email;
    private String content;
    private Date regDate;
}
