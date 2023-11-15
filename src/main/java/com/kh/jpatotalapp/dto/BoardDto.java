package com.kh.jpatotalapp.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class BoardDto {
    private Long boardId;
    private String email;
    private Long categoryId;
    private String title;
    private String content;
    private String img;
    private Date regDate;
}
