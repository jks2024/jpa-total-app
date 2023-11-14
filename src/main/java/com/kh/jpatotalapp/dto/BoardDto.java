package com.kh.jpatotalapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
    private Long boardId;
    private String email;
    private String title;
    private String content;
    private String img;
    private String regDate;
}
