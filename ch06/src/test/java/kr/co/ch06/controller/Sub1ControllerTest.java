package kr.co.ch06.controller;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@AutoConfigureMockMvc // MockMvc 주입을 위한 어노테이션
@SpringBootTest
class Sub1ControllerTest {

    //Tomcat 이 작동하지 않고 있으므로, 가상 MVC 객체를 생성
    @Autowired
    private MockMvc mockMvc;


    @Test
    void hello() throws Exception {

        for(int i=0; i<1000; i++){

            mockMvc.perform(get("/sub1/hello"))             //요청 주소
                    .andExpect(status().isOk())             //요청 결과 코드
                    .andExpect(view().name("/sub1/hello"))  //요청 결과 페이지
                    .andDo(print());                        //요청 테스트 결과 출력

        }


    }
    @RepeatedTest(1000)
    void repeatHello() throws Exception {
        mockMvc.perform(get("/sub1/hello"))
                .andExpect(status().isOk())
                .andExpect(view().name("/sub1/hello"))
                .andDo(print());
    }

    @Test
    void welcome() throws Exception {   //-> post 요청이 없어서 테스트 실패!

        mockMvc.perform(
                    post("/sub1/welcome")
                        .param("uid", "a101")             //요청 주소 + parameter
                        .param("name", "테스트")
                        .param("hp", "010-1234-1234")
                        .param("age", "22"))
                .andExpect(status().isOk())             //요청 결과 코드
                .andExpect(view().name("/sub1/welcome"))  //요청 결과 페이지
                .andDo(print());                        //요청 테스트 결과 출력

    }

    @Test
    void greeting() throws Exception {  //-> redirect 하지 않기 때문에 Test 실패!

        mockMvc.perform(
                post("/sub1/greeting"))
                .andExpect(status().is3xxRedirection())             //요청 결과 코드
                .andDo(print());                        //요청 테스트 결과 출력
    }
}