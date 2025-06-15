package com.example.RandomUser.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/printer")
public class Printer {

    @GetMapping
    public String prnt(){
        return "deneme basarili";
    }
}
