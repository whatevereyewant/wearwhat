package com.example.hnm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CustomerDto {

    // 로그인 유무 //
    private Boolean isLogin;
    private String realname; //찐이름: 신짱구
    private String password; //비번
    private String username; //아이디
    private String role; //권한
    private String usernickname; //별명: 짱구는못말려
    private Integer userheight; //키: 100
    private Integer userweight; //몸무게: 20
    private String userphonenum; //폰번호
}
