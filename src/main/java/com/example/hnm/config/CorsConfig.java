package com.example.hnm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 인증 정보를 포함한 요청을 허용하도록 설정
        config.setAllowCredentials(true);
        // 허용할 출처를 명시적으로 설정
        // 예: http://localhost:3000, https://yourdomain.com
        config.addAllowedOriginPattern("http://localhost:8080"); // 특정 출처 패턴 사용
        config.addAllowedOriginPattern("https://7sxigayt9j.execute-api.ap-northeast-2.amazonaws.com/MyClosetModel/");
        // 허용할 HTTP 메소드 설정
        config.addAllowedMethod("*");
        // 허용할 헤더 설정
        config.addAllowedHeader("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
