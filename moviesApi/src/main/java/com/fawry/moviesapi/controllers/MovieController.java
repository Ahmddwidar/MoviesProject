package com.fawry.moviesapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class MovieController {
    @GetMapping("/admin")
    public String adminHome(){
        return "admin home";
    }
    @GetMapping("/user")
    public String userHome(){
        return "user home";
    }
    @GetMapping
    public String home(){
        return "home";
    }
}
