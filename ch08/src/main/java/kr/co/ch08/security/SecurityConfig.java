package kr.co.ch08.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        //로그인 설정
        http.formLogin(login -> login
                .loginPage("/user/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?code=100")
                .usernameParameter("uid")       //login.html 에 입력데는 id 데이터 이름
                .passwordParameter("pass"));    //login.html 에 입력데는 password 데이터 이름


        //로그아웃 설정
        http.logout(logout -> logout
                .logoutUrl("/user/logout")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/user/login?code=101"));


        //인가 설정
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/")
                .permitAll()  // -> 로그인 하던 하지 않던 index("/") 에 접근 할 수 있다!
                .anyRequest()
                .permitAll());



        //기타 보안 설정              :: -> 람다식, 메소드 참조 문법
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
