package kr.co.ch08.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerController {

    @GetMapping({"/manager", "/manager/index"})
    public String index(){
        return "/manager/index";
    }

}
