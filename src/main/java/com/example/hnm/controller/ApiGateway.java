package com.example.hnm.controller;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.client.RestTemplate;
import com.amazonaws.AmazonServiceException;

@RestController
public class ApiGateway {

    private RestTemplate restTemplate = new RestTemplate();
    // Lambda Gateway URL을 여기에 넣으세요.
    private String gatewayUrl = "https://7sxigayt9j.execute-api.ap-northeast-2.amazonaws.com/-";

    @PostMapping("/predict")
    public ResponseEntity<?> predict(@RequestParam("image") MultipartFile image) {
        if (image.isEmpty()) {
            return ResponseEntity.badRequest().body("이미지 파일이 없습니다.");
        }
        try {
            String keyName = image.getOriginalFilename();
            // S3에 이미지 업로드는 이미 완료되었다고 가정합니다.
            String imageUrl =  keyName;
            JSONObject json = new JSONObject();
            json.put("photo", imageUrl);

            // HttpHeaders 객체 생성 및 Content-Type을 application/json으로 설정
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // HttpEntity에 JSON 데이터와 HttpHeaders 포함
            HttpEntity<String> request = new HttpEntity<>(json.toString(), headers);

            // RestTemplate을 사용하여 Lambda 함수 호출
            String response = restTemplate.postForObject(gatewayUrl, request, String.class);
            return ResponseEntity.ok(response);
        } catch (AmazonServiceException e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("S3 업로드 실패: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Lambda 호출 실패: " + e.getMessage());
        }
    }
}
