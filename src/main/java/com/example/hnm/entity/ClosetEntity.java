package com.example.hnm.entity;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.*;


@Entity(name = "ClosetEntity")
@Table(name = "ClosetInfo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClosetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 고유 식별자

    @Column(name = "username")
    private String username; // 사용자 아이디

    @Lob
    @Column(name = "userpic")
    private byte[] userpic; // 사진 URL or 파일 참조

    @NotBlank
    @Column(name = "piccategory")
    private String piccategory; // 카테고리 예: 상의, 하의

    @NotBlank
    @Column(name = "picstyle")
    private String picstyle; // 스타일 예: 스트릿, 클래식

    @NotBlank
    @Column(name = "picseason")
    private String picseason; // 계절

    @Size(max = 100)
    @Column(name = "piccomment")
    private String piccomment; // 사용자 코멘트

    @Column(name = "picdate", columnDefinition = "DATE")
    private LocalDate picdate; // 업로드 날짜
}