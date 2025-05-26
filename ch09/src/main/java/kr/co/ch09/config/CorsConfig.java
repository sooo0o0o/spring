package kr.co.ch09.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        /*
            CORS 설정
                - Cross-Origin-Resource-Sharing 
                - 웹 애플리케이션에서 다른 도메인의 리소스에 접근하는 HTTP 정책
                - HTTP 통신은 기본적으로 보안상의 이유로 다른 도메인의 리소스 접근을 차단
                - API로 서비스하기위해 CORS 정책을 허용 
        */

        registry.addMapping("/**")
                .allowedOriginPatterns("http://127.0.0.1:5500/", "http://127.0.0.1:5173/", "http://localhost:5173/") //허용할 외부 도메인
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);

    }
}
