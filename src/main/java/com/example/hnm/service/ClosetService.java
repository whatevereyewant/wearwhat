package com.example.hnm.service;

import com.example.hnm.entity.*;
import java.util.*;
import com.example.hnm.dto.*;

public interface ClosetService {
    List<ClosetEntity> findByUsername(String username); //리스트로 확인
    List<ClosetEntity> findOutersByUsername(String username);
    List<ClosetEntity> findTopsByUsername(String username);
    List<ClosetEntity> findBottomsByUsername(String username);
    List<ClosetEntity> findOnepieceByUsername(String username);
    ClosetEntity saveCloset(ClosetDto closetDto); //저장
    ClosetEntity updateCloset(Long id, ClosetDto closetDto); //수정
    void deleteCloset(Long id); //삭제
    Optional<ClosetEntity> findById(Long id);
    String getUsername();
}