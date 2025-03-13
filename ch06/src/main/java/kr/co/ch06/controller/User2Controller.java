package kr.co.ch06.controller;

import kr.co.ch06.dto.User2DTO;
import kr.co.ch06.service.User2Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Controller
public class User2Controller {
    private final User2Service user2Service;

    @GetMapping("/user2/list")
    public String list(Model model){
        log.info("list");
        List<User2DTO> user2List = user2Service.findAll();
        model.addAttribute("user2List", user2List);

        return "/user2/list";
    }

    @GetMapping("/user2/register")
    public String register(){

        return "/user2/register";
    }
    @PostMapping("/user2/register")
    public String register(User2DTO user2DTO){
        log.info("user2DTO: {}", user2DTO);

        user2Service.register(user2DTO);

        return "redirect:/user2/list";
    }

    @GetMapping("/user2/modify")
    public String modify(String uid, Model model){
        log.info("uid: {}", uid);

        User2DTO user2DTO = user2Service.findById(uid);

        model.addAttribute("user2DTO", user2DTO);

        return "/user2/modify";
    }
    @PostMapping("/user2/modify")
    public String modify(User2DTO user2DTO){
        log.info("user2DTO: {}", user2DTO);

        user2Service.modify(user2DTO);

        return "redirect:/user2/list";
    }

    @GetMapping("/user2/delete")
    public String delete(String uid){
        log.info("uid: {}", uid);

        user2Service.delete(uid);

        return "redirect:/user2/list";
    }



}
