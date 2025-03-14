package kr.co.ch06.controller;

import kr.co.ch06.dto.User3DTO;
import kr.co.ch06.service.User3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class User3Controller {
    private final User3Service user3Service;

    @GetMapping("/user3/list")
    public String list(Model model) {
        log.info("list");
        List<User3DTO> user3List = user3Service.findAll();
        model.addAttribute("user3List", user3List);

        return "/user3/list";
    }

    @GetMapping("/user3/register")
    public String register() {
        return "/user3/register";
    }
    @PostMapping("/user3/register")
    public String register(User3DTO user3DTO) {
        log.info("user3DTO: {}", user3DTO);

        user3Service.register(user3DTO);

        return "redirect:/user3/list";
    }

    @GetMapping("/user3/modify")
    public String modify(String uid, Model model){
        log.info("uid: {}", uid);

        User3DTO user3DTO = user3Service.findById(uid);

        model.addAttribute("user3DTO", user3DTO);

        return "/user3/modify";
    }
    @PostMapping("/user3/modify")
    public String modify(User3DTO user3DTO) {
        log.info("user3DTO: {}", user3DTO);

        user3Service.modify(user3DTO);

        return "redirect:/user3/list";
    }

    @GetMapping("/user3/delete")
    public String delete(String uid) {
        log.info("uid: {}", uid);

        user3Service.delete(uid);

        return "redirect:/user3/list";
    }





}
