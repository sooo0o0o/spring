package kr.co.ch09.controller;

import jakarta.validation.Valid;
import kr.co.ch09.dto.User1DTO;
import kr.co.ch09.service.User1Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class User1Controller {

    private final User1Service user1Service;

    @ResponseBody
    @GetMapping("/user1")
    public List<User1DTO> list(){
        return user1Service.findAll();
    }

    // @ResponseBody 는 메서드의 반환값을 응답객체 본문으로 출력하기 위한 어노테이션
    // 자동 JSON 변환 출력
    @ResponseBody
    @GetMapping("/user1/{uid}")
    public User1DTO user(@PathVariable("uid") String uid){
        //@PathVariable 은 주소 파라미터 값을 바인딩하기 위한 어노테이션
        return user1Service.findById(uid);
    }

    /*
    @RequestBody
        - 요청 본문 내용에 포함된 데이터를 Java 객체로 변환하는 어노테이션
            => Json 데이터를 수신하기 위해서 사용

    @Valid
        - RestAPI 서비스 특성상 클라이언트에서 유효성 검사를 하기 어려우므로, 백엔드에서
        유효성 검사를 수행
        - 수신처리되는 DTO의 유효성 검사 어노테이션 사용
            => @NotBlank, @NotEmpty, @NotNull, @Email
        - @Valid 어노테이션으로 DTO의 유효성 검사 어노테이션을 수행

    */
    @PostMapping("/user1")
    public ResponseEntity<User1DTO> register(@Valid @RequestBody User1DTO user1DTO){
        log.info("user1DTO: {}", user1DTO);

        User1DTO savedUser1 = user1Service.save(user1DTO);

        //ResponseEntity 응답객체 반환시 @ResponseBody 어노테이션 생략 가능
        return ResponseEntity.ok(savedUser1);

    }

    @PutMapping("/user1")
    public ResponseEntity<User1DTO> modify(@RequestBody User1DTO user1DTO){
        log.info("user1DTO: {}", user1DTO);

        User1DTO modifiedUser1 = user1Service.modify(user1DTO);

        //ResponseEntity 객체를 사용하면 요청 결과에대한 다양한 사용자 정의가 가능하기 때문에 개발의 유연성이 높아짐
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(modifiedUser1);
    }

    @DeleteMapping("/user1/{uid}")
    public ResponseEntity<String> delete(@PathVariable("uid") String uid){

        boolean isdeleted = user1Service.delete(uid);

        if(isdeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("success");
        }

        return ResponseEntity.notFound().build();
    }

}
