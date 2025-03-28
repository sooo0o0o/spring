package kr.co.ch10.security;

import kr.co.ch10.jwt.JwtAuthenticationFilter;
import kr.co.ch10.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    private final JwtProvider jwtProvider;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        //토근 기반 인증 시큐리티 설정
        http.csrf(CsrfConfigurer::disable)                  //CSRF 보안설정 해제
                .httpBasic(HttpBasicConfigurer::disable)    //기본 HTTP 인증 방식 해제
                .formLogin((FormLoginConfigurer::disable))  //폼 로그인 해제
                .sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //세션 해제
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize   //인가 설정
                        .requestMatchers("/")
                        .permitAll()  // -> 로그인 하던 하지 않던 index("/") 에 접근 할 수 있다!
                        .requestMatchers("/admin/**")
                        .hasRole("ADMIN")
                        .requestMatchers("/manager/**")
                        .hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/staff/**")
                        .hasAnyRole("ADMIN","MANAGER", "STAFF")
                        .anyRequest()
                        .permitAll());
        ;



        /*
            인가 설정
                - MyUserDetails 권한 목록 생성하는 메서드(getAuthorities)에서
                접두어로 ROLE_ 입력해야 hasRole, hasAnyRole 권한처리가 가능함
                - Sprint Security 는 기본적으로 존재하지 않는 요청 주소에 대해 login 페이지로 redirec 시킴
        */


        //인가 설정
        http.exceptionHandling(exception -> exception
                .accessDeniedHandler((request, response, AccessDeniedException) -> response
                        .sendRedirect("/")));

        //기타 보안 설정              :: -> 람다식, 메소드 참조 문법
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }




}
