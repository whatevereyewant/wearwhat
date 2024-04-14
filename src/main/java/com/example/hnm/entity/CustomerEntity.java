package com.example.hnm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
@Entity(name = "CustomerEntity")
@Table(name = "UserInfo")
public class CustomerEntity {
    // 로그인 유무 //
    @Column(columnDefinition = "tinyint(1) default 0")
    private Boolean isLogin;
    @NotBlank
    @Size(min = 2, max = 8)
    @Column(name = "realname")
    private String realname; //찐이름: 신짱구
    @NotBlank
    @Column(name = "password")
    private String password; //비번
    @Id
    @NotBlank(message = "아이디는 비워둘 수 없습니다.")
    @Column(name = "username", unique = true)
    private String username; // 아이디
    @Column(name = "role")
    private String role; //권한
    @NotBlank
    @Column(name = "usernickname", unique = true)
    private String usernickname; //별명: 짱구는못말려
    @Max(value = 300)
    @Min(value = 30)
    @Column(name = "userheight")
    private Integer userheight; //키: 100
    @Column(name = "userweight")
    private Integer userweight; //몸무게: 20
    @NotBlank
    @Column(name = "userphonenum", unique = true)
    private String userphonenum; //폰번호
}
