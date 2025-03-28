package kr.co.ch10.controller;

import kr.co.ch10.dto.UserDTO;
import kr.co.ch10.entity.User;
import kr.co.ch10.jwt.JwtProvider;
import kr.co.ch10.security.MyUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @GetMapping("/user")
    public ResponseEntity<User> user(Authentication authentication){

        log.info("authentication : {}", authentication);

        User user = (User) authentication.getPrincipal();
        log.info("user : {}", user);

        return ResponseEntity.ok().body(user);
    }

    //@RequestBody = JSON
    @PostMapping("/user/login")
    public ResponseEntity login(@RequestBody UserDTO userDTO){
        try {
            //security 인증 토큰 생성
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDTO.getUid(), userDTO.getPass());

            //security 인증 처리 (ID 와 PW 를 가지고 실질적인 사용자 DB 조회
            Authentication authentication = authenticationManager.authenticate(authToken);
            log.info("authentication: {}", authentication);

            //security 인증 사용자 가져오기
            MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
            log.info("myUserDetails: {}", myUserDetails);

            User user = myUserDetails.getUser();
            log.info("user: {}", user);

            //JWT 토큰 생성
            String accessToken = jwtProvider.createToken(user, 1);
            String refreshToken = jwtProvider.createToken(user, 7);
            log.info("accessToken: {}", accessToken);
            log.info("refreshToken: {}", refreshToken);

            //Refresh DB 저장(생략)

            //토큰 전송
            Map<String,Object> tokenMap = new HashMap<>();
            tokenMap.put("access_token", accessToken);
            tokenMap.put("refresh_token", refreshToken);

            return ResponseEntity
                    .ok()
                    .body(tokenMap);

        }catch (Exception e){
            
            //로그인(인증) 실패
            log.error(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND).body("user not found");
        }


    }

}
