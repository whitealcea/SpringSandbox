package com.example.springsandbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller("/login")
public class LoginController {

    @GetMapping
    public String login() {
        return "login";
    }

    @PostMapping
    public String loginSuccess() {
        return "top";
    }
}
