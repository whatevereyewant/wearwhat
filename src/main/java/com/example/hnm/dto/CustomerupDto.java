package com.example.hnm.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class CustomerupDto {
    
    private String usernickname; // 별명
    private Integer userheight; // 키
    private Integer userweight; // 몸무게
    @Pattern(regexp = "^01[0-9]{8,9}$", message = "폰 번호는 01로 시작하는 10자리 또는 11자리의 숫자여야 합니다.")
    private String userphonenum; // 폰번호

    public String getUsernickname() {
        return usernickname;
    }

    public void setUsernickname(String usernickname) {
        this.usernickname = usernickname;
    }

    public Integer getUserheight() {
        return userheight;
    }

    public void setUserheight(Integer userheight) {
        this.userheight = userheight;
    }

    public Integer getUserweight() {
        return userweight;
    }

    public void setUserweight(Integer userweight) {
        this.userweight = userweight;
    }

    public String getUserphonenum() {
        return userphonenum;
    }

    public void setUserphonenum(String userphonenum) {
        this.userphonenum = userphonenum;
    }
}
