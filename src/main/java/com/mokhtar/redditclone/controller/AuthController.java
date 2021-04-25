package com.mokhtar.redditclone.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping(value = {"/login", "/auth/login.html"})
    public String getLogin(){
        return "auth/login";
    }

    @GetMapping("/register")
    public String getRegister(){
        return "auth/register";
    }

    @GetMapping("/profile")
    public String getProfile(){
        return "auth/profile";
    }

}
