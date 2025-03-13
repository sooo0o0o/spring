package kr.co.ch05.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.ch05.dto.User1DTO;
import kr.co.ch05.service.user1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class User1Controller {

    private final user1Service user1Service;

    @Autowired
    public User1Controller(user1Service user1Service) {
        this.user1Service = user1Service;
    }


    @RequestMapping(value = "/user1/list", method = RequestMethod.GET)
    public String list(Model model) {

        //데이터 조회
        List<User1DTO> user1DTOs = user1Service.findAll();

        //모델 참조
        model.addAttribute("user1DTOs", user1DTOs);


        //view forwarding
        return "list";
    }

    @GetMapping("/user1/register")
    public String register(){
        return "register";
    }

    @PostMapping("/user1/register")
    public String register(User1DTO user1DTO) {

        /*
        //데이터 수신
        String uid = req.getParameter("uid");
        String name = req.getParameter("name");
        String hp = req.getParameter("hp");
        String age = req.getParameter("age");

        //DTO 생성
        User1DTO user1DTO = new User1DTO();
        user1DTO.setUid(uid);
        user1DTO.setName(name);
        user1DTO.setHp(hp);
        user1DTO.setAge(Integer.parseInt(age));
        */

        //서비스 호출
        user1Service.register(user1DTO);

        //리다이렉트
        return "redirect:/user1/list";

    }

    @GetMapping("/user1/modify")
    public String modify(@RequestParam("uid") String uid, Model model) {

        //수정 데이터 조회
        User1DTO user1DTO = user1Service.findByUid(uid);

        //수정 출력 하기 위해 모델 참조
        model.addAttribute(user1DTO);

        return "modify";
    }

    @PostMapping("/user1/modify")
    public String modify(User1DTO user1DTO) {
        user1Service.modify(user1DTO);

        return "redirect:/user1/list";

    }

    @GetMapping("/user1/delete")
    public String delete(@RequestParam("uid") String uid) {
        user1Service.delete(uid);
        return "redirect:/user1/list";
    }



}
