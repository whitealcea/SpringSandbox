package com.example.springsandbox.controller;

import com.example.springsandbox.dto.HelloDto;
import com.example.springsandbox.service.HelloService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@Slf4j
public class HelloController {
    @NonNull
    private HelloService helloService;

    @RequestMapping("/hello")
    public String hello(Model model) {
        List<HelloDto> employeeList = helloService.getEmployeeList();
        model.addAttribute("employees", employeeList);
        return "hello";
    }
}
