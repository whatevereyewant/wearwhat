package com.example.hnm.dao;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.hnm.entity.CustomerEntity;
import com.example.hnm.repository.CustomerRepository;
import java.util.List;

@Service
public class CustomerDao {
    
    @Autowired
    private CustomerRepository customerRepository;

    public List<CustomerEntity> selectCustomerAll(){
        List<CustomerEntity> custlist = customerRepository.findAll();
        return custlist;
    }
}