package com.example.hnm.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.hnm.entity.CustomerEntity;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PrincipalDetails implements UserDetails {
    
    private CustomerEntity customerEntity;

    // 사용자의 권한 리스트를 주입.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            
            @Override
            public String getAuthority() {
                // userDto의 권한이 추가됨.
                return customerEntity.getRole();
            }
        });

        return collection;
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return customerEntity.getPassword();
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return customerEntity.getUsername();
    }


    public String getRealname(){
        return customerEntity.getRealname();
    }

    public Integer getHeight(){
        return customerEntity.getUserheight();
    }

    public Integer getWeight(){
        return customerEntity.getUserweight();
    }

    public String getNickname(){
        return customerEntity.getUsernickname();
    }

    public String getPhonenum(){
        return customerEntity.getUserphonenum();
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        // 계정 만료 유무 확인 
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        // 계정 잠긴 유무 확인
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        // 계정 비번 오래 사용했는지 유무 확인 
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        // 활성화된 계정인지 유무 확인  
        return true;
    }
    
}
