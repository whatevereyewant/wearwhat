package com.example.hnm.repository;

import com.example.hnm.entity.ClosetEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClosetRepository extends JpaRepository<ClosetEntity, Long> {
    List<ClosetEntity> findByUsername(String username);
}