package kr.co.ch06.controller;

import kr.co.ch06.dto.User1DTO;
import kr.co.ch06.service.User1Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j  // 로그 어노테이션
@Controller
@RequiredArgsConstructor // or @AllArgsConstructor
public class User1Controller {

    //롬복 어노테이션 사용
    private final User1Service user1Service;


    @GetMapping("/user1/list")
    public String list(Model model){

        List<User1DTO> user1List = user1Service.findAll();
        model.addAttribute("user1List", user1List);

        return "/user1/list";
    }

    @GetMapping("/user1/register")
    public String register(){

        return "/user1/register";
    }
    @PostMapping("/user1/register")
    public String register(User1DTO user1DTO){
        log.info("user1DTO: {}", user1DTO);
        
        //서비스호출
        user1Service.register(user1DTO);
        
        //리다이렉트
        return "redirect:/user1/list";
    }

    @GetMapping("/user1/modify")
    public String modify(String uid, Model model){
        log.info("uid: {}", uid);
        
        //수정 데이터 조회
        User1DTO user1DTO = user1Service.findById(uid);

        //모델 참조
        model.addAttribute(user1DTO);

        return "/user1/modify";
    }
    @PostMapping("/user1/modify")
    public String modify(User1DTO user1DTO){
        log.info("user1DTO: {}", user1DTO);
        
        //서비스 호출
        user1Service.modify(user1DTO);
        
        //리다이렉트
        return "redirect:/user1/list";
    }

    @GetMapping("/user1/delete")
    public String delete(String uid){
        log.info("uid: {}", uid);

        user1Service.delete(uid);

        return "redirect:/user1/delete";
    }

}
