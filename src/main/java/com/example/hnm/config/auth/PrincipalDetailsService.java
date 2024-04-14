package com.example.hnm.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.hnm.entity.CustomerEntity;
import com.example.hnm.repository.CustomerRepository;

@Service
public class PrincipalDetailsService implements UserDetailsService{
    
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub

        CustomerEntity customerEntity = customerRepository.getCustomerDtobyUsername(username);
        if(customerEntity != null) {
            // 이미 가입이 된 사용자!!!
            return new PrincipalDetails(customerEntity);
        }

        // 가입이 되지 않은 사용자!!
        return null;
    }
}


