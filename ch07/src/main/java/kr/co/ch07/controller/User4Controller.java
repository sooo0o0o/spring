package kr.co.ch07.controller;


import kr.co.ch07.dto.User4DTO;
import kr.co.ch07.service.User4Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class User4Controller {
    private final User4Service service;

    @GetMapping("/user4/list")
    public String list(Model model){
        List<User4DTO> user4DTOList = service.findAll();

        model.addAttribute("user4DTOList", user4DTOList);
        return "/user4/list";
    }

    @GetMapping("/user4/register")
    public String register(){
        return "/user4/register";
    }
    @PostMapping("/user4/register")
    public String register(User4DTO user4DTO){
        service.register(user4DTO);

        return "redirect:/user4/list";
    }

    @GetMapping("/user4/modify")
    public String modify(String uid, Model model){
        User4DTO user4DTO = service.findById(uid);

        model.addAttribute("user4DTO", user4DTO);

        return "/user4/modify";
    }
    @PostMapping("/user4/modify")
    public String modify(User4DTO user4DTO){
        System.out.println("수정된 성별: " + user4DTO.getGender());
        service.modify(user4DTO);

        return "redirect:/user4/list";
    }

    @GetMapping("/user4/delete")
    public String delete(String uid){
        service.delete(uid);
        return "redirect:/user4/list";
    }

}
