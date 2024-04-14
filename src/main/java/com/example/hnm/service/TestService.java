package com.example.hnm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.hnm.entity.TestEntity;
import com.example.hnm.repository.TestRepository;

@Service
public class TestService {
    
    @Autowired
    private TestRepository testRepository;

    public List<TestEntity> findAllUsers() {
        return testRepository.findAll();
    }
}
