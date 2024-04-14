package com.example.hnm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.hnm.dto.ClosetDto;
import com.example.hnm.entity.ClosetEntity;
import com.example.hnm.repository.ClosetRepository;
import jakarta.persistence.EntityNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;

@Service
public class ClosetServiceImpl implements ClosetService{
    
    private final ClosetRepository closetRepository;

    @Autowired
    public ClosetServiceImpl(ClosetRepository closetRepository) {
        this.closetRepository = closetRepository;
    }

    @Override
    public List<ClosetEntity> findByUsername(String username) {
        return closetRepository.findByUsername(username);
    }

    @Override
    public ClosetEntity saveCloset(ClosetDto closetDto) {
    ClosetEntity closet = new ClosetEntity();
    // DTO에서 Entity로 필드 값 복사
    closet.setUsername(closetDto.getUsername());
    try {
        closet.setUserpic(closetDto.getUserpic().getBytes()); // 파일 처리
    } catch (IOException e) {
        // 예외 처리
    }
    closet.setPiccategory(closetDto.getPiccategory());
    closet.setPicstyle(closetDto.getPicstyle());
    closet.setPicseason(closetDto.getPicseason());
    closet.setPiccomment(closetDto.getPiccomment());
    closet.setPicdate(LocalDate.now()); // 혹은 DTO에서 받아온 값을 사용

    // 변환된 Entity를 저장하고 반환
    return closetRepository.save(closet);
    }

    @Override
    public ClosetEntity updateCloset(Long id, ClosetDto closetDto) {
        // ID로 옷장 아이템을 찾음
        ClosetEntity closet = closetRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Closet not found with id " + id));

        // DTO에서 받은 정보로 엔티티 업데이트
        closet.setUsername(closetDto.getUsername());
        if (closetDto.getUserpic() != null && !closetDto.getUserpic().isEmpty()) {
            try {
                closet.setUserpic(closetDto.getUserpic().getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Error processing upload", e);
            }
        }
        closet.setPiccategory(closetDto.getPiccategory());
        closet.setPicstyle(closetDto.getPicstyle());
        closet.setPicseason(closetDto.getPicseason());
        closet.setPiccomment(closetDto.getPiccomment());
        // 여기서 이미지 파일 처리가 필요하다면 추가 작업을 해야 할 수도 있어

        // 업데이트된 옷장 아이템 저장
        return closetRepository.save(closet);
    }

    @Override
    public void deleteCloset(Long id) {
        closetRepository.deleteById(id);
    }

    @Override
    public Optional<ClosetEntity> findById(Long id) {
    return closetRepository.findById(id);
    }

    @Override
    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null; // 혹은 적절한 예외 처리
    }

    @Override
    public List<ClosetEntity> findOutersByUsername(String username) {
        List<String> outerCategories = Arrays.asList("blazer", "coat", "winterOuter", "cardigan", "jacket","parka");
        return findByUsername(username).stream()
                .filter(closet -> outerCategories.contains(closet.getPiccategory()))
                .collect(Collectors.toList());
}
    @Override
    public List<ClosetEntity> findTopsByUsername(String username){
        List<String> outerCategories = Arrays.asList("fourSeasonsBlouseShirt", "hoodie", "longSleeve", "midSeasonsBlouseShirt", "sweater", "shortSleeve", "trainingTop", "vest");
        return findByUsername(username).stream()
                .filter(closet -> outerCategories.contains(closet.getPiccategory()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClosetEntity> findBottomsByUsername(String username){
        List<String> outerCategories = Arrays.asList("jeans", "longPants", "longSkirt", "shortPants", "shortSkirt", "trainingBottom");
        return findByUsername(username).stream()
                .filter(closet -> outerCategories.contains(closet.getPiccategory()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClosetEntity> findOnepieceByUsername(String username){
        List<String> outerCategories = Arrays.asList("onePieceSet");
        return findByUsername(username).stream()
                .filter(closet -> outerCategories.contains(closet.getPiccategory()))
                .collect(Collectors.toList());
    }
}
