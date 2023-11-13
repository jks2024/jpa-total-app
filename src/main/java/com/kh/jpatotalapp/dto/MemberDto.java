package com.kh.jpatotalapp.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MemberDto {
    private String userId;
    private String pwd;
    private String name;
    private String email;
    private Date regDate;
}
