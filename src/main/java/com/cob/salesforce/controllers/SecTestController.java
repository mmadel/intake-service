package com.cob.salesforce.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class SecTestController {

    @GetMapping("/all")
    public String sayGreeting(HttpServletRequest request) {
        request.getUserPrincipal();
        return "hello,world..!!";
    }

    @GetMapping("/user")
    public String sayGreetingUser(HttpServletRequest request) {
        request.getUserPrincipal();
        return "hello,world user..!!";
    }

    @GetMapping("/admin")
    public String sayGreetingAdmin() {
        return "hello,world admin..!!";
    }


}
