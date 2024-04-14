package com.example.hnm.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.hnm.entity.TestEntity;
import com.example.hnm.service.TestService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/users")
public class TestController {
    
    @Autowired
    private TestService testService;

    @GetMapping
    public ResponseEntity<List<TestEntity>> getAllUsers(){
        List<TestEntity> users = testService.findAllUsers();
        return ResponseEntity.ok().body(users);
    }
}
