package com.example.hnm.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.hnm.config.auth.PrincipalDetails;

public class PrincipalDetailProvider implements AuthenticationProvider{
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private PrincipalDetailsService principalDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication)throws AuthenticationException{
        
        String username = authentication.getName(); // authentication 에는 getUsername이 없음
        String password = (String)authentication.getCredentials();

        UserDetails userDetails = (PrincipalDetails)principalDetailsService.loadUserByUsername(username);
        if(userDetails == null){
            throw new UsernameNotFoundException("There is no username" + username);
        }else if(isNotMatches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Your password is incorrect");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
    }
    @Override
    public boolean supports(Class<?> authentication){
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private boolean isNotMatches(String password, String encodePassword){
        return !bCryptPasswordEncoder.matches(password, encodePassword);
    }
}
