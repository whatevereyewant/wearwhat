package com.example.hnm.dto;

import java.time.LocalDate;
import org.springframework.web.multipart.MultipartFile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClosetDto {

    private Long id; // 수정 및 삭제를 위해 id도 포함시킴
    private String username; // 사용자 아이디
    private MultipartFile userpic; // 클라이언트로부터 받은 이미지 파일
    private String piccategory; // 카테고리 예: 상의, 하의
    private String picstyle; // 스타일 예: 스트릿, 클래식
    private String picseason; // 계절
    private String piccomment; // 사용자 코멘트
    private LocalDate picdate; // 업로드 날짜, 클라이언트가 제공하지 않으면 서버 측에서 설정할 수 있음

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MultipartFile getUserpic() {
        return userpic;
    }

    public void setUserpic(MultipartFile userpic) {
        this.userpic = userpic;
    }
}
