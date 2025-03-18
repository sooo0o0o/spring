package kr.co.ch07.controller;

import kr.co.ch07.dto.User3DTO;
import kr.co.ch07.service.User3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class User3Controller {

    private final User3Service service;

    @GetMapping("/user3/list")
    public String list(Model model) {
        List<User3DTO> user3DTOList = service.findAll();
        model.addAttribute("user3DTOList", user3DTOList);

        return "/user3/list";
    }

    @GetMapping("/user3/register")
    public String register() {
        return "/user3/register";
    }
    @PostMapping("/user3/register")
    public String register(User3DTO user3DTO) {
        service.register(user3DTO);

        return "redirect:/user3/list";
    }

    @GetMapping("/user3/modify")
    public String modify(String uid, Model model) {
        User3DTO user3DTO = service.findById(uid);

        model.addAttribute("user3DTO", user3DTO);
        return "/user3/modify";
    }
    @PostMapping("/user3/modify")
    public String modify(User3DTO user3DTO) {
        service.modify(user3DTO);
        return "redirect:/user3/list";
    }
    @GetMapping("/user3/delete")
    public String delete(String uid) {
        service.delete(uid);
        return "redirect:/user3/list";
    }

}
