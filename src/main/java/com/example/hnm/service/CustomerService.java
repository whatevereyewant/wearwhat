package com.example.hnm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.hnm.dao.CustomerDao;
import com.example.hnm.entity.CustomerEntity;
import com.example.hnm.dto.*;
import java.util.*;
import com.example.hnm.repository.CustomerRepository;

@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomerDao customerDao;

    public void updateIsLoginByusername(String username, Boolean isLogin){
        CustomerEntity customerEntity = customerRepository.getCustomerDtobyUsername(username);
        customerEntity.setIsLogin(isLogin);
        customerRepository.save(customerEntity);
    }

    public void joinCustomerDto(CustomerEntity customerEntity) {

        // 권한 적용 
        customerEntity.setRole("USER");
        if(customerEntity.getUsername().equals("root")) {
            customerEntity.setRole("ADMIN");
        }else if(customerEntity.getUsername().equals("manger")){
            customerEntity.setRole("MANAGER");
        }

        // 비밀번호 암호화 적용 
        String rawPwd = customerEntity.getPassword();
        String encodedPwd = bCryptPasswordEncoder.encode(rawPwd);
        customerEntity.setPassword(encodedPwd);

        customerEntity.setIsLogin(false);

        customerRepository.save(customerEntity);
    }

    public void updateCustomerInfo(String username, CustomerupDto updateDto) {
        Optional<CustomerEntity> customerOptional = customerRepository.findByUsername(username);
        if (customerOptional.isPresent()) {
            CustomerEntity customerEntity = customerOptional.get();

            // 업데이트할 정보 설정
            customerEntity.setUsernickname(updateDto.getUsernickname());
            customerEntity.setUserheight(updateDto.getUserheight());
            customerEntity.setUserweight(updateDto.getUserweight());
            customerEntity.setUserphonenum(updateDto.getUserphonenum());

            customerRepository.save(customerEntity);
        } else {
            // 사용자를 찾을 수 없는 경우의 처리
            throw new UsernameNotFoundException("User not found");
        }
    }

    public CustomerEntity getCustomerInfo(String username) {
        Optional<CustomerEntity> customerOptional = customerRepository.findByUsername(username);
        if (customerOptional.isPresent()) {
            return customerOptional.get();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
