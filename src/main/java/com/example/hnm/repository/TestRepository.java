package com.example.hnm.repository;

import org.springframework.stereotype.Repository;

import com.example.hnm.entity.TestEntity;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface TestRepository extends JpaRepository<TestEntity, String>{
    
}

